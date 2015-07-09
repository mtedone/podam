/**
 *
 */
package uk.co.jemos.podam.api;

import net.jcip.annotations.ThreadSafe;

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
@ThreadSafe
public final class RandomDataProviderStrategyImpl extends
		AbstractRandomDataProviderStrategy {

	// ------------------->> Constants

	// ------------------->> Instance / Static variables

	// ------------------->> Constructors

	/**
	 * Implementation of the Singleton pattern
	 */
	public RandomDataProviderStrategyImpl() {
		super();
		setMemoization(false);
	}

	// ------------------->> Public methods

	/**
	 * Other factory method which assigns a default number of collection
	 * elements before returning the singleton.
	 *
	 * @param nbrCollectionElements
	 *            The number of collection elements
	 * @return The Singleton, set with the number of collection elements set as
	 *         parameter
	 */
	public static RandomDataProviderStrategyImpl getInstance(
			int nbrCollectionElements) {

		RandomDataProviderStrategyImpl strategy = new RandomDataProviderStrategyImpl();
				strategy.setDefaultNumberOfCollectionElements(nbrCollectionElements);
		return strategy;

	}

	// ------------------->> Getters / Setters

	// ------------------->> Private methods

	// ------------------->> equals() / hashcode() / toString()

	// ------------------->> Inner classes

}
