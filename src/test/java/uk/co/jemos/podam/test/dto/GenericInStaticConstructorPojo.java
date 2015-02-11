package uk.co.jemos.podam.test.dto;

import java.util.Vector;

/**
 * Generic Pojo with generic type in constructor
 *
 * @author daivanov
 */
public abstract class GenericInStaticConstructorPojo {

	protected Vector<String> vector;

	public static GenericInStaticConstructorPojo getInstance(Vector<String> vector) {
		return new GenericInStaticConstructorPojoImpl(vector);
	}

	public Vector<String> getVector() {
		return vector;
	}
}
