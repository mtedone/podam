package uk.co.jemos.podam.typeManufacturers;

import org.springframework.messaging.Message;

/**
 * Interface for a type manufacturer
 * Created by tedonema on 17/05/2015.
 *
 * @since 6.0.0.RELEASE
 */
public interface PodamTypes {

    /**
     * Returns a type value conforming to the annotations and the AttributeMetadata provided.
     * @return A type value conforming to the annotations and the AttributeMetadata provided.
     */
    Object getType(Message<? extends Object> message);
}
