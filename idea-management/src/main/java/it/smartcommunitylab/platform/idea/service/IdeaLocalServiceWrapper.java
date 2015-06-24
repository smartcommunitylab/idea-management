package it.smartcommunitylab.platform.idea.service;

import com.liferay.portal.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link IdeaLocalService}.
 *
 * @author mirko perillo
 * @see IdeaLocalService
 * @generated
 */
public class IdeaLocalServiceWrapper implements IdeaLocalService,
    ServiceWrapper<IdeaLocalService> {
    private IdeaLocalService _ideaLocalService;

    public IdeaLocalServiceWrapper(IdeaLocalService ideaLocalService) {
        _ideaLocalService = ideaLocalService;
    }

    /**
    * Adds the idea to the database. Also notifies the appropriate model listeners.
    *
    * @param idea the idea
    * @return the idea that was added
    * @throws SystemException if a system exception occurred
    */
    @Override
    public it.smartcommunitylab.platform.idea.model.Idea addIdea(
        it.smartcommunitylab.platform.idea.model.Idea idea)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _ideaLocalService.addIdea(idea);
    }

    /**
    * Creates a new idea with the primary key. Does not add the idea to the database.
    *
    * @param ideaId the primary key for the new idea
    * @return the new idea
    */
    @Override
    public it.smartcommunitylab.platform.idea.model.Idea createIdea(long ideaId) {
        return _ideaLocalService.createIdea(ideaId);
    }

    /**
    * Deletes the idea with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param ideaId the primary key of the idea
    * @return the idea that was removed
    * @throws PortalException if a idea with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    @Override
    public it.smartcommunitylab.platform.idea.model.Idea deleteIdea(long ideaId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return _ideaLocalService.deleteIdea(ideaId);
    }

    /**
    * Deletes the idea from the database. Also notifies the appropriate model listeners.
    *
    * @param idea the idea
    * @return the idea that was removed
    * @throws SystemException if a system exception occurred
    */
    @Override
    public it.smartcommunitylab.platform.idea.model.Idea deleteIdea(
        it.smartcommunitylab.platform.idea.model.Idea idea)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _ideaLocalService.deleteIdea(idea);
    }

    @Override
    public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
        return _ideaLocalService.dynamicQuery();
    }

    /**
    * Performs a dynamic query on the database and returns the matching rows.
    *
    * @param dynamicQuery the dynamic query
    * @return the matching rows
    * @throws SystemException if a system exception occurred
    */
    @Override
    @SuppressWarnings("rawtypes")
    public java.util.List dynamicQuery(
        com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _ideaLocalService.dynamicQuery(dynamicQuery);
    }

    /**
    * Performs a dynamic query on the database and returns a range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link it.smartcommunitylab.platform.idea.model.impl.IdeaModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param dynamicQuery the dynamic query
    * @param start the lower bound of the range of model instances
    * @param end the upper bound of the range of model instances (not inclusive)
    * @return the range of matching rows
    * @throws SystemException if a system exception occurred
    */
    @Override
    @SuppressWarnings("rawtypes")
    public java.util.List dynamicQuery(
        com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
        int end) throws com.liferay.portal.kernel.exception.SystemException {
        return _ideaLocalService.dynamicQuery(dynamicQuery, start, end);
    }

    /**
    * Performs a dynamic query on the database and returns an ordered range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link it.smartcommunitylab.platform.idea.model.impl.IdeaModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param dynamicQuery the dynamic query
    * @param start the lower bound of the range of model instances
    * @param end the upper bound of the range of model instances (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of matching rows
    * @throws SystemException if a system exception occurred
    */
    @Override
    @SuppressWarnings("rawtypes")
    public java.util.List dynamicQuery(
        com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
        int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _ideaLocalService.dynamicQuery(dynamicQuery, start, end,
            orderByComparator);
    }

    /**
    * Returns the number of rows that match the dynamic query.
    *
    * @param dynamicQuery the dynamic query
    * @return the number of rows that match the dynamic query
    * @throws SystemException if a system exception occurred
    */
    @Override
    public long dynamicQueryCount(
        com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _ideaLocalService.dynamicQueryCount(dynamicQuery);
    }

    /**
    * Returns the number of rows that match the dynamic query.
    *
    * @param dynamicQuery the dynamic query
    * @param projection the projection to apply to the query
    * @return the number of rows that match the dynamic query
    * @throws SystemException if a system exception occurred
    */
    @Override
    public long dynamicQueryCount(
        com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery,
        com.liferay.portal.kernel.dao.orm.Projection projection)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _ideaLocalService.dynamicQueryCount(dynamicQuery, projection);
    }

    @Override
    public it.smartcommunitylab.platform.idea.model.Idea fetchIdea(long ideaId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _ideaLocalService.fetchIdea(ideaId);
    }

    /**
    * Returns the idea with the matching UUID and company.
    *
    * @param uuid the idea's UUID
    * @param companyId the primary key of the company
    * @return the matching idea, or <code>null</code> if a matching idea could not be found
    * @throws SystemException if a system exception occurred
    */
    @Override
    public it.smartcommunitylab.platform.idea.model.Idea fetchIdeaByUuidAndCompanyId(
        java.lang.String uuid, long companyId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _ideaLocalService.fetchIdeaByUuidAndCompanyId(uuid, companyId);
    }

    /**
    * Returns the idea matching the UUID and group.
    *
    * @param uuid the idea's UUID
    * @param groupId the primary key of the group
    * @return the matching idea, or <code>null</code> if a matching idea could not be found
    * @throws SystemException if a system exception occurred
    */
    @Override
    public it.smartcommunitylab.platform.idea.model.Idea fetchIdeaByUuidAndGroupId(
        java.lang.String uuid, long groupId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _ideaLocalService.fetchIdeaByUuidAndGroupId(uuid, groupId);
    }

    /**
    * Returns the idea with the primary key.
    *
    * @param ideaId the primary key of the idea
    * @return the idea
    * @throws PortalException if a idea with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    @Override
    public it.smartcommunitylab.platform.idea.model.Idea getIdea(long ideaId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return _ideaLocalService.getIdea(ideaId);
    }

    @Override
    public com.liferay.portal.model.PersistedModel getPersistedModel(
        java.io.Serializable primaryKeyObj)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return _ideaLocalService.getPersistedModel(primaryKeyObj);
    }

    /**
    * Returns the idea with the matching UUID and company.
    *
    * @param uuid the idea's UUID
    * @param companyId the primary key of the company
    * @return the matching idea
    * @throws PortalException if a matching idea could not be found
    * @throws SystemException if a system exception occurred
    */
    @Override
    public it.smartcommunitylab.platform.idea.model.Idea getIdeaByUuidAndCompanyId(
        java.lang.String uuid, long companyId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return _ideaLocalService.getIdeaByUuidAndCompanyId(uuid, companyId);
    }

    /**
    * Returns the idea matching the UUID and group.
    *
    * @param uuid the idea's UUID
    * @param groupId the primary key of the group
    * @return the matching idea
    * @throws PortalException if a matching idea could not be found
    * @throws SystemException if a system exception occurred
    */
    @Override
    public it.smartcommunitylab.platform.idea.model.Idea getIdeaByUuidAndGroupId(
        java.lang.String uuid, long groupId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return _ideaLocalService.getIdeaByUuidAndGroupId(uuid, groupId);
    }

    /**
    * Returns a range of all the ideas.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link it.smartcommunitylab.platform.idea.model.impl.IdeaModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of ideas
    * @param end the upper bound of the range of ideas (not inclusive)
    * @return the range of ideas
    * @throws SystemException if a system exception occurred
    */
    @Override
    public java.util.List<it.smartcommunitylab.platform.idea.model.Idea> getIdeas(
        int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _ideaLocalService.getIdeas(start, end);
    }

    /**
    * Returns the number of ideas.
    *
    * @return the number of ideas
    * @throws SystemException if a system exception occurred
    */
    @Override
    public int getIdeasCount()
        throws com.liferay.portal.kernel.exception.SystemException {
        return _ideaLocalService.getIdeasCount();
    }

    /**
    * Updates the idea in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param idea the idea
    * @return the idea that was updated
    * @throws SystemException if a system exception occurred
    */
    @Override
    public it.smartcommunitylab.platform.idea.model.Idea updateIdea(
        it.smartcommunitylab.platform.idea.model.Idea idea)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _ideaLocalService.updateIdea(idea);
    }

    /**
    * Returns the Spring bean ID for this bean.
    *
    * @return the Spring bean ID for this bean
    */
    @Override
    public java.lang.String getBeanIdentifier() {
        return _ideaLocalService.getBeanIdentifier();
    }

    /**
    * Sets the Spring bean ID for this bean.
    *
    * @param beanIdentifier the Spring bean ID for this bean
    */
    @Override
    public void setBeanIdentifier(java.lang.String beanIdentifier) {
        _ideaLocalService.setBeanIdentifier(beanIdentifier);
    }

    @Override
    public java.lang.Object invokeMethod(java.lang.String name,
        java.lang.String[] parameterTypes, java.lang.Object[] arguments)
        throws java.lang.Throwable {
        return _ideaLocalService.invokeMethod(name, parameterTypes, arguments);
    }

    @Override
    public it.smartcommunitylab.platform.idea.model.Idea addIdea(long userId,
        it.smartcommunitylab.platform.idea.beans.IdeaBean ideaBean,
        com.liferay.portal.service.ServiceContext serviceContext)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return _ideaLocalService.addIdea(userId, ideaBean, serviceContext);
    }

    @Override
    public void updateIdea(long userId,
        it.smartcommunitylab.platform.idea.beans.IdeaBean ideaBean,
        com.liferay.portal.service.ServiceContext serviceContext)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        _ideaLocalService.updateIdea(userId, ideaBean, serviceContext);
    }

    @Override
    public void deleteIdea(long userId,
        it.smartcommunitylab.platform.idea.beans.IdeaBean ideaBean,
        com.liferay.portal.service.ServiceContext serviceContext)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        _ideaLocalService.deleteIdea(userId, ideaBean, serviceContext);
    }

    @Override
    public java.util.List<it.smartcommunitylab.platform.idea.model.Idea> getIdeasByCat(
        long catId, long[] tagIds)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _ideaLocalService.getIdeasByCat(catId, tagIds);
    }

    @Override
    public java.util.List<it.smartcommunitylab.platform.idea.model.Idea> getIdeasByCat(
        long catId, long[] tagIds, int begin, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _ideaLocalService.getIdeasByCat(catId, tagIds, begin, end);
    }

    @Override
    public java.util.List<it.smartcommunitylab.platform.idea.model.Idea> getIdeasByCat(
        long catId) throws com.liferay.portal.kernel.exception.SystemException {
        return _ideaLocalService.getIdeasByCat(catId);
    }

    @Override
    public java.util.List<it.smartcommunitylab.platform.idea.model.Idea> getIdeasByCat(
        long catId, int begin, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _ideaLocalService.getIdeasByCat(catId, begin, end);
    }

    @Override
    public java.util.List<it.smartcommunitylab.platform.idea.model.Idea> getIdeasByRating()
        throws com.liferay.portal.kernel.exception.SystemException {
        return _ideaLocalService.getIdeasByRating();
    }

    @Override
    public java.util.List<it.smartcommunitylab.platform.idea.model.Idea> getIdeasByRating(
        int begin, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _ideaLocalService.getIdeasByRating(begin, end);
    }

    @Override
    public java.util.List<it.smartcommunitylab.platform.idea.model.Idea> getIdeasByRating(
        long catId) throws com.liferay.portal.kernel.exception.SystemException {
        return _ideaLocalService.getIdeasByRating(catId);
    }

    @Override
    public java.util.List<it.smartcommunitylab.platform.idea.model.Idea> getIdeasByRating(
        long catId, int begin, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _ideaLocalService.getIdeasByRating(catId, begin, end);
    }

    @Override
    public java.util.List<it.smartcommunitylab.platform.idea.model.Idea> getIdeasByRating(
        long catId, long[] tagIds) {
        return _ideaLocalService.getIdeasByRating(catId, tagIds);
    }

    @Override
    public java.util.List<it.smartcommunitylab.platform.idea.model.Idea> getIdeasByRating(
        long catId, long[] tagIds, int begin, int end) {
        return _ideaLocalService.getIdeasByRating(catId, tagIds, begin, end);
    }

    @Override
    public java.util.List<it.smartcommunitylab.platform.idea.model.Idea> getIdeasByCall(
        long callId, int begin, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _ideaLocalService.getIdeasByCall(callId, begin, end);
    }

    @Override
    public java.util.List<it.smartcommunitylab.platform.idea.model.Idea> getIdeasByCallAndRating(
        long callId) throws com.liferay.portal.kernel.exception.SystemException {
        return _ideaLocalService.getIdeasByCallAndRating(callId);
    }

    @Override
    public java.util.List<it.smartcommunitylab.platform.idea.model.Idea> getIdeasByCallAndRating(
        long callId, int begin, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _ideaLocalService.getIdeasByCallAndRating(callId, begin, end);
    }

    @Override
    public java.util.List<it.smartcommunitylab.platform.idea.model.Idea> getIdeas()
        throws com.liferay.portal.kernel.exception.SystemException {
        return _ideaLocalService.getIdeas();
    }

    @Override
    public java.util.List<it.smartcommunitylab.platform.idea.model.Idea> getIdeas(
        long groupId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _ideaLocalService.getIdeas(groupId);
    }

    @Override
    public java.util.List<it.smartcommunitylab.platform.idea.model.Idea> getIdeas(
        long groupId, int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _ideaLocalService.getIdeas(groupId, start, end);
    }

    @Override
    public void toggleUserParticipation(long ideaId, long userId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        _ideaLocalService.toggleUserParticipation(ideaId, userId);
    }

    @Override
    public java.util.Map<java.lang.String, java.lang.String> getCategoryColors(
        long groupId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return _ideaLocalService.getCategoryColors(groupId);
    }

    @Override
    public java.util.List<com.liferay.portlet.asset.model.AssetTag> getCategoryTags(
        long[] categoryIds, long groupId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _ideaLocalService.getCategoryTags(categoryIds, groupId);
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
     */
    public IdeaLocalService getWrappedIdeaLocalService() {
        return _ideaLocalService;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
     */
    public void setWrappedIdeaLocalService(IdeaLocalService ideaLocalService) {
        _ideaLocalService = ideaLocalService;
    }

    @Override
    public IdeaLocalService getWrappedService() {
        return _ideaLocalService;
    }

    @Override
    public void setWrappedService(IdeaLocalService ideaLocalService) {
        _ideaLocalService = ideaLocalService;
    }
}
