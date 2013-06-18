/**
 * 
 */
package uk.co.jemos.podam.test.unit.pdm3;

import junit.framework.Assert;

import org.junit.Test;

import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;
import uk.co.jemos.podam.test.dto.pdm3.Pdm3APojo;
import uk.co.jemos.podam.test.dto.pdm3.Pdm3Pojo;

/**
 * @author tedonema
 * 
 */
public class Pdm3PojoUnitTest {

	@Test
	public void testPdm3Pojo() {

		PodamFactory factory = new PodamFactoryImpl();
		Pdm3Pojo pojo = factory.manufacturePojo(Pdm3Pojo.class);
		Assert.assertNotNull(pojo);
		Assert.assertNotNull("The collection element should not be null!",
				pojo.getObjs());
		Assert.assertFalse("The collection attribute should not be empty", pojo
				.getObjs().isEmpty());

	}

	@Test
	public void testPdm3APojo() {

		PodamFactory factory = new PodamFactoryImpl();
		Pdm3APojo pojo = factory.manufacturePojo(Pdm3APojo.class);
		Assert.assertNotNull(pojo);
		Assert.assertNotNull("The collection element should not be null!",
				pojo.getObjs());
		Assert.assertFalse("The collection attribute should not be empty", pojo
				.getObjs().isEmpty());

	}

}
