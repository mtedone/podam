package uk.co.jemos.podam.test.unit.features.singletons;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Title;
import org.junit.Test;
import org.junit.runner.RunWith;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.test.dto.OneDimensionalTestPojo;
import uk.co.jemos.podam.test.dto.SingletonWithParametersInStaticFactoryPojo;
import uk.co.jemos.podam.test.unit.AbstractPodamSteps;

import java.util.List;
import java.util.Map;

/**
 * Created by tedonema on 01/06/2015.
 */
@RunWith(SerenityRunner.class)
public class SingletonsTest extends AbstractPodamSteps {


    @Test
    @Title("Podam should handle Singletons with parameters in the static method")
    public void podamShouldHandleSingletonsWithParametersInPublicStaticMethod() throws Exception {

        PodamFactory podamFactory = podamFactorySteps.givenAStandardPodamFactory();
        SingletonWithParametersInStaticFactoryPojo pojo =
                podamInvocationSteps.whenIInvokeTheFactoryForClass(SingletonWithParametersInStaticFactoryPojo.class, podamFactory);
        podamValidationSteps.theObjectShouldNotBeNull(pojo);
        podamValidationSteps.theCalendarFieldShouldNotBeNull(pojo.getCreateDate());
        podamValidationSteps.theStringFieldCannotBeNullOrEmpty(pojo.getFirstName());

        List<OneDimensionalTestPojo> pojoList = pojo.getPojoList();
        podamValidationSteps.theListShouldNotBeNullAndContainAtLeastOneNonEmptyElement(pojoList);

        Map<String, OneDimensionalTestPojo> pojoMap = pojo.getPojoMap();
        podamValidationSteps.theMapShouldContainAtLeastOneNonEmptyElement(pojoMap);

    }

}
