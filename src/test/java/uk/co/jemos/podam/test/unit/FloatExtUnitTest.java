/**
 *
 */
package uk.co.jemos.podam.test.unit;

import org.junit.Assert;
import org.junit.Test;

import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;
import uk.co.jemos.podam.test.dto.FloatExt;
import uk.co.jemos.podam.test.dto.FloatExt2;

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

	@Test
	public void testFloat2Pojo() {

		PodamFactory podam = new PodamFactoryImpl();

		FloatExt2 pojo = podam.manufacturePojo(FloatExt2.class);

		Assert.assertNotNull(pojo);

	}

}
