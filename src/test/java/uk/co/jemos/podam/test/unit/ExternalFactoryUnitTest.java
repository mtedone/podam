package uk.co.jemos.podam.test.unit;

import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import uk.co.jemos.podam.api.AbstractExternalFactory;
import uk.co.jemos.podam.api.NullExternalFactory;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;
import uk.co.jemos.podam.test.dto.*;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Test @uk.co.jemos.podam.test.dto.JAXBElementPojo@ construction
 *
 * @author daivanov
 *
 */
public class ExternalFactoryUnitTest {

	private static List<Class<?>> failures = new ArrayList<Class<?>>();

	private final static PodamFactory externalFactory =
			new AbstractExternalFactory() {

				@Override
				public <T> T manufacturePojo(Class<T> pojoClass,
						Type... genericTypeArgs) {
					failures.add(pojoClass);
					return null;
				}
	};

	private final static PodamFactory podam = new PodamFactoryImpl(externalFactory);

	@After
	public void after() {
		failures.clear();
	}

	@Test
	public void testInterface() {

		InterfacePojo pojo = podam.manufacturePojo(InterfacePojo.class);
		Assert.assertNull("Should not produce interfaces", pojo);
		Assert.assertEquals("List " + failures.toString(), 1, failures.size());
		Assert.assertEquals("List " + failures.toString(), InterfacePojo.class, failures.get(0));
	}

	@Test
	public void testPojoWithInterfaces() {

		PojoWithInterfaces pojo = podam.manufacturePojo(PojoWithInterfaces.class);
		Assert.assertNotNull("Manufacturing failed", pojo);
		Assert.assertEquals("List " + failures.toString(), 2, failures.size());
		Assert.assertEquals("List " + failures.toString(), ObjectExt.class, failures.get(0));
		Assert.assertEquals("List " + failures.toString(), InterfacePojo.class, failures.get(1));
	}

	@Test
	public void testAbstractClass() {

		AbstractClass pojo = podam.manufacturePojo(AbstractClass.class);
		Assert.assertNull("Should not produce abstract classes", pojo);
		Assert.assertEquals("List " + failures.toString(), 1, failures.size());
		Assert.assertEquals("List " + failures.toString(), AbstractClass.class, failures.get(0));
	}

	@Test
	public void testNonInstantiatableClass() {

		NonInstantiatableClass pojo = podam.manufacturePojo(NonInstantiatableClass.class);
		Assert.assertNull("Should not produce non-instantiatable classes", pojo);
		Assert.assertEquals("List " + failures.toString(), 1, failures.size());
		Assert.assertEquals("List " + failures.toString(), NonInstantiatableClass.class, failures.get(0));
	}

	@Test
	public void testAbstractExternalFactoryMethods() {
		Assert.assertNull(externalFactory.getClassStrategy());
		Assert.assertNull(externalFactory.getStrategy());
		Assert.assertNull(externalFactory.setClassStrategy(null));
	}

	@Test
	public void testExternalFactoryWithFullConstructor() {
		NullExternalFactory factory = NullExternalFactory.getInstance();
		PodamFactory newPodam = new PodamFactoryImpl(factory);
		PojoWithInterfaces pojo = newPodam.manufacturePojoWithFullData(PojoWithInterfaces.class);
		Assert.assertNotNull(pojo);
	}
}
