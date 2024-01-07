package uk.co.jemos.podam.typeManufacturers;

import uk.co.jemos.podam.api.AttributeMetadata;
import uk.co.jemos.podam.api.DataProviderStrategy;
import uk.co.jemos.podam.common.CommonTypeOrStrategy;
import uk.co.jemos.podam.common.ManufacturingContext;

import java.time.YearMonth;
import java.util.concurrent.atomic.AtomicReference;

/**
 * Default {@link YearMonth} type manufacturer.
 *
 * @author liam on 04/01/2024.
 * @since 8.0.1.RELEASE
 */
public class YearMonthTypeManufacturerImpl extends AbstractTypeManufacturer<YearMonth> implements CommonTypeOrStrategy {
    /**
     * {@inheritDoc}
     */
    @Override
    public YearMonth getType(DataProviderStrategy strategy, AttributeMetadata attributeMetadata, ManufacturingContext manufacturingCtx) {

        AtomicReference<Integer> years = new AtomicReference<>();
        AtomicReference<Integer> months = new AtomicReference<>();
        AtomicReference<Integer> days = new AtomicReference<>();

        getYearsMonthsDays(attributeMetadata.getAttributeAnnotations(), years, months, days);

        return YearMonth.of(years.get(), months.get());
    }
}
