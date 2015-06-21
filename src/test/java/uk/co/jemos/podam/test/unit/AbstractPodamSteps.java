package uk.co.jemos.podam.test.unit;

import net.thucydides.core.annotations.Steps;
import uk.co.jemos.podam.test.unit.steps.*;

/**
 * Created by tedonema on 31/05/2015.
 */
public abstract class AbstractPodamSteps {

    @Steps
    protected PodamFactorySteps podamFactorySteps;

    @Steps
    protected PodamInvocationSteps podamInvocationSteps;

    @Steps
    protected PodamValidationSteps podamValidationSteps;

    @Steps
    protected OneDimentionalPojoValidationSteps oneDimentionalPojoValidationSteps;

    @Steps
    protected PodamStrategySteps podamStrategySteps;

    @Steps
    protected RecursivePojoValidationSteps recursivePojoValidationSteps;

    @Steps
    protected ConstructorSelfReferenceValidationSteps constructorSelfReferenceValidationSteps;

    @Steps
    protected ClassInfoValidationSteps classInfoValidationSteps;

    @Steps
    protected ValidatorSteps validatorSteps;

    @Steps
    protected WalkThroughSteps walkThroughSteps;

}
