package uk.co.jemos.podam.typeManufacturers;

import org.apache.commons.lang3.StringUtils;

import uk.co.jemos.podam.api.AttributeMetadata;
import uk.co.jemos.podam.api.DataProviderStrategy;
import uk.co.jemos.podam.api.PodamUtils;
import uk.co.jemos.podam.common.PodamConstants;
import uk.co.jemos.podam.common.PodamLongValue;

import java.lang.reflect.Type;
import java.util.Map;

/**
 * Default int type manufacturer.
 *
 * Created by tedonema on 17/05/2015.
 *
 * @since 6.0.0.RELEASE
 */
public class LongTypeManufacturerImpl extends AbstractTypeManufacturer<Long> {

    /**
     * {@inheritDoc}
     */
    @Override
    public Long getType(DataProviderStrategy strategy,
            AttributeMetadata attributeMetadata,
            Map<String, Type> genericTypesArgumentsMap) {

        Long retValue;

        PodamLongValue annotationStrategy = findElementOfType(
                attributeMetadata.getAttributeAnnotations(), PodamLongValue.class);

        if (null != annotationStrategy) {

            String numValueStr = annotationStrategy.numValue();
            if (StringUtils.isNotEmpty(numValueStr)) {
                try {
                    retValue = Long.valueOf(numValueStr);
                } catch (NumberFormatException nfe) {
                    throw new IllegalArgumentException(PodamConstants.THE_ANNOTATION_VALUE_STR
                            + numValueStr
                            + " could not be converted to a Long. An exception will be thrown.",
                            nfe);
                }
            } else {

                long minValue = annotationStrategy.minValue();
                long maxValue = annotationStrategy.maxValue();

                // Sanity check
                if (minValue > maxValue) {
                   maxValue = minValue;
                }

                retValue = getLongInRange(minValue, maxValue,
                        attributeMetadata);
            }
        } else {
            retValue = getLong(attributeMetadata);
        }

        return retValue;
    }

    /** It returns a long/Long value.
	 *
	 * @param attributeMetadata
	 *            attribute metadata for instance to be fetched
	 * @return A long/Long value
	 * */
	public Long getLong(AttributeMetadata attributeMetadata) {

		return System.nanoTime();
	}

	/**
	 * It returns a long/Long value between min and max value (included).
	 * 
	 * @param minValue
	 *            The minimum value for the returned value
	 * @param maxValue
	 *            The maximum value for the returned value
	 * @param attributeMetadata
	 *            attribute metadata for instance to be fetched
	 * @return A long/Long value between min and max value (included).
	 */
	public Long getLongInRange(long minValue, long maxValue,
			AttributeMetadata attributeMetadata) {

		return PodamUtils.getLongInRange(minValue, maxValue);
	}

}
