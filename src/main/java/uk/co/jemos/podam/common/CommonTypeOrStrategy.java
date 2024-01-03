package uk.co.jemos.podam.common;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.PastOrPresent;
import java.lang.annotation.Annotation;
import java.time.*;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Set;
import java.util.concurrent.atomic.AtomicReference;
import uk.co.jemos.podam.api.PodamUtils;

/**
 * Base {@link java.time.temporal.Temporal} type strategy.
 *
 * @author liam on 03/01/2024.
 * @since 8.0.1.RELEASE
 */
public interface CommonTypeOrStrategy {

    /**
     * It returns an {@link java.time.ZoneId} value.
     *
     * @return A {@link java.time.ZoneId} value
     */
    default ZoneId getZoneId() {
        Set<String> zoneIds = ZoneId.getAvailableZoneIds();
        Integer index = PodamUtils.getIntegerInRange(0, zoneIds.size());

        int i = 0;
        for (String zoneId : zoneIds) {
            if (index.equals(i++)) {
                return ZoneId.of(zoneId);
            }
        }

        return ZoneId.systemDefault();
    }

    /**
     * Calculate a couple of seconds and nanoseconds
     *
     * @param annotations list of annotations attached to an attribute
     * @param seconds     A amount a seconds
     * @param nanos       A amoutn of nanoseconds
     */
    default void getSecondsNanos(List<Annotation> annotations, AtomicReference<Long> seconds, AtomicReference<Long> nanos) {

        Instant now = Instant.now();

        if (annotations.stream().anyMatch(Future.class::isInstance)) {

            Instant futur = now.plus(1L, ChronoUnit.DAYS);
            seconds.set(PodamUtils.getLongInRange(futur.getEpochSecond(), Instant.MAX.getEpochSecond()));

        } else if (annotations.stream().anyMatch(FutureOrPresent.class::isInstance)) {

            seconds.set(PodamUtils.getLongInRange(now.getEpochSecond(), Instant.MAX.getEpochSecond()));

        } else if (annotations.stream().anyMatch(PastOrPresent.class::isInstance)) {

            seconds.set(PodamUtils.getLongInRange(Instant.MIN.getEpochSecond(), now.getEpochSecond()));

        } else if (annotations.stream().anyMatch(Past.class::isInstance)) {

            Instant past = now.minus(1L, ChronoUnit.DAYS);
            seconds.set(PodamUtils.getLongInRange(Instant.MIN.getEpochSecond(), past.getEpochSecond()));

        } else {

            seconds.set(PodamUtils.getLongInRange(Instant.MIN.getEpochSecond(), Instant.MAX.getEpochSecond()));
        }

        nanos.set(PodamUtils.getLongInRange(Instant.MIN.getNano(), Instant.MAX.getNano()));
    }

    /**
     * Calculate a couple of years
     *
     * @param annotations list of annotations attached to an attribute
     * @param years       A amount a year
     */
    default void getYearsMonthsDays(List<Annotation> annotations, AtomicReference<Integer> years, AtomicReference<Integer> months, AtomicReference<Integer> days) {

        YearMonth now = YearMonth.now();

        if (annotations.stream().anyMatch(Future.class::isInstance)) {

            YearMonth futur = now.plusMonths(6L);
            years.set(PodamUtils.getIntegerInRange(futur.getYear(), Year.MAX_VALUE));

        } else if (annotations.stream().anyMatch(FutureOrPresent.class::isInstance)) {

            years.set(PodamUtils.getIntegerInRange(now.getYear(), Year.MAX_VALUE));

        } else if (annotations.stream().anyMatch(PastOrPresent.class::isInstance)) {

            years.set(PodamUtils.getIntegerInRange(Year.MIN_VALUE, now.getYear()));

        } else if (annotations.stream().anyMatch(Past.class::isInstance)) {

            YearMonth past = now.minusMonths(6L);
            years.set(PodamUtils.getIntegerInRange(Year.MIN_VALUE, past.getYear()));

        } else {

            years.set(PodamUtils.getIntegerInRange(Year.MIN_VALUE, Year.MAX_VALUE));
        }

        months.set(PodamUtils.getIntegerInRange(1, 12));
        days.set(PodamUtils.getIntegerInRange(1, YearMonth.of(years.get(), months.get()).atEndOfMonth().getDayOfMonth()));
    }
}
