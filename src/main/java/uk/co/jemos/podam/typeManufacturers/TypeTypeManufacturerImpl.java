package uk.co.jemos.podam.typeManufacturers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import uk.co.jemos.podam.api.AttributeMetadata;
import uk.co.jemos.podam.api.DataProviderStrategy;
import uk.co.jemos.podam.common.PodamConstants;

import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.Arrays;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;

/**
 * Default byte type manufacturer.
 *
 * Created by tedonema on 17/05/2015.
 *
 * @since 6.0.0.RELEASE
 */
public class TypeTypeManufacturerImpl extends AbstractTypeManufacturer<Object> {

    /** The application logger */
    private static final Logger LOG = LoggerFactory.getLogger(TypeTypeManufacturerImpl.class);

    @Override
    public Object getType(DataProviderStrategy strategy,
            AttributeMetadata attributeMetadata,
            Map<String, Type> genericTypesArgumentsMap) {

        Type genericAttributeType = attributeMetadata.getAttributeGenericType();
        AtomicReference<Type[]> elementGenericTypeArgs
                = new AtomicReference<Type[]>(PodamConstants.NO_TYPES);
        TypeManufacturerUtil.resolveGenericParameter(genericAttributeType,
                genericTypesArgumentsMap, elementGenericTypeArgs);

        Type paremeterType;
        if (elementGenericTypeArgs.get().length > 0) {

            paremeterType = elementGenericTypeArgs.get()[0];
        } else {
            TypeVariable<?>[] attrTypeParams = attributeMetadata.getAttributeType().getTypeParameters();
            if (attrTypeParams.length > 0) {
                paremeterType = attrTypeParams[0];
            } else {
                LOG.error("{} is missing generic type argument, supplied {} {}",
                        genericAttributeType, genericTypesArgumentsMap,
                        Arrays.toString(attributeMetadata.getAttrGenericArgs()));
                return null;
            }
        }

        return TypeManufacturerUtil.resolveGenericParameter(paremeterType,
                genericTypesArgumentsMap, elementGenericTypeArgs);
    }
}
