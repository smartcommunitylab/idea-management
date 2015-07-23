package it.smartcommunitylab.platform.userprofile;

import it.smartcommunitylab.platform.userprofile.beans.UserBean.Gender;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.model.Address;
import com.liferay.portal.model.Contact;
import com.liferay.portal.model.User;
import com.liferay.portal.service.AddressLocalServiceUtil;
import com.liferay.portal.service.ContactLocalServiceUtil;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.util.bridges.mvc.MVCPortlet;

/**
 * Portlet implementation class UserProfile
 */
public class UserProfilePortlet extends MVCPortlet {

	public void editProfile(ActionRequest req, ActionResponse resp) {
		long userId = ParamUtil.getLong(req, "userId");
		long addressId = ParamUtil.getLong(req, "addressId");
		long contactId = ParamUtil.getLong(req, "contactId");
		User user;
		try {
			user = UserLocalServiceUtil.getUser(userId);
			user.setFirstName(ParamUtil.getString(req, "firstName"));
			user.setLastName(ParamUtil.getString(req, "lastName"));
			user.setEmailAddress(ParamUtil.getString(req, "emailAddress"));
			user.setJobTitle(ParamUtil.getString(req, "occupation"));
			UserLocalServiceUtil.updateUser(user);
		} catch (PortalException | SystemException e) {

		}

		try {
			Contact c = ContactLocalServiceUtil.getContact(contactId);
			c.setMale(ParamUtil.getString(req, "gender").equals(
					Gender.M.toString()));
			ContactLocalServiceUtil.updateContact(c);
		} catch (PortalException | SystemException e1) {
		}

		if (addressId > 0) {
			try {
				Address addr = AddressLocalServiceUtil.getAddress(addressId);
				addr.setStreet1(ParamUtil.getString(req, "address"));
				addr.setCity(ParamUtil.getString(req, "city"));
				addr.setZip(ParamUtil.getString(req, "postcode"));
				AddressLocalServiceUtil.updateAddress(addr);
			} catch (PortalException | SystemException e) {
			}
		}

	}
}
