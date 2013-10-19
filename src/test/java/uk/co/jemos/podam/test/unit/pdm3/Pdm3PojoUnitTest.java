/**
 * 
 */
package uk.co.jemos.podam.test.unit.pdm3;

import java.util.Collection;

import static org.junit.Assert.*;

import org.junit.Test;

import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;
import uk.co.jemos.podam.test.dto.pdm3.Pdm3Pojo;
import uk.co.jemos.podam.test.dto.pdm3.Pdm3PojoGenericsConstructor;

/**
 * @author tedonema
 * 
 */
public class Pdm3PojoUnitTest {

	@Test
	public void testPdm3Pojo() {

		PodamFactory factory = new PodamFactoryImpl();
		Pdm3Pojo pojo = factory.manufacturePojo(Pdm3Pojo.class);
		assertNotNull(pojo);
		assertCollection(pojo.getSomething());
		assertCollection(pojo.getDescendants());
		assertCollection(pojo.getAncestors());
	}

	@Test
	public void testPdm3PojoGenericsConstructor() {

		PodamFactory factory = new PodamFactoryImpl();
		Pdm3PojoGenericsConstructor pojo = factory.manufacturePojo(Pdm3PojoGenericsConstructor.class);
		assertNotNull(pojo);
		assertCollection(pojo.getSomething());
		assertCollection(pojo.getDescendants());
		assertCollection(pojo.getAncestors());
	}

	private void assertCollection(Collection<?> collection) {

		assertNotNull("The collection element should not be null!",
				collection);
		assertFalse("The collection attribute should not be empty",
				collection.isEmpty());

	}
}
