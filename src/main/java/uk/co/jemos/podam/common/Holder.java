package uk.co.jemos.podam.common;

/**
 * Object to pass objects by reference
 * 
 * @param <T> type of objects to be hold by this class
 * @author daivanov
 * 
 */
public class Holder<T> {

	/** Object to hold */
	private T value;

	/**
	 * Default constructor
	 */
	public Holder() {
	}

	/**
	 * Default constructor
	 * @param value value to set
	 */
	public Holder(T value) {
		this.value = value;
	}

	/**
	 * Setter for holder's value
	 * @param value value to set
	 */
	public void setValue(T value) {
		this.value = value;
	}

	/**
	 * Getter for holder's value
	 * @return value
	 */
	public T getValue() {
		return this.value;
	}

}
