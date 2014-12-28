package uk.co.jemos.podam.test.unit;

import java.io.Serializable;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;
import uk.co.jemos.podam.api.RandomDataProviderStrategy;
import uk.co.jemos.podam.common.PodamExclude;
import uk.co.jemos.podam.test.dto.BooleanPojo;
import uk.co.jemos.podam.test.dto.SimplePojoWithExcludeAnnotationToTestSetters.TestExclude;

/**
 * Test @uk.co.jemos.podam.test.dto.BooleanPojo@ construction
 *
 * @author daivanov
 *
 */
public class BooleanUnitTest implements Serializable {
	private static final long serialVersionUID = 8040488550006045568L;

	private final static RandomDataProviderStrategy strategy =
			RandomDataProviderStrategy.getInstance();

	private final static PodamFactory podam = new PodamFactoryImpl(strategy);

	@BeforeClass
	public static void init() {
		strategy.addExcludedAnnotation(TestExclude.class);
		strategy.addExcludedAnnotation(PodamExclude.class);
		Assert.assertEquals("Unexpected number of exluded annotations",
				2, strategy.getExcludedAnnotations().size());
	}

	@AfterClass
	public static void deinit() {
		strategy.removeExcludedAnnotation(TestExclude.class);
		Assert.assertEquals("Unexpected number of exluded annotations",
				1, strategy.getExcludedAnnotations().size());
	}

	@Test
	public void testBooleanFieldsSetting() throws Exception {
	
		BooleanPojo pojo = podam.manufacturePojo(BooleanPojo.class);
		Assert.assertNotNull("Construction failed", pojo);

		Assert.assertEquals("Should be filled", true, pojo.isValue1());

		Assert.assertNull("Should be excluded", pojo.isValue2());

		Assert.assertNotNull("Should be filled", pojo.getValue3());
		Assert.assertEquals("Should be filled", true, pojo.getValue3());
	}

}
