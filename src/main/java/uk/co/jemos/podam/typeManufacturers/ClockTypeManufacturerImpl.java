package uk.co.jemos.podam.typeManufacturers;

import java.time.Clock;
import java.time.ZoneId;
import uk.co.jemos.podam.api.AttributeMetadata;
import uk.co.jemos.podam.api.DataProviderStrategy;
import uk.co.jemos.podam.common.CommonTypeOrStrategy;
import uk.co.jemos.podam.common.ManufacturingContext;
import uk.co.jemos.podam.common.PodamClockValue;

import static java.util.Objects.nonNull;

/**
 * Default {@link java.time.Clock} type manufacturer.
 *
 * @author liam on 02/01/2024.
 * @since 8.0.1.RELEASE
 */
public class ClockTypeManufacturerImpl extends AbstractTypeManufacturer<Clock> implements CommonTypeOrStrategy {
    /**
     * {@inheritDoc}
     */
    @Override
    public Clock getType(DataProviderStrategy strategy, AttributeMetadata attributeMetadata, ManufacturingContext manufacturingCtx) {

    	PodamClockValue annotationStrategy = findElementOfType(attributeMetadata.getAttributeAnnotations(), PodamClockValue.class);
        if (nonNull(annotationStrategy)) {
        	
        	return Clock.system(ZoneId.of(annotationStrategy.zoneId()));
        }

    	return Clock.system(getZoneId());
    }
}
