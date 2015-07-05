package uk.co.jemos.podam.typeManufacturers;

import uk.co.jemos.podam.api.AttributeMetadata;
import uk.co.jemos.podam.api.DataProviderStrategy;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

/**
 * Wraps parameters for the Message Channel.
 *
 * Created by tedonema on 28/06/2015.
 *
 * @since 6.0.0.RELEASE
 */
public class TypeManufacturerParamsWrapperForGenericTypes extends TypeManufacturerParamsWrapper {


    /** The Map of type arguments */
    private final Map<String, Type> typeArgumentsMap;

    /** The attribute generic type */
    private final Type attributeGenericType;

    /**
     * Full constructor.
     *
     * @param dataProviderStrategy The DataProviderStrategy
     * @param attributeMetadata    The AttributeMetadata
     * @param typeArgumentsMap The map of type arguments
     * @param attributeGenericType The attribute generic type
     */
    public TypeManufacturerParamsWrapperForGenericTypes(DataProviderStrategy dataProviderStrategy,
                                                        AttributeMetadata attributeMetadata,
                                                        Map<String, Type> typeArgumentsMap,
                                                        Type attributeGenericType) {
        super(dataProviderStrategy, attributeMetadata);

        //safe copy
        this.typeArgumentsMap = new HashMap<String, Type>(typeArgumentsMap);
        this.attributeGenericType = attributeGenericType;
    }

    /**
     * Returns the type arguments map.
     * @return The type arguments map
     */
    public Map<String, Type> getTypeArgumentsMap() {
        return new HashMap<String, Type>(typeArgumentsMap);
    }

    /**
     * Returns the attribute generic type.
     * @return The attribute generic type
     */
    public Type getAttributeGenericType() {
        return attributeGenericType;
    }

}
