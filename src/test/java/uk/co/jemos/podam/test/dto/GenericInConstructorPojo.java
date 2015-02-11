package uk.co.jemos.podam.test.dto;

import java.util.Vector;

/**
 * Generic Pojo with generic type in constructor
 *
 * @author daivanov
 */
public class GenericInConstructorPojo {

	private Vector<String> vector;

	public GenericInConstructorPojo(Vector<String> vector) {
		this.vector = vector;
	}

	public Vector<String> getVector() {
		return vector;
	}
}
