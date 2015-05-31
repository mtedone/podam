package uk.co.jemos.podam.test.unit.features.collections;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Title;
import org.junit.Test;
import org.junit.runner.RunWith;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.test.dto.CollectionsPojo;
import uk.co.jemos.podam.test.dto.NoSetterWithCollectionInConstructorPojo;
import uk.co.jemos.podam.test.dto.OneDimensionalTestPojo;
import uk.co.jemos.podam.test.dto.SimplePojoToTestSetters;
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
        podamValidationSteps.theSetOfStringsShouldContainAtleastOneNonEmptyElement(hashSetStr);
        List<String> listStrCollection = new ArrayList<String>(
                pojo.getStrCollection());
        podamValidationSteps.theListShouldNotBeNullAndContainAtLeastOneNonEmptyElement(listStrCollection);
        Set<String> setStrCollection = new HashSet<String>(
                pojo.getStrCollection());
        podamValidationSteps.theSetOfStringsShouldContainAtleastOneNonEmptyElement(setStrCollection);
        Set<String> strSet = pojo.getStrSet();
        podamValidationSteps.theSetOfStringsShouldContainAtleastOneNonEmptyElement(strSet);
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

}
