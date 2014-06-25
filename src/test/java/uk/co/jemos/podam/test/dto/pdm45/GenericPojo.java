package uk.co.jemos.podam.test.dto.pdm45;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import uk.co.jemos.podam.common.PodamCollection;

/**
 * Generic Pojo.
 * 
 * @author marciocarmona
 * 
 * @param <F>
 *            the first generic type
 * @param <S>
 *            the second generic type
 */
public class GenericPojo<F, S> {

	private F firstValue;
	private S secondValue;
	@PodamCollection(nbrElements = 2)
	private List<F> firstList;
	@PodamCollection(nbrElements = 2)
	private S[] secondArray;
	@PodamCollection(nbrElements = 2)
	private Map<F, S> firstSecondMap;

	/**
	 * @return the firstValue
	 */
	public F getFirstValue() {
		return firstValue;
	}

	/**
	 * @param firstValue
	 *            the firstValue to set
	 */
	public void setFirstValue(F firstValue) {
		this.firstValue = firstValue;
	}

	/**
	 * @return the secondValue
	 */
	public S getSecondValue() {
		return secondValue;
	}

	/**
	 * @param secondValue
	 *            the secondValue to set
	 */
	public void setSecondValue(S secondValue) {
		this.secondValue = secondValue;
	}

	/**
	 * @return the firstList
	 */
	public List<F> getFirstList() {
		return firstList;
	}

	/**
	 * @param firstList
	 *            the firstList to set
	 */
	public void setFirstList(List<F> firstList) {
		this.firstList = firstList;
	}

	/**
	 * @return the secondArray
	 */
	public S[] getSecondArray() {
		return secondArray;
	}

	/**
	 * @param secondArray
	 *            the secondArray to set
	 */
	public void setSecondArray(S[] secondArray) {
		this.secondArray = secondArray;
	}

	/**
	 * @return the firstSecondMap
	 */
	public Map<F, S> getFirstSecondMap() {
		return firstSecondMap;
	}

	/**
	 * @param firstSecondMap the firstSecondMap to set
	 */
	public void setFirstSecondMap(Map<F, S> firstSecondMap) {
		this.firstSecondMap = firstSecondMap;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "GenericPojo [firstValue=" + firstValue + ", secondValue="
				+ secondValue + ", firstList=" + firstList + ", secondArray="
				+ Arrays.toString(secondArray) + ", firstSecondMap="
				+ firstSecondMap + "]";
	}
}
