package uk.co.jemos.podam.test.dto;

/**
 * Pojo typed class
 * 
 * @author daivanov
 *
 */
public class ExtendedTypedClassPojo<T> extends SimpleTypedClassPojo<Long> {

	private T typedValue2;

	/**
	 * @return the typedValue2
	 */
	public T getTypedValue2() {
		return typedValue2;
	}

	/**
	 * @param typedValue2
	 *            the typedList to set
	 */
	public void setTypedValue2(T typedValue2) {
		this.typedValue2 = typedValue2;
	}
}
