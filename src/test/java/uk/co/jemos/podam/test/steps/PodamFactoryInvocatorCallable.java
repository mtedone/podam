package uk.co.jemos.podam.test.steps;

import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.test.dto.SimplePojoToTestSetters;

import java.util.concurrent.Callable;

/**
 * Created by tedonema on 19/05/2015.
 */
public class PodamFactoryInvocatorCallable implements Callable<SimplePojoToTestSetters> {

    private final PodamFactory podamFactory;

    /**
     * Full constructor.
     * @param podamFactory The Podam factory
     */
    public PodamFactoryInvocatorCallable(PodamFactory podamFactory) {
        this.podamFactory = podamFactory;

    }

    /**
     * Computes a result, or throws an exception if unable to do so.
     *
     * @return computed result
     * @throws Exception if unable to compute a result
     */
    @Override
    public SimplePojoToTestSetters call() throws Exception {
        return podamFactory.manufacturePojo(SimplePojoToTestSetters.class);
    }
}
