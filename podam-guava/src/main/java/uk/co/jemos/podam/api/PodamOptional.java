package uk.co.jemos.podam.api;

/**
 * An {@link OptionalFactory} that uses a {@link PodamFactory} to hydrate it's optional.
 */
public class PodamOptional<T> extends OptionalFactory<T> {

	private PodamFactory factory = new PodamFactoryImpl();

	/**
	 * {@inheritDoc}
	 */
	public PodamOptional(Class<T> optionalInnerClass, boolean randomlyAbsent) {
		super(optionalInnerClass, randomlyAbsent);
	}

	/**
	 * @param optionalInnerClass the class to hydrate
	 */
	public PodamOptional(Class<T> optionalInnerClass) {
		super(optionalInnerClass, false);
	}

	@Override
	T getOptionalValue() {
		T value = factory.<T>manufacturePojo(making);
		return value;
	}
}
