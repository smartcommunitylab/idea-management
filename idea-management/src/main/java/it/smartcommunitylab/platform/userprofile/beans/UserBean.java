package it.smartcommunitylab.platform.userprofile.beans;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.model.User;

public class UserBean {

	public static enum Gender {
		M, F
	};

	private String firstName;
	private String lastName;
	private String address;
	private String postcode;
	private String city;
	private String occupation;
	private Gender gender;
	private String emailAddress;

	public UserBean(User user) {
		if (user != null) {
			this.firstName = user.getFirstName();
			lastName = user.getLastName();
			occupation = user.getJobTitle();
			emailAddress = user.getEmailAddress();
			try {
				gender = user.getMale() ? Gender.M : Gender.F;
			} catch (PortalException | SystemException e) {
				gender = Gender.F;
			}

			try {
				if (user.getAddresses() != null
						&& !user.getAddresses().isEmpty()) {
					address = user.getAddresses().get(0).getStreet1();
					city = user.getAddresses().get(0).getCity();
					postcode = user.getAddresses().get(0).getZip();
				}
			} catch (SystemException e) {
			}
		}
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPostcode() {
		return postcode;
	}

	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getOccupation() {
		return occupation;
	}

	public void setOccupation(String occupation) {
		this.occupation = occupation;
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

}
