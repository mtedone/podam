/**
 * Test multiple constructors with setters
 */
package uk.co.jemos.podam.test.unit.pdm4;

import static org.junit.Assert.*;

import org.junit.Ignore;
import org.junit.Test;

import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;
import uk.co.jemos.podam.test.dto.pdm4.Pdm4PojoWithSetters;

/**
 * @author divanov
 *
 */
public class Pdm4PojoUnitTest {

	@Test
	@Ignore("Seem broken, at least as of 01ba280, at least in jdk7")
	public void testPdm4Pojo() {

		PodamFactory factory = new PodamFactoryImpl();
		Pdm4PojoWithSetters pojo = factory.manufacturePojo(Pdm4PojoWithSetters.class);
		assertNotNull(pojo);
		assertEquals("Invocation order has changed", 4, pojo.invocationOrder.size());
		assertEquals("Invocation order has changed", "PodamConstructor", pojo.invocationOrder.get(0));
		assertEquals("Invocation order has changed", "no-op", pojo.invocationOrder.get(1));
		assertEquals("Invocation order has changed", "InputStream", pojo.invocationOrder.get(2));
		assertEquals("Invocation order has changed", "int", pojo.invocationOrder.get(3));
	}
}
