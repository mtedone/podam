/**
 * 
 */
package uk.co.jemos.podam.test.dto;

import java.io.Serializable;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

/**
 * A POJO to test a Singleton-like scenario where the public static Singleton
 * method contains parameters.
 * 
 * @author mtedone
 * 
 */
public class SingletonWithParametersInStaticFactoryPojo implements Serializable {

	// ------------------->> Constants

	private static final long serialVersionUID = 1L;

	// ------------------->> Instance / Static variables

	private final Calendar createDate;

	private final List<OneDimensionalTestPojo> pojoList;

	private final Map<String, OneDimensionalTestPojo> pojoMap;

	private final String firstName;

	private static SingletonWithParametersInStaticFactoryPojo singleton;

	// ------------------->> Constructors

	/**
	 * A private constructor to enforce the Singleton pattern
	 * 
	 * @param createDate
	 *            The creation date
	 * @param pojoList
	 *            A list
	 * @param pojoMap
	 *            A map
	 * @param firstName
	 *            The first name
	 */
	private SingletonWithParametersInStaticFactoryPojo(Calendar createDate,
			List<OneDimensionalTestPojo> pojoList,
			Map<String, OneDimensionalTestPojo> pojoMap, String firstName) {
		super();
		this.createDate = createDate;
		this.pojoList = pojoList;
		this.pojoMap = pojoMap;
		this.firstName = firstName;
	}

	// ------------------->> Public methods

	/**
	 * Singleton method
	 * 
	 * @param createDate
	 *            The creation date
	 * @param pojoList
	 *            A list
	 * @param pojoMap
	 *            A map
	 * @param firstName
	 *            The first name
	 * @return a singleton instance of this class
	 */
	public static SingletonWithParametersInStaticFactoryPojo getInstance(
			Calendar createDate, List<OneDimensionalTestPojo> pojoList,
			Map<String, OneDimensionalTestPojo> pojoMap, String firstName) {
		if (null == singleton) {
			singleton = new SingletonWithParametersInStaticFactoryPojo(
					createDate, pojoList, pojoMap, firstName);
		}

		return singleton;
	}

	// ------------------->> Getters / Setters

	/**
	 * @return the createDate
	 */
	public Calendar getCreateDate() {
		return createDate;
	}

	/**
	 * @return the pojoList
	 */
	public List<OneDimensionalTestPojo> getPojoList() {
		return pojoList;
	}

	/**
	 * @return the pojoMap
	 */
	public Map<String, OneDimensionalTestPojo> getPojoMap() {
		return pojoMap;
	}

	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
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

		retValue.append("SingletonWithParametersInStaticFactoryPojo ( ")
				.append("createDate = ").append(createDate.getTime())
				.append(TAB).append("pojoList = ").append(pojoList).append(TAB)
				.append("pojoMap = ").append(pojoMap).append(TAB)
				.append("firstName = ").append(firstName).append(TAB)
				.append(" )");

		return retValue.toString();
	}

	// ------------------->> Inner classes

}
