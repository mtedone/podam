package uk.co.jemos.podam.test.stories.steps.thread.safety;

import org.junit.Assert;
import uk.co.jemos.podam.api.RandomDataProviderStrategy;
import uk.co.jemos.podam.api.RandomDataProviderStrategyImpl;
import uk.co.jemos.podam.test.dto.AbstractTestPojo;
import uk.co.jemos.podam.test.dto.ConcreteTestPojo;
import uk.co.jemos.podam.test.dto.ConcreteTestPojoWithAdditionalString;

/**
 * Created by tedonema on 25/05/2015.
 */
public class StrategyThreadSafetySteps {


    private RandomDataProviderStrategy abstractStrategy;

    private Class<? extends AbstractTestPojo> concreteTypeInStrategy;

    public void provideRandomDataProviderStrategy() {
        abstractStrategy = new RandomDataProviderStrategyImpl();
    }

    public void addSpecificTypeForAbstractType() {
        abstractStrategy.addSpecific(AbstractTestPojo.class, ConcreteTestPojoWithAdditionalString.class);
    }

    public void addAnotherSpecificTypeForAbstractType() {
        abstractStrategy.addSpecific(AbstractTestPojo.class, ConcreteTestPojo.class);
    }

    public void retrieveContentForAbstractType() {
        concreteTypeInStrategy = abstractStrategy.getSpecificClass(AbstractTestPojo.class);
    }

    public void verifyConcreteType() {
        Assert.assertTrue(ConcreteTestPojoWithAdditionalString.class.isAssignableFrom(concreteTypeInStrategy));
        Assert.assertFalse(ConcreteTestPojo.class.isAssignableFrom(concreteTypeInStrategy));
    }


}
