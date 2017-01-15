package uk.co.jemos.podam.test.unit.features.factories;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Title;

import org.junit.Test;
import org.junit.runner.RunWith;

import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.test.unit.AbstractPodamSteps;

/**
 * Test @uk.co.jemos.podam.test.dto.JAXBElementPojo@ construction
 *
 * @author daivanov
 *
 */
@RunWith(SerenityRunner.class)
public class FactoryUnitTest extends AbstractPodamSteps {

	@Test
	@Title("Podam should be able to create an abstract class with help of factory")
	public void podamShouldBeAbleToCreateAnAbstractClassWithHelpOfFactory()
			throws Exception {

		PodamFactory podamFactory = podamFactorySteps.givenAPodamFactoryWithDefinedFactoryForAnAbstractClass(
				Transformer.class, TransformerFactory.class);

		Transformer pojo = podamInvocationSteps.whenIInvokeTheFactoryForClass(Transformer.class, podamFactory);
		podamValidationSteps.theObjectShouldNotBeNull(pojo);

		podamFactorySteps.removeADefinedFactoryForAnAbstractClassFromAPodamFactory(podamFactory, TransformerFactory.class);
	}
}
