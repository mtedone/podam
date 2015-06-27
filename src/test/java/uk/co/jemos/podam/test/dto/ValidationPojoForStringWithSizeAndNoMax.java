package uk.co.jemos.podam.test.dto;

import javax.validation.constraints.Size;

/**
 * POJO to test Issue 110
 * <p>
 *     When a String is annotated with @Size and only min value is defined,
 *     Podam should allow for sensible, max defaults
 * </p>
 * Created by tedonema on 26/06/2015.
 */
public class ValidationPojoForStringWithSizeAndNoMax {

    @Size(min = 0)
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("ValidationPojoForStringWithSizeAndNoMax{");
        sb.append("name='").append(name).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
