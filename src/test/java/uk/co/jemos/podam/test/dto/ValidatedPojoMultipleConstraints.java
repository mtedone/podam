package uk.co.jemos.podam.test.dto;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;

/**
 * POJO to test multiple constraints attached to the same field
 *
 * @author daivanov
 *
 */
public class ValidatedPojoMultipleConstraints {

    @DecimalMax("100.0")
    @DecimalMin("-100.0")
    @Digits(integer = 1, fraction = 1)
    private Double value;

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder(this.getClass().getSimpleName());
        sb.append('{');
        sb.append("value='").append(value).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
