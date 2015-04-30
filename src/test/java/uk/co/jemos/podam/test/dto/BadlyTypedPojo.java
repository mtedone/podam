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
public class BadlyTypedPojo {
	private Integer value;

	public Integer getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = Integer.valueOf(value);
	}

}
