package uk.co.jemos.podam.test.unit.steps;

import net.thucydides.core.annotations.Step;
import org.junit.Assert;

import uk.co.jemos.podam.api.DataProviderStrategy;
import uk.co.jemos.podam.test.utils.TypesUtils;

import java.lang.reflect.Array;
import java.util.*;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

/**
 * Created by tedonema on 27/05/2015.
 */
public class PodamValidationSteps {

    @Step("Then the object {0} should not be null")
    public void theObjectShouldNotBeNull(Object pojo) {
        assertThat(pojo, is(notNullValue()));
    }

    @Step("Then the Pojo should contain some data")
    public void thePojoShouldContainSomeData(Object pojo) {
         assertThat(pojo.getClass().getDeclaredFields(), arrayWithSize(greaterThan(0)));
    }

    @Step("Then the Pojo should be null")
    public void thePojoShouldBeNull(Object pojo) {
        Assert.assertNull("The pojo should be null", pojo);
    }

    @Step("Then the string {0} should match the pattern {1}")
    public void theStringMatchesAPattern(String string, String pattern) {
        Assert.assertTrue(string + " doesn't match " + pattern, string.matches(pattern));
    }

    @Step("Then the int field should not be zero")
    public void theIntFieldShouldNotBeZero(int intField) {
        assertThat("The integer field should not be zero", intField, is(not(equalTo(0))));
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
        Assert.assertNotNull("The string object value should not be null", strField);
        assertThat(strField, not(isEmptyOrNullString()));
    }

    @Step("Then the pojo {0} must be of the type {1}")
    public void thePojoMustBeOfTheType(Object pojo, Class<?> type) {
        assertThat("The pojo must be of the type", pojo, instanceOf(type));
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
        assertThat("The length of the string should be " + stringLength,
                stringValue.length(), equalTo(stringLength));
    }

    @Step("Then string [{0}] should be exactly like string [{1}]")
    public void theStringValueShouldBeExactly(String stringValue, String annotationPreciseValue) {
        Assert.assertEquals(stringValue, annotationPreciseValue);
    }

    @Step("Then the array should have exactly {1} elements")
    public void theArrayShouldHaveExactlyTheExpectedNumberOfElements(Object array, int nbrElements) {
        int length = Array.getLength(array);
        assertThat("The collection should have exactly " + nbrElements + " elements",
                length, equalTo(nbrElements));
    }

    @Step("Then the map should not be null or empty and each element should have key of type {1} and value of type {2} and have exactly {3} elements")
    public void theMapShouldNotBeNullOrEmptyAndShouldHaveExactlyTheExpectedNumberOfElements(
            Map<?, ?> map, Class<?> keyType, Class<?> valueType, int nbrElements) {
        theMapShouldNotBeNullOrEmptyAndContainElementsOfType(map, keyType, valueType);
        assertThat("The map should have exactly " + nbrElements + " elements",
                map.size(), equalTo(nbrElements));
    }

    @Step("Then the collection should not be null or empty")
    private void theCollectionShouldNotBeNullOrEmpty(Collection<?> collection) {
        Assert.assertNotNull("The collection should not be null", collection);
        assertThat("The collection should not be empty", collection, is(not(empty())));
    }

    @Step("Then the collection should have exactly {1} elements")
    public void theCollectionShouldHaveExactlyTheExpectedNumberOfElements(Collection<?> collection, int nbrElements) {
        assertThat("The collection should have exactly " + nbrElements + " elements",
                collection.size(), equalTo(nbrElements));
    }

    @Step("Then the collection should have should have elements of type {1} and exactly {2} elements")
    public void theCollectionShouldNotBeNullOrEmptyAndShouldHaveExactlyTheExpectedNumberOfElements(
            Collection<?> collection, Class<?> elementType, int nbrElements) {
        theCollectionShouldNotBeNullOrEmptyAndContainElementsOfType(collection, elementType);
        theCollectionShouldHaveExactlyTheExpectedNumberOfElements(collection, nbrElements);
    }

    @Step("Then the calendar object should have exactly the value of calendar object {1}")
    public void theTwoCalendarObjectsShouldHaveTheSameTime(Calendar expectedValue, Calendar actualValue) {
        assertThat("Calendar values must be equal", actualValue.getTime().getTime(), equalTo(expectedValue.getTime().getTime()));
    }

    @Step("Then the given array should not be null or empty and contain elements of type {1}")
    public void theArrayOfTheGivenTypeShouldNotBeNullOrEmptyAndContainElementsOfTheRightType(Object array, Class<?> elementType) {
        Assert.assertNotNull("Array should not be null", array);
        int length = Array.getLength(array);
        assertThat("Array should not be empty", length, greaterThan(0));
        for (int i = 0; i < length; i++) {
            Object element = Array.get(array, i);
            assertThat("Wrong element type", element, instanceOf(elementType));
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
    public void theCollectionShouldNotBeNullOrEmptyAndContainElementsOfType(Collection<?> collection, Class<?> elementType) {
        theCollectionShouldNotBeNullOrEmpty(collection);
        for (Object element : collection) {
            assertThat("Wrong element type", element, instanceOf(elementType));
        }
    }

    @Step("Then the Map should not be null or empty and each element should have key of type {1} and value of type {2}")
    public void theMapShouldNotBeNullOrEmptyAndContainElementsOfType(Map<?,?> map, Class<?> keyType, Class<?> valueType) {
        theMapShouldNotBeNullOrEmpty(map);
        for (Map.Entry<?, ?> element : map.entrySet()) {
            assertThat("Wrong key type", element.getKey(), instanceOf(keyType));
            assertThat("Wrong value type", element.getValue(), instanceOf(valueType));
        }
    }

    @Step("Then the map should be empty")
    public void theMapShouldBeEmtpy(Map<?, ?> map) {
        assertThat("The Map should be empty", map.keySet(), is(empty()));
    }

    @Step("Then the collection should be empty")
    public void theCollectionShouldBeEmpty(Collection<?> collection) {
        assertThat("The Map should be empty", collection, is(empty()));
    }

    @Step("Then the {0} and {1} should be strictly equal (e.g. according to == operator)")
    public void theTwoObjectsShouldBeStrictlyEqual(Object pojo1, Object pojo2) {
        assertThat("The two objects are not strictly equal", pojo1, equalTo(pojo2));
    }

    @Step("Then the {0} and {1} should be different")
    public void theTwoObjectsShouldBeDifferent(Object pojo1, Object pojo2) {
        assertThat("The two objects should be different", pojo1, not(equalTo(pojo2)));
    }

    @Step("Then the collection should contain at least one element of type {1}")
    public void theCollectionShouldContainAtLeastOneElementOfType(Collection<?> accessed, Class<?> type) {
        Assert.assertTrue("The collection doesn't contain an element of type " + type, accessed.contains(type));
    }

    @Step("Then the array of the given type should not be null or empty and contain exactly {1} elements of type {2}")
    public void theArrayOfTheGivenTypeShouldNotBeNullOrEmptyAndContainExactlyTheGivenNumberOfElements(
            Object array, int size, Class<?> elementType) {
        theArrayOfTheGivenTypeShouldNotBeNullOrEmptyAndContainElementsOfTheRightType(array, elementType);
        theArrayShouldHaveExactlyTheExpectedNumberOfElements(array, size);
    }

    @Step("Then the map should not be null or empty")
    private void theMapShouldNotBeNullOrEmpty(Map<?, ?> map) {
        Assert.assertNotNull("The map should not be null", map);
        assertThat("The map should not be empty", map.keySet(), is(not(empty())));
    }

    @Step("Then Data Provider Strategy should have memoization {1}")
    public void theMemoizationShouldBeEnabled(DataProviderStrategy strategy, boolean isMemoizationEnabled) {

        Assert.assertEquals("Payload must be valid", isMemoizationEnabled, strategy.isMemoizationEnabled());
    }
}
