/**
 *
 */
package uk.co.jemos.podam.common;

import java.lang.reflect.Constructor;
import java.util.Comparator;

/**
 * It provides a comparator to sort the constructor to choose first.
 * <p>
 * The priority goes to constructors with the {@link PodamConstructor}
 * annotation first, and then to those with less arguments.
 * </p>
 * 
 * @author tedonema
 *
 */
public class ConstructorComparator implements Comparator<Constructor<?>> {

	@Override
	public int compare(Constructor<?> constructor1, Constructor<?> constructor2) {
		/* Constructors with Podam annotation first */
		boolean choose1 = constructor1.getAnnotation(PodamConstructor.class) != null;
		boolean choose2 = constructor2.getAnnotation(PodamConstructor.class) != null;
		if (choose1 && !choose2) {
			return Integer.MIN_VALUE;
		} else if (!choose1 && choose2) {
			return Integer.MAX_VALUE;
		}

		/* Then constructors with less parameters */
		return constructor1.getParameterTypes().length
				- constructor2.getParameterTypes().length;
	}

}
