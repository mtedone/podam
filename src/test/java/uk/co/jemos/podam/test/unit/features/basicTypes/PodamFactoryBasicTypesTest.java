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

        podamValidationSteps.theObjectShouldNotBeNull(oneDimensionalTestPojo);

        podamValidationSteps.thePojoShouldContainSomeData(oneDimensionalTestPojo);

        oneDimentionalPojoValidationSteps.validateDimensionalTestPojo(oneDimensionalTestPojo, podamFactory.getStrategy());

    }

    @Test
    @Title("Podam should fill POJOs with non default constructors")
    public void podamShouldFillPojosWithNonDefaultConstructor() throws Exception {

        PodamFactory podamFactory = podamFactorySteps.givenAStandardPodamFactory();

        NoDefaultConstructorPojo pojo = podamInvocationSteps.whenIInvokeTheFactoryForClass(NoDefaultConstructorPojo.class, podamFactory);

        podamValidationSteps.theObjectShouldNotBeNull(pojo);

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
        podamValidationSteps.theObjectShouldNotBeNull(recursivePojo);
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
    @Title("Podam should fill in POJOs which have a circular dependency")
    public void podamShouldSupportCircularDependencies() throws Exception {

        PodamFactory podamFactory = podamFactorySteps.givenAStandardPodamFactory();
        Parent parent = podamInvocationSteps.whenIInvokeTheFactoryForClass(Parent.class, podamFactory);
        podamValidationSteps.theObjectShouldNotBeNull(parent);
        Child child = parent.getChild();
        podamValidationSteps.theChildPojoShouldNotBeNull(child);

    }

    @Test
    @Title("Podam should fill in lists of the containing class type")
    public void podamShouldSupportRecursiveLists() throws Exception {

        PodamFactory podamFactory = podamFactorySteps.givenAStandardPodamFactory();
        RecursiveList recursiveListPojo = podamInvocationSteps.whenIInvokeTheFactoryForClass(RecursiveList.class, podamFactory);
        podamValidationSteps.theObjectShouldNotBeNull(recursiveListPojo);
        recursivePojoValidationSteps.thePojoListShouldNotBeNull(recursiveListPojo.getList());
        recursivePojoValidationSteps.thePojoListShouldNotBeEmpty(recursiveListPojo.getList());
        podamValidationSteps.eachListElementShouldNotBeNull(recursiveListPojo.getList());
    }

    @Test
    @Title("Podam should fill in Maps of the containing class type")
    public void podamShouldSupportRecursiveMaps() throws Exception {

        PodamFactory podamFactory = podamFactorySteps.givenAStandardPodamFactory();
        RecursiveMap recursiveMap = podamInvocationSteps.whenIInvokeTheFactoryForClass(RecursiveMap.class, podamFactory);
        podamValidationSteps.theObjectShouldNotBeNull(recursiveMap);
        recursivePojoValidationSteps.thePojoMapShouldNotBeNull(recursiveMap.getMap());
        recursivePojoValidationSteps.thePojoMapShouldNotBeEmpty(recursiveMap.getMap());
        podamValidationSteps.eachMapElementShouldNotBeNull(recursiveMap.getMap());

    }

    @Test
    @Title("Podam should handle immutable non annotated POJOs")
    public void podamShouldHandleImmutableNonAnnotatedPojos() throws Exception {

        PodamFactory podamFactory = podamFactorySteps.givenAStandardPodamFactory();
        ImmutableNonAnnotatedPojo pojo =
                podamInvocationSteps.whenIInvokeTheFactoryForClass(ImmutableNonAnnotatedPojo.class, podamFactory);
        podamValidationSteps.theObjectShouldNotBeNull(pojo);
        podamValidationSteps.theIntFieldShouldNotBeZero(pojo.getIntField());
        podamValidationSteps.theCalendarFieldShouldNotBeNull(pojo.getDateCreated());
        podamValidationSteps.theDateObjectShouldNotBeNull(pojo.getDateCreated().getTime());
        podamValidationSteps.theLongArrayShouldNotBeNullOrEmpty(pojo.getLongArray());
        podamValidationSteps.theLongValueShouldNotBeZero(pojo.getLongArray()[0]);
    }


    @Test
    @Title("Podam should fill in POJOs with Enums")
    public void podamShouldFillPojoWithEnums() throws Exception {

        PodamFactory podamFactory = podamFactorySteps.givenAStandardPodamFactory();
        EnumsPojo pojo = podamInvocationSteps.whenIInvokeTheFactoryForClass(EnumsPojo.class, podamFactory);
        podamValidationSteps.theObjectShouldNotBeNull(pojo);

        ExternalRatePodamEnum ratePodamExternal = pojo.getRatePodamExternal();
        podamValidationSteps.theObjectShouldNotBeNull(ratePodamExternal);

        EnumsPojo.RatePodamInternal ratePodamInternal = pojo.getRatePodamInternal();
        podamValidationSteps.theObjectShouldNotBeNull(ratePodamInternal);

    }

    @Test
    @Title("Podam should fill Java native types")
    public void podamShouldFillJavaNativeTypes() throws Exception {

        PodamFactory podamFactory = podamFactorySteps.givenAStandardPodamFactory();

        String pojo = podamInvocationSteps.whenIInvokeTheFactoryForClassWithFullConstructor(String.class, podamFactory);
        podamValidationSteps.theStringFieldCannotBeNullOrEmpty(pojo);

        Integer integerPojo = podamInvocationSteps.whenIInvokeTheFactoryForClass(Integer.class, podamFactory);
        podamValidationSteps.theIntegerObjectFieldShouldNotBeNull(integerPojo);

        Calendar calendarPojo = podamInvocationSteps.whenIInvokeTheFactoryForClass(GregorianCalendar.class, podamFactory);
        podamValidationSteps.theCalendarFieldShouldNotBeNull(calendarPojo);

        Date datePojo = podamInvocationSteps.whenIInvokeTheFactoryForClass(Date.class, podamFactory);
        podamValidationSteps.theDateObjectShouldNotBeNull(datePojo);

        BigDecimal bigDecimalPojo = podamInvocationSteps.whenIInvokeTheFactoryForClass(BigDecimal.class, podamFactory);
        podamValidationSteps.theObjectShouldNotBeNull(bigDecimalPojo);

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
