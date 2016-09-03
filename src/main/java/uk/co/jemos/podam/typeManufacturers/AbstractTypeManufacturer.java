package uk.co.jemos.podam.typeManufacturers;

/**
 * Parent of all type manufacturer.
 *
 * Created by tedonema on 28/06/2015.
 *
 * @param <T> The type of the value to be manufactured
 * @since 6.0.0.RELEASE
 */
public abstract class AbstractTypeManufacturer<T> implements TypeManufacturer<T> {


    /**
     * Checks that the given wrapper is valid.
     * @param wrapper The wrapper to be checked
     *
     * @throws IllegalArgumentException If the wrapper or its content is invalid
     */
    protected void checkWrapperIsValid(TypeManufacturerParamsWrapper wrapper) {

        String errMsg = null;

        if (null == wrapper) {
            errMsg = "The wrapper cannot be null";
            throw new IllegalArgumentException(errMsg);
        }

        if (null == wrapper.getAttributeMetadata()) {
            errMsg = "The attribute metadata inside the wrapper cannot be null";
            throw new IllegalArgumentException(errMsg);
        }

        if (null == wrapper.getAttributeMetadata().getAttributeAnnotations()) {
            errMsg = "The annotations list within the attribute metadata cannot be null, although it can be empty";
            throw new IllegalArgumentException(errMsg);
        }
    }
}
