package uk.co.jemos.podam.test.unit.steps;

import net.thucydides.core.annotations.Step;
import org.junit.Assert;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by tedonema on 27/05/2015.
 */
public class PodamValidationSteps {

    @Step("Then the Pojo should not be null")
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
}
