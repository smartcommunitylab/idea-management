<%@page import="java.util.ArrayList"%>
<%@ page import="it.smartcommunitylab.platform.idea.model.Idea"%>
<%@ page import="com.liferay.portal.kernel.util.ParamUtil"%>
<%@ page import="it.smartcommunitylab.platform.idea.service.IdeaLocalServiceUtil"%>
<%@page import="it.smartcommunitylab.platform.idea.model.Call"%>
<%@page import="it.smartcommunitylab.platform.idea.service.CallLocalServiceUtil"%>
<%@ page import="com.liferay.portlet.asset.model.AssetCategory" %>

<%@ include file="/html/common-init.jsp" %>

<%
	Idea idea = null;

        long ideaId = ParamUtil.getLong(request, "ideaId");

        if (ideaId > 0) {
                idea = IdeaLocalServiceUtil.getIdea(ideaId);
        }
        
        String categoryId = ParamUtil.getString(request, "categoryId");
        
        Long callId = ParamUtil.getLong(request, "callId");
        if(callId > 0) {
            AssetEntry assetEntry = AssetEntryLocalServiceUtil.getEntry(
            	      Call.class.getName(), callId);
            List<AssetCategory> categories = assetEntry.getCategories();   
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

<portlet:renderURL var="viewURL">
	<portlet:param name="mvcPath" value="/html/idea/view.jsp"></portlet:param>
</portlet:renderURL>

<portlet:actionURL
	name='<%=idea == null ? "addNewIdea" : "updateIdea"%>'
	var="addIdeaURL"></portlet:actionURL>

<aui:form action="<%=addIdeaURL%>" name="<portlet:namespace />idea">
<aui:model-context bean="<%= idea %>" model="<%= Idea.class %>" />
	
	<aui:fieldset>
		<aui:input name="title" label="lbl_title"></aui:input>
	</aui:fieldset>

  <aui:field-wrapper label="lbl_shortDesc">
    <aui:input name="shortDesc" type="textarea" label=""></aui:input>  
  </aui:field-wrapper>
  
	<aui:field-wrapper label="lbl_longDesc">
		<liferay-ui:input-editor name="longDesc"
			toolbarSet="liferay-article" initMethod="initEditor2" width="200" />
		<script type="text/javascript">
        function <portlet:namespace />initEditor2() { return document.getElementById('_<%=Constants.IDEA_PORTLET_ID%>_ldesc').value; }
    </script>
	</aui:field-wrapper>
	
	<aui:input name="ldesc" type="hidden" value='<%= idea != null ? idea.getLongDesc() : "" %>'></aui:input>
	<aui:input name="ideaId" type="hidden"></aui:input>
	<aui:input name="categoryId" type="hidden" value="<%= categoryId %>"></aui:input>
	<aui:input name="callId" type="hidden" value="<%= callId %>"></aui:input>

	<liferay-ui:asset-tags-error />

	<label>Tags</label>
	<liferay-ui:asset-tags-selector  curTags='<%=tagNames %>' className="<%=Idea.class.getName()%>"
		classPK="<%=ideaId%>">
	</liferay-ui:asset-tags-selector>



	<liferay-ui:panel defaultState="closed" extended="<%=false%>"
		id="ideaAssetLinksPanel" persistState="<%=true%>"
		title="related-assets">
		<aui:fieldset>
			<liferay-ui:input-asset-links className="<%=Idea.class.getName()%>"
				classPK="<%=ideaId%>" />
		</aui:fieldset>
	</liferay-ui:panel>

	<aui:button-row>
		<aui:button type="submit"></aui:button>
		<aui:button type="cancel" onClick="<%= viewURL.toString() %>"></aui:button>
	</aui:button-row>
</aui:form>