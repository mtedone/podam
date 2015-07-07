package uk.co.jemos.podam.typeManufacturers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import uk.co.jemos.podam.api.DataProviderStrategy;
import uk.co.jemos.podam.common.PodamConstants;
import uk.co.jemos.podam.common.PodamLongValue;

import java.lang.annotation.Annotation;

/**
 * Default int type manufacturer.
 *
 * Created by tedonema on 17/05/2015.
 *
 * @since 6.0.0.RELEASE
 */
public class LongTypeManufacturerImpl extends AbstractTypeManufacturer {

    /** The application logger */
    private static final Logger LOG = LoggerFactory.getLogger(LongTypeManufacturerImpl.class);

    /**
     * {@inheritDoc}
     */
    @Override
    public Long getType(TypeManufacturerParamsWrapper wrapper) {

        super.checkWrapperIsValid(wrapper);

        DataProviderStrategy strategy = wrapper.getDataProviderStrategy();

        Long retValue = null;

        for (Annotation annotation : wrapper.getAttributeMetadata().getAttributeAnnotations()) {

            if (PodamLongValue.class.isAssignableFrom(annotation.getClass())) {
                PodamLongValue longStrategy = (PodamLongValue) annotation;

                String numValueStr = longStrategy.numValue();
                if (null != numValueStr && !"".equals(numValueStr)) {
                    try {
                        retValue = Long.valueOf(numValueStr);
                    } catch (NumberFormatException nfe) {
                        String errMsg = PodamConstants.THE_ANNOTATION_VALUE_STR
                                + numValueStr
                                + " could not be converted to a Long. An exception will be thrown.";
                        LOG.error(errMsg);
                        throw new IllegalArgumentException(errMsg, nfe);
                    }
                } else {

                    long minValue = longStrategy.minValue();
                    long maxValue = longStrategy.maxValue();

                    // Sanity check
                    if (minValue > maxValue) {
                        maxValue = minValue;
                    }

                    retValue = strategy.getLongInRange(minValue, maxValue,
                            wrapper.getAttributeMetadata());

                }

                break;

            }

        }

        if (retValue == null) {
            retValue = strategy.getLong(wrapper.getAttributeMetadata());
        }

        return retValue;
    }
}
