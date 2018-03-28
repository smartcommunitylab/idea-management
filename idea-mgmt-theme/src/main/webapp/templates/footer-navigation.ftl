<#assign liferay_util = PortalJspTagLibs["/WEB-INF/tld/liferay-util.tld"] />
<#assign liferay_ui = PortalJspTagLibs["/WEB-INF/tld/liferay-ui.tld"] />
<#assign aui = PortalJspTagLibs["/WEB-INF/tld/liferay-aui.tld"] />
<#assign portlet = PortalJspTagLibs["http://java.sun.com/portlet_2_0"] />

<nav class="${nav_css_class} navbar" id="footer-navigation" role="navigation">
	<div class="navbar-inner">
		<div class="navbar-row">
			<#-- <@liferay.language key="powered-by" /> -->
			<div class="powered-by">
				<a href="http://www.smartcommunitylab.it" rel="external">&copy; 2015 Smart Community Lab - Fondazione Bruno Kessler</a>
        <div style="float:right;">
        <div style="display: inline-block; max-width: 600px; color: #fff; vertical-align: middle; line-height: 1.5">STARDUST è un progetto co-finanziato dal programma di ricerca e innovazione dell'Unione Europea Horizon 2020. Accordo di sovvenzione 774094</div>
        <img src="${images_folder}/loghi/eu.png" style="height:60px">
        </div>
			</div>
			<ul id="footer-navmenu" aria-label="<@liferay.language key="site-pages" />" role="menubar" class="nav">					
				<#list nav_items as nav_item>
					<#if nav_item.getLayout().getExpandoBridge().hasAttribute(footer) && nav_item.getLayout().getExpandoBridge().getAttribute(footer, false) == true>
						<#assign nav_item_attr_has_popup = "" />
						<#assign nav_item_attr_selected = "" />
						<#assign nav_item_css_class = "" />
			
						<#if nav_item.isSelected()>
							<#assign nav_item_attr_has_popup = "aria-haspopup='true'" />
							<#assign nav_item_attr_selected = "aria-selected='true'" />
							<#assign nav_item_css_class = "selected" />
						</#if>					
			
						<li ${nav_item_attr_selected} class="${nav_item_css_class} right" id="layout_${nav_item.getLayoutId()}" role="presentation">
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
