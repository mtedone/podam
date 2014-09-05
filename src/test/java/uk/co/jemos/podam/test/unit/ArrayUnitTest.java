package uk.co.jemos.podam.test.unit;

import org.junit.Assert;
import org.junit.Test;

import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;
import uk.co.jemos.podam.test.dto.ArrayPojo;

public class ArrayUnitTest {

	@Test
	public void testArrayCreation() throws Exception {

		PodamFactory podam = new PodamFactoryImpl();
		ArrayPojo pojo = podam.manufacturePojo(ArrayPojo.class);

		Assert.assertNotNull(pojo);

		String[] array = pojo.getMyStringArray();
		Assert.assertTrue("The array should not be empty", array.length > 0);
		for (String string : array) {
			Assert.assertTrue(
					"The length of each string in the array should be > 0",
					string.length() > 0);
		}

	}

}
