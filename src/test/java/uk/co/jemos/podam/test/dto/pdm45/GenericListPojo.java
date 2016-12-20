package uk.co.jemos.podam.test.dto.pdm45;

import java.util.List;

/**
 * Pojo with list of generic attributes.
 * 
 * @author daivanov
 *
 */
public class GenericListPojo<F, S> {

	private List<GenericPojo<F, S>> genericPojos;

	/**
	 * @return the genericPojo
	 */
	public List<GenericPojo<F, S>> getGenericPojos() {
		return genericPojos;
	}

	/**
	 * @param genericPojo the genericPojo to set
	 */
	public void setGenericPojos(List<GenericPojo<F, S>> genericPojos) {
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
