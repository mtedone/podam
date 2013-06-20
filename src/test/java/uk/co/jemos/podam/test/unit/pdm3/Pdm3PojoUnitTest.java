/**
 * 
 */
package uk.co.jemos.podam.test.unit.pdm3;

import java.util.Collection;

import junit.framework.Assert;

import org.junit.Test;

import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;
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
		assertCollection(pojo.getSomething());
		assertCollection(pojo.getDescendants());
		assertCollection(pojo.getAncestors());
	}

	private void assertCollection(Collection<?> collection) {

		Assert.assertNotNull("The collection element should not be null!",
				collection);
		Assert.assertFalse("The collection attribute should not be empty",
				collection.isEmpty());

	}

}
