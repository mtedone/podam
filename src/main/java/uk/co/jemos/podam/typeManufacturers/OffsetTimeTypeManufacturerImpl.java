package uk.co.jemos.podam.typeManufacturers;

import java.time.Instant;
import java.time.OffsetTime;
import uk.co.jemos.podam.api.AttributeMetadata;
import uk.co.jemos.podam.api.DataProviderStrategy;
import uk.co.jemos.podam.api.PodamUtils;
import uk.co.jemos.podam.common.CommonTypeOrStrategy;
import uk.co.jemos.podam.common.ManufacturingContext;

/**
 * Default {@link java.time.OffsetTime} type manufacturer.
 *
 * @author liam on 02/01/2024.
 * @since 8.0.1.RELEASE
 */
public class OffsetTimeTypeManufacturerImpl extends AbstractTypeManufacturer<OffsetTime> implements CommonTypeOrStrategy {
    /**
     * {@inheritDoc}
     */
    @Override
    public OffsetTime getType(DataProviderStrategy strategy, AttributeMetadata attributeMetadata, ManufacturingContext manufacturingCtx) {

        long seconds = PodamUtils.getLongInRange(Instant.MIN.getEpochSecond(), Instant.MAX.getEpochSecond());
        long nanos = PodamUtils.getLongInRange(Instant.MIN.getNano(), Instant.MAX.getNano());

        Instant instant = Instant.ofEpochSecond(seconds, nanos);

        return OffsetTime.ofInstant(instant, getZoneId());
    }
}
