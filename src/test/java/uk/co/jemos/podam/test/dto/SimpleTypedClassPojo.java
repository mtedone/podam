package uk.co.jemos.podam.test.dto;

/**
 * Pojo typed class
 * 
 * @author daivanov
 *
 */
public class SimpleTypedClassPojo<T extends Number> {

	private T typedValue;

	/**
	 * @return the typedValue
	 */
	public T getTypedValue() {
		return typedValue;
	}

	/**
	 * @param typedValue
	 *            the typedList to set
	 */
	public void setTypedValue(T typedValue) {
		this.typedValue = typedValue;
	}
}
