/**
 *
 */
package uk.co.jemos.podam.api;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.*;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import uk.co.jemos.podam.common.PodamExclude;

/**
 * Default abstract implementation of a {@link ClassInfoStrategy}
 * <p>
 * This default implementation is based on field introspection.
 * </p>
 *
 * @author daivanov
 *
 * @since 5.1.0
 *
 */

public abstract class AbstractClassInfoStrategy implements ClassInfoStrategy,
		ClassAttributeApprover {

	// ------------------->> Constants

	private final Pattern GETTER_PATTERN = getGetterPattern();
	private final Pattern SETTER_PATTERN = getSetterPattern();

	// ------------------->> Instance / Static variables

	/** The application logger. */
	private static final Logger LOG = LoggerFactory.getLogger(AbstractClassInfoStrategy.class);

	/**
	 * Set of annotations, which mark fields to be skipped from populating.
	 */
	private final Set<Class<? extends Annotation>> excludedAnnotations =
			new HashSet<Class<? extends Annotation>>();

	/**
	 * Set of fields, which mark fields to be skipped from populating.
	 */
	private Map<Class<?>, Set<String>> excludedFields
			= new HashMap<Class<?>, Set<String>>();


	/**
	 * Set of extra methods to execute.
	 * @since 5.3.0
	 **/
	private final Map<Class<?>, List<Method>> extraMethods = new HashMap<Class<?>, List<Method>>();

	// ------------------->> Constructors

	// ------------------->> Public methods



	/**
	 * Adds the specified {@link Annotation} to set of excluded annotations,
	 * if it is not already present.
	 *
	 * @param annotation
	 *            the annotation to use as an exclusion mark
	 * @return itself
	 */
	public AbstractClassInfoStrategy addExcludedAnnotation(
			final Class<? extends Annotation> annotation) {
		excludedAnnotations.add(annotation);
		return this;
	}

	/**
	 * It adds an extra method to execute
	 * @param pojoClass The pojo class where to execute the method
	 * @param methodName name to be scheduled for execution
	 * @param methodArgs list of method arguments
	 * @return this object
	 * @throws SecurityException If a security exception occurred while retrieving the method
	 * @throws NoSuchMethodException If pojoClass doesn't declare the required method
	 * @since 5.3.0
	 */
	public AbstractClassInfoStrategy addExtraMethod(
			Class<?> pojoClass, String methodName, Class<?> ... methodArgs)
			throws NoSuchMethodException, SecurityException {

		Method method = pojoClass.getMethod(methodName, methodArgs);

		List<Method> methods = extraMethods.get(pojoClass);
		if (methods == null) {
			methods = new ArrayList<Method>();
			extraMethods.put(pojoClass, methods);
		}

		methods.add(method);

		return this;
	}

	/**
	 * Removes the specified {@link Annotation} from set of excluded annotations.
	 *
	 * @param annotation
	 *            the annotation used as an exclusion mark
	 * @return itself
	 */
	public AbstractClassInfoStrategy removeExcludedAnnotation(
			final Class<? extends Annotation> annotation) {
		excludedAnnotations.remove(annotation);
		return this;
	}

	/**
	 * Adds the specified field to set of excluded fields,
	 * if it is not already present.
	 *
	 * @param pojoClass
	 *        a class for which fields should be skipped
	 * @param fieldName
	 *            the field name to use as an exclusion mark
	 * @return itself
	 */
	public AbstractClassInfoStrategy addExcludedField(
			final Class<?> pojoClass, final String fieldName) {
		Set<String> fields = excludedFields.get(pojoClass);
		if (fields == null) {
			fields = new HashSet<String>();
			excludedFields.put(pojoClass, fields);
		}
		fields.add(fieldName);
		return this;
	}

	/**
	 * Removes the field name from set of excluded fields.
	 *
	 * @param pojoClass
	 *        a class for which fields should be skipped
	 * @param fieldName
	 *            the field name used as an exlusion mark
	 * @return itself
	 */
	public AbstractClassInfoStrategy removeExcludedField(
			final Class<?> pojoClass, final String fieldName) {
		Set<String> fields = excludedFields.get(pojoClass);
		if (fields != null) {
			fields.remove(fieldName);
		}
		return this;
	}


	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean approve(ClassAttribute attribute) {
		/* skip setters having more than one parameter,
		 * when there is more than one setter for a field */
		if (attribute.getRawSetters().size() > 1) {
			for (Method setter : attribute.getRawSetters()) {
				if (setter.getParameterTypes().length > 1) {
					return false;
				}
			}
		}
		return (attribute.getAttribute() != null);
	}

	// ------------------->> Getters / Setters
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Set<Class<? extends Annotation>> getExcludedAnnotations() {
		return excludedAnnotations;
	}


	/**
	 * {@inheritDoc}
	 */
	@Override
	public Set<String> getExcludedFields(final Class<?> pojoClass) {
		return excludedFields.get(pojoClass);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ClassInfo getClassInfo(Class<?> pojoClass) {
		Set<String> excludedAttributes = excludedFields.get(pojoClass);
		if (null == excludedAttributes) {
			excludedAttributes = Collections.emptySet();
		}
		List<Method> localExtraMethods = extraMethods.get(pojoClass);
		if (null == localExtraMethods) {
			localExtraMethods = Collections.emptyList();
		}
		return getClassInfo(pojoClass,
				excludedAnnotations, excludedAttributes, this, localExtraMethods);
	}

	@Override
	public ClassAttributeApprover getClassAttributeApprover(Class<?> pojoClass) {
		return this;
	}

	@Override
	public Collection<Method> getExtraMethods(Class<?> pojoClass) {
		return extraMethods.get(pojoClass);
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
	 * @param extraMethods
	 *            extra methods to call after object initialization
	 * @return a {@link ClassInfo} object for the given class
	 */
	public ClassInfo getClassInfo(Class<?> clazz,
										 Set<Class<? extends Annotation>> excludeFieldAnnotations,
										 Set<String> excludedFields,
										 ClassAttributeApprover attributeApprover,
										 Collection<Method> extraMethods) {

		if (null == attributeApprover) {
			attributeApprover = DefaultClassInfoStrategy.getInstance().getClassAttributeApprover(clazz);
		}

		Map<String, ClassAttribute> attributeMap = new TreeMap<String, ClassAttribute>();
		findPojoAttributes(clazz, attributeMap, excludeFieldAnnotations, excludedFields);

		/* Approve all found attributes */
		Collection<ClassAttribute> attributes = new ArrayList<ClassAttribute>(attributeMap.values());
		Iterator<ClassAttribute> iter = attributes.iterator();
		main : while(iter.hasNext()) {
			ClassAttribute attribute = iter.next();

			Field field = attribute.getAttribute();
			if (excludedFields.contains(attribute.getName()) ||
					(field != null && containsAnyAnnotation(field, excludeFieldAnnotations))) {
				iter.remove();
				continue;
			}

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

			if (!attributeApprover.approve(attribute)) {
				iter.remove();
			}
		}

		return new ClassInfo(clazz, attributes, extraMethods);
	}

	// ------------------->> Private methods

	/**
	 * Checks if the given method has any one of the annotations
	 *
	 * @param method
	 *            the method to check for
	 * @param annotations
	 *            the set of annotations to look for in the field
	 * @return true if the field is marked with any of the given annotations
	 */
	private boolean containsAnyAnnotation(Method method,
			Set<Class<? extends Annotation>> annotations) {

		for (Class<? extends Annotation> annotation : annotations) {
			if (method.getAnnotation(annotation) != null) {
				return true;
			}
		}
		return false;
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
	private boolean containsAnyAnnotation(Field field,
			Set<Class<? extends Annotation>> annotations) {
		for (Class<? extends Annotation> annotation : annotations) {
			if (field.getAnnotation(annotation) != null) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Given a class and a set of class declared fields it returns a map of
	 * setters, getters and fields defined for this class
	 *
	 * @param clazz
	 *            The class to analyze for setters
	 * @param attributeMap
	 *            The {@link Map} which will be filled with class' attributes
	 * @param excludeAnnotations
	 *            The {@link Set} containing annotations marking fields to be excluded
	 * @param excludedFields
	 *            The {@link Set} containing field names to be excluded
	 */
	protected void findPojoAttributes(Class<?> clazz,
			Map<String, ClassAttribute> attributeMap,
			Set<Class<? extends Annotation>> excludeAnnotations,
			Set<String> excludedFields) {

		if (excludeAnnotations == null) {
			excludeAnnotations = new HashSet<Class<? extends Annotation>>();
		}
		excludeAnnotations.add(PodamExclude.class);

		Class<?> workClass = clazz;

		while (!Object.class.equals(workClass)) {

			Method[] declaredMethods = workClass.getDeclaredMethods();

			Field[] declaredFields = workClass.getDeclaredFields();
			for (Field field : declaredFields) {
				int modifiers = field.getModifiers();
				if (!Modifier.isStatic(modifiers)) {

					String attributeName = field.getName();
					ClassAttribute attribute = attributeMap.get(attributeName);
					if (attribute != null) {
						/* In case we have hidden fields, we probably want the
						 * latest one, but there could be corner cases */
						if (attribute.getAttribute() == null) {
							attribute.setAttribute(field);
						}
					} else {
						attribute = new ClassAttribute(field.getName(),
								field, Collections.<Method>emptySet(), Collections.<Method>emptySet());
						attributeMap.put(field.getName(), attribute);
					}
				}
			}

			for (Method method : declaredMethods) {
				/*
				 * Bridge methods are automatically generated by compiler to
				 * deal with type erasure and they are not type safe. That why
				 * they should be ignored
				 */
				if (!method.isBridge() && !Modifier.isNative(method.getModifiers())) {

					Pattern pattern;
					if (method.getParameterTypes().length == 0
							&& !method.getReturnType().equals(void.class)) {

						pattern = GETTER_PATTERN;
					} else if (method.getParameterTypes().length > 0
							&& (method.getReturnType().equals(void.class)
									|| method.getReturnType().equals(workClass))) {

						pattern = SETTER_PATTERN;
					} else {

						continue;
					}

					String methodName = method.getName();
					String attributeName = extractFieldNameFromMethod(methodName,
							pattern);
					if (!attributeName.equals(methodName)) {
						if (!attributeName.isEmpty()) {
							ClassAttribute attribute = attributeMap.get(attributeName);
							if (attribute == null) {
								attribute = new ClassAttribute(attributeName, null,
										Collections.<Method>emptySet(),
										Collections.<Method>emptySet());
								attributeMap.put(attributeName, attribute);
							}
							Set<Method> accessors;
							if (pattern == GETTER_PATTERN) {
								accessors = attribute.getRawGetters();
							} else {
								accessors = attribute.getRawSetters();
							}
							accessors.add(method);
						} else {
							LOG.debug("Encountered accessor {}. This will be ignored.", method);
						}
					}
				}
			}

			workClass = workClass.getSuperclass();
		}
	}

	/**
	 * Given a accessor's name, it extracts the field name, according to
	 * JavaBean standards
	 * <p>
	 * This method, given a accessor method's name, it returns the corresponding
	 * attribute name. For example: given setIntField the method would return
	 * intField. given getIntField the method would return intField; given
	 * isBoolField the method would return boolField.The correctness of the
	 * return value depends on the adherence to
	 * JavaBean standards.
	 * </p>
	 *
	 * @param methodName
	 *            The accessor method from which the field name is required
	 * @param pattern
	 *            The pattern to strip from the method name
	 * @return The field name corresponding to the setter
	 */
	protected String extractFieldNameFromMethod(String methodName, Pattern pattern) {
		String candidateField = pattern.matcher(methodName).replaceFirst("");
		if (!candidateField.isEmpty() && !candidateField.equals(methodName)) {
			candidateField = Character.toLowerCase(candidateField.charAt(0))
					+ candidateField.substring(1);
		}

		return candidateField;
	}

	/**
	 * Defines a regular expression for a getter's name
	 *
	 * @return a compiled pattern for the getter's name
	 */
	protected Pattern getGetterPattern() {
		return Pattern.compile("^(get|is)");
	}

	/**
	 * Defines a regular expression for a setters name
	 *
	 * @return a compiled pattern for the setter's name
	 */
	protected Pattern getSetterPattern() {
		return Pattern.compile("^set");
	}

	// ------------------->> equals() / hashcode() / toString()

	// ------------------->> Inner classes

}
