package uk.co.jemos.podam.typeManufacturers;

import java.lang.reflect.Type;
import java.util.Map;

import uk.co.jemos.podam.api.AttributeMetadata;
import uk.co.jemos.podam.api.DataProviderStrategy;
import uk.co.jemos.podam.api.PodamUtils;

/**
 * Default Enum type manufacturer.
 *
 * Created by tedonema on 17/05/2015.
 *
 * @since 6.0.0.RELEASE
 */
public class EnumTypeManufacturerImpl extends AbstractTypeManufacturer<Enum<?>> {

    /**
     * {@inheritDoc}
     */
    @Override
    public Enum<?> getType(DataProviderStrategy strategy,
            AttributeMetadata attributeMetadata,
            Map<String, Type> genericTypesArgumentsMap) {

        Class<?> realAttributeType = attributeMetadata.getAttributeType();

        Object[] enumConstants = realAttributeType.getEnumConstants();
        if (null == enumConstants) {
            enumConstants = Thread.State.class.getEnumConstants();
        }

        Enum<?> retValue = null;
        final int enumConstantsLength = enumConstants.length;
        if (enumConstantsLength > 0) {
            int enumIndex = PodamUtils.getIntegerInRange(0, enumConstantsLength)
                    % enumConstantsLength;
            retValue = (Enum<?>) enumConstants[enumIndex];
        }

        return retValue;
    }
}
