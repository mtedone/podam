package uk.co.jemos.podam.typeManufacturers;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.concurrent.atomic.AtomicReference;
import uk.co.jemos.podam.api.AttributeMetadata;
import uk.co.jemos.podam.api.DataProviderStrategy;
import uk.co.jemos.podam.common.CommonTypeOrStrategy;
import uk.co.jemos.podam.common.ManufacturingContext;

/**
 * Default {@link java.time.LocalDateTime} type manufacturer.
 *
 * @author by liam on 02/01/2024.
 * @since 8.0.1.RELEASE
 */
public class LocalDateTimeTypeManufacturerImpl extends AbstractTypeManufacturer<LocalDateTime> implements CommonTypeOrStrategy {
    /**
     * {@inheritDoc}
     */
    @Override
    public LocalDateTime getType(DataProviderStrategy strategy, AttributeMetadata attributeMetadata, ManufacturingContext manufacturingCtx) {

        AtomicReference<Long> seconds = new AtomicReference<>();
        AtomicReference<Long> nanos = new AtomicReference<>();

        getSecondsNanos(attributeMetadata.getAttributeAnnotations(), seconds, nanos);

        Instant instant = Instant.ofEpochSecond(seconds.get(), nanos.get());

        return LocalDateTime.ofInstant(instant, getZoneId());
    }
}
