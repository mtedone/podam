package uk.co.jemos.podam.test;

import org.junit.Assert;
import org.junit.Test;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;
import uk.co.jemos.podam.test.dto.InnerClassPojo;

public class InnerClassTest {

	@Test(expected = StackOverflowError.class)
	public void normalInnerClass(){
		PodamFactory factory = new PodamFactoryImpl();
		InnerClassPojo innerClassTest = factory.manufacturePojo(InnerClassPojo.class);
		Assert.assertNotNull(innerClassTest.getIp());

		Assert.assertTrue(true);
	}
}
