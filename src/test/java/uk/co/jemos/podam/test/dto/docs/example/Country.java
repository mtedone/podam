/**
 * 
 */
package uk.co.jemos.podam.test.dto.docs.example;

import java.io.Serializable;

import uk.co.jemos.podam.common.PodamConstructor;
import uk.co.jemos.podam.common.PodamStringValue;

/**
 * A Country domain Model Object
 * 
 * @author mtedone
 * 
 */
public class Country implements Serializable {

	private static final long serialVersionUID = 1L;

	private final int countryId;

	private final String countryCode;

	private final String description;

	/**
	 * Full constructor.
	 * 
	 * @param countryId
	 *            The Country id
	 * @param countryCode
	 *            The country code
	 * @param description
	 *            The description
	 */
	@PodamConstructor(comment = "Immutable-like POJOs must be annotated with @PodamConstructor")
	public Country(int countryId,
			@PodamStringValue(length = 2) String countryCode, String description) {
		super();
		this.countryId = countryId;
		this.countryCode = countryCode;
		this.description = description;
	}

	/**
	 * @return the countryId
	 */
	public int getCountryId() {
		return countryId;
	}

	/**
	 * @return the countryCode
	 */
	public String getCountryCode() {
		return countryCode;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

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

		retValue.append("Country ( ").append("countryId = ").append(countryId)
				.append(TAB).append("countryCode = ").append(countryCode)
				.append(TAB).append("description = ").append(description)
				.append(TAB).append(" )");

		return retValue.toString();
	}

}
