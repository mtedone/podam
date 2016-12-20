/**
 *
 */
package uk.co.jemos.podam.api;

/**
 * Singleton implementation of a {@link AbstractClassInfoStrategy}
 * <p>
 * This singleton implementation performs class attribute introspection
 * on the basis of class declared fields
 * </p>
 *
 * @author daivanov
 *
 * @since 5.1.0
 *
 */

public final class DefaultClassInfoStrategy extends
		AbstractClassInfoStrategy {

	// ------------------->> Constants

	/** The singleton instance of this implementation */
	private static final DefaultClassInfoStrategy SINGLETON = new DefaultClassInfoStrategy();

	// ------------------->> Instance / Static variables

	// ------------------->> Constructors

	/**
	 * Implementation of the Singleton pattern
	 */
	private DefaultClassInfoStrategy() {
		super();
	}

	// ------------------->> Public methods

	/**
	 * Implementation of the Singleton pattern
	 *
	 * @return A singleton instance of this class
	 */
	public static DefaultClassInfoStrategy getInstance() {
		return SINGLETON;
	}

	// ------------------->> Getters / Setters

	// ------------------->> Private methods

	// ------------------->> equals() / hashcode() / toString()

	// ------------------->> Inner classes

}
