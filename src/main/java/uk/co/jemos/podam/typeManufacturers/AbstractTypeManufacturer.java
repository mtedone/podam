package uk.co.jemos.podam.typeManufacturers;

import java.util.Collection;

import uk.co.jemos.podam.api.PodamUtils;

/**
 * Parent of all type manufacturer.
 *
 * Created by tedonema on 28/06/2015.
 *
 * @param <T> The type of the value to be manufactured
 * @since 6.0.0.RELEASE
 */
public abstract class AbstractTypeManufacturer<T> implements TypeManufacturer<T> {

	/**
	 * @param <R> The type for which should be found

	 * @param collection collection with elements
	 * @param type a type of which element should be found
	 * @return element of desired type or null, if the collection doesn't
	 * contain any object of that type.
	 */
	public <R> R findElementOfType(Collection<?> collection, Class<R> type) {
		for (Object element : collection) {
			if (type.isInstance(element)) {
				return type.cast(element);
			}
		}
		return null;
	}

    /** It returns a int/Integer value in an interval (0, bound).
	 *
	 * @param bound
	 *            the upper bound (exclusive). Must be positive.
	 * @return A random int value.
	 */
	public int getInteger(int bound) {

		return PodamUtils.getIntegerInRange(0, bound);
	}

	/** It returns a double value in an interval (0, 1.0)
	 * 
	 * @return A random double value
	 */
	public double getDouble() {

		return PodamUtils.getDoubleInRange(0.0, 1.0);
	}
}
