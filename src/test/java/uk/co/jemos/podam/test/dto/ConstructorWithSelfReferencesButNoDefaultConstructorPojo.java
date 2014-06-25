/**
 * 
 */
package uk.co.jemos.podam.test.dto;

import java.io.Serializable;

import uk.co.jemos.podam.common.PodamConstructor;
import uk.co.jemos.podam.exceptions.PodamMockeryException;

/**
 * Immutable-like POJO with constructor which receives self-types but no default
 * constructor.
 * <p>
 * This POJO cannot be reliably built due to infinite loop, so PODAM will throw
 * a {@link PodamMockeryException}
 * </p>
 * 
 * @author mtedone
 * 
 */
public class ConstructorWithSelfReferencesButNoDefaultConstructorPojo implements
		Serializable {

	private static final long serialVersionUID = 1L;

	// ------------------->> Constants

	// ------------------->> Instance / Static variables

	/** Int field */
	private int intField;

	/** Parent instance */
	private ConstructorWithSelfReferencesButNoDefaultConstructorPojo parent;

	/** Another parent instance */
	private ConstructorWithSelfReferencesButNoDefaultConstructorPojo anotherParent;

	// ------------------->> Constructors

	/**
	 * @param intField
	 * @param parent
	 * @param anotherParent
	 */
	@PodamConstructor
	public ConstructorWithSelfReferencesButNoDefaultConstructorPojo(
			int intField,
			ConstructorWithSelfReferencesButNoDefaultConstructorPojo parent,
			ConstructorWithSelfReferencesButNoDefaultConstructorPojo anotherParent) {
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
	public ConstructorWithSelfReferencesButNoDefaultConstructorPojo getParent() {
		return parent;
	}

	/**
	 * @return the anotherParent
	 */
	public ConstructorWithSelfReferencesButNoDefaultConstructorPojo getAnotherParent() {
		return anotherParent;
	}

	// ------------------->> Private methods

	// ------------------->> equals() / hashcode() / toString()

	// ------------------->> Inner classes

}
