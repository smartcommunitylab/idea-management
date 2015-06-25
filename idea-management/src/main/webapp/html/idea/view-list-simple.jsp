<%@page import="java.text.DateFormat"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@ include file="/html/common-init.jsp" %>
<%@ page import="javax.portlet.PortletURL" %>
<%@ page import="javax.portlet.ActionRequest" %>
<%@ page import="com.liferay.portal.kernel.util.HttpUtil" %>
<%@ page import="com.liferay.portal.kernel.util.StringUtil" %>

<%@ page import="com.liferay.portlet.asset.service.AssetEntryServiceUtil" %>
<%@ page import="com.liferay.portlet.asset.model.AssetCategory" %>
<%@ page import="com.liferay.portlet.ratings.service.RatingsStatsLocalServiceUtil" %>
<%@ page import="com.liferay.portlet.ratings.model.RatingsStats" %>

<%@ page import="it.smartcommunitylab.platform.idea.service.IdeaLocalServiceUtil"%>
<%@ page import="it.smartcommunitylab.platform.idea.model.Idea"%>


<%
  List<Idea> results = (List)request.getAttribute("ideas");
  Integer currentPage = (Integer)request.getAttribute("_currentPage");
  Integer delta = (Integer)request.getAttribute("_delta");
  if (currentPage == null) currentPage = 1;
  if (delta == null) delta = results.size()+1;
  
  String baseUrl = (String) request.getAttribute("_baseUrl");
  PortletURL portletURL = renderResponse.createRenderURL();
	int offset = delta - results.size();
	String offsetClass = (offset > 0) ? "offset" + offset*2 : "";
	java.util.Map<String,String> CC = IdeaLocalServiceUtil.getCategoryColors(scopeGroupId);
%>
  <div class="idea-slider row-fluid">
    <span class="span1 text-right">
        <c:if test="<%= currentPage > 1%>">
            <% 
            portletURL.setParameter("cur", StringUtil.valueOf(currentPage - 1)); 
            String prevURL = baseUrl + "?" + HttpUtil.getQueryString(portletURL.toString());
            %>
            <a href="<%= HtmlUtil.escape(prevURL) %>">
                <i class="icon-arrow-left idea-slider-arrow"></i>
            </a>
        </c:if>
        <c:if test="<%= currentPage == 1%>">
            <i class="icon-arrow-left idea-slider-arrow"></i>
        </c:if>
    </span>

    <% for(Idea idea : results) {%>
        <portlet:renderURL var="viewIdea" windowState="maximized">
          <portlet:param name="mvcPath" value="/html/idea/asset/full_content.jsp" />
          <portlet:param name="ideaId" value="<%=String.valueOf(idea.getIdeaId()) %>" />
        </portlet:renderURL>
        
        <% 
        long classPK = idea.getIdeaId();
        AssetEntry curEntry = AssetEntryLocalServiceUtil.getEntry(Idea.class.getName(),classPK);
        List<AssetCategory> categories = curEntry.getCategories();   
        RatingsStats stat = RatingsStatsLocalServiceUtil.getStats(Idea.class.getName(),classPK);
        String color = categories.size() > 0 ? CC.get(""+categories.get(0).getCategoryId()) : "";
        String catTitle = categories.size() > 0 ? categories.get(0).getTitle(locale): "";
        %>
        <span class="span2">
            <a class="idea" href="<%=viewIdea.toString() %>">
                <div class="thumbnail" style="border-left-color: <%=color %>;">
                    <h6 class="idea-cat" style="color: <%=color %>;"><%=catTitle %></h6>
                    <h4><%=idea.getTitle() %></h4>
                    <p class="pull-left">
                    <%
                    DateFormat formatter = DateFormat.getDateInstance(DateFormat.SHORT, themeDisplay.getLocale());
                    out.print(formatter.format(idea.getCreateDate()));
                    %>
                    </p>
   		            <p class="pull-right"><%=stat.getAverageScore() %> (<%=stat.getTotalEntries() %>)</p>
                </div>
            </a>
        </span>
        
        
    <% } %>

    <span class="span1 <%= offsetClass%> text-left">
        <c:if test="<%=(results.size() >= delta) %>">
            <%
            portletURL.setParameter("cur", StringUtil.valueOf(currentPage + 1));
            String nextURL = baseUrl + "?" + HttpUtil.getQueryString(portletURL.toString());
            %>
            <a href="<%=HtmlUtil.escape(nextURL)%>">
                <i class="icon-arrow-right idea-slider-arrow"></i>
            </a>
        </c:if>
        <c:if test="<%=(results.size() < delta) %>">
            <i class="icon-arrow-right idea-slider-arrow"></i>
        </c:if>
    </span>
</div>   