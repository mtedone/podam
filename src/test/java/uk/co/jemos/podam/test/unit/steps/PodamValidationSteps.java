package uk.co.jemos.podam.test.unit.steps;

import net.thucydides.core.annotations.Step;
import org.junit.Assert;
import org.springframework.util.StringUtils;
import uk.co.jemos.podam.test.utils.TypesUtils;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * Created by tedonema on 27/05/2015.
 */
public class PodamValidationSteps {

    @Step("Then the Object should not be null")
    public boolean theObjectShouldNotBeNull(Object pojo) {
        return pojo == null;
    }

    @Step("Then the Pojo should contain some data")
    public boolean thePojoShouldContainSomeData(Object pojo) {
        return pojo.getClass().getDeclaredFields()[0] != null;
    }

    @Step("Then the Pojo should be null")
    public void thePojoShouldBeNull(Object pojo) {
        Assert.assertNull("The pojo should be null", pojo);
    }

    @Step("Then the inner pojo instance variable should not be null")
    public void theInnerPojoInstanceShouldNotBeNull(Object pojo) {
        Assert.assertNotNull("The inner pojo instance variable should not be null", pojo);
    }

    @Step("Then the int field should not be zero")
    public void theIntFieldShouldNotBeZero(int intField) {
        Assert.assertFalse("The integer field should not be zero", intField == 0);
    }

    @Step("Then the child pojo should not be null")
    public void theChildPojoShouldNotBeNull(Object child) {
        Assert.assertNotNull("The child pojo should not be null", child);
    }

    @Step("Then each of the list elements should not be null")
    public void eachListElementShouldNotBeNull(List<?> list) {
        for (int i = 0; i < list.size(); i++) {
            Assert.assertNotNull(list.get(i));
        }
    }

    @Step("Then each of the map elements should not be null")
    public void eachMapElementShouldNotBeNull(Map<?, ?> map) {

        for (Object mapValue : map.values()) {
            Assert.assertNotNull("The pojo's map element cannot be null!",
                    mapValue);
        }
        
    }

    @Step("Then the calendar object should not be null")
    public void theCalendarFieldShouldNotBeNull(Calendar dateCreated) {
        Assert.assertNotNull("The calendar object should not be null", dateCreated);
    }

    @Step("Then the Date object should not be null")
    public void theDateObjectShouldNotBeNull(Date time) {
        Assert.assertNotNull("The Date object should not be null", time);
    }

    @Step("Then the long[] array should not be null or empty")
    public void theLongArrayShouldNotBeNullOrEmpty(long[] array) {
        Assert.assertNotNull("The given long[] array cannot be null", array);
        Assert.assertTrue("The given long[] array should not be null or empty", array.length > 0);
    }

    @Step("The long value cannot be zero")
    public void theLongValueShouldNotBeZero(long value) {
        Assert.assertTrue("The long value cannot be zero", value > 0);
    }

    @Step("Then any field annotated with @PodamExclude should be null")
    public void anyFieldWithPodamExcludeAnnotationShouldBeNull(Object someObject) {
        Assert.assertNull("The field should be null", someObject);
    }

    @Step("Then the integer field should be greater or equal to zero")
    public void theIntFieldShouldBeGreaterOrEqualToZero(int intField) {
        Assert.assertTrue("The integer field should be greater or equal to zero", intField >= 0);
    }

    @Step("Then the integer field {0} should have a value not greater than {1}")
    public void theIntFieldShouldHaveValueNotGreaterThan(int intField, int maxValue) {
        Assert.assertTrue("The int field should have a value <= " + maxValue, intField <= maxValue);
    }

    @Step("Then the integer field {2} should have a value between {0} and {1}")
    public void theIntFieldShouldHaveValueBetween(int minValue, int maxValue, int intField) {
        Assert.assertTrue("The integer field value " + intField +
                " should be between " + minValue + " and " + maxValue,
                intField >= minValue && intField <= maxValue);
    }

    @Step("Then the integer field should not be null")
    public void theIntegerObjectFieldShouldNotBeNull(Integer integerObjectField) {
        Assert.assertNotNull("The integer object field should not be null", integerObjectField);
    }

    @Step("Then the integer field {0} should have the precise value of {1}")
    public void theIntFieldShouldHaveThePreciseValueOf(int intFieldWithPreciseValue, int preciseValue) {
        Assert.assertTrue("The int field hasn't got a precise value", intFieldWithPreciseValue == preciseValue);
    }

    @Step("Then the long field {0} should be greater or equal to zero")
    public void theLongFieldShouldBeGreaterOrEqualToZero(long longFieldValue) {
        Assert.assertTrue("The long field should be >= 0", longFieldValue >= 0);

    }

    @Step("Then the long field {0} should be less than {1}")
    public void theLongFieldShouldHaveValueNotGreaterThan(long longValue, int maxValue) {
        Assert.assertTrue("The long value " + longValue + " should be <= " + maxValue, longValue <= maxValue);
    }

    @Step("Then the long field {2} should have a value comprised between {0} and {1}")
    public void theLongFieldShouldHaveValueBetween(int minValue, int maxValue, long longValue) {
        Assert.assertTrue("The long value should have a value between " +
                minValue + " and " + maxValue,
                longValue >= minValue && longValue <= maxValue);
    }

    @Step("Then the long object value {0} should not be null")
    public void theLongObjectFieldShouldNotBeNull(Long longObjectValue) {
        Assert.assertNotNull("The long object value should not be null", longObjectValue);
    }

    @Step("Then the long value should be precisely {1}")
    public void theLongFieldShouldHaveThePreciseValueOf(long longValueWithPreciseValue, long preciseValue) {
        Assert.assertTrue("The value " + longValueWithPreciseValue + " should be exactly " + preciseValue,
                longValueWithPreciseValue == preciseValue);

    }

    @Step("Then the int value {0} should be less or equal to {1}")
    public void theIntFieldShouldHaveValueLessThen(int intField, int maxValue) {
        Assert.assertTrue("The int value " + intField + " should be <= " + maxValue, intField <= maxValue);
    }

    @Step("Then the calendar value should be valid")
    public void theCalendarFieldShouldBeValid(Calendar calendarField) {
        TypesUtils.checkCalendarIsValid(calendarField);
    }

    @Step("Then the String field {0} cannot be null or empty")
    public void theStringFieldCannotBeNullOrEmpty(String strField) {
        Assert.assertTrue(StringUtils.hasText(strField));
    }

    @Step("Then the List<?> {0} should not be null and contain at least one non-empty element")
    public void theListShouldNotBeNullAndContainAtLeastOneNonEmptyElement(List<?> list) {
        Assert.assertNotNull("The List<String> should not be null!", list);
        Assert.assertFalse("The List<String> cannot be empty!", list.isEmpty());
        Object element = list.get(0);
        Assert.assertNotNull(
                "The List<String> must have a non-null String element", element);
    }

    @Step("Then the Set<?> {0} should contain at least one non-empty element")
    public void theSetShouldContainAtleastOneNonEmptyElement(Set<?> set) {

        Assert.assertNotNull("The Set<?> should not be null!", set);
        Assert.assertFalse("The Set<?> cannot be empty!", set.isEmpty());
        Object element = set.iterator().next();
        Assert.assertNotNull(
                "The Set<?> must have a non-null String element", element);

    }

    @Step("Then the Map<?, ?> {0} should contain at least one non empty element")
    public void theMapShouldContainAtLeastOneNonEmptyElement(Map<?, ?> map) {
        Assert.assertTrue("The map attribute must be of type HashMap",
                map instanceof HashMap);
        Assert.assertNotNull("The map object in the POJO cannot be null", map);
        Set<?> keySet = map.keySet();
        Assert.assertNotNull("The Map must have at least one element", keySet);
        Object o = map.get(keySet
                .iterator().next());

        Assert.assertNotNull("The map element must not be null!",
                o);
    }

    @Step("Then the queue {0} cannot be null")
    public void theQueueCannotBeNull(Queue<?> queue) {
        Assert.assertNotNull("The Queue cannot be null", queue);
    }

    @Step("Then the Queue {0} should be an instance of {1}")
    public void theQueueMustBeAnInstanceOf(Queue<?> queue, Class<LinkedList> linkedListClass) {
        Assert.assertTrue(queue.getClass().isAssignableFrom(linkedListClass));
    }

    @Step("Then the ConcurrentHashMap<String, ?> {0} should contain at least one non-empty element")
    public void theConcurrentHashMapOfStringsObjectsShouldContainAtLeastOneNonEmptyElement(ConcurrentMap<String, ?> map) {
        Assert.assertTrue("The map attribute must be of type HashMap",
                map instanceof ConcurrentHashMap);
        Assert.assertNotNull("The map object in the POJO cannot be null", map);
        Set<String> keySet = map.keySet();
        Assert.assertNotNull("The Map must have at least one element", keySet);
        Object o = map.get(keySet
                .iterator().next());

        Assert.assertNotNull("The map element must not be null!",
                o);
    }

    @Step("Then the non generified List {0} should not be null or empty")
    public void theNonGenerifiedListShouldNotBeNullOrEmpty(List nonGenerifiedList) {
        Assert.assertNotNull(nonGenerifiedList);
        Assert.assertTrue("The non generified list should at least have one element", nonGenerifiedList.size() > 0);
    }

    @Step("Then the non generified Map {0} should not be null or empty")
    public void theNonGenerifiedMapShouldNotBeNullOrEmpty(Map<?, ?> nonGenerifiedMap) {
        Assert.assertNotNull(nonGenerifiedMap);
        Assert.assertTrue("The non generified Map should at least have one element", nonGenerifiedMap.size() > 0);
    }

    @Step("Then the byte value {0} should be greater or equal than {1}")
    public void theByteValueShouldBeGreaterOrEqualThan(byte byteValue, int minValue) {
        Assert.assertTrue("The byte value should be >= " + minValue, byteValue >= minValue);
    }

    @Step("Then the byte value {0} should be lower or equal to {1}")
    public void theByteValueShouldBeLowerOrEqualThan(byte byteValue, int maxValue) {
        Assert.assertTrue("The byte value " + byteValue + " should be <= " + maxValue, byteValue <= maxValue);
    }

    @Step("Then the byte value {0} should be between {1} and {2}")
    public void theByteValueShouldBeBetween(byte byteValue, int minValue, int maxValue) {
        Assert.assertTrue("The byte value should be between " + minValue + " and " + maxValue,
                byteValue >= minValue && byteValue <= maxValue);
    }

    @Step("The byte value {0} should be precisely {1}")
    public void theByteValueShouldHavePreciselyValueOf(byte byteValue, byte preciseValue) {
        Assert.assertTrue("The byte value " + byteValue + " should have a precise value of " + preciseValue,
                byteValue == preciseValue);
    }

    @Step("Then the value {0} should be greater or equal than {1}")
    public void theShortValueShouldBeGreaterOrEqualThan(short shortValue, int minValue) {
        Assert.assertTrue("The value " + shortValue + " should be >= " + minValue, shortValue >= minValue);
    }

    @Step("Then the short value {0} should be lower or equal than {1}")
    public void theShortValueShouldBeLowerOrEqualThan(short shortValue, int maxValue) {
        Assert.assertTrue("The short value " + shortValue + "should be <= " + maxValue,
                shortValue <= maxValue);
    }

    @Step("Then the short value {0} should be between {1} and {2}")
    public void theShortValueShouldBeBetween(short shortValue, int minValue, int maxValue) {
        Assert.assertTrue("The short value " + shortValue + " should be between " + minValue + " and " + maxValue,
                shortValue >= minValue && shortValue <= maxValue);
    }

    @Step("Then the short value {0} should be precisely {1}")
    public void theShortPreciseValueShouldBe(short shortFieldWithPreciseValue, short preciseValue) {
        Assert.assertTrue("The short value " + shortFieldWithPreciseValue + " should be precisely " + preciseValue,
                shortFieldWithPreciseValue == preciseValue);
    }

    @Step("Then the char value should be greater or equal than {1}")
    public void theCharValueShouldBeGreaterOrEqualThan(char charValue, char minValue) {
        Assert.assertTrue("The char value " + charValue + " should be >= " + minValue,
                charValue >= minValue);
    }

    @Step("Then the char value should be lower or equal than {1}")
    public void theCharValueShouldBeLowerOrEqualThan(char charValue, int maxValue) {
        Assert.assertTrue("The char value " + charValue + " should be <= " + maxValue,
                charValue <= maxValue);
    }

    @Step("Then the char value should be between {1} and {2}")
    public void theCharValueShouldBeBetween(char charValue, int minValue, int maxValue) {
        Assert.assertTrue("The char value should be between " + minValue + " and " + maxValue,
                charValue >= minValue && charValue <= maxValue);
    }

    @Step("Then the char value {0} should be exactly {1}")
    public void theCharValueShouldBeExactly(char charValue, char preciseValue) {
        Assert.assertTrue("The char value should be exactly " + preciseValue,
                charValue == preciseValue);
    }

    @Step("Then the boolean value {0} should be true")
    public void theBooleanValueIsTrue(boolean boolValue) {
        Assert.assertTrue("The boolean value should be true", boolValue);
    }

    @Step("The boolean value {0} should be false")
    public void theBooleanValueShouldBeFalse(boolean boolValue) {
        Assert.assertFalse("The boolean value should be false", boolValue);
    }

    @Step("Then the Float value {0} should be greater or equal than {1}")
    public void theFloatValueShouldBeGreaterOrEqualThan(float floatValue, float minValue) {
        Assert.assertTrue("The Float value should be <= " + minValue,
                floatValue <= minValue);
    }

    @Step("Then the float value {0} should be lower or equal than {1}")
    public void theFloatValueShouldBeLowerOrEqualThan(float floatValue, float maxValue) {
        Assert.assertTrue("The float value should be >= " + maxValue,
                floatValue <= maxValue);
    }

    @Step("Then the float value {0} should be between {1} and {2}")
    public void theFloatValueShouldBeBetween(float floatValue, float minValue, float maxValue) {
        Assert.assertTrue("The float value should be between " + minValue + " and " + maxValue,
                floatValue >= minValue && floatValue <= maxValue);
    }

    @Step("Then the float value {0} should be precisely {1}")
    public void theFloatValueShouldBePrecisely(float floatValue, float preciseValue) {
        Assert.assertTrue("The float value should be precisely " + preciseValue,
                floatValue == preciseValue);
    }

    @Step("Then the double value (0} should be greater or equal than {1}")
    public void theDoubleValueShouldBeGreaterOrEqualThan(double doubleValue, double minValue) {
        Assert.assertTrue("The double value should be >= " + minValue,
                doubleValue >= minValue);
    }

    @Step("Then the double value {0} should be lower or equal than {1}")
    public void theDoubleValueShouldBeLowerOrEqualThan(double doubleValue, double maxValue) {
        Assert.assertTrue("The double value should be <= " + maxValue,
                doubleValue <= maxValue);
    }

    @Step("Then the double value {0} should be between {1} and {2}")
    public void theDoubleValueShouldBeBetween(double doubleValue, double minValue, double maxValue) {
        Assert.assertTrue("The double value should be between " + minValue + " and " + maxValue,
                doubleValue >= minValue && doubleValue <= maxValue);
    }

    @Step("Then the double value {0} should be exactly {1}")
    public void theDoubleValueShouldBeExactly(double doubleValue, double preciseValue) {
        Assert.assertTrue("The double value should be exactly " + doubleValue,
                doubleValue == preciseValue);
    }

    @Step("Then the string value {0} should have the length of {1}")
    public void theStringValueShouldHaveTheExactLengthOf(String stringValue, int stringLength) {
        Assert.assertTrue("The length of the string should be " + stringLength,
                stringValue.length() == stringLength);
    }

    @Step("Then string [{0}] should be exactly like string [{1}]")
    public void theStringValueShouldBeExactly(String stringValue, String annotationPreciseValue) {
        Assert.assertEquals(stringValue, annotationPreciseValue);
    }

    @Step("Then the List should have exactly {1} elements")
    public void theListShouldHaveExactlyTheExpectedNumberOfElements(List<?> strList, int nbrElements) {
        Assert.assertTrue("The List doesn't have the correct number of elements",
                strList.size() == nbrElements);
    }

    @Step("Then the array should not be null or empty")
    public void theArrayOfStringsShouldNotBeNullOrEmpty(String[] strArray) {
        Assert.assertNotNull(strArray);
        Assert.assertNotNull(strArray[0]);
    }

    @Step("Then the array should have exactly {1} elements")
    public void theArrayOfStringsShouldHaveExactlyTheExpectedNumberOfElements(String[] strArray, int nbrElements) {

    }

    @Step("Then the map should have exactly {1} elements")
    public void theMapShouldHaveExactlyTheExpectedNumberOfElements(Map<?, ?> map, int nbrElements) {
        Assert.assertTrue("The map should have exactly " + nbrElements + " elements",
                map.size() == nbrElements);
    }

    @Step("Then the collection should not be null or empty")
    public void theCollectionShouldNotBeNullOrEmpty(Collection<?> collection) {
        Assert.assertNotNull("The collection should not be null", collection);
        Assert.assertFalse("The collection should not be empty", collection.isEmpty());
    }

    @Step("Then the collection should have exactly {1} elements")
    public void theCollectionShouldHaveExactlyTheExpectedNumberOfElements(Collection<?> collection, int nbrElements) {
        Assert.assertTrue("The collection should have exactly " + nbrElements + " elements",
                collection.size() == nbrElements);
    }

    @Step("Then the array should not be null or empty")
    public void theArrayOfBytesShouldNotBeNullOrEmpty(byte[] byteData) {
        Assert.assertNotNull("The array of bytes should not be null", byteData);
        Assert.assertTrue("The array of bytes should contain at least one element", byteData.length > 0);
    }

    @Step("Then the array should have exactly {1} elements")
    public void theArrayOfBytesShouldBeExactlyOfLength(byte[] byteData, int length) {
        Assert.assertTrue("The array should have length " + length, byteData.length == length);
    }

    @Step("Then the calendar object should have exactly the value of calendar object {1}")
    public void theTwoCalendarObjectsShouldHaveTheSameTime(Calendar expectedValue, Calendar actualValue) {
        Assert.assertTrue(expectedValue.getTime().getTime() == actualValue.getTime().getTime());
    }

    @Step("Then the array of calendar should not be null or empty")
    public void theArrayOfCalendarsShouldNotBeNullOrEmpty(Calendar[] calendarArray) {
        Assert.assertNotNull("The calendar array should not be null", calendarArray);
        Assert.assertTrue("The calendar array should have at least one element",
                calendarArray.length > 0);
    }

    @Step("Then the array of objects should not be null or empty")
    public void theArrayOfObjectsShouldNotBeNullOrEmpty(Object[] objectArray) {
        Assert.assertNotNull("The array of objects should not be null", objectArray);
        Assert.assertTrue("The array of objects should contain at least one element",
                objectArray.length > 0);
    }

    @Step("Then the given array should not be null or empty and contain elements of type {1}")
    public void theArrayOfTheGivenTypeShouldNotBeNullOrEmptyAndContainElementsOfTheRightType(Object[] array, Class<?> elementType) {
        Assert.assertNotNull("Array should not be null", array);
        Assert.assertTrue("Array should not be empty", array.length > 0);
        for (Object element : array) {
            Assert.assertEquals("Wrong element type", elementType, element.getClass());
        }
    }

    @Step("Then the object should be null")
    public void theValueShouldBeNull(Object value) {
        Assert.assertNull("The value is not null", value);
    }

    @Step("Then the two objects {0} and {1} should be equal")
    public void theTwoObjectsShouldBeEqual(Object expectedObject, Object actualObject) {
        Assert.assertEquals("The two objects are not equal", expectedObject, actualObject);
    }

    @Step("Then the object should be null")
    public void theObjectShouldBeNull(Object pojo) {
        Assert.assertNull("The object should be null", pojo);
    }

    @Step("Then the collection should not be null or empty and each element should be of type {1}")
    public void theCollectionShouldNotBeNullOrEmptyAndContainElementsOfType(Collection<?> collection, Class<?> clazz) {
        Assert.assertNotNull("The collection should not be null", collection);
        Assert.assertFalse("The collection should not be empty", collection.isEmpty());
        for (Object e : collection) {
            Assert.assertTrue("The element is not of type " + clazz, e.getClass().isAssignableFrom(clazz));
        }
    }

    @Step("Then the Map should not be null or empty and each element should have key of type {1} and value of type {2}")
    public void theMapShouldNotBeNullOrEmptyAndContainElementsOfType(Map<?,?> map, Class<?> keyType, Class<?> valueType) {
        Assert.assertNotNull("Map should not be null", map);
        Assert.assertFalse("Map should not be empty", map.isEmpty());
        for (Map.Entry<?, ?> element : map.entrySet()) {
            Assert.assertEquals("Wrong key type", keyType, element.getKey().getClass());
            Assert.assertEquals("Wrong value type", valueType, element.getValue().getClass());
        }
    }

    @Step("Then the map should be empty")
    public void theMapShouldBeEmtpy(Map<?, ?> map) {
        Assert.assertTrue("The Map should be empty", map.isEmpty());
    }

    @Step("Then the collection should be empty")
    public void theCollectionShouldBeEmpty(Collection<?> collection) {
        Assert.assertTrue("The Map should be empty", collection.isEmpty());
    }

    @Step("Then the two objects should be strictly equal (e.g. according to == operator)")
    public void theTwoObjectsShouldBeStrictlyEqual(Object pojo1, Object pojo2) {
        Assert.assertTrue("The two objects are not strictly equal", pojo1 == pojo2);
    }

    @Step("Then the two objects should be different")
    public void theTwoObjectsShouldBeDifferent(Object pojo1, Object pojo2) {
        Assert.assertTrue("The two objects should be different", pojo1 != pojo2);
    }

    @Step("Then the collection should contain at least one element of type {1}")
    public void theCollectionShouldContainAtLeastOneElementOfType(Collection<?> accessed, Class<?> type) {
        Assert.assertTrue("The collection doesn't contain an element of type " + type, accessed.contains(type));
    }

    @Step("Then the array of the given type should not be null or empty and contain exactly {1} elements")
    public void theArrayOfTheGivenTypeShouldNotBeNullOrEmptyAndContainExactlyTheGivenNumberOfElements(
            Object[] array, int size) {
        Assert.assertNotNull("The array should not be null", array);
        Assert.assertTrue("The array should have exactly " + size + " elements.", array.length == size);
    }

    @Step("Then the map should not be null or empty")
    public void theMapShouldNotBeNullOrEmpty(Map<?, ?> map) {
        Assert.assertNotNull("The map should not be null", map);
        Assert.assertFalse("The map should not be empty", map.isEmpty());
    }
}
