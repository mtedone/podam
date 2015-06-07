package uk.co.jemos.podam.test.unit.steps;

import net.thucydides.core.annotations.Step;
import uk.co.jemos.podam.api.AbstractRandomDataProviderStrategy;
import uk.co.jemos.podam.api.AttributeMetadata;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;
import uk.co.jemos.podam.test.dto.annotations.PojoSpecific;

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
}
