/**
 * 
 */
package uk.co.jemos.podam.test.dto.annotations;

import java.io.Serializable;

import uk.co.jemos.podam.annotations.PodamIntValue;
import uk.co.jemos.podam.test.utils.PodamTestConstants;

/**
 * POJO to test the {@link PodamIntValue} annotation
 * 
 * @author mtedone
 * 
 */
public class IntegerRangeValuesPojo implements Serializable {

	// ------------------->> Constants

	private static final long serialVersionUID = 1L;

	// ------------------->> Instance / Static variables

	@PodamIntValue(minValue = PodamTestConstants.NUMBER_INT_MIN_VALUE)
	private int intFieldWithMinValueOnly;

	@PodamIntValue(maxValue = PodamTestConstants.NUMBER_INT_ONE_HUNDRED)
	private int intFieldWithMaxValueOnly;

	@PodamIntValue(minValue = PodamTestConstants.NUMBER_INT_MIN_VALUE, maxValue = PodamTestConstants.NUMBER_INT_MAX_VALUE)
	private int intFieldWithMinAndMaxValue;

	@PodamIntValue(minValue = PodamTestConstants.NUMBER_INT_MIN_VALUE)
	private Integer integerObjectFieldWithMinValueOnly;

	@PodamIntValue(maxValue = PodamTestConstants.NUMBER_INT_ONE_HUNDRED)
	private Integer integerObjectFieldWithMaxValueOnly;

	@PodamIntValue(minValue = PodamTestConstants.NUMBER_INT_MIN_VALUE, maxValue = PodamTestConstants.NUMBER_INT_MAX_VALUE)
	private Integer integerObjectFieldWithMinAndMaxValue;

	// ------------------->> Constructors

	// ------------------->> Public methods

	// ------------------->> Getters / Setters

	/**
	 * @return the intFieldWithMinValueOnly
	 */
	public int getIntFieldWithMinValueOnly() {
		return intFieldWithMinValueOnly;
	}

	/**
	 * @param intFieldWithMinValueOnly
	 *            the intFieldWithMinValueOnly to set
	 */
	public void setIntFieldWithMinValueOnly(int intFieldWithMinValueOnly) {
		this.intFieldWithMinValueOnly = intFieldWithMinValueOnly;
	}

	/**
	 * @return the intFieldWithMaxValueOnly
	 */
	public int getIntFieldWithMaxValueOnly() {
		return intFieldWithMaxValueOnly;
	}

	/**
	 * @param intFieldWithMaxValueOnly
	 *            the intFieldWithMaxValueOnly to set
	 */
	public void setIntFieldWithMaxValueOnly(int intFieldWithMaxValueOnly) {
		this.intFieldWithMaxValueOnly = intFieldWithMaxValueOnly;
	}

	/**
	 * @return the intFieldWithMinAndMaxValue
	 */
	public int getIntFieldWithMinAndMaxValue() {
		return intFieldWithMinAndMaxValue;
	}

	/**
	 * @param intFieldWithMinAndMaxValue
	 *            the intFieldWithMinAndMaxValue to set
	 */
	public void setIntFieldWithMinAndMaxValue(int intFieldWithMinAndMaxValue) {
		this.intFieldWithMinAndMaxValue = intFieldWithMinAndMaxValue;
	}

	/**
	 * @return the integerObjectFieldWithMinValueOnly
	 */
	public Integer getIntegerObjectFieldWithMinValueOnly() {
		return integerObjectFieldWithMinValueOnly;
	}

	/**
	 * @param integerObjectFieldWithMinValueOnly
	 *            the integerObjectFieldWithMinValueOnly to set
	 */
	public void setIntegerObjectFieldWithMinValueOnly(
			Integer integerObjectFieldWithMinValueOnly) {
		this.integerObjectFieldWithMinValueOnly = integerObjectFieldWithMinValueOnly;
	}

	/**
	 * @return the integerObjectFieldWithMaxValueOnly
	 */
	public Integer getIntegerObjectFieldWithMaxValueOnly() {
		return integerObjectFieldWithMaxValueOnly;
	}

	/**
	 * @param integerObjectFieldWithMaxValueOnly
	 *            the integerObjectFieldWithMaxValueOnly to set
	 */
	public void setIntegerObjectFieldWithMaxValueOnly(
			Integer integerObjectFieldWithMaxValueOnly) {
		this.integerObjectFieldWithMaxValueOnly = integerObjectFieldWithMaxValueOnly;
	}

	/**
	 * @return the integerObjectFieldWithMinAndMaxValue
	 */
	public Integer getIntegerObjectFieldWithMinAndMaxValue() {
		return integerObjectFieldWithMinAndMaxValue;
	}

	/**
	 * @param integerObjectFieldWithMinAndMaxValue
	 *            the integerObjectFieldWithMinAndMaxValue to set
	 */
	public void setIntegerObjectFieldWithMinAndMaxValue(
			Integer integerObjectFieldWithMinAndMaxValue) {
		this.integerObjectFieldWithMinAndMaxValue = integerObjectFieldWithMinAndMaxValue;
	}

	/**
	 * Constructs a <code>String</code> with all attributes in name = value
	 * format.
	 * 
	 * @return a <code>String</code> representation of this object.
	 */
	@Override
	public String toString() {
		final String TAB = "    ";

		StringBuilder retValue = new StringBuilder();

		retValue.append("IntegerRangeValuesPojo ( ")
				.append("intFieldWithMinValueOnly = ")
				.append(intFieldWithMinValueOnly).append(TAB)
				.append("intFieldWithMaxValueOnly = ")
				.append(intFieldWithMaxValueOnly).append(TAB)
				.append("intFieldWithMinAndMaxValue = ")
				.append(intFieldWithMinAndMaxValue).append(TAB)
				.append("integerObjectFieldWithMinValueOnly = ")
				.append(integerObjectFieldWithMinValueOnly).append(TAB)
				.append("integerObjectFieldWithMaxValueOnly = ")
				.append(integerObjectFieldWithMaxValueOnly).append(TAB)
				.append("integerObjectFieldWithMinAndMaxValue = ")
				.append(integerObjectFieldWithMinAndMaxValue).append(TAB)
				.append(" )");

		return retValue.toString();
	}

	// ------------------->> Private methods

	// ------------------->> equals() / hashcode() / toString()

	// ------------------->> Inner classes

}
