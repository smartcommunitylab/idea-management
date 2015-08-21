<%@page import="com.liferay.portal.model.UserConstants"%>
<%@page import="javax.portlet.WindowState"%>
<%@page import="javax.portlet.PortletMode"%>
<%@page import="com.liferay.portal.kernel.portlet.LiferayWindowState"%>
<%@page import="com.liferay.portal.model.Address"%>
<%@page
	import="it.smartcommunitylab.platform.userprofile.beans.UserBean"%>
<%@page import="com.liferay.portal.service.GroupLocalServiceUtil"%>

<%@ page import="com.liferay.portal.model.User"%>
<%@ page import="com.liferay.portal.model.Group"%>
<%@ page import="com.liferay.portal.service.UserLocalServiceUtil"%>

<%@ include file="/html/common-init.jsp"%>


<%
	boolean minimized = ParamUtil.getBoolean(renderRequest, "hidden");
%>
<c:if test='<%=!minimized%>'>
	<%
		long passedGroupId = ParamUtil.getLong(renderRequest, "groupId");
			if (passedGroupId == 0) passedGroupId = themeDisplay.getScopeGroupId();

			  User selUser = null;
			  Group group = GroupLocalServiceUtil.getGroup(passedGroupId);
			  long selUserId = group.getCreatorUserId();
			  selUser = UserLocalServiceUtil.getUser(selUserId);
			  long addressId = selUser.getAddresses() != null
			  && !selUser.getAddresses().isEmpty() ? selUser
			  .getAddresses().get(0).getAddressId() : -1;
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
									<h6 class="field">
										<liferay-ui:message key="lbl_firstName" />
									</h6>
									<p class="value"><%=userBean.getFirstName()%></p>
								</div>
								<div class="entry">
									<h6 class="field">
										<liferay-ui:message key="lbl_lastName" />
									</h6>
									<p class="value"><%=userBean.getLastName()%></p>
								</div>
								<!-- <div class="entry">
                <h6 class="field"><liferay-ui:message key="lbl_email"/></h6>
                <p class="value"><%=userBean.getEmailAddress()%></p>
              </div> -->
								<div class="entry">
									<h6 class="field">
										<liferay-ui:message key="lbl_sex" />
									</h6>
									<p class="value">
										<c:choose>
											<c:when test="<%=selUser.getMale()%>">
												<liferay-ui:message key="lbl_sex_male" />
											</c:when>
											<c:when test="<%=selUser.getFemale()%>">
												<liferay-ui:message key="lbl_sex_female" />
											</c:when>
											<c:otherwise>
												<liferay-ui:message key="lbl_sex_undefined" />
											</c:otherwise>
										</c:choose>
									</p>
								</div>
								<div class="entry">
									<h6 class="field">
										<liferay-ui:message key="lbl_rangeAge" />
									</h6>
									<p class="value">
										<%=userBean.getRangeAge()%>
									</p>
								</div>
							</div>
							<div class="span6">
								<div class="entry">
									<h6 class="field">
										<liferay-ui:message key="lbl_occupation" />
									</h6>
									<p class="value"><%=userBean.getOccupation()%></p>
								</div>
								<h4 class="field">
									<liferay-ui:message key="lbl_residence" />
								</h4>
								<div class="entry">
									<h6 class="field">
										<liferay-ui:message key="lbl_street" />
									</h6>
									<p class="value">
										<%=GetterUtil.getString(userBean.getAddress())%>
									</p>
									<h6 class="field">
										<liferay-ui:message key="lbl_city" />
									</h6>
									<p class="value">
										<%=GetterUtil.getString(userBean.getCity())%>
									</p>
									<h6 class="field">
										<liferay-ui:message key="lbl_zip" />
									</h6>
									<p class="value">
										<%=GetterUtil.getString(userBean.getPostcode())%>
									</p>
								</div>
							</div>
				</c:when>
				<c:otherwise>
					<!-- FORM -->
					<portlet:actionURL name="editProfile" var="editProfileURL">
					</portlet:actionURL>

					<aui:model-context bean="<%=userBean%>" model="<%=UserBean.class%>" />


					<liferay-portlet:renderURL var="redirectURL"
						doAsGroupId="<%=selUser.getGroupId()%>"
						portletMode="<%=PortletMode.VIEW.toString()%>"
						windowState="<%=WindowState.MAXIMIZED.toString()%>"
						portletName="2">
						<liferay-portlet:param name="p_u_i_d"
							value="<%=String.valueOf(selUserId)%>" />
						<liferay-portlet:param name="controlPanelCategory" value="my" />

					</liferay-portlet:renderURL>

					<%
						redirectURL = redirectURL.substring(redirectURL.indexOf("?"));
																	        redirectURL = "/group/control_panel/manage" + redirectURL;
					%>

					<liferay-portlet:renderURL var="editAvatarURL"
						doAsGroupId="<%=selUser.getGroupId()%>"
						portletMode="<%=PortletMode.VIEW.toString()%>"
						windowState='<%="pop_up"%>' portletName="2">
						<liferay-portlet:param name="p_u_i_d"
							value="<%=String.valueOf(selUserId)%>" />
						<liferay-portlet:param name="controlPanelCategory" value="my" />
						<liferay-portlet:param name="portrait_id"
							value="<%=String.valueOf(0)%>" />
						<liferay-portlet:param name="struts_action"
							value="/my_account/edit_user_portrait" />
						<liferay-portlet:param name="redirect"
							value="<%=themeDisplay.getURLCurrent()%>" />
					</liferay-portlet:renderURL>

					<%
						editAvatarURL = editAvatarURL.substring(editAvatarURL.indexOf("?"));
																	        editAvatarURL = "/group/control_panel/manage" + editAvatarURL;
					%>


					<aui:script>
						var login_popup = null;
						Liferay.provide(window, '_2_changeLogo', function(url) {
							var curPortlet = '#p_p_id<portlet:namespace/>';
							Liferay.Portlet.refresh(curPortlet);
							login_popup.hide();
						}, [ 'liferay-util-window' ]);

						AUI()
								.use(
										'aui-base',
										'liferay-util-window',
										'aui-io-plugin-deprecated',
										function(A) {

											A
													.one(
															'#<portlet:namespace/>portrait-update')
													.on(
															'click',
															function(event) {
																login_popup = Liferay.Util.Window
																		.getWindow(
																				{
																					dialog : {
																						centered : true,
																						constrain2view : true,
																						modal : true,
																						resizable : false,
																						width : 600,
																					}
																				})
																		.plug(
																				A.Plugin.DialogIframe,
																				{
																					autoLoad : true,
																					iframeCssClass : 'dialog-iframe',
																					uri : '<%=editAvatarURL%>'
																				})
																		.render();
																login_popup
																		.show();
																login_popup.titleNode
																		.html('<liferay-ui:message key="lbl_change_image"/>');
																login_popup
																		.on(
																				'close',
																				function() {
																					console
																							.log("Modal closed")
																				})

															});
										});
					</aui:script>
						

					
					<div class="container">
						<aui:form cssClass="idea-form" method="POST"
							action="<%=editProfileURL.toString()%>" name="profile">
							<div class="row-fluid">
								<div class="avatar span6 offset3">
									<div class="col-md-6">
										<img
											src="<%=selUser.getPortraitURL(themeDisplay)
										.toString()%>" />
									</div>
									<div class="col-md-6 pull-right">
										<div>
											<liferay-ui:message key="lbl_image" />
										</div>
										<div style="margin-top: 1em;">
											<aui:button cssClass="formbutton-primary" value='lbl_update'
												type="button" id="portrait-update" name="portrait-update"></aui:button>
										</div>
									</div>
								</div>
							</div>
							<div class="row-fluid">
								<div class="span6">
									<div class="entry">
										<aui:field-wrapper required="true" cssClass="simple-field"
											label="lbl_firstName">
											<aui:input required="true" type="text" first="true" label=""
												name="firstName"></aui:input>
										</aui:field-wrapper>
									</div>
									<div class="entry">
										<aui:field-wrapper required="true" cssClass="simple-field"
											label="lbl_lastName">
											<aui:input required="true" type="text" first="true" label=""
												name="lastName"></aui:input>
										</aui:field-wrapper>
									</div>
									<div class="entry">
										<aui:field-wrapper required="true" cssClass="simple-field"
											label="lbl_email">
											<aui:input required="true" type="text" first="true" label=""
												name="emailAddress"></aui:input>
										</aui:field-wrapper>
									</div>
									<div class="entry">
										<aui:field-wrapper label="lbl_sex">
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
										</aui:field-wrapper>
									</div>
									<div class="entry">
										<aui:field-wrapper cssClass="simple-field"
											label="lbl_rangeAge" required="true">
											<aui:select name="rangeAge" showEmptyOption="true" label="">
												<aui:option value="<16" label="<16"></aui:option>
												<aui:option value="16-35" label="16-35"></aui:option>
												<aui:option value="36-55" label="36-55"></aui:option>
												<aui:option value="56-75" label="56-75"></aui:option>
												<aui:option value=">75" label=">75"></aui:option>
											</aui:select>
										</aui:field-wrapper>
									</div>
								</div>
								<div class="span6">
									<div class="entry">
										<aui:fieldset cssClass="simple-field" label="lbl_occupation">
											<aui:input type="text" first="true" label=""
												name="occupation"></aui:input>
										</aui:fieldset>
									</div>
									<h4 class="field">
										<liferay-ui:message key="lbl_residence" />
									</h4>
									<div class="entry">
										<aui:fieldset cssClass="simple-field" label="lbl_street">
											<aui:input id="address" type="text" first="true" label=""
												name="address">
												<aui:validator name="custom" errorMessage="city_required">
                      function (val, fieldNode, ruleValue) {
                       if (val) {
                         return !!document.getElementById('<portlet:namespace />city').value;
                       }
                       return true;
                      }
                      </aui:validator>
											</aui:input>
										</aui:fieldset>
										<aui:fieldset cssClass="simple-field" label="lbl_city">
											<aui:input id="city" type="text" first="true" label=""
												name="city">
												<aui:validator name="custom" errorMessage="address_required">
											function (val, fieldNode, ruleValue) {
											 if (val) {
											   return !!document.getElementById('<portlet:namespace />address').value;
											 }
											 return true;
											}
											</aui:validator>
											</aui:input>
										</aui:fieldset>
										<aui:fieldset cssClass="simple-field" label="lbl_zip">
											<aui:input type="text" first="true" label="" name="postcode">
											</aui:input>
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
									<aui:button cssClass="formbutton-primary" value='lbl_save'
										type="submit"></aui:button>
								</aui:button-row>
							</div>
						</aui:form>
					</div>

					<aui:script use="aui-base">
						AUI()
								.ready(
										'alloy-node',
										'aui-form-validator',
										function(A) {
											var formValidator = new A.FormValidator(
													{
														boundingBox : document.<portlet:namespace />profile,
														rules : {
															<portlet:namespace />rangeAge : {
																required : true
															}
														},
														on : {
															validateField : function(
																	event) {
															},
															validField : function(
																	event) {
															},
															errorField : function(
																	event) {
															},
															submitError : function(
																	event) {
																var errors = event.validator.errors;
																event
																		.preventDefault();
															},
															submit : function(
																	event) {
																return false;
															}
														}
													});
										});
					</aui:script>
	


					
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
</c:if>
