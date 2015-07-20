package it.smartcommunitylab.platform.idea.service.impl;

import it.smartcommunitylab.platform.idea.beans.CallBean;
import it.smartcommunitylab.platform.idea.model.Call;
import it.smartcommunitylab.platform.idea.service.base.CallLocalServiceBaseImpl;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.liferay.portal.kernel.dao.orm.OrderFactoryUtil;
import com.liferay.portal.kernel.dao.orm.PropertyFactoryUtil;
import com.liferay.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.search.Indexer;
import com.liferay.portal.kernel.search.IndexerRegistryUtil;
import com.liferay.portal.kernel.util.ContentTypes;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.model.Group;
import com.liferay.portal.model.GroupConstants;
import com.liferay.portal.model.ResourceConstants;
import com.liferay.portal.model.User;
import com.liferay.portal.service.GroupLocalServiceUtil;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portlet.asset.model.AssetCategory;
import com.liferay.portlet.asset.model.AssetEntry;
import com.liferay.portlet.asset.model.AssetLinkConstants;
import com.liferay.portlet.asset.service.AssetCategoryLocalServiceUtil;

/**
 * The implementation of the call local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are
 * added, rerun ServiceBuilder to copy their definitions into the
 * {@link it.smartcommunitylab.platform.idea.service.CallLocalService}
 * interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security
 * checks based on the propagated JAAS credentials because this service can only
 * be accessed from within the same VM.
 * </p>
 *
 * @author mirko perillo
 * @see it.smartcommunitylab.platform.idea.service.base.CallLocalServiceBaseImpl
 * @see it.smartcommunitylab.platform.idea.service.CallLocalServiceUtil
 */
public class CallLocalServiceImpl extends CallLocalServiceBaseImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 * 
	 * Never reference this interface directly. Always use {@link
	 * it.smartcommunitylab.platform.idea.service.CallLocalServiceUtil} to
	 * access the call local service.
	 */

	public List<Call> getOpenCalls(int begin, int end) throws SystemException {
		// TODO consider null values also
		DynamicQuery query = DynamicQueryFactoryUtil.forClass(Call.class)
				.add(RestrictionsFactoryUtil.or(PropertyFactoryUtil.forName("deadline").ge(new Date()),PropertyFactoryUtil.forName("deadline").isNull()))
				.addOrder(OrderFactoryUtil.desc("deadline"));
		return end > 0 ? callPersistence.findWithDynamicQuery(query, begin, end) : callPersistence.findWithDynamicQuery(query);
	}
	public List<Call> getInDiscussionCalls(int begin, int end) throws SystemException {
		DynamicQuery query = DynamicQueryFactoryUtil.forClass(Call.class)
				.add(PropertyFactoryUtil.forName("deadline").lt(new Date()))
				.add(RestrictionsFactoryUtil.or(PropertyFactoryUtil.forName("publicationDeadline").ge(new Date()),PropertyFactoryUtil.forName("publicationDeadline").isNull()))
				.addOrder(OrderFactoryUtil.desc("deadline"));
		return end > 0 ? callPersistence.findWithDynamicQuery(query, begin, end) : callPersistence.findWithDynamicQuery(query);
	}
	public List<Call> getClosedCalls(int begin, int end) throws SystemException {
		DynamicQuery query = DynamicQueryFactoryUtil.forClass(Call.class)
				.add(PropertyFactoryUtil.forName("deadline").lt(new Date()))
				.add(PropertyFactoryUtil.forName("publicationDeadline").lt(new Date()))
				.addOrder(OrderFactoryUtil.desc("deadline"));
		return end > 0 ? callPersistence.findWithDynamicQuery(query, begin, end) : callPersistence.findWithDynamicQuery(query);
	}

	public Call createCall(long userId, CallBean callBean,
			ServiceContext serviceContext) throws SystemException,
			PortalException {
		long groupId = serviceContext.getScopeGroupId();
		User user = userPersistence.findByPrimaryKey(userId);
		Date now = new Date();

		long id = counterLocalService.increment();
		Call call = createCall(id);

		Group group = GroupLocalServiceUtil.addGroup(userId, 0L, null, 0L, 0L,
				callBean.getTitle() + " - " + id, null,
				GroupConstants.TYPE_SITE_PRIVATE, false, 0, null, true, true,
				serviceContext);
		GroupLocalServiceUtil.addUserGroup(userId, group.getGroupId());

		long[] assetCategoryIds = serviceContext.getAssetCategoryIds();
		
		call.setGroupId(groupId);
		call.setCreateDate(now);
		call.setUserId(userId);
		call.setUserName(user.getFullName());
		call.setCompanyId(user.getCompanyId());
		call.setTitle(callBean.getTitle());
		call.setDeadline(callBean.getDeadline());
		call.setPublicationDeadline(callBean.getPublicationDeadline());
		call.setRealizationDeadline(callBean.getRealizationDeadline());
		call.setDescription(callBean.getDescription());
		call.setUserGroupId(group.getGroupId());
		call.setCategoryIds(StringUtil.merge(assetCategoryIds));

		call = callPersistence.update(call);

		resourceLocalService.addResources(user.getCompanyId(), groupId, userId,
				Call.class.getName(), id, false, true, true);

		AssetEntry assetEntry = assetEntryLocalService.updateEntry(userId,
				groupId, call.getCreateDate(), call.getModifiedDate(),
				Call.class.getName(), id, call.getUuid(), 0,
				assetCategoryIds,
				serviceContext.getAssetTagNames(), true, null, null, null,
				ContentTypes.TEXT_HTML, call.getTitle(), null, null, null,
				null, 0, 0, null, false);

		assetLinkLocalService.updateLinks(userId, assetEntry.getEntryId(),
				serviceContext.getAssetLinkEntryIds(),
				AssetLinkConstants.TYPE_RELATED);

		Indexer indexer = IndexerRegistryUtil.nullSafeGetIndexer(Call.class);
		indexer.reindex(call);

		return call;
	}

	public void updateCall(CallBean callBean, ServiceContext serviceContext)
			throws SystemException, PortalException {
		if (callBean != null) {
			long[] assetCategoryIds = serviceContext.getAssetCategoryIds();

			Call call = callPersistence.fetchByPrimaryKey(callBean.getId());
			call.setTitle(callBean.getTitle());
			call.setDescription(callBean.getDescription());
			call.setDeadline(callBean.getDeadline());
			call.setPublicationDeadline(callBean.getPublicationDeadline());
			call.setRealizationDeadline(callBean.getRealizationDeadline());
			call.setCategoryIds(StringUtil.merge(assetCategoryIds));

			callPersistence.update(call);

			resourceLocalService.updateResources(serviceContext.getCompanyId(),
					serviceContext.getScopeGroupId(), Call.class.getName(),
					call.getCallId(), serviceContext.getGroupPermissions(),
					serviceContext.getGuestPermissions());
			AssetEntry assetEntry = assetEntryLocalService.updateEntry(
					serviceContext.getUserId(), call.getGroupId(),
					call.getCreateDate(), call.getModifiedDate(),
					Call.class.getName(), callBean.getId(), call.getUuid(), 0,
					assetCategoryIds,
					serviceContext.getAssetTagNames(), true, null, null, null,
					ContentTypes.TEXT_HTML, call.getTitle(), null, null, null,
					null, 0, 0, null, false);

			assetLinkLocalService.updateLinks(serviceContext.getUserId(),
					assetEntry.getEntryId(),
					serviceContext.getAssetLinkEntryIds(),
					AssetLinkConstants.TYPE_RELATED);

			Indexer indexer = IndexerRegistryUtil
					.nullSafeGetIndexer(Call.class);
			indexer.reindex(call);
		}
	}

	public void deleteCall(long callId, ServiceContext serviceContext)
			throws SystemException, PortalException {
		Call del = callPersistence.remove(callId);

		Indexer indexer = IndexerRegistryUtil.nullSafeGetIndexer(Call.class);
		indexer.delete(del);

		GroupLocalServiceUtil.deleteUserGroup(serviceContext.getUserId(), del.getUserGroupId());
		GroupLocalServiceUtil.deleteGroup(del.getUserGroupId());

		resourceLocalService.deleteResource(serviceContext.getCompanyId(),
				Call.class.getName(), ResourceConstants.SCOPE_INDIVIDUAL,
				callId);

		AssetEntry assetEntry = assetEntryLocalService.fetchEntry(
				Call.class.getName(), callId);

		if (assetEntry != null) {
			assetLinkLocalService.deleteLinks(assetEntry.getEntryId());
			assetEntryLocalService.deleteEntry(assetEntry);
		}
	}
}
