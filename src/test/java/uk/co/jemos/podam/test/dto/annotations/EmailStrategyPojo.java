/**
 * 
 */
package uk.co.jemos.podam.test.dto.annotations;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;

/**
 * POJO to test the attribute-level data strategy, through the use of
 * {@link AttributeStrategy} implementations.
 * 
 * @author daivanov
 * 
 */
public class EmailStrategyPojo {

	@Email
    @NotEmpty
	private String email;

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email
	 *            the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

}
