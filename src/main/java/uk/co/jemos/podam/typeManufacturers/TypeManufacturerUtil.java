package uk.co.jemos.podam.typeManufacturers;

import jakarta.validation.Constraint;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.lang.annotation.Annotation;
import java.lang.reflect.*;
import java.util.*;
import java.util.concurrent.atomic.AtomicReference;
import org.apache.commons.lang3.ArrayUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import uk.co.jemos.podam.api.DataProviderStrategy;
import uk.co.jemos.podam.api.ObjectStrategy;
import uk.co.jemos.podam.api.PodamUtils;
import uk.co.jemos.podam.common.*;

/**
 * Type Manufacturer utility class.
 *
 * Created by tedonema on 01/07/2015.
 *
 * @since 6.0.0.RELEASE
 */
public abstract class TypeManufacturerUtil {
    
    /** The application logger */
    private static final Logger LOG = LoggerFactory.getLogger(TypeManufacturerUtil.class);

    /**
     * It returns a {@link AttributeStrategy} if one was specified in
     * annotations, or {@code null} otherwise.
     *
     * @param strategy
     *            The data provider strategy
     * @param annotations
     *            The list of annotations, irrelevant annotations will be removed
     * @param attributeType
     *            Type of attribute expected to be returned
     * @return {@link AttributeStrategy}, if {@link PodamStrategyValue} or bean
     *         validation constraint annotation was found among annotations
     * @throws IllegalAccessException
     *         if attribute strategy cannot be instantiated
     * @throws InstantiationException
     *         if attribute strategy cannot be instantiated
     * @throws SecurityException 
     *         if access security is violated
     * @throws InvocationTargetException
     *         if invocation failed
     * @throws IllegalArgumentException 
     *         if illegal argument provided to a constructor
     */
    public static AttributeStrategy<?> findAttributeStrategy(DataProviderStrategy strategy,
            List<Annotation> annotations, Class<?> attributeType)
            throws InstantiationException, IllegalAccessException, SecurityException, IllegalArgumentException, InvocationTargetException {

        List<Annotation> localAnnotations = new ArrayList<Annotation>(annotations);
        List<Class<? extends Annotation>> annotationsToCheck = new ArrayList<Class<? extends Annotation>>();
        List<Class<? extends Annotation>> constraintAnnotationsWithoutRegisteredStrategy = new ArrayList<Class<? extends Annotation>>();
        Iterator<Annotation> localAnnotationsIter = localAnnotations.iterator();
        while (localAnnotationsIter.hasNext()) {
            Annotation annotation = localAnnotationsIter.next();
            if (annotation instanceof PodamStrategyValue) {
                PodamStrategyValue strategyAnnotation = (PodamStrategyValue) annotation;
                return strategyAnnotation.value().newInstance();
            }

            /* Podam annotation is present, this will be handled later by type manufacturers */
            if (annotation.annotationType().getAnnotation(PodamAnnotation.class) != null) {
                return null;
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

            AttributeStrategy<?> attrStrategy = strategy.getStrategyForAnnotation(annotationClass);
            if (null != attrStrategy) {
                return attrStrategy;
            } else {
                for (Class<?> iface : annotationClass.getInterfaces()) {
                    if (Annotation.class.isAssignableFrom(iface)) {
                        @SuppressWarnings("unchecked")
                        Class<? extends Annotation> tmp = (Class<? extends Annotation>) iface;
                        annotationsToCheck.add(tmp);
                    }
                }
            }

            if (annotation.annotationType().getAnnotation(Constraint.class) != null) {
                if (annotation instanceof NotNull ||
                        annotation instanceof NotBlank ||
                        annotation instanceof NotEmpty ||
                        annotation.annotationType().getName().equals("org.hibernate.validator.constraints.NotEmpty") ||
                        annotation.annotationType().getName().equals("org.hibernate.validator.constraints.NotBlank")) {
					/* We don't need to do anything for NotNull constraint */
                    localAnnotationsIter.remove();
                } else if (!NotNull.class.getPackage().equals(annotationClass.getPackage())) {
                    constraintAnnotationsWithoutRegisteredStrategy.add(annotationClass);
                }
            } else {
                localAnnotationsIter.remove();
            }
        }

        Iterator<Class<? extends Annotation>> annotationsToCheckIter = annotationsToCheck.iterator();
        while (annotationsToCheckIter.hasNext()) {
            Class<? extends Annotation> annotationClass = annotationsToCheckIter.next();
            AttributeStrategy<?> attrStrategy = strategy.getStrategyForAnnotation(annotationClass);
            if (null != attrStrategy) {
                return attrStrategy;
            } else {
                for (Class<?> iface : annotationClass.getInterfaces()) {
                    if (Annotation.class.isAssignableFrom(iface)) {
                        @SuppressWarnings("unchecked")
                        Class<? extends Annotation> tmp = (Class<? extends Annotation>) iface;
                        annotationsToCheck.add(tmp);
                    }
                }
            }
        }

        for (Class<? extends Annotation> constraintAnnotationWithoutRegisteredStrategy : constraintAnnotationsWithoutRegisteredStrategy) {
            /* This message is logged only when no applicable strategy is found for given annotation - neither for
             * the annotation itself nor for any interface it implements. */
            LOG.warn("Please, register AttributeStrategy for custom "
                + "constraint {}, in DataProviderStrategy! Value "
                + "will be left to null", constraintAnnotationWithoutRegisteredStrategy);
        }

        AttributeStrategy<?> retValue = null;
        if (!localAnnotations.isEmpty()
                && !Collection.class.isAssignableFrom(attributeType)
                && !Map.class.isAssignableFrom(attributeType)
                && !attributeType.isArray()) {

            retValue = new BeanValidationStrategy(attributeType);
        }

        return retValue;
    }

    /**
     * Finds suitable static constructors for POJO instantiation
     * <p>
     * This method places required and provided types for object creation into a
     * map, which will be used for type mapping.
     * </p>
     *
     * @param factoryClass
     *            Factory class to produce the POJO
     * @param pojoClass
     *            Typed class
     * @return an array of suitable static constructors found
     */
    public static Method[] findSuitableConstructors(final Class<?> factoryClass,
            final Class<?> pojoClass) {

        // If no publicly accessible constructors are available,
        // the best we can do is to find a constructor (e.g.
        // getInstance())

        Method[] declaredMethods = factoryClass.getDeclaredMethods();
        List<Method> constructors = new ArrayList<Method>();

        // A candidate factory method is a method which returns the
        // Class type
        for (Method candidateConstructor : declaredMethods) {

            if (candidateConstructor.getReturnType().equals(pojoClass)) {
                if (Modifier.isStatic(candidateConstructor.getModifiers())
                        || !factoryClass.equals(pojoClass)) {
                    constructors.add(candidateConstructor);
                }
            }
        }

        return constructors.toArray(new Method[constructors.size()]);
    }

    /**
     * Searches for annotation with information about collection/map size
     * and filling strategies
     *
     * @param strategy
     *        a data provider strategy
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
                        elementStrategyHolder.setValue(attributeStrategy.newInstance());
                    }
                }
                if (null != keyStrategyHolder) {

                    Class<? extends AttributeStrategy<?>> attributeStrategy
                            = collectionAnnotation.mapKeyStrategy();
                    if (null != attributeStrategy) {
                        keyStrategyHolder.setValue(attributeStrategy.newInstance());
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
     * Utility to merge actual types with supplied array of generic type
     * substitutions
     *
     * @param attributeType
     *            actual type of object
     * @param genericAttributeType
     *            generic type of object
     * @param suppliedTypes
     *            an array of supplied types for generic type substitution
     * @param manufacturingCtx
     *            a context with a map relating the generic class arguments ("&lt;T, V&gt;" for
     *            example) with their actual types
     * @return An array of merged actual and supplied types with generic types
     *            resolved
     */
    public static Type[] mergeActualAndSuppliedGenericTypes(
            Class<?> attributeType, Type genericAttributeType, Type[] suppliedTypes,
            ManufacturingContext manufacturingCtx) {

        TypeVariable<?>[] actualTypes = attributeType.getTypeParameters();

        if (actualTypes.length <= suppliedTypes.length) {
            return suppliedTypes;
        }

        Type[] genericTypes = null;
        if (genericAttributeType instanceof ParameterizedType) {
            ParameterizedType paramType = (ParameterizedType) genericAttributeType;
            genericTypes = paramType.getActualTypeArguments();
        } else if (genericAttributeType instanceof WildcardType) {
            WildcardType wildcardType = (WildcardType) genericAttributeType;
            genericTypes = wildcardType.getLowerBounds();
            if (ArrayUtils.isEmpty(genericTypes)) {
                genericTypes = wildcardType.getUpperBounds();
            }
        }

        List<Type> resolvedTypes = new ArrayList<Type>();
        List<Type> substitutionTypes = new ArrayList<Type>(Arrays.asList(suppliedTypes));
        for (int i = 0; i < actualTypes.length; i++) {

            Type type = null;
            if (actualTypes[i] instanceof TypeVariable) {
                type = manufacturingCtx.resolveType(((TypeVariable<?>)actualTypes[i]).getName());
            } else if (actualTypes[i] instanceof WildcardType) {
                AtomicReference<Type[]> methodGenericTypeArgs
                        = new AtomicReference<Type[]>(PodamConstants.NO_TYPES);
                type = TypeManufacturerUtil.resolveGenericParameter(actualTypes[i], manufacturingCtx,
                        methodGenericTypeArgs);
            }

            if ((type == null) && (genericTypes != null)) {
                if (genericTypes[i] instanceof Class) {
                    type = genericTypes[i];
                } else if (genericTypes[i] instanceof WildcardType) {
                    AtomicReference<Type[]> methodGenericTypeArgs
                            = new AtomicReference<Type[]>(PodamConstants.NO_TYPES);
                    type = resolveGenericParameter(genericTypes[i], manufacturingCtx,
                            methodGenericTypeArgs);
                } else if (genericTypes[i] instanceof ParameterizedType) {
                    type = genericTypes[i];
                } else {
                    LOG.debug("Skipping type {} {}", actualTypes[i], genericTypes[i]);
                }
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
        return ArrayUtils.addAll(resolved, supplied);
    }

    /**
     * It resolves generic parameter type
     *
     *
     * @param paramType
     *            The generic parameter type
     * @param manufacturingCtx
     *            A manufacturing context with a map of resolved types
     * @param methodGenericTypeArgs
     *            Return value posible generic types of the generic parameter
     *            type
     * @return value for class representing the generic parameter type
     */
    public static Class<?> resolveGenericParameter(Type paramType,
                                             ManufacturingContext manufacturingCtx,
                                             AtomicReference<Type[]> methodGenericTypeArgs) {

        Class<?> parameterType = null;

        //Safe copy
        manufacturingCtx.cloneTypeArgsMap();

        methodGenericTypeArgs.set(PodamConstants.NO_TYPES);
        if (paramType instanceof Class) {
            parameterType = (Class<?>) paramType;
        } else if (paramType instanceof TypeVariable<?>) {
            final TypeVariable<?> typeVariable = (TypeVariable<?>) paramType;
            final Type type = manufacturingCtx.resolveType(typeVariable.getName());
            if (type != null) {
                parameterType = resolveGenericParameter(type, manufacturingCtx,
                        methodGenericTypeArgs);
            }
        } else if (paramType instanceof ParameterizedType) {
            ParameterizedType pType = (ParameterizedType) paramType;
            parameterType = (Class<?>) pType.getRawType();
            Type[] actualTypeArgs = pType.getActualTypeArguments();
            if (!manufacturingCtx.isTypeArgsEmpty()) {
                for (int i = 0; i < actualTypeArgs.length; i++) {
                    Class<?> tmp = resolveGenericParameter(actualTypeArgs[i],
                        manufacturingCtx, methodGenericTypeArgs);
                    if (tmp != actualTypeArgs[i]) {
                        /* If actual type argument has its own arguments,
                         * we will loose them now, so we will leave type unresolved
                         * until lower levels of type resolution */
                        if (ArrayUtils.isEmpty(methodGenericTypeArgs.get())) {
                            actualTypeArgs[i] = tmp;
                        }
                    }
                }
            }
            methodGenericTypeArgs.set(actualTypeArgs);
        } else if (paramType instanceof WildcardType) {
            WildcardType wType = (WildcardType) paramType;
            Type[] bounds = wType.getLowerBounds();
            String msg;
            if (ArrayUtils.isNotEmpty(bounds)) {
                msg = "Lower bounds:";
            } else {
                bounds = wType.getUpperBounds();
                msg = "Upper bounds:";
            }
            if (ArrayUtils.isNotEmpty(bounds)) {
                LOG.debug(msg + Arrays.toString(bounds));
                parameterType = resolveGenericParameter(bounds[0], manufacturingCtx,
                        methodGenericTypeArgs);
            }
        }

        if (parameterType == null) {
            LOG.warn("Unrecognized type {}. Will use Object instead",
                    paramType);
            parameterType = Object.class;
        }
        manufacturingCtx.restoreTypeArgsMap();
        return parameterType;
    }


    /**
     * It retrieves the value for the {@link PodamStrategyValue} annotation with
     * which the attribute was annotated
     *
     * @param attributeType
     *            The attribute type, used for type checking
     * @param annotations
     *            Annotations attached to the attribute
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
            List<Annotation> annotations,
            AttributeStrategy<?> attributeStrategy)
            throws IllegalArgumentException {

        if (null == attributeStrategy) {
            return null;
        }

        Object retValue = attributeStrategy.getValue(attributeType, annotations);
        if (retValue != null) {

            Class<?> desiredType = attributeType.isPrimitive() ?
                    PodamUtils.primitiveToBoxedType(attributeType) : attributeType;
            if (!desiredType.isAssignableFrom(retValue.getClass())) {
                String errMsg = "The AttributeStrategy "
                        + attributeStrategy.getClass().getName()
                        + " produced value of type "
                        + retValue.getClass().getName()
                        + " incompatible with attribute type "
                        + attributeType.getName();
                throw new IllegalArgumentException(errMsg);
            } else {
                LOG.debug("The parameter {} will be filled using the following strategy {}",
                        attributeType, attributeStrategy);
            }
        }

        return retValue;

    }

}
