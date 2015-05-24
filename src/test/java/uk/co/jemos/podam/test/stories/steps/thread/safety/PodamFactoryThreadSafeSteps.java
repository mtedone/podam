package uk.co.jemos.podam.test.stories.steps.thread.safety;

import net.thucydides.core.annotations.Step;
import org.apache.log4j.Logger;
import org.junit.Assert;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;
import uk.co.jemos.podam.test.dto.SimplePojoToTestSetters;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

/**
 * Created by tedonema on 19/05/2015.
 */
public class PodamFactoryThreadSafeSteps {

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

    @Step
    public void providePodamFactory() {
        podamFactory = new PodamFactoryImpl();
    }

    @Step
    public void invokePodam() {
        pojo = podamFactory.manufacturePojo(SimplePojoToTestSetters.class);
    }

    @Step
    public void verifyPojoIsNotNull() {
        Assert.assertNotNull(pojo);
    }

    @Step
    public void verifyPojoContainsData() {
        Assert.assertNotNull(pojo.getStringField());
    }

    //----> Scenario 2


    public void buildExecutor(int nbrThreads) {
        this.nbrThreads = nbrThreads;
        executor = Executors.newFixedThreadPool(nbrThreads);
    }

    @Step
    public void submitJobs() throws Exception {

        for (int i = 0; i < nbrThreads; i++) {
            executor.submit(future1);
            executor.submit(future2);
        }
    }

    @Step
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

    @Step
    public void verifyResults(int nbrResults) {
        Assert.assertTrue("There should be two different objects in the Set!", results.size() == nbrResults);
        executor.shutdown();
    }

}
