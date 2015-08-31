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

<div id='result' class="idea-slider row-fluid">

<aui:script use="liferay-portlet-url">
	var url = Liferay.PortletURL.createResourceURL();
	url.setParameter("cur", "1");
    url.setPortletId('<%= Constants.IDEA_PORTLET_ID %>');
	url.setParameter("categoryId", <%= Long.toString(categoryId)%>);
	url.setParameter("callId", <%= Long.toString(callId)%>);
	paginateIdeas(url.toString());
</aui:script>

	<aui:script>
		Liferay.provide(window, 'paginateIdeas', function(url) {
			var instance = this;
			var A = AUI();
            //alert(url);
			A.io.request(url, {
				dataType : 'json',
				on : {
					success : function() {
						var data = this.get('responseData');
                        // transform data
                        if( data.currentPage == 1) {
                        	data.currentPage = 0; // for handlebars templata
                        }
                        
                        var offset = data.elementInPage - data.result.size;
                        data.offsetClass = (offset > 0) ? "offset" + offset*2 : "";
                       
                        $.each(data.result.data,function(i,v) {
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
		{{#if result.currentPage }}
			<a onclick="paginateIdeas('{{result.prevURL}}');"> <i
				class="icon-arrow-left idea-slider-arrow"></i>
			</a>
		{{/if}}
	</span>
    {{#each result.result.data}}
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
	
	<span class="span1 text-left {{result.offsetClass}}"> 
			<a onclick="paginateIdeas('{{result.nextURL}}');"> <i
				class="icon-arrow-right idea-slider-arrow"></i>
			</a>
	</span>
</script>

</div>
