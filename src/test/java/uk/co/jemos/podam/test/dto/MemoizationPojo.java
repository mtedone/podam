/**
 *
 */
package uk.co.jemos.podam.test.dto;

import java.util.Currency;
import java.util.List;
import java.util.Map;

/**
 * POJO to test when Podam memoization with collections and arrays
 *
 * @author daivanov
 *
 */
public class MemoizationPojo {

	private Currency single;
	private Currency singleReadOnly;
	private Currency[] array;
	private List<Currency> list;
	private Map<Currency, Currency> map;

	public MemoizationPojo(Currency singleReadOnly) {
		this.singleReadOnly = singleReadOnly;
	}

	public Currency getSingle() {
		return single;
	}

	public Currency getSingleReadOnly() {
		return singleReadOnly;
	}

	public void setSingle(Currency single) {
		this.single = single;
	}

	public Currency[] getArray() {
		return array;
	}

	public void setArray(Currency[] array) {
		this.array = array;
	}

	public List<Currency> getList() {
		return list;
	}

	public void setList(List<Currency> list) {
		this.list = list;
	}

	public Map<Currency, Currency> getMap() {
		return map;
	}

	public void setMap(Map<Currency, Currency> map) {
		this.map = map;
	}
}
