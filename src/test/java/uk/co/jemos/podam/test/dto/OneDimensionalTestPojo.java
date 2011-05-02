/**
 * 
 */
package uk.co.jemos.podam.test.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

/**
 * Simple graphed POJO with different attribute types
 * 
 * @author mtedone
 * 
 */
public class OneDimensionalTestPojo implements Serializable {

	private static final long serialVersionUID = 1L;

	/** A boolean field */
	private boolean booleanField;

	/** A Boolean object field */
	private Boolean booleanObjectField;

	/** A byte type */
	private byte byteField;

	/** A Byte object field */
	private Byte byteObjectField;

	/** A short field */
	private short shortField;

	/** A Short object field */
	private Short shortObjectField;

	/** A char field */
	private char charField;

	/** A Character field */
	private Character charObjectField;

	/** An int field */
	private int intField;

	/** An Integer field */
	private Integer intObjectField;

	/** a long field */
	private long longField;

	/** A Long object field */
	private Long longObjectField;

	/** A float field */
	private float floatField;

	/** A Float object field */
	private Float floatObjectField;

	/** A double field */
	private double doubleField;

	/** A Double object field */
	private Double doubleObjectField;

	/** A String field */
	private String stringField;

	/** An object field */
	private Object objectField;

	/** A Calendar field */
	private Calendar calendarField;

	/** A Date field */
	private Date dateField;

	/** An array type */
	private Random[] randomArray;

	/** An array of int */
	private int[] intArray;

	/** An array of booleans */
	private boolean[] booleanArray;

	/** A BigDecimal attribute */
	private BigDecimal bigDecimalField;

	/** No-args constructor */
	public OneDimensionalTestPojo() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @return the intField
	 */
	public int getIntField() {
		return intField;
	}

	/**
	 * @param intField
	 *            the intField to set
	 */
	public void setIntField(int intField) {
		this.intField = intField;
	}

	/**
	 * @return the longField
	 */
	public long getLongField() {
		return longField;
	}

	/**
	 * @param longField
	 *            the longField to set
	 */
	public void setLongField(long longField) {
		this.longField = longField;
	}

	/**
	 * @return the floatField
	 */
	public float getFloatField() {
		return floatField;
	}

	/**
	 * @param floatField
	 *            the floatField to set
	 */
	public void setFloatField(float floatField) {
		this.floatField = floatField;
	}

	/**
	 * @return the doubleField
	 */
	public double getDoubleField() {
		return doubleField;
	}

	/**
	 * @param doubleField
	 *            the doubleField to set
	 */
	public void setDoubleField(double doubleField) {
		this.doubleField = doubleField;
	}

	/**
	 * @return the stringField
	 */
	public String getStringField() {
		return stringField;
	}

	/**
	 * @param stringField
	 *            the stringField to set
	 */
	public void setStringField(String stringField) {
		this.stringField = stringField;
	}

	/**
	 * @return the objectField
	 */
	public Object getObjectField() {
		return objectField;
	}

	/**
	 * @param objectField
	 *            the objectField to set
	 */
	public void setObjectField(Object objectField) {
		this.objectField = objectField;
	}

	/**
	 * @return the calendarField
	 */
	public Calendar getCalendarField() {
		return calendarField;
	}

	/**
	 * @param calendarField
	 *            the calendarField to set
	 */
	public void setCalendarField(Calendar calendarField) {
		this.calendarField = calendarField;
	}

	/**
	 * @return the dateField
	 */
	public Date getDateField() {
		return dateField;
	}

	/**
	 * @param dateField
	 *            the dateField to set
	 */
	public void setDateField(Date dateField) {
		this.dateField = dateField;
	}

	/**
	 * @return the booleanField
	 */
	public boolean isBooleanField() {
		return booleanField;
	}

	/**
	 * @param booleanField
	 *            the booleanField to set
	 */
	public void setBooleanField(boolean booleanField) {
		this.booleanField = booleanField;
	}

	/**
	 * @return the randomArray
	 */
	public Random[] getRandomArray() {
		return randomArray;
	}

	/**
	 * @param randomArray
	 *            the randomArray to set
	 */
	public void setRandomArray(Random[] randomArray) {
		this.randomArray = randomArray;
	}

	/**
	 * @return the booleanObjectField
	 */
	public Boolean getBooleanObjectField() {
		return booleanObjectField;
	}

	/**
	 * @param booleanObjectField
	 *            the booleanObjectField to set
	 */
	public void setBooleanObjectField(Boolean booleanObjectField) {
		this.booleanObjectField = booleanObjectField;
	}

	/**
	 * @return the byteField
	 */
	public byte getByteField() {
		return byteField;
	}

	/**
	 * @param byteField
	 *            the byteField to set
	 */
	public void setByteField(byte byteField) {
		this.byteField = byteField;
	}

	/**
	 * @return the byteObjectField
	 */
	public Byte getByteObjectField() {
		return byteObjectField;
	}

	/**
	 * @param byteObjectField
	 *            the byteObjectField to set
	 */
	public void setByteObjectField(Byte byteObjectField) {
		this.byteObjectField = byteObjectField;
	}

	/**
	 * @return the shortField
	 */
	public short getShortField() {
		return shortField;
	}

	/**
	 * @param shortField
	 *            the shortField to set
	 */
	public void setShortField(short shortField) {
		this.shortField = shortField;
	}

	/**
	 * @return the shortObjectField
	 */
	public Short getShortObjectField() {
		return shortObjectField;
	}

	/**
	 * @param shortObjectField
	 *            the shortObjectField to set
	 */
	public void setShortObjectField(Short shortObjectField) {
		this.shortObjectField = shortObjectField;
	}

	/**
	 * @return the charField
	 */
	public char getCharField() {
		return charField;
	}

	/**
	 * @param charField
	 *            the charField to set
	 */
	public void setCharField(char charField) {
		this.charField = charField;
	}

	/**
	 * @return the charObjectField
	 */
	public Character getCharObjectField() {
		return charObjectField;
	}

	/**
	 * @param charObjectField
	 *            the charObjectField to set
	 */
	public void setCharObjectField(Character charObjectField) {
		this.charObjectField = charObjectField;
	}

	/**
	 * @return the intObjectField
	 */
	public Integer getIntObjectField() {
		return intObjectField;
	}

	/**
	 * @param intObjectField
	 *            the intObjectField to set
	 */
	public void setIntObjectField(Integer intObjectField) {
		this.intObjectField = intObjectField;
	}

	/**
	 * @return the longObjectField
	 */
	public Long getLongObjectField() {
		return longObjectField;
	}

	/**
	 * @param longObjectField
	 *            the longObjectField to set
	 */
	public void setLongObjectField(Long longObjectField) {
		this.longObjectField = longObjectField;
	}

	/**
	 * @return the floatObjectField
	 */
	public Float getFloatObjectField() {
		return floatObjectField;
	}

	/**
	 * @param floatObjectField
	 *            the floatObjectField to set
	 */
	public void setFloatObjectField(Float floatObjectField) {
		this.floatObjectField = floatObjectField;
	}

	/**
	 * @return the doubleObjectField
	 */
	public Double getDoubleObjectField() {
		return doubleObjectField;
	}

	/**
	 * @param doubleObjectField
	 *            the doubleObjectField to set
	 */
	public void setDoubleObjectField(Double doubleObjectField) {
		this.doubleObjectField = doubleObjectField;
	}

	/**
	 * @return the intArray
	 */
	public int[] getIntArray() {
		return intArray;
	}

	/**
	 * @param intArray
	 *            the intArray to set
	 */
	public void setIntArray(int[] intArray) {
		this.intArray = intArray;
	}

	/**
	 * @return the booleanArray
	 */
	public boolean[] getBooleanArray() {
		return booleanArray;
	}

	/**
	 * @param booleanArray
	 *            the booleanArray to set
	 */
	public void setBooleanArray(boolean[] booleanArray) {
		this.booleanArray = booleanArray;
	}

	/**
	 * @return the bigDecimalField
	 */
	public BigDecimal getBigDecimalField() {
		return bigDecimalField;
	}

	/**
	 * @param bigDecimalField
	 *            the bigDecimalField to set
	 */
	public void setBigDecimalField(BigDecimal bigDecimalField) {
		this.bigDecimalField = bigDecimalField;
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

		retValue.append("OneDimensionalTestPojo ( ").append("booleanField = ")
				.append(booleanField).append(TAB)
				.append("booleanObjectField = ").append(booleanObjectField)
				.append(TAB).append("byteField = ").append(byteField)
				.append(TAB).append("byteObjectField = ")
				.append(byteObjectField).append(TAB).append("shortField = ")
				.append(shortField).append(TAB).append("shortObjectField = ")
				.append(shortObjectField).append(TAB).append("charField = ")
				.append(charField).append(TAB).append("charObjectField = ")
				.append(charObjectField).append(TAB).append("intField = ")
				.append(intField).append(TAB).append("intObjectField = ")
				.append(intObjectField).append(TAB).append("longField = ")
				.append(longField).append(TAB).append("longObjectField = ")
				.append(longObjectField).append(TAB).append("floatField = ")
				.append(floatField).append(TAB).append("floatObjectField = ")
				.append(floatObjectField).append(TAB).append("doubleField = ")
				.append(doubleField).append(TAB).append("doubleObjectField = ")
				.append(doubleObjectField).append(TAB).append("stringField = ")
				.append(stringField).append(TAB).append("objectField = ")
				.append(objectField).append(TAB).append("calendarField = ")
				.append(calendarField.getTime()).append(TAB)
				.append("dateField = ").append(dateField).append(TAB)
				.append("randomArray = ").append(randomArray).append(TAB)
				.append("intArray = ").append(intArray).append(TAB)
				.append("booleanArray = ").append(booleanArray).append(TAB)
				.append("bigDecimalField = ").append(bigDecimalField)
				.append(TAB).append(" )");

		return retValue.toString();
	}

}
