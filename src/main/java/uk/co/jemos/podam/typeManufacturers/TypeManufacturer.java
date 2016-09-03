package uk.co.jemos.podam.typeManufacturers;

import java.lang.reflect.Type;
import java.util.Map;

import uk.co.jemos.podam.api.AttributeMetadata;
import uk.co.jemos.podam.api.DataProviderStrategy;

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
     * @param strategy The DataProviderStrategy
     * @param attributeMetadata The AttributeMetadata
     * @param genericTypesArgumentsMap map with generic types mapped to actual types
     *
     * @return A type value conforming to the annotations and the AttributeMetadata provided.
     */
    T getType(DataProviderStrategy strategy,
            AttributeMetadata attributeMetadata,
            Map<String, Type> genericTypesArgumentsMap);
}
