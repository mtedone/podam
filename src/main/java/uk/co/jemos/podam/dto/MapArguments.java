package uk.co.jemos.podam.dto;

import java.io.Serializable;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * @author Marco Tedone
 * 
 */
public class MapArguments implements Serializable {

	private static final long serialVersionUID = 1L;

	/** The POJO where the Map attribute has been declared. */
	private Class<?> pojoClass;

	/** Set of manufactured pojos' types. */
	private Map<Class<?>, Integer> pojos;

	/** The attribute name. */
	private String attributeName;

	/** The annotations for the attribute. */
	private List<Annotation> annotations;

	/** The Map to be returned. */
	private Map<? super Object, ? super Object> mapToBeFilled;

	/** The type of the Map key. */
	private Class<?> keyClass;

	/** The type of the Map element. */
	private Class<?> elementClass;

	/**
	 * The generic type arguments for the current key generic class
	 * instance.
	 */
	private Type[] keyGenericTypeArgs;

	/**
	 * The generic type arguments for the current element generic
	 * class instance.
	 */
	private Type[] elementGenericTypeArgs;

	/**
	 * @return the pojoClass
	 */
	public Class<?> getPojoClass() {
		return pojoClass;
	}

	/**
	 * @param pojoClass
	 *            the pojoClass to set
	 */
	public void setPojoClass(Class<?> pojoClass) {
		this.pojoClass = pojoClass;
	}

	/**
	 * @return the pojos
	 */
	public Map<Class<?>, Integer> getPojos() {
		return pojos;
	}

	/**
	 * @param pojos
	 *            the pojos to set
	 */
	public void setPojos(Map<Class<?>, Integer> pojos) {
		this.pojos = pojos;
	}

	/**
	 * @return the attributeName
	 */
	public String getAttributeName() {
		return attributeName;
	}

	/**
	 * @param attributeName
	 *            the attributeName to set
	 */
	public void setAttributeName(String attributeName) {
		this.attributeName = attributeName;
	}

	/**
	 * @return the annotations
	 */
	public List<Annotation> getAnnotations() {
		return annotations;
	}

	/**
	 * @param annotations
	 *            the annotations to set
	 */
	public void setAnnotations(List<Annotation> annotations) {
		this.annotations = annotations;
	}

	/**
	 * @return the mapToBeFilled
	 */
	public Map<? super Object, ? super Object> getMapToBeFilled() {
		return mapToBeFilled;
	}

	/**
	 * @param mapToBeFilled
	 *            the mapToBeFilled to set
	 */
	public void setMapToBeFilled(
			Map<? super Object, ? super Object> mapToBeFilled) {
		this.mapToBeFilled = mapToBeFilled;
	}

	/**
	 * @return the keyClass
	 */
	public Class<?> getKeyClass() {
		return keyClass;
	}

	/**
	 * @param keyClass
	 *            the keyClass to set
	 */
	public void setKeyClass(Class<?> keyClass) {
		this.keyClass = keyClass;
	}

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
		this.keyGenericTypeArgs = keyGenericTypeArgs;
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
		this.elementGenericTypeArgs = elementGenericTypeArgs;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("MapArguments [pojoClass=");
		builder.append(pojoClass);
		builder.append(", pojos=");
		builder.append(pojos);
		builder.append(", attributeName=");
		builder.append(attributeName);
		builder.append(", annotations=");
		builder.append(annotations);
		builder.append(", mapToBeFilled=");
		builder.append(mapToBeFilled);
		builder.append(", keyClass=");
		builder.append(keyClass);
		builder.append(", elementClass=");
		builder.append(elementClass);
		builder.append(", keyGenericTypeArgs=");
		builder.append(Arrays.toString(keyGenericTypeArgs));
		builder.append(", elementGenericTypeArgs=");
		builder.append(Arrays.toString(elementGenericTypeArgs));
		builder.append("]");
		return builder.toString();
	}

}
