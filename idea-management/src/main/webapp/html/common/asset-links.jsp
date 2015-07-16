<%--
/**
 * Copyright (c) 2000-2013 Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */
--%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ taglib uri="http://liferay.com/tld/theme" prefix="liferay-theme" %>
<%@ taglib uri="http://liferay.com/tld/ui" prefix="liferay-ui" %>
<%@ taglib uri="http://liferay.com/tld/util" prefix="liferay-util" %>

<%@
page import="com.liferay.portal.kernel.language.LanguageUtil" %><%@
page import="com.liferay.portal.kernel.portlet.LiferayWindowState" %><%@
page import="com.liferay.portal.kernel.servlet.SessionErrors" %><%@
page import="com.liferay.portal.kernel.util.FriendlyURLNormalizerUtil" %><%@
page import="com.liferay.portal.kernel.util.GetterUtil" %><%@
page import="com.liferay.portal.kernel.util.HtmlUtil" %><%@
page import="com.liferay.portal.kernel.util.JavaConstants" %><%@
page import="com.liferay.portal.kernel.util.ParamUtil" %><%@
page import="com.liferay.portal.kernel.util.StringPool" %><%@
page import="com.liferay.portal.kernel.util.StringUtil" %><%@
page import="com.liferay.portal.kernel.util.UnicodeFormatter" %><%@
page import="com.liferay.portal.kernel.util.Validator" %><%@
page import="com.liferay.portlet.documentlibrary.model.DLFileEntry" %><%@
page import="com.liferay.portal.model.*" %><%@
page import="com.liferay.portal.service.*" %><%@
page import="com.liferay.portal.util.PortalUtil" %><%@
page import="com.liferay.portal.util.PortletKeys" %><%@
page import="com.liferay.portlet.PortletURLFactoryUtil" %><%@
page import="com.liferay.portlet.asset.AssetRendererFactoryRegistryUtil" %><%@
page import="com.liferay.portlet.asset.model.AssetEntry" %><%@
page import="com.liferay.portlet.asset.model.AssetRendererFactory" %><%@
page import="com.liferay.portlet.asset.model.AssetRenderer" %><%@
page import="com.liferay.portlet.asset.service.AssetEntryLocalServiceUtil" %><%@
page import="com.liferay.portlet.asset.service.AssetEntryServiceUtil" %><%@ 
page import="java.util.ArrayList" %><%@
page import="java.util.HashMap" %><%@
page import="java.util.List" %><%@
page import="java.util.Map" %><%@ 
page import="javax.portlet.PortletMode" %><%@
page import="javax.portlet.PortletRequest" %><%@
page import="javax.portlet.PortletResponse" %><%@
page import="javax.portlet.PortletURL" %>

<liferay-theme:defineObjects />

<%@ page import="com.liferay.portlet.asset.model.AssetLink" %>
<%@ page import="com.liferay.portlet.asset.service.AssetLinkLocalServiceUtil" %>
<%@ page import="com.liferay.portal.kernel.util.DateFormatFactoryUtil" %>
<%@ page import="com.liferay.taglib.aui.AUIUtil" %>
<%@ page import="com.liferay.taglib.util.InlineUtil" %>

<%
PortletRequest portletRequest = (PortletRequest)request.getAttribute(JavaConstants.JAVAX_PORTLET_REQUEST);

PortletResponse portletResponse = (PortletResponse)request.getAttribute(JavaConstants.JAVAX_PORTLET_RESPONSE);

String namespace = StringPool.BLANK;

boolean auiFormUseNamespace = GetterUtil.getBoolean((String)request.getAttribute("aui:form:useNamespace"), true);

if ((portletResponse != null) && auiFormUseNamespace) {
  namespace = GetterUtil.getString(request.getAttribute("aui:form:portletNamespace"), portletResponse.getNamespace());
}

String currentURL = PortalUtil.getCurrentURL(request);
%>

<%
String randomNamespace = PortalUtil.generateRandomKey(request, "taglib_ui_input_asset_links_page") + StringPool.UNDERLINE;

String eventName = randomNamespace + "selectAsset";

long assetEntryId = GetterUtil.getLong((String)request.getAttribute("liferay-ui:input-asset-links:assetEntryId"));
String className = (String)request.getAttribute("liferay-ui:input-asset-links:className");

List<AssetLink> assetLinks = new ArrayList<AssetLink>();
AssetRendererFactory assetRendererFactory = AssetRendererFactoryRegistryUtil.getAssetRendererFactoryByClassName(DLFileEntry.class.getName());

if (assetEntryId > 0) {
  List<AssetLink> directAssetLinks = AssetLinkLocalServiceUtil.getDirectLinks(assetEntryId);

  for (AssetLink assetLink : directAssetLinks) {
    AssetEntry assetLinkEntry = null;

    if ((assetEntryId > 0) || (assetLink.getEntryId1() == assetEntryId)) {
      assetLinkEntry = AssetEntryLocalServiceUtil.getEntry(assetLink.getEntryId2());
    }
    else {
      assetLinkEntry = AssetEntryLocalServiceUtil.getEntry(assetLink.getEntryId1());
    }

    AssetRendererFactory linkRendererFactory = AssetRendererFactoryRegistryUtil.getAssetRendererFactoryByClassName(assetLinkEntry.getClassName());

    if (assetRendererFactory == linkRendererFactory) {
      assetLinks.add(assetLink);
    }
  }
}

long controlPanelPlid = PortalUtil.getControlPanelPlid(company.getCompanyId());

Group scopeGroup = GroupLocalServiceUtil.getGroup(scopeGroupId);

boolean stagedLocally = scopeGroup.isStaged() && !scopeGroup.isStagedRemotely();
boolean stagedReferrerPortlet = false;

if (stagedLocally) {
  AssetRendererFactory referrerAssetRendererFactory = AssetRendererFactoryRegistryUtil.getAssetRendererFactoryByClassName(className);

  stagedReferrerPortlet = scopeGroup.isStagedPortlet(referrerAssetRendererFactory.getPortletId());
}

PortletURL assetBrowserURL = PortletURLFactoryUtil.create(request, PortletKeys.ASSET_BROWSER, controlPanelPlid, PortletRequest.RENDER_PHASE);

assetBrowserURL.setParameter("struts_action", "/asset_browser/view");
assetBrowserURL.setParameter("eventName", eventName);
assetBrowserURL.setPortletMode(PortletMode.VIEW);
assetBrowserURL.setWindowState(LiferayWindowState.POP_UP);
%>
<script type="text/javascript">
function rem(id) {
    var hiddenId = '<%= portletResponse.getNamespace() %>assetLinksSearchContainerPrimaryKeys';
    var hidden = document.getElementById(hiddenId);
    
    var assetLinksContainerId = '<%= portletResponse.getNamespace() %>assetLinksContainer';
    var assetLinksContainer = document.getElementById(assetLinksContainerId);

    var vals = hidden.value.split(',');
    var newVals = [];
    var index = 0;
    for (var i = 0; i < vals.length;i++) {
      if (vals[i] != id) {
    	  newVals.push(vals[i]);
      } else {
    	  index = i;
      }
    }
    hidden.value = newVals.join(',');

    var cntr = -1;
    for (var i = 0; i < assetLinksContainer.childNodes.length;i++) {
    	if (assetLinksContainer.childNodes[i].tagName && "LI" == assetLinksContainer.childNodes[i].tagName.toUpperCase()) cntr++;
    	if (cntr == index) {
    	    assetLinksContainer.removeChild(assetLinksContainer.childNodes[i]);
    	    return;
    	}
    }
 };
</script>

<liferay-ui:icon-menu cssClass="select-existing-selector" icon='<%= themeDisplay.getPathThemeImages() + "/common/search.png" %>' id='<%= randomNamespace + "inputAssetLinks" %>' message="select">

  <%
  if (assetEntryId > 0) {
    assetBrowserURL.setParameter("refererAssetEntryId", String.valueOf(assetEntryId));
  }

  long groupId = scopeGroupId;

  if (stagedLocally) {
    boolean stagedReferencePortlet = scopeGroup.isStagedPortlet(assetRendererFactory.getPortletId());

    if (stagedReferrerPortlet && !stagedReferencePortlet) {
      groupId = scopeGroup.getLiveGroupId();
    }
  }

  assetBrowserURL.setParameter("groupId", String.valueOf(groupId));
  assetBrowserURL.setParameter("selectedGroupIds", themeDisplay.getCompanyGroupId() + "," + groupId);
  assetBrowserURL.setParameter("typeSelection", assetRendererFactory.getClassName());

  Map<String, Object> data = new HashMap<String, Object>();

  data.put("href", assetBrowserURL.toString());
  data.put("title", LanguageUtil.format(pageContext, "select-x", assetRendererFactory.getTypeName(locale, false)));

  String type = assetRendererFactory.getTypeName(locale, false);

  data.put("type", assetRendererFactory.getClassName());
  %>
      <button type="button" class="btn addidea-button edit-assetlinks-button">
      <liferay-ui:icon
        cssClass="asset-selector"
        iconCssClass="icon icon-folder-open"
        data="<%= data %>"
        message=""
        id="<%= FriendlyURLNormalizerUtil.normalize(type) %>"
        url="javascript:;"
      />
      </button>

</liferay-ui:icon-menu>


    <ul class="asset-links-list" id="<%= portletResponse.getNamespace() %>assetLinksContainer">

      <%
      List<Long> ids = new ArrayList<Long>();
      for (AssetLink assetLink : assetLinks) {
        AssetEntry assetLinkEntry = null;

        if (assetLink.getEntryId1() == assetEntryId) {
          assetLinkEntry = AssetEntryLocalServiceUtil.getEntry(assetLink.getEntryId2());
        }
        else {
          assetLinkEntry = AssetEntryLocalServiceUtil.getEntry(assetLink.getEntryId1());
        }

        if (!assetLinkEntry.isVisible()) {
          continue;
        }
        
        ids.add(assetLinkEntry.getEntryId());
        assetLinkEntry = assetLinkEntry.toEscapedModel();

        AssetRenderer assetRenderer = assetRendererFactory.getAssetRenderer(assetLinkEntry.getClassPK());

        if (assetRenderer.hasViewPermission(permissionChecker)) {
          String asseLinktEntryTitle = assetLinkEntry.getTitle(locale);
      %>

          <li class="asset-links-list-item">
            <%= asseLinktEntryTitle %>
            <a class="remove-link textboxlistentry-remove" onClick="rem('<%=assetLinkEntry.getEntryId() %>')">
              <i class="icon icon-remove"></i>
            </a>  
          </li>

      <%
        }
      }
      %>

    </ul>
<aui:input name="assetLinksSearchContainerPrimaryKeys" type="hidden" value="<%= StringUtil.merge(ids) %>"/>

<aui:script use="aui-base,escape,liferay-search-container">
  A.getBody().delegate(
    'click',
    function(event) {
      event.preventDefault();

      Liferay.Util.selectEntity(
        {
          dialog: {
            constrain: true,
            modal: true
          },
          eventName: '<%= eventName %>',
          id: '<%= eventName %>' + event.currentTarget.attr('id'),
          title: event.currentTarget.attr('data-title'),
          uri: event.currentTarget.attr('data-href')
        },
        function(event) {
          var assetLinksContainerId = '<%= portletResponse.getNamespace() %>assetLinksContainer';
          var assetLinksContainer = document.getElementById(assetLinksContainerId);
          var entryLink = '<li class="asset-links-list-item">'+A.Escape.html(event.assettitle) +
                          '<a class="remove-link textboxlistentry-remove" data-rowId="' + event.assetentryid + '" onClick="rem('+event.assetentryid+')">'+
                          '<i class="icon icon-remove"</i></a>'+
                          '</li>';
          var wrapper= document.createElement('div');
          wrapper.innerHTML = entryLink;
          assetLinksContainer.appendChild(wrapper.firstChild);
          var hiddenId = '<%= portletResponse.getNamespace() %>assetLinksSearchContainerPrimaryKeys';
          var hidden = document.getElementById(hiddenId);
          var vals = hidden.value.split(',');
          for (var i = 0; i < vals.length;i++) {
              if (vals[i] == event.assetentryid) return;
            }
          vals.push(event.assetentryid);
          hidden.value = vals.join(',');
          
        }
      );
    },
    '.asset-selector a'
  );
</aui:script>
