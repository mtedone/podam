package uk.co.jemos.podam.api;

import com.google.common.base.Optional;

/**
 * A {@link PodamFactory} that comes with the {@link GenericOptionalFactory}
 * pre registered for your convenience.
 */
public class GuavaPodamFactory extends PodamFactoryImpl {

	private GenericOptionalFactory podamOptional;

	{
		podamOptional = new GenericOptionalFactory(false);
		super.registerFactory(Optional.class, podamOptional);
	}

	public GuavaPodamFactory(DataProviderStrategy strategy) {
		super(strategy);
	}

	public GuavaPodamFactory() {
	}

	/**
	 * @see GuavaPodamFactory#setRandomlyAbsent(boolean)
	 */
	public void setRandomlyAbsent(boolean randomlyAbsent) {
		podamOptional.setRandomlyAbsent(randomlyAbsent);
	}
}
