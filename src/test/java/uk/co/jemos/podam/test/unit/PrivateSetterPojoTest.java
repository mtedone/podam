package uk.co.jemos.podam.test.unit;

import org.junit.Assert;
import org.junit.Test;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

public class PrivateSetterPojoTest {
	@Test
	public void testPrivateAccessors() {
		PodamFactory factory = new PodamFactoryImpl();
		PrivateSetterPojo pojo = factory.manufacturePojo(PrivateSetterPojo.class);
		Assert.assertNotNull(pojo);
		Assert.assertNotNull(pojo.getValue());
	}
}
