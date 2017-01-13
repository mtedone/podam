/**
 * 
 */
package uk.co.jemos.podam.common;

import java.lang.annotation.*;

/**
 * Annotation used to ignore other non podam annotations
 *
 *
 *
 *
 */
@Documented
@PodamAnnotation
@Target(value = { ElementType.FIELD, ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
public @interface IgnoreNonPodamAnnotations {
}
