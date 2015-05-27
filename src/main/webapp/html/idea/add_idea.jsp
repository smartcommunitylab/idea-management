<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>
<%@ taglib uri="http://alloy.liferay.com/tld/aui" prefix="aui" %>
<%@ taglib uri="http://liferay.com/tld/ui" prefix="liferay-ui" %>

<%@ page import="it.smartcommunitylab.platform.idea.model.Idea" %>
<%@ page import="com.liferay.portal.kernel.util.ParamUtil" %>
<%@ page import="it.smartcommunitylab.platform.idea.service.IdeaLocalServiceUtil" %>
<%@ page import="com.liferay.portal.kernel.util.WebKeys" %>
<%@ page import="com.liferay.portal.theme.ThemeDisplay" %>

<portlet:defineObjects />

<%
        Idea idea = null;

        long ideaId = ParamUtil.getLong(request, "ideaId");

        if (ideaId > 0) {
                idea = IdeaLocalServiceUtil.getIdea(ideaId);
        }
%>

<%
// workaround for assert-tag-selector nullpointerexception
ThemeDisplay themeDisplay = (ThemeDisplay) renderRequest.getAttribute(WebKeys.THEME_DISPLAY);
pageContext.setAttribute("themeDisplay", themeDisplay);
%>

<portlet:renderURL var="viewURL">
    <portlet:param name="mvcPath" value="/html/idea/view.jsp"></portlet:param>
</portlet:renderURL>

<portlet:actionURL name='<%= idea == null ? "addNewIdea" : "updateIdea" %>' var="addIdeaURL"></portlet:actionURL>

<aui:form action="<%= addIdeaURL %>" name="<portlet:namespace />idea">

        <aui:fieldset>

            <aui:input name="name"></aui:input>
            <aui:input type="textarea" name="shortDesc"></aui:input>
            <aui:input type="textarea" name="longDesc"></aui:input>

        </aui:fieldset>
        
                <liferay-ui:asset-categories-error />
                <liferay-ui:asset-tags-error />
                
                <liferay-ui:asset-categories-selector className="<%=Idea.class.getName()%>" classPK="<%=ideaId%>">
                
                </liferay-ui:asset-categories-selector>
                
                
                <label>Tags</label>
                <liferay-ui:asset-tags-selector  className="<%=Idea.class.getName()%>" classPK="<%=ideaId%>">
                </liferay-ui:asset-tags-selector>
                
               

                <liferay-ui:panel defaultState="closed" extended="<%= false %>" id="ideaAssetLinksPanel" persistState="<%= true %>" title="related-assets">
                        <aui:fieldset>
                                <liferay-ui:input-asset-links
                                        className="<%= Idea.class.getName() %>"
                                        classPK="<%= ideaId %>"
                                />
                        </aui:fieldset>
                </liferay-ui:panel>

        <aui:button-row>

            <aui:button type="submit"></aui:button>
            <aui:button type="cancel" onClick="<%= viewURL.toString() %>"></aui:button>

        </aui:button-row>
</aui:form>