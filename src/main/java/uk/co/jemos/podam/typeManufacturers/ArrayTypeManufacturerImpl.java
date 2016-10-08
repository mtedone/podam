package uk.co.jemos.podam.typeManufacturers;

import uk.co.jemos.podam.api.AttributeMetadata;
import uk.co.jemos.podam.api.DataProviderStrategy;
import uk.co.jemos.podam.common.AttributeStrategy;
import uk.co.jemos.podam.common.PodamConstants;
import uk.co.jemos.podam.exceptions.PodamMockeryException;

import java.lang.reflect.Array;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;

import javax.xml.ws.Holder;

/**
 * Default collection type manufacturer.
 *
 * @since 7.0.0.RELEASE
 */
public class ArrayTypeManufacturerImpl extends AbstractTypeManufacturer<Cloneable> {

    @Override
    public Cloneable getType(DataProviderStrategy strategy,
            AttributeMetadata attributeMetadata,
            Map<String, Type> genericTypesArgumentsMap) {

        Class<?> attrType = attributeMetadata.getAttributeType();

        if (attrType.isArray()) {

            Type genericType = attributeMetadata.getAttributeGenericType();
            Class<?> componentType = null;
            AtomicReference<Type[]> genericTypeArgs = new AtomicReference<Type[]>(
                    PodamConstants.NO_TYPES);
            if (genericType instanceof GenericArrayType) {
                Type genericComponentType = ((GenericArrayType) genericType).getGenericComponentType();
                if (genericComponentType instanceof TypeVariable) {
                    TypeVariable<?> componentTypeVariable
                            = (TypeVariable<?>) genericComponentType;
                    final Type resolvedType = genericTypesArgumentsMap.get(
                            componentTypeVariable.getName());
                    componentType
                            = TypeManufacturerUtil.resolveGenericParameter(
                                    resolvedType, genericTypesArgumentsMap,
                                    genericTypeArgs);
                }
            }

            if (componentType == null) {
                componentType = attrType.getComponentType();
            }

            // If the user defined a strategy to fill the collection elements,
            // we use it
            Holder<AttributeStrategy<?>> elementStrategyHolder
                    = new Holder<AttributeStrategy<?>>();
            Holder<AttributeStrategy<?>> keyStrategyHolder = null;
            Integer nbrElements;
            try {
                nbrElements = TypeManufacturerUtil.findCollectionSize(strategy,
                        attributeMetadata.getAttributeAnnotations(), attrType,
                        elementStrategyHolder, keyStrategyHolder);
            } catch (InstantiationException e) {
                throw new PodamMockeryException("Instantiation failed", e);
            } catch (IllegalAccessException e) {
                throw new PodamMockeryException("Instantiation failed", e);
            }

            return (Cloneable) Array.newInstance(componentType, nbrElements);
        } else {

            return null;
        }
    }
}
