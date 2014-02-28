package uk.co.jemos.podam.test.dto.pdm6;

/**
 * Pojo to test recursive dependencies
 * 
 * @author ludochane
 * 
 */
public class Parent {

	private Child child;
	private String name;

	public Child getChild() {
		return child;
	}

	public void setChild(Child child) {
		this.child = child;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}