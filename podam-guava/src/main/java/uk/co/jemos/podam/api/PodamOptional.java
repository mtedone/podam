package uk.co.jemos.podam.api;

/**
 * An {@link OptionalFactory} that uses a {@link PodamFactory} to hydrate it's optional.
 */
public class PodamOptional<T> extends OptionalFactory<T> {

	private PodamFactory factory;

	/**
	 * {@inheritDoc}
	 */
	public PodamOptional(Class<T> optionalInnerClass, boolean randomlyAbsent) {
		this(optionalInnerClass, randomlyAbsent, new PodamFactoryImpl());
	}

	/**
	 * @param optionalInnerClass the class to hydrate
	 */
	public PodamOptional(Class<T> optionalInnerClass, boolean randomlyAbsent, PodamFactory customFactory) {
		super(optionalInnerClass, randomlyAbsent);
		this.factory = customFactory;
	}

	/**
	 * @param optionalInnerClass the class to hydrate
	 */
	public PodamOptional(Class<T> optionalInnerClass, PodamFactory customFactory) {
		this(optionalInnerClass, false, customFactory);
	}

	/**
	 * @param optionalInnerClass the class to hydrate
	 */
	public PodamOptional(Class<T> optionalInnerClass) {
		this(optionalInnerClass, false);
	}

	@Override
	T getOptionalValue() {
		T value = factory.<T>manufacturePojo(making);
		return value;
	}
}
