package uk.co.jemos.podam.test.unit;

import org.junit.Assert;
import org.junit.Test;

import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;
import uk.co.jemos.podam.test.dto.GenericInConstructorPojo;

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
}
