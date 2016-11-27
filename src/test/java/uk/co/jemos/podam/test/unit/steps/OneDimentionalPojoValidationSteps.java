package uk.co.jemos.podam.test.unit.steps;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import net.thucydides.core.annotations.Step;
import org.junit.Assert;
import uk.co.jemos.podam.api.DataProviderStrategy;
import uk.co.jemos.podam.test.dto.OneDimensionalTestPojo;
import uk.co.jemos.podam.test.utils.TypesUtils;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

public class OneDimentionalPojoValidationSteps {

    @Step("Then OneDimensionalTestPojo should be valid")
    public void validateDimensionalTestPojo(OneDimensionalTestPojo pojo, DataProviderStrategy strategy) {

        assertThat(
                "The boolean object field should have a value of TRUE",
                pojo.getBooleanObjectField(), equalTo(true));

        assertThat("The boolean field should have a value of TRUE",
                pojo.isBooleanField(), equalTo(true));

        byte byteField = pojo.getByteField();
        assertThat("The byte field should not be zero",
                byteField, not(equalTo((byte)0)));

        Byte byteObjectField = pojo.getByteObjectField();
        assertThat("The Byte object field should not be zero",
                byteObjectField, not(equalTo((byte)0)));

        short shortField = pojo.getShortField();
        assertThat("The short field should not be zero",
                shortField, not(equalTo((short)0)));

        Short shortObjectField = pojo.getShortObjectField();
        assertThat("The Short Object field should not be zero",
                shortObjectField, not(equalTo((short)0)));

        char charField = pojo.getCharField();
        assertThat("The char field should not be zero",
                charField, not(equalTo((char)0)));
        Character characterObjectField = pojo.getCharObjectField();
        assertThat("The Character object field should not be zero",
                characterObjectField,  not(equalTo((char)0)));

        int intField = pojo.getIntField();
        assertThat("The int field cannot be zero", intField, not(equalTo(0)));
        Integer integerField = pojo.getIntObjectField();
        assertThat("The Integer object field cannot be zero",
                integerField, not(equalTo(0)));

        long longField = pojo.getLongField();
        assertThat("The long field cannot be zero",
                longField, not(equalTo(0L)));
        Long longObjectField = pojo.getLongObjectField();
        assertThat("The Long object field cannot be zero",
                longObjectField, not(equalTo(0L)));

        float floatField = pojo.getFloatField();
        assertThat("The float field cannot be zero",
                floatField, not(equalTo(0.0f)));
        Float floatObjectField = pojo.getFloatObjectField();
        assertThat("The Float object field cannot be zero",
                floatObjectField, not(equalTo(0.0f)));

        double doubleField = pojo.getDoubleField();
        assertThat("The double field cannot be zero",
                doubleField, not(equalTo(0.0d)));
        Double doubleObjectField = pojo.getDoubleObjectField();
        assertThat("The Double object field cannot be zero",
                doubleObjectField, not(equalTo(0.0d)));

        String stringField = pojo.getStringField();
        assertThat("The String field cannot be empty",
                stringField, not(isEmptyOrNullString()));

        Object objectField = pojo.getObjectField();
        Assert.assertNotNull("The Object field cannot be null", objectField);

        Calendar calendarField = pojo.getCalendarField();
        TypesUtils.checkCalendarIsValid(calendarField);

        Date dateField = pojo.getDateField();
        Assert.assertNotNull("The date field is not valid", dateField);

        Random[] randomArray = pojo.getRandomArray();
        Assert.assertNotNull("The array of Random objects cannot be null!",
                randomArray);
        Assert.assertEquals("The array of Random length should be one!",
                strategy.getNumberOfCollectionElements(Random.class),
                randomArray.length);
        Random random = randomArray[0];
        Assert.assertNotNull(
                "The Random array element at [0] should not be null", random);

        int[] intArray = pojo.getIntArray();
        Assert.assertNotNull("The array of ints cannot be null!", intArray);
        Assert.assertEquals(
                "The array of ints length should be the same as defined in the strategy!",
                strategy.getNumberOfCollectionElements(Integer.class),
                intArray.length);
        assertThat(
                "The first element in the array of ints must be different from zero!",
                intArray[0], not(equalTo(0)));

        boolean[] booleanArray = pojo.getBooleanArray();
        Assert.assertNotNull("The array of booleans cannot be null!",
                booleanArray);
        Assert.assertEquals(
                "The array of boolean length should be the same as the one set in the strategy!",
                strategy.getNumberOfCollectionElements(Boolean.class),
                booleanArray.length);

        BigDecimal bigDecimalField = pojo.getBigDecimalField();
        Assert.assertNotNull("The BigDecimal field cannot be null!",
                bigDecimalField);

    }
}