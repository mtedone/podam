package uk.co.jemos.podam.test.dto;

/**
 * Generic Pojo with generic array type in constructor
 *
 * @author daivanov
 */
public class GenericArrayInConstructorPojo<E> {

	private E[] array;

	public GenericArrayInConstructorPojo(E[] array) {
		this.array = array;
	}

	public E[] getArray() {
		return array;
	}
}
