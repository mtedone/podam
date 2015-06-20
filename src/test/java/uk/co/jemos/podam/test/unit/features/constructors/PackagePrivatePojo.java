package uk.co.jemos.podam.test.unit.features.constructors;

/**
 * Package private pojo
 *
 * @author daivanov
 *
 */
class PackagePrivatePojo {

	public PackagePrivatePojo() {
	}

	private String value;
	
	/**
	 * @return the value
	 */
	public String getValue() {
		return value;
	}

	/**
	 * @param value
	 *            the value to set
	 */
	public void setValue(String value) {
		this.value = value;
	}
}
