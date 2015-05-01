/**
 * 
 */
package uk.co.jemos.podam.test.dto.annotations;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import uk.co.jemos.podam.common.AttributeStrategy;
import uk.co.jemos.podam.common.PodamCollection;
import uk.co.jemos.podam.common.PodamStrategyValue;
import uk.co.jemos.podam.test.strategies.ByteArrayStrategy;
import uk.co.jemos.podam.test.strategies.MyBirthdayStrategy;
import uk.co.jemos.podam.test.strategies.PostCodeStrategy;

/**
 * POJO to test the attribute-level data strategy, through the use of
 * {@link AttributeStrategy} implementations.
 * 
 * @author mtedone
 * 
 */
public class PodamStrategyPojo implements Serializable {

	// ------------------->> Constants
	private static final long serialVersionUID = 1L;
	// ------------------->> Instance / Static variables

	@PodamStrategyValue(PostCodeStrategy.class)
	private String postCode;

	private String postCode2;

	private String postCode3;

	@PodamStrategyValue(ByteArrayStrategy.class)
	private byte[] byteData;

	@PodamStrategyValue(MyBirthdayStrategy.class)
	private Calendar myBirthday;

	@PodamCollection(nbrElements = 2, collectionElementStrategy = MyBirthdayStrategy.class)
	private List<Calendar> myBirthdays = new ArrayList<Calendar>();

	@PodamCollection(nbrElements = 2)
	private List<Object> objectList = new ArrayList<Object>();

	@PodamCollection(nbrElements = 2, mapElementStrategy = MyBirthdayStrategy.class)
	private Map<String, Calendar> myBirthdaysMap = new HashMap<String, Calendar>();

	@SuppressWarnings("rawtypes")
	// This is intentional
	private List nonGenericObjectList = new ArrayList();

	@PodamCollection(nbrElements = 2, collectionElementStrategy = MyBirthdayStrategy.class)
	private Calendar[] myBirthdaysArray;

	@PodamCollection(nbrElements = 2)
	private Object[] myObjectArray;

	// ------------------->> Constructors

	public PodamStrategyPojo(@PodamStrategyValue(PostCodeStrategy.class) String postCode3) {
		this.postCode3 = postCode3;
	}

	// ------------------->> Public methods

	// ------------------->> Getters / Setters

	/**
	 * @return the postCode
	 */
	public String getPostCode() {
		return postCode;
	}

	/**
	 * @param postCode
	 *            the postCode to set
	 */
	public void setPostCode(String postCode) {
		this.postCode = postCode;
	}

	/**
	 * @return the postCode2
	 */
	public String getPostCode2() {
		return postCode2;
	}

	/**
	 * @param postCode2
	 *            the postCode2 to set
	 */
	public void setPostCode2(@PodamStrategyValue(PostCodeStrategy.class) String postCode2) {
		this.postCode2 = postCode2;
	}

	/**
	 * @return the postCode3
	 */
	public String getPostCode3() {
		return postCode3;
	}

	/**
	 * @return the byteData
	 */
	public byte[] getByteData() {
		return byteData;
	}

	/**
	 * @param byteData
	 *            the byteData to set
	 */
	public void setByteData(byte[] byteData) {
		this.byteData = byteData;
	}

	// ------------------->> Private methods

	// ------------------->> equals() / hashcode() / toString()

	/**
	 * @return the myBirthday
	 */
	public Calendar getMyBirthday() {
		return myBirthday;
	}

	/**
	 * @param myBirthday
	 *            the myBirthday to set
	 */
	public void setMyBirthday(Calendar myBirthday) {
		this.myBirthday = myBirthday;
	}

	/**
	 * @return the myBirthdays
	 */
	public List<Calendar> getMyBirthdays() {
		return myBirthdays;
	}

	/**
	 * @param myBirthdays
	 *            the myBirthdays to set
	 */
	public void setMyBirthdays(List<Calendar> myBirthdays) {
		this.myBirthdays = myBirthdays;
	}

	/**
	 * @return the myBirthdaysArray
	 */
	public Calendar[] getMyBirthdaysArray() {
		return myBirthdaysArray;
	}

	/**
	 * @param myBirthdaysArray
	 *            the myBirthdaysArray to set
	 */
	public void setMyBirthdaysArray(Calendar[] myBirthdaysArray) {
		this.myBirthdaysArray = myBirthdaysArray;
	}

	/**
	 * @return the objectList
	 */
	public List<Object> getObjectList() {
		return objectList;
	}

	/**
	 * @param objectList
	 *            the objectList to set
	 */
	public void setObjectList(List<Object> objectList) {
		this.objectList = objectList;
	}

	/**
	 * @return the myObjectArray
	 */
	public Object[] getMyObjectArray() {
		return myObjectArray;
	}

	/**
	 * @param myObjectArray
	 *            the myObjectArray to set
	 */
	public void setMyObjectArray(Object[] myObjectArray) {
		this.myObjectArray = myObjectArray;
	}

	/**
	 * @return the nonGenericObjectList
	 */
	@SuppressWarnings("rawtypes")
	public List getNonGenericObjectList() {
		return nonGenericObjectList;
	}

	/**
	 * @param nonGenericObjectList
	 *            the nonGenericObjectList to set
	 */
	public void setNonGenericObjectList(
			@SuppressWarnings("rawtypes") List nonGenericObjectList) {
		this.nonGenericObjectList = nonGenericObjectList;
	}

	/**
	 * @return the myBirthdaysMap
	 */
	public Map<String, Calendar> getMyBirthdaysMap() {
		return myBirthdaysMap;
	}

	/**
	 * @param myBirthdaysMap
	 *            the myBirthdaysMap to set
	 */
	public void setMyBirthdaysMap(Map<String, Calendar> myBirthdaysMap) {
		this.myBirthdaysMap = myBirthdaysMap;
	}

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

		retValue.append("PodamStrategyPojo ( ").append("postCode = ")
				.append(postCode).append(TAB).append("postCode2 = ")
				.append(postCode2).append(TAB).append("postCode3 = ")
				.append(postCode3).append(TAB).append("myBirthday = ")
				.append(myBirthday).append(TAB).append("myBirthdays = ")
				.append(myBirthdays).append(TAB).append("objectList = ")
				.append(objectList).append(TAB).append("myBirthdaysMap = ")
				.append(myBirthdaysMap).append(TAB)
				.append("nonGenericObjectList = ").append(nonGenericObjectList)
				.append(TAB).append("myBirthdaysArray = ")
				.append(myBirthdaysArray).append(TAB)
				.append("myObjectArray = ").append(myObjectArray).append(TAB)
				.append(" )");

		return retValue.toString();
	}

	// ------------------->> Inner classes

}
