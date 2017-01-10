/**
 *
 */
package uk.co.jemos.podam.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * PODAM Utilities class.
 *
 * @author mtedone
 *
 * @since 1.0.0
 *
 */
public abstract class PodamUtils {

	// ---------------------->> Constants

	/** An array of valid String characters */
	public static final char[] NICE_ASCII_CHARACTERS = new char[] { 'a', 'b',
			'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o',
			'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', 'A', 'B',
			'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O',
			'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', '0', '1',
			'2', '3', '4', '5', '6', '7', '8', '9', '_' };

	/** The application logger. */
	private static final Logger LOG = LoggerFactory.getLogger(PodamUtils.class);

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

		Class<?> clazz = pojoClass;
		while (clazz != null) {
			try {
				return clazz.getDeclaredField(attributeName);
			} catch (NoSuchFieldException e) {
				clazz = clazz.getSuperclass();
			}
		}

		LOG.warn("A field could not be found for attribute '{}[{}]'",
				pojoClass, attributeName);
		return null;
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
	 * Searches among set of a class'es methods and selects the one defined in
	 * the most specific descend of the hierarchy tree
	 *
	 * @param methods a set of methods to choose from
	 * @return the selected method
	 */
	public static Method selectLatestMethod(Set<Method> methods) {
		/* We want to find a method defined the latest */
		Method selected = null;
		for (Method method : methods) {
			if (selected == null || selected.getDeclaringClass().isAssignableFrom(method.getDeclaringClass())) {
				selected = method;
			}
		}
		return selected;
	}

	/**
	 * Given the attribute and setter it combines annotations from them
	 * or an empty collection if no custom annotations were found
	 *
	 * @param attribute
	 *            The class attribute
	 * @param methods
	 *            List of setters and getter to check annotations
	 * @return all annotations for the attribute
	 */
	public static List<Annotation> getAttributeAnnotations(final Field attribute,
			final Method... methods) {

		List<Annotation> retValue = new ArrayList<Annotation>();

		if (null != attribute) {
			for (Annotation annotation : attribute.getAnnotations()) {
				retValue.add(annotation);
			}
		}
		for (Method method : methods) {
			Annotation[][] paramAnnotations = method.getParameterAnnotations();
			if (paramAnnotations.length > 0) {
				for (Annotation annotation : paramAnnotations[0]) {
					retValue.add(annotation);
				}
			} else {
				for (Annotation annotation : method.getAnnotations()) {
					retValue.add(annotation);
				}
			}
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
	 * It returns a random int/Integer value between min and max value (included).
	 * 
	 * @param minValue
	 *            The minimum value for the returned value
	 * @param maxValue
	 *            The maximum value for the returned value
	 * @return An int/Integer value between min and max value (included).
	 */
	public static int getIntegerInRange(int minValue, int maxValue) {

		return (int) (minValue + Math.random() * (maxValue - minValue) + 0.5);
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
