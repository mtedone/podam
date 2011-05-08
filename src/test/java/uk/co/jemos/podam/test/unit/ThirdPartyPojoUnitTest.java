/**
 * 
 */
package uk.co.jemos.podam.test.unit;

import junit.framework.Assert;

import org.joda.time.LocalDate;
import org.junit.Before;
import org.junit.Test;

import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;
import uk.co.jemos.podam.test.dto.thirdparty.JodaTimePojo;

/**
 * PODAM unit tests for third party libraries
 * 
 * @author mtedone
 * 
 */
public class ThirdPartyPojoUnitTest {

	// ------------------->> Constants

	// ------------------->> Instance / Static variables

	private PodamFactory factory;

	// ------------------->> Constructors

	// ------------------->> Public methods
	@Before
	public void init() {
		factory = new PodamFactoryImpl();
	}

	@Test
	public void testJodaTimePojo() {

		JodaTimePojo pojo = factory.manufacturePojo(JodaTimePojo.class);
		Assert.assertNotNull("The Joda Time POJO cannot be null!", pojo);

		LocalDate localDate = pojo.getLocalDate();
		Assert.assertNotNull("The local date cannot be null!", localDate);

	}

	// ------------------->> Getters / Setters

	// ------------------->> Private methods

	// ------------------->> equals() / hashcode() / toString()

	// ------------------->> Inner classes

}
