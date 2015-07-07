package uk.co.jemos.podam.typeManufacturers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import uk.co.jemos.podam.api.DataProviderStrategy;
import uk.co.jemos.podam.common.PodamBooleanValue;

import java.lang.annotation.Annotation;

/**
 * Default boolean type manufacturer.
 *
 * Created by tedonema on 17/05/2015.
 *
 * @since 6.0.0.RELEASE
 */
public class BooleanTypeManufacturerImpl extends AbstractTypeManufacturer {

    /** The application logger */
    private static final Logger LOG = LoggerFactory.getLogger(BooleanTypeManufacturerImpl.class);

    /**
     * {@inheritDoc}
     */
    @Override
    public Boolean getType(TypeManufacturerParamsWrapper wrapper) {

        super.checkWrapperIsValid(wrapper);

        DataProviderStrategy strategy = wrapper.getDataProviderStrategy();

        Boolean retValue = null;

        for (Annotation annotation : wrapper.getAttributeMetadata().getAttributeAnnotations()) {

            if (PodamBooleanValue.class.isAssignableFrom(annotation.getClass())) {
                PodamBooleanValue localStrategy = (PodamBooleanValue) annotation;
                retValue = localStrategy.boolValue();

                break;
            }
        }

        if (retValue == null) {
            retValue = strategy.getBoolean(wrapper.getAttributeMetadata());
        }

        return retValue;
    }

}
