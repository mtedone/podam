/**
 * 
 */
package uk.co.jemos.podam.test.dto.thirdparty;

import java.io.Serializable;

import org.joda.time.LocalDate;

/**
 * Joda Time POJO to test whether PODAM sets its state correctly.
 * 
 * @author mtedone
 * 
 */
public class JodaTimePojo implements Serializable {

	// ------------------->> Constants

	private static final long serialVersionUID = 1L;
	// ------------------->> Instance / Static variables

	private LocalDate localDate;

	// ------------------->> Constructors

	// ------------------->> Public methods

	// ------------------->> Getters / Setters

	/**
	 * @return the localDate
	 */
	public LocalDate getLocalDate() {
		return localDate;
	}

	/**
	 * @param localDate
	 *            the localDate to set
	 */
	public void setLocalDate(LocalDate localDate) {
		this.localDate = localDate;
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

		retValue.append("JodaTimePojo ( ").append("localDate = ")
				.append(localDate).append(TAB).append(" )");

		return retValue.toString();
	}

	// ------------------->> Inner classes

}
