<%@ page import="it.smartcommunitylab.platform.idea.model.Idea"%>
<%@ page import="it.smartcommunitylab.platform.idea.model.Idea"%>
<%@ page import="it.smartcommunitylab.platform.idea.service.IdeaLocalServiceUtil"%>

<%@ page import="com.liferay.portal.kernel.util.DateFormatFactoryUtil" %>
<%@ page import="com.liferay.portal.service.UserLocalServiceUtil" %>
<%@ page import="com.liferay.portal.model.User" %>

<%@ include file="/html/common-init.jsp" %>

<%
	Idea idea = (Idea) request.getAttribute("idea");
  long ideaId = ParamUtil.getLong(renderRequest, "ideaId");
	if (idea == null) {
		  idea = IdeaLocalServiceUtil.getIdea(ideaId);		
	}
	idea = idea.toEscapedModel();
	
	User owner = UserLocalServiceUtil.getUser(idea.getUserId());
	
	AssetEntry assetEntry = AssetEntryLocalServiceUtil.getEntry(
			Idea.class.getName(), idea.getIdeaId());

	String currentURL = PortalUtil.getCurrentURL(request);

	PortalUtil.addPortletBreadcrumbEntry(request, idea.getTitle(),
			currentURL);

	PortalUtil.setPageSubtitle(idea.getTitle(), request);
	PortalUtil.setPageDescription(idea.getTitle(), request);

	List<AssetTag> assetTags = AssetTagLocalServiceUtil.getTags(
			Idea.class.getName(), idea.getIdeaId());
	
	List<AssetTag> categoryTags = IdeaLocalServiceUtil.getCategoryTags(assetEntry.getCategoryIds(), assetEntry.getGroupId());  
	java.util.Set<String> categoryTagsSet = new java.util.HashSet<String>();
	java.util.Set<String> parentTagSet = new java.util.HashSet<String>();
	java.util.Set<String> ownTagSet = new java.util.HashSet<String>();
	for (AssetTag tag : categoryTags) {
		categoryTagsSet.add(tag.getName());
	}
  for (AssetTag tag : assetTags) {
    if (categoryTagsSet.contains(tag.getName())) parentTagSet.add(tag.getName());
    else ownTagSet.add(tag.getName());
  }
	
  List<User> users = UserLocalServiceUtil.getGroupUsers(idea.getUserGroupId());
  boolean isOwner = owner.getUserId() == user.getUserId();
  boolean participates = false;
  for (User u : users) {
	  if (u.getUserId() == user.getUserId()) participates = true;
  }
  
	PortalUtil.setPageKeywords(ListUtil.toString(assetTags, "name"),
			request);
%>
<div class="row-fluid">
  <span class="span8 idea-view-title"><%=HtmlUtil.unescape(idea.getTitle())%></span>
  <span class="span4 idea-creator text-right">
    <liferay-ui:message key="lbl_createdWhenWho" arguments="<%=new String[]{DateFormatFactoryUtil.getDate(locale).format(idea.getCreateDate()),owner.getScreenName()}%>" />  
	  
	  <c:if test="<%= themeDisplay.getUser().getUserUuid().equals(idea.getUserUuid())%>">
	    <portlet:renderURL var="editIdea" windowState="maximized">
	      <portlet:param name="mvcPath" value="/html/idea/edit_idea.jsp" />
	      <portlet:param name="ideaId" value="<%=String.valueOf(idea.getIdeaId()) %>" />
	    </portlet:renderURL>
      <portlet:actionURL var="deleteURL" name="deleteEntry">
        <portlet:param name="entryId" value="<%=String.valueOf(idea.getIdeaId()) %>" />
      </portlet:actionURL>
	    <a href="<%=editIdea.toString()%>"><i class="icon-pencil"></i></a>
      <liferay-ui:icon-delete message="lbl_delete" url="<%=deleteURL.toString()%>"/>
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

    <c:if test="<%=parentTagSet.size() > 0 %>">
    <div class="row-fluid">
      <liferay-ui:message key="lbl_tags"/>
        <% for (String tag: parentTagSet) {%>
        <span class="badge"><%=tag %></span>
        <%} %>
    </div>
    </c:if>
    <c:if test="<%=ownTagSet.size() > 0 %>">
    <div class="row-fluid">
      <liferay-ui:message key="lbl_usertags"/>
        <% for (String tag: ownTagSet) {%>
        <span class="badge"><%=tag %></span>
        <%} %>
    </div>
    </c:if>
		<liferay-ui:asset-links
		  assetEntryId="<%=(assetEntry != null) ? assetEntry.getEntryId() : 0%>"
		  className="<%=Idea.class.getName()%>" classPK="<%=idea.getIdeaId()%>" />
  </liferay-ui:panel>
  <c:if test="<%=themeDisplay.isSignedIn()%>">
  <liferay-ui:panel collapsible="true" id="discussion" title='<%= LanguageUtil.get(locale, "lbl_discussion") %>'>
      <div class="row-fluid text-center">
        <div class="span6">
          <liferay-ui:message key="lbl_rate"/>
        </div>
        <div class="span6">
          <liferay-ui:message key="lbl_participate"/>
        </div>
      </div>
		  <div class="row-fluid">
		    <div class="span6">
				  <liferay-ui:ratings
				    className="<%= Idea.class.getName() %>"
				    classPK="<%= idea.getIdeaId() %>"
		      />
        </div>
	      <div class="span6">
		      <portlet:actionURL var="toggleURL" name="toggleUserParticipation">
            <portlet:param name="mvcPath" value="/html/idea/asset/full_content.jsp" />
		        <portlet:param name="ideaId" value="<%=String.valueOf(idea.getIdeaId()) %>" />
            <portlet:param name="userId" value="<%=String.valueOf(user.getUserId()) %>" />
		      </portlet:actionURL>
  	      <a class="btn <%=participates ? "btn-primary" : "" %>" href="<%=toggleURL.toString() %>"><i class="icon-hand-up"></i></a>
  	      <i class="icon-user"></i><%=users.size() %>
	      </div>
	      
      </div>

      <hr/>
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
  </liferay-ui:panel>
  </c:if>
  <liferay-ui:panel collapsible="true" id="participants" title='<%= LanguageUtil.get(locale, "lbl_participants") %>'>
        <% for (User participant: users) {%>
        <liferay-ui:user-display userId="<%=participant.getUserId() %>" userName="<%=participant.getScreenName() %>" displayStyle="0"></liferay-ui:user-display>
        <c:if test="<%=participant.getUserId() == owner.getUserId() %>"><liferay-ui:message key="lbl_author"/></c:if>
        <%} %>
  </liferay-ui:panel>
  <liferay-ui:panel collapsible="true" id="state" title='<%= LanguageUtil.get(locale, "lbl_state") %>'>
  </liferay-ui:panel>
</liferay-ui:panel-container>
