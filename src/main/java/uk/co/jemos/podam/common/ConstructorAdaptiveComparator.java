/**
 *
 */
package uk.co.jemos.podam.common;

import java.lang.reflect.Constructor;
import java.util.HashSet;
import java.util.Set;

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
public class ConstructorAdaptiveComparator extends AbstractConstructorComparator {

	public static final ConstructorAdaptiveComparator INSTANCE =
			new ConstructorAdaptiveComparator();

	private Set<Class<?>> heavyFirstClasses = new HashSet<Class<?>>();

	private ConstructorAdaptiveComparator() {
	}

	@Override
	public int compare(Constructor<?> constructor1, Constructor<?> constructor2) {

		if (heavyFirstClasses.contains(constructor1.getDeclaringClass())) {
			return ConstructorHeavyFirstComparator.INSTANCE.compare(constructor1, constructor2);
		} else {
			return ConstructorLightFirstComparator.INSTANCE.compare(constructor1, constructor2);
		}
	}

	/**
	 * Register class type as heavy-first
	 *
	 * @param heavyFirstClass
	 *        Class type to add as heavy-first class
	 */
	public void addHeavyClass(Class<?> heavyFirstClass) {
		this.heavyFirstClasses.add(heavyFirstClass);
	}

	/**
	 * Checks, if class was added to the set of heavy-first classes
	 *
	 * @param heavyFirstClass
	 *        Class type to check
	 * @return true, if class was added to the set of heavy-first classes
	 */
	public boolean isHeavyClass(Class<?> heavyFirstClass) {
		return this.heavyFirstClasses.contains(heavyFirstClass);
	}

	/**
	 * Unregister class type from the set of heavy-first classes
	 *
	 * @param heavyFirstClass
	 *        Class type to remove from the set of heavy-first classes
	 */
	public void removeHeavyClass(Class<?> heavyFirstClass) {
		this.heavyFirstClasses.remove(heavyFirstClass);
	}

}
