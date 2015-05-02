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
 * Annotation which will have PODAM to skip the annotated attribute from
 * consideration.
 * <p>
 * PODAM will skip POJO attributes annotated with this annotation, thus their
 * value will not be set.
 * </p>
 * 
 * @author mtedone
 * 
 */
@Documented
@Target(value = ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface PodamExclude {

	/** Gives users the possibility to leave comments
	 *
	 * @return comment value
	 */
	String comment() default "";
}
