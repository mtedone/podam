package uk.co.jemos.podam.test.steps;

import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import org.junit.Assert;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;
import uk.co.jemos.podam.test.dto.SimplePojoToTestSetters;

/**
 * Created by tedonema on 19/05/2015.
 */
public class PodamFactoryThreadSafeSteps  {

    private PodamFactory podamFactory;

    private SimplePojoToTestSetters pojo;

    @Given("I have a Podam Factory")
    public void providePodamFactory() {
        podamFactory = new PodamFactoryImpl();
    }

    @When("I invoke Podam")
    public void invokePodam() {
        pojo = podamFactory.manufacturePojo(SimplePojoToTestSetters.class);
    }

    @Then("The returned POJO should not be null")
    public void verifyPojoIsNotNull() {
        Assert.assertNotNull(pojo);
    }

    @Then("The returned POJO should have some fields filled in with data")
    public void verifyPojoContainsData() {
        Assert.assertNotNull(pojo.getStringField());
    }
}
