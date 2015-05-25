package uk.co.jemos.podam.test.stories.steps.thread.safety;

import net.thucydides.core.annotations.Steps;
import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;

/**
 * Created by tedonema on 25/05/2015.
 */
public class StrategyThreadSafetyDefinitionSteps {

    @Steps
    StrategyThreadSafetySteps steps;

    @Given("I have an Abstract Strategy")
    public void haveAnAstractStrategy() {
        steps.provideRandomDataProviderStrategy();
    }

    @When("I add a specific type for an Abstract type")
    public void addSpecificTypeForAbstractType() {
        steps.addSpecificTypeForAbstractType();
    }

    @When("I add another specific type for the same Abstract type")
    public void addAnotherSpecificTypeForTheSameAbstractType() {
        steps.addAnotherSpecificTypeForAbstractType();
    }

    @When("I retrieve the content of the specific type for the given Abstract type")
    public void retrieveContentOfTheSpecificTypeForTheGivenAbstractType() {
        steps.retrieveContentForAbstractType();
    }

    @Then("The returned type should be the same as the first added")
    public void returnedTypeShouldBeTheSameAsFirstAdded() {
        steps.verifyConcreteType();
    }
}
