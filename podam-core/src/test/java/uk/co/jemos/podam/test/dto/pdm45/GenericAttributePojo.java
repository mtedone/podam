package uk.co.jemos.podam.test.dto.pdm45;

/**
 * Pojo with generic attribute.
 * 
 * @author marciocarmona
 *
 */
public class GenericAttributePojo {

	private GenericPojo<String, Long> genericPojo;

	/**
	 * @return the genericPojo
	 */
	public GenericPojo<String, Long> getGenericPojo() {
		return genericPojo;
	}

	/**
	 * @param genericPojo the genericPojo to set
	 */
	public void setGenericPojo(GenericPojo<String, Long> genericPojo) {
		this.genericPojo = genericPojo;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "GenericAttributePojo [genericPojo=" + genericPojo + "]";
	}
}
