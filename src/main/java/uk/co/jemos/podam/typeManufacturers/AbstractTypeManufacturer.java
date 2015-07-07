package uk.co.jemos.podam.typeManufacturers;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Parent of all type manufacturer.
 *
 * Created by tedonema on 28/06/2015.
 *
 * @since 6.0.0.RELEASE
 */
public abstract class AbstractTypeManufacturer implements TypeManufacturer {


    /** The application logger */
    private static final Logger LOG = LoggerFactory.getLogger(AbstractTypeManufacturer.class);

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
