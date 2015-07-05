package uk.co.jemos.podam.typeManufacturers;

import uk.co.jemos.podam.api.AttributeMetadata;
import uk.co.jemos.podam.api.DataProviderStrategy;

/**
 * Wraps parameters for the Message Channel
 *
 * Created by tedonema on 28/06/2015.
 *
 * @since 6.0.0.RELEASE
 */
public class TypeManufacturerParamsWrapper {


    /** The Data DataProviderStrategy. */
    private final DataProviderStrategy dataProviderStrategy;

    /** The AttributeMetadata. */
    private final AttributeMetadata attributeMetadata;

    /**
     * Full constructor.
     * @param dataProviderStrategy The DataProviderStrategy
     * @param attributeMetadata The AttributeMetadata
     */
    public TypeManufacturerParamsWrapper(DataProviderStrategy dataProviderStrategy,
                                         AttributeMetadata attributeMetadata) {

        this.dataProviderStrategy = dataProviderStrategy;
        this.attributeMetadata = attributeMetadata;
    }

    public DataProviderStrategy getDataProviderStrategy() {
        return dataProviderStrategy;
    }

    public AttributeMetadata getAttributeMetadata() {
        return attributeMetadata;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("TypeManufacturerParamsWrapper{");
        sb.append("dataProviderStrategy=").append(dataProviderStrategy);
        sb.append(", attributeMetadata=").append(attributeMetadata);
        sb.append('}');
        return sb.toString();
    }
}
