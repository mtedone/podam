/**
 * 
 */
package uk.co.jemos.podam.api;

import java.lang.annotation.Annotation;
import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
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

import uk.co.jemos.podam.annotations.PodamByteValue;
import uk.co.jemos.podam.annotations.PodamCharValue;
import uk.co.jemos.podam.annotations.PodamCollection;
import uk.co.jemos.podam.annotations.PodamConstructor;
import uk.co.jemos.podam.annotations.PodamDoubleValue;
import uk.co.jemos.podam.annotations.PodamFloatValue;
import uk.co.jemos.podam.annotations.PodamIntValue;
import uk.co.jemos.podam.annotations.PodamLongValue;
import uk.co.jemos.podam.annotations.PodamShortValue;
import uk.co.jemos.podam.annotations.PodamStringValue;
import uk.co.jemos.podam.dto.ClassInfo;
import uk.co.jemos.podam.exceptions.PodamMockeryException;
import uk.co.jemos.podam.utils.PodamConstants;
import uk.co.jemos.podam.utils.PodamUtils;

/**
 * @author mtedone
 * 
 */
public class PodamFactory {

	// ------------------->> Constants

	/** Application logger */
	private static final org.apache.log4j.Logger LOG = org.apache.log4j.Logger
			.getLogger(PodamFactory.class.getName());

	// ------------------->> Instance / Static variables

	// ------------------->> Constructors

	// ------------------->> Public methods

	/**
	 * Generic method which returns an instance of the given class filled with
	 * dummy values, using the default data provider strategy.
	 * 
	 * <p>
	 * This method uses {@link RandomDataProviderStrategy} as the default
	 * implementation.
	 * </p>
	 * 
	 * @param <T>
	 *            The type for which a filled instance is required
	 * @param pojoClass
	 *            The name of the class for which an instance filled with values
	 *            is required
	 * @return An instance of <T> filled with dummy values
	 * 
	 * @throws PodamMockeryException
	 *             if a problem occurred while creating a POJO instance or while
	 *             setting its state
	 */
	public static <T> T manufacturePojo(Class<T> pojoClass) {

		return manufacturePojo(pojoClass,
				RandomDataProviderStrategy.getInstance());
	}

	/**
	 * Generic method which returns an instance of the given class filled with
	 * dummy values
	 * 
	 * @param <T>
	 *            The type for which a filled instance is required
	 * @param pojoClass
	 *            The name of the class for which an instance filled with values
	 *            is required
	 * @param strategy
	 *            The {@link DataProviderStrategy} that PODAM will use to fill
	 *            data
	 * @return An instance of <T> filled with dummy values
	 * 
	 * @throws PodamMockeryException
	 *             if a problem occurred while creating a POJO instance or while
	 *             setting its state
	 */
	public static <T> T manufacturePojo(Class<T> pojoClass,
			DataProviderStrategy strategy) {
		return manufacturePojoInternal(strategy, pojoClass, 0);
	}

	// ------------------->> Getters / Setters

	// ------------------->> Private methods

	/**
	 * It attempts to create an instance of the given class
	 * <p>
	 * This method attempts to create an instance of the given argument for
	 * classes without setters. These may be either immutable classes (e.g. with
	 * final attributes and no setters) or Java classes (e.g. belonging to the
	 * java / javax namespace). In case the class does not provide a public,
	 * no-arg constructor (e.g. Calendar), this method attempts to find a
	 * static, no-args, factory method (e.g. getInstance()) and it invokes it
	 * </p>
	 * 
	 * @param clazz
	 *            The class for which a new instance is required
	 * 
	 * @param strategy
	 *            The {@link DataProviderStrategy} that PODAM will use to fill
	 *            data
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
	 *             static factory method
	 * @throws ClassNotFoundException
	 *             If it was not possible to create a class from a string
	 */
	private static Object createNewInstanceForClassWithoutSetters(
			DataProviderStrategy strategy, Class<?> clazz)
			throws IllegalArgumentException, InstantiationException,
			IllegalAccessException, InvocationTargetException,
			ClassNotFoundException {

		Object retValue = null;

		Constructor<?>[] constructors = clazz.getConstructors();

		if (constructors.length == 0) {

			// If no publicly accessible constructors are available,
			// the best we can do is to find a static constructor (e.g.
			// getInstance())

			Method[] declaredMethods = clazz.getDeclaredMethods();

			// A candidate factory method is a static method which returns the
			// Class type

			// The parameters to pass to the method invocation
			Object[] parameterValues = null;

			for (Method candidateConstructor : declaredMethods) {

				if (!Modifier.isStatic(candidateConstructor.getModifiers())
						|| !candidateConstructor.getReturnType().equals(clazz)) {
					continue;
				}

				if (clazz.getName().startsWith("java.")
						|| clazz.getName().startsWith("javax.")) {
					if (candidateConstructor.getParameterTypes().length != 0) {
						continue;
					}

					return candidateConstructor.invoke(clazz, new Object[] {});

				}

				parameterValues = new Object[candidateConstructor
						.getParameterTypes().length];

				Type[] parameterTypes = candidateConstructor
						.getGenericParameterTypes();

				if (parameterTypes.length == 0) {

					// There is a static factory method with no arguments
					retValue = candidateConstructor.invoke(clazz,
							new Object[] {});

				} else {

					// This is a static factory method with arguments

					Annotation[][] parameterAnnotations = candidateConstructor
							.getParameterAnnotations();

					int idx = 0;

					for (Type paramType : parameterTypes) {

						Class<?> parameterType = Class
								.forName(PodamUtils
										.extractClassNameFromParameterisedTypeInField(paramType));

						List<Annotation> annotations = Arrays
								.asList(parameterAnnotations[idx]);

						String attributeName = null;

						if (Collection.class.isAssignableFrom(parameterType)) {

							Collection<? super Object> listType = resolveCollectionType(parameterType);

							Class<?> elementType = retrieveClassFromCollectionTypeInConstructor(paramType
									.toString());

							int nbrElements = PodamConstants.ANNOTATION_COLLECTION_DEFAULT_NBR_ELEMENTS;

							for (Annotation annotation : annotations) {
								if (annotation.annotationType().equals(
										PodamCollection.class)) {

									PodamCollection ann = (PodamCollection) annotation;

									nbrElements = ann.nbrElements();

								}
							}

							for (int i = 0; i < nbrElements; i++) {
								Object attributeValue = manufactureAttributeValue(
										strategy, clazz, elementType,
										annotations, attributeName);

								listType.add(attributeValue);
							}

							parameterValues[idx] = listType;

						} else if (Map.class.isAssignableFrom(parameterType)) {

							Map<? super Object, ? super Object> mapType = resolveMapType(parameterType);

							Class<?>[] keyValueClasses = retrieveClassFromMapTypeInConstructor(paramType
									.toString());

							int nbrElements = PodamConstants.ANNOTATION_COLLECTION_DEFAULT_NBR_ELEMENTS;

							for (Annotation annotation : annotations) {
								if (annotation.annotationType().equals(
										PodamCollection.class)) {

									PodamCollection ann = (PodamCollection) annotation;

									nbrElements = ann.nbrElements();

								}
							}

							for (int i = 0; i < nbrElements; i++) {
								Object keyValue = manufactureAttributeValue(
										strategy, clazz, keyValueClasses[0],
										annotations, attributeName);

								Object elementValue = manufactureAttributeValue(
										strategy, clazz, keyValueClasses[1],
										annotations, attributeName);

								mapType.put(keyValue, elementValue);
							}

							parameterValues[idx] = mapType;

						} else {

							parameterValues[idx] = manufactureAttributeValue(
									strategy, clazz, parameterType,
									annotations, attributeName);

						}

						idx++;

					}

				}

				retValue = candidateConstructor.invoke(clazz, parameterValues);

				break;

			}

		} else {

			// TODO To provide support for constructors with argument types

			// There are public constructors. We need the no-arg
			// one for now.
			for (Constructor<?> constructor : constructors) {
				if (constructor.getParameterTypes().length != 0) {
					continue;
				}

				retValue = constructor.newInstance(new Object[] {});

			}

		}

		return retValue;

	}

	/**
	 * It resolves and returns the primitive value depending on the type
	 * 
	 * @param strategy
	 *            The {@link DataProviderStrategy} that PODAM will use to fill
	 *            data
	 * 
	 * @param primitiveClass
	 *            The primitive type class
	 * @param annotations
	 *            The annotations to consider for this attribute
	 * @return the primitive value depending on the type
	 */
	private static Object resolvePrimitiveValue(DataProviderStrategy strategy,
			Class<?> primitiveClass, List<Annotation> annotations) {

		Object retValue = null;

		if (primitiveClass.equals(int.class)) {

			if (annotations.isEmpty()) {

				retValue = strategy.getInteger();

			} else {

				retValue = getIntValueWithinRange(strategy, annotations);

				if (retValue == null) {
					retValue = strategy.getInteger();
				}
			}

		} else if (primitiveClass.equals(long.class)) {

			if (annotations.isEmpty()) {

				retValue = strategy.getLong();

			} else {

				retValue = getLongValueWithinRange(strategy, annotations);

				if (retValue == null) {
					retValue = strategy.getLong();
				}
			}

		} else if (primitiveClass.equals(float.class)) {

			if (annotations.isEmpty()) {

				retValue = strategy.getFloat();

			} else {

				retValue = getFloatValueWithinRange(strategy, annotations);

				if (retValue == null) {
					retValue = strategy.getFloat();
				}
			}

		} else if (primitiveClass.equals(double.class)) {

			if (annotations.isEmpty()) {

				retValue = strategy.getDouble();

			} else {

				retValue = getDoubleValueWithinRange(strategy, annotations);

				if (retValue == null) {
					retValue = strategy.getDouble();
				}
			}

		} else if (primitiveClass.equals(boolean.class)) {

			retValue = strategy.getBoolean();

		} else if (primitiveClass.equals(byte.class)) {

			if (annotations.isEmpty()) {

				retValue = strategy.getByte();

			} else {

				retValue = getByteValueWithinRange(strategy, annotations);

				if (retValue == null) {
					retValue = strategy.getByte();
				}
			}

		} else if (primitiveClass.equals(short.class)) {

			if (annotations.isEmpty()) {

				retValue = strategy.getShort();

			} else {

				retValue = getShortValueWithinRange(strategy, annotations);

				if (retValue == null) {
					retValue = strategy.getShort();
				}
			}

		} else if (primitiveClass.equals(char.class)) {

			if (annotations.isEmpty()) {

				retValue = strategy.getCharacter();

			} else {

				retValue = getCharacterValueWithinRange(strategy, annotations);

				if (retValue == null) {
					retValue = strategy.getCharacter();
				}
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
	 * @param strategy
	 *            The {@link DataProviderStrategy} to use to retrieve the data.
	 * @return A random byte if the attribute was annotated with
	 */
	private static Byte getByteValueWithinRange(DataProviderStrategy strategy,
			List<Annotation> annotations) {
		Byte retValue = null;

		for (Annotation annotation : annotations) {

			if (annotation.annotationType().equals(PodamByteValue.class)) {
				PodamByteValue intStrategy = (PodamByteValue) annotation;
				byte minValue = intStrategy.minValue();
				byte maxValue = intStrategy.maxValue();

				// Sanity check
				if (minValue > maxValue) {
					maxValue = minValue;
				}

				retValue = strategy.getByteInRange(minValue, maxValue);

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
	 * @param strategy
	 *            The {@link DataProviderStrategy} to use to retrieve the data.
	 * 
	 * @return A random short if the attribute was annotated with
	 *         {@link PodamShortValue} or {@code null} otherwise
	 */
	private static Short getShortValueWithinRange(
			DataProviderStrategy strategy, List<Annotation> annotations) {

		Short retValue = null;

		for (Annotation annotation : annotations) {

			if (annotation.annotationType().equals(PodamShortValue.class)) {
				PodamShortValue intStrategy = (PodamShortValue) annotation;
				short minValue = intStrategy.minValue();
				short maxValue = intStrategy.maxValue();

				// Sanity check
				if (minValue > maxValue) {
					maxValue = minValue;
				}

				retValue = strategy.getShortInRange(minValue, maxValue);

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
	 * @param strategy
	 *            The {@link DataProviderStrategy} to use to retrieve the data.
	 * 
	 * @return A random {@link Character} value
	 */
	private static Character getCharacterValueWithinRange(
			DataProviderStrategy strategy, List<Annotation> annotations) {

		Character retValue = null;

		for (Annotation annotation : annotations) {

			if (annotation.annotationType().equals(PodamCharValue.class)) {
				PodamCharValue annotationStrategy = (PodamCharValue) annotation;
				char minValue = annotationStrategy.minValue();
				char maxValue = annotationStrategy.maxValue();

				// Sanity check
				if (minValue > maxValue) {
					maxValue = minValue;
				}

				retValue = strategy.getCharacterInRange(minValue, maxValue);

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
	 * @param strategy
	 *            The {@link DataProviderStrategy} to use to retrieve the data.
	 * 
	 * @return Either a customised int value if a {@link PodamIntValue}
	 *         annotation was provided or a random integer if this was not the
	 *         case
	 */
	private static Integer getIntValueWithinRange(
			DataProviderStrategy strategy, List<Annotation> annotations) {

		Integer retValue = null;

		for (Annotation annotation : annotations) {

			if (annotation.annotationType().equals(PodamIntValue.class)) {
				PodamIntValue intStrategy = (PodamIntValue) annotation;
				int minValue = intStrategy.minValue();
				int maxValue = intStrategy.maxValue();

				// Sanity check
				if (minValue > maxValue) {
					maxValue = minValue;
				}

				retValue = strategy.getIntegerInRange(minValue, maxValue);

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
	 * @param strategy
	 *            The {@link DataProviderStrategy} to use to retrieve the data.
	 * 
	 * @return Either a customised float value if a {@link PodamFloatValue}
	 *         annotation was provided or a random float if this was not the
	 *         case
	 */
	private static Float getFloatValueWithinRange(
			DataProviderStrategy strategy, List<Annotation> annotations) {

		Float retValue = null;

		for (Annotation annotation : annotations) {

			if (annotation.annotationType().equals(PodamFloatValue.class)) {
				PodamFloatValue intStrategy = (PodamFloatValue) annotation;
				float minValue = intStrategy.minValue();
				float maxValue = intStrategy.maxValue();

				// Sanity check
				if (minValue > maxValue) {
					maxValue = minValue;
				}

				retValue = strategy.getFloatInRange(minValue, maxValue);

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
	 * @param strategy
	 *            The {@link DataProviderStrategy} to use to retrieve the data.
	 * 
	 * @return a random {@link Double} value
	 */
	private static Double getDoubleValueWithinRange(
			DataProviderStrategy strategy, List<Annotation> annotations) {

		Double retValue = null;

		for (Annotation annotation : annotations) {

			if (annotation.annotationType().equals(PodamDoubleValue.class)) {
				PodamDoubleValue intStrategy = (PodamDoubleValue) annotation;
				double minValue = intStrategy.minValue();
				double maxValue = intStrategy.maxValue();

				// Sanity check
				if (minValue > maxValue) {
					maxValue = minValue;
				}

				retValue = strategy.getDoubleInRange(minValue, maxValue);

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
	 * 
	 * @param strategy
	 *            The {@link DataProviderStrategy} to use to retrieve the data.
	 * 
	 * @return Either a customised long value if a {@link PodamLongValue}
	 *         annotation was provided or a random long if this was not the case
	 */
	private static Long getLongValueWithinRange(DataProviderStrategy strategy,
			List<Annotation> annotations) {

		Long retValue = null;

		for (Annotation annotation : annotations) {

			if (annotation.annotationType().equals(PodamLongValue.class)) {
				PodamLongValue intStrategy = (PodamLongValue) annotation;
				long minValue = intStrategy.minValue();
				long maxValue = intStrategy.maxValue();

				// Sanity check
				if (minValue > maxValue) {
					maxValue = minValue;
				}

				retValue = strategy.getLongInRange(minValue, maxValue);

				break;

			}

		}
		return retValue;
	}

	/**
	 * It attempts to resolve the given class as a wrapper class and if this is
	 * the case it assigns a random value
	 * 
	 * @param strategy
	 *            The {@link DataProviderStrategy} that PODAM will use to fill
	 *            data
	 * 
	 * @param candidateWrapperClass
	 *            The class which might be a wrapper class
	 * @return {@code null} if this is not a wrapper class, otherwise an Object
	 *         with the value for the wrapper class
	 */
	private static Object resolveWrapperValue(DataProviderStrategy strategy,
			Class<?> candidateWrapperClass, List<Annotation> annotations) {

		Object retValue = null;

		if (candidateWrapperClass.equals(Integer.class)) {

			if (annotations.isEmpty()) {

				retValue = strategy.getInteger();

			} else {

				retValue = getIntValueWithinRange(strategy, annotations);

				if (retValue == null) {
					retValue = strategy.getInteger();
				}
			}

		} else if (candidateWrapperClass.equals(Long.class)) {

			if (annotations.isEmpty()) {

				retValue = strategy.getLong();

			} else {

				retValue = getLongValueWithinRange(strategy, annotations);

				if (retValue == null) {
					retValue = strategy.getLong();
				}
			}

		} else if (candidateWrapperClass.equals(Float.class)) {

			if (annotations.isEmpty()) {

				retValue = strategy.getFloat();

			} else {

				retValue = getFloatValueWithinRange(strategy, annotations);

				if (retValue == null) {
					retValue = strategy.getFloat();
				}
			}

		} else if (candidateWrapperClass.equals(Double.class)) {

			if (annotations.isEmpty()) {

				retValue = strategy.getDouble();

			} else {

				retValue = getDoubleValueWithinRange(strategy, annotations);

				if (retValue == null) {
					retValue = strategy.getDouble();
				}
			}

		} else if (candidateWrapperClass.equals(Boolean.class)) {

			retValue = Boolean.TRUE;

		} else if (candidateWrapperClass.equals(Byte.class)) {

			if (annotations.isEmpty()) {

				retValue = strategy.getByte();

			} else {

				retValue = getByteValueWithinRange(strategy, annotations);

				if (retValue == null) {
					retValue = strategy.getByte();
				}
			}

		} else if (candidateWrapperClass.equals(Short.class)) {

			if (annotations.isEmpty()) {

				retValue = strategy.getShort();

			} else {

				retValue = getShortValueWithinRange(strategy, annotations);

				if (retValue == null) {
					retValue = strategy.getShort();
				}
			}

		} else if (candidateWrapperClass.equals(Character.class)) {

			if (annotations.isEmpty()) {

				retValue = strategy.getCharacter();

			} else {

				retValue = getCharacterValueWithinRange(strategy, annotations);

				if (retValue == null) {
					retValue = strategy.getCharacter();
				}
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
	 * @param strategy
	 *            The {@link DataProviderStrategy} that PODAM will use to fill
	 *            data
	 * 
	 * @param pojoClass
	 *            The class of which an instance is required
	 * @param depth
	 *            How many instances of the same class have been created so far
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
	 *             static factory method
	 * @throws ClassNotFoundException
	 *             If it was not possible to create a class from a string
	 */
	@SuppressWarnings("unchecked")
	private static <T> T resolvePojoWithoutDefaultConstructor(
			DataProviderStrategy strategy, Class<T> pojoClass, int depth)
			throws IllegalArgumentException, InstantiationException,
			IllegalAccessException, InvocationTargetException,
			ClassNotFoundException {

		T retValue = null;

		Constructor<?>[] constructors = pojoClass.getConstructors();
		if (constructors.length == 0) {
			retValue = (T) createNewInstanceForClassWithoutSetters(strategy,
					pojoClass);
		} else {
			for (Constructor<?> constructor : constructors) {

				if (constructor.getAnnotation(PodamConstructor.class) == null) {
					continue;
				}

				Annotation[][] parameterAnnotations = constructor
						.getParameterAnnotations();

				Object[] parameterValues = new Object[constructor
						.getParameterTypes().length];

				// Found a constructor with @PodamConstructor annotation
				Class<?>[] parameterTypes = constructor.getParameterTypes();

				int idx = 0;
				for (Class<?> parameterType : parameterTypes) {

					List<Annotation> annotations = Arrays
							.asList(parameterAnnotations[idx]);

					// Recursive hierarchy in the constructor?
					if (parameterType.equals(pojoClass)) {

						if (depth < PodamConstants.MAX_DEPTH) {
							depth++;
							parameterValues[idx++] = manufacturePojoInternal(
									strategy, parameterType, depth);
							continue;
						} else {
							parameterValues[idx++] = createNewInstanceForClassWithoutSetters(
									strategy, parameterType);
							continue;
						}

					} else {

						String attributeName = null;

						if (Collection.class.isAssignableFrom(parameterType)) {

							Collection<? super Object> listType = resolveCollectionType(parameterType);

							Type type = constructor.getGenericParameterTypes()[idx];

							String typeStr = type.toString();

							Class<?> elementType = retrieveClassFromCollectionTypeInConstructor(typeStr);

							int nbrElements = PodamConstants.ANNOTATION_COLLECTION_DEFAULT_NBR_ELEMENTS;

							for (Annotation annotation : annotations) {
								if (annotation.annotationType().equals(
										PodamCollection.class)) {

									PodamCollection ann = (PodamCollection) annotation;

									nbrElements = ann.nbrElements();

								}
							}

							for (int i = 0; i < nbrElements; i++) {
								Object attributeValue = manufactureAttributeValue(
										strategy, pojoClass, elementType,
										annotations, attributeName);

								listType.add(attributeValue);
							}

							parameterValues[idx] = listType;

						} else if (Map.class.isAssignableFrom(parameterType)) {

							Map<? super Object, ? super Object> mapType = resolveMapType(parameterType);

							Type type = constructor.getGenericParameterTypes()[idx];

							Class<?>[] keyValueClasses = retrieveClassFromMapTypeInConstructor(type
									.toString());

							int nbrElements = PodamConstants.ANNOTATION_COLLECTION_DEFAULT_NBR_ELEMENTS;

							for (Annotation annotation : annotations) {
								if (annotation.annotationType().equals(
										PodamCollection.class)) {

									PodamCollection ann = (PodamCollection) annotation;

									nbrElements = ann.nbrElements();

								}
							}

							for (int i = 0; i < nbrElements; i++) {
								Object keyValue = manufactureAttributeValue(
										strategy, pojoClass,
										keyValueClasses[0], annotations,
										attributeName);

								Object elementValue = manufactureAttributeValue(
										strategy, pojoClass,
										keyValueClasses[1], annotations,
										attributeName);

								mapType.put(keyValue, elementValue);
							}

							parameterValues[idx] = mapType;

						} else {

							parameterValues[idx] = manufactureAttributeValue(
									strategy, pojoClass, parameterType,
									annotations, attributeName);

						}

					}

					idx++;

				}

				// Being a generic method we cannot be sure on the identify of
				// T,
				// therefore the mismatch between the newInstance() return value
				// (Object) and T is acceptable, thus the SuppressWarning
				// annotation

				retValue = (T) constructor.newInstance(parameterValues);
			}
		}

		return retValue;
	}

	/**
	 * Given a String representing a type for a collection or map in a
	 * constructor, it returns the class of that collection or map
	 * 
	 * @param typeStr
	 *            The String identifying the collection type
	 * @return The class of that collection or map
	 * @throws IllegalStateException
	 *             If the class identified by {@code typeStr} was not found
	 */
	private static Class<?> retrieveClassFromCollectionTypeInConstructor(
			String typeStr) {

		Class<?> retValue = null;

		if (!typeStr.contains("<") && !(typeStr.contains(">"))) {

			LOG.warn("The Collection type in the constructor: "
					+ typeStr
					+ " is non generic. We will assume Collection<Object> for you.");

			// PODAM supports non-generic collections
			retValue = Object.class;

		} else {

			int startIdx = typeStr.indexOf("<") + 1;
			int endIdx = typeStr.indexOf(">");

			String classType = typeStr.substring(startIdx, endIdx);

			try {
				retValue = Class.forName(classType);
			} catch (ClassNotFoundException e) {
				throw new IllegalStateException("Class " + classType
						+ " could not be loaded!");
			}
		}

		return retValue;
	}

	/**
	 * Given a string representing a Map in a constructor, it returns an array
	 * identifying the classes for the key and value in the map
	 * 
	 * @param typeStr
	 *            The type of the Map. It can be non-generic
	 * @return An array identifying the classes for the key and value in the map
	 * @throws IllegalStateException
	 *             If either the key or value classes could not be loaded
	 *             because not found
	 */
	private static Class<?>[] retrieveClassFromMapTypeInConstructor(
			String typeStr) {

		Class<?>[] retValue = new Class<?>[2];

		if (!typeStr.contains("<") && !typeStr.contains(">")) {

			LOG.warn("The Map type in the constructor: "
					+ typeStr
					+ " is non generic. We will assume Map<Object, Object> for you.");

			// PODAM supports non-generic Maps
			retValue[0] = Object.class;
			retValue[1] = Object.class;

		} else {

			int startIdx = typeStr.indexOf("<") + 1;
			int endIdx = typeStr.indexOf(">");

			String classType = typeStr.substring(startIdx, endIdx);

			String[] keyValueTypeStr = classType.split(",");

			try {
				retValue[0] = Class.forName(keyValueTypeStr[0].trim());
				retValue[1] = Class.forName(keyValueTypeStr[1].trim());
			} catch (ClassNotFoundException e) {
				throw new IllegalStateException("Class " + classType
						+ " could not be loaded!");
			}

		}

		return retValue;
	}

	/**
	 * Generic method which returns an instance of the given class filled with
	 * dummy values
	 * 
	 * @param <T>
	 *            The type for which a filled instance is required
	 * 
	 * @param strategy
	 *            The {@link DataProviderStrategy} that PODAM will use to fill
	 *            data
	 * 
	 * @param pojoClass
	 *            The name of the class for which an instance filled with values
	 *            is required
	 * @param depth
	 *            How many times {@code dtoClass} has been found
	 * @return An instance of <T> filled with dummy values
	 * 
	 * @throws PodamMockeryException
	 *             if a problem occurred while creating a POJO instance or while
	 *             setting its state
	 */
	private static <T> T manufacturePojoInternal(DataProviderStrategy strategy,
			Class<T> pojoClass, int depth) {
		try {

			if (pojoClass.isInterface() || pojoClass.isPrimitive()) {
				throw new PodamMockeryException(
						"It's not possible to instantiate an interface or a primitive type.");
			}

			ClassInfo classInfo = PodamUtils.getClassInfo(pojoClass);

			if (classInfo.getClassSetters().isEmpty()) {

				// A rudimentary attempt to manage immutable classes (e.g. with
				// constructor only and final fields - no setters)
				return resolvePojoWithoutDefaultConstructor(strategy,
						pojoClass, depth);

			}

			// Key PODAM requirement: POJO with setters must have a no-arguments
			// constructor
			T retValue = pojoClass.newInstance();

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
				if (attributeType.equals(pojoClass)) {
					if (depth < PodamConstants.MAX_DEPTH) {
						depth++;
						setterArg = manufacturePojoInternal(strategy,
								attributeType, depth);
						setter.invoke(retValue, setterArg);
						continue;

					} else {

						setterArg = createNewInstanceForClassWithoutSetters(
								strategy, pojoClass);

						setter.invoke(retValue, setterArg);
						depth = 0;
						continue;

					}

				}

				setterArg = manufactureAttributeValue(strategy, pojoClass,
						attributeType, pojoAttributeAnnotations, attributeName);

				if (setterArg != null) {
					// If the setter is not public we set it to accessible or
					// otherwise the invocation will fail.
					if (!Modifier.isPublic(setter.getModifiers())) {
						LOG.warn("The setter: "
								+ setter.getName()
								+ " is not public. Setting it to accessible(true). "
								+ "However if you have got security in place to avoid these kind of things, you will get an error");
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
			throw new PodamMockeryException("Invocation Target Exception", e);
		}
	}

	/**
	 * It manufactures and returns the value for a POJO attribute.
	 * 
	 * @param strategy
	 *            The {@link DataProviderStrategy} that PODAM will use to fill
	 *            data
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
	 * @return The value for an attribute
	 * 
	 * @throws InstantiationException
	 *             If an exception occurred during instantiation
	 * @throws IllegalAccessException
	 *             If security was violated while creating the object
	 * @throws InvocationTargetException
	 *             If an exception occurred while invoking the constructor or
	 *             static factory method
	 * @throws ClassNotFoundException
	 *             If it was not possible to create a class from a string
	 * @throws IllegalArgumentException
	 *             If an illegal argument was passed
	 */
	private static Object manufactureAttributeValue(
			DataProviderStrategy strategy, Class<?> pojoClass,
			Class<?> attributeType, List<Annotation> annotations,
			String attributeName) throws InstantiationException,
			IllegalAccessException, InvocationTargetException,
			IllegalArgumentException, ClassNotFoundException {
		Object attributeValue = null;

		if (attributeType.isPrimitive()) {

			attributeValue = resolvePrimitiveValue(strategy, attributeType,
					annotations);

		} else if (isWrapper(attributeType)) {

			attributeValue = resolveWrapperValue(strategy, attributeType,
					annotations);

		} else if (attributeType.equals(String.class)) {

			attributeValue = resolveStringValue(strategy, annotations);

			// Is this an array?
		} else if (attributeType.getName().startsWith("[")) {

			attributeValue = resolveArrayElementValue(strategy, attributeType,
					annotations, pojoClass, attributeName);

			// Otherwise it's a different type of Object (including
			// the Object class)
		} else if (Collection.class.isAssignableFrom(attributeType)) {

			attributeValue = resolveCollectionValueWhenCollectionIsPojoAttribute(
					strategy, pojoClass, attributeType, attributeName,
					annotations);

		} else if (Map.class.isAssignableFrom(attributeType)) {

			attributeValue = resolveMapValueWhenMapIsPojoAttribute(strategy,
					pojoClass, attributeType, attributeName, annotations);

		} else if (attributeType.getName().startsWith("java.")
				|| attributeType.getName().startsWith("javax.")) {

			// For classes in the Java namespace we attempt the no-args or the
			// static factory constructor strategy

			attributeValue = createNewInstanceForClassWithoutSetters(strategy,
					attributeType);

		} else {

			// For any class not in the Java namespace, we try the PODAM
			// strategy
			attributeValue = manufacturePojo(attributeType);

		}

		return attributeValue;
	}

	/**
	 * It creates and returns a String value, eventually customised by
	 * annotations
	 * 
	 * @param strategy
	 *            The {@link DataProviderStrategy} that PODAM will use to fill
	 *            data
	 * 
	 * @param annotations
	 *            The list of annotations used to customise the String value, if
	 *            any
	 * @return a String value, eventually customised by annotations
	 */
	private static String resolveStringValue(DataProviderStrategy strategy,
			List<Annotation> annotations) {

		String retValue = null;

		if ((annotations == null) || annotations.isEmpty()) {

			retValue = strategy.getStringValue();

		} else {

			for (Annotation annotation : annotations) {

				if (!annotation.annotationType().equals(PodamStringValue.class)) {
					continue;
				}

				// A specific value takes precedence over the length
				PodamStringValue podamAnnotation = (PodamStringValue) annotation;

				if ((podamAnnotation.strValue() != null)
						&& (podamAnnotation.strValue().length() > 0)) {

					retValue = podamAnnotation.strValue();

				} else {

					retValue = strategy.getStringOfLength(podamAnnotation
							.length());

				}

			}

			if (retValue == null) {
				retValue = strategy.getStringValue();
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
	private static boolean isWrapper(Class<?> candidateWrapperClass) {

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
	private static List<Annotation> retrieveFieldAnnotations(Class<?> clazz,
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

			if ((annotations != null) && (annotations.length != 0)) {
				retValue = Arrays.asList(annotations);
			}
		}

		return retValue;

	}

	/**
	 * It returns a collection of some sort with some data in it.
	 * 
	 * @param strategy
	 *            The {@link DataProviderStrategy} that PODAM will use to fill
	 *            data
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
	 * @return a collection of some sort with some data in it
	 * @throws PodamMockeryException
	 *             An exception occurred while resolving the collection
	 * @throws IllegalArgumentException
	 *             If the field name is null or empty
	 */
	@SuppressWarnings({ "unchecked" })
	private static Collection<? super Object> resolveCollectionValueWhenCollectionIsPojoAttribute(
			DataProviderStrategy strategy, Class<?> pojoClass,
			Class<?> collectionType, String attributeName,
			List<Annotation> annotations) {

		validateAttributeName(attributeName);

		// This needs to be generic because collections can be of any type
		Collection<? super Object> retValue = null;

		try {

			// Checks whether the user initialised the collection in the class
			// definition
			Object newInstance = pojoClass.newInstance();

			// Will throw exception if invalid
			Field field = pojoClass.getDeclaredField(attributeName);

			// It allows to invoke Field.get on private fields
			field.setAccessible(true);

			Collection<? super Object> coll = (Collection<? super Object>) field
					.get(newInstance);

			if (null != coll) {
				retValue = coll;
			} else {
				retValue = resolveCollectionType(collectionType);
			}

			Class<?> typeClass = null;

			Type genericType = field.getGenericType();
			if (!(genericType instanceof ParameterizedType)) {

				// Support for non-generified collections
				typeClass = Object.class;

			} else {
				ParameterizedType pType = (ParameterizedType) genericType;
				Type actualTypeArguments = pType.getActualTypeArguments()[0];

				// The Type for a collection has got the format:
				// "class java.lang.String"
				typeClass = Class
						.forName(PodamUtils
								.extractClassNameFromParameterisedTypeInField(actualTypeArguments));
			}

			int nbrElements = resolveCollectionNbrElementsFromAnnotations(annotations);

			for (int i = 0; i < nbrElements; i++) {
				retValue.add(manufactureAttributeValue(strategy, pojoClass,
						typeClass, annotations, attributeName));
			}

		} catch (SecurityException e) {
			throw new PodamMockeryException(
					"An exception occurred while resolving the collection", e);
		} catch (NoSuchFieldException e) {
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
	 * It manufactures and returns a Map with at least one element in it
	 * 
	 * @param strategy
	 *            The {@link DataProviderStrategy} that PODAM will use to fill
	 *            data
	 * 
	 * @param pojoClass
	 *            The POJO being initialised
	 * @param attributeType
	 *            The type of the POJO map attribute
	 * @param attributeName
	 *            The POJO attribute name
	 * @param annotations
	 *            The annotations specified for this attribute
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
	private static Map<? super Object, ? super Object> resolveMapValueWhenMapIsPojoAttribute(
			DataProviderStrategy strategy, Class<?> pojoClass,
			Class<?> attributeType, String attributeName,
			List<Annotation> annotations) {

		validateAttributeName(attributeName);

		Map<? super Object, ? super Object> retValue = null;

		// Checks whether the user initialised the collection in the class
		// definition
		Object newInstance = null;
		try {
			newInstance = pojoClass.newInstance();
			// Will throw exception if invalid
			Field field = pojoClass.getDeclaredField(attributeName);

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

			Type genericType = field.getGenericType();

			Class<?> keyClass = null;

			Class<?> elementClass = null;

			if ((null != genericType)
					&& (genericType instanceof ParameterizedType)) {

				ParameterizedType pType = (ParameterizedType) genericType;
				Type[] actualTypeArguments = pType.getActualTypeArguments();

				// Expected only key, value type
				if (actualTypeArguments.length != 2) {
					throw new IllegalStateException(
							"In a Map only key value generic type are expected.");
				}

				// The Type for a collection has got the format:
				// "class java.lang.String"
				keyClass = Class
						.forName(PodamUtils
								.extractClassNameFromParameterisedTypeInField(actualTypeArguments[0]));

				elementClass = Class
						.forName(PodamUtils
								.extractClassNameFromParameterisedTypeInField(actualTypeArguments[1]));
			} else {

				LOG.warn("Map attribute: "
						+ attributeName
						+ " is non-generic. We will assume a Map<Object, Object> for you.");

				keyClass = Object.class;

				elementClass = Object.class;

			}

			int nbrElements = resolveCollectionNbrElementsFromAnnotations(annotations);

			for (int i = 0; i < nbrElements; i++) {

				Object keyValue = manufactureAttributeValue(strategy,
						pojoClass, keyClass, annotations, attributeName);

				Object elementValue = manufactureAttributeValue(strategy,
						pojoClass, elementClass, annotations, attributeName);

				retValue.put(keyValue, elementValue);

			}

		} catch (InstantiationException e) {
			throw new PodamMockeryException(
					"An exception occurred while creating a Map object", e);
		} catch (IllegalAccessException e) {
			throw new PodamMockeryException(
					"An exception occurred while creating a Map object", e);
		} catch (SecurityException e) {
			throw new PodamMockeryException(
					"An exception occurred while creating a Map object", e);
		} catch (NoSuchFieldException e) {
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
	 * It returns an Array with the first element set
	 * 
	 * @param strategy
	 *            The {@link DataProviderStrategy} that PODAM will use to fill
	 *            data
	 * 
	 * @param attributeType
	 *            The array type
	 * @param annotations
	 *            The annotations to be considered
	 * @param pojoClass
	 * @param attributeName
	 * @return Array with the first element set
	 * @throws IllegalArgumentException
	 *             If an illegal argument was passed to the constructor
	 * @throws InstantiationException
	 *             If an exception occurred during instantiation
	 * @throws IllegalAccessException
	 *             If security was violated while creating the object
	 * @throws InvocationTargetException
	 *             If an exception occurred while invoking the constructor or
	 *             static factory method
	 * @throws ClassNotFoundException
	 *             If it was not possible to create a class from a string
	 */
	private static Object resolveArrayElementValue(
			DataProviderStrategy strategy, Class<?> attributeType,
			List<Annotation> annotations, Class<?> pojoClass,
			String attributeName) throws IllegalArgumentException,
			InstantiationException, IllegalAccessException,
			InvocationTargetException, ClassNotFoundException {

		Class<?> componentType = attributeType.getComponentType();

		// Checks if the number of elements has been customised
		int nbrElements = resolveCollectionNbrElementsFromAnnotations(annotations);

		Object array = Array.newInstance(componentType, nbrElements);

		Object arrayElement = null;

		for (int i = 0; i < nbrElements; i++) {

			arrayElement = manufactureAttributeValue(strategy, pojoClass,
					componentType, annotations, attributeName);

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
	private static Collection<? super Object> resolveCollectionType(
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
	private static Map<? super Object, ? super Object> resolveMapType(
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
	private static void validateAttributeName(String attributeName) {
		if ((attributeName == null) || "".equals(attributeName)) {
			throw new IllegalArgumentException(
					"The field name must not be null or empty!");
		}
	}

	/**
	 * Given a list of annotations, it scans whether there is one to customise
	 * the number of elements in a collection
	 * 
	 * <p>
	 * The number of elements defaults to
	 * {@link PodamConstants#ANNOTATION_COLLECTION_DEFAULT_NBR_ELEMENTS}
	 * </p>
	 * 
	 * @param annotations
	 *            The list of annotations to scan for {@link PodamCollection}
	 * @return The number of elements to add to the collection
	 */
	private static int resolveCollectionNbrElementsFromAnnotations(
			List<Annotation> annotations) {

		int nbrElements = PodamConstants.ANNOTATION_COLLECTION_DEFAULT_NBR_ELEMENTS;

		// Checks whether the user customised the number of elements in the
		// collection
		for (Annotation annotation : annotations) {
			if (!annotation.annotationType().equals(PodamCollection.class)) {
				continue;
			}

			PodamCollection podamAnnotation = (PodamCollection) annotation;
			nbrElements = podamAnnotation.nbrElements();
		}
		return nbrElements;
	}

	// ------------------->> equals() / hashcode() / toString()

	// ------------------->> Inner classes

}
