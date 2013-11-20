package uk.co.jemos.podam.test.unit;

import org.junit.Assert;
import org.junit.Test;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;
import uk.co.jemos.podam.test.dto.InnerClassPojo;

public class InnerClassTest {

	@Test
	public void normalInnerClass(){
		PodamFactory factory = new PodamFactoryImpl();
		InnerClassPojo innerClassPojo = factory.manufacturePojo(InnerClassPojo.class);
		Assert.assertNotNull(innerClassPojo);
		Assert.assertNotNull(innerClassPojo.getIp());
	}
}
