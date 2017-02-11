/**
 * 
 */
package uk.co.jemos.podam.common;

import java.lang.annotation.Annotation;
import java.util.List;

/**
 * Generic contract for attribute-level data provider strategies.
 * 
 * @author mtedone
 * 
 */
public interface AttributeStrategy<T> {

	/**
	 * It returns a value of the given type
	 * 
	 * @param attrType
	 *            an attribute's type
	 * @param attrAnnotations
	 *            list of annotations attached to an attribute
	 * @return A value of the given type
	 */
	T getValue(Class<?> attrType, List<Annotation> attrAnnotations);

}
