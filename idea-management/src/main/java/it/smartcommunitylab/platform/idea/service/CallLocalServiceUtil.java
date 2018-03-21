package it.smartcommunitylab.platform.idea.service;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.util.ReferenceRegistry;
import com.liferay.portal.service.InvokableLocalService;

/**
 * Provides the local service utility for Call. This utility wraps
 * {@link it.smartcommunitylab.platform.idea.service.impl.CallLocalServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author mirko perillo
 * @see CallLocalService
 * @see it.smartcommunitylab.platform.idea.service.base.CallLocalServiceBaseImpl
 * @see it.smartcommunitylab.platform.idea.service.impl.CallLocalServiceImpl
 * @generated
 */
public class CallLocalServiceUtil {
    private static CallLocalService _service;

    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify this class directly. Add custom service methods to {@link it.smartcommunitylab.platform.idea.service.impl.CallLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
     */

    /**
    * Adds the call to the database. Also notifies the appropriate model listeners.
    *
    * @param call the call
    * @return the call that was added
    * @throws SystemException if a system exception occurred
    */
    public static it.smartcommunitylab.platform.idea.model.Call addCall(
        it.smartcommunitylab.platform.idea.model.Call call)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().addCall(call);
    }

    /**
    * Creates a new call with the primary key. Does not add the call to the database.
    *
    * @param callId the primary key for the new call
    * @return the new call
    */
    public static it.smartcommunitylab.platform.idea.model.Call createCall(
        long callId) {
        return getService().createCall(callId);
    }

    /**
    * Deletes the call with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param callId the primary key of the call
    * @return the call that was removed
    * @throws PortalException if a call with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static it.smartcommunitylab.platform.idea.model.Call deleteCall(
        long callId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return getService().deleteCall(callId);
    }

    /**
    * Deletes the call from the database. Also notifies the appropriate model listeners.
    *
    * @param call the call
    * @return the call that was removed
    * @throws SystemException if a system exception occurred
    */
    public static it.smartcommunitylab.platform.idea.model.Call deleteCall(
        it.smartcommunitylab.platform.idea.model.Call call)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().deleteCall(call);
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
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link it.smartcommunitylab.platform.idea.model.impl.CallModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link it.smartcommunitylab.platform.idea.model.impl.CallModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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

    public static it.smartcommunitylab.platform.idea.model.Call fetchCall(
        long callId) throws com.liferay.portal.kernel.exception.SystemException {
        return getService().fetchCall(callId);
    }

    /**
    * Returns the call with the matching UUID and company.
    *
    * @param uuid the call's UUID
    * @param companyId the primary key of the company
    * @return the matching call, or <code>null</code> if a matching call could not be found
    * @throws SystemException if a system exception occurred
    */
    public static it.smartcommunitylab.platform.idea.model.Call fetchCallByUuidAndCompanyId(
        java.lang.String uuid, long companyId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().fetchCallByUuidAndCompanyId(uuid, companyId);
    }

    /**
    * Returns the call matching the UUID and group.
    *
    * @param uuid the call's UUID
    * @param groupId the primary key of the group
    * @return the matching call, or <code>null</code> if a matching call could not be found
    * @throws SystemException if a system exception occurred
    */
    public static it.smartcommunitylab.platform.idea.model.Call fetchCallByUuidAndGroupId(
        java.lang.String uuid, long groupId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().fetchCallByUuidAndGroupId(uuid, groupId);
    }

    /**
    * Returns the call with the primary key.
    *
    * @param callId the primary key of the call
    * @return the call
    * @throws PortalException if a call with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static it.smartcommunitylab.platform.idea.model.Call getCall(
        long callId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return getService().getCall(callId);
    }

    public static com.liferay.portal.model.PersistedModel getPersistedModel(
        java.io.Serializable primaryKeyObj)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return getService().getPersistedModel(primaryKeyObj);
    }

    /**
    * Returns the call with the matching UUID and company.
    *
    * @param uuid the call's UUID
    * @param companyId the primary key of the company
    * @return the matching call
    * @throws PortalException if a matching call could not be found
    * @throws SystemException if a system exception occurred
    */
    public static it.smartcommunitylab.platform.idea.model.Call getCallByUuidAndCompanyId(
        java.lang.String uuid, long companyId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return getService().getCallByUuidAndCompanyId(uuid, companyId);
    }

    /**
    * Returns the call matching the UUID and group.
    *
    * @param uuid the call's UUID
    * @param groupId the primary key of the group
    * @return the matching call
    * @throws PortalException if a matching call could not be found
    * @throws SystemException if a system exception occurred
    */
    public static it.smartcommunitylab.platform.idea.model.Call getCallByUuidAndGroupId(
        java.lang.String uuid, long groupId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return getService().getCallByUuidAndGroupId(uuid, groupId);
    }

    /**
    * Returns a range of all the calls.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link it.smartcommunitylab.platform.idea.model.impl.CallModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of calls
    * @param end the upper bound of the range of calls (not inclusive)
    * @return the range of calls
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<it.smartcommunitylab.platform.idea.model.Call> getCalls(
        int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().getCalls(start, end);
    }

    /**
    * Returns the number of calls.
    *
    * @return the number of calls
    * @throws SystemException if a system exception occurred
    */
    public static int getCallsCount()
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().getCallsCount();
    }

    /**
    * Updates the call in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param call the call
    * @return the call that was updated
    * @throws SystemException if a system exception occurred
    */
    public static it.smartcommunitylab.platform.idea.model.Call updateCall(
        it.smartcommunitylab.platform.idea.model.Call call)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().updateCall(call);
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

    public static java.util.List<it.smartcommunitylab.platform.idea.model.Call> getOpenCalls(
        int begin, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().getOpenCalls(begin, end);
    }

    public static java.util.List<it.smartcommunitylab.platform.idea.model.Call> getInDiscussionCalls(
        int begin, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().getInDiscussionCalls(begin, end);
    }

    public static java.util.List<it.smartcommunitylab.platform.idea.model.Call> getClosedCalls(
        int begin, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().getClosedCalls(begin, end);
    }

    public static it.smartcommunitylab.platform.idea.model.Call createCall(
        long userId,
        it.smartcommunitylab.platform.idea.beans.CallBean callBean,
        com.liferay.portal.service.ServiceContext serviceContext)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return getService().createCall(userId, callBean, serviceContext);
    }

    public static void updateCall(
        it.smartcommunitylab.platform.idea.beans.CallBean callBean,
        com.liferay.portal.service.ServiceContext serviceContext)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        getService().updateCall(callBean, serviceContext);
    }

    public static void deleteCall(long callId,
        com.liferay.portal.service.ServiceContext serviceContext)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        getService().deleteCall(callId, serviceContext);
    }

    public static java.util.List<it.smartcommunitylab.platform.idea.model.Call> getCallsByCat(
        long catId) throws com.liferay.portal.kernel.exception.SystemException {
        return getService().getCallsByCat(catId);
    }

    public static java.util.List<it.smartcommunitylab.platform.idea.model.Call> getCallsByCat(
        long catId, int begin, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().getCallsByCat(catId, begin, end);
    }

    public static void toggleUserParticipation(long callId, long userId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        getService().toggleUserParticipation(callId, userId);
    }

    public static void clearService() {
        _service = null;
    }

    public static CallLocalService getService() {
        if (_service == null) {
            InvokableLocalService invokableLocalService = (InvokableLocalService) PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
                    CallLocalService.class.getName());

            if (invokableLocalService instanceof CallLocalService) {
                _service = (CallLocalService) invokableLocalService;
            } else {
                _service = new CallLocalServiceClp(invokableLocalService);
            }

            ReferenceRegistry.registerReference(CallLocalServiceUtil.class,
                "_service");
        }

        return _service;
    }

    /**
     * @deprecated As of 6.2.0
     */
    public void setService(CallLocalService service) {
    }
}
