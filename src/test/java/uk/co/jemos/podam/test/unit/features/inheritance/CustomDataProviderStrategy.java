package uk.co.jemos.podam.test.unit.features.inheritance;

import uk.co.jemos.podam.api.AbstractRandomDataProviderStrategy;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tedonema on 20/06/2015.
 */
public class CustomDataProviderStrategy extends AbstractRandomDataProviderStrategy {
    private List<Class<?>> accessed = new ArrayList<Class<?>>();

    @Override
    public <T> Class<? extends T> getSpecificClass(
            Class<T> nonInstantiatableClass) {
        accessed.add(nonInstantiatableClass);
        return nonInstantiatableClass;
    }

    public List<Class<?>> getAccessed() {
        return accessed;
    }

}
