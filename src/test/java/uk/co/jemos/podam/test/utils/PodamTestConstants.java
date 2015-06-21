/**
 * 
 */
package uk.co.jemos.podam.test.utils;

/**
 * Utility class to support testing
 * 
 * @author mtedone
 * 
 */
public class PodamTestConstants {

	// ------------------->> Constants

	/** The minimum value for numeric custom annotations */
	public static final int NUMBER_INT_MIN_VALUE = 0;

	/** A max value for numeric custom annotations */
	public static final int NUMBER_INT_ONE_HUNDRED = 100;

	/** The value 10 for numeric custom annotations */
	public static final int NUMBER_INT_TEN = 10;

	/** A maximum value for numeric custom annotations */
	public static final int NUMBER_INT_MAX_VALUE = 1000;

	/** The minimum value for float-type numeric custom annotations */
	public static final float NUMBER_FLOAT_MIN_VALUE = 0.0f;

	/** A max value for float-type numeric custom annotations */
	public static final float NUMBER_FLOAT_ONE_HUNDRED = 100.0f;

	/** A maximum value for float-type numeric custom annotations */
	public static final float NUMBER_FLOAT_MAX_VALUE = 1000.0f;

	/** The minimum value for double-type numeric custom annotations */
	public static final double NUMBER_DOUBLE_MIN_VALUE = 0.0;

	/** A max value for float-type numeric custom annotations */
	public static final double NUMBER_DOUBLE_ONE_HUNDRED = 100.0;

	/** A maximum value for float-type numeric custom annotations */
	public static final double NUMBER_DOUBLE_MAX_VALUE = 1000.0;

	/** A precise value for the Podam String annotation */
	public static final String STR_ANNOTATION_PRECISE_VALUE = "preciseStringValue";

	/** The length of the string in the POJO to test String value annotation */
	public static final int STR_ANNOTATION_TWENTY_LENGTH = 20;

	/** The number of elements in an annotated collection */
	public static final int ANNOTATION_COLLECTION_NBR_ELEMENTS = 5;

	/** A precise byte value to test annotations */
	public static final String BYTE_PRECISE_VALUE = "12";

	/** A precise short value to test annotations */
	public static final String SHORT_PRECISE_VALUE = "17";

	/** A precise character value for testing */
	public static final char CHAR_PRECISE_VALUE = 'a';

	/** A precise integer value for testing */
	public static final String INTEGER_PRECISE_VALUE = "145241";

	/** A precise long value for testing */
	public static final String LONG_PRECISE_VALUE = "12345678912";

	/** A precise float value for testing */
	public static final String FLOAT_PRECISE_VALUE = "123456f";

	/** A precise double value for testing */
	public static final String DOUBLE_PRECISE_VALUE = "10.1245";

	/** A post code value for testing. */
	public static final String POST_CODE = "W1E X9P";

	// ------------------->> Instance / Static variables

	// ------------------->> Constructors

	/** Non instantiable class */
	private PodamTestConstants() {
		throw new AssertionError();
	}

}
