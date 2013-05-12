/**
 * 
 */
package uk.co.jemos.podam.api;

import uk.co.jemos.podam.dto.AttributeMetadata;

/**
 * This interface defines the contact for PODAM data providers.
 * <p>
 * PODAM is a tool to fill POJOs with data. There are different requirements
 * when it comes to which data POJOs should be filled with. The default strategy
 * adopted by PODAM is to fill POJOs with random data. However other
 * requirements might dictate to assign deterministic data using sequences, or
 * other predictable sources of data. In order to do so, clients of PODAM will
 * have to provide an implementation of this interface and pass it to the
 * constructor of the {@link PodamFactoryImpl} class.
 * </p>
 * 
 * @author mtedone
 * 
 * @since 1.0.0
 * 
 */
public interface DataProviderStrategy {

	/** It returns a boolean/Boolean value. */
	public Boolean getBoolean(AttributeMetadata attributeMetadata);

	/** It returns a byte/Byte value. */
	public Byte getByte(AttributeMetadata attributeMetadata);

	/**
	 * It returns a byte/Byte within min and max value (included).
	 * 
	 * @param minValue
	 *            The minimum value for the returned value
	 * @param maxValue
	 *            The maximum value for the returned value
	 * @return A byte/Byte within min and max value (included).
	 */
	public Byte getByteInRange(byte minValue, byte maxValue,
			AttributeMetadata attributeMetadata);

	/** It returns a char/Character value. */
	public Character getCharacter(AttributeMetadata attributeMetadata);

	/**
	 * It returns a char/Character value between min and max value (included).
	 * 
	 * @param minValue
	 *            The minimum value for the returned value
	 * @param maxValue
	 *            The maximum value for the returned value
	 * @return A char/Character value between min and max value (included).
	 */
	public Character getCharacterInRange(char minValue, char maxValue,
			AttributeMetadata attributeMetadata);

	/** It returns a double/Double value */
	public Double getDouble(AttributeMetadata attributeMetadata);

	/**
	 * It returns a double/Double value between min and max value (included).
	 * 
	 * @param minValue
	 *            The minimum value for the returned value
	 * @param maxValue
	 *            The maximum value for the returned value
	 * @return A double/Double value between min and max value (included)
	 */
	public Double getDoubleInRange(double minValue, double maxValue,
			AttributeMetadata attributeMetadata);

	/** It returns a float/Float value. */
	public Float getFloat(AttributeMetadata attributeMetadata);

	/**
	 * It returns a float/Float value between min and max value (included).
	 * 
	 * @param minValue
	 *            The minimum value for the returned value
	 * @param maxValue
	 *            The maximum value for the returned value
	 * @return A float/Float value between min and max value (included).
	 */
	public Float getFloatInRange(float minValue, float maxValue,
			AttributeMetadata attributeMetadata);

	/** It returns an int/Integer value. */
	public Integer getInteger(AttributeMetadata attributeMetadata);

	/**
	 * It returns an int/Integer value between min and max value (included).
	 * 
	 * @param minValue
	 *            The minimum value for the returned value
	 * @param maxValue
	 *            The maximum value for the returned value
	 * @return An int/Integer value between min and max value (included).
	 */
	public int getIntegerInRange(int minValue, int maxValue,
			AttributeMetadata attributeMetadata);

	/** It returns a long/Long value. */
	public Long getLong(AttributeMetadata attributeMetadata);

	/**
	 * It returns a long/Long value between min and max value (included).
	 * 
	 * @param minValue
	 *            The minimum value for the returned value
	 * @param maxValue
	 *            The maximum value for the returned value
	 * @return A long/Long value between min and max value (included).
	 */
	public Long getLongInRange(long minValue, long maxValue,
			AttributeMetadata attributeMetadata);

	/** It returns a short/Short value. */
	public Short getShort(AttributeMetadata attributeMetadata);

	/**
	 * It returns a short/Short value between min and max value (included).
	 * 
	 * @param minValue
	 *            The minimum value for the returned value
	 * @param maxValue
	 *            The maximum value for the returned value
	 * @return A short/Short value between min and max value (included).
	 */
	public Short getShortInRange(short minValue, short maxValue,
			AttributeMetadata attributeMetadata);

	/** It returns a string value */
	public String getStringValue(AttributeMetadata attributeMetadata);

	/**
	 * It returns a String of {@code length} characters.
	 * 
	 * @param length
	 *            The number of characters required in the returned String
	 * @return A String of {@code length} characters
	 */
	public String getStringOfLength(int length,
			AttributeMetadata attributeMetadata);

	/**
	 * Returns the number of default collection elements.
	 * <p>
	 * Implementations of this interface need to provide this value.
	 * </p>
	 * 
	 * @return The number of default collection elements
	 */
	public int getNumberOfCollectionElements();

}
