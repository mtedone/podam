package uk.co.jemos.podam.typeManufacturers;

import uk.co.jemos.podam.api.AttributeMetadata;
import uk.co.jemos.podam.api.DataProviderStrategy;
import uk.co.jemos.podam.common.PodamCharValue;

import java.lang.annotation.Annotation;
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

        Character retValue = null;

        for (Annotation annotation : attributeMetadata.getAttributeAnnotations()) {

            if (PodamCharValue.class.isAssignableFrom(annotation.getClass())) {
                PodamCharValue annotationStrategy = (PodamCharValue) annotation;

                char charValue = annotationStrategy.charValue();
                if (charValue != ' ') {
                    retValue = charValue;

                } else {

                    char minValue = annotationStrategy.minValue();
                    char maxValue = annotationStrategy.maxValue();

                    // Sanity check
                    if (minValue > maxValue) {
                        maxValue = minValue;
                    }

                    retValue = strategy.getCharacterInRange(minValue, maxValue,
                            attributeMetadata);

                }

                break;

            }
        }

        if (retValue == null) {
            retValue = strategy.getCharacter(attributeMetadata);
        }

        return retValue;
    }
}
