<div class="ambito-details">
    <#if (image.getData()?has_content)>
        <div class="cover" style="background-image:url(${image.getData()});"></div>
    </#if>
    <h2 class="title" style="color:${color.getData()}">${stringUtil.valueOf(.vars['reserved-article-title'].data)}</h1>
    <div>
    ${Content.getData()}
    </div>
</div>
