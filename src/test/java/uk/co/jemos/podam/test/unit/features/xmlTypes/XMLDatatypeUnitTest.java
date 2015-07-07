package uk.co.jemos.podam.test.unit.features.xmlTypes;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Title;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.test.dto.XMLDatatypePojo;
import uk.co.jemos.podam.test.unit.AbstractPodamSteps;

import javax.xml.datatype.Duration;
import javax.xml.datatype.XMLGregorianCalendar;

/**
 * Test @uk.co.jemos.podam.test.dto.JAXBElementPojo@ construction
 *
 * @author daivanov
 *
 */
@RunWith(SerenityRunner.class)
public class XMLDatatypeUnitTest extends AbstractPodamSteps{

	private static final Logger LOG = LoggerFactory.getLogger(XMLDatatypeUnitTest.class);


	@Test
	@Title("When given an external factory, Podam should be able to create instances of XMLGregorianCalendar")
	public void testXMLGregorianCalendarManufacturing() throws Exception {

		PodamFactory podamFactory = podamFactorySteps.givenAPodamFactoryWithXmlTypesExternalFactory();

		XMLGregorianCalendar pojo = podamInvocationSteps.whenIInvokeTheFactoryForClass(
				XMLGregorianCalendar.class, podamFactory);

		podamValidationSteps.theObjectShouldNotBeNull(pojo);
	}

	@Test
	@Title("When given an external factory, Podam should be able to create instances of the Duration class")
	public void testDurationManufacturing() throws Exception {

		PodamFactory podamFactory = podamFactorySteps.givenAPodamFactoryWithXmlTypesExternalFactory();

		Duration pojo = podamInvocationSteps.whenIInvokeTheFactoryForClass(Duration.class, podamFactory);
		podamValidationSteps.theObjectShouldNotBeNull(pojo);
	}

	@Test
	@Title("When given an external factory, Podam should be able to fill POJOs with instance attributes" +
			" containing XMLGregorianCalendar and Duration")
	public void testXMLDatatypesFieldSetting() throws Exception {

		PodamFactory podamFactory = podamFactorySteps.givenAPodamFactoryWithXmlTypesExternalFactory();

		XMLDatatypePojo pojo = podamInvocationSteps.whenIInvokeTheFactoryForClass(XMLDatatypePojo.class, podamFactory);
		podamValidationSteps.theObjectShouldNotBeNull(pojo);
		podamValidationSteps.theObjectShouldNotBeNull(pojo.getCalendar());
		podamValidationSteps.theObjectShouldNotBeNull(pojo.getDuration());
	}

}
