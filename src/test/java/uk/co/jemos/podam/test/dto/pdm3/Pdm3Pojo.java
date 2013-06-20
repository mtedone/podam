package uk.co.jemos.podam.test.dto.pdm3;

import java.util.List;

/**
 * Pojo to test <a href="https://agileguru.atlassian.net/browse/PDM-3">PDM-3</a>
 * 
 * @author tedonema
 * 
 */
public class Pdm3Pojo {

	private List<?> something;

	private List<? extends Object> descendants;

	private List<? super String> ancestors;

	public List<?> getSomething() {
		return something;
	}

	public void setSomething(List<?> something) {
		this.something = something;
	}

	public List<? extends Object> getDescendants() {
		return descendants;
	}

	public void setDescendants(List<? extends Object> descendants) {
		this.descendants = descendants;
	}

	public List<? super String> getAncestors() {
		return ancestors;
	}

	public void setAncestors(List<? super String> ancestors) {
		this.ancestors = ancestors;
	}

	@Override
	public String toString() {
		return String.format(
				"{something: '%s'} {descendants: '%s'} {ancestors: '%s'}",
				something, descendants, ancestors);
	}
}
