/**
 *
 */
package uk.co.jemos.podam.api;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.HashSet;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import uk.co.jemos.podam.common.PodamExclude;

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
	 * @return a {@link ClassInfo} object for the given class
	 */
	public static ClassInfo getClassInfo(Class<?> clazz) {
		return getClassInfo(clazz, null);
	}

	/**
	 * It returns a {@link ClassInfo} object for the given class
	 *
	 * @param clazz
	 *            The class to retrieve info from
	 * @param excludeFieldAnnotations
	 *            the fields marked with any of these annotations will not be
	 *            included in the class info
	 * @return a {@link ClassInfo} object for the given class
	 */
	public static ClassInfo getClassInfo(Class<?> clazz,
			Set<Class<? extends Annotation>> excludeFieldAnnotations) {
		Set<String> classFields = getDeclaredInstanceFields(clazz, excludeFieldAnnotations);
		Set<Method> classSetters = getPojoSetters(clazz, classFields);
		return new ClassInfo(clazz, classFields, classSetters);
	}

	/**
	 * Given a class, it returns a Set of its declared instance field names.
	 *
	 * @param clazz
	 *            The class to analyse to retrieve declared fields
	 * @return Set of a class declared field names.
	 */
	public static Set<String> getDeclaredInstanceFields(Class<?> clazz) {
		return getDeclaredInstanceFields(clazz, null);
	}

	/**
	 * Given a class, it returns a Set of its declared instance field names.
	 *
	 * @param clazz
	 *            The class to analyse to retrieve declared fields
	 * @param excludeAnnotations
	 *            fields marked with any of the mentioned annotations will be
	 *            skipped
	 * @return Set of a class declared field names.
	 */
	public static Set<String> getDeclaredInstanceFields(Class<?> clazz,
			Set<Class<? extends Annotation>> excludeAnnotations) {

		if (excludeAnnotations == null) {
			excludeAnnotations = new HashSet<Class<? extends Annotation>>();
		}
		excludeAnnotations.add(PodamExclude.class);

		Class<?> workClass = clazz;

		Set<String> classFields = new HashSet<String>();

		while (workClass != null) {
			Field[] declaredFields = workClass.getDeclaredFields();
			for (Field field : declaredFields) {
				// If users wanted to skip this field, we grant their wishes
				if (containsAnyAnnotation(field, excludeAnnotations)) {
					continue;
				}
				int modifiers = field.getModifiers();
				if (!Modifier.isStatic(modifiers)) {

					classFields.add(field.getName());
				}

			}
			workClass = workClass.getSuperclass();
		}

		return classFields;
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
		Method method = getGetterFor(field);
		for (Class<? extends Annotation> annotation : annotations) {
			if (field.getAnnotation(annotation) != null) {
				return true;
			}
			if (method != null && method.getAnnotation(annotation) != null) {
				return true;
			}
		}
		return false;
	}

	/**
	 * It returns the getter for the given field.
	 *
	 * @param field
	 *            The {@link Field} for which the getter is required
	 * @return the getter for the given field or null if no getter was found
	 */
	public static Method getGetterFor(Field field) {
		String name = field.getName().substring(0, 1).toUpperCase()
				+ field.getName().substring(1);

		String methodName;
		if (boolean.class.isAssignableFrom(field.getType()) ||
			Boolean.class.isAssignableFrom(field.getType())) {
			methodName = "is" + name;
		} else {
			methodName = "get" + name;
		}

		try {
			return field.getDeclaringClass().getMethod(methodName);
		} catch (NoSuchMethodException e) {
			if (methodName.startsWith("is")) {
				methodName = "get" + name;
				try {
					return field.getDeclaringClass().getMethod(methodName);
				} catch (NoSuchMethodException e2) {
				}
			}
			LOG.info("No getter {}() for field {}[{}]", methodName,
					field.getDeclaringClass().getName(), field.getName());
			return null;
		}
	}

	/**
	 * Given a class and a set of class declared fields it returns a Set of
	 * setters matching the declared fields
	 * <p>
	 * If present, a setter method is considered if and only if the
	 * {@code classFields} argument contains an attribute whose name matches the
	 * setter, according to JavaBean standards.
	 * </p>
	 *
	 * @param clazz
	 *            The class to analyse for setters
	 * @param classFields
	 *            A Set of field names for which setters are to be found
	 * @return A Set of setters matching the class declared field names
	 *
	 */
	public static Set<Method> getPojoSetters(Class<?> clazz,
			Set<String> classFields) {

		Class<?> workClass = clazz;

		Set<Method> classSetters = new HashSet<Method>();

		while (workClass != null) {

			Method[] declaredMethods = workClass.getDeclaredMethods();
			String candidateField = null;

			for (Method method : declaredMethods) {
				/* Bridge methods are automatically generated by compiler to
				   deal with type erasure and they are not type safe.
				   That why they should be ignored */
				if (!method.isBridge()
						&& method.getName().startsWith("set")
						&& method.getReturnType().equals(void.class)) {
					candidateField = extractFieldNameFromSetterMethod(method);
					if (classFields.contains(candidateField)) {
						classSetters.add(method);
					}

				}

			}
			workClass = workClass.getSuperclass();
		}

		return classSetters;
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
		String candidateField = null;
		candidateField = method.getName().substring(SETTER_IDENTIFIER_LENGTH);
		if (!"".equals(candidateField)) {
			candidateField = Character.toLowerCase(candidateField.charAt(0))
					+ candidateField.substring(1);
		} else {
			LOG.warn("Encountered method {}. This will be ignored.",
					method);
		}

		return candidateField;
	}

}
