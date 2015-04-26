/**
 * 
 */
package uk.co.jemos.podam.test.utils;

import java.util.Calendar;
import java.util.Collection;
import java.util.Map;
import java.util.TimeZone;
import java.util.Map.Entry;

import org.junit.Assert;

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

	/**
	 * Asserts collection is non-empty and elements are of certain type
	 * 
	 * @param collection
	 *            Collection to examine
	 * @param elementType
	 *            Element type to ensure
	 */
	public static void assertCollectionElementsType(Collection<?> collection, Class<?> elementType) {
		Assert.assertNotNull("List should not be null", collection);
		Assert.assertFalse("List should not be empty", collection.isEmpty());
		for (Object element : collection) {
			Assert.assertEquals("Wrong element type", elementType, element.getClass());
		}
	}

	/**
	 * Asserts map is non-empty and key and values are of certain type
	 *
	 * @param map
	 *           Map to examine
	 * @param keyType
	 *           Key type to ensure
	 * @param valueType
	 *           Value type to ensure
	 */
	public static void assertMapElementsType(Map<?, ?> map, Class<?> keyType, Class<?> valueType) {
		Assert.assertNotNull("Map should not be null", map);
		Assert.assertFalse("Map should not be empty", map.isEmpty());
		for (Entry<?, ?> element : map.entrySet()) {
			Assert.assertEquals("Wrong key type", keyType, element.getKey().getClass());
			Assert.assertEquals("Wrong value type", valueType, element.getValue().getClass());
		}
	}

	/**
	 * Asserts array is non-empty and elements are of certain type
	 *
	 * @param array
	 *           Array to examine
	 * @param elementType
	 *           Element type to ensure
	 */
	public static void assertArrayElementsType(Object[] array, Class<?> elementType) {
		Assert.assertNotNull("Array should not be null", array);
		Assert.assertTrue("Array should not be empty", array.length > 0);
		for (Object element : array) {
			Assert.assertEquals("Wrong element type", elementType, element.getClass());
		}
	}

	// ------------------->> Getters / Setters

	// ------------------->> Private methods

	// ------------------->> equals() / hashcode() / toString()

	// ------------------->> Inner classes

}
