package uk.co.jemos.podam.test.unit;

import junit.framework.Assert;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;
import uk.co.jemos.podam.api.RandomDataProviderStrategy;
import uk.co.jemos.podam.test.dto.RecursivePojo;
import uk.co.jemos.podam.test.dto.SimplePojoToTestSetters;

/**
 * Created by Victor on 21/08/14.
 */
public class MemoizationTest {

	private RandomDataProviderStrategy strategy;

	private PodamFactory factory;

	@Before
	public void setUp() throws Exception {
		strategy = RandomDataProviderStrategy.getInstance();
		factory = new PodamFactoryImpl(strategy);
	}

	@Test
	public void whenEnabledObjectsAreSame() throws Exception {
		strategy.setMemoizationEnabled(true);
		SimplePojoToTestSetters pojo1 = factory.manufacturePojo(SimplePojoToTestSetters.class);
		SimplePojoToTestSetters pojo2 = factory.manufacturePojo(SimplePojoToTestSetters.class);
		Assert.assertTrue(pojo1 == pojo2);
	}

	@Test
	public void whenDisabledObjectsAreDifferent() throws Exception {
		strategy.setMemoizationEnabled(false);
		SimplePojoToTestSetters pojo1 = factory.manufacturePojo(SimplePojoToTestSetters.class);
		SimplePojoToTestSetters pojo2 = factory.manufacturePojo(SimplePojoToTestSetters.class);
		Assert.assertTrue(pojo1 != pojo2);
	}

	@Test
	public void recursiveObjectsAreSame() throws Exception {
		strategy.setMemoizationEnabled(true);
		RecursivePojo pojo = factory.manufacturePojo(RecursivePojo.class);
		Assert.assertTrue(pojo == pojo.getParent());
	}

	@After
	public void tearDown() throws Exception {
		strategy.setMemoizationEnabled(false); // clean for next tests, because strategy is singleton
	}
}
