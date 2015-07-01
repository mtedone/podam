package uk.co.jemos.podam.typeManufacturers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import uk.co.jemos.podam.common.PodamConstants;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.lang.reflect.WildcardType;
import java.util.Arrays;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;

/**
 * Type Manufacturer utility class.
 *
 * Created by tedonema on 01/07/2015.
 *
 * @since 6.0.0.RELEASE
 */
public class TypeManufacturerUtil {
    
    /** The application logger */
    private static final Logger LOG = LogManager.getLogger(TypeManufacturerUtil.class);
    
    /** Non instantiable. */
    private TypeManufacturerUtil() {
        throw new AssertionError("Non instantiable");
    }


    /**
     * It resolves generic parameter type
     *
     *
     * @param paramType
     *            The generic parameter type
     * @param typeArgsMap
     *            A map of resolved types
     * @param methodGenericTypeArgs
     *            Return value posible generic types of the generic parameter
     *            type
     * @return value for class representing the generic parameter type
     */
    public static Class<?> resolveGenericParameter(Type paramType,
                                             Map<String, Type> typeArgsMap,
                                             AtomicReference<Type[]> methodGenericTypeArgs) {

        Class<?> parameterType = null;
        methodGenericTypeArgs.set(PodamConstants.NO_TYPES);
        if (paramType instanceof TypeVariable<?>) {
            final TypeVariable<?> typeVariable = (TypeVariable<?>) paramType;
            final Type type = typeArgsMap.get(typeVariable.getName());
            if (type != null) {
                parameterType = resolveGenericParameter(type, typeArgsMap,
                        methodGenericTypeArgs);
            }
        } else if (paramType instanceof ParameterizedType) {
            ParameterizedType pType = (ParameterizedType) paramType;
            parameterType = (Class<?>) pType.getRawType();
            methodGenericTypeArgs.set(pType.getActualTypeArguments());
        } else if (paramType instanceof WildcardType) {
            WildcardType wType = (WildcardType) paramType;
            Type[] bounds = wType.getLowerBounds();
            String msg;
            if (bounds != null && bounds.length > 0) {
                msg = "Lower bounds:";
            } else {
                bounds = wType.getUpperBounds();
                msg = "Upper bounds:";
            }
            if (bounds != null && bounds.length > 0) {
                LOG.debug(msg + Arrays.toString(bounds));
                parameterType = resolveGenericParameter(bounds[0], typeArgsMap,
                        methodGenericTypeArgs);
            }
        } else if (paramType instanceof Class) {
            parameterType = (Class<?>) paramType;
        }

        if (parameterType == null) {
            LOG.warn("Unrecognized type {}. Will use Object instead",
                    paramType);
            parameterType = Object.class;
        }
        return parameterType;
    }
}
