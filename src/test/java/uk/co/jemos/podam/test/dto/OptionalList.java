package uk.co.jemos.podam.test.dto;

import java.util.List;
import java.util.Optional;

public class OptionalList {

    private final Optional<List<String>> optionalList;

    public OptionalList(Optional<List<String>> optionalList) {
        this.optionalList = optionalList;
    }

    public Optional<List<String>> getOptionalList() {
        return optionalList;
    }
}
