package uk.co.jemos.podam.typeManufacturers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import uk.co.jemos.podam.api.AttributeMetadata;
import uk.co.jemos.podam.api.DataProviderStrategy;

/**
 * Default Enum type manufacturer.
 *
 * Created by tedonema on 17/05/2015.
 *
 * @since 6.0.0.RELEASE
 */
public class EnumTypeManufacturerImpl extends AbstractTypeManufacturer {

    /** The application logger */
    private static final Logger LOG = LoggerFactory.getLogger(EnumTypeManufacturerImpl.class);

    /**
     * {@inheritDoc}
     */
    @Override
    public Object getType(TypeManufacturerParamsWrapper wrapper) {

        super.checkWrapperIsValid(wrapper);

        DataProviderStrategy strategy = wrapper.getDataProviderStrategy();

        AttributeMetadata attributeMetadata = wrapper.getAttributeMetadata();

        Class<?> realAttributeType = attributeMetadata.getAttributeType();

        Object retValue = null;

        // Enum type
        int enumConstantsLength = realAttributeType.getEnumConstants().length;

        if (enumConstantsLength > 0) {
            int enumIndex = strategy.getIntegerInRange(0,
                    enumConstantsLength, attributeMetadata)
                    % enumConstantsLength;
            retValue = realAttributeType.getEnumConstants()[enumIndex];
        }

        return retValue;
    }
}
