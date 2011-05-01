/**
 * 
 */
package uk.co.jemos.podam.test.dto;

import java.io.Serializable;

/**
 * @author mtedone
 * 
 */
public class AddressTestPojo implements Serializable {

	private static final long serialVersionUID = 1L;

	/** Address line 1 */
	private String address1;

	/** Address line 2 */
	private String address2;

	/** Address line 3 */
	private String address3;

	/** The city */
	private String city;

	/** The province */
	private String province;

	/** The zip code */
	private String zipCode;

	/** The country */
	private String country;

	/**
	 * @return the address1
	 */
	public String getAddress1() {
		return address1;
	}

	/**
	 * @return the address2
	 */
	public String getAddress2() {
		return address2;
	}

	/**
	 * @return the address3
	 */
	public String getAddress3() {
		return address3;
	}

	/**
	 * @return the city
	 */
	public String getCity() {
		return city;
	}

	/**
	 * @return the province
	 */
	public String getProvince() {
		return province;
	}

	/**
	 * @return the zipCode
	 */
	public String getZipCode() {
		return zipCode;
	}

	/**
	 * @return the country
	 */
	public String getCountry() {
		return country;
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
				+ ((address1 == null) ? 0 : address1.hashCode());
		result = (prime * result)
				+ ((address2 == null) ? 0 : address2.hashCode());
		result = (prime * result)
				+ ((address3 == null) ? 0 : address3.hashCode());
		result = (prime * result) + ((city == null) ? 0 : city.hashCode());
		result = (prime * result)
				+ ((country == null) ? 0 : country.hashCode());
		result = (prime * result)
				+ ((province == null) ? 0 : province.hashCode());
		result = (prime * result)
				+ ((zipCode == null) ? 0 : zipCode.hashCode());
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
		AddressTestPojo other = (AddressTestPojo) obj;
		if (address1 == null) {
			if (other.address1 != null) {
				return false;
			}
		} else if (!address1.equals(other.address1)) {
			return false;
		}
		if (address2 == null) {
			if (other.address2 != null) {
				return false;
			}
		} else if (!address2.equals(other.address2)) {
			return false;
		}
		if (address3 == null) {
			if (other.address3 != null) {
				return false;
			}
		} else if (!address3.equals(other.address3)) {
			return false;
		}
		if (city == null) {
			if (other.city != null) {
				return false;
			}
		} else if (!city.equals(other.city)) {
			return false;
		}
		if (country == null) {
			if (other.country != null) {
				return false;
			}
		} else if (!country.equals(other.country)) {
			return false;
		}
		if (province == null) {
			if (other.province != null) {
				return false;
			}
		} else if (!province.equals(other.province)) {
			return false;
		}
		if (zipCode == null) {
			if (other.zipCode != null) {
				return false;
			}
		} else if (!zipCode.equals(other.zipCode)) {
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

		retValue.append("AddressTestPojo ( ").append(TAB).append("address1 = ")
				.append(address1).append(TAB).append("address2 = ")
				.append(address2).append(TAB).append("address3 = ")
				.append(address3).append(TAB).append("city = ").append(city)
				.append(TAB).append("province = ").append(province).append(TAB)
				.append("zipCode = ").append(zipCode).append(TAB)
				.append("country = ").append(country).append(TAB).append(" )");

		return retValue.toString();
	}

}
