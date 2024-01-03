package uk.co.jemos.podam.typeManufacturers;

import java.time.ZoneId;
import uk.co.jemos.podam.api.AttributeMetadata;
import uk.co.jemos.podam.api.DataProviderStrategy;
import uk.co.jemos.podam.common.CommonTypeOrStrategy;
import uk.co.jemos.podam.common.ManufacturingContext;

/**
 * Default {@link java.time.ZoneId} type manufacturer.
 *
 * @author liam on 02/01/2024.
 * @since 8.0.1.RELEASE
 */
public class ZoneIdTypeManufacturerImpl extends AbstractTypeManufacturer<ZoneId> implements CommonTypeOrStrategy {
    /**
     * {@inheritDoc}
     */
    @Override
    public ZoneId getType(DataProviderStrategy strategy, AttributeMetadata attributeMetadata, ManufacturingContext manufacturingCtx) {
    	
        return getZoneId();
    }
}
