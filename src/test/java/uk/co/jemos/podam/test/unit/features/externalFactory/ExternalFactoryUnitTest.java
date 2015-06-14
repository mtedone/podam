package uk.co.jemos.podam.test.unit.features.externalFactory;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Title;
import org.junit.Test;
import org.junit.runner.RunWith;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.test.dto.*;
import uk.co.jemos.podam.test.unit.AbstractPodamSteps;

/**
 * Test @uk.co.jemos.podam.test.dto.JAXBElementPojo@ construction
 *
 * @author daivanov
 *
 */
@RunWith(SerenityRunner.class)
public class ExternalFactoryUnitTest extends AbstractPodamSteps {

	@Test
	@Title("Podam should not create instances of an interface when given an external factory which " +
			"does not manufacture the concrete type")
	public void podamShouldNotBeAbleToCreateInterfaceInstancesGivenAnExternalFactoryWhichDoesNotManufactureTheConcreteType()
	throws Exception {

		TestExternalFactory externalFactory = (TestExternalFactory) podamFactorySteps.givenAnExternalFactory();
		PodamFactory podamFactory = podamFactorySteps.givenAdPodamFactoryWithExternalFactory(externalFactory);

		InterfacePojo pojo = podamInvocationSteps.whenIInvokeTheFactoryForClass(InterfacePojo.class, podamFactory);
		podamValidationSteps.theObjectShouldBeNull(pojo);
		podamValidationSteps.theTwoObjectsShouldBeEqual(1, externalFactory.getFailures().size());
		podamValidationSteps.theTwoObjectsShouldBeEqual(InterfacePojo.class, externalFactory.getFailures().get(0));
		podamValidationSteps.theTwoObjectsShouldBeEqual(0, externalFactory.getFullDataCalls().size());
	}

	@Test
	@Title("Podam cannot fill interface attributes if the external factory does not manufacture the right type")
	public void podamCannotFillInterfaceAttributesIfTheExternalFactoryDoesNotManufactureTheRightType() throws Exception {

		TestExternalFactory externalFactory = (TestExternalFactory) podamFactorySteps.givenAnExternalFactory();
		PodamFactory podamFactory = podamFactorySteps.givenAdPodamFactoryWithExternalFactory(externalFactory);

		PojoWithInterfaces pojo = podamInvocationSteps.whenIInvokeTheFactoryForClass(PojoWithInterfaces.class, podamFactory);
		podamValidationSteps.theObjectShouldNotBeNull(pojo);
		podamValidationSteps.theTwoObjectsShouldBeEqual(ObjectExt.class, externalFactory.getFailures().get(0));
		podamValidationSteps.theTwoObjectsShouldBeEqual(InterfacePojo.class, externalFactory.getFailures().get(1));
		podamValidationSteps.theTwoObjectsShouldBeEqual(0, externalFactory.getFullDataCalls().size());
	}

	@Test
	@Title("Podam should fill POJOs and interface attributes if a full constructor sets the interface attribute value")
	public void podamShouldFillPojoInterfaceAttributeIfFullConstructorSetsItsValue() throws Exception {

		TestExternalFactory externalFactory = (TestExternalFactory) podamFactorySteps.givenAnExternalFactory();
		PodamFactory podamFactory = podamFactorySteps.givenAdPodamFactoryWithExternalFactory(externalFactory);

		PojoWithInterfaces pojo = podamInvocationSteps.whenIInvokeTheFactoryForClassWithFullConstructor(
				PojoWithInterfaces.class, podamFactory);
		podamValidationSteps.theObjectShouldNotBeNull(pojo);
		podamValidationSteps.theTwoObjectsShouldBeEqual(2, externalFactory.getFailures().size());
		podamValidationSteps.theTwoObjectsShouldBeEqual(ObjectExt.class, externalFactory.getFailures().get(0));
		podamValidationSteps.theTwoObjectsShouldBeEqual(InterfacePojo.class, externalFactory.getFailures().get(1));
		podamValidationSteps.theTwoObjectsShouldBeEqual(2, externalFactory.getFullDataCalls().size());
	}

	@Test
	@Title("Podam should not be able to create instances of an Abstract class if the external factory does not " +
			"provide a concrete implementation")
	public void podamDoesNotCreateInstancesOfAbstractClassesIfExternalFactoryDoesNotDefineThem() throws Exception {

		TestExternalFactory externalFactory = (TestExternalFactory) podamFactorySteps.givenAnExternalFactory();
		PodamFactory podamFactory = podamFactorySteps.givenAdPodamFactoryWithExternalFactory(externalFactory);

		AbstractClass pojo = podamInvocationSteps.whenIInvokeTheFactoryForClass(AbstractClass.class, podamFactory);
		podamValidationSteps.theObjectShouldBeNull(pojo);
		podamValidationSteps.theTwoObjectsShouldBeEqual(1, externalFactory.getFailures().size());
		podamValidationSteps.theTwoObjectsShouldBeEqual(AbstractClass.class, externalFactory.getFailures().get(0));
		podamValidationSteps.theTwoObjectsShouldBeEqual(0, externalFactory.getFullDataCalls().size());
	}

	@Test
	@Title("Podam should not be able to create instances of an Abstract class even if the POJO has a full constructor")
	public void podamShouldNotCreateInstancesOfAnAbstractClassEvenIfPojoHasFullConstructor() throws Exception {

		TestExternalFactory externalFactory = (TestExternalFactory) podamFactorySteps.givenAnExternalFactory();
		PodamFactory podamFactory = podamFactorySteps.givenAdPodamFactoryWithExternalFactory(externalFactory);

		AbstractClass pojo = podamInvocationSteps.whenIInvokeTheFactoryForClassWithFullConstructor(
				AbstractClass.class, podamFactory);
		podamValidationSteps.theObjectShouldBeNull(pojo);
		podamValidationSteps.theTwoObjectsShouldBeEqual(1, externalFactory.getFailures().size());
		podamValidationSteps.theTwoObjectsShouldBeEqual(AbstractClass.class, externalFactory.getFailures().get(0));
		podamValidationSteps.theTwoObjectsShouldBeEqual(1, externalFactory.getFullDataCalls().size());

	}

	@Test
	@Title("Podam should not create instances of non-instantiable classes")
	public void podamShouldNotcreateInstancesOfNonInstantiableClasses() throws Exception {

		TestExternalFactory externalFactory = (TestExternalFactory) podamFactorySteps.givenAnExternalFactory();
		PodamFactory podamFactory = podamFactorySteps.givenAdPodamFactoryWithExternalFactory(externalFactory);

		NonInstantiatableClass pojo = podamInvocationSteps.whenIInvokeTheFactoryForClass(
				NonInstantiatableClass.class, podamFactory);
		podamValidationSteps.theObjectShouldBeNull(pojo);
		podamValidationSteps.theTwoObjectsShouldBeEqual(1, externalFactory.getFailures().size());
		podamValidationSteps.theTwoObjectsShouldBeEqual(NonInstantiatableClass.class, externalFactory.getFailures().get(0));
		podamValidationSteps.theTwoObjectsShouldBeEqual(0, externalFactory.getFullDataCalls().size());
	}

	@Test
	@Title("The management of external factories should be correct and chaining should work")
	public void theManagementOfExternalFactoriesShouldBeCorrectAndChainingShouldWord() throws Exception {

		TestExternalFactory externalFactory = (TestExternalFactory) podamFactorySteps.givenAnExternalFactory();

		podamValidationSteps.theObjectShouldBeNull(externalFactory.getClassStrategy());
		podamValidationSteps.theTwoObjectsShouldBeEqual(externalFactory, externalFactory.setClassStrategy(null));
		podamValidationSteps.theObjectShouldBeNull(externalFactory.getStrategy());
		podamValidationSteps.theTwoObjectsShouldBeEqual(externalFactory, externalFactory.setStrategy(null));
		podamValidationSteps.theObjectShouldBeNull(externalFactory.getExternalFactory());
		podamValidationSteps.theTwoObjectsShouldBeEqual(externalFactory, externalFactory.setExternalFactory(null));
	}
}
