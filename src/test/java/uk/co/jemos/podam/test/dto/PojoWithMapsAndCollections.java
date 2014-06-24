package uk.co.jemos.podam.test.dto;

import java.util.List;
import java.util.Map;

/**
 * Pojo typed class
 *
 * @author daivanov
 *
 */
public class PojoWithMapsAndCollections {

	private String[] array;
	private List<Boolean> list;
	private Map<Integer, Long> map;

	/**
	 * @return the array
	 */
	public String[] getArray() {
		return array;
	}

	/**
	 * @param array
	 *            the array to set
	 */
	public void setArray(String[] array) {
		this.array = array;
	}

	/**
	 * @return the list
	 */
	public List<Boolean> getList() {
		return list;
	}

	/**
	 * @param list
	 *            the list to set
	 */
	public void setList(List<Boolean> list) {
		this.list = list;
	}

	/**
	 * @return the map
	 */
	public Map<Integer, Long> getMap() {
		return map;
	}

	/**
	 * @param map
	 *            the map to set
	 */
	public void setMap(Map<Integer, Long> map) {
		this.map = map;
	}
}
