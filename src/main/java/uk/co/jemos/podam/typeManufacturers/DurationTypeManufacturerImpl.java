package uk.co.jemos.podam.typeManufacturers;

import java.time.Duration;
import java.util.concurrent.atomic.AtomicReference;

import uk.co.jemos.podam.api.AttributeMetadata;
import uk.co.jemos.podam.api.DataProviderStrategy;
import uk.co.jemos.podam.common.CommonTypeOrStrategy;
import uk.co.jemos.podam.common.ManufacturingContext;

/**
 * Default {@link java.time.Duration} type manufacturer.
 *
 * @author liam on 02/01/2024.
 * @since 8.0.1.RELEASE
 */
public class DurationTypeManufacturerImpl extends AbstractTypeManufacturer<Duration> implements CommonTypeOrStrategy {
    /**
     * {@inheritDoc}
     */
    @Override
    public Duration getType(DataProviderStrategy strategy, AttributeMetadata attributeMetadata, ManufacturingContext manufacturingCtx) {

        AtomicReference<Long> seconds = new AtomicReference<>();
        AtomicReference<Long> nanos = new AtomicReference<>();

        getSecondsNanos(attributeMetadata.getAttributeAnnotations(), seconds, nanos);

        return Duration.ofSeconds(seconds.get(), nanos.get());
    }
}
