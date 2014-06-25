/**
 *
 */
package uk.co.jemos.podam.api;

import java.lang.reflect.Constructor;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import uk.co.jemos.podam.common.ConstructorComparator;
import uk.co.jemos.podam.common.PodamConstants;

/**
 * Default implementation of a {@link DataProviderStrategy}
 * <p>
 * This default implementation returns values based on a random generator.
 * <b>Don't use this implementation if you seek deterministic values</b>
 * </p>
 *
 * <p>
 * All values returned by this implementation are <b>different from zero</b>.
 * </p>
 *
 * <p>
 * This implementation is a Singleton
 * </p>
 *
 * @author mtedone
 *
 * @since 1.0.0
 *
 */

public final class RandomDataProviderStrategy implements DataProviderStrategy {

	// ------------------->> Constants

	/** A RANDOM generator */
	private static final Random RANDOM = new Random(System.currentTimeMillis());

	/** The singleton instance of this implementation */
	private static final RandomDataProviderStrategy SINGLETON = new RandomDataProviderStrategy();

	/** The constructor comparator */
	private static final ConstructorComparator constructorComparator = new ConstructorComparator();

	/** An array of valid String characters */
	public static final char[] NICE_ASCII_CHARACTERS = new char[] { 'a', 'b',
			'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o',
			'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', 'A', 'B',
			'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O',
			'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', '0', '1',
			'2', '3', '4', '5', '6', '7', '8', '9', '_' };

	/**
	 * How many times it is allowed to PODAM to create an instance of the same
	 * class in a recursive hierarchy
	 */
	public static final int MAX_DEPTH = 1;

	/** The default number of collection elements for this strategy */
	public static final int DEFAULT_NBR_COLLECTION_ELEMENTS = 5;

	/** The max stack trace depth. */
	private int maxDepth = MAX_DEPTH;

	/** The number of collection elements. */
	private int nbrOfCollectionElements;

	/**
	 * A list of user-submitted specific implementations for interfaces and
	 * abstract classes
	 */
	private final Map<Class<?>, Class<?>> specificTypes = new HashMap<Class<?>, Class<?>>();

	// ------------------->> Instance / Static variables

	// ------------------->> Constructors

	/**
	 * Implementation of the Singleton pattern
	 */
	private RandomDataProviderStrategy() {
		this(DEFAULT_NBR_COLLECTION_ELEMENTS);
	}

	private RandomDataProviderStrategy(int nbrOfCollectionElements) {
		this.nbrOfCollectionElements = nbrOfCollectionElements;
	}

	// ------------------->> Public methods

	/**
	 * Implementation of the Singleton pattern
	 *
	 * @return A singleton instance of this class
	 */
	public static RandomDataProviderStrategy getInstance() {
		return SINGLETON;
	}

	/**
	 * Other factory method which assigns a default number of collection
	 * elements before returning the singleton.
	 *
	 * @param nbrCollectionElements
	 *            The number of collection elements
	 * @return The Singleton, set with the number of collection elements set as
	 *         parameter
	 */
	public static RandomDataProviderStrategy getInstance(
			int nbrCollectionElements) {

		SINGLETON.setNumberOfCollectionElements(nbrCollectionElements);
		return SINGLETON;

	}

	/**
	 * {@inheritDoc}
	 */

	@Override
	public Boolean getBoolean(AttributeMetadata attributeMetadata) {
		return Boolean.TRUE;
	}

	/**
	 * {@inheritDoc}
	 */

	@Override
	public Byte getByte(AttributeMetadata attributeMetadata) {
		byte nextByte = (byte) RANDOM.nextInt(Byte.MAX_VALUE);
		while (nextByte == 0) {
			nextByte = (byte) RANDOM.nextInt(Byte.MAX_VALUE);
		}
		return nextByte;
	}

	/**
	 * {@inheritDoc}
	 */

	@Override
	public Byte getByteInRange(byte minValue, byte maxValue,
			AttributeMetadata attributeMetadata) {
		// This can happen. It's a way to specify a precise value
		if (minValue == maxValue) {
			return minValue;
		}
		byte retValue = (byte) (minValue + (byte) (Math.random() * (maxValue
				- minValue + 1)));
		while (retValue < minValue || retValue > maxValue) {
			retValue = (byte) (minValue + (byte) (Math.random() * (maxValue
					- minValue + 1)));
		}
		return retValue;
	}

	/**
	 * {@inheritDoc}
	 */

	@Override
	public Character getCharacter(AttributeMetadata attributeMetadata) {

		int randomCharIdx = getIntegerInRange(0,
				NICE_ASCII_CHARACTERS.length - 1, attributeMetadata);

		int charToReturnIdx = randomCharIdx % NICE_ASCII_CHARACTERS.length;

		return NICE_ASCII_CHARACTERS[charToReturnIdx];

	}

	/**
	 * {@inheritDoc}
	 */

	@Override
	public Character getCharacterInRange(char minValue, char maxValue,
			AttributeMetadata attributeMetadata) {
		// This can happen. It's a way to specify a precise value
		if (minValue == maxValue) {
			return minValue;
		}
		char retValue = (char) (minValue + (char) (Math.random() * (maxValue
				- minValue + 1)));
		while (retValue < minValue || retValue > maxValue) {
			retValue = (char) (minValue + (char) (Math.random() * (maxValue
					- minValue + 1)));
		}

		return retValue;
	}

	/**
	 * {@inheritDoc}
	 */

	@Override
	public Double getDouble(AttributeMetadata attributeMetadata) {
		double retValue = RANDOM.nextDouble();
		while (retValue == 0.0) {
			retValue = RANDOM.nextDouble();
		}
		return retValue;
	}

	/**
	 * {@inheritDoc}
	 */

	@Override
	public Double getDoubleInRange(double minValue, double maxValue,
			AttributeMetadata attributeMetadata) {
		// This can happen. It's a way to specify a precise value
		if (minValue == maxValue) {
			return minValue;
		}
		double retValue = minValue + Math.random() * (maxValue - minValue + 1);
		while (retValue < minValue || retValue > maxValue) {
			retValue = minValue + Math.random() * (maxValue - minValue + 1);
		}
		return retValue;
	}

	/**
	 * {@inheritDoc}
	 */

	@Override
	public Float getFloat(AttributeMetadata attributeMetadata) {
		float retValue = RANDOM.nextFloat();
		while (retValue == 0.0f) {
			retValue = RANDOM.nextFloat();
		}
		return retValue;
	}

	/**
	 * {@inheritDoc}
	 */

	@Override
	public Float getFloatInRange(float minValue, float maxValue,
			AttributeMetadata attributeMetadata) {
		// This can happen. It's a way to specify a precise value
		if (minValue == maxValue) {
			return minValue;
		}
		float retValue = minValue
				+ (float) (Math.random() * (maxValue - minValue + 1));
		while (retValue < minValue || retValue > maxValue) {
			retValue = minValue
					+ (float) (Math.random() * (maxValue - minValue + 1));
		}
		return retValue;
	}

	/**
	 * {@inheritDoc}
	 */

	@Override
	public Integer getInteger(AttributeMetadata attributeMetadata) {
		Integer retValue = RANDOM.nextInt();
		while (retValue.intValue() == 0) {
			retValue = RANDOM.nextInt();
		}
		return retValue;
	}

	/**
	 * {@inheritDoc}
	 */

	@Override
	public int getIntegerInRange(int minValue, int maxValue,
			AttributeMetadata attributeMetadata) {
		// This can happen. It's a way to specify a precise value
		if (minValue == maxValue) {
			return minValue;
		}
		int retValue = minValue
				+ (int) (Math.random() * (maxValue - minValue + 1));
		while (retValue < minValue || retValue > maxValue) {
			retValue = minValue
					+ (int) (Math.random() * (maxValue - minValue + 1));
		}
		return retValue;
	}

	/**
	 * This implementation returns the current time in milliseconds.
	 * <p>
	 * This can be useful for Date-like constructors which accept a long as
	 * argument. A complete random number would cause the instantiation of such
	 * classes to fail on a non-deterministic basis, e.g. when the random long
	 * would not be an acceptable value for, say, a YEAR field.
	 * </p>
	 * {@inheritDoc}
	 */

	@Override
	public Long getLong(AttributeMetadata attributeMetadata) {
		return System.currentTimeMillis();
	}

	/**
	 * {@inheritDoc}
	 */

	@Override
	public Long getLongInRange(long minValue, long maxValue,
			AttributeMetadata attributeMetadata) {
		// This can happen. It's a way to specify a precise value
		if (minValue == maxValue) {
			return minValue;
		}
		long retValue = minValue
				+ (long) (Math.random() * (maxValue - minValue + 1));
		while (retValue < minValue || retValue > maxValue) {
			retValue = minValue
					+ (long) (Math.random() * (maxValue - minValue + 1));
		}
		return retValue;
	}

	/**
	 * {@inheritDoc}
	 */

	@Override
	public Short getShort(AttributeMetadata attributeMetadata) {
		short retValue = (short) RANDOM.nextInt(Byte.MAX_VALUE);
		while (retValue == 0) {
			retValue = (short) RANDOM.nextInt(Byte.MAX_VALUE);
		}
		return retValue;
	}

	/**
	 * {@inheritDoc}
	 */

	@Override
	public Short getShortInRange(short minValue, short maxValue,
			AttributeMetadata attributeMetadata) {
		// This can happen. It's a way to specify a precise value
		if (minValue == maxValue) {
			return minValue;
		}
		short retValue = (short) (minValue + (short) (Math.random() * (maxValue
				- minValue + 1)));
		while (retValue < minValue || retValue > maxValue) {
			retValue = (short) (minValue + (short) (Math.random() * (maxValue
					- minValue + 1)));
		}
		return retValue;
	}

	/**
	 * {@inheritDoc}
	 */

	@Override
	public String getStringValue(AttributeMetadata attributeMetadata) {
		return getStringOfLength(PodamConstants.STR_DEFAULT_LENGTH,
				attributeMetadata);
	}

	/**
	 * {@inheritDoc}
	 */

	@Override
	public String getStringOfLength(int length,
			AttributeMetadata attributeMetadata) {

		StringBuilder buff = new StringBuilder(
				PodamConstants.STR_DEFAULT_ENCODING);
		// Default length was 5 for some reason
		buff.setLength(0);

		while (buff.length() < length) {
			buff.append(getCharacter(attributeMetadata));
		}

		return buff.toString();

	}

	// ------------------->> Getters / Setters

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int getNumberOfCollectionElements(Class<?> type) {
		return nbrOfCollectionElements;
	}

	/**
	 * Sets the new number of default collection elements.
	 *
	 * @param newNumberOfCollectionElements
	 *            The new number of collection elements.
	 */
	public void setNumberOfCollectionElements(int newNumberOfCollectionElements) {
		nbrOfCollectionElements = newNumberOfCollectionElements;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int getMaxDepth(Class<?> type) {
		return maxDepth;
	}

	/**
	 * Sets the new max stack trace depth.
	 *
	 * @param newMaxDepth
	 *            The new max stack trace depth.
	 */
	public void setMaxDepth(int newMaxDepth) {
		maxDepth = newMaxDepth;
	}

	/**
	 * Rearranges POJO's constructors in order they will be tried to produce the
	 * POJO. Default strategy consist of putting constructors with less
	 * parameters to be tried first.
	 *
	 * @param constructors
	 *            Array of POJO's constructors
	 */
	@Override
	public void sort(Constructor<?>[] constructors) {
		Arrays.sort(constructors, constructorComparator);
	}

	/**
	 * Bind an interface/abstract class to a specific implementation
	 *
	 * @param abstractClass
	 *            the interface/abstract class to bind
	 * @param specificClass
	 *            the specific class implementing or extending
	 *            {@code abstractClass}.
	 * @return itself
	 */
	public <T> RandomDataProviderStrategy addSpecific(
			final Class<T> abstractClass, final Class<? extends T> specificClass) {
		specificTypes.put(abstractClass, specificClass);
		return this;
	}

	/**
	 * Remove binding of an interface/abstract class to a specific
	 * implementation
	 *
	 * @param abstractClass
	 *            the interface/abstract class to remove binding
	 * @return itself
	 */
	public <T> RandomDataProviderStrategy removeSpecific(
			final Class<T> abstractClass) {
		specificTypes.remove(abstractClass);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public <T> Class<? extends T> getSpecificClass(
			Class<T> nonInstantiatableClass) {
		Class<? extends T> found = (Class<? extends T>) specificTypes
				.get(nonInstantiatableClass);
		if (found == null) {
			found = nonInstantiatableClass;
		}
		return found;
	}

	// ------------------->> Private methods

	// ------------------->> equals() / hashcode() / toString()

	// ------------------->> Inner classes

}
