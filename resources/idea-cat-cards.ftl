<#if entries?has_content>
    <#assign AEQ  = objectUtil("com.liferay.portlet.asset.service.persistence.AssetEntryQuery") />
    <#assign AEQCall  = objectUtil("com.liferay.portlet.asset.service.persistence.AssetEntryQuery") />
    <#assign AELSU= staticUtil["com.liferay.portlet.asset.service.AssetEntryLocalServiceUtil"] />

    <#assign ideaClassNameId = [portalUtil.getClassNameId("it.smartcommunitylab.platform.idea.model.Idea")]>
    <#assign callClassNameId = [portalUtil.getClassNameId("it.smartcommunitylab.platform.idea.model.Call")]>

    <#assign portletURL = renderResponse.createRenderURL() >
	<#assign pos = 0>
	<#list entries as cat>
        ${AEQ.setAllCategoryIds(cat.getCategoryIds())}
        ${AEQCall.setAllCategoryIds(cat.getCategoryIds())}
        ${AEQ.setClassNameIds(ideaClassNameId)}
        ${AEQCall.setClassNameIds(callClassNameId)}

	    ${portletURL.setParameter("resetCur", "true")}
	    ${portletURL.setParameter("categoryId", stringUtil.valueOf(cat.getCategoryIds()[0]))}
	    <#if pos % 2 == 0>
	        <div class="row-fluid"><div class="span12"><div class="row-fluid">
        </#if>
		<div class="well span6 text-center">
		   <div class="row-fluid">
		        <div class="span6">${AELSU.getEntriesCount(AEQ)}</div>
		        <div class="span6">${AELSU.getEntriesCount(AEQCall)}</div>
		   </div>
		   <a href="${htmlUtil.escape(portletURL.toString())}">${cat.getTitle(locale)}</a>
		</div>
	    <#if pos % 2 == 1>
	        </div></div></div>
        </#if>
        <#assign pos = pos + 1>
	</#list>
</#if>