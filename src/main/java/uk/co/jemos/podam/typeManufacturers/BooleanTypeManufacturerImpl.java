package uk.co.jemos.podam.typeManufacturers;

import uk.co.jemos.podam.api.AttributeMetadata;
import uk.co.jemos.podam.api.DataProviderStrategy;
import uk.co.jemos.podam.common.PodamBooleanValue;

import java.lang.reflect.Type;
import java.util.Map;

/**
 * Default boolean type manufacturer.
 *
 * Created by tedonema on 17/05/2015.
 *
 * @since 6.0.0.RELEASE
 */
public class BooleanTypeManufacturerImpl extends AbstractTypeManufacturer<Boolean> {

    /**
     * {@inheritDoc}
     */
    @Override
    public Boolean getType(DataProviderStrategy strategy,
            AttributeMetadata attributeMetadata,
            Map<String, Type> genericTypesArgumentsMap) {

        Boolean retValue;

        PodamBooleanValue annotationStrategy = findElementOfType(
                attributeMetadata.getAttributeAnnotations(), PodamBooleanValue.class);

        if (null != annotationStrategy) {
            retValue = annotationStrategy.boolValue();
        } else {
            retValue = getBoolean(attributeMetadata);
        }

        return retValue;
    }

	/** It returns a boolean/Boolean value.
	 * 
	 * @param attributeMetadata
	 *            attribute metadata for instance to be fetched
	 * @return a boolean/Boolean value
	 */
	public Boolean getBoolean(AttributeMetadata attributeMetadata) {

		return Boolean.TRUE;
	}

}
