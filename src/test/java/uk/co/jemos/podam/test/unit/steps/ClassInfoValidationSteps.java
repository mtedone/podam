package uk.co.jemos.podam.test.unit.steps;


import net.thucydides.core.annotations.Step;
import org.junit.Assert;
import uk.co.jemos.podam.api.ClassAttribute;

import java.util.Set;

/**
 * Validation steps for ClassInfo stories
 * Created by tedonema on 14/06/2015.
 */
public class ClassInfoValidationSteps {

    @Step("Then the attributes contains in ClassInfo should match the ones contains in the POJO")
    public void theClassInfoAttributesShouldMatchthePojoOnes(Set<String> attribs, Set<ClassAttribute> classAttributes) {

        Assert.assertEquals("Wrong number of attributes" + classAttributes, attribs.size(),
                classAttributes.size());
        for (ClassAttribute attribute : classAttributes) {
            String attrName = attribute.getAttribute().getName();
            if (!attribs.contains(attrName)) {
                Assert.fail("Unexpected attribute " + attrName);
            }
            Assert.assertEquals("Wrong number of getters " + attribute.getGetters(),
                    1, attribute.getGetters().size());
            Assert.assertEquals("Wrong number of setters " + attribute.getSetters(),
                    1, attribute.getSetters().size());
        }
    }

}
