package uk.co.jemos.podam.test.dto.pdm6;

import java.util.List;

/**
 * Pojo to test recursive dependencies
 * 
 * @author daivanov
 * 
 */
public class RecursiveList {

	private List<RecursiveList> list;

	public List<RecursiveList> getList() {
		return this.list;
	}

	public void setList(List<RecursiveList> list) {
		this.list = list;
	}
}