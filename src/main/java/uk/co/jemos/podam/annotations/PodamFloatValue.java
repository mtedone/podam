/**
 * 
 */
package uk.co.jemos.podam.annotations;

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
@Target(value = { ElementType.FIELD, ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
public @interface PodamFloatValue {

	/** The minimum value for the annotated field */
	float minValue() default 0.0f;

	/** The minimum value for the annotated field */
	float maxValue() default 0.0f;

	/** It allows clients to make comments */
	String comment() default "";

}
