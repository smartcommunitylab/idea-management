<#--
This file allows you to override and define new FreeMarker variables.
-->
<#assign footer = "footer-navigation" />
<#if (themeDisplay.getLayout().getExpandoBridge().hasAttribute(footer) == false)>
	${themeDisplay.getLayout().getExpandoBridge().addAttribute(footer, 1, false, false)}
</#if>
