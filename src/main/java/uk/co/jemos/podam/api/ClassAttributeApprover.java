/**
 * 
 */
package uk.co.jemos.podam.api;

/**
 * An interface, which is used to customize selection of class' attributes
 * for further filling or skipping.
 * 
 * @author daivanov
 * 
 */
public interface ClassAttributeApprover {

	/**
	 * Override this method to select or reject class attributes
	 *
	 * @param attribute
	 *        class attribute to analyze for further processing or skipping
	 * @return
	 *        true, if attribute should be kept, false, if it should be skipped
	 */
	boolean approve(ClassAttribute attribute);
}
