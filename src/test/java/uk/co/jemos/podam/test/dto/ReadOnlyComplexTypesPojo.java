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
public class ReadOnlyComplexTypesPojo {

	public static class Value {
		private String value;

		public String getValue() {
			return value;
		}

		public void setValue(String value) {
			this.value = value;
		}
	}

	private List<Integer> list = new ArrayList<Integer>();

	private Map<Long, String> map = new HashMap<Long, String>();
	
	private Value value = new Value();

	public List<Integer> getList() {
		return list;
	}

	public Map<Long, String> getMap() {
		return map;
	}

	public Value getValue() {
		return value;
	}
}
