<%@page import="java.util.ArrayList"%>
<%@ page import="it.smartcommunitylab.platform.idea.model.Idea"%>
<%@ page import="com.liferay.portal.kernel.util.ParamUtil"%>
<%@ page import="it.smartcommunitylab.platform.idea.service.IdeaLocalServiceUtil"%>
<%@page import="it.smartcommunitylab.platform.idea.model.Call"%>
<%@page import="it.smartcommunitylab.platform.idea.service.CallLocalServiceUtil"%>
<%@ page import="com.liferay.portlet.asset.model.AssetCategory" %>
<%@ page import="it.smartcommunitylab.platform.idea.portlet.Utils"%>

<%@ include file="/html/common-init.jsp" %>

<%
        String baseUrl = Utils.getBaseURL(request);
        Idea idea = null;

        long ideaId = ParamUtil.getLong(request, "ideaId");
        AssetEntry assetEntry = null;

        if (ideaId > 0) {
                idea = IdeaLocalServiceUtil.getIdea(ideaId);
                assetEntry = AssetEntryLocalServiceUtil.getEntry(
                        Idea.class.getName(), idea.getIdeaId());
        }
        
        String categoryId = ParamUtil.getString(request, "categoryId");
        
        Long callId = ParamUtil.getLong(request, "callId");
        if(callId > 0) {
            AssetEntry callEntry = AssetEntryLocalServiceUtil.getEntry(
            	      Call.class.getName(), callId);
            List<AssetCategory> categories = callEntry.getCategories();   
            if (categories != null && categories.size() > 0) {
            	categoryId = ""+ categories.get(0).getCategoryId();
            }
        }
        

        String tagNames = "";
        
        if(!categoryId.isEmpty()) {
        	List<AssetTag> categoryTags = IdeaLocalServiceUtil.getCategoryTags(new long[]{Long.valueOf(categoryId)}, scopeGroupId);
        	for(AssetTag t : categoryTags) {
        		tagNames+= (tagNames.isEmpty() ? "" : ",") + t.getName();
        	}
        }
        
%>

<%
	// workaround for assert-tag-selector nullpointerexception
pageContext.setAttribute("themeDisplay", themeDisplay);
%>

<portlet:renderURL var="redirect">
  <c:if test="<%=idea != null %>">
  <portlet:param name="ideaId" value='<%=String.valueOf(idea.getIdeaId()) %>'></portlet:param>
  </c:if>
  <portlet:param name="mvcPath" value='<%=(idea == null ? "/html/idea/view.jsp" : "/html/idea/asset/full_content.jsp")%>' />
</portlet:renderURL>


<portlet:actionURL name='<%= idea == null ? "addNewIdea" : "updateIdea"%>' var="addIdeaURL">
  <c:if test='<%=idea != null %>'>
  <portlet:param name="ideaId" value='<%= String.valueOf(idea.getIdeaId()) %>'></portlet:param>
  </c:if>
  <portlet:param name="redirect" value='<%=Utils.generateRenderURL(baseUrl, redirect.toString()) %>'></portlet:param>
</portlet:actionURL>

<aui:form cssClass="idea-form" action="<%=addIdeaURL.toString()%>" name="idea">
<aui:model-context bean="<%= idea %>" model="<%= Idea.class %>" />
	<aui:fieldset cssClass="simple-field">
		<aui:input placeholder='<%=LanguageUtil.get(locale, "lbl_title") %>' first="true" label="" name="title"></aui:input>
	</aui:fieldset>

  <aui:field-wrapper>
    <aui:input placeholder='<%=LanguageUtil.get(locale, "lbl_shortDesc") %>' name="shortDesc" type="textarea" label=""></aui:input>  
  </aui:field-wrapper>
  
	<aui:field-wrapper label="lbl_longDesc">
    <aui:input name="ldesc" type="hidden" value='<%= idea != null ? idea.getLongDesc() : "" %>'></aui:input>
		<liferay-ui:input-editor name="longDesc" 
			toolbarSet="liferay-article" initMethod="initEditor2" onChangeMethod="onChange2" width="200" />
		<script type="text/javascript">
        function <portlet:namespace />initEditor2() { return document.getElementById('<%=renderResponse.getNamespace()%>ldesc').value; }
        function <portlet:namespace />onChange2() { document.getElementById('<portlet:namespace />ldesc').value = window['<portlet:namespace />longDesc'].getHTML(); }
    </script>
    
	</aui:field-wrapper>
	
	<aui:input name="ideaId" type="hidden"></aui:input>
	<aui:input name="categoryId" type="hidden" value="<%= categoryId %>"></aui:input>
	<aui:input name="callId" type="hidden" value="<%= callId %>"></aui:input>

  <aui:field-wrapper  label="lbl_discussionLimit">
    <aui:select name="discussionLimit" label="">
    <% for (int i : Constants.DISCUSSION_LIMITS) {%>    
    <aui:option value="<%=i %>" label="<%= i %>"/>  
    <% } %>
    </aui:select>
  </aui:field-wrapper>

  <aui:field-wrapper>
    <aui:input placeholder='<%=LanguageUtil.get(locale, "lbl_deadlineConstraints") %>' name="deadlineConstraints" type="textarea" label=""></aui:input>  
  </aui:field-wrapper>

	<liferay-ui:asset-tags-error />

	<liferay-ui:asset-tags-selector  curTags='<%=tagNames %>' className="<%=Idea.class.getName()%>"
		classPK="<%=ideaId%>">
	</liferay-ui:asset-tags-selector>

  <aui:fieldset label="">
  <%
  request.setAttribute("liferay-ui:input-asset-links:className",Idea.class.getName());
  request.setAttribute("liferay-ui:input-asset-links:assetEntryId",String.valueOf(assetEntry == null ? 0 : assetEntry.getEntryId()));
  %>
  <%@ include file="/html/common/asset-links.jsp" %>
  </aui:fieldset>

	<aui:button-row cssClass="formbutton-row">
    <aui:button cssClass="formbutton-cancel" type="cancel" onClick="javascript: window.history.go(-1);"></aui:button>
    <aui:button cssClass="formbutton-primary" value='<%= LanguageUtil.get(locale, "btn_save_idea") %>' type="submit"></aui:button>
	</aui:button-row>
</aui:form>
<aui:script>
AUI().use('aui-form-validator',
  function(A) {
	 
	 var rules = {
	    <portlet:namespace/>ldesc: {
	        required: true
	    },
	    <portlet:namespace/>title: {
	        required: true
	    },
      <portlet:namespace/>shortDesc: {
          required: true,
          maxLength: 50
      }
	 };
	 new A.FormValidator(
		      {
		        boundingBox: '#<portlet:namespace/>idea',
		        rules: rules,
		        showAllMessages: true
		      }
		    );
		  }
		);
</aui:script>	 
	         