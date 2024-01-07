package uk.co.jemos.podam.test.unit.issue323;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Title;
import org.junit.Test;
import org.junit.runner.RunWith;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.test.dto.issue323.*;
import uk.co.jemos.podam.test.unit.AbstractPodamSteps;
import uk.co.jemos.podam.test.dto.issue323.ClockValuePojo;
import uk.co.jemos.podam.test.dto.issue323.DurationValuePojo;

/**
 * @author liam on 02/01/2024.
 * @since 8.0.1.RELEASE
 */
@RunWith(SerenityRunner.class)
public class AnnotationsTest extends AbstractPodamSteps {
    @Test
    @Title("Podam should handle both native and wrapped {@link java.time.Clock} values")
    public void podamShouldHandleClockFuture() throws Exception {

        PodamFactory podamFactory = podamFactorySteps.givenAStandardPodamFactory();
        ClockValuePojo pojo = podamInvocationSteps.whenIInvokeTheFactoryForClass(ClockValuePojo.class, podamFactory);
        podamValidationSteps.theObjectShouldNotBeNull(pojo);
        podamValidationSteps.theObjectShouldNotBeNull(pojo.getClockFieldValue());
    }

    @Test
    @Title("Podam should handle both native and wrapped {@link java.time.Duration} values")
    public void podamShouldHandleDurationAnnotated() throws Exception {

        PodamFactory podamFactory = podamFactorySteps.givenAStandardPodamFactory();
        DurationValuePojo pojo = podamInvocationSteps.whenIInvokeTheFactoryForClass(DurationValuePojo.class, podamFactory);
        podamValidationSteps.theObjectShouldNotBeNull(pojo);
        podamValidationSteps.theObjectShouldNotBeNull(pojo.getDurationFieldValue());
    }

    @Test
    @Title("Podam should handle both native and wrapped {@link java.time.Instant} values with {@link jakarta.validation.constraint.Future} annotation")
    public void podamShouldHandleInstantAnnotatedWithFuture() throws Exception {

        PodamFactory podamFactory = podamFactorySteps.givenAStandardPodamFactory();
        InstantValuePojo pojo = podamInvocationSteps.whenIInvokeTheFactoryForClass(InstantValuePojo.class, podamFactory);
        podamValidationSteps.theObjectShouldNotBeNull(pojo);
        podamValidationSteps.theInstantShouldInTheFutur(pojo.getInstantFieldWithFutureValue());
    }

    @Test
    @Title("Podam should handle both native and wrapped {@link java.time.Instant} values with {@link jakarta.validation.constraint.FutureOrPresent} annotation")
    public void podamShouldHandleInstantAnnotatedWithFutureOrPresent() throws Exception {

        PodamFactory podamFactory = podamFactorySteps.givenAStandardPodamFactory();
        InstantValuePojo pojo = podamInvocationSteps.whenIInvokeTheFactoryForClass(InstantValuePojo.class, podamFactory);
        podamValidationSteps.theObjectShouldNotBeNull(pojo);
        podamValidationSteps.theInstantShouldInTheFuturOrPresent(pojo.getInstantFieldWithFutureOrPresentValue());
    }

    @Test
    @Title("Podam should handle both native and wrapped {@link java.time.Instant} values with {@link jakarta.validation.constraint.PastOrPresent} annotation")
    public void podamShouldHandleInstantAnnotatedWithPastOrPresent() throws Exception {

        PodamFactory podamFactory = podamFactorySteps.givenAStandardPodamFactory();
        InstantValuePojo pojo = podamInvocationSteps.whenIInvokeTheFactoryForClass(InstantValuePojo.class, podamFactory);
        podamValidationSteps.theObjectShouldNotBeNull(pojo);
        podamValidationSteps.theInstantShouldInThePastOrPresent(pojo.getInstantFieldWithPastOrPresentValue());
    }

    @Test
    @Title("Podam should handle both native and wrapped {@link java.time.Instant} values with {@link jakarta.validation.constraint.Past} annotation")
    public void podamShouldHandleInstantAnnotatedWithPast() throws Exception {

        PodamFactory podamFactory = podamFactorySteps.givenAStandardPodamFactory();
        InstantValuePojo pojo = podamInvocationSteps.whenIInvokeTheFactoryForClass(InstantValuePojo.class, podamFactory);
        podamValidationSteps.theObjectShouldNotBeNull(pojo);
        podamValidationSteps.theInstantShouldInThePast(pojo.getInstantFieldWithPastValue());
    }

    @Test
    @Title("Podam should handle both native and wrapped {@link java.time.LocalDateTime} values with {@link jakarta.validation.constraint.Future} annotation")
    public void podamShouldHandleLocalDateTimeAnnotatedWithFuture() throws Exception {

        PodamFactory podamFactory = podamFactorySteps.givenAStandardPodamFactory();
        LocalDateTimeValuePojo pojo = podamInvocationSteps.whenIInvokeTheFactoryForClass(LocalDateTimeValuePojo.class, podamFactory);
        podamValidationSteps.theObjectShouldNotBeNull(pojo);
        podamValidationSteps.theLocalDateTimeShouldInTheFutur(pojo.getLocalDateTimeFieldWithFutureValue());
    }

    @Test
    @Title("Podam should handle both native and wrapped {@link java.time.LocalDateTime} values with {@link jakarta.validation.constraint.FutureOrPresent} annotation")
    public void podamShouldHandleLocalDateTimeAnnotatedWithFutureOrPresent() throws Exception {

        PodamFactory podamFactory = podamFactorySteps.givenAStandardPodamFactory();
        LocalDateTimeValuePojo pojo = podamInvocationSteps.whenIInvokeTheFactoryForClass(LocalDateTimeValuePojo.class, podamFactory);
        podamValidationSteps.theObjectShouldNotBeNull(pojo);
        podamValidationSteps.theLocalDateTimeShouldInTheFuturOrPresent(pojo.getLocalDateTimeFieldWithFutureOrPresentValue());
    }

    @Test
    @Title("Podam should handle both native and wrapped {@link java.time.LocalDateTime} values with {@link jakarta.validation.constraint.PastOrPresent} annotation")
    public void podamShouldHandleLocalDateTimeAnnotatedWithPastOrPresent() throws Exception {

        PodamFactory podamFactory = podamFactorySteps.givenAStandardPodamFactory();
        LocalDateTimeValuePojo pojo = podamInvocationSteps.whenIInvokeTheFactoryForClass(LocalDateTimeValuePojo.class, podamFactory);
        podamValidationSteps.theObjectShouldNotBeNull(pojo);
        podamValidationSteps.theLocalDateTimeShouldInThePastOrPresent(pojo.getLocalDateTimeFieldWithPastOrPresentValue());
    }

    @Test
    @Title("Podam should handle both native and wrapped {@link java.time.LocalDateTime} values with {@link jakarta.validation.constraint.Past} annotation")
    public void podamShouldHandleLocalDateTimeAnnotatedWithPast() throws Exception {

        PodamFactory podamFactory = podamFactorySteps.givenAStandardPodamFactory();
        LocalDateTimeValuePojo pojo = podamInvocationSteps.whenIInvokeTheFactoryForClass(LocalDateTimeValuePojo.class, podamFactory);
        podamValidationSteps.theObjectShouldNotBeNull(pojo);
        podamValidationSteps.theLocalDateTimeShouldInThePast(pojo.getLocalDateTimeFieldWithPastValue());
    }

    @Test
    @Title("Podam should handle both native and wrapped {@link java.time.LocalDate} values with {@link jakarta.validation.constraint.Future} annotation")
    public void podamShouldHandleLocalDateAnnotatedWithFuture() throws Exception {

        PodamFactory podamFactory = podamFactorySteps.givenAStandardPodamFactory();
        LocalDateValuePojo pojo = podamInvocationSteps.whenIInvokeTheFactoryForClass(LocalDateValuePojo.class, podamFactory);
        podamValidationSteps.theObjectShouldNotBeNull(pojo);
        podamValidationSteps.theLocalDateShouldInTheFutur(pojo.getLocalDateFieldWithFutureValue());
    }

    @Test
    @Title("Podam should handle both native and wrapped {@link java.time.LocalDate} values with {@link jakarta.validation.constraint.FutureOrPresent} annotation")
    public void podamShouldHandleLocalDateAnnotatedWithFutureOrPresent() throws Exception {

        PodamFactory podamFactory = podamFactorySteps.givenAStandardPodamFactory();
        LocalDateValuePojo pojo = podamInvocationSteps.whenIInvokeTheFactoryForClass(LocalDateValuePojo.class, podamFactory);
        podamValidationSteps.theObjectShouldNotBeNull(pojo);
        podamValidationSteps.theLocalDateShouldInTheFuturOrPresent(pojo.getLocalDateFieldWithFutureOrPresentValue());
    }

    @Test
    @Title("Podam should handle both native and wrapped {@link java.time.LocalDate} values with {@link jakarta.validation.constraint.PastOrPresent} annotation")
    public void podamShouldHandleLocalDateAnnotatedWithPastOrPresent() throws Exception {

        PodamFactory podamFactory = podamFactorySteps.givenAStandardPodamFactory();
        LocalDateValuePojo pojo = podamInvocationSteps.whenIInvokeTheFactoryForClass(LocalDateValuePojo.class, podamFactory);
        podamValidationSteps.theObjectShouldNotBeNull(pojo);
        podamValidationSteps.theLocalDateShouldInThePastOrPresent(pojo.getLocalDateFieldWithPastOrPresentValue());
    }

    @Test
    @Title("Podam should handle both native and wrapped {@link java.time.LocalDate} values with {@link jakarta.validation.constraint.Past} annotation")
    public void podamShouldHandleLocalDateAnnotatedWithPast() throws Exception {

        PodamFactory podamFactory = podamFactorySteps.givenAStandardPodamFactory();
        LocalDateValuePojo pojo = podamInvocationSteps.whenIInvokeTheFactoryForClass(LocalDateValuePojo.class, podamFactory);
        podamValidationSteps.theObjectShouldNotBeNull(pojo);
        podamValidationSteps.theLocalDateShouldInThePast(pojo.getLocalDateFieldWithPastValue());
    }

    @Test
    @Title("Podam should handle both native and wrapped {@link java.time.LocalTime} values with {@link jakarta.validation.constraint.Future} annotation")
    public void podamShouldHandleLocalTimeAnnotatedWithFuture() throws Exception {

        PodamFactory podamFactory = podamFactorySteps.givenAStandardPodamFactory();
        LocalTimeValuePojo pojo = podamInvocationSteps.whenIInvokeTheFactoryForClass(LocalTimeValuePojo.class, podamFactory);
        podamValidationSteps.theObjectShouldNotBeNull(pojo);
        podamValidationSteps.theObjectShouldNotBeNull(pojo.getLocalTimeFieldValue());
    }

    @Test
    @Title("Podam should handle both native and wrapped {@link java.time.OffsetDateTime} values with {@link jakarta.validation.constraint.Future} annotation")
    public void podamShouldHandleOffsetDateTimeAnnotatedWithFuture() throws Exception {

        PodamFactory podamFactory = podamFactorySteps.givenAStandardPodamFactory();
        OffsetDateTimeValuePojo pojo = podamInvocationSteps.whenIInvokeTheFactoryForClass(OffsetDateTimeValuePojo.class, podamFactory);
        podamValidationSteps.theObjectShouldNotBeNull(pojo);
        podamValidationSteps.theOffsetDateTimeShouldInTheFutur(pojo.getOffsetDateTimeFieldWithFutureValue());
    }

    @Test
    @Title("Podam should handle both native and wrapped {@link java.time.OffsetDateTime} values with {@link jakarta.validation.constraint.FutureOrPresent} annotation")
    public void podamShouldHandleOffsetDateTimeAnnotatedWithFutureOrPresent() throws Exception {

        PodamFactory podamFactory = podamFactorySteps.givenAStandardPodamFactory();
        OffsetDateTimeValuePojo pojo = podamInvocationSteps.whenIInvokeTheFactoryForClass(OffsetDateTimeValuePojo.class, podamFactory);
        podamValidationSteps.theObjectShouldNotBeNull(pojo);
        podamValidationSteps.theOffsetDateTimeShouldInTheFuturOrPresent(pojo.getOffsetDateTimeFieldWithFutureOrPresentValue());
    }

    @Test
    @Title("Podam should handle both native and wrapped {@link java.time.OffsetDateTime} values with {@link jakarta.validation.constraint.PastOrPresent} annotation")
    public void podamShouldHandleOffsetDateTimeAnnotatedWithPastOrPresent() throws Exception {

        PodamFactory podamFactory = podamFactorySteps.givenAStandardPodamFactory();
        OffsetDateTimeValuePojo pojo = podamInvocationSteps.whenIInvokeTheFactoryForClass(OffsetDateTimeValuePojo.class, podamFactory);
        podamValidationSteps.theObjectShouldNotBeNull(pojo);
        podamValidationSteps.theOffsetDateTimeShouldInThePastOrPresent(pojo.getOffsetDateTimeFieldWithPastOrPresentValue());
    }

    @Test
    @Title("Podam should handle both native and wrapped {@link java.time.OffsetDateTime} values with {@link jakarta.validation.constraint.Past} annotation")
    public void podamShouldHandleOffsetDateTimeAnnotatedWithPast() throws Exception {

        PodamFactory podamFactory = podamFactorySteps.givenAStandardPodamFactory();
        OffsetDateTimeValuePojo pojo = podamInvocationSteps.whenIInvokeTheFactoryForClass(OffsetDateTimeValuePojo.class, podamFactory);
        podamValidationSteps.theObjectShouldNotBeNull(pojo);
        podamValidationSteps.theOffsetDateTimeShouldInThePast(pojo.getOffsetDateTimeFieldWithPastValue());
    }

    @Test
    @Title("Podam should handle both native and wrapped {@link java.time.MonthDay} values")
    public void podamShouldHandleMonthDayAnnotatedWithFuture() throws Exception {

        PodamFactory podamFactory = podamFactorySteps.givenAStandardPodamFactory();
        MonthDayValuePojo pojo = podamInvocationSteps.whenIInvokeTheFactoryForClass(MonthDayValuePojo.class, podamFactory);
        podamValidationSteps.theObjectShouldNotBeNull(pojo);
        podamValidationSteps.theObjectShouldNotBeNull(pojo.getMonthDayFieldValue());
    }

    @Test
    @Title("Podam should handle both native and wrapped {@link java.time.OffsetTime} values")
    public void podamShouldHandleOffsetTimeAnnotatedWithPast() throws Exception {

        PodamFactory podamFactory = podamFactorySteps.givenAStandardPodamFactory();
        OffsetTimeValuePojo pojo = podamInvocationSteps.whenIInvokeTheFactoryForClass(OffsetTimeValuePojo.class, podamFactory);
        podamValidationSteps.theObjectShouldNotBeNull(pojo);
        podamValidationSteps.theObjectShouldNotBeNull(pojo.getOffsetTimeFieldValue());
    }

    @Test
    @Title("Podam should handle both native and wrapped {@link java.time.Period} values")
    public void podamShouldHandlePeriodAnnotated() throws Exception {

        PodamFactory podamFactory = podamFactorySteps.givenAStandardPodamFactory();
        PeriodValuePojo pojo = podamInvocationSteps.whenIInvokeTheFactoryForClass(PeriodValuePojo.class, podamFactory);
        podamValidationSteps.theObjectShouldNotBeNull(pojo);
        podamValidationSteps.theObjectShouldNotBeNull(pojo.getPeriodFieldValue());
    }

    @Test
    @Title("Podam should handle both native and wrapped {@link java.time.YearMonth} values with {@link jakarta.validation.constraint.Future} annotation")
    public void podamShouldHandleYearMonthAnnotatedWithFuture() throws Exception {

        PodamFactory podamFactory = podamFactorySteps.givenAStandardPodamFactory();
        YearMonthValuePojo pojo = podamInvocationSteps.whenIInvokeTheFactoryForClass(YearMonthValuePojo.class, podamFactory);
        podamValidationSteps.theObjectShouldNotBeNull(pojo);
        podamValidationSteps.theYearMonthShouldInTheFutur(pojo.getYearMonthFieldWithFutureValue());
    }

    @Test
    @Title("Podam should handle both native and wrapped {@link java.time.YearMonth} values with {@link jakarta.validation.constraint.FutureOrPresent} annotation")
    public void podamShouldHandleYearMonthAnnotatedWithFutureOrPresent() throws Exception {

        PodamFactory podamFactory = podamFactorySteps.givenAStandardPodamFactory();
        YearMonthValuePojo pojo = podamInvocationSteps.whenIInvokeTheFactoryForClass(YearMonthValuePojo.class, podamFactory);
        podamValidationSteps.theObjectShouldNotBeNull(pojo);
        podamValidationSteps.theYearMonthShouldInTheFuturOrPresent(pojo.getYearMonthFieldWithFutureOrPresentValue());
    }

    @Test
    @Title("Podam should handle both native and wrapped {@link java.time.YearMonth} values with {@link jakarta.validation.constraint.PastOrPresent} annotation")
    public void podamShouldHandleYearMonthAnnotatedWithPastOrPresent() throws Exception {

        PodamFactory podamFactory = podamFactorySteps.givenAStandardPodamFactory();
        YearMonthValuePojo pojo = podamInvocationSteps.whenIInvokeTheFactoryForClass(YearMonthValuePojo.class, podamFactory);
        podamValidationSteps.theObjectShouldNotBeNull(pojo);
        podamValidationSteps.theYearMonthShouldInThePastOrPresent(pojo.getYearMonthFieldWithPastOrPresentValue());
    }

    @Test
    @Title("Podam should handle both native and wrapped {@link java.time.YearMonth} values with {@link jakarta.validation.constraint.Past} annotation")
    public void podamShouldHandleYearMonthAnnotatedWithPast() throws Exception {

        PodamFactory podamFactory = podamFactorySteps.givenAStandardPodamFactory();
        YearMonthValuePojo pojo = podamInvocationSteps.whenIInvokeTheFactoryForClass(YearMonthValuePojo.class, podamFactory);
        podamValidationSteps.theObjectShouldNotBeNull(pojo);
        podamValidationSteps.theYearMonthShouldInThePast(pojo.getYearMonthFieldWithPastValue());
    }

    @Test
    @Title("Podam should handle both native and wrapped {@link java.time.Year} values with {@link jakarta.validation.constraint.Future} annotation")
    public void podamShouldHandleYearAnnotatedWithFuture() throws Exception {

        PodamFactory podamFactory = podamFactorySteps.givenAStandardPodamFactory();
        YearValuePojo pojo = podamInvocationSteps.whenIInvokeTheFactoryForClass(YearValuePojo.class, podamFactory);
        podamValidationSteps.theObjectShouldNotBeNull(pojo);
        podamValidationSteps.theYearShouldInTheFutur(pojo.getYearFieldWithFutureValue());
    }

    @Test
    @Title("Podam should handle both native and wrapped {@link java.time.Year} values with {@link jakarta.validation.constraint.FutureOrPresent} annotation")
    public void podamShouldHandleYearAnnotatedWithFutureOrPresent() throws Exception {

        PodamFactory podamFactory = podamFactorySteps.givenAStandardPodamFactory();
        YearValuePojo pojo = podamInvocationSteps.whenIInvokeTheFactoryForClass(YearValuePojo.class, podamFactory);
        podamValidationSteps.theObjectShouldNotBeNull(pojo);
        podamValidationSteps.theYearShouldInTheFuturOrPresent(pojo.getYearFieldWithFutureOrPresentValue());
    }

    @Test
    @Title("Podam should handle both native and wrapped {@link java.time.Year} values with {@link jakarta.validation.constraint.PastOrPresent} annotation")
    public void podamShouldHandleYearAnnotatedWithPastOrPresent() throws Exception {

        PodamFactory podamFactory = podamFactorySteps.givenAStandardPodamFactory();
        YearValuePojo pojo = podamInvocationSteps.whenIInvokeTheFactoryForClass(YearValuePojo.class, podamFactory);
        podamValidationSteps.theObjectShouldNotBeNull(pojo);
        podamValidationSteps.theYearShouldInThePastOrPresent(pojo.getYearFieldWithPastOrPresentValue());
    }

    @Test
    @Title("Podam should handle both native and wrapped {@link java.time.Year} values with {@link jakarta.validation.constraint.Past} annotation")
    public void podamShouldHandleYearAnnotatedWithPast() throws Exception {

        PodamFactory podamFactory = podamFactorySteps.givenAStandardPodamFactory();
        YearValuePojo pojo = podamInvocationSteps.whenIInvokeTheFactoryForClass(YearValuePojo.class, podamFactory);
        podamValidationSteps.theObjectShouldNotBeNull(pojo);
        podamValidationSteps.theYearShouldInThePast(pojo.getYearFieldWithPastValue());
    }

    @Test
    @Title("Podam should handle both native and wrapped {@link java.time.ZonedDateTime} values with {@link jakarta.validation.constraint.Future} annotation")
    public void podamShouldHandleZonedDateTimeAnnotatedWithFuture() throws Exception {

        PodamFactory podamFactory = podamFactorySteps.givenAStandardPodamFactory();
        ZonedDateTimeValuePojo pojo = podamInvocationSteps.whenIInvokeTheFactoryForClass(ZonedDateTimeValuePojo.class, podamFactory);
        podamValidationSteps.theObjectShouldNotBeNull(pojo);
        podamValidationSteps.theZonedDateTimeShouldInTheFutur(pojo.getZonedDateTimeFieldWithFutureValue());
    }

    @Test
    @Title("Podam should handle both native and wrapped {@link java.time.ZonedDateTime} values with {@link jakarta.validation.constraint.FutureOrPresent} annotation")
    public void podamShouldHandleZonedDateTimeAnnotatedWithFutureOrPresent() throws Exception {

        PodamFactory podamFactory = podamFactorySteps.givenAStandardPodamFactory();
        ZonedDateTimeValuePojo pojo = podamInvocationSteps.whenIInvokeTheFactoryForClass(ZonedDateTimeValuePojo.class, podamFactory);
        podamValidationSteps.theObjectShouldNotBeNull(pojo);
        podamValidationSteps.theZonedDateTimeShouldInTheFuturOrPresent(pojo.getZonedDateTimeFieldWithFutureOrPresentValue());
    }

    @Test
    @Title("Podam should handle both native and wrapped {@link java.time.ZonedDateTime} values with {@link jakarta.validation.constraint.PastOrPresent} annotation")
    public void podamShouldHandleZonedDateTimeAnnotatedWithPastOrPresent() throws Exception {

        PodamFactory podamFactory = podamFactorySteps.givenAStandardPodamFactory();
        ZonedDateTimeValuePojo pojo = podamInvocationSteps.whenIInvokeTheFactoryForClass(ZonedDateTimeValuePojo.class, podamFactory);
        podamValidationSteps.theObjectShouldNotBeNull(pojo);
        podamValidationSteps.theZonedDateTimeShouldInThePastOrPresent(pojo.getZonedDateTimeFieldWithPastOrPresentValue());
    }

    @Test
    @Title("Podam should handle both native and wrapped {@link java.time.ZonedDateTime} values with {@link jakarta.validation.constraint.Past} annotation")
    public void podamShouldHandleZonedDateTimeAnnotatedWithPast() throws Exception {

        PodamFactory podamFactory = podamFactorySteps.givenAStandardPodamFactory();
        ZonedDateTimeValuePojo pojo = podamInvocationSteps.whenIInvokeTheFactoryForClass(ZonedDateTimeValuePojo.class, podamFactory);
        podamValidationSteps.theObjectShouldNotBeNull(pojo);
        podamValidationSteps.theZonedDateTimeShouldInThePast(pojo.getZonedDateTimeFieldWithPastValue());
    }

    @Test
    @Title("Podam should handle both native and wrapped {@link java.time.ZoneId} values")
    public void podamShouldHandleZoneIdAnnotated() throws Exception {

        PodamFactory podamFactory = podamFactorySteps.givenAStandardPodamFactory();
        ZoneIdValuePojo pojo = podamInvocationSteps.whenIInvokeTheFactoryForClass(ZoneIdValuePojo.class, podamFactory);
        podamValidationSteps.theObjectShouldNotBeNull(pojo);
        podamValidationSteps.theObjectShouldNotBeNull(pojo.getZoneIdFieldValue());
    }

    @Test
    @Title("Podam should handle both native and wrapped {@link java.time.ZoneOffset} values")
    public void podamShouldHandleZoneOffsetAnnotatedWithFuture() throws Exception {

        PodamFactory podamFactory = podamFactorySteps.givenAStandardPodamFactory();
        ZoneOffsetValuePojo pojo = podamInvocationSteps.whenIInvokeTheFactoryForClass(ZoneOffsetValuePojo.class, podamFactory);
        podamValidationSteps.theObjectShouldNotBeNull(pojo);
        podamValidationSteps.theObjectShouldNotBeNull(pojo.getZoneOffsetFieldValue());
    }
}
