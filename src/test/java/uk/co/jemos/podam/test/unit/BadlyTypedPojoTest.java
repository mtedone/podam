package uk.co.jemos.podam.test.unit;

import org.junit.Assert;
import org.junit.Test;

import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;
import uk.co.jemos.podam.exceptions.PodamMockeryException;
import uk.co.jemos.podam.test.dto.BadlyTypedFixedPojo;
import uk.co.jemos.podam.test.dto.BadlyTypedPojo;

public class BadlyTypedPojoTest {

	private static final PodamFactory podam = new PodamFactoryImpl();

	@Test(expected=PodamMockeryException.class)
	public void testBrokenPojo() throws Exception {

		BadlyTypedPojo pojo = podam.manufacturePojo(BadlyTypedPojo.class);
		Assert.assertNotNull("Manufacturing failed", pojo);
	}

	@Test
	public void testFixedPojo() throws Exception {

		BadlyTypedFixedPojo pojo = podam.manufacturePojo(BadlyTypedFixedPojo.class);
		Assert.assertNotNull("Manufacturing failed", pojo);
	}

}
