package uk.co.jemos.podam.test.unit.steps;

import net.thucydides.core.annotations.Step;
import org.hibernate.validator.constraints.Email;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import uk.co.jemos.podam.api.*;
import uk.co.jemos.podam.common.AttributeStrategy;
import uk.co.jemos.podam.common.PodamConstants;
import uk.co.jemos.podam.test.dto.annotations.PojoSpecific;
import uk.co.jemos.podam.test.strategies.CustomRandomDataProviderStrategy;
import uk.co.jemos.podam.test.strategies.EmailStrategy;
import uk.co.jemos.podam.test.unit.features.extensions.NonEJBClassInfoStrategy;
import uk.co.jemos.podam.test.unit.features.externalFactory.TestExternalFactory;
import uk.co.jemos.podam.test.unit.features.inheritance.CustomDataProviderStrategy;
import uk.co.jemos.podam.test.unit.features.inheritance.TrackingExternalFactory;
import uk.co.jemos.podam.test.unit.features.xmlTypes.XmlTypesExternalFactory;
import uk.co.jemos.podam.typeManufacturers.TypeManufacturerParamsWrapper;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by tedonema on 27/05/2015.
 */
public class PodamFactorySteps {

    @Step("Given a standard Podam Factory")
    public PodamFactory givenAStandardPodamFactory() {
        return new PodamFactoryImpl();
    }

    @Step("Given a Podam Factory to use as External Factory")
    public PodamFactory givenAPodamExternalFactorytoTestAttributeMetadata() {

        PodamFactory factory = new PodamFactoryImpl(new AbstractRandomDataProviderStrategy() {
            @Override
            public String getStringValue(AttributeMetadata attributeMetadata) {

                if (attributeMetadata.getPojoClass() == PojoSpecific.class) {
                    return "specific";
                } else {
                    return "classic";
                }
            }
        });

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

        return PodamUtils.getClassInfo(pojoClass, excludeAnnotations, excludeFields, nullApprover);

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

    @Step("Given a Podam Factory with external factory")
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

    @Step("Given a Podam Factory with Email strategy")
    public PodamFactory givenAPodamFactoryWithEmailStrategy() {

        PodamFactory factory = new PodamFactoryImpl();

        Class<AttributeStrategy<?>> strategy = (Class<AttributeStrategy<?>>)(Class<?>)EmailStrategy.class;
        ((RandomDataProviderStrategy)factory.getStrategy()).addOrReplaceAttributeStrategy(Email.class, strategy);
        return factory;
    }

    @Step("Given a Javax Validator")
    public Validator givenAJavaxValidator() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        return factory.getValidator();
    }

    @Step("Given a Podam factory with XML Types external factory")
    public PodamFactory givenAPodamFactoryWithXmlTypesExternalFactory() {

        PodamFactory externalFactory = new XmlTypesExternalFactory();
        PodamFactory factory = new PodamFactoryImpl(externalFactory);
        return factory;
    }

    @Step("Given Podam Root application context")
    public AbstractApplicationContext givenPodamRootApplicationContext() {

        return new ClassPathXmlApplicationContext(PodamConstants.SPRING_ROOT_CONFIG_LOCATION);

    }

    @Step("Given a Message Channel to manufacture types")
    public MessageChannel givenAMessageChannelToManufactureValues(ApplicationContext applicationContext) {

        return applicationContext.getBean("podamInputChannel", MessageChannel.class);
    }

    @Step("Given an empty AttributeMetadata object")
    public AttributeMetadata givenAnEmptyAttributeMetadata(Class<?> pojoClass) {

        if (null == pojoClass) {
            throw new IllegalArgumentException("pojoClass cannot be null");
        }

        String attributeName = null;
        Class<?> realAttributeType = null;
        Type[] genericTypeArgs = new Type[0];
        List<Annotation> annotations = Collections.emptyList();
        AttributeMetadata attributeMetadata = new AttributeMetadata(
                attributeName, realAttributeType, genericTypeArgs, annotations,
                pojoClass);

        return attributeMetadata;
    }

    @Step("Given a Message with header {1} and class {2}")
    public Message<? extends Object> givenATypeManufacturingMessage(TypeManufacturerParamsWrapper paramsWrapper,
                                                                    String headerName,
                                                                    Class<?> clazz) {
        Message<? extends Object> message = MessageBuilder.withPayload(
                paramsWrapper).setHeader(headerName, clazz.getName()).build();
        return message;
    }

    @Step("Given a Message with header {1} and qualifier {2}")
    public Message<? extends Object> givenATypeManufacturingMessageWithStringQualifier(
            TypeManufacturerParamsWrapper paramsWrapper,
            String headerName,
            String qualifier) {
        Message<? extends Object> message = MessageBuilder.withPayload(
                paramsWrapper).setHeader(headerName, qualifier).build();
        return message;
    }

    @Step("Given an Attribute Meta Data object for Enums")
    public AttributeMetadata givenAnAttributeMetadataForEnums(Class<?> pojoClass) {
        if (null == pojoClass) {
            throw new IllegalArgumentException("pojoClass cannot be null");
        }

        String attributeName = null;
        Class<?> realAttributeType = pojoClass;
        Type[] genericTypeArgs = new Type[0];
        List<Annotation> annotations = Collections.emptyList();
        AttributeMetadata attributeMetadata = new AttributeMetadata(
                attributeName, realAttributeType, genericTypeArgs, annotations,
                pojoClass);

        return attributeMetadata;
    }

    @Step("Given an Attribute Meta Data for Generic Types")
    public AttributeMetadata givenAnAttributeMetadataForGenericTypes(Class<?> pojoClass) {

        if (null == pojoClass) {
            throw new IllegalArgumentException("pojoClass cannot be null");
        }

        String attributeName = null;
        Class<?> realAttributeType = pojoClass;
        Type[] typeParams = pojoClass.getTypeParameters();
        List<Annotation> annotations = Collections.emptyList();
        AttributeMetadata attributeMetadata = new AttributeMetadata(
                attributeName, realAttributeType, typeParams, annotations,
                pojoClass);

        return attributeMetadata;
    }

    @Step("Given a Random Data Provider Strategy with memoization set to true")
    public DataProviderStrategy givenADataProviderStrategyWithMemoizationSetToTrue() {

        DataProviderStrategy strategy = new RandomDataProviderStrategyImpl();
        strategy.setMemoization(true);
        return strategy;
    }
}
