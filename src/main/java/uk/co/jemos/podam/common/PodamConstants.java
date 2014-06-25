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
	public static final int ANNOTATION_COLLECTION_DEFAULT_NBR_ELEMENTS = 1;

	/** The default encoding for Strings */
	public static final String STR_DEFAULT_ENCODING = "UTF-8";

	/** The name of the {@link AttributeStrategy} interface */
	public static final String PODAM_ATTRIBUTE_STRATEGY_METHOD_NAME = "getValue";

	/** Non-instantiable constructor */
	private PodamConstants() {
		throw new AssertionError();
	}

	// ------------------->> Public methods

	// ------------------->> Getters / Setters

	// ------------------->> Private methods

	// ------------------->> equals() / hashcode() / toString()

	// ------------------->> Inner classes

}
