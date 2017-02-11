package uk.co.jemos.podam.test.unit.features.constructors;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Title;

import org.junit.Test;
import org.junit.runner.RunWith;

import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.test.dto.*;
import uk.co.jemos.podam.test.dto.InnerClassPojo.InnerPojo;
import uk.co.jemos.podam.test.dto.issue123.GenericCollectionsConstructorPojo;
import uk.co.jemos.podam.test.unit.AbstractPodamSteps;

import javax.xml.bind.JAXBElement;
import javax.xml.namespace.QName;

import java.util.Date;
import java.util.Observable;
import java.util.TimeZone;

/**
 * @author daivanov
 *
 */
@RunWith(SerenityRunner.class)
public class ConstructorsUnitTest extends AbstractPodamSteps {



	@Test
	@Title("Podam should handle generics in the constructor")
	public void podamShouldHandleGenericsInConstructor() throws Exception {
		PodamFactory podamFactory = podamFactorySteps.givenAStandardPodamFactory();
		GenericInConstructorPojo pojo
				= podamInvocationSteps.whenIInvokeTheFactoryForClass(GenericInConstructorPojo.class, podamFactory);
		podamValidationSteps.thePojoMustBeOfTheType(pojo, GenericInConstructorPojo.class);
		podamValidationSteps.theCollectionShouldNotBeNullOrEmptyAndContainElementsOfType(pojo.getVector(), String.class);
	}
	@Test
	@Title("Podam should handle generics in setters during Pojo instantiation")
	public void podamShouldHandleGenericsInSettersDuringPojoInstantiation() throws Exception {
		PodamFactory podamFactory = podamFactorySteps.givenAStandardPodamFactory();
		GenericInSetterPojo pojo
				= podamInvocationSteps.whenIInvokeTheFactoryForClass(GenericInSetterPojo.class, podamFactory);
		podamValidationSteps.thePojoMustBeOfTheType(pojo, GenericInSetterPojo.class);
		podamValidationSteps.theCollectionShouldNotBeNullOrEmptyAndContainElementsOfType(pojo.getVector(), String.class);
	}

	@Test
	@Title("Podam should handle generics in static constructors during POJO instantiation")
	public void podamShouldHandleGenericsInStaticConstructorsDuringPojoInstantiation() throws Exception {
		PodamFactory podamFactory = podamFactorySteps.givenAStandardPodamFactory();
		GenericInStaticConstructorPojo pojo
				= podamInvocationSteps.whenIInvokeTheFactoryForClass(GenericInStaticConstructorPojo.class, podamFactory);
		podamValidationSteps.thePojoMustBeOfTheType(pojo, GenericInStaticConstructorPojo.class);
		podamValidationSteps.theCollectionShouldNotBeNullOrEmptyAndContainElementsOfType(pojo.getVector(), String.class);
	}

	@Test
	@Title("Podam should handle constructors with generic arrays during Pojo instantiation")
	public void podamShouldHandleConstructorsWithGenericArraysDuringPojoInstantiation() {
		PodamFactory podamFactory = podamFactorySteps.givenAStandardPodamFactory();
		GenericArrayInConstructorPojo<?> pojo
				= podamInvocationSteps.whenIInvokeTheFactoryForGenericTypeWithSpecificType(
                GenericArrayInConstructorPojo.class, podamFactory, String.class);
		podamValidationSteps.thePojoMustBeOfTheType(pojo, GenericArrayInConstructorPojo.class);
		podamValidationSteps.theArrayOfTheGivenTypeShouldNotBeNullOrEmptyAndContainElementsOfTheRightType(
                pojo.getArray(), String.class);
	}

	@Test
	@Title("Podam should handle constructors with multiple generics during Pojo instantiation")
	public void podamShouldHandleConstructorsWithMultipleGenericsDuringPojoInstantiation() {
		PodamFactory podamFactory = podamFactorySteps.givenAStandardPodamFactory();
		MultipleGenericInConstructorPojo<?, ?, ?, ?> pojo
				= podamInvocationSteps.whenIInvokeTheFactoryForGenericTypeWithSpecificType(MultipleGenericInConstructorPojo.class,
				podamFactory, String.class, Character.class, Byte.class, Integer.class);
		podamValidationSteps.thePojoMustBeOfTheType(pojo, MultipleGenericInConstructorPojo.class);
		podamValidationSteps.theTwoObjectsShouldBeEqual(String.class, pojo.getType());
		podamValidationSteps.theCollectionShouldNotBeNullOrEmptyAndContainElementsOfType(pojo.getList(), Character.class);
		podamValidationSteps.theMapShouldNotBeNullOrEmptyAndContainElementsOfType(
                pojo.getMap(), Byte.class, Integer.class);
	}

	@Test
	@Title("Podam should handle classes with generic Key/Value types")
	public void podamShouldHandleClassesWithKeyValueGenericTypes() throws Exception {

		PodamFactory podamFactory = podamFactorySteps.givenAStandardPodamFactory();

		DefaultFieldPojo<?,?> pojo = podamInvocationSteps.whenIInvokeTheFactoryForGenericTypeWithSpecificType(
				DefaultFieldPojo.class, podamFactory, String.class, Long.class);
		podamValidationSteps.thePojoMustBeOfTheType(pojo, DefaultFieldPojo.class);
		podamValidationSteps.theMapShouldNotBeNullOrEmptyAndContainElementsOfType(
				pojo.getMap(), String.class, Long.class);
	}


	@Test
	@Title("Podam should be able to manufacture instances of the Observable class")
	public void podamShouldBeAbleToManufactureInstancesOfTheObservableClass() throws Exception {
		PodamFactory podamFactory = podamFactorySteps.givenAStandardPodamFactory();
		Observable observable = podamInvocationSteps.whenIInvokeTheFactoryForClass(Observable.class, podamFactory);
		podamValidationSteps.thePojoMustBeOfTheType(observable, Observable.class);
	}

	@Test
	@Title("Podam should be able to manufacture POJOs which contain immutable collections")
	public void podamShouldBeAbleToManufacturePojosWhichContainImmutableCollections() throws Exception {
		PodamFactory podamFactory = podamFactorySteps.givenAStandardPodamFactory();
		ImmutableDefaultFieldsPojo model =
				podamInvocationSteps.whenIInvokeTheFactoryForClass(ImmutableDefaultFieldsPojo.class, podamFactory);
		podamValidationSteps.thePojoMustBeOfTheType(model, ImmutableDefaultFieldsPojo.class);
		podamValidationSteps.theCollectionShouldNotBeNullOrEmptyAndShouldHaveExactlyTheExpectedNumberOfElements(
				model.getList(), String.class, podamFactory.getStrategy().getNumberOfCollectionElements(model.getList().getClass()));
		podamValidationSteps.theMapShouldNotBeNullOrEmptyAndShouldHaveExactlyTheExpectedNumberOfElements(
				model.getMap(), String.class, Integer.class, podamFactory.getStrategy().getNumberOfCollectionElements(model.getMap().getClass()));
	}

	@Test
	@Title("Podam should be able to manufacture any type of Lists")
	public void podamShouldBeAbleToManufactureAnyTypeOfCollections() throws Exception {
		PodamFactory podamFactory = podamFactorySteps.givenAStandardPodamFactory();
		UnsupportedCollectionInConstructorPojo<?> pojo =
				podamInvocationSteps.whenIInvokeTheFactoryForGenericTypeWithSpecificType(
                        UnsupportedCollectionInConstructorPojo.class, podamFactory, String.class);
		podamValidationSteps.thePojoMustBeOfTheType(pojo, UnsupportedCollectionInConstructorPojo.class);
		podamValidationSteps.theCollectionShouldNotBeNullOrEmptyAndContainElementsOfType(pojo.getVector(), String.class);
	}

	@Test
	@Title("Podam should be able to manufacture any type of Maps")
	public void podamShouldBeAbleToManufactureAnyTypeOfMaps() throws Exception {

		PodamFactory podamFactory = podamFactorySteps.givenAStandardPodamFactory();

		UnsupportedMapInConstructorPojo<?,?> pojo =
				podamInvocationSteps.whenIInvokeTheFactoryForGenericTypeWithSpecificType(
						UnsupportedMapInConstructorPojo.class, podamFactory, String.class, Integer.class);

		podamValidationSteps.thePojoMustBeOfTheType(pojo, UnsupportedMapInConstructorPojo.class);
		podamValidationSteps.theMapShouldNotBeNullOrEmptyAndContainElementsOfType(
                pojo.getHashTable(), String.class, Integer.class);
	}

	@Test
	@Title("Podam should be able to instantiate POJOs with immutable Collections")
	public void podamShouldBeAbleToInstantiatePojosWithImmutableCollections() throws Exception {
		PodamFactory podamFactory = podamFactorySteps.givenAStandardPodamFactory();

		ImmutableVector<?> pojo = podamInvocationSteps.whenIInvokeTheFactoryForGenericTypeWithSpecificType(
				ImmutableVector.class, podamFactory, String.class);
		podamValidationSteps.theCollectionShouldBeEmpty(pojo);
	}

	@Test
	@Title("Podam should be able to instantiate POJOs with immutable Maps")
	public void podamShouldBeAbleToInstantiatePojosWithImmutableMaps() throws Exception {

		PodamFactory podamFactory = podamFactorySteps.givenAStandardPodamFactory();
		ImmutableHashtable<?,?> pojo = podamInvocationSteps.whenIInvokeTheFactoryForGenericTypeWithSpecificType(
				ImmutableHashtable.class, podamFactory, String.class, Integer.class);
		podamValidationSteps.thePojoMustBeOfTheType(pojo, ImmutableHashtable.class);
		podamValidationSteps.theMapShouldBeEmtpy(pojo);
	}

	@Test
	@Title("Podam should be able to create instances of abstract POJOs with factory methods which return concrete types")
	public void podamShouldInstantiateAbstractClassesForWhichItKnowsConcreteTypes() throws Exception {

		PodamFactory podamFactory = podamFactorySteps.givenAStandardPodamFactory();

		TimeZone pojo = podamInvocationSteps.whenIInvokeTheFactoryForClass(TimeZone.class, podamFactory);
		podamValidationSteps.thePojoMustBeOfTheType(pojo, TimeZone.class);
	}

	@Test
	@Title("Podam should be able to create instances of generic POJOs with factory methods when the concrete type is known")
	public void podamShouldCreateInstancesOfGenericPojosWithFactoryMethodsWhenTheConcreteTypeIsKnown() throws Exception {

		PodamFactory podamFactory = podamFactorySteps.givenAStandardPodamFactory();

		FactoryInstantiablePojo<?> pojo = podamInvocationSteps.whenIInvokeTheFactoryForGenericTypeWithSpecificType(
                FactoryInstantiablePojo.class, podamFactory, Date.class);

		podamValidationSteps.thePojoMustBeOfTheType(pojo, FactoryInstantiablePojo.class);
		podamValidationSteps.thePojoMustBeOfTheType(pojo.getTypedValue(), Date.class);
	}

	@Test
	@Title("Podam should be able to create instances of generic read only POJOs with factory methods when the concrete type is known")
	public void podamShouldCreateInstancesOfGenericReadOnlyPojosWithFactoryMethodsWhenTheConcreteTypeIsKnown() throws Exception {

		PodamFactory podamFactory = podamFactorySteps.givenAStandardPodamFactory();

		FactoryInstantiableReadOnlyPojo<?> pojo = podamInvocationSteps.whenIInvokeTheFactoryForGenericTypeWithSpecificType(
                FactoryInstantiableReadOnlyPojo.class, podamFactory, String.class);

		podamValidationSteps.thePojoMustBeOfTheType(pojo, FactoryInstantiableReadOnlyPojo.class);
		podamValidationSteps.thePojoMustBeOfTheType(pojo.getTypedValue(), String.class);
	}

	@Test
	@Title("Podam should choose the fullest constructor when invoked for full data")
	public void podamShouldChooseTheFullestConstructorWhenInvokedForFullData() throws Exception {

		PodamFactory podamFactory = podamFactorySteps.givenAStandardPodamFactory();

		ImmutablePojo pojo = podamInvocationSteps.whenIInvokeTheFactoryForClassWithFullConstructor(
                ImmutablePojo.class, podamFactory);
		podamValidationSteps.thePojoMustBeOfTheType(pojo, ImmutablePojo.class);
		podamValidationSteps.thePojoMustBeOfTheType(pojo.getValue(), String.class);
		podamValidationSteps.thePojoMustBeOfTheType(pojo.getValue2(), Integer.class);
	}

	@Test
	@Title("Podam should choose the lightest constructor when the standard manufacturing method is invoked")
	public void testImmutablePojoConstructionFailure() throws Exception {

		PodamFactory podamFactory = podamFactorySteps.givenAStandardPodamFactory();

		ImmutablePojo pojo = podamInvocationSteps.whenIInvokeTheFactoryForClass(ImmutablePojo.class, podamFactory);
		podamValidationSteps.thePojoMustBeOfTheType(pojo, ImmutablePojo.class);
		podamValidationSteps.theObjectShouldBeNull(pojo.getValue());
		podamValidationSteps.theObjectShouldBeNull(pojo.getValue2());
	}

	@Test
	@Title("Podam should be able to create instances of inner classes")
	public void podamShouldCreateInstancesOfInnerClasses() throws Exception {

		PodamFactory podamFactory = podamFactorySteps.givenAStandardPodamFactory();

		InnerClassPojo pojo = podamInvocationSteps.whenIInvokeTheFactoryForClass(InnerClassPojo.class, podamFactory);
		podamValidationSteps.thePojoMustBeOfTheType(pojo, InnerClassPojo.class);
		podamValidationSteps.thePojoMustBeOfTheType(pojo.getIp(), InnerPojo.class);
	}


	@Test
	@Title("Podam should be able to create instances of JAXBElement")
	public void podamShouldCreateInstancesOfJAXBElements() throws Exception {

		PodamFactory podamFactory = podamFactorySteps.givenAStandardPodamFactory();

		JAXBElement<?> pojo = podamInvocationSteps.whenIInvokeTheFactoryForGenericTypeWithSpecificType(
                JAXBElement.class, podamFactory, String.class);
		podamValidationSteps.thePojoMustBeOfTheType(pojo, JAXBElement.class);
		podamValidationSteps.thePojoMustBeOfTheType(pojo.getName(), QName.class);
		podamValidationSteps.thePojoMustBeOfTheType(pojo.getValue(), String.class);
	}

	@Test
	@Title("Podam should be able to create instances of JAXBElements declared as instance variables in a POJO")
	public void podamShouldCreateInstancesOfJAXBElementsDeclaredAsInstanceVariablesInAPojo() throws Exception {

		PodamFactory podamFactory = podamFactorySteps.givenAStandardPodamFactory();

		JAXBElementPojo<?> pojo = podamInvocationSteps.whenIInvokeTheFactoryForGenericTypeWithSpecificType(
				JAXBElementPojo.class, podamFactory, String.class);
		podamValidationSteps.thePojoMustBeOfTheType(pojo, JAXBElementPojo.class);
		podamValidationSteps.thePojoMustBeOfTheType(pojo.getValue(), JAXBElement.class);
		podamValidationSteps.thePojoMustBeOfTheType(pojo.getValue().getName(), QName.class);
		podamValidationSteps.thePojoMustBeOfTheType(pojo.getValue().getValue(), String.class);
	}

	@Test
	@Title("Podam should manufacture package private POJOs")
	public void podamShouldManufacturePackagePrivatePojos() throws Exception {

		PodamFactory podamFactory = podamFactorySteps.givenAStandardPodamFactory();

		PackagePrivatePojo pojo = podamInvocationSteps.whenIInvokeTheFactoryForClass(
                PackagePrivatePojo.class, podamFactory);
		podamValidationSteps.thePojoMustBeOfTheType(pojo, PackagePrivatePojo.class);
		podamValidationSteps.thePojoMustBeOfTheType(pojo.getValue(), String.class);

	}


	@Test
	@Title("Podam should create instances of POJOs extending generic classes")
	public void podamShouldCreateInstancesOfPojosExtendingGenericClasses() throws Exception {

		PodamFactory podamFactory = podamFactorySteps.givenAStandardPodamFactory();

		TypedClassPojo2 pojo = podamInvocationSteps.whenIInvokeTheFactoryForClass(TypedClassPojo2.class, podamFactory);
		podamValidationSteps.thePojoMustBeOfTheType(pojo, TypedClassPojo2.class);
		podamValidationSteps.thePojoMustBeOfTheType(pojo.getTypedValue(), String.class);
		podamValidationSteps.theCollectionShouldNotBeNullOrEmptyAndContainElementsOfType(pojo.getTypedList(), String.class);
	}

    @Test
    @Title("Podam should correctly handle generic collections in constructor with memoization disabled")
    public void podamShouldHandleGenericCollectionsInConstructorWithMemoizationDisabled() throws Exception {

        PodamFactory podamFactory = podamFactorySteps.givenAStandardPodamFactory();

        GenericCollectionsConstructorPojo pojo = podamInvocationSteps.whenIInvokeTheFactoryForClass
                (GenericCollectionsConstructorPojo.class, podamFactory);

        podamValidationSteps.thePojoMustBeOfTheType(pojo, GenericCollectionsConstructorPojo.class);
        podamValidationSteps.theCollectionShouldNotBeNullOrEmptyAndContainElementsOfType(pojo.getList1(), Long.class);
        podamValidationSteps.theCollectionShouldNotBeNullOrEmptyAndContainElementsOfType(pojo.getList2(), String.class);
        podamValidationSteps.theCollectionShouldNotBeNullOrEmptyAndContainElementsOfType(pojo.getList3(), Integer
                .class);

    }

    @Test
    @Title("Podam should correctly handle generic collections in constructor with memoization enabled")
    public void podamShouldHandleGenericCollectionsInConstructorWithMemoizationEnabled() throws Exception {

        PodamFactory podamFactory = podamFactorySteps.givenAStandardPodamFactoryWithMemoizationEnabled();

        GenericCollectionsConstructorPojo pojo = podamInvocationSteps.whenIInvokeTheFactoryForClass
                (GenericCollectionsConstructorPojo.class, podamFactory);

        podamValidationSteps.thePojoMustBeOfTheType(pojo, GenericCollectionsConstructorPojo.class);
        podamValidationSteps.theCollectionShouldNotBeNullOrEmptyAndContainElementsOfType(pojo.getList1(), Long.class);
        podamValidationSteps.theCollectionShouldNotBeNullOrEmptyAndContainElementsOfType(pojo.getList2(), String.class);
        podamValidationSteps.theCollectionShouldNotBeNullOrEmptyAndContainElementsOfType(pojo.getList3(), Integer
                .class);

    }

}
