/**
 * 
 */
package uk.co.jemos.podam.api;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Type;
import java.util.Arrays;

/**
 * Default implementation of an external factory which does nothing.
 * 
 * @author daivanov
 * 
 * @since 4.3.0
 * 
 */
public class NullExternalFactory extends AbstractExternalFactory {

	// ------------------->> Constants

	/** Application logger */
	private static final Logger LOG = LoggerFactory.getLogger(NullExternalFactory.class);

	// ------------------->> Constructors

	/**
	 * Implementation of the Singleton pattern
	 */
	private NullExternalFactory() {
	}

	// ------------------->> Public methods

	/**
	 * Instantiation method
	 *
	 * @return A singleton instance of this class
	 */
	public static NullExternalFactory getInstance() {
		return new NullExternalFactory();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public <T> T manufacturePojo(Class<T> pojoClass, Type... genericTypeArgs) {
		LOG.warn("Cannot instantiate {} with arguments {}. Returning null.",
				pojoClass, Arrays.toString(genericTypeArgs));
		return null;
	}

	@Override
	public <T> T populatePojo(T pojo, Type... genericTypeArgs) {
		return pojo;
	}
}
