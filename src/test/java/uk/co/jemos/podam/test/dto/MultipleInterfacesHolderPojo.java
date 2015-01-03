package uk.co.jemos.podam.test.dto;


/**
 * Holder for pojo derived from two interfaces: Map/List and another one
 * 
 * @author daivanov
 *
 */
public class MultipleInterfacesHolderPojo<K,V> {

	private MultipleInterfacesListPojo<K> list;

	private MultipleInterfacesMapPojo<K,V> map;

	public MultipleInterfacesListPojo<K> getList() {
		return list;
	}

	public void setList(MultipleInterfacesListPojo<K> list) {
		this.list = list;
	}

	public MultipleInterfacesMapPojo<K,V> getMap() {
		return map;
	}

	public void setMap(MultipleInterfacesMapPojo<K,V> map) {
		this.map = map;
	}
}

