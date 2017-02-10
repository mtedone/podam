package uk.co.jemos.podam.test.unit.steps;

import net.thucydides.core.annotations.Step;
import uk.co.jemos.podam.api.*;
import uk.co.jemos.podam.common.AttributeStrategy;
import uk.co.jemos.podam.test.dto.annotations.PojoSpecific;
import uk.co.jemos.podam.test.strategies.CustomRandomDataProviderStrategy;
import uk.co.jemos.podam.test.unit.features.extensions.NonEJBClassInfoStrategy;
import uk.co.jemos.podam.test.unit.features.externalFactory.TestExternalFactory;
import uk.co.jemos.podam.test.unit.features.inheritance.CustomDataProviderStrategy;
import uk.co.jemos.podam.test.unit.features.inheritance.TrackingExternalFactory;
import uk.co.jemos.podam.test.unit.features.xmlTypes.XmlTypesExternalFactory;
import uk.co.jemos.podam.typeManufacturers.IntTypeManufacturerImpl;
import uk.co.jemos.podam.typeManufacturers.StringTypeManufacturerImpl;
import uk.co.jemos.podam.typeManufacturers.TypeManufacturer;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.sql.Timestamp;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by tedonema on 27/05/2015.
 */
public class PodamFactorySteps {

    private AbstractClassInfoStrategy classInfoStrategy = new AbstractClassInfoStrategy() {};

    @Step("Given a standard Podam Factory")
    public PodamFactory givenAStandardPodamFactory() {
        return new PodamFactoryImpl();
    }

    @Step("Given a Podam Factory with a Custom String Manufacturer")
    public PodamFactory givenAPodamWithACustomStringTypeManufacturer() {

        TypeManufacturer<String> manufacturer = new StringTypeManufacturerImpl() {

            @Override
            public String getStringValue(AttributeMetadata attributeMetadata) {

                if (attributeMetadata.getPojoClass() == PojoSpecific.class) {
                    return "specific";
                } else {
                    return "classic";
                }
            }
        };

        return givenAPodamWithACustomTypeManufacturer(String.class, manufacturer);
    }

    @Step("Given a Podam Factory with a Custom Integer Manufacturer")
    public PodamFactory givenAPodamWithACustomIntegerTypeManufacturer() {

        TypeManufacturer<Integer> manufacturer = new IntTypeManufacturerImpl() {

            @Override
            public Integer getInteger(AttributeMetadata attributeMetadata) {

                if (attributeMetadata.getPojoClass() == Timestamp.class) {
                    return PodamUtils.getIntegerInRange(0, 999999999);
                } else {
                    return super.getInteger(attributeMetadata);
                }
            }
        };

        return givenAPodamWithACustomTypeManufacturer(int.class, manufacturer);
    }

    @Step("Given a Podam Factory with an Input Stream Manufacturer")
    public PodamFactory givenAPodamWithAInputStreamTypeManufacturer() {

        TypeManufacturer<InputStream> manufacturer = new TypeManufacturer<InputStream>() {

            @Override
            public InputStream getType(DataProviderStrategy strategy,
                    AttributeMetadata attributeMetadata,
                    Map<String, Type> genericTypesArgumentsMap) {

                byte[] data = new byte[] { 0x0, 0x2, 0x4 };
                return new ByteArrayInputStream(data);
            }
        };

        return givenAPodamWithACustomTypeManufacturer(InputStream.class, manufacturer);
    }

    @Step("Given a Podam Factory with a Custom Type Manufacturer {0}")
    public <T> PodamFactory givenAPodamWithACustomTypeManufacturer(
            Class<T> type, TypeManufacturer<T> typeManufacturer) {

        PodamFactory factory = new PodamFactoryImpl();
        factory.getStrategy().addOrReplaceTypeManufacturer(type, typeManufacturer);
        return factory;
    }

    @Step("Given a ClassInfo for class {0} with empty attributes")
    public ClassInfo givenAClassInfoForPojoWithNoAttributes(Class<?> pojoClass, List<ClassAttribute> attributes) {
        return new ClassInfo(pojoClass, attributes);
    }

    @Step("Given a Set of annotations to be excluded")
    public Set<Class<? extends Annotation>> givenASetOfExcludedAnnotationsToBeExcluded(Class<? extends Annotation>... excludedAnnotations) {
        Set<Class<? extends Annotation>> retValue = new HashSet<Class<? extends Annotation>>();
        for (int i = 0; i < excludedAnnotations.length; i++) {
            retValue.add(excludedAnnotations[i]);
        }
        return retValue;
    }

    @Step("Given a ClassInfo with excluded annotations and excluded fields")
    public ClassInfo givenAClassInfoForPojoWithWithExcludedAnnotationsAndFields(
            Class<?> pojoClass, Set<Class<? extends Annotation>> excludeAnnotations, Set<String> excludeFields) {

        ClassAttributeApprover nullApprover = null;

        return classInfoStrategy.getClassInfo(pojoClass, excludeAnnotations,
                excludeFields, nullApprover,
                Collections.<Method>emptySet());

    }

    @Step("Given a set of excluded fields")
    public Set<String> givenASetOfExcludedFields(String...excludedFields) {

        Set<String> retValue = new HashSet<String>();
        for (int i = 0; i < excludedFields.length; i++) {
            retValue.add(excludedFields[i]);
        }
        return retValue;
    }

    @Step("Given an external factory")
    public PodamFactory givenAnExternalFactory() {
        return new TestExternalFactory();
    }

    @Step("Given a Podam Factory with external factory {0}")
    public PodamFactory givenAdPodamFactoryWithExternalFactory(PodamFactory externalFactory) {
        return new PodamFactoryImpl(externalFactory);
    }

    @Step("Given a Tracking External Factory")
    public TrackingExternalFactory givenATrackingExternalFactory() {
        return new TrackingExternalFactory();
    }

    @Step("Given a Custom Data Provider Strategy")
    public CustomDataProviderStrategy givenACustomDataProviderStrategy() {
        return new CustomDataProviderStrategy();
    }

    @Step("Given a Podam Factory with external factory and custom data provider strategy")
    public PodamFactory givenAPodamFactoryWithExternalFactoryAndCustomStrategy(PodamFactory externalFactory,
                                                                               DataProviderStrategy customDataProviderStrategy) {
        return new PodamFactoryImpl(externalFactory, customDataProviderStrategy);
    }

    @Step("Given a Class Info Strategy which approves only attributes belonging to NonEJBPojo")
    public NonEJBClassInfoStrategy givenANonEJBClassInfoStrategy() {
        return new NonEJBClassInfoStrategy();
    }

    @Step("Given a Podam Factory with custom Class Info Strategy")
    public PodamFactory givenAPodamFactoryWithCustomClassInfoStrategy(ClassInfoStrategy classInfoStrategy) {
        return new PodamFactoryImpl().setClassStrategy(classInfoStrategy);
    }

    @Step("Given a Random Data Provider Strategy")
    public DataProviderStrategy givenARandomDataProviderStrategy() {
        return new RandomDataProviderStrategyImpl();
    }

    @Step("Given a Podam Factory with custom data provider strategy")
    public PodamFactory givenAPodamFactoryWithCustomDataProviderStrategy(DataProviderStrategy strategy) {
        return new PodamFactoryImpl(strategy);
    }

    @Step("Given a Custom Random Data Provider Strategy")
    public CustomRandomDataProviderStrategy givenACustomRandomDataProviderStrategy() {
        return new CustomRandomDataProviderStrategy();
    }

    @Step("Given a Podam Factory with custom strategy {1} for annotation {0}")
    public PodamFactory givenAPodamFactoryWithCustomStrategy(Class<? extends Annotation> annotation, AttributeStrategy<?> strategy) {

        PodamFactory factory = new PodamFactoryImpl();
        ((RandomDataProviderStrategy)factory.getStrategy()).addOrReplaceAttributeStrategy(annotation, strategy);
        return factory;
    }

    @Step("Remove a custom strategy {1} from a Podam Factory")
    public PodamFactory removeCustomStrategy(PodamFactory podamFactory, Class<? extends Annotation> annotation) {

        PodamFactory factory = new PodamFactoryImpl();
        ((RandomDataProviderStrategy)factory.getStrategy()).removeAttributeStrategy(annotation);
        return factory;
    }

    @Step("Given a Podam Factory with Defined Factory {1} for an Abstract Class {0}")
    public PodamFactory givenAPodamFactoryWithDefinedFactoryForAnAbstractClass(Class<?> abstractClass, Class<?> factoryClass) {

        PodamFactory factory = new PodamFactoryImpl();
        ((RandomDataProviderStrategy)factory.getStrategy()).addOrReplaceFactory(abstractClass, factoryClass);
        return factory;
    }

    @Step("Remove a Defined Factory {1} for an Abstract Class from a Podam Factory")
    public PodamFactory removeADefinedFactoryForAnAbstractClassFromAPodamFactory(PodamFactory factory, Class<?> factoryClass) {

        ((RandomDataProviderStrategy)factory.getStrategy()).removeFactory(factoryClass);
        return factory;
    }

    @Step("Given a Javax Validator")
    public Validator givenAJavaxValidator() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        return factory.getValidator();
    }

    @Step("Given a Podam factory with XML Types external factory")
    public PodamFactory givenAPodamFactoryWithXmlTypesExternalFactory() {

        PodamFactory factory = new PodamFactoryImpl();
        PodamFactory externalFactory = new XmlTypesExternalFactory();
        factory.setExternalFactory(externalFactory);
        return factory;
    }

    @Step("Given an AttributeMetadata object")
    public AttributeMetadata givenAnAttributeMetadata(
            Class<?> pojoClass, Object pojoInstance, Class<?> pojoType) {

        if (null == pojoClass) {
            throw new IllegalArgumentException("pojoClass cannot be null");
        }

        String attributeName = null;
        Class<?> realAttributeType = pojoType;
        Type realGenericType = pojoType;
        Type[] genericTypeArgs = new Type[0];
        List<Annotation> annotations = Collections.emptyList();
        AttributeMetadata attributeMetadata = new AttributeMetadata(
                attributeName, realAttributeType, realGenericType,
                genericTypeArgs, annotations, pojoClass, pojoInstance);

        return attributeMetadata;
    }

    @Step("Given an Attribute Meta Data object for Enums")
    public AttributeMetadata givenAnAttributeMetadataForEnums(
            Class<?> pojoClass, Object pojoInstance) {
        if (null == pojoClass) {
            throw new IllegalArgumentException("pojoClass cannot be null");
        }

        String attributeName = null;
        Class<?> realAttributeType = pojoClass;
        Type realGenericType = pojoClass;
        Type[] genericTypeArgs = new Type[0];
        List<Annotation> annotations = Collections.emptyList();
        AttributeMetadata attributeMetadata = new AttributeMetadata(
                attributeName, realAttributeType, realGenericType,
                genericTypeArgs, annotations, pojoClass, pojoInstance);

        return attributeMetadata;
    }

    @Step("Given a Random Data Provider Strategy with memoization set to true")
    public DataProviderStrategy givenADataProviderStrategyWithMemoizationSetToTrue() {

        DataProviderStrategy strategy = new RandomDataProviderStrategyImpl();
        strategy.setMemoization(true);
        return strategy;
    }

    @Step("Given a Standard Podam Factory with memoization enabled")
    public PodamFactory givenAStandardPodamFactoryWithMemoizationEnabled() {

        PodamFactory podamFactory = givenAStandardPodamFactory();
        DataProviderStrategy strategyWithMemoization = givenADataProviderStrategyWithMemoizationSetToTrue();
        podamFactory.setStrategy(strategyWithMemoization);
        return podamFactory;

    }
}
