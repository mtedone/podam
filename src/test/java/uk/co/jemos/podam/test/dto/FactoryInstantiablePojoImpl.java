package uk.co.jemos.podam.test.dto;

/**
 * Implementation of factory instantiable class
 * 
 * @author daivanov
 *
 */
class FactoryInstantiablePojoImpl<T> extends FactoryInstantiablePojo<T> {

	protected FactoryInstantiablePojoImpl(T typedValue) {
		setTypedValue(typedValue);
	}
}
