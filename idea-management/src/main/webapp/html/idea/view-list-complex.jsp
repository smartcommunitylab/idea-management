<%@ include file="/html/common-init.jsp" %>

<%
	Long categoryId = ParamUtil.getLong(renderRequest,"categoryId");
  Long callId = ParamUtil.getLong(renderRequest,"callId");
  
//   java.util.Set<Long> selectedTagIds = (java.util.Set<Long>)request.getAttribute("selectedTagIds");
//   String selectedTagIdsString = null;
//   if (selectedTagIds != null && ! selectedTagIds.isEmpty()) {
// 	  selectedTagIdsString = com.liferay.portal.kernel.util.StringUtil.merge(selectedTagIds, ",");
//   }


	String listType = GetterUtil.getString(portletPreferences.getValue("listType", Constants.PREF_LISTTYPE_RECENT));
	boolean pagination = GetterUtil.getBoolean(portletPreferences.getValue("activatePagination", "true"));
	int delta = 0;
    if(pagination) {
    	delta = GetterUtil.getInteger(portletPreferences.getValue("elementInPage", Integer.toString(Constants.PAGINATION_ELEMENTS_IN_PAGE))); 
    }
    
    String confirmMsg =  LanguageUtil.get(locale,"are-you-sure-you-want-to-delete-this");
    
    int columns = GetterUtil.getInteger(portletPreferences.getValue("columns", Integer.toString(Constants.COLUMNS)));
    String colClass = columns == 3 ? "span4" : columns == 6 ? "span2" : columns == 2 ? "span6" : columns == 4 ? "span3": "span12";    
%>

<span id="<portlet:namespace/>result">
</span>


<script id="<portlet:namespace/>idea-templ" type="text/x-handlebars">
<div class="row-fluid idea-cards" >
	<div class="span12">
	{{#each result.result.data}}
		{{#if this.startRow }}
		<div class="row-fluid">
		{{/if}}
  		<span class="<%=colClass %>" id="result">
   			<div id="<portlet:namespace/>resContainer" onClick="javascript:window.location = '{{this.detailURL}}';" class="idea-card" style="border-color: {{this.boxColor}};">
	 				<div class="idea-card-header">
      			<div class="span8" style="overflow: hidden; text-overflow: ellipsis; color: #fff">
	      			{{#if this.callId}}
								<span class="idea-card-call-label" style="white-space: nowrap;">{{this.callName}}</span> 
							{{/if}}
        		</div>
        		<div class="span4">
        			{{#if this.deleteURL}}
								<a id="delete-link-{{@index}}" href={{this.deleteURL}} onclick="return confirm('<%= confirmMsg %>');" class="pull-right"><span class="delete-icon" style="height:16px;width:16px;display:inline-block;"></span></a>
							{{/if}}	
          		<span class="idea-card-date">
          			{{this.creationDate}}
          		</span>
        		</div>
      		</div>	
					<h4>{{this.title}}</h4>
        	<div class="idea-card-footer">
        		<div class="span6 idea-rating">
          	{{#each this.stars}}
          		{{#if this}}
            	<i class="icon-star"></i>
            	{{else}}
            	<i class="icon-star-empty"></i>
            	{{/if}}
          	{{/each}}
          	</div>
          	<div class="span6">
          		<span class="idea-card-comments">{{this.comments}}</span>
          	</div>
					</div>
      	</div>
    	</span>
		{{#if  this.endRow}}
		</div>
		{{/if}}
	{{/each}}
	{{#if this.noResults }}
		<div class="row-fluid">
			<span class="empty-results"><liferay-ui:message key="lbl_noresults"/></span>
		</div>
	{{/if}}
	</div>
	<div class="idea-paging row-fluid">
  	<div class="span6">
   		{{#if result.viewPrevArrow }}
    	<a class="idea-paging-prev" onclick="<portlet:namespace/>paginateIdeas('{{result.prevURL}}');"><liferay-ui:message key="prev_page"/></a>
			{{/if}}
  	</div>
  	<div class="span6">
  		{{#if result.viewNextArrow }}
      <a class="idea-paging-next" onclick="<portlet:namespace/>paginateIdeas('{{result.nextURL}}');"><liferay-ui:message key="next_page"/></a>
			{{/if}}
  	</div>
	</div>
</div>
	
</script>

<aui:script use="liferay-portlet-url">
	var A = new AUI();
  var ftype = A.one('input[name=<%=renderResponse.getNamespace() %>listType]:checked');
  var listType = ftype ? ftype.val() : '<%= Constants.PREF_LISTTYPE_POPULAR %>';
	var tags = A.all('input[id$=Checkbox]:checked');
	var tagIds = [];
	tags.each(function(){
	  tagIds.push(this.val());
	});

	var url = Liferay.PortletURL.createResourceURL();
    url.setPortletId('<%= Constants.IDEA_PORTLET_ID %>');
    url.setResourceId("loadSimple");
	url.setParameter("cur", "1");
//     url.setParameter("listType",'<%= listType %>');
    url.setParameter("pagination",'<%= Boolean.toString(pagination) %>');
    url.setParameter("delta",'<%= Integer.toString(delta) %>');
	url.setParameter("categoryId", '<%= Long.toString(categoryId) %>');
	url.setParameter("callId", '<%= Long.toString(callId) %>');
    url.setParameter("listType", listType);
    url.setParameter("tag", tagIds.join() );
		
	<portlet:namespace/>paginateIdeas(url.toString());

</aui:script>


	<aui:script>
		Liferay.provide(window, '<portlet:namespace/>paginateIdeas', function(url) {
			var instance = this;
			var A = AUI();
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
                        data.noResults = data.result.size == 0 && data.currentPage == 1;
                        //data.rows = Math.floor(data.result.size / 3);
                        $.each(data.result.data,function(j,v) {
                            
                            v.stars = [0,0,0,0,0];
                            for(var i = 1 ; i <= 5; i++) {
                                v.stars[i-1] = i <= v.avgRating;
                            }
                            
                            v.boxColor = (v.cats[0]) ? v.cats[0].color : "";
                            
                            v.startRow = j % <%=columns %> == 0;
                            v.endRow = j % <%=columns %> == 2;
                            if(j == (data.result.size - 1)) {
                            	v.endRow = true;
                            }
                        });
						var nodeResult = A.one('#<portlet:namespace/>result');
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
			A.io.request(url, {
				dataType : 'json',
				on : {
					success : function() {
                    }
                    }
                });
            }, [ 'aui-io', 'aui-node' ]);
	</aui:script>
