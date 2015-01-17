package uk.co.jemos.podam.test.unit;

import org.junit.Assert;
import org.junit.Test;

import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;
import uk.co.jemos.podam.test.dto.ClassGenericConstructorPojo;
import uk.co.jemos.podam.test.dto.ClassGenericPojo;
import uk.co.jemos.podam.test.dto.ClassInheritedPojo;
import uk.co.jemos.podam.test.dto.ClassPojo;

/**
 * Test @uk.co.jemos.podam.test.dto.JAXBElementPojo@ construction
 *
 * @author daivanov
 *
 */
public class ClassUnitTest {

	private final static PodamFactory podam = new PodamFactoryImpl();

	@Test
	public void testClassWithSpecificType() throws Exception {
	
		ClassPojo pojo = podam.manufacturePojo(ClassPojo.class);
		Assert.assertNotNull("Construction failed", pojo);
		Assert.assertNotNull("Class attr should not be empty", pojo.getClazz());
		Assert.assertEquals(String.class, pojo.getClazz());
	}

	@Test
	public void testClassWithGenericType() throws Exception {
	
		ClassGenericPojo<?> pojo = podam.manufacturePojo(ClassGenericPojo.class, String.class);
		Assert.assertNotNull("Construction failed", pojo);
		Assert.assertNotNull("Class attr should not be empty", pojo.getClazz());
		Assert.assertEquals(String.class, pojo.getClazz());
	}

	@Test
	public void testClassWithGenericConstructorType() throws Exception {
	
		ClassGenericConstructorPojo<?> pojo = podam.manufacturePojo(ClassGenericConstructorPojo.class, String.class);
		Assert.assertNotNull("Construction failed", pojo);
		Assert.assertNotNull("Class attr should not be empty", pojo.getClazz());
		Assert.assertEquals(String.class, pojo.getClazz());
	}

	@Test
	public void testClassWithInheritedType() throws Exception {
	
		ClassInheritedPojo pojo = podam.manufacturePojo(ClassInheritedPojo.class);
		Assert.assertNotNull("Construction failed", pojo);
		Assert.assertNotNull("Class attr should not be empty", pojo.getClazz());
		Assert.assertEquals(String.class, pojo.getClazz());
	}
}
