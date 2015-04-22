package uk.co.jemos.podam.test.dto;

/**
 * Class which doesn't have any specific implementation
 * @author daivanov
 */
public abstract class AbstractClass {
	private String name;

	public AbstractClass(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}
}
