<%@page import="com.liferay.portal.kernel.util.HtmlUtil"%>
<%@ page import="it.smartcommunitylab.platform.idea.model.Idea"%>
<%@page import="com.liferay.portal.kernel.util.WebKeys"%>
<%@page import="com.liferay.portal.theme.ThemeDisplay"%>
<%@ page import="com.liferay.portal.kernel.util.ParamUtil"%>
<%@ page import="it.smartcommunitylab.platform.idea.model.Idea"%>
<%@ page
	import="com.liferay.portlet.asset.service.AssetEntryLocalServiceUtil"%>
<%@ page
	import="it.smartcommunitylab.platform.idea.service.IdeaLocalServiceUtil"%>
<%@ page import="com.liferay.portlet.asset.model.AssetEntry"%>
<%@ page import="com.liferay.portal.util.PortalUtil"%>
<%@ page
	import="com.liferay.portlet.asset.service.AssetTagLocalServiceUtil"%>
<%@ page import="java.util.List"%>
<%@ page import="com.liferay.portlet.asset.model.AssetTag"%>
<%@ page import="com.liferay.portal.kernel.util.ListUtil"%>
<%@ page import="com.liferay.portal.kernel.language.LanguageUtil"%>
<%@page import="it.smartcommunitylab.platform.idea.portlet.Constants"%>
<%@ include file="/html/common-init.jsp" %>

<%
	Idea idea = (Idea) request.getAttribute("idea");
  long ideaId = ParamUtil.getLong(renderRequest, "ideaId");
	if (idea == null) {
		  idea = IdeaLocalServiceUtil.getIdea(ideaId);		
	}
	idea = idea.toEscapedModel();
	
	AssetEntry assetEntry = AssetEntryLocalServiceUtil.getEntry(
			Idea.class.getName(), idea.getIdeaId());

	String currentURL = PortalUtil.getCurrentURL(request);

	PortalUtil.addPortletBreadcrumbEntry(request, idea.getTitle(),
			currentURL);

	PortalUtil.setPageSubtitle(idea.getTitle(), request);
	PortalUtil.setPageDescription(idea.getTitle(), request);

	List<AssetTag> assetTags = AssetTagLocalServiceUtil.getTags(
			Idea.class.getName(), idea.getIdeaId());
	PortalUtil.setPageKeywords(ListUtil.toString(assetTags, "name"),
			request);
%>


<dl>
	<c:if
		test="<%=idea.getShortDesc() != null
						|| !idea.getShortDesc().isEmpty()%>">
		<dt>Summary</dt>
		<dd><%=HtmlUtil.unescape(idea.getShortDesc())%></dd>
	</c:if>
	<dt>Content</dt>
	<dd><%=HtmlUtil.unescape(idea.getLongDesc())%></dd>
</dl>


<%-- <c:if test="<%=themeDisplay.isSignedIn()%>">

	<portlet:actionURL name="addComment" var="discussionURL">
		<!-- workaround to invoke liferary class that manage comment/discussion -->
		<portlet:param name="struts_action"
			value="/asset_publisher/edit_entry_discussion" />
	</portlet:actionURL>

	<liferay-ui:discussion className="<%=Idea.class.getName()%>" 
		classPK="<%=idea.getIdeaId()%>" formAction="<%=discussionURL%>"
		formName="fm2" ratingsEnabled="<%=true%>" redirect="<%=currentURL%>"
		subject="<%=idea.getTitle()%>" userId="<%=idea.getUserId()%>" />
</c:if> --%>

<liferay-ui:asset-links
	assetEntryId="<%=(assetEntry != null) ? assetEntry.getEntryId() : 0%>"
	className="<%=Idea.class.getName()%>" classPK="<%=idea.getIdeaId()%>" />