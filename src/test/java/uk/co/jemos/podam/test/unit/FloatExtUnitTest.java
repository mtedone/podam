/**
 *
 */
package uk.co.jemos.podam.test.unit;

import org.junit.Assert;
import org.junit.Test;

import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;
import uk.co.jemos.podam.test.dto.FloatExt;

/**
 * @author tedonema
 *
 */
public class FloatExtUnitTest {

	@Test
	public void testFloatPojo() {

		PodamFactory podam = new PodamFactoryImpl();

		FloatExt<Float> pojo = podam.manufacturePojo(FloatExt.class,
				Float.class);

		Assert.assertNotNull(pojo);

	}

}
