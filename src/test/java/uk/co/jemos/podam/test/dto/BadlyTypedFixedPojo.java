/**
 *
 */
package uk.co.jemos.podam.test.dto;

/**
 * POJO to test class with setters for strings, which are being
 * then parsed to some other types. This creates problems for PODAM
 *
 * @author daivanov
 *
 */
public class BadlyTypedFixedPojo extends BadlyTypedPojo {

	/**
	 * We created guide for PODAM what is real type of field to set
	 * @param value integer value to set
	 */
	public void setValue(Integer value) {
		setValue(value.toString());
	}

}
