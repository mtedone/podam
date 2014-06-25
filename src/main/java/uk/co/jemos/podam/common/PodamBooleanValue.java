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
 * Annotation used to customise a boolean value on the annotated field
 * 
 * @author mtedone
 * 
 */
@Documented
@Target(value = { ElementType.FIELD, ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
public @interface PodamBooleanValue {

	/**
	 * The value to assign to the annotated attribute.
	 * 
	 * @return The value to assign to the annotated attribute
	 */
	boolean boolValue() default false;

}
