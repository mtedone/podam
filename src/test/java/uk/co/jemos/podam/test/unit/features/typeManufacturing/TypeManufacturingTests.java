package uk.co.jemos.podam.test.unit.features.typeManufacturing;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Title;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import uk.co.jemos.podam.api.AttributeMetadata;
import uk.co.jemos.podam.api.DataProviderStrategy;
import uk.co.jemos.podam.api.TypeManufacturerParamsWrapper;
import uk.co.jemos.podam.common.PodamConstants;
import uk.co.jemos.podam.test.dto.SimplePojoToTestSetters;
import uk.co.jemos.podam.test.unit.AbstractPodamSteps;

/**
 * Created by tedonema on 28/06/2015.
 */
@RunWith(SerenityRunner.class)
public class TypeManufacturingTests extends AbstractPodamSteps {


    @Test
    @Title("Podam Spring application context should return an int primitive value")
    public void podamApplicationContextShouldReturnAnIntValue() throws Exception {

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
    @Title("Podam Spring application context should return an integer value")
    public void podamApplicationContextShouldReturnAnIntegerValue() throws Exception {

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
    @Title("Podam Spring application context should return a boolean primitive value")
    public void podamApplicationContextShouldReturnABooleanPrimitiveValue() throws Exception {

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
    @Title("Podam Spring application context should return a boolean wrapped value")
    public void podamApplicationContextShouldReturnABooleanWrappedValue() throws Exception {

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
    @Title("Podam Spring application context should return a char primitive value")
    public void podamApplicationContextShouldReturnACharacterPrimitiveValue() throws Exception {

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
    @Title("Podam Spring application context should return a Character wrapped value")
    public void podamApplicationContextShouldReturnACharacterWrappedValue() throws Exception {

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
    @Title("Podam Spring application context should return a short primitive value")
    public void podamApplicationContextShouldReturnAShortPrimitiveValue() throws Exception {

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
    @Title("Podam Spring application context should return a Short wrapped value")
    public void podamApplicationContextShouldReturnAShortWrappedValue() throws Exception {

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

}
