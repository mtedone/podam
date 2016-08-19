/**
 * 
 */
package uk.co.jemos.podam.test.dto;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * Test pojo
 * <p>
 * Pojo with read-only map and collection
 * </p>
 * 
 * @author daivanov
 * 
 */
public class PojoWithCollectionAndMap {

	private CollectionImplementingGenericsInterface list = new CollectionImplementingGenericsInterface();

	private MapImplementingGenericInterface map = new MapImplementingGenericInterface();

	private CollectionIndirectExtendingGenericsPojo list2 = new CollectionIndirectExtendingGenericsPojo();

	private MapIndirectExtendingGenericsPojo map2 = new MapIndirectExtendingGenericsPojo();

	private Collection<String> list3 = new ArrayList<String>();

	private Map<Integer,String> map3 = new HashMap<Integer,String>();

	public CollectionImplementingGenericsInterface getList() {
		return list;
	}

	public void setList(CollectionImplementingGenericsInterface list) {
		this.list = list;
	}

	public MapImplementingGenericInterface getMap() {
		return map;
	}

	public void setMap(MapImplementingGenericInterface map) {
		this.map = map;
	}

	public CollectionIndirectExtendingGenericsPojo getList2() {
		return list2;
	}

	public void setList2(CollectionIndirectExtendingGenericsPojo list2) {
		this.list2 = list2;
	}

	public MapIndirectExtendingGenericsPojo getMap2() {
		return map2;
	}

	public void setMap2(MapIndirectExtendingGenericsPojo map2) {
		this.map2 = map2;
	}

	public Collection<String> getList3() {
		return list3;
	}

	public void setList3(Collection<String> list3) {
		this.list3 = list3;
	}

	public Map<Integer,String> getMap3() {
		return map3;
	}

	public void setMap3(Map<Integer,String> map3) {
		this.map3 = map3;
	}

}
