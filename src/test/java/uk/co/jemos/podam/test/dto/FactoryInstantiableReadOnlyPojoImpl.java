package uk.co.jemos.podam.test.dto;

/**
 * Implementation of factory instantiable read-only class
 * 
 * @author daivanov
 *
 */
class FactoryInstantiableReadOnlyPojoImpl<T> extends FactoryInstantiableReadOnlyPojo<T> {

	protected FactoryInstantiableReadOnlyPojoImpl(T typedValue) {
		this.typedValue = typedValue;
	}
}
