package uk.co.jemos.podam.typeManufacturers;

import uk.co.jemos.podam.api.AttributeMetadata;
import uk.co.jemos.podam.api.DataProviderStrategy;
import uk.co.jemos.podam.api.PodamUtils;
import uk.co.jemos.podam.common.PodamCharValue;

import java.lang.reflect.Type;
import java.util.Map;

/**
 * Default character type manufacturer.
 *
 * Created by tedonema on 17/05/2015.
 *
 * @since 6.0.0.RELEASE
 */
public class CharTypeManufacturerImpl extends AbstractTypeManufacturer<Character> {

    /**
     * {@inheritDoc}
     */
    @Override
    public Character getType(DataProviderStrategy strategy,
            AttributeMetadata attributeMetadata,
            Map<String, Type> genericTypesArgumentsMap) {

        Character retValue;

        PodamCharValue annotationStrategy = findElementOfType(
                attributeMetadata.getAttributeAnnotations(), PodamCharValue.class);
        if (null != annotationStrategy) {

            retValue = annotationStrategy.charValue();
            if (retValue == ' ') {

                char minValue = annotationStrategy.minValue();
                char maxValue = annotationStrategy.maxValue();

                // Sanity check
                if (minValue > maxValue) {
                    maxValue = minValue;
                }

                retValue = getCharacterInRange(minValue, maxValue,
                        attributeMetadata);
            }
        } else {
            retValue = getCharacter(attributeMetadata);
        }

        return retValue;
    }

	/** It returns a char/Character value.
	 * 
	 * @param attributeMetadata
	 *            attribute metadata for instance to be fetched
	 * @return a char/Character value
	 */
	public Character getCharacter(AttributeMetadata attributeMetadata) {

		return PodamUtils.getNiceCharacter();
	}

	/**
	 * It returns a char/Character value between min and max value (included).
	 * 
	 * @param minValue
	 *            The minimum value for the returned value
	 * @param maxValue
	 *            The maximum value for the returned value
	 * @param attributeMetadata
	 *            attribute metadata for instance to be fetched
	 * @return A char/Character value between min and max value (included).
	 */
	public Character getCharacterInRange(char minValue, char maxValue,
			AttributeMetadata attributeMetadata) {

		return (char)PodamUtils.getIntegerInRange(minValue, maxValue);
	}

}
