package uk.co.jemos.podam.test.unit;

import org.junit.Assert;
import org.junit.Test;

import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;
import uk.co.jemos.podam.test.dto.TypedClassPojo2;

/**
 * @author daivanov
 *
 */
public class TypedClassTest {

	@Test
	public void testTypedClassInstantiation(){
		PodamFactory factory = new PodamFactoryImpl();
		TypedClassPojo2 pojo = factory.manufacturePojo(TypedClassPojo2.class);
		Assert.assertNotNull(pojo);
		Assert.assertNotNull(pojo.getTypedValue());
		Assert.assertNotNull(pojo.getTypedList());
	}
}
