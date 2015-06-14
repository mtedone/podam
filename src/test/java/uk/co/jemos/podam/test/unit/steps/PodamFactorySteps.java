package uk.co.jemos.podam.test.unit.steps;

import net.thucydides.core.annotations.Step;
import uk.co.jemos.podam.api.*;
import uk.co.jemos.podam.test.dto.annotations.PojoSpecific;

import java.lang.annotation.Annotation;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by tedonema on 27/05/2015.
 */
public class PodamFactorySteps {

    @Step("Given a standard Podam Factory")
    public PodamFactory givenAStandardPodamFactory() {
        return new PodamFactoryImpl();
    }

    @Step("Given a Podam Factory to use as External Factory")
    public PodamFactory givenAPodamExternalFactorytoTestAttributeMetadata() {

        PodamFactory factory = new PodamFactoryImpl(new AbstractRandomDataProviderStrategy() {
            @Override
            public String getStringValue(AttributeMetadata attributeMetadata) {

                if (attributeMetadata.getPojoClass() == PojoSpecific.class) {
                    return "specific";
                } else {
                    return "classic";
                }
            }
        });

        return factory;
    }

    @Step("Given a ClassInfo for class {0} with empty attributes")
    public ClassInfo givenAClassInfoForPojoWithNoAttributes(Class<?> pojoClass, List<ClassAttribute> attributes) {
        return new ClassInfo(pojoClass, attributes);
    }

    @Step("Given a Set of annotations to be excluded")
    public Set<Class<? extends Annotation>> givenASetOfExcludedAnnotationsToBeExcluded(Class<? extends Annotation>... excludedAnnotations) {
        Set<Class<? extends Annotation>> retValue = new HashSet<Class<? extends Annotation>>();
        for (int i = 0; i < excludedAnnotations.length; i++) {
            retValue.add(excludedAnnotations[i]);
        }
        return retValue;
    }

    @Step("Given a ClassInfo with excluded annotations and excluded fields")
    public ClassInfo givenAClassInfoForPojoWithWithExcludedAnnotationsAndFields(
            Class<?> pojoClass, Set<Class<? extends Annotation>> excludeAnnotations, Set<String> excludeFields) {

        ClassAttributeApprover nullApprover = null;

        return PodamUtils.getClassInfo(pojoClass, excludeAnnotations, excludeFields, nullApprover);

    }

    @Step("Given a set of excluded fields")
    public Set<String> givenASetOfExcludedFields(String...excludedFields) {

        Set<String> retValue = new HashSet<String>();
        for (int i = 0; i < excludedFields.length; i++) {
            retValue.add(excludedFields[i]);
        }
        return retValue;
    }
}
