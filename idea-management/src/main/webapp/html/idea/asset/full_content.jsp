<%@ page import="it.smartcommunitylab.platform.idea.model.Idea"%>
<%@ page import="it.smartcommunitylab.platform.idea.model.Idea"%>
<%@ page import="it.smartcommunitylab.platform.idea.service.IdeaLocalServiceUtil"%>
<%@ page import="com.liferay.portal.kernel.util.DateFormatFactoryUtil" %>
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
<div class="row-fluid">
  <span class="span8 idea-view-title"><%=HtmlUtil.unescape(idea.getTitle())%></span>
  <span class="span4 idea-creator text-right">
    <liferay-ui:message key="lbl_createdWhenWho" arguments="<%=new String[]{DateFormatFactoryUtil.getDate(locale).format(idea.getCreateDate()),user.getScreenName()}%>" />  
	  <c:if test="<%= themeDisplay.getUser().getUserUuid().equals(idea.getUserUuid())%>">
	    <portlet:renderURL var="editIdea" windowState="maximized">
	      <portlet:param name="mvcPath" value="/html/idea/edit_idea.jsp" />
	      <portlet:param name="ideaId" value="<%=String.valueOf(idea.getIdeaId()) %>" />
	    </portlet:renderURL>
	    <a href="<%=editIdea.toString()%>"><i class="icon-pencil"></i></a>
	  </c:if>
  </span>
</div>

<div class="row-fluid">
<%--   <portlet:renderURL var="bookmarkURL" windowState="<%= javax.portlet.WindowState.NORMAL.toString() %>"> --%>
<%--     <portlet:param name="struts_action" value="/idea/view_entry" /> --%>
<%--     <portlet:param name="urlTitle" value="<%= assetEntry.getUrlTitle() %>" /> --%>
<%--   </portlet:renderURL> --%>
  <div class="span12 pull-right"><liferay-ui:social-bookmarks target="_blank" title='<%= LanguageUtil.get(locale, "lbl_share") %>' url="<%= PortalUtil.getCanonicalURL(currentURL.toString(), themeDisplay, layout) %>"></liferay-ui:social-bookmarks></div>
</div>

<liferay-ui:panel-container accordion="true" extended="true">
	<liferay-ui:panel collapsible="true" id="info" title='<%= LanguageUtil.get(locale, "lbl_info") %>'>
	  <div><%=HtmlUtil.unescape(idea.getLongDesc())%></div>

		<liferay-ui:asset-links
		  assetEntryId="<%=(assetEntry != null) ? assetEntry.getEntryId() : 0%>"
		  className="<%=Idea.class.getName()%>" classPK="<%=idea.getIdeaId()%>" />
  </liferay-ui:panel>
  <liferay-ui:panel collapsible="true" id="discussion" title='<%= LanguageUtil.get(locale, "lbl_discussion") %>'>
		<c:if test="<%=themeDisplay.isSignedIn()%>">
		
		  <div class="row-fluid">
			  <liferay-ui:ratings
			    className="<%= Idea.class.getName() %>"
			    classPK="<%= idea.getIdeaId() %>"
	      />
      </div>

      <div class="row-fluid">
	      <portlet:actionURL name="addComment" var="discussionURL">
	        <!-- workaround to invoke liferary class that manage comment/discussion -->
	        <portlet:param name="struts_action"
	          value="/asset_publisher/edit_entry_discussion" />
	      </portlet:actionURL>
      </div>		
		
		  <liferay-ui:discussion className="<%=Idea.class.getName()%>" 
		    classPK="<%=idea.getIdeaId()%>" formAction="<%=discussionURL%>"
		    formName="fm2" ratingsEnabled="<%=true%>" redirect="<%=currentURL%>"
		    subject="<%=idea.getTitle()%>" userId="<%=idea.getUserId()%>" />
		</c:if>
  </liferay-ui:panel>
  <liferay-ui:panel collapsible="true" id="participants" title='<%= LanguageUtil.get(locale, "lbl_participants") %>'>
  </liferay-ui:panel>
  <liferay-ui:panel collapsible="true" id="state" title='<%= LanguageUtil.get(locale, "lbl_state") %>'>
  </liferay-ui:panel>
</liferay-ui:panel-container>
