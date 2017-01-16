/**
 *
 */
package uk.co.jemos.podam.test.dto;

import org.hibernate.validator.constraints.Length;

import uk.co.jemos.podam.common.PodamIntValue;
import uk.co.jemos.podam.common.PodamStringValue;

import javax.persistence.Id;
import javax.persistence.Version;
import javax.validation.constraints.Max;
import javax.validation.constraints.Pattern;

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
