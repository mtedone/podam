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

	public void addHeavyClass(Class<?> heavyFirstClass) {
		this.heavyFirstClasses.add(heavyFirstClass);
	}

	public void removeHeavyClass(Class<?> heavyFirstClass) {
		this.heavyFirstClasses.remove(heavyFirstClass);
	}

	public Set<Class<?>> getHeavyFirstClasses() {
		return heavyFirstClasses;
	}
}
