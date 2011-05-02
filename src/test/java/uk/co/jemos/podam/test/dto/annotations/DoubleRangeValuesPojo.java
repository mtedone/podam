/**
 * 
 */
package uk.co.jemos.podam.test.dto.annotations;

import java.io.Serializable;

import uk.co.jemos.podam.annotations.PodamDoubleValue;
import uk.co.jemos.podam.test.utils.PodamTestConstants;

/**
 * POJO to test the {@link PodamDoubleValue} annotation
 * 
 * @author mtedone
 * 
 */
public class DoubleRangeValuesPojo implements Serializable {

	// ------------------->> Constants

	private static final long serialVersionUID = 1L;

	// ------------------->> Instance / Static variables

	@PodamDoubleValue(minValue = PodamTestConstants.NUMBER_DOUBLE_MIN_VALUE)
	private double doubleFieldWithMinValueOnly;

	@PodamDoubleValue(maxValue = PodamTestConstants.NUMBER_DOUBLE_ONE_HUNDRED)
	private double doubleFieldWithMaxValueOnly;

	@PodamDoubleValue(minValue = PodamTestConstants.NUMBER_DOUBLE_MIN_VALUE, maxValue = PodamTestConstants.NUMBER_DOUBLE_MAX_VALUE)
	private double doubleFieldWithMinAndMaxValue;

	@PodamDoubleValue(minValue = PodamTestConstants.NUMBER_DOUBLE_MIN_VALUE)
	private Double doubleObjectFieldWithMinValueOnly;

	@PodamDoubleValue(maxValue = PodamTestConstants.NUMBER_DOUBLE_ONE_HUNDRED)
	private Double doubleObjectFieldWithMaxValueOnly;

	@PodamDoubleValue(minValue = PodamTestConstants.NUMBER_DOUBLE_MIN_VALUE, maxValue = PodamTestConstants.NUMBER_DOUBLE_MAX_VALUE)
	private Double doubleObjectFieldWithMinAndMaxValue;

	// ------------------->> Constructors

	// ------------------->> Public methods

	// ------------------->> Getters / Setters

	/**
	 * @return the doubleFieldWithMinValueOnly
	 */
	public double getDoubleFieldWithMinValueOnly() {
		return doubleFieldWithMinValueOnly;
	}

	/**
	 * @param doubleFieldWithMinValueOnly
	 *            the doubleFieldWithMinValueOnly to set
	 */
	public void setDoubleFieldWithMinValueOnly(
			double doubleFieldWithMinValueOnly) {
		this.doubleFieldWithMinValueOnly = doubleFieldWithMinValueOnly;
	}

	/**
	 * @return the doubleFieldWithMaxValueOnly
	 */
	public double getDoubleFieldWithMaxValueOnly() {
		return doubleFieldWithMaxValueOnly;
	}

	/**
	 * @param doubleFieldWithMaxValueOnly
	 *            the doubleFieldWithMaxValueOnly to set
	 */
	public void setDoubleFieldWithMaxValueOnly(
			double doubleFieldWithMaxValueOnly) {
		this.doubleFieldWithMaxValueOnly = doubleFieldWithMaxValueOnly;
	}

	/**
	 * @return the doubleFieldWithMinAndMaxValue
	 */
	public double getDoubleFieldWithMinAndMaxValue() {
		return doubleFieldWithMinAndMaxValue;
	}

	/**
	 * @param doubleFieldWithMinAndMaxValue
	 *            the doubleFieldWithMinAndMaxValue to set
	 */
	public void setDoubleFieldWithMinAndMaxValue(
			double doubleFieldWithMinAndMaxValue) {
		this.doubleFieldWithMinAndMaxValue = doubleFieldWithMinAndMaxValue;
	}

	/**
	 * @return the doubleObjectFieldWithMinValueOnly
	 */
	public Double getDoubleObjectFieldWithMinValueOnly() {
		return doubleObjectFieldWithMinValueOnly;
	}

	/**
	 * @param doubleObjectFieldWithMinValueOnly
	 *            the doubleObjectFieldWithMinValueOnly to set
	 */
	public void setDoubleObjectFieldWithMinValueOnly(
			Double doubleObjectFieldWithMinValueOnly) {
		this.doubleObjectFieldWithMinValueOnly = doubleObjectFieldWithMinValueOnly;
	}

	/**
	 * @return the doubleObjectFieldWithMaxValueOnly
	 */
	public Double getDoubleObjectFieldWithMaxValueOnly() {
		return doubleObjectFieldWithMaxValueOnly;
	}

	/**
	 * @param doubleObjectFieldWithMaxValueOnly
	 *            the doubleObjectFieldWithMaxValueOnly to set
	 */
	public void setDoubleObjectFieldWithMaxValueOnly(
			Double doubleObjectFieldWithMaxValueOnly) {
		this.doubleObjectFieldWithMaxValueOnly = doubleObjectFieldWithMaxValueOnly;
	}

	/**
	 * @return the doubleObjectFieldWithMinAndMaxValue
	 */
	public Double getDoubleObjectFieldWithMinAndMaxValue() {
		return doubleObjectFieldWithMinAndMaxValue;
	}

	/**
	 * @param doubleObjectFieldWithMinAndMaxValue
	 *            the doubleObjectFieldWithMinAndMaxValue to set
	 */
	public void setDoubleObjectFieldWithMinAndMaxValue(
			Double doubleObjectFieldWithMinAndMaxValue) {
		this.doubleObjectFieldWithMinAndMaxValue = doubleObjectFieldWithMinAndMaxValue;
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

		retValue.append("DoubleRangeValuesPojo ( ")
				.append("doubleFieldWithMinValueOnly = ")
				.append(doubleFieldWithMinValueOnly).append(TAB)
				.append("doubleFieldWithMaxValueOnly = ")
				.append(doubleFieldWithMaxValueOnly).append(TAB)
				.append("doubleFieldWithMinAndMaxValue = ")
				.append(doubleFieldWithMinAndMaxValue).append(TAB)
				.append("doubleObjectFieldWithMinValueOnly = ")
				.append(doubleObjectFieldWithMinValueOnly).append(TAB)
				.append("doubleObjectFieldWithMaxValueOnly = ")
				.append(doubleObjectFieldWithMaxValueOnly).append(TAB)
				.append("doubleObjectFieldWithMinAndMaxValue = ")
				.append(doubleObjectFieldWithMinAndMaxValue).append(TAB)
				.append(" )");

		return retValue.toString();
	}

	// ------------------->> Private methods

	// ------------------->> equals() / hashcode() / toString()

	// ------------------->> Inner classes

}
