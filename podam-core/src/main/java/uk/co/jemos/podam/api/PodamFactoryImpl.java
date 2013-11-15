/**
 * 
 */
package uk.co.jemos.podam.api;

import java.lang.annotation.Annotation;
import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.lang.reflect.WildcardType;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicReference;

import net.jcip.annotations.Immutable;
import net.jcip.annotations.ThreadSafe;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import uk.co.jemos.podam.annotations.PodamBooleanValue;
import uk.co.jemos.podam.annotations.PodamByteValue;
import uk.co.jemos.podam.annotations.PodamCharValue;
import uk.co.jemos.podam.annotations.PodamCollection;
import uk.co.jemos.podam.annotations.PodamConstructor;
import uk.co.jemos.podam.annotations.PodamDoubleValue;
import uk.co.jemos.podam.annotations.PodamFloatValue;
import uk.co.jemos.podam.annotations.PodamIntValue;
import uk.co.jemos.podam.annotations.PodamLongValue;
import uk.co.jemos.podam.annotations.PodamShortValue;
import uk.co.jemos.podam.annotations.PodamStrategyValue;
import uk.co.jemos.podam.annotations.PodamStringValue;
import uk.co.jemos.podam.annotations.strategies.ObjectStrategy;
import uk.co.jemos.podam.dto.AttributeMetadata;
import uk.co.jemos.podam.dto.ClassInfo;
import uk.co.jemos.podam.exceptions.PodamMockeryException;
import uk.co.jemos.podam.utils.PodamConstants;
import uk.co.jemos.podam.utils.PodamUtils;

/**
 * The PODAM factory implementation
 * 
 * @author mtedone
 * 
 * @since 1.0.0
 * 
 */
@ThreadSafe
@Immutable
public class PodamFactoryImpl implements PodamFactory {

	// ------------------->> Constants

	/** Application logger */
	private final Logger LOG = LoggerFactory
			.getLogger(PodamFactoryImpl.class.getName());

	// ------------------->> Instance / variables

	/**
	 * The strategy to use to fill data.
	 * <p>
	 * The default is {@link RandomDataProviderStrategy}.
	 * </p>
	 */
	private final DataProviderStrategy strategy;

	/**
	 * A list of {@link Annotation}s for attributes that PODAM shouldn't
	 * consider
	 */
	private List<Class<? extends Annotation>> excludeAnnotations;

	/**
	 * Map of global class to AttributeStrategy's
	 */
	private Map<Class, AttributeStrategy> factoryMap = new HashMap();

	// ------------------->> Constructors
	/**
	 * Default constructor.
	 */
	public PodamFactoryImpl() {
		this(RandomDataProviderStrategy.getInstance());
	}

	/**
	 * Full constructor.
	 * 
	 * @param strategy
	 *            The strategy to use to fill data
	 */
	public PodamFactoryImpl(DataProviderStrategy strategy) {
		super();
		this.strategy = strategy;
	}

	/**
	 * Add an {@link AttributeStrategy} to be used whenever encountering the given class.
	 *
	 * @param attributeClass
	 * @param attributeStrategy
	 */
	public void registerFactory(Class attributeClass, AttributeStrategy attributeStrategy) {
		factoryMap.put(attributeClass, attributeStrategy);
	}

	// ------------------->> Public methods

	/**
	 * {@inheritDoc}
	 */

	public <T> T manufacturePojo(Class<T> pojoClass, Type... genericTypeArgs) {
		int depth = 0;
		return this.manufacturePojoInternal(pojoClass, depth, genericTypeArgs);
	}

	// ------------------->> Getters / Setters

	/**
	 * {@inheritDoc}
	 */

	public DataProviderStrategy getStrategy() {
		return strategy;
	}

	// ------------------->> Private methods

	/**
	 * Fills type agruments map
	 * <p>
	 * This method places required and provided types for object creation into a
	 * map, which will be used for type mapping.
	 * </p>
	 * 
	 * @param typeArgsMap
	 *            a map to fill
	 * @param typeParameters
	 *            Type arguments needed for a generics object creation
	 * @param genericTypeArgs
	 *            Type arguments provided for a generics object by caller
	 * @return Array of unused provided generic type arguments
	 */
	private Type[] fillTypeArgMap(final Map<String, Type> typeArgsMap,
			final TypeVariable<?>[] typeParameters, final Type[] genericTypeArgs) {

		int i;
		for (i = 0; i < typeParameters.length; i++) {
			typeArgsMap.put(typeParameters[i].getName(), genericTypeArgs[i]);
		}
		Type[] genericTypeArgsExtra;
		if (typeParameters.length < genericTypeArgs.length) {
			genericTypeArgsExtra = Arrays.copyOfRange(genericTypeArgs, i,
					genericTypeArgs.length);
		} else {
			genericTypeArgsExtra = null;
		}
		return genericTypeArgsExtra;
	}

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

	/**
	 * It attempts to create an instance of the given class
	 * <p>
	 * This method attempts to create an instance of the given argument for
	 * classes without setters. These may be either immutable classes (e.g. with
	 * final attributes and no setters) or Java classes (e.g. belonging to the
	 * java / javax namespace). In case the class does not provide a public,
	 * no-arg constructor (e.g. Calendar), this method attempts to find a ,
	 * no-args, factory method (e.g. getInstance()) and it invokes it
	 * </p>
	 * 
	 * @param clazz
	 *            The class for which a new instance is required
	 * @param genericTypeArgs
	 *            The generic type arguments for the current generic class
	 *            instance
	 * 
	 * 
	 * @return An instance of the given class
	 * @throws IllegalArgumentException
	 *             If an illegal argument was passed to the constructor
	 * @throws InstantiationException
	 *             If an exception occurred during instantiation
	 * @throws IllegalAccessException
	 *             If security was violated while creating the object
	 * @throws InvocationTargetException
	 *             If an exception occurred while invoking the constructor or
	 *             factory method
	 * @throws ClassNotFoundException
	 *             If it was not possible to create a class from a string
	 */
	private Object createNewInstanceForClassWithoutConstructors(
			Class<?> pojoClass, Class<?> clazz, Type... genericTypeArgs)
			throws IllegalArgumentException, InstantiationException,
			IllegalAccessException, InvocationTargetException,
			ClassNotFoundException {

		final TypeVariable<?>[] typeParameters = pojoClass.getTypeParameters();
		if (typeParameters.length > genericTypeArgs.length) {
			LOG.warn(pojoClass.getCanonicalName()
					+ " is missing generic type arguments, expected "
					+ typeParameters.length + " found "
					+ genericTypeArgs.length + ". Returning null.");
			return null;
		}

		Object retValue = null;

		Constructor<?>[] constructors = clazz.getConstructors();

		if (constructors.length == 0) {

			final Map<String, Type> typeArgsMap = new HashMap<String, Type>();
			Type[] genericTypeArgsExtra = fillTypeArgMap(typeArgsMap,
					typeParameters, genericTypeArgs);
			if (genericTypeArgsExtra != null) {
				LOG.warn(String.format("Lost %d generic type arguments",
						genericTypeArgsExtra.length));
			}

			// If no publicly accessible constructors are available,
			// the best we can do is to find a constructor (e.g.
			// getInstance())

			Method[] declaredMethods = clazz.getDeclaredMethods();

			// A candidate factory method is a method which returns the
			// Class type

			// The parameters to pass to the method invocation
			Object[] parameterValues = null;

			for (Method candidateConstructor : declaredMethods) {

				if (!Modifier.isStatic(candidateConstructor.getModifiers())
						|| !candidateConstructor.getReturnType().equals(clazz)) {
					continue;
				}

				parameterValues = new Object[candidateConstructor
						.getParameterTypes().length];

				Type[] parameterTypes = candidateConstructor
						.getGenericParameterTypes();

				if (parameterTypes.length == 0) {

					// There is a factory method with no arguments
					retValue = candidateConstructor.invoke(clazz,
							new Object[] {});

				} else {

					// This is a factory method with arguments

					Annotation[][] parameterAnnotations = candidateConstructor
							.getParameterAnnotations();

					int idx = 0;

					for (Type paramType : parameterTypes) {

						AtomicReference<Type[]> methodGenericTypeArgs = new AtomicReference<Type[]>();
						Class<?> parameterType = resolveGenericParameter(
								paramType, typeArgsMap, methodGenericTypeArgs);

						List<Annotation> annotations = Arrays
								.asList(parameterAnnotations[idx]);

						String attributeName = null;

						// It's a Collection type
						if (Collection.class.isAssignableFrom(parameterType)) {

							Collection<? super Object> listType = resolveCollectionType(parameterType);

							Class<?> elementType;
							if (paramType instanceof ParameterizedType) {
								elementType = (Class<?>) methodGenericTypeArgs
										.get()[0];
							} else {
								elementType = Object.class;
							}

							int nbrElements = strategy
									.getNumberOfCollectionElements();

							for (Annotation annotation : annotations) {
								if (annotation.annotationType().equals(
										PodamCollection.class)) {

									PodamCollection ann = (PodamCollection) annotation;

									nbrElements = ann.nbrElements();

								}
							}

							for (int i = 0; i < nbrElements; i++) {
								Object attributeValue = manufactureAttributeValue(
										clazz, elementType, annotations,
										attributeName);

								listType.add(attributeValue);
							}

							parameterValues[idx] = listType;

							// It's a Map
						} else if (Map.class.isAssignableFrom(parameterType)) {

							Map<? super Object, ? super Object> mapType = resolveMapType(parameterType);

							Class<?> keyClass;
							Class<?> valueClass;
							if (paramType instanceof ParameterizedType) {
								keyClass = (Class<?>) methodGenericTypeArgs
										.get()[0];
								valueClass = (Class<?>) methodGenericTypeArgs
										.get()[1];
							} else {
								keyClass = Object.class;
								valueClass = Object.class;
							}

							int nbrElements = strategy
									.getNumberOfCollectionElements();

							for (Annotation annotation : annotations) {
								if (annotation.annotationType().equals(
										PodamCollection.class)) {

									PodamCollection ann = (PodamCollection) annotation;

									nbrElements = ann.nbrElements();

								}
							}

							for (int i = 0; i < nbrElements; i++) {
								Object keyValue = manufactureAttributeValue(
										clazz, keyClass, annotations,
										attributeName);

								Object elementValue = manufactureAttributeValue(
										clazz, valueClass, annotations,
										attributeName);

								mapType.put(keyValue, elementValue);
							}

							parameterValues[idx] = mapType;

						} else {

							// It's any other object
							parameterValues[idx] = manufactureAttributeValue(
									clazz, parameterType, annotations,
									attributeName, typeArgsMap, genericTypeArgs);

						}

						idx++;

					}

				}

				try {

					retValue = candidateConstructor.invoke(clazz,
							parameterValues);
					LOG.info("Could create an instance using "
							+ candidateConstructor);

					break;
				} catch (Throwable t) {

					LOG.warn("PODAM could not create an instance for constructor: "
							+ candidateConstructor
							+ ". Will try another one...");

				}

			}

		} else {

			// There are public constructors. We want constructor with minumum
			// number of parameters to speed up the creation
			Arrays.sort(constructors, ConstructorComparator);

			for (Constructor<?> constructor : constructors) {

				try {

					Object[] constructorArgs = getParameterValuesForConstructor(
							constructor, pojoClass, genericTypeArgs);

					retValue = constructor.newInstance(constructorArgs);

					LOG.debug("For class: "
							+ clazz.getName()
							+ " a valid constructor: "
							+ constructor
							+ " was found. PODAM will use it to create an instance.");

					break;

				} catch (Throwable t) {

					LOG.warn("Couldn't create attribute with constructor: "
							+ constructor
							+ ". Will check if other constructors are available");

				}

			}

			if (retValue == null) {
				LOG.warn("For class: " + clazz.getName()
						+ " PODAM could not possibly create a value."
						+ " This attribute will be returned as null.");
			}

		}

		return retValue;

	}

	/**
	 * It resolves generic parameter type
	 * 
	 * 
	 * @param paramType
	 *            The generic parameter type
	 * @param typeArgsMap
	 *            A map of resolved types
	 * @param methodGenericTypeArgs
	 *            Return value posible generic types of the generic parameter
	 *            type
	 * @return value for class representing the generic parameter type
	 */
	private Class<?> resolveGenericParameter(Type paramType,
			Map<String, Type> typeArgsMap,
			AtomicReference<Type[]> methodGenericTypeArgs) {

		Class<?> parameterType;
		methodGenericTypeArgs.set(new Type[] {});
		if (paramType instanceof TypeVariable<?>) {
			final String typeName = ((TypeVariable<?>) paramType).getName();
			final Type type = typeArgsMap.get(typeName);
			parameterType = resolveGenericParameter(type, typeArgsMap,
					methodGenericTypeArgs);
		} else if (paramType instanceof ParameterizedType) {
			ParameterizedType pType = (ParameterizedType) paramType;
			parameterType = (Class<?>) pType.getRawType();
			methodGenericTypeArgs.set(pType.getActualTypeArguments());
		} else if (paramType instanceof WildcardType) {
			WildcardType wType = (WildcardType) paramType;
			Type[] bounds = wType.getLowerBounds();
			String msg;
			if (bounds != null && bounds.length > 0) {
				msg = "Lower bounds:";
			} else {
				bounds = wType.getUpperBounds();
				msg = "Upper bounds:";
			}
			if (bounds != null && bounds.length > 0) {
				LOG.debug(msg + Arrays.toString(bounds));
				parameterType = resolveGenericParameter(bounds[0], typeArgsMap,
						methodGenericTypeArgs);
			} else {
				LOG.warn("Unrecognized argument type" + wType.toString()
						+ ". Will use Object intead");
				parameterType = Object.class;
			}
		} else if (paramType instanceof Class) {
			parameterType = (Class<?>) paramType;
		} else {
			LOG.warn("Unrecognized argument type"
					+ paramType.getClass().getSimpleName()
					+ ". Will use Object intead");
			parameterType = Object.class;
		}
		return parameterType;
	}

	/**
	 * It resolves and returns the primitive value depending on the type
	 * 
	 * 
	 * @param primitiveClass
	 *            The primitive type class
	 * @param annotations
	 *            The annotations to consider for this attribute
	 * @param attributeMetadata
	 * @return the primitive value depending on the type
	 * 
	 * @throws IllegalArgumentException
	 *             If a specific value was set in an annotation but it was not
	 *             possible to convert such value in the desired type
	 */
	private Object resolvePrimitiveValue(Class<?> primitiveClass,
			List<Annotation> annotations, AttributeMetadata attributeMetadata) {

		Object retValue = null;

		if (primitiveClass.equals(int.class)) {

			if (!annotations.isEmpty()) {

				retValue = getIntegerValueWithinRange(annotations,
						attributeMetadata);

			}

			if (retValue == null) {
				retValue = strategy.getInteger(attributeMetadata);
			}

		} else if (primitiveClass.equals(long.class)) {

			if (!annotations.isEmpty()) {

				retValue = getLongValueWithinRange(annotations,
						attributeMetadata);

			}

			if (retValue == null) {
				retValue = strategy.getLong(attributeMetadata);
			}

		} else if (primitiveClass.equals(float.class)) {

			if (!annotations.isEmpty()) {

				retValue = getFloatValueWithinRange(annotations,
						attributeMetadata);

			}

			if (retValue == null) {
				retValue = strategy.getFloat(attributeMetadata);
			}

		} else if (primitiveClass.equals(double.class)) {

			if (!annotations.isEmpty()) {

				retValue = getDoubleValueWithinRange(annotations,
						attributeMetadata);

			}

			if (retValue == null) {
				retValue = strategy.getDouble(attributeMetadata);
			}

		} else if (primitiveClass.equals(boolean.class)) {

			if (!annotations.isEmpty()) {

				retValue = getBooleanValueForAnnotation(annotations);

			}

			if (retValue == null) {
				retValue = strategy.getBoolean(attributeMetadata);
			}

		} else if (primitiveClass.equals(byte.class)) {

			if (!annotations.isEmpty()) {

				retValue = getByteValueWithinRange(annotations,
						attributeMetadata);

			}

			if (retValue == null) {
				retValue = strategy.getByte(attributeMetadata);
			}

		} else if (primitiveClass.equals(short.class)) {

			if (!annotations.isEmpty()) {

				retValue = getShortValueWithinRange(annotations,
						attributeMetadata);

			}

			if (retValue == null) {
				retValue = strategy.getShort(attributeMetadata);
			}

		} else if (primitiveClass.equals(char.class)) {

			if (!annotations.isEmpty()) {

				retValue = getCharacterValueWithinRange(annotations,
						attributeMetadata);

			}

			if (retValue == null) {
				retValue = strategy.getCharacter(attributeMetadata);
			}

		}
		return retValue;
	}

	/**
	 * It returns the boolean value indicated in the annotation.
	 * 
	 * @param annotations
	 *            The collection of annotations for the annotated attribute
	 * @return The boolean value indicated in the annotation
	 */
	private Boolean getBooleanValueForAnnotation(List<Annotation> annotations) {

		Boolean retValue = null;

		for (Annotation annotation : annotations) {

			if (PodamBooleanValue.class.isAssignableFrom(annotation.getClass())) {
				PodamBooleanValue strategy = (PodamBooleanValue) annotation;
				retValue = strategy.boolValue();

				break;
			}

		}

		return retValue;
	}

	/**
	 * It returns a random byte if the attribute was annotated with
	 * {@link PodamByteValue} or {@code null} otherwise
	 * 
	 * @param annotations
	 *            The list of annotations for this attribute
	 * 
	 * @param attributeMetadata
	 *            The attribute's metadata, if any, used for customisation
	 * 
	 * @return A random byte if the attribute was annotated with
	 * 
	 * @throws IllegalArgumentException
	 *             If the {@link PodamByteValue#numValue()} value has been set
	 *             and it is not convertible to a byte type
	 */
	private Byte getByteValueWithinRange(List<Annotation> annotations,
			AttributeMetadata attributeMetadata) {
		Byte retValue = null;

		for (Annotation annotation : annotations) {

			if (PodamByteValue.class.isAssignableFrom(annotation.getClass())) {
				PodamByteValue intStrategy = (PodamByteValue) annotation;

				String numValueStr = intStrategy.numValue();
				if (null != numValueStr && !"".equals(numValueStr)) {
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

					retValue = strategy.getByteInRange(minValue, maxValue,
							attributeMetadata);
				}

				break;

			}

		}
		return retValue;
	}

	/**
	 * It returns a random short if the attribute was annotated with
	 * {@link PodamShortValue} or {@code null} otherwise
	 * 
	 * @param annotations
	 *            The annotations with which the attribute was annotated
	 * 
	 * @param attributeMetadata
	 *            The attribute's metadata, if any, used for customisation
	 * 
	 * @return A random short if the attribute was annotated with
	 *         {@link PodamShortValue} or {@code null} otherwise
	 * 
	 * @throws IllegalArgumentException
	 *             If {@link PodamShortValue#numValue()} was set and its value
	 *             could not be converted to a Short type
	 */
	private Short getShortValueWithinRange(List<Annotation> annotations,
			AttributeMetadata attributeMetadata) {

		Short retValue = null;

		for (Annotation annotation : annotations) {

			if (PodamShortValue.class.isAssignableFrom(annotation.getClass())) {
				PodamShortValue shortStrategy = (PodamShortValue) annotation;

				String numValueStr = shortStrategy.numValue();
				if (null != numValueStr && !"".equals(numValueStr)) {
					try {
						retValue = Short.valueOf(numValueStr);
					} catch (NumberFormatException nfe) {
						String errMsg = "The precise value: "
								+ numValueStr
								+ " cannot be converted to a short type. An exception will be thrown.";
						LOG.error(errMsg);
						throw new IllegalArgumentException(errMsg, nfe);
					}
				} else {

					short minValue = shortStrategy.minValue();
					short maxValue = shortStrategy.maxValue();

					// Sanity check
					if (minValue > maxValue) {
						maxValue = minValue;
					}

					retValue = strategy.getShortInRange(minValue, maxValue,
							attributeMetadata);

				}

				break;

			}

		}
		return retValue;
	}

	/**
	 * It creates and returns a random {@link Character} value
	 * 
	 * @param annotations
	 *            The list of annotations which might customise the return value
	 * 
	 * @param attributeMetadata
	 *            The attribute's metadata, if any, used for customisation
	 * 
	 * @return A random {@link Character} value
	 */
	private Character getCharacterValueWithinRange(
			List<Annotation> annotations, AttributeMetadata attributeMetadata) {

		Character retValue = null;

		for (Annotation annotation : annotations) {

			if (PodamCharValue.class.isAssignableFrom(annotation.getClass())) {
				PodamCharValue annotationStrategy = (PodamCharValue) annotation;

				char charValue = annotationStrategy.charValue();
				if (charValue != ' ') {
					retValue = charValue;

				} else {

					char minValue = annotationStrategy.minValue();
					char maxValue = annotationStrategy.maxValue();

					// Sanity check
					if (minValue > maxValue) {
						maxValue = minValue;
					}

					retValue = strategy.getCharacterInRange(minValue, maxValue,
							attributeMetadata);

				}

				break;

			}

		}
		return retValue;
	}

	/**
	 * Returns either a customised int value if a {@link PodamIntValue}
	 * annotation was provided or a random integer if this was not the case
	 * 
	 * @param annotations
	 *            The list of annotations for the int attribute
	 * 
	 * @param attributeMetadata
	 *            The attribute's metadata, if any, used for customisation
	 * 
	 * @return Either a customised int value if a {@link PodamIntValue}
	 *         annotation was provided or a random integer if this was not the
	 *         case
	 * 
	 * @throws IllegalArgumentException
	 *             If it was not possible to convert the
	 *             {@link PodamIntValue#numValue()} to an Integer
	 */
	private Integer getIntegerValueWithinRange(List<Annotation> annotations,
			AttributeMetadata attributeMetadata) {

		Integer retValue = null;

		for (Annotation annotation : annotations) {

			if (PodamIntValue.class.isAssignableFrom(annotation.getClass())) {
				PodamIntValue intStrategy = (PodamIntValue) annotation;

				String numValueStr = intStrategy.numValue();
				if (null != numValueStr && !"".equals(numValueStr)) {
					try {
						retValue = Integer.valueOf(numValueStr);
					} catch (NumberFormatException nfe) {
						String errMsg = "The annotation value: "
								+ numValueStr
								+ " could not be converted to an Integer. An exception will be thrown.";
						LOG.error(errMsg);
						throw new IllegalArgumentException(errMsg, nfe);

					}

				} else {

					int minValue = intStrategy.minValue();
					int maxValue = intStrategy.maxValue();

					// Sanity check
					if (minValue > maxValue) {
						maxValue = minValue;
					}

					retValue = strategy.getIntegerInRange(minValue, maxValue,
							attributeMetadata);

				}

				break;

			}

		}
		return retValue;
	}

	/**
	 * Returns either a customised float value if a {@link PodamFloatValue}
	 * annotation was provided or a random float if this was not the case
	 * 
	 * @param annotations
	 *            The list of annotations for the int attribute
	 * 
	 * @param attributeMetadata
	 *            The attribute's metadata, if any, used for customisation
	 * 
	 * 
	 * @return Either a customised float value if a {@link PodamFloatValue}
	 *         annotation was provided or a random float if this was not the
	 *         case
	 * 
	 * @throws IllegalArgumentException
	 *             If {@link PodamFloatValue#numValue()} contained a value not
	 *             convertible to a Float type
	 */
	private Float getFloatValueWithinRange(List<Annotation> annotations,
			AttributeMetadata attributeMetadata) {

		Float retValue = null;

		for (Annotation annotation : annotations) {

			if (PodamFloatValue.class.isAssignableFrom(annotation.getClass())) {
				PodamFloatValue floatStrategy = (PodamFloatValue) annotation;

				String numValueStr = floatStrategy.numValue();
				if (null != numValueStr && !"".equals(numValueStr)) {
					try {
						retValue = Float.valueOf(numValueStr);
					} catch (NumberFormatException nfe) {
						String errMsg = "The annotation value: "
								+ numValueStr
								+ " could not be converted to a Float. An exception will be thrown.";
						LOG.error(errMsg);
						throw new IllegalArgumentException(errMsg, nfe);
					}
				} else {

					float minValue = floatStrategy.minValue();
					float maxValue = floatStrategy.maxValue();

					// Sanity check
					if (minValue > maxValue) {
						maxValue = minValue;
					}

					retValue = strategy.getFloatInRange(minValue, maxValue,
							attributeMetadata);

				}

				break;

			}

		}
		return retValue;
	}

	/**
	 * It creates and returns a random {@link Double} value
	 * 
	 * @param annotations
	 *            The list of annotations which might customise the return value
	 * 
	 * @param attributeMetadata
	 *            The attribute's metadata, if any, used for customisation *
	 * 
	 * @return a random {@link Double} value
	 */
	private Double getDoubleValueWithinRange(List<Annotation> annotations,
			AttributeMetadata attributeMetadata) {

		Double retValue = null;

		for (Annotation annotation : annotations) {

			if (PodamDoubleValue.class.isAssignableFrom(annotation.getClass())) {
				PodamDoubleValue doubleStrategy = (PodamDoubleValue) annotation;

				String numValueStr = doubleStrategy.numValue();
				if (null != numValueStr && !"".equals(numValueStr)) {

					try {
						retValue = Double.valueOf(numValueStr);
					} catch (NumberFormatException nfe) {
						String errMsg = "The annotation value: "
								+ numValueStr
								+ " could not be converted to a Double. An exception will be thrown.";
						LOG.error(errMsg);
						throw new IllegalArgumentException(errMsg, nfe);
					}

				} else {

					double minValue = doubleStrategy.minValue();
					double maxValue = doubleStrategy.maxValue();

					// Sanity check
					if (minValue > maxValue) {
						maxValue = minValue;
					}

					retValue = strategy.getDoubleInRange(minValue, maxValue,
							attributeMetadata);
				}

				break;

			}

		}
		return retValue;

	}

	/**
	 * Returns either a customised long value if a {@link PodamLongValue}
	 * annotation was provided or a random long if this was not the case
	 * 
	 * @param annotations
	 *            The list of annotations for the int attribute
	 * @param attributeMetadata
	 *            The attribute's metadata, if any, used for customisation
	 * 
	 * @return Either a customised long value if a {@link PodamLongValue}
	 *         annotation was provided or a random long if this was not the case
	 * 
	 * @throws IllegalArgumentException
	 *             If it was not possible to convert
	 *             {@link PodamLongValue#numValue()} to a Long
	 */
	private Long getLongValueWithinRange(List<Annotation> annotations,
			AttributeMetadata attributeMetadata) {

		Long retValue = null;

		for (Annotation annotation : annotations) {

			if (PodamLongValue.class.isAssignableFrom(annotation.getClass())) {
				PodamLongValue longStrategy = (PodamLongValue) annotation;

				String numValueStr = longStrategy.numValue();
				if (null != numValueStr && !"".equals(numValueStr)) {
					try {
						retValue = Long.valueOf(numValueStr);
					} catch (NumberFormatException nfe) {
						String errMsg = "The annotation value: "
								+ numValueStr
								+ " could not be converted to a Long. An exception will be thrown.";
						LOG.error(errMsg);
						throw new IllegalArgumentException(errMsg, nfe);
					}
				} else {

					long minValue = longStrategy.minValue();
					long maxValue = longStrategy.maxValue();

					// Sanity check
					if (minValue > maxValue) {
						maxValue = minValue;
					}

					retValue = strategy.getLongInRange(minValue, maxValue,
							attributeMetadata);

				}

				break;

			}

		}
		return retValue;
	}

	/**
	 * It attempts to resolve the given class as a wrapper class and if this is
	 * the case it assigns a random value
	 * 
	 * 
	 * @param candidateWrapperClass
	 *            The class which might be a wrapper class
	 * @param attributeMetadata
	 *            The attribute's metadata, if any, used for customisation
	 * @return {@code null} if this is not a wrapper class, otherwise an Object
	 *         with the value for the wrapper class
	 */
	private Object resolveWrapperValue(Class<?> candidateWrapperClass,
			List<Annotation> annotations, AttributeMetadata attributeMetadata) {

		Object retValue = null;

		if (candidateWrapperClass.equals(Integer.class)) {

			if (!annotations.isEmpty()) {

				retValue = getIntegerValueWithinRange(annotations,
						attributeMetadata);

			}

			if (retValue == null) {
				retValue = strategy.getInteger(attributeMetadata);
			}

		} else if (candidateWrapperClass.equals(Long.class)) {

			if (!annotations.isEmpty()) {

				retValue = getLongValueWithinRange(annotations,
						attributeMetadata);

			}

			if (retValue == null) {
				retValue = strategy.getLong(attributeMetadata);
			}

		} else if (candidateWrapperClass.equals(Float.class)) {

			if (!annotations.isEmpty()) {

				retValue = getFloatValueWithinRange(annotations,
						attributeMetadata);

			}

			if (retValue == null) {
				retValue = strategy.getFloat(attributeMetadata);
			}

		} else if (candidateWrapperClass.equals(Double.class)) {

			if (!annotations.isEmpty()) {

				retValue = getDoubleValueWithinRange(annotations,
						attributeMetadata);

			}

			if (retValue == null) {
				retValue = strategy.getDouble(attributeMetadata);
			}

		} else if (candidateWrapperClass.equals(Boolean.class)) {

			if (!annotations.isEmpty()) {

				retValue = getBooleanValueForAnnotation(annotations);

			}

			if (retValue == null) {
				retValue = strategy.getBoolean(attributeMetadata);
			}

		} else if (candidateWrapperClass.equals(Byte.class)) {

			if (!annotations.isEmpty()) {

				retValue = getByteValueWithinRange(annotations,
						attributeMetadata);

			}

			if (retValue == null) {
				retValue = strategy.getByte(attributeMetadata);
			}

		} else if (candidateWrapperClass.equals(Short.class)) {

			if (!annotations.isEmpty()) {

				retValue = getShortValueWithinRange(annotations,
						attributeMetadata);

			}

			if (retValue == null) {
				retValue = strategy.getShort(attributeMetadata);
			}

		} else if (candidateWrapperClass.equals(Character.class)) {

			if (!annotations.isEmpty()) {

				retValue = getCharacterValueWithinRange(annotations,
						attributeMetadata);

			}

			if (retValue == null) {
				retValue = strategy.getCharacter(attributeMetadata);
			}

		}

		return retValue;
	}

	/**
	 * It creates and returns an instance of the given class if at least one of
	 * its constructors has been annotated with {@link PodamConstructor}
	 * 
	 * @param <T>
	 *            The type of the instance to return
	 * 
	 * @param pojoClass
	 *            The class of which an instance is required
	 * @param depth
	 *            How many instances of the same class have been created so far
	 * @param genericTypeArgs
	 *            The generic type arguments for the current generic class
	 *            instance
	 * @return an instance of the given class if at least one of its
	 *         constructors has been annotated with {@link PodamConstructor}
	 * @throws IllegalArgumentException
	 *             If an illegal argument was passed to the constructor
	 * @throws InstantiationException
	 *             If an exception occurred during instantiation
	 * @throws IllegalAccessException
	 *             If security was violated while creating the object
	 * @throws InvocationTargetException
	 *             If an exception occurred while invoking the constructor or
	 *             factory method
	 * @throws ClassNotFoundException
	 *             If it was not possible to create a class from a string
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private <T> T resolvePojoWithoutSetters(Class<T> pojoClass, int depth,
			Type... genericTypeArgs) throws IllegalArgumentException,
			InstantiationException, IllegalAccessException,
			InvocationTargetException, ClassNotFoundException {

		T retValue = null;

		Constructor<?>[] constructors = pojoClass.getConstructors();
		if (constructors.length == 0) {
			retValue = (T) createNewInstanceForClassWithoutConstructors(
					pojoClass, pojoClass, genericTypeArgs);
		} else {

			// There are public constructors. We want constructor with minumum
			// number of parameters to speed up the creation
			Arrays.sort(constructors, ConstructorComparator);

			for (Constructor<?> constructor : constructors) {

				Object[] parameterValues = getParameterValuesForConstructor(
						constructor, pojoClass, genericTypeArgs);

				// Being a generic method we cannot be sure on the identity of
				// T, therefore the mismatch between the newInstance() return
				// value (Object) and T is acceptable, thus the SuppressWarning
				// annotation

				try {
					retValue = (T) constructor.newInstance(parameterValues);
					if (retValue instanceof Collection
							&& ((Collection) retValue).size() == 0) {
						LOG.debug("We could create an instance with constructor: " + constructor
								  + ", but collection is empty" + ". Will try with another one.");
					} else {
						LOG.debug("We could create an instance with constructor: " + constructor);
						break;
					}
				} catch (Throwable t) {
					LOG.debug("We couldn't create an instance for pojo: "
							+ pojoClass + " for constructor: " + constructor
							+ ". Will try with another one. " + t.getMessage());
				}

			}

			if (retValue == null) {
				LOG.debug("For class: " + pojoClass.getName()
						+ " PODAM could not possibly create a value."
						+ " This attribute will be returned as null.");
			}

		}

		return retValue;
	}

	/**
	 * Generic method which returns an instance of the given class filled with
	 * values dictated by the strategy
	 * 
	 * @param <T>
	 *            The type for which a filled instance is required
	 * 
	 * @param pojoClass
	 *            The name of the class for which an instance filled with values
	 *            is required
	 * @param depth
	 *            How many times {@code pojoClass} has been found. This will be
	 *            used for reentrant objects
	 * @param genericTypeArgs
	 *            The generic type arguments for the current generic class
	 *            instance
	 * @return An instance of <T> filled with dummy values
	 * 
	 * @throws PodamMockeryException
	 *             if a problem occurred while creating a POJO instance or while
	 *             setting its state
	 */
	@SuppressWarnings("unchecked")
	private <T> T manufacturePojoInternal(Class<T> pojoClass, int depth,
			Type... genericTypeArgs) {
		try {

			T retValue = null;

			final TypeVariable<?>[] typeParameters = pojoClass
					.getTypeParameters();
			if (typeParameters.length > genericTypeArgs.length) {
				LOG.warn(pojoClass.getCanonicalName()
						+ " is missing generic type arguments, expected "
						+ typeParameters.length + " found "
						+ genericTypeArgs.length + ". Returning null.");

				return retValue;

			}

			if (pojoClass.isPrimitive()) {
				// For JDK POJOs we can't retrieve attribute name
				ArrayList<Annotation> annotations = new ArrayList<Annotation>();
				String noName = null;
				return (T) resolvePrimitiveValue(pojoClass, annotations,
						new AttributeMetadata(noName, pojoClass, annotations));
			}

			boolean thereIsAMatchingFactory = factoryMap.containsKey(pojoClass);
			if (thereIsAMatchingFactory) {
				AttributeStrategy attributeStrategy = factoryMap.get(pojoClass);
				T constructedClass = (T) attributeStrategy.getValue();
				return constructedClass;
			}

			if (pojoClass.isInterface()
					|| Modifier.isAbstract(pojoClass.getModifiers())) {
				LOG.warn("Cannot instantiate an interface or abstract class. Returning null.");
				return null;
			}

			ClassInfo classInfo = PodamUtils.getClassInfo(pojoClass,
					excludeAnnotations);

			if (classInfo.getClassSetters().isEmpty()) {

				// A rudimentary attempt to manage immutable classes (e.g. with
				// constructor only and final fields - no setters)
				return this.resolvePojoWithoutSetters(pojoClass, depth,
						genericTypeArgs);

			}

			// If a public no-arg constructor can be found we use it,
			// otherwise we try to find a non-public one and we use that. If the
			// class does not have a no-arg constructor we search for a suitable
			// constructor.

			try {

				Constructor<?>[] constructors = pojoClass.getConstructors();

				if (constructors == null || constructors.length == 0) {

					LOG.warn("No public constructors were found. "
							+ "We'll look for a default, non-public constructor. ");
					Constructor<T> defaultConstructor = pojoClass
							.getDeclaredConstructor(new Class[] {});
					LOG.info("Will use: " + defaultConstructor);

					// Security hack
					defaultConstructor.setAccessible(true);
					retValue = defaultConstructor.newInstance();

				} else {

					retValue = resolvePojoWithoutSetters(pojoClass, depth,
						genericTypeArgs);
				}

			} catch (SecurityException e) {
				throw new PodamMockeryException(
						"Security exception while applying introspection.",
						e);
			} catch (NoSuchMethodException e1) {

				LOG.warn("No default (public or non-public) constructors were found. "
						+ "Also no other public constructors were found. "
						+ "Your last hope is that we find a non-public, non-default constructor.");

				Constructor<?>[] constructors = pojoClass
						.getDeclaredConstructors();
				if (constructors == null || constructors.length == 0) {
					throw new IllegalStateException(
							"The POJO "
									+ pojoClass
									+ " appears without constructors. How is this possible? ");
				}

				LOG.info("Will use: " + constructors[0]);

				// It uses the first public constructor found
				Object[] parameterValuesForConstructor = getParameterValuesForConstructor(
						constructors[0], pojoClass, genericTypeArgs);
				constructors[0].setAccessible(true);
				retValue = (T) constructors[0]
						.newInstance(parameterValuesForConstructor);

			}

			Class<?>[] parameterTypes = null;
			Class<?> attributeType = null;

			// According to JavaBeans standards, setters should have only
			// one argument
			Object setterArg = null;
			for (Method setter : classInfo.getClassSetters()) {

				List<Annotation> pojoAttributeAnnotations = retrieveFieldAnnotations(
						pojoClass, setter);

				String attributeName = PodamUtils
						.extractFieldNameFromSetterMethod(setter);

				parameterTypes = setter.getParameterTypes();
				if (parameterTypes.length != 1) {
					throw new IllegalStateException(
							"A JavaBean setter should have only one argument");
				}

				// A class which has got an attribute to itself (e.g.
				// recursive hierarchies)
				attributeType = parameterTypes[0];

				// If an attribute has been annotated with
				// PodamAttributeStrategy, it takes the precedence over any
				// other strategy. Additionally we don't pass the attribute
				// metadata for value customisation; if user went to the extent
				// of specifying a PodamAttributeStrategy annotation for an
				// attribute they are already customising the value assigned to
				// that attribute.

				PodamStrategyValue attributeStrategyAnnotation = containsAttributeStrategyAnnotation(pojoAttributeAnnotations);
				if (null != attributeStrategyAnnotation) {

					AttributeStrategy<?> attributeStrategy = attributeStrategyAnnotation
							.value().newInstance();

					if (LOG.isDebugEnabled()) {
						LOG.debug("The attribute: "
								+ attributeName
								+ " will be filled using the following strategy: "
								+ attributeStrategy);
					}

					// TODO To pass the AttributeMetadata
					setterArg = returnAttributeDataStrategyValue(attributeType,
							attributeStrategy);

				} else {

					final Map<String, Type> typeArgsMap = new HashMap<String, Type>();
					Type[] genericTypeArgsExtra = fillTypeArgMap(typeArgsMap,
							typeParameters, genericTypeArgs);
					if (genericTypeArgsExtra != null) {
						LOG.warn(String.format(
								"Lost %d generic type arguments",
								genericTypeArgsExtra.length));
					}

					if (attributeType.equals(pojoClass)) {
						if (depth < PodamConstants.MAX_DEPTH) {
							depth++;
							setterArg = this.manufacturePojoInternal(
									attributeType, depth);
							setter.invoke(retValue, setterArg);
							continue;

						} else {

							setterArg = createNewInstanceForClassWithoutConstructors(
									pojoClass, pojoClass);

							setter.invoke(retValue, setterArg);
							depth = 0;
							continue;

						}

					}

					Type[] typeArguments = new Type[] {};
					// If the parameter is a generic parameterized type resolve
					// the actual type arguments
					if (setter.getGenericParameterTypes()[0] instanceof ParameterizedType) {
						final ParameterizedType attributeParameterizedType = (ParameterizedType) setter
								.getGenericParameterTypes()[0];
						typeArguments = attributeParameterizedType
								.getActualTypeArguments();
					} else if (setter.getGenericParameterTypes()[0] instanceof TypeVariable) {
						final TypeVariable<?> typeVariable = (TypeVariable<?>) setter
								.getGenericParameterTypes()[0];
						Type type = typeArgsMap.get(typeVariable.getName());
						if (type instanceof ParameterizedType) {
							final ParameterizedType attributeParameterizedType = (ParameterizedType) type;

							typeArguments = attributeParameterizedType
									.getActualTypeArguments();
							attributeType = (Class<?>) attributeParameterizedType
									.getRawType();
						} else {
							attributeType = (Class<?>) type;
						}
					}

					setterArg = manufactureAttributeValue(pojoClass,
							attributeType, pojoAttributeAnnotations,
							attributeName, typeArgsMap, typeArguments);

				}

				if (setterArg != null) {
					// If the setter is not public we set it to accessible for
					// the sake of
					// usability. However this violates Javabean standards and
					// it's a security hack
					if (!Modifier.isPublic(setter.getModifiers())) {
						LOG.warn("The setter: "
								+ setter.getName()
								+ " is not public. Setting it to accessible. "
								+ "However this is a security hack and your code should really adhere to Javabean standards.");
						setter.setAccessible(true);
					}
					setter.invoke(retValue, setterArg);
				} else {
					LOG.warn("Couldn't find a suitable value for attribute: "
							+ attributeName
							+ ". This POJO attribute will be left to null.");
				}

			}

			return retValue;

		} catch (InstantiationException e) {
			throw new PodamMockeryException(
					"An instantiation exception occurred", e);
		} catch (IllegalAccessException e) {
			throw new PodamMockeryException("An illegal access occurred", e);
		} catch (IllegalArgumentException e) {
			throw new PodamMockeryException("An illegal argument was passed", e);
		} catch (InvocationTargetException e) {
			throw new PodamMockeryException("Invocation Target Exception", e);
		} catch (ClassNotFoundException e) {
			throw new PodamMockeryException("ClassNotFoundException Exception",
					e);
		}
	}

	/**
	 * It manufactures and returns the value for a POJO attribute.
	 * 
	 * 
	 * @param pojoClass
	 *            The POJO class being filled with values
	 * @param attributeType
	 *            The type of the attribute for which a value is being
	 *            manufactured
	 * @param annotations
	 *            The annotations for the attribute being considered
	 * @param attributeName
	 *            The attribute name
	 * @param genericTypeArgs
	 *            The generic type arguments for the current generic class
	 *            instance
	 * @return The value for an attribute
	 * 
	 * @throws InstantiationException
	 *             If an exception occurred during instantiation
	 * @throws IllegalAccessException
	 *             If security was violated while creating the object
	 * @throws InvocationTargetException
	 *             If an exception occurred while invoking the constructor or
	 *             factory method
	 * @throws ClassNotFoundException
	 *             If it was not possible to create a class from a string
	 * @throws IllegalArgumentException
	 *             <ul>
	 *             <li>If an illegal argument was passed</li>
	 *             <li>If an invalid value was set for a precise value in an
	 *             annotation and such value could not be converted to the
	 *             desired type</li>
	 *             </ul>
	 * 
	 */
	private Object manufactureAttributeValue(Class<?> pojoClass,
			Class<?> attributeType, List<Annotation> annotations,
			String attributeName, Type... genericTypeArgs)
			throws InstantiationException, IllegalAccessException,
			InvocationTargetException, IllegalArgumentException,
			ClassNotFoundException {

		Map<String, Type> nullTypeArgsMap = null;

		return manufactureAttributeValue(pojoClass, attributeType, annotations,
				attributeName, nullTypeArgsMap, genericTypeArgs);
	}

	/**
	 * It manufactures and returns the value for a POJO attribute.
	 * 
	 * 
	 * @param pojoClass
	 *            The POJO class being filled with values
	 * @param attributeType
	 *            The type of the attribute for which a value is being
	 *            manufactured
	 * @param annotations
	 *            The annotations for the attribute being considered
	 * @param attributeName
	 *            The attribute name
	 * @param typeArgsMap
	 *            a map relating the generic class arguments ("<T, V>" for
	 *            example) with their actual types
	 * @param genericTypeArgs
	 *            The generic type arguments for the current generic class
	 *            instance
	 * @return The value for an attribute
	 * 
	 * @throws InstantiationException
	 *             If an exception occurred during instantiation
	 * @throws IllegalAccessException
	 *             If security was violated while creating the object
	 * @throws InvocationTargetException
	 *             If an exception occurred while invoking the constructor or
	 *             factory method
	 * @throws ClassNotFoundException
	 *             If it was not possible to create a class from a string
	 * @throws IllegalArgumentException
	 *             <ul>
	 *             <li>If an illegal argument was passed</li>
	 *             <li>If an invalid value was set for a precise value in an
	 *             annotation and such value could not be converted to the
	 *             desired type</li>
	 *             </ul>
	 * 
	 */
	@SuppressWarnings("rawtypes")
	private Object manufactureAttributeValue(Class<?> pojoClass,
			Class<?> attributeType, List<Annotation> annotations,
			String attributeName, Map<String, Type> typeArgsMap,
			Type... genericTypeArgs) throws InstantiationException,
			IllegalAccessException, InvocationTargetException,
			IllegalArgumentException, ClassNotFoundException {
		Object attributeValue = null;

		Class<?> realAttributeType;
		if (genericTypeArgs.length > 0 && genericTypeArgs[0] instanceof Class
				&& attributeType.isAssignableFrom((Class) genericTypeArgs[0])) {
			realAttributeType = (Class) genericTypeArgs[0];
		} else {
			realAttributeType = attributeType;
		}
		AttributeMetadata attributeMetadata = new AttributeMetadata(
				attributeName, realAttributeType, annotations);

		// Primitive type
		if (realAttributeType.isPrimitive()) {

			attributeValue = resolvePrimitiveValue(realAttributeType,
					annotations, attributeMetadata);

			// Wrapper type
		} else if (isWrapper(realAttributeType)) {

			attributeValue = resolveWrapperValue(realAttributeType,
					annotations, attributeMetadata);

			// String type
		} else if (realAttributeType.equals(String.class)) {

			attributeValue = resolveStringValue(annotations, attributeMetadata);

		} else if (realAttributeType.getName().startsWith("[")) {

			// Array type

			attributeValue = resolveArrayElementValue(realAttributeType,
					annotations, pojoClass, attributeName, typeArgsMap);

			// Otherwise it's a different type of Object (including
			// the Object class)
		} else if (Collection.class.isAssignableFrom(realAttributeType)) {

			// Collection type
			attributeValue = resolveCollectionValueWhenCollectionIsPojoAttribute(
					pojoClass, realAttributeType, attributeName, annotations,
					typeArgsMap, genericTypeArgs);

		} else if (Map.class.isAssignableFrom(realAttributeType)) {

			// Map type
			attributeValue = resolveMapValueWhenMapIsPojoAttribute(pojoClass,
					realAttributeType, attributeName, annotations, typeArgsMap,
					genericTypeArgs);

		} else if (realAttributeType.getName().startsWith("java.")
				|| realAttributeType.getName().startsWith("javax.")) {

			// For classes in the Java namespace we attempt the no-args or the
			// factory constructor strategy

			attributeValue = createNewInstanceForClassWithoutConstructors(
					pojoClass, realAttributeType, genericTypeArgs);

		} else if (realAttributeType.isEnum()) {

			// Enum type
			int enumConstantsLength = realAttributeType.getEnumConstants().length;

			if (enumConstantsLength > 0) {
				int enumIndex = strategy.getIntegerInRange(0,
						enumConstantsLength, attributeMetadata)
						% enumConstantsLength;
				attributeValue = realAttributeType.getEnumConstants()[enumIndex];
				// attributeValue = realAttributeType.getEnumConstants()[0];
			}

		} else {

			// For any other type, we use the PODAM strategy
			attributeValue = this.manufacturePojo(realAttributeType,
					genericTypeArgs);

		}

		return attributeValue;
	}

	/**
	 * It creates and returns a String value, eventually customised by
	 * annotations
	 * 
	 * @param annotations
	 *            The list of annotations used to customise the String value, if
	 *            any
	 * @param attributeMetadata
	 * @return a String value, eventually customised by annotations
	 * @throws IllegalAccessException
	 *             If an exception occurred while creating an instance of the
	 *             strategy
	 * @throws InstantiationException
	 *             If an exception occurred while creating an instance of the
	 *             strategy
	 * 
	 * @throws IllegalArgumentException
	 *             If {@link PodamStrategyValue} was specified but the type was
	 *             not correct for the attribute being set
	 */
	private String resolveStringValue(List<Annotation> annotations,
			AttributeMetadata attributeMetadata) throws InstantiationException,
			IllegalAccessException {

		String retValue = null;

		if (annotations == null || annotations.isEmpty()) {

			retValue = strategy.getStringValue(attributeMetadata);

		} else {

			for (Annotation annotation : annotations) {

				if (!PodamStringValue.class.isAssignableFrom(annotation
						.getClass())) {
					continue;
				}

				// A specific value takes precedence over the length
				PodamStringValue podamAnnotation = (PodamStringValue) annotation;

				if (podamAnnotation.strValue() != null
						&& podamAnnotation.strValue().length() > 0) {

					retValue = podamAnnotation.strValue();

				} else {

					retValue = strategy.getStringOfLength(
							podamAnnotation.length(), attributeMetadata);

				}

			}

			if (retValue == null) {
				retValue = strategy.getStringValue(attributeMetadata);
			}

		}

		return retValue;
	}

	/**
	 * It returns a {@link PodamStrategyValue} if one was specified, or
	 * {@code null} otherwise.
	 * 
	 * @param annotations
	 *            The list of annotations
	 * @return {@code true} if the list of annotations contains at least one
	 *         {@link PodamStrategyValue} annotation.
	 */
	private PodamStrategyValue containsAttributeStrategyAnnotation(
			List<Annotation> annotations) {
		PodamStrategyValue retValue = null;

		for (Annotation annotation : annotations) {
			if (PodamStrategyValue.class
					.isAssignableFrom(annotation.getClass())) {
				retValue = (PodamStrategyValue) annotation;
				break;
			}
		}

		return retValue;
	}

	/**
	 * It returns {@code true} if this class is a wrapper class, {@code false}
	 * otherwise
	 * 
	 * @param candidateWrapperClass
	 *            The class to check
	 * @return {@code true} if this class is a wrapper class, {@code false}
	 *         otherwise
	 */
	private boolean isWrapper(Class<?> candidateWrapperClass) {

		return candidateWrapperClass.equals(Byte.class) ? true
				: candidateWrapperClass.equals(Boolean.class) ? true
						: candidateWrapperClass.equals(Character.class) ? true
								: candidateWrapperClass.equals(Short.class) ? true
										: candidateWrapperClass
												.equals(Integer.class) ? true
												: candidateWrapperClass
														.equals(Long.class) ? true
														: candidateWrapperClass
																.equals(Float.class) ? true
																: candidateWrapperClass
																		.equals(Double.class) ? true
																		: false;
	}

	/**
	 * Given the original class and the setter method, it returns all
	 * annotations for the field or an empty collection if no custom annotations
	 * were found on the field
	 * 
	 * @param clazz
	 *            The class containing the annotated attribute
	 * @param setter
	 *            The setter method
	 * @return all annotations for the field
	 * @throws NoSuchFieldException
	 *             If the field could not be found
	 * @throws SecurityException
	 *             if a security exception occurred
	 */
	private List<Annotation> retrieveFieldAnnotations(Class<?> clazz,
			Method setter) {

		List<Annotation> retValue = new ArrayList<Annotation>();

		// Checks if the field has got any custom annotations
		String attributeName = PodamUtils
				.extractFieldNameFromSetterMethod(setter);
		Field setterField = null;

		while (clazz != null) {
			try {
				setterField = clazz.getDeclaredField(attributeName);
				break;
			} catch (NoSuchFieldException e) {
				clazz = clazz.getSuperclass();
			} catch (SecurityException e) {
				throw e;
			}

		}

		if (setterField != null) {
			Annotation[] annotations = setterField.getAnnotations();

			if (annotations != null && annotations.length != 0) {
				retValue = Arrays.asList(annotations);
			}
		}

		return retValue;

	}

	/**
	 * It returns a collection of some sort with some data in it.
	 * 
	 * 
	 * @param pojoClass
	 *            The POJO being analysed
	 * @param collectionType
	 *            The type of the attribute being evaluated
	 * @param annotations
	 *            The set of annotations for the annotated attribute. It might
	 *            be empty
	 * @param attributeName
	 *            The name of the field being set
	 * @param typeArgsMap
	 *            a map relating the generic class arguments ("<T, V>" for
	 *            example) with their actual types
	 * @param genericTypeArgs
	 *            The generic type arguments for the current generic class
	 *            instance
	 * @return a collection of some sort with some data in it
	 * @throws PodamMockeryException
	 *             An exception occurred while resolving the collection
	 * @throws IllegalArgumentException
	 *             If the field name is null or empty
	 */
	@SuppressWarnings({ "unchecked" })
	private Collection<? super Object> resolveCollectionValueWhenCollectionIsPojoAttribute(
			Class<?> pojoClass, Class<?> collectionType, String attributeName,
			List<Annotation> annotations, Map<String, Type> typeArgsMap,
			Type... genericTypeArgs) {

		// This needs to be generic because collections can be of any type
		Collection<? super Object> retValue = null;

		try {
			try {
				validateAttributeName(attributeName);

				// Checks whether the user initialized the collection in the
				// class
				// definition
				Object newInstance = pojoClass.newInstance();

				Field field = null;

				Class<?> clazz = pojoClass;

				while (clazz != null) {
					try {
						field = clazz.getDeclaredField(attributeName);
						break;
					} catch (NoSuchFieldException e) {
						clazz = clazz.getSuperclass();
					} catch (SecurityException e) {
						throw e;
					}

				}
				if (field == null) {
					throw new NoSuchFieldException();
				}

				// It allows to invoke Field.get on private fields
				field.setAccessible(true);

				Collection<? super Object> coll = (Collection<? super Object>) field
						.get(newInstance);

				if (null != coll) {
					retValue = coll;
				} else {
					retValue = resolveCollectionType(collectionType);
				}
			} catch (Exception e) {
				// Name is empty or could not call an empty constructor
				// (probably this call is for a parameterized constructor)
				// Create a new Collection
				retValue = resolveCollectionType(collectionType);
			}

			Class<?> typeClass = null;

			AtomicReference<Type[]> elementGenericTypeArgs = new AtomicReference<Type[]>(
					new Type[] {});
			if (genericTypeArgs == null || genericTypeArgs.length == 0) {

				LOG.warn("The collection attribute: "
						+ attributeName
						+ " does not have a type. We will assume Object for you");
				// Support for non-generified collections
				typeClass = Object.class;

			} else {
				Type actualTypeArgument = genericTypeArgs[0];

				typeClass = resolveGenericParameter(actualTypeArgument,
						typeArgsMap, elementGenericTypeArgs);
			}

			fillCollection(pojoClass, attributeName, annotations, retValue,
					typeClass, elementGenericTypeArgs.get());

		} catch (SecurityException e) {
			throw new PodamMockeryException(
					"An exception occurred while resolving the collection", e);
		} catch (IllegalArgumentException e) {
			throw new PodamMockeryException(
					"An exception occurred while resolving the collection", e);
		} catch (InstantiationException e) {
			throw new PodamMockeryException(
					"An exception occurred while resolving the collection", e);
		} catch (IllegalAccessException e) {
			throw new PodamMockeryException(
					"An exception occurred while resolving the collection", e);
		} catch (ClassNotFoundException e) {
			throw new PodamMockeryException(
					"An exception occurred while resolving the collection", e);
		} catch (InvocationTargetException e) {
			throw new PodamMockeryException(
					"An exception occurred while resolving the collection", e);
		}

		return retValue;
	}

	/**
	 * It fills a collection with the required number of elements of the
	 * required type.
	 * 
	 * <p>
	 * This method has a so-called side effect. It updates the collection passed
	 * as argument.
	 * </p>
	 * 
	 * @param pojoClass
	 *            The POJO where the collection attribute is defined
	 * @param attributeName
	 *            The attribute name
	 * @param annotations
	 *            The annotations for this attribute
	 * @param collection
	 *            The Collection to be filled
	 * @param collectionElementType
	 *            The type of the collection element
	 * @param genericTypeArgs
	 *            The generic type arguments for the current generic class
	 *            instance
	 * @throws InstantiationException
	 *             If an exception occurred during instantiation
	 * @throws IllegalAccessException
	 *             If security was violated while creating the object
	 * @throws InvocationTargetException
	 *             If an exception occurred while invoking the constructor or
	 *             factory method
	 * @throws ClassNotFoundException
	 *             If it was not possible to create a class from a string
	 * 
	 */
	private void fillCollection(Class<?> pojoClass, String attributeName,
			List<Annotation> annotations,
			Collection<? super Object> collection,
			Class<?> collectionElementType, Type... genericTypeArgs)
			throws InstantiationException, IllegalAccessException,
			InvocationTargetException, ClassNotFoundException {

		// If the user defined a strategy to fill the collection elements,
		// we use it
		PodamCollection collectionAnnotation = null;
		AttributeStrategy<?> elementStrategy = null;
		for (Annotation annotation : annotations) {
			if (PodamCollection.class.isAssignableFrom(annotation.getClass())) {
				collectionAnnotation = (PodamCollection) annotation;
				break;
			}

		}

		int nbrElements = strategy.getNumberOfCollectionElements();

		if (null != collectionAnnotation) {

			nbrElements = collectionAnnotation.nbrElements();
			elementStrategy = collectionAnnotation.collectionElementStrategy()
					.newInstance();
		}

		for (int i = 0; i < nbrElements; i++) {

			// The default
			if (null != elementStrategy
					&& ObjectStrategy.class.isAssignableFrom(elementStrategy
							.getClass())
					&& Object.class.equals(collectionElementType)) {
				LOG.debug("Element strategy is ObjectStrategy and collection element is of type Object: using the ObjectStrategy strategy");
				collection.add(elementStrategy.getValue());
			} else if (null != elementStrategy
					&& !ObjectStrategy.class.isAssignableFrom(elementStrategy
							.getClass())) {
				LOG.debug("Collection elements will be filled using the following strategy: "
						+ elementStrategy);
				Object strategyValue = returnAttributeDataStrategyValue(
						collectionElementType, elementStrategy);
				collection.add(strategyValue);
			} else {
				collection.add(manufactureAttributeValue(pojoClass,
						collectionElementType, annotations, attributeName,
						genericTypeArgs));
			}

		}
	}

	/**
	 * It manufactures and returns a Map with at least one element in it
	 * 
	 * @param pojoClass
	 *            The POJO being initialised
	 * @param attributeType
	 *            The type of the POJO map attribute
	 * @param attributeName
	 *            The POJO attribute name
	 * @param annotations
	 *            The annotations specified for this attribute
	 * @param typeArgsMap
	 *            a map relating the generic class arguments ("<T, V>" for
	 *            example) with their actual types
	 * @param genericTypeArgs
	 *            The generic type arguments for the current generic class
	 *            instance
	 * @return Map with at least one element in it
	 * 
	 * @throws IllegalArgumentException
	 *             <ul>
	 *             <li>If the attribute name is null or empty</li>
	 *             <li>If the array of types of the Map has length different
	 *             from 2</li>
	 *             </ul>
	 * 
	 * @throws PodamMockeryException
	 *             If an error occurred while creating the Map object
	 */
	private Map<? super Object, ? super Object> resolveMapValueWhenMapIsPojoAttribute(
			Class<?> pojoClass, Class<?> attributeType, String attributeName,
			List<Annotation> annotations, Map<String, Type> typeArgsMap,
			Type... genericTypeArgs) {

		Map<? super Object, ? super Object> retValue = null;

		try {
			try {
				validateAttributeName(attributeName);

				// Checks whether the user initialised the collection in the
				// class definition

				Class<?> workClass = pojoClass;

				Object newInstance = null;

				Field field = null;

				newInstance = pojoClass.newInstance();

				while (workClass != null) {
					try {
						field = workClass.getDeclaredField(attributeName);
						break;
					} catch (NoSuchFieldException e) {
						workClass = workClass.getSuperclass();
					}

				}

				if (field == null) {
					throw new IllegalStateException(
							"It was not possible to retrieve field: "
									+ attributeName);
				}

				// Will throw exception if invalid

				// It allows to invoke Field.get on private fields
				field.setAccessible(true);

				@SuppressWarnings("unchecked")
				Map<? super Object, ? super Object> coll = (Map<? super Object, ? super Object>) field
						.get(newInstance);

				if (null != coll) {
					retValue = coll;
				} else {
					retValue = resolveMapType(attributeType);
				}
			} catch (Exception e) {
				// Name is empty or could not call an empty constructor
				// (probably this call is for a parameterized constructor)
				// Create a new Map
				retValue = resolveMapType(attributeType);
			}

			Class<?> keyClass = null;

			Class<?> elementClass = null;

			AtomicReference<Type[]> keyGenericTypeArgs = new AtomicReference<Type[]>(
					new Type[] {});
			AtomicReference<Type[]> elementGenericTypeArgs = new AtomicReference<Type[]>(
					new Type[] {});
			if (genericTypeArgs == null || genericTypeArgs.length == 0) {

				LOG.warn("Map attribute: "
						+ attributeName
						+ " is non-generic. We will assume a Map<Object, Object> for you.");

				keyClass = Object.class;

				elementClass = Object.class;

			} else {

				// Expected only key, value type
				if (genericTypeArgs.length != 2) {
					throw new IllegalStateException(
							"In a Map only key value generic type are expected.");
				}

				Type[] actualTypeArguments = genericTypeArgs;
				keyClass = resolveGenericParameter(actualTypeArguments[0],
						typeArgsMap, keyGenericTypeArgs);
				elementClass = resolveGenericParameter(actualTypeArguments[1],
						typeArgsMap, elementGenericTypeArgs);
			}

			fillMap(pojoClass, attributeName, annotations, retValue, keyClass,
					elementClass, keyGenericTypeArgs.get(),
					elementGenericTypeArgs.get());

		} catch (InstantiationException e) {
			throw new PodamMockeryException(
					"An exception occurred while creating a Map object", e);
		} catch (IllegalAccessException e) {
			throw new PodamMockeryException(
					"An exception occurred while creating a Map object", e);
		} catch (SecurityException e) {
			throw new PodamMockeryException(
					"An exception occurred while creating a Map object", e);
		} catch (ClassNotFoundException e) {
			throw new PodamMockeryException(
					"An exception occurred while creating a Map object", e);
		} catch (InvocationTargetException e) {
			throw new PodamMockeryException(
					"An exception occurred while creating a Map object", e);
		}

		return retValue;
	}

	/**
	 * It fills a Map with the required number of elements of the required type.
	 * 
	 * <p>
	 * This method has a so-called side-effect. It updates the Map given as
	 * argument.
	 * </p>
	 * 
	 * @param pojoClass
	 *            The POJO where the Map attribute has been declared
	 * @param attributeName
	 *            The attribute name
	 * @param annotations
	 *            The annotations for the attribute
	 * @param mapToBeFilled
	 *            The Map to be returned
	 * @param keyClass
	 *            The type of the Map key
	 * @param elementClass
	 *            The type of the Map element
	 * @param keyGenericTypeArgs
	 *            The generic type arguments for the current key generic class
	 *            instance
	 * @param elementGenericTypeArgs
	 *            The generic type arguments for the current element generic
	 *            class instance
	 * @throws InstantiationException
	 *             If an exception occurred during instantiation
	 * @throws IllegalAccessException
	 *             If security was violated while creating the object
	 * @throws InvocationTargetException
	 *             If an exception occurred while invoking the constructor or
	 *             factory method
	 * @throws ClassNotFoundException
	 *             If it was not possible to create a class from a string
	 * 
	 */
	private void fillMap(Class<?> pojoClass, String attributeName,
			List<Annotation> annotations,
			Map<? super Object, ? super Object> mapToBeFilled,
			Class<?> keyClass, Class<?> elementClass,
			Type[] keyGenericTypeArgs, Type[] elementGenericTypeArgs)
			throws InstantiationException, IllegalAccessException,
			InvocationTargetException, ClassNotFoundException {

		// If the user defined a strategy to fill the collection elements,
		// we use it
		PodamCollection collectionAnnotation = null;
		AttributeStrategy<?> keyStrategy = null;
		AttributeStrategy<?> elementStrategy = null;
		for (Annotation annotation : annotations) {
			if (PodamCollection.class.isAssignableFrom(annotation.getClass())) {
				collectionAnnotation = (PodamCollection) annotation;
				break;
			}

		}

		int nbrElements = strategy.getNumberOfCollectionElements();

		if (null != collectionAnnotation) {

			nbrElements = collectionAnnotation.nbrElements();
			keyStrategy = collectionAnnotation.mapKeyStrategy().newInstance();
			elementStrategy = collectionAnnotation.mapElementStrategy()
					.newInstance();

		}

		for (int i = 0; i < nbrElements; i++) {

			Object keyValue = null;

			Object elementValue = null;

			keyValue = getMapKeyOrElementValue(pojoClass, attributeName,
					annotations, keyClass, collectionAnnotation, keyStrategy,
					keyGenericTypeArgs);

			elementValue = getMapKeyOrElementValue(pojoClass, attributeName,
					annotations, elementClass, collectionAnnotation,
					elementStrategy, elementGenericTypeArgs);

			mapToBeFilled.put(keyValue, elementValue);

		}
	}

	/**
	 * It fills a Map key or value with the appropriate value, considering
	 * attribute-level customisation.
	 * 
	 * @param pojoClass
	 *            The class containing the annotated attribute
	 * @param attributeName
	 *            The attribute name
	 * @param annotations
	 *            The list of annotations for this attribute
	 * @param keyOrValueType
	 *            The Map key / element type
	 * @param collectionAnnotation
	 *            The {@link PodamCollection} annotation
	 * @param elementStrategy
	 *            The strategy to use to fill the Map key or value element
	 * @param genericTypeArgs
	 *            The generic type arguments for the current generic class
	 *            instance
	 * @return A Map key or value
	 * @throws InstantiationException
	 *             If an exception occurred during instantiation
	 * @throws IllegalAccessException
	 *             If security was violated while creating the object
	 * @throws InvocationTargetException
	 *             If an exception occurred while invoking the constructor or
	 *             factory method
	 * @throws IllegalArgumentException
	 *             <ul>
	 *             <li>If an illegal argument was passed</li>
	 *             <li>If an invalid value was set for a precise value in an
	 *             annotation and such value could not be converted to the
	 *             desired type</li>
	 *             </ul>
	 */
	private Object getMapKeyOrElementValue(Class<?> pojoClass,
			String attributeName, List<Annotation> annotations,
			Class<?> keyOrValueType, PodamCollection collectionAnnotation,
			AttributeStrategy<?> elementStrategy, Type... genericTypeArgs)
			throws InstantiationException, IllegalAccessException,
			InvocationTargetException, ClassNotFoundException {

		Object retValue = null;

		if (null != elementStrategy
				&& ObjectStrategy.class.isAssignableFrom(elementStrategy
						.getClass()) && Object.class.equals(keyOrValueType)) {
			LOG.debug("Element strategy is ObjectStrategy and Map key or value type is of type Object: using the ObjectStrategy strategy");
			retValue = elementStrategy.getValue();
		} else if (null != elementStrategy
				&& !ObjectStrategy.class.isAssignableFrom(elementStrategy
						.getClass())) {
			LOG.debug("Map key or value will be filled using the following strategy: "
					+ elementStrategy);
			retValue = returnAttributeDataStrategyValue(keyOrValueType,
					elementStrategy);

		} else {

			retValue = manufactureAttributeValue(pojoClass, keyOrValueType,
					annotations, attributeName, genericTypeArgs);
		}
		return retValue;
	}

	/**
	 * It returns an Array with the first element set
	 * 
	 * 
	 * @param attributeType
	 *            The array type
	 * @param annotations
	 *            The annotations to be considered
	 * @param pojoClass
	 * @param attributeName
	 * @param typeArgsMap
	 *            a map relating the generic class arguments ("<T, V>" for
	 *            example) with their actual types
	 * @return Array with the first element set
	 * @throws IllegalArgumentException
	 *             If an illegal argument was passed to the constructor
	 * @throws InstantiationException
	 *             If an exception occurred during instantiation
	 * @throws IllegalAccessException
	 *             If security was violated while creating the object
	 * @throws InvocationTargetException
	 *             If an exception occurred while invoking the constructor or
	 *             factory method
	 * @throws ClassNotFoundException
	 *             If it was not possible to create a class from a string
	 */
	private Object resolveArrayElementValue(Class<?> attributeType,
			List<Annotation> annotations, Class<?> pojoClass,
			String attributeName, Map<String, Type> typeArgsMap)
			throws IllegalArgumentException, InstantiationException,
			IllegalAccessException, InvocationTargetException,
			ClassNotFoundException {

		Class<?> componentType = attributeType.getComponentType();
		AtomicReference<Type[]> genericTypeArgs = new AtomicReference<Type[]>(
				new Type[] {});
		if (null != attributeName) {
			try {
				final Type genericType = pojoClass.getDeclaredField(
						attributeName).getGenericType();
				if (genericType instanceof GenericArrayType) {
					final Type type = ((GenericArrayType) genericType)
							.getGenericComponentType();
					if (type instanceof TypeVariable<?>) {
						final Type typeVarType = typeArgsMap
								.get(((TypeVariable<?>) type).getName());
						componentType = resolveGenericParameter(typeVarType,
								typeArgsMap, genericTypeArgs);
					}
				}
			} catch (NoSuchFieldException e) {
				LOG.info("Cannot get the declared field type for field "
						+ attributeName + " of class " + pojoClass.getName());
			}
		}

		int nbrElements = strategy.getNumberOfCollectionElements();

		Object arrayElement = null;

		// If the user defined a strategy to fill the collection elements,
		// we use it
		PodamCollection collectionAnnotation = null;
		AttributeStrategy<?> elementStrategy = null;
		for (Annotation annotation : annotations) {
			if (PodamCollection.class.isAssignableFrom(annotation.getClass())) {
				collectionAnnotation = (PodamCollection) annotation;
				break;
			}

		}

		if (null != collectionAnnotation) {

			nbrElements = collectionAnnotation.nbrElements();
			elementStrategy = collectionAnnotation.collectionElementStrategy()
					.newInstance();
		}

		Object array = Array.newInstance(componentType, nbrElements);

		for (int i = 0; i < nbrElements; i++) {

			// The default
			if (null != elementStrategy
					&& ObjectStrategy.class
							.isAssignableFrom(collectionAnnotation
									.collectionElementStrategy())
					&& Object.class.equals(componentType)) {
				LOG.debug("Element strategy is ObjectStrategy and array element is of type Object: using the ObjectStrategy strategy");
				arrayElement = elementStrategy.getValue();
			} else if (null != elementStrategy
					&& !ObjectStrategy.class
							.isAssignableFrom(collectionAnnotation
									.collectionElementStrategy())) {
				LOG.debug("Array elements will be filled using the following strategy: "
						+ elementStrategy);
				arrayElement = returnAttributeDataStrategyValue(componentType,
						elementStrategy);

			} else {

				arrayElement = manufactureAttributeValue(pojoClass,
						componentType, annotations, attributeName, typeArgsMap,
						genericTypeArgs.get());

			}

			Array.set(array, i, arrayElement);

		}

		return array;
	}

	/**
	 * Given a collection type it returns an instance
	 * <p>
	 * <ul>
	 * <li>The default type for a {@link List} is an {@link ArrayList}</li>
	 * <li>The default type for a {@link Queue} is a {@link LinkedList}</li>
	 * <li>The default type for a {@link Set} is a {@link HashSet}</li>
	 * </ul>
	 * 
	 * </p>
	 * 
	 * @param collectionType
	 *            The collection type *
	 * @return an instance of the collection type
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private Collection<? super Object> resolveCollectionType(
			Class<?> collectionType) {

		Collection<? super Object> retValue = null;

		// Default list and set are ArrayList and HashSet. If users
		// wants a particular collection flavour they have to initialise
		// the collection
		if (List.class.isAssignableFrom(collectionType)
				|| collectionType.equals(Collection.class)) {
			retValue = new ArrayList();
		} else if (Queue.class.isAssignableFrom(collectionType)) {
			retValue = new LinkedList();
		} else if (Set.class.isAssignableFrom(collectionType)) {
			retValue = new HashSet();
		} else {
			throw new IllegalArgumentException("Collection type: "
					+ collectionType + " not supported");
		}
		return retValue;
	}

	/**
	 * It manufactures and returns a default instance for each map type
	 * 
	 * <p>
	 * The default implementation for a {@link ConcurrentMap} is
	 * {@link ConcurrentHashMap}
	 * </p>
	 * 
	 * <p>
	 * The default implementation for a {@link SortedMap} is a {@link TreeMap}
	 * </p>
	 * 
	 * <p>
	 * The default Map is none of the above was recognised is a {@link HashMap}
	 * </p>
	 * 
	 * @param attributeType
	 *            The attribute type
	 * @return A default instance for each map type
	 * 
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private Map<? super Object, ? super Object> resolveMapType(
			Class<?> attributeType) {

		Map<? super Object, ? super Object> retValue = null;

		if (SortedMap.class.isAssignableFrom(attributeType)) {

			retValue = new TreeMap();

		} else if (ConcurrentMap.class.isAssignableFrom(attributeType)) {

			retValue = new ConcurrentHashMap();

		} else {

			retValue = new HashMap();

		}

		return retValue;

	}

	/**
	 * It validates that the attribute name is not null or empty
	 * 
	 * @param attributeName
	 *            The attribute to be validated
	 * @throws IllegalArgumentException
	 *             If the attribute name is null or empty
	 */
	private void validateAttributeName(String attributeName) {
		if (attributeName == null || "".equals(attributeName)) {
			throw new IllegalArgumentException(
					"The field name must not be null or empty!");
		}
	}

	/**
	 * Given a constructor it manufactures and returns the parameter values
	 * required to invoke it
	 * 
	 * @param constructor
	 *            The constructor for which parameter values are required
	 * @param pojoClass
	 *            The POJO class containing the constructor
	 * @param genericTypeArgs
	 *            The generic type arguments for the current generic class
	 *            instance
	 * 
	 * @return The parameter values required to invoke the constructor
	 * @throws IllegalArgumentException
	 *             If an illegal argument was passed to the constructor
	 * @throws InstantiationException
	 *             If an exception occurred during instantiation
	 * @throws IllegalAccessException
	 *             If security was violated while creating the object
	 * @throws InvocationTargetException
	 *             If an exception occurred while invoking the constructor or
	 *             factory method
	 * @throws ClassNotFoundException
	 *             If it was not possible to create a class from a string
	 */
	private Object[] getParameterValuesForConstructor(
			Constructor<?> constructor, Class<?> pojoClass,
			Type... genericTypeArgs) throws IllegalArgumentException,
			InstantiationException, IllegalAccessException,
			InvocationTargetException, ClassNotFoundException {

		final TypeVariable<?>[] typeParameters = pojoClass.getTypeParameters();
		if (typeParameters.length > genericTypeArgs.length) {
			LOG.info(pojoClass.getCanonicalName()
					+ " is missing generic type arguments, expected "
					+ typeParameters.length + " found "
					+ genericTypeArgs.length + ". Returning null.");
			return null;
		}

		final Map<String, Type> typeArgsMap = new HashMap<String, Type>();
		Type[] genericTypeArgsExtra = fillTypeArgMap(typeArgsMap,
				typeParameters, genericTypeArgs);

		Annotation[][] parameterAnnotations = constructor
				.getParameterAnnotations();

		Object[] parameterValues = new Object[constructor.getParameterTypes().length];

		// Found a constructor with @PodamConstructor annotation
		Class<?>[] parameterTypes = constructor.getParameterTypes();

		int idx = 0;
		for (Class<?> parameterType : parameterTypes) {

			List<Annotation> annotations = Arrays
					.asList(parameterAnnotations[idx]);

			if (parameterType.equals(pojoClass)) {
				// Recursive hierarchy in the constructor? If so the POJO should
				// also have a no-arg constructor
				// to avoid infinite looping

				Class<?> declaringClass = constructor.getDeclaringClass();
				Constructor<?> noArgConstructor = null;
				try {
					noArgConstructor = declaringClass
							.getConstructor(new Class<?>[] {});
				} catch (NoSuchMethodException e) {
					String errorMsg = "For class: "
							+ declaringClass
							+ " a constructor with its own type as argument does not have a no-arg constructor. Impossible to create an instance of this argument.";
					LOG.error(errorMsg);
					throw new IllegalArgumentException(errorMsg);
				}

				parameterValues[idx] = noArgConstructor
						.newInstance(new Object[] {});

			} else {

				String attributeName = null;

				if (Collection.class.isAssignableFrom(parameterType)) {

					Collection<? super Object> collection = resolveCollectionType(parameterType);

					Type type = constructor.getGenericParameterTypes()[idx];
					Class<?> collectionElementType;
					AtomicReference<Type[]> collectionGenericTypeArgs = new AtomicReference<Type[]>(
							new Type[] {});
					if (type instanceof ParameterizedType) {
						ParameterizedType pType = (ParameterizedType) type;
						Type actualTypeArgument = pType
								.getActualTypeArguments()[0];

						collectionElementType = resolveGenericParameter(
								actualTypeArgument, typeArgsMap,
								collectionGenericTypeArgs);
					} else {
						collectionElementType = Object.class;
					}

					Type[] genericTypeArgsAll;
					if (genericTypeArgsExtra != null) {
						Type[] genericTypeArgsMain = collectionGenericTypeArgs
								.get();
						genericTypeArgsAll = new Type[genericTypeArgsMain.length
								+ genericTypeArgsExtra.length];
						System.arraycopy(genericTypeArgsMain, 0,
								genericTypeArgsAll, 0,
								genericTypeArgsMain.length);
						System.arraycopy(genericTypeArgsExtra, 0,
								genericTypeArgsAll, genericTypeArgsMain.length,
								genericTypeArgsExtra.length);
					} else {
						genericTypeArgsAll = collectionGenericTypeArgs.get();
					}
					fillCollection(pojoClass, attributeName, annotations,
							collection, collectionElementType,
							genericTypeArgsAll);

					parameterValues[idx] = collection;

				} else if (Map.class.isAssignableFrom(parameterType)) {

					Map<? super Object, ? super Object> mapType = resolveMapType(parameterType);

					Type type = constructor.getGenericParameterTypes()[idx];

					Class<?> keyClass;
					Class<?> elementClass;
					AtomicReference<Type[]> keyGenericTypeArgs = new AtomicReference<Type[]>(
							new Type[] {});
					AtomicReference<Type[]> elementGenericTypeArgs = new AtomicReference<Type[]>(
							new Type[] {});
					if (type instanceof ParameterizedType) {
						ParameterizedType pType = (ParameterizedType) type;
						Type[] actualTypeArguments = pType
								.getActualTypeArguments();

						keyClass = resolveGenericParameter(
								actualTypeArguments[0], typeArgsMap,
								keyGenericTypeArgs);
						elementClass = resolveGenericParameter(
								actualTypeArguments[1], typeArgsMap,
								elementGenericTypeArgs);
					} else {
						keyClass = Object.class;
						elementClass = Object.class;
					}

					fillMap(pojoClass, attributeName, annotations, mapType,
							keyClass, elementClass, keyGenericTypeArgs.get(),
							elementGenericTypeArgs.get());

					parameterValues[idx] = mapType;

				} else {

					parameterValues[idx] = manufactureAttributeValue(pojoClass,
							parameterType, annotations, attributeName,
							genericTypeArgs);

				}

			}

			idx++;

		}

		return parameterValues;

	}

	/**
	 * It retrieves the value for the {@link PodamStrategyValue} annotation with
	 * which the attribute was annotated
	 * 
	 * @param attributeType
	 *            The attribute type, used for type checking
	 * @param attributeStrategy
	 *            The {@link AttributeStrategy} to use
	 * @return The value for the {@link PodamStrategyValue} annotation with
	 *         which the attribute was annotated
	 * @throws InstantiationException
	 *             If an exception occurred while creating an instance of the
	 *             strategy contained within the {@link PodamStrategyValue}
	 *             annotation
	 * @throws IllegalAccessException
	 *             If an exception occurred while creating an instance of the
	 *             strategy contained within the {@link PodamStrategyValue}
	 *             annotation
	 * 
	 * @throws IllegalArgumentException
	 *             If the type of the data strategy defined for the
	 *             {@link PodamStrategyValue} annotation is not assignable to
	 *             the annotated attribute. This de facto guarantees type
	 *             safety.
	 */
	private Object returnAttributeDataStrategyValue(Class<?> attributeType,
			AttributeStrategy<?> attributeStrategy)
			throws InstantiationException, IllegalAccessException {

		Object retValue = null;

		Method attributeStrategyMethod = null;

		try {
			attributeStrategyMethod = attributeStrategy.getClass().getMethod(
					PodamConstants.PODAM_ATTRIBUTE_STRATEGY_METHOD_NAME,
					new Class<?>[] {});

			if (!attributeType.isAssignableFrom(attributeStrategyMethod
					.getReturnType())) {
				String errMsg = "The type of the Podam Attribute Strategy is not "
						+ attributeType.getName()
						+ " but "
						+ attributeStrategyMethod.getReturnType().getName()
						+ ". An exception will be thrown.";
				LOG.error(errMsg);
				throw new IllegalArgumentException(errMsg);
			}

			retValue = attributeStrategy.getValue();

		} catch (SecurityException e) {
			throw new IllegalStateException(
					"A security issue occurred while retrieving the Podam Attribute Strategy details",
					e);
		} catch (NoSuchMethodException e) {
			throw new IllegalStateException(
					"It seems the Podam Attribute Annotation is of the wrong type",
					e);
		}

		return retValue;

	}

	/**
	 * @return the excludeAnnotations
	 */
	public List<Class<? extends Annotation>> getExcludeAnnotations() {
		return excludeAnnotations;
	}

	/**
	 * @param excludeAnnotations
	 *            the excludeAnnotations to set
	 */
	public void setExcludeAnnotations(
			List<Class<? extends Annotation>> excludeAnnotations) {
		this.excludeAnnotations = excludeAnnotations;
	}

	// ------------------->> equals() / hashcode() / toString()

	// ------------------->> Inner classes

}
