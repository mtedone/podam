package uk.co.jemos.podam.test.unit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.junit.Test;
import uk.co.jemos.podam.api.*;
import uk.co.jemos.podam.test.dto.MemoizationPojo;
import uk.co.jemos.podam.test.dto.RecursivePojo;
import uk.co.jemos.podam.test.dto.SimplePojoToTestSetters;
import uk.co.jemos.podam.test.utils.PodamTestUtils;

import javax.xml.ws.Holder;
import java.util.Currency;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Victor on 21/08/14.
 */
public class MemoizationTest {
	private static final Logger LOG = LogManager.getLogger(MemoizationTest.class);

	private static final DataProviderStrategy strategy = new AbstractRandomDataProviderStrategy() {
		@Override
		public Object getMemoizedObject(AttributeMetadata attributeMetadata) {
			LOG.info("Memoization request: {}", attributeMetadata);
			if (attributeMetadata.getPojoClass() != null
					&& attributeMetadata.getPojoClass() != Currency.class
					&& attributeMetadata.getPojoClass() != MemoizationPojo.class) {
				Assert.assertNotNull(attributeMetadata.getAttributeName());
			}
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
	public void testMemoizationWithGenericsEquality() throws Exception {
		strategy.setMemoization(true);
		Holder<?> pojo1 = factory.manufacturePojoWithFullData(Holder.class, String.class);
		Assert.assertNotNull("Manufacturing failed", pojo1);

		Holder<?> pojo2 = factory.manufacturePojoWithFullData(Holder.class, String.class);
		Assert.assertNotNull("Manufacturing failed", pojo2);

		Assert.assertEquals("Should be the same", pojo1, pojo2);
		Assert.assertEquals("Should be the same", pojo1.value, pojo2.value);
	}

	@Test
	public void testMemoizationWithGenericsInequality() throws Exception {
		strategy.setMemoization(true);
		Holder<?> pojo1 = factory.manufacturePojo(Holder.class, String.class);
		Assert.assertNotNull("Manufacturing failed", pojo1);

		Holder<?> pojo2 = factory.manufacturePojo(Holder.class, Integer.class);
		Assert.assertNotNull("Manufacturing failed", pojo2);

		Assert.assertTrue("Should be different", pojo1 != pojo2);
	}

	@Test
	public void testCollectionsAndArrays() throws Exception {
		strategy.setMemoization(true);
		MemoizationPojo pojo = factory.manufacturePojo(MemoizationPojo.class);
		Assert.assertNotNull("Manufacturing failed", pojo);
		PodamTestUtils.assertArrayElementsType(pojo.getArray(), Currency.class);
		Set<Currency> countingSet = new HashSet<Currency>();
		for (Currency currency : pojo.getArray()) {
			countingSet.add(currency);
		}
		Assert.assertEquals("Wrong array size",
				strategy.getNumberOfCollectionElements(Currency.class),
				countingSet.size());
		PodamTestUtils.assertCollectionElementsType(pojo.getCollection(), Currency.class);
		Assert.assertEquals("Wrong collection size",
				strategy.getNumberOfCollectionElements(Currency.class),
				pojo.getCollection().size());
		PodamTestUtils.assertMapElementsType(pojo.getMap(), Currency.class, Currency.class);
		Assert.assertEquals("Wrong map size",
				strategy.getNumberOfCollectionElements(Currency.class),
				pojo.getMap().size());
	}
}
