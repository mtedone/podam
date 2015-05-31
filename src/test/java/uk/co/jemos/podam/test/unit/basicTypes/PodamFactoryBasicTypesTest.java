package uk.co.jemos.podam.test.unit.basicTypes;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;
import org.junit.Test;
import org.junit.runner.RunWith;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.test.dto.*;
import uk.co.jemos.podam.test.dto.pdm6.Child;
import uk.co.jemos.podam.test.dto.pdm6.Parent;
import uk.co.jemos.podam.test.dto.pdm6.RecursiveList;
import uk.co.jemos.podam.test.dto.pdm6.RecursiveMap;
import uk.co.jemos.podam.test.unit.steps.*;

import javax.activation.DataHandler;
import java.net.URL;

/**
 * Created by tedonema on 27/05/2015.
 */
@RunWith(SerenityRunner.class)
public class PodamFactoryBasicTypesTest {

    @Steps
    PodamFactorySteps podamFactorySteps;

    @Steps
    PodamInvocationSteps podamInvocationSteps;

    @Steps
    PodamValidationSteps podamValidationSteps;

    @Steps
    OneDimentionalPojoValidationSteps oneDimentionalPojoValidationSteps;

    @Steps
    PodamStrategySteps podamStrategySteps;

    @Steps
    RecursivePojoValidationSteps recursivePojoValidationSteps;

    @Test
    @Title("Podam should fill in a POJO with basic jvm types")
    public void podamShouldGenerateBasicTypes() throws Exception {

        PodamFactory podamFactory = podamFactorySteps.givenAStandardPodamFactory();

        OneDimensionalTestPojo oneDimensionalTestPojo = podamInvocationSteps.whenIInvokeTheFactoryForClass(OneDimensionalTestPojo.class, podamFactory);

        podamValidationSteps.thePojoShouldNotBeNull(oneDimensionalTestPojo);

        podamValidationSteps.thePojoShouldContainSomeData(oneDimensionalTestPojo);

        oneDimentionalPojoValidationSteps.validateDimensionalTestPojo(oneDimensionalTestPojo, podamFactory.getStrategy());

    }

    @Test
    @Title("Podam should fill POJOs with non default constructors")
    public void podamShouldFillPojosWithNonDefaultConstructor() throws Exception {

        PodamFactory podamFactory = podamFactorySteps.givenAStandardPodamFactory();

        NoDefaultConstructorPojo pojo = podamInvocationSteps.whenIInvokeTheFactoryForClass(NoDefaultConstructorPojo.class, podamFactory);

        podamValidationSteps.thePojoShouldNotBeNull(pojo);

    }

    @Test
    @Title("Invoking Podam on an Abstract class should return a null pojo")
    public void invokingPodamOnAbstractClassShouldReturnANullPojo() throws Exception {

        PodamFactory podamFactory = podamFactorySteps.givenAStandardPodamFactory();
        AbstractTestPojo pojo = podamInvocationSteps.whenIInvokeTheFactoryForClass(AbstractTestPojo.class, podamFactory);
        podamValidationSteps.thePojoShouldBeNull(pojo);

    }

    @Test
    @Title("Podam should generate a non null POJO for Abstract types with a concrete type")
    public void podamShouldGenerateANonNullPojoForAbstractTypesWithConcreteImplementation() throws Exception {

        PodamFactory podamFactory = podamFactorySteps.givenAStandardPodamFactory();
        podamStrategySteps.addOrReplaceSpecific(podamFactory, AbstractTestPojo.class, ConcreteTestPojo.class);
        AbstractTestPojo pojo = podamInvocationSteps.whenIInvokeTheFactoryForClass(AbstractTestPojo.class, podamFactory);
        podamValidationSteps.thePojoShouldNotBeNull(pojo);

    }

    @Test
    @Title("Podam should fill embedded abstract classes if a concrete type has been specified")
    public void podamShouldFillEmbeddedAbstractClassesIfAConcreteTypeHasBeenSpecified() throws Exception {

        PodamFactory podamFactory = podamFactorySteps.givenAStandardPodamFactory();
        podamStrategySteps.addOrReplaceSpecific(podamFactory, AbstractTestPojo.class, ConcreteTestPojo.class);
        EmbeddedAbstractFieldTestPojo pojo = podamInvocationSteps.whenIInvokeTheFactoryForClass(EmbeddedAbstractFieldTestPojo.class, podamFactory);
        podamValidationSteps.thePojoShouldNotBeNull(pojo);
        podamValidationSteps.theInnerPojoInstanceShouldNotBeNull(pojo.getPojo());

    }

    @Test
    @Title("Invoking Podam on an interface should return an empty POJO")
    public void invokingPodamOnAnInterfaceShouldReturnAnEmptyPojo() throws Exception {

        PodamFactory podamFactory = podamFactorySteps.givenAStandardPodamFactory();
        InterfacePojo pojo = podamInvocationSteps.whenIInvokeTheFactoryForClass(InterfacePojo.class, podamFactory);
        podamValidationSteps.thePojoShouldBeNull(pojo);

    }

    @Test
    @Title("Invoking Podam on a POJO with a private, no arguments constructor, should return a non null POJO")
    public void invokingPodamOnaPojoWithPrivateNoArgumentsConstructorShouldReturnANonEmptyPojo() throws Exception {

        PodamFactory podamFactory = podamFactorySteps.givenAStandardPodamFactory();
        PrivateNoArgConstructorPojo pojo = podamInvocationSteps.whenIInvokeTheFactoryForClass(PrivateNoArgConstructorPojo.class, podamFactory);
        podamValidationSteps.thePojoShouldNotBeNull(pojo);
        podamValidationSteps.theIntFieldShouldNotBeZero(pojo.getIntField());

    }

    @Test
    @Title("Podam should fill recursive POJOs correctly, including all their fields")
    public void podamShouldFillRecursivePojos() throws Exception {

        PodamFactory podamFactory = podamFactorySteps.givenAStandardPodamFactory();
        RecursivePojo recursivePojo = podamInvocationSteps.whenIInvokeTheFactoryForClass(RecursivePojo.class, podamFactory);
        podamValidationSteps.thePojoShouldNotBeNull(recursivePojo);
        recursivePojoValidationSteps.allPojosInTheRecursiveStrategyShouldBeValid(recursivePojo);
    }

    @Test
    @Title("Podam should fill recursive POJOs when invoking the factory population directly")
    public void podamShouldFillRecursivePojosWhenInvokingPopulationDirectly() throws Exception {

        PodamFactory podamFactory = podamFactorySteps.givenAStandardPodamFactory();
        RecursivePojo pojo = new RecursivePojo();
        podamInvocationSteps.whenIInvokeThePojoPopulationDirectly(pojo, podamFactory);
        recursivePojoValidationSteps.allPojosInTheRecursiveStrategyShouldBeValid(pojo);

    }

    @Test
    @Title("Podam should fill in POJOs which have a circular dependency")
    public void podamShouldSupportCircularDependencies() throws Exception {

        PodamFactory podamFactory = podamFactorySteps.givenAStandardPodamFactory();
        Parent parent = podamInvocationSteps.whenIInvokeTheFactoryForClass(Parent.class, podamFactory);
        podamValidationSteps.thePojoShouldNotBeNull(parent);
        Child child = parent.getChild();
        podamValidationSteps.theChildPojoShouldNotBeNull(child);

    }

    @Test
    @Title("Podam should fill in lists of the containing class type")
    public void podamShouldSupportRecursiveLists() throws Exception {

        PodamFactory podamFactory = podamFactorySteps.givenAStandardPodamFactory();
        RecursiveList recursiveListPojo = podamInvocationSteps.whenIInvokeTheFactoryForClass(RecursiveList.class, podamFactory);
        podamValidationSteps.thePojoShouldNotBeNull(recursiveListPojo);
        recursivePojoValidationSteps.thePojoListShouldNotBeNull(recursiveListPojo.getList());
        recursivePojoValidationSteps.thePojoListShouldNotBeEmpty(recursiveListPojo.getList());
        podamValidationSteps.eachListElementShouldNotBeNull(recursiveListPojo.getList());
    }

    @Test
    @Title("Podam should fill in Maps of the containing class type")
    public void podamShouldSupportRecursiveMaps() throws Exception {

        PodamFactory podamFactory = podamFactorySteps.givenAStandardPodamFactory();
        RecursiveMap recursiveMap = podamInvocationSteps.whenIInvokeTheFactoryForClass(RecursiveMap.class, podamFactory);
        podamValidationSteps.thePojoShouldNotBeNull(recursiveMap);
        recursivePojoValidationSteps.thePojoMapShouldNotBeNull(recursiveMap.getMap());
        recursivePojoValidationSteps.thePojoMapShouldNotBeEmpty(recursiveMap.getMap());
        podamValidationSteps.eachMapElementShouldNotBeNull(recursiveMap.getMap());

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
        podamValidationSteps.thePojoShouldNotBeNull(pojo);
    }

    @Test
    @Title("Podam should handle immutable POJOs annotated with @PodamConstructor")
    public void podamShouldHandleImmutablePojosAnnotatedWithPodamConstructor() throws Exception {

        PodamFactory podamFactory = podamFactorySteps.givenAStandardPodamFactory();
        ImmutableNoHierarchicalAnnotatedPojo pojo =
                podamInvocationSteps.whenIInvokeTheFactoryForClass(ImmutableNoHierarchicalAnnotatedPojo.class, podamFactory);
        podamValidationSteps.thePojoShouldNotBeNull(pojo);
        podamValidationSteps.theIntFieldShouldNotBeZero(pojo.getIntField());
        podamValidationSteps.theCalendarFieldShouldNotBeNull(pojo.getDateCreated());
        podamValidationSteps.theDateObjectShouldNotBeNull(pojo.getDateCreated().getTime());
        podamValidationSteps.theLongArrayShouldNotBeNullOrEmpty(pojo.getLongArray());
        podamValidationSteps.theLongValueShouldNotBeZero(pojo.getLongArray()[0]);
    }


}
