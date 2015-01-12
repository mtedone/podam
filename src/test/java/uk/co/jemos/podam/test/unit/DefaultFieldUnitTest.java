package uk.co.jemos.podam.test.unit;

import java.util.Map.Entry;
import java.util.TreeMap;

import org.junit.Assert;
import org.junit.Test;

import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;
import uk.co.jemos.podam.test.dto.DefaultFieldPojo;

/**
 * Test @uk.co.jemos.podam.test.dto.JAXBElementPojo@ construction
 *
 * @author daivanov
 *
 */
public class DefaultFieldUnitTest {

	private final static PodamFactory podam = new PodamFactoryImpl();

	@Test
	public void testDefaultFieldPojo() throws Exception {
	
		DefaultFieldPojo<?,?> pojo = podam.manufacturePojo(
				DefaultFieldPojo.class, String.class, Long.class);
		Assert.assertNotNull("Construction failed", pojo);
		Assert.assertNotNull("Field setting failed", pojo.getMap());
		Assert.assertEquals("Wrong type of field",
				TreeMap.class, pojo.getMap().getClass());
		for (Entry<?, ?> entry : pojo.getMap().entrySet()) {
			Assert.assertEquals("Wrong type of field's key",
					String.class, entry.getKey().getClass());
			Assert.assertEquals("Wrong type of field's key",
					Long.class, entry.getValue().getClass());
		}
	}

}
