package uk.co.jemos.podam.test.dto;


/**
 * Holder for pojo derived from two interfaces: Map/List and another one
 * 
 * @author daivanov
 *
 */
public class MultipleInterfacesHolderPojo {

	private MultipleInterfacesListPojo list;

	private MultipleInterfacesMapPojo map;

	public MultipleInterfacesListPojo getList() {
		return list;
	}

	public void setList(MultipleInterfacesListPojo list) {
		this.list = list;
	}

	public MultipleInterfacesMapPojo getMap() {
		return map;
	}

	public void setMap(MultipleInterfacesMapPojo map) {
		this.map = map;
	}
}

