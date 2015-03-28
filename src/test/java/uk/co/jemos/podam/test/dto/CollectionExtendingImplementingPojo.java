/**
 * 
 */
package uk.co.jemos.podam.test.dto;

import java.util.LinkedList;
import java.util.Observable;
import java.util.Observer;

/**
 * Test pojo
 * <p>
 * Pojo extending Pojo with generic type and implementing another inteface
 * </p>
 * 
 * @author daivanov
 * 
 */
public class CollectionExtendingImplementingPojo extends LinkedList<String>
		implements Observer {
	private static final long serialVersionUID = 1L;

	@Override
	public void update(Observable o, Object arg) {
	}
}
