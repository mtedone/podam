/**
 * 
 */
package uk.co.jemos.podam.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import uk.co.jemos.podam.api.PodamUtils;
import uk.co.jemos.podam.exceptions.PodamMockeryException;

import jakarta.validation.constraints.*;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.Instant;
import java.time.temporal.Temporal;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
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

	private static final String METHOD_NAME_NOW = "now";

	/** expected return type of an attribute */
	private Class<?> attributeType;

	/** EmailStrategy implementation */
	private EmailStrategy emailStrategy = new EmailStrategy();

	// ------------------->> Constructors

	/**
	 * Constructor for the strategy
	 *
	 * @param attributeType
	 *        expected return type of an attribute
	 */
	public BeanValidationStrategy(Class<?> attributeType) {
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
	public Object getValue(Class<?> attrType, List<Annotation> annotations) throws PodamMockeryException {

		if (null != findTypeFromList(annotations, AssertTrue.class)) {

			return Boolean.TRUE;
		}

		if (null != findTypeFromList(annotations, AssertFalse.class)) {

			return Boolean.FALSE;
		}

		if (null != findTypeFromList(annotations, Past.class)) {

			long days = PodamUtils.getIntegerInRange(1, 365);
			long offset = -TimeUnit.DAYS.toSeconds(days);
			return timestampToReturnType(offset);
		}

		if (null != findTypeFromList(annotations, PastOrPresent.class)) {

			long days = PodamUtils.getIntegerInRange(0, 365);
			long offset = -TimeUnit.DAYS.toSeconds(days);
			return timestampToReturnType(-days);
		}

		if (null != findTypeFromList(annotations, Future.class)) {

			long days = PodamUtils.getIntegerInRange(1, 365);
			long offset = TimeUnit.DAYS.toSeconds(days);
			return timestampToReturnType(days);
		}

		if (null != findTypeFromList(annotations, FutureOrPresent.class)) {

			long days = PodamUtils.getIntegerInRange(0, 365);
			long offset = TimeUnit.DAYS.toSeconds(days);
			return timestampToReturnType(days);
		}

		Size size = findTypeFromList(annotations, Size.class);
		if (null != size) {

			int minValue = size.min();
			int maxValue = size.max();

			if (minValue < 1 && maxValue > 0) {
				minValue = 1;
			}

			if (maxValue == Integer.MAX_VALUE) {
				maxValue = PodamConstants.STR_DEFAULT_LENGTH;
			}

			int length = PodamUtils.getIntegerInRange(minValue, maxValue);
			return PodamUtils.getNiceString(length);

		}

		Email email = findTypeFromList(annotations, Email.class);
		if (null != email) {

			return emailStrategy.getValue(attrType, annotations);

		}

		Pattern pattern = findTypeFromList(annotations, Pattern.class);
		if (null != pattern) {

			LOG.warn("At the moment PODAM doesn't support @Pattern({}),"
					+ " returning null", pattern.regexp());
			return null;

		}

		boolean isRound = false;
		boolean isFloat = false;
		BigDecimal min;
		BigDecimal max;
		if (Long.class.equals(attributeType)
				|| long.class.equals(attributeType)) {

			min = new BigDecimal(Long.MIN_VALUE);
			max = new BigDecimal(Long.MAX_VALUE);

		} else if (Integer.class.equals(attributeType)
				|| int.class.equals(attributeType)) {

			min = new BigDecimal(Integer.MIN_VALUE);
			max = new BigDecimal(Integer.MAX_VALUE);

		} else if (Short.class.equals(attributeType)
				|| short.class.equals(attributeType)) {

			min = new BigDecimal(Short.MIN_VALUE);
			max = new BigDecimal(Short.MAX_VALUE);

		} else if (Byte.class.equals(attributeType)
				|| byte.class.equals(attributeType)) {

			min = new BigDecimal(Byte.MIN_VALUE);
			max = new BigDecimal(Byte.MAX_VALUE);
		} else {

			min = new BigDecimal(-Double.MAX_VALUE);
			max = new BigDecimal(Double.MAX_VALUE);
		}

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

		Positive positiveAnno = findTypeFromList(annotations, Positive.class);
		if (null != positiveAnno) {
			isFloat = true;
			max = new BigDecimal(Integer.MAX_VALUE);
			min = new BigDecimal(1);
		}

		PositiveOrZero positiveOrZeroAnno = findTypeFromList(annotations, PositiveOrZero.class);
		if (null != positiveOrZeroAnno) {
			isFloat = true;
			max = new BigDecimal(Integer.MAX_VALUE);
			min = new BigDecimal(0);
		}

		Negative negativeAnno = findTypeFromList(annotations, Negative.class);
		if (null != negativeAnno) {
			isFloat = true;
			max = new BigDecimal(-1);
			min = new BigDecimal(Integer.MIN_VALUE);
		}

		NegativeOrZero negativeOrZeroAnno = findTypeFromList(annotations, NegativeOrZero.class);
		if (null != negativeOrZeroAnno) {
			isFloat = true;
			max = new BigDecimal(0);
			min = new BigDecimal(Integer.MIN_VALUE);
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
	public static <T> T findTypeFromList(List<?> list, Class<T> type) {

		for (Object item : list) {
			if (type.isAssignableFrom(item.getClass())) {
				@SuppressWarnings("unchecked")
				T found = (T)item;
				return found;
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

		BigDecimal scale = new BigDecimal(PodamUtils.getDoubleInRange(0.0, 1.0));
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
	 * {@link java.util.Date} or {@link java.util.Calendar} or java.time.* types
	 *
	 * @param offsetSecs
	 *        {@link Long} time offset to add to the current time 
	 * @return actual attribute type object
	 */
	private Object timestampToReturnType(Long offsetSecs) {

        long timestamp;
		if (Temporal.class.isAssignableFrom(attributeType)) {

			try {
				Method method = attributeType.getMethod(METHOD_NAME_NOW);
				Temporal result = (Temporal)method.invoke(null);
                return result.plus(offsetSecs, ChronoUnit.SECONDS);
			} catch (Exception e) {

				LOG.warn("We couldn't call method {} in {}",
						METHOD_NAME_NOW, attributeType, e);
				return null;
			}

        } else {

			timestamp = System.currentTimeMillis() + offsetSecs * 1000;
        }

		if (attributeType.isAssignableFrom(Date.class)) {

			return new Date(timestamp);

		} else if (attributeType.isAssignableFrom(Calendar.class)) {

			Calendar calendar = Calendar.getInstance();
			calendar.setTimeInMillis(timestamp);
			return calendar;

		} else {

			LOG.warn("Unsupported attribute type {}", attributeType);
			return null;

		}
	}

	// ------------------->> equals() / hashcode() / toString()

	// ------------------->> Inner classes

}
