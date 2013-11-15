package uk.co.jemos.podam.api;

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
	 * @param rawType the generic class type
	 * @param actualTypeArguments the type arguments
	 */
	public PodamParameterizedType(final Class<?> rawType, final Type... actualTypeArguments) {
		super();
		this.rawType = rawType;
		this.actualTypeArguments = actualTypeArguments;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.reflect.ParameterizedType#getActualTypeArguments()
	 */
	public Type[] getActualTypeArguments() {
		return actualTypeArguments;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.reflect.ParameterizedType#getRawType()
	 */
	public Class<?> getRawType() {
		return rawType;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.reflect.ParameterizedType#getOwnerType()
	 */
	public Type getOwnerType() {
		return null;
	}

}
