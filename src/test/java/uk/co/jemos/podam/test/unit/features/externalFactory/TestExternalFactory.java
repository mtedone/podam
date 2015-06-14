package uk.co.jemos.podam.test.unit.features.externalFactory;

import uk.co.jemos.podam.api.AbstractExternalFactory;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by tedonema on 14/06/2015.
 */
public class TestExternalFactory extends AbstractExternalFactory {

    private final List<Class<?>> failures = new ArrayList<Class<?>>();
    private final List<Class<?>> fullDataCalls = new ArrayList<Class<?>>();

    /**
     * Generic method which returns an instance of the given class filled with
     * dummy values, using the default data provider strategy.
     * <p>
     * <p>
     * This method uses RandomDataProviderStrategyImpl as the default
     * implementation.
     * </p>
     *
     * @param pojoClass       The name of the class for which an instance filled with values
     *                        is required
     * @param genericTypeArgs The generic Type arguments for a generic class instance
     * @return An instance of &lt;T&gt; filled with dummy values
     * @throws uk.co.jemos.podam.exceptions.PodamMockeryException if a problem occurred while creating a POJO instance or while
     *                               setting its state
     */
    @Override
    public <T> T manufacturePojo(Class<T> pojoClass, Type... genericTypeArgs) {
        failures.add(pojoClass);
        return null;
    }

    /**
     * Generic method which populates an instance of the given class with
     * dummy values, using the default data provider strategy.
     * <p>
     * <p>
     * This method uses RandomDataProviderStrategyImpl as the default
     * implementation.
     * </p>
     *
     * @param pojo            The instance to be filled with values
     * @param genericTypeArgs The generic Type arguments for a generic class instance
     * @return The same instance of object for chaining
     * @throws uk.co.jemos.podam.exceptions.PodamMockeryException if a problem occurred while creating a POJO instance or while
     *                               setting its state
     * @since 5.4.0
     */
    @Override
    public <T> T populatePojo(T pojo, Type... genericTypeArgs) {
        return pojo;
    }

    @Override
    public <T> T manufacturePojoWithFullData(Class<T> pojoClass, Type... genericTypeArgs) {
        fullDataCalls.add(pojoClass);
        return this.manufacturePojo(pojoClass, genericTypeArgs);
    }

    public List<Class<?>> getFullDataCalls() {
        return fullDataCalls;
    }

    public List<Class<?>> getFailures() {

        return failures;
    }
}
