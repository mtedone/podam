package uk.co.jemos.podam.test.dto;

import com.google.common.collect.ImmutableList;

public class ExternalLibraryPojo {

    private final ImmutableList<Double> doubles;

    public ExternalLibraryPojo(ImmutableList<Double> doubles) {
        this.doubles = doubles;
    }

    public ImmutableList<Double> getDoubles() {
        return doubles;
    }
}
