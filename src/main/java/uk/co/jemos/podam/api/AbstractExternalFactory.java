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


    /** Empty list of types */
    protected static final Type[] NO_TYPES = new Type[0];

    @Override
    public <T> T manufacturePojo(Class<T> pojoClass) {
        return this.manufacturePojo(pojoClass, NO_TYPES);
    }

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
    public ClassInfoStrategy getClassStrategy() {
        return null;
    }

    @Override
    public PodamFactory setClassStrategy(
            ClassInfoStrategy classInfoStrategy) {
        return null;
    }

}
