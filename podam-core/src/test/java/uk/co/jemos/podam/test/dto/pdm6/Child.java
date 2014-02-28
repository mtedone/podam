package uk.co.jemos.podam.test.dto.pdm6;

/**
 * Pojo to test recursive dependencies
 * 
 * @author ludochane
 * 
 */
public class Child {

	private Parent parent;
	private String name;

	public Parent getParent() {
		return parent;
	}

	public void setParent(Parent parent) {
		this.parent = parent;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}