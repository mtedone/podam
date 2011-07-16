package uk.co.jemos.podam.test.dto.pdm42;

import java.util.Map;

public abstract class C {

	private Map<FieldInfo, Object> customValue;

	public Map<FieldInfo, Object> getCustomValue() {
		return customValue;
	}

	public void setCustomValue(Map<FieldInfo, Object> customValue) {
		this.customValue = customValue;
	}

}
