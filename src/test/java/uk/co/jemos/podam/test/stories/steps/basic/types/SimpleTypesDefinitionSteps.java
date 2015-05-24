package uk.co.jemos.podam.test.stories.steps.basic.types;

import net.thucydides.core.annotations.Steps;
import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;

/**
 * Created by tedonema on 24/05/2015.
 */
public class SimpleTypesDefinitionSteps {

        @Steps
        NativeTypesSteps steps;

        @Given("I have a Podam Factory for simple types")
        public void providePodamFactory() {
            steps.providePodamFactory();
        }

        @When("I invoke Podam on a POJO with only basic types")
        public void whenIInvokePodam() {
            steps.invokePodamOnPojoWithOnlyBasicTypes();
        }

        @Then("The returned simple POJO should not be null")
        public void thenTheReturnedPojoShouldNotBeNull() {
            steps.validateReturnedPojoIsNotNull();
        }

        @Then("The returned simple POJO should have its basic types filled with data")
        public void theReturnedPojoShouldHaveItsBasicTypesFilledWithData() {
            steps.thenTheReturnedPojoShouldHaveItsBasicFieldsValidatedWithData();
        }
}
