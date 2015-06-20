package uk.co.jemos.podam.test.unit.features.extensions;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Title;
import org.junit.Test;
import org.junit.runner.RunWith;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.test.dto.NonEJBPojo;
import uk.co.jemos.podam.test.unit.AbstractPodamSteps;

/**
 * Test @uk.co.jemos.podam.test.dto.NonEJBPojo@ construction
 *
 * @author daivanov
 *
 */
@RunWith(SerenityRunner.class)
public class NonEJBPojoTest extends AbstractPodamSteps {

	@Test
	@Title("Podam should handle attributes in accordance with custom Class Info Strategies")
	public void podamShouldHandleAttributesInAccordanceWithCustomClassInfoStrategies() throws Exception {

		NonEJBClassInfoStrategy classInfoStrategy = podamFactorySteps.givenANonEJBClassInfoStrategy();

		PodamFactory podamFactory = podamFactorySteps.givenAPodamFactoryWithCustomClassInfoStrategy(classInfoStrategy);

		NonEJBPojo pojo = podamInvocationSteps.whenIInvokeTheFactoryForClass(NonEJBPojo.class, podamFactory);
		podamValidationSteps.theObjectShouldNotBeNull(pojo);
		podamValidationSteps.theStringFieldCannotBeNullOrEmpty(pojo.getMyString());
		podamValidationSteps.theObjectShouldNotBeNull(pojo.getMyLong());
	}
}
