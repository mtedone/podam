/**
 *
 */
package uk.co.jemos.podam.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import uk.co.jemos.podam.common.PodamExclude;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.*;

/**
 * PODAM Utilities class.
 *
 * @author mtedone
 *
 * @since 1.0.0
 *
 */
public final class PodamUtils {

	// ---------------------->> Constants

	private static final int SETTER_IDENTIFIER_LENGTH = 3;

	private static final String GETTER_PREFIX = "get";
	private static final String GETTER_PREFIX2 = "is";

	/** An array of valid String characters */
	public static final char[] NICE_ASCII_CHARACTERS = new char[] { 'a', 'b',
			'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o',
			'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', 'A', 'B',
			'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O',
			'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', '0', '1',
			'2', '3', '4', '5', '6', '7', '8', '9', '_' };

	/** The application logger. */
	private static final Logger LOG = LoggerFactory.getLogger(PodamUtils.class);

	/** Non instantiable constructor */
	private PodamUtils() {
		throw new AssertionError();
	}

	/**
	 * It returns a {@link ClassInfo} object for the given class
	 *
	 * @param clazz
	 *            The class to retrieve info from
	 * @param attributeApprover
	 *            a {@link ClassAttributeApprover} implementation,
	 *             which attributes to skip and which to process.
	 *             If one hasn't been provided, Podam will use the
	 *             default one in the {@link DefaultClassInfoStrategy} class.
	 * @return a {@link ClassInfo} object for the given class
	 */
	public static ClassInfo getClassInfo(Class<?> clazz,
			ClassAttributeApprover attributeApprover) {

		return getClassInfo(clazz,
				new HashSet<Class<? extends Annotation>>(),
				Collections.<String>emptySet(),
				attributeApprover);
	}

	/**
	 * It returns a {@link ClassInfo} object for the given class
	 *
	 * @param clazz
	 *            The class to retrieve info from
	 * @param excludeFieldAnnotations
	 *            the fields marked with any of these annotations will not be
	 *            included in the class info
	 * @param excludedFields
	 *            the fields matching the given names will not be included in the class info
	 * @param attributeApprover
	 *            a {@link ClassAttributeApprover} implementation,
	 *             which defines which attributes to skip and which to process
	 * @return a {@link ClassInfo} object for the given class
	 */
	public static ClassInfo getClassInfo(Class<?> clazz,
			Set<Class<? extends Annotation>> excludeFieldAnnotations,
			Set<String> excludedFields,
			ClassAttributeApprover attributeApprover) {


		return getClassInfo(clazz, excludeFieldAnnotations, excludedFields, attributeApprover, Collections.<Method>emptySet());

	}


	public static ClassInfo getClassInfo(Class<?> clazz,
										 Set<Class<? extends Annotation>> excludeFieldAnnotations,
										 Set<String> excludedFields,
										 ClassAttributeApprover attributeApprover,
										 Collection<Method> extraMethods) {

		if (null == attributeApprover) {
			attributeApprover = DefaultClassInfoStrategy.getInstance().getClassAttributeApprover(clazz);
		}

		Set<Field> classFields = new HashSet<Field>();
		Set<Method> classGetters = new HashSet<Method>();
		Set<Method> classSetters = new HashSet<Method>();
		fillPojoSets(clazz, classFields, classGetters, classSetters, excludeFieldAnnotations, excludedFields);

		Map<String, ClassAttribute> map = new TreeMap<String, ClassAttribute>();
		for (Field classField : classFields) {
			ClassAttribute attribute = new ClassAttribute(classField,
					Collections.<Method>emptySet(), Collections.<Method>emptySet());
			map.put(classField.getName(), attribute);
		}

		for (Method classGetter : classGetters) {
			String attributeName = extractFieldNameFromGetterMethod(classGetter);
			ClassAttribute attribute = map.get(attributeName);
			if (attribute != null) {
				attribute.getRawGetters().add(classGetter);
			} else {
				attribute = new ClassAttribute(null,
						Collections.singleton(classGetter),
						Collections.<Method>emptySet());
				map.put(attributeName, attribute);
			}
		}

		for (Method classSetter : classSetters) {
			String attributeName = extractFieldNameFromSetterMethod(classSetter);
			ClassAttribute attribute = map.get(attributeName);
			if (attribute != null) {
				attribute.getRawSetters().add(classSetter);
			} else {
				attribute = new ClassAttribute(null,
						Collections.<Method>emptySet(),
						Collections.singleton(classSetter));
				map.put(attributeName, attribute);
			}
		}

		/* Approve all found attributes */
		Collection<ClassAttribute> attributes = new ArrayList<ClassAttribute>(map.values());
		Iterator<ClassAttribute> iter = attributes.iterator();
		main : while(iter.hasNext()) {
			ClassAttribute attribute = iter.next();

			for (Method classGetter : attribute.getRawGetters()) {
				if (containsAnyAnnotation(classGetter, excludeFieldAnnotations)) {
					iter.remove();
					continue main;
				}
			}

			for (Method classSetter : attribute.getRawSetters()) {
				if (containsAnyAnnotation(classSetter, excludeFieldAnnotations)) {
					iter.remove();
					continue main;
				}
			}

			Field field = attribute.getAttribute();
			if (field != null && (
					excludedFields.contains(field.getName())
					|| containsAnyAnnotation(field, excludeFieldAnnotations))) {
				iter.remove();
				continue;
			}

			if (!attributeApprover.approve(attribute)) {
				iter.remove();
			}
		}

		return new ClassInfo(clazz, attributes, extraMethods);
	}


	/**
	 * Checks if the given field has any one of the annotations
	 *
	 * @param field
	 *            the field to check for
	 * @param annotations
	 *            the set of annotations to look for in the field
	 * @return true if the field is marked with any of the given annotations
	 */
	public static boolean containsAnyAnnotation(Field field,
			Set<Class<? extends Annotation>> annotations) {
		for (Class<? extends Annotation> annotation : annotations) {
			if (field.getAnnotation(annotation) != null) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Checks if the given method has any one of the annotations
	 *
	 * @param method
	 *            the method to check for
	 * @param annotations
	 *            the set of annotations to look for in the field
	 * @return true if the field is marked with any of the given annotations
	 */
	private static boolean containsAnyAnnotation(Method method,
			Set<Class<? extends Annotation>> annotations) {

		for (Class<? extends Annotation> annotation : annotations) {
			if (method.getAnnotation(annotation) != null) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Given a class and a set of class declared fields it returns a Set of
	 * setters, getters and fields defined for this class
	 * <p>
	 * Anything present: setter, getter or field will be recorded as three
	 * independent sets available for future analysis
	 * </p>
	 *
	 * @param clazz
	 *            The class to analyze for setters
	 * @param classFields
	 *            The {@link Set} which will be filled with class' fields
	 * @param classGetters
	 *            The {@link Set} which will be filled with class' getters
	 * @param classSetters
	 *            The {@link Set} which will be filled with class' setters
	 * @param excludeAnnotations
	 *            The {@link Set} containing annotations marking fields to be excluded
	 * @param excludedFields
	 *            The {@link Set} containing field names to be excluded
	 */
	public static void fillPojoSets(Class<?> clazz, Set<Field> classFields,
			Set<Method> classGetters, Set<Method> classSetters,
			Set<Class<? extends Annotation>> excludeAnnotations,
			Set<String> excludedFields) {

		if (excludeAnnotations == null) {
			excludeAnnotations = new HashSet<Class<? extends Annotation>>();
		}
		excludeAnnotations.add(PodamExclude.class);

		Class<?> workClass = clazz;

		while (workClass != null) {

			Method[] declaredMethods = workClass.getDeclaredMethods();

			for (Method method : declaredMethods) {
				/*
				 * Bridge methods are automatically generated by compiler to
				 * deal with type erasure and they are not type safe. That why
				 * they should be ignored
				 */
				if (!method.isBridge() && !Modifier.isNative(method.getModifiers())) {

					if (method.getName().startsWith("set")
							&& method.getReturnType().equals(void.class)) {
						classSetters.add(method);
					} else if ((method.getName().startsWith(GETTER_PREFIX) || method.getName().startsWith(GETTER_PREFIX2)) &&
							method.getParameterTypes().length == 0 && !method.getReturnType().equals(void.class)) {
						classGetters.add(method);
					}
				}
			}

			Field[] declaredFields = workClass.getDeclaredFields();
			for (Field field : declaredFields) {
				int modifiers = field.getModifiers();
				if (!Modifier.isStatic(modifiers)) {

					classFields.add(field);
				}
			}

			workClass = workClass.getSuperclass();
		}
	}

	/**
	 * Given a setter {@link Method}, it extracts the field name, according to
	 * JavaBean standards
	 * <p>
	 * This method, given a setter method, it returns the corresponding
	 * attribute name. For example: given setIntField the method would return
	 * intField. The correctness of the return value depends on the adherence to
	 * JavaBean standards.
	 * </p>
	 *
	 * @param method
	 *            The setter method from which the field name is required
	 * @return The field name corresponding to the setter
	 */
	public static String extractFieldNameFromSetterMethod(Method method) {
		String candidateField = method.getName().substring(SETTER_IDENTIFIER_LENGTH);
		if (!"".equals(candidateField)) {
			candidateField = Character.toLowerCase(candidateField.charAt(0))
					+ candidateField.substring(1);
		} else {
			LOG.debug("Encountered method {}. This will be ignored.", method);
		}

		return candidateField;
	}

	/**
	 * Given a getter {@link Method}, it extracts the field name, according to
	 * JavaBean standards
	 * <p>
	 * This method, given a getter method, it returns the corresponding
	 * attribute name. For example: given getIntField the method would return
	 * intField; given isBoolField the method would return boolField. The
	 * correctness of the return value depends on the adherence to JavaBean
	 * standards.
	 * </p>
	 *
	 * @param method
	 *            The setter method from which the field name is required
	 * @return The field name corresponding to the setter
	 */
	public static String extractFieldNameFromGetterMethod(Method method) {
		String candidateField = method.getName();
		if (candidateField.startsWith(GETTER_PREFIX)) {
			candidateField = candidateField.substring(GETTER_PREFIX.length());
		} else if (candidateField.startsWith(GETTER_PREFIX2)) {
			candidateField = candidateField.substring(GETTER_PREFIX2.length());
		}
		if (!"".equals(candidateField)) {
			candidateField = Character.toLowerCase(candidateField.charAt(0))
					+ candidateField.substring(1);
		} else {
			LOG.debug("Encountered method {}. This will be ignored.", method);
		}

		return candidateField;
	}

	/**
	 * It returns a {@link Field} matching the attribute name or null if a field
	 * was not found.
	 *
	 * @param pojoClass
	 *            The class supposed to contain the field
	 * @param attributeName
	 *            The field name
	 *
	 * @return a {@link Field} matching the attribute name or null if a field
	 *         was not found.
	 */
	public static Field getField(Class<?> pojoClass, String attributeName) {

		Field field = null;

		Class<?> clazz = pojoClass;

		while (clazz != null) {
			try {
				field = clazz.getDeclaredField(attributeName);
				break;
			} catch (NoSuchFieldException e) {
				clazz = clazz.getSuperclass();
			}
		}
		if (field == null) {
			LOG.warn("A field could not be found for attribute '{}[{}]'",
					pojoClass, attributeName);
		}
		return field;
	}

	/**
	 * It returns an value for a {@link Field} matching the attribute
	 * name or null if a field was not found.
	 *
	 * @param <T>
	 *            The type of field to be returned
	 * @param pojo
	 *            The class supposed to contain the field
	 * @param attributeName
	 *            The field name
	 *
	 * @return an instance of {@link Field} matching the attribute name or
	 *         null if a field was not found.
	 */
	public static <T> T getFieldValue(Object pojo, String attributeName) {
		T retValue = null;

		try {
			Field field = PodamUtils.getField(pojo.getClass(), attributeName);

			if (field != null) {

				// It allows to invoke Field.get on private fields
				field.setAccessible(true);

				@SuppressWarnings("unchecked")
				T t = (T) field.get(pojo);
				retValue = t;
			} else {

				LOG.info("The field {}[{}] didn't exist.", pojo.getClass(), attributeName);
			}

		} catch (Exception e) {

			LOG.warn("We couldn't get default value for {}[{}]",
					pojo.getClass(), attributeName, e);
		}

		return retValue;
	}

	/**
	 * Given the attribute and setter it combines annotations from them
	 * or an empty collection if no custom annotations were found
	 *
	 * @param attribute
	 *            The class attribute
	 * @param setter
	 *            The class attribute's setter
	 * @return all annotations for the attribute
	 */
	public static List<Annotation> getAttributeAnnotations(final Field attribute,
			final Method setter) {

		Annotation[] annotations = (attribute != null ? attribute.getAnnotations() : null);

		List<Annotation> retValue;
		if (annotations != null && annotations.length != 0) {
			retValue = new ArrayList<Annotation>(Arrays.asList(annotations));
		} else {
			retValue = new ArrayList<Annotation>();
		}
		for (Annotation annotation : setter.getParameterAnnotations()[0]) {
			retValue.add(annotation);
		}

		return retValue;
	}

	/**
	 * Generates random character from set valid for identifiers in Java language
	 *
	 * @return random character suitable for identifier
	 */
	public static Character getNiceCharacter() {

		int randomCharIdx =
				(int) (Math.random() * (NICE_ASCII_CHARACTERS.length - 1) + 0.5);

		return NICE_ASCII_CHARACTERS[randomCharIdx];

	}
	
	/**
	 * It returns a long/Long value between min and max value (included).
	 * 
	 * @param minValue
	 *            The minimum value for the returned value
	 * @param maxValue
	 *            The maximum value for the returned value
	 * @return A long/Long value between min and max value (included).
	 */
	public static long getLongInRange(long minValue, long maxValue) {
		return (long) (minValue + Math.random() * (maxValue - minValue) + 0.5);
	}

	/**
	 * Finds boxed type for a primitive type
	 * 
	 * @param primitiveType
	 *            Primitive type to find boxed type for
	 * @return A boxed type or the same type, if original type was not primitive
	 */
	public static Class<?> primitiveToBoxedType(Class<?> primitiveType) {

		if (int.class.equals(primitiveType)) {
			return Integer.class;
		} else if (double.class.equals(primitiveType)) {
			return Double.class;
		} else if (long.class.equals(primitiveType)) {
			return Long.class;
		} else if (byte.class.equals(primitiveType)) {
			return Byte.class;
		} else if (float.class.equals(primitiveType)) {
			return Float.class;
		} else if (char.class.equals(primitiveType)) {
			return Character.class;
		} else if (short.class.equals(primitiveType)) {
			return Short.class;
		} else {
			return primitiveType;
		}
	}
}
