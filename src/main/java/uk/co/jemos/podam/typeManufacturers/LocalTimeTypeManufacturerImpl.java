package uk.co.jemos.podam.typeManufacturers;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.LocalTime;
import uk.co.jemos.podam.api.AttributeMetadata;
import uk.co.jemos.podam.api.DataProviderStrategy;
import uk.co.jemos.podam.api.PodamUtils;
import uk.co.jemos.podam.common.CommonTypeOrStrategy;
import uk.co.jemos.podam.common.ManufacturingContext;

/**
 * Default {@link java.time.LocalTime} type manufacturer.
 *
 * @author liam on 02/01/2024.
 * @since 8.0.1.RELEASE
 */
public class LocalTimeTypeManufacturerImpl extends AbstractTypeManufacturer<LocalTime> implements CommonTypeOrStrategy {
    /**
     * {@inheritDoc}
     */
    @Override
    public LocalTime getType(DataProviderStrategy strategy, AttributeMetadata attributeMetadata, ManufacturingContext manufacturingCtx) {

        long seconds = PodamUtils.getLongInRange(Instant.MIN.getEpochSecond(), Instant.MAX.getEpochSecond());
        long nanos = PodamUtils.getLongInRange(Instant.MIN.getNano(), Instant.MAX.getNano());

        Instant instant = Instant.ofEpochSecond(seconds, nanos);

        return LocalDateTime.ofInstant(instant, getZoneId()).toLocalTime();
    }
}
