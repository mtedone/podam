/**
 * 
 */
package uk.co.jemos.podam.test.unit;

import junit.framework.Assert;

import org.junit.Test;

import uk.co.jemos.podam.api.DataProviderStrategy;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;
import uk.co.jemos.podam.api.RandomDataProviderStrategy;
import uk.co.jemos.podam.test.dto.PojoWithMapsAndCollections;
import uk.co.jemos.podam.test.strategies.CustomRandomDataProviderStrategy;

/**
 * It checks that the {@link RandomDataProviderStrategy} is initialised properly
 * 
 * @author Marco Tedone
 * 
 */
public class RandomDataProviderStrategyInitialisationUnitTest {

	private DataProviderStrategy strategy;

	@Test
	public void testInitialization() {

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
	public void testRandomDataProviderStrategy() {

		strategy = RandomDataProviderStrategy.getInstance();
		PodamFactory factory = new PodamFactoryImpl(strategy);
		PojoWithMapsAndCollections pojo =
				factory.manufacturePojo(PojoWithMapsAndCollections.class);

		Assert.assertNotNull("POJO manufacturing failed", pojo);
		Assert.assertNotNull("Array is null", pojo.getArray());
		Assert.assertEquals(
				strategy.getNumberOfCollectionElements(Object.class),
				pojo.getArray().length);
		Assert.assertNotNull("List is null", pojo.getList());
		Assert.assertEquals(
				strategy.getNumberOfCollectionElements(Object.class),
				pojo.getList().size());
		Assert.assertNotNull("Map is null", pojo.getMap());
		Assert.assertEquals(
				strategy.getNumberOfCollectionElements(Object.class),
				pojo.getMap().size());

	}

	@Test
	public void testCustomRandomDataProviderStrategy() {

		strategy = new CustomRandomDataProviderStrategy();
		PodamFactory factory = new PodamFactoryImpl(strategy);
		PojoWithMapsAndCollections pojo =
				factory.manufacturePojo(PojoWithMapsAndCollections.class);

		Assert.assertNotNull("POJO manufacturing failed", pojo);
		Assert.assertNotNull("Array is null", pojo.getArray());
		Assert.assertEquals(2, pojo.getArray().length);
		Assert.assertNotNull("List is null", pojo.getList());
		Assert.assertEquals(3, pojo.getList().size());
		Assert.assertNotNull("Map is null", pojo.getMap());
		Assert.assertEquals(4, pojo.getMap().size());

	}

}
