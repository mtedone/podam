package uk.co.jemos.podam.test.unit.features.basicTypes;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Title;
import org.junit.*;
import org.junit.runner.RunWith;
import uk.co.jemos.podam.api.DefaultClassInfoStrategy;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.RandomDataProviderStrategyImpl;
import uk.co.jemos.podam.common.PodamExclude;
import uk.co.jemos.podam.test.dto.BooleanPojo;
import uk.co.jemos.podam.test.dto.SimplePojoWithExcludeAnnotationToTestSetters.TestExclude;
import uk.co.jemos.podam.test.unit.AbstractPodamSteps;

/**
 * Test @uk.co.jemos.podam.test.dto.BooleanPojo@ construction
 *
 * @author daivanov
 *
 */
@RunWith(SerenityRunner.class)
public class BooleanUnitTest extends AbstractPodamSteps {

	private final static RandomDataProviderStrategyImpl strategy =
			new RandomDataProviderStrategyImpl();

	private final static DefaultClassInfoStrategy classInfoStrategy =
			DefaultClassInfoStrategy.getInstance();

	@BeforeClass
	public static void init() {
		classInfoStrategy.addExcludedField(BooleanPojo.class, "value4");
		classInfoStrategy.addExcludedAnnotation(TestExclude.class);
		classInfoStrategy.addExcludedAnnotation(PodamExclude.class);
		Assert.assertEquals("Unexpected number of exluded annotations",
				2, classInfoStrategy.getExcludedAnnotations().size());
	}

	@AfterClass
	public static void deinit() {
		classInfoStrategy.removeExcludedField(BooleanPojo.class, "value4");
		classInfoStrategy.removeExcludedAnnotation(TestExclude.class);
		Assert.assertEquals("Unexpected number of exluded annotations",
				1, classInfoStrategy.getExcludedAnnotations().size());
	}

	@Test
	@Title("Podam should handle booleans correctly")
	public void podamShouldHandleBooleansCorrect() throws Exception {

		PodamFactory podamFactory = podamFactorySteps.givenAStandardPodamFactory();
		BooleanPojo pojo = podamInvocationSteps.whenIInvokeTheFactoryForClass(BooleanPojo.class, podamFactory);

		podamValidationSteps.theObjectShouldNotBeNull(pojo);
		podamValidationSteps.theBooleanValueIsTrue(pojo.isValue1());
		podamValidationSteps.theValueShouldBeNull(pojo.isValue2());
		podamValidationSteps.theObjectShouldNotBeNull(pojo.getValue3());
		podamValidationSteps.theValueShouldBeNull(pojo.getValue4());
	}

}
