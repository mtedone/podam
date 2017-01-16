package uk.co.jemos.podam.test.unit.features.basicTypes;

import java.util.Set;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Title;

import org.junit.*;
import org.junit.runner.RunWith;

import uk.co.jemos.podam.api.DefaultClassInfoStrategy;
import uk.co.jemos.podam.api.PodamFactory;
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

	private final static String EXCLUDED_FIELD = "value4";
	private final static DefaultClassInfoStrategy classInfoStrategy =
			DefaultClassInfoStrategy.getInstance();

	@BeforeClass
	public static void init() {
		classInfoStrategy.addExcludedAnnotation(TestExclude.class);
		classInfoStrategy.addExcludedAnnotation(PodamExclude.class);
		Assert.assertEquals("Unexpected number of exluded annotations",
				2, classInfoStrategy.getExcludedAnnotations().size());
	}

	@AfterClass
	public static void deinit() {
		classInfoStrategy.removeExcludedField(BooleanPojo.class, EXCLUDED_FIELD);
		classInfoStrategy.removeExcludedAnnotation(TestExclude.class);
		Assert.assertEquals("Unexpected number of exluded annotations",
				1, classInfoStrategy.getExcludedAnnotations().size());
	}

	@Test
	@Title("Podam should handle booleans correctly")
	public void podamShouldHandleBooleansCorrect() throws Exception {

		classInfoStrategy.removeExcludedField(BooleanPojo.class, EXCLUDED_FIELD);
		Set<String> excludedFields = classInfoStrategy.getExcludedFields(BooleanPojo.class);
		podamValidationSteps.theObjectShouldBeNull(excludedFields);

		classInfoStrategy.addExcludedField(BooleanPojo.class, EXCLUDED_FIELD);
		excludedFields = classInfoStrategy.getExcludedFields(BooleanPojo.class);
		podamValidationSteps.theTwoObjectsShouldBeStrictlyEqual(1, excludedFields.size());

		classInfoStrategy.addExcludedField(BooleanPojo.class, EXCLUDED_FIELD);
		excludedFields = classInfoStrategy.getExcludedFields(BooleanPojo.class);
		podamValidationSteps.theTwoObjectsShouldBeStrictlyEqual(1, excludedFields.size());

		PodamFactory podamFactory = podamFactorySteps.givenAStandardPodamFactory();
		BooleanPojo pojo = podamInvocationSteps.whenIInvokeTheFactoryForClass(BooleanPojo.class, podamFactory);

		podamValidationSteps.theObjectShouldNotBeNull(pojo);
		podamValidationSteps.theBooleanValueIsTrue(pojo.isValue1());
		podamValidationSteps.theValueShouldBeNull(pojo.isValue2());
		podamValidationSteps.theObjectShouldNotBeNull(pojo.getValue3());
		podamValidationSteps.theValueShouldBeNull(pojo.getValue4());

		classInfoStrategy.removeExcludedField(BooleanPojo.class, EXCLUDED_FIELD);
		excludedFields = classInfoStrategy.getExcludedFields(BooleanPojo.class);
		podamValidationSteps.theCollectionShouldBeEmpty(excludedFields);
	}

}
