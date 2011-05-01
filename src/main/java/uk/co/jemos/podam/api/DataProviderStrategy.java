/**
 * 
 */
package uk.co.jemos.podam.api;

/**
 * This interface defines the contact for PODAM data providers.
 * <p>
 * PODAM is a tool to fill POJOs with data. There are different requirements
 * when it comes to which data POJOs should be filled with. The default strategy
 * adopted by PODAM is to fill POJOs with random data. However other
 * requirements (e.g. http://www.jemos.eu/jira/browse/PDM-19) might want to
 * assign deterministic data using sequences, or other predictable sources of
 * data. In order to do so, all clients of PODAM will have to do is to provide
 * an implementation of this interface and pass it to the {@link PodamFactory}
 * method which manufactures a POJO.
 * </p>
 * 
 * @author mtedone
 * 
 */
public interface DataProviderStrategy {

	/** It returns a boolean/Boolean value. */
	public Boolean getBoolean();

	/** It returns a byte/Byte value. */
	public Byte getByte();

	/**
	 * It returns a byte/Byte within min and max value (included).
	 * 
	 * @param minValue
	 *            The minimum value for the returned value
	 * @param maxValue
	 *            The maximum value for the returned value
	 * @return A byte/Byte within min and max value (included).
	 */
	public Byte getByteInRange(byte minValue, byte maxValue);

	/** It returns a char/Character value. */
	public Character getCharacter();

	/**
	 * It returns a char/Character value between min and max value (included).
	 * 
	 * @param minValue
	 *            The minimum value for the returned value
	 * @param maxValue
	 *            The maximum value for the returned value
	 * @return A char/Character value between min and max value (included).
	 */
	public Character getCharacterInRange(char minValue, char maxValue);

	/** It returns a double/Double value */
	public Double getDouble();

	/**
	 * It returns a double/Double value between min and max value (included).
	 * 
	 * @param minValue
	 *            The minimum value for the returned value
	 * @param maxValue
	 *            The maximum value for the returned value
	 * @return A double/Double value between min and max value (included)
	 */
	public Double getDoubleInRange(double minValue, double maxValue);

	/** It returns a float/Float value. */
	public Float getFloat();

	/**
	 * It returns a float/Float value between min and max value (included).
	 * 
	 * @param minValue
	 *            The minimum value for the returned value
	 * @param maxValue
	 *            The maximum value for the returned value
	 * @return A float/Float value between min and max value (included).
	 */
	public Float getFloatInRange(float minValue, float maxValue);

	/** It returns an int/Integer value. */
	public Integer getInteger();

	/**
	 * It returns an int/Integer value between min and max value (included).
	 * 
	 * @param minValue
	 *            The minimum value for the returned value
	 * @param maxValue
	 *            The maximum value for the returned value
	 * @return An int/Integer value between min and max value (included).
	 */
	public int getIntegerInRange(int minValue, int maxValue);

	/** It returns a long/Long value. */
	public Long getLong();

	/**
	 * It returns a long/Long value between min and max value (included).
	 * 
	 * @param minValue
	 *            The minimum value for the returned value
	 * @param maxValue
	 *            The maximum value for the returned value
	 * @return A long/Long value between min and max value (included).
	 */
	public Long getLongInRange(long minValue, long maxValue);

	/** It returns a short/Short value. */
	public Short getShort();

	/**
	 * It returns a short/Short value between min and max value (included).
	 * 
	 * @param minValue
	 *            The minimum value for the returned value
	 * @param maxValue
	 *            The maximum value for the returned value
	 * @return A short/Short value between min and max value (included).
	 */
	public Short getShortInRange(short minValue, short maxValue);

	/** It returns a string value */
	public String getStringValue();

	/**
	 * It returns a String of {@code length} characters.
	 * 
	 * @param length
	 *            The number of characters required in the returned String
	 * @return A String of {@code length} characters
	 */
	public String getStringOfLength(int length);

}
