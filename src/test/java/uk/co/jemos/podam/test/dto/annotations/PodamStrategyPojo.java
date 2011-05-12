/**
 * 
 */
package uk.co.jemos.podam.test.dto.annotations;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import uk.co.jemos.podam.annotations.PodamCollection;
import uk.co.jemos.podam.annotations.PodamStrategyValue;
import uk.co.jemos.podam.api.AttributeDataStrategy;
import uk.co.jemos.podam.test.strategies.MyBirthdayStrategy;
import uk.co.jemos.podam.test.strategies.PostCodeStrategy;

/**
 * POJO to test the attribute-level data strategy, through the use of
 * {@link AttributeDataStrategy} implementations.
 * 
 * @author mtedone
 * 
 */
public class PodamStrategyPojo implements Serializable {

	// ------------------->> Constants
	private static final long serialVersionUID = 1L;
	// ------------------->> Instance / Static variables

	@PodamStrategyValue(PostCodeStrategy.class)
	private String postCode;

	@PodamStrategyValue(MyBirthdayStrategy.class)
	private Calendar myBirthday;

	@PodamCollection(nbrElements = 2, elementStrategy = MyBirthdayStrategy.class)
	private List<Calendar> myBirthdays = new ArrayList<Calendar>();

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
	 * @return the myBirthday
	 */
	public Calendar getMyBirthday() {
		return myBirthday;
	}

	/**
	 * @param myBirthday
	 *            the myBirthday to set
	 */
	public void setMyBirthday(Calendar myBirthday) {
		this.myBirthday = myBirthday;
	}

	/**
	 * @return the myBirthdays
	 */
	public List<Calendar> getMyBirthdays() {
		return myBirthdays;
	}

	/**
	 * @param myBirthdays
	 *            the myBirthdays to set
	 */
	public void setMyBirthdays(List<Calendar> myBirthdays) {
		this.myBirthdays = myBirthdays;
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

		retValue.append("PodamStrategyPojo ( ").append("postCode = ")
				.append(postCode).append(TAB).append("myBirthday = ")
				.append(myBirthday).append(TAB).append("myBirthdays = ")
				.append(myBirthdays).append(TAB).append(" )");

		return retValue.toString();
	}

	// ------------------->> Inner classes

}
