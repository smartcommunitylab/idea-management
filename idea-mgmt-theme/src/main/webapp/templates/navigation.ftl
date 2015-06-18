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
		<div id="sign-in">
			<#if !is_signed_in>
				<a href="${sign_in_url}" data-redirect="${is_login_redirect_required?string}" idrel="nofollow">${sign_in_text}</a>
			<#else>
				<#--  ${themeDisplay.isImpersonated()?string("impersonating-user", "")} -->
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
			</#if>
		</div>
	</div>
</nav>

<script>
	Liferay.Data.NAV_LIST_SELECTOR = '#navmenu';
</script>