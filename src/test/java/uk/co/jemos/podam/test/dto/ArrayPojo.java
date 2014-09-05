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

	/**
	 * Default constructor.
	 */
	public ArrayPojo() {

	}

	/**
	 * Gets the array.
	 *
	 * @return
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

}
