<!DOCTYPE html>

<#include init />

<html class="${root_css_class}" dir="<@liferay.language key="lang.dir" />" lang="${w3c_language_id}">

<head>
	<title>${the_title} - ${company_name}</title>

	<meta content="initial-scale=1.0, width=device-width" name="viewport" />

	${theme.include(top_head_include)}

	<link href="${css_folder}/vendor/fullcalendar.min.css" rel="stylesheet" type="text/css" />
	
	<script type="text/javascript" src="${javascript_folder}/vendor/jquery.min.js"></script>
	<script type="text/javascript" src="${javascript_folder}/vendor/moment.min.js"></script>
	<script type="text/javascript" src="${javascript_folder}/vendor/fullcalendar.min.js"></script>
	<script type="text/javascript" src="${javascript_folder}/vendor/lang-all.js"></script>
	
</head>

<body class="${css_class}">

<a href="#main-content" id="skip-to-content"><@liferay.language key="skip-to-content" /></a>

${theme.include(body_top_include)}

<#-- <#if is_signed_in> -->
<#if ((permissionChecker.isOmniadmin()) || permissionChecker.isCompanyAdmin(theme_display.getCompanyId()) || permissionChecker.isCommunityAdmin(theme_display.getScopeGroupId()))>
	<@liferay.dockbar />
</#if>

<div class="container-fluid" id="wrapper">
	<header id="banner" role="banner">
		<div id="heading">
			<#--
			<h1 class="site-title">
				<a class="${logo_css_class}" href="${site_default_url}" title="<@liferay.language_format arguments="${site_name}" key="go-to-x" />">
					<img alt="${logo_description}" height="${site_logo_height}" src="${site_logo}" width="${site_logo_width}" />
				</a>

				<#if show_site_name>
					<span class="site-name" title="<@liferay.language_format arguments="${site_name}" key="go-to-x" />">
						${site_name}
					</span>
				</#if>
			</h1>
			-->

			<#--
			<h2 class="page-title">
				<span>${the_title}</span>
			</h2>
			-->
		</div>

		<#--
		<#if !is_signed_in>
			<a href="${sign_in_url}" data-redirect="${is_login_redirect_required?string}" id="sign-in" rel="nofollow">${sign_in_text}</a>
		</#if>
		-->

		<#if has_navigation || is_signed_in>
			<#include "${full_templates_path}/navigation.ftl" />
		</#if>
	</header>

	<div id="content">
		<#-- <nav id="breadcrumbs"><@liferay.breadcrumbs /></nav> -->

		<#if selectable>
			${theme.include(content_include)}
		<#else>
			${portletDisplay.recycle()}
			${portletDisplay.setTitle(the_title)}
			${theme.wrapPortlet("portlet.ftl", content_include)}
		</#if>
	</div>

	<div class="beta"><span class="star">*</span> <@liferay.language key="beta_version" /></div>
	
	<footer id="footer" role="contentinfo">
		<!-- <p class="powered-by">
			<@liferay.language key="powered-by" /> <a href="http://www.liferay.com" rel="external">Liferay</a>
		</p> -->
		<div class="partners-box center-block">
    		<div class="partner"> <a href="http://www.comune.trento.it/" target="_blank"><img src="${images_folder}/loghi/1.png"></a> </div>
    		<div class="partner"> <a href="http://www.anci.it/" target="_blank"><img src="${images_folder}/loghi/2.png"></a> </div>
    		<div class="partner"> <a href="http://www.agenziagiovani.it/" target="_blank"><img src="${images_folder}/loghi/3.png"></a> </div>
    		<div class="partner"> <a href="http://www.fondazioneifel.it/" target="_blank"><img src="${images_folder}/loghi/4.png"></a> </div>
        </div>
        <div align="center"> Iniziativa co-finanziata dall'Agenzia Nazionale per i Giovani </div> 
        
		<#if has_navigation || is_signed_in>
			<#include "${full_templates_path}/footer-navigation.ftl" />
		</#if>
	</footer>
</div>

${theme.include(body_bottom_include)}
${theme.include(bottom_include)}

</body>
</html>