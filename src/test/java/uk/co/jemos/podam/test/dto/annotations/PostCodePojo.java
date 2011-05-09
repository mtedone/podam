/**
 * 
 */
package uk.co.jemos.podam.test.dto.annotations;

import java.io.Serializable;

import uk.co.jemos.podam.annotations.PodamAttributeStrategy;
import uk.co.jemos.podam.api.AttributeDataStrategy;
import uk.co.jemos.podam.test.strategies.PostCodeStrategy;

/**
 * POJO to test the attribute-level data strategy, through the use of
 * {@link AttributeDataStrategy} implementations.
 * 
 * @author mtedone
 * 
 */
public class PostCodePojo implements Serializable {

	// ------------------->> Constants
	private static final long serialVersionUID = 1L;
	// ------------------->> Instance / Static variables

	@PodamAttributeStrategy(PostCodeStrategy.class)
	private String postCode;

	// ------------------->> Constructors

	// ------------------->> Public methods

	// ------------------->> Getters / Setters

	/**
	 * @return the postCode
	 */
	public String getPostCode() {
		return postCode;
	}

	/**
	 * @param postCode
	 *            the postCode to set
	 */
	public void setPostCode(String postCode) {
		this.postCode = postCode;
	}

	// ------------------->> Private methods

	// ------------------->> equals() / hashcode() / toString()

	/**
	 * Constructs a <code>String</code> with all attributes in name = value
	 * format.
	 * 
	 * @return a <code>String</code> representation of this object.
	 */
	@Override
	public String toString() {
		final String TAB = "    ";

		StringBuilder retValue = new StringBuilder();

		retValue.append("PostCodePojo ( ").append("postCode = ")
				.append(postCode).append(TAB).append(" )");

		return retValue.toString();
	}

	// ------------------->> Inner classes

}
