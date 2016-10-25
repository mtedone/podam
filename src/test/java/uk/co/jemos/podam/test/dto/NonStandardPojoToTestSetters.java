/**
 * 
 */
package uk.co.jemos.podam.test.dto;

import java.io.Serializable;

/**
 * @author daivanov
 * 
 */
public class NonStandardPojoToTestSetters implements Serializable {

	private static final long serialVersionUID = 1L;

	private String stringField;

	private int intField;

	public String whatIsStringField() {
		return stringField;
	}

	public NonStandardPojoToTestSetters withStringField(String stringField) {
		this.stringField = stringField;
		return this;
	}

	public int whatIsIntField() {
		return intField;
	}

	public NonStandardPojoToTestSetters withIntField(int intField) {
		this.intField = intField;
		return this;
	}
}
