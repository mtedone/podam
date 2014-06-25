/**
 * 
 */
package uk.co.jemos.podam.test.dto.docs.example;

import java.io.Serializable;

import uk.co.jemos.podam.common.PodamConstructor;
import uk.co.jemos.podam.common.PodamDoubleValue;
import uk.co.jemos.podam.common.PodamIntValue;

/**
 * @author mtedone
 * 
 */
public class Article implements Serializable {

	private static final long serialVersionUID = 1L;

	private final int id;

	private final String description;

	private final Double itemCost;

	/**
	 * Full constructor.
	 * 
	 * @param id
	 *            The article id
	 * @param description
	 *            The article description
	 * @param itemCost
	 *            The item cost
	 */
	@PodamConstructor(comment = "Immutable-like POJOs must be annotated with @PodamConstructor")
	public Article(@PodamIntValue(maxValue = 100000) int id,
			String description,
			@PodamDoubleValue(minValue = 50.0) Double itemCost) {
		super();
		this.id = id;
		this.description = description;
		this.itemCost = itemCost;
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @return the itemCost
	 */
	public Double getItemCost() {
		return itemCost;
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

		retValue.append("Article ( ").append("id = ").append(id).append(TAB)
				.append("description = ").append(description).append(TAB)
				.append("itemCost = ").append(itemCost).append(TAB)
				.append(" )");

		return retValue.toString();
	}

}
