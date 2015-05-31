package uk.co.jemos.podam.test.unit.features.annotations;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Title;
import org.junit.Test;
import org.junit.runner.RunWith;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.test.dto.ConstructorWithSelfReferencesPojoAndDefaultConstructor;
import uk.co.jemos.podam.test.dto.ExcludeAnnotationPojo;
import uk.co.jemos.podam.test.dto.ImmutableNoHierarchicalAnnotatedPojo;
import uk.co.jemos.podam.test.dto.annotations.IntegerValuePojo;
import uk.co.jemos.podam.test.dto.annotations.IntegerValueWithErrorPojo;
import uk.co.jemos.podam.test.dto.annotations.LongValuePojo;
import uk.co.jemos.podam.test.dto.annotations.LongValueWithErrorPojo;
import uk.co.jemos.podam.test.unit.AbstractPodamSteps;
import uk.co.jemos.podam.test.utils.PodamTestConstants;

/**
 * Created by tedonema on 31/05/2015.
 */
@RunWith(SerenityRunner.class)
public class AnnotationsTest extends AbstractPodamSteps {


    @Test
    @Title("Podam should handle immutable POJOs annotated with @PodamConstructor")
    public void podamShouldHandleImmutablePojosAnnotatedWithPodamConstructor() throws Exception {

        PodamFactory podamFactory = podamFactorySteps.givenAStandardPodamFactory();
        ImmutableNoHierarchicalAnnotatedPojo pojo =
                podamInvocationSteps.whenIInvokeTheFactoryForClass(ImmutableNoHierarchicalAnnotatedPojo.class, podamFactory);
        podamValidationSteps.thePojoShouldNotBeNull(pojo);
        podamValidationSteps.theIntFieldShouldNotBeZero(pojo.getIntField());
        podamValidationSteps.theCalendarFieldShouldNotBeNull(pojo.getDateCreated());
        podamValidationSteps.theDateObjectShouldNotBeNull(pojo.getDateCreated().getTime());
        podamValidationSteps.theLongArrayShouldNotBeNullOrEmpty(pojo.getLongArray());
        podamValidationSteps.theLongValueShouldNotBeZero(pojo.getLongArray()[0]);
    }

    @Test
    @Title("Podam should handle POJOs with constructors that have one or more self references to the POJO class, " +
            "provided the required constructor is annotated with @PodamConstructor")
    public void podamShouldHandleConstructorsWithOneOrMoreSelfReferences() throws Exception {

        PodamFactory podamFactory = podamFactorySteps.givenAStandardPodamFactory();
        ConstructorWithSelfReferencesPojoAndDefaultConstructor pojo =
                podamInvocationSteps.whenIInvokeTheFactoryForClass(ConstructorWithSelfReferencesPojoAndDefaultConstructor.class, podamFactory);
        podamValidationSteps.thePojoShouldNotBeNull(pojo);
        constructorSelfReferenceValidationSteps.theFirstSelfReferenceForPojoWithDefaultConstructorShouldNotBeNull(pojo);
        constructorSelfReferenceValidationSteps.theSecondSelfReferenceForPojoWithDefaultConstructorShouldNotBeNull(pojo);

    }

    @Test
    @Title("Podam should not fill POJO's attributes annotated with @PodamExclude")
    public void podamShouldNotFillFieldsAnnotatedWithExcludeAnnotation() throws Exception {

        PodamFactory podamFactory = podamFactorySteps.givenAStandardPodamFactory();
        ExcludeAnnotationPojo pojo = podamInvocationSteps.whenIInvokeTheFactoryForClass(ExcludeAnnotationPojo.class, podamFactory);
        podamValidationSteps.thePojoShouldNotBeNull(pojo);
        podamValidationSteps.theIntFieldShouldNotBeZero(pojo.getIntField());
        podamValidationSteps.anyFieldWithPodamExcludeAnnotationShouldBeNull(pojo.getSomePojo());

    }

    @Test
    @Title("Podam should handle integer values, both native and wrapped")
    public void podamShouldHandleIntegerValues() throws Exception {

        PodamFactory podamFactory = podamFactorySteps.givenAStandardPodamFactory();
        IntegerValuePojo pojo = podamInvocationSteps.whenIInvokeTheFactoryForClass(IntegerValuePojo.class, podamFactory);
        podamValidationSteps.thePojoShouldNotBeNull(pojo);
        podamValidationSteps.theIntFieldShouldBeGreaterOrEqualToZero(pojo.getIntFieldWithMinValueOnly());
        int maxValue = PodamTestConstants.NUMBER_INT_ONE_HUNDRED;
        podamValidationSteps.theIntFieldShouldHaveValueNotGreaterThan(pojo.getIntFieldWithMaxValueOnly(), maxValue);
        int minValue = PodamTestConstants.NUMBER_INT_MIN_VALUE;
        maxValue = PodamTestConstants.NUMBER_INT_MAX_VALUE;
        podamValidationSteps.theIntFieldShouldHaveValueBetween(minValue, maxValue, pojo.getIntFieldWithMinAndMaxValue());
        podamValidationSteps.theIntegerObjectFieldShouldNotBeNull(pojo.getIntegerObjectFieldWithMinValueOnly());
        podamValidationSteps.theIntFieldShouldBeGreaterOrEqualToZero(pojo.getIntegerObjectFieldWithMinValueOnly());
        podamValidationSteps.theIntegerObjectFieldShouldNotBeNull(pojo.getIntegerObjectFieldWithMaxValueOnly());
        maxValue = PodamTestConstants.NUMBER_INT_ONE_HUNDRED;
        podamValidationSteps.theIntFieldShouldHaveValueNotGreaterThan(pojo.getIntegerObjectFieldWithMaxValueOnly(), maxValue);
        podamValidationSteps.theIntegerObjectFieldShouldNotBeNull(pojo.getIntegerObjectFieldWithMinAndMaxValue());
        maxValue = PodamTestConstants.NUMBER_INT_MAX_VALUE;
        podamValidationSteps.theIntFieldShouldHaveValueBetween(minValue, maxValue, pojo.getIntegerObjectFieldWithMinAndMaxValue());
        int preciseValue = Integer.valueOf(PodamTestConstants.INTEGER_PRECISE_VALUE);
        podamValidationSteps.theIntFieldShouldHaveThePreciseValueOf(pojo.getIntFieldWithPreciseValue(), preciseValue);
        podamValidationSteps.theIntegerObjectFieldShouldNotBeNull(pojo.getIntegerObjectFieldWithPreciseValue());
        podamValidationSteps.theIntFieldShouldHaveThePreciseValueOf(pojo.getIntegerObjectFieldWithPreciseValue(), preciseValue);

    }

    @Test
    @Title("Podam should handle long values, both native and wrapped")
    public void podamShouldHandleLongValues() throws Exception {

        PodamFactory podamFactory = podamFactorySteps.givenAStandardPodamFactory();
        LongValuePojo pojo = podamInvocationSteps.whenIInvokeTheFactoryForClass(LongValuePojo.class, podamFactory);
        podamValidationSteps.thePojoShouldNotBeNull(pojo);
        podamValidationSteps.theLongFieldShouldBeGreaterOrEqualToZero(pojo.getLongFieldWithMinValueOnly());
        int maxValue = PodamTestConstants.NUMBER_INT_ONE_HUNDRED;
        podamValidationSteps.theLongFieldShouldHaveValueNotGreaterThan(pojo.getLongFieldWithMaxValueOnly(), maxValue);
        int minValue = PodamTestConstants.NUMBER_INT_MIN_VALUE;
        maxValue = PodamTestConstants.NUMBER_INT_MAX_VALUE;
        podamValidationSteps.theLongFieldShouldHaveValueBetween(minValue, maxValue, pojo.getLongFieldWithMinAndMaxValue());
        podamValidationSteps.theLongObjectFieldShouldNotBeNull(pojo.getLongObjectFieldWithMinValueOnly());
        podamValidationSteps.theLongFieldShouldBeGreaterOrEqualToZero(pojo.getLongObjectFieldWithMinValueOnly());
        podamValidationSteps.theLongObjectFieldShouldNotBeNull(pojo.getLongObjectFieldWithMaxValueOnly());
        maxValue = PodamTestConstants.NUMBER_INT_ONE_HUNDRED;
        podamValidationSteps.theLongFieldShouldHaveValueNotGreaterThan(pojo.getLongObjectFieldWithMinValueOnly(), maxValue);
        podamValidationSteps.theLongObjectFieldShouldNotBeNull(pojo.getLongObjectFieldWithMinAndMaxValue());
        maxValue = PodamTestConstants.NUMBER_INT_MAX_VALUE;
        podamValidationSteps.theLongFieldShouldHaveValueBetween(minValue, maxValue, pojo.getLongObjectFieldWithMinAndMaxValue());
        long preciseValue = Long.valueOf(PodamTestConstants.LONG_PRECISE_VALUE);
        podamValidationSteps.theLongFieldShouldHaveThePreciseValueOf(pojo.getLongFieldWithPreciseValue(), preciseValue);
        podamValidationSteps.theLongObjectFieldShouldNotBeNull(pojo.getLongObjectFieldWithPreciseValue());
        podamValidationSteps.theLongFieldShouldHaveThePreciseValueOf(pojo.getLongObjectFieldWithPreciseValue(), preciseValue);

    }

    @Test(expected = IllegalArgumentException.class)
    @Title("Podam should throw an IllegalArgumentException if the @PodamIntValue annotation contains invalid format")
    public void podamShouldThrowExceptionWhenThePodamIntValueAnnotationHasGotAnInvalidFormat() throws Exception {
        PodamFactory podamFactory = podamFactorySteps.givenAStandardPodamFactory();
        podamInvocationSteps.whenIInvokeTheFactoryForClass(IntegerValueWithErrorPojo.class, podamFactory);
    }

    @Test(expected = IllegalArgumentException.class)
    @Title("Podam should throw an IllegalArgumentException if the @PodamLongValue annotation contains invalid format")
    public void podamShouldThrowExceptionWhenThePodamLongValueAnnotationHasGotAnInvalidFormat() throws Exception {
        PodamFactory podamFactory = podamFactorySteps.givenAStandardPodamFactory();
        podamInvocationSteps.whenIInvokeTheFactoryForClass(LongValueWithErrorPojo.class, podamFactory);
    }

}
