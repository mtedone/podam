/**
 * 
 */
package uk.co.jemos.podam.api;

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
