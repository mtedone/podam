/**
 * 
 */
package uk.co.jemos.podam.test.dto;

import java.io.Serializable;

import uk.co.jemos.podam.annotations.PodamConstructor;

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
