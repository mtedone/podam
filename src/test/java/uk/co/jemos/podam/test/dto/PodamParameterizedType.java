package uk.co.jemos.podam.test.dto;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * {@link ParameterizedType} implementation for generic multidimensional pojo
 * manufacturing
 * 
 * @author marciocarmona
 * 
 */
public class PodamParameterizedType implements ParameterizedType {

	private final Class<?> rawType;
	private final Type[] actualTypeArguments;

	/**
	 * Base constructor.
	 * 
	 * @param rawType
	 *            the generic class type
	 * @param actualTypeArguments
	 *            the type arguments
	 */
	public PodamParameterizedType(final Class<?> rawType,
			final Type... actualTypeArguments) {
		super();
		this.rawType = rawType;
		this.actualTypeArguments = actualTypeArguments;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Type[] getActualTypeArguments() {
		return actualTypeArguments;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Class<?> getRawType() {
		return rawType;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Type getOwnerType() {
		return null;
	}

}
