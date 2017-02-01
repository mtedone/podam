/**
 * 
 */
package uk.co.jemos.podam.test.unit.pdm46;

import org.junit.Test;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;
import uk.co.jemos.podam.api.RandomDataProviderStrategyImpl;
import uk.co.jemos.podam.test.dto.pdm46.WildcardEnumPojo;

public class Pdm46UnitTest {

	private PodamFactory factory = new PodamFactoryImpl(new RandomDataProviderStrategyImpl());

	@Test
	public void testWildcardEnumPojoDoesntThrowException() {
		factory.manufacturePojo(WildcardEnumPojo.class);
	}
}
