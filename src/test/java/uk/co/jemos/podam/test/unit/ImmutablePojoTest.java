package uk.co.jemos.podam.test.unit;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;
import uk.co.jemos.podam.api.RandomDataProviderStrategy;
import uk.co.jemos.podam.common.AbstractConstructorComparator;
import uk.co.jemos.podam.common.ConstructorHeavyFirstComparator;
import uk.co.jemos.podam.common.ConstructorLightFirstComparator;
import uk.co.jemos.podam.test.dto.ImmutablePojo;

/**
 * Test @uk.co.jemos.podam.test.dto.ImmutablePojo@ construction
 *
 * @author daivanov
 *
 */
public class ImmutablePojoTest {

	private final static RandomDataProviderStrategy strategy =
			RandomDataProviderStrategy.getInstance();

	private final static PodamFactory podam = new PodamFactoryImpl(strategy);

	private static AbstractConstructorComparator backupComparator;

	@BeforeClass
	public static void init() {
		/* Backup constructor comparator */
		backupComparator = strategy.getConstructorComparator();
	}

	@AfterClass
	public static void deinit() {
		/* RandomDataProviderStrategy is singleton,
		 * so we want to restore it's state */
		strategy.setConstructorComparator(backupComparator);
	}

	@Test
	public void testImmutablePojoConstruction() throws Exception {

		strategy.setConstructorComparator(ConstructorHeavyFirstComparator.INSTANCE);
		ImmutablePojo pojo = podam.manufacturePojo(ImmutablePojo.class);
		Assert.assertNotNull("Construction failed", pojo);
		Assert.assertNotNull("Value should be filled", pojo.getValue());
		Assert.assertNotNull("Value should be filled", pojo.getValue2());
	}

	@Test
	public void testImmutablePojoConstructionFailure() throws Exception {

		strategy.setConstructorComparator(ConstructorLightFirstComparator.INSTANCE);
		ImmutablePojo pojo = podam.manufacturePojo(ImmutablePojo.class);
		Assert.assertNotNull("Construction failed", pojo);
		Assert.assertNull("Value should be empty", pojo.getValue());
		Assert.assertNull("Value should be empty", pojo.getValue2());
	}

}
