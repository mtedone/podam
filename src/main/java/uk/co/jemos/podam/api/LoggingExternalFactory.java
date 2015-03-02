/**
 * 
 */
package uk.co.jemos.podam.api;

import java.lang.reflect.Type;
import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Default logging implementation of an external factory for {@link PodamFactory}
 * 
 * @author daivanov
 * 
 * @since 4.3.0
 * 
 */
public class LoggingExternalFactory implements PodamFactory {

	// ------------------->> Constants

	/** Application logger */
	private static final Logger LOG = LoggerFactory
			.getLogger(LoggingExternalFactory.class);

	/** The singleton instance of this implementation */
	private static final LoggingExternalFactory SINGLETON
			= new LoggingExternalFactory();

	/** Empty list of types */
	private static final Type[] NO_TYPES = new Type[0];

	// ------------------->> Constructors

	/**
	 * Implementation of the Singleton pattern
	 */
	private LoggingExternalFactory() {
	}

	// ------------------->> Public methods

	/**
	 * Instantiation method
	 *
	 * @return A singleton instance of this class
	 */
	public static LoggingExternalFactory getInstance() {
		return SINGLETON;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public <T> T manufacturePojo(Class<T> pojoClass) {
		return this.manufacturePojo(pojoClass, NO_TYPES);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public <T> T manufacturePojoWithFullData(Class<T> pojoClass,
			Type... genericTypeArgs) {
		return this.manufacturePojo(pojoClass, genericTypeArgs);
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

	/**
	 * {@inheritDoc}
	 */
	@Override
	public DataProviderStrategy getStrategy() {
		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ClassInfoStrategy getClassStrategy() {
		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public PodamFactory setClassStrategy(ClassInfoStrategy classInfoStrategy) {
		return this;
	}

}
