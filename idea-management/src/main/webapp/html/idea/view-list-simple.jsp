<%@ include file="/html/common-init.jsp"%>

<%

  	Long categoryId = ParamUtil.getLong(renderRequest,"categoryId");
  	Long callId = ParamUtil.getLong(renderRequest,"callId");

	String listType = GetterUtil.getString(portletPreferences.getValue("listType", Constants.PREF_LISTTYPE_RECENT));
	boolean pagination = GetterUtil.getBoolean(portletPreferences.getValue("activatePagination", "true"));
	int delta = 0;
    if(pagination) {
    	delta = GetterUtil.getInteger(portletPreferences.getValue("elementInPage", Integer.toString(Constants.PAGINATION_ELEMENTS_IN_PAGE))); 
    }
    
    String confirmMsg =  LanguageUtil.get(locale,"are-you-sure-you-want-to-delete-this");
%>

<div class="row-fluid">
	<span class="idea-slider-title offset1 span10"> <liferay-ui:message
			key='<%="lbl_listTypeTitle_"+listType%>' />
	</span>
</div>

<div id="<portlet:namespace/>result" class="idea-slider row-fluid">
</div>

<aui:script use="liferay-portlet-url">
	var url = Liferay.PortletURL.createResourceURL();
    url.setPortletId('<%= Constants.IDEA_PORTLET_ID %>');
    url.setResourceId("loadSimple");
	url.setParameter("cur", "1");
    url.setParameter("listType",'<%= listType %>');
    url.setParameter("pagination",'<%= Boolean.toString(pagination) %>');
    url.setParameter("delta",'<%= Integer.toString(delta) %>');
	url.setParameter("categoryId", '<%= Long.toString(categoryId) %>');
	url.setParameter("callId", '<%= Long.toString(callId) %>');
	
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
                            
                            v.stars = [0,0,0,0,0];
                            for(var i = 1 ; i <= 5; i++) {
                                v.stars[i-1] = i <= v.avgRating;
                            }
                            
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
                {{this.creationDate}}
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
