/**
 * 
 */
package uk.co.jemos.podam.test.dto.docs.example;

import java.io.Serializable;

import uk.co.jemos.podam.common.PodamExclude;

/**
 * @author mtedone
 * 
 */
public class OrderItem implements Serializable {

	private static final long serialVersionUID = 1L;

	private int id;

	@PodamExclude(comment = "We don't want notes to be automatically filled")
	private String note;

	private double lineAmount;

	private Article article;

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the note
	 */
	public String getNote() {
		return note;
	}

	/**
	 * @param note
	 *            the note to set
	 */
	public void setNote(String note) {
		this.note = note;
	}

	/**
	 * @return the lineAmount
	 */
	public double getLineAmount() {
		return lineAmount;
	}

	/**
	 * @param lineAmount
	 *            the lineAmount to set
	 */
	public void setLineAmount(double lineAmount) {
		this.lineAmount = lineAmount;
	}

	/**
	 * @return the article
	 */
	public Article getArticle() {
		return article;
	}

	/**
	 * @param article
	 *            the article to set
	 */
	public void setArticle(Article article) {
		this.article = article;
	}

	/**
	 * Constructs a <code>String</code> with all attributes in name = value
	 * format.
	 * 
	 * @return a <code>String</code> representation of this object.
	 */
	@Override
	public String toString() {
		final String TAB = "    ";

		StringBuilder retValue = new StringBuilder();

		retValue.append("OrderItem ( ").append("id = ").append(id).append(TAB)
				.append("note = ").append(note).append(TAB)
				.append("lineAmount = ").append(lineAmount).append(TAB)
				.append("article = ").append(article).append(TAB).append(" )");

		return retValue.toString();
	}

}
