package uk.co.jemos.podam.typeManufacturers;

import java.time.*;

import uk.co.jemos.podam.api.AttributeMetadata;
import uk.co.jemos.podam.api.DataProviderStrategy;
import uk.co.jemos.podam.api.PodamUtils;
import uk.co.jemos.podam.common.CommonTypeOrStrategy;
import uk.co.jemos.podam.common.ManufacturingContext;

/**
 * Default {@link java.time.Period} type manufacturer.
 *
 * @author liam on 02/01/2024.
 * @since 8.0.1.RELEASE
 */
public class PeriodTypeManufacturerImpl extends AbstractTypeManufacturer<Period> implements CommonTypeOrStrategy {
    /**
     * {@inheritDoc}
     */
    @Override
    public Period getType(DataProviderStrategy strategy, AttributeMetadata attributeMetadata, ManufacturingContext manufacturingCtx) {

        int years = PodamUtils.getIntegerInRange(Year.MIN_VALUE, Year.MAX_VALUE);
        int months = PodamUtils.getIntegerInRange(1, 12);
        int days = PodamUtils.getIntegerInRange(1, YearMonth.of(years, months).atEndOfMonth().getDayOfMonth());
    	
        return Period.of(years, months, days);
    }
}
