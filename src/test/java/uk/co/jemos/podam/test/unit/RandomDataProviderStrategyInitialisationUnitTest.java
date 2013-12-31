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
 * @author Marco Tedone
 * 
 */
public class RandomDataProviderStrategyInitialisationUnitTest {

	private DataProviderStrategy strategy;

	@Before
	public void init() {

		strategy = RandomDataProviderStrategy.getInstance();
		Assert.assertEquals(
				"An incorrect default number of collection elements",
				RandomDataProviderStrategy.DEFAULT_NBR_COLLECTION_ELEMENTS,
				strategy.getNumberOfCollectionElements(Object.class));

		int aNumberOfCollectionElements = 3;
		strategy = RandomDataProviderStrategy
				.getInstance(aNumberOfCollectionElements);
		Assert.assertEquals(
				"An incorrect default number of collection elements",
				aNumberOfCollectionElements,
				strategy.getNumberOfCollectionElements(Object.class));

	}

	@Test
	public void dummyTest() {
		Assert.assertTrue(true);
	}

}
