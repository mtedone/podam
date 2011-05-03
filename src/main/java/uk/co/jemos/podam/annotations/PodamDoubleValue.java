/**
 * 
 */
package uk.co.jemos.podam.annotations;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Annotation used to customise min and max values on a double type attribute or
 * constructor parameter.
 * 
 * 
 * @author mtedone
 * 
 */
@Documented
@Target(value = { ElementType.FIELD, ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
public @interface PodamDoubleValue {

	/** The minimum value for the annotated field */
	double minValue() default 0.0;

	/** The minimum value for the annotated field */
	double maxValue() default 0.0;

	/** It allows clients to make comments */
	String comment() default "";

}
