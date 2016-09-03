package uk.co.jemos.podam.typeManufacturers;

/**
 * Interface for a type manufacturer
 * Created by tedonema on 17/05/2015.
 *
 * @param <T> The type of the value to be manufactured
 * @since 6.0.0.RELEASE
 */
public interface TypeManufacturer<T> {

    /**
     * Returns a type value conforming to the annotations and the AttributeMetadata provided.
     *
     * @param wrapper The Wrapper object containing parameters required by type manufacturers to work correctly
     *
     * @return A type value conforming to the annotations and the AttributeMetadata provided.
     */
    T getType(TypeManufacturerParamsWrapper wrapper);
}
