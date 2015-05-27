package it.smartcommunitylab.platform.idea.service;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.util.ReferenceRegistry;
import com.liferay.portal.service.InvokableLocalService;

/**
 * Provides the local service utility for Idea. This utility wraps
 * {@link it.smartcommunitylab.platform.idea.service.impl.IdeaLocalServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author mirko perillo
 * @see IdeaLocalService
 * @see it.smartcommunitylab.platform.idea.service.base.IdeaLocalServiceBaseImpl
 * @see it.smartcommunitylab.platform.idea.service.impl.IdeaLocalServiceImpl
 * @generated
 */
public class IdeaLocalServiceUtil {
    private static IdeaLocalService _service;

    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify this class directly. Add custom service methods to {@link it.smartcommunitylab.platform.idea.service.impl.IdeaLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
     */

    /**
    * Adds the idea to the database. Also notifies the appropriate model listeners.
    *
    * @param idea the idea
    * @return the idea that was added
    * @throws SystemException if a system exception occurred
    */
    public static it.smartcommunitylab.platform.idea.model.Idea addIdea(
        it.smartcommunitylab.platform.idea.model.Idea idea)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().addIdea(idea);
    }

    /**
    * Creates a new idea with the primary key. Does not add the idea to the database.
    *
    * @param ideaId the primary key for the new idea
    * @return the new idea
    */
    public static it.smartcommunitylab.platform.idea.model.Idea createIdea(
        long ideaId) {
        return getService().createIdea(ideaId);
    }

    /**
    * Deletes the idea with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param ideaId the primary key of the idea
    * @return the idea that was removed
    * @throws PortalException if a idea with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static it.smartcommunitylab.platform.idea.model.Idea deleteIdea(
        long ideaId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return getService().deleteIdea(ideaId);
    }

    /**
    * Deletes the idea from the database. Also notifies the appropriate model listeners.
    *
    * @param idea the idea
    * @return the idea that was removed
    * @throws SystemException if a system exception occurred
    */
    public static it.smartcommunitylab.platform.idea.model.Idea deleteIdea(
        it.smartcommunitylab.platform.idea.model.Idea idea)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().deleteIdea(idea);
    }

    public static com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
        return getService().dynamicQuery();
    }

    /**
    * Performs a dynamic query on the database and returns the matching rows.
    *
    * @param dynamicQuery the dynamic query
    * @return the matching rows
    * @throws SystemException if a system exception occurred
    */
    @SuppressWarnings("rawtypes")
    public static java.util.List dynamicQuery(
        com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().dynamicQuery(dynamicQuery);
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
    @SuppressWarnings("rawtypes")
    public static java.util.List dynamicQuery(
        com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
        int end) throws com.liferay.portal.kernel.exception.SystemException {
        return getService().dynamicQuery(dynamicQuery, start, end);
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
    @SuppressWarnings("rawtypes")
    public static java.util.List dynamicQuery(
        com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
        int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService()
                   .dynamicQuery(dynamicQuery, start, end, orderByComparator);
    }

    /**
    * Returns the number of rows that match the dynamic query.
    *
    * @param dynamicQuery the dynamic query
    * @return the number of rows that match the dynamic query
    * @throws SystemException if a system exception occurred
    */
    public static long dynamicQueryCount(
        com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().dynamicQueryCount(dynamicQuery);
    }

    /**
    * Returns the number of rows that match the dynamic query.
    *
    * @param dynamicQuery the dynamic query
    * @param projection the projection to apply to the query
    * @return the number of rows that match the dynamic query
    * @throws SystemException if a system exception occurred
    */
    public static long dynamicQueryCount(
        com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery,
        com.liferay.portal.kernel.dao.orm.Projection projection)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().dynamicQueryCount(dynamicQuery, projection);
    }

    public static it.smartcommunitylab.platform.idea.model.Idea fetchIdea(
        long ideaId) throws com.liferay.portal.kernel.exception.SystemException {
        return getService().fetchIdea(ideaId);
    }

    /**
    * Returns the idea with the matching UUID and company.
    *
    * @param uuid the idea's UUID
    * @param companyId the primary key of the company
    * @return the matching idea, or <code>null</code> if a matching idea could not be found
    * @throws SystemException if a system exception occurred
    */
    public static it.smartcommunitylab.platform.idea.model.Idea fetchIdeaByUuidAndCompanyId(
        java.lang.String uuid, long companyId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().fetchIdeaByUuidAndCompanyId(uuid, companyId);
    }

    /**
    * Returns the idea matching the UUID and group.
    *
    * @param uuid the idea's UUID
    * @param groupId the primary key of the group
    * @return the matching idea, or <code>null</code> if a matching idea could not be found
    * @throws SystemException if a system exception occurred
    */
    public static it.smartcommunitylab.platform.idea.model.Idea fetchIdeaByUuidAndGroupId(
        java.lang.String uuid, long groupId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().fetchIdeaByUuidAndGroupId(uuid, groupId);
    }

    /**
    * Returns the idea with the primary key.
    *
    * @param ideaId the primary key of the idea
    * @return the idea
    * @throws PortalException if a idea with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static it.smartcommunitylab.platform.idea.model.Idea getIdea(
        long ideaId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return getService().getIdea(ideaId);
    }

    public static com.liferay.portal.model.PersistedModel getPersistedModel(
        java.io.Serializable primaryKeyObj)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return getService().getPersistedModel(primaryKeyObj);
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
    public static it.smartcommunitylab.platform.idea.model.Idea getIdeaByUuidAndCompanyId(
        java.lang.String uuid, long companyId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return getService().getIdeaByUuidAndCompanyId(uuid, companyId);
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
    public static it.smartcommunitylab.platform.idea.model.Idea getIdeaByUuidAndGroupId(
        java.lang.String uuid, long groupId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return getService().getIdeaByUuidAndGroupId(uuid, groupId);
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
    public static java.util.List<it.smartcommunitylab.platform.idea.model.Idea> getIdeas(
        int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().getIdeas(start, end);
    }

    /**
    * Returns the number of ideas.
    *
    * @return the number of ideas
    * @throws SystemException if a system exception occurred
    */
    public static int getIdeasCount()
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().getIdeasCount();
    }

    /**
    * Updates the idea in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param idea the idea
    * @return the idea that was updated
    * @throws SystemException if a system exception occurred
    */
    public static it.smartcommunitylab.platform.idea.model.Idea updateIdea(
        it.smartcommunitylab.platform.idea.model.Idea idea)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().updateIdea(idea);
    }

    /**
    * Returns the Spring bean ID for this bean.
    *
    * @return the Spring bean ID for this bean
    */
    public static java.lang.String getBeanIdentifier() {
        return getService().getBeanIdentifier();
    }

    /**
    * Sets the Spring bean ID for this bean.
    *
    * @param beanIdentifier the Spring bean ID for this bean
    */
    public static void setBeanIdentifier(java.lang.String beanIdentifier) {
        getService().setBeanIdentifier(beanIdentifier);
    }

    public static java.lang.Object invokeMethod(java.lang.String name,
        java.lang.String[] parameterTypes, java.lang.Object[] arguments)
        throws java.lang.Throwable {
        return getService().invokeMethod(name, parameterTypes, arguments);
    }

    public static it.smartcommunitylab.platform.idea.model.Idea addIdea(
        long userId,
        it.smartcommunitylab.platform.idea.beans.IdeaBean ideaBean,
        com.liferay.portal.service.ServiceContext serviceContext)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return getService().addIdea(userId, ideaBean, serviceContext);
    }

    public static void updateIdea(long userId,
        it.smartcommunitylab.platform.idea.beans.IdeaBean ideaBean,
        com.liferay.portal.service.ServiceContext serviceContext)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        getService().updateIdea(userId, ideaBean, serviceContext);
    }

    public static void deleteIdea(long userId,
        it.smartcommunitylab.platform.idea.beans.IdeaBean ideaBean,
        com.liferay.portal.service.ServiceContext serviceContext)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        getService().deleteIdea(userId, ideaBean, serviceContext);
    }

    public static java.util.List<it.smartcommunitylab.platform.idea.model.Idea> getIdeas(
        long groupId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().getIdeas(groupId);
    }

    public static java.util.List<it.smartcommunitylab.platform.idea.model.Idea> getIdeas(
        long groupId, int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().getIdeas(groupId, start, end);
    }

    public static void clearService() {
        _service = null;
    }

    public static IdeaLocalService getService() {
        if (_service == null) {
            InvokableLocalService invokableLocalService = (InvokableLocalService) PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
                    IdeaLocalService.class.getName());

            if (invokableLocalService instanceof IdeaLocalService) {
                _service = (IdeaLocalService) invokableLocalService;
            } else {
                _service = new IdeaLocalServiceClp(invokableLocalService);
            }

            ReferenceRegistry.registerReference(IdeaLocalServiceUtil.class,
                "_service");
        }

        return _service;
    }

    /**
     * @deprecated As of 6.2.0
     */
    public void setService(IdeaLocalService service) {
    }
}
