/**
 * 
 */
package uk.co.jemos.podam.utils;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import uk.co.jemos.podam.annotations.PodamExclude;
import uk.co.jemos.podam.dto.ClassInfo;

/**
 * PODAM Utilities class.
 * 
 * @author mtedone
 * 
 * @since 1.0.0
 * 
 */

public class PodamUtils {

	// ---------------------->> Constants

	/** The application logger. */
	public static final org.apache.log4j.Logger LOG = org.apache.log4j.Logger
			.getLogger(PodamUtils.class);

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
	 * @param excludeFieldAnnotations the fields marked with any of these annotations will not be included in the class info
	 * @return a {@link ClassInfo} object for the given class
	 */
	public static ClassInfo getClassInfo(Class<?> clazz, List<Class<? extends Annotation>> excludeFieldAnnotations) {
		Set<String> classFields = null;
		if (excludeFieldAnnotations != null && !excludeFieldAnnotations.isEmpty()) {
			classFields = getDeclaredInstanceFields(clazz, excludeFieldAnnotations);
		} else {
			classFields = getDeclaredInstanceFields(clazz);
		}
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
		List<Class<? extends Annotation>> excludedAnnotations = new ArrayList<Class<? extends Annotation>>();
		excludedAnnotations.add(PodamExclude.class);
		return getDeclaredInstanceFields(clazz, excludedAnnotations);
	}
	
	/**
	 * Given a class, it returns a Set of its declared instance field names.
	 * 
	 * @param clazz
	 *            The class to analyse to retrieve declared fields
	 * @param excludeAnnotations fields marked with any of the mentioned annotations will be skipped
	 * @return Set of a class declared field names.
	 */
	public static Set<String> getDeclaredInstanceFields(Class<?> clazz, List<Class<? extends Annotation>> excludeAnnotations) {
		Set<String> classFields = new HashSet<String>();

		while (clazz != null) {
			Field[] declaredFields = clazz.getDeclaredFields();
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
			clazz = clazz.getSuperclass();
		}

		return classFields;
	}
	
	/**
	 * Checks if the given field has any one of the annotations
	 * 
	 * @param field the field to check for
	 * @param annotations the list of annotations to look for in the field
	 * @return true if the field is marked with any of the given annotations 
	 */
	public static boolean containsAnyAnnotation(Field field, List<Class<? extends Annotation>> annotations) {
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
	
	public static Method getGetterFor(Field field) {
		String name = field.getName().substring(0, 1).toUpperCase() + field.getName().substring(1);
		try {
			return field.getDeclaringClass().getMethod("get" + name);
		} catch (NoSuchMethodException e) {
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
	public static Set<Method> getPojoSetters(Class<?> clazz, Set<String> classFields) {

		Set<Method> classSetters = new HashSet<Method>();

		while (clazz != null) {

			Method[] declaredMethods = clazz.getDeclaredMethods();
			String candidateField = null;
			for (Method method : declaredMethods) {
				if (!method.getName().startsWith("set")) {
					continue;
				}
				if (!method.getReturnType().equals(void.class)) {
					continue;
				}
				candidateField = extractFieldNameFromSetterMethod(method);
				if (!classFields.contains(candidateField)) {
					continue;
				}
				classSetters.add(method);

			}
			clazz = clazz.getSuperclass();
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
		candidateField = method.getName().substring(3);
		if (!candidateField.equals("")) {
			candidateField = Character.toLowerCase(candidateField.charAt(0))
					+ candidateField.substring(1);
		} else {
			LOG.warn("Encountered method set. This will be ignored.");
		}

		return candidateField;
	}

}
