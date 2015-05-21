/**
 * 
 */
package uk.co.jemos.podam.test.unit;

import junit.framework.Assert;
import org.junit.After;
import org.junit.Test;
import uk.co.jemos.podam.api.DataProviderStrategy;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;
import uk.co.jemos.podam.api.RandomDataProviderStrategy;
import uk.co.jemos.podam.common.AbstractConstructorComparator;
import uk.co.jemos.podam.common.AbstractMethodComparator;
import uk.co.jemos.podam.common.PodamConstants;
import uk.co.jemos.podam.test.dto.PojoWithMapsAndCollections;
import uk.co.jemos.podam.test.strategies.CustomRandomDataProviderStrategy;

import java.util.HashMap;
import java.util.Map;

/**
 * It checks that the {@link RandomDataProviderStrategy} is initialised properly
 * 
 * @author Marco Tedone
 * 
 */
public class RandomDataProviderStrategyInitialisationUnitTest {

	private DataProviderStrategy strategy;
	
	private static final int numOfCollectionElemsBackup
			= new RandomDataProviderStrategy().getNumberOfCollectionElements(Object.class);

	@After
	public void after() {
		strategy.setDefaultNumberOfCollectionElements(numOfCollectionElemsBackup);
	}

	@Test
	public void testNumberOfCollectionElementChange() {

		strategy = new RandomDataProviderStrategy();
		Assert.assertEquals(
				"An incorrect default number of collection elements",
				PodamConstants.DEFAULT_NBR_COLLECTION_ELEMENTS,
				strategy.getNumberOfCollectionElements(Object.class));

		int aNumberOfCollectionElements = 3;
		strategy.setDefaultNumberOfCollectionElements(aNumberOfCollectionElements);
		Assert.assertEquals(
				"An incorrect default number of collection elements",
				aNumberOfCollectionElements,
				strategy.getNumberOfCollectionElements(Object.class));

	}

	@Test
	public void testInitialization() {

		strategy = new RandomDataProviderStrategy();
		Assert.assertEquals(
				"An incorrect default number of collection elements",
				PodamConstants.DEFAULT_NBR_COLLECTION_ELEMENTS,
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

		strategy = new RandomDataProviderStrategy();
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

	@Test
	public void testRandomProviderStrategyForLong() {

		strategy = new CustomRandomDataProviderStrategy();
		PodamFactory factory = new PodamFactoryImpl(strategy);
		Map<?, ?> pojo =
				factory.manufacturePojo(HashMap.class, Long.class, String.class);

		Assert.assertNotNull("POJO manufacturing failed", pojo);
		Assert.assertEquals("Wrong map size",
				strategy.getNumberOfCollectionElements(String.class),
				pojo.size());

	}

	@Test
	public void testConstructorLightComparator() {

		strategy = new RandomDataProviderStrategy();
		RandomDataProviderStrategy randomStrategy = (RandomDataProviderStrategy) strategy;
		AbstractConstructorComparator comparator = randomStrategy.getConstructorLightComparator();
		Assert.assertNotNull(comparator);
		randomStrategy.setConstructorLightComparator(null);
		Assert.assertEquals(null, randomStrategy.getConstructorLightComparator());
		randomStrategy.setConstructorLightComparator(comparator);
		Assert.assertEquals(comparator, randomStrategy.getConstructorLightComparator());
	}

	@Test
	public void testConstructorHeavyComparator() {

		strategy = new RandomDataProviderStrategy();
		RandomDataProviderStrategy randomStrategy = (RandomDataProviderStrategy) strategy;
		AbstractConstructorComparator comparator = randomStrategy.getConstructorHeavyComparator();
		Assert.assertNotNull(comparator);
		randomStrategy.setConstructorHeavyComparator(null);
		Assert.assertEquals(null, randomStrategy.getConstructorHeavyComparator());
		randomStrategy.setConstructorHeavyComparator(comparator);
		Assert.assertEquals(comparator, randomStrategy.getConstructorHeavyComparator());
	}

	@Test
	public void testMethodLightComparator() {

		strategy = new RandomDataProviderStrategy();
		RandomDataProviderStrategy randomStrategy = (RandomDataProviderStrategy) strategy;
		AbstractMethodComparator comparator = randomStrategy.getMethodLightComparator();
		Assert.assertNotNull(comparator);
		randomStrategy.setMethodLightComparator(null);
		Assert.assertEquals(null, randomStrategy.getMethodLightComparator());
		randomStrategy.setMethodLightComparator(comparator);
		Assert.assertEquals(comparator, randomStrategy.getMethodLightComparator());
	}

	@Test
	public void testMethodHeavyComparator() {

		strategy = new RandomDataProviderStrategy();
		RandomDataProviderStrategy randomStrategy = (RandomDataProviderStrategy) strategy;
		AbstractMethodComparator comparator = randomStrategy.getMethodHeavyComparator();
		Assert.assertNotNull(comparator);
		randomStrategy.setMethodHeavyComparator(null);
		Assert.assertEquals(null, randomStrategy.getMethodHeavyComparator());
		randomStrategy.setMethodHeavyComparator(comparator);
		Assert.assertEquals(comparator, randomStrategy.getMethodHeavyComparator());
	}

}
