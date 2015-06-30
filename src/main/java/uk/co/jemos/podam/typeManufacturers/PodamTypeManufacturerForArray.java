package uk.co.jemos.podam.typeManufacturers;

import uk.co.jemos.podam.typeManufacturers.wrappers.TypeManufacturerParamsWrapperForArray;

/**
 * Interface for an array type manufacturer
 *
 * Created by tedonema on 17/05/2015.
 *
 * @since 6.0.0.RELEASE
 */
public interface PodamTypeManufacturerForArray {

    /**
     * Returns an array type filled with values
     *
     * @param wrapper The Wrapper object containing parameters required by type manufacturers to work correctly
     *
     * @return an array type.
     */
    Object getType(TypeManufacturerParamsWrapperForArray wrapper);
}
