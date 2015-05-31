package uk.co.jemos.podam.test.unit.features.edgeCases;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Title;
import org.junit.Test;
import org.junit.runner.RunWith;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.test.dto.*;
import uk.co.jemos.podam.test.unit.AbstractPodamSteps;

import javax.activation.DataHandler;
import java.net.URL;

/**
 * Created by tedonema on 31/05/2015.
 */
@RunWith(SerenityRunner.class)
public class AbnormalPojosTest extends AbstractPodamSteps {

    @Test
    @Title("Podam should generate a non null POJO for Abstract types with a concrete type")
    public void podamShouldGenerateANonNullPojoForAbstractTypesWithConcreteImplementation() throws Exception {

        PodamFactory podamFactory = podamFactorySteps.givenAStandardPodamFactory();
        podamStrategySteps.addOrReplaceSpecific(podamFactory, AbstractTestPojo.class, ConcreteTestPojo.class);
        AbstractTestPojo pojo = podamInvocationSteps.whenIInvokeTheFactoryForClass(AbstractTestPojo.class, podamFactory);
        podamValidationSteps.theObjectShouldNotBeNull(pojo);

    }

    @Test
    @Title("Podam should fill embedded abstract classes if a concrete type has been specified")
    public void podamShouldFillEmbeddedAbstractClassesIfAConcreteTypeHasBeenSpecified() throws Exception {

        PodamFactory podamFactory = podamFactorySteps.givenAStandardPodamFactory();
        podamStrategySteps.addOrReplaceSpecific(podamFactory, AbstractTestPojo.class, ConcreteTestPojo.class);
        EmbeddedAbstractFieldTestPojo pojo = podamInvocationSteps.whenIInvokeTheFactoryForClass(EmbeddedAbstractFieldTestPojo.class, podamFactory);
        podamValidationSteps.theObjectShouldNotBeNull(pojo);
        podamValidationSteps.theInnerPojoInstanceShouldNotBeNull(pojo.getPojo());

    }

    @Test
    @Title("Invoking Podam on a POJO with a private, no arguments constructor, should return a non null POJO")
    public void invokingPodamOnaPojoWithPrivateNoArgumentsConstructorShouldReturnANonEmptyPojo() throws Exception {

        PodamFactory podamFactory = podamFactorySteps.givenAStandardPodamFactory();
        PrivateNoArgConstructorPojo pojo = podamInvocationSteps.whenIInvokeTheFactoryForClass(PrivateNoArgConstructorPojo.class, podamFactory);
        podamValidationSteps.theObjectShouldNotBeNull(pojo);
        podamValidationSteps.theIntFieldShouldNotBeZero(pojo.getIntField());

    }

    @Test
    @Title("Invoking Podam on a POJO with a circular constructor (e.g. java.net.URL) leads to a null POJO")
    public void podamCannotHandleCircularConstructors() throws Exception {

        PodamFactory podamFactory = podamFactorySteps.givenAStandardPodamFactory();
        URL pojo = podamInvocationSteps.whenIInvokeTheFactoryForClass(URL.class, podamFactory);
        podamValidationSteps.thePojoShouldBeNull(pojo);

    }

    @Test
    @Title("Invoking Podam on a POJO with both circular and non circular constructors (e.g. javax.activation.DataHandler) should lead to a non empty POJO" )
    public void podamShouldHandlePojosWithAMixOfCircularAndNonCircularConstructors() throws Exception {

        PodamFactory podamFactory = podamFactorySteps.givenAStandardPodamFactory();
        DataHandler pojo = podamInvocationSteps.whenIInvokeTheFactoryForClass(DataHandler.class, podamFactory);
        podamValidationSteps.theObjectShouldNotBeNull(pojo);
    }

    @Test
    @Title("Podam should handle POJOs with self reference constructors but not default constructors " +
            "without the need for the @PodamConstructor annotation")
    public void podamShouldHandlePojosWithSelfReferenceConstructorsButNotDefaultConstructors() throws Exception {

        PodamFactory podamFactory = podamFactorySteps.givenAStandardPodamFactory();
        ConstructorWithSelfReferencesButNoDefaultConstructorPojo pojo =
                podamInvocationSteps.whenIInvokeTheFactoryForClass(ConstructorWithSelfReferencesButNoDefaultConstructorPojo.class, podamFactory);
        podamValidationSteps.theObjectShouldNotBeNull(pojo);
        constructorSelfReferenceValidationSteps.theFirstSelfReferenceForPojoWithoutDefaultConstructorShouldNotBeNull(pojo);
        constructorSelfReferenceValidationSteps.theSecondSelfReferenceForPojoWithoutDefaultConstructorShouldNotBeNull(pojo);

    }
}
