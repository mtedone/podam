package uk.co.jemos.podam.test.unit.steps;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import net.thucydides.core.annotations.Step;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.Set;

/**
 * Created by tedonema on 21/06/2015.
 */
public class ValidatorSteps {

    @Step("Then the POJO should not have any Validator violations for {1}")
    public <T> void thePojoShouldNotViolateAnyValidations(Validator validator, T pojo) throws Exception {

        Set<ConstraintViolation<T>> violations = validator.validate(pojo);
        assertThat("There should be no violations",
                violations, is(empty()));
    }

}
