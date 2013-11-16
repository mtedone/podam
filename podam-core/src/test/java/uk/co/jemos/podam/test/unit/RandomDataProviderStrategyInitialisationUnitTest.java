/**
 * 
 */
package uk.co.jemos.podam.test.unit;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import uk.co.jemos.podam.api.DataProviderStrategy;
import uk.co.jemos.podam.api.RandomDataProviderStrategy;

/**
 * It checks that the {@link RandomDataProviderStrategy} is initialised properly
 * 
 * @author tedonema
 * 
 */
public class RandomDataProviderStrategyInitialisationUnitTest {

	private DataProviderStrategy strategy;

	@Before
	public void init() {

		strategy = RandomDataProviderStrategy.getInstance();
		Assert.assertTrue(
				"The default number of collection elements should be "
						+ RandomDataProviderStrategy.DEFAULT_NBR_COLLECTION_ELEMENTS,
				strategy.getNumberOfCollectionElements() == RandomDataProviderStrategy.DEFAULT_NBR_COLLECTION_ELEMENTS);

		int aNumberOfCollectionElements = 3;
		strategy = RandomDataProviderStrategy
				.getInstance(aNumberOfCollectionElements);
		Assert.assertTrue(
				"The default number of collection elements should be "
						+ aNumberOfCollectionElements,
				strategy.getNumberOfCollectionElements() == aNumberOfCollectionElements);

	}

	@Test
	public void dummyTest() {
		Assert.assertTrue(true);
	}

}
