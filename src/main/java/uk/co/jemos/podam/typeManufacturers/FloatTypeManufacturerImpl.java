package uk.co.jemos.podam.typeManufacturers;

import org.apache.commons.lang3.StringUtils;

import uk.co.jemos.podam.api.AttributeMetadata;
import uk.co.jemos.podam.api.DataProviderStrategy;
import uk.co.jemos.podam.api.PodamUtils;
import uk.co.jemos.podam.common.PodamConstants;
import uk.co.jemos.podam.common.PodamFloatValue;

import java.lang.reflect.Type;
import java.util.Map;

/**
 * Default float type manufacturer.
 *
 * Created by tedonema on 17/05/2015.
 *
 * @since 6.0.0.RELEASE
 */
public class FloatTypeManufacturerImpl extends AbstractTypeManufacturer<Float> {

    /**
     * {@inheritDoc}
     */
    @Override
    public Float getType(DataProviderStrategy strategy,
            AttributeMetadata attributeMetadata,
            Map<String, Type> genericTypesArgumentsMap) {

        Float retValue;

        PodamFloatValue annotationStrategy = findElementOfType(
                attributeMetadata.getAttributeAnnotations(), PodamFloatValue.class);

        if (null != annotationStrategy) {

            String numValueStr = annotationStrategy.numValue();
            if (StringUtils.isNotEmpty(numValueStr)) {
                try {
                    retValue = Float.valueOf(numValueStr);
                } catch (NumberFormatException nfe) {
                    throw new IllegalArgumentException(PodamConstants.THE_ANNOTATION_VALUE_STR
                            + numValueStr
                            + " could not be converted to a Float. An exception will be thrown.",
                            nfe);
                }
            } else {

                float minValue = annotationStrategy.minValue();
                float maxValue = annotationStrategy.maxValue();

                // Sanity check
                if (minValue > maxValue) {
                    maxValue = minValue;
                }

                retValue = getFloatInRange(minValue, maxValue,
                        attributeMetadata);
            }
        } else {
            retValue = getFloat(attributeMetadata);
        }

        return retValue;
    }

	/** It returns a float/Float value.
	 * 
	 * @param attributeMetadata
	 *            attribute metadata for instance to be fetched
	 * @return A float/Float value
	 */
	public Float getFloat(AttributeMetadata attributeMetadata) {

		return (float)getDouble();
	}

	/**
	 * It returns a float/Float value between min and max value (included).
	 * 
	 * @param minValue
	 *            The minimum value for the returned value
	 * @param maxValue
	 *            The maximum value for the returned value
	 * @param attributeMetadata
	 *            attribute metadata for instance to be fetched
	 * @return A float/Float value between min and max value (included).
	 */
	public Float getFloatInRange(float minValue, float maxValue,
			AttributeMetadata attributeMetadata) {

		return (float)PodamUtils.getDoubleInRange(minValue, maxValue);
	}

}
