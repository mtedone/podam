package uk.co.jemos.podam.test.unit.features.extensions;

import uk.co.jemos.podam.api.AbstractClassInfoStrategy;
import uk.co.jemos.podam.api.ClassAttribute;
import uk.co.jemos.podam.test.dto.NonEJBPojo;

import java.lang.reflect.Method;

/**
 * Created by tedonema on 20/06/2015.
 */
public class NonEJBClassInfoStrategy extends AbstractClassInfoStrategy {

    @Override
    public boolean approve(ClassAttribute attribute) {

			/* EJB attributes: field and setter */
        if (attribute.getAttribute() != null) {
            return true;
        }

			/* Exclusion for NonEJBPojo class */
        for (Method setter : attribute.getSetters()) {
            if (NonEJBPojo.class.equals(setter.getDeclaringClass())) {
                return true;
            }
        }
        return false;
    }
}
