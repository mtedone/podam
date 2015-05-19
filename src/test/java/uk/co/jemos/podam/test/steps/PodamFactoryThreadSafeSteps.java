package uk.co.jemos.podam.test.steps;

import org.apache.log4j.Logger;
import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import org.junit.Assert;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;
import uk.co.jemos.podam.test.dto.SimplePojoToTestSetters;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.*;

/**
 * Created by tedonema on 19/05/2015.
 */
public class PodamFactoryThreadSafeSteps  {

    /** The application logger */
    private static final Logger LOG = Logger.getLogger(PodamFactoryThreadSafeSteps.class);

    private PodamFactory podamFactory;

    private SimplePojoToTestSetters pojo;

    private ExecutorService executor;

    Set<SimplePojoToTestSetters> results = new HashSet<SimplePojoToTestSetters>();

    PodamFactoryInvocatorCallable callable1 = new PodamFactoryInvocatorCallable(new PodamFactoryImpl());
    FutureTask<SimplePojoToTestSetters> future1  = new FutureTask<SimplePojoToTestSetters>(callable1);
    PodamFactoryInvocatorCallable callable2 = new PodamFactoryInvocatorCallable(new PodamFactoryImpl());
    FutureTask<SimplePojoToTestSetters> future2  = new FutureTask<SimplePojoToTestSetters>(callable2);

    private int nbrThreads;

    //----> Scenario 1
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

    //----> Scenario 2

    @Given("I have an executor service with $nbrThreads threads and a callable which returns a String")
    public void buildExecutor(int nbrThreads) {
        this.nbrThreads = nbrThreads;
        executor = Executors.newFixedThreadPool(nbrThreads);
    }

    @When("I invoke the executor service for each thread")
    public void submitJobs() throws Exception {

        for (int i = 0; i < nbrThreads; i++) {
            executor.submit(future1);
            executor.submit(future2);
        }
    }

    @When("I retrieve the results")
    public void retrieveResults() {

        SimplePojoToTestSetters pojo1 = null;
        SimplePojoToTestSetters pojo2 = null;
        try {
            pojo1 = future1.get();
            LOG.info(pojo1);
            pojo2 = future2.get();
            LOG.info(pojo2);
            results.add(pojo1);
            results.add(pojo2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

    }

    @Then("I should receive 2 distinct results")
    public void verifyResults() {
        Assert.assertTrue("There should be two different objects in the Set!", results.size() == nbrThreads);
        executor.shutdown();
    }

}
