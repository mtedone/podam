package uk.co.jemos.podam.test.dto;

/**
 * Abstract factory instantiable read-only class
 * 
 * @author daivanov
 *
 */
public abstract class FactoryInstantiableReadOnlyPojo<T> {

	protected T typedValue;

	public static <E> FactoryInstantiableReadOnlyPojo<E> getInstance(E typedValue) {
		return new FactoryInstantiableReadOnlyPojoImpl<E>(typedValue);
	}

	/**
	 * @return the typedValue
	 */
	public T getTypedValue() {
		return typedValue;
	}
}
