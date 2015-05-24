package uk.co.jemos.podam.test.utils;

import org.junit.Assert;

import java.util.Calendar;
import java.util.Date;

public class TypesUtils {

    private TypesUtils() {
        throw new AssertionError("Non instantiable");
    }

    /**
     * It checks that the Calendar instance is valid
     * <p>
     * If the calendar returns a valid date then it's a valid instance
     * </p>
     *
     * @param calendarField The calendar instance to check
     */
    public static void checkCalendarIsValid(Calendar calendarField) {
        Assert.assertNotNull("The Calendar field cannot be null", calendarField);
        Date calendarDate = calendarField.getTime();
        Assert.assertNotNull("It appears the Calendar field is not valid",
                calendarDate);
    }
}