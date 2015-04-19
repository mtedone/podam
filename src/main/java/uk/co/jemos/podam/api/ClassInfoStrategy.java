/**
 * 
 */
package uk.co.jemos.podam.api;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.Set;

/**
 * This interface defines the contact for PODAM class info introspection.
 * <p>
 * It provides a tool for customization of class introspection process.
 * </p>
 * 
 * @author daivanov
 * 
 * @since 5.1.0
 * 
 */
public interface ClassInfoStrategy {

	/**
	 * Identifies {@link Annotation}s for fields to be skipped.
	 * <p>
	 * Should return set of annotations, which will be treated as notion for
	 * {@link PodamFactory} to skip production of these particular fields.
	 * </p>
	 * 
	 * @return set of annotations, which mark fields to be skipped from populating.
	 */
	Set<Class<? extends Annotation>> getExcludedAnnotations();

	/**
	 * Identifies fields to be skipped.
	 * <p>
	 * Should return set of field names as case-sensitive string, which will
	 * be treated as notion for {@link PodamFactory} to skip production of
	 * these particular fields.
	 * </p>
	 * 
	 * @param pojoClass
	 *        a class for which fields should be skipped
	 * @return set of field name, which mark fields to be skipped from populating.
	 */
	Set<String> getExcludedFields(Class<?> pojoClass);

	/**
	 * 
	 * @param pojoClass
	 *        a class to introspect and fetch attributes
	 * @return information about class internal structure {@link ClassInfo}
	 */
	ClassInfo getClassInfo(Class<?> pojoClass);


	ClassAttributeApprover getClassAttributeApprover(Class<?> pojoClass);

	/**
	 * It returns the collection of extra methods to execute.
	 * @param pojoClass The pojo class
	 * @return the collection of extra methods to execute.
	 * @since 5.3.0
	 */
	Collection<Method> getExtraMethods(Class<?> pojoClass);

}
