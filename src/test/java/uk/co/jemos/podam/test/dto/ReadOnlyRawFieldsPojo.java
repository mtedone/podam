/**
 *
 */
package uk.co.jemos.podam.test.dto;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * POJO to test read-only fields with wildcards instantiation
 *
 * @author daivanov
 *
 */
@SuppressWarnings("rawtypes")
public class ReadOnlyRawFieldsPojo {

	private Class clazz = Object.class;

	private List list = new ArrayList<Object>();

	private Map map = new HashMap<Object, Object>();

	public Class getClazz() {
		return clazz;
	}

	public List getList() {
		return list;
	}

	public Map getMap() {
		return map;
	}
}
