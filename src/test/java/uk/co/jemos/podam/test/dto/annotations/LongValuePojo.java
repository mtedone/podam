/**
 * 
 */
package uk.co.jemos.podam.test.dto.annotations;

import java.io.Serializable;

import uk.co.jemos.podam.common.PodamLongValue;
import uk.co.jemos.podam.test.utils.PodamTestConstants;

/**
 * @author mtedone
 * 
 */
public class LongValuePojo implements Serializable {

	// ------------------->> Constants

	private static final long serialVersionUID = 1L;

	// ------------------->> Instance / Static variables

	@PodamLongValue(minValue = 0)
	private long longFieldWithMinValueOnly;

	@PodamLongValue(maxValue = 100)
	private long longFieldWithMaxValueOnly;

	@PodamLongValue(minValue = 0, maxValue = 1000)
	private long longFieldWithMinAndMaxValue;

	@PodamLongValue(numValue = PodamTestConstants.LONG_PRECISE_VALUE)
	private long longFieldWithPreciseValue;

	@PodamLongValue(minValue = 0)
	private Long longObjectFieldWithMinValueOnly;

	@PodamLongValue(maxValue = 100)
	private Long longObjectFieldWithMaxValueOnly;

	@PodamLongValue(minValue = 0, maxValue = 1000)
	private Long longObjectFieldWithMinAndMaxValue;

	@PodamLongValue(numValue = PodamTestConstants.LONG_PRECISE_VALUE)
	private Long longObjectFieldWithPreciseValue;

	// ------------------->> Constructors

	// ------------------->> Public methods

	// ------------------->> Getters / Setters

	/**
	 * @return the longFieldWithMinValueOnly
	 */
	public long getLongFieldWithMinValueOnly() {
		return longFieldWithMinValueOnly;
	}

	/**
	 * @param longFieldWithMinValueOnly
	 *            the longFieldWithMinValueOnly to set
	 */
	public void setLongFieldWithMinValueOnly(long longFieldWithMinValueOnly) {
		this.longFieldWithMinValueOnly = longFieldWithMinValueOnly;
	}

	/**
	 * @return the longFieldWithMaxValueOnly
	 */
	public long getLongFieldWithMaxValueOnly() {
		return longFieldWithMaxValueOnly;
	}

	/**
	 * @param longFieldWithMaxValueOnly
	 *            the longFieldWithMaxValueOnly to set
	 */
	public void setLongFieldWithMaxValueOnly(long longFieldWithMaxValueOnly) {
		this.longFieldWithMaxValueOnly = longFieldWithMaxValueOnly;
	}

	/**
	 * @return the longFieldWithMinAndMaxValue
	 */
	public long getLongFieldWithMinAndMaxValue() {
		return longFieldWithMinAndMaxValue;
	}

	/**
	 * @param longFieldWithMinAndMaxValue
	 *            the longFieldWithMinAndMaxValue to set
	 */
	public void setLongFieldWithMinAndMaxValue(long longFieldWithMinAndMaxValue) {
		this.longFieldWithMinAndMaxValue = longFieldWithMinAndMaxValue;
	}

	/**
	 * @return the longObjectFieldWithMinValueOnly
	 */
	public Long getLongObjectFieldWithMinValueOnly() {
		return longObjectFieldWithMinValueOnly;
	}

	/**
	 * @param longObjectFieldWithMinValueOnly
	 *            the longObjectFieldWithMinValueOnly to set
	 */
	public void setLongObjectFieldWithMinValueOnly(
			Long longObjectFieldWithMinValueOnly) {
		this.longObjectFieldWithMinValueOnly = longObjectFieldWithMinValueOnly;
	}

	/**
	 * @return the longObjectFieldWithMaxValueOnly
	 */
	public Long getLongObjectFieldWithMaxValueOnly() {
		return longObjectFieldWithMaxValueOnly;
	}

	/**
	 * @param longObjectFieldWithMaxValueOnly
	 *            the longObjectFieldWithMaxValueOnly to set
	 */
	public void setLongObjectFieldWithMaxValueOnly(
			Long longObjectFieldWithMaxValueOnly) {
		this.longObjectFieldWithMaxValueOnly = longObjectFieldWithMaxValueOnly;
	}

	/**
	 * @return the longObjectFieldWithMinAndMaxValue
	 */
	public Long getLongObjectFieldWithMinAndMaxValue() {
		return longObjectFieldWithMinAndMaxValue;
	}

	/**
	 * @param longObjectFieldWithMinAndMaxValue
	 *            the longObjectFieldWithMinAndMaxValue to set
	 */
	public void setLongObjectFieldWithMinAndMaxValue(
			Long longObjectFieldWithMinAndMaxValue) {
		this.longObjectFieldWithMinAndMaxValue = longObjectFieldWithMinAndMaxValue;
	}

	/**
	 * @return the longFieldWithPreciseValue
	 */
	public long getLongFieldWithPreciseValue() {
		return longFieldWithPreciseValue;
	}

	/**
	 * @param longFieldWithPreciseValue
	 *            the longFieldWithPreciseValue to set
	 */
	public void setLongFieldWithPreciseValue(long longFieldWithPreciseValue) {
		this.longFieldWithPreciseValue = longFieldWithPreciseValue;
	}

	/**
	 * @return the longObjectFieldWithPreciseValue
	 */
	public Long getLongObjectFieldWithPreciseValue() {
		return longObjectFieldWithPreciseValue;
	}

	/**
	 * @param longObjectFieldWithPreciseValue
	 *            the longObjectFieldWithPreciseValue to set
	 */
	public void setLongObjectFieldWithPreciseValue(
			Long longObjectFieldWithPreciseValue) {
		this.longObjectFieldWithPreciseValue = longObjectFieldWithPreciseValue;
	}

	/**
	 * Constructs a <code>String</code> with all attributes
	 * in name = value format.
	 *
	 * @return a <code>String</code> representation 
	 * of this object.
	 */
	public String toString()
	{
	    final String TAB = "    ";
	
	    StringBuilder retValue = new StringBuilder();
	    
	    retValue.append("LongRangeValuesPojo ( ")        
	        .append("longFieldWithMinValueOnly = ").append(this.longFieldWithMinValueOnly).append(TAB)
	        .append("longFieldWithMaxValueOnly = ").append(this.longFieldWithMaxValueOnly).append(TAB)
	        .append("longFieldWithMinAndMaxValue = ").append(this.longFieldWithMinAndMaxValue).append(TAB)
	        .append("longFieldWithPreciseValue = ").append(this.longFieldWithPreciseValue).append(TAB)
	        .append("longObjectFieldWithMinValueOnly = ").append(this.longObjectFieldWithMinValueOnly).append(TAB)
	        .append("longObjectFieldWithMaxValueOnly = ").append(this.longObjectFieldWithMaxValueOnly).append(TAB)
	        .append("longObjectFieldWithMinAndMaxValue = ").append(this.longObjectFieldWithMinAndMaxValue).append(TAB)
	        .append("longObjectFieldWithPreciseValue = ").append(this.longObjectFieldWithPreciseValue).append(TAB)
	        .append(" )");
	    
	    return retValue.toString();
	}

	// ------------------->> Private methods

	// ------------------->> equals() / hashcode() / toString()

	// ------------------->> Inner classes

}
