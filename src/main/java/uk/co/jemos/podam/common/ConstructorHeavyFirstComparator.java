/**
 *
 */
package uk.co.jemos.podam.common;

import java.lang.reflect.Constructor;

/**
 * It provides a comparator to sort the constructor to choose first.
 * <p>
 * The priority goes to constructors with the {@link PodamConstructor}
 * annotation first, and then to those with less arguments.
 * </p>
 * 
 * @author daivanov
 *
 */
public class ConstructorHeavyFirstComparator extends AbstractConstructorComparator {

	public static final ConstructorHeavyFirstComparator INSTANCE =
			new ConstructorHeavyFirstComparator();

	private ConstructorHeavyFirstComparator() {
	}

	@Override
	public int compare(Constructor<?> constructor1, Constructor<?> constructor2) {

		int result = super.compareAnnotations(constructor1, constructor2);
		if (result != 0) {
			return result;
		}

		/* Then constructors with more parameters */
		result = -constructor1.getParameterTypes().length
				+ constructor2.getParameterTypes().length;
		if (result != 0) {
			return result;
		}

		/* Then less complex constructor */
		return constructorComplexity(constructor1)
				- constructorComplexity(constructor2);
	}

}
