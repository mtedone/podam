package uk.co.jemos.podam.test.unit;

import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.hibernate.validator.constraints.Email;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import uk.co.jemos.podam.api.AbstractExternalFactory;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;
import uk.co.jemos.podam.api.RandomDataProviderStrategy;
import uk.co.jemos.podam.common.AttributeStrategy;
import uk.co.jemos.podam.test.dto.ValidatedPojo;
import uk.co.jemos.podam.test.strategies.EmailStrategy;

/**
 * Tests Java bean validation API
 *
 * @author daivanov
 */
public class ValidatedPojoTest {

	private static final AbstractExternalFactory externalFactory = new AbstractExternalFactory() {

		@Override
		public <T> T manufacturePojo(Class<T> pojoClass,
				Type... genericTypeArgs) {
			Assert.fail("Received request for " + pojoClass + " with "
				+ Arrays.toString(genericTypeArgs));
			return null;
		}
	};

	private static final PodamFactory factory = new PodamFactoryImpl(externalFactory);

	@BeforeClass
	public static void init() {
		@SuppressWarnings("unchecked")
		Class<AttributeStrategy<?>> strategy = (Class<AttributeStrategy<?>>)(Class<?>)EmailStrategy.class;
		((RandomDataProviderStrategy)factory.getStrategy()).addAttributeStrategy(Email.class, strategy);
	}

	@AfterClass
	public static void clear() {
		((RandomDataProviderStrategy)factory.getStrategy()).removeAttributeStrategy(Email.class);
	}

	@Test
	public void testBeanValidation(){

		ValidatedPojo pojo = factory.manufacturePojo(ValidatedPojo.class);
		Assert.assertNotNull("Manufacturing failed", pojo);
		Assert.assertNotNull("Empty field boolFalse", pojo.getBoolFalse());
		Assert.assertNotNull("Empty field boolTrue", pojo.getBoolTrue());
		Assert.assertNotNull("Empty field filledString", pojo.getFilledString());
		Assert.assertNull("Non empty field emptyString", pojo.getEmptyString());
		Assert.assertNotNull("Empty field decimalDouble", pojo.getDecimalDouble());
		Assert.assertNotNull("Empty field decimalFloat", pojo.getDecimalFloat());
		Assert.assertNotNull("Empty field decimalString", pojo.getDecimalString());
		Assert.assertNotNull("Empty field intNumber", pojo.getIntNumber());
		Assert.assertNotNull("Empty field bigIntNumber", pojo.getBigIntNumber());
		Assert.assertNotNull("Empty field shortNumber", pojo.getShortNumber());
		Assert.assertNotNull("Empty field byteNumber", pojo.getByteNumber());
		Assert.assertNotNull("Empty field intString", pojo.getIntString());
		Assert.assertNotNull("Empty field fractionDecimal", pojo.getFractionDecimal());
		Assert.assertNotNull("Empty field fractionString", pojo.getFractionString());
		Assert.assertNotNull("Empty field pastDate", pojo.getPastDate());
		Assert.assertNotNull("Empty field futureCalendar", pojo.getFutureCalendar());
		Assert.assertNotNull("Empty field sizedString", pojo.getSizedString());
		Assert.assertNotNull("Empty field maxCollection", pojo.getMaxCollection());
		Assert.assertNotNull("Empty field minCollection", pojo.getMinCollection());
		Assert.assertNotNull("Empty field email", pojo.getEmail());

		Assert.assertNull("PODAM doesn't support @Pattern ATM",
				pojo.getIdentifier());

		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		Validator validator = factory.getValidator();
		Set<ConstraintViolation<ValidatedPojo>> violations = validator.validate(pojo);
		Assert.assertTrue("Unexpected " + violations.size() + " violations "
				+ violations, violations.isEmpty());
	}
}
