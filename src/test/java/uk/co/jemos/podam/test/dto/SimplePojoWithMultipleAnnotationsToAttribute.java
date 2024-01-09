/**
 *
 */
package uk.co.jemos.podam.test.dto;

import org.hibernate.validator.constraints.Length;

import uk.co.jemos.podam.common.PodamIntValue;
import uk.co.jemos.podam.common.PodamStringValue;

import jakarta.persistence.Basic;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Version;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

/**
 * @author sba45
 */
public class SimplePojoWithMultipleAnnotationsToAttribute {

    private String stringField;

    @Id
    @Length(min = 3, max = 3)
    @PodamStringValue(strValue = "testString")
    private String stringFieldWithHibernateAnnotation;

    @Length(min = 3, max = 3)
    @Pattern(regexp = "stringFieldWithPatternRegex")
    @PodamStringValue(strValue = "stringFieldWithPatternRegex")
    private String stringFieldWithPatternAnnotation;

    @Max(value = 100)
    @PodamIntValue(maxValue = 100, minValue = 0)
    @Version
    private Integer integerWithHibernateAnnotation;

    @Basic
    @Size(min = 7, max = 7)
    private String stringFieldNoPodamAnnotation;

    @Size(min = 3, max = 3)
    @Basic
    private String stringFieldBasicSecondAnnotation;

    @GeneratedValue(strategy=GenerationType.AUTO)
    private String stringFieldUnsupportedAnnotation;

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

    public String getStringFieldNoPodamAnnotation() {
        return stringFieldNoPodamAnnotation;
    }

    public void setStringFieldNoPodamAnnotation(String stringFieldNoPodamAnnotation) {
        this.stringFieldNoPodamAnnotation = stringFieldNoPodamAnnotation;
    }

    public String getStringFieldBasicSecondAnnotation() {
        return stringFieldBasicSecondAnnotation;
    }

    public void setStringFieldBasicSecondAnnotation(String stringFieldBasicSecondAnnotation) {
        this.stringFieldBasicSecondAnnotation = stringFieldBasicSecondAnnotation;
    }

    public String getStringFieldUnsupportedAnnotation() {
        return stringFieldUnsupportedAnnotation;
    }

    public void setStringFieldUnsupportedAnnotation(String stringFieldUnsupportedAnnotation) {
        this.stringFieldUnsupportedAnnotation = stringFieldUnsupportedAnnotation;
    }
}
