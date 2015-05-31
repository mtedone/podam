package uk.co.jemos.podam.test.unit.steps;

import net.thucydides.core.annotations.Step;
import org.junit.Assert;
import uk.co.jemos.podam.test.dto.ConstructorWithSelfReferencesButNoDefaultConstructorPojo;
import uk.co.jemos.podam.test.dto.ConstructorWithSelfReferencesPojoAndDefaultConstructor;

/**
 * Created by tedonema on 31/05/2015.
 */
public class ConstructorSelfReferenceValidationSteps {

    @Step("Then the first self-reference for a POJO with default constructor should not be null")
    public void theFirstSelfReferenceForPojoWithDefaultConstructorShouldNotBeNull(ConstructorWithSelfReferencesPojoAndDefaultConstructor parentPojo) {
        Assert.assertNotNull("The first self-reference should not be null", parentPojo.getParent());
    }

    @Step("Then the second self-reference for a POJO with default constructor should not be null")
    public void theSecondSelfReferenceForPojoWithDefaultConstructorShouldNotBeNull(ConstructorWithSelfReferencesPojoAndDefaultConstructor parentPojo) {
        Assert.assertNotNull("The second self-reference should not be null", parentPojo.getAnotherParent());
    }

    @Step("Then the first self-reference for a POJO without default constructor should not be null")
    public void theFirstSelfReferenceForPojoWithoutDefaultConstructorShouldNotBeNull(ConstructorWithSelfReferencesButNoDefaultConstructorPojo parentPojo) {
        Assert.assertNotNull("The first self-reference should not be null", parentPojo.getParent());
    }

    @Step("Then the first self-reference for a POJO without default constructor should not be null")
    public void theSecondSelfReferenceForPojoWithoutDefaultConstructorShouldNotBeNull(ConstructorWithSelfReferencesButNoDefaultConstructorPojo parentPojo) {
        Assert.assertNotNull("The seciond self-reference should not be null", parentPojo.getAnotherParent());
    }
}
