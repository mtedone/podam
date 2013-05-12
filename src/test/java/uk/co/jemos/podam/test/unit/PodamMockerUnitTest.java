package uk.co.jemos.podam.test.unit;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;
import uk.co.jemos.podam.api.RandomDataProviderStrategy;
import uk.co.jemos.podam.exceptions.PodamMockeryException;
import uk.co.jemos.podam.test.dto.AbstractTestPojo;
import uk.co.jemos.podam.test.dto.CollectionsPojo;
import uk.co.jemos.podam.test.dto.ConstructorWithSelfReferencesButNoDefaultConstructorPojo;
import uk.co.jemos.podam.test.dto.ConstructorWithSelfReferencesPojo;
import uk.co.jemos.podam.test.dto.EnumsPojo;
import uk.co.jemos.podam.test.dto.EnumsPojo.RatePodamInternal;
import uk.co.jemos.podam.test.dto.ExcludeAnnotationPojo;
import uk.co.jemos.podam.test.dto.ImmutableNoHierarchicalAnnotatedPojo;
import uk.co.jemos.podam.test.dto.ImmutableNonAnnotatedPojo;
import uk.co.jemos.podam.test.dto.ImmutableWithGenericCollectionsPojo;
import uk.co.jemos.podam.test.dto.ImmutableWithNonGenericCollectionsPojo;
import uk.co.jemos.podam.test.dto.InterfacePojo;
import uk.co.jemos.podam.test.dto.NoDefaultConstructorPojo;
import uk.co.jemos.podam.test.dto.NoSetterWithCollectionInConstructorPojo;
import uk.co.jemos.podam.test.dto.OneDimensionalChildPojo;
import uk.co.jemos.podam.test.dto.OneDimensionalTestPojo;
import uk.co.jemos.podam.test.dto.PrivateNoArgConstructorPojo;
import uk.co.jemos.podam.test.dto.RecursivePojo;
import uk.co.jemos.podam.test.dto.SimplePojoToTestSetters;
import uk.co.jemos.podam.test.dto.SingletonWithParametersInStaticFactoryPojo;
import uk.co.jemos.podam.test.dto.annotations.BooleanValuePojo;
import uk.co.jemos.podam.test.dto.annotations.ByteValuePojo;
import uk.co.jemos.podam.test.dto.annotations.ByteValueWithErrorPojo;
import uk.co.jemos.podam.test.dto.annotations.CharValuePojo;
import uk.co.jemos.podam.test.dto.annotations.CollectionAnnotationPojo;
import uk.co.jemos.podam.test.dto.annotations.DoubleValuePojo;
import uk.co.jemos.podam.test.dto.annotations.DoubleValueWithErrorPojo;
import uk.co.jemos.podam.test.dto.annotations.FloatValuePojo;
import uk.co.jemos.podam.test.dto.annotations.FloatValueWithErrorPojo;
import uk.co.jemos.podam.test.dto.annotations.IntegerValuePojo;
import uk.co.jemos.podam.test.dto.annotations.IntegerValueWithErrorPojo;
import uk.co.jemos.podam.test.dto.annotations.LongValuePojo;
import uk.co.jemos.podam.test.dto.annotations.LongValueWithErrorPojo;
import uk.co.jemos.podam.test.dto.annotations.PodamStrategyPojo;
import uk.co.jemos.podam.test.dto.annotations.ShortValuePojo;
import uk.co.jemos.podam.test.dto.annotations.ShortValueWithErrorPojo;
import uk.co.jemos.podam.test.dto.annotations.StringValuePojo;
import uk.co.jemos.podam.test.dto.annotations.StringWithWrongStrategyTypePojo;
import uk.co.jemos.podam.test.dto.pdm33.NoDefaultPublicConstructorPojo;
import uk.co.jemos.podam.test.dto.pdm33.PrivateOnlyConstructorPojo;
import uk.co.jemos.podam.test.dto.pdm33.ProtectedNonDefaultConstructorPojo;
import uk.co.jemos.podam.test.enums.ExternalRatePodamEnum;
import uk.co.jemos.podam.test.utils.PodamTestConstants;
import uk.co.jemos.podam.test.utils.PodamTestUtils;

/**
 * Unit test for simple App.
 */
public class PodamMockerUnitTest {

	/** The podam factory */
	private PodamFactory factory;

	/** The default data strategy */
	private final RandomDataProviderStrategy strategy = RandomDataProviderStrategy
			.getInstance();

	@Before
	public void init() {

		factory = new PodamFactoryImpl(strategy);

	}

	@Test
	public void testMockerForClassWithoutDefaultConstructor() {

		// With a no-arg constructor, an instantiation exception will be thrown
		NoDefaultConstructorPojo pojo = factory
				.manufacturePojo(NoDefaultConstructorPojo.class);
		Assert.assertNotNull(
				"The pojo with no default constructors must not be null!", pojo);

	}

	@Test
	public void testMockerForAbstractClass() {
		// Trying to create an abstract class should thrown an instantiation
		// exception
		AbstractTestPojo pojo = factory.manufacturePojo(AbstractTestPojo.class);
		Assert.assertNull("The abstract pojo should be null!", pojo);
	}

	@Test
	public void testMockerForInterface() {
		// Trying to create an interface class should thrown an instantiation
		// exception
		InterfacePojo pojo = factory.manufacturePojo(InterfacePojo.class);
		Assert.assertNull("The interface pojo should be null!", pojo);

	}

	@Test
	public void testMockerForPrimitiveType() {
		// Trying to create an interface class should thrown an instantiation
		// exception
		int intValue = factory.manufacturePojo(int.class);
		Assert.assertTrue("The int value should not be zero!", intValue != 0);
	}

	@Test
	public void testMockerForPojoWithPrivateNoArgConstructor() {
		PrivateNoArgConstructorPojo pojo = factory
				.manufacturePojo(PrivateNoArgConstructorPojo.class);
		Assert.assertNotNull(
				"The pojo with private default constructor cannot be null!",
				pojo);
	}

	@Test
	public void testOneDimensionalTestPojo() {

		OneDimensionalTestPojo pojo = factory
				.manufacturePojo(OneDimensionalTestPojo.class);
		Assert.assertNotNull("The object cannot be null!", pojo);

		Boolean booleanObjectField = pojo.getBooleanObjectField();
		Assert.assertTrue(
				"The boolean object field should have a value of TRUE",
				booleanObjectField);

		boolean booleanField = pojo.isBooleanField();
		Assert.assertTrue("The boolean field should have a value of TRUE",
				booleanField);

		byte byteField = pojo.getByteField();
		Assert.assertTrue("The byte field should not be zero", byteField != 0);

		Byte byteObjectField = pojo.getByteObjectField();
		Assert.assertTrue("The Byte object field should not be zero",
				byteObjectField != 0);

		short shortField = pojo.getShortField();
		Assert.assertTrue("The short field should not be zero", shortField != 0);

		Short shortObjectField = pojo.getShortObjectField();
		Assert.assertTrue("The Short Object field should not be zero",
				shortObjectField != 0);

		char charField = pojo.getCharField();
		Assert.assertTrue("The char field should not be zero", charField != 0);
		Character characterObjectField = pojo.getCharObjectField();
		Assert.assertTrue("The Character object field should not be zero",
				characterObjectField != 0);

		int intField = pojo.getIntField();
		Assert.assertTrue("The int field cannot be zero", intField != 0);
		Integer integerField = pojo.getIntObjectField();
		Assert.assertTrue("The Integer object field cannot be zero",
				integerField != 0);

		long longField = pojo.getLongField();
		Assert.assertTrue("The long field cannot be zero", longField != 0);
		Long longObjectField = pojo.getLongObjectField();
		Assert.assertTrue("The Long object field cannot be zero",
				longObjectField != 0);

		float floatField = pojo.getFloatField();
		Assert.assertTrue("The float field cannot be zero", floatField != 0.0);
		Float floatObjectField = pojo.getFloatObjectField();
		Assert.assertTrue("The Float object field cannot be zero",
				floatObjectField != 0.0);

		double doubleField = pojo.getDoubleField();
		Assert.assertTrue("The double field cannot be zero",
				doubleField != 0.0d);
		Double doubleObjectField = pojo.getDoubleObjectField();
		Assert.assertTrue("The Double object field cannot be zero",
				doubleObjectField != 0.0d);

		String stringField = pojo.getStringField();
		Assert.assertNotNull("The String field cannot be null", stringField);
		Assert.assertFalse("The String field cannot be empty",
				stringField.equals(""));

		Object objectField = pojo.getObjectField();
		Assert.assertNotNull("The Object field cannot be null", objectField);

		Calendar calendarField = pojo.getCalendarField();
		checkCalendarIsValid(calendarField);

		Date dateField = pojo.getDateField();
		Assert.assertNotNull("The date field is not valid", dateField);

		Random[] randomArray = pojo.getRandomArray();
		Assert.assertNotNull("The array of Random objects cannot be null!",
				randomArray);
		Assert.assertTrue("The array of Random length should be one!",
				randomArray.length == strategy.getNumberOfCollectionElements());
		Random random = randomArray[0];
		Assert.assertNotNull(
				"The Random array element at [0] should not be null", random);

		int[] intArray = pojo.getIntArray();
		Assert.assertNotNull("The array of ints cannot be null!", intArray);
		Assert.assertTrue(
				"The array of ints length should be the same as defined in the strategy!",
				intArray.length == strategy.getNumberOfCollectionElements());
		Assert.assertTrue(
				"The first element in the array of ints must be different from zero!",
				intArray[0] != 0);

		boolean[] booleanArray = pojo.getBooleanArray();
		Assert.assertNotNull("The array of booleans cannot be null!",
				booleanArray);
		Assert.assertTrue(
				"The array of boolean length should be the same as the one set in the strategy!",
				booleanArray.length == strategy.getNumberOfCollectionElements());

		BigDecimal bigDecimalField = pojo.getBigDecimalField();
		Assert.assertNotNull("The BigDecimal field cannot be null!",
				bigDecimalField);

	}

	@Test
	public void testRecursiveHierarchyPojo() {

		RecursivePojo pojo = factory.manufacturePojo(RecursivePojo.class);
		Assert.assertNotNull("The recursive pojo cannot be null!", pojo);
		Assert.assertTrue("The integer value in the pojo should not be zero!",
				pojo.getIntField() != 0);

		RecursivePojo parentPojo = pojo.getParent();
		Assert.assertNotNull("The parent pojo cannot be null!", parentPojo);
		Assert.assertTrue(
				"The integer value in the parent pojo should not be zero!",
				parentPojo.getIntField() != 0);
		Assert.assertNotNull(
				"The parent attribute of the parent pojo cannot be null!",
				parentPojo.getParent());

	}

	@Test
	public void testImmutableNoHierarchicalAnnotatedPojo() {

		ImmutableNoHierarchicalAnnotatedPojo pojo = factory
				.manufacturePojo(ImmutableNoHierarchicalAnnotatedPojo.class);
		Assert.assertNotNull("The Immutable Simple Pojo cannot be null!", pojo);
		int intField = pojo.getIntField();
		Assert.assertTrue("The int field cannot be zero", intField != 0);
		Calendar dateCreated = pojo.getDateCreated();
		Assert.assertNotNull(
				"The Date Created Calendar object cannot be null!", dateCreated);
		Assert.assertNotNull(
				"The Date object within the dateCreated Calendar object cannot be null!",
				dateCreated.getTime());
		long[] longArray = pojo.getLongArray();
		Assert.assertNotNull("The array of longs cannot be null!", longArray);
		Assert.assertTrue("The array of longs cannot be empty!",
				longArray.length > 0);
		long longElement = longArray[0];
		Assert.assertTrue(
				"The long element within the long array cannot be zero!",
				longElement != 0);

	}

	@Test
	public void testImmutableNonAnnotatedPojo() {

		ImmutableNonAnnotatedPojo pojo = factory
				.manufacturePojo(ImmutableNonAnnotatedPojo.class);
		Assert.assertNotNull(
				"The immutable non annotated POJO should not be null!", pojo);

		Assert.assertNotNull("The date created cannot be null!",
				pojo.getDateCreated());

		Assert.assertTrue("The int field cannot be zero!",
				pojo.getIntField() != 0);

		long[] longArray = pojo.getLongArray();
		Assert.assertNotNull("The array of longs cannot be null!", longArray);
		Assert.assertTrue("The array of longs must have 1 element!",
				longArray.length == strategy.getNumberOfCollectionElements());

	}

	@Test
	public void testPojoWithSelfReferencesInConstructor() {

		ConstructorWithSelfReferencesPojo pojo = factory
				.manufacturePojo(ConstructorWithSelfReferencesPojo.class);
		Assert.assertNotNull("The POJO cannot be null!", pojo);
		Assert.assertNotNull("The first self-reference cannot be null!",
				pojo.getParent());
		Assert.assertNotNull("The second self-reference cannot be null!",
				pojo.getAnotherParent());

	}

	@Test(expected = PodamMockeryException.class)
	public void testPojoWithSelfReferenceInConstructorButNoDefaultConstructor() {

		factory.manufacturePojo(ConstructorWithSelfReferencesButNoDefaultConstructorPojo.class);

	}

	@Test
	public void testPodamExcludeAnnotation() {

		ExcludeAnnotationPojo pojo = factory
				.manufacturePojo(ExcludeAnnotationPojo.class);
		Assert.assertNotNull("The pojo should not be null!", pojo);
		int intField = pojo.getIntField();
		Assert.assertTrue("The int field should not be zero!", intField != 0);
		Assert.assertNull(
				"The other object in the pojo should be null because annotated with PodamExclude!",
				pojo.getSomePojo());

	}

	@Test
	public void testIntegerValueAnnotation() {

		IntegerValuePojo pojo = factory.manufacturePojo(IntegerValuePojo.class);
		Assert.assertNotNull("The pojo cannot be null!", pojo);
		int intFieldWithMinValueOnly = pojo.getIntFieldWithMinValueOnly();
		Assert.assertTrue("The int field with only minValue should be >= 0",
				intFieldWithMinValueOnly >= 0);
		int intFieldWithMaxValueOnly = pojo.getIntFieldWithMaxValueOnly();
		Assert.assertTrue(
				"The int field with maximum value only should have a maximum value of 100",
				intFieldWithMaxValueOnly <= 100);
		int intObjectFieldWithMinAndMaxValue = pojo
				.getIntFieldWithMinAndMaxValue();
		Assert.assertTrue(
				"The int field with both min and max value should have a value comprised between",
				intObjectFieldWithMinAndMaxValue >= 0
						&& intObjectFieldWithMinAndMaxValue <= 1000);
		Integer integerObjectFieldWithMinValueOnly = pojo
				.getIntegerObjectFieldWithMinValueOnly();
		Assert.assertNotNull(
				"The integer field with minimum value only should not be null!",
				integerObjectFieldWithMinValueOnly);
		Assert.assertTrue(
				"The integer field with minimum value only should have a minimum value greater or equal to zero!",
				integerObjectFieldWithMinValueOnly.intValue() >= 0);
		Integer integerObjectFieldWithMaxValueOnly = pojo
				.getIntegerObjectFieldWithMaxValueOnly();
		Assert.assertNotNull(
				"The integer field with maximum value only should not be null!",
				integerObjectFieldWithMaxValueOnly);
		Assert.assertTrue(
				"The integer field with maximum value only should have a maximum value of 100",
				integerObjectFieldWithMaxValueOnly.intValue() <= 100);
		Integer integerObjectFieldWithMinAndMaxValue = pojo
				.getIntegerObjectFieldWithMinAndMaxValue();
		Assert.assertNotNull(
				"The integer field with minimum and maximum value should not be null!",
				integerObjectFieldWithMinAndMaxValue);
		Assert.assertTrue(
				"The integer field with minimum and maximum value should have value comprised between 0 and 1000",
				integerObjectFieldWithMinAndMaxValue.intValue() >= 0
						&& integerObjectFieldWithMinAndMaxValue.intValue() <= 1000);

		int intFieldWithPreciseValue = pojo.getIntFieldWithPreciseValue();
		Assert.assertTrue(
				"The integer field with precise value must have a value of: "
						+ PodamTestConstants.INTEGER_PRECISE_VALUE,
				intFieldWithPreciseValue == Integer
						.valueOf(PodamTestConstants.INTEGER_PRECISE_VALUE));

		Integer integerObjectFieldWithPreciseValue = pojo
				.getIntegerObjectFieldWithPreciseValue();
		Assert.assertNotNull(
				"The integer object field with precise value cannot be null!",
				integerObjectFieldWithPreciseValue);
		Assert.assertTrue(
				"The integer object field with precise value should have a value of "
						+ PodamTestConstants.INTEGER_PRECISE_VALUE,
				integerObjectFieldWithPreciseValue.intValue() == Integer
						.valueOf(PodamTestConstants.INTEGER_PRECISE_VALUE));

	}

	@Test(expected = PodamMockeryException.class)
	public void testIntegerValueAnnotationWithNumberFormatError() {
		factory.manufacturePojo(IntegerValueWithErrorPojo.class);
	}

	@Test
	public void testLongValueAnnotation() {

		LongValuePojo pojo = factory.manufacturePojo(LongValuePojo.class);
		Assert.assertNotNull("The pojo cannot be null!", pojo);
		long longFieldWithMinValueOnly = pojo.getLongFieldWithMinValueOnly();
		Assert.assertTrue(
				"The long field with min value only should have a value >= 0",
				longFieldWithMinValueOnly >= 0);
		long longFieldWithMaxValueOnly = pojo.getLongFieldWithMaxValueOnly();
		Assert.assertTrue(
				"The long field with maximumm value only should have a maximum value of 100",
				longFieldWithMaxValueOnly <= 100);
		long longFieldWithMinAndMaxValue = pojo
				.getLongFieldWithMinAndMaxValue();
		Assert.assertTrue(
				"The long field with both min and max value should have a value comprised between 0 and 1000!",
				longFieldWithMinAndMaxValue >= 0
						&& longFieldWithMinAndMaxValue <= 1000);

		Long longObjectFieldWithMinValueOnly = pojo
				.getLongObjectFieldWithMinValueOnly();
		Assert.assertNotNull(
				"The Long Object field with min value only cannot be null!",
				longObjectFieldWithMinValueOnly);
		Assert.assertTrue(
				"The Long Object field with min value only should have a value >= 0",
				longObjectFieldWithMinValueOnly >= 0);

		Long longObjectFieldWithMaxValueOnly = pojo
				.getLongObjectFieldWithMaxValueOnly();
		Assert.assertNotNull(
				"The Long Object field with max value only cannot be null!",
				longObjectFieldWithMaxValueOnly);
		Assert.assertTrue(
				"The Long Object field with max value only should have a value <= 100",
				longObjectFieldWithMaxValueOnly <= 100);

		Long longObjectFieldWithMinAndMaxValue = pojo
				.getLongObjectFieldWithMinAndMaxValue();
		Assert.assertNotNull(
				"The Long Object field with min and max value cannot be null!",
				longObjectFieldWithMinAndMaxValue);
		Assert.assertTrue(
				"The Long object field with min and max value should have a value comprised between 0 and 1000",
				longObjectFieldWithMinAndMaxValue >= 0L
						&& longObjectFieldWithMinAndMaxValue <= 1000L);

		long longFieldWithPreciseValue = pojo.getLongFieldWithPreciseValue();
		Assert.assertTrue(
				"The long field with precise value must have a value of "
						+ PodamTestConstants.LONG_PRECISE_VALUE,
				longFieldWithPreciseValue == Long
						.valueOf(PodamTestConstants.LONG_PRECISE_VALUE));

		Long longObjectFieldWithPreciseValue = pojo
				.getLongObjectFieldWithPreciseValue();
		Assert.assertNotNull(
				"The long object with precise value should not be null!",
				longObjectFieldWithPreciseValue);
		Assert.assertTrue(
				"The long object field with precise value must have a value of "
						+ PodamTestConstants.LONG_PRECISE_VALUE,
				longObjectFieldWithPreciseValue.longValue() == Long.valueOf(
						PodamTestConstants.LONG_PRECISE_VALUE).longValue());

	}

	@Test(expected = PodamMockeryException.class)
	public void testLongValueAnnotationWithNumberFormatException() {
		factory.manufacturePojo(LongValueWithErrorPojo.class);
	}

	@Test
	public void testInheritance() {

		OneDimensionalChildPojo pojo = factory
				.manufacturePojo(OneDimensionalChildPojo.class);
		Assert.assertNotNull("The pojo cannot be null!", pojo);
		int parentIntField = pojo.getParentIntField();
		Assert.assertTrue("The super int field must be <= 10",
				parentIntField <= 10);
		Calendar parentCalendarField = pojo.getParentCalendarField();
		checkCalendarIsValid(parentCalendarField);
		int intField = pojo.getIntField();
		Assert.assertTrue("The int field must be different from zero!",
				intField != 0);
		String strField = pojo.getStrField();
		Assert.assertNotNull("The string field cannot be null!", strField);
		Assert.assertTrue("The String field cannot be empty",
				strField.length() != 0);

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

		Map nonGenerifiedMap = pojo.getNonGenerifiedMap();
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

	@Test(expected = PodamMockeryException.class)
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

	@Test(expected = PodamMockeryException.class)
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

	@Test(expected = PodamMockeryException.class)
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

	@Test(expected = PodamMockeryException.class)
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
	public void testEnumsPojo() {

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
	public void testPodamStrategyValueAnnotation() {

		PodamStrategyPojo pojo = factory
				.manufacturePojo(PodamStrategyPojo.class);
		Assert.assertNotNull("The post code pojo cannot be null!", pojo);

		String postCode = pojo.getPostCode();
		Assert.assertNotNull("The post code cannot be null!", postCode);
		Assert.assertEquals("The post code does not match the expected value",
				PodamTestConstants.POST_CODE, postCode);

		Calendar expectedBirthday = PodamTestUtils.getMyBirthday();

		Calendar myBirthday = pojo.getMyBirthday();

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

	@Test(expected = PodamMockeryException.class)
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
	 * It checks that the Calendar instance is valid
	 * <p>
	 * If the calendar returns a valid date then it's a valid instance
	 * </p>
	 * 
	 * @param calendarField
	 *            The calendar instance to check
	 */
	private void checkCalendarIsValid(Calendar calendarField) {
		Assert.assertNotNull("The Calendar field cannot be null", calendarField);
		Date calendarDate = calendarField.getTime();
		Assert.assertNotNull("It appears the Calendar field is not valid",
				calendarDate);
	}

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
