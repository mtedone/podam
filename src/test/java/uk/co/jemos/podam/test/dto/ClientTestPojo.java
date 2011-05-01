/**
 * 
 */
package uk.co.jemos.podam.test.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * A simple graph POJO for testing
 * <p>
 * A simple graph DTO is considered a POJO with primitive type properties (e.g.
 * there are no relationships with other POJOs
 * </p>
 * 
 * @author mtedone
 * 
 */
public class ClientTestPojo implements Serializable {

	// ------------------->> Constants

	private static final long serialVersionUID = 1L;

	// ------------------->> Instance / Static variables

	/** The client's first name */
	private String firstName;

	/** The client's last name */
	private String lastName;

	/** The date this client was created */
	private Calendar dateCreated;

	/** The date this client was last updated */
	private Calendar dateLastUpdated;

	/** This Client Address */
	private AddressTestPojo address;

	/** This Client bank accounts */
	private final List<BankAccountTestPojo> bankAccounts = new ArrayList<BankAccountTestPojo>();

	// ------------------->> Constructors

	/** No-arg constructor */
	public ClientTestPojo() {
	}

	// ------------------->> Public methods

	// ------------------->> Getters / Setters

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public Calendar getDateCreated() {
		return dateCreated;
	}

	public Calendar getDateLastUpdated() {
		return dateLastUpdated;
	}

	public AddressTestPojo getAddress() {
		return address;
	}

	public List<BankAccountTestPojo> getBankAccounts() {
		return bankAccounts;
	}

	// ------------------->> Private methods

	// ------------------->> equals() / hashcode() / toString()
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
				+ ((address == null) ? 0 : address.hashCode());
		result = (prime * result)
				+ ((bankAccounts == null) ? 0 : bankAccounts.hashCode());
		result = (prime * result)
				+ ((dateCreated == null) ? 0 : dateCreated.hashCode());
		result = (prime * result)
				+ ((dateLastUpdated == null) ? 0 : dateLastUpdated.hashCode());
		result = (prime * result)
				+ ((firstName == null) ? 0 : firstName.hashCode());
		result = (prime * result)
				+ ((lastName == null) ? 0 : lastName.hashCode());
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
		ClientTestPojo other = (ClientTestPojo) obj;
		if (address == null) {
			if (other.address != null) {
				return false;
			}
		} else if (!address.equals(other.address)) {
			return false;
		}
		if (bankAccounts == null) {
			if (other.bankAccounts != null) {
				return false;
			}
		} else if (!bankAccounts.equals(other.bankAccounts)) {
			return false;
		}
		if (dateCreated == null) {
			if (other.dateCreated != null) {
				return false;
			}
		} else if (!dateCreated.equals(other.dateCreated)) {
			return false;
		}
		if (dateLastUpdated == null) {
			if (other.dateLastUpdated != null) {
				return false;
			}
		} else if (!dateLastUpdated.equals(other.dateLastUpdated)) {
			return false;
		}
		if (firstName == null) {
			if (other.firstName != null) {
				return false;
			}
		} else if (!firstName.equals(other.firstName)) {
			return false;
		}
		if (lastName == null) {
			if (other.lastName != null) {
				return false;
			}
		} else if (!lastName.equals(other.lastName)) {
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

		retValue.append("ClientTestPojo ( ").append(TAB).append("firstName = ")
				.append(firstName).append(TAB).append("lastName = ")
				.append(lastName).append(TAB).append("dateCreated = ")
				.append(dateCreated).append(TAB).append("dateLastUpdated = ")
				.append(dateLastUpdated).append(TAB).append("address = ")
				.append(address).append(TAB).append("bankAccounts = ")
				.append(bankAccounts).append(TAB).append(" )");

		return retValue.toString();
	}

	// ------------------->> Inner classes

}
