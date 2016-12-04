/**
 * 
 */
package uk.co.jemos.podam.common;


import java.lang.reflect.Type;

/**
 * PODAM constants.
 * 
 * @author mtedone
 * 
 * @since 1.0.0
 * 
 */
public interface PodamConstants {

	/**
	 * The default string length that Podam will assign to an annotated
	 * attribute
	 */
	public static final int STR_DEFAULT_LENGTH = 10;

	/** The default number of elements for a collection-type element. */
	public static final int DEFAULT_NBR_COLLECTION_ELEMENTS = 5;

	/** The default encoding for Strings. */
	public static final String STR_DEFAULT_ENCODING = "UTF-8";

    /** The Podam Spring rool configuration file name. */
    public static final String SPRING_ROOT_CONFIG_LOCATION = "META-INF/spring/podam-config.xml";

    /** A String used for messages. */
    public static final String THE_ANNOTATION_VALUE_STR = "The annotation value: ";

    /** An empty array of Types. */
    public static final Type[] NO_TYPES = new Type[0];

    /** An empty object array. */
    public static final Object[] NO_ARGS = new Object[0];

}
