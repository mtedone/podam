/**
 *
 */
package uk.co.jemos.podam.test.dto;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.OffsetDateTime;
import java.time.OffsetTime;
import java.time.YearMonth;
import java.time.Year;
import java.time.ZonedDateTime;
import java.time.chrono.HijrahDate;
import java.time.chrono.JapaneseDate;
import java.time.chrono.MinguoDate;
import java.time.chrono.ThaiBuddhistDate;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.PastOrPresent;

import lombok.Getter;
import lombok.Setter;

/**
 * POJO to test bean validation API 2.0
 *
 * @author daivanov
 *
 */
public class ValidatedTimePojo {

	@Getter @Setter @Past
	private Instant past;

	@Getter @Setter @PastOrPresent
	private LocalDateTime pastOrPresent;

	@Getter @Setter @Future
	private OffsetDateTime future;

	@Getter @Setter @FutureOrPresent
	private ZonedDateTime futureOrPresent;

	@Getter @Setter @Future.List(value = {@Future(message = "must be in the future")}) @NotNull(message = "must be not null")
	private Instant instantWithFutureList;

	@Getter @Setter @Past.List(value = {@Past(message = "must be in the past")}) @NotNull(message = "must be not null")
	private Instant instantWithPastList;

	@Getter @Setter @Past
	private YearMonth yearMonth;

	@Getter @Setter @Future
	private LocalDate localDate;

	@Getter @Setter @Past
	private HijrahDate hijrahDate;

	@Getter @Setter @Future
	private JapaneseDate japaneseDate;

	@Getter @Setter @Past
	private MinguoDate minguoDate;

	@Getter @Setter @Future
	private ThaiBuddhistDate thaiBuddhistDate;

	@Getter @Setter @PastOrPresent
	private LocalTime localTime;

	@Getter @Setter @FutureOrPresent
	private OffsetTime offsetTime;

	@Getter @Setter @Future
	private Year year;
}
