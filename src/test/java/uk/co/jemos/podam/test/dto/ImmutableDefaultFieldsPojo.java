/**
 *
 */
package uk.co.jemos.podam.test.dto;

import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * @author divanov
 *
 */
public class ImmutableDefaultFieldsPojo {

	private List<String> list = Collections.singletonList("key");

	private Map<String, Integer> map = Collections.singletonMap("key", 100);

	public List<String> getList() {
		return list;
	}

	public void setList(List<String> list) {
		this.list = list;
	}

	public Map<String, Integer> getMap() {
		return map;
	}

	public void setMap(Map<String, Integer> map) {
		this.map = map;
	}
}
