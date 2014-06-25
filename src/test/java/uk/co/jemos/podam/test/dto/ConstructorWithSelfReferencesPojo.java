/**
 * 
 */
package uk.co.jemos.podam.test.dto;

import java.io.Serializable;

import uk.co.jemos.podam.common.PodamConstructor;
import uk.co.jemos.podam.exceptions.PodamMockeryException;

/**
 * @author mtedone
 * 
 */
public class ConstructorWithSelfReferencesPojo implements Serializable {

	private static final long serialVersionUID = 1L;

	// ------------------->> Constants

	// ------------------->> Instance / Static variables

	/** Int field */
	private int intField;

	/** Parent instance */
	private ConstructorWithSelfReferencesPojo parent;

	/** Another parent instance */
	private ConstructorWithSelfReferencesPojo anotherParent;

	// ------------------->> Constructors

	/**
	 * No-args constructor
	 * <p>
	 * This is a pre-requisite for this type of POJOs or a
	 * {@link PodamMockeryException} will be thrown
	 * </p>
	 */
	public ConstructorWithSelfReferencesPojo() {
	}

	/**
	 * @param intField
	 * @param parent
	 * @param anotherParent
	 */
	@PodamConstructor
	public ConstructorWithSelfReferencesPojo(int intField,
			ConstructorWithSelfReferencesPojo parent,
			ConstructorWithSelfReferencesPojo anotherParent) {
		super();
		this.intField = intField;
		this.parent = parent;
		this.anotherParent = anotherParent;
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
	 * @return the parent
	 */
	public ConstructorWithSelfReferencesPojo getParent() {
		return parent;
	}

	/**
	 * @return the anotherParent
	 */
	public ConstructorWithSelfReferencesPojo getAnotherParent() {
		return anotherParent;
	}

	// ------------------->> Private methods

	// ------------------->> equals() / hashcode() / toString()

	// ------------------->> Inner classes

}
