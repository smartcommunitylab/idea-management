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
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.service.ServiceContextFactory;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.util.bridges.mvc.MVCPortlet;

/**
 * Portlet implementation class UserProfile
 */
public class UserProfilePortlet extends MVCPortlet {

	public void editProfile(ActionRequest req, ActionResponse resp)
			throws PortalException, SystemException {
		long userId = ParamUtil.getLong(req, "userId");
		long addressId = ParamUtil.getLong(req, "addressId");
		long contactId = ParamUtil.getLong(req, "contactId");
		User user;
		user = UserLocalServiceUtil.getUser(userId);
		user.setFirstName(ParamUtil.getString(req, "firstName"));
		user.setLastName(ParamUtil.getString(req, "lastName"));
		user.setEmailAddress(ParamUtil.getString(req, "emailAddress"));
		user.setJobTitle(ParamUtil.getString(req, "occupation"));

		user.getExpandoBridge().setAttribute("rangeAge",
				ParamUtil.getString(req, "rangeAge"));
		UserLocalServiceUtil.updateUser(user);

		Contact c = ContactLocalServiceUtil.getContact(contactId);
		c.setMale(ParamUtil.getString(req, "gender")
				.equals(Gender.M.toString()));
		ContactLocalServiceUtil.updateContact(c);

		String city = ParamUtil.getString(req, "city");
		String street = ParamUtil.getString(req, "address");

		if (city != null && !city.trim().isEmpty() && street != null
				&& !street.trim().isEmpty()) {
			if (addressId > 0) {
				Address addr = AddressLocalServiceUtil.getAddress(addressId);
				addr.setStreet1(street);
				addr.setCity(city);
				addr.setZip(ParamUtil.getString(req, "postcode"));
				AddressLocalServiceUtil.updateAddress(addr);
			} else {
				ServiceContext serviceContext = ServiceContextFactory
						.getInstance(User.class.getName(), req);

				Address addr = AddressLocalServiceUtil.addAddress(userId,
						Contact.class.getName(), user.getContactId(),
						ParamUtil.getString(req, "address"), null, null,
						ParamUtil.getString(req, "city"),
						ParamUtil.getString(req, "postcode"), 0, 0, 11000,
						true, true, serviceContext);

			}
		}

	}
}
