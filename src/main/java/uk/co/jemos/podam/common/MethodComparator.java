/**
 *
 */
package uk.co.jemos.podam.common;

import java.lang.reflect.Method;
import java.util.Comparator;

/**
 * It provides a comparator to sort the constructor to choose first.
 * <p>
 * The priority goes to constructors with the {@link PodamConstructor}
 * annotation first, and then to those with more arguments.
 * </p>
 * 
 * @author tedonema
 *
 */
public class MethodComparator implements Comparator<Method> {

	@Override
	public int compare(Method method1, Method method2) {
		/* Constructors with Podam annotation first */
		boolean choose1 = method1.getAnnotation(PodamConstructor.class) != null;
		boolean choose2 = method2.getAnnotation(PodamConstructor.class) != null;
		if (choose1 && !choose2) {
			return Integer.MIN_VALUE;
		} else if (!choose1 && choose2) {
			return Integer.MAX_VALUE;
		}

		/* Then constructors with more parameters */
		return method2.getParameterTypes().length
				- method1.getParameterTypes().length;
	}

}
