package it.smartcommunitylab.platform.idea.service.persistence;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ReferenceRegistry;
import com.liferay.portal.service.ServiceContext;

import it.smartcommunitylab.platform.idea.model.Idea;

import java.util.List;

/**
 * The persistence utility for the idea service. This utility wraps {@link IdeaPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author mirko perillo
 * @see IdeaPersistence
 * @see IdeaPersistenceImpl
 * @generated
 */
public class IdeaUtil {
    private static IdeaPersistence _persistence;

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
    public static void clearCache(Idea idea) {
        getPersistence().clearCache(idea);
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
    public static List<Idea> findWithDynamicQuery(DynamicQuery dynamicQuery)
        throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery);
    }

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
     */
    public static List<Idea> findWithDynamicQuery(DynamicQuery dynamicQuery,
        int start, int end) throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
    }

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
     */
    public static List<Idea> findWithDynamicQuery(DynamicQuery dynamicQuery,
        int start, int end, OrderByComparator orderByComparator)
        throws SystemException {
        return getPersistence()
                   .findWithDynamicQuery(dynamicQuery, start, end,
            orderByComparator);
    }

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel)
     */
    public static Idea update(Idea idea) throws SystemException {
        return getPersistence().update(idea);
    }

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, ServiceContext)
     */
    public static Idea update(Idea idea, ServiceContext serviceContext)
        throws SystemException {
        return getPersistence().update(idea, serviceContext);
    }

    /**
    * Returns all the ideas where uuid = &#63;.
    *
    * @param uuid the uuid
    * @return the matching ideas
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<it.smartcommunitylab.platform.idea.model.Idea> findByUuid(
        java.lang.String uuid)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findByUuid(uuid);
    }

    /**
    * Returns a range of all the ideas where uuid = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link it.smartcommunitylab.platform.idea.model.impl.IdeaModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param uuid the uuid
    * @param start the lower bound of the range of ideas
    * @param end the upper bound of the range of ideas (not inclusive)
    * @return the range of matching ideas
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<it.smartcommunitylab.platform.idea.model.Idea> findByUuid(
        java.lang.String uuid, int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findByUuid(uuid, start, end);
    }

    /**
    * Returns an ordered range of all the ideas where uuid = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link it.smartcommunitylab.platform.idea.model.impl.IdeaModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param uuid the uuid
    * @param start the lower bound of the range of ideas
    * @param end the upper bound of the range of ideas (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of matching ideas
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<it.smartcommunitylab.platform.idea.model.Idea> findByUuid(
        java.lang.String uuid, int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findByUuid(uuid, start, end, orderByComparator);
    }

    /**
    * Returns the first idea in the ordered set where uuid = &#63;.
    *
    * @param uuid the uuid
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching idea
    * @throws it.smartcommunitylab.platform.idea.NoSuchIdeaException if a matching idea could not be found
    * @throws SystemException if a system exception occurred
    */
    public static it.smartcommunitylab.platform.idea.model.Idea findByUuid_First(
        java.lang.String uuid,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException,
            it.smartcommunitylab.platform.idea.NoSuchIdeaException {
        return getPersistence().findByUuid_First(uuid, orderByComparator);
    }

    /**
    * Returns the first idea in the ordered set where uuid = &#63;.
    *
    * @param uuid the uuid
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching idea, or <code>null</code> if a matching idea could not be found
    * @throws SystemException if a system exception occurred
    */
    public static it.smartcommunitylab.platform.idea.model.Idea fetchByUuid_First(
        java.lang.String uuid,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().fetchByUuid_First(uuid, orderByComparator);
    }

    /**
    * Returns the last idea in the ordered set where uuid = &#63;.
    *
    * @param uuid the uuid
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching idea
    * @throws it.smartcommunitylab.platform.idea.NoSuchIdeaException if a matching idea could not be found
    * @throws SystemException if a system exception occurred
    */
    public static it.smartcommunitylab.platform.idea.model.Idea findByUuid_Last(
        java.lang.String uuid,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException,
            it.smartcommunitylab.platform.idea.NoSuchIdeaException {
        return getPersistence().findByUuid_Last(uuid, orderByComparator);
    }

    /**
    * Returns the last idea in the ordered set where uuid = &#63;.
    *
    * @param uuid the uuid
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching idea, or <code>null</code> if a matching idea could not be found
    * @throws SystemException if a system exception occurred
    */
    public static it.smartcommunitylab.platform.idea.model.Idea fetchByUuid_Last(
        java.lang.String uuid,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().fetchByUuid_Last(uuid, orderByComparator);
    }

    /**
    * Returns the ideas before and after the current idea in the ordered set where uuid = &#63;.
    *
    * @param ideaId the primary key of the current idea
    * @param uuid the uuid
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the previous, current, and next idea
    * @throws it.smartcommunitylab.platform.idea.NoSuchIdeaException if a idea with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static it.smartcommunitylab.platform.idea.model.Idea[] findByUuid_PrevAndNext(
        long ideaId, java.lang.String uuid,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException,
            it.smartcommunitylab.platform.idea.NoSuchIdeaException {
        return getPersistence()
                   .findByUuid_PrevAndNext(ideaId, uuid, orderByComparator);
    }

    /**
    * Removes all the ideas where uuid = &#63; from the database.
    *
    * @param uuid the uuid
    * @throws SystemException if a system exception occurred
    */
    public static void removeByUuid(java.lang.String uuid)
        throws com.liferay.portal.kernel.exception.SystemException {
        getPersistence().removeByUuid(uuid);
    }

    /**
    * Returns the number of ideas where uuid = &#63;.
    *
    * @param uuid the uuid
    * @return the number of matching ideas
    * @throws SystemException if a system exception occurred
    */
    public static int countByUuid(java.lang.String uuid)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().countByUuid(uuid);
    }

    /**
    * Returns the idea where uuid = &#63; and groupId = &#63; or throws a {@link it.smartcommunitylab.platform.idea.NoSuchIdeaException} if it could not be found.
    *
    * @param uuid the uuid
    * @param groupId the group ID
    * @return the matching idea
    * @throws it.smartcommunitylab.platform.idea.NoSuchIdeaException if a matching idea could not be found
    * @throws SystemException if a system exception occurred
    */
    public static it.smartcommunitylab.platform.idea.model.Idea findByUUID_G(
        java.lang.String uuid, long groupId)
        throws com.liferay.portal.kernel.exception.SystemException,
            it.smartcommunitylab.platform.idea.NoSuchIdeaException {
        return getPersistence().findByUUID_G(uuid, groupId);
    }

    /**
    * Returns the idea where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
    *
    * @param uuid the uuid
    * @param groupId the group ID
    * @return the matching idea, or <code>null</code> if a matching idea could not be found
    * @throws SystemException if a system exception occurred
    */
    public static it.smartcommunitylab.platform.idea.model.Idea fetchByUUID_G(
        java.lang.String uuid, long groupId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().fetchByUUID_G(uuid, groupId);
    }

    /**
    * Returns the idea where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
    *
    * @param uuid the uuid
    * @param groupId the group ID
    * @param retrieveFromCache whether to use the finder cache
    * @return the matching idea, or <code>null</code> if a matching idea could not be found
    * @throws SystemException if a system exception occurred
    */
    public static it.smartcommunitylab.platform.idea.model.Idea fetchByUUID_G(
        java.lang.String uuid, long groupId, boolean retrieveFromCache)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().fetchByUUID_G(uuid, groupId, retrieveFromCache);
    }

    /**
    * Removes the idea where uuid = &#63; and groupId = &#63; from the database.
    *
    * @param uuid the uuid
    * @param groupId the group ID
    * @return the idea that was removed
    * @throws SystemException if a system exception occurred
    */
    public static it.smartcommunitylab.platform.idea.model.Idea removeByUUID_G(
        java.lang.String uuid, long groupId)
        throws com.liferay.portal.kernel.exception.SystemException,
            it.smartcommunitylab.platform.idea.NoSuchIdeaException {
        return getPersistence().removeByUUID_G(uuid, groupId);
    }

    /**
    * Returns the number of ideas where uuid = &#63; and groupId = &#63;.
    *
    * @param uuid the uuid
    * @param groupId the group ID
    * @return the number of matching ideas
    * @throws SystemException if a system exception occurred
    */
    public static int countByUUID_G(java.lang.String uuid, long groupId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().countByUUID_G(uuid, groupId);
    }

    /**
    * Returns all the ideas where uuid = &#63; and companyId = &#63;.
    *
    * @param uuid the uuid
    * @param companyId the company ID
    * @return the matching ideas
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<it.smartcommunitylab.platform.idea.model.Idea> findByUuid_C(
        java.lang.String uuid, long companyId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findByUuid_C(uuid, companyId);
    }

    /**
    * Returns a range of all the ideas where uuid = &#63; and companyId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link it.smartcommunitylab.platform.idea.model.impl.IdeaModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param uuid the uuid
    * @param companyId the company ID
    * @param start the lower bound of the range of ideas
    * @param end the upper bound of the range of ideas (not inclusive)
    * @return the range of matching ideas
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<it.smartcommunitylab.platform.idea.model.Idea> findByUuid_C(
        java.lang.String uuid, long companyId, int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findByUuid_C(uuid, companyId, start, end);
    }

    /**
    * Returns an ordered range of all the ideas where uuid = &#63; and companyId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link it.smartcommunitylab.platform.idea.model.impl.IdeaModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param uuid the uuid
    * @param companyId the company ID
    * @param start the lower bound of the range of ideas
    * @param end the upper bound of the range of ideas (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of matching ideas
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<it.smartcommunitylab.platform.idea.model.Idea> findByUuid_C(
        java.lang.String uuid, long companyId, int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByUuid_C(uuid, companyId, start, end, orderByComparator);
    }

    /**
    * Returns the first idea in the ordered set where uuid = &#63; and companyId = &#63;.
    *
    * @param uuid the uuid
    * @param companyId the company ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching idea
    * @throws it.smartcommunitylab.platform.idea.NoSuchIdeaException if a matching idea could not be found
    * @throws SystemException if a system exception occurred
    */
    public static it.smartcommunitylab.platform.idea.model.Idea findByUuid_C_First(
        java.lang.String uuid, long companyId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException,
            it.smartcommunitylab.platform.idea.NoSuchIdeaException {
        return getPersistence()
                   .findByUuid_C_First(uuid, companyId, orderByComparator);
    }

    /**
    * Returns the first idea in the ordered set where uuid = &#63; and companyId = &#63;.
    *
    * @param uuid the uuid
    * @param companyId the company ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching idea, or <code>null</code> if a matching idea could not be found
    * @throws SystemException if a system exception occurred
    */
    public static it.smartcommunitylab.platform.idea.model.Idea fetchByUuid_C_First(
        java.lang.String uuid, long companyId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .fetchByUuid_C_First(uuid, companyId, orderByComparator);
    }

    /**
    * Returns the last idea in the ordered set where uuid = &#63; and companyId = &#63;.
    *
    * @param uuid the uuid
    * @param companyId the company ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching idea
    * @throws it.smartcommunitylab.platform.idea.NoSuchIdeaException if a matching idea could not be found
    * @throws SystemException if a system exception occurred
    */
    public static it.smartcommunitylab.platform.idea.model.Idea findByUuid_C_Last(
        java.lang.String uuid, long companyId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException,
            it.smartcommunitylab.platform.idea.NoSuchIdeaException {
        return getPersistence()
                   .findByUuid_C_Last(uuid, companyId, orderByComparator);
    }

    /**
    * Returns the last idea in the ordered set where uuid = &#63; and companyId = &#63;.
    *
    * @param uuid the uuid
    * @param companyId the company ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching idea, or <code>null</code> if a matching idea could not be found
    * @throws SystemException if a system exception occurred
    */
    public static it.smartcommunitylab.platform.idea.model.Idea fetchByUuid_C_Last(
        java.lang.String uuid, long companyId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .fetchByUuid_C_Last(uuid, companyId, orderByComparator);
    }

    /**
    * Returns the ideas before and after the current idea in the ordered set where uuid = &#63; and companyId = &#63;.
    *
    * @param ideaId the primary key of the current idea
    * @param uuid the uuid
    * @param companyId the company ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the previous, current, and next idea
    * @throws it.smartcommunitylab.platform.idea.NoSuchIdeaException if a idea with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static it.smartcommunitylab.platform.idea.model.Idea[] findByUuid_C_PrevAndNext(
        long ideaId, java.lang.String uuid, long companyId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException,
            it.smartcommunitylab.platform.idea.NoSuchIdeaException {
        return getPersistence()
                   .findByUuid_C_PrevAndNext(ideaId, uuid, companyId,
            orderByComparator);
    }

    /**
    * Removes all the ideas where uuid = &#63; and companyId = &#63; from the database.
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
    * Returns the number of ideas where uuid = &#63; and companyId = &#63;.
    *
    * @param uuid the uuid
    * @param companyId the company ID
    * @return the number of matching ideas
    * @throws SystemException if a system exception occurred
    */
    public static int countByUuid_C(java.lang.String uuid, long companyId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().countByUuid_C(uuid, companyId);
    }

    /**
    * Returns all the ideas where groupId = &#63;.
    *
    * @param groupId the group ID
    * @return the matching ideas
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<it.smartcommunitylab.platform.idea.model.Idea> findByGroupId(
        long groupId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findByGroupId(groupId);
    }

    /**
    * Returns a range of all the ideas where groupId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link it.smartcommunitylab.platform.idea.model.impl.IdeaModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param groupId the group ID
    * @param start the lower bound of the range of ideas
    * @param end the upper bound of the range of ideas (not inclusive)
    * @return the range of matching ideas
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<it.smartcommunitylab.platform.idea.model.Idea> findByGroupId(
        long groupId, int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findByGroupId(groupId, start, end);
    }

    /**
    * Returns an ordered range of all the ideas where groupId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link it.smartcommunitylab.platform.idea.model.impl.IdeaModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param groupId the group ID
    * @param start the lower bound of the range of ideas
    * @param end the upper bound of the range of ideas (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of matching ideas
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<it.smartcommunitylab.platform.idea.model.Idea> findByGroupId(
        long groupId, int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByGroupId(groupId, start, end, orderByComparator);
    }

    /**
    * Returns the first idea in the ordered set where groupId = &#63;.
    *
    * @param groupId the group ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching idea
    * @throws it.smartcommunitylab.platform.idea.NoSuchIdeaException if a matching idea could not be found
    * @throws SystemException if a system exception occurred
    */
    public static it.smartcommunitylab.platform.idea.model.Idea findByGroupId_First(
        long groupId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException,
            it.smartcommunitylab.platform.idea.NoSuchIdeaException {
        return getPersistence().findByGroupId_First(groupId, orderByComparator);
    }

    /**
    * Returns the first idea in the ordered set where groupId = &#63;.
    *
    * @param groupId the group ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching idea, or <code>null</code> if a matching idea could not be found
    * @throws SystemException if a system exception occurred
    */
    public static it.smartcommunitylab.platform.idea.model.Idea fetchByGroupId_First(
        long groupId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().fetchByGroupId_First(groupId, orderByComparator);
    }

    /**
    * Returns the last idea in the ordered set where groupId = &#63;.
    *
    * @param groupId the group ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching idea
    * @throws it.smartcommunitylab.platform.idea.NoSuchIdeaException if a matching idea could not be found
    * @throws SystemException if a system exception occurred
    */
    public static it.smartcommunitylab.platform.idea.model.Idea findByGroupId_Last(
        long groupId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException,
            it.smartcommunitylab.platform.idea.NoSuchIdeaException {
        return getPersistence().findByGroupId_Last(groupId, orderByComparator);
    }

    /**
    * Returns the last idea in the ordered set where groupId = &#63;.
    *
    * @param groupId the group ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching idea, or <code>null</code> if a matching idea could not be found
    * @throws SystemException if a system exception occurred
    */
    public static it.smartcommunitylab.platform.idea.model.Idea fetchByGroupId_Last(
        long groupId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().fetchByGroupId_Last(groupId, orderByComparator);
    }

    /**
    * Returns the ideas before and after the current idea in the ordered set where groupId = &#63;.
    *
    * @param ideaId the primary key of the current idea
    * @param groupId the group ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the previous, current, and next idea
    * @throws it.smartcommunitylab.platform.idea.NoSuchIdeaException if a idea with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static it.smartcommunitylab.platform.idea.model.Idea[] findByGroupId_PrevAndNext(
        long ideaId, long groupId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException,
            it.smartcommunitylab.platform.idea.NoSuchIdeaException {
        return getPersistence()
                   .findByGroupId_PrevAndNext(ideaId, groupId, orderByComparator);
    }

    /**
    * Returns all the ideas that the user has permission to view where groupId = &#63;.
    *
    * @param groupId the group ID
    * @return the matching ideas that the user has permission to view
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<it.smartcommunitylab.platform.idea.model.Idea> filterFindByGroupId(
        long groupId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().filterFindByGroupId(groupId);
    }

    /**
    * Returns a range of all the ideas that the user has permission to view where groupId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link it.smartcommunitylab.platform.idea.model.impl.IdeaModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param groupId the group ID
    * @param start the lower bound of the range of ideas
    * @param end the upper bound of the range of ideas (not inclusive)
    * @return the range of matching ideas that the user has permission to view
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<it.smartcommunitylab.platform.idea.model.Idea> filterFindByGroupId(
        long groupId, int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().filterFindByGroupId(groupId, start, end);
    }

    /**
    * Returns an ordered range of all the ideas that the user has permissions to view where groupId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link it.smartcommunitylab.platform.idea.model.impl.IdeaModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param groupId the group ID
    * @param start the lower bound of the range of ideas
    * @param end the upper bound of the range of ideas (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of matching ideas that the user has permission to view
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<it.smartcommunitylab.platform.idea.model.Idea> filterFindByGroupId(
        long groupId, int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .filterFindByGroupId(groupId, start, end, orderByComparator);
    }

    /**
    * Returns the ideas before and after the current idea in the ordered set of ideas that the user has permission to view where groupId = &#63;.
    *
    * @param ideaId the primary key of the current idea
    * @param groupId the group ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the previous, current, and next idea
    * @throws it.smartcommunitylab.platform.idea.NoSuchIdeaException if a idea with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static it.smartcommunitylab.platform.idea.model.Idea[] filterFindByGroupId_PrevAndNext(
        long ideaId, long groupId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException,
            it.smartcommunitylab.platform.idea.NoSuchIdeaException {
        return getPersistence()
                   .filterFindByGroupId_PrevAndNext(ideaId, groupId,
            orderByComparator);
    }

    /**
    * Removes all the ideas where groupId = &#63; from the database.
    *
    * @param groupId the group ID
    * @throws SystemException if a system exception occurred
    */
    public static void removeByGroupId(long groupId)
        throws com.liferay.portal.kernel.exception.SystemException {
        getPersistence().removeByGroupId(groupId);
    }

    /**
    * Returns the number of ideas where groupId = &#63;.
    *
    * @param groupId the group ID
    * @return the number of matching ideas
    * @throws SystemException if a system exception occurred
    */
    public static int countByGroupId(long groupId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().countByGroupId(groupId);
    }

    /**
    * Returns the number of ideas that the user has permission to view where groupId = &#63;.
    *
    * @param groupId the group ID
    * @return the number of matching ideas that the user has permission to view
    * @throws SystemException if a system exception occurred
    */
    public static int filterCountByGroupId(long groupId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().filterCountByGroupId(groupId);
    }

    /**
    * Returns all the ideas where callId = &#63;.
    *
    * @param callId the call ID
    * @return the matching ideas
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<it.smartcommunitylab.platform.idea.model.Idea> findByCallId(
        long callId) throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findByCallId(callId);
    }

    /**
    * Returns a range of all the ideas where callId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link it.smartcommunitylab.platform.idea.model.impl.IdeaModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param callId the call ID
    * @param start the lower bound of the range of ideas
    * @param end the upper bound of the range of ideas (not inclusive)
    * @return the range of matching ideas
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<it.smartcommunitylab.platform.idea.model.Idea> findByCallId(
        long callId, int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findByCallId(callId, start, end);
    }

    /**
    * Returns an ordered range of all the ideas where callId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link it.smartcommunitylab.platform.idea.model.impl.IdeaModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param callId the call ID
    * @param start the lower bound of the range of ideas
    * @param end the upper bound of the range of ideas (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of matching ideas
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<it.smartcommunitylab.platform.idea.model.Idea> findByCallId(
        long callId, int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByCallId(callId, start, end, orderByComparator);
    }

    /**
    * Returns the first idea in the ordered set where callId = &#63;.
    *
    * @param callId the call ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching idea
    * @throws it.smartcommunitylab.platform.idea.NoSuchIdeaException if a matching idea could not be found
    * @throws SystemException if a system exception occurred
    */
    public static it.smartcommunitylab.platform.idea.model.Idea findByCallId_First(
        long callId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException,
            it.smartcommunitylab.platform.idea.NoSuchIdeaException {
        return getPersistence().findByCallId_First(callId, orderByComparator);
    }

    /**
    * Returns the first idea in the ordered set where callId = &#63;.
    *
    * @param callId the call ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching idea, or <code>null</code> if a matching idea could not be found
    * @throws SystemException if a system exception occurred
    */
    public static it.smartcommunitylab.platform.idea.model.Idea fetchByCallId_First(
        long callId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().fetchByCallId_First(callId, orderByComparator);
    }

    /**
    * Returns the last idea in the ordered set where callId = &#63;.
    *
    * @param callId the call ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching idea
    * @throws it.smartcommunitylab.platform.idea.NoSuchIdeaException if a matching idea could not be found
    * @throws SystemException if a system exception occurred
    */
    public static it.smartcommunitylab.platform.idea.model.Idea findByCallId_Last(
        long callId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException,
            it.smartcommunitylab.platform.idea.NoSuchIdeaException {
        return getPersistence().findByCallId_Last(callId, orderByComparator);
    }

    /**
    * Returns the last idea in the ordered set where callId = &#63;.
    *
    * @param callId the call ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching idea, or <code>null</code> if a matching idea could not be found
    * @throws SystemException if a system exception occurred
    */
    public static it.smartcommunitylab.platform.idea.model.Idea fetchByCallId_Last(
        long callId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().fetchByCallId_Last(callId, orderByComparator);
    }

    /**
    * Returns the ideas before and after the current idea in the ordered set where callId = &#63;.
    *
    * @param ideaId the primary key of the current idea
    * @param callId the call ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the previous, current, and next idea
    * @throws it.smartcommunitylab.platform.idea.NoSuchIdeaException if a idea with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static it.smartcommunitylab.platform.idea.model.Idea[] findByCallId_PrevAndNext(
        long ideaId, long callId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException,
            it.smartcommunitylab.platform.idea.NoSuchIdeaException {
        return getPersistence()
                   .findByCallId_PrevAndNext(ideaId, callId, orderByComparator);
    }

    /**
    * Removes all the ideas where callId = &#63; from the database.
    *
    * @param callId the call ID
    * @throws SystemException if a system exception occurred
    */
    public static void removeByCallId(long callId)
        throws com.liferay.portal.kernel.exception.SystemException {
        getPersistence().removeByCallId(callId);
    }

    /**
    * Returns the number of ideas where callId = &#63;.
    *
    * @param callId the call ID
    * @return the number of matching ideas
    * @throws SystemException if a system exception occurred
    */
    public static int countByCallId(long callId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().countByCallId(callId);
    }

    /**
    * Caches the idea in the entity cache if it is enabled.
    *
    * @param idea the idea
    */
    public static void cacheResult(
        it.smartcommunitylab.platform.idea.model.Idea idea) {
        getPersistence().cacheResult(idea);
    }

    /**
    * Caches the ideas in the entity cache if it is enabled.
    *
    * @param ideas the ideas
    */
    public static void cacheResult(
        java.util.List<it.smartcommunitylab.platform.idea.model.Idea> ideas) {
        getPersistence().cacheResult(ideas);
    }

    /**
    * Creates a new idea with the primary key. Does not add the idea to the database.
    *
    * @param ideaId the primary key for the new idea
    * @return the new idea
    */
    public static it.smartcommunitylab.platform.idea.model.Idea create(
        long ideaId) {
        return getPersistence().create(ideaId);
    }

    /**
    * Removes the idea with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param ideaId the primary key of the idea
    * @return the idea that was removed
    * @throws it.smartcommunitylab.platform.idea.NoSuchIdeaException if a idea with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static it.smartcommunitylab.platform.idea.model.Idea remove(
        long ideaId)
        throws com.liferay.portal.kernel.exception.SystemException,
            it.smartcommunitylab.platform.idea.NoSuchIdeaException {
        return getPersistence().remove(ideaId);
    }

    public static it.smartcommunitylab.platform.idea.model.Idea updateImpl(
        it.smartcommunitylab.platform.idea.model.Idea idea)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().updateImpl(idea);
    }

    /**
    * Returns the idea with the primary key or throws a {@link it.smartcommunitylab.platform.idea.NoSuchIdeaException} if it could not be found.
    *
    * @param ideaId the primary key of the idea
    * @return the idea
    * @throws it.smartcommunitylab.platform.idea.NoSuchIdeaException if a idea with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static it.smartcommunitylab.platform.idea.model.Idea findByPrimaryKey(
        long ideaId)
        throws com.liferay.portal.kernel.exception.SystemException,
            it.smartcommunitylab.platform.idea.NoSuchIdeaException {
        return getPersistence().findByPrimaryKey(ideaId);
    }

    /**
    * Returns the idea with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param ideaId the primary key of the idea
    * @return the idea, or <code>null</code> if a idea with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static it.smartcommunitylab.platform.idea.model.Idea fetchByPrimaryKey(
        long ideaId) throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().fetchByPrimaryKey(ideaId);
    }

    /**
    * Returns all the ideas.
    *
    * @return the ideas
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<it.smartcommunitylab.platform.idea.model.Idea> findAll()
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findAll();
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
    public static java.util.List<it.smartcommunitylab.platform.idea.model.Idea> findAll(
        int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end);
    }

    /**
    * Returns an ordered range of all the ideas.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link it.smartcommunitylab.platform.idea.model.impl.IdeaModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of ideas
    * @param end the upper bound of the range of ideas (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of ideas
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<it.smartcommunitylab.platform.idea.model.Idea> findAll(
        int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end, orderByComparator);
    }

    /**
    * Removes all the ideas from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public static void removeAll()
        throws com.liferay.portal.kernel.exception.SystemException {
        getPersistence().removeAll();
    }

    /**
    * Returns the number of ideas.
    *
    * @return the number of ideas
    * @throws SystemException if a system exception occurred
    */
    public static int countAll()
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().countAll();
    }

    public static IdeaPersistence getPersistence() {
        if (_persistence == null) {
            _persistence = (IdeaPersistence) PortletBeanLocatorUtil.locate(it.smartcommunitylab.platform.idea.service.ClpSerializer.getServletContextName(),
                    IdeaPersistence.class.getName());

            ReferenceRegistry.registerReference(IdeaUtil.class, "_persistence");
        }

        return _persistence;
    }

    /**
     * @deprecated As of 6.2.0
     */
    public void setPersistence(IdeaPersistence persistence) {
    }
}
