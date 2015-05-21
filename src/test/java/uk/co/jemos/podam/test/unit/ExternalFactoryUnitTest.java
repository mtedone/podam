package uk.co.jemos.podam.test.unit;

import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import uk.co.jemos.podam.api.AbstractExternalFactory;
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

	private final static List<Class<?>> failures = new ArrayList<Class<?>>();

	private final static List<Class<?>> fullDataCalls = new ArrayList<Class<?>>();

	private final static PodamFactory externalFactory =
			new AbstractExternalFactory() {

				@Override
				public <T> T manufacturePojoWithFullData(Class<T> pojoClass,
						Type... genericTypeArgs) {
					fullDataCalls.add(pojoClass);
					return this.manufacturePojo(pojoClass, genericTypeArgs);
				}

				@Override
				public <T> T manufacturePojo(Class<T> pojoClass,
						Type... genericTypeArgs) {
					failures.add(pojoClass);
					return null;
				}

				@Override
				public <T> T populatePojo(T pojo, Type... genericTypeArgs) {
					return pojo;
				}
	};

	private final static PodamFactory podam = new PodamFactoryImpl(externalFactory);

	@After
	public void after() {
		fullDataCalls.clear();
		failures.clear();
		podam.getStrategy().clearMemoizationCache();
	}

	@Test
	public void testInterface() {

		InterfacePojo pojo = podam.manufacturePojo(InterfacePojo.class);
		Assert.assertNull("Should not produce interfaces", pojo);
		Assert.assertEquals("List " + failures.toString(), 1, failures.size());
		Assert.assertEquals("List " + failures.toString(), InterfacePojo.class, failures.get(0));
		Assert.assertEquals("List " + fullDataCalls.toString(), 0, fullDataCalls.size());
	}

	@Test
	public void testPojoWithInterfaces() {

		PojoWithInterfaces pojo = podam.manufacturePojo(PojoWithInterfaces.class);
		Assert.assertNotNull("Manufacturing failed", pojo);
		Assert.assertEquals("List " + failures.toString(), 2, failures.size());
		Assert.assertEquals("List " + failures.toString(), ObjectExt.class, failures.get(0));
		Assert.assertEquals("List " + failures.toString(), InterfacePojo.class, failures.get(1));
		Assert.assertEquals("List " + fullDataCalls.toString(), 0, fullDataCalls.size());
	}

	@Test
	public void testPojoWithInterfacesWithFullData() {

		PojoWithInterfaces pojo = podam.manufacturePojoWithFullData(PojoWithInterfaces.class);
		Assert.assertNotNull("Manufacturing failed", pojo);
		Assert.assertEquals("List " + failures.toString(), 2, failures.size());
		Assert.assertEquals("List " + failures.toString(), ObjectExt.class, failures.get(0));
		Assert.assertEquals("List " + failures.toString(), InterfacePojo.class, failures.get(1));
		Assert.assertEquals("List " + fullDataCalls.toString(), 2, fullDataCalls.size());
	}

	@Test
	public void testAbstractClass() {

		AbstractClass pojo = podam.manufacturePojo(AbstractClass.class);
		Assert.assertNull("Should not produce abstract classes", pojo);
		Assert.assertEquals("List " + failures.toString(), 1, failures.size());
		Assert.assertEquals("List " + failures.toString(), AbstractClass.class, failures.get(0));
		Assert.assertEquals("List " + fullDataCalls.toString(), 0, fullDataCalls.size());
	}

	@Test
	public void testAbstractClassWithFullData() {

		AbstractClass pojo = podam.manufacturePojoWithFullData(AbstractClass.class);
		Assert.assertNull("Should not produce abstract classes", pojo);
		Assert.assertEquals("List " + failures.toString(), 1, failures.size());
		Assert.assertEquals("List " + failures.toString(), AbstractClass.class, failures.get(0));
		Assert.assertEquals("List " + fullDataCalls.toString(), 1, fullDataCalls.size());
		Assert.assertEquals("List " + fullDataCalls.toString(), AbstractClass.class, failures.get(0));
	}

	@Test
	public void testNonInstantiatableClass() {

		NonInstantiatableClass pojo = podam.manufacturePojo(NonInstantiatableClass.class);
		Assert.assertNull("Should not produce non-instantiatable classes", pojo);
		Assert.assertEquals("List " + failures.toString(), 1, failures.size());
		Assert.assertEquals("List " + failures.toString(), NonInstantiatableClass.class, failures.get(0));
		Assert.assertEquals("List " + fullDataCalls.toString(), 0, fullDataCalls.size());
	}

	@Test
	public void testAbstractExternalFactoryMethods() {
		Assert.assertNull("Class strategy should be null",
				externalFactory.getClassStrategy());
		Assert.assertEquals("Should chain",
				externalFactory, externalFactory.setClassStrategy(null));
		Assert.assertNull("Strategy should be null",
				externalFactory.getStrategy());
		Assert.assertEquals("Should chain",
				externalFactory, externalFactory.setStrategy(null));
		Assert.assertNull("External factory should be null",
				externalFactory.getExternalFactory());
		Assert.assertEquals("Should chain",
				externalFactory, externalFactory.setExternalFactory(null));
	}
}
