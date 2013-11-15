package uk.co.jemos.podam.api;

import com.google.common.base.Optional;

import uk.co.jemos.podam.exceptions.PodamMockeryException;

/**
 * A {@link AttributeStrategy} that understands Guava's {@link Optional} type.
 *
 * Leaves the actual construction of the wrapped optional value up to implementations.
 */
public abstract class OptionalFactory<T> implements AttributeStrategy<Optional<T>> {

	boolean randomlyAbsent;

	/**
	 * @param theOptionalClass the class to hydrate
	 * @param randomlyAbsent true to randomly make the optional absent
	 */
	OptionalFactory(Class<T> theOptionalClass, boolean randomlyAbsent) {
		this.making = theOptionalClass;
		this.randomlyAbsent = randomlyAbsent;
	}

	Class<T> making;

	public Optional<T> getValue() throws PodamMockeryException {
		if (randomlyAbsent && Math.random() > 0.5) {
			return Optional.absent();
		} else {
			T optionalValue = getOptionalValue();
			Optional<T> tOptional = Optional.fromNullable(optionalValue);
			return tOptional;
		}
	}

	/**
	 * Construct the value that the optional wraps.
	 * @return
	 */
	abstract T getOptionalValue();
}