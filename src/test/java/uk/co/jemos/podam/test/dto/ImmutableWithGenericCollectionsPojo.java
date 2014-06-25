/**
 * 
 */
package uk.co.jemos.podam.test.dto;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Collection;
import java.util.Map;
import java.util.Set;

import net.jcip.annotations.Immutable;
import uk.co.jemos.podam.common.PodamCollection;
import uk.co.jemos.podam.common.PodamConstructor;

/**
 * @author mtedone
 * 
 */
@Immutable
public class ImmutableWithGenericCollectionsPojo implements Serializable {

	// ------------------->> Constants

	private static final long serialVersionUID = 1L;

	public static final int NBR_ELEMENTS = 5;

	// ------------------->> Instance / Static variables

	private final Collection<OneDimensionalTestPojo> generifiedCollection;

	private final Map<String, Calendar> generifiedMap;

	private final Set<ImmutableWithNonGenericCollectionsPojo> generifiedSet;

	// ------------------->> Constructors

	/**
	 * @param generifiedCollection
	 * @param generifiedMap
	 */
	@PodamConstructor
	public ImmutableWithGenericCollectionsPojo(
			@PodamCollection(nbrElements = NBR_ELEMENTS) Collection<OneDimensionalTestPojo> generifiedCollection,
			@PodamCollection(nbrElements = NBR_ELEMENTS) Map<String, Calendar> generifiedMap,
			@PodamCollection(nbrElements = NBR_ELEMENTS) Set<ImmutableWithNonGenericCollectionsPojo> generifiedSet) {
		super();
		this.generifiedCollection = generifiedCollection;
		this.generifiedMap = generifiedMap;
		this.generifiedSet = generifiedSet;
	}

	// ------------------->> Public methods

	// ------------------->> Getters / Setters

	// ------------------->> Private methods

	// ------------------->> equals() / hashcode() / toString()

	/**
	 * @return the generifiedCollection
	 */
	public Collection<OneDimensionalTestPojo> getGenerifiedCollection() {
		return generifiedCollection;
	}

	/**
	 * @return the generifiedMap
	 */
	public Map<String, Calendar> getGenerifiedMap() {
		return generifiedMap;
	}

	/**
	 * @return the generifiedSet
	 */
	public Set<ImmutableWithNonGenericCollectionsPojo> getGenerifiedSet() {
		return generifiedSet;
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

		retValue.append("ImmutableWithNonGenericCollectionsPojo ( ")
				.append("nonGenerifiedCollection = ")
				.append(generifiedCollection).append(TAB)
				.append("nonGenerifiedMap = ").append(generifiedMap)
				.append(TAB).append("nonGenerifiedSet = ")
				.append(generifiedSet).append(TAB).append(" )");

		return retValue.toString();
	}

	// ------------------->> Inner classes

}
