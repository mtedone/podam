package uk.co.jemos.podam.test.unit.features.basicTypes;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Title;
import org.junit.Test;
import org.junit.runner.RunWith;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;
import uk.co.jemos.podam.test.dto.ClassGenericConstructorPojo;
import uk.co.jemos.podam.test.dto.ClassGenericPojo;
import uk.co.jemos.podam.test.dto.ClassInheritedPojo;
import uk.co.jemos.podam.test.dto.ClassPojo;
import uk.co.jemos.podam.test.unit.AbstractPodamSteps;

/**
 * Test @uk.co.jemos.podam.test.dto.JAXBElementPojo@ construction
 *
 * @author daivanov
 *
 */
@RunWith(SerenityRunner.class)
public class ClassUnitTest extends AbstractPodamSteps {

	private final static PodamFactory podam = new PodamFactoryImpl();

	@Test
	@Title("Podam should handle the manufacturing of basic types")
	public void podamShouldHandleTheManufacturingOfBasicTypes() throws Exception {

		PodamFactory podamFactory = podamFactorySteps.givenAStandardPodamFactory();
		ClassPojo pojo = podamInvocationSteps.whenIInvokeTheFactoryForClass(ClassPojo.class, podamFactory);
		podamValidationSteps.theObjectShouldNotBeNull(pojo);
		podamValidationSteps.theObjectShouldNotBeNull(pojo.getClazz());
		podamValidationSteps.theTwoObjectsShouldBeEqual(String.class, pojo.getClazz());
	}

	@Test
	@Title("Podam should handle the manufacturing of generic POJOs")
	public void podamShouldHandleTheManufacturingOfGenericPojos() throws Exception {

		PodamFactory podamFactory = podamFactorySteps.givenAStandardPodamFactory();
		ClassGenericPojo<?> pojo  = podamInvocationSteps.whenIInvokeTheFactoryForGenericTypeWithSpecificType(
				ClassGenericPojo.class, podamFactory, String.class);
		podamValidationSteps.theObjectShouldNotBeNull(pojo);
		podamValidationSteps.theObjectShouldNotBeNull(pojo.getClazz());
		podamValidationSteps.theTwoObjectsShouldBeEqual(String.class, pojo.getClazz());

	}

	@Test
	@Title("Podam should handle the manufacturing of POJOs with generic types in constructor")
	public void podamShouldHandleTheManufacturingOfPojosWithGenericTypesInTheConstructor() throws Exception {

		PodamFactory podamFactory = podamFactorySteps.givenAStandardPodamFactory();

		ClassGenericConstructorPojo<?> pojo = podamInvocationSteps.whenIInvokeTheFactoryForGenericTypeWithSpecificType(
				ClassGenericConstructorPojo.class, podamFactory, String.class);
		podamValidationSteps.theObjectShouldNotBeNull(pojo);
		podamValidationSteps.theObjectShouldNotBeNull(pojo.getClazz());
		podamValidationSteps.theTwoObjectsShouldBeEqual(String.class, pojo.getClazz());
	}


}
