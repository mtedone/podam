package uk.co.jemos.podam.typeManufacturers;

import uk.co.jemos.podam.api.AttributeMetadata;
import uk.co.jemos.podam.api.DataProviderStrategy;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

/**
 * Default collection type manufacturer.
 *
 * @since 7.0.0.RELEASE
 */
public class CollectionTypeManufacturerImpl extends AbstractTypeManufacturer<Collection<Object>> {

    @Override
    public Collection<Object> getType(DataProviderStrategy strategy,
            AttributeMetadata attributeMetadata,
            Map<String, Type> genericTypesArgumentsMap) {

        Class<?> collectionType = attributeMetadata.getAttributeType();
        Collection<Object> retValue = null;

        // Default list and set are ArrayList and HashSet. If users
        // wants a particular collection flavour they have to initialise
        // the collection
        if (Queue.class.isAssignableFrom(collectionType)) {
            if (collectionType.isAssignableFrom(LinkedList.class)) {
                retValue = new LinkedList<Object>();
            }
        } else if (Set.class.isAssignableFrom(collectionType)) {
            if (collectionType.isAssignableFrom(HashSet.class)) {
                retValue = new HashSet<Object>();
            }
        } else {
            if (collectionType.isAssignableFrom(ArrayList.class)) {
                retValue = new ArrayList<Object>();
            }
        }
        return retValue;
    }
}
