/**
 * Test multiple constructors with setters
 */
package uk.co.jemos.podam.test.unit.pdm4;

import static org.junit.Assert.*;

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
	public void testPdm4Pojo() {

		PodamFactory factory = new PodamFactoryImpl();
		Pdm4PojoWithSetters pojo = factory.manufacturePojo(Pdm4PojoWithSetters.class);
		assertNotNull(pojo);
	}
}
