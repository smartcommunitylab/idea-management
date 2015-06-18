package it.smartcommunitylab.platform.idea.service.persistence;

import com.liferay.portal.service.persistence.BasePersistence;

import it.smartcommunitylab.platform.idea.model.Idea;

/**
 * The persistence interface for the idea service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author mirko perillo
 * @see IdeaPersistenceImpl
 * @see IdeaUtil
 * @generated
 */
public interface IdeaPersistence extends BasePersistence<Idea> {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this interface directly. Always use {@link IdeaUtil} to access the idea persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
     */

    /**
    * Returns all the ideas where uuid = &#63;.
    *
    * @param uuid the uuid
    * @return the matching ideas
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<it.smartcommunitylab.platform.idea.model.Idea> findByUuid(
        java.lang.String uuid)
        throws com.liferay.portal.kernel.exception.SystemException;

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
    public java.util.List<it.smartcommunitylab.platform.idea.model.Idea> findByUuid(
        java.lang.String uuid, int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException;

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
    public java.util.List<it.smartcommunitylab.platform.idea.model.Idea> findByUuid(
        java.lang.String uuid, int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the first idea in the ordered set where uuid = &#63;.
    *
    * @param uuid the uuid
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching idea
    * @throws it.smartcommunitylab.platform.idea.NoSuchIdeaException if a matching idea could not be found
    * @throws SystemException if a system exception occurred
    */
    public it.smartcommunitylab.platform.idea.model.Idea findByUuid_First(
        java.lang.String uuid,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException,
            it.smartcommunitylab.platform.idea.NoSuchIdeaException;

    /**
    * Returns the first idea in the ordered set where uuid = &#63;.
    *
    * @param uuid the uuid
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching idea, or <code>null</code> if a matching idea could not be found
    * @throws SystemException if a system exception occurred
    */
    public it.smartcommunitylab.platform.idea.model.Idea fetchByUuid_First(
        java.lang.String uuid,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the last idea in the ordered set where uuid = &#63;.
    *
    * @param uuid the uuid
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching idea
    * @throws it.smartcommunitylab.platform.idea.NoSuchIdeaException if a matching idea could not be found
    * @throws SystemException if a system exception occurred
    */
    public it.smartcommunitylab.platform.idea.model.Idea findByUuid_Last(
        java.lang.String uuid,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException,
            it.smartcommunitylab.platform.idea.NoSuchIdeaException;

    /**
    * Returns the last idea in the ordered set where uuid = &#63;.
    *
    * @param uuid the uuid
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching idea, or <code>null</code> if a matching idea could not be found
    * @throws SystemException if a system exception occurred
    */
    public it.smartcommunitylab.platform.idea.model.Idea fetchByUuid_Last(
        java.lang.String uuid,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

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
    public it.smartcommunitylab.platform.idea.model.Idea[] findByUuid_PrevAndNext(
        long ideaId, java.lang.String uuid,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException,
            it.smartcommunitylab.platform.idea.NoSuchIdeaException;

    /**
    * Removes all the ideas where uuid = &#63; from the database.
    *
    * @param uuid the uuid
    * @throws SystemException if a system exception occurred
    */
    public void removeByUuid(java.lang.String uuid)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the number of ideas where uuid = &#63;.
    *
    * @param uuid the uuid
    * @return the number of matching ideas
    * @throws SystemException if a system exception occurred
    */
    public int countByUuid(java.lang.String uuid)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the idea where uuid = &#63; and groupId = &#63; or throws a {@link it.smartcommunitylab.platform.idea.NoSuchIdeaException} if it could not be found.
    *
    * @param uuid the uuid
    * @param groupId the group ID
    * @return the matching idea
    * @throws it.smartcommunitylab.platform.idea.NoSuchIdeaException if a matching idea could not be found
    * @throws SystemException if a system exception occurred
    */
    public it.smartcommunitylab.platform.idea.model.Idea findByUUID_G(
        java.lang.String uuid, long groupId)
        throws com.liferay.portal.kernel.exception.SystemException,
            it.smartcommunitylab.platform.idea.NoSuchIdeaException;

    /**
    * Returns the idea where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
    *
    * @param uuid the uuid
    * @param groupId the group ID
    * @return the matching idea, or <code>null</code> if a matching idea could not be found
    * @throws SystemException if a system exception occurred
    */
    public it.smartcommunitylab.platform.idea.model.Idea fetchByUUID_G(
        java.lang.String uuid, long groupId)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the idea where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
    *
    * @param uuid the uuid
    * @param groupId the group ID
    * @param retrieveFromCache whether to use the finder cache
    * @return the matching idea, or <code>null</code> if a matching idea could not be found
    * @throws SystemException if a system exception occurred
    */
    public it.smartcommunitylab.platform.idea.model.Idea fetchByUUID_G(
        java.lang.String uuid, long groupId, boolean retrieveFromCache)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Removes the idea where uuid = &#63; and groupId = &#63; from the database.
    *
    * @param uuid the uuid
    * @param groupId the group ID
    * @return the idea that was removed
    * @throws SystemException if a system exception occurred
    */
    public it.smartcommunitylab.platform.idea.model.Idea removeByUUID_G(
        java.lang.String uuid, long groupId)
        throws com.liferay.portal.kernel.exception.SystemException,
            it.smartcommunitylab.platform.idea.NoSuchIdeaException;

    /**
    * Returns the number of ideas where uuid = &#63; and groupId = &#63;.
    *
    * @param uuid the uuid
    * @param groupId the group ID
    * @return the number of matching ideas
    * @throws SystemException if a system exception occurred
    */
    public int countByUUID_G(java.lang.String uuid, long groupId)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns all the ideas where uuid = &#63; and companyId = &#63;.
    *
    * @param uuid the uuid
    * @param companyId the company ID
    * @return the matching ideas
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<it.smartcommunitylab.platform.idea.model.Idea> findByUuid_C(
        java.lang.String uuid, long companyId)
        throws com.liferay.portal.kernel.exception.SystemException;

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
    public java.util.List<it.smartcommunitylab.platform.idea.model.Idea> findByUuid_C(
        java.lang.String uuid, long companyId, int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException;

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
    public java.util.List<it.smartcommunitylab.platform.idea.model.Idea> findByUuid_C(
        java.lang.String uuid, long companyId, int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

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
    public it.smartcommunitylab.platform.idea.model.Idea findByUuid_C_First(
        java.lang.String uuid, long companyId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException,
            it.smartcommunitylab.platform.idea.NoSuchIdeaException;

    /**
    * Returns the first idea in the ordered set where uuid = &#63; and companyId = &#63;.
    *
    * @param uuid the uuid
    * @param companyId the company ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching idea, or <code>null</code> if a matching idea could not be found
    * @throws SystemException if a system exception occurred
    */
    public it.smartcommunitylab.platform.idea.model.Idea fetchByUuid_C_First(
        java.lang.String uuid, long companyId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

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
    public it.smartcommunitylab.platform.idea.model.Idea findByUuid_C_Last(
        java.lang.String uuid, long companyId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException,
            it.smartcommunitylab.platform.idea.NoSuchIdeaException;

    /**
    * Returns the last idea in the ordered set where uuid = &#63; and companyId = &#63;.
    *
    * @param uuid the uuid
    * @param companyId the company ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching idea, or <code>null</code> if a matching idea could not be found
    * @throws SystemException if a system exception occurred
    */
    public it.smartcommunitylab.platform.idea.model.Idea fetchByUuid_C_Last(
        java.lang.String uuid, long companyId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

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
    public it.smartcommunitylab.platform.idea.model.Idea[] findByUuid_C_PrevAndNext(
        long ideaId, java.lang.String uuid, long companyId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException,
            it.smartcommunitylab.platform.idea.NoSuchIdeaException;

    /**
    * Removes all the ideas where uuid = &#63; and companyId = &#63; from the database.
    *
    * @param uuid the uuid
    * @param companyId the company ID
    * @throws SystemException if a system exception occurred
    */
    public void removeByUuid_C(java.lang.String uuid, long companyId)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the number of ideas where uuid = &#63; and companyId = &#63;.
    *
    * @param uuid the uuid
    * @param companyId the company ID
    * @return the number of matching ideas
    * @throws SystemException if a system exception occurred
    */
    public int countByUuid_C(java.lang.String uuid, long companyId)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns all the ideas where groupId = &#63;.
    *
    * @param groupId the group ID
    * @return the matching ideas
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<it.smartcommunitylab.platform.idea.model.Idea> findByGroupId(
        long groupId)
        throws com.liferay.portal.kernel.exception.SystemException;

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
    public java.util.List<it.smartcommunitylab.platform.idea.model.Idea> findByGroupId(
        long groupId, int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException;

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
    public java.util.List<it.smartcommunitylab.platform.idea.model.Idea> findByGroupId(
        long groupId, int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the first idea in the ordered set where groupId = &#63;.
    *
    * @param groupId the group ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching idea
    * @throws it.smartcommunitylab.platform.idea.NoSuchIdeaException if a matching idea could not be found
    * @throws SystemException if a system exception occurred
    */
    public it.smartcommunitylab.platform.idea.model.Idea findByGroupId_First(
        long groupId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException,
            it.smartcommunitylab.platform.idea.NoSuchIdeaException;

    /**
    * Returns the first idea in the ordered set where groupId = &#63;.
    *
    * @param groupId the group ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching idea, or <code>null</code> if a matching idea could not be found
    * @throws SystemException if a system exception occurred
    */
    public it.smartcommunitylab.platform.idea.model.Idea fetchByGroupId_First(
        long groupId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the last idea in the ordered set where groupId = &#63;.
    *
    * @param groupId the group ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching idea
    * @throws it.smartcommunitylab.platform.idea.NoSuchIdeaException if a matching idea could not be found
    * @throws SystemException if a system exception occurred
    */
    public it.smartcommunitylab.platform.idea.model.Idea findByGroupId_Last(
        long groupId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException,
            it.smartcommunitylab.platform.idea.NoSuchIdeaException;

    /**
    * Returns the last idea in the ordered set where groupId = &#63;.
    *
    * @param groupId the group ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching idea, or <code>null</code> if a matching idea could not be found
    * @throws SystemException if a system exception occurred
    */
    public it.smartcommunitylab.platform.idea.model.Idea fetchByGroupId_Last(
        long groupId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

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
    public it.smartcommunitylab.platform.idea.model.Idea[] findByGroupId_PrevAndNext(
        long ideaId, long groupId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException,
            it.smartcommunitylab.platform.idea.NoSuchIdeaException;

    /**
    * Returns all the ideas that the user has permission to view where groupId = &#63;.
    *
    * @param groupId the group ID
    * @return the matching ideas that the user has permission to view
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<it.smartcommunitylab.platform.idea.model.Idea> filterFindByGroupId(
        long groupId)
        throws com.liferay.portal.kernel.exception.SystemException;

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
    public java.util.List<it.smartcommunitylab.platform.idea.model.Idea> filterFindByGroupId(
        long groupId, int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException;

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
    public java.util.List<it.smartcommunitylab.platform.idea.model.Idea> filterFindByGroupId(
        long groupId, int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

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
    public it.smartcommunitylab.platform.idea.model.Idea[] filterFindByGroupId_PrevAndNext(
        long ideaId, long groupId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException,
            it.smartcommunitylab.platform.idea.NoSuchIdeaException;

    /**
    * Removes all the ideas where groupId = &#63; from the database.
    *
    * @param groupId the group ID
    * @throws SystemException if a system exception occurred
    */
    public void removeByGroupId(long groupId)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the number of ideas where groupId = &#63;.
    *
    * @param groupId the group ID
    * @return the number of matching ideas
    * @throws SystemException if a system exception occurred
    */
    public int countByGroupId(long groupId)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the number of ideas that the user has permission to view where groupId = &#63;.
    *
    * @param groupId the group ID
    * @return the number of matching ideas that the user has permission to view
    * @throws SystemException if a system exception occurred
    */
    public int filterCountByGroupId(long groupId)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns all the ideas where callId = &#63;.
    *
    * @param callId the call ID
    * @return the matching ideas
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<it.smartcommunitylab.platform.idea.model.Idea> findByCallId(
        long callId) throws com.liferay.portal.kernel.exception.SystemException;

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
    public java.util.List<it.smartcommunitylab.platform.idea.model.Idea> findByCallId(
        long callId, int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException;

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
    public java.util.List<it.smartcommunitylab.platform.idea.model.Idea> findByCallId(
        long callId, int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the first idea in the ordered set where callId = &#63;.
    *
    * @param callId the call ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching idea
    * @throws it.smartcommunitylab.platform.idea.NoSuchIdeaException if a matching idea could not be found
    * @throws SystemException if a system exception occurred
    */
    public it.smartcommunitylab.platform.idea.model.Idea findByCallId_First(
        long callId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException,
            it.smartcommunitylab.platform.idea.NoSuchIdeaException;

    /**
    * Returns the first idea in the ordered set where callId = &#63;.
    *
    * @param callId the call ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching idea, or <code>null</code> if a matching idea could not be found
    * @throws SystemException if a system exception occurred
    */
    public it.smartcommunitylab.platform.idea.model.Idea fetchByCallId_First(
        long callId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the last idea in the ordered set where callId = &#63;.
    *
    * @param callId the call ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching idea
    * @throws it.smartcommunitylab.platform.idea.NoSuchIdeaException if a matching idea could not be found
    * @throws SystemException if a system exception occurred
    */
    public it.smartcommunitylab.platform.idea.model.Idea findByCallId_Last(
        long callId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException,
            it.smartcommunitylab.platform.idea.NoSuchIdeaException;

    /**
    * Returns the last idea in the ordered set where callId = &#63;.
    *
    * @param callId the call ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching idea, or <code>null</code> if a matching idea could not be found
    * @throws SystemException if a system exception occurred
    */
    public it.smartcommunitylab.platform.idea.model.Idea fetchByCallId_Last(
        long callId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

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
    public it.smartcommunitylab.platform.idea.model.Idea[] findByCallId_PrevAndNext(
        long ideaId, long callId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException,
            it.smartcommunitylab.platform.idea.NoSuchIdeaException;

    /**
    * Removes all the ideas where callId = &#63; from the database.
    *
    * @param callId the call ID
    * @throws SystemException if a system exception occurred
    */
    public void removeByCallId(long callId)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the number of ideas where callId = &#63;.
    *
    * @param callId the call ID
    * @return the number of matching ideas
    * @throws SystemException if a system exception occurred
    */
    public int countByCallId(long callId)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Caches the idea in the entity cache if it is enabled.
    *
    * @param idea the idea
    */
    public void cacheResult(it.smartcommunitylab.platform.idea.model.Idea idea);

    /**
    * Caches the ideas in the entity cache if it is enabled.
    *
    * @param ideas the ideas
    */
    public void cacheResult(
        java.util.List<it.smartcommunitylab.platform.idea.model.Idea> ideas);

    /**
    * Creates a new idea with the primary key. Does not add the idea to the database.
    *
    * @param ideaId the primary key for the new idea
    * @return the new idea
    */
    public it.smartcommunitylab.platform.idea.model.Idea create(long ideaId);

    /**
    * Removes the idea with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param ideaId the primary key of the idea
    * @return the idea that was removed
    * @throws it.smartcommunitylab.platform.idea.NoSuchIdeaException if a idea with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public it.smartcommunitylab.platform.idea.model.Idea remove(long ideaId)
        throws com.liferay.portal.kernel.exception.SystemException,
            it.smartcommunitylab.platform.idea.NoSuchIdeaException;

    public it.smartcommunitylab.platform.idea.model.Idea updateImpl(
        it.smartcommunitylab.platform.idea.model.Idea idea)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the idea with the primary key or throws a {@link it.smartcommunitylab.platform.idea.NoSuchIdeaException} if it could not be found.
    *
    * @param ideaId the primary key of the idea
    * @return the idea
    * @throws it.smartcommunitylab.platform.idea.NoSuchIdeaException if a idea with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public it.smartcommunitylab.platform.idea.model.Idea findByPrimaryKey(
        long ideaId)
        throws com.liferay.portal.kernel.exception.SystemException,
            it.smartcommunitylab.platform.idea.NoSuchIdeaException;

    /**
    * Returns the idea with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param ideaId the primary key of the idea
    * @return the idea, or <code>null</code> if a idea with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public it.smartcommunitylab.platform.idea.model.Idea fetchByPrimaryKey(
        long ideaId) throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns all the ideas.
    *
    * @return the ideas
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<it.smartcommunitylab.platform.idea.model.Idea> findAll()
        throws com.liferay.portal.kernel.exception.SystemException;

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
    public java.util.List<it.smartcommunitylab.platform.idea.model.Idea> findAll(
        int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException;

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
    public java.util.List<it.smartcommunitylab.platform.idea.model.Idea> findAll(
        int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Removes all the ideas from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public void removeAll()
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the number of ideas.
    *
    * @return the number of ideas
    * @throws SystemException if a system exception occurred
    */
    public int countAll()
        throws com.liferay.portal.kernel.exception.SystemException;
}
