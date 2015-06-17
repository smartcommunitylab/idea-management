<#--
Application display templates can be used to modify the look of a
specific application.

Please use the left panel to quickly add commonly used variables.
Autocomplete is also available and can be invoked by typing "${".
-->

<#assign current_page = paramUtil.getInteger(renderRequest, "cur", 1) />
<#assign portletURL = renderResponse.createRenderURL() />
<#assign delta = portletPreferences.delta[0]?number />
<#assign offset = delta - entries?size />
<#assign offsetClass = (offset > 0)?string('offset' + offset*2, '') />
<#assign baseUrl = httpUtil.getProtocol(request.getAttribute("CURRENT_COMPLETE_URL"))+"://"+httpUtil.getDomain(request.getAttribute("CURRENT_COMPLETE_URL"))+request.getAttribute("FRIENDLY_URL")>
<#assign ILS = serviceLocator.findService("idea-management", "it.smartcommunitylab.platform.idea.service.IdeaLocalService") />
<#assign CC = ILS.getCategoryColors(scopeGroupId) />
<#assign RSS = staticUtil["com.liferay.portlet.ratings.service.RatingsStatsLocalServiceUtil"] />

<div class="idea-slider row-fluid">
    <span class="span1 text-right">
        <#if (current_page > 1)>
            ${portletURL.setParameter("cur", stringUtil.valueOf(current_page - 1))}
            <#assign prevURL = baseUrl + "?" + httpUtil.getQueryString(portletURL.toString()) >
            <a href="${htmlUtil.escape(prevURL)}">
                <i class="icon-arrow-left idea-slider-arrow"></i>
            </a>
        <#else>
            <i class="icon-arrow-left idea-slider-arrow"></i>
        </#if>
    </span>
    <#if entries?has_content>
        <#list entries as curEntry>
            <#assign classPK = curEntry.getClassPK() />
            <#assign idea = ILS.getIdea(classPK) />
            <#assign categories = curEntry.getCategories() />
            <#assign stat = RSS.getStats("it.smartcommunitylab.platform.idea.model.Idea",classPK) />
            <span class="span2">
                <a class="idea" href="${curEntry.getAssetRenderer().getURLView(renderResponse, renderRequest.getWindowState())}">
                    <div class="thumbnail" style="border-left-color: ${stringUtil.valueOf(CC[stringUtil.valueOf(categories[0].getCategoryId())])};">
                        <#-- <img data-src="holder.js/300x200" alt=""> -->
                        <h6 class="idea-cat" style="color: ${stringUtil.valueOf(CC[stringUtil.valueOf(categories[0].getCategoryId())])};">${categories[0].getTitle(locale)}</h6>
                        <h3>${idea.getTitle()}</h3>
                        <#-- <p>${idea.getShortDesc()}</p> -->
                        <p>${stat.getTotalScore()} (${stat.getTotalEntries()} users)</p>
                    </div>
                </a>
            </span>
        </#list>
    </#if>
	<span class="span1 ${offsetClass} text-left">
        <#if (entries?size >= delta)>
            ${portletURL.setParameter("cur", stringUtil.valueOf(current_page + 1))}
            <#assign nextURL = baseUrl + "?" + httpUtil.getQueryString(portletURL.toString()) />
            <a href="${htmlUtil.escape(nextURL)}">
                <i class="icon-arrow-right idea-slider-arrow"></i>
            </a>
        <#else>
            <i class="icon-arrow-right idea-slider-arrow"></i>
        </#if>
    </span>
</div>
