package uk.co.jemos.podam.test.unit.steps;

import net.thucydides.core.annotations.Step;
import org.junit.Assert;
import uk.co.jemos.podam.test.dto.ValidatedPojo;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.Set;

/**
 * Created by tedonema on 21/06/2015.
 */
public class ValidatorSteps {

    @Step("Then the POJO should not have any Validator violations")
    public void thePojoShouldNotViolateAnyValidations(Validator validator, ValidatedPojo pojo) throws Exception {

        Set<ConstraintViolation<ValidatedPojo>> violations = validator.validate(pojo);
        Assert.assertTrue("Unexpected " + violations.size() + " violations "
                + violations, violations.isEmpty());
    }
}
