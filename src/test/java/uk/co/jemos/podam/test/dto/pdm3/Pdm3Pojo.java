package uk.co.jemos.podam.test.dto.pdm3;

import java.util.List;

/**
 * Pojo to test <a href="https://agileguru.atlassian.net/browse/PDM-3">PDM-3</a>
 * 
 * @author tedonema
 * 
 */
public class Pdm3Pojo {

	private List<? extends Object> objs;

	public List<? extends Object> getObjs() {
		return objs;
	}

	public void setObjs(List<? extends Object> objs) {
		this.objs = objs;
	}

	@Override
	public String toString() {
		return "{objs: '" + objs + "'}";
	}
}
