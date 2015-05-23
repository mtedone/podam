package uk.co.jemos.podam.test.unit;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import uk.co.jemos.podam.api.DefaultClassInfoStrategy;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;
import uk.co.jemos.podam.api.RandomDataProviderStrategyImpl;
import uk.co.jemos.podam.common.PodamExclude;
import uk.co.jemos.podam.test.dto.BooleanPojo;
import uk.co.jemos.podam.test.dto.SimplePojoWithExcludeAnnotationToTestSetters.TestExclude;

import java.io.Serializable;

/**
 * Test @uk.co.jemos.podam.test.dto.BooleanPojo@ construction
 *
 * @author daivanov
 *
 */
public class BooleanUnitTest implements Serializable {
	private static final long serialVersionUID = 1L;

	private final static RandomDataProviderStrategyImpl strategy =
			new RandomDataProviderStrategyImpl();

	private final static DefaultClassInfoStrategy classInfoStrategy =
			DefaultClassInfoStrategy.getInstance();

	private final static PodamFactory podam = new PodamFactoryImpl(strategy);

	@BeforeClass
	public static void init() {
		classInfoStrategy.addExcludedField(BooleanPojo.class, "value4");
		classInfoStrategy.addExcludedAnnotation(TestExclude.class);
		classInfoStrategy.addExcludedAnnotation(PodamExclude.class);
		Assert.assertEquals("Unexpected number of exluded annotations",
				2, classInfoStrategy.getExcludedAnnotations().size());
	}

	@AfterClass
	public static void deinit() {
		classInfoStrategy.removeExcludedField(BooleanPojo.class, "value4");
		classInfoStrategy.removeExcludedAnnotation(TestExclude.class);
		Assert.assertEquals("Unexpected number of exluded annotations",
				1, classInfoStrategy.getExcludedAnnotations().size());
	}

	@Test
	public void testBooleanFieldsSetting() throws Exception {
	
		BooleanPojo pojo = podam.manufacturePojo(BooleanPojo.class);
		Assert.assertNotNull("Construction failed", pojo);

		Assert.assertEquals("Should be filled", true, pojo.isValue1());

		Assert.assertNull("Should be excluded", pojo.isValue2());

		Assert.assertNotNull("Should be filled", pojo.getValue3());
		Assert.assertEquals("Should be filled", true, pojo.getValue3());

		Assert.assertNull("Should be excluded", pojo.getValue4());
	}

}
