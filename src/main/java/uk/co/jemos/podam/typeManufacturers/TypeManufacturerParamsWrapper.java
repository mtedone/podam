package uk.co.jemos.podam.typeManufacturers;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

import uk.co.jemos.podam.api.AttributeMetadata;
import uk.co.jemos.podam.api.DataProviderStrategy;

/**
 * Wraps parameters for the Message Channel
 *
 * Created by tedonema on 28/06/2015.
 *
 * @since 6.0.0.RELEASE
 */
public class TypeManufacturerParamsWrapper {


    /** The Data DataProviderStrategy. */
    private final DataProviderStrategy dataProviderStrategy;

    /** The AttributeMetadata. */
    private final AttributeMetadata attributeMetadata;

    /** The Map of type arguments */
    private final Map<String, Type> typeArgumentsMap;

    /**
     * Full constructor.
     * @param dataProviderStrategy The DataProviderStrategy
     * @param attributeMetadata The AttributeMetadata
     * @param typeArgumentsMap map with generic types mapped to actual types
     */
    public TypeManufacturerParamsWrapper(DataProviderStrategy dataProviderStrategy,
                                         AttributeMetadata attributeMetadata,
                                         Map<String, Type> typeArgumentsMap) {

        this.dataProviderStrategy = dataProviderStrategy;
        this.attributeMetadata = attributeMetadata;
        // safe copy
        this.typeArgumentsMap = new HashMap<String, Type>(typeArgumentsMap);
    }

    public DataProviderStrategy getDataProviderStrategy() {
        return dataProviderStrategy;
    }

    public AttributeMetadata getAttributeMetadata() {
        return attributeMetadata;
    }

    /**
     * Returns the type arguments map.
     * @return The type arguments map
     */
    public Map<String, Type> getTypeArgumentsMap() {
        return typeArgumentsMap;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("TypeManufacturerParamsWrapper{");
        sb.append("dataProviderStrategy=").append(dataProviderStrategy);
        sb.append(", attributeMetadata=").append(attributeMetadata);
        sb.append(", typeArgumentsMap=").append(typeArgumentsMap);
        sb.append('}');
        return sb.toString();
    }
}
