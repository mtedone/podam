/**
 * 
 */
package uk.co.jemos.podam.test.dto;

import java.io.Serializable;
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
public class ImmutableWithNonGenericCollectionsPojo implements Serializable {

	// ------------------->> Constants

	private static final long serialVersionUID = 1L;

	public static final int NBR_ELEMENTS = 5;

	// ------------------->> Instance / Static variables

	@SuppressWarnings("rawtypes")
	// This is actually intentional
	private final Collection nonGenerifiedCollection;

	@SuppressWarnings("rawtypes")
	// This is actually intentional
	private final Map nonGenerifiedMap;

	@SuppressWarnings("rawtypes")
	private final Set nonGenerifiedSet;

	// ------------------->> Constructors

	/**
	 * @param nonGenerifiedCollection
	 * @param nonGenerifiedMap
	 */
	// This is actually intentional
	@SuppressWarnings("rawtypes")
	@PodamConstructor
	public ImmutableWithNonGenericCollectionsPojo(
			@PodamCollection(nbrElements = NBR_ELEMENTS) Collection nonGenerifiedCollection,
			@PodamCollection(nbrElements = NBR_ELEMENTS) Map nonGenerifiedMap,
			@PodamCollection(nbrElements = NBR_ELEMENTS) Set nonGenerifiedSet) {
		super();
		this.nonGenerifiedCollection = nonGenerifiedCollection;
		this.nonGenerifiedMap = nonGenerifiedMap;
		this.nonGenerifiedSet = nonGenerifiedSet;
	}

	// ------------------->> Public methods

	// ------------------->> Getters / Setters

	/**
	 * @return the nonGenerifiedCollection
	 */
	// This is actually intentional
	@SuppressWarnings("rawtypes")
	public Collection getNonGenerifiedCollection() {
		return nonGenerifiedCollection;
	}

	/**
	 * @return the nonGenerifiedMap
	 */
	// This is actually intentional
	@SuppressWarnings("rawtypes")
	public Map getNonGenerifiedMap() {
		return nonGenerifiedMap;
	}

	// ------------------->> Private methods

	// ------------------->> equals() / hashcode() / toString()

	/**
	 * @return the nonGenerifiedSet
	 */
	@SuppressWarnings("rawtypes")
	public Set getNonGenerifiedSet() {
		return nonGenerifiedSet;
	}

	/**
	 * Constructs a <code>String</code> with all attributes
	 * in name = value format.
	 *
	 * @return a <code>String</code> representation 
	 * of this object.
	 */
	public String toString()
	{
	    final String TAB = "    ";
	
	    StringBuilder retValue = new StringBuilder();
	    
	    retValue.append("ImmutableWithNonGenericCollectionsPojo ( ")        
	        .append("nonGenerifiedCollection = ").append(this.nonGenerifiedCollection).append(TAB)
	        .append("nonGenerifiedMap = ").append(this.nonGenerifiedMap).append(TAB)
	        .append("nonGenerifiedSet = ").append(this.nonGenerifiedSet).append(TAB)
	        .append(" )");
	    
	    return retValue.toString();
	}

	// ------------------->> Inner classes

}
