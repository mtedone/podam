package uk.co.jemos.podam.typeManufacturers;

import uk.co.jemos.podam.api.AttributeMetadata;
import uk.co.jemos.podam.api.DataProviderStrategy;
import uk.co.jemos.podam.api.PodamUtils;
import uk.co.jemos.podam.common.PodamConstants;
import uk.co.jemos.podam.common.PodamStringValue;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

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

        String retValue = null;

        List<Annotation> annotations = attributeMetadata.getAttributeAnnotations();

        if (annotations == null || annotations.isEmpty()) {

            retValue = getStringValue(attributeMetadata);

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

                    retValue = getStringOfLength(
                            podamAnnotation.length(), attributeMetadata);

                }

            }

            if (retValue == null) {
                retValue = getStringValue(attributeMetadata);
            }

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
