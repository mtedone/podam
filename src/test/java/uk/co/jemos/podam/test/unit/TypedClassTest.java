package uk.co.jemos.podam.test.unit;

import org.junit.Assert;
import org.junit.Test;

import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;
import uk.co.jemos.podam.test.dto.TypedClassPojo2;
import uk.co.jemos.podam.test.utils.PodamTestUtils;

/**
 * @author daivanov
 *
 */
public class TypedClassTest {

	private static final PodamFactory factory = new PodamFactoryImpl();

	@Test
	public void testTypedClassInstantiation(){
		TypedClassPojo2 pojo = factory.manufacturePojo(TypedClassPojo2.class);
		Assert.assertNotNull(pojo);
		Assert.assertNotNull(pojo.getTypedValue());
		Assert.assertEquals("Invalid value type",
				String.class, pojo.getTypedValue().getClass());
		PodamTestUtils.assertCollectionElementsType(pojo.getTypedList(), String.class);
	}
}
