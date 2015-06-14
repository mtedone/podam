package uk.co.jemos.podam.test.unit.features.classInfo;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Title;
import org.junit.Test;
import org.junit.runner.RunWith;
import uk.co.jemos.podam.api.ClassAttribute;
import uk.co.jemos.podam.api.ClassAttributeApprover;
import uk.co.jemos.podam.api.ClassInfo;
import uk.co.jemos.podam.test.dto.EmptyTestPojo;
import uk.co.jemos.podam.test.dto.SimplePojoToTestSetters;
import uk.co.jemos.podam.test.dto.SimplePojoWithExcludeAnnotationToTestSetters;
import uk.co.jemos.podam.test.unit.AbstractPodamSteps;

import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by tedonema on 14/06/2015.
 */
@RunWith(SerenityRunner.class)
public class ClassInfoTest extends AbstractPodamSteps {

    @Test
    @Title("Podam should return valid class info for POJOs with no attributes")
    public void podamShouldReturnAValidClassInfoForPojoWithNoAttributes() {

        List<ClassAttribute> attributes = new ArrayList<ClassAttribute>();
        ClassInfo expectedClassInfo = podamFactorySteps.givenAClassInfoForPojoWithNoAttributes(EmptyTestPojo.class, attributes);

        ClassAttributeApprover nullApprover = null;

        ClassInfo actualClassInfo = podamInvocationSteps.getClassInfo(EmptyTestPojo.class, nullApprover);
        podamValidationSteps.theTwoObjectsShouldBeEqual(expectedClassInfo, actualClassInfo);

    }

    @Test
    @Title("Podam should generate a ClassInfo which contains information for the setters contained in the POJO")
    public void podamShouldReturnAClassInfoObjectWhichContainsTheSameAttributesAsThePojoBeingProcessed() {

        ClassAttributeApprover nullApprover = null;

        ClassInfo actualClassInfo = podamInvocationSteps.getClassInfo(SimplePojoToTestSetters.class, nullApprover);
        podamValidationSteps.theObjectShouldNotBeNull(actualClassInfo);
        podamValidationSteps.theTwoObjectsShouldBeEqual(SimplePojoToTestSetters.class, actualClassInfo.getClassName());
        Set<String> attribs = new HashSet<String>();
        attribs.add("stringField");
        attribs.add("intField");
        classInfoValidationSteps.theClassInfoAttributesShouldMatchthePojoOnes(attribs, actualClassInfo.getClassAttributes());
    }

    @Test
    @Title("The ClassInfo object created for a class should handle @PodamExcludeAnnotations")
    public void inPresenceOfExcludeAnnotationsTheClassInfoObjectShouldContainAValidSetOfPojoAttributes() {

        Set<Class<? extends Annotation>> excludeAnnotations =
                podamFactorySteps.givenASetOfExcludedAnnotationsToBeExcluded(SimplePojoWithExcludeAnnotationToTestSetters.TestExclude.class);

        Set<String> excludeFields = new HashSet<String>();

        ClassInfo classInfo = podamFactorySteps.givenAClassInfoForPojoWithWithExcludedAnnotationsAndFields(
                SimplePojoWithExcludeAnnotationToTestSetters.class,
                excludeAnnotations, excludeFields);

        podamValidationSteps.theObjectShouldNotBeNull(classInfo);
        Set<String> attribs = new HashSet<String>();
        attribs.add("stringField");
        attribs.add("intField");
        classInfoValidationSteps.theClassInfoAttributesShouldMatchthePojoOnes(attribs, classInfo.getClassAttributes());

    }

    @Test
    @Title("In Presence of excluded attributes, the Class Info object should not contain those attributes")
    public void inPresenceOfExcludedAttributesTheClassInfoObjectShouldNotContainThoseAttributes() {

        Set<Class<? extends Annotation>> excludeAnnotations =
                podamFactorySteps.givenASetOfExcludedAnnotationsToBeExcluded();

        Set<String> excludeFields = podamFactorySteps.givenASetOfExcludedFields(
                "excludeField1",
                "excludeField2",
                "excludeField3");

        ClassInfo classInfo = podamFactorySteps.givenAClassInfoForPojoWithWithExcludedAnnotationsAndFields(
                SimplePojoWithExcludeAnnotationToTestSetters.class,
                excludeAnnotations, excludeFields);

        podamValidationSteps.theObjectShouldNotBeNull(classInfo);
        Set<String> attribs = new HashSet<String>();
        attribs.add("stringField");
        attribs.add("intField");
        classInfoValidationSteps.theClassInfoAttributesShouldMatchthePojoOnes(attribs, classInfo.getClassAttributes());

    }



}
