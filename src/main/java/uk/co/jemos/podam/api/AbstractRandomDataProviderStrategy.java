/**
 *
 */
package uk.co.jemos.podam.api;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import uk.co.jemos.podam.common.AbstractConstructorComparator;
import uk.co.jemos.podam.common.ConstructorAdaptiveComparator;
import uk.co.jemos.podam.common.MethodComparator;
import uk.co.jemos.podam.common.PodamConstants;

/**
 * Default abstract implementation of a {@link DataProviderStrategy}
 * <p>
 * This default implementation returns values based on a random generator.
 * Convinient for subclassing and redefining behaviour.
 * <b>Don't use this implementation if you seek deterministic values</b>
 * </p>
 *
 * <p>
 * All values returned by this implementation are <b>different from zero</b>.
 * </p>
 *
 * @author mtedone
 *
 * @since 1.0.0
 *
 */

public abstract class AbstractRandomDataProviderStrategy implements DataProviderStrategy {

	// ------------------->> Constants

	/** Application logger */
	private static final Logger LOG = LoggerFactory
			.getLogger(AbstractRandomDataProviderStrategy.class);

	/** A RANDOM generator */
	private static final Random RANDOM = new Random(System.currentTimeMillis());

	/** The constructor comparator */
	private static final MethodComparator METHOD_COMPARATOR = new MethodComparator();

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

	/** Flag to enable/disable the memoization setting. */
	private boolean isMemoizationEnabled;

	/**
	 * A map to keep one object for each class. If memoization is enabled, the
	 * factory will use this table to avoid creating objects of the same class
	 * multiple times.
	 */
	private Map<Class<?>, Map<Type[], Object>> memoizationTable = new HashMap<Class<?>, Map<Type[], Object>>();

	/**
	 * A list of user-submitted specific implementations for interfaces and
	 * abstract classes
	 */
	private final Map<Class<?>, Class<?>> specificTypes = new HashMap<Class<?>, Class<?>>();

	/** The constructor comparator */
	private AbstractConstructorComparator constructorComparator =
			ConstructorAdaptiveComparator.INSTANCE;

	// ------------------->> Instance / Static variables

	// ------------------->> Constructors

	/**
	 * Implementation of the Singleton pattern
	 */
	public AbstractRandomDataProviderStrategy() {
		this(DEFAULT_NBR_COLLECTION_ELEMENTS);
	}

	public AbstractRandomDataProviderStrategy(int nbrOfCollectionElements) {
		this.nbrOfCollectionElements = nbrOfCollectionElements;
	}

	// ------------------->> Public methods

	/**
	 * {@inheritDoc}
	 */

	@Override
	public Boolean getBoolean(AttributeMetadata attributeMetadata) {

		log(attributeMetadata);
		return Boolean.TRUE;
	}

	/**
	 * {@inheritDoc}
	 */

	@Override
	public Byte getByte(AttributeMetadata attributeMetadata) {

		log(attributeMetadata);
		byte nextByte;
		do {
			nextByte = (byte) RANDOM.nextInt(Byte.MAX_VALUE);
		} while (nextByte == 0);
		return nextByte;
	}

	/**
	 * {@inheritDoc}
	 */

	@Override
	public Byte getByteInRange(byte minValue, byte maxValue,
			AttributeMetadata attributeMetadata) {

		log(attributeMetadata);
		return (byte) (minValue + Math.random() * (maxValue - minValue) + 0.5);
	}

	/**
	 * {@inheritDoc}
	 */

	@Override
	public Character getCharacter(AttributeMetadata attributeMetadata) {

		log(attributeMetadata);
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

		log(attributeMetadata);
		return (char) (minValue + Math.random() * (maxValue - minValue) + 0.5);
	}

	/**
	 * {@inheritDoc}
	 */

	@Override
	public Double getDouble(AttributeMetadata attributeMetadata) {

		log(attributeMetadata);
		double retValue;
		do {
			retValue = RANDOM.nextDouble();
		} while (retValue == 0.0);
		return retValue;
	}

	/**
	 * {@inheritDoc}
	 */

	@Override
	public Double getDoubleInRange(double minValue, double maxValue,
			AttributeMetadata attributeMetadata) {

		log(attributeMetadata);
		// This can happen. It's a way to specify a precise value
		if (minValue == maxValue) {
			return minValue;
		}
		double retValue;
		do {
			retValue = minValue + Math.random() * (maxValue - minValue + 1);
		} while (retValue > maxValue);
		return retValue;
	}

	/**
	 * {@inheritDoc}
	 */

	@Override
	public Float getFloat(AttributeMetadata attributeMetadata) {

		log(attributeMetadata);
		float retValue;
		do {
			retValue = RANDOM.nextFloat();
		} while (retValue == 0.0f);
		return retValue;
	}

	/**
	 * {@inheritDoc}
	 */

	@Override
	public Float getFloatInRange(float minValue, float maxValue,
			AttributeMetadata attributeMetadata) {

		log(attributeMetadata);
		// This can happen. It's a way to specify a precise value
		if (minValue == maxValue) {
			return minValue;
		}
		float retValue;
		do {
			retValue = (float) (minValue
					+ Math.random() * (maxValue - minValue + 1));
		} while (retValue > maxValue);
		return retValue;
	}

	/**
	 * {@inheritDoc}
	 */

	@Override
	public Integer getInteger(AttributeMetadata attributeMetadata) {

		log(attributeMetadata);
		Integer retValue;
		do {
			retValue = RANDOM.nextInt();
		} while (retValue.intValue() == 0);
		return retValue;
	}

	/**
	 * {@inheritDoc}
	 */

	@Override
	public int getIntegerInRange(int minValue, int maxValue,
			AttributeMetadata attributeMetadata) {

		log(attributeMetadata);
		return (int) (minValue + Math.random() * (maxValue - minValue) + 0.5);
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

		log(attributeMetadata);
		return System.nanoTime();
	}

	/**
	 * {@inheritDoc}
	 */

	@Override
	public Long getLongInRange(long minValue, long maxValue,
			AttributeMetadata attributeMetadata) {

		log(attributeMetadata);
		return (long) (minValue + Math.random() * (maxValue - minValue) + 0.5);
	}

	/**
	 * {@inheritDoc}
	 */

	@Override
	public Short getShort(AttributeMetadata attributeMetadata) {

		log(attributeMetadata);
		short retValue;
		do {
			retValue = (short) RANDOM.nextInt(Byte.MAX_VALUE);
		} while (retValue == 0);
		return retValue;
	}

	/**
	 * {@inheritDoc}
	 */

	@Override
	public Short getShortInRange(short minValue, short maxValue,
			AttributeMetadata attributeMetadata) {

		log(attributeMetadata);
		return (short) (minValue + Math.random() * (maxValue - minValue) + 0.5);
	}

	/**
	 * {@inheritDoc}
	 */

	@Override
	public String getStringValue(AttributeMetadata attributeMetadata) {

		log(attributeMetadata);
		return getStringOfLength(PodamConstants.STR_DEFAULT_LENGTH,
				attributeMetadata);
	}

	/**
	 * {@inheritDoc}
	 */

	@Override
	public String getStringOfLength(int length,
			AttributeMetadata attributeMetadata) {

		log(attributeMetadata);
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
	 * {@inheritDoc}
	 */
	@Override
	public boolean isMemoizationEnabled() {
		return isMemoizationEnabled;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setMemoization(boolean isMemoizationEnabled) {
		this.isMemoizationEnabled = isMemoizationEnabled;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Object getMemoizedObject(AttributeMetadata attributeMetadata) {

		if (isMemoizationEnabled) {
			/* No memoization for arrays, collections and maps */
			Class<?> pojoClass = attributeMetadata.getPojoClass();
			if (pojoClass == null ||
					(!pojoClass.isArray() &&
					!Collection.class.isAssignableFrom(pojoClass) &&
					!Map.class.isAssignableFrom(pojoClass))) {
				Map<Type[], Object> map = memoizationTable.get(attributeMetadata.getAttributeType());
				if (map != null) {
					for (Entry<Type[], Object> entry : map.entrySet()) {
						if (Arrays.equals(entry.getKey(), attributeMetadata.getAttrGenericArgs())) {
							return entry.getValue();
						}
					}
				}
			}
		}
		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void cacheMemoizedObject(AttributeMetadata attributeMetadata,
			Object instance) {

		if (isMemoizationEnabled) {
			Map<Type[], Object> map = memoizationTable.get(attributeMetadata.getAttributeType());
			if (map == null) {
				map = new HashMap<Type[], Object>();
				memoizationTable.put(attributeMetadata.getAttributeType(), map);
			}
			map.put(attributeMetadata.getAttrGenericArgs(), instance);
		}
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
	 * Rearranges POJO's methods in order they will be tried to produce the
	 * POJO. Default strategy consist of putting methods with more
	 * parameters to be tried first.
	 *
	 * @param methods
	 *            Array of POJO's methods
	 */
	@Override
	public void sort(Method[] methods) {
		Arrays.sort(methods, METHOD_COMPARATOR);
	}

	/**
	 * Bind an interface/abstract class to a specific implementation. If the
	 * strategy previously contained a binding for the interface/abstract class,
	 * the old value is replaced by the new value. If you want to implement
	 * more sophisticated binding strategy, override this class.
	 *
	 * @param <T> return type
	 * @param abstractClass
	 *            the interface/abstract class to bind
	 * @param specificClass
	 *            the specific class implementing or extending
	 *            {@code abstractClass}.
	 * @return itself
	 */
	public <T> AbstractRandomDataProviderStrategy addSpecific(
			final Class<T> abstractClass, final Class<? extends T> specificClass) {
		specificTypes.put(abstractClass, specificClass);
		return this;
	}

	/**
	 * Remove binding of an interface/abstract class to a specific
	 * implementation
	 *
	 * @param <T> return type
	 * @param abstractClass
	 *            the interface/abstract class to remove binding
	 * @return itself
	 */
	public <T> AbstractRandomDataProviderStrategy removeSpecific(
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
		@SuppressWarnings("unchecked")
		Class<? extends T> found = (Class<? extends T>) specificTypes
				.get(nonInstantiatableClass);
		if (found == null) {
			found = nonInstantiatableClass;
		}
		return found;
	}

	/**
	 * Getter for constructor comparator
	 * @return current constructor comparator used by strategy
	 */
	public AbstractConstructorComparator getConstructorComparator() {
		return constructorComparator;
	}

	/**
	 * Setter for constructor comparator. Default implementations are
	 * {@link uk.co.jemos.podam.common.ConstructorHeavyFirstComparator} and
	 * {@link uk.co.jemos.podam.common.ConstructorLightFirstComparator}.
	 * @param constructorComparator constructor comparator to set
	 */
	public void setConstructorComparator(AbstractConstructorComparator constructorComparator) {
		this.constructorComparator = constructorComparator;
	}

	// ------------------->> Private methods

	private void log(AttributeMetadata attributeMetadata) {
		LOG.trace("Providing data for attribute {}.{}",
				attributeMetadata.getPojoClass().getName(),
				attributeMetadata.getAttributeName() != null ? attributeMetadata.getAttributeName() : "");
	}

	// ------------------->> equals() / hashcode() / toString()

	// ------------------->> Inner classes

}
