package uk.co.jemos.podam.test.unit;

import org.junit.Assert;
import org.junit.Test;

import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

/**
 * @author daivanov
 *
 */
public class PackagePrivatePojoTest {

	@Test
	public void testPackagePrivateClassInstantiation() {
		PodamFactory factory = new PodamFactoryImpl();
		PackagePrivatePojo pojo = factory
				.manufacturePojo(PackagePrivatePojo.class);
		Assert.assertNotNull(pojo);
		Assert.assertNotNull(pojo.getValue());

	}
}
