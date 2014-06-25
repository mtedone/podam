/**
 * 
 */
package uk.co.jemos.podam.test.dto.annotations;

import java.io.Serializable;

import uk.co.jemos.podam.common.PodamStrategyValue;
import uk.co.jemos.podam.test.strategies.WrongTypeStrategy;

/**
 * POJO to test that when a wrong type is given to
 * {@link PodamStrategyValue} an exception will be thrown.
 * 
 * @author mtedone
 * 
 */
public class StringWithWrongStrategyTypePojo implements Serializable {

	// ------------------->> Constants
	private static final long serialVersionUID = 1L;
	// ------------------->> Instance / Static variables

	@PodamStrategyValue(value = WrongTypeStrategy.class)
	private String postCodeDestinedToFail;

	// ------------------->> Constructors

	// ------------------->> Public methods

	// ------------------->> Getters / Setters

	/**
	 * @return the postCodeDestinedToFail
	 */
	public String getPostCodeDestinedToFail() {
		return postCodeDestinedToFail;
	}

	/**
	 * @param postCodeDestinedToFail
	 *            the postCodeDestinedToFail to set
	 */
	public void setPostCodeDestinedToFail(String postCodeDestinedToFail) {
		this.postCodeDestinedToFail = postCodeDestinedToFail;
	}

	// ------------------->> Private methods

	// ------------------->> equals() / hashcode() / toString()

	// ------------------->> Inner classes

}
