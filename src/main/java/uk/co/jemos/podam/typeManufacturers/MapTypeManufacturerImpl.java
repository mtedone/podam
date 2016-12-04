package uk.co.jemos.podam.typeManufacturers;

import uk.co.jemos.podam.api.AttributeMetadata;
import uk.co.jemos.podam.api.DataProviderStrategy;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Default collection type manufacturer.
 *
 * @since 7.0.0.RELEASE
 */
public class MapTypeManufacturerImpl extends AbstractTypeManufacturer<Map<Object,Object>> {

    @Override
    public Map<Object,Object> getType(DataProviderStrategy strategy,
            AttributeMetadata attributeMetadata,
            Map<String, Type> genericTypesArgumentsMap) {

        Class<?> mapType = attributeMetadata.getAttributeType();
        Map<Object, Object> retValue = null;

        if (mapType.isAssignableFrom(HashMap.class)) {
            // Map
            retValue = new HashMap<Object, Object>();
        } else if (mapType.isAssignableFrom(TreeMap.class)) {
            // SortedMap
            retValue = new TreeMap<Object, Object>();
        } else if (mapType.isAssignableFrom(ConcurrentHashMap.class)) {
            // ConcurrentMap
            retValue = new ConcurrentHashMap<Object, Object>();
        }

        return retValue;
    }
}
