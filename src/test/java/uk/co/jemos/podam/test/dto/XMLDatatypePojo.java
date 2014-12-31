/**
 *
 */
package uk.co.jemos.podam.test.dto;

import javax.xml.datatype.Duration;
import javax.xml.datatype.XMLGregorianCalendar;

/**
 * POJO to test when Podam sets a boolean.
 *
 * @author daivanov
 *
 */
public class XMLDatatypePojo {
	private XMLGregorianCalendar calendar;
	private Duration duration;

	public XMLDatatypePojo() {
	}

	public XMLGregorianCalendar getCalendar() {
		return calendar;
	}

	public void setCalendar(XMLGregorianCalendar calendar) {
		this.calendar = calendar;
	}

	public Duration getDuration() {
		return duration;
	}

	public void setDuration(Duration duration) {
		this.duration = duration;
	}
}
