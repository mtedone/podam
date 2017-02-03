package uk.co.jemos.podam.typeManufacturers;

import uk.co.jemos.podam.api.AttributeMetadata;
import uk.co.jemos.podam.api.DataProviderStrategy;

import java.lang.reflect.Type;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Default collection type manufacturer.
 *
 * @since 7.0.5.RELEASE
 */
public class HashSetTypeManufacturerImpl extends AbstractTypeManufacturer<Set<Object>> {
    @Override
    public Set<Object> getType(DataProviderStrategy strategy, AttributeMetadata attributeMetadata, Map<String, Type> genericTypesArgumentsMap) {
        Class<?> collectionType = attributeMetadata.getAttributeType();
        Set<Object> retValue = null;
        if (collectionType.isAssignableFrom(HashSet.class)) {
            retValue = new HashSet<Object>();
        }
        return retValue;
    }
}
