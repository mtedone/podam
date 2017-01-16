package uk.co.jemos.podam.typeManufacturers;

import uk.co.jemos.podam.api.AttributeMetadata;
import uk.co.jemos.podam.api.DataProviderStrategy;
import uk.co.jemos.podam.api.PodamUtils;
import uk.co.jemos.podam.common.PodamConstants;
import uk.co.jemos.podam.common.PodamStringValue;

import java.lang.reflect.Type;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

/**
 * Default String type manufacturer.
 *
 * Created by tedonema on 17/05/2015.
 *
 * @since 6.0.0.RELEASE
 */
public class StringTypeManufacturerImpl extends AbstractTypeManufacturer<String> {

    /**
     * {@inheritDoc}
     */
    @Override
    public String getType(DataProviderStrategy strategy,
            AttributeMetadata attributeMetadata,
            Map<String, Type> genericTypesArgumentsMap) {

        String retValue;

        PodamStringValue annotationStrategy = findElementOfType(
                attributeMetadata.getAttributeAnnotations(), PodamStringValue.class);

            retValue = getStringValue(attributeMetadata);

        if (null != annotationStrategy) {

            retValue = annotationStrategy.strValue();
            if (StringUtils.isEmpty(retValue)) {

                retValue = getStringOfLength(
                        annotationStrategy.length(), attributeMetadata);
            }
        } else {
            retValue = getStringValue(attributeMetadata);
        }

        return retValue;
    }

	/** It returns a string value
	 * 
	 * @param attributeMetadata
	 *            attribute metadata for instance to be fetched
	 * @return A String of default length
	 */
	public String getStringValue(AttributeMetadata attributeMetadata) {

		return getStringOfLength(PodamConstants.STR_DEFAULT_LENGTH,
				attributeMetadata);
	}

	/**
	 * It returns a String of {@code length} characters.
	 * 
	 * @param length
	 *            The number of characters required in the returned String
	 * @param attributeMetadata
	 *            attribute metadata for instance to be fetched
	 * @return A String of {@code length} characters
	 */
	public String getStringOfLength(int length,
			AttributeMetadata attributeMetadata) {

		StringBuilder buff = new StringBuilder();

		while (buff.length() < length) {
			buff.append(PodamUtils.getNiceCharacter());
		}

		return buff.toString();
	}

}
