package uk.co.jemos.podam.test.dto;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * POJO with read only fields which are not accessible
 *
 * @author daivanov
 */
final class ReadOnlyNonAccessible extends ReadOnlyAbstract {

	private List<Integer> list = new ArrayList<Integer>();

	private Map<Long, String> map = new HashMap<Long, String>();

	public List<Integer> getList() {
		return list;
	}

	public Map<Long, String> getMap() {
		return map;
	}
}
