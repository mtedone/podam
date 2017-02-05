package uk.co.jemos.podam.typeManufacturers;


import org.apache.commons.lang3.StringUtils;

import uk.co.jemos.podam.api.AttributeMetadata;
import uk.co.jemos.podam.api.DataProviderStrategy;
import uk.co.jemos.podam.api.PodamUtils;
import uk.co.jemos.podam.common.PodamConstants;
import uk.co.jemos.podam.common.PodamDoubleValue;

import java.lang.reflect.Type;
import java.util.Map;

/**
 * Default double type manufacturer.
 *
 * Created by tedonema on 17/05/2015.
 *
 * @since 6.0.0.RELEASE
 */
public class DoubleTypeManufacturerImpl extends AbstractTypeManufacturer<Double> {

    /**
     * {@inheritDoc}
     */
    @Override
    public Double getType(DataProviderStrategy strategy,
            AttributeMetadata attributeMetadata,
            Map<String, Type> genericTypesArgumentsMap) {

        Double retValue;

        PodamDoubleValue annotationStrategy = findElementOfType(
                attributeMetadata.getAttributeAnnotations(), PodamDoubleValue.class);

        if (null != annotationStrategy) {

            String numValueStr = annotationStrategy.numValue();
            if (StringUtils.isNotEmpty(numValueStr)) {

                try {
                    retValue = Double.valueOf(numValueStr);
                } catch (NumberFormatException nfe) {
                    throw new IllegalArgumentException(PodamConstants.THE_ANNOTATION_VALUE_STR
                            + numValueStr
                            + " could not be converted to a Double. An exception will be thrown.",
                            nfe);
                }
            } else {

                double minValue = annotationStrategy.minValue();
                double maxValue = annotationStrategy.maxValue();

                // Sanity check
                if (minValue > maxValue) {
                    maxValue = minValue;
                }

                retValue = getDoubleInRange(minValue, maxValue,
                         attributeMetadata);
            }
        } else {
            retValue = getDouble(attributeMetadata);
        }

        return retValue;
    }

    /** It returns a double/Double value
	 *
	 * @param attributeMetadata
	 *            attribute metadata for instance to be fetched
	 * @return a double/Double value
	 */
	public Double getDouble(AttributeMetadata attributeMetadata) {

		return getDouble();
	}

	/**
	 * It returns a double/Double value between min and max value (included).
	 * 
	 * @param minValue
	 *            The minimum value for the returned value
	 * @param maxValue
	 *            The maximum value for the returned value
	 * @param attributeMetadata
	 *            attribute metadata for instance to be fetched
	 * @return A double/Double value between min and max value (included)
	 */
	public Double getDoubleInRange(double minValue, double maxValue,
			AttributeMetadata attributeMetadata) {

		return PodamUtils.getDoubleInRange(minValue, maxValue);
	}

}
