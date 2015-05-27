package uk.co.jemos.podam.test.unit.steps;

import net.thucydides.core.annotations.Step;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

/**
 * Created by tedonema on 27/05/2015.
 */
public class PodamFactorySteps {

    @Step("Given a standard Podam Factory")
    public PodamFactory givenAStandardPodamFactory() {
        return new PodamFactoryImpl();
    }
}
