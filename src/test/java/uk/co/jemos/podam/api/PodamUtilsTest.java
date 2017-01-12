package uk.co.jemos.podam.api;

import net.thucydides.core.annotations.Title;
import org.hibernate.validator.constraints.Length;
import org.junit.Test;
import uk.co.jemos.podam.common.PodamAnnotation;

import java.lang.annotation.*;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class PodamUtilsTest {


    @Test
    @Title("If multiple annotations are present, all the non Podam one are removed")
    public void ifMultipleAnnotationsArePresentAllTheNonPodamOneAreRemoved() {
        Field[] declaredFields = TestPojo.class.getDeclaredFields();

        List<Annotation> annotations = PodamUtils.filterNonPodamAnnotations(Arrays.asList(declaredFields[0].getAnnotations()));
        assertThat(annotations.size(), is(1));
        assertThat(annotations.get(0).annotationType().getCanonicalName(), containsString("APodamAnnotation"));

    }


    //Fixture for testing
    @Target(value = { ElementType.FIELD, ElementType.PARAMETER })
    @Retention(RetentionPolicy.RUNTIME)
    @interface NonPodamAnnotation {
    }

    @Target(value = { ElementType.FIELD, ElementType.PARAMETER })
    @Retention(RetentionPolicy.RUNTIME)
    @interface AnotherNonPodamAnnotation {
    }

    @Target(value = { ElementType.FIELD, ElementType.PARAMETER })
    @Retention(RetentionPolicy.RUNTIME)
    @PodamAnnotation
    @interface APodamAnnotation {
    }


    private class TestPojo {
        @APodamAnnotation
        @NonPodamAnnotation
        @AnotherNonPodamAnnotation
        String attribute;
    }


}