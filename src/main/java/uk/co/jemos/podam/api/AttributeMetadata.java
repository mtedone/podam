/**
 * 
 */
package uk.co.jemos.podam.api;

import java.io.Serializable;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.Collections;
import java.util.List;

import net.jcip.annotations.Immutable;

/**
 * Contains metadata about the attribute for which a value is being assigned.
 * <p>
 * This class is available to all strategies and it allows users to customise
 * behaviour of a strategy depending on the metadata of the attribute to which a
 * value is being assigned. For instance, clients might assign different values
 * based on the attribute name.
 * </p>
 * 
 * @author mtedone
 * 
 */
@Immutable
public class AttributeMetadata implements Serializable {

	// ------------------->> Constants

	private static final long serialVersionUID = 1L;

	// ------------------->> Instance / Static variables

	/** The attribute name */
	private final String attributeName;

	/** The attribute type */
	private final Class<?> attributeType;

	/** The attribute generic type */
	private final Type attributeGenericType;

	/** The attribute type generic arguments */
	private final Type[] attrGenericArgs;

	/** The attribute annotations */
	private final List<Annotation> attributeAnnotations;

	/** Type of class that owns the attribute */
	private final Class<?> pojoClass;

	// ------------------->> Constructors

	/**
	 * Full constructor.
	 * 
	 * @param attributeName
	 *            The attribute name
	 * @param attributeType
	 *            The attribute type
	 * @param attributeGenericType
	 *            The attribute generic type
	 * @param attrGenericArgs
	 *            The attribute type generic arguments
	 * @param attributeAnnotations
	 *            The attribute annotations
	 * @param declaringClass
	 *            The type of class that owns the attribute
	 */
	public AttributeMetadata(String attributeName, Class<?> attributeType,
			Type attributeGenericType, Type[] attrGenericArgs,
			List<Annotation> attributeAnnotations, Class<?> declaringClass) {
		this.attributeName = attributeName;
		this.attributeType = attributeType;
		this.attributeGenericType = attributeGenericType;
		this.attrGenericArgs = attrGenericArgs;
		this.attributeAnnotations = attributeAnnotations;
		this.pojoClass = declaringClass;
	}

	/**
	 * Constructor for method parameters metadata
	 * 
	 * @param attributeType
	 *            The attribute type
	 * @param attributeGenericType
	 *            The attribute generic type
	 * @param attrGenericArgs
	 *            The attribute type generic arguments
	 * @param declaringClass
	 *            The type of class that owns the attribute
	 */
	public AttributeMetadata(Class<?> attributeType, Type attributeGenericType,
			Type[] attrGenericArgs, Class<?> declaringClass) {
		this(null, attributeType, attributeGenericType, attrGenericArgs,
				Collections.<Annotation>emptyList(), declaringClass);
	}

	// ------------------->> Public methods

	// ------------------->> Getters / Setters

	public String getAttributeName() {
		return attributeName;
	}

	public Class<?> getAttributeType() {
		return attributeType;
	}

	public Type getAttributeGenericType() {
		return attributeGenericType;
	}

	public Type[] getAttrGenericArgs() {
		return attrGenericArgs;
	}

	public List<Annotation> getAttributeAnnotations() {
		return attributeAnnotations;
	}

	public Class<?> getPojoClass() {
		return pojoClass;
	}

	// ------------------->> Private methods

	// ------------------->> equals() / hashcode() / toString()

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("AttributeMetadata [");
		builder.append("attributeName=").append(attributeName);
		builder.append(", attributeType=").append(attributeType);
		builder.append(", attributeGenericType=").append(attributeGenericType);
		builder.append(", pojoClass=").append(pojoClass);
		builder.append(", attributeAnnotations=").append(attributeAnnotations);
		builder.append("]");
		return builder.toString();
	}

	// ------------------->> Inner classes

}
