/**
 * 
 */
package uk.co.jemos.podam.api;

import java.lang.reflect.Method;

/**
 * A Listener interface that listens to the factory events like setting the attribute. 
 * 
 * @author ganeshs
 *
 */
public interface PodamFactoryListener {

	/**
	 * Invoked when an attribute is set to the pojo
	 * 
	 * @param pojo
	 *            The POJO instance whose attribute is set
	 * @param setter
	 *            The setter method
	 * @param value
	 *            The setter value
	 */
	void onAttributeSet(Object pojo, Method setter, Object value);
	
}
