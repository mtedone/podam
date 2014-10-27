/**
 *
 */
package uk.co.jemos.podam.test.dto;

/**
 * @author tedonema
 *
 */
public class FloatExt implements ObjectExt<Float> {

	private Float value;

	@Override
	public Float getValue() {
		return value;
	}

	@Override
	public void setValue(Float value) {
		this.value = value;
	}

}
