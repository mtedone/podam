/**
 * 
 */
package uk.co.jemos.podam.test.utils;

import java.util.Calendar;
import java.util.TimeZone;

/**
 * Test utility class
 * 
 * @author mtedone
 * 
 */
public class PodamTestUtils {

	// ------------------->> Constants

	// ------------------->> Instance / Static variables

	// ------------------->> Constructors

	/** Non instantiable class */
	private PodamTestUtils() {
	}

	// ------------------->> Public methods

	/**
	 * Returns a Calendar with my birthday date.
	 * 
	 * @return A Calendar with my birthday date.
	 */
	public static Calendar getMyBirthday() {
		Calendar myBirthday = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
		myBirthday.set(Calendar.DAY_OF_MONTH, 20);
		myBirthday.set(Calendar.MONTH, 5);
		myBirthday.set(Calendar.YEAR, 1972);
		myBirthday.set(Calendar.HOUR_OF_DAY, 2);
		myBirthday.set(Calendar.MINUTE, 0);
		myBirthday.set(Calendar.SECOND, 0);
		myBirthday.set(Calendar.MILLISECOND, 0);

		return myBirthday;
	}

}
