<#assign liferay_util = PortalJspTagLibs["/WEB-INF/tld/liferay-util.tld"] />
<#assign liferay_ui = PortalJspTagLibs["/WEB-INF/tld/liferay-ui.tld"] />
<#assign aui = PortalJspTagLibs["/WEB-INF/tld/liferay-aui.tld"] />
<#assign portlet = PortalJspTagLibs["http://java.sun.com/portlet_2_0"] />

<div id="nav-toggle" class="btn addidea-button">
	MENU
</div>

<nav class="${nav_css_class} navbar" id="navigation" role="navigation">
	<div class="navbar-inner">
		<div class="navbar-row right">
			<div id="user-menu" role="menu">
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
				<ul id="searchbar">
					<@aui["nav-item"] href="#" id="searchtoggle" iconCssClass="icon-search" />
					
					<li id="searchform">
						<@liferay_ui["search"] id="prova"/>
					</li>
	     <!-- mettere script -->
				</ul>
			</div>
		</div>
		<div class="navbar-row">
			<ul id="navmenu" role="menubar" class="nav">
				<#function alignitem item>
					<#if (item?lower_case == "obiettivi prg" || item?lower_case == "aiuto" || item?lower_case == "contatti")>
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
        <li class="nav-icon right">
              <a href="http://comune.trento.it" target="_blank" >
                <span><img style="max-height: 75px; margin-right: 0px;" src="${images_folder}/loghi/logo.jpg"></span>
              </a>
        </li>					
				<#list nav_items as nav_item>
					<#if nav_item.getLayout().getExpandoBridge().hasAttribute(footer) && nav_item.getLayout().getExpandoBridge().getAttribute(footer, false) == false>
						<#assign nav_item_attr_has_popup = "" />
						<#assign nav_item_attr_selected = "" />
						<#assign nav_item_css_class = "" />
			
						<#if nav_item.isSelected()>
							<#assign nav_item_attr_has_popup = "" />
							<#assign nav_item_attr_selected = "" />
							<#assign nav_item_css_class = "selected" />
						</#if>					
			
						<li ${nav_item_attr_selected} class="${nav_item_css_class} ${alignitem(nav_item.getName())} ${haschildren(nav_item)}" id="layout_${nav_item.getLayoutId()}">
							<a aria-labelledby="layout_${nav_item.getLayoutId()}" ${nav_item_attr_has_popup} href="${nav_item.getURL()}" ${nav_item.getTarget()} >
								<span>${nav_item.icon()} ${nav_item.getName()}</span>
							</a>
			
							<#if nav_item.hasChildren()>
								<ul class="child-menu">
									<#list nav_item.getChildren() as nav_child>
										<#assign nav_child_attr_selected = "" />
										<#assign nav_child_css_class = "" />
			
										<#if nav_child.isSelected()>
											<#assign nav_child_attr_selected = "" />
											<#assign nav_child_css_class = "selected" />
										</#if>
			
										<li ${nav_child_attr_selected} class="${nav_child_css_class}" id="layout_${nav_child.getLayoutId()}" >
											<a aria-labelledby="layout_${nav_child.getLayoutId()}" href="${nav_child.getURL()}" ${nav_child.getTarget()} >
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
<ul class="rainbow">
	<li class="rainbow-red"></span>
	<li class="rainbow-orange"></span>
	<li class="rainbow-yellow"></span>
	<li class="rainbow-greenlight"></span>
	<li class="rainbow-greendark"></span>
	<li class="rainbow-bluedark"></span>
	<li class="rainbow-bluelight"></span>
</ul>


<script>
	Liferay.Data.NAV_LIST_SELECTOR = '#navmenu';
</script>
