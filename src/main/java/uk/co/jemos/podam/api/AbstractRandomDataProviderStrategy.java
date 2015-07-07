/**
 *
 */
package uk.co.jemos.podam.api;

import net.jcip.annotations.NotThreadSafe;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import uk.co.jemos.podam.common.*;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.Collection;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;


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
@NotThreadSafe
public abstract class AbstractRandomDataProviderStrategy implements RandomDataProviderStrategy {

	// ------------------->> Constants

	/** Application logger */
	private static final Logger LOG = LoggerFactory.getLogger(AbstractRandomDataProviderStrategy.class);

	/** A RANDOM generator */
	private static final Random RANDOM = new Random(System.currentTimeMillis());

	/**
	 * How many times it is allowed to PODAM to create an instance of the same
	 * class in a recursive hierarchy
	 */
	public static final int MAX_DEPTH = 1;

	/** The max stack trace depth. */
	private final int maxDepth = MAX_DEPTH;

	/** The number of collection elements. */
	private int nbrOfCollectionElements;

	/** Flag to enable/disable the memoization setting. */
	private boolean isMemoizationEnabled =  true;

	/**
	 * A map to keep one object for each class. If memoization is enabled, the
	 * factory will use this table to avoid creating objects of the same class
	 * multiple times.
	 */
	private final ConcurrentMap<Class<?>, ConcurrentMap<Type[], Object>> memoizationTable = new ConcurrentHashMap<Class<?>, ConcurrentMap<Type[], Object>>();

	/**
	 * A list of user-submitted specific implementations for interfaces and
	 * abstract classes
	 */
	private final ConcurrentMap<Class<?>, Class<?>> specificTypes = new ConcurrentHashMap<Class<?>, Class<?>>();

	/**
	 * Mapping between annotations and attribute strategies
	 */
	private final ConcurrentMap<Class<? extends Annotation>, Class<AttributeStrategy<?>>> attributeStrategies
			= new ConcurrentHashMap<Class<? extends Annotation>, Class<AttributeStrategy<?>>>();

	/** The constructor comparator */
	private AbstractConstructorComparator constructorHeavyComparator =
			ConstructorHeavyFirstComparator.INSTANCE;

	/** The constructor comparator */
	private AbstractConstructorComparator constructorLightComparator =
			ConstructorLightFirstComparator.INSTANCE;

	/** The constructor comparator */
	private AbstractMethodComparator methodHeavyComparator
			= MethodHeavyFirstComparator.INSTANCE;

	/** The constructor comparator */
	private AbstractMethodComparator methodLightComparator
			= MethodLightFirstComparator.INSTANCE;

	// ------------------->> Instance / Static variables

	// ------------------->> Constructors

	/**
	 * Implementation of the Singleton pattern
	 */
	public AbstractRandomDataProviderStrategy() {
		this(PodamConstants.DEFAULT_NBR_COLLECTION_ELEMENTS);
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
		return PodamUtils.getNiceCharacter();

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
		return PodamUtils.getLongInRange(minValue, maxValue);
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
		StringBuilder buff = new StringBuilder();

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
	 * {@inheritDoc}
	 */
	@Override
	public void setDefaultNumberOfCollectionElements(int newNumberOfCollectionElements) {
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

				ConcurrentMap<Type[], Object> map = memoizationTable.get(attributeMetadata.getAttributeType());
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
			ConcurrentMap<Type[], Object> map = memoizationTable.get(attributeMetadata.getAttributeType());
			if (map == null) {
				map = new ConcurrentHashMap<Type[], Object>();

				memoizationTable.putIfAbsent(attributeMetadata.getAttributeType(), map);
			}
			map.putIfAbsent(attributeMetadata.getAttrGenericArgs(), instance);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void clearMemoizationCache() {

		memoizationTable.clear();


	}

	/**
	 * Rearranges POJO's constructors in order they will be tried to produce the
	 * POJO. Default strategy consist of putting constructors with less
	 * parameters to be tried first.
	 *
	 * @param constructors
	 *            Array of POJO's constructors
	 * @param order
	 *            {@link uk.co.jemos.podam.api.DataProviderStrategy.Order} how to sort constructors
	 */
	@Override
	public void sort(Constructor<?>[] constructors, Order order) {
		AbstractConstructorComparator constructorComparator;
		switch(order) {
		case HEAVY_FIRST:
			constructorComparator = constructorHeavyComparator;
			break;
		default:
			constructorComparator = constructorLightComparator;
			break;
		}
		Arrays.sort(constructors, constructorComparator);
	}

	/**
	 * Rearranges POJO's methods in order they will be tried to produce the
	 * POJO. Default strategy consist of putting methods with more
	 * parameters to be tried first.
	 *
	 * @param methods
	 *            Array of POJO's methods
	 * @param order
	 *            {@link uk.co.jemos.podam.api.DataProviderStrategy.Order} how to sort constructors
	 */
	@Override
	public void sort(Method[] methods, Order order) {
		AbstractMethodComparator methodComparator;
		switch(order) {
		case HEAVY_FIRST:
			methodComparator = methodHeavyComparator;
			break;
		default:
			methodComparator = methodLightComparator;
			break;
		}
		Arrays.sort(methods, methodComparator);
	}

	/**
	 * Bind an interface/abstract class to a specific implementation. If the
	 * strategy previously contained a binding for the interface/abstract class,
	 * the old value will not be replaced by the new value. If you want to force the
	 * value replacement, invoke removeSpecific before invoking this method.
	 * If you want to implement more sophisticated binding strategy, override this class.
	 *
	 * @param <T> return type
	 * @param abstractClass
	 *            the interface/abstract class to bind
	 * @param specificClass
	 *            the specific class implementing or extending
	 *            {@code abstractClass}.
	 * @return itself
	 */
	@Override
	public <T> DataProviderStrategy addOrReplaceSpecific(
			final Class<T> abstractClass, final Class<? extends T> specificClass) {

		specificTypes.put(abstractClass, specificClass);

		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public <T> DataProviderStrategy removeSpecific(
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

	/**
	 * {@inheritDoc}
	 */
	@Override
	public RandomDataProviderStrategy addOrReplaceAttributeStrategy(
			final Class<? extends Annotation> annotationClass,
			final Class<AttributeStrategy<?>> strategyClass) {

		attributeStrategies.put(annotationClass, strategyClass);

		return this;
	}

	/**
	 * Remove binding of an annotation to attribute strategy
	 *
	 * @param annotationClass
	 *            the annotation class to remove binding
	 * @return itself
	 */
	@Override
	public RandomDataProviderStrategy removeAttributeStrategy(
			final Class<? extends Annotation> annotationClass) {

		attributeStrategies.remove(annotationClass);

		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Class<AttributeStrategy<?>> getStrategyForAnnotation(
			final Class<? extends Annotation> annotationClass) {

		return attributeStrategies.get(annotationClass);

	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public AbstractConstructorComparator getConstructorLightComparator() {
		return constructorLightComparator;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setConstructorLightComparator(AbstractConstructorComparator constructorLightComparator) {
		this.constructorLightComparator = constructorLightComparator;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public AbstractConstructorComparator getConstructorHeavyComparator() {
		return constructorHeavyComparator;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setConstructorHeavyComparator(AbstractConstructorComparator constructorHeavyComparator) {
		this.constructorHeavyComparator = constructorHeavyComparator;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public AbstractMethodComparator getMethodLightComparator() {
		return methodLightComparator;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setMethodLightComparator(AbstractMethodComparator methodLightComparator) {
		this.methodLightComparator = methodLightComparator;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public AbstractMethodComparator getMethodHeavyComparator() {
		return methodHeavyComparator;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setMethodHeavyComparator(AbstractMethodComparator methodHeavyComparator) {
		this.methodHeavyComparator = methodHeavyComparator;
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
