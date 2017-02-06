package uk.co.jemos.podam.test.unit.features.basicTypes;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Title;
import org.junit.Test;
import org.junit.runner.RunWith;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.test.dto.*;
import uk.co.jemos.podam.test.dto.pdm6.Child;
import uk.co.jemos.podam.test.dto.pdm6.Parent;
import uk.co.jemos.podam.test.dto.pdm6.RecursiveList;
import uk.co.jemos.podam.test.dto.pdm6.RecursiveMap;
import uk.co.jemos.podam.test.enums.ExternalRatePodamEnum;
import uk.co.jemos.podam.test.unit.AbstractPodamSteps;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Created by tedonema on 27/05/2015.
 */
@RunWith(SerenityRunner.class)
public class PodamFactoryBasicTypesTest extends AbstractPodamSteps {

    @Test
    @Title("Podam should fill in a POJO with basic jvm types")
    public void podamShouldGenerateBasicTypes() throws Exception {

        PodamFactory podamFactory = podamFactorySteps.givenAStandardPodamFactory();

        OneDimensionalTestPojo oneDimensionalTestPojo = podamInvocationSteps.whenIInvokeTheFactoryForClass(OneDimensionalTestPojo.class, podamFactory);

        podamValidationSteps.thePojoMustBeOfTheType(oneDimensionalTestPojo, OneDimensionalTestPojo.class);

        podamValidationSteps.thePojoShouldContainSomeData(oneDimensionalTestPojo);

        oneDimentionalPojoValidationSteps.validateDimensionalTestPojo(oneDimensionalTestPojo, podamFactory.getStrategy());

    }

    @Test
    @Title("Podam should fill POJOs with non default constructors")
    public void podamShouldFillPojosWithNonDefaultConstructor() throws Exception {

        PodamFactory podamFactory = podamFactorySteps.givenAStandardPodamFactory();

        NoDefaultConstructorPojo pojo = podamInvocationSteps.whenIInvokeTheFactoryForClass(NoDefaultConstructorPojo.class, podamFactory);

        podamValidationSteps.thePojoMustBeOfTheType(pojo, NoDefaultConstructorPojo.class);

    }

    @Test
    @Title("Invoking Podam on an Abstract class should return a null pojo")
    public void invokingPodamOnAbstractClassShouldReturnANullPojo() throws Exception {

        PodamFactory podamFactory = podamFactorySteps.givenAStandardPodamFactory();
        AbstractTestPojo pojo = podamInvocationSteps.whenIInvokeTheFactoryForClass(AbstractTestPojo.class, podamFactory);
        podamValidationSteps.thePojoShouldBeNull(pojo);

    }

    @Test
    @Title("Invoking Podam on an interface should return an empty POJO")
    public void invokingPodamOnAnInterfaceShouldReturnAnEmptyPojo() throws Exception {

        PodamFactory podamFactory = podamFactorySteps.givenAStandardPodamFactory();
        InterfacePojo pojo = podamInvocationSteps.whenIInvokeTheFactoryForClass(InterfacePojo.class, podamFactory);
        podamValidationSteps.thePojoShouldBeNull(pojo);

    }


    @Test
    @Title("Podam should fill recursive POJOs correctly, including all their fields")
    public void podamShouldFillRecursivePojos() throws Exception {

        PodamFactory podamFactory = podamFactorySteps.givenAStandardPodamFactory();
        RecursivePojo recursivePojo = podamInvocationSteps.whenIInvokeTheFactoryForClass(RecursivePojo.class, podamFactory);
        podamValidationSteps.thePojoMustBeOfTheType(recursivePojo, RecursivePojo.class);
        recursivePojoValidationSteps.allPojosInTheRecursiveStrategyShouldBeValid(recursivePojo);
    }

    @Test
    @Title("Podam should fill recursive POJOs when invoking the factory population directly")
    public void podamShouldFillRecursivePojosWhenInvokingPopulationDirectly() throws Exception {

        PodamFactory podamFactory = podamFactorySteps.givenAStandardPodamFactory();
        RecursivePojo pojo = new RecursivePojo();
        podamInvocationSteps.whenIInvokeThePojoPopulationDirectly(pojo, podamFactory);
        recursivePojoValidationSteps.allPojosInTheRecursiveStrategyShouldBeValid(pojo);

    }

    @Test
    @Title("Podam should fill array when invoking the factory population directly")
    public void podamShouldFillArrays() throws Exception {

        PodamFactory podamFactory = podamFactorySteps.givenAStandardPodamFactory();
        String[] pojo = new String[4];
        podamInvocationSteps.whenIInvokeThePojoPopulationDirectly(pojo, podamFactory);
		podamValidationSteps.theArrayOfTheGivenTypeShouldNotBeNullOrEmptyAndContainElementsOfTheRightType(pojo, String.class);
    }

    @Test
    @Title("Podam should fill in POJOs which have a circular dependency")
    public void podamShouldSupportCircularDependencies() throws Exception {

        PodamFactory podamFactory = podamFactorySteps.givenAStandardPodamFactory();
        Parent parent = podamInvocationSteps.whenIInvokeTheFactoryForClass(Parent.class, podamFactory);
        podamValidationSteps.thePojoMustBeOfTheType(parent, Parent.class);
        Child child = parent.getChild();
        podamValidationSteps.thePojoMustBeOfTheType(child, Child.class);

    }

    @Test
    @Title("Podam should fill in lists of the containing class type")
    public void podamShouldSupportRecursiveLists() throws Exception {

        PodamFactory podamFactory = podamFactorySteps.givenAStandardPodamFactory();
        RecursiveList recursiveListPojo = podamInvocationSteps.whenIInvokeTheFactoryForClass(RecursiveList.class, podamFactory);
        podamValidationSteps.thePojoMustBeOfTheType(recursiveListPojo, RecursiveList.class);
        podamValidationSteps.theCollectionShouldNotBeNullOrEmptyAndContainElementsOfType(recursiveListPojo.getList(), RecursiveList.class);
    }

    @Test
    @Title("Podam should fill in Maps of the containing class type")
    public void podamShouldSupportRecursiveMaps() throws Exception {

        PodamFactory podamFactory = podamFactorySteps.givenAStandardPodamFactory();
        RecursiveMap recursiveMap = podamInvocationSteps.whenIInvokeTheFactoryForClass(RecursiveMap.class, podamFactory);
        podamValidationSteps.thePojoMustBeOfTheType(recursiveMap, RecursiveMap.class);
        podamValidationSteps.theMapShouldNotBeNullOrEmptyAndContainElementsOfType(recursiveMap.getMap(), String.class, RecursiveMap.class);

    }

    @Test
    @Title("Podam should handle immutable non annotated POJOs")
    public void podamShouldHandleImmutableNonAnnotatedPojos() throws Exception {

        PodamFactory podamFactory = podamFactorySteps.givenAStandardPodamFactory();
        ImmutableNonAnnotatedPojo pojo =
                podamInvocationSteps.whenIInvokeTheFactoryForClass(ImmutableNonAnnotatedPojo.class, podamFactory);
        podamValidationSteps.thePojoMustBeOfTheType(pojo, ImmutableNonAnnotatedPojo.class);
        podamValidationSteps.theIntFieldShouldNotBeZero(pojo.getIntField());
        podamValidationSteps.thePojoMustBeOfTheType(pojo.getDateCreated(), GregorianCalendar.class);
        podamValidationSteps.thePojoMustBeOfTheType(pojo.getDateCreated().getTime(), Date.class);
        podamValidationSteps.theArrayOfTheGivenTypeShouldNotBeNullOrEmptyAndContainElementsOfTheRightType(pojo.getLongArray(), Long.class);
        podamValidationSteps.theLongValueShouldNotBeZero(pojo.getLongArray()[0]);
    }


    @Test
    @Title("Podam should fill in POJOs with Enums")
    public void podamShouldFillPojoWithEnums() throws Exception {

        PodamFactory podamFactory = podamFactorySteps.givenAStandardPodamFactory();
        EnumsPojo pojo = podamInvocationSteps.whenIInvokeTheFactoryForClass(EnumsPojo.class, podamFactory);
        podamValidationSteps.thePojoMustBeOfTheType(pojo, EnumsPojo.class);

        ExternalRatePodamEnum ratePodamExternal = pojo.getRatePodamExternal();
        podamValidationSteps.thePojoMustBeOfTheType(ratePodamExternal, ExternalRatePodamEnum.class);

        EnumsPojo.RatePodamInternal ratePodamInternal = pojo.getRatePodamInternal();
        podamValidationSteps.thePojoMustBeOfTheType(ratePodamInternal, EnumsPojo.RatePodamInternal.class);

        EnumsPojo.EmptyPodamInternal emptyPodamInternal = pojo.getEmptyPodamInternal();
        podamValidationSteps.thePojoShouldBeNull(emptyPodamInternal);
    }

    @Test
    @Title("Podam should fill in wildcard Enum fields")
    public void podamShouldIgnoreWildcardEnumFields() throws Exception {
        PodamFactory podamFactory = podamFactorySteps.givenAStandardPodamFactory();
        WildcardEnumPojo pojo = podamInvocationSteps.whenIInvokeTheFactoryForClass(WildcardEnumPojo.class, podamFactory);
        podamValidationSteps.thePojoMustBeOfTheType(pojo, WildcardEnumPojo.class);

        Enum<?> wildcardEnumField = pojo.getWildcardEnumField();
        podamValidationSteps.thePojoMustBeOfTheType(wildcardEnumField, Enum.class);
    }

    @Test
    @Title("Podam should fill Java string type")
    public void podamShouldFillJavaStringType() throws Exception {

        PodamFactory podamFactory = podamFactorySteps.givenAStandardPodamFactory();

        String pojo = podamInvocationSteps.whenIInvokeTheFactoryForClass(String.class, podamFactory);
        podamValidationSteps.theStringFieldCannotBeNullOrEmpty(pojo);
    }

    @Test
    @Title("Podam should fill Java string type with full constructor")
    public void podamShouldFillJavaStringTypeWithFullConstructor() throws Exception {

        PodamFactory podamFactory = podamFactorySteps.givenAStandardPodamFactory();

        String pojo = podamInvocationSteps.whenIInvokeTheFactoryForClassWithFullConstructor(String.class, podamFactory);
        podamValidationSteps.theStringFieldCannotBeNullOrEmpty(pojo);
    }

    @Test
    @Title("Podam should fill Java native types")
    public void podamShouldFillJavaNativeTypes() throws Exception {

        PodamFactory podamFactory = podamFactorySteps.givenAStandardPodamFactory();

        Integer integerPojo = podamInvocationSteps.whenIInvokeTheFactoryForClass(Integer.class, podamFactory);
        podamValidationSteps.thePojoMustBeOfTheType(integerPojo, Integer.class);

        Calendar calendarPojo = podamInvocationSteps.whenIInvokeTheFactoryForClass(GregorianCalendar.class, podamFactory);
        podamValidationSteps.thePojoMustBeOfTheType(calendarPojo, GregorianCalendar.class);

        Date datePojo = podamInvocationSteps.whenIInvokeTheFactoryForClass(Date.class, podamFactory);
        podamValidationSteps.thePojoMustBeOfTheType(datePojo, Date.class);

        BigDecimal bigDecimalPojo = podamInvocationSteps.whenIInvokeTheFactoryForClass(BigDecimal.class, podamFactory);
        podamValidationSteps.thePojoMustBeOfTheType(bigDecimalPojo, BigDecimal.class);

    }

    @Test
    @Title("Podam should fill arrays with elements")
    public void podamShouldFillArraysWithElements() throws Exception {

        PodamFactory podamFactory = podamFactorySteps.givenAStandardPodamFactory();
        ArrayPojo pojo = podamInvocationSteps.whenIInvokeTheFactoryForClass(ArrayPojo.class, podamFactory);
        podamValidationSteps.theArrayOfTheGivenTypeShouldNotBeNullOrEmptyAndContainElementsOfTheRightType(
                pojo.getMyStringArray(), String.class);
        podamValidationSteps.theArrayOfTheGivenTypeShouldNotBeNullOrEmptyAndContainElementsOfTheRightType(
                pojo.getMyObjectArray(), Object.class);
    }

}
