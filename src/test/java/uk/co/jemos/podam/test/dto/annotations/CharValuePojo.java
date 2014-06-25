/**
 * 
 */
package uk.co.jemos.podam.test.dto.annotations;

import java.io.Serializable;

import uk.co.jemos.podam.common.PodamCharValue;
import uk.co.jemos.podam.test.utils.PodamTestConstants;

/**
 * POJO to test {@link PodamCharValue} annotation
 * 
 * @author mtedone
 * 
 */
public class CharValuePojo implements Serializable {

	// ------------------->> Constants

	private static final long serialVersionUID = 1L;

	// ------------------->> Instance / Static variables

	@PodamCharValue(minValue = PodamTestConstants.NUMBER_INT_MIN_VALUE)
	private char charFieldWithMinValueOnly;

	@PodamCharValue(maxValue = PodamTestConstants.NUMBER_INT_ONE_HUNDRED)
	private char charFieldWithMaxValueOnly;

	@PodamCharValue(minValue = PodamTestConstants.NUMBER_INT_MIN_VALUE, maxValue = PodamTestConstants.NUMBER_INT_ONE_HUNDRED)
	private char charFieldWithMinAndMaxValue;

	@PodamCharValue(charValue = ' ')
	private char charFieldWithBlankInPreciseValue;

	@PodamCharValue(minValue = PodamTestConstants.NUMBER_INT_MIN_VALUE)
	private Character charObjectFieldWithMinValueOnly;

	@PodamCharValue(maxValue = PodamTestConstants.NUMBER_INT_ONE_HUNDRED)
	private Character charObjectFieldWithMaxValueOnly;

	@PodamCharValue(minValue = PodamTestConstants.NUMBER_INT_MIN_VALUE, maxValue = PodamTestConstants.NUMBER_INT_ONE_HUNDRED)
	private Character charObjectFieldWithMinAndMaxValue;

	@PodamCharValue(charValue = PodamTestConstants.CHAR_PRECISE_VALUE)
	private char charFieldWithPreciseValue;

	// ------------------->> Constructors

	// ------------------->> Public methods

	// ------------------->> Getters / Setters

	/**
	 * @return the charFieldWithMinValueOnly
	 */
	public char getCharFieldWithMinValueOnly() {
		return charFieldWithMinValueOnly;
	}

	/**
	 * @param charFieldWithMinValueOnly
	 *            the charFieldWithMinValueOnly to set
	 */
	public void setCharFieldWithMinValueOnly(char charFieldWithMinValueOnly) {
		this.charFieldWithMinValueOnly = charFieldWithMinValueOnly;
	}

	/**
	 * @return the charFieldWithMaxValueOnly
	 */
	public char getCharFieldWithMaxValueOnly() {
		return charFieldWithMaxValueOnly;
	}

	/**
	 * @param charFieldWithMaxValueOnly
	 *            the charFieldWithMaxValueOnly to set
	 */
	public void setCharFieldWithMaxValueOnly(char charFieldWithMaxValueOnly) {
		this.charFieldWithMaxValueOnly = charFieldWithMaxValueOnly;
	}

	/**
	 * @return the charFieldWithMinAndMaxValue
	 */
	public char getCharFieldWithMinAndMaxValue() {
		return charFieldWithMinAndMaxValue;
	}

	/**
	 * @param charFieldWithMinAndMaxValue
	 *            the charFieldWithMinAndMaxValue to set
	 */
	public void setCharFieldWithMinAndMaxValue(char charFieldWithMinAndMaxValue) {
		this.charFieldWithMinAndMaxValue = charFieldWithMinAndMaxValue;
	}

	/**
	 * @return the charObjectFieldWithMinValueOnly
	 */
	public Character getCharObjectFieldWithMinValueOnly() {
		return charObjectFieldWithMinValueOnly;
	}

	/**
	 * @param charObjectFieldWithMinValueOnly
	 *            the charObjectFieldWithMinValueOnly to set
	 */
	public void setCharObjectFieldWithMinValueOnly(
			Character charObjectFieldWithMinValueOnly) {
		this.charObjectFieldWithMinValueOnly = charObjectFieldWithMinValueOnly;
	}

	/**
	 * @return the charObjectFieldWithMaxValueOnly
	 */
	public Character getCharObjectFieldWithMaxValueOnly() {
		return charObjectFieldWithMaxValueOnly;
	}

	/**
	 * @param charObjectFieldWithMaxValueOnly
	 *            the charObjectFieldWithMaxValueOnly to set
	 */
	public void setCharObjectFieldWithMaxValueOnly(
			Character charObjectFieldWithMaxValueOnly) {
		this.charObjectFieldWithMaxValueOnly = charObjectFieldWithMaxValueOnly;
	}

	/**
	 * @return the charObjectFieldWithMinAndMaxValue
	 */
	public Character getCharObjectFieldWithMinAndMaxValue() {
		return charObjectFieldWithMinAndMaxValue;
	}

	/**
	 * @param charObjectFieldWithMinAndMaxValue
	 *            the charObjectFieldWithMinAndMaxValue to set
	 */
	public void setCharObjectFieldWithMinAndMaxValue(
			Character charObjectFieldWithMinAndMaxValue) {
		this.charObjectFieldWithMinAndMaxValue = charObjectFieldWithMinAndMaxValue;
	}

	/**
	 * @return the charFieldWithPreciseValue
	 */
	public char getCharFieldWithPreciseValue() {
		return charFieldWithPreciseValue;
	}

	/**
	 * @param charFieldWithPreciseValue
	 *            the charFieldWithPreciseValue to set
	 */
	public void setCharFieldWithPreciseValue(char charFieldWithPreciseValue) {
		this.charFieldWithPreciseValue = charFieldWithPreciseValue;
	}

	// ------------------->> Private methods

	// ------------------->> equals() / hashcode() / toString()

	/**
	 * @return the charFieldWithBlankInPreciseValue
	 */
	public char getCharFieldWithBlankInPreciseValue() {
		return charFieldWithBlankInPreciseValue;
	}

	/**
	 * @param charFieldWithBlankInPreciseValue
	 *            the charFieldWithBlankInPreciseValue to set
	 */
	public void setCharFieldWithBlankInPreciseValue(
			char charFieldWithBlankInPreciseValue) {
		this.charFieldWithBlankInPreciseValue = charFieldWithBlankInPreciseValue;
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
	    
	    retValue.append("CharValuePojo ( ")        
	        .append("charFieldWithMinValueOnly = ").append(this.charFieldWithMinValueOnly).append(TAB)
	        .append("charFieldWithMaxValueOnly = ").append(this.charFieldWithMaxValueOnly).append(TAB)
	        .append("charFieldWithMinAndMaxValue = ").append(this.charFieldWithMinAndMaxValue).append(TAB)
	        .append("charFieldWithBlankInPreciseValue = ").append(this.charFieldWithBlankInPreciseValue).append(TAB)
	        .append("charObjectFieldWithMinValueOnly = ").append(this.charObjectFieldWithMinValueOnly).append(TAB)
	        .append("charObjectFieldWithMaxValueOnly = ").append(this.charObjectFieldWithMaxValueOnly).append(TAB)
	        .append("charObjectFieldWithMinAndMaxValue = ").append(this.charObjectFieldWithMinAndMaxValue).append(TAB)
	        .append("charFieldWithPreciseValue = ").append(this.charFieldWithPreciseValue).append(TAB)
	        .append(" )");
	    
	    return retValue.toString();
	}

	// ------------------->> Inner classes

}
