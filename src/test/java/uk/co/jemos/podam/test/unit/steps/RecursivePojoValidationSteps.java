package uk.co.jemos.podam.test.unit.steps;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import net.thucydides.core.annotations.Step;
import uk.co.jemos.podam.test.dto.RecursivePojo;

/**
 * Created by tedonema on 30/05/2015.
 */
public class RecursivePojoValidationSteps {

    @Step("Then all the POJOs in the recursive hierarchy should be valid")
    public void allPojosInTheRecursiveStrategyShouldBeValid(RecursivePojo pojo) {

        assertThat("The integer value in the pojo should not be zero!",
                pojo.getIntField(), not(equalTo(0)));

        RecursivePojo parentPojo = pojo.getParent();
        assertThat("The parent pojo cannot be null!", parentPojo, not(nullValue()));
        assertThat(
                "The integer value in the parent pojo should not be zero!",
                parentPojo.getIntField(), not(equalTo(0)));
        assertThat(
                "The parent attribute of the parent pojo cannot be null!",
                parentPojo.getParent(), not(nullValue()));

    }
}
