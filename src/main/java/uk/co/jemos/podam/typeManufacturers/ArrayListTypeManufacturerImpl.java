package uk.co.jemos.podam.typeManufacturers;

import uk.co.jemos.podam.api.AttributeMetadata;
import uk.co.jemos.podam.api.DataProviderStrategy;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Default collection type manufacturer.
 *
 * @since 7.0.5.RELEASE
 */
public class ArrayListTypeManufacturerImpl extends AbstractTypeManufacturer<List<Object>> {
    @Override
    public List<Object> getType(DataProviderStrategy strategy, AttributeMetadata attributeMetadata, Map<String, Type> genericTypesArgumentsMap) {
        Class<?> collectionType = attributeMetadata.getAttributeType();
        List<Object> retValue = null;
        if (collectionType.isAssignableFrom(ArrayList.class)) {
            retValue = new ArrayList<Object>();
        }
        return retValue;
    }
}
