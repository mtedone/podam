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
public class ClassGenericPojo<T> {
	private Class<T> clazz;

	public ClassGenericPojo() {
	}

	public Class<T> getClazz() {
		return clazz;
	}

	public void setClazz(Class<T> clazz) {
		this.clazz = clazz;
	}
}
