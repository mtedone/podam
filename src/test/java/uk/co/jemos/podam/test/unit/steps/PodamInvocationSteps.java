package uk.co.jemos.podam.test.unit.steps;

import net.thucydides.core.annotations.Step;
import uk.co.jemos.podam.api.ClassAttributeApprover;
import uk.co.jemos.podam.api.ClassInfo;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamUtils;

/**
 * Created by tedonema on 27/05/2015.
 */
public class PodamInvocationSteps {

    @Step("When I invoke the factory manufacturing for {0}")
    public <T> T whenIInvokeTheFactoryForClass(Class<T> className, PodamFactory podamFactory) throws Exception {
        return podamFactory.manufacturePojo(className);
    }

    @Step("When I invoke the pojo's population directly for {0}")
    public <T> T whenIInvokeThePojoPopulationDirectly(T className, PodamFactory podamFactory) throws Exception {
        return podamFactory.populatePojo(className);
    }

    @Step("When I invoke the factory to manufacture {0} with the fullest constructor")
    public <T> T whenIInvokeTheFactoryForClassWithFullConstructor(Class<T> className, PodamFactory podamFactory)
    throws Exception {
        return podamFactory.manufacturePojoWithFullData(className);
    }

    @Step("When I invoke Podam Utils method to get class info for class {0} and approver {1}")
    public ClassInfo getClassInfo(Class<?> pojoClass, ClassAttributeApprover approver) {
        return PodamUtils.getClassInfo(pojoClass, approver);
    }
}
