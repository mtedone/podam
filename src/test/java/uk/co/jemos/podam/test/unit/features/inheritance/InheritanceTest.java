package uk.co.jemos.podam.test.unit.features.inheritance;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Title;
import org.junit.Test;
import org.junit.runner.RunWith;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.test.dto.ClassInheritedPojo;
import uk.co.jemos.podam.test.dto.OneDimensionalChildPojo;
import uk.co.jemos.podam.test.dto.pdm42.A;
import uk.co.jemos.podam.test.dto.pdm42.B;
import uk.co.jemos.podam.test.unit.AbstractPodamSteps;
import uk.co.jemos.podam.test.utils.PodamTestConstants;

/**
 * Created by tedonema on 31/05/2015.
 */
@RunWith(SerenityRunner.class)
public class InheritanceTest extends AbstractPodamSteps {

    @Test
    @Title("Podam should handle basic inheritance scenarios")
    public void podamShouldHandleBasicInheritance() throws Exception {

        PodamFactory podamFactory = podamFactorySteps.givenAStandardPodamFactory();
        OneDimensionalChildPojo pojo =
                podamInvocationSteps.whenIInvokeTheFactoryForClass(OneDimensionalChildPojo.class, podamFactory);
        podamValidationSteps.theObjectShouldNotBeNull(pojo);
        int maxValue = PodamTestConstants.NUMBER_INT_TEN;
        podamValidationSteps.theIntFieldShouldHaveValueLessThen(pojo.getParentIntField(), maxValue);
        podamValidationSteps.theCalendarFieldShouldBeValid(pojo.getParentCalendarField());
        podamValidationSteps.theIntFieldShouldNotBeZero(pojo.getIntField());
        podamValidationSteps.theStringFieldCannotBeNullOrEmpty(pojo.getStrField());

    }

    @Test
    @Title("Podam should handle the manufacturing of POJOs which inherit from other classes")
    public void podamShouldHandleTheManufacturingOfPojosWhichInheritFromOtherClasses() throws Exception {

        PodamFactory podamFactory = podamFactorySteps.givenAStandardPodamFactory();

        ClassInheritedPojo pojo = podamInvocationSteps.whenIInvokeTheFactoryForClass(
                ClassInheritedPojo.class, podamFactory);
        podamValidationSteps.theObjectShouldNotBeNull(pojo);
        podamValidationSteps.theObjectShouldNotBeNull(pojo.getClazz());
        podamValidationSteps.theTwoObjectsShouldBeEqual(String.class, pojo.getClazz());
    }


    @Test
    @Title("Podam should manufacture all POJOs in a tree hierarchy")
    public void podamShouldManufactureAllPojosInATreeHierarchy() throws Exception {

        PodamFactory podamFactory = podamFactorySteps.givenAStandardPodamFactory();
        A pojo = podamInvocationSteps.whenIInvokeTheFactoryForClass(A.class, podamFactory);
        podamValidationSteps.theObjectShouldNotBeNull(pojo);

        B b = pojo.getB();
        podamValidationSteps.theObjectShouldNotBeNull(b);
        podamValidationSteps.theObjectShouldNotBeNull(b.getCustomValue());

    }
}
