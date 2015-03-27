/**
 * 
 */
package uk.co.jemos.podam.test.dto;

/**
 * An class with interfaces as parameters
 * 
 * @author daivanov
 * 
 */
public class PojoWithInterfaces {

	private InterfacePojo value;

	public PojoWithInterfaces(ObjectExt<?> value) {
	}
	
	public InterfacePojo getValue() {
		return value;
	}

	public void setValue(InterfacePojo value) {
		this.value = value;
	}
}
