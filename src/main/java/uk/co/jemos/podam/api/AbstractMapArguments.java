package uk.co.jemos.podam.api;

import java.lang.annotation.Annotation;
import java.util.List;
import java.util.Map;

/**
 * Parent for map-related arguments POJO
 * 
 * @author Marco Tedone
 * 
 */
public abstract class AbstractMapArguments {

	/** The POJO where the Map attribute has been declared. */
	private Class<?> pojoClass;
	/** Set of manufactured pojos' types. */
	private Map<Class<?>, Integer> pojos;
	/** The attribute name. */
	private String attributeName;
	/** The annotations for the attribute. */
	private List<Annotation> annotations;

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
	 * {@inheritDoc}
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("AbstractMapArguments [pojoClass=");
		builder.append(pojoClass);
		builder.append(", pojos=");
		builder.append(pojos);
		builder.append(", attributeName=");
		builder.append(attributeName);
		builder.append(", annotations=");
		builder.append(annotations);
		builder.append("]");
		return builder.toString();
	}

}
