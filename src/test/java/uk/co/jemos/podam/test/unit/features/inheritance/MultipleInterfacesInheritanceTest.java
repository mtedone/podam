package uk.co.jemos.podam.test.unit.features.inheritance;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Title;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;
import uk.co.jemos.podam.test.dto.MultipleInterfacesHolderPojo;
import uk.co.jemos.podam.test.dto.MultipleInterfacesListPojo;
import uk.co.jemos.podam.test.dto.MultipleInterfacesMapPojo;
import uk.co.jemos.podam.test.unit.AbstractPodamSteps;

import java.util.List;

/**
 * @author daivanov
 */
@RunWith(SerenityRunner.class)
public class MultipleInterfacesInheritanceTest extends AbstractPodamSteps {


	private static final TrackingExternalFactory externalFactory
			= new TrackingExternalFactory();

	private static final CustomDataProviderStrategy strategy
			= new CustomDataProviderStrategy();

	private static final PodamFactory factory
			= new PodamFactoryImpl(externalFactory, strategy);





	@Test
	@Title("Podam cannot instantiate interfaces")
	public void podamCannotInstantiateInterfaces() throws Exception {

		PodamFactory podamFactory = provideCustomisedPodamFactory();

		MultipleInterfacesListPojo<?> pojo =
				podamInvocationSteps.whenIInvokeTheFactoryForGenericTypeWithSpecificType(MultipleInterfacesListPojo.class,
						podamFactory, String.class);
		podamValidationSteps.theObjectShouldBeNull(pojo);
		List<Class<?>> accessed = ((CustomDataProviderStrategy)podamFactory.getStrategy()).getAccessed();
		podamValidationSteps.theCollectionShouldHaveExactlyTheExpectedNumberOfElements(accessed, 1);
		podamValidationSteps.theTwoObjectsShouldBeEqual(MultipleInterfacesListPojo.class, accessed.get(0));
	}

	@Test
	@Title("Podam cannot instantiate interfaces which extend Map")
	public void podamCannotInstantiateInterfacesWhichExtendMap() throws Exception {

		PodamFactory podamFactory = provideCustomisedPodamFactory();

		MultipleInterfacesMapPojo<?,?> pojo =
				podamInvocationSteps.whenIInvokeTheFactoryForGenericTypeWithSpecificType(
						MultipleInterfacesMapPojo.class, podamFactory, String.class, Long.class);
		podamValidationSteps.theObjectShouldBeNull(pojo);
		List<Class<?>> accessed = ((CustomDataProviderStrategy)podamFactory.getStrategy()).getAccessed();
		podamValidationSteps.theCollectionShouldHaveExactlyTheExpectedNumberOfElements(accessed, 1);
		podamValidationSteps.theTwoObjectsShouldBeEqual(MultipleInterfacesMapPojo.class, accessed.get(0));
	}

	@Test
	@Title("Podam will create an instance of a POJO but its interface types won't be instantiated")
	public void testHolderOfPojoWithMultiInterfaces() throws Exception {

		PodamFactory podamFactory = provideCustomisedPodamFactory();

		MultipleInterfacesHolderPojo<?,?> pojo =
				podamInvocationSteps.whenIInvokeTheFactoryForGenericTypeWithSpecificType(
						MultipleInterfacesHolderPojo.class, podamFactory, String.class, Long.class
				);

		podamValidationSteps.theObjectShouldNotBeNull(pojo);
		podamValidationSteps.theObjectShouldBeNull(pojo.getList());
		podamValidationSteps.theObjectShouldBeNull(pojo.getMap());
		List<Class<?>> accessed = ((CustomDataProviderStrategy)podamFactory.getStrategy()).getAccessed();
		podamValidationSteps.theTwoObjectsShouldBeEqual(accessed.size(), 2);
		podamValidationSteps.theCollectionShouldContainAtLeastOneElementOfType(
				accessed, MultipleInterfacesListPojo.class);
		podamValidationSteps.theCollectionShouldContainAtLeastOneElementOfType(
				accessed, MultipleInterfacesMapPojo.class);
	}


	//-------------> Private methods

	private PodamFactory provideCustomisedPodamFactory() throws Exception {
		TrackingExternalFactory externalFactory = podamFactorySteps.givenATrackingExternalFactory();

		CustomDataProviderStrategy customDataProviderStrategy = podamFactorySteps.givenACustomDataProviderStrategy();

		PodamFactory podamFactory = podamFactorySteps.givenAPodamFactoryWithExternalFactoryAndCustomStrategy(
				externalFactory, customDataProviderStrategy);

		return podamFactory;
	}


}
