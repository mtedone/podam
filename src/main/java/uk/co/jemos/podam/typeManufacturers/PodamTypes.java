package uk.co.jemos.podam.typeManufacturers;

import uk.co.jemos.podam.api.TypeManufacturerParamsWrapper;

/**
 * Interface for a type manufacturer
 * Created by tedonema on 17/05/2015.
 *
 * @since 6.0.0.RELEASE
 */
public interface PodamTypes {

    /**
     * Returns a type value conforming to the annotations and the AttributeMetadata provided.
     *
     * @param wrapper The Wrapper object containing parameters required by type manufacturers to work correctly
     *
     * @return A type value conforming to the annotations and the AttributeMetadata provided.
     */
    Object getType(TypeManufacturerParamsWrapper wrapper);
}
