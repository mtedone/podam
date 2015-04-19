package uk.co.jemos.podam.test.unit;

import org.junit.Assert;
import org.junit.Test;

import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;
import uk.co.jemos.podam.test.dto.NonInstantiatableClass;

/**
 * @author kurtinaitis
 * 
 */
public class ConstructionFailureTest {

	@Test
	public void testConstructionFailure(){
		PodamFactory factory = new PodamFactoryImpl();
		NonInstantiatableClass innerClassPojo =
				factory.manufacturePojo(NonInstantiatableClass.class);
		Assert.assertNull(innerClassPojo);
	}
}
