/**
 * 
 */
package uk.co.jemos.podam.common;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import uk.co.jemos.podam.api.PodamUtils;
import uk.co.jemos.podam.exceptions.PodamMockeryException;

import javax.validation.constraints.*;
import java.lang.annotation.Annotation;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * This strategy fills attributes and parameters annotated with Java bean
 * validation annotations
 *
 * @author daivanov
 */
public class BeanValidationStrategy implements AttributeStrategy<Object> {

	// ------------------->> Constants

	// ------------------->> Instance / Static variables

	private static final Logger LOG = LoggerFactory.getLogger(BeanValidationStrategy.class);

	/** A RANDOM generator */
	private static final Random RANDOM = new Random(System.currentTimeMillis());

	/** bean validation annotations */
	private List<Annotation> annotations;

	/** expected return type of an attribute */
	private Class<?> attributeType;

	// ------------------->> Constructors

	/**
	 * Constructor for the strategy
	 *
	 * @param annotations
	 *        bean validation annotations
	 * @param attributeType
	 *        expected return type of an attribute
	 */
	public BeanValidationStrategy(List<Annotation> annotations, Class<?> attributeType) {
		this.annotations = annotations;
		this.attributeType = attributeType;
	}

	// ------------------->> Public methods

	// ------------------->> Getters / Setters

	/**
	 * It returns a {@link Calendar} objects complying with Java bean validation
	 * annotations.
	 * 
	 * {@inheritDoc}
	 */
	public Object getValue() throws PodamMockeryException {

		if (null != findTypeFromList(annotations, AssertTrue.class)) {

			return Boolean.TRUE;
		}

		if (null != findTypeFromList(annotations, AssertFalse.class)) {

			return Boolean.FALSE;
		}

		if (null != findTypeFromList(annotations, Past.class)) {

			int days = RANDOM.nextInt(365) + 1;
			long timestamp = System.currentTimeMillis() - TimeUnit.DAYS.toSeconds(days);
			return timestampToReturnType(timestamp);
		}

		if (null != findTypeFromList(annotations, Future.class)) {

			int days = RANDOM.nextInt(365) + 1;
			long timestamp = System.currentTimeMillis() + TimeUnit.DAYS.toSeconds(days);
			return timestampToReturnType(timestamp);
		}

		Size size = findTypeFromList(annotations, Size.class);
		if (null != size) {

			int minValue = size.min();
			int maxValue = size.max();

			if (maxValue == Integer.MAX_VALUE) {
				maxValue = PodamConstants.STR_DEFAULT_LENGTH;
			}

			long length = PodamUtils.getLongInRange(minValue, maxValue);

			StringBuilder sb = new StringBuilder();
			while (sb.length() < length) {
				sb.append(PodamUtils.getNiceCharacter());
			}
			return sb.toString();

		}

		Pattern pattern = findTypeFromList(annotations, Pattern.class);
		if (null != pattern) {

			LOG.warn("At the moment PODAM doesn't support @Pattern({}),"
					+ " returning null", pattern.regexp());
			return null;

		}

		boolean isRound = false;
		boolean isFloat = false;
		BigDecimal min = new BigDecimal(-Double.MAX_VALUE);
		BigDecimal max = new BigDecimal(Double.MAX_VALUE);

		DecimalMin decimalMin = findTypeFromList(annotations, DecimalMin.class);
		if (null != decimalMin) {
			isFloat = true;
			min = new BigDecimal(decimalMin.value());
		}

		DecimalMax decimalMax = findTypeFromList(annotations, DecimalMax.class);
		if (null != decimalMax) {
			isFloat = true;
			max = new BigDecimal(decimalMax.value());
		}

		Min minAnno = findTypeFromList(annotations, Min.class);
		if (null != minAnno) {
			isRound = true;
			min = new BigDecimal(minAnno.value()).max(min);
		}

		Max maxAnno = findTypeFromList(annotations, Max.class);
		if (null != maxAnno) {
			isRound = true;
			max = new BigDecimal(maxAnno.value()).min(max);
		}

		Digits digits = findTypeFromList(annotations, Digits.class);
		BigDecimal divisor = null;
		if (null != digits) {
			isRound = true;
			divisor = BigDecimal.TEN.pow(digits.fraction());
			BigDecimal limit = BigDecimal.TEN.pow(digits.integer());
			max = limit.min(max).multiply(divisor);
			min = limit.negate().max(min).multiply(divisor);
		}

		if (isRound || isFloat) {
			BigDecimal value = getValueInRange(min, max);

			if (isRound) {

				/* Integer part */
				BigInteger intValue = value.toBigInteger();
				value = new BigDecimal(intValue);
			}

			if (null != divisor) {
				value = value.divide(divisor);
			}

			return decimalToReturnType(value);
		}

		return null;
	}

	// ------------------->> Private methods

	/**
	 * Utility to find an item of a desired type in the given list
	 *
	 * @param <T>
	 *        Return type of item to find
	 * @param list
	 *        List to search in
	 * @param type
	 *        Type to find in the list
	 * @return
	 *        First element from the list of desired type
	 */
	@SuppressWarnings("unchecked")
	private static <T> T findTypeFromList(List<?> list, Class<T> type) {

		for (Object item : list) {
			if (type.isAssignableFrom(item.getClass())) {
				return (T)item;
			}
		}
		return null;
	}

	/**
	 * Produces random decimal value within specified range
	 *
	 * @param min
	 *        minimum value of range
	 * @param max
	 *        maximum value of range
	 * @return
	 *        decimal value in the specified range
	 */
	private BigDecimal getValueInRange(BigDecimal min, BigDecimal max) {

		BigDecimal scale = new BigDecimal(RANDOM.nextDouble());
		return min.add(max.subtract(min).multiply(scale));
	}

	/**
	 * Converts intermediate decimal value to the actual attribute type,
	 * for example, string representation of this decimal
	 *
	 * @param result
	 *        {@link BigDecimal} intermediate result to convert to the
	 *        real attribute type 
	 * @return actual attribute type object
	 */
	private Object decimalToReturnType(BigDecimal result) {

		if (String.class.equals(attributeType)) {

			return result.toPlainString();

		} else if (Double.class.equals(attributeType)
				|| double.class.equals(attributeType)) {

			return result.doubleValue();

		} else if (Float.class.equals(attributeType)
				|| float.class.equals(attributeType)) {

			return result.floatValue();

		} else if (Long.class.equals(attributeType)
				|| long.class.equals(attributeType)) {

			return result.longValue();

		} else if (Integer.class.equals(attributeType)
				|| int.class.equals(attributeType)) {

			return result.intValue();

		} else if (Short.class.equals(attributeType)
				|| short.class.equals(attributeType)) {

			return result.shortValue();

		} else if (Byte.class.equals(attributeType)
				|| byte.class.equals(attributeType)) {

			return result.byteValue();

		} else if (attributeType.isAssignableFrom(BigDecimal.class)) {

			return result;

		} else if (attributeType.isAssignableFrom(BigInteger.class)) {

			return result.toBigInteger();

		} else {

			LOG.warn("Unsupported attribute type {}", attributeType);
			return null;

		}
	}

	/**
	 * Converts intermediate long time stamp value to the actual attribute type,
	 * {@link java.util.Date} or {@link java.util.Calendar}
	 *
	 * @param result
	 *        {@link Long} intermediate result to convert to the
	 *        real attribute type 
	 * @return actual attribute type object
	 */
	private Object timestampToReturnType(Long result) {

		if (attributeType.isAssignableFrom(Date.class)) {

			return new Date(result);

		} else if (attributeType.isAssignableFrom(Calendar.class)) {

			Calendar calendar = Calendar.getInstance();
			calendar.setTimeInMillis(result);
			return calendar;

		} else {

			LOG.warn("Unsupported attribute type {}", attributeType);
			return null;

		}
	}

	// ------------------->> equals() / hashcode() / toString()

	// ------------------->> Inner classes

}
