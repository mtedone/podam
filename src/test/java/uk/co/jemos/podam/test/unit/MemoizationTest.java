package uk.co.jemos.podam.test.unit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.junit.Test;
import uk.co.jemos.podam.api.*;
import uk.co.jemos.podam.test.dto.MemoizationPojo;
import uk.co.jemos.podam.test.utils.PodamTestUtils;

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








}
