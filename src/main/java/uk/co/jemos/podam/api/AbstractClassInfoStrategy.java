/**
 *
 */
package uk.co.jemos.podam.api;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.*;

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

	// ------------------->> Instance / Static variables

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
		return PodamUtils.getClassInfo(pojoClass,
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


	// ------------------->> Private methods

	// ------------------->> equals() / hashcode() / toString()

	// ------------------->> Inner classes

}
