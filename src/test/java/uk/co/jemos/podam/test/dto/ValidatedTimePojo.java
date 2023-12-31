/**
 *
 */
package uk.co.jemos.podam.test.dto;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZonedDateTime;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.PastOrPresent;

/**
 * POJO to test bean validation API 2.0
 *
 * @author daivanov
 *
 */
public class ValidatedTimePojo {

	@Past
	private Instant past;

	@PastOrPresent
	private LocalDateTime pastOrPresent;

	@Future
	private OffsetDateTime future;

	@FutureOrPresent
	private ZonedDateTime futureOrPresent;

	public Instant getPast() {
		return past;
	}

	public void setPast(Instant past) {
		this.past = past;
	}

	public LocalDateTime getPastOrPresent() {
		return pastOrPresent;
	}

	public void setPastOrPresent(LocalDateTime pastOrPresent) {
		this.pastOrPresent = pastOrPresent;
	}

	public OffsetDateTime getFuture() {
		return future;
	}

	public void setFuture(OffsetDateTime future) {
		this.future = future;
	}

	public ZonedDateTime getFutureOrPresent() {
		return futureOrPresent;
	}

	public void setFutureOrPresent(ZonedDateTime futureOrPresent) {
		this.futureOrPresent = futureOrPresent;
	}

}
