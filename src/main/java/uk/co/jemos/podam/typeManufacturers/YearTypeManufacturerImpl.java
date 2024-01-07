package uk.co.jemos.podam.typeManufacturers;

import java.time.Year;
import java.util.concurrent.atomic.AtomicReference;
import uk.co.jemos.podam.api.AttributeMetadata;
import uk.co.jemos.podam.api.DataProviderStrategy;
import uk.co.jemos.podam.common.CommonTypeOrStrategy;
import uk.co.jemos.podam.common.ManufacturingContext;

/**
 * Default {@link java.time.Year} type manufacturer.
 *
 * @author liam on 04/01/2024.
 * @since 8.0.1.RELEASE
 */
public class YearTypeManufacturerImpl extends AbstractTypeManufacturer<Year> implements CommonTypeOrStrategy {
    /**
     * {@inheritDoc}
     */
    @Override
    public Year getType(DataProviderStrategy strategy, AttributeMetadata attributeMetadata, ManufacturingContext manufacturingCtx) {

        AtomicReference<Integer> years = new AtomicReference<>();
        AtomicReference<Integer> months = new AtomicReference<>();
        AtomicReference<Integer> days = new AtomicReference<>();

        getYearsMonthsDays(attributeMetadata.getAttributeAnnotations(), years, months, days);

        return Year.of(years.get());
    }
}
