/**
 *
 */
package uk.co.jemos.podam.test.dto;

import org.hibernate.validator.constraints.Length;
import uk.co.jemos.podam.common.IgnoreNonPodamAnnotations;
import uk.co.jemos.podam.common.PodamIntValue;
import uk.co.jemos.podam.common.PodamStringValue;

import javax.validation.constraints.Max;
import javax.validation.constraints.Pattern;
import java.io.Serializable;


/**
 * @author mtedone
 */
public class SimplePojoWithIgnoreNonPodamAnnotations implements Serializable {


    private String stringField;

    @IgnoreNonPodamAnnotations
    @Length(min = 3, max = 3)
    private String stringFieldWithHibernateAnnotation;

    @Length(min = 3, max = 3)
    @Pattern(regexp = "stringFieldWithPatternRegex")
    @IgnoreNonPodamAnnotations
    private String stringFieldWithPatternAnnotation;

    @Max(value = 100)
    @IgnoreNonPodamAnnotations
    private Integer integerWithHibernateAnnotation;


    public String getStringField() {
        return stringField;
    }

    public void setStringField(String stringField) {
        this.stringField = stringField;
    }

    public String getStringFieldWithHibernateAnnotation() {
        return stringFieldWithHibernateAnnotation;
    }

    public void setStringFieldWithHibernateAnnotation(String stringFieldWithHibernateAnnotation) {
        this.stringFieldWithHibernateAnnotation = stringFieldWithHibernateAnnotation;
    }


    public String getStringFieldWithPatternAnnotation() {
        return stringFieldWithPatternAnnotation;
    }

    public void setStringFieldWithPatternAnnotation(String stringFieldWithPatternAnnotation) {
        this.stringFieldWithPatternAnnotation = stringFieldWithPatternAnnotation;
    }

    public Integer getIntegerWithHibernateAnnotation() {
        return integerWithHibernateAnnotation;
    }

    public void setIntegerWithHibernateAnnotation(Integer integerWithHibernateAnnotation) {
        this.integerWithHibernateAnnotation = integerWithHibernateAnnotation;
    }
}
