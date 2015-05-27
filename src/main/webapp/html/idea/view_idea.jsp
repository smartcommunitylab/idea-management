<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>
<%@ taglib uri="http://alloy.liferay.com/tld/aui" prefix="aui" %>
<%@ taglib uri="http://liferay.com/tld/ui" prefix="liferay-ui" %>
<%@ taglib prefix="c" 
           uri="http://java.sun.com/jsp/jstl/core" %>

<%@page import="com.liferay.portal.kernel.util.WebKeys"%>
<%@page import="com.liferay.portal.theme.ThemeDisplay"%>
<%@ page import="com.liferay.portal.kernel.util.ParamUtil" %>
<%@ page import="it.smartcommunitylab.platform.idea.model.Idea" %>
<%@ page import="com.liferay.portlet.asset.service.AssetEntryLocalServiceUtil" %>
<%@ page import="it.smartcommunitylab.platform.idea.service.IdeaLocalServiceUtil" %>
<%@ page import="com.liferay.portlet.asset.model.AssetEntry" %>
<%@ page import="com.liferay.portal.util.PortalUtil" %>
<%@ page import="com.liferay.portlet.asset.service.AssetTagLocalServiceUtil" %>
<%@ page import="java.util.List" %>
<%@ page import="com.liferay.portlet.asset.model.AssetTag" %>
<%@ page import="com.liferay.portal.kernel.util.ListUtil" %>
<%@ page import="com.liferay.portal.kernel.language.LanguageUtil" %>

<portlet:defineObjects />

<portlet:renderURL var="viewURL">
        <portlet:param name="mvcPath" value="/html/ideaadmin/view.jsp"></portlet:param>
</portlet:renderURL>

<liferay-ui:header backURL="<%= viewURL %>" title="idea" />

<%
		ThemeDisplay themeDisplay = (ThemeDisplay)request.getAttribute(
				WebKeys.THEME_DISPLAY);
        long ideaId = ParamUtil.getLong(renderRequest, "ideaId");
        Idea idea = IdeaLocalServiceUtil.getIdea(ideaId);
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
        <dt>Name</dt>
        <dd><%= idea.getTitle() %></dd>
</dl>

<c:if test="<%= themeDisplay.isSignedIn() %>">
        <liferay-ui:panel-container extended="<%= false %>"
                id="ideaCollaborationPanelContainer" persistState="<%= true %>">
                <liferay-ui:panel collapsible="<%= true %>" extended="<%= true %>"
                id="ideaCollaborationPanel" persistState="<%= true %>"
                title='<%= LanguageUtil.get(pageContext, "collaboration") %>'>
                        <liferay-ui:ratings className="<%= Idea.class.getName() %>"
                                classPK="<%= idea.getIdeaId() %>" type="stars" />

                        <br />

                        <portlet:actionURL name="invokeTaglibDiscussion" var="discussionURL" />

                        <liferay-ui:discussion className="<%= Idea.class.getName() %>"
                    classPK="<%= idea.getIdeaId() %>"
                    formAction="<%= discussionURL %>" formName="fm2"
                    ratingsEnabled="<%= true %>" redirect="<%= currentURL %>"
                    subject="<%= idea.getTitle() %>"
                    userId="<%= idea.getUserId() %>" />

                </liferay-ui:panel>
        </liferay-ui:panel-container>
</c:if>

<liferay-ui:asset-links
        assetEntryId="<%= (assetEntry != null) ? assetEntry.getEntryId() : 0 %>"
        className="<%= Idea.class.getName() %>"
        classPK="<%= idea.getIdeaId() %>" />