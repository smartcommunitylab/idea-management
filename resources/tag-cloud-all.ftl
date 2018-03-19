<div class="tag-cloud-header">${languageUtil.get(locale, "tag_cloud_title")}</div>
<#if entries?has_content>
    <#assign counter = 0 >
    <#assign portletURL = renderResponse.createRenderURL() >
    <#assign ATSLS = serviceLocator.findService("com.liferay.portlet.asset.service.AssetTagStatsLocalService") />
	<#list entries?sort_by("assetCount")?reverse as curTag>
	    ${portletURL.setParameter("resetCur", "true")}
    	${portletURL.setParameter("tag", curTag.name)}
        <#assign stat = ATSLS.getTagStats(curTag.getTagId(),portalUtil.getClassNameId("it.smartcommunitylab.platform.idea.model.Idea")) >
        <#assign statCall = ATSLS.getTagStats(curTag.getTagId(),portalUtil.getClassNameId("it.smartcommunitylab.platform.idea.model.Call")) >
	    <#if (counter % 2) == 0 >
        <div class="row-fluid tagcloud-row">
		  <div class="span6 tagcloud-left">
		    <div class="tagcloud-wrapper" onClick="javascript:window.location='${htmlUtil.escape(portletURL.toString())}';">
		        <span class="tagcloud-count">${stat.getAssetCount()}</span>
		        <span class="tagcloud-name">${curTag.name}</span>
		    </div>
		  </div>
		<#else>
		  <div class="span6 tagcloud-right">
		    <div class="tagcloud-wrapper" onClick="javascript:window.location='${htmlUtil.escape(portletURL.toString())}';">
		        <span class="tagcloud-count">${stat.getAssetCount()+statCall.getAssetCount()}</span>
		        <span class="tagcloud-name">${curTag.name}</span>
		    </div>
		  </div>
	    </#if>
	    <#if (counter % 2) == 1 >
        </div>
	    </#if>
	    <#assign counter = counter +1 >
	</#list>
	<#if (counter % 2) == 1>
	    </div>
	</#if>
</#if>