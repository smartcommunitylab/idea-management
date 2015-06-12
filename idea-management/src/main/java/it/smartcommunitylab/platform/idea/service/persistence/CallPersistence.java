package it.smartcommunitylab.platform.idea.service.persistence;

import com.liferay.portal.service.persistence.BasePersistence;

import it.smartcommunitylab.platform.idea.model.Call;

/**
 * The persistence interface for the call service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author mirko perillo
 * @see CallPersistenceImpl
 * @see CallUtil
 * @generated
 */
public interface CallPersistence extends BasePersistence<Call> {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this interface directly. Always use {@link CallUtil} to access the call persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
     */

    /**
    * Returns all the calls where uuid = &#63;.
    *
    * @param uuid the uuid
    * @return the matching calls
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<it.smartcommunitylab.platform.idea.model.Call> findByUuid(
        java.lang.String uuid)
        throws com.liferay.portal.kernel.exception.SystemException;

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
    public java.util.List<it.smartcommunitylab.platform.idea.model.Call> findByUuid(
        java.lang.String uuid, int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException;

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
    public java.util.List<it.smartcommunitylab.platform.idea.model.Call> findByUuid(
        java.lang.String uuid, int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the first call in the ordered set where uuid = &#63;.
    *
    * @param uuid the uuid
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching call
    * @throws it.smartcommunitylab.platform.idea.NoSuchCallException if a matching call could not be found
    * @throws SystemException if a system exception occurred
    */
    public it.smartcommunitylab.platform.idea.model.Call findByUuid_First(
        java.lang.String uuid,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException,
            it.smartcommunitylab.platform.idea.NoSuchCallException;

    /**
    * Returns the first call in the ordered set where uuid = &#63;.
    *
    * @param uuid the uuid
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching call, or <code>null</code> if a matching call could not be found
    * @throws SystemException if a system exception occurred
    */
    public it.smartcommunitylab.platform.idea.model.Call fetchByUuid_First(
        java.lang.String uuid,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the last call in the ordered set where uuid = &#63;.
    *
    * @param uuid the uuid
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching call
    * @throws it.smartcommunitylab.platform.idea.NoSuchCallException if a matching call could not be found
    * @throws SystemException if a system exception occurred
    */
    public it.smartcommunitylab.platform.idea.model.Call findByUuid_Last(
        java.lang.String uuid,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException,
            it.smartcommunitylab.platform.idea.NoSuchCallException;

    /**
    * Returns the last call in the ordered set where uuid = &#63;.
    *
    * @param uuid the uuid
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching call, or <code>null</code> if a matching call could not be found
    * @throws SystemException if a system exception occurred
    */
    public it.smartcommunitylab.platform.idea.model.Call fetchByUuid_Last(
        java.lang.String uuid,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

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
    public it.smartcommunitylab.platform.idea.model.Call[] findByUuid_PrevAndNext(
        long callId, java.lang.String uuid,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException,
            it.smartcommunitylab.platform.idea.NoSuchCallException;

    /**
    * Removes all the calls where uuid = &#63; from the database.
    *
    * @param uuid the uuid
    * @throws SystemException if a system exception occurred
    */
    public void removeByUuid(java.lang.String uuid)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the number of calls where uuid = &#63;.
    *
    * @param uuid the uuid
    * @return the number of matching calls
    * @throws SystemException if a system exception occurred
    */
    public int countByUuid(java.lang.String uuid)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the call where uuid = &#63; and groupId = &#63; or throws a {@link it.smartcommunitylab.platform.idea.NoSuchCallException} if it could not be found.
    *
    * @param uuid the uuid
    * @param groupId the group ID
    * @return the matching call
    * @throws it.smartcommunitylab.platform.idea.NoSuchCallException if a matching call could not be found
    * @throws SystemException if a system exception occurred
    */
    public it.smartcommunitylab.platform.idea.model.Call findByUUID_G(
        java.lang.String uuid, long groupId)
        throws com.liferay.portal.kernel.exception.SystemException,
            it.smartcommunitylab.platform.idea.NoSuchCallException;

    /**
    * Returns the call where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
    *
    * @param uuid the uuid
    * @param groupId the group ID
    * @return the matching call, or <code>null</code> if a matching call could not be found
    * @throws SystemException if a system exception occurred
    */
    public it.smartcommunitylab.platform.idea.model.Call fetchByUUID_G(
        java.lang.String uuid, long groupId)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the call where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
    *
    * @param uuid the uuid
    * @param groupId the group ID
    * @param retrieveFromCache whether to use the finder cache
    * @return the matching call, or <code>null</code> if a matching call could not be found
    * @throws SystemException if a system exception occurred
    */
    public it.smartcommunitylab.platform.idea.model.Call fetchByUUID_G(
        java.lang.String uuid, long groupId, boolean retrieveFromCache)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Removes the call where uuid = &#63; and groupId = &#63; from the database.
    *
    * @param uuid the uuid
    * @param groupId the group ID
    * @return the call that was removed
    * @throws SystemException if a system exception occurred
    */
    public it.smartcommunitylab.platform.idea.model.Call removeByUUID_G(
        java.lang.String uuid, long groupId)
        throws com.liferay.portal.kernel.exception.SystemException,
            it.smartcommunitylab.platform.idea.NoSuchCallException;

    /**
    * Returns the number of calls where uuid = &#63; and groupId = &#63;.
    *
    * @param uuid the uuid
    * @param groupId the group ID
    * @return the number of matching calls
    * @throws SystemException if a system exception occurred
    */
    public int countByUUID_G(java.lang.String uuid, long groupId)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns all the calls where uuid = &#63; and companyId = &#63;.
    *
    * @param uuid the uuid
    * @param companyId the company ID
    * @return the matching calls
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<it.smartcommunitylab.platform.idea.model.Call> findByUuid_C(
        java.lang.String uuid, long companyId)
        throws com.liferay.portal.kernel.exception.SystemException;

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
    public java.util.List<it.smartcommunitylab.platform.idea.model.Call> findByUuid_C(
        java.lang.String uuid, long companyId, int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException;

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
    public java.util.List<it.smartcommunitylab.platform.idea.model.Call> findByUuid_C(
        java.lang.String uuid, long companyId, int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

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
    public it.smartcommunitylab.platform.idea.model.Call findByUuid_C_First(
        java.lang.String uuid, long companyId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException,
            it.smartcommunitylab.platform.idea.NoSuchCallException;

    /**
    * Returns the first call in the ordered set where uuid = &#63; and companyId = &#63;.
    *
    * @param uuid the uuid
    * @param companyId the company ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching call, or <code>null</code> if a matching call could not be found
    * @throws SystemException if a system exception occurred
    */
    public it.smartcommunitylab.platform.idea.model.Call fetchByUuid_C_First(
        java.lang.String uuid, long companyId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

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
    public it.smartcommunitylab.platform.idea.model.Call findByUuid_C_Last(
        java.lang.String uuid, long companyId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException,
            it.smartcommunitylab.platform.idea.NoSuchCallException;

    /**
    * Returns the last call in the ordered set where uuid = &#63; and companyId = &#63;.
    *
    * @param uuid the uuid
    * @param companyId the company ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching call, or <code>null</code> if a matching call could not be found
    * @throws SystemException if a system exception occurred
    */
    public it.smartcommunitylab.platform.idea.model.Call fetchByUuid_C_Last(
        java.lang.String uuid, long companyId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

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
    public it.smartcommunitylab.platform.idea.model.Call[] findByUuid_C_PrevAndNext(
        long callId, java.lang.String uuid, long companyId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException,
            it.smartcommunitylab.platform.idea.NoSuchCallException;

    /**
    * Removes all the calls where uuid = &#63; and companyId = &#63; from the database.
    *
    * @param uuid the uuid
    * @param companyId the company ID
    * @throws SystemException if a system exception occurred
    */
    public void removeByUuid_C(java.lang.String uuid, long companyId)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the number of calls where uuid = &#63; and companyId = &#63;.
    *
    * @param uuid the uuid
    * @param companyId the company ID
    * @return the number of matching calls
    * @throws SystemException if a system exception occurred
    */
    public int countByUuid_C(java.lang.String uuid, long companyId)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns all the calls where userId = &#63;.
    *
    * @param userId the user ID
    * @return the matching calls
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<it.smartcommunitylab.platform.idea.model.Call> findByUserId(
        long userId) throws com.liferay.portal.kernel.exception.SystemException;

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
    public java.util.List<it.smartcommunitylab.platform.idea.model.Call> findByUserId(
        long userId, int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException;

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
    public java.util.List<it.smartcommunitylab.platform.idea.model.Call> findByUserId(
        long userId, int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the first call in the ordered set where userId = &#63;.
    *
    * @param userId the user ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching call
    * @throws it.smartcommunitylab.platform.idea.NoSuchCallException if a matching call could not be found
    * @throws SystemException if a system exception occurred
    */
    public it.smartcommunitylab.platform.idea.model.Call findByUserId_First(
        long userId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException,
            it.smartcommunitylab.platform.idea.NoSuchCallException;

    /**
    * Returns the first call in the ordered set where userId = &#63;.
    *
    * @param userId the user ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching call, or <code>null</code> if a matching call could not be found
    * @throws SystemException if a system exception occurred
    */
    public it.smartcommunitylab.platform.idea.model.Call fetchByUserId_First(
        long userId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the last call in the ordered set where userId = &#63;.
    *
    * @param userId the user ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching call
    * @throws it.smartcommunitylab.platform.idea.NoSuchCallException if a matching call could not be found
    * @throws SystemException if a system exception occurred
    */
    public it.smartcommunitylab.platform.idea.model.Call findByUserId_Last(
        long userId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException,
            it.smartcommunitylab.platform.idea.NoSuchCallException;

    /**
    * Returns the last call in the ordered set where userId = &#63;.
    *
    * @param userId the user ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching call, or <code>null</code> if a matching call could not be found
    * @throws SystemException if a system exception occurred
    */
    public it.smartcommunitylab.platform.idea.model.Call fetchByUserId_Last(
        long userId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

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
    public it.smartcommunitylab.platform.idea.model.Call[] findByUserId_PrevAndNext(
        long callId, long userId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException,
            it.smartcommunitylab.platform.idea.NoSuchCallException;

    /**
    * Removes all the calls where userId = &#63; from the database.
    *
    * @param userId the user ID
    * @throws SystemException if a system exception occurred
    */
    public void removeByUserId(long userId)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the number of calls where userId = &#63;.
    *
    * @param userId the user ID
    * @return the number of matching calls
    * @throws SystemException if a system exception occurred
    */
    public int countByUserId(long userId)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Caches the call in the entity cache if it is enabled.
    *
    * @param call the call
    */
    public void cacheResult(it.smartcommunitylab.platform.idea.model.Call call);

    /**
    * Caches the calls in the entity cache if it is enabled.
    *
    * @param calls the calls
    */
    public void cacheResult(
        java.util.List<it.smartcommunitylab.platform.idea.model.Call> calls);

    /**
    * Creates a new call with the primary key. Does not add the call to the database.
    *
    * @param callId the primary key for the new call
    * @return the new call
    */
    public it.smartcommunitylab.platform.idea.model.Call create(long callId);

    /**
    * Removes the call with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param callId the primary key of the call
    * @return the call that was removed
    * @throws it.smartcommunitylab.platform.idea.NoSuchCallException if a call with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public it.smartcommunitylab.platform.idea.model.Call remove(long callId)
        throws com.liferay.portal.kernel.exception.SystemException,
            it.smartcommunitylab.platform.idea.NoSuchCallException;

    public it.smartcommunitylab.platform.idea.model.Call updateImpl(
        it.smartcommunitylab.platform.idea.model.Call call)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the call with the primary key or throws a {@link it.smartcommunitylab.platform.idea.NoSuchCallException} if it could not be found.
    *
    * @param callId the primary key of the call
    * @return the call
    * @throws it.smartcommunitylab.platform.idea.NoSuchCallException if a call with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public it.smartcommunitylab.platform.idea.model.Call findByPrimaryKey(
        long callId)
        throws com.liferay.portal.kernel.exception.SystemException,
            it.smartcommunitylab.platform.idea.NoSuchCallException;

    /**
    * Returns the call with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param callId the primary key of the call
    * @return the call, or <code>null</code> if a call with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public it.smartcommunitylab.platform.idea.model.Call fetchByPrimaryKey(
        long callId) throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns all the calls.
    *
    * @return the calls
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<it.smartcommunitylab.platform.idea.model.Call> findAll()
        throws com.liferay.portal.kernel.exception.SystemException;

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
    public java.util.List<it.smartcommunitylab.platform.idea.model.Call> findAll(
        int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException;

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
    public java.util.List<it.smartcommunitylab.platform.idea.model.Call> findAll(
        int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Removes all the calls from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public void removeAll()
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the number of calls.
    *
    * @return the number of calls
    * @throws SystemException if a system exception occurred
    */
    public int countAll()
        throws com.liferay.portal.kernel.exception.SystemException;
}
