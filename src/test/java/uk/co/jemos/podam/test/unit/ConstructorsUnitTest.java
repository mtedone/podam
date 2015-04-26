package uk.co.jemos.podam.test.unit;

import org.junit.Assert;
import org.junit.Test;

import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;
import uk.co.jemos.podam.test.dto.GenericArrayInConstructorPojo;
import uk.co.jemos.podam.test.dto.GenericInConstructorPojo;
import uk.co.jemos.podam.test.dto.GenericInSetterPojo;
import uk.co.jemos.podam.test.dto.GenericInStaticConstructorPojo;
import uk.co.jemos.podam.test.dto.MultipleGenericInConstructorPojo;
import uk.co.jemos.podam.test.utils.PodamTestUtils;

/**
 * @author daivanov
 *
 */
public class ConstructorsUnitTest {

	private static final PodamFactory factory = new PodamFactoryImpl();

	@Test
	public void testGenericInConstructorPojoInstantiation() {
		GenericInConstructorPojo pojo
				= factory.manufacturePojo(GenericInConstructorPojo.class);
		Assert.assertNotNull("Instantiation failed", pojo);
		Assert.assertNotNull("Field instantiation failed", pojo.getVector());
		for (Object element : pojo.getVector()) {
			Assert.assertEquals("Wrong collection element type",
					String.class, element.getClass());
		}
	}

	@Test
	public void testGenericInSetterPojoInstantiation() {
		GenericInSetterPojo pojo
				= factory.manufacturePojo(GenericInSetterPojo.class);
		Assert.assertNotNull("Instantiation failed", pojo);
		Assert.assertNotNull("Field instantiation failed", pojo.getVector());
		for (Object element : pojo.getVector()) {
			Assert.assertEquals("Wrong collection element type",
					String.class, element.getClass());
		}
	}

	@Test
	public void testGenericInStaticConstructorPojoInstantiation() {
		GenericInStaticConstructorPojo pojo
				= factory.manufacturePojo(GenericInStaticConstructorPojo.class);
		Assert.assertNotNull("Instantiation failed", pojo);
		Assert.assertNotNull("Field instantiation failed", pojo.getVector());
		for (Object element : pojo.getVector()) {
			Assert.assertEquals("Wrong collection element type",
					String.class, element.getClass());
		}
	}

	@Test
	public void testGenericArrayInConstructorPojoInstantiation() {
		GenericArrayInConstructorPojo<?> pojo
				= factory.manufacturePojo(GenericArrayInConstructorPojo.class,
						String.class);
		Assert.assertNotNull("Instantiation failed", pojo);
		Assert.assertNotNull("Array instantiation failed", pojo.getArray());
		for (Object element : pojo.getArray()) {
			Assert.assertEquals("Wrong array element type",
					String.class, element.getClass());
		}
	}
	
	@Test
	public void testMultipleGenericInConstructorPojoInstantiation() {
		MultipleGenericInConstructorPojo<?, ?, ?, ?> pojo
				= factory.manufacturePojo(MultipleGenericInConstructorPojo.class,
						String.class, Character.class, Byte.class, Integer.class);
		Assert.assertNotNull("Instantiation failed", pojo);
		Assert.assertEquals("Class instantiation failed", String.class, pojo.getType());
		PodamTestUtils.assertCollectionElementsType(pojo.getList(), Character.class);
		PodamTestUtils.assertMapElementsType(pojo.getMap(), Byte.class, Integer.class);
	}
}
