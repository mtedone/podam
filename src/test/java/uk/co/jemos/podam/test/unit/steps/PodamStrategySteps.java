package uk.co.jemos.podam.test.unit.steps;

import net.thucydides.core.annotations.Step;
import uk.co.jemos.podam.api.DataProviderStrategy;
import uk.co.jemos.podam.api.PodamFactory;

/**
 * Created by tedonema on 30/05/2015.
 */
public class PodamStrategySteps {

    @Step("When I add a specific type {2} for an abstract type or interface {1}")
    public <T> void addOrReplaceSpecific(PodamFactory podamFactory, Class<T> abstractType, Class<? extends T> concreteType) {
        DataProviderStrategy strategy = podamFactory.getStrategy();
        strategy.addOrReplaceSpecific(abstractType, concreteType);
    }

    @Step("When I remove a specific type {1} for an abstract type or interface")
    public <T> void removeSpecific(PodamFactory podamFactory, Class<T> abstractType) {
        DataProviderStrategy strategy = podamFactory.getStrategy();
        strategy.removeSpecific(abstractType);
    }

}
