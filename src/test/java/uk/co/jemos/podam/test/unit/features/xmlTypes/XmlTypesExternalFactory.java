package uk.co.jemos.podam.test.unit.features.xmlTypes;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import uk.co.jemos.podam.api.AbstractExternalFactory;

import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.Duration;
import javax.xml.datatype.XMLGregorianCalendar;
import java.lang.reflect.Type;
import java.util.GregorianCalendar;

/**
 * Created by tedonema on 21/06/2015.
 */
public class XmlTypesExternalFactory extends AbstractExternalFactory {


    /** The application logger */
    private static final Logger LOG = LoggerFactory.getLogger(XmlTypesExternalFactory.class);

    @Override
    public <T> T manufacturePojo(Class<T> pojoClass, Type... genericTypeArgs) {
        try {
            if (pojoClass.isAssignableFrom(XMLGregorianCalendar.class)) {
                DatatypeFactory factory = DatatypeFactory.newInstance();
                T calendar = (T) factory.newXMLGregorianCalendar(new GregorianCalendar());
                LOG.info("Externally created XMLGregorianCalendar");
                return calendar;
            } else if (pojoClass.isAssignableFrom(Duration.class)) {
                DatatypeFactory factory = DatatypeFactory.newInstance();
                @SuppressWarnings("unchecked")
                T duration = (T) factory.newDuration(0L);
                LOG.info("Externally created Duration");
                return duration;
            }
        } catch (Exception e) {
            throw new IllegalStateException("Manufacturing failed", e);
        }
        return null;
    }

    @Override
    public <T> T populatePojo(T pojo, Type... genericTypeArgs) {
        return pojo;
    }
}
