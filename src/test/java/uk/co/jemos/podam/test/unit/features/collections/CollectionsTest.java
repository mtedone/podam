package uk.co.jemos.podam.test.unit.features.collections;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Title;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.test.dto.*;
import uk.co.jemos.podam.test.unit.AbstractPodamSteps;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * Created by tedonema on 31/05/2015.
 */
@RunWith(SerenityRunner.class)
public class CollectionsTest extends AbstractPodamSteps {

    @Test
    @Title("Podam should handle standard collections")
    public void podamShouldHandleStandardCollections() throws Exception {

        PodamFactory podamFactory = podamFactorySteps.givenAStandardPodamFactory();
        CollectionsPojo pojo = podamInvocationSteps.whenIInvokeTheFactoryForClass(CollectionsPojo.class, podamFactory);
        podamValidationSteps.theObjectShouldNotBeNull(pojo);
        List<String> strList = pojo.getStrList();
        podamValidationSteps.theListShouldNotBeNullAndContainAtLeastOneNonEmptyElement(strList);
        ArrayList<String> arrayListStr = pojo.getArrayListStr();
        podamValidationSteps.theListShouldNotBeNullAndContainAtLeastOneNonEmptyElement(arrayListStr);
        List<String> copyOnWriteList = pojo.getCopyOnWriteList();
        podamValidationSteps.theListShouldNotBeNullAndContainAtLeastOneNonEmptyElement(copyOnWriteList);
        HashSet<String> hashSetStr = pojo.getHashSetStr();
        podamValidationSteps.theSetShouldContainAtleastOneNonEmptyElement(hashSetStr);
        List<String> listStrCollection = new ArrayList<String>(
                pojo.getStrCollection());
        podamValidationSteps.theListShouldNotBeNullAndContainAtLeastOneNonEmptyElement(listStrCollection);
        Set<String> setStrCollection = new HashSet<String>(
                pojo.getStrCollection());
        podamValidationSteps.theSetShouldContainAtleastOneNonEmptyElement(setStrCollection);
        Set<String> strSet = pojo.getStrSet();
        podamValidationSteps.theSetShouldContainAtleastOneNonEmptyElement(strSet);
        Map<String, OneDimensionalTestPojo> map = pojo.getMap();
        podamValidationSteps.theMapShouldContainAtLeastOneNonEmptyElement(map);
        HashMap<String, OneDimensionalTestPojo> hashMap = pojo.getHashMap();
        podamValidationSteps.theMapShouldContainAtLeastOneNonEmptyElement(hashMap);
        ConcurrentMap<String, OneDimensionalTestPojo> concurrentHashMap = pojo
                .getConcurrentHashMap();
        podamValidationSteps.theConcurrentHashMapOfStringsObjectsShouldContainAtLeastOneNonEmptyElement(concurrentHashMap);
        ConcurrentHashMap<String, OneDimensionalTestPojo> concurrentHashMapImpl = pojo
                .getConcurrentHashMapImpl();
        podamValidationSteps.theConcurrentHashMapOfStringsObjectsShouldContainAtLeastOneNonEmptyElement(concurrentHashMapImpl);
        Queue<SimplePojoToTestSetters> queue = pojo.getQueue();
        podamValidationSteps.theQueueCannotBeNull(queue);
        podamValidationSteps.theQueueMustBeAnInstanceOf(queue, LinkedList.class);

        SimplePojoToTestSetters pojoQueueElement = queue.poll();
        podamValidationSteps.theObjectShouldNotBeNull(pojoQueueElement);
        List nonGenerifiedList = pojo.getNonGenerifiedList();
        podamValidationSteps.theNonGenerifiedListShouldNotBeNullOrEmpty(nonGenerifiedList);

        Map<?,?> nonGenerifiedMap = pojo.getNonGenerifiedMap();
        podamValidationSteps.theNonGenerifiedMapShouldNotBeNullOrEmpty(nonGenerifiedMap);

        Object object = nonGenerifiedMap.get(nonGenerifiedMap.keySet()
                .iterator().next());
        podamValidationSteps.theObjectShouldNotBeNull(object);
    }

    @Test
    @Title("Podam should handle POJOs with no setters and collections in the constructor")
    public void podamShouldHandlePojosWithNoSettersAndCollectionsInTheConstructor() throws Exception {

        PodamFactory podamFactory = podamFactorySteps.givenAStandardPodamFactory();
        NoSetterWithCollectionInConstructorPojo pojo =
                podamInvocationSteps.whenIInvokeTheFactoryForClass(NoSetterWithCollectionInConstructorPojo.class, podamFactory);
        podamValidationSteps.theObjectShouldNotBeNull(pojo);
        List<String> strList = pojo.getStrList();
        podamValidationSteps.theListShouldNotBeNullAndContainAtLeastOneNonEmptyElement(strList);
        int intField = pojo.getIntField();
        podamValidationSteps.theIntFieldShouldNotBeZero(intField);

    }

    @Test
    @Title("Podam should handle immutable Pojos with non generic collections")
    public void podamShouldHandleImmutablePojosWithNonGenericCollections() throws Exception {

        PodamFactory podamFactory = podamFactorySteps.givenAStandardPodamFactory();
        ImmutableWithNonGenericCollectionsPojo pojo =
                podamInvocationSteps.whenIInvokeTheFactoryForClass(ImmutableWithNonGenericCollectionsPojo.class, podamFactory);

        podamValidationSteps.theObjectShouldNotBeNull(pojo);

        Collection<Object> nonGenerifiedCollection = pojo
                .getNonGenerifiedCollection();
        podamValidationSteps.theCollectionShouldNotBeNullOrEmpty(pojo.getNonGenerifiedCollection());
        podamValidationSteps.theCollectionShouldHaveExactlyTheExpectedNumberOfElements(nonGenerifiedCollection,
                ImmutableWithNonGenericCollectionsPojo.NBR_ELEMENTS);

        Set<Object> nonGenerifiedSet = pojo.getNonGenerifiedSet();
        podamValidationSteps.theSetShouldContainAtleastOneNonEmptyElement(nonGenerifiedSet);
        podamValidationSteps.theCollectionShouldHaveExactlyTheExpectedNumberOfElements(
                nonGenerifiedSet, ImmutableWithNonGenericCollectionsPojo.NBR_ELEMENTS);
        Map<Object, Object> nonGenerifiedMap = pojo.getNonGenerifiedMap();
        podamValidationSteps.theMapShouldContainAtLeastOneNonEmptyElement(nonGenerifiedMap);
        Assert.assertTrue(
                "The number of elements in the map: " + nonGenerifiedMap.size()
                        + " does not match the expected value: "
                        + ImmutableWithNonGenericCollectionsPojo.NBR_ELEMENTS,
                nonGenerifiedMap.size() == ImmutableWithNonGenericCollectionsPojo.NBR_ELEMENTS);
        podamValidationSteps.theMapShouldHaveExactlyTheExpectedNumberOfElements(nonGenerifiedMap,
                ImmutableWithNonGenericCollectionsPojo.NBR_ELEMENTS);

    }

    @Test
    @Title("Podam should handle immutable POJOs with generified collections in the constructor")
    public void podamShouldHandleImmutablePojoWithGenerifiedCollectionsInConstructor() throws Exception {

        PodamFactory podamFactory = podamFactorySteps.givenAStandardPodamFactory();
        ImmutableWithGenericCollectionsPojo pojo =
                podamInvocationSteps.whenIInvokeTheFactoryForClass(ImmutableWithGenericCollectionsPojo.class, podamFactory);
        podamValidationSteps.theObjectShouldNotBeNull(pojo);

        Collection<OneDimensionalTestPojo> generifiedCollection = pojo
                .getGenerifiedCollection();
        podamValidationSteps.theCollectionShouldNotBeNullOrEmpty(generifiedCollection);
        podamValidationSteps.theCollectionShouldHaveExactlyTheExpectedNumberOfElements(generifiedCollection,
                ImmutableWithNonGenericCollectionsPojo.NBR_ELEMENTS);

        Map<String, Calendar> generifiedMap = pojo.getGenerifiedMap();
        podamValidationSteps.theMapShouldContainAtLeastOneNonEmptyElement(generifiedMap);
        podamValidationSteps.theMapShouldHaveExactlyTheExpectedNumberOfElements(generifiedMap,
                ImmutableWithNonGenericCollectionsPojo.NBR_ELEMENTS);
        Set<ImmutableWithNonGenericCollectionsPojo> generifiedSet = pojo
                .getGenerifiedSet();
        podamValidationSteps.theCollectionShouldNotBeNullOrEmpty(generifiedSet);
        podamValidationSteps.theCollectionShouldHaveExactlyTheExpectedNumberOfElements(generifiedSet,
                ImmutableWithNonGenericCollectionsPojo.NBR_ELEMENTS);

    }


}
