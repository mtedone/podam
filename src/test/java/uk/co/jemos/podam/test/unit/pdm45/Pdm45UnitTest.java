/**
 * 
 */
package uk.co.jemos.podam.test.unit.pdm45;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;
import uk.co.jemos.podam.api.RandomDataProviderStrategyImpl;
import uk.co.jemos.podam.test.dto.PodamParameterizedType;
import uk.co.jemos.podam.test.dto.pdm45.GenericArrayPojo;
import uk.co.jemos.podam.test.dto.pdm45.GenericAttributePojo;
import uk.co.jemos.podam.test.dto.pdm45.GenericListPojo;
import uk.co.jemos.podam.test.dto.pdm45.GenericMapPojo;
import uk.co.jemos.podam.test.dto.pdm45.GenericPojo;
import uk.co.jemos.podam.test.dto.pdm45.MultiDimensionalConstructorPojo;
import uk.co.jemos.podam.test.dto.pdm45.MultiDimensionalTestPojo;

import java.lang.reflect.ParameterizedType;
import java.util.*;
import java.util.Map.Entry;

/**
 * Unit tests for <a href="http://www.jemos.eu/jira/browse/PDM-45">PDM-45</a>.
 * 
 * @author marciocarmona
 * 
 */
public class Pdm45UnitTest {

	/** The podam factory */
	private PodamFactory factory;

	@Before
	public void init() {

		factory = new PodamFactoryImpl(new RandomDataProviderStrategyImpl());

	}

	@Test
	public void testGenericListPojoManufacture() {
		@SuppressWarnings("unchecked")
		final GenericListPojo<Double, Boolean> pojo = factory.manufacturePojo(GenericListPojo.class, Double.class, Boolean.class);
		Assert.assertNotNull("The GenericPojo object cannot be null!", pojo);

		List<GenericPojo<Double, Boolean>> pojos = pojo.getGenericPojos();
		for (GenericPojo<Double, Boolean> element : pojos) {
			validateGenericPojo(element, Double.class, Boolean.class);
		}
	}

	@Test
	public void testGenericMapPojoManufacture() {
		@SuppressWarnings("unchecked")
		final GenericMapPojo<Double, Boolean> pojo = factory.manufacturePojo(GenericMapPojo.class, Double.class, Boolean.class);
		Assert.assertNotNull("The GenericPojo object cannot be null!", pojo);

		Map<String,GenericPojo<Double, Boolean>> pojos = pojo.getGenericPojos();
		for (GenericPojo<Double, Boolean> element : pojos.values()) {
			validateGenericPojo(element, Double.class, Boolean.class);
		}
	}

	@Test
	public void testGenericArrayPojoManufacture() {
		@SuppressWarnings("unchecked")
		final GenericArrayPojo<Double, Boolean> pojo = factory.manufacturePojo(GenericArrayPojo.class, Double.class, Boolean.class);
		Assert.assertNotNull("The GenericPojo object cannot be null!", pojo);

		GenericPojo<Double, Boolean>[] pojos = pojo.getGenericPojos();
		for (GenericPojo<Double, Boolean> element : pojos) {
			validateGenericPojo(element, Double.class, Boolean.class);
		}
	}

	@Test
	public void testPojoWithGenericFields() {
		final GenericAttributePojo pojo = factory.manufacturePojo(GenericAttributePojo.class);
		Assert.assertNotNull("The GenericPojo object cannot be null!", pojo);

		final GenericPojo<String, Long> genericPojo = pojo.getGenericPojo();
		validateGenericPojo(genericPojo, String.class, Long.class);
	}

	@Test
	public void testGenericPojoManufacture() {
		@SuppressWarnings("unchecked")
		final GenericPojo<Double, Boolean> pojo = factory.manufacturePojo(GenericPojo.class, Double.class, Boolean.class);
		validateGenericPojo(pojo, Double.class, Boolean.class);
	}

	private void validateGenericPojo(GenericPojo<?,?> pojo, Class<?> typeParam1, Class<?> typeParam2) {

		Assert.assertNotNull("The GenericPojo object cannot be null!", pojo);
		
		Assert.assertNotNull("The generated object cannot be null!", pojo.getFirstValue());
		Assert.assertEquals("The generated object must be a Double!", typeParam1, pojo.getFirstValue().getClass());
		Assert.assertNotNull("The generated object cannot be null!", pojo.getSecondValue());
		Assert.assertEquals("The generated object must be a Boolean!", typeParam2, pojo.getSecondValue().getClass());
		Assert.assertNotNull("The generated list cannot be null!", pojo.getFirstList());
		Assert.assertEquals("The generated list type must be of Double!", typeParam1, pojo.getFirstList().get(0).getClass());
		Assert.assertNotNull("The generated array cannot be null!", pojo.getSecondArray());
		Assert.assertEquals("The generated array type must be of Boolean!", typeParam2, pojo.getSecondArray()[0].getClass());
		Assert.assertNotNull("The generated map cannot be null!", pojo.getFirstSecondMap());
		Assert.assertEquals("The generated map key type must be of Double!", typeParam1,
				pojo.getFirstSecondMap().entrySet().iterator().next().getKey().getClass());
		Assert.assertEquals("The generated map value type must be of Boolean!", typeParam2,
				pojo.getFirstSecondMap().entrySet().iterator().next().getValue().getClass());
	}

	@Test
	public void testMultiDimensionalTestPojo() {
		final MultiDimensionalTestPojo pojo = factory.manufacturePojo(MultiDimensionalTestPojo.class);
		
		checkMultiDimensionalPojo(pojo);
	}

	@Test
	public void testConstructorMultiDimensionalPojo() {
		final MultiDimensionalConstructorPojo pojo = factory.manufacturePojo(MultiDimensionalConstructorPojo.class);
		
		checkMultiDimensionalPojo(pojo);
	}
	
	@Test
	@SuppressWarnings("unchecked")
	public void testMultiDimensionalPojoManufacture() {
		ParameterizedType twoDimensionalStringListType =
				new PodamParameterizedType(List.class, 
						new PodamParameterizedType(List.class,
								String.class));
		ParameterizedType longDoubleMapType =
				new PodamParameterizedType(Map.class, Long.class, Double.class);
		
		final GenericPojo<List<List<String>>, Map<Long, Double>> pojo =
				factory.manufacturePojo(GenericPojo.class, twoDimensionalStringListType, longDoubleMapType);
		
		Assert.assertNotNull("The GenericPojo object cannot be null!", pojo);
		
		Assert.assertNotNull("The generated object cannot be null!", pojo.getFirstValue());
		Assert.assertEquals("The generated object must be a String!", String.class,
				pojo.getFirstValue().get(0).get(0).getClass());
		Assert.assertNotNull("The generated object cannot be null!", pojo.getSecondValue());
		Assert.assertEquals("The generated object must be a Long!", Long.class,
				pojo.getSecondValue().keySet().iterator().next().getClass());
		Assert.assertEquals("The generated object must be a Double!", Double.class,
				pojo.getSecondValue().values().iterator().next().getClass());
		Assert.assertNotNull("The generated list cannot be null!", pojo.getFirstList());
		Assert.assertEquals("The generated list type must be of String!", String.class,
				pojo.getFirstList().get(0).get(0).get(0).getClass());
		Assert.assertNotNull("The generated array cannot be null!", pojo.getSecondArray());
		Assert.assertEquals("The generated array type must be of Long!", Long.class,
				pojo.getSecondArray()[0].keySet().iterator().next().getClass());
		Assert.assertEquals("The generated array type must be of Double!", Double.class,
				pojo.getSecondArray()[0].values().iterator().next().getClass());
		Assert.assertNotNull("The generated map cannot be null!", pojo.getFirstSecondMap());
		Assert.assertEquals("The generated map key type must be of String!", String.class,
				pojo.getFirstSecondMap().entrySet().iterator().next().getKey().get(0).get(0).getClass());
		Assert.assertEquals("The generated map value type must be of Long!", Long.class,
				pojo.getFirstSecondMap().entrySet().iterator().next().getValue().keySet().iterator().next().getClass());
		Assert.assertEquals("The generated map value type must be of Double!", Double.class,
				pojo.getFirstSecondMap().entrySet().iterator().next().getValue().values().iterator().next().getClass());
	}

	/**
	 * It validates a {@link MultiDimensionalTestPojo}.
	 * 
	 * @param pojo the pojo to validate
	 */
	private void checkMultiDimensionalPojo(final MultiDimensionalTestPojo pojo) {
		Assert.assertNotNull("The GenericPojo object cannot be null!", pojo);
		
		checkMultiDimensionalCollection(pojo.getThreeDimensionalList(), String.class);
		checkMultiDimensionalCollection(pojo.getThreeDimensionalQueue(), Date.class);
		checkMultiDimensionalCollection(pojo.getThreeDimensionalSet(), Double.class);
		checkMultiDimensionalCollection(pojo.getThreeDimensionalCollection(), Long.class);
		
		Assert.assertEquals("The generated Array must have size=2!", 2, pojo.getThreeDimensionalArray().length);
		Assert.assertEquals("The generated Array must have size=2!", 2, pojo.getThreeDimensionalArray()[0].length);
		Assert.assertEquals("The generated Array must have size=2!", 2, pojo.getThreeDimensionalArray()[0][0].length);
		Assert.assertEquals("The generated Array must be of String!", String.class,
				pojo.getThreeDimensionalArray()[0][0][0].getClass());
		
		// Boolean key is always true, so just have one element
		Assert.assertEquals("The generated Map must have size=1!", 1, pojo.getThreeDimensionalMap().size());
		Entry<Boolean, Map<Float, Map<Integer, Calendar>>> entry =
				pojo.getThreeDimensionalMap().entrySet().iterator().next();
		Assert.assertEquals("The generated Map entry key must be of Boolean!", Boolean.class, entry.getKey().getClass());
		Assert.assertEquals("The generated Map must have size=2!", 2, entry.getValue().size());
		Entry<Float, Map<Integer, Calendar>> entry2 = entry.getValue().entrySet().iterator().next();
		Assert.assertEquals("The generated Map entry key must be of Float!", Float.class, entry2.getKey().getClass());
		Assert.assertEquals("The generated Map must have size=2!", 2, entry2.getValue().size());
		Entry<Integer, Calendar> entry3 = entry2.getValue().entrySet().iterator().next();
		Assert.assertEquals("The generated Map entry key must be of Integer!", Integer.class, entry3.getKey().getClass());
		Assert.assertEquals("The generated Map entry key must be of Calendar!", GregorianCalendar.class, entry3.getValue().getClass());
	}

	/**
	 * It validates a {@link MultiDimensionalTestPojo} collection.
	 * 
	 * @param collection the collection to validate
	 * @param type the type of the class to validate
     */
	@SuppressWarnings("unchecked")
	private <T> void checkMultiDimensionalCollection(final Collection<?> collection, Class<T> type) {
		Assert.assertEquals("The generated List must have size=2!", 2, collection.size());
		Collection<?> subcollection = (Collection<?>)collection.iterator().next();
		Assert.assertEquals("The generated List must have size=2!", 2, subcollection.size());
		subcollection = (Collection<?>)subcollection.iterator().next();
		Assert.assertEquals("The generated List must have size=2!", 2, subcollection.size());
		T element = (T) subcollection.iterator().next();
		Assert.assertEquals("The generated List must be of " + type + "!", type, element.getClass());
	}

}
