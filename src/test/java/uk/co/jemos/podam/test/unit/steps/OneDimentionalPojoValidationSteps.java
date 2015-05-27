package uk.co.jemos.podam.test.unit.steps;

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

        Boolean booleanObjectField = pojo.getBooleanObjectField();
        Assert.assertTrue(
                "The boolean object field should have a value of TRUE",
                booleanObjectField);

        boolean booleanField = pojo.isBooleanField();
        Assert.assertTrue("The boolean field should have a value of TRUE",
                booleanField);

        byte byteField = pojo.getByteField();
        Assert.assertTrue("The byte field should not be zero", byteField != 0);

        Byte byteObjectField = pojo.getByteObjectField();
        Assert.assertTrue("The Byte object field should not be zero",
                byteObjectField != 0);

        short shortField = pojo.getShortField();
        Assert.assertTrue("The short field should not be zero", shortField != 0);

        Short shortObjectField = pojo.getShortObjectField();
        Assert.assertTrue("The Short Object field should not be zero",
                shortObjectField != 0);

        char charField = pojo.getCharField();
        Assert.assertTrue("The char field should not be zero", charField != 0);
        Character characterObjectField = pojo.getCharObjectField();
        Assert.assertTrue("The Character object field should not be zero",
                characterObjectField != 0);

        int intField = pojo.getIntField();
        Assert.assertTrue("The int field cannot be zero", intField != 0);
        Integer integerField = pojo.getIntObjectField();
        Assert.assertTrue("The Integer object field cannot be zero",
                integerField != 0);

        long longField = pojo.getLongField();
        Assert.assertTrue("The long field cannot be zero", longField != 0);
        Long longObjectField = pojo.getLongObjectField();
        Assert.assertTrue("The Long object field cannot be zero",
                longObjectField != 0);

        float floatField = pojo.getFloatField();
        Assert.assertTrue("The float field cannot be zero", floatField != 0.0);
        Float floatObjectField = pojo.getFloatObjectField();
        Assert.assertTrue("The Float object field cannot be zero",
                floatObjectField != 0.0);

        double doubleField = pojo.getDoubleField();
        Assert.assertTrue("The double field cannot be zero",
                doubleField != 0.0d);
        Double doubleObjectField = pojo.getDoubleObjectField();
        Assert.assertTrue("The Double object field cannot be zero",
                doubleObjectField != 0.0d);

        String stringField = pojo.getStringField();
        Assert.assertNotNull("The String field cannot be null", stringField);
        Assert.assertFalse("The String field cannot be empty",
                stringField.equals(""));

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
        Assert.assertTrue(
                "The first element in the array of ints must be different from zero!",
                intArray[0] != 0);

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