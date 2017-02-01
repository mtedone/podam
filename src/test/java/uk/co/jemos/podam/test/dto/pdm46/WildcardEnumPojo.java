package uk.co.jemos.podam.test.dto.pdm46;

import java.io.Serializable;

public class WildcardEnumPojo implements Serializable {

    private Enum<?> wildcardEnumField;

    public Enum<?> getWildcardEnumField() {
        return wildcardEnumField;
    }

    public void setWildcardEnumField(Enum<?> wildcardEnumField) {
        this.wildcardEnumField = wildcardEnumField;
    }

    @Override
    public String toString() {
        return "WildcardEnumPojo{" +
                "wildcardEnumField=" + wildcardEnumField +
                '}';
    }
}