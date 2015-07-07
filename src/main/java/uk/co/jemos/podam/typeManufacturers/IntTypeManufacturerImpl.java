package uk.co.jemos.podam.typeManufacturers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import uk.co.jemos.podam.api.DataProviderStrategy;
import uk.co.jemos.podam.common.PodamConstants;
import uk.co.jemos.podam.common.PodamIntValue;

import java.lang.annotation.Annotation;

/**
 * Default int type manufacturer.
 *
 * Created by tedonema on 17/05/2015.
 *
 * @since 6.0.0.RELEASE
 */
public class IntTypeManufacturerImpl extends AbstractTypeManufacturer {

    /** The application logger */
    private static final Logger LOG = LoggerFactory.getLogger(IntTypeManufacturerImpl.class);

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer getType(TypeManufacturerParamsWrapper wrapper) {

        super.checkWrapperIsValid(wrapper);

        DataProviderStrategy strategy = wrapper.getDataProviderStrategy();

        Integer retValue = null;

        for (Annotation annotation : wrapper.getAttributeMetadata().getAttributeAnnotations()) {

            if (PodamIntValue.class.isAssignableFrom(annotation.getClass())) {
                PodamIntValue intStrategy = (PodamIntValue) annotation;

                String numValueStr = intStrategy.numValue();
                if (null != numValueStr && !"".equals(numValueStr)) {
                    try {
                        retValue = Integer.valueOf(numValueStr);
                    } catch (NumberFormatException nfe) {
                        String errMsg = PodamConstants.THE_ANNOTATION_VALUE_STR
                                + numValueStr
                                + " could not be converted to an Integer. An exception will be thrown.";
                        LOG.error(errMsg);
                        throw new IllegalArgumentException(errMsg, nfe);

                    }

                } else {

                    int minValue = intStrategy.minValue();
                    int maxValue = intStrategy.maxValue();

                    // Sanity check
                    if (minValue > maxValue) {
                        maxValue = minValue;
                    }

                    retValue = strategy.getIntegerInRange(minValue, maxValue,
                            wrapper.getAttributeMetadata());

                }

                break;

            }

        }

        if (retValue == null) {
            retValue = strategy.getInteger(wrapper.getAttributeMetadata());
        }

        return retValue;
    }
}
