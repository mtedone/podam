/**
 * 
 */
package uk.co.jemos.podam.test.dto.annotations;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import uk.co.jemos.podam.common.PodamCollection;
import uk.co.jemos.podam.test.utils.PodamTestConstants;

/**
 * @author mtedone
 * 
 */
public class CollectionAnnotationPojo implements Serializable {

	// ------------------->> Constants

	private static final long serialVersionUID = 1L;
	// ------------------->> Instance / Static variables

	@PodamCollection(nbrElements = PodamTestConstants.ANNOTATION_COLLECTION_NBR_ELEMENTS)
	/** A collection with a specified number of elements */
	private List<String> strList = new ArrayList<String>();

	@PodamCollection(nbrElements = PodamTestConstants.ANNOTATION_COLLECTION_NBR_ELEMENTS)
	/** An array with a specified number of elements */
	private String[] strArray = new String[PodamTestConstants.ANNOTATION_COLLECTION_NBR_ELEMENTS];

	@PodamCollection(nbrElements = PodamTestConstants.ANNOTATION_COLLECTION_NBR_ELEMENTS)
	/** A Map with a specified number of elements */
	private Map<String, String> stringMap = new HashMap<String, String>();

	// ------------------->> Constructors

	// ------------------->> Public methods

	// ------------------->> Getters / Setters

	/**
	 * @return the strList
	 */
	public List<String> getStrList() {
		return strList;
	}

	/**
	 * @param strList
	 *            the strList to set
	 */
	public void setStrList(List<String> strList) {
		this.strList = strList;
	}

	/**
	 * @return the strArray
	 */
	public String[] getStrArray() {
		return strArray;
	}

	/**
	 * @param strArray
	 *            the strArray to set
	 */
	public void setStrArray(String[] strArray) {
		this.strArray = strArray;
	}

	/**
	 * @return the stringMap
	 */
	public Map<String, String> getStringMap() {
		return stringMap;
	}

	/**
	 * @param stringMap
	 *            the stringMap to set
	 */
	public void setStringMap(Map<String, String> stringMap) {
		this.stringMap = stringMap;
	}

	// ------------------->> Private methods

	// ------------------->> equals() / hashcode() / toString()

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

		retValue.append("CollectionAnnotationPojo ( ").append("strList = ")
				.append(strList).append(TAB).append("strArray = ")
				.append(Arrays.toString(strArray)).append(TAB)
				.append("stringMap = ").append(stringMap).append(TAB)
				.append(" )");

		return retValue.toString();
	}

	// ------------------->> Inner classes

}
