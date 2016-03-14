/**
 * 
 */
package uk.co.jemos.podam.test.integration;

import javax.annotation.Resource;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.test.dto.SimplePojoToTestSetters;

/**
 * @author mtedone
 * 
 */
@ContextConfiguration(locations = {"classpath:podam-test-appContext.xml"})
public class PodamFactoryInjectionIntegrationTest
		extends
			AbstractJUnit4SpringContextTests {

	// ------------------->> Constants

	// ------------------->> Instance / Static variables

	/** The Podam Factory */
	@Resource
	private PodamFactory factory;

	// ------------------->> Constructors

	// ------------------->> Public methods

	@Before
	public void init() {
		Assert.assertNotNull("The PODAM factory cannot be null!", factory);
		Assert.assertNotNull("The factory strategy cannot be null!",
				factory.getStrategy());
	}

	@Test
	public void testSimplePojo() {

		SimplePojoToTestSetters pojo = factory
				.manufacturePojo(SimplePojoToTestSetters.class);
		Assert.assertNotNull("The pojo cannot be null!", pojo);

		int intField = pojo.getIntField();
		Assert.assertTrue("The int field cannot be zero!", intField != 0);

		String stringField = pojo.getStringField();
		Assert.assertNotNull("The string field cannot be null!", stringField);
	}

	// ------------------->> Getters / Setters

	// ------------------->> Private methods

	// ------------------->> equals() / hashcode() / toString()

	// ------------------->> Inner classes

}
