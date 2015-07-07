<#assign liferay_util = PortalJspTagLibs["/WEB-INF/tld/liferay-util.tld"] />
<#assign liferay_ui = PortalJspTagLibs["/WEB-INF/tld/liferay-ui.tld"] />
<#assign aui = PortalJspTagLibs["/WEB-INF/tld/liferay-aui.tld"] />

<nav class="${nav_css_class} navbar" id="navigation" role="navigation">
	<div class="navbar-inner">
		<ul id="navmenu" aria-label="<@liferay.language key="site-pages" />" role="menubar" class="nav">
			<#list nav_items as nav_item>
				<#assign nav_item_attr_has_popup = "" />
				<#assign nav_item_attr_selected = "" />
				<#assign nav_item_css_class = "" />
	
				<#if nav_item.isSelected()>
					<#assign nav_item_attr_has_popup = "aria-haspopup='true'" />
					<#assign nav_item_attr_selected = "aria-selected='true'" />
					<#assign nav_item_css_class = "selected" />
				</#if>
	
				<li ${nav_item_attr_selected} class="${nav_item_css_class}" id="layout_${nav_item.getLayoutId()}" role="presentation">
					<a aria-labelledby="layout_${nav_item.getLayoutId()}" ${nav_item_attr_has_popup} href="${nav_item.getURL()}" ${nav_item.getTarget()} role="menuitem">
						<span>${nav_item.icon()} ${nav_item.getName()}</span>
					</a>
	
					<#if nav_item.hasChildren()>
						<ul class="child-menu" role="menu">
							<#list nav_item.getChildren() as nav_child>
								<#assign nav_child_attr_selected = "" />
								<#assign nav_child_css_class = "" />
	
								<#if nav_item.isSelected()>
									<#assign nav_child_attr_selected = "aria-selected='true'" />
									<#assign nav_child_css_class = "selected" />
								</#if>
	
								<li ${nav_child_attr_selected} class="${nav_child_css_class}" id="layout_${nav_child.getLayoutId()}" role="presentation">
									<a aria-labelledby="layout_${nav_child.getLayoutId()}" href="${nav_child.getURL()}" ${nav_child.getTarget()} role="menuitem">
										${nav_child.getName()}
									</a>
								</li>
							</#list>
						</ul>
					</#if>
				</li>
			</#list>
			<#if is_signed_in>
				
			</#if>
		</ul>
		<ul id="user-menu">
			<#if !is_signed_in>
				<#assign anchorData = {"redirect", portalUtil.isLoginRedirectRequired(request)} />
				<@aui["nav-item"] anchorData=anchorData cssClass="sign-in" href="${themeDisplay.getURLSignIn()}" iconCssClass="icon-user" label="sign-in" />
			<#else>
				<#assign id = themeDisplay.getPortletDisplay().getId() />
				<#assign plid = themeDisplay.getPlid() />
				<#assign portletURL = portletURLFactory.create(request, "49", plid, "ACTION_PHASE") />
				${portletURL.setParameter("struts_action", "/my_sites/view")}
				${portletURL.setParameter("groupId", "" + themeDisplay.getUser().getGroupId())}
				${portletURL.setParameter("privateLayout", "false")}
				${portletURL.setPortletMode("VIEW")}
				${portletURL.setWindowState("NORMAL")}
			
				<#--  ${themeDisplay.isImpersonated()?string("impersonating-user", "")} -->
				<@liferay_util["buffer"] var="userprofile">
					<a href="${portletURL.toString()}">
						<span class="user-full-name">${user.getFullName()}</span>
						<img class="user-avatar-image" src="${user.getPortraitURL(themeDisplay)}" />
					</a>
				</@>
				
				<#--
				<@aui["nav-item"] anchorCssClass="user-avatar-link" cssClass="user-avatar" dropdown=false id="userAvatar" label="${userprofile}">
					<li class="my-sites-menu public-site">
						<#-- <a href="<%= myProfileURL.toString() %>"> ->
						<a href="${portletURL.toString()}">
							<span class="site-name"><@liferay_ui["message"] key="my-profile" /></span> 
						</a>
					</li>
					
					<#if themeDisplay.isShowSignOutIcon()>
						<@aui["nav-item"] cssClass="sign-out" href="${themeDisplay.getURLSignOut()}" iconCssClass="icon-off" label="sign-out" />
					</#if>
					
				</@>
				-->
				
				<@aui["nav-item"] anchorCssClass="user-avatar-link" dropdown=false id="userAvatar" label="${userprofile}" />
				
				<@aui["nav-item"] anchorCssClass="user-avatar-link" href="${portletURL.toString()}" iconCssClass="icon-bell" />
				
				<#if themeDisplay.isShowSignOutIcon()>
					<@aui["nav-item"] anchorCssClass="user-avatar-link" href="${themeDisplay.getURLSignOut()}" iconCssClass="icon-off" />
				</#if>
				
				<#--
				<div class="user-data-container">
					<div class="user-data">
						<div class="user-full-name">
							${user.getFullName()}
						</div>
						<div class="user-actions">
							<a href="/c/portal/logout">Logout</a>
						</div>
					</div>
					<div class="user-image">
						<img src="${user.getPortraitURL(themeDisplay)}" />
					</div>
				</div>
				-->
			</#if>
		</ul>
	</div>
</nav>

<script>
	Liferay.Data.NAV_LIST_SELECTOR = '#navmenu';
</script>