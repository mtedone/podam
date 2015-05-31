package uk.co.jemos.podam.test.unit.features.annotations;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Title;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.test.dto.ConstructorWithSelfReferencesPojoAndDefaultConstructor;
import uk.co.jemos.podam.test.dto.ExcludeAnnotationPojo;
import uk.co.jemos.podam.test.dto.ImmutableNoHierarchicalAnnotatedPojo;
import uk.co.jemos.podam.test.dto.annotations.*;
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
    @Title("Podam should handle both native and wrapped integer values with @PodamIntValue annotation")
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
    @Title("Podam should handle both native and wrapped long values with @PodamLongValue annotation")
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

    @Test
    @Title("Podam should handle both native and wrapped byte values with @PodamByteValue annotation")
    public void podamShouldHandleByteValuesWithThePodamByteValueAnnotation() throws Exception {

        PodamFactory podamFactory = podamFactorySteps.givenAStandardPodamFactory();
        ByteValuePojo pojo = podamInvocationSteps.whenIInvokeTheFactoryForClass(ByteValuePojo.class, podamFactory);
        podamValidationSteps.thePojoShouldNotBeNull(pojo);

        byte byteFieldWithMinValueOnly = pojo.getByteFieldWithMinValueOnly();
        podamValidationSteps.theByteValueShouldBeGreaterOrEqualThan(
                byteFieldWithMinValueOnly, PodamTestConstants.NUMBER_INT_MIN_VALUE);

        byte byteFieldWithMaxValueOnly = pojo.getByteFieldWithMaxValueOnly();
        podamValidationSteps.theByteValueShouldBeLowerOrEqualThan(
                byteFieldWithMaxValueOnly, PodamTestConstants.NUMBER_INT_ONE_HUNDRED);

        byte byteFieldWithMinAndMaxValue = pojo
                .getByteFieldWithMinAndMaxValue();
        podamValidationSteps.theByteValueShouldBeBetween(byteFieldWithMinAndMaxValue,
                PodamTestConstants.NUMBER_INT_MIN_VALUE, PodamTestConstants.NUMBER_INT_ONE_HUNDRED);

        Byte byteObjectFieldWithMinValueOnly = pojo
                .getByteObjectFieldWithMinValueOnly();
        podamValidationSteps.thePojoShouldNotBeNull(byteObjectFieldWithMinValueOnly);
        podamValidationSteps.theByteValueShouldBeGreaterOrEqualThan(
                byteObjectFieldWithMinValueOnly, PodamTestConstants.NUMBER_INT_MIN_VALUE);

        Byte byteObjectFieldWithMaxValueOnly = pojo
                .getByteObjectFieldWithMaxValueOnly();
        podamValidationSteps.thePojoShouldNotBeNull(byteFieldWithMaxValueOnly);
        podamValidationSteps.theByteValueShouldBeLowerOrEqualThan(
                byteObjectFieldWithMaxValueOnly, PodamTestConstants.NUMBER_INT_ONE_HUNDRED);

        Byte byteObjectFieldWithMinAndMaxValue = pojo
                .getByteObjectFieldWithMinAndMaxValue();
        podamValidationSteps.thePojoShouldNotBeNull(byteObjectFieldWithMinAndMaxValue);
        podamValidationSteps.theByteValueShouldBeBetween(byteObjectFieldWithMinAndMaxValue,
                PodamTestConstants.NUMBER_INT_MIN_VALUE,
                PodamTestConstants.NUMBER_INT_ONE_HUNDRED);
        byte byteFieldWithPreciseValue = pojo.getByteFieldWithPreciseValue();
        podamValidationSteps.theByteValueShouldHavePreciselyValueOf(byteFieldWithPreciseValue,
                Byte.valueOf(PodamTestConstants.BYTE_PRECISE_VALUE));

    }

    @Test
    @Title("Podam should handle both native and wrapped short values with @PodamShortValue annotation")
    public void podamShouldHandleShortValuesWithThePodamShortValueAnnotation() throws Exception {

        PodamFactory podamFactory = podamFactorySteps.givenAStandardPodamFactory();
        ShortValuePojo pojo = podamInvocationSteps.whenIInvokeTheFactoryForClass(ShortValuePojo.class, podamFactory);

        podamValidationSteps.thePojoShouldNotBeNull(pojo);

        short shortFieldWithMinValueOnly = pojo.getShortFieldWithMinValueOnly();
        podamValidationSteps.theShortValueShouldBeGreaterOrEqualThan(
                shortFieldWithMinValueOnly, PodamTestConstants.NUMBER_INT_MIN_VALUE);

        short shortFieldWithMaxValueOnly = pojo.getShortFieldWithMaxValueOnly();
        podamValidationSteps.theShortValueShouldBeLowerOrEqualThan(
                shortFieldWithMaxValueOnly, PodamTestConstants.NUMBER_INT_ONE_HUNDRED);

        short shortFieldWithMinAndMaxValue = pojo
                .getShortFieldWithMinAndMaxValue();
        podamValidationSteps.theShortValueShouldBeBetween(shortFieldWithMinAndMaxValue,
                PodamTestConstants.NUMBER_INT_MIN_VALUE, PodamTestConstants.NUMBER_INT_MAX_VALUE);

        Short shortObjectFieldWithMinValueOnly = pojo
                .getShortObjectFieldWithMinValueOnly();
        podamValidationSteps.thePojoShouldNotBeNull(shortFieldWithMinValueOnly);
        podamValidationSteps.theShortValueShouldBeGreaterOrEqualThan(shortObjectFieldWithMinValueOnly,
                PodamTestConstants.NUMBER_INT_MIN_VALUE);

        Short shortObjectFieldWithMaxValueOnly = pojo
                .getShortObjectFieldWithMaxValueOnly();

        podamValidationSteps.thePojoShouldNotBeNull(shortFieldWithMaxValueOnly);
        podamValidationSteps.theShortValueShouldBeLowerOrEqualThan(shortObjectFieldWithMaxValueOnly,
                PodamTestConstants.NUMBER_INT_ONE_HUNDRED);

        Short shortObjectFieldWithMinAndMaxValue = pojo
                .getShortObjectFieldWithMinAndMaxValue();
        podamValidationSteps.thePojoShouldNotBeNull(shortObjectFieldWithMinAndMaxValue);
        podamValidationSteps.theShortValueShouldBeBetween(shortObjectFieldWithMinAndMaxValue,
                PodamTestConstants.NUMBER_INT_MIN_VALUE,
                PodamTestConstants.NUMBER_INT_ONE_HUNDRED);

        short shortFieldWithPreciseValue = pojo.getShortFieldWithPreciseValue();
        podamValidationSteps.theShortPreciseValueShouldBe(shortFieldWithPreciseValue,
                Short.valueOf(PodamTestConstants.SHORT_PRECISE_VALUE));

    }

    @Test
    @Title("Podam should handle both native and wrapped char values with @PodamCharValue annotation")
    public void podamShouldHandleCharValuesWithThePodamCharValueAnnotation() throws Exception {

        PodamFactory podamFactory = podamFactorySteps.givenAStandardPodamFactory();
        CharValuePojo pojo = podamInvocationSteps.whenIInvokeTheFactoryForClass(CharValuePojo.class, podamFactory);

        //TODO To Serenify it

        Assert.assertNotNull("The pojo cannot be null!", pojo);

        char charFieldWithMinValueOnly = pojo.getCharFieldWithMinValueOnly();
        Assert.assertTrue(
                "The char attribute with min value only should have a value greater than "
                        + PodamTestConstants.NUMBER_INT_MIN_VALUE,
                charFieldWithMinValueOnly >= PodamTestConstants.NUMBER_INT_MIN_VALUE);

        char charFieldWithMaxValueOnly = pojo.getCharFieldWithMaxValueOnly();
        Assert.assertTrue(
                "The char attribute with max value only should have a value less or equal than "
                        + PodamTestConstants.NUMBER_INT_ONE_HUNDRED,
                charFieldWithMaxValueOnly <= PodamTestConstants.NUMBER_INT_ONE_HUNDRED);

        char charFieldWithMinAndMaxValue = pojo
                .getCharFieldWithMinAndMaxValue();
        Assert.assertTrue(
                "The char attribute with min and max value must have a value between "
                        + PodamTestConstants.NUMBER_INT_MIN_VALUE + " and "
                        + PodamTestConstants.NUMBER_INT_ONE_HUNDRED,
                charFieldWithMinAndMaxValue >= PodamTestConstants.NUMBER_INT_MIN_VALUE
                        && charFieldWithMinAndMaxValue <= PodamTestConstants.NUMBER_INT_ONE_HUNDRED);

        Character charObjectFieldWithMinValueOnly = pojo
                .getCharObjectFieldWithMinValueOnly();
        Assert.assertNotNull(
                "The char object attribute with min value only  cannot be null!",
                charObjectFieldWithMinValueOnly);
        Assert.assertTrue(
                "The char object attribute with min value only should have a value greater than "
                        + PodamTestConstants.NUMBER_INT_MIN_VALUE,
                charObjectFieldWithMinValueOnly >= PodamTestConstants.NUMBER_INT_MIN_VALUE);

        Character charObjectFieldWithMaxValueOnly = pojo
                .getCharObjectFieldWithMaxValueOnly();
        Assert.assertNotNull(
                "The char object attribute with max value only cannot be null!",
                charObjectFieldWithMaxValueOnly);
        Assert.assertTrue(
                "The char object attribute with max value only should have a value less or equal than "
                        + PodamTestConstants.NUMBER_INT_ONE_HUNDRED,
                charObjectFieldWithMaxValueOnly <= PodamTestConstants.NUMBER_INT_ONE_HUNDRED);

        Character charObjectFieldWithMinAndMaxValue = pojo
                .getCharObjectFieldWithMinAndMaxValue();
        Assert.assertNotNull(
                "The char object attribute with min and max value cannot be null!",
                charObjectFieldWithMinAndMaxValue);
        Assert.assertTrue(
                "The char object attribute with min and max value must have a value between "
                        + PodamTestConstants.NUMBER_INT_MIN_VALUE + " and "
                        + PodamTestConstants.NUMBER_INT_ONE_HUNDRED,
                charObjectFieldWithMinAndMaxValue >= PodamTestConstants.NUMBER_INT_MIN_VALUE
                        && charObjectFieldWithMinAndMaxValue <= PodamTestConstants.NUMBER_INT_ONE_HUNDRED);

        char charFieldWithPreciseValue = pojo.getCharFieldWithPreciseValue();
        Assert.assertTrue(
                "The character field with precise value should have a value of "
                        + PodamTestConstants.CHAR_PRECISE_VALUE,
                charFieldWithPreciseValue == PodamTestConstants.CHAR_PRECISE_VALUE);

        char charFieldWithBlankInPreciseValue = pojo
                .getCharFieldWithBlankInPreciseValue();

        Assert.assertTrue(
                "The value for the char field with an empty char in the precise value and no other annotation attributes should be zero",
                charFieldWithBlankInPreciseValue == 0);
    }


    @Test
    @Title("Podam should handle both native and wrapped boolean values with @PodamBooleanValue annotation")
    public void podamShouldHandleBooleanValuesWithThePodamBooleanValueAnnotation() throws Exception {

        PodamFactory podamFactory = podamFactorySteps.givenAStandardPodamFactory();
        BooleanValuePojo pojo = podamInvocationSteps.whenIInvokeTheFactoryForClass(BooleanValuePojo.class, podamFactory);

        //TODO To Serenify it

        Assert.assertNotNull("The pojo cannot be null!", pojo);

        boolean boolDefaultToTrue = pojo.isBoolDefaultToTrue();
        Assert.assertTrue(
                "The boolean attribute forced to true should be true!",
                boolDefaultToTrue);

        boolean boolDefaultToFalse = pojo.isBoolDefaultToFalse();
        Assert.assertFalse(
                "The boolean attribute forced to false should be false!",
                boolDefaultToFalse);

        Boolean boolObjectDefaultToFalse = pojo.getBoolObjectDefaultToFalse();
        Assert.assertNotNull(
                "The boolean object forced to false should not be null!",
                boolObjectDefaultToFalse);
        Assert.assertFalse(
                "The boolean object forced to false should have a value of false!",
                boolObjectDefaultToFalse);

        Boolean boolObjectDefaultToTrue = pojo.getBoolObjectDefaultToTrue();
        Assert.assertNotNull(
                "The boolean object forced to true should not be null!",
                boolObjectDefaultToTrue);
        Assert.assertTrue(
                "The boolean object forced to true should have a value of true!",
                boolObjectDefaultToTrue);

    }

    @Test
    @Title("Podam should handle both native and wrapped float values with @PodamFloatValue annotation")
    public void podamShouldHandleFloatValuesWithThePodamFloatValueAnnotation() throws Exception {

        PodamFactory podamFactory = podamFactorySteps.givenAStandardPodamFactory();
        FloatValuePojo pojo = podamInvocationSteps.whenIInvokeTheFactoryForClass(FloatValuePojo.class, podamFactory);

        Assert.assertNotNull("The pojo cannot be null!", pojo);

        float floatFieldWithMinValueOnly = pojo.getFloatFieldWithMinValueOnly();
        Assert.assertTrue(
                "The float field with min value only must have value greater than "
                        + PodamTestConstants.NUMBER_FLOAT_MIN_VALUE,
                floatFieldWithMinValueOnly >= PodamTestConstants.NUMBER_FLOAT_MIN_VALUE);

        float floatFieldWithMaxValueOnly = pojo.getFloatFieldWithMaxValueOnly();
        Assert.assertTrue(
                "The float field with max value only can only have a value less or equal than "
                        + PodamTestConstants.NUMBER_FLOAT_ONE_HUNDRED,
                floatFieldWithMaxValueOnly <= PodamTestConstants.NUMBER_FLOAT_ONE_HUNDRED);

        float floatFieldWithMinAndMaxValue = pojo
                .getFloatFieldWithMinAndMaxValue();
        Assert.assertTrue(
                "The float field with min and max value must have a value between "
                        + PodamTestConstants.NUMBER_FLOAT_MIN_VALUE + " and "
                        + PodamTestConstants.NUMBER_FLOAT_MAX_VALUE,
                floatFieldWithMinAndMaxValue >= PodamTestConstants.NUMBER_FLOAT_MIN_VALUE
                        && floatFieldWithMinAndMaxValue <= PodamTestConstants.NUMBER_FLOAT_MAX_VALUE);

        Float floatObjectFieldWithMinValueOnly = pojo
                .getFloatObjectFieldWithMinValueOnly();
        Assert.assertNotNull(
                "The float object attribute with min value only cannot be null!",
                floatObjectFieldWithMinValueOnly);
        Assert.assertTrue(
                "The float object attribute with min value only must have a value greater or equal than "
                        + PodamTestConstants.NUMBER_FLOAT_MIN_VALUE,
                floatObjectFieldWithMinValueOnly >= PodamTestConstants.NUMBER_FLOAT_MIN_VALUE);

        Float floatObjectFieldWithMaxValueOnly = pojo
                .getFloatObjectFieldWithMaxValueOnly();
        Assert.assertNotNull(
                "The float object attribute with max value only cannot be null!",
                floatObjectFieldWithMaxValueOnly);
        Assert.assertTrue(
                "The float object attribute with max value only must have a value less than or equal to "
                        + PodamTestConstants.NUMBER_FLOAT_ONE_HUNDRED,
                floatObjectFieldWithMaxValueOnly <= PodamTestConstants.NUMBER_FLOAT_ONE_HUNDRED);

        Float floatObjectFieldWithMinAndMaxValue = pojo
                .getFloatObjectFieldWithMinAndMaxValue();
        Assert.assertNotNull(
                "The float object attribute with min and max value cannot be null!",
                floatObjectFieldWithMinAndMaxValue);
        Assert.assertTrue(
                "The float object attribute with min and max value only must have a value between "
                        + PodamTestConstants.NUMBER_FLOAT_MIN_VALUE
                        + " and "
                        + PodamTestConstants.NUMBER_FLOAT_MAX_VALUE,
                floatObjectFieldWithMinAndMaxValue >= PodamTestConstants.NUMBER_FLOAT_MIN_VALUE
                        && floatObjectFieldWithMinAndMaxValue <= PodamTestConstants.NUMBER_FLOAT_MAX_VALUE);

        float floatFieldWithPreciseValue = pojo.getFloatFieldWithPreciseValue();
        Assert.assertTrue(
                "The float field with precise value should have a value of "
                        + PodamTestConstants.FLOAT_PRECISE_VALUE,
                floatFieldWithPreciseValue == Float
                        .valueOf(PodamTestConstants.FLOAT_PRECISE_VALUE));

        Float floatObjectFieldWithPreciseValue = pojo
                .getFloatObjectFieldWithPreciseValue();
        Assert.assertNotNull(
                "The float object field with precise value cannot be null!",
                floatObjectFieldWithPreciseValue);
        Assert.assertTrue(
                "The float object field with precise value should have a value of "
                        + PodamTestConstants.FLOAT_PRECISE_VALUE,
                floatObjectFieldWithPreciseValue.floatValue() == Float.valueOf(
                        PodamTestConstants.FLOAT_PRECISE_VALUE).floatValue());

    }


}
