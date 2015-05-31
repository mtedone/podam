/**
 * 
 */
package uk.co.jemos.podam.test.dto;

import uk.co.jemos.podam.common.PodamConstructor;
import uk.co.jemos.podam.exceptions.PodamMockeryException;

import java.io.Serializable;

/**
 * @author mtedone
 * 
 */
public class ConstructorWithSelfReferencesPojoAndDefaultConstructor implements Serializable {

	private static final long serialVersionUID = 1L;

	// ------------------->> Constants

	// ------------------->> Instance / Static variables

	/** Int field */
	private int intField;

	/** Parent instance */
	private ConstructorWithSelfReferencesPojoAndDefaultConstructor parent;

	/** Another parent instance */
	private ConstructorWithSelfReferencesPojoAndDefaultConstructor anotherParent;

	// ------------------->> Constructors

	/**
	 * No-args constructor
	 * <p>
	 * This is a pre-requisite for this type of POJOs or a
	 * {@link PodamMockeryException} will be thrown
	 * </p>
	 */
	public ConstructorWithSelfReferencesPojoAndDefaultConstructor() {
	}

	/**
	 * @param intField
	 * @param parent
	 * @param anotherParent
	 */
	@PodamConstructor
	public ConstructorWithSelfReferencesPojoAndDefaultConstructor(int intField,
																  ConstructorWithSelfReferencesPojoAndDefaultConstructor parent,
																  ConstructorWithSelfReferencesPojoAndDefaultConstructor anotherParent) {
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
	public ConstructorWithSelfReferencesPojoAndDefaultConstructor getParent() {
		return parent;
	}

	/**
	 * @return the anotherParent
	 */
	public ConstructorWithSelfReferencesPojoAndDefaultConstructor getAnotherParent() {
		return anotherParent;
	}

	// ------------------->> Private methods

	// ------------------->> equals() / hashcode() / toString()

	// ------------------->> Inner classes

}
