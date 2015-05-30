package uk.co.jemos.podam.api;

import uk.co.jemos.podam.common.AbstractConstructorComparator;
import uk.co.jemos.podam.common.AbstractMethodComparator;
import uk.co.jemos.podam.common.AttributeStrategy;

import java.lang.annotation.Annotation;

/**
 * Adds additional methods for a random data provider strategy.
 *
 * Created by tedonema on 23/05/2015.
 *
 * @since 5.4.1
 */
public interface RandomDataProviderStrategy extends DataProviderStrategy {


    /**
     * Bind an annotation to attribute strategy class. If the
     * strategy previously contained a binding for the annotation,
     * the old will be replaced.
     * If you want to implement more sophisticated binding strategy,
     * override this class.
     *
     * @param annotationClass
     *            the annotation class
     * @param strategyClass
     *            the attribute strategy class
     * @return itself
     */
    RandomDataProviderStrategy addOrReplaceAttributeStrategy(
            Class<? extends Annotation> annotationClass,
            Class<AttributeStrategy<?>> strategyClass);

    /**
     * Remove binding of an annotation to attribute strategy
     *
     * @param annotationClass
     *            the annotation class to remove binding
     * @return itself
     */
    RandomDataProviderStrategy removeAttributeStrategy(
            Class<? extends Annotation> annotationClass);

    /**
     * Getter for constructor light comparator.
     *
     * @return current constructor comparator used by strategy
     */
    AbstractConstructorComparator getConstructorLightComparator();

    /**
     * Setter for constructor öight comparator.
     * <p>Default implementations are
     * {@link uk.co.jemos.podam.common.ConstructorHeavyFirstComparator} and
     * {@link uk.co.jemos.podam.common.ConstructorLightFirstComparator}.</p>
     *
     * @param constructorLightComparator constructor comparator to set
     */
    void setConstructorLightComparator(AbstractConstructorComparator constructorLightComparator);

    /**
     * Getter for constructor heavy comparator.
     * @return current constructor comparator used by strategy
     */
    AbstractConstructorComparator getConstructorHeavyComparator();

    /**
     * Setter for constructor heavy comparator.
     * <p>Default implementations are
     * {@link uk.co.jemos.podam.common.ConstructorHeavyFirstComparator} and
     * {@link uk.co.jemos.podam.common.ConstructorLightFirstComparator}.</p>
     *
     * @param constructorHeavyComparator constructor comparator to set
     */
    void setConstructorHeavyComparator(AbstractConstructorComparator constructorHeavyComparator);

    /**
     * Getter for method light comparator.
     * @return current method comparator used by strategy
     */
    AbstractMethodComparator getMethodLightComparator();

    /**
     * Setter for method light comparator.
     * <p>Default implementations is
     * {@link uk.co.jemos.podam.common.MethodHeavyFirstComparator}.</p>
     *
     * @param methodLightComparator method comparator to set
     */
    void setMethodLightComparator(AbstractMethodComparator methodLightComparator);

    /**
     * Getter for method heavy comparator.
     * @return current method comparator used by strategy
     */
    AbstractMethodComparator getMethodHeavyComparator();

    /**
     * Setter for method heavy comparator.
     * <p>Default implementations is
     * {@link uk.co.jemos.podam.common.MethodHeavyFirstComparator}.</p>
     *
     * @param methodHeavyComparator method comparator to set
     */
    void setMethodHeavyComparator(AbstractMethodComparator methodHeavyComparator);
}
