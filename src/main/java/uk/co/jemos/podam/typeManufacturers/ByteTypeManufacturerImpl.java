package uk.co.jemos.podam.typeManufacturers;


import org.apache.commons.lang3.StringUtils;

import uk.co.jemos.podam.api.AttributeMetadata;
import uk.co.jemos.podam.api.DataProviderStrategy;
import uk.co.jemos.podam.api.PodamUtils;
import uk.co.jemos.podam.common.PodamByteValue;

import java.lang.reflect.Type;
import java.util.Map;

/**
 * Default byte type manufacturer.
 *
 * Created by tedonema on 17/05/2015.
 *
 * @since 6.0.0.RELEASE
 */
public class ByteTypeManufacturerImpl extends AbstractTypeManufacturer<Byte> {

    /**
     * {@inheritDoc}
     */
    @Override
    public Byte getType(DataProviderStrategy strategy,
            AttributeMetadata attributeMetadata,
            Map<String, Type> genericTypesArgumentsMap) {

        Byte retValue;

        PodamByteValue annotationStrategy = findElementOfType(
                attributeMetadata.getAttributeAnnotations(), PodamByteValue.class);

        if (null != annotationStrategy) {

            String numValueStr = annotationStrategy.numValue();
            if (StringUtils.isNotEmpty(numValueStr)) {
                try {

                    retValue = Byte.valueOf(numValueStr);
                } catch (NumberFormatException nfe) {
                    throw new IllegalArgumentException("The precise value: "
                            + numValueStr
                            + " cannot be converted to a byte type. An exception will be thrown.",
                            nfe);
                }
            } else {
                byte minValue = annotationStrategy.minValue();
                byte maxValue = annotationStrategy.maxValue();

                // Sanity check
                if (minValue > maxValue) {
                    maxValue = minValue;
                }

                retValue = getByteInRange(minValue, maxValue,
                        attributeMetadata);
            }
        } else {
            retValue = getByte(attributeMetadata);
        }

        return retValue;
    }

	/** It returns a byte/Byte value.
	 * 
	 * @param attributeMetadata
	 *            attribute metadata for instance to be fetched
	 * @return a boolean/Boolean value
	 */
	public Byte getByte(AttributeMetadata attributeMetadata) {

		return (byte)getInteger(Byte.MAX_VALUE);
	}

	/**
	 * It returns a byte/Byte within min and max value (included).
	 * 
	 * @param minValue
	 *            The minimum value for the returned value
	 * @param maxValue
	 *            The maximum value for the returned value
	 * @param attributeMetadata
	 *            attribute metadata for instance to be fetched
	 * @return A byte/Byte within min and max value (included).
	 */
	public Byte getByteInRange(byte minValue, byte maxValue,
			AttributeMetadata attributeMetadata) {

		return (byte)PodamUtils.getIntegerInRange(minValue, maxValue);
	}

}
