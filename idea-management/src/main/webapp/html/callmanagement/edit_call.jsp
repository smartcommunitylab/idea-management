
<%@page import="java.util.Calendar"%>
<%@page import="java.util.GregorianCalendar"%>
<%@page import="java.util.Date"%>
<%@ include file="/html/common-init.jsp" %>

<%@ page import="it.smartcommunitylab.platform.idea.model.Call"%>
<%@ page import="com.liferay.portal.kernel.util.ParamUtil"%>
<%@ page import="it.smartcommunitylab.platform.idea.portlet.Utils"%>
<%@ page import="com.liferay.portal.kernel.util.WebKeys"%>
<%@ page import="com.liferay.portal.theme.ThemeDisplay"%>
<%@ page import="it.smartcommunitylab.platform.idea.service.CallLocalServiceUtil"%>



<%
    String baseUrl = Utils.getBaseURL(request);
	  Call entry = null;
    AssetEntry assetEntry = null;

    long callId = ParamUtil.getLong(request, "callId");

        if (callId > 0) {
                entry = CallLocalServiceUtil.getCall(callId);
                assetEntry = AssetEntryLocalServiceUtil.getEntry(
                        Call.class.getName(), callId);
    }

    Utils.clearPublicRenderParameter(renderRequest, "mvcPath");
    String curCategoryIds = entry == null ? "" : entry.getCategoryIds();
    PortletResponse portletRes = (PortletResponse)request.getAttribute(JavaConstants.JAVAX_PORTLET_RESPONSE);

%>

<%
	// workaround for assert-tag-selector nullpointerexception
pageContext.setAttribute("themeDisplay", themeDisplay);
%>


<portlet:renderURL var="viewURL"  windowState="normal">
	<portlet:param name="mvcPath" value="/html/callmanagement/asset/full_content.jsp"></portlet:param>
</portlet:renderURL>

<portlet:renderURL var="redirect" windowState="normal">
  <c:if test="<%=entry != null %>">
  <portlet:param name="callId" value='<%=String.valueOf(entry.getCallId()) %>'></portlet:param>
  </c:if>
  <portlet:param name="mvcPath" value='<%=(entry == null ? "/html/callmanagement/view.jsp" : "/html/callmanagement/asset/full_content.jsp")%>' />
</portlet:renderURL>

<portlet:actionURL
	name='<%=entry == null ? "addEntry" : "updateEntry"%>'
	var="editEntryURL"  windowState="normal">
	<c:if test="<%=entry != null %>">
  <portlet:param name="callId" value='<%=String.valueOf(entry.getCallId()) %>'></portlet:param>
  </c:if>
  <portlet:param name="redirect" value='<%=Utils.generateRenderURL(baseUrl, redirect.toString()) %>'></portlet:param>
</portlet:actionURL>

<aui:form cssClass="idea-form" action="<%=editEntryURL%>" name="call">
<aui:model-context bean="<%= entry %>" model="<%= Call.class %>" />
	
	<aui:fieldset cssClass="simple-field">
		<aui:input name="title" label="" placeholder='<%=LanguageUtil.get(locale, "lbl_title") %>' ></aui:input>
	</aui:fieldset>

	<aui:field-wrapper label="lbl_longDesc">
  <aui:input name="hdesc" type="hidden" value='<%= entry != null ? entry.getDescription() : "" %>'></aui:input>
		<liferay-ui:input-editor name="desc" onChangeMethod="onChange1"
			toolbarSet="liferay-article" initMethod="initEditor1" width="200" />
		<script type="text/javascript">
        function <portlet:namespace />initEditor1() { return document.getElementById('<portlet:namespace />hdesc').value; }
        function <portlet:namespace />onChange1() { document.getElementById('<portlet:namespace />hdesc').value = window['<portlet:namespace />desc'].getHTML(); }
    </script>
	</aui:field-wrapper>
	
	<div class="row-fluid">
	  <div class="span4">
			<aui:fieldset label="lbl_deadline">
			  <% if (entry != null && entry.getDeadline() != null) {
  			    GregorianCalendar cal = new GregorianCalendar();
            cal.setTime(entry.getDeadline());
				    int initDay = cal.get(Calendar.DAY_OF_MONTH);
				    int initMonth = cal.get(Calendar.MONTH); 
				    int initYear  = cal.get(Calendar.YEAR);
			  %>
				<liferay-ui:input-date name="deadline" dayParam="dday" monthParam="dmonth" yearParam="dyear" dayValue="<%=initDay %>" monthValue="<%=initMonth %>" yearValue="<%=initYear %>"></liferay-ui:input-date>
				<% } else { %>
        <liferay-ui:input-date nullable="true" name="deadline" dayParam="dday" monthParam="dmonth" yearParam="dyear"></liferay-ui:input-date>
				<%  } %>
			</aui:fieldset>
    </div>
    <div class="span4">
		  <aui:fieldset label="lbl_publicationdeadline">
        <% if (entry != null && entry.getPublicationDeadline() != null) {
            GregorianCalendar cal = new GregorianCalendar();
            cal.setTime(entry.getPublicationDeadline());
            int initPDay = cal.get(Calendar.DAY_OF_MONTH);
            int initPMonth = cal.get(Calendar.MONTH); 
            int initPYear  = cal.get(Calendar.YEAR);
        %>
		    <liferay-ui:input-date name="publicationdeadline" dayParam="pdday" monthParam="pdmonth" yearParam="pdyear" dayValue="<%=initPDay %>" monthValue="<%=initPMonth %>" yearValue="<%=initPYear %>"></liferay-ui:input-date>
        <% } else { %>
        <liferay-ui:input-date nullable="true" name="publicationdeadline" dayParam="pdday" monthParam="pdmonth" yearParam="pdyear"></liferay-ui:input-date>
        <%  } %>
		  </aui:fieldset>
    </div>
    <div class="span4">
		  <aui:fieldset label="lbl_realizationdeadline">
        <% if (entry != null && entry.getRealizationDeadline() != null) {
            GregorianCalendar cal = new GregorianCalendar();
            cal.setTime(entry.getRealizationDeadline());
            int initEDay = cal.get(Calendar.DAY_OF_MONTH);
            int initEMonth = cal.get(Calendar.MONTH); 
            int initEYear  = cal.get(Calendar.YEAR);
        %>
        <liferay-ui:input-date name="realizationdeadline" dayParam="edday" monthParam="edmonth" yearParam="edyear" dayValue="<%=initEDay %>" monthValue="<%=initEMonth %>" yearValue="<%=initEYear %>"></liferay-ui:input-date>
        <% } else { %>
        <liferay-ui:input-date nullable="true" name="realizationdeadline" dayParam="edday" monthParam="edmonth" yearParam="edyear"></liferay-ui:input-date>
        <%  } %>
		  </aui:fieldset>
		</div>
	</div>
	<aui:input name="callId" type="hidden"></aui:input>

	<liferay-ui:asset-categories-error />
<%--   <aui:field-wrapper cssClass="categoriesselector-wrapper" label="lbl_cat"> --%>
<%--   <aui:input name="catId" type="hidden"></aui:input> --%>
<%-- 	<liferay-ui:asset-categories-selector curCategoryIds="<%=curCategoryIds %>"> --%>
<%-- 	</liferay-ui:asset-categories-selector> --%>
  <%
//   request.setAttribute("im-acs:single-select",false);
//   request.setAttribute("im-acs:curCategories",curCategoryIds);
  %>
<%--   <%@ include file="/html/common/asset-categories.jsp" %> --%>
<%-- 	</aui:field-wrapper> --%>

  <aui:field-wrapper cssClass="categoriesselector-wrapper" label="lbl_cat">
  <aui:input name="catId" type="hidden"></aui:input>
 <liferay-ui:asset-categories-selector curCategoryIds="<%=curCategoryIds %>">
 </liferay-ui:asset-categories-selector>
  </aui:field-wrapper>

  <liferay-ui:asset-tags-error />
	<liferay-ui:asset-tags-selector className="<%=Call.class.getName()%>"
		classPK="<%= callId%>">
	</liferay-ui:asset-tags-selector>



  <aui:fieldset label="">
  <%
  request.setAttribute("liferay-ui:input-asset-links:className",Call.class.getName());
  request.setAttribute("liferay-ui:input-asset-links:assetEntryId",String.valueOf(assetEntry == null ? 0 : assetEntry.getEntryId()));
  %>
  <%@ include file="/html/common/asset-links.jsp" %>
  </aui:fieldset>

	<aui:button-row cssClass="formbutton-row">
    <aui:button cssClass="formbutton-cancel" type="cancel" onClick="javascript: window.history.go(-1);"></aui:button>
    <aui:button cssClass="formbutton-primary" value='<%= LanguageUtil.get(locale, "btn_save_call") %>' type="submit"></aui:button>
	</aui:button-row>
</aui:form>
<aui:script>
AUI().use('aui-base','liferay-form','aui-form-validator',
  function(A) {

	 Liferay.Form.register(
      {
        id: '<portlet:namespace/>call',
        fieldRules: [
          {
        	     body:'',
        	     custom: false,
        	     fieldName: '<portlet:namespace/>hdesc',
	             validatorName:'required'
          },
          {
              body:'',
              custom: false,
	            fieldName: '<portlet:namespace/>title',
	            validatorName:'required'
           },
           {
           	   body:function (val, fieldNode, ruleValue) {
                 var inputs = document.getElementsByTagName("input");
                 for (var i = 0; i < inputs.length; i++) {
                	 if (inputs[i].id.indexOf( '<portlet:namespace/>assetCategoryIds') == 0) {
                		 if (inputs[i].value) return true;
                	 }
                 }
                 return false;
           	   },
           	   errorMessage:Liferay.Language.get('this-field-is-required'),
							 custom:true,
	             fieldName: '<portlet:namespace/>catId',
               validatorName:'customCatValidator'
           }
        ]     
                   
  });
	/*
   var rules = {
      <portlet:namespace/>hdesc: {
          required: true
      },
      <portlet:namespace/>title: {
          required: true
      }
   };
   new A.FormValidator(
          {
            boundingBox: '#<portlet:namespace/>call',
            rules: rules,
            showAllMessages: true
          }
        );
   */
      }
    );
</aui:script>  
           