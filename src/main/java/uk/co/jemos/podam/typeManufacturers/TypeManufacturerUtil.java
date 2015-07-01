package uk.co.jemos.podam.typeManufacturers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import uk.co.jemos.podam.common.PodamConstants;

import java.lang.reflect.*;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicReference;

/**
 * Type Manufacturer utility class.
 *
 * Created by tedonema on 01/07/2015.
 *
 * @since 6.0.0.RELEASE
 */
public final class TypeManufacturerUtil {
    
    /** The application logger */
    private static final Logger LOG = LogManager.getLogger(TypeManufacturerUtil.class);
    
    /** Non instantiable. */
    private TypeManufacturerUtil() {
        throw new AssertionError("Non instantiable");
    }

    /**
     * Given a collection type it returns an instance
     *
     * <ul>
     * <li>The default type for a {@link List} is an {@link ArrayList}</li>
     * <li>The default type for a {@link Queue} is a {@link LinkedList}</li>
     * <li>The default type for a {@link Set} is a {@link HashSet}</li>
     * </ul>
     *
     * @param collectionType
     *            The collection type *
     * @param defaultValue
     *            Default value for the collection, can be null
     * @return an instance of the collection type or null
     */
    public static Collection<? super Object> resolveCollectionType(
            Class<?> collectionType, Collection<? super Object> defaultValue) {

        Collection<? super Object> retValue = null;

        // Default list and set are ArrayList and HashSet. If users
        // wants a particular collection flavour they have to initialise
        // the collection
        if (null != defaultValue &&
                (defaultValue.getClass().getModifiers() & Modifier.PRIVATE) == 0) {
			/* Default collection, which is not immutable */
            retValue = defaultValue;
        } else {
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
            if (null != retValue && null != defaultValue) {
                retValue.addAll(defaultValue);
            }
        }
        return retValue;
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


    /**
     * It manufactures and returns a default instance for each map type
     *
     * <p>
     * The default implementation for a {@link ConcurrentMap} is
     * {@link ConcurrentHashMap}
     * </p>
     *
     * <p>
     * The default implementation for a {@link SortedMap} is a {@link TreeMap}
     * </p>
     *
     * <p>
     * The default Map is none of the above was recognised is a {@link HashMap}
     * </p>
     *
     * @param mapType
     *            The attribute type implementing Map
     * @param defaultValue
     *            Default value for map
     * @return A default instance for each map type or null
     *
     */
    public static Map<? super Object, ? super Object> resolveMapType(
            Class<?> mapType, Map<? super Object, ? super Object> defaultValue) {

        Map<? super Object, ? super Object> retValue = null;

        if (null != defaultValue &&
                (defaultValue.getClass().getModifiers() & Modifier.PRIVATE) == 0) {
			/* Default map, which is not immutable */
            retValue = defaultValue;
        } else {
            if (SortedMap.class.isAssignableFrom(mapType)) {
                if (mapType.isAssignableFrom(TreeMap.class)) {
                    retValue = new TreeMap<Object, Object>();
                }
            } else if (ConcurrentMap.class.isAssignableFrom(mapType)) {
                if (mapType.isAssignableFrom(ConcurrentHashMap.class)) {
                    retValue = new ConcurrentHashMap<Object, Object>();
                }
            } else {
                if (mapType.isAssignableFrom(HashMap.class)) {
                    retValue = new HashMap<Object, Object>();
                }
            }
        }

        return retValue;

    }

}
