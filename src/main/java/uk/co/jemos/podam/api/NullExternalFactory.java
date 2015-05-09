/**
 * 
 */
package uk.co.jemos.podam.api;

import java.lang.reflect.Type;
import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
	private static final Logger LOG = LoggerFactory
			.getLogger(NullExternalFactory.class);

	/** The singleton instance of this implementation */
	private static final NullExternalFactory SINGLETON
			= new NullExternalFactory();

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
		return SINGLETON;
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
