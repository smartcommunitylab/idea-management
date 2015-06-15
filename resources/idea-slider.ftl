<#assign current_page = paramUtil.getInteger(renderRequest, "cur", 1)>
<#assign portletURL = renderResponse.createRenderURL() >
<#assign delta = portletPreferences.delta[0]?number >
<#assign baseUrl = httpUtil.getProtocol(request.getAttribute("CURRENT_COMPLETE_URL"))+"://"+httpUtil.getDomain(request.getAttribute("CURRENT_COMPLETE_URL"))+request.getAttribute("FRIENDLY_URL")>
<#assign ILS = serviceLocator.findService("idea-management", "it.smartcommunitylab.platform.idea.service.IdeaLocalService") />

<div class="row-fluid">
    <ul class="thumbnails">
        <span class="span1">
            <#if (current_page > 1)>
                ${portletURL.setParameter("cur", stringUtil.valueOf(current_page-1))}
                <#assign prevURL = baseUrl+"?"+ httpUtil.getQueryString(portletURL.toString()) >
                <a href="${htmlUtil.escape(prevURL)}"><h1><i class="icon-chevron-left"></i></h1><a>
            <#else>
                <h1><i class="icon-chevron-left"></i></h1>
            </#if>
        </span>
        <#if entries?has_content>
	        <#list entries as curEntry>
	            <#assign classPK = curEntry.getClassPK() />
	            <#assign idea = ILS.getIdea(classPK) />
	            <#assign categories = curEntry.getCategories() />
                <li class="span2">
                    <div class="thumbnail">
                        <!--<img data-src="holder.js/300x200" alt="">-->
                        <a href="${assetPublisherHelper.getAssetViewURL(renderRequest, renderResponse, entry)}"><h3>${idea.getTitle()}</h3></a>
                        <p>${idea.getShortDesc()}</p>
                        <p>${categories[0].getTitle(locale)}</p>
                    </div>
                </li>
	        </#list>
        </#if>
        <span class="span1">
            <#if (entries?size >= delta)>
                ${portletURL.setParameter("cur", stringUtil.valueOf(current_page+1))}
                <#assign nextURL = baseUrl+"?"+ httpUtil.getQueryString(portletURL.toString()) >
                <a href="${htmlUtil.escape(nextURL)}"><h1><i class="icon-chevron-right"></i></h1><a>
            <#else>
                <h1><i class="icon-chevron-right"></i></h1>
            </#if>
        </span>
	</ul>
</div>
