package uk.co.jemos.podam.test.unit;

public class PrivateSetterPojo {
	private int value;
	
	public PrivateSetterPojo() {
	}
	
	public int getValue() {
		return value;
	}
	
	private void setValue(int value) {
		this.value = value;
	}
}
