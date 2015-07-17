<%@page import="it.smartcommunitylab.platform.idea.model.Idea"%>
<%@page import="com.liferay.portal.kernel.workflow.WorkflowConstants"%>
<%@page import="com.liferay.portlet.messageboards.service.MBMessageLocalServiceUtil"%>
<%@ page import="javax.portlet.PortletURL" %>
<%@page import="java.text.NumberFormat"%>
<%@ page import="it.smartcommunitylab.platform.idea.service.IdeaLocalServiceUtil"%>
<%@ page import="com.liferay.portlet.ratings.service.RatingsStatsLocalServiceUtil" %>
<%@ page import="com.liferay.portlet.ratings.model.RatingsStats" %>
<%@ page import="com.liferay.portal.kernel.util.HttpUtil" %>
<%@ page import="com.liferay.portal.kernel.util.StringUtil" %>
<%@ page import="javax.portlet.WindowState" %>
<%@ page import="it.smartcommunitylab.platform.idea.model.Call"%>
<%@ page import="it.smartcommunitylab.platform.idea.service.CallLocalServiceUtil"%>
<%@ page import="com.liferay.portlet.asset.model.AssetCategory" %>
<%@ page import="it.smartcommunitylab.platform.idea.portlet.Utils"%>

<%@ include file="/html/common-init.jsp" %>

<%
  List<Idea> results = (List)request.getAttribute("ideas");
  Integer currentPage = (Integer)request.getAttribute("_currentPage");
  Integer delta = (Integer)request.getAttribute("_delta");
  if (currentPage == null) currentPage = 1;
  if (delta == null) delta = results.size()+1;
  
  String baseUrl = (String) request.getAttribute("_baseUrl");
  PortletURL portletURL = renderResponse.createRenderURL();
  portletURL.setParameter("mvcPath", "/html/idea/view.jsp");

  int offset = delta - results.size();
  String offsetClass = (offset > 0) ? "offset" + offset*2 : "";
  java.util.Map<String,String> CC = IdeaLocalServiceUtil.getCategoryColors(scopeGroupId);
  
  NumberFormat numberFormat = NumberFormat.getInstance();
  numberFormat.setMaximumFractionDigits(1);
  numberFormat.setMinimumFractionDigits(0);

  Long categoryId = ParamUtil.getLong(renderRequest,"categoryId");//(Long) request.getAttribute("categoryId");
  Long callId = ParamUtil.getLong(renderRequest,"callId");
//   portletURL.setParameter("categoryId", String.valueOf(categoryId));
//   portletURL.setParameter("callId", String.valueOf(callId));
  Map<String,Object> params = new HashMap<String,Object>();
  params.put("categoryId", categoryId);
  params.put("callId", callId);
  params.put("mvcPath", "/html/idea/view.jsp");
  
  String listType = GetterUtil.getString(portletPreferences.getValue("listType", Constants.PREF_LISTTYPE_RECENT));
	String color = "#DDD";
	if (callId > 0 && categoryId == 0) {
	    Call call = CallLocalServiceUtil.getCall(callId);
        AssetEntry assetEntry = AssetEntryLocalServiceUtil.getEntry(
                Call.class.getName(), call.getCallId());
          List<AssetCategory> categories = assetEntry.getCategories();
          AssetCategory category = null;
          if (categories != null && categories.size() > 0) {
            category = categories.get(0);
            categoryId = category.getCategoryId();
          }
	}
	if (categoryId > 0) {
	     color = CC.get(""+categoryId);
	}  
	PortletURL redirectURL = renderResponse.createRenderURL();
	redirectURL.setParameter("ideaId", "0");
	redirectURL.setWindowState(WindowState.NORMAL);
%>


<div class="row-fluid idea-cards">
  <div class="span12">
    <%
    int rowCount = 0;
    for(Idea idea : results) {
    %>
    <%
      if ((rowCount % 3) == 0) {
    %>
    <div class="row-fluid">
    <%
      }
    %>
        <portlet:renderURL var="viewIdea" windowState="maximized">
          <portlet:param name="ideaId" value="<%=String.valueOf(idea.getIdeaId()) %>" />
          <portlet:param name="mvcPath" value="/html/idea/asset/full_content.jsp" />
<%--           <portlet:param name="redirect" value="<%=redirectURL.toString() %>" /> --%>
        </portlet:renderURL>
        
        <% 
        long classPK = idea.getIdeaId();
        AssetEntry curEntry = AssetEntryLocalServiceUtil.getEntry(Idea.class.getName(),classPK);
        RatingsStats stat = RatingsStatsLocalServiceUtil.getStats(Idea.class.getName(),classPK);
        String scoreString = numberFormat.format(stat.getAverageScore());
        %>
        <div class="span4">
                <div onClick="javascript:window.location='<%=viewIdea.toString() %>';" class="idea-card" style="border-color: <%=color %>;">
                  <div class="idea-card-header">
                    <div class="span6">
	                    <c:if test="<%=idea.getCallId() > 0 %>"><span class="idea-card-call-label"><liferay-ui:message key="lbl_call"/></span> </c:if>
                    </div>
                    <div class="span6">
	                    <c:if test="<%= false %>">
	                      <portlet:actionURL var="deleteURL" name="deleteEntry">
	                        <portlet:param name="entryId" value="<%=String.valueOf(idea.getIdeaId()) %>" />
	                        <portlet:param name="categoryId" value="<%=String.valueOf(categoryId) %>" />
	                        <portlet:param name="callId" value="<%=String.valueOf(callId) %>" />
	                      </portlet:actionURL>
	                      <liferay-ui:icon-delete message="lbl_delete" url="<%=deleteURL.toString()%>"/>
	                    </c:if>
                      <span class="idea-card-date">
                        <%=dateFormatter.format(idea.getCreateDate()) %>
                      </span>
                    </div>
                  </div>
                  <h4><%=idea.getTitle() %></h4>
                  <div class="idea-card-abstract">
                  <%=idea.getShortDesc() %>
                  </div>  
                  <div class="idea-card-footer">
                      <div class="span6 idea-rating">
                            <%
                            for (int i = 1; i <= 5; i++) {
                            %>
                              <i class="<%= (i <= stat.getAverageScore()) ? "icon-star" : "icon-star-empty" %>"></i>
                            <%
                            }
                            %>
                      </div>
                      <div class="span6">
                      <span class="idea-card-comments"><%=String.valueOf(MBMessageLocalServiceUtil.getDiscussionMessagesCount(Idea.class.getName(), idea.getIdeaId(), WorkflowConstants.STATUS_APPROVED)) %></span>
                      </div>
                  </div>
                </div>
        </div>
        
    <%
      if ((rowCount++ % 3) == 2) {
    %>
    </div>
    <%
      }
    %>
    <% } %>
		<c:if test="<%= currentPage == 1 && results.size() == 0%>">
		  <div class="row-fluid">
		  <span class="empty-results"><liferay-ui:message key="lbl_noresults"/></span>
		  </div>
		</c:if>
	</div>	
</div>
<div class="idea-paging row-fluid">
  <div class="span6">
    <c:if test="<%=currentPage > 1 %>">
    <% 
//     portletURL.setParameter("cur", StringUtil.valueOf(currentPage - 1)); 
//     String prevURL = baseUrl + "?" + HttpUtil.getQueryString(portletURL.toString());
       params.put("cur", currentPage - 1);
       String prevURL = Utils.generateRenderURL(renderResponse, baseUrl, params);
    %>
    <a class="idea-paging-prev" href="<%= HtmlUtil.escape(prevURL) %>"><liferay-ui:message key="prev_page"/></a>
    </c:if>
  </div>
  <div class="span6">
   <c:if test="<%=(results.size() >= delta) %>">
       <%
//        portletURL.setParameter("cur", StringUtil.valueOf(currentPage + 1));
//        String nextURL = baseUrl + "?" + HttpUtil.getQueryString(portletURL.toString());
		      params.put("cur", currentPage + 1);
		      String nextURL = Utils.generateRenderURL(renderResponse, baseUrl, params);
       %>
       <a class="idea-paging-next" href="<%=HtmlUtil.escape(nextURL)%>"><liferay-ui:message key="next_page"/></a>
   </c:if>  
  </div>
</div>
