package uk.co.jemos.podam.test.unit.features.collections;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Title;
import org.junit.Test;
import org.junit.runner.RunWith;
import uk.co.jemos.podam.api.DataProviderStrategy;
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
        podamValidationSteps.thePojoMustBeOfTheType(pojo, CollectionsPojo.class);
        List<String> strList = pojo.getStrList();
        podamValidationSteps.theCollectionShouldNotBeNullOrEmptyAndContainElementsOfType(strList, String.class);
        ArrayList<String> arrayListStr = pojo.getArrayListStr();
        podamValidationSteps.theCollectionShouldNotBeNullOrEmptyAndContainElementsOfType(arrayListStr, String.class);
        List<String> copyOnWriteList = pojo.getCopyOnWriteList();
        podamValidationSteps.theCollectionShouldNotBeNullOrEmptyAndContainElementsOfType(copyOnWriteList, String.class);
        HashSet<String> hashSetStr = pojo.getHashSetStr();
        podamValidationSteps.theCollectionShouldNotBeNullOrEmptyAndContainElementsOfType(hashSetStr, String.class);
        List<String> listStrCollection = new ArrayList<String>(
                pojo.getStrCollection());
        podamValidationSteps.theCollectionShouldNotBeNullOrEmptyAndContainElementsOfType(listStrCollection, String.class);
        Set<String> setStrCollection = new HashSet<String>(
                pojo.getStrCollection());
        podamValidationSteps.theCollectionShouldNotBeNullOrEmptyAndContainElementsOfType(
                setStrCollection, String.class);
        Set<String> strSet = pojo.getStrSet();
        podamValidationSteps.theCollectionShouldNotBeNullOrEmptyAndContainElementsOfType(
                strSet, String.class);
        Map<String, OneDimensionalTestPojo> map = pojo.getMap();
        podamValidationSteps.theMapShouldNotBeNullOrEmptyAndContainElementsOfType(map, String.class, OneDimensionalTestPojo.class);
        HashMap<String, OneDimensionalTestPojo> hashMap = pojo.getHashMap();
        podamValidationSteps.theMapShouldNotBeNullOrEmptyAndContainElementsOfType(hashMap, String.class, OneDimensionalTestPojo.class);
        ConcurrentMap<String, OneDimensionalTestPojo> concurrentHashMap = pojo
                .getConcurrentHashMap();
        podamValidationSteps.thePojoMustBeOfTheType(concurrentHashMap, ConcurrentHashMap.class);
        podamValidationSteps.theMapShouldNotBeNullOrEmptyAndContainElementsOfType(
                concurrentHashMap, String.class, OneDimensionalTestPojo.class);
        ConcurrentHashMap<String, OneDimensionalTestPojo> concurrentHashMapImpl = pojo
                .getConcurrentHashMapImpl();
        podamValidationSteps.thePojoMustBeOfTheType(concurrentHashMapImpl, ConcurrentHashMap.class);
        podamValidationSteps.theMapShouldNotBeNullOrEmptyAndContainElementsOfType(
                concurrentHashMapImpl, String.class, OneDimensionalTestPojo.class);
        Queue<SimplePojoToTestSetters> queue = pojo.getQueue();
        podamValidationSteps.thePojoMustBeOfTheType(queue, LinkedList.class);

        SimplePojoToTestSetters pojoQueueElement = queue.poll();
        podamValidationSteps.thePojoMustBeOfTheType(pojoQueueElement, SimplePojoToTestSetters.class);

        List<?> nonGenerifiedList = pojo.getNonGenerifiedList();
        podamValidationSteps.theCollectionShouldNotBeNullOrEmptyAndContainElementsOfType(nonGenerifiedList, Object.class);

        List<?> looseCoupledNonGenerifiedList = pojo.getLooseCoupledNonGenerifiedList();
        podamValidationSteps.theCollectionShouldNotBeNullOrEmptyAndContainElementsOfType(looseCoupledNonGenerifiedList, Object.class);

        Set<?> looseCoupledNonGenerifiedSet = pojo.getLooseCoupledNonGenerifiedSet();
        podamValidationSteps.theCollectionShouldNotBeNullOrEmptyAndContainElementsOfType(looseCoupledNonGenerifiedSet, Object.class);

        Map<?,?> nonGenerifiedMap = pojo.getNonGenerifiedMap();
        podamValidationSteps.theMapShouldNotBeNullOrEmptyAndContainElementsOfType(nonGenerifiedMap, Object.class, Object.class);

        Map<?,?> looseCoupledNonGenerifiedMap = pojo.getLooseCoupledNonGenerifiedMap();
        podamValidationSteps.theMapShouldNotBeNullOrEmptyAndContainElementsOfType(looseCoupledNonGenerifiedMap, Object.class, Object.class);
    }

    @Test
    @Title("Podam should handle POJOs with no setters and collections in the constructor")
    public void podamShouldHandlePojosWithNoSettersAndCollectionsInTheConstructor() throws Exception {

        PodamFactory podamFactory = podamFactorySteps.givenAStandardPodamFactory();
        CollectionReadOnlyPojo pojo =
                podamInvocationSteps.whenIInvokeTheFactoryForClass(CollectionReadOnlyPojo.class, podamFactory);
        podamValidationSteps.thePojoMustBeOfTheType(pojo, CollectionReadOnlyPojo.class);
        List<Date> dates = pojo.getDates();
        podamValidationSteps.theCollectionShouldNotBeNullOrEmptyAndShouldHaveExactlyTheExpectedNumberOfElements(dates, Date.class, 2);
        List<String> strList = pojo.getStrList();
        podamValidationSteps.theCollectionShouldNotBeNullOrEmptyAndShouldHaveExactlyTheExpectedNumberOfElements(strList, String.class, 5);
        int intField = pojo.getIntField();
        podamValidationSteps.theIntFieldShouldNotBeZero(intField);

    }

    @Test
    @Title("Podam should handle immutable Pojos with non generic collections")
    public void podamShouldHandleImmutablePojosWithNonGenericCollections() throws Exception {

        PodamFactory podamFactory = podamFactorySteps.givenAStandardPodamFactory();
        ImmutableWithNonGenericCollectionsPojo pojo =
                podamInvocationSteps.whenIInvokeTheFactoryForClass(ImmutableWithNonGenericCollectionsPojo.class, podamFactory);

        podamValidationSteps.thePojoMustBeOfTheType(pojo, ImmutableWithNonGenericCollectionsPojo.class);

        Collection<?> nonGenerifiedCollection = pojo.getNonGenerifiedCollection();
        podamValidationSteps.theCollectionShouldNotBeNullOrEmptyAndShouldHaveExactlyTheExpectedNumberOfElements(
                nonGenerifiedCollection, Object.class, ImmutableWithNonGenericCollectionsPojo.NBR_ELEMENTS);

        Set<?> nonGenerifiedSet = pojo.getNonGenerifiedSet();
        podamValidationSteps.theCollectionShouldNotBeNullOrEmptyAndShouldHaveExactlyTheExpectedNumberOfElements(
                nonGenerifiedSet, Object.class, ImmutableWithNonGenericCollectionsPojo.NBR_ELEMENTS);
        Map<?, ?> nonGenerifiedMap = pojo.getNonGenerifiedMap();
        podamValidationSteps.theMapShouldNotBeNullOrEmptyAndShouldHaveExactlyTheExpectedNumberOfElements(
                nonGenerifiedMap, Object.class, Object.class, ImmutableWithNonGenericCollectionsPojo.NBR_ELEMENTS);

    }

    @Test
    @Title("Podam should handle immutable POJOs with generified collections in the constructor")
    public void podamShouldHandleImmutablePojoWithGenerifiedCollectionsInConstructor() throws Exception {

        PodamFactory podamFactory = podamFactorySteps.givenAStandardPodamFactory();
        ImmutableWithGenericCollectionsPojo pojo =
                podamInvocationSteps.whenIInvokeTheFactoryForClass(ImmutableWithGenericCollectionsPojo.class, podamFactory);
        podamValidationSteps.thePojoMustBeOfTheType(pojo, ImmutableWithGenericCollectionsPojo.class);

        Collection<OneDimensionalTestPojo> generifiedCollection = pojo
                .getGenerifiedCollection();
        podamValidationSteps.theCollectionShouldNotBeNullOrEmptyAndShouldHaveExactlyTheExpectedNumberOfElements(
                generifiedCollection, OneDimensionalTestPojo.class, ImmutableWithNonGenericCollectionsPojo.NBR_ELEMENTS);

        Map<String, Calendar> generifiedMap = pojo.getGenerifiedMap();
        podamValidationSteps.theMapShouldNotBeNullOrEmptyAndShouldHaveExactlyTheExpectedNumberOfElements(
                generifiedMap, String.class, GregorianCalendar.class, ImmutableWithNonGenericCollectionsPojo.NBR_ELEMENTS);
        Set<ImmutableWithNonGenericCollectionsPojo> generifiedSet = pojo
                .getGenerifiedSet();
        podamValidationSteps.theCollectionShouldNotBeNullOrEmptyAndShouldHaveExactlyTheExpectedNumberOfElements(
                generifiedSet, ImmutableWithNonGenericCollectionsPojo.class, ImmutableWithNonGenericCollectionsPojo.NBR_ELEMENTS);

    }


    @Test
    @Title("Podam should be able to create instances of Sorted Maps")
    public void testSortedMapCreation() {
        testMap(SortedMap.class);
    }

    @Test
    @Title("Podam should be able to create instances of Concurrent Hash Maps")
    public void testConcurrentMapCreation() {
        testMap(ConcurrentMap.class);
    }

    @Test
    @Title("Podam should be able to create instances of Hash Maps")
    public void testHashMapCreation() {
        testMap(Map.class);
    }


    @Test
    @Title("Podam should fill in POJOs with attributes containing wildcards")
    public void podamShouldFillInPojosWithAttributesContainingWildcards() throws Exception {

        PodamFactory podamFactory = podamFactorySteps.givenAStandardPodamFactory();

        ReadOnlyWildcardFieldsPojo pojo	= podamInvocationSteps.whenIInvokeTheFactoryForClass(
                ReadOnlyWildcardFieldsPojo.class, podamFactory);

        podamValidationSteps.thePojoMustBeOfTheType(pojo, ReadOnlyWildcardFieldsPojo.class);
        podamValidationSteps.theCollectionShouldNotBeNullOrEmptyAndContainElementsOfType(pojo.getList(), Object.class);
        podamValidationSteps.theMapShouldNotBeNullOrEmptyAndContainElementsOfType(
                pojo.getMap(), Object.class, Object.class);
    }

    @Test
    @Title("Podam should be able to fill in POJOs with raw type collection attributes and default values to Object")
    public void podamShouldBeAbleToFillInPojosWithRawTypeCollectionAttributesAndDefaultValueToObject() throws Exception {

        PodamFactory podamFactory = podamFactorySteps.givenAStandardPodamFactory();

        ReadOnlyRawFieldsPojo pojo = podamInvocationSteps.whenIInvokeTheFactoryForClass(
                ReadOnlyRawFieldsPojo.class, podamFactory);
        podamValidationSteps.thePojoMustBeOfTheType(pojo, ReadOnlyRawFieldsPojo.class);
        podamValidationSteps.theCollectionShouldNotBeNullOrEmptyAndContainElementsOfType(pojo.getList(), Object.class);
        podamValidationSteps.theMapShouldNotBeNullOrEmptyAndContainElementsOfType(
                pojo.getMap(), Object.class, Object.class);
    }


    //------------------> Private methods

    private void testMap(Class<? extends Map> mapType) {

        PodamFactory podamFactory = podamFactorySteps.givenAStandardPodamFactory();

        DataProviderStrategy strategy = podamFactory.getStrategy();

        int mapSize = strategy.getNumberOfCollectionElements(PodamTestInterface.class);

        if (ConcurrentMap.class.isAssignableFrom(mapType)) {
            mapSize = 0;
        }

        Map<?,?> pojo = podamInvocationSteps.whenIInvokeTheFactoryForGenericTypeWithSpecificType(
                mapType, podamFactory, String.class, PodamTestInterface.class);


        podamValidationSteps.thePojoMustBeOfTheType(pojo, Map.class);
        podamValidationSteps.theTwoObjectsShouldBeEqual(mapSize, pojo.keySet().size());
        podamValidationSteps.theTwoObjectsShouldBeEqual(mapSize, pojo.values().size());
    }


}
