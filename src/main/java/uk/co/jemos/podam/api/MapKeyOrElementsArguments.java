package uk.co.jemos.podam.api;

import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.Arrays;

/**
 * Contains attributes for the arguments to pass to the factory method to fill
 * map key or elements.
 * 
 * @author Marco Tedone
 * 
 */
public class MapKeyOrElementsArguments extends AbstractMapArguments implements
		Serializable {

	private static final long serialVersionUID = 1L;

	/** The Map key / element type. */
	private Class<?> keyOrValueType;

	/** The {@link PodamCollection} annotation. */
	private PodamCollection collectionAnnotation;

	/** The strategy to use to fill the Map key or value element. */
	private AttributeStrategy<?> elementStrategy;

	/**
	 * The generic type arguments for the current generic class
	 * instance.
	 */
	private Type[] genericTypeArgs;

	/**
	 * @return the keyOrValueType
	 */
	public Class<?> getKeyOrValueType() {
		return keyOrValueType;
	}

	/**
	 * @param keyOrValueType
	 *            the keyOrValueType to set
	 */
	public void setKeyOrValueType(Class<?> keyOrValueType) {
		this.keyOrValueType = keyOrValueType;
	}

	/**
	 * @return the collectionAnnotation
	 */
	public PodamCollection getCollectionAnnotation() {
		return collectionAnnotation;
	}

	/**
	 * @param collectionAnnotation
	 *            the collectionAnnotation to set
	 */
	public void setCollectionAnnotation(PodamCollection collectionAnnotation) {
		this.collectionAnnotation = collectionAnnotation;
	}

	/**
	 * @return the elementStrategy
	 */
	public AttributeStrategy<?> getElementStrategy() {
		return elementStrategy;
	}

	/**
	 * @param elementStrategy
	 *            the elementStrategy to set
	 */
	public void setElementStrategy(AttributeStrategy<?> elementStrategy) {
		this.elementStrategy = elementStrategy;
	}

	/**
	 * @return the genericTypeArgs
	 */
	public Type[] getGenericTypeArgs() {
		return genericTypeArgs;
	}

	/**
	 * @param genericTypeArgs
	 *            the genericTypeArgs to set
	 */
	public void setGenericTypeArgs(Type[] genericTypeArgs) {
		this.genericTypeArgs = genericTypeArgs.clone();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("MapKeyOrElementsArguments [keyOrValueType=");
		builder.append(keyOrValueType);
		builder.append(", collectionAnnotation=");
		builder.append(collectionAnnotation);
		builder.append(", elementStrategy=");
		builder.append(elementStrategy);
		builder.append(", genericTypeArgs=");
		builder.append(Arrays.toString(genericTypeArgs));
		builder.append(", pojoClass=");
		builder.append(getPojoClass());
		builder.append(", pojos=");
		builder.append(getPojos());
		builder.append(", attributeName=");
		builder.append(getAttributeName());
		builder.append(", annotations=");
		builder.append(getAnnotations());
		builder.append("]");
		return builder.toString();
	}

}
