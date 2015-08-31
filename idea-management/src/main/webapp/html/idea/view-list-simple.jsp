<%@page import="java.text.DateFormat"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.text.NumberFormat"%>
<%@ page import="javax.portlet.PortletURL"%>
<%@ page import="javax.portlet.ActionRequest"%>
<%@ page import="com.liferay.portal.kernel.util.HttpUtil"%>
<%@ page import="com.liferay.portal.kernel.util.StringUtil"%>

<%@ page
	import="com.liferay.portlet.asset.service.AssetEntryServiceUtil"%>
<%@ page import="com.liferay.portlet.asset.model.AssetCategory"%>
<%@ page
	import="com.liferay.portlet.ratings.service.RatingsStatsLocalServiceUtil"%>
<%@ page import="com.liferay.portlet.ratings.model.RatingsStats"%>

<%@ page
	import="it.smartcommunitylab.platform.idea.service.IdeaLocalServiceUtil"%>
<%@ page import="it.smartcommunitylab.platform.idea.model.Idea"%>
<%@ page import="it.smartcommunitylab.platform.idea.portlet.Utils"%>

<%@ include file="/html/common-init.jsp"%>

<%
	List<Idea> results = (List)request.getAttribute("ideas");
  Integer currentPage = (Integer)request.getAttribute("_currentPage");
  Integer delta = (Integer)request.getAttribute("_delta");
  if (currentPage == null) currentPage = 1;
  if (delta == null) delta = results.size()+1;
  
  String baseUrl = (String) request.getAttribute("_baseUrl");
//   PortletURL portletURL = renderResponse.createRenderURL();
  Long categoryId = ParamUtil.getLong(renderRequest,"categoryId");
  Long callId = ParamUtil.getLong(renderRequest,"callId");
  Map<String,Object> params = new HashMap<String,Object>();
  params.put("categoryId", categoryId);
  params.put("callId", callId);
  params.put("mvcPath", "/html/idea/view.jsp");

//   portletURL.setParameter("categoryId", String.valueOf(categoryId));
//   portletURL.setParameter("callId", String.valueOf(callId));
//   portletURL.setParameter("mvcPath", "/html/idea/view.jsp");
  
	int offset = delta - results.size();
	String offsetClass = (offset > 0) ? "offset" + offset*2 : "";
	java.util.Map<String,String> CC = IdeaLocalServiceUtil.getCategoryColors(scopeGroupId);
	
	NumberFormat numberFormat = NumberFormat.getInstance();
	numberFormat.setMaximumFractionDigits(1);
	numberFormat.setMinimumFractionDigits(0);

	String listType = GetterUtil.getString(portletPreferences.getValue("listType", Constants.PREF_LISTTYPE_RECENT));
%>
<div class="row-fluid">
	<span class="idea-slider-title offset1 span10"> <liferay-ui:message
			key='<%="lbl_listTypeTitle_"+listType%>' />
	</span>
</div>

<portlet:resourceURL var="ajaxPrevUrl">
	<portlet:param name="cur"
		value="<%=Integer.toString(currentPage - 1)%>" />
	<portlet:param name="categoryId"
		value='<%=ParamUtil.getString(renderRequest,"categoryId")%>' />
	<portlet:param name="callId"
		value='<%=ParamUtil.getString(renderRequest,"callId")%>' />
</portlet:resourceURL>

<portlet:resourceURL var="ajaxNextUrl">
	<portlet:param name="cur"
		value="<%=Integer.toString(currentPage + 1)%>" />
	<portlet:param name="categoryId"
		value='<%=ParamUtil.getString(renderRequest,"categorId")%>' />
	<portlet:param name="callId"
		value='<%=ParamUtil.getString(renderRequest,"callId")%>' />
</portlet:resourceURL>

<div id='result' class="idea-slider row-fluid">
	<span class="span1 text-right"> <c:if
			test="<%=currentPage > 1%>">
			<%
				//             portletURL.setParameter("cur", StringUtil.valueOf(currentPage - 1)); 
			//             String prevURL = baseUrl + "?" + HttpUtil.getQueryString(portletURL.toString());
			           // params.put("cur", currentPage - 1);
			            //String prevURL = Utils.generateRenderURL(renderResponse, baseUrl, params);
			%>
			<a onclick="paginateIdeas('prev');"> <i
				class="icon-arrow-left idea-slider-arrow"></i>
			</a>
		</c:if>
	</span>

	<aui:script>
		Liferay.provide(window, 'paginateIdeas', function(direction) {
			var instance = this;
			var A = AUI();
			var url = '<%= ajaxPrevUrl %>';
			if (direction === 'next') {
				url = '<%= ajaxNextUrl %>';
			}
            
			A.io.request(url, {
				dataType : 'json',
				on : {
					success : function() {
						var data = this.get('responseData');
                        // transform data
                        $.each(data,function(i,v) {
                            if(v.creationTs) {
                                v.formattedCreation = new Date(v.creationTs).toLocaleFormat('%d/%m/%Y');
                            }
                            v.stars = [0,0,0,0,0];
                            for(var i = 1 ; i <= 5; i++) {
                                v.stars[i-1] = i <= v.avgRating;
                            }
                        });

						var nodeResult = A.one('#result');
						nodeResult.empty();
						var handlebars = new A.Template(A.Handlebars);
						var source = A.one("#idea-templ").html();
						var compiled = handlebars.compile(source);
						var html = compiled({
							result : data
						});
						nodeResult.html(html);
                        
                        }
                    }
                });
            }, [ 'aui-io', 'aui-node', 'template-base', 'handlebars' ]);
	</aui:script>
	


	
<script id="idea-templ" type="text/x-handlebars">
    <span class="span1 text-right">
    </span>
    {{#each result}}
     <span class="span2" id="result">
   <div id="resContainer" onClick="javascript:window.location = {{this.detailURL}};" class="thumbnail" style="border-left-color: '{{this.categoryColor}}';">
	<div class="idea-cat">
				<span style="color: {{this.categoryColor}};">{{this.category}}</span>
				{{#if this.deleteURL}}
					<a id="delete-link-{{@index}}" href="{{this.deleteURL}}" class="pull-right"><span class="delete-icon" style="height:16px;width:16px;display:inline-block;"></span></a>
				{{/if}}			
			</div>
                    <h4>{{this.title}}</h4>
	<div class="thumbnail-bottom">
				<div class="pull-left">
                {{formattedCreation}}
				</div>
				<div class="idea-rating pull-right">
					{{#each this.stars}}
                        {{#if this}}
                            <i class="icon-star"></i>
                        {{else}}
                            <i class="icon-star-empty"></i>
                        {{/if}}
                    {{/each}}
                </div>
			</div>
    </div>
    </span>
    {{/each}}
</script>

	<%
		for (Idea idea : results) {
	%>
	<portlet:renderURL var="viewIdea">
		<portlet:param name="mvcPath"
			value="/html/idea/asset/full_content.jsp" />
		<portlet:param name="ideaId"
			value="<%=String.valueOf(idea.getIdeaId())%>" />
	</portlet:renderURL>

	<%
		long classPK = idea.getIdeaId();
			AssetEntry curEntry = AssetEntryLocalServiceUtil.getEntry(
					Idea.class.getName(), classPK);
			List<AssetCategory> categories = Utils.getOrderedCategories(
					idea.getCategoryIds(), curEntry);
			RatingsStats stat = RatingsStatsLocalServiceUtil.getStats(
					Idea.class.getName(), classPK);
			String color = categories.size() > 0 ? CC.get(""
					+ categories.get(0).getCategoryId()) : "";
			String catTitle = categories.size() > 0 ? categories.get(0)
					.getTitle(locale) : "";
	%>
	<span class="span2">
		<div
			onClick="javascript:window.location = '<%=viewIdea.toString()%>';"
			class="thumbnail" style="border-left-color: <%=color%>;">
			<div class="idea-cat">
				<%
					for (int i = 0; i < categories.size(); i++) {
							color = CC.get("" + categories.get(i).getCategoryId());
							catTitle = categories.get(i).getTitle(locale);
				%>
				<span style="color: <%=color%>;"><%=catTitle%></span>
				<%
					}
				%>
				<c:if test="<%=Utils.ideaDeleteEnabled(idea, renderRequest)%>">
					<portlet:actionURL var="deleteURL" name="deleteEntry">
						<portlet:param name="entryId"
							value="<%=String.valueOf(idea.getIdeaId())%>" />
						<portlet:param name="categoryId"
							value="<%=String.valueOf(categoryId)%>" />
						<portlet:param name="callId" value="<%=String.valueOf(callId)%>" />
					</portlet:actionURL>
					<liferay-ui:icon-delete message="lbl_delete"
						url="<%=deleteURL.toString()%>" />
				</c:if>
			</div>
			<h4><%=idea.getTitle()%></h4>
			<div class="thumbnail-bottom">
				<div class="pull-left">
					<%
						out.print(dateFormatter.format(idea.getCreateDate()));
							String scoreString = numberFormat
									.format(stat.getAverageScore());
					%>
				</div>
				<div class="idea-rating pull-right">
					<%
						for (int i = 1; i <= 5; i++) {
					%>
					<i
						class="<%=(i <= stat.getAverageScore()) ? "icon-star"
							: "icon-star-empty"%>"></i>
					<%
						}
					%>
				</div>
			</div>
		</div>
	</span>


	<%
		}
	%>

	<span class="span1 <%=offsetClass%> text-left"> <c:if
			test="<%=(results.size() >= delta)%>">
			<portlet:renderURL var="ne">
				<portlet:param name="mvcPath" value="/html/idea/view.jsp" />
				<portlet:param name="categoryId"
					value="<%=String.valueOf(categoryId)%>" />
				<portlet:param name="callId" value="<%=String.valueOf(callId)%>" />
				<portlet:param name="ideaId" value="0" />
				<portlet:param name="cur"
					value="<%=StringUtil.valueOf(currentPage + 1)%>" />
			</portlet:renderURL>
			<%
				params.put("cur", currentPage + 1);
					String nextURL = Utils.generateRenderURL(renderResponse,
							baseUrl, params);
					//          portletURL.setParameter("cur", StringUtil.valueOf(currentPage + 1));
					//             String nextURL = baseUrl + "?" + HttpUtil.getQueryString(portletURL.toString());
			%>
			<a onclick="paginateIdeas('next');"> <i
				class="icon-arrow-right idea-slider-arrow"></i>
			</a>
		</c:if>
	</span>
</div>