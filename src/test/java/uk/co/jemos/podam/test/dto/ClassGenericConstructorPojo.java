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
public class ClassGenericConstructorPojo<T> {
	private Class<T> clazz;

	public ClassGenericConstructorPojo(Class<T> clazz) {
		this.clazz = clazz;
	}

	public Class<T> getClazz() {
		return clazz;
	}
}
