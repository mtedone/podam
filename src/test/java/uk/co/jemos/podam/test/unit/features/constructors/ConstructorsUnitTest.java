package uk.co.jemos.podam.test.unit.features.constructors;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Title;
import org.junit.Test;
import org.junit.runner.RunWith;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.test.dto.*;
import uk.co.jemos.podam.test.unit.AbstractPodamSteps;

/**
 * @author daivanov
 *
 */
@RunWith(SerenityRunner.class)
public class ConstructorsUnitTest extends AbstractPodamSteps {



	@Test
	@Title("Podam should handle generics in the constructor")
	public void podamShouldHandleGenericsInConstructor() throws Exception {
		PodamFactory podamFactory = podamFactorySteps.givenAStandardPodamFactory();
		GenericInConstructorPojo pojo
				= podamInvocationSteps.whenIInvokeTheFactoryForClass(GenericInConstructorPojo.class, podamFactory);
		podamValidationSteps.theObjectShouldNotBeNull(pojo);
		podamValidationSteps.theCollectionShouldNotBeNullOrEmptyAndContainElementsOfType(pojo.getVector(), String.class);
	}
	@Test
	@Title("Podam should handle generics in setters during Pojo instantiation")
	public void podamShouldHandleGenericsInSettersDuringPojoInstantiation() throws Exception {
		PodamFactory podamFactory = podamFactorySteps.givenAStandardPodamFactory();
		GenericInSetterPojo pojo
				= podamInvocationSteps.whenIInvokeTheFactoryForClass(GenericInSetterPojo.class, podamFactory);
		podamValidationSteps.theObjectShouldNotBeNull(pojo);
		podamValidationSteps.theCollectionShouldNotBeNullOrEmptyAndContainElementsOfType(pojo.getVector(), String.class);
	}

	@Test
	@Title("Podam should handle generics in static constructors during POJO instantiation")
	public void podamShouldHandleGenericsInStaticConstructorsDuringPojoInstantiation() throws Exception {
		PodamFactory podamFactory = podamFactorySteps.givenAStandardPodamFactory();
		GenericInStaticConstructorPojo pojo
				= podamInvocationSteps.whenIInvokeTheFactoryForClass(GenericInStaticConstructorPojo.class, podamFactory);
		podamValidationSteps.theObjectShouldNotBeNull(pojo);
		podamValidationSteps.theCollectionShouldNotBeNullOrEmptyAndContainElementsOfType(pojo.getVector(), String.class);
	}

	@Test
	@Title("Podam should handle constructors with generic arrays during Pojo instantiation")
	public void podamShouldHandleConstructorsWithGenericArraysDuringPojoInstantiation() {
		PodamFactory podamFactory = podamFactorySteps.givenAStandardPodamFactory();
		GenericArrayInConstructorPojo<?> pojo
				= podamInvocationSteps.whenIInvokeTheFactoryForGenericTypeWithSpecificType(
				GenericArrayInConstructorPojo.class, podamFactory, String.class);
		podamValidationSteps.theObjectShouldNotBeNull(pojo);
		podamValidationSteps.theArrayOfTheGivenTypeShouldNotBeNullOrEmptyAndContainElementsOfTheRightType(
				pojo.getArray(), String.class);
	}

	@Test
	@Title("Podam should handle constructors with multiple generics during Pojo instantiation")
	public void podamShouldHandleConstructorsWithMultipleGenericsDuringPojoInstantiation() {
		PodamFactory podamFactory = podamFactorySteps.givenAStandardPodamFactory();
		MultipleGenericInConstructorPojo<?, ?, ?, ?> pojo
				= podamInvocationSteps.whenIInvokeTheFactoryForGenericTypeWithSpecificType(MultipleGenericInConstructorPojo.class,
				podamFactory, String.class, Character.class, Byte.class, Integer.class);
		podamValidationSteps.theObjectShouldNotBeNull(pojo);
		podamValidationSteps.theTwoObjectsShouldBeEqual(String.class, pojo.getType());
		podamValidationSteps.theCollectionShouldNotBeNullOrEmptyAndContainElementsOfType(pojo.getList(), Character.class);
		podamValidationSteps.theMapShouldNotBeNullOrEmptyAndContainElementsOfType(
				pojo.getMap(), Byte.class, Integer.class);
	}

}
