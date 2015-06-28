/**
 * 
 */
package uk.co.jemos.podam.common;



/**
 * PODAM constants.
 * 
 * @author mtedone
 * 
 * @since 1.0.0
 * 
 */
public final class PodamConstants {

	// ------------------->> Constants

	// ------------------->> Instance / Static variables

	// ------------------->> Constructors

	/**
	 * The default string length that Podam will assign to an annotated
	 * attribute
	 */
	public static final int STR_DEFAULT_LENGTH = 10;

	/** The default number of elements for a collection-type element */
	public static final int DEFAULT_NBR_COLLECTION_ELEMENTS = 5;

	/** The default encoding for Strings */
	public static final String STR_DEFAULT_ENCODING = "UTF-8";
    public static final String HEADER_NAME = "type";
    public static final String SPRING_ROOT_CONFIG_LOCATION = "META-INF/spring/podam-config.xml";

    /** Non-instantiable constructor */
	private PodamConstants() {
		throw new AssertionError("Non instantiable");
	}

	// ------------------->> Public methods

	// ------------------->> Getters / Setters

	// ------------------->> Private methods

	// ------------------->> equals() / hashcode() / toString()

	// ------------------->> Inner classes

}
