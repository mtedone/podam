/**
 * 
 */
package uk.co.jemos.podam.api;

import uk.co.jemos.podam.common.AttributeStrategy;
import uk.co.jemos.podam.common.PodamCollection;

/**
 * A default Object strategy, just to provide a default to
 * {@link PodamCollection#collectionElementStrategy()}.
 * 
 * @author mtedone
 * 
 */
public class ObjectStrategy implements AttributeStrategy<Object> {

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Object getValue() {
		return new Object();
	}

}
