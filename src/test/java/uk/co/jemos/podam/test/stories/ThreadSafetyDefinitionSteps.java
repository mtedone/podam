package uk.co.jemos.podam.test.stories;

import net.thucydides.core.annotations.Steps;
import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import uk.co.jemos.podam.test.stories.steps.PodamFactoryThreadSafeSteps;

/**
 * Created by tedonema on 23/05/2015.
 */
public class ThreadSafetyDefinitionSteps {

    @Steps
    PodamFactoryThreadSafeSteps pfThreadSafetySteps;

    @Given("Given I have a Podam Factory")
    public void givenIHaveAPodamFactory() {
        pfThreadSafetySteps.providePodamFactory();
    }

    @When("When I invoke Podam")
    public void whenIInvokePodam() {
        pfThreadSafetySteps.invokePodam();
    }

    @Then("Then The returned POJO should not be null")
    public void thenTheReturnedPojoShouldNotBeNull() {
        pfThreadSafetySteps.verifyPojoIsNotNull();
    }

    @Then("And The returned POJO should have some fields filled in with data")
    public void andTheReturnedPojoShouldHaveSomeFieldsFilledInWithData() {
        pfThreadSafetySteps.verifyPojoContainsData();
    }

    @Given("I have an executor service with $nbrThreads threads and a callable which returns a String")
    public void givenIHaveAnExecutorServiceWithNbrThreadsAndACallableWhichReturnsAString(int nbrThreads) {
        pfThreadSafetySteps.buildExecutor(nbrThreads);
    }

    @When("I invoke the executor service for each thread")
    public void iInvokeTheExecutorServiceForEachThread() throws Exception {
        pfThreadSafetySteps.submitJobs();
    }

    @When("I retrieve the results")
    public void iRetrieveTheResults() {
        pfThreadSafetySteps.retrieveResults();
    }

    @Then("I should receive $nbrResults distinct results")
    public void iShouldReceiveNbrDistinctResults(int nbrResults) {
        pfThreadSafetySteps.verifyResults(nbrResults);
    }


}
