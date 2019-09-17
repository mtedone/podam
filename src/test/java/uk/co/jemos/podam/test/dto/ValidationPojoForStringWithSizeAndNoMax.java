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

    @Size(max = 100)
    private String name2;

    public String getName2() {
        return name2;
    }

    public void setName2(String name2) {
        this.name2 = name2;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("ValidationPojoForStringWithSizeAndNoMax{");
        sb.append("name='").append(name).append('\'');
        sb.append("name2='").append(name2).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
