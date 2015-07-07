package uk.co.jemos.podam.typeManufacturers;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import uk.co.jemos.podam.api.DataProviderStrategy;
import uk.co.jemos.podam.common.PodamConstants;
import uk.co.jemos.podam.common.PodamDoubleValue;

import java.lang.annotation.Annotation;

/**
 * Default double type manufacturer.
 *
 * Created by tedonema on 17/05/2015.
 *
 * @since 6.0.0.RELEASE
 */
public class DoubleTypeManufacturerImpl extends AbstractTypeManufacturer {

    /** The application logger */
    private static final Logger LOG = LoggerFactory.getLogger(DoubleTypeManufacturerImpl.class);

    /**
     * {@inheritDoc}
     */
    @Override
    public Double getType(TypeManufacturerParamsWrapper wrapper) {

        super.checkWrapperIsValid(wrapper);

        DataProviderStrategy strategy = wrapper.getDataProviderStrategy();

        Double retValue = null;

        for (Annotation annotation : wrapper.getAttributeMetadata().getAttributeAnnotations()) {

            if (PodamDoubleValue.class.isAssignableFrom(annotation.getClass())) {
                PodamDoubleValue doubleStrategy = (PodamDoubleValue) annotation;

                String numValueStr = doubleStrategy.numValue();
                if (null != numValueStr && !"".equals(numValueStr)) {

                    try {
                        retValue = Double.valueOf(numValueStr);
                    } catch (NumberFormatException nfe) {
                        String errMsg = PodamConstants.THE_ANNOTATION_VALUE_STR
                                + numValueStr
                                + " could not be converted to a Double. An exception will be thrown.";
                        LOG.error(errMsg);
                        throw new IllegalArgumentException(errMsg, nfe);
                    }

                } else {

                    double minValue = doubleStrategy.minValue();
                    double maxValue = doubleStrategy.maxValue();

                    // Sanity check
                    if (minValue > maxValue) {
                        maxValue = minValue;
                    }

                    retValue = strategy.getDoubleInRange(minValue, maxValue,
                            wrapper.getAttributeMetadata());
                }

                break;

            }

        }

        if (retValue == null) {
            retValue = strategy.getDouble(wrapper.getAttributeMetadata());
        }

        return retValue;
    }
}
