/**
 *
 */
package uk.co.jemos.podam.test.dto;


import org.hibernate.validator.constraints.Email;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;

import javax.validation.constraints.AssertFalse;
import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Future;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 * POJO to test bean validation API
 *
 * @author daivanov
 *
 */
public class ValidatedPojo {

	@AssertTrue
	private Boolean boolTrue;

	@AssertFalse
	private Boolean boolFalse;

	@NotNull
	private String filledString;

	@Null
	private String emptyString;

	@DecimalMin("-5.5")
	@DecimalMax("5.5")
	private String decimalString;

	@DecimalMax("5.5")
	private Double decimalDouble;

	@DecimalMin("-5.5")
	private Float decimalFloat;

	@Min(-10)
	@Max(10)
	private BigInteger bigIntNumber;

	@Min(-10)
	@Max(10)
	private Long longNumber;

	@Min(-10)
	@Max(10)
	private Integer intNumber;

	@Min(-10)
	private Short shortNumber;

	@Max(10)
	private Byte byteNumber;

	@Min(-10)
	@Max(10)
	private String intString;

	@Digits(integer = 3, fraction = 2)
	private String fractionString;

	@Digits(integer = 3, fraction = 2)
	private BigDecimal fractionDecimal;

	@Past
	private Date pastDate;

	@Future
	private Calendar futureCalendar;

	@Size(min = 7, max = 10)
	private String sizedString;

	@Size(max = 2)
	private Collection<String> maxCollection;

	@Size(min = 2)
	private Collection<String> minCollection;

	@Pattern(regexp = "^[a-zA-Z0-9_]*$")
	private String identifier;

	@Email
	private String email;

	@Min(-5)
	@Max(5)
	private long longPrimitive;

	@Min(-5)
	@Max(5)
	private int intPrimitive;

	@Min(-5)
	private short shortPrimitive;

	@Max(5)
	private byte bytePrimitive;

	@DecimalMax("2.5")
	private double doublePrimitive;

	@DecimalMin("-2.5")
	private float floatPrimitive;

	public Boolean getBoolTrue() {
		return boolTrue;
	}

	public void setBoolTrue(Boolean boolTrue) {
		this.boolTrue = boolTrue;
	}

	public Boolean getBoolFalse() {
		return boolFalse;
	}

	public void setBoolFalse(Boolean boolFalse) {
		this.boolFalse = boolFalse;
	}

	public String getFilledString() {
		return filledString;
	}

	public void setFilledString(String filledString) {
		this.filledString = filledString;
	}

	public String getEmptyString() {
		return emptyString;
	}

	public void setEmptyString(String emptyString) {
		this.emptyString = emptyString;
	}

	public String getDecimalString() {
		return decimalString;
	}

	public void setDecimalString(String decimalString) {
		this.decimalString = decimalString;
	}

	public Double getDecimalDouble() {
		return decimalDouble;
	}

	public void setDecimalDouble(Double decimalDouble) {
		this.decimalDouble = decimalDouble;
	}

	public Float getDecimalFloat() {
		return decimalFloat;
	}

	public void setDecimalFloat(Float decimalFloat) {
		this.decimalFloat = decimalFloat;
	}

	public BigInteger getBigIntNumber() {
		return bigIntNumber;
	}

	public void setBigIntNumber(BigInteger bigIntNumber) {
		this.bigIntNumber = bigIntNumber;
	}

	public Long getLongNumber() {
		return longNumber;
	}

	public void setLongNumber(Long longNumber) {
		this.longNumber = longNumber;
	}

	public Integer getIntNumber() {
		return intNumber;
	}

	public void setIntNumber(Integer intNumber) {
		this.intNumber = intNumber;
	}

	public Short getShortNumber() {
		return shortNumber;
	}

	public void setShortNumber(Short shortNumber) {
		this.shortNumber = shortNumber;
	}

	public Byte getByteNumber() {
		return byteNumber;
	}

	public void setByteNumber(Byte byteNumber) {
		this.byteNumber = byteNumber;
	}

	public String getIntString() {
		return intString;
	}

	public void setIntString(String intString) {
		this.intString = intString;
	}

	public String getFractionString() {
		return fractionString;
	}

	public void setFractionString(String fractionString) {
		this.fractionString = fractionString;
	}

	public BigDecimal getFractionDecimal() {
		return fractionDecimal;
	}

	public void setFractionDecimal(BigDecimal fractionDecimal) {
		this.fractionDecimal = fractionDecimal;
	}

	public Date getPastDate() {
		return pastDate;
	}

	public void setPastDate(Date pastDate) {
		this.pastDate = pastDate;
	}

	public Calendar getFutureCalendar() {
		return futureCalendar;
	}

	public void setFutureCalendar(Calendar futureCalendar) {
		this.futureCalendar = futureCalendar;
	}

	public String getSizedString() {
		return sizedString;
	}

	public void setSizedString(String sizedString) {
		this.sizedString = sizedString;
	}

	public Collection<String> getMaxCollection() {
		return maxCollection;
	}

	public void setMaxCollection(Collection<String> maxCollection) {
		this.maxCollection = maxCollection;
	}

	public Collection<String> getMinCollection() {
		return minCollection;
	}

	public void setMinCollection(Collection<String> minCollection) {
		this.minCollection = minCollection;
	}

	public String getIdentifier() {
		return identifier;
	}

	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public long getLongPrimitive() {
		return longPrimitive;
	}

	public void setLongPrimitive(long longPrimitive) {
		this.longPrimitive = longPrimitive;
	}

	public int getIntPrimitive() {
		return intPrimitive;
	}

	public void setIntPrimitive(int intPrimitive) {
		this.intPrimitive = intPrimitive;
	}

	public short getShortPrimitive() {
		return shortPrimitive;
	}

	public void setShortPrimitive(short shortPrimitive) {
		this.shortPrimitive = shortPrimitive;
	}

	public byte getBytePrimitive() {
		return bytePrimitive;
	}

	public void setBytePrimitive(byte bytePrimitive) {
		this.bytePrimitive = bytePrimitive;
	}

	public double getDoublePrimitive() {
		return doublePrimitive;
	}

	public void setDoublePrimitive(double doublePrimitive) {
		this.doublePrimitive = doublePrimitive;
	}

	public float getFloatPrimitive() {
		return floatPrimitive;
	}

	public void setFloatPrimitive(float floatPrimitive) {
		this.floatPrimitive = floatPrimitive;
	}
}
