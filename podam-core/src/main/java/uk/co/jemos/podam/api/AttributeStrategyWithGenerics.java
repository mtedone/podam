package uk.co.jemos.podam.api;

import uk.co.jemos.podam.exceptions.PodamMockeryException;

import java.lang.reflect.Type;

public interface AttributeStrategyWithGenerics<T> extends AttributeStrategy<T> {

	T getValue(Type genericTypeArg) throws PodamMockeryException;

}
