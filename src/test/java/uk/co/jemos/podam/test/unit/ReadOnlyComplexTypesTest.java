package uk.co.jemos.podam.test.unit;

import java.beans.beancontext.BeanContextServicesSupport;

import org.junit.Assert;
import org.junit.Test;

import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;
import uk.co.jemos.podam.test.dto.ReadOnlyAbstract;
import uk.co.jemos.podam.test.dto.ReadOnlyComplexTypesPojo;
import uk.co.jemos.podam.test.dto.ReadOnlyGenericComplexTypesPojo;
import uk.co.jemos.podam.test.dto.ReadOnlyRawFieldsPojo;
import uk.co.jemos.podam.test.dto.ReadOnlyWildcardFieldsPojo;
import uk.co.jemos.podam.test.utils.PodamTestUtils;

/**
 * @author daivanov
 *
 */
public class ReadOnlyComplexTypesTest {

	private static final PodamFactory factory = new PodamFactoryImpl();

	@Test
	public void testReadOnlyComplexTypesPojoInstantiation() {
		ReadOnlyComplexTypesPojo pojo = factory.manufacturePojo(ReadOnlyComplexTypesPojo.class);
		Assert.assertNotNull("Manufacturing failed", pojo);
		Assert.assertNotNull("List should be present", pojo.getList());
		Assert.assertNotNull("Map should be present", pojo.getMap());
		Assert.assertNotNull("Complex element should be present", pojo.getValue());
		PodamTestUtils.assertListElementsType(pojo.getList(), Integer.class);
		PodamTestUtils.assertMapElementsType(pojo.getMap(), Long.class, String.class);
		Assert.assertNotNull("Complex element should not be empty",
				pojo.getValue().getValue());
	}

	@Test
	public void testReadOnlyGenericComplexTypesPojoInstantiation() {
		ReadOnlyGenericComplexTypesPojo<?, ?, ?> pojo
				= factory.manufacturePojo(ReadOnlyGenericComplexTypesPojo.class,
						Character.class, Long.class, Integer.class);
		Assert.assertNotNull("Manufacturing failed", pojo);
		Assert.assertNotNull("List should be present", pojo.getList());
		Assert.assertNotNull("Map should be present", pojo.getMap());
		Assert.assertNotNull("Complex element should be present", pojo.getValue());
		PodamTestUtils.assertListElementsType(pojo.getList(), Long.class);
		PodamTestUtils.assertMapElementsType(pojo.getMap(), Integer.class, String.class);
		Assert.assertNotNull("Complex element should not be empty",
				pojo.getValue().getValue());
		Assert.assertEquals("Wrong element type", Character.class,
				pojo.getValue().getValue().getClass());
	}

	@Test
	public void testLoopInFillingReadOnlyFields() {
		Object pojo = factory.manufacturePojo(BeanContextServicesSupport.class);
		Assert.assertNotNull("Manufacturing failed", pojo);
	}

	@Test
	public void testAttributeWithWildcards() {
		ReadOnlyWildcardFieldsPojo pojo
				= factory.manufacturePojo(ReadOnlyWildcardFieldsPojo.class);
		Assert.assertNotNull("Manufacturing failed", pojo);
		PodamTestUtils.assertListElementsType(pojo.getList(), Object.class);
		PodamTestUtils.assertMapElementsType(pojo.getMap(), Object.class, Object.class);
	}

	@Test
	public void testAttributeWithRawTypes() {
		ReadOnlyRawFieldsPojo pojo
				= factory.manufacturePojo(ReadOnlyRawFieldsPojo.class);
		Assert.assertNotNull("Manufacturing failed", pojo);
		PodamTestUtils.assertListElementsType(pojo.getList(), Object.class);
		PodamTestUtils.assertMapElementsType(pojo.getMap(), Object.class, Object.class);
	}

	@Test
	public void testNonAccessibleAttribute() {
		ReadOnlyAbstract pojo
				= factory.manufacturePojo(ReadOnlyAbstract.class);
		Assert.assertNotNull("Manufacturing failed", pojo);
	}
}
