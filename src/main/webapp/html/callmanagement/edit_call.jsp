
<%@page import="java.util.Calendar"%>
<%@page import="java.util.GregorianCalendar"%>
<%@page import="java.util.Date"%>
<%@ include file="/html/common-init.jsp" %>

<%@ page import="it.smartcommunitylab.platform.idea.model.Call"%>
<%@ page import="com.liferay.portal.kernel.util.ParamUtil"%>
<%@ page
	import="it.smartcommunitylab.platform.idea.service.CallLocalServiceUtil"%>
<%@ page import="com.liferay.portal.kernel.util.WebKeys"%>
<%@ page import="com.liferay.portal.theme.ThemeDisplay"%>



<%
	 Call entry = null;

    long entryId = ParamUtil.getLong(request, "entryId");

        if (entryId > 0) {
                entry = CallLocalServiceUtil.getCall(entryId);
        }
        
%>

<%
	// workaround for assert-tag-selector nullpointerexception
pageContext.setAttribute("themeDisplay", themeDisplay);
%>


<portlet:renderURL var="viewURL">
	<portlet:param name="mvcPath" value="/html/idea/view.jsp"></portlet:param>
</portlet:renderURL>

<portlet:actionURL
	name='<%=entry == null ? "addEntry" : "updateEntry"%>'
	var="editEntryURL"></portlet:actionURL>

<aui:form action="<%=editEntryURL%>" name="call">
<aui:model-context bean="<%= entry %>" model="<%= Call.class %>" />
	
	<aui:fieldset>
		<aui:input name="title" label="lbl_title"></aui:input>
	</aui:fieldset>

	<aui:field-wrapper label="lbl_desc">
		<liferay-ui:input-editor name="desc"
			toolbarSet="liferay-article" initMethod="initEditor1" width="200" />
		<script type="text/javascript">
        function <portlet:namespace />initEditor1() { return document.getElementById('<portlet:namespace />hdesc').value; }
    </script>
	</aui:field-wrapper>

	<%
		GregorianCalendar cal = new GregorianCalendar();
		if(entry != null && entry.getDeadline() != null) {
			cal.setTime(entry.getDeadline());
		}
		int initDay = cal.get(Calendar.DAY_OF_MONTH);
		int initMonth = cal.get(Calendar.MONTH); 
		int initYear  = cal.get(Calendar.YEAR);
	%>
	
	<aui:fieldset label="lbl_deadline">
		<liferay-ui:input-date name="deadline" dayParam="dday" monthParam="dmonth" yearParam="dyear" dayValue="<%=initDay %>" monthValue="<%=initMonth %>" yearValue="<%=initYear %>"></liferay-ui:input-date>
	</aui:fieldset>
	
	
	<aui:input name="hdesc" type="hidden" value='<%= entry != null ? entry.getDescription() : "" %>'></aui:input>
	<aui:input name="callId" type="hidden"></aui:input>

	<liferay-ui:asset-categories-error />
	<liferay-ui:asset-tags-error />

	<liferay-ui:asset-categories-selector
		className="<%=Call.class.getName()%>" classPK="<%=entryId%>">

	</liferay-ui:asset-categories-selector>


	<label>Tags</label>
	<liferay-ui:asset-tags-selector className="<%=Call.class.getName()%>"
		classPK="<%= entryId%>">
	</liferay-ui:asset-tags-selector>



	<liferay-ui:panel defaultState="closed" extended="<%=false%>"
		id="ideaAssetLinksPanel" persistState="<%=true%>"
		title="related-assets">
		<aui:fieldset>
			<liferay-ui:input-asset-links className="<%=Call.class.getName()%>"
				classPK="<%=entryId%>" />
		</aui:fieldset>
	</liferay-ui:panel>

	<aui:button-row>

		<aui:button type="submit"></aui:button>
		<aui:button type="cancel" onClick="<%= viewURL.toString() %>"></aui:button>

	</aui:button-row>
</aui:form>