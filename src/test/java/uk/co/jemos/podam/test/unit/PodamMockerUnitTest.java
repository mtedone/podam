package uk.co.jemos.podam.test.unit;


import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;
import uk.co.jemos.podam.api.RandomDataProviderStrategy;
import uk.co.jemos.podam.test.dto.*;
import uk.co.jemos.podam.test.dto.EnumsPojo.RatePodamInternal;
import uk.co.jemos.podam.test.dto.annotations.*;
import uk.co.jemos.podam.test.dto.pdm33.NoDefaultPublicConstructorPojo;
import uk.co.jemos.podam.test.dto.pdm33.PrivateOnlyConstructorPojo;
import uk.co.jemos.podam.test.dto.pdm33.ProtectedNonDefaultConstructorPojo;
import uk.co.jemos.podam.test.enums.ExternalRatePodamEnum;
import uk.co.jemos.podam.test.strategies.ByteArrayStrategy;
import uk.co.jemos.podam.test.utils.PodamTestConstants;
import uk.co.jemos.podam.test.utils.PodamTestUtils;

import java.math.BigDecimal;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * Unit test for simple App.
 */
public class PodamMockerUnitTest {

	/** The podam factory */
	private static final PodamFactory factory = new PodamFactoryImpl();

	/** The default data strategy */
	private static final RandomDataProviderStrategy strategy
			= (RandomDataProviderStrategy) factory.getStrategy();

	/** Backup of memoization setting */
	private final static boolean memoizationBackup = strategy.isMemoizationEnabled();

	/**
	 * Restores memoization settings after test run
	 */
	@After
	public void cleanup() {
		strategy.setMemoization(memoizationBackup);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testIntegerValueAnnotationWithNumberFormatError() {
		factory.manufacturePojo(IntegerValueWithErrorPojo.class);
	}


	@Test(expected = IllegalArgumentException.class)
	public void testLongValueAnnotationWithNumberFormatException() {
		factory.manufacturePojo(LongValueWithErrorPojo.class);
	}

	@Test
	public void testCollectionsPojo() {

		CollectionsPojo pojo = factory.manufacturePojo(CollectionsPojo.class);
		Assert.assertNotNull("The POJO cannot be null!", pojo);
		List<String> strList = pojo.getStrList();
		validateReturnedList(strList);
		ArrayList<String> arrayListStr = pojo.getArrayListStr();
		validateReturnedList(arrayListStr);
		List<String> copyOnWriteList = pojo.getCopyOnWriteList();
		validateReturnedList(copyOnWriteList);
		HashSet<String> hashSetStr = pojo.getHashSetStr();
		validateReturnedSet(hashSetStr);
		List<String> listStrCollection = new ArrayList<String>(
				pojo.getStrCollection());
		validateReturnedList(listStrCollection);
		Set<String> setStrCollection = new HashSet<String>(
				pojo.getStrCollection());
		validateReturnedSet(setStrCollection);
		Set<String> strSet = pojo.getStrSet();
		validateReturnedSet(strSet);
		Map<String, OneDimensionalTestPojo> map = pojo.getMap();
		validateHashMap(map);
		HashMap<String, OneDimensionalTestPojo> hashMap = pojo.getHashMap();
		validateHashMap(hashMap);
		ConcurrentMap<String, OneDimensionalTestPojo> concurrentHashMap = pojo
				.getConcurrentHashMap();
		validateConcurrentHashMap(concurrentHashMap);
		ConcurrentHashMap<String, OneDimensionalTestPojo> concurrentHashMapImpl = pojo
				.getConcurrentHashMapImpl();
		validateConcurrentHashMap(concurrentHashMapImpl);
		Queue<SimplePojoToTestSetters> queue = pojo.getQueue();
		Assert.assertNotNull("The queue cannot be null!", queue);
		Assert.assertTrue("The queue must be an instance of LinkedList",
				queue instanceof LinkedList);
		SimplePojoToTestSetters pojoQueueElement = queue.poll();
		Assert.assertNotNull("The queue element cannot be null!",
				pojoQueueElement);
		@SuppressWarnings("rawtypes")
		List nonGenerifiedList = pojo.getNonGenerifiedList();
		Assert.assertNotNull("The non generified list cannot be null!",
				nonGenerifiedList);
		Assert.assertFalse("The non-generified list cannot be empty!",
				nonGenerifiedList.isEmpty());

		Map<?,?> nonGenerifiedMap = pojo.getNonGenerifiedMap();
		Assert.assertNotNull("The non generified map cannot be null!",
				nonGenerifiedMap);
		Assert.assertFalse("The non generified Map cannot be empty!",
				nonGenerifiedMap.isEmpty());
		Object object = nonGenerifiedMap.get(nonGenerifiedMap.keySet()
				.iterator().next());
		Assert.assertNotNull(
				"The object element within the Map cannot be null!", object);

	}

	@Test
	public void testPojoWithNoSettersAndCollectionInConstructor() {

		NoSetterWithCollectionInConstructorPojo pojo = factory
				.manufacturePojo(NoSetterWithCollectionInConstructorPojo.class);
		Assert.assertNotNull("The POJO cannot be null!", pojo);
		List<String> strList = pojo.getStrList();
		Assert.assertNotNull(
				"The collection of Strings in the constructor cannot be null!",
				strList);
		Assert.assertFalse(
				"The collection of Strings in the constructor cannot be empty!",
				strList.isEmpty());
		String strElement = strList.get(0);
		Assert.assertNotNull("The collection element cannot be null!",
				strElement);

		int intField = pojo.getIntField();
		Assert.assertTrue(
				"The int field in the constructor must be different from zero",
				intField != 0);

	}

	@Test
	public void testByteValueAnnotation() {

		ByteValuePojo pojo = factory.manufacturePojo(ByteValuePojo.class);
		Assert.assertNotNull("The Pojo cannot be null!", pojo);
		byte byteFieldWithMinValueOnly = pojo.getByteFieldWithMinValueOnly();
		Assert.assertTrue(
				"The byte field with min value only should have a minimum value of zero!",
				byteFieldWithMinValueOnly >= PodamTestConstants.NUMBER_INT_MIN_VALUE);
		byte byteFieldWithMaxValueOnly = pojo.getByteFieldWithMaxValueOnly();
		Assert.assertTrue(
				"The byte field value cannot be greater than: "
						+ PodamTestConstants.NUMBER_INT_ONE_HUNDRED,
						byteFieldWithMaxValueOnly <= PodamTestConstants.NUMBER_INT_ONE_HUNDRED);
		byte byteFieldWithMinAndMaxValue = pojo
				.getByteFieldWithMinAndMaxValue();
		Assert.assertTrue(
				"The byte field value must be between: "
						+ PodamTestConstants.NUMBER_INT_MIN_VALUE + " and "
						+ PodamTestConstants.NUMBER_INT_ONE_HUNDRED,
						byteFieldWithMinAndMaxValue >= PodamTestConstants.NUMBER_INT_MIN_VALUE
						&& byteFieldWithMinAndMaxValue <= PodamTestConstants.NUMBER_INT_ONE_HUNDRED);
		Byte byteObjectFieldWithMinValueOnly = pojo
				.getByteObjectFieldWithMinValueOnly();
		Assert.assertNotNull(
				"The byte object with min value only cannot be null!",
				byteObjectFieldWithMinValueOnly);
		Assert.assertTrue(
				"The byte object value must be greate or equal than: "
						+ PodamTestConstants.NUMBER_INT_MIN_VALUE,
						byteObjectFieldWithMinValueOnly >= PodamTestConstants.NUMBER_INT_MIN_VALUE);

		Byte byteObjectFieldWithMaxValueOnly = pojo
				.getByteObjectFieldWithMaxValueOnly();
		Assert.assertNotNull("The byte object field cannot be null",
				byteObjectFieldWithMaxValueOnly);
		Assert.assertTrue(
				"The byte object field must have a value less or equal to  "
						+ PodamTestConstants.NUMBER_INT_ONE_HUNDRED,
						byteObjectFieldWithMaxValueOnly <= PodamTestConstants.NUMBER_INT_ONE_HUNDRED);

		Byte byteObjectFieldWithMinAndMaxValue = pojo
				.getByteObjectFieldWithMinAndMaxValue();
		Assert.assertNotNull("The byte object must not be null!",
				byteObjectFieldWithMinAndMaxValue);
		Assert.assertTrue(
				"The byte object must have a value between: "
						+ PodamTestConstants.NUMBER_INT_MIN_VALUE + " and "
						+ PodamTestConstants.NUMBER_INT_MAX_VALUE,
						byteObjectFieldWithMinAndMaxValue >= PodamTestConstants.NUMBER_INT_MIN_VALUE
						&& byteObjectFieldWithMinAndMaxValue <= PodamTestConstants.NUMBER_INT_MAX_VALUE);
		byte byteFieldWithPreciseValue = pojo.getByteFieldWithPreciseValue();
		Assert.assertTrue("The byte with precise value should have value: "
				+ PodamTestConstants.BYTE_PRECISE_VALUE,
				byteFieldWithPreciseValue == Byte
				.valueOf(PodamTestConstants.BYTE_PRECISE_VALUE));

	}

	@Test(expected = IllegalArgumentException.class)
	public void testByteAnnotationWithNumberFormatError() {
		factory.manufacturePojo(ByteValueWithErrorPojo.class);
	}

	@Test
	public void testShortValueAnnotation() {

		ShortValuePojo pojo = factory.manufacturePojo(ShortValuePojo.class);
		Assert.assertNotNull("The Pojo cannot be null!", pojo);

		short shortFieldWithMinValueOnly = pojo.getShortFieldWithMinValueOnly();
		Assert.assertTrue(
				"The short attribute with min value only should have a value greater than "
						+ PodamTestConstants.NUMBER_INT_MIN_VALUE,
						shortFieldWithMinValueOnly >= PodamTestConstants.NUMBER_INT_MIN_VALUE);

		short shortFieldWithMaxValueOnly = pojo.getShortFieldWithMaxValueOnly();
		Assert.assertTrue(
				"The short attribute with max value only should have a value less than: "
						+ PodamTestConstants.NUMBER_INT_ONE_HUNDRED,
						shortFieldWithMaxValueOnly <= PodamTestConstants.NUMBER_INT_ONE_HUNDRED);

		short shortFieldWithMinAndMaxValue = pojo
				.getShortFieldWithMinAndMaxValue();
		Assert.assertTrue(
				"The short field with min and max values should have a value beetween "
						+ PodamTestConstants.NUMBER_INT_MIN_VALUE + " and "
						+ PodamTestConstants.NUMBER_INT_MAX_VALUE,
						shortFieldWithMinAndMaxValue >= PodamTestConstants.NUMBER_INT_MIN_VALUE
						&& shortFieldWithMinAndMaxValue <= PodamTestConstants.NUMBER_INT_ONE_HUNDRED);

		Short shortObjectFieldWithMinValueOnly = pojo
				.getShortObjectFieldWithMinValueOnly();
		Assert.assertNotNull(
				"The short object field with min value only should not be null!",
				shortObjectFieldWithMinValueOnly);
		Assert.assertTrue(
				"The short object attribute with min value only should have a value greater than "
						+ PodamTestConstants.NUMBER_INT_MIN_VALUE,
						shortObjectFieldWithMinValueOnly >= PodamTestConstants.NUMBER_INT_MIN_VALUE);

		Short shortObjectFieldWithMaxValueOnly = pojo
				.getShortObjectFieldWithMaxValueOnly();
		Assert.assertNotNull(
				"The short object field with max value only should not be null!",
				shortObjectFieldWithMaxValueOnly);
		Assert.assertTrue(
				"The short object attribute with max value only should have a value less than: "
						+ PodamTestConstants.NUMBER_INT_ONE_HUNDRED,
						shortObjectFieldWithMaxValueOnly <= PodamTestConstants.NUMBER_INT_ONE_HUNDRED);

		Short shortObjectFieldWithMinAndMaxValue = pojo
				.getShortObjectFieldWithMinAndMaxValue();
		Assert.assertNotNull(
				"The short object field with max value only should not be null!",
				shortObjectFieldWithMinAndMaxValue);
		Assert.assertTrue(
				"The short object field with min and max values should have a value beetween "
						+ PodamTestConstants.NUMBER_INT_MIN_VALUE + " and "
						+ PodamTestConstants.NUMBER_INT_ONE_HUNDRED,
						shortObjectFieldWithMinAndMaxValue >= PodamTestConstants.NUMBER_INT_MIN_VALUE
						&& shortObjectFieldWithMinAndMaxValue <= PodamTestConstants.NUMBER_INT_ONE_HUNDRED);

		short shortFieldWithPreciseValue = pojo.getShortFieldWithPreciseValue();
		Assert.assertTrue(
				"The short attribute with precise value should have a value of "
						+ PodamTestConstants.SHORT_PRECISE_VALUE
						+ " but instead it had a value of "
						+ shortFieldWithPreciseValue,
						shortFieldWithPreciseValue == Short
						.valueOf(PodamTestConstants.SHORT_PRECISE_VALUE));

	}

	@Test(expected = IllegalArgumentException.class)
	public void testShortValueAnnotationWithNumberFormatException() {
		factory.manufacturePojo(ShortValueWithErrorPojo.class);
	}

	@Test
	public void testCharacterValueAnnotation() {

		CharValuePojo pojo = factory.manufacturePojo(CharValuePojo.class);
		Assert.assertNotNull("The pojo cannot be null!", pojo);

		char charFieldWithMinValueOnly = pojo.getCharFieldWithMinValueOnly();
		Assert.assertTrue(
				"The char attribute with min value only should have a value greater than "
						+ PodamTestConstants.NUMBER_INT_MIN_VALUE,
						charFieldWithMinValueOnly >= PodamTestConstants.NUMBER_INT_MIN_VALUE);

		char charFieldWithMaxValueOnly = pojo.getCharFieldWithMaxValueOnly();
		Assert.assertTrue(
				"The char attribute with max value only should have a value less or equal than "
						+ PodamTestConstants.NUMBER_INT_ONE_HUNDRED,
						charFieldWithMaxValueOnly <= PodamTestConstants.NUMBER_INT_ONE_HUNDRED);

		char charFieldWithMinAndMaxValue = pojo
				.getCharFieldWithMinAndMaxValue();
		Assert.assertTrue(
				"The char attribute with min and max value must have a value between "
						+ PodamTestConstants.NUMBER_INT_MIN_VALUE + " and "
						+ PodamTestConstants.NUMBER_INT_ONE_HUNDRED,
						charFieldWithMinAndMaxValue >= PodamTestConstants.NUMBER_INT_MIN_VALUE
						&& charFieldWithMinAndMaxValue <= PodamTestConstants.NUMBER_INT_ONE_HUNDRED);

		Character charObjectFieldWithMinValueOnly = pojo
				.getCharObjectFieldWithMinValueOnly();
		Assert.assertNotNull(
				"The char object attribute with min value only  cannot be null!",
				charObjectFieldWithMinValueOnly);
		Assert.assertTrue(
				"The char object attribute with min value only should have a value greater than "
						+ PodamTestConstants.NUMBER_INT_MIN_VALUE,
						charObjectFieldWithMinValueOnly >= PodamTestConstants.NUMBER_INT_MIN_VALUE);

		Character charObjectFieldWithMaxValueOnly = pojo
				.getCharObjectFieldWithMaxValueOnly();
		Assert.assertNotNull(
				"The char object attribute with max value only cannot be null!",
				charObjectFieldWithMaxValueOnly);
		Assert.assertTrue(
				"The char object attribute with max value only should have a value less or equal than "
						+ PodamTestConstants.NUMBER_INT_ONE_HUNDRED,
						charObjectFieldWithMaxValueOnly <= PodamTestConstants.NUMBER_INT_ONE_HUNDRED);

		Character charObjectFieldWithMinAndMaxValue = pojo
				.getCharObjectFieldWithMinAndMaxValue();
		Assert.assertNotNull(
				"The char object attribute with min and max value cannot be null!",
				charObjectFieldWithMinAndMaxValue);
		Assert.assertTrue(
				"The char object attribute with min and max value must have a value between "
						+ PodamTestConstants.NUMBER_INT_MIN_VALUE + " and "
						+ PodamTestConstants.NUMBER_INT_ONE_HUNDRED,
						charObjectFieldWithMinAndMaxValue >= PodamTestConstants.NUMBER_INT_MIN_VALUE
						&& charObjectFieldWithMinAndMaxValue <= PodamTestConstants.NUMBER_INT_ONE_HUNDRED);

		char charFieldWithPreciseValue = pojo.getCharFieldWithPreciseValue();
		Assert.assertTrue(
				"The character field with precise value should have a value of "
						+ PodamTestConstants.CHAR_PRECISE_VALUE,
						charFieldWithPreciseValue == PodamTestConstants.CHAR_PRECISE_VALUE);

		char charFieldWithBlankInPreciseValue = pojo
				.getCharFieldWithBlankInPreciseValue();

		Assert.assertTrue(
				"The value for the char field with an empty char in the precise value and no other annotation attributes should be zero",
				charFieldWithBlankInPreciseValue == 0);
	}

	@Test
	public void testBooleanValueAnnotation() {

		BooleanValuePojo pojo = factory.manufacturePojo(BooleanValuePojo.class);
		Assert.assertNotNull("The pojo cannot be null!", pojo);

		boolean boolDefaultToTrue = pojo.isBoolDefaultToTrue();
		Assert.assertTrue(
				"The boolean attribute forced to true should be true!",
				boolDefaultToTrue);

		boolean boolDefaultToFalse = pojo.isBoolDefaultToFalse();
		Assert.assertFalse(
				"The boolean attribute forced to false should be false!",
				boolDefaultToFalse);

		Boolean boolObjectDefaultToFalse = pojo.getBoolObjectDefaultToFalse();
		Assert.assertNotNull(
				"The boolean object forced to false should not be null!",
				boolObjectDefaultToFalse);
		Assert.assertFalse(
				"The boolean object forced to false should have a value of false!",
				boolObjectDefaultToFalse);

		Boolean boolObjectDefaultToTrue = pojo.getBoolObjectDefaultToTrue();
		Assert.assertNotNull(
				"The boolean object forced to true should not be null!",
				boolObjectDefaultToTrue);
		Assert.assertTrue(
				"The boolean object forced to true should have a value of true!",
				boolObjectDefaultToTrue);

	}

	@Test
	public void testFloatValueAnnotation() {

		FloatValuePojo pojo = factory.manufacturePojo(FloatValuePojo.class);
		Assert.assertNotNull("The pojo cannot be null!", pojo);

		float floatFieldWithMinValueOnly = pojo.getFloatFieldWithMinValueOnly();
		Assert.assertTrue(
				"The float field with min value only must have value greater than "
						+ PodamTestConstants.NUMBER_FLOAT_MIN_VALUE,
						floatFieldWithMinValueOnly >= PodamTestConstants.NUMBER_FLOAT_MIN_VALUE);

		float floatFieldWithMaxValueOnly = pojo.getFloatFieldWithMaxValueOnly();
		Assert.assertTrue(
				"The float field with max value only can only have a value less or equal than "
						+ PodamTestConstants.NUMBER_FLOAT_ONE_HUNDRED,
						floatFieldWithMaxValueOnly <= PodamTestConstants.NUMBER_FLOAT_ONE_HUNDRED);

		float floatFieldWithMinAndMaxValue = pojo
				.getFloatFieldWithMinAndMaxValue();
		Assert.assertTrue(
				"The float field with min and max value must have a value between "
						+ PodamTestConstants.NUMBER_FLOAT_MIN_VALUE + " and "
						+ PodamTestConstants.NUMBER_FLOAT_MAX_VALUE,
						floatFieldWithMinAndMaxValue >= PodamTestConstants.NUMBER_FLOAT_MIN_VALUE
						&& floatFieldWithMinAndMaxValue <= PodamTestConstants.NUMBER_FLOAT_MAX_VALUE);

		Float floatObjectFieldWithMinValueOnly = pojo
				.getFloatObjectFieldWithMinValueOnly();
		Assert.assertNotNull(
				"The float object attribute with min value only cannot be null!",
				floatObjectFieldWithMinValueOnly);
		Assert.assertTrue(
				"The float object attribute with min value only must have a value greater or equal than "
						+ PodamTestConstants.NUMBER_FLOAT_MIN_VALUE,
						floatObjectFieldWithMinValueOnly >= PodamTestConstants.NUMBER_FLOAT_MIN_VALUE);

		Float floatObjectFieldWithMaxValueOnly = pojo
				.getFloatObjectFieldWithMaxValueOnly();
		Assert.assertNotNull(
				"The float object attribute with max value only cannot be null!",
				floatObjectFieldWithMaxValueOnly);
		Assert.assertTrue(
				"The float object attribute with max value only must have a value less than or equal to "
						+ PodamTestConstants.NUMBER_FLOAT_ONE_HUNDRED,
						floatObjectFieldWithMaxValueOnly <= PodamTestConstants.NUMBER_FLOAT_ONE_HUNDRED);

		Float floatObjectFieldWithMinAndMaxValue = pojo
				.getFloatObjectFieldWithMinAndMaxValue();
		Assert.assertNotNull(
				"The float object attribute with min and max value cannot be null!",
				floatObjectFieldWithMinAndMaxValue);
		Assert.assertTrue(
				"The float object attribute with min and max value only must have a value between "
						+ PodamTestConstants.NUMBER_FLOAT_MIN_VALUE
						+ " and "
						+ PodamTestConstants.NUMBER_FLOAT_MAX_VALUE,
						floatObjectFieldWithMinAndMaxValue >= PodamTestConstants.NUMBER_FLOAT_MIN_VALUE
						&& floatObjectFieldWithMinAndMaxValue <= PodamTestConstants.NUMBER_FLOAT_MAX_VALUE);

		float floatFieldWithPreciseValue = pojo.getFloatFieldWithPreciseValue();
		Assert.assertTrue(
				"The float field with precise value should have a value of "
						+ PodamTestConstants.FLOAT_PRECISE_VALUE,
						floatFieldWithPreciseValue == Float
						.valueOf(PodamTestConstants.FLOAT_PRECISE_VALUE));

		Float floatObjectFieldWithPreciseValue = pojo
				.getFloatObjectFieldWithPreciseValue();
		Assert.assertNotNull(
				"The float object field with precise value cannot be null!",
				floatObjectFieldWithPreciseValue);
		Assert.assertTrue(
				"The float object field with precise value should have a value of "
						+ PodamTestConstants.FLOAT_PRECISE_VALUE,
						floatObjectFieldWithPreciseValue.floatValue() == Float.valueOf(
								PodamTestConstants.FLOAT_PRECISE_VALUE).floatValue());

	}

	@Test(expected = IllegalArgumentException.class)
	public void testFloatValueAnnotationWithNumberFormatError() {
		factory.manufacturePojo(FloatValueWithErrorPojo.class);
	}

	@Test
	public void testDoubleValueAnnotation() {

		DoubleValuePojo pojo = factory.manufacturePojo(DoubleValuePojo.class);
		Assert.assertNotNull("The pojo cannot be null!", pojo);

		double doubleFieldWithMinValueOnly = pojo
				.getDoubleFieldWithMinValueOnly();
		Assert.assertTrue(
				"The double attribute with min value only must have a value greater than "
						+ PodamTestConstants.NUMBER_DOUBLE_MIN_VALUE,
						doubleFieldWithMinValueOnly >= PodamTestConstants.NUMBER_DOUBLE_MIN_VALUE);

		double doubleFieldWithMaxValueOnly = pojo
				.getDoubleFieldWithMaxValueOnly();
		Assert.assertTrue(
				"The double attribute with max value only must have a value less or equal to "
						+ PodamTestConstants.NUMBER_DOUBLE_ONE_HUNDRED,
						doubleFieldWithMaxValueOnly <= PodamTestConstants.NUMBER_DOUBLE_ONE_HUNDRED);

		double doubleFieldWithMinAndMaxValue = pojo
				.getDoubleFieldWithMinAndMaxValue();
		Assert.assertTrue(
				"The double attribute with min and mx value must have a value between "
						+ PodamTestConstants.NUMBER_DOUBLE_MIN_VALUE + " and "
						+ PodamTestConstants.NUMBER_DOUBLE_MAX_VALUE,
						doubleFieldWithMinAndMaxValue >= PodamTestConstants.NUMBER_DOUBLE_MIN_VALUE
						&& doubleFieldWithMinAndMaxValue <= PodamTestConstants.NUMBER_DOUBLE_MAX_VALUE);

		double doubleFieldWithPreciseValue = pojo
				.getDoubleFieldWithPreciseValue();
		Assert.assertTrue(
				"The double field with precise value should have a value of: "
						+ PodamTestConstants.DOUBLE_PRECISE_VALUE,
						doubleFieldWithPreciseValue == Double
						.valueOf(PodamTestConstants.DOUBLE_PRECISE_VALUE));

		Double doubleObjectFieldWithPreciseValue = pojo
				.getDoubleObjectFieldWithPreciseValue();
		Assert.assertNotNull(
				"The double object field with precise value cannot be null!",
				doubleObjectFieldWithPreciseValue);
		Assert.assertTrue(
				"The double object field with precise value should have a value of: "
						+ PodamTestConstants.DOUBLE_PRECISE_VALUE,
						doubleObjectFieldWithPreciseValue.doubleValue() == Double
						.valueOf(PodamTestConstants.DOUBLE_PRECISE_VALUE)
						.doubleValue());

	}

	@Test(expected = IllegalArgumentException.class)
	public void testDoubleValueAnnotationWithError() {

		factory.manufacturePojo(DoubleValueWithErrorPojo.class);

	}

	@Test
	public void testStringValueAnnotation() {

		StringValuePojo pojo = factory.manufacturePojo(StringValuePojo.class);
		String twentyLengthString = pojo.getTwentyLengthString();
		Assert.assertNotNull("The twentyLengthString cannot be null!",
				twentyLengthString);
		Assert.assertTrue(
				"The twenty length string must have a length of "
						+ PodamTestConstants.STR_ANNOTATION_TWENTY_LENGTH
						+ "! but it did have a length of "
						+ twentyLengthString.length(),
						twentyLengthString.length() == PodamTestConstants.STR_ANNOTATION_TWENTY_LENGTH);

		String preciseValueString = pojo.getPreciseValueString();
		Assert.assertNotNull("The precise value string cannot be null!",
				preciseValueString);
		Assert.assertEquals(
				"The expected and actual String values don't match",
				PodamTestConstants.STR_ANNOTATION_PRECISE_VALUE,
				preciseValueString);

	}

	@Test
	public void testCollectionAnnotation() {

		CollectionAnnotationPojo pojo = factory
				.manufacturePojo(CollectionAnnotationPojo.class);
		Assert.assertNotNull("The pojo cannot be null!", pojo);

		List<String> strList = pojo.getStrList();
		Assert.assertNotNull("The string list cannot be null!", strList);
		Assert.assertFalse("The string list cannot be empty!",
				strList.isEmpty());
		Assert.assertTrue(
				"The string list must have "
						+ PodamTestConstants.ANNOTATION_COLLECTION_NBR_ELEMENTS
						+ " elements but it had only " + strList.size(),
						strList.size() == PodamTestConstants.ANNOTATION_COLLECTION_NBR_ELEMENTS);

		String[] strArray = pojo.getStrArray();
		Assert.assertNotNull("The array cannot be null!", strArray);
		Assert.assertFalse("The array cannot be empty!", strArray.length == 0);
		Assert.assertTrue(
				"The number of elements in the array (" + strArray.length
				+ ") does not match "
				+ PodamTestConstants.ANNOTATION_COLLECTION_NBR_ELEMENTS,
				strArray.length == PodamTestConstants.ANNOTATION_COLLECTION_NBR_ELEMENTS);

		Map<String, String> stringMap = pojo.getStringMap();
		Assert.assertNotNull("The map cannot be null!", stringMap);
		Assert.assertFalse("The map of strings cannot be empty!",
				stringMap.isEmpty());
		Assert.assertTrue(
				"The number of elements in the map (" + stringMap.size()
				+ ") does not match "
				+ PodamTestConstants.ANNOTATION_COLLECTION_NBR_ELEMENTS,
				stringMap.size() == PodamTestConstants.ANNOTATION_COLLECTION_NBR_ELEMENTS);

	}

	@Test
	public void testImmutablePojoWithNonGenericCollections() {

		ImmutableWithNonGenericCollectionsPojo pojo = factory
				.manufacturePojo(ImmutableWithNonGenericCollectionsPojo.class);
		Assert.assertNotNull("The pojo cannot be null!", pojo);

		@SuppressWarnings("unchecked")
		Collection<Object> nonGenerifiedCollection = pojo
		.getNonGenerifiedCollection();
		Assert.assertNotNull("The non-generified collection cannot be null!",
				nonGenerifiedCollection);
		Assert.assertFalse("The non-generified collection cannot be empty!",
				nonGenerifiedCollection.isEmpty());
		Assert.assertTrue(
				"The number of elements in the collection: "
						+ nonGenerifiedCollection.size()
						+ " does not match the expected value: "
						+ ImmutableWithNonGenericCollectionsPojo.NBR_ELEMENTS,
						nonGenerifiedCollection.size() == ImmutableWithNonGenericCollectionsPojo.NBR_ELEMENTS);

		@SuppressWarnings("unchecked")
		Set<Object> nonGenerifiedSet = pojo.getNonGenerifiedSet();
		Assert.assertNotNull("The non-generified Set cannot be null!",
				nonGenerifiedSet);
		Assert.assertFalse("The non-generified Set cannot be empty!",
				nonGenerifiedSet.isEmpty());
		Assert.assertTrue(
				"The number of elements in the Set: " + nonGenerifiedSet.size()
				+ " does not match the expected value: "
				+ ImmutableWithNonGenericCollectionsPojo.NBR_ELEMENTS,
				nonGenerifiedSet.size() == ImmutableWithNonGenericCollectionsPojo.NBR_ELEMENTS);

		@SuppressWarnings("unchecked")
		Map<Object, Object> nonGenerifiedMap = pojo.getNonGenerifiedMap();
		Assert.assertNotNull("The non-generified map cannot be null!",
				nonGenerifiedMap);
		Assert.assertFalse("The non generified map cannot be empty!",
				nonGenerifiedMap.isEmpty());
		Assert.assertTrue(
				"The number of elements in the map: " + nonGenerifiedMap.size()
				+ " does not match the expected value: "
				+ ImmutableWithNonGenericCollectionsPojo.NBR_ELEMENTS,
				nonGenerifiedMap.size() == ImmutableWithNonGenericCollectionsPojo.NBR_ELEMENTS);

	}

	@Test
	public void testImmutablePojoWithGenerifiedCollectionsInConstructor() {

		strategy.setMemoization(false);

		ImmutableWithGenericCollectionsPojo pojo = factory
				.manufacturePojo(ImmutableWithGenericCollectionsPojo.class);
		Assert.assertNotNull("The pojo cannot be null!", pojo);

		Collection<OneDimensionalTestPojo> generifiedCollection = pojo
				.getGenerifiedCollection();
		Assert.assertNotNull("The generified collection cannot be null!",
				generifiedCollection);
		Assert.assertFalse("The generified collection cannot be empty!",
				generifiedCollection.isEmpty());
		Assert.assertTrue(
				"The number of elements in the generified collection: "
						+ generifiedCollection.size()
						+ " does not match the expected value: "
						+ ImmutableWithNonGenericCollectionsPojo.NBR_ELEMENTS,
						generifiedCollection.size() == ImmutableWithNonGenericCollectionsPojo.NBR_ELEMENTS);

		Map<String, Calendar> generifiedMap = pojo.getGenerifiedMap();
		Assert.assertNotNull("The generified map cannot be null!",
				generifiedMap);
		Assert.assertFalse("The generified map cannot be empty!",
				generifiedMap.isEmpty());
		Assert.assertTrue(
				"The number of elements in the generified map: "
						+ generifiedMap.size()
						+ " does not match the expected value: "
						+ ImmutableWithNonGenericCollectionsPojo.NBR_ELEMENTS,
						generifiedMap.size() == ImmutableWithNonGenericCollectionsPojo.NBR_ELEMENTS);

		Set<ImmutableWithNonGenericCollectionsPojo> generifiedSet = pojo
				.getGenerifiedSet();
		Assert.assertNotNull("The generified set cannot be null!",
				generifiedSet);
		Assert.assertFalse("The generified set cannot be empty!",
				generifiedSet.isEmpty());
		Assert.assertTrue(
				"The number of elements in the generified set: "
						+ generifiedSet.size()
						+ " does not match the expected value: "
						+ ImmutableWithNonGenericCollectionsPojo.NBR_ELEMENTS,
						generifiedSet.size() == ImmutableWithNonGenericCollectionsPojo.NBR_ELEMENTS);

	}

	@Test
	public void testSingletonWithParametersInPublicStaticMethod() {

		SingletonWithParametersInStaticFactoryPojo pojo = factory
				.manufacturePojo(SingletonWithParametersInStaticFactoryPojo.class);
		Assert.assertNotNull("The pojo cannot be null!", pojo);

		Assert.assertNotNull("The calendar object cannot be null!",
				pojo.getCreateDate());

		Assert.assertNotNull("The first name cannot be null!",
				pojo.getFirstName());

		List<OneDimensionalTestPojo> pojoList = pojo.getPojoList();
		Assert.assertNotNull("The pojo list cannot be null!", pojoList);
		Assert.assertFalse("The pojo list cannot be empty", pojoList.isEmpty());

		Map<String, OneDimensionalTestPojo> pojoMap = pojo.getPojoMap();
		Assert.assertNotNull("The pojo map cannot be null!", pojoMap);
		Assert.assertFalse("The pojo map cannot be empty!", pojoMap.isEmpty());

	}

	@Test
	public void testPojoWithEnums() {

		EnumsPojo pojo = factory.manufacturePojo(EnumsPojo.class);
		Assert.assertNotNull("The pojo cannot be null!", pojo);

		ExternalRatePodamEnum ratePodamExternal = pojo.getRatePodamExternal();
		Assert.assertNotNull("The external enum attribute cannot be null!",
				ratePodamExternal);

		RatePodamInternal ratePodamInternal = pojo.getRatePodamInternal();

		// Can't test for equality since internal enum is not visible
		Assert.assertNotNull("The internal enum cannot be null!",
				ratePodamInternal);

	}

	@Test
	public void testEnumPojo() {

		ExternalRatePodamEnum pojo = factory
				.manufacturePojo(ExternalRatePodamEnum.class);
		Assert.assertNotNull("The pojo cannot be null!", pojo);
	}

	@Test
	public void testPodamStrategyValueAnnotation() {

		PodamStrategyPojo pojo = factory
				.manufacturePojo(PodamStrategyPojo.class);
		Assert.assertNotNull("The post code pojo cannot be null!", pojo);

		String postCode = pojo.getPostCode();
		Assert.assertNotNull("The post code cannot be null!", postCode);
		Assert.assertEquals("The post code does not match the expected value",
				PodamTestConstants.POST_CODE, postCode);

		String postCode2 = pojo.getPostCode2();
		Assert.assertNotNull("The post code 2 cannot be null!", postCode2);
		Assert.assertEquals("The post code 2 does not match the expected value",
				PodamTestConstants.POST_CODE, postCode2);

		String postCode3 = pojo.getPostCode3();
		Assert.assertNotNull("The post code 3 cannot be null!", postCode3);
		Assert.assertEquals("The post code 3 does not match the expected value",
				PodamTestConstants.POST_CODE, postCode3);

		Calendar expectedBirthday = PodamTestUtils.getMyBirthday();

		Calendar myBirthday = pojo.getMyBirthday();

		Assert.assertNotNull("byte array manufacturing failed",
				pojo.getByteData());
		Assert.assertEquals("byte array wrong length",
				ByteArrayStrategy.LENGTH, pojo.getByteData().length);

		Assert.assertEquals(
				"The expected and actual calendar objects are not the same",
				expectedBirthday.getTime(), myBirthday.getTime());

		List<Calendar> myBirthdays = pojo.getMyBirthdays();
		Assert.assertNotNull("The birthdays collection cannot be null!",
				myBirthdays);
		Assert.assertFalse("The birthdays collection cannot be empty!",
				myBirthdays.isEmpty());

		for (Calendar birthday : myBirthdays) {
			Assert.assertEquals(
					"The expected birthday element does not match the actual",
					expectedBirthday.getTime(), birthday.getTime());
		}

		Calendar[] myBirthdaysArray = pojo.getMyBirthdaysArray();
		Assert.assertNotNull("The birthdays array cannot be null!",
				myBirthdaysArray);
		Assert.assertFalse("The birthdays array cannot be empty!",
				myBirthdaysArray.length == 0);

		for (Calendar birthday : myBirthdaysArray) {
			Assert.assertEquals(
					"The expected birthday element does not match the actual",
					expectedBirthday.getTime(), birthday.getTime());
		}

		List<Object> objectList = pojo.getObjectList();
		Assert.assertNotNull("The list of objects cannot be null!", objectList);
		Assert.assertFalse("The list of objects cannot be empty!",
				objectList.isEmpty());

		Object[] myObjectArray = pojo.getMyObjectArray();
		Assert.assertNotNull("The array of objects cannot be null!",
				myObjectArray);
		Assert.assertTrue("The array of objects cannot be empty",
				myObjectArray.length > 0);

		@SuppressWarnings("rawtypes")
		List nonGenericObjectList = pojo.getNonGenericObjectList();
		Assert.assertNotNull("The non generified object list cannot be null!",
				nonGenericObjectList);
		Assert.assertFalse("The non generified object list cannot be empty!",
				nonGenericObjectList.isEmpty());

		Map<String, Calendar> myBirthdaysMap = pojo.getMyBirthdaysMap();
		Assert.assertNotNull("The birthday map cannot be null!", myBirthdaysMap);
		Assert.assertFalse("The birthday map cannot be empty!",
				myBirthdaysMap.isEmpty());

		Set<String> keySet = myBirthdaysMap.keySet();
		for (String key : keySet) {

			Assert.assertEquals("The map element is not my birthday!",
					expectedBirthday.getTime(), myBirthdaysMap.get(key)
					.getTime());

		}

	}

	@Test(expected = IllegalArgumentException.class)
	public void testStringPojoWithWrongTypeForAnnotationStrategy() {

		factory.manufacturePojo(StringWithWrongStrategyTypePojo.class);

	}

	@Test
	public void testPrivateOnlyConstructorPojo() {

		PrivateOnlyConstructorPojo pojo = factory
				.manufacturePojo(PrivateOnlyConstructorPojo.class);
		Assert.assertNotNull("The pojo cannot be null!", pojo);
		Assert.assertNotNull("The string attribute in pojo cannot be null!",
				pojo.getFirstName());
		Assert.assertTrue("The int field in pojo cannot be zero!",
				pojo.getIntField() != 0);

	}

	@Test
	public void testNoDefaultPublicConstructorPojo() {

		NoDefaultPublicConstructorPojo pojo = factory
				.manufacturePojo(NoDefaultPublicConstructorPojo.class);
		Assert.assertNotNull("The pojo cannot be null!", pojo);
		Assert.assertNotNull("The string field cannot be null!",
				pojo.getFirstName());
		Assert.assertTrue("The int field cannot be zero!",
				pojo.getIntField() != 0);

	}

	@Test
	public void testProtectedNonDefaultConstructorPojo() {
		ProtectedNonDefaultConstructorPojo pojo = factory
				.manufacturePojo(ProtectedNonDefaultConstructorPojo.class);
		Assert.assertNotNull("The pojo cannot be null!", pojo);
		Assert.assertNotNull("The string attribute cannot be null!",
				pojo.getFirstName());
		Assert.assertTrue("The int field cannot be zero!",
				pojo.getIntField() != 0);
	}

	@Test
	public void testSomeJavaNativeClasses() {
		String pojo = factory.manufacturePojo(String.class);
		Assert.assertNotNull("The generated String object cannot be null!",
				pojo);

		Integer integerPojo = factory.manufacturePojo(Integer.class);
		Assert.assertNotNull("The integer pojo cannot be null!", integerPojo);

		Calendar calendarPojo = factory
				.manufacturePojo(GregorianCalendar.class);
		Assert.assertNotNull("The calendar pojo cannot be null", calendarPojo);

		Date datePojo = factory.manufacturePojo(Date.class);
		Assert.assertNotNull("The date pojo cannot be null!", datePojo);

		BigDecimal bigDecimalPojo = factory.manufacturePojo(BigDecimal.class);
		Assert.assertNotNull("The Big decimal pojo cannot be null!",
				bigDecimalPojo);

	}

	// -----------------------------> Private methods

	/**
	 * It validates that the returned list contains the expected values
	 *
	 * @param list
	 *            The list to verify
	 */
	private void validateReturnedList(List<String> list) {
		Assert.assertNotNull("The List<String> should not be null!", list);
		Assert.assertFalse("The List<String> cannot be empty!", list.isEmpty());
		String element = list.get(0);
		Assert.assertNotNull(
				"The List<String> must have a non-null String element", element);
	}

	/**
	 * It validates that the returned list contains the expected values
	 *
	 * @param set
	 *            The set to verify
	 */
	private void validateReturnedSet(Set<String> set) {
		Assert.assertNotNull("The Set<String> should not be null!", set);
		Assert.assertFalse("The Set<String> cannot be empty!", set.isEmpty());
		String element = set.iterator().next();
		Assert.assertNotNull(
				"The Set<String> must have a non-null String element", element);
	}

	/**
	 * It validates the {@link HashMap} returned by Podam
	 *
	 * @param map
	 *            the map to be validated
	 */
	private void validateHashMap(Map<String, OneDimensionalTestPojo> map) {

		Assert.assertTrue("The map attribute must be of type HashMap",
				map instanceof HashMap);
		Assert.assertNotNull("The map object in the POJO cannot be null", map);
		Set<String> keySet = map.keySet();
		Assert.assertNotNull("The Map must have at least one element", keySet);

		validateMapElement(map, keySet);
	}

	/**
	 * It validates the concurrent hash map returned by podam
	 *
	 * @param map
	 */
	private void validateConcurrentHashMap(
			ConcurrentMap<String, OneDimensionalTestPojo> map) {

		Assert.assertTrue(
				"The map attribute must be of type ConcurrentHashMap",
				map instanceof ConcurrentHashMap);
		Assert.assertNotNull("The map object in the POJO cannot be null", map);
		Set<String> keySet = map.keySet();
		Assert.assertNotNull("The Map must have at least one element", keySet);

		validateMapElement(map, keySet);
	}

	/**
	 * It validates a map element
	 *
	 * @param map
	 *            The Map to validate
	 * @param keySet
	 *            The Set of keys in the map
	 */
	private void validateMapElement(Map<String, OneDimensionalTestPojo> map,
			Set<String> keySet) {
		OneDimensionalTestPojo oneDimensionalTestPojo = map.get(keySet
				.iterator().next());

		Assert.assertNotNull("The map element must not be null!",
				oneDimensionalTestPojo);
	}
}
