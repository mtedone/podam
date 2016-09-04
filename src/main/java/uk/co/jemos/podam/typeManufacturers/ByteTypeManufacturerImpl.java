package uk.co.jemos.podam.typeManufacturers;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import uk.co.jemos.podam.api.AttributeMetadata;
import uk.co.jemos.podam.api.DataProviderStrategy;
import uk.co.jemos.podam.common.PodamByteValue;

import java.lang.annotation.Annotation;
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

    /** The application logger */
    private static final Logger LOG = LoggerFactory.getLogger(ByteTypeManufacturerImpl.class);

    /**
     * {@inheritDoc}
     */
    @Override
    public Byte getType(DataProviderStrategy strategy,
            AttributeMetadata attributeMetadata,
            Map<String, Type> genericTypesArgumentsMap) {

        Byte retValue = null;

        for (Annotation annotation : attributeMetadata.getAttributeAnnotations()) {

            if (PodamByteValue.class.isAssignableFrom(annotation.getClass())) {
                PodamByteValue intStrategy = (PodamByteValue) annotation;

                String numValueStr = intStrategy.numValue();
                if (null != numValueStr && !numValueStr.isEmpty()) {
                    try {

                        retValue = Byte.valueOf(numValueStr);

                    } catch (NumberFormatException nfe) {
                        String errMsg = "The precise value: "
                                + numValueStr
                                + " cannot be converted to a byte type. An exception will be thrown.";
                        LOG.error(errMsg);
                        throw new IllegalArgumentException(errMsg, nfe);
                    }
                } else {
                    byte minValue = intStrategy.minValue();
                    byte maxValue = intStrategy.maxValue();

                    // Sanity check
                    if (minValue > maxValue) {
                        maxValue = minValue;
                    }

                    retValue = getByteInRange(minValue, maxValue,
                            attributeMetadata);
                }

                break;

            }
        }

        if (retValue == null) {
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

		byte nextByte;
		do {
			nextByte = (byte) RANDOM.nextInt(Byte.MAX_VALUE);
		} while (nextByte == 0);
		return nextByte;
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

		return (byte) (minValue + Math.random() * (maxValue - minValue) + 0.5);
	}

}
