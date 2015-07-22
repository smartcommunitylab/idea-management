package it.smartcommunitylab.platform.idea.service.impl;

import it.smartcommunitylab.platform.idea.NoSuchIdeaException;
import it.smartcommunitylab.platform.idea.beans.IdeaBean;
import it.smartcommunitylab.platform.idea.model.Call;
import it.smartcommunitylab.platform.idea.model.Idea;
import it.smartcommunitylab.platform.idea.portlet.Constants;
import it.smartcommunitylab.platform.idea.service.base.IdeaLocalServiceBaseImpl;
import it.smartcommunitylab.platform.idea.service.persistence.IdeaFinderUtil;
import it.smartcommunitylab.platform.idea.workflow.WorkflowUtil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.search.Indexer;
import com.liferay.portal.kernel.search.IndexerRegistryUtil;
import com.liferay.portal.kernel.util.ContentTypes;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import com.liferay.portal.kernel.workflow.WorkflowHandlerRegistryUtil;
import com.liferay.portal.kernel.xml.Element;
import com.liferay.portal.kernel.xml.SAXReaderUtil;
import com.liferay.portal.kernel.xml.XPath;
import com.liferay.portal.model.Group;
import com.liferay.portal.model.GroupConstants;
import com.liferay.portal.model.ResourceConstants;
import com.liferay.portal.model.Role;
import com.liferay.portal.model.User;
import com.liferay.portal.service.GroupLocalServiceUtil;
import com.liferay.portal.service.RoleLocalServiceUtil;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.portal.util.PortalUtil;
import com.liferay.portlet.asset.model.AssetEntry;
import com.liferay.portlet.asset.model.AssetLinkConstants;
import com.liferay.portlet.asset.model.AssetTag;
import com.liferay.portlet.asset.service.AssetEntryLocalServiceUtil;
import com.liferay.portlet.asset.service.persistence.AssetEntryQuery;
import com.liferay.portlet.journal.model.JournalArticle;
import com.liferay.portlet.journal.model.JournalStructure;
import com.liferay.portlet.journal.service.JournalArticleLocalServiceUtil;
import com.liferay.portlet.journal.service.JournalStructureLocalServiceUtil;
import com.liferay.util.portlet.PortletProps;

/**
 * The implementation of the idea local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are
 * added, rerun ServiceBuilder to copy their definitions into the
 * {@link it.smartcommunitylab.platform.idea.service.IdeaLocalService}
 * interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security
 * checks based on the propagated JAAS credentials because this service can only
 * be accessed from within the same VM.
 * </p>
 *
 * @author mirko perillo
 * @see it.smartcommunitylab.platform.idea.service.base.IdeaLocalServiceBaseImpl
 * @see it.smartcommunitylab.platform.idea.service.IdeaLocalServiceUtil
 */
public class IdeaLocalServiceImpl extends IdeaLocalServiceBaseImpl {

	/*
	 * NOTE FOR DEVELOPERS:
	 * 
	 * Never reference this interface directly. Always use {@link
	 * it.smartcommunitylab.platform.idea.service.IdeaLocalServiceUtil} to
	 * access the idea local service.
	 */

	public Idea addIdea(long userId, IdeaBean ideaBean,
			ServiceContext serviceContext) throws SystemException,
			PortalException {
		long groupId = serviceContext.getScopeGroupId();

		User user = userPersistence.findByPrimaryKey(userId);

		Date now = new Date();

		// validate(name);

		long pkId = counterLocalService.increment();

		Idea idea = ideaPersistence.create(pkId);

		Group group = GroupLocalServiceUtil.addGroup(userId, 0L, null, 0L, 0L,
				ideaBean.getTitle() + " - " + pkId, null,
				GroupConstants.TYPE_SITE_PRIVATE, false, 0, null, true, true,
				serviceContext);
		GroupLocalServiceUtil.addUserGroup(userId, group.getGroupId());

		long[] assetCategoryIds = serviceContext.getAssetCategoryIds();

		idea.setUuid(serviceContext.getUuid());
		idea.setUserId(userId);
		idea.setGroupId(groupId);
		idea.setCompanyId(user.getCompanyId());
		idea.setUserName(user.getFullName());
		idea.setCreateDate(serviceContext.getCreateDate(now));
		idea.setModifiedDate(serviceContext.getModifiedDate(now));
		idea.setExpandoBridgeAttributes(serviceContext);
		idea.setUserGroupId(group.getGroupId());
		idea.setCategoryIds(StringUtil.merge(assetCategoryIds));

		idea.setTitle(ideaBean.getTitle());
		idea.setShortDesc(ideaBean.getShortDesc());
		idea.setLongDesc(ideaBean.getLongDesc());
		idea.setCallId(ideaBean.getCallId());

		idea.setState(Constants.IDEA_STATE_PROPOSED);
		idea.setDiscussionLimit(ideaBean.getDiscussionLimit());
		idea.setDeadlineConstraints(ideaBean.getDeadlineConstraints());

		idea.setStatus(WorkflowConstants.STATUS_DRAFT);

		ideaPersistence.update(idea);

		resourceLocalService.addResources(user.getCompanyId(), groupId, userId,
				Idea.class.getName(), pkId, false, true, true);

		AssetEntry assetEntry = assetEntryLocalService.updateEntry(userId,
				groupId, idea.getCreateDate(), idea.getModifiedDate(),
				Idea.class.getName(), pkId, idea.getUuid(), 0,
				assetCategoryIds, serviceContext.getAssetTagNames(), true,
				null, null, null, ContentTypes.TEXT_HTML, idea.getTitle(),
				idea.getLongDesc(), idea.getShortDesc(), null, null, 0, 0,
				null, false);

		CalendarUtils.createCalendar(group.getGroupId(), userId, group.getName(), serviceContext, assetEntry.getClassTypeId(), assetEntry.getClassPK(), assetEntry.getClassUuid());

		assetLinkLocalService.updateLinks(userId, assetEntry.getEntryId(),
				serviceContext.getAssetLinkEntryIds(),
				AssetLinkConstants.TYPE_RELATED);

		Indexer indexer = IndexerRegistryUtil.nullSafeGetIndexer(Idea.class);

		indexer.reindex(idea);

		serviceContext = WorkflowUtil.addWorkflowVars(serviceContext);

		WorkflowHandlerRegistryUtil.startWorkflowInstance(idea.getCompanyId(),
				idea.getGroupId(), idea.getUserId(), Idea.class.getName(),
				idea.getPrimaryKey(), idea, serviceContext);
		return idea;
	}

	public void updateIdea(long userId, IdeaBean ideaBean,
			ServiceContext serviceContext) throws SystemException,
			PortalException {

		try {
			long[] assetCategoryIds = serviceContext.getAssetCategoryIds();

			Idea idea = ideaPersistence.findByPrimaryKey(ideaBean.getId());
			idea.setTitle(ideaBean.getTitle());
			idea.setLongDesc(ideaBean.getLongDesc());
			idea.setShortDesc(ideaBean.getShortDesc());

			idea.setDiscussionLimit(ideaBean.getDiscussionLimit());
			idea.setDeadlineConstraints(ideaBean.getDeadlineConstraints());
			idea.setCategoryIds(StringUtil.merge(assetCategoryIds));

			ideaPersistence.update(idea);

			resourceLocalService.updateResources(serviceContext.getCompanyId(),
					serviceContext.getScopeGroupId(), idea.getTitle(),
					idea.getIdeaId(), serviceContext.getGroupPermissions(),
					serviceContext.getGuestPermissions());

			AssetEntry assetEntry = assetEntryLocalService.updateEntry(
					idea.getUserId(), idea.getGroupId(), idea.getCreateDate(),
					idea.getModifiedDate(), Idea.class.getName(),
					idea.getIdeaId(), idea.getUuid(), 0, assetCategoryIds,
					serviceContext.getAssetTagNames(), true, null, null, null,
					ContentTypes.TEXT_HTML, idea.getTitle(),
					idea.getLongDesc(), idea.getShortDesc(), null, null, 0, 0,
					null, false);

			assetLinkLocalService.updateLinks(serviceContext.getUserId(),
					assetEntry.getEntryId(),
					serviceContext.getAssetLinkEntryIds(),
					AssetLinkConstants.TYPE_RELATED);

			Indexer indexer = IndexerRegistryUtil
					.nullSafeGetIndexer(Idea.class);
			indexer.reindex(idea);
		} catch (NoSuchIdeaException e) {

		}
	}

	public void deleteIdea(long userId, IdeaBean ideaBean,
			ServiceContext serviceContext) throws SystemException,
			PortalException {

		try {
			Idea idea = ideaPersistence.remove(ideaBean.getId());
			Indexer indexer = IndexerRegistryUtil.nullSafeGetIndexer(Idea.class
					.getClass());
			indexer.delete(idea);

			GroupLocalServiceUtil
					.deleteUserGroup(userId, idea.getUserGroupId());
			GroupLocalServiceUtil.deleteGroup(idea.getUserGroupId());
			CalendarUtils.deleteCalendar(idea.getUserGroupId());

			resourceLocalService.deleteResource(serviceContext.getCompanyId(),
					Idea.class.getName(), ResourceConstants.SCOPE_INDIVIDUAL,
					idea.getIdeaId());

			AssetEntry assetEntry = assetEntryLocalService.fetchEntry(
					Idea.class.getName(), idea.getIdeaId());
			assetLinkLocalService.deleteLinks(assetEntry.getEntryId());
			assetEntryLocalService.deleteEntry(assetEntry);

		} catch (NoSuchIdeaException e) {
		}

	}

	public void changeIdeaState(long ideaId, String state, String stateJudgement)
			throws SystemException, PortalException {
		Idea idea = ideaPersistence.findByPrimaryKey(ideaId);
		idea.setState(state);
		idea.setStateJudgement(stateJudgement);
		ideaPersistence.update(idea);
	}

	public List<Idea> getIdeasByCat(long catId, long[] tagIds)
			throws SystemException {
		return IdeaFinderUtil.findByCatAndTags(catId, tagIds);
	}

	public List<Idea> getIdeasByCat(long catId, long[] tagIds, int begin,
			int end) throws SystemException {
		if (tagIds != null && tagIds.length > 0) {
			return IdeaFinderUtil.findByCatAndTags(catId, tagIds, begin, end);
		} else {
			return IdeaFinderUtil.findByCat(catId, begin, end);
		}
	}

	public List<Idea> getIdeasByCat(long catId) throws SystemException {
		return IdeaFinderUtil.findByCat(catId);
	}

	public List<Idea> getIdeasByCat(long catId, int begin, int end)
			throws SystemException {
		return IdeaFinderUtil.findByCat(catId, begin, end);
	}

	public List<Idea> getIdeasByRating() throws SystemException {
		return IdeaFinderUtil.findByRating();
	}

	public List<Idea> getIdeasByRating(int begin, int end)
			throws SystemException {
		return IdeaFinderUtil.findByRating(begin, end);
	}

	public List<Idea> getIdeasByRating(long catId) throws SystemException {
		return IdeaFinderUtil.findByCatAndRating(catId);
	}

	public List<Idea> getIdeasByRating(long catId, int begin, int end)
			throws SystemException {
		return IdeaFinderUtil.findByCatAndRating(catId, begin, end);
	}

	public List<Idea> getIdeasByRating(long catId, long[] tagIds)
			throws SystemException {
		if (tagIds != null && tagIds.length > 0) {
			return IdeaFinderUtil.findByCatAndRatingAndTags(catId, tagIds);
		} else {
			return getIdeasByRating(catId);
		}
	}

	public List<Idea> getIdeasByRating(long catId, long[] tagIds, int begin,
			int end) throws SystemException {
		if (tagIds != null && tagIds.length > 0) {
			return IdeaFinderUtil.findByCatAndRatingAndTags(catId, tagIds,
					begin, end);
		} else {
			return getIdeasByRating(catId, begin, end);
		}
	}

	public List<Idea> getIdeasByCall(long callId, int begin, int end)
			throws SystemException {
		if (begin <= 0 && end <= 0) {
			return ideaPersistence.findByCallId(callId);
		} else {
			return ideaPersistence.findByCallId(callId, begin, end);
		}
	}

	public List<Idea> getIdeasByCall(long callId, long[] tagIds, int begin,
			int end) throws SystemException {
		if (tagIds == null || tagIds.length == 0) {
			return getIdeasByCall(callId, begin, end);
		} else {
			if (begin <= 0 && end <= 0) {
				return IdeaFinderUtil.findByCallAndTags(callId, tagIds);
			} else {
				return IdeaFinderUtil.findByCallAndTags(callId, tagIds, begin,
						end);
			}
		}
	}

	public List<Idea> getIdeasByCallAndRating(long callId)
			throws SystemException {
		return getIdeasByCallAndRating(callId, -1, -1);
	}

	public List<Idea> getIdeasByCallAndRating(long callId, int begin, int end)
			throws SystemException {
		if (begin <= 0 && end <= 0) {
			return IdeaFinderUtil.findByCallAndRating(callId);
		} else {
			return IdeaFinderUtil.findByCallAndRating(callId, begin, end);
		}
	}

	public List<Idea> getIdeasByTagsAndRating(long[] tagIds)
			throws SystemException {
		return getIdeasByTagsAndRating(tagIds, -1, -1);
	}

	public List<Idea> getIdeasByTagsAndRating(long[] tagIds, int begin, int end)
			throws SystemException {
		if (begin <= 0 && end <= 0) {
			return IdeaFinderUtil.findByTagsAndRating(tagIds);
		} else {
			return IdeaFinderUtil.findByTagsAndRating(tagIds, begin, end);
		}
	}

	public List<Idea> getIdeasByCallAndRating(long callId, long[] tagIds,
			int begin, int end) throws SystemException {
		if (tagIds == null || tagIds.length == 0) {
			return getIdeasByCallAndRating(callId, begin, end);
		} else {
			if (begin <= 0 && end <= 0) {
				return IdeaFinderUtil
						.findByCallAndRatingAndTags(callId, tagIds);
			} else {
				return IdeaFinderUtil.findByCallAndRatingAndTags(callId,
						tagIds, begin, end);
			}
		}
	}

	public List<Idea> getIdeas() throws SystemException {
		return ideaPersistence.findAll();
	}

	public List<Idea> getIdeas(int begin, int end) throws SystemException {
		if (begin <= 0 && end <= 0) {
			return ideaPersistence.findAll();
		} else {
			return ideaPersistence.findAll(begin, end);
		}
	}

	public List<Idea> getIdeas(long groupId) throws SystemException {
		return ideaPersistence.findByGroupId(groupId);
	}

	public List<Idea> getIdeas(long groupId, int start, int end)
			throws SystemException {
		return ideaPersistence.findByGroupId(groupId, start, end);
	}

	public List<Idea> getIdeasByTags(long[] tags) throws SystemException {
		return IdeaFinderUtil.findByTags(tags);
	}

	public List<Idea> getIdeasByTags(long[] tags, int start, int end)
			throws SystemException {
		return IdeaFinderUtil.findByTags(tags, start, end);
	}

	public void toggleUserParticipation(long ideaId, long userId)
			throws SystemException, PortalException {
		List<Group> userGroups = GroupLocalServiceUtil.getUserGroups(userId);
		Idea idea = getIdea(ideaId);
		if (idea.getUserId() == userId)
			return;

		long groupId = idea.getUserGroupId();
		if (userGroups != null) {
			for (Group g : userGroups) {
				if (g.getGroupId() == groupId) {
					UserLocalServiceUtil.deleteGroupUser(groupId, userId);
					return;
				}
			}
		}
		GroupLocalServiceUtil.addUserGroup(userId, groupId);
	}

	public List<AssetEntry> getCategoryObjects(long groupId)
			throws SystemException, PortalException {
		AssetEntryQuery entryQuery = new AssetEntryQuery();
		entryQuery.setClassNameIds(new long[] { PortalUtil
				.getClassNameId(JournalArticle.class) });
		entryQuery.setClassTypeIds(new long[] { getStructureIdByStructureName(
				groupId, Constants.IDEA_CATEGORY_TYPE_NAME) });
		List<AssetEntry> entries = AssetEntryLocalServiceUtil
				.getEntries(entryQuery);
		if (entries != null && entries.size() > 0) {
			return entries;
		}
		return Collections.emptyList();
	}

	public Map<String, String> getCategoryColors(long groupId)
			throws SystemException, PortalException {
		Map<String, String> res = new HashMap<String, String>();
		AssetEntryQuery entryQuery = new AssetEntryQuery();
		XPath xpath = SAXReaderUtil
				.createXPath("dynamic-element[@name='color']");

		entryQuery.setClassNameIds(new long[] { PortalUtil
				.getClassNameId(JournalArticle.class) });
		entryQuery.setClassTypeIds(new long[] { getStructureIdByStructureName(
				groupId, Constants.IDEA_CATEGORY_TYPE_NAME) });
		List<AssetEntry> entries = AssetEntryLocalServiceUtil
				.getEntries(entryQuery);
		if (entries != null && entries.size() > 0) {
			for (AssetEntry entry : entries) {
				if (entry.getCategoryIds() != null
						&& entry.getCategoryIds().length > 0) {
					JournalArticle article = JournalArticleLocalServiceUtil
							.getLatestArticle(entry.getClassPK());
					try {
						Element el = SAXReaderUtil.read(article.getContent())
								.getRootElement();
						String color = xpath.selectSingleNode(el)
								.getStringValue();
						res.put("" + entry.getCategoryIds()[0], color.trim());
					} catch (Exception e) {
						e.printStackTrace();
						continue;
					}
				}
			}
		}
		return res;
	}

	public List<AssetTag> getCallTags(long callId) throws SystemException,
			PortalException {

		AssetEntryQuery query = new AssetEntryQuery();
		query.setAttribute("clasPK", callId);
		AssetEntry e = AssetEntryLocalServiceUtil.getEntry(
				Call.class.getName(), callId);
		return e.getTags();
	}

	public List<AssetTag> getCategoryTags(long[] categoryIds, long groupId)
			throws SystemException {

		AssetEntryQuery entryQuery = new AssetEntryQuery();
		entryQuery.setAnyCategoryIds(categoryIds);
		entryQuery.setClassNameIds(new long[] { PortalUtil
				.getClassNameId(JournalArticle.class) });
		entryQuery.setClassTypeIds(new long[] { getStructureIdByStructureName(
				groupId, Constants.IDEA_CATEGORY_TYPE_NAME) });
		List<AssetEntry> entries = AssetEntryLocalServiceUtil
				.getEntries(entryQuery);
		if (entries != null && entries.size() > 0) {
			List<AssetTag> tags = new ArrayList<AssetTag>();
			for (AssetEntry entry : entries) {
				tags.addAll(entry.getTags());
			}
			return tags;
		}
		return Collections.emptyList();
	}

	private long getStructureIdByStructureName(long groupId,
			String structureName) throws SystemException {
		List<JournalStructure> journalStructures = JournalStructureLocalServiceUtil
				.getStructures(groupId);
		for (JournalStructure journalStructure : journalStructures) {
			if (journalStructure.getNameCurrentValue().equalsIgnoreCase(
					structureName)) {
				return journalStructure.getId();
			}
		}
		return -1;
	}

	public List<Idea> searchByCallAndCategoryAndTags(long categoryId,
			long callId, long[] tagIds, int begin, int end)
			throws SystemException {
		boolean searchByCat = categoryId > 0;
		boolean searchByCall = callId > 0;
		boolean searchByTags = tagIds != null && tagIds.length > 0;

		List<Idea> ideas = null;
		if (searchByCall) {
			ideas = getIdeasByCall(callId, tagIds, begin, end);
		} else if (searchByCat) {
			ideas = getIdeasByCat(categoryId, tagIds, begin, end);
		} else if (searchByTags) {
			ideas = getIdeasByTags(tagIds, begin, end);
		} else {
			ideas = getIdeas(begin, end);
		}

		return ideas;
	}

	public List<Idea> searchPopularByCallAndCategoryAndTags(long categoryId,
			long callId, long[] tagIds, int begin, int end)
			throws SystemException {
		boolean searchByCat = categoryId > 0;
		boolean searchByCall = callId > 0;
		boolean searchByTags = tagIds != null && tagIds.length > 0;

		List<Idea> ideas = null;
		if (searchByCall) {
			ideas = getIdeasByCallAndRating(callId, tagIds, begin, end);
		} else if (searchByCat) {
			ideas = getIdeasByRating(categoryId, tagIds, begin, end);
		} else if (searchByTags) {
			ideas = getIdeasByTagsAndRating(tagIds, begin, end);
		} else {
			ideas = getIdeasByRating(begin, end);
		}

		return ideas;
	}

	public Idea updateStatus(long userId, long ideaId, int WFStatus,
			String ideaStatus, ServiceContext serviceContext)
			throws PortalException, SystemException {

		User user = userLocalService.getUser(userId);
		Idea i = getIdea(ideaId);

		// idea visibility
		if (WFStatus == WorkflowConstants.STATUS_PENDING
				|| WFStatus == WorkflowConstants.STATUS_APPROVED) {
			assetEntryLocalService.updateVisible(Idea.class.getName(), ideaId,
					true);
		} else {
			assetEntryLocalService.updateVisible(Idea.class.getName(), ideaId,
					false);
		}

		if (ideaStatus != null) {
			// idea status
			switch (ideaStatus) {
			case Constants.IDEA_STATE_BLOCKED_DUPLICATED:
				break;
			case Constants.IDEA_STATE_BLOCKED_ABUSIVE:
				blacklistUser(userId);
				break;
			default:
				break;
			}
			i.setState(ideaStatus);

		}

		i.setStatus(WFStatus);
		i.setStatusByUserId(userId);
		i.setStatusByUserName(user.getFullName());
		String comments = (String) serviceContext
				.getAttribute(WorkflowConstants.CONTEXT_TASK_COMMENTS);
		// System.out.println("comments: " + comments);
		if (comments != null) {
			i.setStateJudgement(comments);
		}
		// i.setStatusDate(new Date());

		ideaPersistence.update(i);

		return i;
	}

	public void blacklistUser(long userId) {
		Role role = null;
		try {
			role = RoleLocalServiceUtil.getRole(
					PortalUtil.getDefaultCompanyId(),
					PortletProps.get("role.blacklist"));
			RoleLocalServiceUtil.addUserRole(userId, role);
		} catch (PortalException | SystemException e) {
			e.printStackTrace();
		}
	}
}
