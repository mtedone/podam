package uk.co.jemos.podam.test.stories.steps.basic.types;

import net.thucydides.core.annotations.Step;
import net.thucydides.core.steps.ScenarioSteps;
import org.junit.Assert;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;
import uk.co.jemos.podam.test.dto.OneDimensionalTestPojo;
import uk.co.jemos.podam.test.utils.OneDimentionalPojoUtils;

/**
 * Created by tedonema on 24/05/2015.
 */
public class NativeTypesSteps extends ScenarioSteps{

    private PodamFactory podamFactory;

    private OneDimensionalTestPojo pojo;

    @Step
    public void providePodamFactory() {
        podamFactory = new PodamFactoryImpl();
    }

    @Step
    public void invokePodamOnPojoWithOnlyBasicTypes() {
        pojo = podamFactory.manufacturePojo(OneDimensionalTestPojo.class);
    }

    @Step
    public void validateReturnedPojoIsNotNull() {
        Assert.assertNotNull(pojo);
    }

    @Step
    public void thenTheReturnedPojoShouldHaveItsBasicFieldsValidatedWithData() {
        OneDimentionalPojoUtils oneDimentionalPojoUtils = new OneDimentionalPojoUtils(pojo, podamFactory.getStrategy());
        oneDimentionalPojoUtils.validateDimensionalTestPojo();
    }

}
