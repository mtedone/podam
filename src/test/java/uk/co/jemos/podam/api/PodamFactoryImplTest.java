/**
 */
package uk.co.jemos.podam.api;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import org.junit.Test;
import static org.junit.Assert.*;
import uk.co.jemos.podam.annotations.PodamCollection;
import uk.co.jemos.podam.annotations.PodamIntValue;
import uk.co.jemos.podam.annotations.PodamStringValue;

/**
 *
 * @author Stosh
 */
@XmlRootElement
public class PodamFactoryImplTest {

    public PodamFactoryImplTest() {
    }

    /**
     * Convert a class into XML in a simple way using Podam and JAXB. Podam will be tested using
     * this utility method. The idea is that you create a class that Podam can only instantiate one
     * way. You then ask Podam to manufacture an object of the class and turn that object into XML.
     * A pre-calculated answer is compared to the result to determine if Podam got the correct
     * answer.
     *
     * @param pojoClass which class is going to be converted
     * @return
     * @throws JAXBException
     */
    public String thisClassToXml(Class<?> pojoClass) throws JAXBException {

        /*
         * create the podam object with Podam
         */
        PodamFactoryImpl instance = new PodamFactoryImpl();
        Object podamObject = instance.manufacturePojo(pojoClass);

        /*
         * Marshal it into XML to make it easy to test. Skip XML header for brevity.
         */
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(1000);
        JAXBContext jaxbContext = JAXBContext.newInstance(pojoClass);
        Marshaller m = jaxbContext.createMarshaller();
        m.setProperty(Marshaller.JAXB_FRAGMENT, Boolean.TRUE);
        m.marshal(podamObject, byteArrayOutputStream);

        /*
         * return as a string for easy comparison
         */
        String xmlText = byteArrayOutputStream.toString();
        return xmlText;
    }

    @Test
    public void testEmptyPojo() throws JAXBException {
        String xmlText = thisClassToXml(this.getClass());
        System.out.println(xmlText);
        assertEquals("<podamFactoryImplTest/>", xmlText);
    }

    @XmlRootElement
    public static class PublicStringAttributeWithoutSetter extends PodamFactoryImplTest {

        @XmlElement
        @PodamStringValue(strValue = "correct string")
        public String dummy = "this text should never show up in xml output";
    }

    @Test
    public void testPublicStringAttributeWithoutSetter() throws JAXBException {
        Object here = new PodamFactoryImplTest.PublicStringAttributeWithoutSetter();
        String xmlText = thisClassToXml(here.getClass());
        System.out.println(xmlText);
        assertEquals("<publicStringAttributeWithoutSetter><dummy>correct string</dummy></publicStringAttributeWithoutSetter>", xmlText);
    }

    @XmlRootElement
    public static class PublicStringAttributeWithoutSetterWithDistraction extends PodamFactoryImplTest {

        @XmlElement
        @PodamStringValue(strValue = "correct string")
        public String dummy = "this text should never show up in xml output";

        /*
         * This distracts the Podam code away from the case where there are
         * zero setters.
         */
//        @XmlElement
        @PodamIntValue(minValue=1, maxValue=1)
        private int distraction;

        public void setDistraction(int distraction) {
            this.distraction = distraction;
        }
    }

    @Test
    public void testPublicStringAttributeWithoutSetterWithDistraction() throws JAXBException {
        Object here = new PodamFactoryImplTest.PublicStringAttributeWithoutSetterWithDistraction();
        String xmlText = thisClassToXml(here.getClass());
        System.out.println(xmlText);
        assertEquals("<publicStringAttributeWithoutSetterWithDistraction>"
                + "<dummy>correct string</dummy>"
//                + "<distraction>1</distraction>"
                + "</publicStringAttributeWithoutSetterWithDistraction>", xmlText);
    }

    @XmlRootElement
    public static class PrivateStringAttributeWithoutSetter extends PodamFactoryImplTest {

        @XmlElement
        @PodamStringValue(strValue = "correct string")
        public String dummy = "this text should never show up in xml output";
    }

    @Test
    public void testPrivateStringAttributeWithoutSetter() throws JAXBException {
        Object here = new PodamFactoryImplTest.PrivateStringAttributeWithoutSetter();
        String xmlText = thisClassToXml(here.getClass());
        System.out.println(xmlText);
        assertEquals("<privateStringAttributeWithoutSetter><dummy>correct string</dummy></privateStringAttributeWithoutSetter>", xmlText);
    }

    @XmlRootElement
    private static class StringAttributeWithSetter extends PodamFactoryImplTest {

        @PodamStringValue(strValue = "correct string")
        private String dummy = "this text should never show up in xml output";

        @XmlElement
        public void setDummy(String dummy) {
            this.dummy = dummy;
        }

        // getter required by JAXB
        public String getDummy() {
            return dummy;
        }
    }

    @Test
    public void testStringAttributeWithSetter() throws JAXBException {
        Object here = new PodamFactoryImplTest.StringAttributeWithSetter();
        String xmlText = thisClassToXml(here.getClass());
        System.out.println(xmlText);
        assertEquals("<stringAttributeWithSetter><dummy>correct string</dummy></stringAttributeWithSetter>", xmlText);
    }

    @XmlRootElement
    private static class ListWithoutSetter extends PodamFactoryImplTest {

        public static class CustomArrayList<T> extends ArrayList<T> {

            public CustomArrayList(Collection<? extends T> c) {
                super(c);
            }
        };
        @XmlElement
        @PodamStringValue(strValue = "jelly")
        @PodamCollection(nbrElements = 1)
        protected List<String> mylist = new PodamFactoryImplTest.ListWithoutSetter.CustomArrayList<String>(Arrays.asList(new String[]{"peanutbutter"}));
    }

    @Test
    public void testListWithoutSetter() throws JAXBException {
        PodamFactoryImplTest.ListWithoutSetter here = new PodamFactoryImplTest.ListWithoutSetter();
        String xmlText = thisClassToXml(here.getClass());
        System.out.println(xmlText);
        assertEquals("<listWithoutSetter><mylist>peanutbutter</mylist>"
                + "<mylist>jelly</mylist></listWithoutSetter>",
                xmlText);
        assertEquals(here.mylist.getClass(), PodamFactoryImplTest.ListWithoutSetter.CustomArrayList.class);
    }
}
/*

Tests in error: 
  testImmutablePojoWithNonGenericCollections(uk.co.jemos.podam.test.unit.PodamMockerUnitTest): An exception occurred while creating a Map object
  testImmutablePojoWithGenerifiedCollectionsInConstructor(uk.co.jemos.podam.test.unit.PodamMockerUnitTest): An exception occurred while creating a Map object
  testSingletonWithParametersInPublicStaticMethod(uk.co.jemos.podam.test.unit.PodamMockerUnitTest): An exception occurred while creating a Map object
  testSomeJavaNativeClasses(uk.co.jemos.podam.test.unit.PodamMockerUnitTest): Invocation Target Exception

 
 */