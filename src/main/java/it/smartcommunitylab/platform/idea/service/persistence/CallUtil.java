package it.smartcommunitylab.platform.idea.service.persistence;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ReferenceRegistry;
import com.liferay.portal.service.ServiceContext;

import it.smartcommunitylab.platform.idea.model.Call;

import java.util.List;

/**
 * The persistence utility for the call service. This utility wraps {@link CallPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author mirko perillo
 * @see CallPersistence
 * @see CallPersistenceImpl
 * @generated
 */
public class CallUtil {
    private static CallPersistence _persistence;

    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify this class directly. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
     */

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#clearCache()
     */
    public static void clearCache() {
        getPersistence().clearCache();
    }

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#clearCache(com.liferay.portal.model.BaseModel)
     */
    public static void clearCache(Call call) {
        getPersistence().clearCache(call);
    }

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#countWithDynamicQuery(DynamicQuery)
     */
    public static long countWithDynamicQuery(DynamicQuery dynamicQuery)
        throws SystemException {
        return getPersistence().countWithDynamicQuery(dynamicQuery);
    }

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
     */
    public static List<Call> findWithDynamicQuery(DynamicQuery dynamicQuery)
        throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery);
    }

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
     */
    public static List<Call> findWithDynamicQuery(DynamicQuery dynamicQuery,
        int start, int end) throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
    }

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
     */
    public static List<Call> findWithDynamicQuery(DynamicQuery dynamicQuery,
        int start, int end, OrderByComparator orderByComparator)
        throws SystemException {
        return getPersistence()
                   .findWithDynamicQuery(dynamicQuery, start, end,
            orderByComparator);
    }

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel)
     */
    public static Call update(Call call) throws SystemException {
        return getPersistence().update(call);
    }

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, ServiceContext)
     */
    public static Call update(Call call, ServiceContext serviceContext)
        throws SystemException {
        return getPersistence().update(call, serviceContext);
    }

    /**
    * Returns all the calls where uuid = &#63;.
    *
    * @param uuid the uuid
    * @return the matching calls
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<it.smartcommunitylab.platform.idea.model.Call> findByUuid(
        java.lang.String uuid)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findByUuid(uuid);
    }

    /**
    * Returns a range of all the calls where uuid = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link it.smartcommunitylab.platform.idea.model.impl.CallModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param uuid the uuid
    * @param start the lower bound of the range of calls
    * @param end the upper bound of the range of calls (not inclusive)
    * @return the range of matching calls
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<it.smartcommunitylab.platform.idea.model.Call> findByUuid(
        java.lang.String uuid, int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findByUuid(uuid, start, end);
    }

    /**
    * Returns an ordered range of all the calls where uuid = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link it.smartcommunitylab.platform.idea.model.impl.CallModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param uuid the uuid
    * @param start the lower bound of the range of calls
    * @param end the upper bound of the range of calls (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of matching calls
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<it.smartcommunitylab.platform.idea.model.Call> findByUuid(
        java.lang.String uuid, int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findByUuid(uuid, start, end, orderByComparator);
    }

    /**
    * Returns the first call in the ordered set where uuid = &#63;.
    *
    * @param uuid the uuid
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching call
    * @throws it.smartcommunitylab.platform.idea.NoSuchCallException if a matching call could not be found
    * @throws SystemException if a system exception occurred
    */
    public static it.smartcommunitylab.platform.idea.model.Call findByUuid_First(
        java.lang.String uuid,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException,
            it.smartcommunitylab.platform.idea.NoSuchCallException {
        return getPersistence().findByUuid_First(uuid, orderByComparator);
    }

    /**
    * Returns the first call in the ordered set where uuid = &#63;.
    *
    * @param uuid the uuid
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching call, or <code>null</code> if a matching call could not be found
    * @throws SystemException if a system exception occurred
    */
    public static it.smartcommunitylab.platform.idea.model.Call fetchByUuid_First(
        java.lang.String uuid,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().fetchByUuid_First(uuid, orderByComparator);
    }

    /**
    * Returns the last call in the ordered set where uuid = &#63;.
    *
    * @param uuid the uuid
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching call
    * @throws it.smartcommunitylab.platform.idea.NoSuchCallException if a matching call could not be found
    * @throws SystemException if a system exception occurred
    */
    public static it.smartcommunitylab.platform.idea.model.Call findByUuid_Last(
        java.lang.String uuid,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException,
            it.smartcommunitylab.platform.idea.NoSuchCallException {
        return getPersistence().findByUuid_Last(uuid, orderByComparator);
    }

    /**
    * Returns the last call in the ordered set where uuid = &#63;.
    *
    * @param uuid the uuid
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching call, or <code>null</code> if a matching call could not be found
    * @throws SystemException if a system exception occurred
    */
    public static it.smartcommunitylab.platform.idea.model.Call fetchByUuid_Last(
        java.lang.String uuid,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().fetchByUuid_Last(uuid, orderByComparator);
    }

    /**
    * Returns the calls before and after the current call in the ordered set where uuid = &#63;.
    *
    * @param callId the primary key of the current call
    * @param uuid the uuid
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the previous, current, and next call
    * @throws it.smartcommunitylab.platform.idea.NoSuchCallException if a call with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static it.smartcommunitylab.platform.idea.model.Call[] findByUuid_PrevAndNext(
        long callId, java.lang.String uuid,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException,
            it.smartcommunitylab.platform.idea.NoSuchCallException {
        return getPersistence()
                   .findByUuid_PrevAndNext(callId, uuid, orderByComparator);
    }

    /**
    * Removes all the calls where uuid = &#63; from the database.
    *
    * @param uuid the uuid
    * @throws SystemException if a system exception occurred
    */
    public static void removeByUuid(java.lang.String uuid)
        throws com.liferay.portal.kernel.exception.SystemException {
        getPersistence().removeByUuid(uuid);
    }

    /**
    * Returns the number of calls where uuid = &#63;.
    *
    * @param uuid the uuid
    * @return the number of matching calls
    * @throws SystemException if a system exception occurred
    */
    public static int countByUuid(java.lang.String uuid)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().countByUuid(uuid);
    }

    /**
    * Returns the call where uuid = &#63; and groupId = &#63; or throws a {@link it.smartcommunitylab.platform.idea.NoSuchCallException} if it could not be found.
    *
    * @param uuid the uuid
    * @param groupId the group ID
    * @return the matching call
    * @throws it.smartcommunitylab.platform.idea.NoSuchCallException if a matching call could not be found
    * @throws SystemException if a system exception occurred
    */
    public static it.smartcommunitylab.platform.idea.model.Call findByUUID_G(
        java.lang.String uuid, long groupId)
        throws com.liferay.portal.kernel.exception.SystemException,
            it.smartcommunitylab.platform.idea.NoSuchCallException {
        return getPersistence().findByUUID_G(uuid, groupId);
    }

    /**
    * Returns the call where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
    *
    * @param uuid the uuid
    * @param groupId the group ID
    * @return the matching call, or <code>null</code> if a matching call could not be found
    * @throws SystemException if a system exception occurred
    */
    public static it.smartcommunitylab.platform.idea.model.Call fetchByUUID_G(
        java.lang.String uuid, long groupId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().fetchByUUID_G(uuid, groupId);
    }

    /**
    * Returns the call where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
    *
    * @param uuid the uuid
    * @param groupId the group ID
    * @param retrieveFromCache whether to use the finder cache
    * @return the matching call, or <code>null</code> if a matching call could not be found
    * @throws SystemException if a system exception occurred
    */
    public static it.smartcommunitylab.platform.idea.model.Call fetchByUUID_G(
        java.lang.String uuid, long groupId, boolean retrieveFromCache)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().fetchByUUID_G(uuid, groupId, retrieveFromCache);
    }

    /**
    * Removes the call where uuid = &#63; and groupId = &#63; from the database.
    *
    * @param uuid the uuid
    * @param groupId the group ID
    * @return the call that was removed
    * @throws SystemException if a system exception occurred
    */
    public static it.smartcommunitylab.platform.idea.model.Call removeByUUID_G(
        java.lang.String uuid, long groupId)
        throws com.liferay.portal.kernel.exception.SystemException,
            it.smartcommunitylab.platform.idea.NoSuchCallException {
        return getPersistence().removeByUUID_G(uuid, groupId);
    }

    /**
    * Returns the number of calls where uuid = &#63; and groupId = &#63;.
    *
    * @param uuid the uuid
    * @param groupId the group ID
    * @return the number of matching calls
    * @throws SystemException if a system exception occurred
    */
    public static int countByUUID_G(java.lang.String uuid, long groupId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().countByUUID_G(uuid, groupId);
    }

    /**
    * Returns all the calls where uuid = &#63; and companyId = &#63;.
    *
    * @param uuid the uuid
    * @param companyId the company ID
    * @return the matching calls
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<it.smartcommunitylab.platform.idea.model.Call> findByUuid_C(
        java.lang.String uuid, long companyId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findByUuid_C(uuid, companyId);
    }

    /**
    * Returns a range of all the calls where uuid = &#63; and companyId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link it.smartcommunitylab.platform.idea.model.impl.CallModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param uuid the uuid
    * @param companyId the company ID
    * @param start the lower bound of the range of calls
    * @param end the upper bound of the range of calls (not inclusive)
    * @return the range of matching calls
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<it.smartcommunitylab.platform.idea.model.Call> findByUuid_C(
        java.lang.String uuid, long companyId, int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findByUuid_C(uuid, companyId, start, end);
    }

    /**
    * Returns an ordered range of all the calls where uuid = &#63; and companyId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link it.smartcommunitylab.platform.idea.model.impl.CallModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param uuid the uuid
    * @param companyId the company ID
    * @param start the lower bound of the range of calls
    * @param end the upper bound of the range of calls (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of matching calls
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<it.smartcommunitylab.platform.idea.model.Call> findByUuid_C(
        java.lang.String uuid, long companyId, int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByUuid_C(uuid, companyId, start, end, orderByComparator);
    }

    /**
    * Returns the first call in the ordered set where uuid = &#63; and companyId = &#63;.
    *
    * @param uuid the uuid
    * @param companyId the company ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching call
    * @throws it.smartcommunitylab.platform.idea.NoSuchCallException if a matching call could not be found
    * @throws SystemException if a system exception occurred
    */
    public static it.smartcommunitylab.platform.idea.model.Call findByUuid_C_First(
        java.lang.String uuid, long companyId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException,
            it.smartcommunitylab.platform.idea.NoSuchCallException {
        return getPersistence()
                   .findByUuid_C_First(uuid, companyId, orderByComparator);
    }

    /**
    * Returns the first call in the ordered set where uuid = &#63; and companyId = &#63;.
    *
    * @param uuid the uuid
    * @param companyId the company ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching call, or <code>null</code> if a matching call could not be found
    * @throws SystemException if a system exception occurred
    */
    public static it.smartcommunitylab.platform.idea.model.Call fetchByUuid_C_First(
        java.lang.String uuid, long companyId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .fetchByUuid_C_First(uuid, companyId, orderByComparator);
    }

    /**
    * Returns the last call in the ordered set where uuid = &#63; and companyId = &#63;.
    *
    * @param uuid the uuid
    * @param companyId the company ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching call
    * @throws it.smartcommunitylab.platform.idea.NoSuchCallException if a matching call could not be found
    * @throws SystemException if a system exception occurred
    */
    public static it.smartcommunitylab.platform.idea.model.Call findByUuid_C_Last(
        java.lang.String uuid, long companyId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException,
            it.smartcommunitylab.platform.idea.NoSuchCallException {
        return getPersistence()
                   .findByUuid_C_Last(uuid, companyId, orderByComparator);
    }

    /**
    * Returns the last call in the ordered set where uuid = &#63; and companyId = &#63;.
    *
    * @param uuid the uuid
    * @param companyId the company ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching call, or <code>null</code> if a matching call could not be found
    * @throws SystemException if a system exception occurred
    */
    public static it.smartcommunitylab.platform.idea.model.Call fetchByUuid_C_Last(
        java.lang.String uuid, long companyId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .fetchByUuid_C_Last(uuid, companyId, orderByComparator);
    }

    /**
    * Returns the calls before and after the current call in the ordered set where uuid = &#63; and companyId = &#63;.
    *
    * @param callId the primary key of the current call
    * @param uuid the uuid
    * @param companyId the company ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the previous, current, and next call
    * @throws it.smartcommunitylab.platform.idea.NoSuchCallException if a call with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static it.smartcommunitylab.platform.idea.model.Call[] findByUuid_C_PrevAndNext(
        long callId, java.lang.String uuid, long companyId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException,
            it.smartcommunitylab.platform.idea.NoSuchCallException {
        return getPersistence()
                   .findByUuid_C_PrevAndNext(callId, uuid, companyId,
            orderByComparator);
    }

    /**
    * Removes all the calls where uuid = &#63; and companyId = &#63; from the database.
    *
    * @param uuid the uuid
    * @param companyId the company ID
    * @throws SystemException if a system exception occurred
    */
    public static void removeByUuid_C(java.lang.String uuid, long companyId)
        throws com.liferay.portal.kernel.exception.SystemException {
        getPersistence().removeByUuid_C(uuid, companyId);
    }

    /**
    * Returns the number of calls where uuid = &#63; and companyId = &#63;.
    *
    * @param uuid the uuid
    * @param companyId the company ID
    * @return the number of matching calls
    * @throws SystemException if a system exception occurred
    */
    public static int countByUuid_C(java.lang.String uuid, long companyId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().countByUuid_C(uuid, companyId);
    }

    /**
    * Returns all the calls where userId = &#63;.
    *
    * @param userId the user ID
    * @return the matching calls
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<it.smartcommunitylab.platform.idea.model.Call> findByUserId(
        long userId) throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findByUserId(userId);
    }

    /**
    * Returns a range of all the calls where userId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link it.smartcommunitylab.platform.idea.model.impl.CallModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param userId the user ID
    * @param start the lower bound of the range of calls
    * @param end the upper bound of the range of calls (not inclusive)
    * @return the range of matching calls
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<it.smartcommunitylab.platform.idea.model.Call> findByUserId(
        long userId, int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findByUserId(userId, start, end);
    }

    /**
    * Returns an ordered range of all the calls where userId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link it.smartcommunitylab.platform.idea.model.impl.CallModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param userId the user ID
    * @param start the lower bound of the range of calls
    * @param end the upper bound of the range of calls (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of matching calls
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<it.smartcommunitylab.platform.idea.model.Call> findByUserId(
        long userId, int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByUserId(userId, start, end, orderByComparator);
    }

    /**
    * Returns the first call in the ordered set where userId = &#63;.
    *
    * @param userId the user ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching call
    * @throws it.smartcommunitylab.platform.idea.NoSuchCallException if a matching call could not be found
    * @throws SystemException if a system exception occurred
    */
    public static it.smartcommunitylab.platform.idea.model.Call findByUserId_First(
        long userId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException,
            it.smartcommunitylab.platform.idea.NoSuchCallException {
        return getPersistence().findByUserId_First(userId, orderByComparator);
    }

    /**
    * Returns the first call in the ordered set where userId = &#63;.
    *
    * @param userId the user ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching call, or <code>null</code> if a matching call could not be found
    * @throws SystemException if a system exception occurred
    */
    public static it.smartcommunitylab.platform.idea.model.Call fetchByUserId_First(
        long userId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().fetchByUserId_First(userId, orderByComparator);
    }

    /**
    * Returns the last call in the ordered set where userId = &#63;.
    *
    * @param userId the user ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching call
    * @throws it.smartcommunitylab.platform.idea.NoSuchCallException if a matching call could not be found
    * @throws SystemException if a system exception occurred
    */
    public static it.smartcommunitylab.platform.idea.model.Call findByUserId_Last(
        long userId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException,
            it.smartcommunitylab.platform.idea.NoSuchCallException {
        return getPersistence().findByUserId_Last(userId, orderByComparator);
    }

    /**
    * Returns the last call in the ordered set where userId = &#63;.
    *
    * @param userId the user ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching call, or <code>null</code> if a matching call could not be found
    * @throws SystemException if a system exception occurred
    */
    public static it.smartcommunitylab.platform.idea.model.Call fetchByUserId_Last(
        long userId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().fetchByUserId_Last(userId, orderByComparator);
    }

    /**
    * Returns the calls before and after the current call in the ordered set where userId = &#63;.
    *
    * @param callId the primary key of the current call
    * @param userId the user ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the previous, current, and next call
    * @throws it.smartcommunitylab.platform.idea.NoSuchCallException if a call with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static it.smartcommunitylab.platform.idea.model.Call[] findByUserId_PrevAndNext(
        long callId, long userId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException,
            it.smartcommunitylab.platform.idea.NoSuchCallException {
        return getPersistence()
                   .findByUserId_PrevAndNext(callId, userId, orderByComparator);
    }

    /**
    * Removes all the calls where userId = &#63; from the database.
    *
    * @param userId the user ID
    * @throws SystemException if a system exception occurred
    */
    public static void removeByUserId(long userId)
        throws com.liferay.portal.kernel.exception.SystemException {
        getPersistence().removeByUserId(userId);
    }

    /**
    * Returns the number of calls where userId = &#63;.
    *
    * @param userId the user ID
    * @return the number of matching calls
    * @throws SystemException if a system exception occurred
    */
    public static int countByUserId(long userId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().countByUserId(userId);
    }

    /**
    * Caches the call in the entity cache if it is enabled.
    *
    * @param call the call
    */
    public static void cacheResult(
        it.smartcommunitylab.platform.idea.model.Call call) {
        getPersistence().cacheResult(call);
    }

    /**
    * Caches the calls in the entity cache if it is enabled.
    *
    * @param calls the calls
    */
    public static void cacheResult(
        java.util.List<it.smartcommunitylab.platform.idea.model.Call> calls) {
        getPersistence().cacheResult(calls);
    }

    /**
    * Creates a new call with the primary key. Does not add the call to the database.
    *
    * @param callId the primary key for the new call
    * @return the new call
    */
    public static it.smartcommunitylab.platform.idea.model.Call create(
        long callId) {
        return getPersistence().create(callId);
    }

    /**
    * Removes the call with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param callId the primary key of the call
    * @return the call that was removed
    * @throws it.smartcommunitylab.platform.idea.NoSuchCallException if a call with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static it.smartcommunitylab.platform.idea.model.Call remove(
        long callId)
        throws com.liferay.portal.kernel.exception.SystemException,
            it.smartcommunitylab.platform.idea.NoSuchCallException {
        return getPersistence().remove(callId);
    }

    public static it.smartcommunitylab.platform.idea.model.Call updateImpl(
        it.smartcommunitylab.platform.idea.model.Call call)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().updateImpl(call);
    }

    /**
    * Returns the call with the primary key or throws a {@link it.smartcommunitylab.platform.idea.NoSuchCallException} if it could not be found.
    *
    * @param callId the primary key of the call
    * @return the call
    * @throws it.smartcommunitylab.platform.idea.NoSuchCallException if a call with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static it.smartcommunitylab.platform.idea.model.Call findByPrimaryKey(
        long callId)
        throws com.liferay.portal.kernel.exception.SystemException,
            it.smartcommunitylab.platform.idea.NoSuchCallException {
        return getPersistence().findByPrimaryKey(callId);
    }

    /**
    * Returns the call with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param callId the primary key of the call
    * @return the call, or <code>null</code> if a call with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static it.smartcommunitylab.platform.idea.model.Call fetchByPrimaryKey(
        long callId) throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().fetchByPrimaryKey(callId);
    }

    /**
    * Returns all the calls.
    *
    * @return the calls
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<it.smartcommunitylab.platform.idea.model.Call> findAll()
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findAll();
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
    public static java.util.List<it.smartcommunitylab.platform.idea.model.Call> findAll(
        int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end);
    }

    /**
    * Returns an ordered range of all the calls.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link it.smartcommunitylab.platform.idea.model.impl.CallModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of calls
    * @param end the upper bound of the range of calls (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of calls
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<it.smartcommunitylab.platform.idea.model.Call> findAll(
        int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end, orderByComparator);
    }

    /**
    * Removes all the calls from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public static void removeAll()
        throws com.liferay.portal.kernel.exception.SystemException {
        getPersistence().removeAll();
    }

    /**
    * Returns the number of calls.
    *
    * @return the number of calls
    * @throws SystemException if a system exception occurred
    */
    public static int countAll()
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().countAll();
    }

    public static CallPersistence getPersistence() {
        if (_persistence == null) {
            _persistence = (CallPersistence) PortletBeanLocatorUtil.locate(it.smartcommunitylab.platform.idea.service.ClpSerializer.getServletContextName(),
                    CallPersistence.class.getName());

            ReferenceRegistry.registerReference(CallUtil.class, "_persistence");
        }

        return _persistence;
    }

    /**
     * @deprecated As of 6.2.0
     */
    public void setPersistence(CallPersistence persistence) {
    }
}
