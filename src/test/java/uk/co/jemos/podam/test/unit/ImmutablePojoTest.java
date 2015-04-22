package uk.co.jemos.podam.test.unit;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import uk.co.jemos.podam.api.DataProviderStrategy;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;
import uk.co.jemos.podam.test.dto.ImmutablePojo;

/**
 * Test @uk.co.jemos.podam.test.dto.ImmutablePojo@ construction
 *
 * @author daivanov
 *
 */
public class ImmutablePojoTest {

	private final static PodamFactory podam = new PodamFactoryImpl();
	private final static DataProviderStrategy strategy = podam.getStrategy();
	private final static boolean memoizationBackup = strategy.isMemoizationEnabled();

	@BeforeClass
	public static void init() {
		strategy.setMemoization(false);
	}

	@Test
	public void testImmutablePojoConstruction() throws Exception {

		ImmutablePojo pojo = podam.manufacturePojoWithFullData(ImmutablePojo.class);
		Assert.assertNotNull("Construction failed", pojo);
		Assert.assertNotNull("Value should be filled", pojo.getValue());
		Assert.assertNotNull("Value should be filled", pojo.getValue2());
	}

	@Test
	public void testImmutablePojoConstructionFailure() throws Exception {

		ImmutablePojo pojo = podam.manufacturePojo(ImmutablePojo.class);
		Assert.assertNotNull("Construction failed", pojo);
		Assert.assertNull("Value should be empty", pojo.getValue());
		Assert.assertNull("Value should be empty", pojo.getValue2());
	}

	@AfterClass
	public static void cleanup() {
		strategy.setMemoization(memoizationBackup);
	}
}
