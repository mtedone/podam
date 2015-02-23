/**
 * 
 */
package uk.co.jemos.podam.test.unit.pdm3;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import uk.co.jemos.podam.api.DataProviderStrategy;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;
import uk.co.jemos.podam.test.dto.CollectionExtendingGenericsPojo;
import uk.co.jemos.podam.test.dto.CollectionImplementingGenericsInterface;
import uk.co.jemos.podam.test.dto.MapExtendingGenericsPojo;
import uk.co.jemos.podam.test.dto.MapImplementingGenericInterface;
import uk.co.jemos.podam.test.dto.pdm3.Pdm3Pojo;
import uk.co.jemos.podam.test.dto.pdm3.Pdm3PojoConstructor;
import uk.co.jemos.podam.test.dto.pdm3.Pdm3PojoGenericsConstructor;

/**
 * @author Marco Tedone
 * 
 */
public class Pdm3PojoUnitTest {

	public static class TrackingExternalFactory implements PodamFactory {

		private static final Type[] NO_TYPES = new Type[0];

		List<Class<?>> failures = new ArrayList<Class<?>>();

		@Override
		public <T> T manufacturePojo(Class<T> pojoClass) {
			return this.manufacturePojo(pojoClass, NO_TYPES);
		}

		@Override
		public <T> T manufacturePojoWithFullData(Class<T> pojoClass,
				Type... genericTypeArgs) {
			return this.manufacturePojo(pojoClass, genericTypeArgs);
		}

		@Override
		public <T> T manufacturePojo(Class<T> pojoClass, Type... genericTypeArgs) {
			failures.add(pojoClass);
			return null;
		}

		@Override
		public DataProviderStrategy getStrategy() {
			return null;
		}
	}

	private static final TrackingExternalFactory trackingFactory
			= new TrackingExternalFactory();

	private static final PodamFactory factory = new PodamFactoryImpl(trackingFactory);

	@Before
	public void start() {
		trackingFactory.failures.clear();
	}

	@After
	public void end() {
		assertEquals(trackingFactory.failures.toString(), 0, trackingFactory.failures.size());
	}

	@Test
	public void testPdm3Pojo() {

		Pdm3Pojo pojo = factory.manufacturePojo(Pdm3Pojo.class);
		assertNotNull(pojo);
		assertCollection(pojo.getSomething(), Object.class);
		assertCollection(pojo.getDescendants(), RuntimeException.class);
		assertCollection(pojo.getAncestors(), NullPointerException.class);
	}

	@Test
	public void testPdm3PojoConstructor() {

		Pdm3PojoConstructor<?> pojo = factory.manufacturePojo(Pdm3PojoConstructor.class, String.class);
		assertNotNull(pojo);
		assertNotNull(pojo.getName());
	}

	@Test
	public void testPdm3ListOfPojos() {

		List<?> pojos =
			factory.manufacturePojo(ArrayList.class, Pdm3PojoConstructor.class, String.class);
		assertCollection(pojos, Pdm3PojoConstructor.class);
	}

	@Test
	public void testPdm3MapOfPojos() {

		Map<?, ?> pojos =
			factory.manufacturePojo(HashMap.class, Integer.class, Pdm3PojoConstructor.class, String.class);
		assertMap(pojos, Integer.class, Pdm3PojoConstructor.class);
	}

	@Test
	public void testPdm3ExtendingListOfPojos() {

		Collection<?> pojos = factory.manufacturePojo(CollectionExtendingGenericsPojo.class);
		assertCollection(pojos, String.class);
	}

	@Test
	public void testPdm3ImplementingListOfPojos() {

		Collection<?> pojos = factory.manufacturePojo(CollectionImplementingGenericsInterface.class);
		assertCollection(pojos, String.class);
	}

	@Test
	public void testPdm3ExtendingMapOfPojos() {

		Map<?,?> pojos = factory.manufacturePojo(MapExtendingGenericsPojo.class);
		assertMap(pojos, Integer.class, String.class);
	}

	@Test
	public void testPdm3ImplementingMapOfPojos() {

		Map<?,?> pojos = factory.manufacturePojo(MapImplementingGenericInterface.class);
		assertMap(pojos, Integer.class, String.class);
	}

	@Test
	public void testPdm3PojoGenericsConstructor() {

		Pdm3PojoGenericsConstructor pojo = factory.manufacturePojo(Pdm3PojoGenericsConstructor.class);
		assertNotNull(pojo);
		assertCollection(pojo.getSomething(), Object.class);
		assertCollection(pojo.getDescendants(), RuntimeException.class);
		assertCollection(pojo.getAncestors(), NullPointerException.class);
	}

	@Test
	public void testPdm3ListOfGenericPojos() {

		List<?> pojos =
			factory.manufacturePojo(ArrayList.class, Pdm3PojoGenericsConstructor.class);
		assertCollection(pojos, Pdm3PojoGenericsConstructor.class);
	}

	@Test
	public void testPdm3MapOfGenericPojos() {

		Map<?, ?> pojos =
			factory.manufacturePojo(HashMap.class, String.class, Pdm3PojoGenericsConstructor.class);
		assertMap(pojos, String.class, Pdm3PojoGenericsConstructor.class);
	}

	private void assertCollection(Collection<?> collection, Class<?> elementType) {

		assertNotNull("The collection should not be null", collection);
		assertFalse("The collection should not be empty", collection.isEmpty());
		for (Object obj : collection) {
			assertNotNull("Collection element should not be null", obj);
			assertEquals("Wrong element's type", elementType, obj.getClass());
			if (obj instanceof Pdm3PojoConstructor) {
				Pdm3PojoConstructor<?> pojo = (Pdm3PojoConstructor<?>)obj;
				assertNotNull("Element's field should not be empty", pojo.getName());
				assertEquals("Element's type is String", RuntimeException.class, pojo.getName().getClass());
			}
		}
	}

	private void assertMap(Map<?,?> map, Class<?> keyType, Class<?> valueType) {

		assertNotNull("The map should not be null", map);
		assertFalse("The map should not be empty", map.isEmpty());
		for (Object key : map.keySet()) {
			assertNotNull("Key should not be empty", key);
			assertEquals("Wrong element's type", keyType, key.getClass());
			Object value = map.get(key);
			assertNotNull("Value should not be empty", value);
			assertEquals("Wrong element's type", valueType, value.getClass());
			if (value instanceof Pdm3PojoConstructor) {
				Pdm3PojoConstructor<?> pojo = (Pdm3PojoConstructor<?>)value;
				assertNotNull("Element's field should not be empty", pojo.getName());
				assertEquals("Element's type is String", RuntimeException.class, pojo.getName().getClass());
			}
		}
	}
}
