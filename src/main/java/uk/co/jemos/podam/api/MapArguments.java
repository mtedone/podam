package uk.co.jemos.podam.api;

import java.io.Serializable;
import java.lang.reflect.Type;

/**
 * Pojo which contains the arguments required to fill a Map as a POJO attribute
 *
 * @author Marco Tedone
 *
 */
public class MapArguments extends AbstractMapArguments implements Serializable {

	private static final long serialVersionUID = 1L;

	/** The type of the Map element. */
	private Class<?> elementClass;

	/**
	 * The generic type arguments for the current key generic class instance.
	 */
	private Type[] keyGenericTypeArgs;

	/**
	 * The generic type arguments for the current element generic class
	 * instance.
	 */
	private Type[] elementGenericTypeArgs;

	/**
	 * @return the elementClass
	 */
	public Class<?> getElementClass() {
		return elementClass;
	}

	/**
	 * @param elementClass
	 *            the elementClass to set
	 */
	public void setElementClass(Class<?> elementClass) {
		this.elementClass = elementClass;
	}

	/**
	 * @return the keyGenericTypeArgs
	 */
	public Type[] getKeyGenericTypeArgs() {
		return keyGenericTypeArgs;
	}

	/**
	 * @param keyGenericTypeArgs
	 *            the keyGenericTypeArgs to set
	 */
	public void setKeyGenericTypeArgs(Type[] keyGenericTypeArgs) {
		this.keyGenericTypeArgs = keyGenericTypeArgs.clone();
	}

	/**
	 * @return the elementGenericTypeArgs
	 */
	public Type[] getElementGenericTypeArgs() {
		return elementGenericTypeArgs;
	}

	/**
	 * @param elementGenericTypeArgs
	 *            the elementGenericTypeArgs to set
	 */
	public void setElementGenericTypeArgs(Type[] elementGenericTypeArgs) {
		this.elementGenericTypeArgs = elementGenericTypeArgs.clone();
	}

}
