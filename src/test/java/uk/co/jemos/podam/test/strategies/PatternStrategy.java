/**
 * 
 */
package uk.co.jemos.podam.test.strategies;

import java.lang.annotation.Annotation;

import javax.validation.constraints.Pattern;

import uk.co.jemos.podam.api.PodamUtils;
import uk.co.jemos.podam.common.AttributeStrategy;

/**
 * @author daivanov
 * 
 */
public class PatternStrategy implements AttributeStrategy<String> {

	/** validation annotation */
	private Pattern patternAnnotation;

	/**
	 * Constructor for the strategy
	 *
	 * @param annotation
	 *        validation annotation
	 */
	public PatternStrategy(Annotation annotation) {
		if (annotation instanceof Pattern) {
			this.patternAnnotation = (Pattern)annotation;
		} else {
			throw new IllegalArgumentException(annotation.getClass().getSimpleName() + " is not Pattern");
		}
	}

	/**
	 * Produces valid patterns.
	 * 
	 * {@inheritDoc}
	 */
	public String getValue() {

		StringBuffer sb = new StringBuffer();

		if ("^[0-9]{1,45}$".equals(patternAnnotation.regexp())) {

			sb.append(Integer.toString(PodamUtils.getIntegerInRange(0, 1000000)));
		} else if ("^[a-zA-Z0-9_]*$".equals(patternAnnotation.regexp())) {

			for (int i = 0; i < 2; i++) {
				sb.append(PodamUtils.getNiceCharacter());
			}
			sb.append(Integer.toString(PodamUtils.getIntegerInRange(0, 10)));
		}
		return sb.toString();
	}

	// ------------------->> Constants

	// ------------------->> Instance / Static variables

	// ------------------->> Constructors

	// ------------------->> Public methods

	// ------------------->> Getters / Setters

	// ------------------->> Private methods

	// ------------------->> equals() / hashcode() / toString()

	// ------------------->> Inner classes

}
