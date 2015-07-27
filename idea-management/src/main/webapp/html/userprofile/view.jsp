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
<c:if test='<%=!minimized %>'>
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
                <h6 class="field">Nome</h6>
                <p class="value"><%=userBean.getFirstName()%></p>
              </div>
              <div class="entry">
                <h6 class="field">Cognome</h6>
                <p class="value"><%=userBean.getLastName()%></p>
              </div>
              <div class="entry">
                <h6 class="field">Email</h6>
                <p class="value"><%=userBean.getEmailAddress()%></p>
              </div>
              <div class="entry">
                <h6 class="field">Sesso</h6>
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
                <p class="value"><%=userBean.getOccupation()%></p>
              </div>
              <h4 class="field">Residenza</h4>
              <div class="entry">
                <h6 class="field">Via e numero</h6>
                <%=GetterUtil.getString(userBean.getAddress())%>
                </p>
                <h6 class="field">Citt&agrave;</h6>
                <p class="value">
                  <%=GetterUtil.getString(userBean.getCity())%>
                </p>
                <h6 class="field">codice postale</h6>
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
          windowState="<%=WindowState.MAXIMIZED.toString()%>" portletName="2">
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
          <liferay-portlet:param name="redirect" value="<%=themeDisplay.getURLCurrent()%>" />
        </liferay-portlet:renderURL>

        <%
          editAvatarURL = editAvatarURL.substring(editAvatarURL.indexOf("?"));
        editAvatarURL = "/group/control_panel/manage" + editAvatarURL;
        %>


          <aui:script>
          var login_popup = null;
  Liferay.provide(
    window,
    '_2_changeLogo',
    function(url) {
      var curPortlet = '#p_p_id<portlet:namespace/>';
      Liferay.Portlet.refresh(curPortlet);
      login_popup.hide();
    },
    ['liferay-util-window']
  );
            
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
                              login_popup.show();
                              login_popup.titleNode
                                  .html("Cambia immagine");
                                                            login_popup.on('close', function() {
                  console.log("Modal closed")})

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
                  <div>la tua immagine profilo</div>
                  <div style="margin-top: 1em;">
                    <aui:button cssClass="formbutton-primary" value='Aggiorna'
                      type="button" id="portrait-update" name="portrait-update"></aui:button>
                  </div>
                </div>
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
</c:if>
