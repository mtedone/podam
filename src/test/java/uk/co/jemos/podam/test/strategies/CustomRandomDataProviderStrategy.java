package uk.co.jemos.podam.test.strategies;

import uk.co.jemos.podam.api.AbstractRandomDataProviderStrategy;

/**
 * Random data provider strategy with variable map and collection length
 *
 * @author daivanov
 *
 */
public class CustomRandomDataProviderStrategy extends AbstractRandomDataProviderStrategy {

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int getNumberOfCollectionElements(Class<?> type) {

		int number;
		if (String[].class.isAssignableFrom(type)) {
			number = 2;
		} else if (Boolean.class.isAssignableFrom(type)) {
			number = 3;
		} else if (Long.class.isAssignableFrom(type)) {
			number = 4;
		} else if (Integer.class.isAssignableFrom(type)) {
			number = 5;
		} else {
			number = super.getNumberOfCollectionElements(type);
		}
		return number;
	}
}
