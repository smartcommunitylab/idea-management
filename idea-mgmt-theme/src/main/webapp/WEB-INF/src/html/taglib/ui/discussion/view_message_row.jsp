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

<%@ include file="/html/taglib/ui/discussion/init.jsp" %>

<%
boolean hideControls = GetterUtil.getBoolean((String)request.getAttribute("liferay-ui:discussion:hideControls"));
boolean ratingsEnabled = GetterUtil.getBoolean((String)request.getAttribute("liferay-ui:discussion:ratingsEnabled"));
String permissionClassName = (String)request.getAttribute("liferay-ui:discussion:permissionClassName");
long permissionClassPK = GetterUtil.getLong((String)request.getAttribute("liferay-ui:discussion:permissionClassPK"));
String redirect = (String)request.getAttribute("liferay-ui:discussion:redirect");
long userId = GetterUtil.getLong((String)request.getAttribute("liferay-ui:discussion:userId"));

MBTreeWalker treeWalker = (MBTreeWalker)request.getAttribute(WebKeys.MESSAGE_BOARDS_TREE_WALKER);
MBMessage selMessage = (MBMessage)request.getAttribute(WebKeys.MESSAGE_BOARDS_TREE_WALKER_SEL_MESSAGE);
MBMessage message = (MBMessage)request.getAttribute(WebKeys.MESSAGE_BOARDS_TREE_WALKER_CUR_MESSAGE);
MBCategory category = (MBCategory)request.getAttribute(WebKeys.MESSAGE_BOARDS_TREE_WALKER_CATEGORY);
MBThread thread = (MBThread)request.getAttribute(WebKeys.MESSAGE_BOARDS_TREE_WALKER_THREAD);
boolean lastNode = ((Boolean)request.getAttribute(WebKeys.MESSAGE_BOARDS_TREE_WALKER_LAST_NODE)).booleanValue();
int depth = ((Integer)request.getAttribute(WebKeys.MESSAGE_BOARDS_TREE_WALKER_DEPTH)).intValue();

String cssClass = (String)request.getAttribute("cssClass");
int i = (Integer) request.getAttribute("idx");
List<RatingsEntry> ratingsEntries = (List<RatingsEntry>)request.getAttribute("ratingsEntries");
List<RatingsStats> ratingsStatsList = (List<RatingsStats>)request.getAttribute("ratingsStatsList");
MBMessage rootMessage = (MBMessage)request.getAttribute("rootMessage");

String randomNamespace = renderResponse.getNamespace();
Format dateFormatDateTime = FastDateFormatFactoryUtil.getDateTime(locale, timeZone);
%>

						<div class='lfr-discussion <%= cssClass %>' style='padding-left: <%= depth * 50 %>px;'>
							<div id="<%= randomNamespace %>messageScroll<%= message.getMessageId() %>">
								<a name="<%= renderResponse.getNamespace() %>message_<%= message.getMessageId() %>"></a>

								<aui:input name='<%= "messageId" + i %>' type="hidden" value="<%= message.getMessageId() %>" />
								<aui:input name='<%= "parentMessageId" + i %>' type="hidden" value="<%= message.getMessageId() %>" />
							</div>

							<aui:row fluid="<%= true %>">
								<aui:col cssClass="lfr-discussion-details" width="25">
									<liferay-ui:user-display
										displayStyle="<%= 2 %>"
										userId="<%= message.getUserId() %>"
										userName="<%= HtmlUtil.escape(message.getUserName()) %>"
									/>
								</aui:col>

								<aui:col cssClass="lfr-discussion-body" width="75">
									<c:if test="<%= (message != null) && !message.isApproved() %>">
										<aui:model-context bean="<%= message %>" model="<%= MBMessage.class %>" />

										<div>
											<aui:workflow-status model="<%= MBDiscussion.class %>" status="<%= message.getStatus() %>" />
										</div>
									</c:if>

									<div class="lfr-discussion-message">

										<%
										String msgBody = BBCodeTranslatorUtil.getHTML(message.getBody());

										msgBody = StringUtil.replace(msgBody, "@theme_images_path@/emoticons", themeDisplay.getPathThemeImages() + "/emoticons");
										%>

										<%= msgBody %>
									</div>

									<div class="lfr-discussion-controls">
										<c:if test="<%= ratingsEnabled && !TrashUtil.isInTrash(message.getClassName(), message.getClassPK()) %>">

											<%
											RatingsEntry ratingsEntry = getRatingsEntry(ratingsEntries, message.getMessageId());
											RatingsStats ratingStats = getRatingsStats(ratingsStatsList, message.getMessageId());
											%>

											<liferay-ui:ratings
												className="<%= MBDiscussion.class.getName() %>"
												classPK="<%= message.getMessageId() %>"
												ratingsEntry="<%= ratingsEntry %>"
												ratingsStats="<%= ratingStats %>"
												type="thumbs"
											/>
										</c:if>

										<c:if test="<%= depth < 2 && !hideControls && !TrashUtil.isInTrash(message.getClassName(), message.getClassPK()) %>">
											<ul class="lfr-discussion-actions">
												<c:if test="<%= MBDiscussionPermission.contains(permissionChecker, company.getCompanyId(), scopeGroupId, permissionClassName, permissionClassPK, userId, ActionKeys.ADD_DISCUSSION) %>">
													<li class="lfr-discussion-reply-to">

														<%
														String taglibPostReplyURL = "javascript:" + randomNamespace + "showForm('" + randomNamespace + "postReplyForm" + i + "', '" + namespace + randomNamespace + "postReplyBody" + i + "'); " + randomNamespace + "hideForm('" + randomNamespace + "editForm" + i + "', '" + namespace + randomNamespace + "editReplyBody" + i + "', '" + HtmlUtil.escapeJS(message.getBody()) + "');";
														%>
														<a class="add-comment-link" href="<%= taglibPostReplyURL %>"><liferay-ui:message key="post-reply"/></a>

													</li>
												</c:if>

												<c:if test="<%= i > 0 %>">

													<%
													String taglibTopURL = "#" + randomNamespace + "messages_top";
													%>
												</c:if>
											</ul>
										</c:if>
									</div>
								</aui:col>
							</aui:row>

							<aui:row cssClass="lfr-discussion-form-container" fluid="<%= true %>">
								<div class="lfr-discussion-form lfr-discussion-form-reply span12" id="<%= randomNamespace %>postReplyForm<%= i %>" style='<%= "display: none; max-width: " + ModelHintsConstants.TEXTAREA_DISPLAY_WIDTH + "px;" %>'>

									<aui:input id='<%= randomNamespace + "postReplyBody" + i %>' label="" name='<%= "postReplyBody" + i %>' style='<%= "height: " + ModelHintsConstants.TEXTAREA_DISPLAY_HEIGHT + "px;" %>' type="textarea" wrap="soft" />

									<aui:button-row  cssClass="add-comment-button-row">
										<aui:button cssClass="btn-comment formbutton-primary" id='<%= namespace + randomNamespace + "postReplyButton" + i %>' onClick='<%= randomNamespace + "postReply(" + i + ");" %>' value='<%= LanguageUtil.get(pageContext, "send") %>' />

										<%
										String taglibCancel = randomNamespace + "hideForm('" + randomNamespace + "postReplyForm" + i + "', '" + namespace + randomNamespace + "postReplyBody" + i + "', '');";
										%>

										<aui:button cssClass="btn-comment formbutton-cancel" onClick="<%= taglibCancel %>" type="cancel" />
									</aui:button-row>

								</div>

								<c:if test="<%= !hideControls && MBDiscussionPermission.contains(permissionChecker, company.getCompanyId(), scopeGroupId, permissionClassName, permissionClassPK, message.getMessageId(), message.getUserId(), ActionKeys.UPDATE_DISCUSSION) %>">
									<div class="lfr-discussion-form lfr-discussion-form-edit span12" id="<%= randomNamespace %>editForm<%= i %>" style='<%= "display: none; max-width: " + ModelHintsConstants.TEXTAREA_DISPLAY_WIDTH + "px;" %>'>
										<aui:input id='<%= randomNamespace + "editReplyBody" + i %>' label="" name='<%= "editReplyBody" + i %>' style='<%= "height: " + ModelHintsConstants.TEXTAREA_DISPLAY_HEIGHT + "px;" %>' type="textarea" value="<%= message.getBody() %>" wrap="soft" />

										<%
										boolean pending = message.isPending();

										String publishButtonLabel = LanguageUtil.get(pageContext, "publish");

										if (WorkflowDefinitionLinkLocalServiceUtil.hasWorkflowDefinitionLink(themeDisplay.getCompanyId(), scopeGroupId, MBDiscussion.class.getName())) {
											if (pending) {
												publishButtonLabel = "save";
											}
											else {
												publishButtonLabel = LanguageUtil.get(pageContext, "submit-for-publication");
											}
										}
										%>

										<aui:button-row>
											<aui:button name='<%= randomNamespace + "editReplyButton" + i %>' onClick='<%= randomNamespace + "updateMessage(" + i + ");" %>' value="<%= publishButtonLabel %>" />

											<%
											String taglibCancel = randomNamespace + "hideForm('" + randomNamespace + "editForm" + i + "', '" + namespace + randomNamespace + "editReplyBody" + i + "', '" + HtmlUtil.escapeJS(message.getBody()) + "');";
											%>

											<aui:button onClick="<%= taglibCancel %>" type="cancel" />
										</aui:button-row>
									</div>
								</c:if>
							</aui:row>

							<div class="lfr-discussion-posted-on">
								<%= LanguageUtil.format(pageContext, "posted-on-x", dateFormatDateTime.format(message.getModifiedDate())) %>
							</div>
						</div>

<%
List messages = treeWalker.getMessages();
int[] range = treeWalker.getChildrenRange(message);

depth++;

for (int j = range[0]; j < range[1]; j++) {
	MBMessage curMessage = (MBMessage)messages.get(j);

	boolean lastChildNode = false;

	if ((j + 1) == range[1]) {
		lastChildNode = true;
	}

	request.setAttribute(WebKeys.MESSAGE_BOARDS_TREE_WALKER, treeWalker);
	request.setAttribute(WebKeys.MESSAGE_BOARDS_TREE_WALKER_CATEGORY, category);
	request.setAttribute(WebKeys.MESSAGE_BOARDS_TREE_WALKER_CUR_MESSAGE, curMessage);
	request.setAttribute(WebKeys.MESSAGE_BOARDS_TREE_WALKER_DEPTH, new Integer(depth));
	request.setAttribute(WebKeys.MESSAGE_BOARDS_TREE_WALKER_LAST_NODE, Boolean.valueOf(lastChildNode));
	request.setAttribute(WebKeys.MESSAGE_BOARDS_TREE_WALKER_SEL_MESSAGE, selMessage);
	request.setAttribute(WebKeys.MESSAGE_BOARDS_TREE_WALKER_THREAD, thread);
 	request.setAttribute("cssClass", cssClass);
	request.setAttribute("idx", j);
	request.setAttribute("ratingsEntries", ratingsEntries);
	request.setAttribute("ratingsStatsList", ratingsStatsList);
	request.setAttribute("rootMessage", rootMessage);
%>

	<liferay-util:include page="/html/taglib/ui/discussion/view_message_row.jsp" />

<%
}
%>
<%!
private RatingsEntry getRatingsEntry(List<RatingsEntry> ratingEntries, long classPK) {
	for (RatingsEntry ratingsEntry : ratingEntries) {
		if (ratingsEntry.getClassPK() == classPK) {
			return ratingsEntry;
		}
	}

	return RatingsEntryUtil.create(0);
}

private RatingsStats getRatingsStats(List<RatingsStats> ratingsStatsList, long classPK) {
	for (RatingsStats ratingsStats : ratingsStatsList) {
		if (ratingsStats.getClassPK() == classPK) {
			return ratingsStats;
		}
	}

	return RatingsStatsUtil.create(0);
}
%>