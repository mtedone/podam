package uk.co.jemos.podam.test.unit;

import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import uk.co.jemos.podam.api.AbstractRandomDataProviderStrategy;
import uk.co.jemos.podam.api.AttributeMetadata;
import uk.co.jemos.podam.api.DataProviderStrategy;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;
import uk.co.jemos.podam.test.dto.MemoizationPojo;
import uk.co.jemos.podam.test.dto.RecursivePojo;
import uk.co.jemos.podam.test.dto.SimplePojoToTestSetters;

/**
 * Created by Victor on 21/08/14.
 */
public class MemoizationTest {
	private static final Logger LOG = LoggerFactory.getLogger(MemoizationTest.class);

	private static final DataProviderStrategy strategy = new AbstractRandomDataProviderStrategy() {
		@Override
		public Object getMemoizedObject(AttributeMetadata attributeMetadata) {
			LOG.info("Memoization request: {}", attributeMetadata);
			return super.getMemoizedObject(attributeMetadata);
		}
	};

	private static PodamFactory factory = new PodamFactoryImpl(strategy);

	@Test
	public void whenEnabledObjectsAreSame() throws Exception {
		strategy.setMemoization(true);
		SimplePojoToTestSetters pojo1 = factory.manufacturePojo(SimplePojoToTestSetters.class);
		Assert.assertNotNull("Manufacturing failed", pojo1);
		SimplePojoToTestSetters pojo2 = factory.manufacturePojo(SimplePojoToTestSetters.class);
		Assert.assertNotNull("Manufacturing failed", pojo2);
		Assert.assertTrue(pojo1 == pojo2);
	}

	@Test
	public void whenDisabledObjectsAreDifferent() throws Exception {
		strategy.setMemoization(false);
		SimplePojoToTestSetters pojo1 = factory.manufacturePojo(SimplePojoToTestSetters.class);
		Assert.assertNotNull("Manufacturing failed", pojo1);
		SimplePojoToTestSetters pojo2 = factory.manufacturePojo(SimplePojoToTestSetters.class);
		Assert.assertNotNull("Manufacturing failed", pojo2);
		Assert.assertTrue(pojo1 != pojo2);
	}

	@Test
	public void recursiveObjectsAreSame() throws Exception {
		strategy.setMemoization(true);
		RecursivePojo pojo = factory.manufacturePojo(RecursivePojo.class);
		Assert.assertNotNull("Manufacturing failed", pojo);
		Assert.assertTrue(pojo == pojo.getParent());
	}

	@Test
	public void testCollectionsAndArrays() throws Exception {
		strategy.setMemoization(true);
		MemoizationPojo pojo = factory.manufacturePojo(MemoizationPojo.class);
		Assert.assertNotNull("Manufacturing failed", pojo);
	}
}
