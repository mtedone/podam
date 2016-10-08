package uk.co.jemos.podam.test.dto.pdm3;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBElement;

/**
 * Pojo to test <a href="https://agileguru.atlassian.net/browse/PDM-3">PDM-3</a>
 * 
 * @author daivanov
 * 
 */
public class WildcardPojo {

	private List<JAXBElement<?>> something = new ArrayList<JAXBElement<?>>();

	private List<JAXBElement<? extends RuntimeException>> descendants = new ArrayList<JAXBElement<? extends RuntimeException>>();

	private List<JAXBElement<? super NullPointerException>> ancestors = new ArrayList<JAXBElement<? super NullPointerException>>();

	public List<JAXBElement<?>> getSomething() {
		return something;
	}

	public List<JAXBElement<? super NullPointerException>> getAncestors() {
		return ancestors;
	}

	public List<JAXBElement<? extends RuntimeException>> getDescendants() {
		return descendants;
	}

	@Override
	public String toString() {
		return String.format(
				"{something: '%s'} {descendants: '%s'} {ancestors: '%s'}",
				something, descendants, ancestors);
	}
}
