/**
 *
 */
package uk.co.jemos.podam.common;

import java.lang.reflect.Method;

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
public class MethodLightFirstComparator extends AbstractMethodComparator {

	public static final MethodLightFirstComparator INSTANCE =
			new MethodLightFirstComparator();

	private MethodLightFirstComparator() {
	}

	@Override
	public int compare(Method method1, Method method2) {

		int result = super.compareAnnotations(method1, method2);
		if (result != 0) {
			return result;
		}

		/* Then constructors with less parameters */
		result = method1.getParameterTypes().length
				- method2.getParameterTypes().length;
		if (result != 0) {
			return result;
		}

		/* Then less complex method */
		return methodComplexity(method1)
				- methodComplexity(method2);
	}

}
