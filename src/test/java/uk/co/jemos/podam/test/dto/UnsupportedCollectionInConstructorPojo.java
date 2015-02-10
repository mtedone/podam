/**
 *
 */
package uk.co.jemos.podam.test.dto;

import java.util.Vector;

/**
 * @author daivanov
 *
 */
public class UnsupportedCollectionInConstructorPojo<E> {

	private Vector<E> vector;

	public UnsupportedCollectionInConstructorPojo(Vector<E> vector) {
		this.vector = vector;
	}

	public Vector<E> getVector() {
		return vector;
	}
}
