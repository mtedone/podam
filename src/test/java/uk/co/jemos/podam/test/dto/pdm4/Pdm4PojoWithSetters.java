package uk.co.jemos.podam.test.dto.pdm4;

import java.io.InputStream;

/**
 * Pojo to test to test multiple constructors and setters
 * 
 * @author divanov
 * 
 */
public class Pdm4PojoWithSetters {

	private String value;
	
	public Pdm4PojoWithSetters(InputStream inputStream) {
		if (inputStream == null) {
			throw new NullPointerException("Invalid input stream provided");
		}
	}

	public Pdm4PojoWithSetters(int num) {
		value = String.valueOf(num);
	}
	
	public void setValue(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

	@Override
	public String toString() {
		return String.format("{value: '%s'}", value);
	}
}
