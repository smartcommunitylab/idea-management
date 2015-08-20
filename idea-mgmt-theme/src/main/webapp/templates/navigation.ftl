<#assign liferay_util = PortalJspTagLibs["/WEB-INF/tld/liferay-util.tld"] />
<#assign liferay_ui = PortalJspTagLibs["/WEB-INF/tld/liferay-ui.tld"] />
<#assign aui = PortalJspTagLibs["/WEB-INF/tld/liferay-aui.tld"] />
<#assign portlet = PortalJspTagLibs["http://java.sun.com/portlet_2_0"] />

<nav class="${nav_css_class} navbar" id="navigation" role="navigation">
	<div class="navbar-inner">
		<div class="navbar-row right">
			<div id="user-menu">
			  <div style="display:none;"> 
        <#assign locPortletId = "userprofile_WAR_ideamanagement" />
        <#assign PortletPreferencesFactoryUtil = staticUtil["com.liferay.portlet.PortletPreferencesFactoryUtil"] />
        ${theme.runtime(locPortletId, "hidden=true", "")} 
        </div>
				<ul>

					<#if !is_signed_in>
						<#assign anchorData = {"redirect", portalUtil.isLoginRedirectRequired(request)} />
						<@aui["nav-item"] anchorData=anchorData cssClass="sign-in" anchorCssClass="use-dialog" title="Accedi" href="${themeDisplay.getURLSignIn()}" iconCssClass="ftn-log-in" label="Accedi" />
					<#else>
						<#assign id = themeDisplay.getPortletDisplay().getId() />
						<#assign plid = themeDisplay.getPlid() />
						<#assign profileURL = portletURLFactory.create(request, "userprofile_WAR_ideamanagement", plid, "ACTION_PHASE") />
						<#--${profileURL.setParameter("struts_action", "/my_sites/view")} -->
						${profileURL.setParameter("groupId", "" + themeDisplay.getUser().getGroupId())}
						<#--${profileURL.setParameter("privateLayout", "false")}-->
						${profileURL.setPortletMode("VIEW")}
						${profileURL.setWindowState("MAXIMIZED")}
						
						<#assign myAccountURL = themeDisplay.getURLMyAccount().toString() + "?controlPanelCategory=my"/>
						<#-- <#assign myAccountURL = HttpUtil.setParameter(myAccountURL, "controlPanelCategory", PortletCategoryKeys.MY); -->
					
						<#--  ${themeDisplay.isImpersonated()?string("impersonating-user", "")} -->
						<@liferay_util["buffer"] var="userprofile">
							<span class="user-full-name">${user.getFullName()}</span>
							<img class="user-avatar-image" src="${user.getPortraitURL(themeDisplay)}" />
						</@>
						
						<#--
						<@aui["nav-item"] cssClass="user-avatar" dropdown=false id="userAvatar" label="${userprofile}">
							<li class="my-sites-menu public-site">
								<#-- <a href="<%= myProfileURL.toString() %>"> ->
								<a href="${profileURL.toString()}">
									<span class="site-name"><@liferay_ui["message"] key="my-profile" /></span> 
								</a>
							</li>
							
							<#if themeDisplay.isShowSignOutIcon()>
								<@aui["nav-item"] cssClass="sign-out" href="${themeDisplay.getURLSignOut()}" iconCssClass="icon-off" label="sign-out" />
							</#if>
							
						</@>
						-->
						<#assign UGRS = staticUtil["com.liferay.portal.service.UserGroupRoleLocalServiceUtil"] />
	                    <#assign label = languageUtil.get(locale,"control-panel-my") />
						<@aui["nav-item"] href="${profileURL.toString()}" id="userAvatar" label="${userprofile}" dropdown=false />
						<#if UGRS.hasUserGroupRole(themeDisplay.getUserId(), themeDisplay.getScopeGroupId(),"Moderator") || UGRS.hasUserGroupRole(themeDisplay.getUserId(), themeDisplay.getScopeGroupId(),"Manager")>
						<@aui["nav-item"] href="${myAccountURL}" id="userAccount" label="${label}" useDialog=true title="${label}" />
						</#if>
						<#-- useDialog="<%= PropsValues.DOCKBAR_ADMINISTRATIVE_LINKS_SHOW_IN_POP_UP %>"-->
						
						<!-- @aui["nav-item"] href="${profileURL.toString()}" id="userNotifications" iconCssClass="ftn-notification" /> -->
						
						<#if themeDisplay.isShowSignOutIcon()>
							<@aui["nav-item"] href="${themeDisplay.getURLSignOut()}" id="userLogout" iconCssClass="ftn-log-out" />
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
				<ul id="search">
					<@aui["nav-item"] href="#" id="searchtoggle" iconCssClass="icon-search" />
					
					<li id="searchform">
						<@liferay_ui["search"] />
					</li>
					
					<script>
						var element = document.getElementById('searchform');
						document.getElementById('searchtoggle').onclick = function() {
							if (element.classList.contains('shown')) {
								element.classList.remove('shown');
								console.log('shown removed');
							} else {
								element.classList.add('shown');
								console.log('shown added');
							}
						};
					</script>
				</ul>
			</div>
		</div>
		<div class="navbar-row">
			<ul id="navmenu" aria-label="<@liferay.language key="site-pages" />" role="menubar" class="nav">
				<#function alignitem item>
					<#if (item?lower_case == "progetto" || item?lower_case == "piattaforma")>
						<#return "right" />
					<#else>
						<#return "" />
					</#if>
				</#function>
				
				<#function haschildren item>
					<#if item.hasChildren()>
						<#return "has-child-menu" />
					<#else>
						<#return "" />
					</#if>
				</#function>
					
				<#list nav_items as nav_item>
					<#if nav_item.getLayout().getExpandoBridge().hasAttribute(footer) && nav_item.getLayout().getExpandoBridge().getAttribute(footer, false) == false>
						<#assign nav_item_attr_has_popup = "" />
						<#assign nav_item_attr_selected = "" />
						<#assign nav_item_css_class = "" />
			
						<#if nav_item.isSelected()>
							<#assign nav_item_attr_has_popup = "aria-haspopup='true'" />
							<#assign nav_item_attr_selected = "aria-selected='true'" />
							<#assign nav_item_css_class = "selected" />
						</#if>					
			
						<li ${nav_item_attr_selected} class="${nav_item_css_class} ${alignitem(nav_item.getName())} ${haschildren(nav_item)}" id="layout_${nav_item.getLayoutId()}" role="presentation">
							<a aria-labelledby="layout_${nav_item.getLayoutId()}" ${nav_item_attr_has_popup} href="${nav_item.getURL()}" ${nav_item.getTarget()} role="menuitem">
								<span>${nav_item.icon()} ${nav_item.getName()}</span>
							</a>
			
							<#if nav_item.hasChildren()>
								<ul class="child-menu" role="menu">
									<#list nav_item.getChildren() as nav_child>
										<#assign nav_child_attr_selected = "" />
										<#assign nav_child_css_class = "" />
			
										<#if nav_child.isSelected()>
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
					</#if>
				</#list>
			</ul>
		</div>
	</div>
</nav>

<script>
	Liferay.Data.NAV_LIST_SELECTOR = '#navmenu';
</script>
