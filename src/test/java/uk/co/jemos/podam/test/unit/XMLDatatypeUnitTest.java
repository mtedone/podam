package uk.co.jemos.podam.test.unit;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.TimeZone;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeConstants.Field;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.Duration;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.namespace.QName;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;
import uk.co.jemos.podam.api.RandomDataProviderStrategy;
import uk.co.jemos.podam.test.dto.XMLDatatypePojo;

/**
 * Test @uk.co.jemos.podam.test.dto.JAXBElementPojo@ construction
 *
 * @author daivanov
 *
 */
public class XMLDatatypeUnitTest {

	private final static RandomDataProviderStrategy strategy =
			RandomDataProviderStrategy.getInstance();

	private final static PodamFactory podam = new PodamFactoryImpl(strategy);

	public static class XMLGregorianCalendarImpl extends XMLGregorianCalendar {

		public static XMLGregorianCalendar getInstance() throws DatatypeConfigurationException {
			DatatypeFactory factory = DatatypeFactory.newInstance();
			return factory.newXMLGregorianCalendar(new GregorianCalendar());
		}

		@Override
		public void clear() {
		}

		@Override
		public void reset() {
		}

		@Override
		public void setYear(BigInteger year) {
		}

		@Override
		public void setYear(int year) {
		}

		@Override
		public void setMonth(int month) {
		}

		@Override
		public void setDay(int day) {
		}

		@Override
		public void setTimezone(int offset) {
		}

		@Override
		public void setHour(int hour) {
		}

		@Override
		public void setMinute(int minute) {
		}

		@Override
		public void setSecond(int second) {
		}

		@Override
		public void setMillisecond(int millisecond) {
		}

		@Override
		public void setFractionalSecond(BigDecimal fractional) {
		}

		@Override
		public BigInteger getEon() {
			return null;
		}

		@Override
		public int getYear() {
			return 0;
		}

		@Override
		public BigInteger getEonAndYear() {
			return null;
		}

		@Override
		public int getMonth() {
			return 0;
		}

		@Override
		public int getDay() {
			return 0;
		}

		@Override
		public int getTimezone() {
			return 0;
		}

		@Override
		public int getHour() {
			return 0;
		}

		@Override
		public int getMinute() {
			return 0;
		}

		@Override
		public int getSecond() {
			return 0;
		}

		@Override
		public BigDecimal getFractionalSecond() {
			return null;
		}

		@Override
		public int compare(XMLGregorianCalendar xmlGregorianCalendar) {
			return 0;
		}

		@Override
		public XMLGregorianCalendar normalize() {
			return null;
		}

		@Override
		public String toXMLFormat() {
			return null;
		}

		@Override
		public QName getXMLSchemaType() {
			return null;
		}

		@Override
		public boolean isValid() {
			return false;
		}

		@Override
		public void add(Duration duration) {
		}

		@Override
		public GregorianCalendar toGregorianCalendar() {
			return null;
		}

		@Override
		public GregorianCalendar toGregorianCalendar(TimeZone timezone,
				Locale aLocale, XMLGregorianCalendar defaults) {
			return null;
		}

		@Override
		public TimeZone getTimeZone(int defaultZoneoffset) {
			return null;
		}

		@Override
		public Object clone() {
			return null;
		}
	}

	public static class DurationImpl extends Duration {

		public static Duration getInstance() throws DatatypeConfigurationException {
			DatatypeFactory factory = DatatypeFactory.newInstance();
			return factory.newDuration(0L);
		}

		@Override
		public int getSign() {
			return 0;
		}

		@Override
		public Number getField(Field field) {
			return null;
		}

		@Override
		public boolean isSet(Field field) {
			return false;
		}

		@Override
		public Duration add(Duration rhs) {
			return null;
		}

		@Override
		public void addTo(Calendar calendar) {
		}

		@Override
		public Duration multiply(BigDecimal factor) {
			return null;
		}

		@Override
		public Duration negate() {
			return null;
		}

		@Override
		public Duration normalizeWith(Calendar startTimeInstant) {
			return null;
		}

		@Override
		public int compare(Duration duration) {
			return 0;
		}

		@Override
		public int hashCode() {
			return 0;
		}
	}

	@BeforeClass
	public static void init() {
		strategy.addSpecific(XMLGregorianCalendar.class, XMLGregorianCalendarImpl.class);
		strategy.addSpecific(Duration.class, DurationImpl.class);
	}

	@AfterClass
	public static void deinit() {
		strategy.removeSpecific(XMLGregorianCalendar.class);
		strategy.removeSpecific(Duration.class);
	}

	@Test
	public void testXMLGregorianCalendarManufacturing() throws Exception {

		XMLGregorianCalendar pojo = podam.manufacturePojo(XMLGregorianCalendar.class);
		Assert.assertNotNull("Construction failed", pojo);
	}

	@Test
	public void testDurationManufacturing() throws Exception {

		Duration pojo = podam.manufacturePojo(Duration.class);
		Assert.assertNotNull("Construction failed", pojo);
	}

	@Test
	public void testXMLDatatypesFieldSetting() throws Exception {

		XMLDatatypePojo pojo = podam.manufacturePojo(XMLDatatypePojo.class);
		Assert.assertNotNull("Construction failed", pojo);
		Assert.assertNotNull("XMLGregorianCalendar attr should not be empty", pojo.getCalendar());
		Assert.assertNotNull("Duration attr should not be empty", pojo.getDuration());
	}

}
