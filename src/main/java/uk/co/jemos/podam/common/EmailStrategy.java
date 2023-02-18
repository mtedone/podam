/**
 * 
 */
package uk.co.jemos.podam.common;

import uk.co.jemos.podam.api.PodamUtils;
import uk.co.jemos.podam.exceptions.PodamMockeryException;

import java.lang.annotation.Annotation;
import java.util.List;

/**
 * This strategy fills Email attributes and parameters annotated with Java bean
 * validation annotations
 *
 * @author daivanov
 */
public class EmailStrategy implements AttributeStrategy<String> {

    /**
     * Constructor for the strategy
     */
    public EmailStrategy() {
    }

    /**
     * It returns objects complying with Java bean Email validation
     * annotation.
     * 
     * {@inheritDoc}
     */
    public String getValue(Class<?> attrType, List<Annotation> annotations) throws PodamMockeryException {

        StringBuilder sb = new StringBuilder();
        sb.append(PodamUtils.getNiceString(3)).append(".").append(PodamUtils.getNiceString(4));
        sb.append("@");
        sb.append(PodamUtils.getNiceString(4)).append(".").append(PodamUtils.getNiceString(3));
        return sb.toString();
    }

}
