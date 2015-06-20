package uk.co.jemos.podam.test.unit.features.inheritance;

import uk.co.jemos.podam.api.AbstractExternalFactory;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by tedonema on 20/06/2015.
 */
public class TrackingExternalFactory extends AbstractExternalFactory {

    List<Class<?>> failures = new ArrayList<Class<?>>();

    @Override
    public <T> T manufacturePojo(Class<T> pojoClass, Type... genericTypeArgs) {
        failures.add(pojoClass);
        return null;
    }

    @Override
    public <T> T populatePojo(T pojo, Type... genericTypeArgs) {
        return pojo;
    }

}
