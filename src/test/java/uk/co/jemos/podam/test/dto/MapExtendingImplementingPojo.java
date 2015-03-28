/**
 * 
 */
package uk.co.jemos.podam.test.dto;

import java.util.Observable;
import java.util.Observer;
import java.util.TreeMap;

/**
 * Test pojo
 * <p>
 * Pojo extending Pojo with generic type.
 * </p>
 * 
 * @author daivanov
 * 
 */
public class MapExtendingImplementingPojo extends TreeMap<Integer,String>
		implements Observer {
	private static final long serialVersionUID = 1L;

	@Override
	public void update(Observable o, Object arg) {
	}
}
