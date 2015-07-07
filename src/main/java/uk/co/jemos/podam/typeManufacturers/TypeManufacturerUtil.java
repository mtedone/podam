package uk.co.jemos.podam.typeManufacturers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.integration.core.MessagingTemplate;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import uk.co.jemos.podam.api.AttributeMetadata;
import uk.co.jemos.podam.api.DataProviderStrategy;
import uk.co.jemos.podam.api.ObjectStrategy;
import uk.co.jemos.podam.api.PodamUtils;
import uk.co.jemos.podam.common.*;

import javax.validation.Constraint;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.ws.Holder;
import java.lang.annotation.Annotation;
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
    private static final Logger LOG = LoggerFactory.getLogger(TypeManufacturerUtil.class);
    
    /** Non instantiable. */
    private TypeManufacturerUtil() {
        throw new AssertionError("Non instantiable");
    }

    /**
     * Obtains a type value
     * @param strategy The Data Provider strategy
     * @param channel The Message Channel where to send/receive the message for the required value
     * @param attributeMetadata The AttributeMetadata information
     * @param qualifier The class of the requested type
     * @return The type value
     */
    public static Object getTypeValue(DataProviderStrategy strategy,
                                      MessageChannel channel,
                                      AttributeMetadata attributeMetadata,
                                      String qualifier) {
        Object retValue = null;

        TypeManufacturerParamsWrapper wrapper =
                new TypeManufacturerParamsWrapper(strategy, attributeMetadata);

        Message<? extends Object> message = MessageBuilder.withPayload(wrapper).setHeader(
                PodamConstants.HEADER_NAME, qualifier)
                .build();

        MessagingTemplate template = new MessagingTemplate();
        retValue = template.sendAndReceive(channel, message).getPayload();
        return retValue;
    }

    /**
     * Obtains a type value
     * @param strategy The Data Provider strategy
     * @param channel The Message Channel where to send/receive the message for the required value
     * @param attributeMetadata The AttributeMetadata information
     * @param genericAttributeType The generic attribute type
     *@param qualifier The class of the requested type  @return The type value
     */
    public static Object getTypeValueForGenericTypes(DataProviderStrategy strategy,
                                                     MessageChannel channel,
                                                     AttributeMetadata attributeMetadata,
                                                     Type genericAttributeType,
                                                     Map<String, Type> genericTypesArgumentsMap,
                                                     String qualifier) {
        Object retValue = null;

        TypeManufacturerParamsWrapperForGenericTypes wrapper =
                new TypeManufacturerParamsWrapperForGenericTypes(strategy, attributeMetadata, genericTypesArgumentsMap,
                        genericAttributeType);

        Message<? extends Object> message = MessageBuilder.withPayload(wrapper).setHeader(
                PodamConstants.HEADER_NAME, qualifier)
                .build();

        MessagingTemplate template = new MessagingTemplate();
        retValue = template.sendAndReceive(channel, message).getPayload();
        return retValue;
    }


    /**
     * It returns a {@link AttributeStrategy} if one was specified in
     * annotations, or {@code null} otherwise.
     *
     * @param annotations
     *            The list of annotations
     * @param attributeType
     *            Type of attribute expected to be returned
     * @return {@link AttributeStrategy}, if {@link PodamStrategyValue} or bean
     *         validation constraint annotation was found among annotations
     * @throws IllegalAccessException
     *         if attribute strategy cannot be instantiated
     * @throws InstantiationException
     *         if attribute strategy cannot be instantiated
     */
    public static AttributeStrategy<?> findAttributeStrategy(DataProviderStrategy strategy,
            List<Annotation> annotations, Class<?> attributeType)
            throws InstantiationException, IllegalAccessException {

        List<Annotation> localAnnotations = new ArrayList<Annotation>(annotations);
        Iterator<Annotation> iter = localAnnotations.iterator();
        while (iter.hasNext()) {
            Annotation annotation = iter.next();
            if (annotation instanceof PodamStrategyValue) {
                PodamStrategyValue strategyAnnotation = (PodamStrategyValue) annotation;
                return strategyAnnotation.value().newInstance();
            }

			/* Find real class out of proxy */
            Class<? extends Annotation> annotationClass = annotation.getClass();
            if (Proxy.isProxyClass(annotationClass)) {
                Class<?>[] interfaces = annotationClass.getInterfaces();
                if (interfaces.length == 1) {
                    @SuppressWarnings("unchecked")
                    Class<? extends Annotation> tmp = (Class<? extends Annotation>) interfaces[0];
                    annotationClass = tmp;
                }
            }

            Class<AttributeStrategy<?>> attrStrategyClass;
            if ((attrStrategyClass = strategy.getStrategyForAnnotation(annotationClass)) != null) {
                return attrStrategyClass.newInstance();
            }

            if (annotation.annotationType().getAnnotation(Constraint.class) != null) {
                if (annotation instanceof NotNull) {
					/* We don't need to do anything for NotNull constraint */
                    iter.remove();
                } else if (!NotNull.class.getPackage().equals(annotationClass.getPackage())) {
                    LOG.warn("Please, registrer AttributeStratergy for custom "
                            + "constraint {}, in DataProviderStrategy! Value "
                            + "will be left to null", annotation);
                }
            } else {
                iter.remove();
            }
        }

        AttributeStrategy<?> retValue = null;
        if (!localAnnotations.isEmpty()
                && !Collection.class.isAssignableFrom(attributeType)
                && !Map.class.isAssignableFrom(attributeType)
                && !attributeType.isArray()) {

            retValue = new BeanValidationStrategy(localAnnotations, attributeType);
        }

        return retValue;
    }

    /**
     * Fills type agruments map
     * <p>
     * This method places required and provided types for object creation into a
     * map, which will be used for type mapping.
     * </p>
     *
     * @param typeArgsMap
     *            a map to fill
     * @param pojoClass
     *            Typed class
     * @param genericTypeArgs
     *            Type arguments provided for a generics object by caller
     * @return Array of unused provided generic type arguments
     * @throws IllegalStateException
     *             If number of typed parameters doesn't match number of
     *             provided generic types
     */
    public static Type[] fillTypeArgMap(final Map<String, Type> typeArgsMap,
                                  final Class<?> pojoClass, final Type[] genericTypeArgs) {

        TypeVariable<?>[] array = pojoClass.getTypeParameters();
        List<TypeVariable<?>> typeParameters = new ArrayList<TypeVariable<?>>(Arrays.asList(array));
        Iterator<TypeVariable<?>> iterator = typeParameters.iterator();
		/* Removing types, which are already in typeArgsMap */
        while (iterator.hasNext()) {
            if (typeArgsMap.containsKey(iterator.next().getName())) {
                iterator.remove();
            }
        }

        List<Type> genericTypes = new ArrayList<Type>(Arrays.asList(genericTypeArgs));
        Iterator<Type> iterator2 = genericTypes.iterator();
		/* Removing types, which are type variables */
        while (iterator2.hasNext()) {
            if (iterator2.next() instanceof TypeVariable) {
                iterator2.remove();
            }
        }

        if (typeParameters.size() > genericTypes.size()) {
            String msg = pojoClass.getCanonicalName()
                    + " is missing generic type arguments, expected "
                    + typeParameters + " found "
                    + Arrays.toString(genericTypeArgs);
            throw new IllegalStateException(msg);
        }

        int i;
        for (i = 0; i < typeParameters.size(); i++) {
            typeArgsMap.put(typeParameters.get(i).getName(), genericTypes.get(0));
            genericTypes.remove(0);
        }
        Type[] genericTypeArgsExtra;
        if (genericTypes.size() > 0) {
            genericTypeArgsExtra = genericTypes.toArray(new Type[genericTypes.size()]);
        } else {
            genericTypeArgsExtra = PodamConstants.NO_TYPES;
        }

		/* Adding types, which were specified during inheritance */
        Class<?> clazz = pojoClass;
        while (clazz != null) {
            Type superType = clazz.getGenericSuperclass();
            clazz = clazz.getSuperclass();
            if (superType instanceof ParameterizedType) {
                ParameterizedType paramType = (ParameterizedType) superType;
                Type[] actualParamTypes = paramType.getActualTypeArguments();
                TypeVariable<?>[] paramTypes = clazz.getTypeParameters();
                for (i = 0; i < actualParamTypes.length
                        && i < paramTypes.length; i++) {
                    if (actualParamTypes[i] instanceof Class) {
                        typeArgsMap.put(paramTypes[i].getName(),
                                actualParamTypes[i]);
                    }
                }
            }
        }

        return genericTypeArgsExtra;
    }

    /**
     * Searches for annotation with information about collection/map size
     * and filling strategies
     *
     * @param annotations
     *        a list of annotations to inspect
     * @param collectionElementType
     *        a collection element type
     * @param elementStrategyHolder
     *        a holder to pass found element strategy back to the caller,
     *        can be null
     * @param keyStrategyHolder
     *        a holder to pass found key strategy back to the caller,
     *        can be null
     * @return
     *        A number of element in collection or null, if no annotation was
     *        found
     * @throws InstantiationException
     *        A strategy cannot be instantiated
     * @throws IllegalAccessException
     *        A strategy cannot be instantiated
     */
    public static Integer findCollectionSize( DataProviderStrategy strategy,
                                        List<Annotation> annotations,
                                        Class<?> collectionElementType,
                                        Holder<AttributeStrategy<?>> elementStrategyHolder,
                                        Holder<AttributeStrategy<?>> keyStrategyHolder)
            throws InstantiationException, IllegalAccessException {

        // If the user defined a strategy to fill the collection elements,
        // we use it
        Size size = null;
        for (Annotation annotation : annotations) {
            if (annotation instanceof PodamCollection) {

                PodamCollection collectionAnnotation = (PodamCollection) annotation;
                if (null != elementStrategyHolder) {

                    Class<? extends AttributeStrategy<?>> attributeStrategy
                            = collectionAnnotation.collectionElementStrategy();
                    if (null == attributeStrategy || ObjectStrategy.class.isAssignableFrom(attributeStrategy)) {
                        attributeStrategy = collectionAnnotation.mapElementStrategy();
                    }
                    if (null != attributeStrategy) {
                        elementStrategyHolder.value = attributeStrategy.newInstance();
                    }
                }
                if (null != keyStrategyHolder) {

                    Class<? extends AttributeStrategy<?>> attributeStrategy
                            = collectionAnnotation.mapKeyStrategy();
                    if (null != attributeStrategy) {
                        keyStrategyHolder.value = attributeStrategy.newInstance();
                    }
                }
                return collectionAnnotation.nbrElements();

            } else if (annotation instanceof Size) {

                size = (Size) annotation;
            }
        }

        Integer nbrElements = strategy
                .getNumberOfCollectionElements(collectionElementType);

        if (null != size) {
            if (nbrElements > size.max()) {
                nbrElements = size.max();
            }
            if (nbrElements < size.min()) {
                nbrElements = size.min();
            }
        }

        return nbrElements;
    }

    /**
     * It returns {@code true} if this class is a wrapper class, {@code false}
     * otherwise
     *
     * @param candidateWrapperClass
     *            The class to check
     * @return {@code true} if this class is a wrapper class, {@code false}
     *         otherwise
     */
    public static boolean isWrapper(Class<?> candidateWrapperClass) {

        return candidateWrapperClass.equals(Byte.class) ? true
                : candidateWrapperClass.equals(Boolean.class) ? true
                : candidateWrapperClass.equals(Character.class) ? true
                : candidateWrapperClass.equals(Short.class) ? true
                : candidateWrapperClass
                .equals(Integer.class) ? true
                : candidateWrapperClass
                .equals(Long.class) ? true
                : candidateWrapperClass
                .equals(Float.class) ? true
                : candidateWrapperClass
                .equals(Double.class) ? true
                : false;
    }

    /**
     * Utility to merge actual types with supplied array of generic type
     * substitutions
     *
     * @param actualTypes
     *            an array of types used for field or POJO declaration
     * @param suppliedTypes
     *            an array of supplied types for generic type substitution
     * @param typeArgsMap
     *            a map relating the generic class arguments ("&lt;T, V&gt;" for
     *            example) with their actual types
     * @return An array of merged actual and supplied types with generic types
     *            resolved
     */
    public static Type[] mergeActualAndSuppliedGenericTypes(
            Type[] actualTypes, Type[] suppliedTypes,
            Map<String, Type> typeArgsMap) {

        List<Type> resolvedTypes = new ArrayList<Type>();
        List<Type> substitutionTypes = new ArrayList<Type>(Arrays.asList(suppliedTypes));
        for (int i = 0; i < actualTypes.length; i++) {

            Type type = null;
            if (actualTypes[i] instanceof TypeVariable) {
                type = typeArgsMap.get(((TypeVariable<?>)actualTypes[i]).getName());
            } else if (actualTypes[i] instanceof Class) {
                type = actualTypes[i];
            } else if (actualTypes[i] instanceof WildcardType) {
                AtomicReference<Type[]> methodGenericTypeArgs
                        = new AtomicReference<Type[]>(PodamConstants.NO_TYPES);
                type = TypeManufacturerUtil.resolveGenericParameter(actualTypes[i], typeArgsMap,
                        methodGenericTypeArgs);
            }
            if (type != null) {
                resolvedTypes.add(type);
                if (!substitutionTypes.isEmpty() && substitutionTypes.get(0).equals(type)) {
                    substitutionTypes.remove(0);
                }
            }
        }
        Type[] resolved = resolvedTypes.toArray(new Type[resolvedTypes.size()]);
        Type[] supplied = substitutionTypes.toArray(new Type[substitutionTypes.size()]);
        return mergeTypeArrays(resolved, supplied);
    }

    /**
     * Utility method to merge two arrays
     *
     * @param original
     *            The main array
     * @param extra
     *            The additional array, optionally may be null
     * @return A merged array of original and extra arrays
     */
    public static Type[] mergeTypeArrays(Type[] original, Type[] extra) {

        Type[] merged;

        if (extra != null) {
            merged = new Type[original.length + extra.length];
            System.arraycopy(original, 0, merged, 0, original.length);
            System.arraycopy(extra, 0, merged, original.length, extra.length);
        } else {
            merged = original;
        }

        return merged;
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

        //Safe copy
        Map<String, Type> localMap = new HashMap<String, Type>(typeArgsMap);

        methodGenericTypeArgs.set(PodamConstants.NO_TYPES);
        if (paramType instanceof TypeVariable<?>) {
            final TypeVariable<?> typeVariable = (TypeVariable<?>) paramType;
            final Type type = localMap.get(typeVariable.getName());
            if (type != null) {
                parameterType = resolveGenericParameter(type, localMap,
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
                parameterType = resolveGenericParameter(bounds[0], localMap,
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

    /**
     * It retrieves the value for the {@link PodamStrategyValue} annotation with
     * which the attribute was annotated
     *
     * @param attributeType
     *            The attribute type, used for type checking
     * @param attributeStrategy
     *            The {@link AttributeStrategy} to use
     * @return The value for the {@link PodamStrategyValue} annotation with
     *         which the attribute was annotated
     * @throws IllegalArgumentException
     *             If the type of the data strategy defined for the
     *             {@link PodamStrategyValue} annotation is not assignable to
     *             the annotated attribute. This de facto guarantees type
     *             safety.
     */
    public static Object returnAttributeDataStrategyValue(Class<?> attributeType,
                                                    AttributeStrategy<?> attributeStrategy)
            throws IllegalArgumentException {

        Object retValue = attributeStrategy.getValue();

        if (retValue != null) {
            Class<?> desiredType = attributeType.isPrimitive() ?
                    PodamUtils.primitiveToBoxedType(attributeType) : attributeType;
            if (!desiredType.isAssignableFrom(retValue.getClass())) {
                String errMsg = "The type of the Podam Attribute Strategy is not "
                        + attributeType.getName() + " but "
                        + retValue.getClass().getName()
                        + ". An exception will be thrown.";
                LOG.error(errMsg);
                throw new IllegalArgumentException(errMsg);
            }
        }

        return retValue;

    }

}
