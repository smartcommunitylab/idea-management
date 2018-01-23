<%@page import="java.util.ArrayList"%>
<%@ page import="it.smartcommunitylab.platform.idea.model.Idea"%>
<%@ page import="com.liferay.portal.kernel.util.ParamUtil"%>
<%@ page
	import="it.smartcommunitylab.platform.idea.service.IdeaLocalServiceUtil"%>
<%@page import="it.smartcommunitylab.platform.idea.model.Call"%>
<%@page
	import="it.smartcommunitylab.platform.idea.service.CallLocalServiceUtil"%>
<%@ page import="com.liferay.portlet.asset.model.AssetCategory"%>
<%@ page import="it.smartcommunitylab.platform.idea.portlet.Utils"%>

<%@ include file="/html/common-init.jsp"%>

<%
	String baseUrl = Utils.getBaseURL(request);
        Idea idea = null;

        long ideaId = ParamUtil.getLong(request, "ideaId");
        AssetEntry assetEntry = null;

        if (ideaId > 0) {
                idea = IdeaLocalServiceUtil.getIdea(ideaId);
                assetEntry = AssetEntryLocalServiceUtil.getEntry(
                        Idea.class.getName(), idea.getIdeaId());
        }
        
        String categoryId = ParamUtil.getString(request, "categoryId");
        String categoryIds = categoryId;
        
        Long callId = ParamUtil.getLong(request, "callId");
        if(callId > 0) {
        	Call call = CallLocalServiceUtil.getCall(callId);
        	if (call != null) categoryIds = call.getCategoryIds();
//             AssetEntry callEntry = AssetEntryLocalServiceUtil.getEntry(
//             	      Call.class.getName(), callId);
//             List<AssetCategory> categories = callEntry.getCategories();   
//             if (categories != null && categories.size() > 0) {
//             	categoryId = ""+ categories.get(0).getCategoryId();
//             	categoryIds = call.
//             }
        }
        

        String tagNames = "";
        
        if(!categoryId.isEmpty()) {
        	long categoryIdLong = Long.valueOf(categoryId);
        	if (categoryIdLong > 0) {
                List<AssetTag> categoryTags = IdeaLocalServiceUtil.getCategoryTags(new long[]{Long.valueOf(categoryId)}, scopeGroupId);
                for(AssetTag t : categoryTags) {
                  tagNames+= (tagNames.isEmpty() ? "" : ",") + t.getName();
                }
        	}
        }

        String curCategoryIds = idea == null ? (""+categoryIds) : idea.getCategoryIds();
%>

<%
	// workaround for assert-tag-selector nullpointerexception
pageContext.setAttribute("themeDisplay", themeDisplay);
%>

<portlet:renderURL var="redirect" windowState="normal">
	<c:if test="<%=idea != null%>">
		<portlet:param name="ideaId"
			value='<%=String.valueOf(idea.getIdeaId())%>'></portlet:param>
	</c:if>
	<portlet:param name="mvcPath"
		value='<%=(idea == null ? "/html/idea/view.jsp" : "/html/idea/asset/full_content.jsp")%>' />
</portlet:renderURL>


<portlet:actionURL
	name='<%=idea == null ? "addNewIdea" : "updateIdea"%>'
	var="addIdeaURL">
	<c:if test='<%=idea != null%>'>
		<portlet:param name="ideaId"
			value='<%=String.valueOf(idea.getIdeaId())%>'></portlet:param>
	</c:if>
	<portlet:param name="redirect"
		value='<%=Utils.generateRenderURL(baseUrl, redirect.toString())%>'></portlet:param>
</portlet:actionURL>

<div class="idea-warning">
	<liferay-ui:message key="idea_warning_1" />
	<liferay-ui:message key="idea_warning_2" />
	<liferay-ui:message key="idea_warning_3" />
</div>

<aui:form cssClass="idea-form" action="<%=addIdeaURL.toString()%>"
	name="idea">
	<aui:model-context bean="<%=idea%>" model="<%=Idea.class%>" />
	
	<aui:fieldset label="lbl_title" cssClass="simple-field">
		<aui:input placeholder='<%=LanguageUtil.get(locale, "lbl_title")%>'
			first="true" label="" name="title"></aui:input>
	</aui:fieldset>

	<!-- <aui:field-wrapper label="lbl_shortDesc" >
    <aui:input placeholder='<%=LanguageUtil.get(locale, "lbl_shortDesc")%>' name="shortDesc" type="textarea" label=""></aui:input>  
  </aui:field-wrapper> -->

	<aui:fieldset label="lbl_idea_longDesc">
		<aui:input name="ldesc" type="hidden"
			value='<%=idea != null ? idea.getLongDesc() : ""%>'></aui:input>
		<liferay-ui:input-editor name="longDesc" toolbarSet="liferay-article"
			initMethod="initEditor2" onChangeMethod="onChange2" width="200"/>
		<script type="text/javascript">
        function <portlet:namespace />initEditor2() { 
        	return document.getElementById('<%=renderResponse.getNamespace()%>ldesc').value;
			}
			function <portlet:namespace />onChange2() {
				document.getElementById('<portlet:namespace />ldesc').value = window['<portlet:namespace />longDesc'].getHTML();
			}
		</script>

	</aui:fieldset>

	<aui:input name="ideaId" type="hidden"></aui:input>
	<aui:input name="categoryId" type="hidden" value="<%=categoryId%>"></aui:input>
	<aui:input name="callId" type="hidden" value="<%=callId%>"></aui:input>
  <aui:input name="discussionLimit" type="hidden" value="10000"></aui:input>


	<liferay-ui:asset-categories-error />
	<aui:fieldset cssClass="categoriesselector-wrapper"
		label="lbl_idea_cat">
		<aui:input name="catId" type="hidden"></aui:input>
		<liferay-ui:asset-categories-selector
			curCategoryIds="<%=curCategoryIds%>">
		</liferay-ui:asset-categories-selector>
	</aui:fieldset>

	<liferay-ui:asset-tags-error />
	<aui:fieldset label="lbl_tags">
		<liferay-ui:asset-tags-selector curTags='<%=tagNames%>'
			className="<%=Idea.class.getName()%>" classPK="<%=ideaId%>">
		</liferay-ui:asset-tags-selector>
	</aui:fieldset>
	
	<aui:fieldset label="related-assets">
		<%
			request.setAttribute("liferay-ui:input-asset-links:className",Idea.class.getName());
		  request.setAttribute("liferay-ui:input-asset-links:assetEntryId",String.valueOf(assetEntry == null ? 0 : assetEntry.getEntryId()));
		%>
		<%@ include file="/html/common/asset-links.jsp"%>
	</aui:fieldset>

	<aui:button-row cssClass="formbutton-row">
		<aui:button cssClass="formbutton-cancel" type="cancel"
			onClick="javascript: confirmLeave = false; window.history.go(-1);"></aui:button>
		<aui:button cssClass="formbutton-primary"
			value='<%=LanguageUtil.get(locale, "btn_save_idea")%>' type="submit">
		</aui:button>
	</aui:button-row>
</aui:form>

<aui:script>
	
	AUI().ready('aui-form-validator', 'aui-overlay-context-panel', function(A) {
		var DEFAULTS_FORM_VALIDATOR = A.config.FormValidator;

		A.mix(DEFAULTS_FORM_VALIDATOR.RULES, {
			descRule : function(val, fieldNode, ruleValue) {
				var desc = document.getElementById('<portlet:namespace />ldesc').value;
				//filter html tags to check empty input
				desc = desc.replace(/[(<p>(&nbsp;)*<\/p>)*|(&nbsp;)*]/gm, '');
				return desc.trim().length > 0;
			}
		}, true);

		var validator2 = new A.FormValidator({
			boundingBox : '#<portlet:namespace/>idea',
			showAllMessages : true,
			rules : {
				<portlet:namespace/>ldesc : {
					required : true,
					descRule : true
				},
				<portlet:namespace/>title : {
					required : true
				}
			},
			on : {
				validateField : function(event) {
				},
				validField : function(event) {
				},
				errorField : function(event) {
				},
				submitError : function(event) {
					var formEvent = event.validator.formEvent;
					var errors = event.validator.errors;
				},
				submit : function(event) {
					var formEvent = event.validator.formEvent;
					confirmLeave = false;
					return true;
				}
			}
		});
	});

	var confirmLeave = true;

	function doSubmit() {
		confirmLeave = false;
		document.<portlet:namespace />idea.submit();
	};

	window.onbeforeunload = function(event) {
		if (!confirmLeave) {
			//event.returnValue = null;
			//return null;
		} else {
			var message = '<liferay-ui:message key="msg_unsaved_data"/>';
			if (typeof event == 'undefined') {
				event = window.event;
			}
			if (event) {
				event.returnValue = message;
			}
			return message;
		} 
	};
	
</aui:script>
	


