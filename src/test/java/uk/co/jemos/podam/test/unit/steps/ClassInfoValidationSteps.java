package uk.co.jemos.podam.test.unit.steps;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import net.thucydides.core.annotations.Step;

import org.junit.Assert;

import uk.co.jemos.podam.api.ClassAttribute;

import java.util.HashSet;
import java.util.Set;

/**
 * Validation steps for ClassInfo stories
 * Created by tedonema on 14/06/2015.
 */
public class ClassInfoValidationSteps {

    @Step("Then the attributes contains in ClassInfo should match the ones contains in the POJO")
    public void theClassInfoAttributesShouldMatchthePojoOnes(Set<String> attribs, Set<ClassAttribute> classAttributes) {

        Set<String> missingAttribs = new HashSet<String>(attribs);
        for (ClassAttribute attribute : classAttributes) {
            String attrName = attribute.getName();
            assertThat("Unexpected attribute", attrName, isIn(missingAttribs));
            missingAttribs.remove(attrName);
            Assert.assertEquals("Wrong number of getters for " + attribute.getName(),
                    1, attribute.getGetters().size());
            Assert.assertEquals("Wrong number of setters for " + attribute.getName(),
                    1, attribute.getSetters().size());
        }
        assertThat("Missing attributes", missingAttribs, is(empty()));
    }

}
