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
<%@page import="java.util.Random"%>

<%@ include file="/html/common-init.jsp"%>

<%
	

	String listType = GetterUtil.getString(portletPreferences.getValue("listType", Constants.PREF_CALLLISTTYPE_OPEN));

	boolean pagination = GetterUtil.getBoolean(portletPreferences.getValue("activatePagination", "true"));
	int delta = 0;
    if(pagination) {
		delta = GetterUtil.getInteger(portletPreferences.getValue("elementInPage", ""+Constants.PAGINATION_CALL_ELEMENTS_IN_PAGE));
    }
    
    String confirmMsg =  LanguageUtil.get(locale,"are-you-sure-you-want-to-delete-this");
%>

<div class="row-fluid">
</div>

<div id="<portlet:namespace/>result" class="idea-slider row-fluid">
</div>

<aui:script use="liferay-portlet-url">
	var url = Liferay.PortletURL.createResourceURL();
    url.setPortletId('<%= Constants.CALL_PORTLET_ID %>');
    url.setResourceId("loadSimple");
	url.setParameter("cur", "1");
    url.setParameter("listType",'<%= listType %>');
    url.setParameter("pagination",'<%= Boolean.toString(pagination) %>');
    url.setParameter("delta",'<%= Integer.toString(delta) %>');
	
	<portlet:namespace/>paginateIdeas(url.toString());
</aui:script>

	<aui:script>
		Liferay.provide(window, '<portlet:namespace/>paginateIdeas', function(url) {
			var instance = this;
			var A = AUI();
            //alert(url);
			A.io.request(url, {
				dataType : 'json',
				on : {
					success : function() {
						var data = this.get('responseData');
                        // transform data
                        data.viewPrevArrow = <%= pagination %> && data.currentPage != 1;
                        data.viewNextArrow = <%= pagination %> && data.result.size >= data.elementInPage;
                        
                        var offset = data.elementInPage - data.result.size;
                        data.offsetClass = (offset > 0) ? "offset" + offset*2 : "";
                       
                        $.each(data.result.data,function(i,v) {
                            v.boxColor = (v.cats[0]) ? v.cats[0].color : "";
                        });
                        //alert(data.result.size);
						var nodeResult = A.one('#<portlet:namespace/>result');
                        //alert(nodeResult);
						nodeResult.empty();
						var handlebars = new A.Template(A.Handlebars);
						var source = A.one('#<portlet:namespace/>idea-templ').html();
						var compiled = handlebars.compile(source);
						var html = compiled({
							result : data
						});
						nodeResult.html(html);
                        
                        }
                    }
                });
            }, [ 'aui-io', 'aui-node', 'template-base', 'handlebars' ]);
		
		
		Liferay.provide(window, '<portlet:namespace/>deleteEntry', function(url) {
			var instance = this;
			var A = AUI();
            //alert(url);
			A.io.request(url, {
				dataType : 'json',
				on : {
					success : function() {
                    }
                    }
                });
            }, [ 'aui-io', 'aui-node' ]);
		
		
		
		
		
		
	</aui:script>


<script id="<portlet:namespace/>idea-templ" type="text/x-handlebars">
    <span class="span1 text-right"> 
		{{#if result.viewPrevArrow }}
			<a onclick="<portlet:namespace/>paginateIdeas('{{result.prevURL}}');"> <i
				class="icon-arrow-left idea-slider-arrow"></i>
			</a>
		{{/if}}
	</span>
	<span class="span10">
	<div class="row-fluid">
    {{#each result.result.data}}
     <span class="span3" id="result">
   <div id="<portlet:namespace/>resContainer" onClick="javascript:window.location = '{{this.detailURL}}';" class="thumbnail" style="border-left-color: {{this.boxColor}};">
	<div class="idea-cat">
				{{#each this.cats}}
					<span style="color: {{this.color}};">{{this.name}}</span>
				{{/each}}
				{{#if this.deleteURL}}
					<a id="delete-link-{{@index}}" href={{this.deleteURL}} onclick="return confirm('<%= confirmMsg %>');" class="pull-right"><span class="delete-icon" style="height:16px;width:16px;display:inline-block;"></span></a>
				{{/if}}			
			</div>
                    <h4 class="title-ellipsis">{{this.title}}</h4>
					  <div class="thumbnail-bottom">
	                    <div class="pull-left">
			                  {{#if this.deadline}}
			                  	<liferay-ui:message key="lbl_call_card_deadline_no_param"/> {{this.deadline}}
			                  {{/if}}
	                    </div>
	   		              <div class="pull-right">
	   		                <span class="call-card-ideas-simple"> {this.ideas}} </span>
	   		              </div>
   		            </div>
    </div>
    </span>
    {{/each}}
	</div>
	</span>
	
	<span class="span1 text-left {{result.offsetClass}}"> 
		{{#if result.viewNextArrow }}
			<a onclick="<portlet:namespace/>paginateIdeas('{{result.nextURL}}');"> <i
				class="icon-arrow-right idea-slider-arrow"></i>
			</a>
		{{/if}}
	</span>
</script>