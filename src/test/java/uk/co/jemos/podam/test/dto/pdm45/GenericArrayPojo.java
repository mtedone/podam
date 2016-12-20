package uk.co.jemos.podam.test.dto.pdm45;

/**
 * Pojo with array of generic attributes.
 * 
 * @author daivanov
 *
 */
public class GenericArrayPojo<F, S> {

	private GenericPojo<F, S>[] genericPojos;

	/**
	 * @return the genericPojo
	 */
	public GenericPojo<F, S>[] getGenericPojos() {
		return genericPojos;
	}

	/**
	 * @param genericPojo the genericPojo to set
	 */
	public void setGenericPojos(GenericPojo<F, S>[] genericPojos) {
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
