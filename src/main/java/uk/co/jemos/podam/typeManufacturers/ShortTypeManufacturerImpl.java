package uk.co.jemos.podam.typeManufacturers;

import org.apache.commons.lang3.StringUtils;

import uk.co.jemos.podam.api.AttributeMetadata;
import uk.co.jemos.podam.api.DataProviderStrategy;
import uk.co.jemos.podam.api.PodamUtils;
import uk.co.jemos.podam.common.PodamShortValue;

import java.lang.reflect.Type;
import java.util.Map;

/**
 * Default short type manufacturer.
 *
 * Created by tedonema on 17/05/2015.
 *
 * @since 6.0.0.RELEASE
 */
public class ShortTypeManufacturerImpl extends AbstractTypeManufacturer<Short> {

    /**
     * {@inheritDoc}
     */
    @Override
    public Short getType(DataProviderStrategy strategy,
            AttributeMetadata attributeMetadata,
            Map<String, Type> genericTypesArgumentsMap) {

        Short retValue;

        PodamShortValue annotationStrategy = findElementOfType(
                attributeMetadata.getAttributeAnnotations(), PodamShortValue.class);

        if (null != annotationStrategy) {

            String numValueStr = annotationStrategy.numValue();
                if (StringUtils.isNotEmpty(numValueStr)) {
                try {
                    retValue = Short.valueOf(numValueStr);
                } catch (NumberFormatException nfe) {
                    throw new IllegalArgumentException("The precise value: "
                            + numValueStr
                            + " cannot be converted to a short type. An exception will be thrown.",
                            nfe);
                }
            } else {

                short minValue = annotationStrategy.minValue();
                short maxValue = annotationStrategy.maxValue();

                // Sanity check
                if (minValue > maxValue) {
                    maxValue = minValue;
                }

                retValue = getShortInRange(minValue, maxValue,
                        attributeMetadata);
            }
        } else {
            retValue = getShort(attributeMetadata);
        }

        return retValue;
    }

    /** It returns a short/Short value.
	 *
	 * @param attributeMetadata
	 *            attribute metadata for instance to be fetched
	 * @return A short/Short value.
	 */
	public Short getShort(AttributeMetadata attributeMetadata) {

		return (short)getInteger(Short.MAX_VALUE);
	}

	/**
	 * It returns a short/Short value between min and max value (included).
	 * 
	 * @param minValue
	 *            The minimum value for the returned value
	 * @param maxValue
	 *            The maximum value for the returned value
	 * @param attributeMetadata
	 *            attribute metadata for instance to be fetched
	 * @return A short/Short value between min and max value (included).
	 */
	public Short getShortInRange(short minValue, short maxValue,
			AttributeMetadata attributeMetadata) {

		return (short)PodamUtils.getIntegerInRange(minValue, maxValue);
	}

}
