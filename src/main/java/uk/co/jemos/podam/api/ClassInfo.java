/**
 *
 */
package uk.co.jemos.podam.api;

import net.jcip.annotations.Immutable;

import java.io.Serializable;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * This class wraps fields and setters information about a given class
 * <p>
 * The purpose of this class is to work as a sort of cache which stores the list
 * of declared fields and setter methods of a given class. These information
 * will then be analysed to compose the list of setters which can be invoked to
 * create the state of a given POJO.
 * </p>
 *
 * @author mtedone
 *
 * @since 1.0.0
 *
 */
@Immutable
public class ClassInfo implements Serializable {

	private static final long serialVersionUID = 1L;

	/** The Class name whose info are stored in this class */
	private final Class<?> className;

	/** The Set of fields belonging to this class */
	private final Set<ClassAttribute> classAttributes = new HashSet<ClassAttribute>();
	private final List<Method> extraMethods = new ArrayList<Method>();

	/**
	 * Partial constructor.
	 *
	 * @param className
	 *            The class name
	 * @param classAttributes
	 *            The collection of attributes belonging to this class
	 */
	public ClassInfo(Class<?> className, Collection<ClassAttribute> classAttributes) {
		this(className, classAttributes, Collections.<Method>emptyList());
	}

	/**
	 * Full constructor.
	 * @param className The class name
	 * @param classAttributes The collection of attributes belonging to this class
	 * @param extraMethods The collection of extra methods to execute
	 */
	public ClassInfo(Class<?> className, Collection<ClassAttribute> classAttributes, Collection<Method> extraMethods) {
		this.className = className;
		this.classAttributes.addAll(classAttributes);
		this.extraMethods.addAll(extraMethods);
	}

	/**
	 * @return the classSetters
	 */
	public Set<ClassAttribute> getClassAttributes() {
		return new HashSet<ClassAttribute>(classAttributes);
	}

	/**
	 * It returns the class name.
	 *
	 * @return the className
	 */
	public Class<?> getClassName() {
		return className;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ (classAttributes == null ? 0 : classAttributes.hashCode());
		result = prime * result
				+ (className == null ? 0 : className.hashCode());
		return result;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (this.getClass() != obj.getClass()) {
			return false;
		}
		ClassInfo other = (ClassInfo) obj;
		if (classAttributes == null) {
			if (other.classAttributes != null) {
				return false;
			}
		} else if (!classAttributes.equals(other.classAttributes)) {
			return false;
		}
		if (className == null) {
			if (other.className != null) {
				return false;
			}
		} else if (!className.getName().equals(other.className.getName())) {
			return false;
		}
		return true;
	}



}
