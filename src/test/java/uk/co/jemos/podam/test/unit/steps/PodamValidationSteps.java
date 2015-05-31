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

    @Step("Then the Pojo {0} should not be null")
    public boolean thePojoShouldNotBeNull(Object pojo) {
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

    @Step("Then the List<String> {0} should not be null and contain at least one non-empty element")
    public void theListOfStringsShouldNotBeNullAndContainAtLeastOneNonEmptyElement(List<String> list) {
        Assert.assertNotNull("The List<String> should not be null!", list);
        Assert.assertFalse("The List<String> cannot be empty!", list.isEmpty());
        String element = list.get(0);
        Assert.assertNotNull(
                "The List<String> must have a non-null String element", element);
    }

    @Step("Then the Set<String> {0} should contain at least one non-empty element")
    public void theSetOfStringsShouldContainAtleastOneNonEmptyElement(Set<String> set) {

        Assert.assertNotNull("The Set<String> should not be null!", set);
        Assert.assertFalse("The Set<String> cannot be empty!", set.isEmpty());
        String element = set.iterator().next();
        Assert.assertNotNull(
                "The Set<String> must have a non-null String element", element);

    }

    @Step("Then the Map<String, ?> {0} should contain at least one non empty element")
    public void theMapOfStringsObjectsShouldContainAtLeastOneNonEmptyElement(Map<String, ?> map) {
        Assert.assertTrue("The map attribute must be of type HashMap",
                map instanceof HashMap);
        Assert.assertNotNull("The map object in the POJO cannot be null", map);
        Set<String> keySet = map.keySet();
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

    @Step("Then the char value {0} should be greater or equal than {1}")
    public void theCharValueShouldBeGreaterOrEqualThan(char charValue, int minValue) {
        Assert.assertTrue("The char value " + charValue + " should be >= " + minValue,
                charValue >= minValue);
    }

    @Step("Then the char value {0} should be lower or equal than {1}")
    public void theCharValueShouldBeLowerOrEqualThan(char charValue, int maxValue) {
        Assert.assertTrue("The char value " + charValue + " should be <= " + maxValue,
                charValue <= maxValue);
    }

    @Step("Then the char value {0} should be between {1} and {2}")
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
}
