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
    @Title("Podam Spring application context should return an integer value")
    public void podamApplicationContextShouldReturnAnIntegerValue() throws Exception {

        DataProviderStrategy dataProviderStrategy = podamFactorySteps.givenARandomDataProviderStrategy();

        AbstractApplicationContext applicationContext = podamFactorySteps.givenPodamRootApplicationContext();
        podamValidationSteps.theObjectShouldNotBeNull(applicationContext);

        try {
            MessageChannel inputChannel = podamFactorySteps.givenAMessageChannelForIntegerValues(applicationContext);
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

}
