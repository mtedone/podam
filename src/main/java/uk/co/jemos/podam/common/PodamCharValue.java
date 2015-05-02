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
 * Annotation used to customise min and max values of a char type attribute or
 * constructor parameter.
 * 
 * 
 * @author mtedone
 * 
 */
@Documented
@Target(value = { ElementType.FIELD, ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
public @interface PodamCharValue {

	/** The minimum value for the annotated field
	 *
	 * @return min value
	 */
	char minValue() default 0;

	/** The minimum value for the annotated field
	 *
	 * @return max value
	 */
	char maxValue() default 0;

	/** It allows clients to make comments
	 *
	 * @return comment value
	 */
	String comment() default "";

	/**
	 * A precise char value to assign to the annotated attribute.
	 * <p>
	 * If set, it will take precedence over all other annotation attributes
	 * (e.g. min/max). It is assumed that if the value of {@code charValue} is
	 * the default, the user didn't set it and therefore this value will not
	 * take precedence over {@code minValue} and {@code maxValue}.
	 * </p>
	 * <p>
	 * This attribute defaults to an empty char.
	 * </p>
	 * 
	 * @return A precise char value to assign to the annotated attribute.
	 */
	char charValue() default ' ';

}
