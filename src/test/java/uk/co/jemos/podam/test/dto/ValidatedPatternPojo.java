/**
 *
 */
package uk.co.jemos.podam.test.dto;


import javax.validation.constraints.Pattern;

/**
 * POJO to test bean validation API
 *
 * @author daivanov
 *
 */
public class ValidatedPatternPojo {

	@Pattern(regexp = "^[0-9]{1,45}$")
	private String number;

	@Pattern(regexp = "^[a-zA-Z0-9_]*$")
	private String identifier;

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getIdentifier() {
		return identifier;
	}

	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}
}
