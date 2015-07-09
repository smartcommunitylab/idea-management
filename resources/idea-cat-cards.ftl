<#if entries?has_content>
    <#assign ILS = serviceLocator.findService("idea-management", "it.smartcommunitylab.platform.idea.service.IdeaLocalService") />
    <#assign CC = ILS.getCategoryColors(scopeGroupId) />

    <#assign portletURL = renderResponse.createRenderURL() >
    <div class="ambiti">
    	<#list entries as cat>
    	    ${portletURL.setParameter("resetCur", "true")}
    	    ${portletURL.setParameter("categoryId", stringUtil.valueOf(cat.getCategoryIds()[0]))}
    	    
    	    <div class="row-fluid ambito">
    		    <div class="span5 wrapper">
    		        <div class="bg" style="background-color:${stringUtil.valueOf(CC[stringUtil.valueOf(cat.getCategoryIds()[0])])};border-color:${stringUtil.valueOf(CC[stringUtil.valueOf(cat.getCategoryIds()[0])])};"></div>
    		        <div class="link">
    		            <a href="${htmlUtil.escape(portletURL.toString())}">${cat.getTitle(locale)}</a>
    		        </div>
    		    </div>
    		    <div class="span7"></div>
    		</div>
    	</#list>
    </div>
</#if>
