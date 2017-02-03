package uk.co.jemos.podam.typeManufacturers;

import uk.co.jemos.podam.api.AttributeMetadata;
import uk.co.jemos.podam.api.DataProviderStrategy;

import java.lang.reflect.Type;
import java.util.LinkedList;
import java.util.Map;

/**
 * Default collection type manufacturer.
 *
 * @since 7.0.5.RELEASE
 */
public class LinkedListTypeManufacturerImpl extends AbstractTypeManufacturer<LinkedList<Object>> {
    @Override
    public LinkedList<Object> getType(DataProviderStrategy strategy, AttributeMetadata attributeMetadata, Map<String, Type> genericTypesArgumentsMap) {
        Class<?> collectionType = attributeMetadata.getAttributeType();
        LinkedList<Object> retValue = null;
        if (collectionType.isAssignableFrom(LinkedList.class)) {
            retValue = new LinkedList<Object>();
        }
        return retValue;
    }
}
