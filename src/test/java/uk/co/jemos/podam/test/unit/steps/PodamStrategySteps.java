package uk.co.jemos.podam.test.unit.steps;

import net.thucydides.core.annotations.Step;
import uk.co.jemos.podam.api.DataProviderStrategy;
import uk.co.jemos.podam.api.PodamFactory;

/**
 * Created by tedonema on 30/05/2015.
 */
public class PodamStrategySteps {

    @Step("When I add a specific type for an abstract type or interface")
    public <T> void addOrReplaceSpecific(PodamFactory podamFactory, Class<T> abstractType, Class<? extends T> concreteType) {
        DataProviderStrategy strategy = podamFactory.getStrategy();
        strategy.addOrReplaceSpecific(abstractType, concreteType);
    }


}
