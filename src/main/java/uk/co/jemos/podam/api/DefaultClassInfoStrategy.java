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

	/**
	 * Other factory method which assigns a default number of collection
	 * elements before returning the singleton.
	 *
	 * @param nbrCollectionElements
	 *            The number of collection elements
	 * @return The Singleton, set with the number of collection elements set as
	 *         parameter
	 */
	public static DefaultClassInfoStrategy getInstance(
			int nbrCollectionElements) {

		return SINGLETON;

	}

	// ------------------->> Getters / Setters

	// ------------------->> Private methods

	// ------------------->> equals() / hashcode() / toString()

	// ------------------->> Inner classes

}
