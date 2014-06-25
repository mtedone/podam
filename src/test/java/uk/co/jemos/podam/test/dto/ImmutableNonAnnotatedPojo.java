/**
 * 
 */
package uk.co.jemos.podam.test.dto;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Calendar;

import net.jcip.annotations.Immutable;
import uk.co.jemos.podam.common.PodamConstructor;

/**
 * This is an immutable POJO which is not annotated with
 * {@link PodamConstructor}.
 * <p>
 * PODAM should guess how to create an instance.
 * </p>
 * 
 * @author mtedone
 * 
 */
@Immutable
public class ImmutableNonAnnotatedPojo implements Serializable {

	// ------------------->> Constants

	private static final long serialVersionUID = 1L;
	// ------------------->> Instance / Static variables

	/** An int field */
	private final int intField;

	/** A Calendar field */
	private final Calendar dateCreated;

	/** An array of longs */
	private final long[] longArray;

	// ------------------->> Constructors

	/**
	 * Full constructor
	 * 
	 * @param intField
	 * @param dateCreated
	 * @param longArray
	 */
	public ImmutableNonAnnotatedPojo(int intField, Calendar dateCreated,
			long[] longArray) {
		super();
		this.intField = intField;
		this.dateCreated = dateCreated;
		this.longArray = longArray;
	}

	// ------------------->> Public methods

	// ------------------->> Getters / Setters

	/**
	 * @return the intField
	 */
	public int getIntField() {
		return intField;
	}

	/**
	 * @return the dateCreated
	 */
	public Calendar getDateCreated() {
		return dateCreated;
	}

	/**
	 * @return the longArray
	 */
	public long[] getLongArray() {
		return longArray;
	}

	// ------------------->> Private methods

	// ------------------->> equals() / hashcode() / toString()

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ImmutableNoHierarchicalPojo [intField=" + intField
				+ ", dateCreated=" + dateCreated.getTime() + ", longArray="
				+ Arrays.toString(longArray) + "]";
	}

	// ------------------->> Inner classes

}
