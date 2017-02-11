/**
 * 
 */
package uk.co.jemos.podam.api;

import java.lang.annotation.Annotation;
import java.util.List;

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
	public Object getValue(Class<?> attrType, List<Annotation> attrAnnotations) {
		return null;
	}

}
