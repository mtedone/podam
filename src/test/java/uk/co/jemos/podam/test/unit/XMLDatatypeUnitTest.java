package uk.co.jemos.podam.test.unit;

import java.lang.reflect.Type;
import java.util.GregorianCalendar;

import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.Duration;
import javax.xml.datatype.XMLGregorianCalendar;

import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import uk.co.jemos.podam.api.ClassInfoStrategy;
import uk.co.jemos.podam.api.DataProviderStrategy;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;
import uk.co.jemos.podam.test.dto.XMLDatatypePojo;

/**
 * Test @uk.co.jemos.podam.test.dto.JAXBElementPojo@ construction
 *
 * @author daivanov
 *
 */
public class XMLDatatypeUnitTest {

	private static final Logger LOG = LoggerFactory.getLogger(XMLDatatypeUnitTest.class);

	protected static final Type[] NO_TYPES = new Type[0];

	private final static PodamFactory externalFactory =
			new PodamFactory() {

				@Override
				public <T> T manufacturePojo(Class<T> pojoClass,
						Type... genericTypeArgs) {
					try {
						if (pojoClass.isAssignableFrom(XMLGregorianCalendar.class)) {
							DatatypeFactory factory = DatatypeFactory.newInstance();
							@SuppressWarnings("unchecked")
							T calendar = (T) factory.newXMLGregorianCalendar(new GregorianCalendar());
							LOG.info("Externally created XMLGregorianCalendar");
							return calendar;
						} else if (pojoClass.isAssignableFrom(Duration.class)) {
							DatatypeFactory factory = DatatypeFactory.newInstance();
							@SuppressWarnings("unchecked")
							T duration = (T) factory.newDuration(0L);
							LOG.info("Externally created Duration");
							return duration;
						} else {
						}
					} catch (Exception e) {
						throw new IllegalStateException("Manufacturing failed", e);
					}
					return null;
				}

				@Override
				public <T> T manufacturePojo(Class<T> pojoClass) {
					return this.manufacturePojo(pojoClass, NO_TYPES);
				}

				@Override
				public <T> T manufacturePojoWithFullData(Class<T> pojoClass,
						Type... genericTypeArgs) {
					return this.manufacturePojo(pojoClass, genericTypeArgs);
				}

				@Override
				public DataProviderStrategy getStrategy() {
					return null;
				}

				@Override
				public ClassInfoStrategy getClassStrategy() {
					return null;
				}

				@Override
				public PodamFactory setClassStrategy(
						ClassInfoStrategy classInfoStrategy) {
					return null;
				}
	};

	private final static PodamFactory podam = new PodamFactoryImpl(externalFactory);

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
