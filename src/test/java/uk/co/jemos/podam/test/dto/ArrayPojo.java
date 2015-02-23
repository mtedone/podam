/**
 *
 */
package uk.co.jemos.podam.test.dto;

/**
 * POJO to test when Podam sets an array.
 *
 * @author tedonema
 *
 */
public class ArrayPojo {

	private String[] myStringArray = new String[10];

	private Object[] myObjectArray;

	/**
	 * Default constructor.
	 */
	public ArrayPojo() {

	}

	/**
	 * Gets the array.
	 *
	 * @return An array of Strings
	 */
	public String[] getMyStringArray() {
		return myStringArray;
	}

	/**
	 * Sets the array.
	 *
	 * @param myStringArray
	 */
	public void setMyStringArray(String[] myStringArray) {
		this.myStringArray = myStringArray;
	}

	/**
	 * Gets the array.
	 *
	 * @return An array of Objects
	 */
	public Object[] getMyObjectArray() {
		return myObjectArray;
	}

	/**
	 * Sets the array.
	 *
	 * @param myObjectArray
	 */
	public void setMyObjectArray(Object[] myObjectArray) {
		this.myObjectArray = myObjectArray;
	}
}
