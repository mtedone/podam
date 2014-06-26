/**
 *
 */
package uk.co.jemos.podam.api;

/**
 * Default implementation of a {@link DataProviderStrategy}
 * <p>
 * This default implementation returns values based on a random generator.
 * <b>Don't use this implementation if you seek deterministic values</b>
 * </p>
 *
 * <p>
 * All values returned by this implementation are <b>different from zero</b>.
 * </p>
 *
 * <p>
 * This implementation is a Singleton
 * </p>
 *
 * @author mtedone
 *
 * @since 1.0.0
 *
 */

public final class RandomDataProviderStrategy extends
		AbstractRandomDataProviderStrategy {

	// ------------------->> Constants

	/** The singleton instance of this implementation */
	private static final RandomDataProviderStrategy SINGLETON = new RandomDataProviderStrategy();

	// ------------------->> Instance / Static variables

	// ------------------->> Constructors

	/**
	 * Implementation of the Singleton pattern
	 */
	private RandomDataProviderStrategy() {
		super();
	}

	// ------------------->> Public methods

	/**
	 * Implementation of the Singleton pattern
	 *
	 * @return A singleton instance of this class
	 */
	public static RandomDataProviderStrategy getInstance() {
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
	public static RandomDataProviderStrategy getInstance(
			int nbrCollectionElements) {

		SINGLETON.setNumberOfCollectionElements(nbrCollectionElements);
		return SINGLETON;

	}

	// ------------------->> Getters / Setters

	// ------------------->> Private methods

	// ------------------->> equals() / hashcode() / toString()

	// ------------------->> Inner classes

}
