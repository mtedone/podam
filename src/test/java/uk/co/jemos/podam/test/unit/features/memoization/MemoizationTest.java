package uk.co.jemos.podam.test.unit.features.memoization;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Title;
import org.junit.Test;
import org.junit.runner.RunWith;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.test.dto.MemoizationPojo;
import uk.co.jemos.podam.test.dto.RecursivePojo;
import uk.co.jemos.podam.test.dto.SimplePojoToTestSetters;
import uk.co.jemos.podam.test.unit.AbstractPodamSteps;

import javax.xml.ws.Holder;
import java.util.Currency;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by tedonema on 20/06/2015.
 */
@RunWith(SerenityRunner.class)
public class MemoizationTest extends AbstractPodamSteps {

    @Test
    @Title("When memoization is set to true Podam should return the same instance for different invocations")
    public void whenMemoizationIsTruePodamShouldReturnTheSameInstanceForDifferentInvocations() throws Exception {

        PodamFactory podamFactory = podamFactorySteps.givenAStandardPodamFactoryWithMemoizationEnabled();
        podamValidationSteps.theMemoizationShouldBeEnabled(podamFactory.getStrategy(), true);

        SimplePojoToTestSetters pojo1 = podamInvocationSteps.whenIInvokeTheFactoryForClass(
                SimplePojoToTestSetters.class, podamFactory);
        podamValidationSteps.thePojoMustBeOfTheType(pojo1, SimplePojoToTestSetters.class);

        SimplePojoToTestSetters pojo2 = podamInvocationSteps.whenIInvokeTheFactoryForClass(
                SimplePojoToTestSetters.class, podamFactory);
        podamValidationSteps.thePojoMustBeOfTheType(pojo2, SimplePojoToTestSetters.class);
        podamValidationSteps.theTwoObjectsShouldBeStrictlyEqual(pojo1, pojo2);
    }


    @Test
    @Title("When memoization is set to false Podam should return different instances for different invocations")
    public void whenMemoizationIsFalsePodamShouldReturnDifferentInstancesForDifferentInvocations() throws Exception {

        PodamFactory podamFactory = podamFactorySteps.givenAStandardPodamFactory();
        podamFactory.getStrategy().setMemoization(false);
        podamValidationSteps.theMemoizationShouldBeEnabled(podamFactory.getStrategy(), false);

        SimplePojoToTestSetters pojo1 = podamInvocationSteps.whenIInvokeTheFactoryForClass(
                SimplePojoToTestSetters.class, podamFactory);
        podamValidationSteps.thePojoMustBeOfTheType(pojo1, SimplePojoToTestSetters.class);

        SimplePojoToTestSetters pojo2 = podamInvocationSteps.whenIInvokeTheFactoryForClass(
                SimplePojoToTestSetters.class, podamFactory);
        podamValidationSteps.thePojoMustBeOfTheType(pojo2, SimplePojoToTestSetters.class);
        podamValidationSteps.theTwoObjectsShouldBeDifferent(pojo1, pojo2);
    }

    @Test
    @Title("Memoization should work for recursive Pojos")
    public void memoizationShouldWorkForRecursivePojos() throws Exception {

        PodamFactory podamFactory = podamFactorySteps.givenAStandardPodamFactoryWithMemoizationEnabled();
        podamValidationSteps.theMemoizationShouldBeEnabled(podamFactory.getStrategy(), true);

        RecursivePojo pojo = podamInvocationSteps.whenIInvokeTheFactoryForClass(RecursivePojo.class, podamFactory);
        podamValidationSteps.thePojoMustBeOfTheType(pojo, RecursivePojo.class);
        podamValidationSteps.theTwoObjectsShouldBeStrictlyEqual(pojo, pojo.getParent());
    }

    @Test
    @Title("Memoization should work with Generics")
    public void memoizationShouldWorkWithGenerics() throws Exception {

        PodamFactory podamFactory = podamFactorySteps.givenAStandardPodamFactoryWithMemoizationEnabled();
        podamValidationSteps.theMemoizationShouldBeEnabled(podamFactory.getStrategy(), true);

        Holder<?> pojo1 = podamInvocationSteps.whenIInvokeTheFactoryForGenericTypeWithSpecificType(
                Holder.class, podamFactory, String.class);
        podamValidationSteps.thePojoMustBeOfTheType(pojo1, Holder.class);

        Holder<?> pojo2 = podamInvocationSteps.whenIInvokeTheFactoryForGenericTypeWithSpecificType(
                Holder.class, podamFactory, String.class);
        podamValidationSteps.thePojoMustBeOfTheType(pojo2, Holder.class);
        podamValidationSteps.theTwoObjectsShouldBeStrictlyEqual(pojo1, pojo2);
        podamValidationSteps.theTwoObjectsShouldBeStrictlyEqual(pojo1.value, pojo2.value);
    }

    @Test
    @Title("Even when memoization is true, if generics Pojos have different types, objects should not be equal")
    public void evenWhenMemoizationIsTrueIfGenericPojosHaveDifferentTypesObjectsShouldNotBeEqual() throws Exception {

        PodamFactory podamFactory = podamFactorySteps.givenAStandardPodamFactory();
        podamValidationSteps.theMemoizationShouldBeEnabled(podamFactory.getStrategy(), false);

        Holder<?> pojo1 = podamInvocationSteps.whenIInvokeTheFactoryForGenericTypeWithSpecificType(
                Holder.class, podamFactory, String.class);
        podamValidationSteps.thePojoMustBeOfTheType(pojo1, Holder.class);

        Holder<?> pojo2 = podamInvocationSteps.whenIInvokeTheFactoryForGenericTypeWithSpecificType(
                Holder.class, podamFactory, Integer.class);
        podamValidationSteps.thePojoMustBeOfTheType(pojo2, Holder.class);
        podamValidationSteps.theTwoObjectsShouldBeDifferent(pojo1, pojo2);
    }

    @Test
    @Title("Memoization should work correctly for collections and arrays")
    public void memoizationShouldWorkCorrectlyForCollectionsAndArrays() throws Exception {

        PodamFactory podamFactory = podamFactorySteps.givenAStandardPodamFactory();
        podamValidationSteps.theMemoizationShouldBeEnabled(podamFactory.getStrategy(), false);

        MemoizationPojo pojo = podamInvocationSteps.whenIInvokeTheFactoryForClass(MemoizationPojo.class, podamFactory);
        podamValidationSteps.thePojoMustBeOfTheType(pojo, MemoizationPojo.class);
        podamValidationSteps.theArrayOfTheGivenTypeShouldNotBeNullOrEmptyAndContainElementsOfTheRightType(
                pojo.getArray(), Currency.class);
        Set<Currency> countingSet = new HashSet<Currency>();
        for (Currency currency : pojo.getArray()) {
            countingSet.add(currency);
        }
        podamValidationSteps.theCollectionShouldHaveExactlyTheExpectedNumberOfElements(
                countingSet, podamFactory.getStrategy().getNumberOfCollectionElements(Currency.class)
        );
        podamValidationSteps.theCollectionShouldNotBeNullOrEmptyAndContainElementsOfType(
                pojo.getCollection(), Currency.class);
        podamValidationSteps.theTwoObjectsShouldBeEqual(
                podamFactory.getStrategy().getNumberOfCollectionElements(Currency.class), pojo.getCollection().size());
        podamValidationSteps.theMapShouldNotBeNullOrEmptyAndContainElementsOfType(
                pojo.getMap(), Currency.class, Currency.class);
        podamValidationSteps.theTwoObjectsShouldBeEqual(podamFactory.getStrategy().getNumberOfCollectionElements(
                Currency.class), pojo.getMap().size());
    }


}
