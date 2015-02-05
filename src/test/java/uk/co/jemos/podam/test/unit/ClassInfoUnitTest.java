/**
 *
 */
package uk.co.jemos.podam.test.unit;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import junit.framework.Assert;

import org.junit.Test;

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

		Set<String> pojoDeclaredFields = new HashSet<String>();

		Set<Method> pojoSetters = new HashSet<Method>();

		Set<Constructor<?>> constructors = new HashSet<Constructor<?>>();

		ClassInfo expectedClassInfo = new ClassInfo(EmptyTestPojo.class,
				pojoDeclaredFields, pojoSetters, constructors);

		ClassInfo actualClassInfo = PodamUtils
				.getClassInfo(EmptyTestPojo.class);

		Assert.assertEquals(
				"The expected and actual ClassInfo objects are not the same",
				expectedClassInfo, actualClassInfo);

	}

	@Test
	public void testClassInfoSettersWithSimplePojo() {

		Set<String> pojoFields = retrievePojoFields();

		Set<Method> pojoSetters = PodamUtils.getPojoSetters(
				SimplePojoToTestSetters.class, pojoFields);

		Set<Constructor<?>> constructors = retrieveConstructors(SimplePojoToTestSetters.class);

		ClassInfo expectedClassInfo = new ClassInfo(
				SimplePojoToTestSetters.class, pojoFields, pojoSetters,
				constructors);

		ClassInfo actualClassInfo = PodamUtils
				.getClassInfo(SimplePojoToTestSetters.class);
		Assert.assertNotNull("ClassInfo cannot be null!", actualClassInfo);
		Assert.assertEquals(
				"The expected and actual ClassInfo objects do not match!",
				expectedClassInfo, actualClassInfo);

	}

	private Set<Constructor<?>> retrieveConstructors(Class<?> clazz) {
		Set<Constructor<?>> constructors = new HashSet<Constructor<?>>(
				Arrays.asList(clazz.getConstructors()));
		return constructors;
	}

	@Test
	public void testClassInfoWithExcludeAnnotations() {

		Set<String> pojoFields = retrievePojoFields();

		Set<Method> pojoSetters = PodamUtils.getPojoSetters(
				SimplePojoWithExcludeAnnotationToTestSetters.class, pojoFields);

		Set<Constructor<?>> constructors = retrieveConstructors(SimplePojoWithExcludeAnnotationToTestSetters.class);

		ClassInfo expectedClassInfo = new ClassInfo(
				SimplePojoWithExcludeAnnotationToTestSetters.class, pojoFields,
				pojoSetters, constructors);
		Set<Class<? extends Annotation>> excludeAnnotations = new HashSet<Class<? extends Annotation>>();
		excludeAnnotations.add(TestExclude.class);
		ClassInfo actualClassInfo = PodamUtils.getClassInfo(
				SimplePojoWithExcludeAnnotationToTestSetters.class,
				excludeAnnotations);
		Assert.assertNotNull("ClassInfo cannot be null!", actualClassInfo);
		Assert.assertEquals(
				"The expected and actual ClassInfo objects do not match!",
				expectedClassInfo, actualClassInfo);
		Assert.assertEquals("All fields from subclass must be excluded", 2,
				actualClassInfo.getClassSetters().size());
	}

	private Set<String> retrievePojoFields() {
		Set<String> pojoFields = new HashSet<String>();
		pojoFields.add("stringField");
		pojoFields.add("intField");
		return pojoFields;
	}

	// ------------------------------> Private methods

}
