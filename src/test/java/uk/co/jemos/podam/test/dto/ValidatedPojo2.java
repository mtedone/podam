/**
 *
 */
package uk.co.jemos.podam.test.dto;


import jakarta.validation.constraints.Negative;
import jakarta.validation.constraints.NegativeOrZero;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;

/**
 * POJO to test bean validation API 2.0
 *
 * @author daivanov
 *
 */
public class ValidatedPojo2 {

	@PositiveOrZero
	private int intPositiveOrZero;

	@Positive
	private int intPositive;

	@NegativeOrZero
	private int intNegativeOrZero;

	@Negative
	private int intNegative;

	public int getIntPositive() {
		return intPositive;
	}

	public void setIntPositive(int intPositive) {
		this.intPositive = intPositive;
	}

	public int getIntPositiveOrZero() {
		return intPositiveOrZero;
	}

	public void setIntPositiveOrZero(int intPositiveOrZero) {
		this.intPositiveOrZero = intPositiveOrZero;
	}

	public int getIntNegative() {
		return intNegative;
	}

	public void setIntNegative(int intNegative) {
		this.intNegative = intNegative;
	}

	public int getIntNegativeOrZero() {
		return intNegativeOrZero;
	}

	public void setIntNegativeOrZero(int intNegativeOrZero) {
		this.intNegativeOrZero = intNegativeOrZero;
	}

}
