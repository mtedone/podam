/**
 * 
 */
package uk.co.jemos.podam.test.unit;

import java.lang.reflect.Method;
import java.util.Collection;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import uk.co.jemos.podam.api.PodamFactoryImpl;
import uk.co.jemos.podam.api.PodamFactoryListener;
import uk.co.jemos.podam.test.dto.CollectionsPojo;
import uk.co.jemos.podam.test.dto.OneDimensionalTestPojo;
import uk.co.jemos.podam.test.dto.RecursivePojo;

/**
 * @author ganeshs
 *
 */
public class PodamFactoryListenerTest {
	
	private PodamFactoryImpl factory;
	
	private final AtomicBoolean ab = new AtomicBoolean(false);
	
	@Before
	public void setup() {
		 factory = new PodamFactoryImpl();
	}

	@Test
	public void testCollectionAttributeSet() {
		factory.registerListener(new PodamFactoryListener() {
			public void onAttributeSet(Object pojo, Method setter, Object value) {
				if (value instanceof Collection) {
					Collection<?> collection = ((Collection<?>) value);
					if (collection.iterator().next() instanceof OneDimensionalTestPojo) {
						ab.set(true);
					}
				}
			}
		});
		factory.manufacturePojo(CollectionsPojo.class);
		Assert.assertTrue(ab.get());
	}
	
	@Test
	public void testMapAttributeSet() {
		factory.registerListener(new PodamFactoryListener() {
			public void onAttributeSet(Object pojo, Method setter, Object value) {
				if (value instanceof Map) {
					Map<?, ?> map = ((Map<?, ?>) value);
					if (map.entrySet().iterator().next().getValue() instanceof OneDimensionalTestPojo) {
						ab.set(true);
					}
				}
			}
		});
		factory.manufacturePojo(CollectionsPojo.class);
		Assert.assertTrue(ab.get());
	}
	
	@Test
	public void testPrimitiveAttributeSet() {
		factory.registerListener(new PodamFactoryListener() {
			public void onAttributeSet(Object pojo, Method setter, Object value) {
				if (value instanceof Integer) {
					ab.set(true);
				}
			}
		});
		factory.manufacturePojo(OneDimensionalTestPojo.class);
		Assert.assertTrue(ab.get());
	}
	
	@Test
	public void testRecursiveAttributeSet() {
		final AtomicInteger ai = new AtomicInteger(0);
		factory.registerListener(new PodamFactoryListener() {
			public void onAttributeSet(Object pojo, Method setter, Object value) {
				if (value instanceof RecursivePojo) {
					ai.incrementAndGet();
				}
			}
		});
		factory.manufacturePojo(RecursivePojo.class);
		Assert.assertTrue(ai.get() == 2);
	}
}
