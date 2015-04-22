package uk.co.jemos.podam.test.unit;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Test;

import uk.co.jemos.podam.api.ClassInfoStrategy;
import uk.co.jemos.podam.api.DataProviderStrategy;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;
import uk.co.jemos.podam.test.dto.AbstractTestPojo;
import uk.co.jemos.podam.test.dto.InterfacePojo;
import uk.co.jemos.podam.test.dto.NonInstantiatableClass;
import uk.co.jemos.podam.test.dto.ObjectExt;
import uk.co.jemos.podam.test.dto.PojoWithInterfaces;

/**
 * Test @uk.co.jemos.podam.test.dto.JAXBElementPojo@ construction
 *
 * @author daivanov
 *
 */
public class ExternalFactoryUnitTest {

	protected static final Type[] NO_TYPES = new Type[0];

	private static List<Class<?>> failures = new ArrayList<Class<?>>();

	private final static PodamFactory externalFactory =
			new PodamFactory() {

				@Override
				public <T> T manufacturePojo(Class<T> pojoClass,
						Type... genericTypeArgs) {
					failures.add(pojoClass);
					return null;
				}

				@Override
				public <T> T manufacturePojo(Class<T> pojoClass) {
					return this.manufacturePojo(pojoClass, NO_TYPES);
				}

				@Override
				public <T> T manufacturePojoWithFullData(Class<T> pojoClass,
						Type... genericTypeArgs) {
					return this.manufacturePojo(pojoClass, genericTypeArgs);
				}

				@Override
				public DataProviderStrategy getStrategy() {
					return null;
				}

				@Override
				public ClassInfoStrategy getClassStrategy() {
					return null;
				}

				@Override
				public PodamFactory setClassStrategy(
						ClassInfoStrategy classInfoStrategy) {
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

		AbstractTestPojo pojo = podam.manufacturePojo(AbstractTestPojo.class);
		Assert.assertNull("Should not produce abstract classes", pojo);
		Assert.assertEquals("List " + failures.toString(), 1, failures.size());
		Assert.assertEquals("List " + failures.toString(), AbstractTestPojo.class, failures.get(0));
	}

	@Test
	public void testNonInstantiatableClass() {

		NonInstantiatableClass pojo = podam.manufacturePojo(NonInstantiatableClass.class);
		Assert.assertNull("Should not produce non-instantiatable classes", pojo);
		Assert.assertEquals("List " + failures.toString(), 1, failures.size());
		Assert.assertEquals("List " + failures.toString(), NonInstantiatableClass.class, failures.get(0));
	}
}
