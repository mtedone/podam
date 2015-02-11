package uk.co.jemos.podam.test.dto.pdm3;

import java.util.List;

/**
 * Pojo to test <a href="https://agileguru.atlassian.net/browse/PDM-3">PDM-3</a>
 * 
 * @author Marco Tedone
 * 
 */
public class Pdm3Pojo {

	private List<?> something;

	private List<? extends RuntimeException> descendants;

	private List<? super NullPointerException> ancestors;

	public List<?> getSomething() {
		return something;
	}

	public void setSomething(List<?> something) {
		this.something = something;
	}

	public List<? extends RuntimeException> getDescendants() {
		return descendants;
	}

	public void setDescendants(List<? extends RuntimeException> descendants) {
		this.descendants = descendants;
	}

	public List<? super NullPointerException> getAncestors() {
		return ancestors;
	}

	public void setAncestors(List<? super NullPointerException> ancestors) {
		this.ancestors = ancestors;
	}

	@Override
	public String toString() {
		return String.format(
				"{something: '%s'} {descendants: '%s'} {ancestors: '%s'}",
				something, descendants, ancestors);
	}
}
