package uk.co.jemos.podam.api;

import com.google.common.base.Optional;
import com.google.common.reflect.TypeToken;
import uk.co.jemos.podam.exceptions.PodamMockeryException;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * Will construct the value of the optional from the {@link Type} passed into #getValue(Type).
 */
public class GenericOptionalFactory implements AttributeStrategyWithGenerics<Optional<Object>> {

	boolean randomlyAbsent;

	private PodamFactory factory;

	/**
	 * @param randomlyAbsent true to randomly make the optional absent
	 */
	GenericOptionalFactory(boolean randomlyAbsent) {
		this.randomlyAbsent = randomlyAbsent;
		factory = new PodamFactoryImpl();
	}

	GenericOptionalFactory(boolean randomlyAbsent, PodamFactory factory) {
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

	private Object getOptionalValue(Type theType) {
		if (theType instanceof ParameterizedType) {
			ParameterizedType theParamType = (ParameterizedType) theType;
			Type[] actualTypeArguments = theParamType.getActualTypeArguments();
			if (actualTypeArguments.length != 1) {
				throw new IllegalStateException("Only support single type argument");
			} else {
				Type actualTypeArgument = actualTypeArguments[0];
				TypeToken<?> typeToken = TypeToken.of(actualTypeArgument);
				Class<?> rawType = typeToken.getRawType();
				Object value = factory.manufacturePojo(rawType);
				return value;
			}
		} else {
			throw new IllegalStateException("Must be param type");
		}
	}

	public Optional<Object> getValue() throws PodamMockeryException {
		throw new IllegalStateException("Should call #getValue(Type) instead.");
	}

	public void setRandomlyAbsent(boolean randomlyAbsent) {
		this.randomlyAbsent = randomlyAbsent;
	}
}