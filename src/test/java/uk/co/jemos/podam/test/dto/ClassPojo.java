/**
 *
 */
package uk.co.jemos.podam.test.dto;

/**
 * POJO to test Class instantiation
 *
 * @author daivanov
 *
 */
public class ClassPojo {
	private Class<String> clazz;

	public ClassPojo() {
	}

	public Class<String> getClazz() {
		return clazz;
	}

	public void setClazz(Class<String> clazz) {
		this.clazz = clazz;
	}
}
