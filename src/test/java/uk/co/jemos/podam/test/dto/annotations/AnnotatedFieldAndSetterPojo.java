package uk.co.jemos.podam.test.dto.annotations;

import uk.co.jemos.podam.common.PodamStringValue;
import uk.co.jemos.podam.test.utils.PodamTestConstants;

/**
 * POJO to with annotated field and field setter
 *
 * @author daivanov
 *
 */
public class AnnotatedFieldAndSetterPojo {

	@PodamStringValue(strValue = PodamTestConstants.POST_CODE)
	private String postCode;

	public String getPostCode() {
		return postCode;
	}

	public void setPostCode(@PodamStringValue(strValue = PodamTestConstants.POST_CODE) String postCode) {
		this.postCode = postCode;
	}


}
