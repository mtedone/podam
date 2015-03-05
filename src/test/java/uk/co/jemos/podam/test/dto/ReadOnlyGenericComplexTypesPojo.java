package uk.co.jemos.podam.test.dto;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Generic POJO with read only complex type fields
 *
 * @author daivanov
 */
public class ReadOnlyGenericComplexTypesPojo<T, E, K> {

	public static class Value<T> {
		private T value;

		public T getValue() {
			return value;
		}

		public void setValue(T value) {
			this.value = value;
		}
	}

	private List<E> list = new ArrayList<E>();

	private Map<K, String> map = new HashMap<K, String>();
	
	private Value<T> value = new Value<T>();

	public List<E> getList() {
		return list;
	}

	public Map<K, String> getMap() {
		return map;
	}

	public Value<T> getValue() {
		return value;
	}
}
