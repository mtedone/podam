package uk.co.jemos.podam.test.dto;

import java.util.Vector;

/**
 * Generic Pojo with several generic types in constructor
 *
 * @author daivanov
 */
public class GenericsInConstructorPojo {

	private Vector<?> objVector;
	private Vector<String> strVector;
	private Vector<Integer> intVector;

	public GenericsInConstructorPojo(Vector<?> objVector, Vector<String> strVector, Vector<Integer> intVector) {
		this.objVector = objVector;
		this.strVector = strVector;
		this.intVector = intVector;
	}

	public Vector<?> getObjVector() {
		return objVector;
	}

	public Vector<String> getStrVector() {
		return strVector;
	}

	public Vector<Integer> getIntVector() {
		return intVector;
	}
}
