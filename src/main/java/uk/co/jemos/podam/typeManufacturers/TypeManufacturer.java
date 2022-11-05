package uk.co.jemos.podam.typeManufacturers;

import java.lang.reflect.Type;
import java.util.Map;

import uk.co.jemos.podam.api.AttributeMetadata;
import uk.co.jemos.podam.api.DataProviderStrategy;
import uk.co.jemos.podam.common.ManufacturingContext;

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
     * @param manufacturingCtx pojo manufacturing context with types defintions
     *
     * @return A type value conforming to the annotations and the AttributeMetadata provided.
     */
    T getType(DataProviderStrategy strategy,
            AttributeMetadata attributeMetadata,
            ManufacturingContext manufacturingCtx);
}
