package uk.co.jemos.podam.test.unit;

import junit.framework.Assert;

import org.junit.Test;

import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;
import uk.co.jemos.podam.test.dto.pdm42.A;
import uk.co.jemos.podam.test.dto.pdm42.B;

public class Pdm42UnitTest {

	@Test
	public void testPdm42Scenario() throws Exception {

		PodamFactory factory = new PodamFactoryImpl();
		A pojo = factory.manufacturePojo(A.class);
		Assert.assertNotNull("The class A cannot be null!", pojo);

		B b = pojo.getB();

		Assert.assertNotNull("The B object cannot be null!", b);

		Assert.assertNotNull("The Map object within B cannot be null!",
				b.getCustomValue());

	}
}
