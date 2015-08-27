package it.smartcommunitylab.platform.userprofile.beans;

import java.util.Calendar;
import java.util.Date;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.User;

public class UserBean {

	public static final String[] AGE_RANGES = new String[]{"<16","16-35", "36-55","56-75",">75"};
	public static final String dob2Age(Date dob) {
		Calendar now = Calendar.getInstance();
		Calendar c = Calendar.getInstance();
		c.setTime(dob);
		int years = now.get(Calendar.YEAR) - c.get(Calendar.YEAR);
		if (now.get(Calendar.MONTH) < c.get(Calendar.MONTH) || 
			now.get(Calendar.MONTH) == c.get(Calendar.MONTH) && now.get(Calendar.DAY_OF_MONTH) < c.get(Calendar.DAY_OF_MONTH)) years--;
		if (years < 16) return AGE_RANGES[0];
		if (years < 36) return AGE_RANGES[1];
		if (years < 56) return AGE_RANGES[2];
		if (years < 76) return AGE_RANGES[3];
		return AGE_RANGES[4];
	}
	
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
	private String rangeAge;

	public UserBean(User user) {
		if (user != null) {
			this.firstName = user.getFirstName();
			lastName = user.getLastName();
			occupation = user.getJobTitle();
			emailAddress = user.getEmailAddress();
			rangeAge = GetterUtil.get(
					user.getExpandoBridge().getAttribute("rangeAge"),
					StringPool.BLANK);
			try {
				gender = user.getMale() ? Gender.M : Gender.F;
			} catch (PortalException | SystemException e) {
				gender = Gender.F;
			}
			try {
				if (Validator.isBlank(rangeAge) && user.getBirthday() != null) {
					rangeAge = dob2Age(user.getBirthday());
				}
			} catch (PortalException | SystemException e1) {
				
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

	public String getRangeAge() {
		return rangeAge;
	}

	public void setRangeAge(String rangeAge) {
		this.rangeAge = rangeAge;
	}

}
