/**
 * 
 */
package uk.co.jemos.podam.test.strategies;

import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.List;

import uk.co.jemos.podam.api.PodamUtils;
import uk.co.jemos.podam.common.AttributeStrategy;

/**
 * @author daivanov
 * Class for handling all annotations at once
 */
public class AnnotationStrategy implements AttributeStrategy<Object> {

    private final List<List<Annotation>> recordedCalls = new ArrayList<List<Annotation>>();

    @Override
    public Object getValue(Class<?> attrType, List<Annotation> annotations) {
        recordedCalls.add(annotations);
        return null;
    }

    public List<List<Annotation>> getRecordedCalls() {
        return recordedCalls;
    }

}
