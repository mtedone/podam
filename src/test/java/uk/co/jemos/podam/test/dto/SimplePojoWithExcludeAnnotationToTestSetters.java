/**
 * 
 */
package uk.co.jemos.podam.test.dto;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


/**
 * @author mtedone
 * 
 */
public class SimplePojoWithExcludeAnnotationToTestSetters extends SimplePojoToTestSetters {

	private static final long serialVersionUID = 1L;

	@TestExclude
	private Object excludeField1;
	
	private Object excludeField2;

	private Boolean excludeField3;

	/**
	 * No args-constructor
	 */
	public SimplePojoWithExcludeAnnotationToTestSetters() {
	}

	/**
	 * @return the excludeField1
	 */
	public Object getExcludeField1() {
		return excludeField1;
	}

	/**
	 * @param excludeField1 the excludeField1 to set
	 */
	public void setExcludeField1(Object excludeField1) {
		this.excludeField1 = excludeField1;
	}

	/**
	 * @return the excludeField2
	 */
	@TestExclude
	public Object getExcludeField2() {
		return excludeField2;
	}

	/**
	 * @param excludeField2 the excludeField2 to set
	 */
	public void setExcludeField2(Object excludeField2) {
		this.excludeField2 = excludeField2;
	}

	/**
	 * @return the excludeField3
	 */
	@TestExclude
	public Boolean isExcludeField3() {
		return excludeField3;
	}

	/**
	 * @param excludeField3 the excludeField3 to set
	 */
	public void setExcludeField3(Boolean excludeField3) {
		this.excludeField3 = excludeField3;
	}

	@Target(value = {ElementType.FIELD, ElementType.METHOD})
	@Retention(RetentionPolicy.RUNTIME)
	public @interface TestExclude {
		
	}
}
