package uk.co.jemos.podam.test.unit;

import org.junit.Assert;
import org.junit.Test;

import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

/**
 * @author kurtinaitis
 * 
 */
public class ConstructionFailureTest {

	public static class NonInstantiatableClass {
		private String name;

		public NonInstantiatableClass() throws Exception {
			throw new Exception();
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getName() {
			return name;
		}
	}

	@Test
	public void testConstructionFailure(){
		PodamFactory factory = new PodamFactoryImpl();
		NonInstantiatableClass innerClassPojo =
				factory.manufacturePojo(NonInstantiatableClass.class);
		Assert.assertNull(innerClassPojo);
	}
}
