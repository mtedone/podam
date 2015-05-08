package uk.co.jemos.podam.common;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * It identifies a constructor to use in order to create an instance of a class.
 * <p>
 * This annotation should be used in POJOs with attributes but without setters.
 * A typical example are immutable classes, where attributes are final and
 * declared in the constructor and they have getters but not setters.
 * </p>
 * 
 * @author mtedone
 * 
 */
@Documented
@Target({ElementType.CONSTRUCTOR, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface PodamConstructor {

	/** Gives users the possibility to leave comments
	 *
	 * @return comment value
	 */
	String comment() default "";
}
