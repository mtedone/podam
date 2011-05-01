/**
 * 
 */
package uk.co.jemos.podam.test.dto.annotations;

import java.io.Serializable;

import uk.co.jemos.podam.annotations.PodamShortValue;
import uk.co.jemos.podam.test.utils.PodamTestConstants;

/**
 * POJO to test {@link PodamShortValue} annotation
 * 
 * @author mtedone
 * 
 */
public class ShortRangeValuesPojo implements Serializable {

	// ------------------->> Constants

	private static final long serialVersionUID = 1L;

	// ------------------->> Instance / Static variables

	@PodamShortValue(minValue = PodamTestConstants.NUMBER_INT_MIN_VALUE)
	private short shortFieldWithMinValueOnly;

	@PodamShortValue(maxValue = PodamTestConstants.NUMBER_INT_ONE_HUNDRED)
	private short shortFieldWithMaxValueOnly;

	@PodamShortValue(minValue = PodamTestConstants.NUMBER_INT_MIN_VALUE, maxValue = PodamTestConstants.NUMBER_INT_ONE_HUNDRED)
	private short shortFieldWithMinAndMaxValue;

	@PodamShortValue(minValue = PodamTestConstants.NUMBER_INT_MIN_VALUE)
	private Short shortObjectFieldWithMinValueOnly;

	@PodamShortValue(maxValue = PodamTestConstants.NUMBER_INT_ONE_HUNDRED)
	private Short shortObjectFieldWithMaxValueOnly;

	@PodamShortValue(minValue = PodamTestConstants.NUMBER_INT_MIN_VALUE, maxValue = PodamTestConstants.NUMBER_INT_ONE_HUNDRED)
	private Short shortObjectFieldWithMinAndMaxValue;

	// ------------------->> Constructors

	// ------------------->> Public methods

	// ------------------->> Getters / Setters

	/**
	 * @return the shortFieldWithMinValueOnly
	 */
	public short getShortFieldWithMinValueOnly() {
		return shortFieldWithMinValueOnly;
	}

	/**
	 * @param shortFieldWithMinValueOnly
	 *            the shortFieldWithMinValueOnly to set
	 */
	public void setShortFieldWithMinValueOnly(short shortFieldWithMinValueOnly) {
		this.shortFieldWithMinValueOnly = shortFieldWithMinValueOnly;
	}

	/**
	 * @return the shortFieldWithMaxValueOnly
	 */
	public short getShortFieldWithMaxValueOnly() {
		return shortFieldWithMaxValueOnly;
	}

	/**
	 * @param shortFieldWithMaxValueOnly
	 *            the shortFieldWithMaxValueOnly to set
	 */
	public void setShortFieldWithMaxValueOnly(short shortFieldWithMaxValueOnly) {
		this.shortFieldWithMaxValueOnly = shortFieldWithMaxValueOnly;
	}

	/**
	 * @return the shortFieldWithMinAndMaxValue
	 */
	public short getShortFieldWithMinAndMaxValue() {
		return shortFieldWithMinAndMaxValue;
	}

	/**
	 * @param shortFieldWithMinAndMaxValue
	 *            the shortFieldWithMinAndMaxValue to set
	 */
	public void setShortFieldWithMinAndMaxValue(
			short shortFieldWithMinAndMaxValue) {
		this.shortFieldWithMinAndMaxValue = shortFieldWithMinAndMaxValue;
	}

	/**
	 * @return the shortObjectFieldWithMinValueOnly
	 */
	public Short getShortObjectFieldWithMinValueOnly() {
		return shortObjectFieldWithMinValueOnly;
	}

	/**
	 * @param shortObjectFieldWithMinValueOnly
	 *            the shortObjectFieldWithMinValueOnly to set
	 */
	public void setShortObjectFieldWithMinValueOnly(
			Short shortObjectFieldWithMinValueOnly) {
		this.shortObjectFieldWithMinValueOnly = shortObjectFieldWithMinValueOnly;
	}

	/**
	 * @return the shortObjectFieldWithMaxValueOnly
	 */
	public Short getShortObjectFieldWithMaxValueOnly() {
		return shortObjectFieldWithMaxValueOnly;
	}

	/**
	 * @param shortObjectFieldWithMaxValueOnly
	 *            the shortObjectFieldWithMaxValueOnly to set
	 */
	public void setShortObjectFieldWithMaxValueOnly(
			Short shortObjectFieldWithMaxValueOnly) {
		this.shortObjectFieldWithMaxValueOnly = shortObjectFieldWithMaxValueOnly;
	}

	/**
	 * @return the shortObjectFieldWithMinAndMaxValue
	 */
	public Short getShortObjectFieldWithMinAndMaxValue() {
		return shortObjectFieldWithMinAndMaxValue;
	}

	/**
	 * @param shortObjectFieldWithMinAndMaxValue
	 *            the shortObjectFieldWithMinAndMaxValue to set
	 */
	public void setShortObjectFieldWithMinAndMaxValue(
			Short shortObjectFieldWithMinAndMaxValue) {
		this.shortObjectFieldWithMinAndMaxValue = shortObjectFieldWithMinAndMaxValue;
	}

	// ------------------->> Private methods

	// ------------------->> equals() / hashcode() / toString()

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

		retValue.append("ShortRangeValuesPojo ( ")
				.append("intFieldWithMinValueOnly = ")
				.append(shortFieldWithMinValueOnly).append(TAB)
				.append("intFieldWithMaxValueOnly = ")
				.append(shortFieldWithMaxValueOnly).append(TAB)
				.append("intFieldWithMinAndMaxValue = ")
				.append(shortFieldWithMinAndMaxValue).append(TAB)
				.append("integerObjectFieldWithMinValueOnly = ")
				.append(shortObjectFieldWithMinValueOnly).append(TAB)
				.append("integerObjectFieldWithMaxValueOnly = ")
				.append(shortObjectFieldWithMaxValueOnly).append(TAB)
				.append("integerObjectFieldWithMinAndMaxValue = ")
				.append(shortObjectFieldWithMinAndMaxValue).append(TAB)
				.append(" )");

		return retValue.toString();
	}

	// ------------------->> Inner classes

}
