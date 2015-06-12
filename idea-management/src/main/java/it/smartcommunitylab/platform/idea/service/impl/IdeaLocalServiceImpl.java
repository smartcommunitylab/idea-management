package it.smartcommunitylab.platform.idea.service.impl;

import it.smartcommunitylab.platform.idea.NoSuchIdeaException;
import it.smartcommunitylab.platform.idea.beans.IdeaBean;
import it.smartcommunitylab.platform.idea.model.Idea;
import it.smartcommunitylab.platform.idea.service.IdeaLocalServiceUtil;
import it.smartcommunitylab.platform.idea.service.base.IdeaLocalServiceBaseImpl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.search.Indexer;
import com.liferay.portal.kernel.search.IndexerRegistryUtil;
import com.liferay.portal.kernel.util.ContentTypes;
import com.liferay.portal.model.ResourceConstants;
import com.liferay.portal.model.User;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portlet.asset.model.AssetEntry;
import com.liferay.portlet.asset.model.AssetLinkConstants;
import com.liferay.portlet.asset.service.AssetEntryLocalServiceUtil;

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

		long guestbookId = counterLocalService.increment();

		Idea idea = ideaPersistence.create(guestbookId);

		idea.setUuid(serviceContext.getUuid());
		idea.setUserId(userId);
		idea.setGroupId(groupId);
		idea.setCompanyId(user.getCompanyId());
		idea.setUserName(user.getFullName());
		idea.setCreateDate(serviceContext.getCreateDate(now));
		idea.setModifiedDate(serviceContext.getModifiedDate(now));
		idea.setExpandoBridgeAttributes(serviceContext);

		idea.setTitle(ideaBean.getTitle());
		idea.setShortDesc(ideaBean.getShortDesc());
		idea.setLongDesc(ideaBean.getLongDesc());

		ideaPersistence.update(idea);

		resourceLocalService.addResources(user.getCompanyId(), groupId, userId,
				Idea.class.getName(), guestbookId, false, true, true);

		AssetEntry assetEntry = assetEntryLocalService.updateEntry(userId,
				groupId, idea.getCreateDate(), idea.getModifiedDate(),
				Idea.class.getName(), guestbookId, idea.getUuid(), 0,
				serviceContext.getAssetCategoryIds(),
				serviceContext.getAssetTagNames(), true, null, null, null,
				ContentTypes.TEXT_HTML, idea.getTitle(), null, null, null,
				null, 0, 0, null, false);

		assetLinkLocalService.updateLinks(userId, assetEntry.getEntryId(),
				serviceContext.getAssetLinkEntryIds(),
				AssetLinkConstants.TYPE_RELATED);

		Indexer indexer = IndexerRegistryUtil.nullSafeGetIndexer(Idea.class);

		indexer.reindex(idea);
		return idea;
	}

	public void updateIdea(long userId, IdeaBean ideaBean,
			ServiceContext serviceContext) throws SystemException,
			PortalException {

		try {
			Idea idea = ideaPersistence.findByPrimaryKey(ideaBean.getId());
			idea.setTitle(ideaBean.getTitle());
			idea.setLongDesc(ideaBean.getLongDesc());
			idea.setShortDesc(ideaBean.getShortDesc());

			ideaPersistence.update(idea);

			resourceLocalService.updateResources(serviceContext.getCompanyId(),
					serviceContext.getScopeGroupId(), idea.getTitle(),
					idea.getIdeaId(), serviceContext.getGroupPermissions(),
					serviceContext.getGuestPermissions());

			AssetEntry assetEntry = assetEntryLocalService.updateEntry(
					idea.getUserId(), idea.getGroupId(), idea.getCreateDate(),
					idea.getModifiedDate(), Idea.class.getName(),
					idea.getIdeaId(), idea.getUuid(), 0,
					serviceContext.getAssetCategoryIds(),
					serviceContext.getAssetTagNames(), true, null, null, null,
					ContentTypes.TEXT_HTML, idea.getTitle(), null, null, null,
					null, 0, 0, null, false);

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

	public List<Idea> getIdeasByCat(long catId) throws SystemException {
		// TODO improve query with join
		List<AssetEntry> entries = AssetEntryLocalServiceUtil
				.getAssetCategoryAssetEntries(catId);
		List<Idea> ideas = new ArrayList<Idea>();
		for (AssetEntry entry : entries) {
			Idea i = IdeaLocalServiceUtil.fetchIdea(entry.getClassPK());
			if (i != null) {
				ideas.add(i);
			}
		}

		return ideas;
	}

	public List<Idea> getIdeas() throws SystemException {
		return ideaPersistence.findAll();
	}

	public List<Idea> getIdeas(long groupId) throws SystemException {
		return ideaPersistence.findByGroupId(groupId);
	}

	public List<Idea> getIdeas(long groupId, int start, int end)
			throws SystemException {
		return ideaPersistence.findByGroupId(groupId, start, end);
	}
}
