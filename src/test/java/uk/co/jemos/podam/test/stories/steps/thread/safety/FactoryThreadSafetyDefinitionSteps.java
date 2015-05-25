package uk.co.jemos.podam.test.stories.steps.thread.safety;

import net.thucydides.core.annotations.Steps;
import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;

/**
 * Created by tedonema on 24/05/2015.
 */
public class FactoryThreadSafetyDefinitionSteps {

        @Steps
        PodamFactoryThreadSafeSteps pfThreadSafetySteps;

        @Given("I have a single Podam Factory")
        public void createPodamFactory() {
            pfThreadSafetySteps.providePodamFactory();
        }

        @When("I invoke only one instance of Podam")
        public void whenIInvokePodam() {
            pfThreadSafetySteps.invokePodam();
        }

        @Then("The returned POJO should not be null")
        public void thenTheReturnedPojoShouldNotBeNull() {
            pfThreadSafetySteps.verifyPojoIsNotNull();
        }

        @Then("The returned POJO should have some fields filled in with data")
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
