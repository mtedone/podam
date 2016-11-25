/**
 * 
 */
package uk.co.jemos.podam.test.integration;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import javax.annotation.Resource;

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
		assertThat("The PODAM factory cannot be null!",
				factory, not(nullValue()));
		assertThat("The factory strategy cannot be null!",
				factory.getStrategy(), not(nullValue()));
	}

	@Test
	public void testSimplePojo() {

		SimplePojoToTestSetters pojo = factory
				.manufacturePojo(SimplePojoToTestSetters.class);
		assertThat("The pojo cannot be null!", pojo, not(nullValue()));

		int intField = pojo.getIntField();
		assertThat("The int field cannot be zero!", intField, not(equalTo(0)));

		String stringField = pojo.getStringField();
		assertThat("The string field cannot be null!", stringField, not(nullValue()));
	}

	// ------------------->> Getters / Setters

	// ------------------->> Private methods

	// ------------------->> equals() / hashcode() / toString()

	// ------------------->> Inner classes

}
