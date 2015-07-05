package uk.co.jemos.podam.typeManufacturers;

/**
 * Interface for a type manufacturer
 * Created by tedonema on 17/05/2015.
 *
 * @since 6.0.0.RELEASE
 */
public interface TypeManufacturer {

    /**
     * Returns a type value conforming to the annotations and the AttributeMetadata provided.
     *
     * @param wrapper The Wrapper object containing parameters required by type manufacturers to work correctly
     *
     * @return A type value conforming to the annotations and the AttributeMetadata provided.
     */
    <T extends TypeManufacturerParamsWrapper> Object  getType(T wrapper);
}
