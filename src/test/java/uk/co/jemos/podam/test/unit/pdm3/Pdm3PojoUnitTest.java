/**
 * 
 */
package uk.co.jemos.podam.test.unit.pdm3;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.junit.Assert.*;

import org.junit.Test;

import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;
import uk.co.jemos.podam.test.dto.pdm3.Pdm3Pojo;
import uk.co.jemos.podam.test.dto.pdm3.Pdm3PojoConstructor;
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
	public void testPdm3PojoConstructor() {

		PodamFactory factory = new PodamFactoryImpl();
		Pdm3PojoConstructor pojo = factory.manufacturePojo(Pdm3PojoConstructor.class, String.class);
		assertNotNull(pojo);
		assertNotNull(pojo.getName());
	}

	@Test
	public void testPdm3ListOfPojos() {

		PodamFactory factory = new PodamFactoryImpl();
		List<Pdm3PojoConstructor> pojos =
			factory.manufacturePojo(ArrayList.class, Pdm3PojoConstructor.class, String.class);
		assertCollection(pojos);
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

	@Test
	public void testPdm3ListOfGenericPojos() {

		PodamFactory factory = new PodamFactoryImpl();
		List<Pdm3PojoGenericsConstructor> pojos =
			factory.manufacturePojo(ArrayList.class, Pdm3PojoGenericsConstructor.class);
		assertCollection(pojos);
	}

	private void assertCollection(Collection<?> collection) {

		assertNotNull("The collection should not be null",
				collection);
		assertFalse("The collection should not be empty",
				collection.isEmpty());
		for (Object obj : collection) {
			assertNotNull("Collection element should not be null",
				obj);
			if (obj instanceof Pdm3PojoConstructor) {
				Pdm3PojoConstructor pojo = (Pdm3PojoConstructor)obj;
				assertNotNull("Element's field should not be empty", pojo.getName());
				assertEquals("Element's type is String", String.class, pojo.getName().getClass());
			}
		}
	}
}
