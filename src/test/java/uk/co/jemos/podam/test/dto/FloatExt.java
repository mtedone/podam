/**
 *
 */
package uk.co.jemos.podam.test.dto;

/**
 * @author tedonema
 *
 */
public class FloatExt<T> implements ObjectExt<T> {

	private T value;

	@Override
	public T getValue() {
		return value;
	}

	@Override
	public void setValue(T value) {
		this.value = value;

	}

}
