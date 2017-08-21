package uk.co.jemos.podam.test.dto;

import java.util.Map;
import java.util.TreeMap;

/**
 * Pojo with default map, which has key and value of the same type
 *
 * @author daivanov
 *
 */
public class DefaultMapSingleTypePojo<K> {

	private Map<K,K> map = new TreeMap<K,K>();

	public Map<K,K> getMap() {
		return map;
	}
}
