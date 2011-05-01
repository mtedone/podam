/**
 * 
 */
package uk.co.jemos.podam.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import uk.co.jemos.podam.utils.PodamConstants;

/**
 * Annotation used to customise collection-type elements
 * 
 * <p>
 * Please note that this annotation can be used with all types of container
 * elements, including arrays.
 * </p>
 * 
 * <p>
 * The minimum number of elements is
 * {@link PodamConstants#ANNOTATION_COLLECTION_DEFAULT_NBR_ELEMENTS}
 * </p>
 * 
 * @author mtedone
 * 
 */
@Target(value = { ElementType.FIELD, ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
public @interface PodamCollection {

	/**
	 * The number of elements to create for the collection
	 */
	int nbrElements() default PodamConstants.ANNOTATION_COLLECTION_DEFAULT_NBR_ELEMENTS;

	/** It allows clients to specify a comment on this annotation */
	String comment() default "";

}
