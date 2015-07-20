<%@page import="com.liferay.portal.service.GroupLocalServiceUtil"%>
<%@ taglib prefix="portlet" uri="http://java.sun.com/portlet_2_0"%>
<%@ taglib prefix="liferay-ui" uri="http://liferay.com/tld/ui"%>

<%@ include file="/html/common-init.jsp"%>

<%@ page import="com.liferay.portal.model.User"%>
<%@ page import="com.liferay.portal.model.Group"%>
<%@ page import="com.liferay.portal.service.UserLocalServiceUtil"%>

<%
	User selUser = null;
	Group group = GroupLocalServiceUtil.getGroup(themeDisplay.getScopeGroupId());
	long selUserId = group.getCreatorUserId();
	selUser = UserLocalServiceUtil.getUser(selUserId);
%>
<div class="userprofile">
	<c:if test="<%=selUser != null%>">
		<c:choose>
			<c:when test="<%=selUserId != user.getUserId()%>">
				<!-- READ ONLY -->
				<div class="container">
					<div class="row">
						<div class="span12">
							<img class="avatar"
								src="<%=selUser.getPortraitURL(themeDisplay).toString()%>" />
						</div>
					</div>
					<div class="row">
						<div class="span6">
							<div class="entry">
								<h5 class="field">Nome e cognome</h5>
								<p class="value"><%=selUser.getFullName()%></p>
							</div>
							<div class="entry">
								<h5 class="field">Email</h5>
								<p class="value"><%=selUser.getEmailAddress()%></p>
							</div>
							<div class="entry">
								<h5 class="field">Genere</h5>
								<p class="value">
									<c:choose>
										<c:when test="<%=selUser.getMale()%>">
									Maschio
								</c:when>
										<c:when test="<%=selUser.getFemale()%>">
									Femmina
								</c:when>
										<c:otherwise>
									Non specificato
								</c:otherwise>
									</c:choose>
								</p>
							</div>
						</div>
						<div class="span6">
							<h5 class="field">Lavoro</h5>
							<p class="value"><%=selUser.getJobTitle()%></p>
						</div>
					</div>
				</div>
			</c:when>
			<c:otherwise>
				<!-- FORM -->
				<div class="container-fluid">
					<div class="row-fluid">
						<div class="span12">
							<img src="<%=selUser.getPortraitURL(themeDisplay).toString()%>" />
						</div>
					</div>
					<div class="row-fluid">
						<div class="span6">col1</div>
						<div class="span6">col2</div>
					</div>
				</div>

				<%-- <liferay-ui:tabs names="Profilo, Notifiche, Attivita, Punteggi"
			refresh="<%=false%>">
			<liferay-ui:section>
				Profilo
			</liferay-ui:section>
			<liferay-ui:section>
				Notifiche
			</liferay-ui:section>
			<liferay-ui:section>
				Attivita
			</liferay-ui:section>
			<liferay-ui:section>
				Punteggi
			</liferay-ui:section>
		</liferay-ui:tabs> --%>
			</c:otherwise>
		</c:choose>
	</c:if>
</div>