package uk.co.jemos.podam.test.dto.pdm3;

import java.util.List;

/**
 * Pojo to test <a href="https://agileguru.atlassian.net/browse/PDM-3">PDM-3</a>
 * 
 * @author daivanov
 * 
 */
public class Pdm3PojoGenericsConstructor {

	public Pdm3PojoGenericsConstructor(
			List<?> something,
			List<? extends RuntimeException> descendants,
			List<? super NullPointerException> ancestors) {
		this.something = something;
		this.descendants = descendants;
		this.ancestors = ancestors;
	}

	private List<?> something;

	private List<? extends RuntimeException> descendants;

	private List<? super NullPointerException> ancestors;

	public List<?> getSomething() {
		return something;
	}

	public List<? extends RuntimeException> getDescendants() {
		return descendants;
	}

	public List<? super NullPointerException> getAncestors() {
		return ancestors;
	}

	@Override
	public String toString() {
		return String.format(
				"{something: '%s'} {descendants: '%s'} {ancestors: '%s'}",
				something, descendants, ancestors);
	}
}
