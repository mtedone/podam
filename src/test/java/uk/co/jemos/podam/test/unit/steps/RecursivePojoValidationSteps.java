package uk.co.jemos.podam.test.unit.steps;

import net.thucydides.core.annotations.Step;
import org.junit.Assert;
import uk.co.jemos.podam.test.dto.RecursivePojo;
import uk.co.jemos.podam.test.dto.pdm6.RecursiveList;
import uk.co.jemos.podam.test.dto.pdm6.RecursiveMap;

import java.util.List;
import java.util.Map;

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

    @Step("Then the recursive list should not be null")
    public void thePojoListShouldNotBeNull(List<RecursiveList> list) {
        Assert.assertNotNull("The list should not be null");
    }

    @Step("Then the recursive list should not be empty")
    public void thePojoListShouldNotBeEmpty(List<RecursiveList> list) {
        Assert.assertTrue("The pojo's recursive list cannot be empty!", !list.isEmpty());
    }

    @Step("Then the recursive map should not be null")
    public void thePojoMapShouldNotBeNull(Map<String, RecursiveMap> map) {
        Assert.assertNotNull("The recursive map should not be null", map);
    }

    @Step("Then the recursive map should not be empty")
    public void thePojoMapShouldNotBeEmpty(Map<String, RecursiveMap> map) {
        Assert.assertTrue("The pojo's recursive map cannot be empty!", !map.isEmpty());
    }
}
