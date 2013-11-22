package uk.co.jemos.podam.api;

import com.google.common.base.Optional;
import uk.co.jemos.podam.exceptions.PodamMockeryException;

import java.lang.reflect.Type;

/**
 * Will construct the value of the optional from the {@link Type} passed into #getValue(Type).
 */
public class GenericOptionalFactory implements AttributeStrategyWithGenerics<Optional<Object>> {

	boolean randomlyAbsent;

	private PodamFactory factory;

	private GuavaReflectionUtils guavaReflectionUtils = new GuavaReflectionUtils();

	public GenericOptionalFactory() {
		this(false);
	}

	/**
	 * @param randomlyAbsent true to randomly make the optional absent
	 */
	public GenericOptionalFactory(boolean randomlyAbsent) {
		this.randomlyAbsent = randomlyAbsent;
		factory = new PodamFactoryImpl();
	}

	public GenericOptionalFactory(boolean randomlyAbsent, PodamFactory factory) {
		this.randomlyAbsent = randomlyAbsent;
		this.factory = factory;
	}

	public Optional<Object> getValue(Type theType) throws PodamMockeryException {
		if (randomlyAbsent && Math.random() > 0.5) {
			return Optional.absent();
		} else {
			Object optionalValue = getOptionalValue(theType);
			Optional<Object> tOptional = Optional.fromNullable(optionalValue);
			return tOptional;
		}
	}

	private Object getOptionalValue(Type rawType) {
		Class<?> rawClass = guavaReflectionUtils.getRawGenericParameterClass(rawType);
		Object value = factory.manufacturePojo(rawClass);
		return value;
	}

	public Optional<Object> getValue() throws PodamMockeryException {
		throw new IllegalStateException("Should call #getValue(Type) instead.");
	}

	public void setRandomlyAbsent(boolean randomlyAbsent) {
		this.randomlyAbsent = randomlyAbsent;
	}
}