package uk.co.jemos.podam.test.dto;

import java.util.Vector;

/**
 * Generic Pojo with generic type in constructor
 *
 * @author daivanov
 */
public class GenericInStaticConstructorPojoImpl
	extends GenericInStaticConstructorPojo {

	protected GenericInStaticConstructorPojoImpl(Vector<String> vector) {
		this.vector = vector;
	}
}
