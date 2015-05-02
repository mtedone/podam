/**
 * 
 */
package uk.co.jemos.podam.common;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Annotation used to customise min and max values on a float type attribute or
 * constructor parameter.
 * 
 * 
 * @author mtedone
 * 
 */
@Documented
@Target(value = { ElementType.FIELD, ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
public @interface PodamFloatValue {

	/** The minimum value for the annotated field
	 *
	 * @return min value
	 */
	float minValue() default 0.0f;

	/** The minimum value for the annotated field
	 *
	 * @return max value
	 */
	float maxValue() default 0.0f;

	/** It allows clients to make comments
	 *
	 * @return comment value
	 */
	String comment() default "";

	/**
	 * It returns the precise value to be assigned to the annotated attribute.
	 * <p>
	 * This attribute defaults to an empty string.
	 * </p>
	 * <p>
	 * Please note that the format should be String to allow for null values for
	 * this annotation attribute. If we set the type to the primitive for this
	 * annotation, then to allow for {@code null} values we should have set
	 * something arbitrary as {@code default 0}. If set, the value must be
	 * convertible to the type of this annotation or a
	 * {@link NumberFormatException} will be thrown.
	 * </p>
	 * <p>
	 * If set, it will take precedence over all other annotation attributes
	 * (e.g. min/max)
	 * </p>
	 * 
	 * @return The precise value to assign to the annotated attribute, in String
	 *         format
	 */
	String numValue() default "";

}
