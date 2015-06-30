package uk.co.jemos.podam.typeManufacturers.wrappers;

import uk.co.jemos.podam.common.ManufacturingContext;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Wrapper for Array Type Manufacturer.
 *
 * Created by tedonema on 30/06/2015.
 *
 * @since 6.0.0.RELEASE
 */
public class TypeManufacturerParamsWrapperForArray {

    /** The attribute type. */
    private final Class<?> attributeType;

    /** The generic type. */
    private final Type genericType;

    /** The attribute name. */
    private final String attributeName;

    /** The manufacturing context. */
    private final ManufacturingContext manufacturingContext;

    /** The list of annotations. */
    private final List<Annotation> annotations;

    /** The POJO being constructed. */
    private final Object pojo;

    /** The map of generic type arguments. */
    private final Map<String, Type> genericTypeArgumentMap;

    /**
     * Full constructor.
     * @param pojo The POJO being constructed
     * @param attributeType The attribute type
     * @param attributeName The attribute name
     * @param genericType The generic type
     * @param manufacturingContext The manufacturing context
     * @param annotations The list of annotations
     * @param genericTypeArgumentMap The Map of generic types arguments
     */
    public TypeManufacturerParamsWrapperForArray(Object pojo,
                                                 Class<?> attributeType,
                                                 String attributeName,
                                                 Type genericType,
                                                 ManufacturingContext manufacturingContext,
                                                 List<Annotation> annotations,
                                                 Map<String, Type> genericTypeArgumentMap) {
        this.pojo = pojo;
        this.attributeType = attributeType;
        this.genericType = genericType;
        this.attributeName = attributeName;
        this.manufacturingContext = manufacturingContext;
        this.annotations = new ArrayList<Annotation>(annotations);
        this.genericTypeArgumentMap = new HashMap<String, Type>(genericTypeArgumentMap);


    }

    /**
     * @return The attribute type
     */
    public Class<?> getAttributeType() {
        return attributeType;
    }


    /**
     * @return The generic type
     */
    public Type getGenericType() {
        return genericType;
    }

    /**
     * @return The attribute name
     */
    public String getAttributeName() {
        return attributeName;
    }

    /**
     * @return The manufacturing context
     */
    public ManufacturingContext getManufacturingContext() {
        return manufacturingContext;
    }

    /**
     * @return the list of annotations
     */
    public List<Annotation> getAnnotations() {
        return annotations;
    }

    /**
     * @return the POJO
     */
    public Object getPojo() {
        return pojo;
    }

    /**
     * @return The Map of generic type arguments
     */
    public Map<String, Type> getGenericTypeArgumentMap() {
        return genericTypeArgumentMap;
    }


    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("TypeManufacturerParamsWrapperForArray{");
        sb.append("attributeType=").append(attributeType);
        sb.append(", genericType=").append(genericType);
        sb.append(", attributeName='").append(attributeName).append('\'');
        sb.append(", manufacturingContext=").append(manufacturingContext);
        sb.append(", annotations=").append(annotations);
        sb.append(", pojo=").append(pojo);
        sb.append(", genericTypeArgumentMap=").append(genericTypeArgumentMap);
        sb.append('}');
        return sb.toString();
    }
}
