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

	/**
	 * The default string length that Podam will assign to an annotated
	 * attribute
	 */
	public static final int STR_DEFAULT_LENGTH = 10;

	/** The default number of elements for a collection-type element. */
	public static final int DEFAULT_NBR_COLLECTION_ELEMENTS = 5;

	/** The default encoding for Strings. */
	public static final String STR_DEFAULT_ENCODING = "UTF-8";

    /** The Spring integration header name which will drive the type manufacturing. */
    public static final String HEADER_NAME = "type";

    /** The Podam Spring rool configuration file name. */
    public static final String SPRING_ROOT_CONFIG_LOCATION = "META-INF/spring/podam-config.xml";

    /** A String used for messages. */
    public static final String THE_ANNOTATION_VALUE_STR = "The annotation value: ";

    /** Non-instantiable constructor */
	private PodamConstants() {
		throw new AssertionError("Non instantiable");
	}


}
