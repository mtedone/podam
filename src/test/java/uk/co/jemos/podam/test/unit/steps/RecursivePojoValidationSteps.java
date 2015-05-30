package uk.co.jemos.podam.test.unit.steps;

import net.thucydides.core.annotations.Step;
import org.junit.Assert;
import uk.co.jemos.podam.test.dto.RecursivePojo;

/**
 * Created by tedonema on 30/05/2015.
 */
public class RecursivePojoValidationSteps {

    @Step("Then all the POJOs in the recursive hierarchy should be valid")
    public void allPojosInTheRecursiveStrategyShouldBeValid(RecursivePojo pojo) {

        Assert.assertTrue("The integer value in the pojo should not be zero!",
                pojo.getIntField() != 0);

        RecursivePojo parentPojo = pojo.getParent();
        Assert.assertNotNull("The parent pojo cannot be null!", parentPojo);
        Assert.assertTrue(
                "The integer value in the parent pojo should not be zero!",
                parentPojo.getIntField() != 0);
        Assert.assertNotNull(
                "The parent attribute of the parent pojo cannot be null!",
                parentPojo.getParent());

    }
}
