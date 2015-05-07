/**
 *
 */
package uk.co.jemos.podam.test.dto;

import uk.co.jemos.podam.test.dto.SimplePojoWithExcludeAnnotationToTestSetters.TestExclude;

/**
 * POJO to test when Podam sets a boolean.
 *
 * @author daivanov
 *
 */
public class BooleanPojo {
	private boolean value1;
	private Boolean value2;
	private Boolean value3;
	private Boolean value4;

	public BooleanPojo() {
	}

	public boolean isValue1() {
		return value1;
	}

	public void setValue1(boolean value1) {
		this.value1 = value1;
	}

	@TestExclude
	public Boolean isValue2() {
		return value2;
	}

	public void setValue2(Boolean value2) {
		this.value2 = value2;
	}

	public Boolean getValue3() {
		return value3;
	}

	public void setValue3(Boolean value3) {
		this.value3 = value3;
	}

	public Boolean getValue4() {
		return value4;
	}

	public void setValue4(Boolean value4) {
		this.value4 = value4;
	}
}
