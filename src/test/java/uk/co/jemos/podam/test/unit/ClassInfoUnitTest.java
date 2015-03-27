/**
 *
 */
package uk.co.jemos.podam.test.unit;

import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import junit.framework.Assert;

import org.junit.Test;

import uk.co.jemos.podam.api.ClassAttribute;
import uk.co.jemos.podam.api.ClassInfo;
import uk.co.jemos.podam.api.PodamUtils;
import uk.co.jemos.podam.test.dto.EmptyTestPojo;
import uk.co.jemos.podam.test.dto.SimplePojoToTestSetters;
import uk.co.jemos.podam.test.dto.SimplePojoWithExcludeAnnotationToTestSetters;
import uk.co.jemos.podam.test.dto.SimplePojoWithExcludeAnnotationToTestSetters.TestExclude;

/**
 * @author mtedone
 *
 */
public class ClassInfoUnitTest {

	@Test
	public void testClassInfoWithEmptyPojo() {

		List<ClassAttribute> attributes = new ArrayList<ClassAttribute>();
		ClassInfo expectedClassInfo = new ClassInfo(
				EmptyTestPojo.class, attributes);

		ClassInfo actualClassInfo = PodamUtils
				.getClassInfo(EmptyTestPojo.class);

		Assert.assertEquals(
				"The expected and actual ClassInfo objects are not the same",
				expectedClassInfo, actualClassInfo);

	}

	@Test
	public void testClassInfoSettersWithSimplePojo() {

		ClassInfo actualClassInfo = PodamUtils
				.getClassInfo(SimplePojoToTestSetters.class);
		Assert.assertNotNull("ClassInfo cannot be null!", actualClassInfo);
		Assert.assertEquals("Class mismatch",
				SimplePojoToTestSetters.class, actualClassInfo.getClassName());
		Set<String> attribs = new HashSet<String>();
		attribs.add("stringField");
		attribs.add("intField");
		checkAttributes(attribs, actualClassInfo.getClassAttributes());
	}

	@Test
	public void testClassInfoWithExcludeAnnotations() {

		Set<Class<? extends Annotation>> excludeAnnotations = new HashSet<Class<? extends Annotation>>();
		excludeAnnotations.add(TestExclude.class);
		Set<String> excludeFields = new HashSet<String>();
		testClassInfoWithExclusions(excludeAnnotations, excludeFields);
	}

	@Test
	public void testClassInfoWithExcludeFields() {

		Set<Class<? extends Annotation>> excludeAnnotations = new HashSet<Class<? extends Annotation>>();
		Set<String> excludeFields = new HashSet<String>();
		excludeFields.add("excludeField1");
		excludeFields.add("excludeField2");
		excludeFields.add("excludeField3");
		testClassInfoWithExclusions(excludeAnnotations, excludeFields);
	}

	// ------------------------------> Private methods

	private void testClassInfoWithExclusions(
			Set<Class<? extends Annotation>> excludeAnnotations,
			Set<String> excludeFields) {

		ClassInfo actualClassInfo = PodamUtils.getClassInfo(
				SimplePojoWithExcludeAnnotationToTestSetters.class,
				excludeAnnotations, excludeFields);
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
}
