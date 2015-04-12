package uk.co.jemos.podam.test.unit;

import java.lang.reflect.Method;

import org.junit.Assert;
import org.junit.Test;

import uk.co.jemos.podam.api.AbstractClassInfoStrategy;
import uk.co.jemos.podam.api.ClassAttribute;
import uk.co.jemos.podam.api.ClassInfoStrategy;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;
import uk.co.jemos.podam.test.dto.ClassGenericConstructorPojo;
import uk.co.jemos.podam.test.dto.ClassGenericPojo;
import uk.co.jemos.podam.test.dto.ClassInheritedPojo;
import uk.co.jemos.podam.test.dto.ClassPojo;
import uk.co.jemos.podam.test.dto.NonEJBPojo;

/**
 * Test @uk.co.jemos.podam.test.dto.NonEJBPojo@ construction
 *
 * @author daivanov
 *
 */
public class NonEJBPojoTest {

	private static ClassInfoStrategy classInfoStrategy
			= new AbstractClassInfoStrategy() {

		@Override
		public boolean approve(ClassAttribute attribute) {

			/* EJB attributes: field and setter */
			if (attribute.getAttribute() != null) {
				return true;
			}

			/* Exclusion for NonEJBPojo class */
			for (Method setter : attribute.getSetters()) {
				if (NonEJBPojo.class.equals(setter.getDeclaringClass())) {
					return true;
				}
			}
			return false;
		}
	};

	private final static PodamFactory podam
			= new PodamFactoryImpl().setClassStrategy(classInfoStrategy);

	@Test
	public void testClassWithSpecificType() throws Exception {
	
		NonEJBPojo pojo = podam.manufacturePojo(NonEJBPojo.class);
		Assert.assertNotNull("Construction failed", pojo);
		Assert.assertNotNull("Attr should not be empty", pojo.getMyString());
		Assert.assertNotNull("Attr should not be empty", pojo.getMyLong());
	}
}
