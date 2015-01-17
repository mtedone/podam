package uk.co.jemos.podam.test.unit;

import javax.xml.bind.JAXBElement;

import org.junit.Assert;
import org.junit.Test;

import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;
import uk.co.jemos.podam.test.dto.JAXBElementPojo;

/**
 * Test @uk.co.jemos.podam.test.dto.JAXBElementPojo@ construction
 *
 * @author daivanov
 *
 */
public class JAXBElementUnitTest {

	private final static PodamFactory podam = new PodamFactoryImpl();

	@Test
	public void testJAXBElementAsPojo() throws Exception {
		JAXBElement<?> pojo = podam.manufacturePojo(JAXBElement.class, String.class);
		Assert.assertNotNull("Construction failed", pojo);
		Assert.assertNotNull("QName should not be empty", pojo.getName());
		Assert.assertNotNull("Value attr should not be empty", pojo.getValue());
		Assert.assertEquals(String.class, pojo.getValue().getClass());
	}

	@Test
	public void testJAXBElementFieldsSetting() throws Exception {
	
		JAXBElementPojo<?> pojo = podam.manufacturePojo(JAXBElementPojo.class, String.class);
		Assert.assertNotNull("Construction failed", pojo);
		Assert.assertNotNull("JAXBElement attr should not be empty", pojo.getValue());
		Assert.assertNotNull("QName should not be empty", pojo.getValue().getName());
		Assert.assertNotNull("Value attr should not be empty", pojo.getValue().getValue());
		Assert.assertEquals(String.class, pojo.getValue().getValue().getClass());
	}

}
