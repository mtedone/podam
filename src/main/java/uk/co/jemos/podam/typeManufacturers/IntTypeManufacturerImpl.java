package uk.co.jemos.podam.typeManufacturers;

import org.apache.commons.lang3.StringUtils;

import uk.co.jemos.podam.api.AttributeMetadata;
import uk.co.jemos.podam.api.DataProviderStrategy;
import uk.co.jemos.podam.api.PodamUtils;
import uk.co.jemos.podam.common.PodamConstants;
import uk.co.jemos.podam.common.PodamIntValue;

import java.lang.reflect.Type;
import java.util.Map;

/**
 * Default int type manufacturer.
 *
 * Created by tedonema on 17/05/2015.
 *
 * @since 6.0.0.RELEASE
 */
public class IntTypeManufacturerImpl extends AbstractTypeManufacturer<Integer> {

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer getType(DataProviderStrategy strategy,
            AttributeMetadata attributeMetadata,
            Map<String, Type> genericTypesArgumentsMap) {

        Integer retValue;

        PodamIntValue annotationStrategy = findElementOfType(
                attributeMetadata.getAttributeAnnotations(), PodamIntValue.class);

        if (null != annotationStrategy) {

            String numValueStr = annotationStrategy.numValue();
            if (StringUtils.isNotEmpty(numValueStr)) {
                try {
                    retValue = Integer.valueOf(numValueStr);
                } catch (NumberFormatException nfe) {
                    throw new IllegalArgumentException(PodamConstants.THE_ANNOTATION_VALUE_STR
                            + numValueStr
                            + " could not be converted to an Integer. An exception will be thrown.",
                            nfe);
                }
            } else {

                int minValue = annotationStrategy.minValue();
                int maxValue = annotationStrategy.maxValue();

                // Sanity check
                if (minValue > maxValue) {
                    maxValue = minValue;
                }

                retValue = getIntegerInRange(minValue, maxValue,
                        attributeMetadata);
            }
        } else {
            retValue = getInteger(attributeMetadata);
        }

        return retValue;
    }

	/** It returns an int/Integer value.
	 *
	 * @param attributeMetadata
	 *            attribute metadata for instance to be fetched
	 * @return A int/Integer value
	 */
	public Integer getInteger(AttributeMetadata attributeMetadata) {

		return getInteger(Integer.MAX_VALUE);
	}

	/**
	 * It returns an int/Integer value between min and max value (included).
	 * 
	 * @param minValue
	 *            The minimum value for the returned value
	 * @param maxValue
	 *            The maximum value for the returned value
	 * @param attributeMetadata
	 *            attribute metadata for instance to be fetched
	 * @return An int/Integer value between min and max value (included).
	 */
	public int getIntegerInRange(int minValue, int maxValue,
			AttributeMetadata attributeMetadata) {

		return PodamUtils.getIntegerInRange(minValue, maxValue);
	}

}
