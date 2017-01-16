package uk.co.jemos.podam.test.unit.features.extensions;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Title;

import java.io.InputStream;
import java.sql.Timestamp;

import org.junit.Test;
import org.junit.runner.RunWith;

import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.test.dto.PojoWithMapsAndCollections;
import uk.co.jemos.podam.test.dto.annotations.PojoClassic;
import uk.co.jemos.podam.test.dto.annotations.PojoSpecific;
import uk.co.jemos.podam.test.strategies.CustomRandomDataProviderStrategy;
import uk.co.jemos.podam.test.unit.AbstractPodamSteps;

/**
 * Created by tedonema on 07/06/2015.
 */
@RunWith(SerenityRunner.class)
public class ExtensionsTest extends AbstractPodamSteps {

    @Test
    @Title("Podam should fill AttributeMetadata with the attribute name")
    public void podamShouldFillTheAttributeMetadataWithTheAttributeNames() throws Exception {

        PodamFactory podamFactory = podamFactorySteps.givenAPodamWithACustomStringTypeManufacturer();
        PojoClassic pojoClassic =
                podamInvocationSteps.whenIInvokeTheFactoryForClass(PojoClassic.class, podamFactory);
        podamValidationSteps.theObjectShouldNotBeNull(pojoClassic);

        PojoSpecific pojoSpecific =
                podamInvocationSteps.whenIInvokeTheFactoryForClass(PojoSpecific.class, podamFactory);
        podamValidationSteps.theObjectShouldNotBeNull(pojoSpecific);

        podamValidationSteps.theStringValueShouldBeExactly(pojoClassic.getAtt(), "classic");
        podamValidationSteps.theStringValueShouldBeExactly(pojoSpecific.getAtt(), "specific");

    }

    @Test
    @Title("Podam should create InputStream with the custom type manufacturer")
    public void podamShouldCreateInputStreamWithCustomTypeManufacturer() throws Exception {

        PodamFactory podamFactory = podamFactorySteps.givenAPodamWithAInputStreamTypeManufacturer();

        InputStream inputStream =
                podamInvocationSteps.whenIInvokeTheFactoryForClass(InputStream.class, podamFactory);
        podamValidationSteps.thePojoMustBeOfTheType(inputStream, InputStream.class);

        podamInvocationSteps.whenIRemoveTypeManufacturer(podamFactory, InputStream.class);
        InputStream inputStream2 =
                podamInvocationSteps.whenIInvokeTheFactoryForClass(InputStream.class, podamFactory);
        podamValidationSteps.theObjectShouldBeNull(inputStream2);
    }

    @Test
    @Title("Podam should create Timestamp with the custom type manufacturer")
    public void podamShouldCreateTimestampWithCustomTypeManufacturer() throws Exception {

        PodamFactory podamFactory = podamFactorySteps.givenAPodamWithACustomIntegerTypeManufacturer();

        Timestamp timestamp =
                podamInvocationSteps.whenIInvokeTheFactoryForClass(Timestamp.class, podamFactory);
        podamValidationSteps.theObjectShouldNotBeNull(timestamp);
    }

    @Test
    @Title("Podam should create POJOs in accordance with custom data provider strategies")
    public void podamShouldCreatePojosInAccordanceWithCustomDataProviderStrategies() throws Exception {

        CustomRandomDataProviderStrategy strategy = podamFactorySteps.givenACustomRandomDataProviderStrategy();


        PodamFactory podamFactory = podamFactorySteps.givenAPodamFactoryWithCustomDataProviderStrategy(strategy);

        PojoWithMapsAndCollections pojo =
                podamInvocationSteps.whenIInvokeTheFactoryForClass(PojoWithMapsAndCollections.class, podamFactory);

        podamValidationSteps.theObjectShouldNotBeNull(pojo);
        podamValidationSteps.theArrayOfTheGivenTypeShouldNotBeNullOrEmptyAndContainExactlyTheGivenNumberOfElements(
                pojo.getArray(), 2, String.class);
        podamValidationSteps.theCollectionShouldNotBeNullOrEmptyAndShouldHaveExactlyTheExpectedNumberOfElements(
                pojo.getList(), Boolean.class, 3);
        podamValidationSteps.theMapShouldNotBeNullOrEmptyAndShouldHaveExactlyTheExpectedNumberOfElements(
                pojo.getMap(), Integer.class, Long.class, 4);

    }


}
