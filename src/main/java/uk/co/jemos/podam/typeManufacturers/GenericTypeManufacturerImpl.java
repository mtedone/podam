package uk.co.jemos.podam.typeManufacturers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import uk.co.jemos.podam.api.AttributeMetadata;
import uk.co.jemos.podam.common.PodamConstants;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
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
public class GenericTypeManufacturerImpl extends AbstractTypeManufacturer {

    /** The application logger */
    private static final Logger LOG = LoggerFactory.getLogger(GenericTypeManufacturerImpl.class);



    @Override
    public <T extends TypeManufacturerParamsWrapper> Object getType(T wrapper) {
        super.checkWrapperIsValid(wrapper);

        TypeManufacturerParamsWrapper localWrapper = (TypeManufacturerParamsWrapper) wrapper;

        AttributeMetadata attributeMetadata = localWrapper.getAttributeMetadata();

        Type[] genericArgs = attributeMetadata.getAttrGenericArgs();

        if (null == genericArgs) {
            throw new IllegalArgumentException("For generic type arguments, "
                    + "the attribute metadata should contain a non null Type[]");

        }

        Type genericAttributeType = attributeMetadata.getAttributeGenericType();

        Class<?> attributeType = attributeMetadata.getAttributeType();

        Type paremeterType = null;
        if (genericAttributeType instanceof ParameterizedType) {
            ParameterizedType parametrized =  (ParameterizedType) genericAttributeType;
            Type[] arguments = parametrized.getActualTypeArguments();
            if (arguments.length > 0) {
                paremeterType = arguments[0];
            }
        } else if (attributeType.getTypeParameters().length > 0) {
            paremeterType = attributeType.getTypeParameters()[0];
        }

        Map<String, Type> typeArgumentsMap = localWrapper.getTypeArgumentsMap();

        if (paremeterType != null) {

            if (null == typeArgumentsMap) {
                throw new IllegalArgumentException("The type arguments map in the wrapper cannot be null");
            }

            AtomicReference<Type[]> elementGenericTypeArgs
                    = new AtomicReference<Type[]>(PodamConstants.NO_TYPES);
            return TypeManufacturerUtil.resolveGenericParameter(paremeterType,
                    typeArgumentsMap, elementGenericTypeArgs);
        } else {
            LOG.error("{} is missing generic type argument, supplied {} {}",
                    genericAttributeType, typeArgumentsMap,
                    Arrays.toString(genericArgs));
            return null;
        }
    }
}
