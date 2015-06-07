package uk.co.jemos.podam.test.unit.features.extensions;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Title;
import org.junit.Test;
import org.junit.runner.RunWith;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.test.dto.annotations.PojoClassic;
import uk.co.jemos.podam.test.dto.annotations.PojoSpecific;
import uk.co.jemos.podam.test.unit.AbstractPodamSteps;

/**
 * Created by tedonema on 07/06/2015.
 */
@RunWith(SerenityRunner.class)
public class ExtensionsTest extends AbstractPodamSteps {


    @Test
    @Title("Podam should fill AttributeMetadata with the attribute name")
    public void podamShouldFillTheAttributeMetadataWithTheAttributeNames() throws Exception {

        PodamFactory podamFactory = podamFactorySteps.givenAPodamExternalFactorytoTestAttributeMetadata();
        PojoClassic pojoClassic =
                podamInvocationSteps.whenIInvokeTheFactoryForClass(PojoClassic.class, podamFactory);
        podamValidationSteps.theObjectShouldNotBeNull(pojoClassic);

        PojoSpecific pojoSpecific =
                podamInvocationSteps.whenIInvokeTheFactoryForClass(PojoSpecific.class, podamFactory);
        podamValidationSteps.theObjectShouldNotBeNull(pojoSpecific);

        podamValidationSteps.theStringValueShouldBeExactly(pojoClassic.getAtt(), "classic");
        podamValidationSteps.theStringValueShouldBeExactly(pojoSpecific.getAtt(), "specific");

    }


}
