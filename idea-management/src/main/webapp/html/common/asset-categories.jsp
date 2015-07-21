<%@ page import="com.liferay.portlet.asset.service.AssetCategoryLocalServiceUtil" %>
<%@ page import="com.liferay.portlet.asset.service.AssetVocabularyServiceUtil" %>
<%@ page import="com.liferay.portlet.asset.model.AssetVocabulary" %>
<%@ page import="com.liferay.portlet.asset.model.AssetCategory" %>
<%@ page import="it.smartcommunitylab.platform.idea.service.IdeaLocalServiceUtil"%>

<%@ include file="/html/common-init.jsp" %>

<%
  boolean singleSelect = GetterUtil.getBoolean(request.getAttribute("im-acs:single-select"), false);
  String className = GetterUtil.getString(request.getAttribute("im-acs:className"));
  String current = GetterUtil.getString(request.getAttribute("im-acs:curCategories"));
  long[] curIds = null;;//StringUtil.split(current, 0L);
  java.util.Set<Long> selected = new java.util.HashSet<Long>();
  for (long curIdSel : curIds) {
	  selected.add(curIdSel);
  }
  
  String randomNamespace = PortalUtil.generateRandomKey(request, "taglib_ui_asset_categories_selector_page") + StringPool.UNDERLINE;
  String namespace =  "";//((PortletResponse)request.getAttribute(JavaConstants.JAVAX_PORTLET_RESPONSE)).getNamespace();

  List<AssetEntry> categoryEntries = IdeaLocalServiceUtil.getCategoryObjects(scopeGroupId);
  long[] catIds = new long[categoryEntries.size()];
  String[] catNames = new String[categoryEntries.size()];
  int i = 0;
  java.util.Map<Long, AssetEntry> catAssetEntryMap = new java.util.HashMap<Long, AssetEntry>();
  for (AssetEntry cae : categoryEntries) {
	  catIds[i] = cae.getCategoryIds()[0];
	  catNames[i] =cae.getTitle(locale);
	  catAssetEntryMap.put(catIds[i],cae);
	  i++;
  }
/*   List<AssetVocabulary> vocs = AssetVocabularyServiceUtil.getGroupsVocabularies(new long[]{scopeGroupId});
  List<AssetCategory> categories = java.util.Collections.emptyList();
  if (vocs != null && vocs.size() > 0) {
	  AssetVocabulary categoryVoc = vocs.get(0);
	  categories = categoryVoc.getCategories();
  }
 */  
%>

<script type="text/javascript">
  var toggleValue = function(id, toAdd) {
      var inputs = document.getElementsByTagName("input");
      for (var i = 0; i < inputs.length; i++) {
          if (inputs[i].id.indexOf( '<portlet:namespace/>assetCategoryIds') == 0) {
            var vals = inputs[i].value.split(',');
            if (toAdd){
            	vals.push(id);
            	inputs[i].value = vals.join(',');
            } else {
              var newVals = [];
              for (var j = 0; j < vals.length;j++) {
                if (vals[j] != id) newVals.push(vals[j]);
              }
              inputs[i].value = newVals.join(',');
            }
          }
        }
  }

  function toggleCategory(cb, id, title) {
	   if (!cb.checked) {
		   removeBtnFn(id);
	   } else {
	     var container = document.getElementById('<%=randomNamespace %>assetCategoriesSelector_ul');
		   var txt = '<li class="yui3-widget component textboxlistentry" id="<%=randomNamespace %>_li_'+id+'" tabindex="0">'+
		             '<span class="textboxlistentry-content">'+
                 '<span class="textboxlistentry-text">'+title+'</span>'+
                 '<span class="textboxlistentry-remove" onClick="removeBtnFn('+id+')"><i class="icon icon-remove"></i></span></span></li>';
       var wrapper= document.createElement('div');
       wrapper.innerHTML = txt;
       container.appendChild(wrapper.firstChild);
		   toggleValue(id,true);
	   }
  }
  
  function removeBtnFn(id) {
	     var container = document.getElementById('<%=randomNamespace %>assetCategoriesSelector_ul');
	     for (var i = 0; i < container.childNodes.length;i++) {
	         if (container.childNodes[i].id == '<%=randomNamespace%>_li_'+id) {
	        	 container.removeChild(container.childNodes[i]);
             toggleValue(id, false);
	         }
	     }

	};
</script>

<aui:script>
AUI().use('aui-base','aui-modal', function(A) {
   A.one('#<%=randomNamespace %>_button').on('click', function(event){
   A.one('#modal-content').setStyle('display','block')
       var modal = new A.Modal(
         {
           srcNode : '#modal-content',
           centered: true,
           headerContent: '<h3><liferay-ui:message key="lbl_categories"/></h3>',
           modal: true,
           render: '#modal',
           width: 450
         }
       ).render();
   });
   
});
 </aui:script>
 <div class="yui3-widget component autocomplete textboxlist tagselector categoriesselector">
 <div class="lfr-tags-selector-content categoriesselector-content" id="<%=randomNamespace %>assetCategoriesSelector">
  <ul class="helper-clearfix textboxlistentry-holder unstyled" id="<%=randomNamespace %>assetCategoriesSelector_ul">
    <% for (long curId: curIds) { 
    	AssetEntry assetCatEntry = catAssetEntryMap.get(curId);  
    	long categoryId = assetCatEntry.getCategoryIds()[0];
        String title = assetCatEntry.getTitle(locale);
    %>
    <li class="yui3-widget component textboxlistentry" id="<%=randomNamespace %>_li_<%=categoryId %>" tabindex="0">
      <span class="textboxlistentry-content">
        <span class="textboxlistentry-text"><%=title %></span>
        <span class="textboxlistentry-remove" onClick="removeBtnFn(<%=categoryId%>)"><i class="icon icon-remove"></i></span>
      </span>
    </li>
    <% } %>
  </ul>
  <div class="toolbar-content yui3-widget component toolbar">
    <button id="<%=randomNamespace %>_button" type="button" class="btn" title="Select Categories"><i class="icon-search"></i> Select</button>
  </div>
 </div>
 </div>
 
<div id="modal"></div>
<div id="modal-content" style="display:none;">
<% for (AssetEntry assetCatEntry : categoryEntries) { 
	   long categoryId = assetCatEntry.getCategoryIds()[0];
	   String title = assetCatEntry.getTitle(locale);
%>
<div class="row-fluid">
  <div class="span12 im-acs-category-row">
		<input <%=selected.contains(categoryId) ? "checked":"" %> onClick="javascript:toggleCategory(this, <%=categoryId %>,'<%=title %>');" class="im-acs-category-checkbox" type="checkbox" name="<portlet:namespace /><%=categoryId%>" id="<portlet:namespace /><%=categoryId%>"/>
		<label for="<portlet:namespace /><%=categoryId%>" class="im-acs-category-label"><%=title %></label>
  </div>
</div>
<% } %>
</div>