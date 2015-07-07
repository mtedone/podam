package uk.co.jemos.podam.typeManufacturers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import uk.co.jemos.podam.api.AttributeMetadata;
import uk.co.jemos.podam.api.DataProviderStrategy;
import uk.co.jemos.podam.common.PodamStringValue;

import java.lang.annotation.Annotation;
import java.util.List;

/**
 * Default String type manufacturer.
 *
 * Created by tedonema on 17/05/2015.
 *
 * @since 6.0.0.RELEASE
 */
public class StringTypeManufacturerImpl extends AbstractTypeManufacturer {

    /** The application logger */
    private static final Logger LOG = LoggerFactory.getLogger(StringTypeManufacturerImpl.class);

    /**
     * {@inheritDoc}
     */
    @Override
    public String getType(TypeManufacturerParamsWrapper wrapper) {

        super.checkWrapperIsValid(wrapper);

        DataProviderStrategy strategy = wrapper.getDataProviderStrategy();

        String retValue = null;

        AttributeMetadata attributeMetadata = wrapper.getAttributeMetadata();

        List<Annotation> annotations = attributeMetadata.getAttributeAnnotations();

        if (annotations == null || annotations.isEmpty()) {

            retValue = strategy.getStringValue(attributeMetadata);

        } else {

            for (Annotation annotation : annotations) {

                if (!PodamStringValue.class.isAssignableFrom(annotation
                        .getClass())) {
                    continue;
                }

                // A specific value takes precedence over the length
                PodamStringValue podamAnnotation = (PodamStringValue) annotation;

                if (podamAnnotation.strValue() != null
                        && podamAnnotation.strValue().length() > 0) {

                    retValue = podamAnnotation.strValue();

                } else {

                    retValue = strategy.getStringOfLength(
                            podamAnnotation.length(), attributeMetadata);

                }

            }

            if (retValue == null) {
                retValue = strategy.getStringValue(attributeMetadata);
            }

        }

        return retValue;
    }
}
