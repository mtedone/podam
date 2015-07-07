package uk.co.jemos.podam.test.unit.features.typeManufacturing;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Title;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import uk.co.jemos.podam.api.AttributeMetadata;
import uk.co.jemos.podam.api.DataProviderStrategy;
import uk.co.jemos.podam.common.PodamConstants;
import uk.co.jemos.podam.test.dto.ClassGenericConstructorPojo;
import uk.co.jemos.podam.test.dto.SimplePojoToTestSetters;
import uk.co.jemos.podam.test.enums.ExternalRatePodamEnum;
import uk.co.jemos.podam.test.unit.AbstractPodamSteps;
import uk.co.jemos.podam.typeManufacturers.TypeManufacturerParamsWrapper;
import uk.co.jemos.podam.typeManufacturers.TypeManufacturerParamsWrapperForGenericTypes;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by tedonema on 28/06/2015.
 */
@RunWith(SerenityRunner.class)
public class TypeManufacturingTest extends AbstractPodamSteps {

    /** The application logger */
    private static final Logger LOG = LoggerFactory.getLogger(TypeManufacturingTest.class);

    @Test
    @Title("Podam Messaging System should return an int primitive value")
    public void podamMessagingSystemShouldReturnAnIntValue() throws Exception {

        DataProviderStrategy dataProviderStrategy = podamFactorySteps.givenARandomDataProviderStrategy();

        AbstractApplicationContext applicationContext = podamFactorySteps.givenPodamRootApplicationContext();
        podamValidationSteps.theObjectShouldNotBeNull(applicationContext);

        try {
            MessageChannel inputChannel = podamFactorySteps.givenAMessageChannelToManufactureValues(applicationContext);
            podamValidationSteps.theObjectShouldNotBeNull(inputChannel);

            AttributeMetadata attributeMetadata = podamFactorySteps.givenAnEmptyAttributeMetadata
                    (SimplePojoToTestSetters.class);
            podamValidationSteps.theObjectShouldNotBeNull(attributeMetadata);

            TypeManufacturerParamsWrapper paramsWrapper =
                    new TypeManufacturerParamsWrapper(dataProviderStrategy, attributeMetadata);

            Message<? extends Object> intMessage = podamFactorySteps.givenATypeManufacturingMessage(
                    paramsWrapper, PodamConstants.HEADER_NAME,  int.class);
            podamValidationSteps.theObjectShouldNotBeNull(intMessage);

            Message value = podamInvocationSteps.whenISendAMessageToTheChannel(inputChannel, intMessage);
            podamValidationSteps.theObjectShouldNotBeNull(value);

            podamValidationSteps.theIntFieldShouldNotBeZero((Integer) value.getPayload());
        } finally {
            if (null != applicationContext) {
                applicationContext.close();
            }
        }

    }

    @Test
    @Title("Podam Messaging System should return an integer value")
    public void podamMessagingSystemShouldReturnAnIntegerValue() throws Exception {

        DataProviderStrategy dataProviderStrategy = podamFactorySteps.givenARandomDataProviderStrategy();

        AbstractApplicationContext applicationContext = podamFactorySteps.givenPodamRootApplicationContext();
        podamValidationSteps.theObjectShouldNotBeNull(applicationContext);

        try {
            MessageChannel inputChannel = podamFactorySteps.givenAMessageChannelToManufactureValues(applicationContext);
            podamValidationSteps.theObjectShouldNotBeNull(inputChannel);

            AttributeMetadata attributeMetadata = podamFactorySteps.givenAnEmptyAttributeMetadata
                    (SimplePojoToTestSetters.class);
            podamValidationSteps.theObjectShouldNotBeNull(attributeMetadata);

            TypeManufacturerParamsWrapper paramsWrapper =
                    new TypeManufacturerParamsWrapper(dataProviderStrategy, attributeMetadata);

            Message<? extends Object> intMessage = podamFactorySteps.givenATypeManufacturingMessage(
                    paramsWrapper, PodamConstants.HEADER_NAME,  Integer.class);
            podamValidationSteps.theObjectShouldNotBeNull(intMessage);

            Message value = podamInvocationSteps.whenISendAMessageToTheChannel(inputChannel, intMessage);
            podamValidationSteps.theObjectShouldNotBeNull(value);

            podamValidationSteps.theIntFieldShouldNotBeZero((Integer) value.getPayload());
        } finally {
            if (null != applicationContext) {
                applicationContext.close();
            }
        }

    }

    @Test
    @Title("Podam Messaging System should return a boolean primitive value")
    public void podamMessagingSystemShouldReturnABooleanPrimitiveValue() throws Exception {

        DataProviderStrategy dataProviderStrategy = podamFactorySteps.givenARandomDataProviderStrategy();

        AbstractApplicationContext applicationContext = podamFactorySteps.givenPodamRootApplicationContext();
        podamValidationSteps.theObjectShouldNotBeNull(applicationContext);

        try {
            MessageChannel inputChannel = podamFactorySteps.givenAMessageChannelToManufactureValues(applicationContext);
            podamValidationSteps.theObjectShouldNotBeNull(inputChannel);

            AttributeMetadata attributeMetadata = podamFactorySteps.givenAnEmptyAttributeMetadata
                    (SimplePojoToTestSetters.class);
            podamValidationSteps.theObjectShouldNotBeNull(attributeMetadata);

            TypeManufacturerParamsWrapper paramsWrapper =
                    new TypeManufacturerParamsWrapper(dataProviderStrategy, attributeMetadata);

            Message<? extends Object> message = podamFactorySteps.givenATypeManufacturingMessage(
                    paramsWrapper, PodamConstants.HEADER_NAME,  boolean.class);
            podamValidationSteps.theObjectShouldNotBeNull(message);

            Message value = podamInvocationSteps.whenISendAMessageToTheChannel(inputChannel, message);
            podamValidationSteps.theObjectShouldNotBeNull(value);

            podamValidationSteps.theBooleanValueIsTrue((Boolean) value.getPayload());

        } finally {

            if (null != applicationContext) {
                applicationContext.close();
            }

        }

    }

    @Test
    @Title("Podam Messaging System should return a boolean wrapped value")
    public void podamMessagingSystemShouldReturnABooleanWrappedValue() throws Exception {

        DataProviderStrategy dataProviderStrategy = podamFactorySteps.givenARandomDataProviderStrategy();

        AbstractApplicationContext applicationContext = podamFactorySteps.givenPodamRootApplicationContext();
        podamValidationSteps.theObjectShouldNotBeNull(applicationContext);

        try {
            MessageChannel inputChannel = podamFactorySteps.givenAMessageChannelToManufactureValues(applicationContext);
            podamValidationSteps.theObjectShouldNotBeNull(inputChannel);

            AttributeMetadata attributeMetadata = podamFactorySteps.givenAnEmptyAttributeMetadata
                    (SimplePojoToTestSetters.class);
            podamValidationSteps.theObjectShouldNotBeNull(attributeMetadata);

            TypeManufacturerParamsWrapper paramsWrapper =
                    new TypeManufacturerParamsWrapper(dataProviderStrategy, attributeMetadata);

            Message<? extends Object> message = podamFactorySteps.givenATypeManufacturingMessage(
                    paramsWrapper, PodamConstants.HEADER_NAME,  Boolean.class);
            podamValidationSteps.theObjectShouldNotBeNull(message);

            Message value = podamInvocationSteps.whenISendAMessageToTheChannel(inputChannel, message);
            podamValidationSteps.theObjectShouldNotBeNull(value);

            podamValidationSteps.theBooleanValueIsTrue((Boolean) value.getPayload());

        } finally {

            if (null != applicationContext) {
                applicationContext.close();
            }

        }

    }

    @Test
    @Title("Podam Messaging System should return a char primitive value")
    public void podamMessagingSystemShouldReturnACharacterPrimitiveValue() throws Exception {

        DataProviderStrategy dataProviderStrategy = podamFactorySteps.givenARandomDataProviderStrategy();

        AbstractApplicationContext applicationContext = podamFactorySteps.givenPodamRootApplicationContext();
        podamValidationSteps.theObjectShouldNotBeNull(applicationContext);

        try {
            MessageChannel inputChannel = podamFactorySteps.givenAMessageChannelToManufactureValues(applicationContext);
            podamValidationSteps.theObjectShouldNotBeNull(inputChannel);

            AttributeMetadata attributeMetadata = podamFactorySteps.givenAnEmptyAttributeMetadata
                    (SimplePojoToTestSetters.class);
            podamValidationSteps.theObjectShouldNotBeNull(attributeMetadata);

            TypeManufacturerParamsWrapper paramsWrapper =
                    new TypeManufacturerParamsWrapper(dataProviderStrategy, attributeMetadata);

            Message<? extends Object> message = podamFactorySteps.givenATypeManufacturingMessage(
                    paramsWrapper, PodamConstants.HEADER_NAME,  char.class);
            podamValidationSteps.theObjectShouldNotBeNull(message);

            Message value = podamInvocationSteps.whenISendAMessageToTheChannel(inputChannel, message);
            podamValidationSteps.theObjectShouldNotBeNull(value);

            podamValidationSteps.theObjectShouldNotBeNull((Character) value.getPayload());

        } finally {

            if (null != applicationContext) {
                applicationContext.close();
            }

        }

    }

    @Test
    @Title("Podam Messaging System should return a Character wrapped value")
    public void podamMessagingSystemShouldReturnACharacterWrappedValue() throws Exception {

        DataProviderStrategy dataProviderStrategy = podamFactorySteps.givenARandomDataProviderStrategy();

        AbstractApplicationContext applicationContext = podamFactorySteps.givenPodamRootApplicationContext();
        podamValidationSteps.theObjectShouldNotBeNull(applicationContext);

        try {
            MessageChannel inputChannel = podamFactorySteps.givenAMessageChannelToManufactureValues(applicationContext);
            podamValidationSteps.theObjectShouldNotBeNull(inputChannel);

            AttributeMetadata attributeMetadata = podamFactorySteps.givenAnEmptyAttributeMetadata
                    (SimplePojoToTestSetters.class);
            podamValidationSteps.theObjectShouldNotBeNull(attributeMetadata);

            TypeManufacturerParamsWrapper paramsWrapper =
                    new TypeManufacturerParamsWrapper(dataProviderStrategy, attributeMetadata);

            Message<? extends Object> message = podamFactorySteps.givenATypeManufacturingMessage(
                    paramsWrapper, PodamConstants.HEADER_NAME,  Character.class);
            podamValidationSteps.theObjectShouldNotBeNull(message);

            Message value = podamInvocationSteps.whenISendAMessageToTheChannel(inputChannel, message);
            podamValidationSteps.theObjectShouldNotBeNull(value);

            podamValidationSteps.theObjectShouldNotBeNull((Character) value.getPayload());

        } finally {

            if (null != applicationContext) {
                applicationContext.close();
            }

        }

    }

    @Test
    @Title("Podam Messaging System should return a short primitive value")
    public void podamMessagingSystemShouldReturnAShortPrimitiveValue() throws Exception {

        DataProviderStrategy dataProviderStrategy = podamFactorySteps.givenARandomDataProviderStrategy();

        AbstractApplicationContext applicationContext = podamFactorySteps.givenPodamRootApplicationContext();
        podamValidationSteps.theObjectShouldNotBeNull(applicationContext);

        try {
            MessageChannel inputChannel = podamFactorySteps.givenAMessageChannelToManufactureValues(applicationContext);
            podamValidationSteps.theObjectShouldNotBeNull(inputChannel);

            AttributeMetadata attributeMetadata = podamFactorySteps.givenAnEmptyAttributeMetadata
                    (SimplePojoToTestSetters.class);
            podamValidationSteps.theObjectShouldNotBeNull(attributeMetadata);

            TypeManufacturerParamsWrapper paramsWrapper =
                    new TypeManufacturerParamsWrapper(dataProviderStrategy, attributeMetadata);

            Message<? extends Object> message = podamFactorySteps.givenATypeManufacturingMessage(
                    paramsWrapper, PodamConstants.HEADER_NAME,  short.class);
            podamValidationSteps.theObjectShouldNotBeNull(message);

            Message value = podamInvocationSteps.whenISendAMessageToTheChannel(inputChannel, message);
            podamValidationSteps.theObjectShouldNotBeNull(value);

            podamValidationSteps.theObjectShouldNotBeNull((Short) value.getPayload());

        } finally {

            if (null != applicationContext) {
                applicationContext.close();
            }

        }

    }

    @Test
    @Title("Podam Messaging System should return a Short wrapped value")
    public void podamMessagingSystemShouldReturnAShortWrappedValue() throws Exception {

        DataProviderStrategy dataProviderStrategy = podamFactorySteps.givenARandomDataProviderStrategy();

        AbstractApplicationContext applicationContext = podamFactorySteps.givenPodamRootApplicationContext();
        podamValidationSteps.theObjectShouldNotBeNull(applicationContext);

        try {
            MessageChannel inputChannel = podamFactorySteps.givenAMessageChannelToManufactureValues(applicationContext);
            podamValidationSteps.theObjectShouldNotBeNull(inputChannel);

            AttributeMetadata attributeMetadata = podamFactorySteps.givenAnEmptyAttributeMetadata
                    (SimplePojoToTestSetters.class);
            podamValidationSteps.theObjectShouldNotBeNull(attributeMetadata);

            TypeManufacturerParamsWrapper paramsWrapper =
                    new TypeManufacturerParamsWrapper(dataProviderStrategy, attributeMetadata);

            Message<? extends Object> message = podamFactorySteps.givenATypeManufacturingMessage(
                    paramsWrapper, PodamConstants.HEADER_NAME,  Short.class);
            podamValidationSteps.theObjectShouldNotBeNull(message);

            Message value = podamInvocationSteps.whenISendAMessageToTheChannel(inputChannel, message);
            podamValidationSteps.theObjectShouldNotBeNull(value);

            podamValidationSteps.theObjectShouldNotBeNull((Short) value.getPayload());

        } finally {

            if (null != applicationContext) {
                applicationContext.close();
            }

        }

    }

    @Test
    @Title("Podam Messaging System should return a byte primitive value")
    public void podamMessagingSystemShouldReturnABytePrimitiveValue() throws Exception {

        DataProviderStrategy dataProviderStrategy = podamFactorySteps.givenARandomDataProviderStrategy();

        AbstractApplicationContext applicationContext = podamFactorySteps.givenPodamRootApplicationContext();
        podamValidationSteps.theObjectShouldNotBeNull(applicationContext);

        try {
            MessageChannel inputChannel = podamFactorySteps.givenAMessageChannelToManufactureValues(applicationContext);
            podamValidationSteps.theObjectShouldNotBeNull(inputChannel);

            AttributeMetadata attributeMetadata = podamFactorySteps.givenAnEmptyAttributeMetadata
                    (SimplePojoToTestSetters.class);
            podamValidationSteps.theObjectShouldNotBeNull(attributeMetadata);

            TypeManufacturerParamsWrapper paramsWrapper =
                    new TypeManufacturerParamsWrapper(dataProviderStrategy, attributeMetadata);

            Message<? extends Object> message = podamFactorySteps.givenATypeManufacturingMessage(
                    paramsWrapper, PodamConstants.HEADER_NAME,  byte.class);
            podamValidationSteps.theObjectShouldNotBeNull(message);

            Message value = podamInvocationSteps.whenISendAMessageToTheChannel(inputChannel, message);
            podamValidationSteps.theObjectShouldNotBeNull(value);

            podamValidationSteps.theObjectShouldNotBeNull((Byte) value.getPayload());

        } finally {

            if (null != applicationContext) {
                applicationContext.close();
            }

        }

    }

    @Test
    @Title("Podam Messaging System should return a Byte wrapped value")
    public void podamMessagingSystemShouldReturnAByteWrappedValue() throws Exception {

        DataProviderStrategy dataProviderStrategy = podamFactorySteps.givenARandomDataProviderStrategy();

        AbstractApplicationContext applicationContext = podamFactorySteps.givenPodamRootApplicationContext();
        podamValidationSteps.theObjectShouldNotBeNull(applicationContext);

        try {
            MessageChannel inputChannel = podamFactorySteps.givenAMessageChannelToManufactureValues(applicationContext);
            podamValidationSteps.theObjectShouldNotBeNull(inputChannel);

            AttributeMetadata attributeMetadata = podamFactorySteps.givenAnEmptyAttributeMetadata
                    (SimplePojoToTestSetters.class);
            podamValidationSteps.theObjectShouldNotBeNull(attributeMetadata);

            TypeManufacturerParamsWrapper paramsWrapper =
                    new TypeManufacturerParamsWrapper(dataProviderStrategy, attributeMetadata);

            Message<? extends Object> message = podamFactorySteps.givenATypeManufacturingMessage(
                    paramsWrapper, PodamConstants.HEADER_NAME,  Byte.class);
            podamValidationSteps.theObjectShouldNotBeNull(message);

            Message value = podamInvocationSteps.whenISendAMessageToTheChannel(inputChannel, message);
            podamValidationSteps.theObjectShouldNotBeNull(value);

            podamValidationSteps.theObjectShouldNotBeNull((Byte) value.getPayload());

        } finally {

            if (null != applicationContext) {
                applicationContext.close();
            }

        }

    }

    @Test
    @Title("Podam Messaging System should return a long primitive value")
    public void podamMessagingSystemShouldReturnALongPrimitiveValue() throws Exception {

        DataProviderStrategy dataProviderStrategy = podamFactorySteps.givenARandomDataProviderStrategy();

        AbstractApplicationContext applicationContext = podamFactorySteps.givenPodamRootApplicationContext();
        podamValidationSteps.theObjectShouldNotBeNull(applicationContext);

        try {
            MessageChannel inputChannel = podamFactorySteps.givenAMessageChannelToManufactureValues(applicationContext);
            podamValidationSteps.theObjectShouldNotBeNull(inputChannel);

            AttributeMetadata attributeMetadata = podamFactorySteps.givenAnEmptyAttributeMetadata
                    (SimplePojoToTestSetters.class);
            podamValidationSteps.theObjectShouldNotBeNull(attributeMetadata);

            TypeManufacturerParamsWrapper paramsWrapper =
                    new TypeManufacturerParamsWrapper(dataProviderStrategy, attributeMetadata);

            Message<? extends Object> message = podamFactorySteps.givenATypeManufacturingMessage(
                    paramsWrapper, PodamConstants.HEADER_NAME,  long.class);
            podamValidationSteps.theObjectShouldNotBeNull(message);

            Message value = podamInvocationSteps.whenISendAMessageToTheChannel(inputChannel, message);
            podamValidationSteps.theObjectShouldNotBeNull(value);

            podamValidationSteps.theObjectShouldNotBeNull((Long) value.getPayload());

        } finally {

            if (null != applicationContext) {
                applicationContext.close();
            }

        }

    }

    @Test
    @Title("Podam Messaging System should return a Long wrapped value")
    public void podamMessagingSystemShouldReturnALongWrappedValue() throws Exception {

        DataProviderStrategy dataProviderStrategy = podamFactorySteps.givenARandomDataProviderStrategy();

        AbstractApplicationContext applicationContext = podamFactorySteps.givenPodamRootApplicationContext();
        podamValidationSteps.theObjectShouldNotBeNull(applicationContext);

        try {
            MessageChannel inputChannel = podamFactorySteps.givenAMessageChannelToManufactureValues(applicationContext);
            podamValidationSteps.theObjectShouldNotBeNull(inputChannel);

            AttributeMetadata attributeMetadata = podamFactorySteps.givenAnEmptyAttributeMetadata
                    (SimplePojoToTestSetters.class);
            podamValidationSteps.theObjectShouldNotBeNull(attributeMetadata);

            TypeManufacturerParamsWrapper paramsWrapper =
                    new TypeManufacturerParamsWrapper(dataProviderStrategy, attributeMetadata);

            Message<? extends Object> message = podamFactorySteps.givenATypeManufacturingMessage(
                    paramsWrapper, PodamConstants.HEADER_NAME,  Long.class);
            podamValidationSteps.theObjectShouldNotBeNull(message);

            Message value = podamInvocationSteps.whenISendAMessageToTheChannel(inputChannel, message);
            podamValidationSteps.theObjectShouldNotBeNull(value);

            podamValidationSteps.theObjectShouldNotBeNull((Long) value.getPayload());

        } finally {

            if (null != applicationContext) {
                applicationContext.close();
            }

        }

    }

    @Test
    @Title("Podam Messaging System should return a float primitive value")
    public void podamMessagingSystemShouldReturnAFloatPrimitiveValue() throws Exception {

        DataProviderStrategy dataProviderStrategy = podamFactorySteps.givenARandomDataProviderStrategy();

        AbstractApplicationContext applicationContext = podamFactorySteps.givenPodamRootApplicationContext();
        podamValidationSteps.theObjectShouldNotBeNull(applicationContext);

        try {
            MessageChannel inputChannel = podamFactorySteps.givenAMessageChannelToManufactureValues(applicationContext);
            podamValidationSteps.theObjectShouldNotBeNull(inputChannel);

            AttributeMetadata attributeMetadata = podamFactorySteps.givenAnEmptyAttributeMetadata
                    (SimplePojoToTestSetters.class);
            podamValidationSteps.theObjectShouldNotBeNull(attributeMetadata);

            TypeManufacturerParamsWrapper paramsWrapper =
                    new TypeManufacturerParamsWrapper(dataProviderStrategy, attributeMetadata);

            Message<? extends Object> message = podamFactorySteps.givenATypeManufacturingMessage(
                    paramsWrapper, PodamConstants.HEADER_NAME,  float.class);
            podamValidationSteps.theObjectShouldNotBeNull(message);

            Message value = podamInvocationSteps.whenISendAMessageToTheChannel(inputChannel, message);
            podamValidationSteps.theObjectShouldNotBeNull(value);

            podamValidationSteps.theObjectShouldNotBeNull((Float) value.getPayload());

        } finally {

            if (null != applicationContext) {
                applicationContext.close();
            }

        }

    }

    @Test
    @Title("Podam Messaging System should return a Float wrapped value")
    public void podamMessagingSystemShouldReturnAFloatWrappedValue() throws Exception {

        DataProviderStrategy dataProviderStrategy = podamFactorySteps.givenARandomDataProviderStrategy();

        AbstractApplicationContext applicationContext = podamFactorySteps.givenPodamRootApplicationContext();
        podamValidationSteps.theObjectShouldNotBeNull(applicationContext);

        try {
            MessageChannel inputChannel = podamFactorySteps.givenAMessageChannelToManufactureValues(applicationContext);
            podamValidationSteps.theObjectShouldNotBeNull(inputChannel);

            AttributeMetadata attributeMetadata = podamFactorySteps.givenAnEmptyAttributeMetadata
                    (SimplePojoToTestSetters.class);
            podamValidationSteps.theObjectShouldNotBeNull(attributeMetadata);

            TypeManufacturerParamsWrapper paramsWrapper =
                    new TypeManufacturerParamsWrapper(dataProviderStrategy, attributeMetadata);

            Message<? extends Object> message = podamFactorySteps.givenATypeManufacturingMessage(
                    paramsWrapper, PodamConstants.HEADER_NAME,  Float.class);
            podamValidationSteps.theObjectShouldNotBeNull(message);

            Message value = podamInvocationSteps.whenISendAMessageToTheChannel(inputChannel, message);
            podamValidationSteps.theObjectShouldNotBeNull(value);

            podamValidationSteps.theObjectShouldNotBeNull((Float) value.getPayload());

        } finally {

            if (null != applicationContext) {
                applicationContext.close();
            }

        }

    }

    @Test
    @Title("Podam Messaging System should return a double primitive value")
    public void podamMessagingSystemShouldReturnADoublePrimitiveValue() throws Exception {

        DataProviderStrategy dataProviderStrategy = podamFactorySteps.givenARandomDataProviderStrategy();

        AbstractApplicationContext applicationContext = podamFactorySteps.givenPodamRootApplicationContext();
        podamValidationSteps.theObjectShouldNotBeNull(applicationContext);

        try {
            MessageChannel inputChannel = podamFactorySteps.givenAMessageChannelToManufactureValues(applicationContext);
            podamValidationSteps.theObjectShouldNotBeNull(inputChannel);

            AttributeMetadata attributeMetadata = podamFactorySteps.givenAnEmptyAttributeMetadata
                    (SimplePojoToTestSetters.class);
            podamValidationSteps.theObjectShouldNotBeNull(attributeMetadata);

            TypeManufacturerParamsWrapper paramsWrapper =
                    new TypeManufacturerParamsWrapper(dataProviderStrategy, attributeMetadata);

            Message<? extends Object> message = podamFactorySteps.givenATypeManufacturingMessage(
                    paramsWrapper, PodamConstants.HEADER_NAME,  double.class);
            podamValidationSteps.theObjectShouldNotBeNull(message);

            Message value = podamInvocationSteps.whenISendAMessageToTheChannel(inputChannel, message);
            podamValidationSteps.theObjectShouldNotBeNull(value);

            podamValidationSteps.theObjectShouldNotBeNull((Double) value.getPayload());

        } finally {

            if (null != applicationContext) {
                applicationContext.close();
            }

        }

    }

    @Test
    @Title("Podam Messaging System should return a Double wrapped value")
    public void podamMessagingSystemShouldReturnADoubleWrappedValue() throws Exception {

        DataProviderStrategy dataProviderStrategy = podamFactorySteps.givenARandomDataProviderStrategy();

        AbstractApplicationContext applicationContext = podamFactorySteps.givenPodamRootApplicationContext();
        podamValidationSteps.theObjectShouldNotBeNull(applicationContext);

        try {
            MessageChannel inputChannel = podamFactorySteps.givenAMessageChannelToManufactureValues(applicationContext);
            podamValidationSteps.theObjectShouldNotBeNull(inputChannel);

            AttributeMetadata attributeMetadata = podamFactorySteps.givenAnEmptyAttributeMetadata
                    (SimplePojoToTestSetters.class);
            podamValidationSteps.theObjectShouldNotBeNull(attributeMetadata);

            TypeManufacturerParamsWrapper paramsWrapper =
                    new TypeManufacturerParamsWrapper(dataProviderStrategy, attributeMetadata);

            Message<? extends Object> message = podamFactorySteps.givenATypeManufacturingMessage(
                    paramsWrapper, PodamConstants.HEADER_NAME,  Double.class);
            podamValidationSteps.theObjectShouldNotBeNull(message);

            Message value = podamInvocationSteps.whenISendAMessageToTheChannel(inputChannel, message);
            podamValidationSteps.theObjectShouldNotBeNull(value);

            podamValidationSteps.theObjectShouldNotBeNull((Double) value.getPayload());

        } finally {

            if (null != applicationContext) {
                applicationContext.close();
            }

        }

    }


    @Test
    @Title("Podam Messaging System should return a String value")
    public void podamMessagingSystemShouldReturnAStringValue() throws Exception {

        DataProviderStrategy dataProviderStrategy = podamFactorySteps.givenARandomDataProviderStrategy();

        AbstractApplicationContext applicationContext = podamFactorySteps.givenPodamRootApplicationContext();
        podamValidationSteps.theObjectShouldNotBeNull(applicationContext);

        try {
            MessageChannel inputChannel = podamFactorySteps.givenAMessageChannelToManufactureValues(applicationContext);
            podamValidationSteps.theObjectShouldNotBeNull(inputChannel);

            AttributeMetadata attributeMetadata = podamFactorySteps.givenAnEmptyAttributeMetadata
                    (SimplePojoToTestSetters.class);
            podamValidationSteps.theObjectShouldNotBeNull(attributeMetadata);

            TypeManufacturerParamsWrapper paramsWrapper =
                    new TypeManufacturerParamsWrapper(dataProviderStrategy, attributeMetadata);

            Message<? extends Object> message = podamFactorySteps.givenATypeManufacturingMessage(
                    paramsWrapper, PodamConstants.HEADER_NAME,  String.class);
            podamValidationSteps.theObjectShouldNotBeNull(message);

            Message value = podamInvocationSteps.whenISendAMessageToTheChannel(inputChannel, message);
            podamValidationSteps.theObjectShouldNotBeNull(value);

            podamValidationSteps.theObjectShouldNotBeNull((String) value.getPayload());

        } finally {

            if (null != applicationContext) {
                applicationContext.close();
            }

        }

    }

    @Test
    @Title("Podam Messaging System should return an Enum value")
    public void podamMessagingSystemShouldReturnAnEnumValue() throws Exception {

        DataProviderStrategy dataProviderStrategy = podamFactorySteps.givenARandomDataProviderStrategy();

        AbstractApplicationContext applicationContext = podamFactorySteps.givenPodamRootApplicationContext();
        podamValidationSteps.theObjectShouldNotBeNull(applicationContext);

        try {
            MessageChannel inputChannel = podamFactorySteps.givenAMessageChannelToManufactureValues(applicationContext);
            podamValidationSteps.theObjectShouldNotBeNull(inputChannel);

            AttributeMetadata attributeMetadata = podamFactorySteps.givenAnAttributeMetadataForEnums
                    (ExternalRatePodamEnum.class);
            podamValidationSteps.theObjectShouldNotBeNull(attributeMetadata);

            TypeManufacturerParamsWrapper paramsWrapper =
                    new TypeManufacturerParamsWrapper(dataProviderStrategy, attributeMetadata);

            Message<? extends Object> message = podamFactorySteps.givenATypeManufacturingMessageWithStringQualifier(
                    paramsWrapper, PodamConstants.HEADER_NAME, PodamConstants.ENUMERATION_QUALIFIER);
            podamValidationSteps.theObjectShouldNotBeNull(message);

            Message value = podamInvocationSteps.whenISendAMessageToTheChannel(inputChannel, message);
            podamValidationSteps.theObjectShouldNotBeNull(value);

            podamValidationSteps.theObjectShouldNotBeNull(value.getPayload());

        } finally {

            if (null != applicationContext) {
                applicationContext.close();
            }

        }

    }

    @Test
    @Title("Podam Messaging System should return a Generic Type value")
    public void podamMessagingSystemShouldReturnAGenericTypeValue() throws Exception {

        DataProviderStrategy dataProviderStrategy = podamFactorySteps.givenARandomDataProviderStrategy();

        AbstractApplicationContext applicationContext = podamFactorySteps.givenPodamRootApplicationContext();
        podamValidationSteps.theObjectShouldNotBeNull(applicationContext);

        try {
            MessageChannel inputChannel = podamFactorySteps.givenAMessageChannelToManufactureValues(applicationContext);
            podamValidationSteps.theObjectShouldNotBeNull(inputChannel);

            AttributeMetadata attributeMetadata = podamFactorySteps.givenAnAttributeMetadataForGenericTypes
                    (ClassGenericConstructorPojo.class);
            podamValidationSteps.theObjectShouldNotBeNull(attributeMetadata);

            Map<String, Type> genericTypeArgumentsMap = new HashMap<String, Type>();

            genericTypeArgumentsMap.put("T", String.class);

            TypeManufacturerParamsWrapperForGenericTypes paramsWrapper =
                    new TypeManufacturerParamsWrapperForGenericTypes(dataProviderStrategy, attributeMetadata,
                            genericTypeArgumentsMap, String.class);

            Message<? extends Object> message = podamFactorySteps.givenATypeManufacturingMessageWithStringQualifier(
                    paramsWrapper, PodamConstants.HEADER_NAME, PodamConstants.GENERIC_TYPE_QUALIFIER);
            podamValidationSteps.theObjectShouldNotBeNull(message);

            Message value = podamInvocationSteps.whenISendAMessageToTheChannel(inputChannel, message);
            podamValidationSteps.theObjectShouldNotBeNull(value);

            Object payload = value.getPayload();
            podamValidationSteps.theObjectShouldNotBeNull(payload);
            podamValidationSteps.theTwoObjectsShouldBeEqual(String.class, payload);

        } finally {

            if (null != applicationContext) {
                applicationContext.close();
            }

        }

    }



}
