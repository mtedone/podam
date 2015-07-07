package uk.co.jemos.podam.typeManufacturers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import uk.co.jemos.podam.api.DataProviderStrategy;
import uk.co.jemos.podam.common.PodamShortValue;

import java.lang.annotation.Annotation;

/**
 * Default short type manufacturer.
 *
 * Created by tedonema on 17/05/2015.
 *
 * @since 6.0.0.RELEASE
 */
public class ShortTypeManufacturerImpl extends AbstractTypeManufacturer {

    /** The application logger */
    private static final Logger LOG = LoggerFactory.getLogger(ShortTypeManufacturerImpl.class);

    /**
     * {@inheritDoc}
     */
    @Override
    public Short getType(TypeManufacturerParamsWrapper wrapper) {

        super.checkWrapperIsValid(wrapper);

        DataProviderStrategy strategy = wrapper.getDataProviderStrategy();

        Short retValue = null;

        for (Annotation annotation : wrapper.getAttributeMetadata().getAttributeAnnotations()) {

            if (PodamShortValue.class.isAssignableFrom(annotation.getClass())) {
                PodamShortValue shortStrategy = (PodamShortValue) annotation;

                String numValueStr = shortStrategy.numValue();
                if (null != numValueStr && !"".equals(numValueStr)) {
                    try {
                        retValue = Short.valueOf(numValueStr);
                    } catch (NumberFormatException nfe) {
                        String errMsg = "The precise value: "
                                + numValueStr
                                + " cannot be converted to a short type. An exception will be thrown.";
                        LOG.error(errMsg);
                        throw new IllegalArgumentException(errMsg, nfe);
                    }
                } else {

                    short minValue = shortStrategy.minValue();
                    short maxValue = shortStrategy.maxValue();

                    // Sanity check
                    if (minValue > maxValue) {
                        maxValue = minValue;
                    }

                    retValue = strategy.getShortInRange(minValue, maxValue,
                            wrapper.getAttributeMetadata());

                }

                break;

            }
        }

        if (retValue == null) {
            retValue = strategy.getShort(wrapper.getAttributeMetadata());
        }

        return retValue;
    }
}
