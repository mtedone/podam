package uk.co.jemos.podam.api;

import java.lang.reflect.Type;

/**
 * Adapter pattern for boilerplate code when creating an external factory
 *
 * @author Marco Tedone
 *
 * @since 5.2.1
 */
public abstract class AbstractExternalFactory implements PodamFactory {

    @Override
    public <T> T manufacturePojoWithFullData(Class<T> pojoClass,
                                             Type... genericTypeArgs) {
        return this.manufacturePojo(pojoClass, genericTypeArgs);
    }

    @Override
    public DataProviderStrategy getStrategy() {
        return null;
    }

    @Override
    public PodamFactory setStrategy(DataProviderStrategy strategy) {
        return this;
    }

    @Override
    public ClassInfoStrategy getClassStrategy() {
        return null;
    }

    @Override
    public PodamFactory setClassStrategy(
            ClassInfoStrategy classInfoStrategy) {
        return this;
    }

    @Override
    public PodamFactory getExternalFactory() {
        return null;
    }

    @Override
    public PodamFactory setExternalFactory(PodamFactory externalFactory) {
        return this;
    }

}
