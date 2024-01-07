package uk.co.jemos.podam.common;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Annotation used to customise min and max values of a {@link java.time.ZoneId} type attribute or
 * constructor parameter.
 *
 *
 * @author liam
 * @since 8.0.1.RELEASE
 */
@Documented
@PodamAnnotation
@Target({FIELD, METHOD, PARAMETER})
@Retention(RUNTIME)
public @interface PodamZoneIdValue {
    String zoneId() default "";

    String comment() default "";

    String value() default "";
}
