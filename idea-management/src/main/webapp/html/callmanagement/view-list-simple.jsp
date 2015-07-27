<%@page import="java.text.DateFormat"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.text.NumberFormat"%>
<%@ page import="javax.portlet.PortletURL" %>
<%@ page import="javax.portlet.ActionRequest" %>
<%@ page import="com.liferay.portal.kernel.util.HttpUtil" %>
<%@ page import="com.liferay.portal.kernel.util.StringUtil" %>

<%@ page import="com.liferay.portlet.asset.service.AssetEntryServiceUtil" %>
<%@ page import="com.liferay.portlet.asset.model.AssetCategory" %>
<%@ page import="com.liferay.portlet.ratings.service.RatingsStatsLocalServiceUtil" %>
<%@ page import="com.liferay.portlet.ratings.model.RatingsStats" %>

<%@ page import="it.smartcommunitylab.platform.idea.service.IdeaLocalServiceUtil"%>
<%@page import="it.smartcommunitylab.platform.idea.model.Call"%>
<%@ page import="it.smartcommunitylab.platform.idea.portlet.Utils"%>

<%@ include file="/html/common-init.jsp" %>

<%
List<Call> list = (List<Call>)request.getAttribute("callList");
java.util.Map<String,String> CC = IdeaLocalServiceUtil.getCategoryColors(scopeGroupId);
  
String baseUrl = Utils.getBaseURL(request);
Map<String,Object> params = new HashMap<String,Object>();
params.put("mvcPath", "/html/callmanagement/add_call.jsp");

Integer currentPage = (Integer)request.getAttribute("_currentPage");
Integer delta = (Integer)request.getAttribute("_delta");
if (currentPage == null) currentPage = 1;
if (delta == null) delta = list.size()+1;
    
int offset = delta - list.size();
String offsetClass = (offset > 0) ? "offset" + offset*2 : "";
	
NumberFormat numberFormat = NumberFormat.getInstance();
numberFormat.setMaximumFractionDigits(1);
numberFormat.setMinimumFractionDigits(0);

%>
  <div class="idea-slider row-fluid">
    <span class="span1 text-right">
        <c:if test="<%= currentPage > 1%>">
            <% 
            params.put("cur", currentPage - 1);
            String prevURL = Utils.generateRenderURL(renderResponse, baseUrl, params);
            %>
            <a href="<%= HtmlUtil.escape(prevURL) %>">
                <i class="icon-arrow-left idea-slider-arrow"></i>
            </a>
        </c:if>
        <c:if test="<%= currentPage == 1%>">
            <i class="icon-arrow-left idea-slider-arrow"></i>
        </c:if>
    </span>

    <% for(Call call : list) {%>
        <portlet:renderURL var="viewCall">
          <portlet:param name="callId" value="<%=String.valueOf(call.getCallId()) %>" />
          <portlet:param name="mvcPath" value="/html/callmanagement/asset/full_content.jsp" />
        </portlet:renderURL>
        <% 
        long classPK = call.getCallId();
        AssetEntry curEntry = AssetEntryLocalServiceUtil.getEntry(Call.class.getName(),classPK);
        List<AssetCategory> categories = Utils.getOrderedCategories(call.getCategoryIds(), curEntry);   
        String color = categories.size() > 0 ? CC.get(""+categories.get(0).getCategoryId()): ""; 

//         categories.size() > 0 ? categories.get(0).getTitle(locale): "";
        int countIdeaByCall = IdeaLocalServiceUtil.getIdeasByCall(call.getCallId(), -1, -1).size();
        %>
        <span class="span2">
                <div onClick="javascript:window.location = '<%=viewCall.toString() %>';" class="thumbnail" style="border-left-color: <%=color %>;">
                       <div class="idea-cat">
                      <% for (int i = 0; i < categories.size();i++) {
                          color = CC.get(""+categories.get(i).getCategoryId());
                          String catTitle = categories.get(i).getTitle(locale);
                      %>
                         <span  style="color: <%=color %>;"><%=catTitle %></span>
                      <% } %>
                      <c:if test="<%= Utils.callDeleteEnabled(call, renderRequest) %>">
		                    <portlet:actionURL var="deleteURL" name="deleteEntry">
		                      <portlet:param name="entryId" value="<%=String.valueOf(call.getCallId()) %>" />
		                    </portlet:actionURL>
		                    <liferay-ui:icon-delete message="lbl_delete" url="<%=deleteURL.toString()%>"/>
                      </c:if>
                    </div>
                    <h4><%=call.getTitle() %></h4>
                    <div class="thumbnail-bottom">
	                    <div class="pull-left">
			                  <c:if test='<%=call.getDeadline() != null %>'>
			                  <liferay-ui:message key="lbl_call_card_deadline" arguments="<%=dateFormatter.format(call.getDeadline()) %>"/>
			                  </c:if>
	                    </div>
	   		              <div class="pull-right">
	   		                <span class="call-card-ideas-simple"><%=countIdeaByCall %></span>
	   		              </div>
   		            </div>
                </div>
        </span>
        
        
    <% } %>

    <span class="span1 <%= offsetClass%> text-left">
        <c:if test="<%=(list.size() >= delta) %>">
            <%
            params.put("cur", currentPage + 1);
            String nextURL = Utils.generateRenderURL(renderResponse, baseUrl, params);
            %>
            <a href="<%=HtmlUtil.escape(nextURL)%>">
                <i class="icon-arrow-right idea-slider-arrow"></i>
            </a>
        </c:if>
        <c:if test="<%=(list.size() < delta) %>">
            <i class="icon-arrow-right idea-slider-arrow"></i>
        </c:if>
    </span>
</div>   