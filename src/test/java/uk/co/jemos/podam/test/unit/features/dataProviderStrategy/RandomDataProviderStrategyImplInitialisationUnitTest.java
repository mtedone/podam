/**
 * 
 */
package uk.co.jemos.podam.test.unit.features.dataProviderStrategy;

import junit.framework.Assert;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Title;
import org.junit.Test;
import org.junit.runner.RunWith;
import uk.co.jemos.podam.api.*;
import uk.co.jemos.podam.common.AbstractConstructorComparator;
import uk.co.jemos.podam.common.AbstractMethodComparator;
import uk.co.jemos.podam.common.PodamConstants;
import uk.co.jemos.podam.test.dto.PojoWithMapsAndCollections;
import uk.co.jemos.podam.test.strategies.CustomRandomDataProviderStrategy;
import uk.co.jemos.podam.test.unit.AbstractPodamSteps;

import java.util.HashMap;
import java.util.Map;

/**
 * It checks that the {@link RandomDataProviderStrategyImpl} is initialised properly
 * 
 * @author Marco Tedone
 * 
 */
@RunWith(SerenityRunner.class)
public class RandomDataProviderStrategyImplInitialisationUnitTest extends AbstractPodamSteps {

	private DataProviderStrategy strategy;


	@Test
	@Title("The Random Data Provider Strategy should be initialised correctly and allow for changes in " +
			"the number of collection elements")
	public void randomDataProviderStrategyShouldBeInitialisedCorrectlyAndAllowForChangesInNbrOfCollectionElements() {

		DataProviderStrategy dataProviderStrategy =
				podamFactorySteps.givenARandomDataProviderStrategy();

		podamValidationSteps.theTwoObjectsShouldBeEqual(PodamConstants.DEFAULT_NBR_COLLECTION_ELEMENTS,
				dataProviderStrategy.getNumberOfCollectionElements(Object.class));

		int aNumberOfCollectionElements = 3;
		dataProviderStrategy.setDefaultNumberOfCollectionElements(aNumberOfCollectionElements);
		podamValidationSteps.theTwoObjectsShouldBeEqual(aNumberOfCollectionElements,
				dataProviderStrategy.getNumberOfCollectionElements(Object.class));

	}

	@Test
	@Title("Podam should create POJOs in accordance with custom data provider strategies")
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

		strategy = new RandomDataProviderStrategyImpl();
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

		strategy = new RandomDataProviderStrategyImpl();
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

		strategy = new RandomDataProviderStrategyImpl();
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

		strategy = new RandomDataProviderStrategyImpl();
		RandomDataProviderStrategy randomStrategy = (RandomDataProviderStrategy) strategy;
		AbstractMethodComparator comparator = randomStrategy.getMethodHeavyComparator();
		Assert.assertNotNull(comparator);
		randomStrategy.setMethodHeavyComparator(null);
		Assert.assertEquals(null, randomStrategy.getMethodHeavyComparator());
		randomStrategy.setMethodHeavyComparator(comparator);
		Assert.assertEquals(comparator, randomStrategy.getMethodHeavyComparator());
	}

}
