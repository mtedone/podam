/**
 * 
 */
package uk.co.jemos.podam.test.unit.pdm3;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

import org.junit.Test;

import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;
import uk.co.jemos.podam.test.dto.pdm3.Pdm3Pojo;
import uk.co.jemos.podam.test.dto.pdm3.Pdm3PojoConstructor;
import uk.co.jemos.podam.test.dto.pdm3.Pdm3PojoGenericsConstructor;

/**
 * @author Marco Tedone
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
	public void testPdm3MapOfPojos() {

		PodamFactory factory = new PodamFactoryImpl();
		Map<String, Pdm3PojoConstructor> pojos =
			factory.manufacturePojo(HashMap.class, Integer.class, Pdm3PojoConstructor.class, String.class);
		assertMap(pojos);
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

	@Test
	public void testPdm3MapOfGenericPojos() {

		PodamFactory factory = new PodamFactoryImpl();
		Map<String, Pdm3PojoGenericsConstructor> pojos =
			factory.manufacturePojo(HashMap.class, String.class, Pdm3PojoGenericsConstructor.class);
		assertMap(pojos);
	}

	private void assertCollection(Collection<?> collection) {

		assertNotNull("The collection should not be null", collection);
		assertFalse("The collection should not be empty", collection.isEmpty());
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

	private void assertMap(Map<?,?> map) {

		assertNotNull("The map should not be null", map);
		assertFalse("The map should not be empty", map.isEmpty());
		for (Object key : map.keySet()) {
			assertNotNull("Key should not be empty", key);
			Object value = map.get(key);
			assertNotNull("Value should not be empty", value);
			if (value instanceof Pdm3PojoConstructor) {
				Pdm3PojoConstructor pojo = (Pdm3PojoConstructor)value;
				assertNotNull("Element's field should not be empty", pojo.getName());
				assertEquals("Element's type is String", String.class, pojo.getName().getClass());
			}
		}
	}
}
