/**
 *
 */
package uk.co.jemos.podam.common;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
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

	public int methodComplexity(Method method) {

		int complexity = 0;
		for (Class<?> parameter : method.getParameterTypes()) {
			if (parameter.isInterface()
					|| (Modifier.isAbstract(parameter.getModifiers()) && !parameter.isPrimitive())
					|| parameter.isAssignableFrom(method.getDeclaringClass())) {
				complexity++;
			}
		}
		return complexity;
	}

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
		int result = method2.getParameterTypes().length
				- method1.getParameterTypes().length;
		if (result != 0) {
			return result;
		}

		/* Then less complex method */
		return methodComplexity(method1)
				- methodComplexity(method2);
	}

}
