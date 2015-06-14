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
import uk.co.jemos.podam.test.strategies.ByteArrayStrategy;
import uk.co.jemos.podam.test.unit.AbstractPodamSteps;
import uk.co.jemos.podam.test.utils.PodamTestConstants;
import uk.co.jemos.podam.test.utils.PodamTestUtils;

import java.util.Calendar;
import java.util.List;
import java.util.Map;
import java.util.Set;

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
        podamValidationSteps.theObjectShouldNotBeNull(pojo);
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
        podamValidationSteps.theObjectShouldNotBeNull(pojo);
        constructorSelfReferenceValidationSteps.theFirstSelfReferenceForPojoWithDefaultConstructorShouldNotBeNull(pojo);
        constructorSelfReferenceValidationSteps.theSecondSelfReferenceForPojoWithDefaultConstructorShouldNotBeNull(pojo);

    }

    @Test
    @Title("Podam should not fill POJO's attributes annotated with @PodamExclude")
    public void podamShouldNotFillFieldsAnnotatedWithExcludeAnnotation() throws Exception {

        PodamFactory podamFactory = podamFactorySteps.givenAStandardPodamFactory();
        ExcludeAnnotationPojo pojo = podamInvocationSteps.whenIInvokeTheFactoryForClass(ExcludeAnnotationPojo.class, podamFactory);
        podamValidationSteps.theObjectShouldNotBeNull(pojo);
        podamValidationSteps.theIntFieldShouldNotBeZero(pojo.getIntField());
        podamValidationSteps.anyFieldWithPodamExcludeAnnotationShouldBeNull(pojo.getSomePojo());

    }

    @Test
    @Title("Podam should handle both native and wrapped integer values with @PodamIntValue annotation")
    public void podamShouldHandleIntegerValues() throws Exception {

        PodamFactory podamFactory = podamFactorySteps.givenAStandardPodamFactory();
        IntegerValuePojo pojo = podamInvocationSteps.whenIInvokeTheFactoryForClass(IntegerValuePojo.class, podamFactory);
        podamValidationSteps.theObjectShouldNotBeNull(pojo);
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
        podamValidationSteps.theObjectShouldNotBeNull(pojo);
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
        podamValidationSteps.theObjectShouldNotBeNull(pojo);

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
        podamValidationSteps.theObjectShouldNotBeNull(byteObjectFieldWithMinValueOnly);
        podamValidationSteps.theByteValueShouldBeGreaterOrEqualThan(
                byteObjectFieldWithMinValueOnly, PodamTestConstants.NUMBER_INT_MIN_VALUE);

        Byte byteObjectFieldWithMaxValueOnly = pojo
                .getByteObjectFieldWithMaxValueOnly();
        podamValidationSteps.theObjectShouldNotBeNull(byteFieldWithMaxValueOnly);
        podamValidationSteps.theByteValueShouldBeLowerOrEqualThan(
                byteObjectFieldWithMaxValueOnly, PodamTestConstants.NUMBER_INT_ONE_HUNDRED);

        Byte byteObjectFieldWithMinAndMaxValue = pojo
                .getByteObjectFieldWithMinAndMaxValue();
        podamValidationSteps.theObjectShouldNotBeNull(byteObjectFieldWithMinAndMaxValue);
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

        podamValidationSteps.theObjectShouldNotBeNull(pojo);

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
        podamValidationSteps.theObjectShouldNotBeNull(shortFieldWithMinValueOnly);
        podamValidationSteps.theShortValueShouldBeGreaterOrEqualThan(shortObjectFieldWithMinValueOnly,
                PodamTestConstants.NUMBER_INT_MIN_VALUE);

        Short shortObjectFieldWithMaxValueOnly = pojo
                .getShortObjectFieldWithMaxValueOnly();

        podamValidationSteps.theObjectShouldNotBeNull(shortFieldWithMaxValueOnly);
        podamValidationSteps.theShortValueShouldBeLowerOrEqualThan(shortObjectFieldWithMaxValueOnly,
                PodamTestConstants.NUMBER_INT_ONE_HUNDRED);

        Short shortObjectFieldWithMinAndMaxValue = pojo
                .getShortObjectFieldWithMinAndMaxValue();
        podamValidationSteps.theObjectShouldNotBeNull(shortObjectFieldWithMinAndMaxValue);
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
        podamValidationSteps.theObjectShouldNotBeNull(pojo);

        char charFieldWithMinValueOnly = pojo.getCharFieldWithMinValueOnly();
        podamValidationSteps.theCharValueShouldBeGreaterOrEqualThan(
                charFieldWithMinValueOnly, (char) PodamTestConstants.NUMBER_INT_MIN_VALUE);

        char charFieldWithMaxValueOnly = pojo.getCharFieldWithMaxValueOnly();
        podamValidationSteps.theCharValueShouldBeLowerOrEqualThan(charFieldWithMaxValueOnly,
                PodamTestConstants.NUMBER_INT_ONE_HUNDRED);

        char charFieldWithMinAndMaxValue = pojo
                .getCharFieldWithMinAndMaxValue();
        podamValidationSteps.theCharValueShouldBeBetween(charFieldWithMinAndMaxValue,
                PodamTestConstants.NUMBER_INT_MIN_VALUE,
                PodamTestConstants.NUMBER_INT_ONE_HUNDRED);

        Character charObjectFieldWithMinValueOnly = pojo
                .getCharObjectFieldWithMinValueOnly();
        podamValidationSteps.theObjectShouldNotBeNull(charObjectFieldWithMinValueOnly);
        podamValidationSteps.theCharValueShouldBeGreaterOrEqualThan(charObjectFieldWithMinValueOnly,
                (char)PodamTestConstants.NUMBER_INT_MIN_VALUE);

        Character charObjectFieldWithMaxValueOnly = pojo
                .getCharObjectFieldWithMaxValueOnly();
        podamValidationSteps.theCharValueShouldBeLowerOrEqualThan(charObjectFieldWithMaxValueOnly,
                 PodamTestConstants.NUMBER_INT_ONE_HUNDRED);

        Character charObjectFieldWithMinAndMaxValue = pojo
                .getCharObjectFieldWithMinAndMaxValue();
        podamValidationSteps.theObjectShouldNotBeNull(charObjectFieldWithMinAndMaxValue);

        podamValidationSteps.theCharValueShouldBeBetween(charObjectFieldWithMinAndMaxValue,
                PodamTestConstants.NUMBER_INT_MIN_VALUE,
                PodamTestConstants.NUMBER_INT_ONE_HUNDRED);

        char charFieldWithPreciseValue = pojo.getCharFieldWithPreciseValue();
        podamValidationSteps.theCharValueShouldBeExactly(charFieldWithPreciseValue,
                PodamTestConstants.CHAR_PRECISE_VALUE);

        char charFieldWithBlankInPreciseValue = pojo
                .getCharFieldWithBlankInPreciseValue();

        podamValidationSteps.theCharValueShouldBeExactly(charFieldWithBlankInPreciseValue, '\u0000');
    }


    @Test
    @Title("Podam should handle both native and wrapped boolean values with @PodamBooleanValue annotation")
    public void podamShouldHandleBooleanValuesWithThePodamBooleanValueAnnotation() throws Exception {

        PodamFactory podamFactory = podamFactorySteps.givenAStandardPodamFactory();
        BooleanValuePojo pojo = podamInvocationSteps.whenIInvokeTheFactoryForClass(BooleanValuePojo.class, podamFactory);

        podamValidationSteps.theObjectShouldNotBeNull(pojo);

        boolean boolDefaultToTrue = pojo.isBoolDefaultToTrue();
        podamValidationSteps.theBooleanValueIsTrue(boolDefaultToTrue);

        boolean boolDefaultToFalse = pojo.isBoolDefaultToFalse();
        podamValidationSteps.theBooleanValueShouldBeFalse(boolDefaultToFalse);

        Boolean boolObjectDefaultToFalse = pojo.getBoolObjectDefaultToFalse();
        podamValidationSteps.theObjectShouldNotBeNull(boolObjectDefaultToFalse);
        podamValidationSteps.theBooleanValueShouldBeFalse(boolObjectDefaultToFalse);

        Boolean boolObjectDefaultToTrue = pojo.getBoolObjectDefaultToTrue();
        podamValidationSteps.theObjectShouldNotBeNull(boolObjectDefaultToTrue);
        podamValidationSteps.theBooleanValueIsTrue(boolObjectDefaultToTrue);

    }

    @Test
    @Title("Podam should handle both native and wrapped float values with @PodamFloatValue annotation")
    public void podamShouldHandleFloatValuesWithThePodamFloatValueAnnotation() throws Exception {

        PodamFactory podamFactory = podamFactorySteps.givenAStandardPodamFactory();
        FloatValuePojo pojo = podamInvocationSteps.whenIInvokeTheFactoryForClass(FloatValuePojo.class, podamFactory);

        Assert.assertNotNull("The pojo cannot be null!", pojo);
        podamValidationSteps.theObjectShouldNotBeNull(pojo);

        float floatFieldWithMinValueOnly = pojo.getFloatFieldWithMinValueOnly();
        podamValidationSteps.theFloatValueShouldBeGreaterOrEqualThan(floatFieldWithMinValueOnly,
                PodamTestConstants.NUMBER_FLOAT_MIN_VALUE);

        float floatFieldWithMaxValueOnly = pojo.getFloatFieldWithMaxValueOnly();
        podamValidationSteps.theFloatValueShouldBeLowerOrEqualThan(floatFieldWithMaxValueOnly,
                PodamTestConstants.NUMBER_FLOAT_ONE_HUNDRED);

        float floatFieldWithMinAndMaxValue = pojo
                .getFloatFieldWithMinAndMaxValue();
        podamValidationSteps.theFloatValueShouldBeBetween(floatFieldWithMinAndMaxValue,
                PodamTestConstants.NUMBER_FLOAT_MIN_VALUE,
                PodamTestConstants.NUMBER_FLOAT_MAX_VALUE);

        Float floatObjectFieldWithMinValueOnly = pojo
                .getFloatObjectFieldWithMinValueOnly();
        podamValidationSteps.theObjectShouldNotBeNull(floatObjectFieldWithMinValueOnly);
        podamValidationSteps.theFloatValueShouldBeGreaterOrEqualThan(floatObjectFieldWithMinValueOnly,
                PodamTestConstants.NUMBER_FLOAT_MIN_VALUE);

        Float floatObjectFieldWithMaxValueOnly = pojo
                .getFloatObjectFieldWithMaxValueOnly();
        podamValidationSteps.theObjectShouldNotBeNull(floatObjectFieldWithMaxValueOnly);
        podamValidationSteps.theFloatValueShouldBeLowerOrEqualThan(floatObjectFieldWithMaxValueOnly,
                PodamTestConstants.NUMBER_FLOAT_ONE_HUNDRED);

        Float floatObjectFieldWithMinAndMaxValue = pojo
                .getFloatObjectFieldWithMinAndMaxValue();
        podamValidationSteps.theObjectShouldNotBeNull(floatObjectFieldWithMinAndMaxValue);
        podamValidationSteps.theFloatValueShouldBeBetween(floatObjectFieldWithMinAndMaxValue,
                PodamTestConstants.NUMBER_FLOAT_MIN_VALUE,
                PodamTestConstants.NUMBER_FLOAT_MAX_VALUE);

        float floatFieldWithPreciseValue = pojo.getFloatFieldWithPreciseValue();
        podamValidationSteps.theFloatValueShouldBePrecisely(floatFieldWithPreciseValue,
                Float.valueOf(PodamTestConstants.FLOAT_PRECISE_VALUE));

        Float floatObjectFieldWithPreciseValue = pojo
                .getFloatObjectFieldWithPreciseValue();
        podamValidationSteps.theObjectShouldNotBeNull(floatObjectFieldWithPreciseValue);
        podamValidationSteps.theFloatValueShouldBePrecisely(floatObjectFieldWithPreciseValue,
                Float.valueOf(PodamTestConstants.FLOAT_PRECISE_VALUE));

    }


    @Test
    @Title("Podam should handle both native and wrapped double values with @PodamDoubleValue annotation")
    public void podamShouldHandleDoubleValuesWithThePodamDoubleValueAnnotation() throws Exception {

        PodamFactory podamFactory = podamFactorySteps.givenAStandardPodamFactory();
        DoubleValuePojo pojo = podamInvocationSteps.whenIInvokeTheFactoryForClass(DoubleValuePojo.class, podamFactory);
        podamValidationSteps.theObjectShouldNotBeNull(pojo);

        double doubleFieldWithMinValueOnly = pojo
                .getDoubleFieldWithMinValueOnly();
        podamValidationSteps.theDoubleValueShouldBeGreaterOrEqualThan(doubleFieldWithMinValueOnly,
                PodamTestConstants.NUMBER_DOUBLE_MIN_VALUE);

        double doubleFieldWithMaxValueOnly = pojo
                .getDoubleFieldWithMaxValueOnly();
        podamValidationSteps.theDoubleValueShouldBeLowerOrEqualThan(doubleFieldWithMaxValueOnly,
                PodamTestConstants.NUMBER_DOUBLE_ONE_HUNDRED);

        double doubleFieldWithMinAndMaxValue = pojo
                .getDoubleFieldWithMinAndMaxValue();
        podamValidationSteps.theDoubleValueShouldBeBetween(doubleFieldWithMinAndMaxValue,
                PodamTestConstants.NUMBER_DOUBLE_MIN_VALUE, PodamTestConstants.NUMBER_DOUBLE_MAX_VALUE);

        double doubleFieldWithPreciseValue = pojo
                .getDoubleFieldWithPreciseValue();
        podamValidationSteps.theDoubleValueShouldBeExactly(doubleFieldWithPreciseValue,
                Double.valueOf(PodamTestConstants.DOUBLE_PRECISE_VALUE));

        Double doubleObjectFieldWithPreciseValue = pojo
                .getDoubleObjectFieldWithPreciseValue();
        podamValidationSteps.theObjectShouldNotBeNull(doubleObjectFieldWithPreciseValue);
        Assert.assertTrue(
                "The double object field with precise value should have a value of: "
                        + PodamTestConstants.DOUBLE_PRECISE_VALUE,
                doubleObjectFieldWithPreciseValue.doubleValue() == Double
                        .valueOf(PodamTestConstants.DOUBLE_PRECISE_VALUE)
                        .doubleValue());
        podamValidationSteps.theDoubleValueShouldBeExactly(doubleObjectFieldWithPreciseValue,
                Double.valueOf(PodamTestConstants.DOUBLE_PRECISE_VALUE));

    }

    @Test
    @Title("Podam should assign precise values and lengths when a String is annotated with @PodamStringValue")
    public void podamShouldAssignPreciseValuesWithTheStringValueAnnotation() throws Exception {

        PodamFactory podamFactory = podamFactorySteps.givenAStandardPodamFactory();
        StringValuePojo pojo = podamInvocationSteps.whenIInvokeTheFactoryForClass(StringValuePojo.class, podamFactory);

        String twentyLengthString = pojo.getTwentyLengthString();
        podamValidationSteps.theObjectShouldNotBeNull(twentyLengthString);
        podamValidationSteps.theStringValueShouldHaveTheExactLengthOf(twentyLengthString,
                PodamTestConstants.STR_ANNOTATION_TWENTY_LENGTH);

        String preciseValueString = pojo.getPreciseValueString();
        podamValidationSteps.theObjectShouldNotBeNull(preciseValueString);
        podamValidationSteps.theStringValueShouldBeExactly(
                preciseValueString, PodamTestConstants.STR_ANNOTATION_PRECISE_VALUE);

    }


    @Test
    @Title("The @PodamCollection annotation should allow to set sizes on all collections and arrays")
    public void thePodamCollectionAnnotationShouldWorkOnAllCollections() throws Exception {

        PodamFactory podamFactory = podamFactorySteps.givenAStandardPodamFactory();
        CollectionAnnotationPojo pojo =
                podamInvocationSteps.whenIInvokeTheFactoryForClass(CollectionAnnotationPojo.class, podamFactory);
        podamValidationSteps.theObjectShouldNotBeNull(pojo);

        List<String> strList = pojo.getStrList();
        podamValidationSteps.theListShouldNotBeNullAndContainAtLeastOneNonEmptyElement(strList);
        podamValidationSteps.theListShouldHaveExactlyTheExpectedNumberOfElements(strList,
                PodamTestConstants.ANNOTATION_COLLECTION_NBR_ELEMENTS);

        String[] strArray = pojo.getStrArray();
        podamValidationSteps.theArrayOfStringsShouldNotBeNullOrEmpty(strArray);
        podamValidationSteps.theArrayOfStringsShouldHaveExactlyTheExpectedNumberOfElements(strArray,
                PodamTestConstants.ANNOTATION_COLLECTION_NBR_ELEMENTS);

        Map<String, String> stringMap = pojo.getStringMap();
        podamValidationSteps.theMapShouldContainAtLeastOneNonEmptyElement(stringMap);
        podamValidationSteps.theMapShouldHaveExactlyTheExpectedNumberOfElements(stringMap,
                PodamTestConstants.ANNOTATION_COLLECTION_NBR_ELEMENTS);

    }


    @Test
    @Title("Podam should assign exactly the values specified with the @PodamStrategyValue annotation")
    public void podamShouldAssignExactValuesDefinedInPodamStrategyValueAnnotation() throws Exception {

        PodamFactory podamFactory = podamFactorySteps.givenAStandardPodamFactory();
        PodamStrategyPojo pojo = podamInvocationSteps.whenIInvokeTheFactoryForClass(PodamStrategyPojo.class, podamFactory);
        podamValidationSteps.theObjectShouldNotBeNull(pojo);

        String postCode = pojo.getPostCode();
        podamValidationSteps.theStringFieldCannotBeNullOrEmpty(postCode);
        podamValidationSteps.theStringValueShouldBeExactly(postCode, PodamTestConstants.POST_CODE);

        String postCode2 = pojo.getPostCode2();
        podamValidationSteps.theStringFieldCannotBeNullOrEmpty(postCode2);
        podamValidationSteps.theStringValueShouldBeExactly(postCode2, PodamTestConstants.POST_CODE);

        String postCode3 = pojo.getPostCode3();
        podamValidationSteps.theStringFieldCannotBeNullOrEmpty(postCode3);
        podamValidationSteps.theStringValueShouldBeExactly(postCode3, PodamTestConstants.POST_CODE);

        podamValidationSteps.theArrayOfBytesShouldNotBeNullOrEmpty(pojo.getByteData());
        podamValidationSteps.theArrayOfBytesShouldBeExactlyOfLength(pojo.getByteData(),
                ByteArrayStrategy.LENGTH);

        Calendar expectedBirthday = PodamTestUtils.getMyBirthday();
        Calendar myBirthday = pojo.getMyBirthday();
        podamValidationSteps.theTwoCalendarObjectsShouldHaveTheSameTime(expectedBirthday, myBirthday);

        List<Calendar> myBirthdays = pojo.getMyBirthdays();
        podamValidationSteps.theListShouldNotBeNullAndContainAtLeastOneNonEmptyElement(myBirthdays);

        for (Calendar birthday : myBirthdays) {
            podamValidationSteps.theTwoCalendarObjectsShouldHaveTheSameTime(expectedBirthday, birthday);
        }

        Calendar[] myBirthdaysArray = pojo.getMyBirthdaysArray();
        podamValidationSteps.theArrayOfCalendarsShouldNotBeNullOrEmpty(myBirthdaysArray);

        for (Calendar birthday : myBirthdaysArray) {
            podamValidationSteps.theTwoCalendarObjectsShouldHaveTheSameTime(expectedBirthday, birthday);
        }

        List<Object> objectList = pojo.getObjectList();
        podamValidationSteps.theListShouldNotBeNullAndContainAtLeastOneNonEmptyElement(objectList);

        Object[] myObjectArray = pojo.getMyObjectArray();
        podamValidationSteps.theArrayOfObjectsShouldNotBeNullOrEmpty(myObjectArray);

        List nonGenericObjectList = pojo.getNonGenericObjectList();
        podamValidationSteps.theListShouldNotBeNullAndContainAtLeastOneNonEmptyElement(nonGenericObjectList);

        Map<String, Calendar> myBirthdaysMap = pojo.getMyBirthdaysMap();
        podamValidationSteps.theMapShouldContainAtLeastOneNonEmptyElement(myBirthdaysMap);

        Set<String> keySet = myBirthdaysMap.keySet();
        for (String key : keySet) {
            podamValidationSteps.theTwoCalendarObjectsShouldHaveTheSameTime(expectedBirthday, myBirthdaysMap.get(key));

        }

    }



}
