/**
 * Test multiple constructors with setters
 */
package uk.co.jemos.podam.test.unit.pdm4;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.After;
import org.junit.Test;

import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;
import uk.co.jemos.podam.test.dto.pdm4.Pdm4PojoWithSetters;
import uk.co.jemos.podam.test.dto.pdm4.PojoWithFactoryMethods;
import uk.co.jemos.podam.test.dto.pdm4.PojoWithMultipleNonPublicConstructors;

/**
 * @author divanov
 *
 */
public class InstantiationUnitTest {

	private static final PodamFactory factory = new PodamFactoryImpl();

	@After
	public void cleanup() {
		Pdm4PojoWithSetters.invocationOrder.clear();
		PojoWithMultipleNonPublicConstructors.invocationOrder.clear();
		PojoWithFactoryMethods.invocationOrder.clear();
	}
	
	@Test
	public void testPdm4PojoWithSetters() {

		Pdm4PojoWithSetters pojo = factory.manufacturePojo(Pdm4PojoWithSetters.class);
		assertNull("POJO should not be created", pojo);
		assertEquals("Invocation order has changed", 5, Pdm4PojoWithSetters.invocationOrder.size());
		assertEquals("Invocation order has changed", "PodamConstructor", Pdm4PojoWithSetters.invocationOrder.get(0));
		assertEquals("Invocation order has changed", "no-op", Pdm4PojoWithSetters.invocationOrder.get(1));
		assertEquals("Invocation order has changed", "InputStream", Pdm4PojoWithSetters.invocationOrder.get(2));
		assertEquals("Invocation order has changed", "int,int", Pdm4PojoWithSetters.invocationOrder.get(3));
		assertEquals("Invocation order has changed", "abstract,int", Pdm4PojoWithSetters.invocationOrder.get(4));
	}

	@Test
	public void testPojoWithMultipleNonPublicConstructors() {

		PojoWithMultipleNonPublicConstructors pojo = factory.manufacturePojo(PojoWithMultipleNonPublicConstructors.class);
		assertNull("POJO should not be created", pojo);
		assertEquals("Invocation order has changed", 5, PojoWithMultipleNonPublicConstructors.invocationOrder.size());
		assertEquals("Invocation order has changed", "PodamConstructor", PojoWithMultipleNonPublicConstructors.invocationOrder.get(0));
		assertEquals("Invocation order has changed", "no-op", PojoWithMultipleNonPublicConstructors.invocationOrder.get(1));
		assertEquals("Invocation order has changed", "InputStream", PojoWithMultipleNonPublicConstructors.invocationOrder.get(2));
		assertEquals("Invocation order has changed", "int,int", PojoWithMultipleNonPublicConstructors.invocationOrder.get(3));
		assertEquals("Invocation order has changed", "abstract,int", PojoWithMultipleNonPublicConstructors.invocationOrder.get(4));
	}

	@Test
	public void testPojoWithFactoryMethods() {

		PojoWithFactoryMethods pojo = factory.manufacturePojo(PojoWithFactoryMethods.class);
		assertNull("POJO should not be created", pojo);
		assertEquals("Invocation order has changed", 7, PojoWithFactoryMethods.invocationOrder.size());
		assertEquals("Invocation order has changed", "PodamConstructor(str)", PojoWithFactoryMethods.invocationOrder.get(0));
		assertEquals("Invocation order has changed", "PodamConstructor(str,bool)", PojoWithFactoryMethods.invocationOrder.get(1));
		assertEquals("Invocation order has changed", "PodamConstructor(str,abstract)", PojoWithFactoryMethods.invocationOrder.get(2));
		assertEquals("Invocation order has changed", "no-op", PojoWithFactoryMethods.invocationOrder.get(3));
		assertEquals("Invocation order has changed", "InputStream", PojoWithFactoryMethods.invocationOrder.get(4));
		assertEquals("Invocation order has changed", "int,int", PojoWithFactoryMethods.invocationOrder.get(5));
		assertEquals("Invocation order has changed", "abstract,int", PojoWithFactoryMethods.invocationOrder.get(6));
	}

	@Test
	public void testPdm4PojoWithFullData() {

		Pdm4PojoWithSetters pojo = factory.manufacturePojoWithFullData(
				Pdm4PojoWithSetters.class);
		assertNull("POJO should not be created", pojo);
		assertEquals("Invocation order has changed", 5, Pdm4PojoWithSetters.invocationOrder.size());
		assertEquals("Invocation order has changed", "PodamConstructor", Pdm4PojoWithSetters.invocationOrder.get(0));
		assertEquals("Invocation order has changed", "int,int", Pdm4PojoWithSetters.invocationOrder.get(1));
		assertEquals("Invocation order has changed", "abstract,int", Pdm4PojoWithSetters.invocationOrder.get(2));
		assertEquals("Invocation order has changed", "InputStream", Pdm4PojoWithSetters.invocationOrder.get(3));
		assertEquals("Invocation order has changed", "no-op", Pdm4PojoWithSetters.invocationOrder.get(4));
	}

	@Test
	public void testPojoWithMultipleNonPublicConstructorsWithFullData() {

		PojoWithMultipleNonPublicConstructors pojo = factory.manufacturePojoWithFullData(
				PojoWithMultipleNonPublicConstructors.class);
		assertNull("POJO should not be created", pojo);
		assertEquals("Invocation order has changed", 5, PojoWithMultipleNonPublicConstructors.invocationOrder.size());
		assertEquals("Invocation order has changed", "PodamConstructor", PojoWithMultipleNonPublicConstructors.invocationOrder.get(0));
		assertEquals("Invocation order has changed", "int,int", PojoWithMultipleNonPublicConstructors.invocationOrder.get(1));
		assertEquals("Invocation order has changed", "abstract,int", PojoWithMultipleNonPublicConstructors.invocationOrder.get(2));
		assertEquals("Invocation order has changed", "InputStream", PojoWithMultipleNonPublicConstructors.invocationOrder.get(3));
		assertEquals("Invocation order has changed", "no-op", PojoWithMultipleNonPublicConstructors.invocationOrder.get(4));
	}

	@Test
	public void testPojoWithFactoryMethodsWithFullData() {

		PojoWithFactoryMethods pojo = factory.manufacturePojoWithFullData(PojoWithFactoryMethods.class);
		assertNull("POJO should not be created", pojo);
		assertEquals("Invocation order has changed", 7, PojoWithFactoryMethods.invocationOrder.size());
		assertEquals("Invocation order has changed", "PodamConstructor(str,bool)", PojoWithFactoryMethods.invocationOrder.get(0));
		assertEquals("Invocation order has changed", "PodamConstructor(str,abstract)", PojoWithFactoryMethods.invocationOrder.get(1));
		assertEquals("Invocation order has changed", "PodamConstructor(str)", PojoWithFactoryMethods.invocationOrder.get(2));
		assertEquals("Invocation order has changed", "int,int", PojoWithFactoryMethods.invocationOrder.get(3));
		assertEquals("Invocation order has changed", "abstract,int", PojoWithFactoryMethods.invocationOrder.get(4));
		assertEquals("Invocation order has changed", "InputStream", PojoWithFactoryMethods.invocationOrder.get(5));
		assertEquals("Invocation order has changed", "no-op", PojoWithFactoryMethods.invocationOrder.get(6));
	}
}
