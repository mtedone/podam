package uk.co.jemos.podam.typeManufacturers;

import uk.co.jemos.podam.api.AttributeMetadata;
import uk.co.jemos.podam.api.DataProviderStrategy;
import uk.co.jemos.podam.api.PodamUtils;
import uk.co.jemos.podam.common.CommonTypeOrStrategy;
import uk.co.jemos.podam.common.ManufacturingContext;

import java.time.MonthDay;
import java.time.Year;
import java.time.YearMonth;

/**
 * Default {@link MonthDay} type manufacturer.
 *
 * @author liam on 04/01/2024.
 * @since 8.0.1.RELEASE
 */
public class MonthDayTypeManufacturerImpl extends AbstractTypeManufacturer<MonthDay> implements CommonTypeOrStrategy {
    /**
     * {@inheritDoc}
     */
    @Override
    public MonthDay getType(DataProviderStrategy strategy, AttributeMetadata attributeMetadata, ManufacturingContext manufacturingCtx) {

    	int years = PodamUtils.getIntegerInRange(Year.MIN_VALUE, Year.MAX_VALUE);
    	int months = PodamUtils.getIntegerInRange(1, 12);
        int days = PodamUtils.getIntegerInRange(1, YearMonth.of(years, months).atEndOfMonth().getDayOfMonth());

        return MonthDay.of(months, days);
    }
}
