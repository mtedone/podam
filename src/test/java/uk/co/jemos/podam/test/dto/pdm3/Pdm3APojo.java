package uk.co.jemos.podam.test.dto.pdm3;

import java.util.List;

import uk.co.jemos.podam.test.dto.pdm42.A;

/**
 * Pojo to test <a href="https://agileguru.atlassian.net/browse/PDM-3">PDM-3</a>
 * 
 * @author tedonema
 * 
 */
public class Pdm3APojo {

	private List<? extends A> objs;

	public List<? extends A> getObjs() {
		return objs;
	}

	public void setObjs(List<? extends A> objs) {
		this.objs = objs;
	}

	@Override
	public String toString() {
		return "{objs: '" + objs + "'}";
	}
}
