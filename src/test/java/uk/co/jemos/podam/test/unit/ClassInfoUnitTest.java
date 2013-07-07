/**
 * 
 */
package uk.co.jemos.podam.test.unit;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import junit.framework.Assert;

import org.junit.Test;

import uk.co.jemos.podam.dto.ClassInfo;
import uk.co.jemos.podam.test.dto.EmptyTestPojo;
import uk.co.jemos.podam.test.dto.SimplePojoToTestSetters;
import uk.co.jemos.podam.test.dto.SimplePojoWithExcludeAnnotationToTestSetters;
import uk.co.jemos.podam.test.dto.SimplePojoWithExcludeAnnotationToTestSetters.TestExclude;
import uk.co.jemos.podam.utils.PodamUtils;

/**
 * @author mtedone
 * 
 */
public class ClassInfoUnitTest {

	@Test
	public void testClassInfoWithEmptyPojo() {

		Set<String> pojoDeclaredFields = new HashSet<String>();

		Set<Method> pojoSetters = new HashSet<Method>();

		ClassInfo expectedClassInfo = new ClassInfo(EmptyTestPojo.class,
				pojoDeclaredFields, pojoSetters);

		ClassInfo actualClassInfo = PodamUtils
				.getClassInfo(EmptyTestPojo.class);

		Assert.assertEquals(
				"The expected and actual ClassInfo objects are not the same",
				expectedClassInfo, actualClassInfo);

	}

	@Test
	public void testClassInfoSettersWithSimplePojo() {

		Set<String> pojoFields = new HashSet<String>();
		pojoFields.add("stringField");
		pojoFields.add("intField");

		Set<Method> pojoSetters = PodamUtils.getPojoSetters(
				SimplePojoToTestSetters.class, pojoFields);

		ClassInfo expectedClassInfo = new ClassInfo(
				SimplePojoToTestSetters.class, pojoFields, pojoSetters);

		ClassInfo actualClassInfo = PodamUtils
				.getClassInfo(SimplePojoToTestSetters.class);
		Assert.assertNotNull("ClassInfo cannot be null!", actualClassInfo);
		Assert.assertEquals(
				"The expected and actual ClassInfo objects do not match!",
				expectedClassInfo, actualClassInfo);

	}
	
	@Test
	public void testClassInfoWithExcludeAnnotations() {
		Set<String> pojoFields = new HashSet<String>();
		pojoFields.add("stringField");
		pojoFields.add("intField");

		Set<Method> pojoSetters = PodamUtils.getPojoSetters(SimplePojoWithExcludeAnnotationToTestSetters.class, pojoFields);

		ClassInfo expectedClassInfo = new ClassInfo(SimplePojoWithExcludeAnnotationToTestSetters.class, pojoFields, pojoSetters);
		List<Class<? extends Annotation>> excludeAnnotations = new ArrayList<Class<? extends Annotation>>();
		excludeAnnotations.add(TestExclude.class);
		ClassInfo actualClassInfo = PodamUtils.getClassInfo(SimplePojoWithExcludeAnnotationToTestSetters.class, excludeAnnotations);
		Assert.assertNotNull("ClassInfo cannot be null!", actualClassInfo);
		Assert.assertEquals("The expected and actual ClassInfo objects do not match!", expectedClassInfo, actualClassInfo);
	}

	// ------------------------------> Private methods

}
