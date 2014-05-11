/**
 * 
 */
package uk.co.jemos.podam.api;

import java.io.Serializable;
import java.lang.reflect.Method;
import java.util.Set;

import net.jcip.annotations.Immutable;

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
	private final Set<String> classFields;

	/** The Set of setters belonging to this class */
	private final Set<Method> classSetters;

	/**
	 * Full constructor
	 * 
	 * @param className
	 *            The class name
	 * @param classFields
	 *            The set of fields belonging to this class
	 * @param classSetters
	 *            The set of setters belonging to this class
	 */
	public ClassInfo(Class<?> className, Set<String> classFields,
			Set<Method> classSetters) {
		super();
		this.className = className;
		this.classFields = classFields;
		this.classSetters = classSetters;
	}

	/**
	 * @return the classSetters
	 */
	public Set<Method> getClassSetters() {
		return classSetters;
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
				+ (classFields == null ? 0 : classFields.hashCode());
		result = prime * result
				+ (className == null ? 0 : className.hashCode());
		result = prime * result
				+ (classSetters == null ? 0 : classSetters.hashCode());
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
		if (classFields == null) {
			if (other.classFields != null) {
				return false;
			}
		} else if (!classFields.equals(other.classFields)) {
			return false;
		}
		if (className == null) {
			if (other.className != null) {
				return false;
			}
		} else if (!className.getName().equals(other.className.getName())) {
			return false;
		}
		if (classSetters == null) {
			if (other.classSetters != null) {
				return false;
			}
		} else if (!classSetters.equals(other.classSetters)) {
			return false;
		}
		return true;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ClassInfo [className=");
		builder.append(className);
		builder.append(", classFields=");
		builder.append(classFields);
		builder.append(", classSetters=");
		builder.append(classSetters);
		builder.append("]");
		return builder.toString();
	}

}
