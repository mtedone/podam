package uk.co.jemos.podam.test.unit.steps;

import net.thucydides.core.annotations.Step;
import org.junit.Assert;

import java.util.List;

/**
 * Created by tedonema on 27/05/2015.
 */
public class PodamValidationSteps {

    @Step("Then the Pojo should not be null")
    public boolean thePojoShouldNotBeNull(Object pojo) {
        return pojo == null;
    }

    @Step("Then the Pojo should contain some data")
    public boolean thePojoShouldContainSomeData(Object pojo) {
        return pojo.getClass().getDeclaredFields()[0] != null;
    }

    @Step("Then the Pojo should be null")
    public void thePojoShouldBeNull(Object pojo) {
        Assert.assertNull("The pojo should be null", pojo);
    }

    @Step("Then the inner pojo instance variable should not be null")
    public void theInnerPojoInstanceShouldNotBeNull(Object pojo) {
        Assert.assertNotNull("The inner pojo instance variable should not be null", pojo);
    }

    @Step("Then the int field should not be zero")
    public void theIntFieldShouldNotBeZero(int intField) {
        Assert.assertFalse("The integer field should not be zero", intField == 0);
    }

    @Step("Then the child pojo should not be null")
    public void theChildPojoShouldNotBeNull(Object child) {
        Assert.assertNotNull("The child pojo should not be null", child);
    }

    @Step("Then each of the list elements should not be null")
    public void eachListElementShouldNotBeNull(List<?> list) {
        for (int i = 0; i < list.size(); i++) {
            Assert.assertNotNull(list.get(i));
        }
    }

}
