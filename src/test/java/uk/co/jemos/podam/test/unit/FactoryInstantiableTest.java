package uk.co.jemos.podam.test.unit;

import java.util.TimeZone;
import java.util.Date;

import org.junit.Assert;
import org.junit.Test;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;
import uk.co.jemos.podam.test.dto.FactoryInstantiablePojo;

/**
 * @author daivanov
 *
 */
public class FactoryInstantiableTest {

	private PodamFactory factory = new PodamFactoryImpl();

	@Test
	public void testFactoryInstantiation() {
		TimeZone pojo = factory.manufacturePojo(TimeZone.class);
		Assert.assertNotNull(pojo);
	}

	@Test
	public void testFactoryInstantiationWithGenerics() {
		FactoryInstantiablePojo pojo = factory.manufacturePojo(
				FactoryInstantiablePojo.class, Date.class);
		Assert.assertNotNull(pojo);

		Object value = pojo.getTypedValue();
		Assert.assertNotNull(value);
		Assert.assertEquals(Date.class, value.getClass());
	}
}
