/**
 * 
 */
package uk.co.jemos.podam.test.dto.docs.example;

import java.io.Serializable;

/**
 * @author mtedone
 * 
 */
public class BankAccount implements Serializable {

	private static final long serialVersionUID = 1L;

	private int account;

	private String bank;

	private String sortCode;

	private double balance;

	/**
	 * @return the account
	 */
	public int getAccount() {
		return account;
	}

	/**
	 * @param account
	 *            the account to set
	 */
	public void setAccount(int account) {
		this.account = account;
	}

	/**
	 * @return the bank
	 */
	public String getBank() {
		return bank;
	}

	/**
	 * @param bank
	 *            the bank to set
	 */
	public void setBank(String bank) {
		this.bank = bank;
	}

	/**
	 * @return the sortCode
	 */
	public String getSortCode() {
		return sortCode;
	}

	/**
	 * @param sortCode
	 *            the sortCode to set
	 */
	public void setSortCode(String sortCode) {
		this.sortCode = sortCode;
	}

	/**
	 * @return the balance
	 */
	public double getBalance() {
		return balance;
	}

	/**
	 * @param balance
	 *            the balance to set
	 */
	public void setBalance(double balance) {
		this.balance = balance;
	}

	/**
	 * Constructs a <code>String</code> with all attributes
	 * in name = value format.
	 *
	 * @return a <code>String</code> representation 
	 * of this object.
	 */
	public String toString()
	{
	    final String TAB = "    ";
	
	    StringBuilder retValue = new StringBuilder();
	    
	    retValue.append("BankAccount ( ")        
	        .append("account = ").append(this.account).append(TAB)
	        .append("bank = ").append(this.bank).append(TAB)
	        .append("sortCode = ").append(this.sortCode).append(TAB)
	        .append("balance = ").append(this.balance).append(TAB)
	        .append(" )");
	    
	    return retValue.toString();
	}
	
	
	

}
