package uk.co.jemos.podam.api;

import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Parent for map-related arguments POJO
 * 
 * @author Marco Tedone
 * 
 */
public abstract class AbstractMapArguments {

	/** The name of map attribute in enclosing POJO */
	private String attributeName;
	/** The type of the Map key. */
	private Class<?> keyOrValueType;
	/** The Map to be returned. */
	private Map<? super Object, ? super Object> mapToBeFilled;
	/** The annotations for the attribute. */
	private List<Annotation> annotations = new ArrayList<Annotation>();

	/**
	 * @return the attribute name for this map
	 */
	public String getAttributeName() {
		return attributeName;
	}

	/**
	 * @param attributeName
	 *            the attribute name for this map
	 */
	public void setAttributeName(String attributeName) {
		this.attributeName = attributeName;
	}

	/**
	 * @return the keyOrValueType
	 */
	public Class<?> getKeyOrValueType() {
		return keyOrValueType;
	}

	/**
	 * @param keyOrValueType
	 *            the keyOrValueType to set
	 */
	public void setKeyOrValueType(Class<?> keyOrValueType) {
		this.keyOrValueType = keyOrValueType;
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
	 * @return the annotations
	 */
	public List<Annotation> getAnnotations() {
		return annotations;
	}

}
