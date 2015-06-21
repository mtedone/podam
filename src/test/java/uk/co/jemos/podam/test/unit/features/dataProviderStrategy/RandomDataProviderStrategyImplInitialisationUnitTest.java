/**
 * 
 */
package uk.co.jemos.podam.test.unit.features.dataProviderStrategy;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Title;
import org.junit.Test;
import org.junit.runner.RunWith;
import uk.co.jemos.podam.api.AbstractRandomDataProviderStrategy;
import uk.co.jemos.podam.api.DataProviderStrategy;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.RandomDataProviderStrategyImpl;
import uk.co.jemos.podam.common.AbstractConstructorComparator;
import uk.co.jemos.podam.common.AbstractMethodComparator;
import uk.co.jemos.podam.common.PodamConstants;
import uk.co.jemos.podam.test.dto.PojoWithMapsAndCollections;
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
	public void podamShouldCreatePojosInAccordanceWithCustomDataProviderStrategies() throws Exception {

		DataProviderStrategy strategy = podamFactorySteps.givenACustomRandomDataProviderStrategy();
		PodamFactory podamFactory = podamFactorySteps.givenAPodamFactoryWithCustomDataProviderStrategy(strategy);
		PojoWithMapsAndCollections pojo =
				podamInvocationSteps.whenIInvokeTheFactoryForClass(PojoWithMapsAndCollections.class, podamFactory);
		podamValidationSteps.theObjectShouldNotBeNull(pojo);
		podamValidationSteps.theArrayOfTheGivenTypeShouldNotBeNullOrEmptyAndContainExactlyTheGivenNumberOfElements(
				pojo.getArray(), 2);
		podamValidationSteps.theCollectionShouldNotBeNullOrEmpty(pojo.getList());
		podamValidationSteps.theCollectionShouldHaveExactlyTheExpectedNumberOfElements(pojo.getList(), 3);
		podamValidationSteps.theMapShouldNotBeNullOrEmpty(pojo.getMap());
		podamValidationSteps.theMapShouldHaveExactlyTheExpectedNumberOfElements(pojo.getMap(), 4);

	}

	@Test
	@Title("Podam should correctly generate HashMaps with Long as key type")
	public void podamShouldCorrectGenerateHashMapsWithLongAsKeyType() throws Exception {

		DataProviderStrategy strategy = podamFactorySteps.givenACustomRandomDataProviderStrategy();
		PodamFactory podamFactory = podamFactorySteps.givenAPodamFactoryWithCustomDataProviderStrategy(strategy);
		Map<?, ?> pojo =
				podamInvocationSteps.whenIInvokeTheFactoryForGenericTypeWithSpecificType(
						HashMap.class, podamFactory, Long.class, String.class);

		podamValidationSteps.theObjectShouldNotBeNull(pojo);
		podamValidationSteps.theTwoObjectsShouldBeEqual(strategy.getNumberOfCollectionElements(
				String.class), pojo.size());

	}

	@Test
	@Title("Creating a Random Data Provider Strategy should create a constructor light comparator")
	public void creatingARandomDataProviderStrategyShouldCreateAConstructorLightComparator() throws Exception {

		AbstractRandomDataProviderStrategy randomStrategy =
				(AbstractRandomDataProviderStrategy) podamFactorySteps.givenARandomDataProviderStrategy();
		AbstractConstructorComparator comparator = randomStrategy.getConstructorLightComparator();
		podamValidationSteps.theObjectShouldNotBeNull(comparator);
		randomStrategy.setConstructorLightComparator(null);
		podamValidationSteps.theTwoObjectsShouldBeEqual(null, randomStrategy.getConstructorLightComparator());
		randomStrategy.setConstructorLightComparator(comparator);
		podamValidationSteps.theTwoObjectsShouldBeEqual(comparator, randomStrategy.getConstructorLightComparator());
	}

	@Test
	@Title("Creating a Random Data Provider Strategy should create a constructor heavy comparator")
	public void creatingARandomDataProviderStrategyShouldCreateAConstructorHeavyComparator() throws Exception {

		AbstractRandomDataProviderStrategy randomStrategy =
				(AbstractRandomDataProviderStrategy) podamFactorySteps.givenARandomDataProviderStrategy();
		AbstractConstructorComparator comparator = randomStrategy.getConstructorHeavyComparator();
		podamValidationSteps.theObjectShouldNotBeNull(comparator);
		randomStrategy.setConstructorHeavyComparator(null);
		podamValidationSteps.theTwoObjectsShouldBeEqual(null, randomStrategy.getConstructorHeavyComparator());
		randomStrategy.setConstructorHeavyComparator(comparator);
		podamValidationSteps.theTwoObjectsShouldBeEqual(comparator, randomStrategy.getConstructorHeavyComparator());
	}

	@Test
	@Title("Creating a Random Data Provider Strategy should create a Method light comparator")
	public void creatingARandomDataProviderStrategyShouldCreateAMethodLightComparator() throws Exception {

		AbstractRandomDataProviderStrategy randomStrategy =
				(AbstractRandomDataProviderStrategy) podamFactorySteps.givenARandomDataProviderStrategy();
		AbstractMethodComparator comparator = randomStrategy.getMethodLightComparator();
		podamValidationSteps.theObjectShouldNotBeNull(comparator);
		randomStrategy.setMethodLightComparator(null);
		podamValidationSteps.theTwoObjectsShouldBeEqual(null, randomStrategy.getMethodLightComparator());
		randomStrategy.setMethodLightComparator(comparator);
		podamValidationSteps.theTwoObjectsShouldBeEqual(comparator, randomStrategy.getMethodLightComparator());
	}

	@Test
	@Title("Creating a Random Data Provider Strategy should create a Method heavy comparator")
	public void creatingARandomDataProviderStrategyShouldCreateAMethodHeavyComparator() throws Exception {

		AbstractRandomDataProviderStrategy randomStrategy =
				(AbstractRandomDataProviderStrategy) podamFactorySteps.givenARandomDataProviderStrategy();
		AbstractMethodComparator comparator = randomStrategy.getMethodHeavyComparator();
		podamValidationSteps.theObjectShouldNotBeNull(comparator);
		randomStrategy.setMethodHeavyComparator(null);
		podamValidationSteps.theTwoObjectsShouldBeEqual(null, randomStrategy.getMethodHeavyComparator());
		randomStrategy.setMethodHeavyComparator(comparator);
		podamValidationSteps.theTwoObjectsShouldBeEqual(comparator, randomStrategy.getMethodHeavyComparator());
	}

}
