package uk.co.jemos.podam.typeManufacturers;

import java.time.ZoneOffset;
import uk.co.jemos.podam.api.AttributeMetadata;
import uk.co.jemos.podam.api.DataProviderStrategy;
import uk.co.jemos.podam.api.PodamUtils;
import uk.co.jemos.podam.common.CommonTypeOrStrategy;
import uk.co.jemos.podam.common.ManufacturingContext;

/**
 * Default {@link java.time.ZoneOffset} type manufacturer.
 *
 * @author liam on 02/01/2024.
 * @since 8.0.1.RELEASE
 */
public class ZoneOffsetTypeManufacturerImpl extends AbstractTypeManufacturer<ZoneOffset> implements CommonTypeOrStrategy {
    /**
     * {@inheritDoc}
     */
    @Override
    public ZoneOffset getType(DataProviderStrategy strategy, AttributeMetadata attributeMetadata, ManufacturingContext manufacturingCtx) {

        int seconds = 0;
        int minutes = 0;
        int hours = PodamUtils.getIntegerInRange(-17, 17);
        if(hours < 0) {
        
        	minutes = PodamUtils.getIntegerInRange(-59, 0);
        	seconds = PodamUtils.getIntegerInRange(-59, 0);
        	
        } else {

        	minutes = PodamUtils.getIntegerInRange(0, 59);
        	seconds = PodamUtils.getIntegerInRange(0, 59);
        }

    	return ZoneOffset.ofHoursMinutesSeconds(hours, minutes, seconds);	
    }
}
