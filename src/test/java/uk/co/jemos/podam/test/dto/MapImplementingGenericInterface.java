/**
 * 
 */
package uk.co.jemos.podam.test.dto;

import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

/**
 * Test pojo
 * <p>
 * Pojo implementing generic interface
 * </p>
 * 
 * @author daivanov
 * 
 */
public class MapImplementingGenericInterface implements Map<Integer, String> {

	private Map<Integer, String> map = new TreeMap<Integer, String>();

	@Override
	public int size() {
		return map.size();
	}

	@Override
	public boolean isEmpty() {
		return map.isEmpty();
	}

	@Override
	public boolean containsKey(Object key) {
		return map.containsKey(key);
	}

	@Override
	public boolean containsValue(Object value) {
		return map.containsValue(value);
	}

	@Override
	public String get(Object key) {
		return map.get(key);
	}

	@Override
	public String put(Integer key, String value) {
		return map.put(key, value);
	}

	@Override
	public String remove(Object key) {
		return map.remove(key);
	}

	@Override
	public void putAll(Map<? extends Integer, ? extends String> m) {
		map.putAll(m);
	}

	@Override
	public void clear() {
		map.clear();
	}

	@Override
	public Set<Integer> keySet() {
		return map.keySet();
	}

	@Override
	public Collection<String> values() {
		return map.values();
	}

	@Override
	public Set<java.util.Map.Entry<Integer, String>> entrySet() {
		return map.entrySet();
	}
}
