package uk.co.jemos.podam.typeManufacturers;

import uk.co.jemos.podam.api.AttributeMetadata;
import uk.co.jemos.podam.api.DataProviderStrategy;
import uk.co.jemos.podam.common.ManufacturingContext;
import uk.co.jemos.podam.common.PodamConstants;

import java.lang.reflect.Type;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;

/**
 * Default generic type type manufacturer.
 *
 * Created by tedonema on 17/05/2015.
 *
 * @since 6.0.0.RELEASE
 */
public class TypeTypeManufacturerImpl extends AbstractTypeManufacturer<Object> {

    @Override
    public Object getType(DataProviderStrategy strategy,
            AttributeMetadata attributeMetadata,
            ManufacturingContext manufacturingCtx) {

        Type genericAttributeType = attributeMetadata.getAttributeGenericType();
        AtomicReference<Type[]> elementGenericTypeArgs
                = new AtomicReference<Type[]>(PodamConstants.NO_TYPES);
        TypeManufacturerUtil.resolveGenericParameter(genericAttributeType,
                manufacturingCtx, elementGenericTypeArgs);

        if (elementGenericTypeArgs.get().length > 0) {

            Type resolvedType = elementGenericTypeArgs.get()[0];
            return TypeManufacturerUtil.resolveGenericParameter(resolvedType,
                    manufacturingCtx, elementGenericTypeArgs);
        } else {

            return attributeMetadata.getAttributeType();
        }

    }
}
