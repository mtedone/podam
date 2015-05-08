package uk.co.jemos.podam.test.dto.pdm4;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import uk.co.jemos.podam.common.PodamConstructor;

/**
 * Pojo to test to test multiple static factory methods
 * 
 * @author divanov
 * 
 */
public abstract class PojoWithFactoryMethods {

	public static List<String> invocationOrder = new ArrayList<String>();

	private String value;
	
	public static PojoWithFactoryMethods getInstance(InputStream inputStream) {
		invocationOrder.add("InputStream");
		throw new IllegalStateException("Cannot use me also");
	}

	public static PojoWithFactoryMethods getInstance(int num, int num2) {
		invocationOrder.add("int,int");
		throw new IllegalStateException("Cannot use me neither");
	}

	public static PojoWithFactoryMethods getInstance(InputStream inputStream, int num) {
		invocationOrder.add("abstract,int");
		throw new IllegalStateException("Cannot use me also");
	}

	@PodamConstructor(comment = "choose this one")
	public static PojoWithFactoryMethods getInstance(String value) {
		invocationOrder.add("PodamConstructor");
		throw new IllegalStateException("Cannot use me");
	}

	public static PojoWithFactoryMethods getInstance() {
		invocationOrder.add("no-op");
		throw new IllegalStateException("Cannot use me too");
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

	@Override
	public String toString() {
		return String.format("{value: '%s'}", value);
	}
}
