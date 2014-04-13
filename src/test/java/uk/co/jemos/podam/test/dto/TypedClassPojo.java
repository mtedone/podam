package uk.co.jemos.podam.test.dto;

import java.util.List;

/**
 * Pojo typed class
 * 
 * @author daivanov
 *
 */
public class TypedClassPojo<T> {

	private T typedValue;

	private List<T> typedList;

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

	/**
	 * @return the typedList
	 */
	public List<T> getTypedList() {
		return typedList;
	}

	/**
	 * @param typedList
	 *            the typedList to set
	 */
	public void setTypedList(List<T> typedList) {
		this.typedList = typedList;
	}
}
