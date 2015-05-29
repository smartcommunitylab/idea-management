<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet"%>
<%@ taglib uri="http://alloy.liferay.com/tld/aui" prefix="aui"%>
<%@ taglib uri="http://liferay.com/tld/ui" prefix="liferay-ui"%>

<%@ page import="it.smartcommunitylab.platform.idea.model.Idea"%>
<%@ page import="com.liferay.portal.kernel.util.ParamUtil"%>
<%@ page
	import="it.smartcommunitylab.platform.idea.service.IdeaLocalServiceUtil"%>
<%@ page import="com.liferay.portal.kernel.util.WebKeys"%>
<%@ page import="com.liferay.portal.theme.ThemeDisplay"%>

<portlet:defineObjects />

<%
	Idea idea = null;

        long ideaId = ParamUtil.getLong(request, "ideaId");

        if (ideaId > 0) {
                idea = IdeaLocalServiceUtil.getIdea(ideaId);
        }
        
%>

<%
	// workaround for assert-tag-selector nullpointerexception
ThemeDisplay themeDisplay = (ThemeDisplay) renderRequest.getAttribute(WebKeys.THEME_DISPLAY);
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
		<liferay-ui:input-editor name="shortDesc"
			toolbarSet="liferay-article" initMethod="initEditor1" width="200" />
		<script type="text/javascript">
        function <portlet:namespace />initEditor1() { return document.getElementById('_ideamanagement_WAR_ideamanagement_sdesc').value; }
    </script>
	</aui:field-wrapper>

	<aui:field-wrapper label="lbl_longDesc">
		<liferay-ui:input-editor name="longDesc"
			toolbarSet="liferay-article" initMethod="initEditor2" width="200" />
		<script type="text/javascript">
        function <portlet:namespace />initEditor2() { return document.getElementById('_ideamanagement_WAR_ideamanagement_ldesc').value; }
    </script>
	</aui:field-wrapper>
	
	<aui:input name="ldesc" type="hidden" value='<%= idea != null ? idea.getLongDesc() : "" %>'></aui:input>
	<aui:input name="sdesc" type="hidden" value='<%= idea != null ? idea.getShortDesc() : "" %>'></aui:input>
	<aui:input name="ideaId" type="hidden"></aui:input>
	<aui:input name="redirect" type="hidden" value="<%= viewURL.toString() %>"></aui:input>

	<liferay-ui:asset-categories-error />
	<liferay-ui:asset-tags-error />

	<liferay-ui:asset-categories-selector
		className="<%=Idea.class.getName()%>" classPK="<%=ideaId%>">

	</liferay-ui:asset-categories-selector>


	<label>Tags</label>
	<liferay-ui:asset-tags-selector className="<%=Idea.class.getName()%>"
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