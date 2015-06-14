/**
 *
 */
package uk.co.jemos.podam.test.unit;

import junit.framework.Assert;
import org.junit.Test;
import uk.co.jemos.podam.api.ClassAttribute;
import uk.co.jemos.podam.api.ClassAttributeApprover;
import uk.co.jemos.podam.api.ClassInfo;
import uk.co.jemos.podam.api.PodamUtils;
import uk.co.jemos.podam.test.dto.SimplePojoWithExcludeAnnotationToTestSetters;

import java.lang.annotation.Annotation;
import java.util.HashSet;
import java.util.Set;

/**
 * @author mtedone
 *
 */
public class ClassInfoUnitTest implements ClassAttributeApprover {








	// ------------------------------> Private methods

	private void testClassInfoWithExclusions(
			Set<Class<? extends Annotation>> excludeAnnotations,
			Set<String> excludeFields) {

		ClassAttributeApprover nullApprover = null;

		ClassInfo actualClassInfo = PodamUtils.getClassInfo(
				SimplePojoWithExcludeAnnotationToTestSetters.class,
				excludeAnnotations, excludeFields, nullApprover);
		Assert.assertNotNull("ClassInfo cannot be null!", actualClassInfo);
		Set<String> attribs = new HashSet<String>();
		attribs.add("stringField");
		attribs.add("intField");
		checkAttributes(attribs, actualClassInfo.getClassAttributes());
	}

	private void checkAttributes(Set<String> attribs, Set<ClassAttribute> classAttributes) {
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

	@Override
	public boolean approve(ClassAttribute attribute) {
		return (attribute.getAttribute() != null);
	}
}
