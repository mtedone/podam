package uk.co.jemos.podam.test.unit.features.constructors;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Title;
import org.junit.Test;
import org.junit.runner.RunWith;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.test.dto.ReadOnlyAbstract;
import uk.co.jemos.podam.test.dto.ReadOnlyComplexTypesPojo;
import uk.co.jemos.podam.test.dto.ReadOnlyGenericComplexTypesPojo;
import uk.co.jemos.podam.test.unit.AbstractPodamSteps;

import java.beans.beancontext.BeanContextServicesSupport;

/**
 * @author daivanov
 *
 */
@RunWith(SerenityRunner.class)
public class ReadOnlyComplexTypesTest extends AbstractPodamSteps {

	@Test
	@Title("Podam should fill in read-only POJOs")
	public void podamShouldFillReadOnlyTypes() throws Exception {

		PodamFactory podamFactory = podamFactorySteps.givenAStandardPodamFactory();

		ReadOnlyComplexTypesPojo pojo = podamInvocationSteps.whenIInvokeTheFactoryForClass(
				ReadOnlyComplexTypesPojo.class, podamFactory);

		podamValidationSteps.theObjectShouldNotBeNull(pojo);
		podamValidationSteps.theObjectShouldNotBeNull(pojo.getValue());
		podamValidationSteps.theCollectionShouldNotBeNullOrEmptyAndContainElementsOfType(pojo.getList(), Integer.class);
		podamValidationSteps.theMapShouldNotBeNullOrEmptyAndContainElementsOfType(
				pojo.getMap(), Long.class, String.class);
		podamValidationSteps.theObjectShouldNotBeNull(pojo.getValue().getValue());
	}

	@Test
	@Title("Podam should fill in complex (e.g. with more than two generic types), read-only POJOs")
	public void podamShouldFillReadOnlyComplexTypes() throws Exception {

		PodamFactory podamFactory = podamFactorySteps.givenAStandardPodamFactory();

		ReadOnlyGenericComplexTypesPojo<?,?,?> pojo =
				podamInvocationSteps.whenIInvokeTheFactoryForGenericTypeWithSpecificType(
						ReadOnlyGenericComplexTypesPojo.class, podamFactory, Character.class, Long.class, Integer.class);

		podamValidationSteps.theObjectShouldNotBeNull(pojo);
		podamValidationSteps.theCollectionShouldNotBeNullOrEmptyAndContainElementsOfType(pojo.getList(), Long.class);
		podamValidationSteps.theMapShouldNotBeNullOrEmptyAndContainElementsOfType(
				pojo.getMap(), Integer.class, String.class);
		podamValidationSteps.theObjectShouldNotBeNull(pojo.getValue());
		podamValidationSteps.theObjectShouldNotBeNull(pojo.getValue().getValue());
		podamValidationSteps.theTwoObjectsShouldBeEqual(Character.class, pojo.getValue().getValue().getClass());
	}

	@Test
	@Title("Podam should fill in POJOs which contain internal loops (e.g. objects that reference parents)")
	public void podamShouldFillInPojosWhichContainInternalLoops() throws Exception {

		PodamFactory podamFactory = podamFactorySteps.givenAStandardPodamFactory();
		BeanContextServicesSupport pojo = podamInvocationSteps.whenIInvokeTheFactoryForClass(
				BeanContextServicesSupport.class, podamFactory);
		podamValidationSteps.theObjectShouldNotBeNull(pojo);
	}


	@Test
	@Title("Podam should create an instance of an Abstract class with a factory method which returns a concrete type" +
			" even if the concrete type has got read-only attributes. These will be empty.")
	public void podamShouldCreateAnInstanceOfAnAbstractClassWithAFactoryMethodWhichReturnsAConcreteType()
		throws Exception {

		PodamFactory podamFactory = podamFactorySteps.givenAStandardPodamFactory();

		ReadOnlyAbstract pojo = podamInvocationSteps.whenIInvokeTheFactoryForClass(ReadOnlyAbstract.class, podamFactory);
		podamValidationSteps.theObjectShouldNotBeNull(pojo);
	}
}
