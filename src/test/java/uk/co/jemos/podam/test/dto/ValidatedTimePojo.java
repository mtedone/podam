/**
 *
 */
package uk.co.jemos.podam.test.dto;

import java.util.Date;

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
	private Date past;

	@PastOrPresent
	private Date pastOrPresent;

	@Future
	private Date future;

	@FutureOrPresent
	private Date futureOrPresent;

	public Date getPast() {
		return past;
	}

	public void setPast(Date past) {
		this.past = past;
	}

	public Date getPastOrPresent() {
		return pastOrPresent;
	}

	public void setPastOrPresent(Date pastOrPresent) {
		this.pastOrPresent = pastOrPresent;
	}

	public Date getFuture() {
		return future;
	}

	public void setFuture(Date future) {
		this.future = future;
	}

	public Date getFutureOrPresent() {
		return futureOrPresent;
	}

	public void setFutureOrPresent(Date futureOrPresent) {
		this.futureOrPresent = futureOrPresent;
	}

}
