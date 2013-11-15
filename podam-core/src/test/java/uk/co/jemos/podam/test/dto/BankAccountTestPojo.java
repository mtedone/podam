/**
 * 
 */
package uk.co.jemos.podam.test.dto;

import java.io.Serializable;

/**
 * @author mtedone
 * 
 */
public class BankAccountTestPojo implements Serializable {

	private static final long serialVersionUID = 1L;

	/** Account nbr */
	private String accountNbr;

	/** Sort code */
	private String sortCode;

	/** Bank name */
	private String bankName;

	/** Branch name */
	private String branchName;

	/**
	 * @return the accountNbr
	 */
	public String getAccountNbr() {
		return accountNbr;
	}

	/**
	 * @return the sortCode
	 */
	public String getSortCode() {
		return sortCode;
	}

	/**
	 * @return the bankName
	 */
	public String getBankName() {
		return bankName;
	}

	/**
	 * @return the branchName
	 */
	public String getBranchName() {
		return branchName;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = (prime * result)
				+ ((accountNbr == null) ? 0 : accountNbr.hashCode());
		result = (prime * result)
				+ ((bankName == null) ? 0 : bankName.hashCode());
		result = (prime * result)
				+ ((branchName == null) ? 0 : branchName.hashCode());
		result = (prime * result)
				+ ((sortCode == null) ? 0 : sortCode.hashCode());
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (this.getClass() != obj.getClass()) {
			return false;
		}
		BankAccountTestPojo other = (BankAccountTestPojo) obj;
		if (accountNbr == null) {
			if (other.accountNbr != null) {
				return false;
			}
		} else if (!accountNbr.equals(other.accountNbr)) {
			return false;
		}
		if (bankName == null) {
			if (other.bankName != null) {
				return false;
			}
		} else if (!bankName.equals(other.bankName)) {
			return false;
		}
		if (branchName == null) {
			if (other.branchName != null) {
				return false;
			}
		} else if (!branchName.equals(other.branchName)) {
			return false;
		}
		if (sortCode == null) {
			if (other.sortCode != null) {
				return false;
			}
		} else if (!sortCode.equals(other.sortCode)) {
			return false;
		}
		return true;
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

		retValue.append("BankAccountTestPojo ( ").append(TAB)
				.append("accountNbr = ").append(accountNbr).append(TAB)
				.append("sortCode = ").append(sortCode).append(TAB)
				.append("bankName = ").append(bankName).append(TAB)
				.append("branchName = ").append(branchName).append(TAB)
				.append(" )");

		return retValue.toString();
	}
}
