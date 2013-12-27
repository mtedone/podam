/**
 * 
 */
package uk.co.jemos.podam.api;

import java.lang.reflect.Constructor;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Random;

import uk.co.jemos.podam.annotations.PodamConstructor;
import uk.co.jemos.podam.dto.AttributeMetadata;
import uk.co.jemos.podam.utils.PodamConstants;

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

public class RandomDataProviderStrategy implements DataProviderStrategy {

	// ------------------->> Constants

	/** Application logger */
	private static final org.apache.log4j.Logger LOG = org.apache.log4j.Logger
			.getLogger(RandomDataProviderStrategy.class.getName());

	/** A RANDOM generator */
	private static final Random RANDOM = new Random(System.currentTimeMillis());

	/** The singleton instance of this implementation */
	private static final RandomDataProviderStrategy SINGLETON = new RandomDataProviderStrategy();

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
	public static final int MAX_DEPTH = 10;

	/** The default number of collection elements for this strategy */
	public static final int DEFAULT_NBR_COLLECTION_ELEMENTS = 5;

	/** The max stack trace depth. */
	private int maxDepth = MAX_DEPTH;

	/** The number of collection elements. */
	private int nbrOfCollectionElements;

	/**
	 * Comparator for sorting constructors.
	 * <p>
	 * We would like to have constructor with less parameters to speed up
	 * creation.
	 * </p>
	 */
	public static Comparator<Constructor<?>> ConstructorComparator
			= new Comparator<Constructor<?>>() {

		public int compare(
				Constructor<?> constructor1,
				Constructor<?> constructor2) {

			/* Constructors with Podam annotation first */
			boolean choose1 =
				(constructor1.getAnnotation(PodamConstructor.class) != null);
			boolean choose2 =
				(constructor2.getAnnotation(PodamConstructor.class) != null);
			if (choose1 && !choose2) {
				return Integer.MIN_VALUE;
			} else if (!choose1 && choose2) {
				return Integer.MAX_VALUE;
			}

			/* Then constructors with less parameters */
			return constructor1.getParameterTypes().length -
				constructor2.getParameterTypes().length;
			}
	};

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

	public Boolean getBoolean(AttributeMetadata attributeMetadata) {
		return Boolean.TRUE;
	}

	/**
	 * {@inheritDoc}
	 */

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

	public Character getCharacter(AttributeMetadata attributeMetadata) {

		int randomCharIdx = getIntegerInRange(0,
				NICE_ASCII_CHARACTERS.length - 1, attributeMetadata);

		int charToReturnIdx = randomCharIdx % NICE_ASCII_CHARACTERS.length;

		return NICE_ASCII_CHARACTERS[charToReturnIdx];

	}

	/**
	 * {@inheritDoc}
	 */

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

	public Long getLong(AttributeMetadata attributeMetadata) {
		return System.currentTimeMillis();
	}

	/**
	 * {@inheritDoc}
	 */

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

	public String getStringValue(AttributeMetadata attributeMetadata) {
		return getStringOfLength(PodamConstants.STR_DEFAULT_LENGTH,
				attributeMetadata);
	}

	/**
	 * {@inheritDoc}
	 */

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
	 * Rearranges POJO's constructors in order they will be tried to
	 * produce the POJO.
	 * Default strategy consist of putting constructors with less parameters
	 * to be tried first.
	 * 
	 * @param constructors
	 *            Array of POJO's constructors
	 */
	public void sort(Constructor<?>[] constructors) {
		Arrays.sort(constructors, ConstructorComparator);
	}

	// ------------------->> Private methods

	// ------------------->> equals() / hashcode() / toString()

	// ------------------->> Inner classes

}
