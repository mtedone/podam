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
	private static final Random RANDOM = new Random(System.currentTimeMillis());

    /** It returns a int/Integer value in an interval (0, bound).
	 *
	 * @param bound
	 *            the upper bound (exclusive). Must be positive.
	 * @return A random int value.
	 */
	public int getInteger(int bound) {

		int retValue;
		do {
			retValue = RANDOM.nextInt(bound);
		} while (retValue == 0);
		return retValue;
	}

	/** It returns a double value in an interval (0, 1.0)
	 * 
	 * @return A random double value
	 */
	public double getDouble() {

		double retValue;
		do {
			retValue = RANDOM.nextDouble();
		} while (retValue == 0.0f);
		return retValue;
	}
}
