<%@page import="com.liferay.portal.model.Address"%>
<%@page
	import="it.smartcommunitylab.platform.userprofile.beans.UserBean"%>
<%@page import="com.liferay.portal.service.GroupLocalServiceUtil"%>

<%@ page import="com.liferay.portal.model.User"%>
<%@ page import="com.liferay.portal.model.Group"%>
<%@ page import="com.liferay.portal.service.UserLocalServiceUtil"%>

<%@ include file="/html/common-init.jsp"%>


<%
	User selUser = null;
	Group group = GroupLocalServiceUtil.getGroup(themeDisplay
			.getScopeGroupId());
	long selUserId = group.getCreatorUserId();
	selUser = UserLocalServiceUtil.getUser(selUserId);
	long addressId = selUser.getAddresses()!= null && !selUser.getAddresses().isEmpty() ? selUser.getAddresses().get(0).getAddressId() : -1;
	UserBean userBean = new UserBean(selUser);
	long contactId = selUser.getContactId();
%>
<div class="userprofile">
	<c:if test="<%=selUser != null%>">
		<c:choose>
			<c:when test="<%=selUserId != user.getUserId()%>">
				<!-- READ ONLY -->
				<div class="container">
					<div class="row-fluid">
						<div class="avatar span6 offset3">
							<img
								src="<%=selUser.getPortraitURL(themeDisplay)
								.toString()%>" />
						</div>
					</div>
					<div class="row-fluid">
						<div class="span6">
							<div class="entry">
								<h6 class="field">Nome</h6>
								<p class="value"><%=selUser.getFirstName()%></p>
							</div>
							<div class="entry">
								<h6 class="field">Cognome</h6>
								<p class="value"><%=selUser.getLastName()%></p>
							</div>
							<div class="entry">
								<h6 class="field">Email</h6>
								<p class="value"><%=selUser.getEmailAddress()%></p>
							</div>
							<div class="entry">
								<h6 class="field">Genere</h6>
								<p class="value">
									<c:choose>
										<c:when test="<%=selUser.getMale()%>">
									Maschio
								</c:when>
										<c:when test="<%=selUser.getFemale()%>">
									Femmina
								</c:when>
										<c:otherwise>
									Non specificato
								</c:otherwise>
									</c:choose>
								</p>
							</div>
						</div>
						<div class="span6">
							<div class="entry">
								<h6 class="field">Professione</h6>
								<p class="value"><%=selUser.getJobTitle()%></p>
							</div>
							<h4 class="field">Residenza</h4>
							<div class="entry">
								<h6 class="field">Via e numero</h6>
								<p class="value">
									<c:choose>
										<c:when test="!selUser.getAddresses().isEmpty()">
											<c:if test="selUser.getAddresses().get(0).getStreet1() != ''">
												<%=selUser.getAddresses().get(0)
											.getStreet1()%>
											</c:if>
											<c:if test="selUser.getAddresses().get(0).getStreet2() != ''">
												<br />
												<%=selUser.getAddresses().get(0)
											.getStreet2()%>
											</c:if>
											<c:if test="selUser.getAddresses().get(0).getStreet3() != ''">
												<br />
												<%=selUser.getAddresses().get(0)
											.getStreet3()%>
											</c:if>
										</c:when>
										<c:otherwise>
											...
										</c:otherwise>
									</c:choose>
								</p>
								<h6 class="field">Citt&agrave;</h6>
								<p class="value">
									<c:choose>
										<c:when test="!selUser.getAddresses().isEmpty()">
											<%=selUser.getAddresses().get(0)
										.getCity()%>
										</c:when>
										<c:otherwise>
											...
										</c:otherwise>
									</c:choose>
								</p>
							</div>
						</div>
			</c:when>
			<c:otherwise>
				<!-- FORM -->
				<portlet:actionURL name="editProfile" var="editProfileURL">
				</portlet:actionURL>

				<aui:model-context bean="<%=userBean%>" model="<%=UserBean.class%>" />

				<div class="container">
					<aui:form cssClass="idea-form" method="POST"
						action="<%=editProfileURL.toString()%>" name="profile">
						<div class="row-fluid">
							<div class="avatar span6 offset3">
								<img
									src="<%=selUser.getPortraitURL(themeDisplay)
									.toString()%>" />
							</div>
						</div>
						<div class="row-fluid">
							<div class="span6">
								<div class="entry">
									<h6 class="field">Nome</h6>
									<aui:fieldset cssClass="simple-field">
										<aui:input type="text" first="true" label="" name="firstName"></aui:input>
									</aui:fieldset>
								</div>
								<div class="entry">
									<h6 class="field">Cognome</h6>
									<aui:fieldset cssClass="simple-field">
										<aui:input type="text" first="true" label="" name="lastName"></aui:input>
									</aui:fieldset>
								</div>
								<div class="entry">
									<h6 class="field">Email</h6>
									<aui:fieldset cssClass="simple-field">
										<aui:input type="text" first="true" label=""
											name="emailAddress"></aui:input>
									</aui:fieldset>
								</div>
								<div class="entry">
									<h6 class="field">Sesso</h6>
									<aui:input
										checked="<%=userBean.getGender().equals(
										UserBean.Gender.M)%>"
										type="radio" label="male" name="gender" value="M"
										inlineField="true">
									</aui:input>
									<aui:input
										checked="<%=userBean.getGender().equals(
										UserBean.Gender.F)%>"
										type="radio" label="female" name="gender" value="F"
										inlineField="true">
									</aui:input>
								</div>
							</div>
							<div class="span6">
								<div class="entry">
									<h6 class="field">Professione</h6>
									<aui:fieldset cssClass="simple-field">
										<aui:input type="text" first="true" label="" name="occupation"></aui:input>
									</aui:fieldset>
								</div>
								<h4 class="field">Residenza</h4>
								<div class="entry">
									<h6 class="field">Via e numero</h6>
									<aui:fieldset cssClass="simple-field">
										<aui:input type="text" first="true" label="" name="address"></aui:input>
									</aui:fieldset>
									<h6 class="field">Citt&agrave;</h6>
									<aui:fieldset cssClass="simple-field">
										<aui:input type="text" first="true" label="" name="city"></aui:input>
									</aui:fieldset>
									<h6 class="field">Codice postale</h6>
									<aui:fieldset cssClass="simple-field">
										<aui:input type="text" first="true" label="" name="postcode"></aui:input>
									</aui:fieldset>
									<aui:fieldset>
										<aui:input type="hidden" name="userId"
											value="<%=user.getUserId()%>"></aui:input>
									</aui:fieldset>
									<aui:fieldset>
										<aui:input type="hidden" name="addressId"
											value="<%=addressId%>"></aui:input>
									</aui:fieldset>
										<aui:fieldset>
										<aui:input type="hidden" name="contactId"
											value="<%=contactId%>"></aui:input>
									</aui:fieldset>
								</div>
							</div>
						</div>
						<div class="row-fluid">
							<aui:button-row cssClass="formbutton-row">
								<aui:button cssClass="formbutton-primary" value='Salva'
									type="submit"></aui:button>
							</aui:button-row>
						</div>
					</aui:form>
				</div>

				<%-- <liferay-ui:tabs names="Profilo, Notifiche, Attivita, Punteggi"
			refresh="<%=false%>">
			<liferay-ui:section>
				Profilo
			</liferay-ui:section>
			<liferay-ui:section>
				Notifiche
			</liferay-ui:section>
			<liferay-ui:section>
				Attivita
			</liferay-ui:section>
			<liferay-ui:section>
				Punteggi
			</liferay-ui:section>
		</liferay-ui:tabs> --%>
			</c:otherwise>
		</c:choose>
	</c:if>
</div>