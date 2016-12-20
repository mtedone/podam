package uk.co.jemos.podam.test.dto.pdm45;

import java.util.Map;

/**
 * Pojo with map of generic attributes.
 * 
 * @author daivanov
 *
 */
public class GenericMapPojo<F, S> {

	private Map<String, GenericPojo<F, S>> genericPojos;

	/**
	 * @return the genericPojo
	 */
	public Map<String, GenericPojo<F, S>> getGenericPojos() {
		return genericPojos;
	}

	/**
	 * @param genericPojo the genericPojo to set
	 */
	public void setGenericPojos(Map<String, GenericPojo<F, S>> genericPojos) {
		this.genericPojos = genericPojos;
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "GenericAttributePojo [genericPojo=" + genericPojos + "]";
	}
}
