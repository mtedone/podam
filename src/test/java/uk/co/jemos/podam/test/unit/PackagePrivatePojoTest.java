package uk.co.jemos.podam.test.unit;

import org.junit.Test;

import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;
import uk.co.jemos.podam.exceptions.PodamMockeryException;

/**
 * @author daivanov
 *
 */
public class PackagePrivatePojoTest {

	@Test(expected = PodamMockeryException.class)
	public void testPackagePrivateClassInstantiation() {
		PodamFactory factory = new PodamFactoryImpl();
		factory.manufacturePojo(PackagePrivatePojo.class);

	}
}
