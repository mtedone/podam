/**
 * 
 */
package uk.co.jemos.podam.exceptions;

/**
 * @author mtedone
 * 
 */
public class PodamMockeryException extends RuntimeException {

	// ------------------->> Constants

	private static final long serialVersionUID = 1L;

	// ------------------->> Instance / Static variables

	// ------------------->> Constructors

	/**
	 * Full constructor
	 * 
	 * @param message
	 *            The exception message
	 * @param cause
	 *            The error which caused this exception to be thrown
	 */
	public PodamMockeryException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * Constructor with message
	 * 
	 * @param message
	 *            The exception message
	 */
	public PodamMockeryException(String message) {
		super(message);
	}
	// ------------------->> Public methods

	// ------------------->> Getters / Setters

	// ------------------->> Private methods

	// ------------------->> equals() / hashcode() / toString()

	// ------------------->> Inner classes

}
