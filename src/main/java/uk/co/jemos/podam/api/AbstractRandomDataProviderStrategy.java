/**
 *
 */
package uk.co.jemos.podam.api;

import net.jcip.annotations.ThreadSafe;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import uk.co.jemos.podam.common.*;
import uk.co.jemos.podam.exceptions.PodamMockeryException;
import uk.co.jemos.podam.typeManufacturers.*;

import jakarta.validation.constraints.*;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.time.*;
import java.time.temporal.Temporal;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Collection;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

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
@ThreadSafe
public abstract class AbstractRandomDataProviderStrategy implements RandomDataProviderStrategy {

	// ------------------->> Constants

	/** Application logger */
	private static final Logger LOG = LoggerFactory.getLogger(AbstractRandomDataProviderStrategy.class);

	/**
	 * How many times it is allowed to PODAM to create an instance of the same
	 * class in a recursive hierarchy
	 */
	private int maxDepth = 3;

	/** The number of collection elements. */
	private final AtomicInteger nbrOfCollectionElements = new AtomicInteger();

	/** Flag to enable/disable the memoization setting. */
	private final AtomicBoolean isMemoizationEnabled = new AtomicBoolean();

	/**
	 * A map to keep one object for each class. If memoization is enabled, the
	 * factory will use this table to avoid creating objects of the same class
	 * multiple times.
	 */
	private final Map<Class<?>, Map<Type[], Object>> memoizationTable = new HashMap<Class<?>, Map<Type[], Object>>();

	/**
	 * A mapping between types and their registered manufacturers
	 */
	private final ConcurrentHashMap<Class<?>, TypeManufacturer<?>> typeManufacturers
			= new ConcurrentHashMap<Class<?>, TypeManufacturer<?>>();

	/**
	 * A list of user-submitted specific implementations for interfaces and
	 * abstract classes
	 */
	private final Map<Class<?>, Class<?>> specificTypes = new ConcurrentHashMap<Class<?>, Class<?>>();

	/**
	 * A list of user-submitted factories to build interfaces and abstract classes
	 */
	private final Map<Class<?>, Class<?>> factoryTypes = new ConcurrentHashMap<Class<?>, Class<?>>();

	/**
	 * Mapping between annotations and attribute strategies
	 */
	private final Map<Class<? extends Annotation>, AttributeStrategy<?>> attributeStrategies
			= new ConcurrentHashMap<Class<? extends Annotation>, AttributeStrategy<?>>();

	/**
	 * Mapping between attributes and attribute strategies
	 */
	private final Map<Class<?>, Map<String,AttributeStrategy<?>>> attributeClassStrategies
			= new ConcurrentHashMap<Class<?>, Map<String,AttributeStrategy<?>>>();

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
		this.nbrOfCollectionElements.set(nbrOfCollectionElements);

		TypeManufacturer<?> byteManufacturer = new ByteTypeManufacturerImpl();
		typeManufacturers.put(byte.class, byteManufacturer);
		typeManufacturers.put(Byte.class, byteManufacturer);

		TypeManufacturer<?> booleanManufacturer = new BooleanTypeManufacturerImpl();
		typeManufacturers.put(boolean.class, booleanManufacturer);
		typeManufacturers.put(Boolean.class, booleanManufacturer);

		TypeManufacturer<?> charManufacturer = new CharTypeManufacturerImpl();
		typeManufacturers.put(char.class, charManufacturer);
		typeManufacturers.put(Character.class, charManufacturer);

		TypeManufacturer<?> shortManufacturer = new ShortTypeManufacturerImpl();
		typeManufacturers.put(short.class, shortManufacturer);
		typeManufacturers.put(Short.class, shortManufacturer);

		TypeManufacturer<?> intManufacturer = new IntTypeManufacturerImpl();
		typeManufacturers.put(int.class, intManufacturer);
		typeManufacturers.put(Integer.class, intManufacturer);

		TypeManufacturer<?> longManufacturer = new LongTypeManufacturerImpl();
		typeManufacturers.put(long.class, longManufacturer);
		typeManufacturers.put(Long.class, longManufacturer);

		TypeManufacturer<?> floatManufacturer = new FloatTypeManufacturerImpl();
		typeManufacturers.put(float.class, floatManufacturer);
		typeManufacturers.put(Float.class, floatManufacturer);

		TypeManufacturer<?> doubleManufacturer = new DoubleTypeManufacturerImpl();
		typeManufacturers.put(double.class, doubleManufacturer);
		typeManufacturers.put(Double.class, doubleManufacturer);

		TypeManufacturer<?> stringManufacturer = new StringTypeManufacturerImpl();
		typeManufacturers.put(CharSequence.class, stringManufacturer);

		TypeManufacturer<?> enumManufacturer = new EnumTypeManufacturerImpl();
		typeManufacturers.put(Enum.class, enumManufacturer);

		TypeManufacturer<?> typeManufacturer = new TypeTypeManufacturerImpl();
		typeManufacturers.put(Type.class, typeManufacturer);

		TypeManufacturer<?> collectionManufacturer = new CollectionTypeManufacturerImpl();
		typeManufacturers.put(Collection.class, collectionManufacturer);

		TypeManufacturer<?> mapManufacturer = new MapTypeManufacturerImpl();
		typeManufacturers.put(Map.class, mapManufacturer);

		TypeManufacturer<?> arrayManufacturer = new ArrayTypeManufacturerImpl();
		typeManufacturers.put(Cloneable.class, arrayManufacturer);
		
        /**
         * Support of classes with annotation strategy:
         * - {@link java.time.Duration}
         * - {@link java.time.ZoneId}
         * - {@link java.time.ZoneOffset}
         */
		addOrReplaceTypeManufacturer(Duration.class, new DurationTypeManufacturerImpl());
		addOrReplaceTypeManufacturer(MonthDay.class, new MonthDayTypeManufacturerImpl());
		addOrReplaceTypeManufacturer(Period.class, new PeriodTypeManufacturerImpl());
		addOrReplaceTypeManufacturer(ZoneId.class, new ZoneIdTypeManufacturerImpl());
		addOrReplaceTypeManufacturer(ZoneOffset.class, new ZoneOffsetTypeManufacturerImpl());
        
		/**
         * Support of {@link java.time.Temporal} classes with annotation strategy:
         * - {@link jakarta.validation.constraint.Future}
         * - {@link jakarta.validation.constraint.FutureOfPresent}
         * - {@link jakarta.validation.constraint.PastOfPresent}
         * - {@link jakarta.validation.constraint.Past}
         */
		addOrReplaceTypeManufacturer(Clock.class, new ClockTypeManufacturerImpl());
		addOrReplaceTypeManufacturer(Instant.class, new InstantTypeManufacturerImpl());
		addOrReplaceTypeManufacturer(LocalDate.class, new LocalDateTypeManufacturerImpl());
		addOrReplaceTypeManufacturer(LocalDateTime.class, new LocalDateTimeTypeManufacturerImpl());
		addOrReplaceTypeManufacturer(LocalTime.class, new LocalTimeTypeManufacturerImpl());
		addOrReplaceTypeManufacturer(OffsetDateTime.class, new OffsetDateTimeTypeManufacturerImpl());
		addOrReplaceTypeManufacturer(OffsetTime.class, new OffsetTimeTypeManufacturerImpl());
		addOrReplaceTypeManufacturer(Year.class, new YearTypeManufacturerImpl());
		addOrReplaceTypeManufacturer(ZonedDateTime.class, new ZonedDateTimeTypeManufacturerImpl());
        addOrReplaceAttributeStrategy(Email.class, new EmailStrategy());
        addOrReplaceAttributeStrategy(Future.class, new FutureStrategy());
        addOrReplaceAttributeStrategy(FutureOrPresent.class, new FutureOrPresentStrategy());
        addOrReplaceAttributeStrategy(PastOrPresent.class, new PastOrPresentStrategy());
        addOrReplaceAttributeStrategy(Past.class, new PastStrategy());
	}

	// ------------------->> Public methods

	// ------------------->> Getters / Setters

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int getNumberOfCollectionElements(Class<?> type) {
		return nbrOfCollectionElements.get();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setDefaultNumberOfCollectionElements(int newNumberOfCollectionElements) {
		nbrOfCollectionElements.set(newNumberOfCollectionElements);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int getMaxDepth(Class<?> type) {
		return maxDepth;
	}

	/**
	 * Max depth setter
	 *
	 * @param maxDepth
	 *            defines new max depth
	 */
	public void setMaxDepth(int maxDepth) {
		this.maxDepth = maxDepth;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isMemoizationEnabled() {
		return isMemoizationEnabled.get();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setMemoization(boolean isMemoizationEnabled) {
		this.isMemoizationEnabled.set(isMemoizationEnabled);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public synchronized Object getMemoizedObject(AttributeMetadata attributeMetadata) {

		if (isMemoizationEnabled.get()) {
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
							LOG.trace("Found memoized {}<{}>", attributeMetadata.getAttributeType(), attributeMetadata.getAttrGenericArgs());
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
	public synchronized void cacheMemoizedObject(AttributeMetadata attributeMetadata,
			Object instance) {

		if (isMemoizationEnabled.get()) {
			Map<Type[], Object> map = memoizationTable.get(attributeMetadata.getAttributeType());
			if (map == null) {
				map = new HashMap<Type[], Object>();
				memoizationTable.put(attributeMetadata.getAttributeType(), map);
			}
			LOG.trace("Saving memoized {}<{}>", attributeMetadata.getAttributeType(), attributeMetadata.getAttrGenericArgs());
			map.put(attributeMetadata.getAttrGenericArgs(), instance);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public synchronized void clearMemoizationCache() {

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
	 * {@inheritDoc}
	 */
	@Override
	public <T> DataProviderStrategy addOrReplaceTypeManufacturer(
			Class<? extends T> type, TypeManufacturer<T> typeManufacturer) {

		typeManufacturers.put(type, typeManufacturer);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public <T> DataProviderStrategy removeTypeManufacturer(
			Class<T> type) {

		typeManufacturers.remove(type);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public <T> T getTypeValue(AttributeMetadata attributeMetadata,
			ManufacturingContext manufacturingCtx,
			Class<T> pojoType) {

		if (null == attributeMetadata) {
			throw new IllegalArgumentException(
					"The attribute metadata inside the wrapper cannot be null");
		}

		if (null == attributeMetadata.getAttributeAnnotations()) {
			throw new IllegalArgumentException(
					"The annotations list within the attribute metadata cannot be null, although it can be empty");
		}

		Deque<Class<?>> types = new ArrayDeque<Class<?>>();
		types.add(pojoType);
		while (!types.isEmpty()) {

			Class<?> type = types.remove();
			TypeManufacturer<?> manufacturer = typeManufacturers.get(type);
			if (null != manufacturer) {
				try {
					@SuppressWarnings("unchecked")
					T tmp = (T) manufacturer.getType(this, attributeMetadata,
							manufacturingCtx);
					if (null != tmp) {
						log(attributeMetadata);
						return tmp;
					} else {
						LOG.debug("{} cannot manufacture {}", manufacturer, pojoType);
					}
				} catch (Exception e) {
					throw new PodamMockeryException(
							"Unable to instantiate " + pojoType, e);
				}
			}

			for (Class<?> iface : type.getInterfaces()) {
				types.add(iface);
			}
			type = type.getSuperclass();
			if (null != type) {
				types.add(type);
			}
		}

		LOG.debug("Failed to find suitable manufacturer for type {}", pojoType);
		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public <T> AbstractRandomDataProviderStrategy addOrReplaceFactory(
			final Class<T> abstractClass, final Class<?> factoryClass) {

		factoryTypes.put(abstractClass, factoryClass);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public <T> AbstractRandomDataProviderStrategy removeFactory(
			final Class<T> abstractClass) {

		factoryTypes.remove(abstractClass);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Class<?> getFactoryClass(Class<?> nonInstantiatableClass) {

		return factoryTypes.get(nonInstantiatableClass);
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

		@SuppressWarnings("unchecked")
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
			final AttributeStrategy<?> attributeStrategy) {

		attributeStrategies.put(annotationClass, attributeStrategy);

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
	public AttributeStrategy<?> getStrategyForAnnotation(
			final Class<? extends Annotation> annotationClass) {

		return attributeStrategies.get(annotationClass);

	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public RandomDataProviderStrategy addOrReplaceAttributeStrategy(
			final Class<?> type, final String attributeName,
			final AttributeStrategy<?> attributeStrategy) {

		Map<String,AttributeStrategy<?>> classStrategies = attributeClassStrategies.get(type);
		if (null == classStrategies) {
			classStrategies = new ConcurrentHashMap<String,AttributeStrategy<?>>();
			attributeClassStrategies.put(type, classStrategies);
		}
		classStrategies.put(attributeName, attributeStrategy);

		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public RandomDataProviderStrategy removeAttributeStrategy(
			final Class<?> type, String attributeName) {

		Map<String,AttributeStrategy<?>> classStrategies = attributeClassStrategies.get(type);
		if (null != classStrategies) {
			classStrategies.remove(attributeName);
		}

		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public AttributeStrategy<?> getStrategyForAttribute(
			final ClassAttribute attribute) {

		AttributeStrategy<?> attributeStrategy = null;
		Field field = attribute.getAttribute();
		if (null != field) {
			Class<?> type = field.getDeclaringClass();
			Map<String,AttributeStrategy<?>> classStrategies = attributeClassStrategies.get(type);
			if (null != classStrategies) {
				attributeStrategy = classStrategies.get(attribute.getName());
			}
		}
		return attributeStrategy;

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
				attributeMetadata.getPojoClass() != null ? attributeMetadata.getPojoClass().getName() : "",
				attributeMetadata.getAttributeName() != null ? attributeMetadata.getAttributeName() : "");
	}

	// ------------------->> equals() / hashcode() / toString()

	// ------------------->> Inner classes

}
