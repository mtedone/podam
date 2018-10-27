package uk.co.jemos.podam.test.dto.pdm45;

import java.util.List;
import java.util.Map;

/**
 * Pojo with map of generic attributes.
 * 
 * @author daivanov
 *
 */
public class GenericMapPojo<F, S> {

	private Map<String, GenericPojo<F, S>> genericPojos;

	private Map<String, List> genericRawLists;

	/**
	 * Map getter
	 * @return the genericPojo
	 */
	public Map<String, GenericPojo<F, S>> getGenericPojos() {
		return genericPojos;
	}

	/**
	 * Map setter
	 * @param genericPojos the genericPojo to set
	 */
	public void setGenericPojos(Map<String, GenericPojo<F, S>> genericPojos) {
		this.genericPojos = genericPojos;
	}

	/**
	 * Map getter
	 * @return the genericRawLists
	 */
	public Map<String, List> getGenericRawLists() {
		return genericRawLists;
	}

	/**
	 * Map setter
	 * @param genericRawLists the genericRawLists to set
	 */
	public void setGenericRawLists(Map<String, List> genericRawLists) {
		this.genericRawLists = genericRawLists;
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "GenericAttributePojo [genericPojo=" + genericPojos + "]";
	}
}
