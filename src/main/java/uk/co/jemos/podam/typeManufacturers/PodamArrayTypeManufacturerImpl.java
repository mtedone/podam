package uk.co.jemos.podam.typeManufacturers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import uk.co.jemos.podam.typeManufacturers.wrappers.TypeManufacturerParamsWrapperForArray;

import java.lang.reflect.Array;

/**
 * Default String type manufacturer.
 *
 * Created by tedonema on 17/05/2015.
 *
 * @since 6.0.0.RELEASE
 */
public class PodamArrayTypeManufacturerImpl implements PodamTypeManufacturerForArray {

    /** The application logger */
    private static final Logger LOG = LogManager.getLogger(PodamArrayTypeManufacturerImpl.class);


    @Override
    public Object getType(TypeManufacturerParamsWrapperForArray wrapper) {
        return Array.newInstance(wrapper.getAttributeType(), 1);
    }
}
