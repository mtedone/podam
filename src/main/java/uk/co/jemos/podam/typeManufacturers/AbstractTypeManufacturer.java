package uk.co.jemos.podam.typeManufacturers;

import java.util.Random;

/**
 * Parent of all type manufacturer.
 *
 * Created by tedonema on 28/06/2015.
 *
 * @param <T> The type of the value to be manufactured
 * @since 6.0.0.RELEASE
 */
public abstract class AbstractTypeManufacturer<T> implements TypeManufacturer<T> {

	/** A RANDOM generator */
	protected static final Random RANDOM = new Random(System.currentTimeMillis());

}
