package it.smartcommunitylab.platform.idea.service.persistence;

import com.liferay.portal.kernel.cache.CacheRegistryUtil;
import com.liferay.portal.kernel.dao.orm.EntityCacheUtil;
import com.liferay.portal.kernel.dao.orm.FinderCacheUtil;
import com.liferay.portal.kernel.dao.orm.FinderPath;
import com.liferay.portal.kernel.dao.orm.Query;
import com.liferay.portal.kernel.dao.orm.QueryPos;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.SQLQuery;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.InstanceFactory;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.kernel.util.PropsUtil;
import com.liferay.portal.kernel.util.SetUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.UnmodifiableList;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.uuid.PortalUUIDUtil;
import com.liferay.portal.model.CacheModel;
import com.liferay.portal.model.ModelListener;
import com.liferay.portal.security.permission.InlineSQLHelperUtil;
import com.liferay.portal.service.persistence.impl.BasePersistenceImpl;

import it.smartcommunitylab.platform.idea.NoSuchIdeaException;
import it.smartcommunitylab.platform.idea.model.Idea;
import it.smartcommunitylab.platform.idea.model.impl.IdeaImpl;
import it.smartcommunitylab.platform.idea.model.impl.IdeaModelImpl;
import it.smartcommunitylab.platform.idea.service.persistence.IdeaPersistence;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

/**
 * The persistence implementation for the idea service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author mirko perillo
 * @see IdeaPersistence
 * @see IdeaUtil
 * @generated
 */
public class IdeaPersistenceImpl extends BasePersistenceImpl<Idea>
    implements IdeaPersistence {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this class directly. Always use {@link IdeaUtil} to access the idea persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
     */
    public static final String FINDER_CLASS_NAME_ENTITY = IdeaImpl.class.getName();
    public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List1";
    public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List2";
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(IdeaModelImpl.ENTITY_CACHE_ENABLED,
            IdeaModelImpl.FINDER_CACHE_ENABLED, IdeaImpl.class,
            FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(IdeaModelImpl.ENTITY_CACHE_ENABLED,
            IdeaModelImpl.FINDER_CACHE_ENABLED, IdeaImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(IdeaModelImpl.ENTITY_CACHE_ENABLED,
            IdeaModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_UUID = new FinderPath(IdeaModelImpl.ENTITY_CACHE_ENABLED,
            IdeaModelImpl.FINDER_CACHE_ENABLED, IdeaImpl.class,
            FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByUuid",
            new String[] {
                String.class.getName(),
                
            Integer.class.getName(), Integer.class.getName(),
                OrderByComparator.class.getName()
            });
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID = new FinderPath(IdeaModelImpl.ENTITY_CACHE_ENABLED,
            IdeaModelImpl.FINDER_CACHE_ENABLED, IdeaImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUuid",
            new String[] { String.class.getName() },
            IdeaModelImpl.UUID_COLUMN_BITMASK |
            IdeaModelImpl.CREATEDATE_COLUMN_BITMASK |
            IdeaModelImpl.TITLE_COLUMN_BITMASK);
    public static final FinderPath FINDER_PATH_COUNT_BY_UUID = new FinderPath(IdeaModelImpl.ENTITY_CACHE_ENABLED,
            IdeaModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUuid",
            new String[] { String.class.getName() });
    private static final String _FINDER_COLUMN_UUID_UUID_1 = "idea.uuid IS NULL";
    private static final String _FINDER_COLUMN_UUID_UUID_2 = "idea.uuid = ?";
    private static final String _FINDER_COLUMN_UUID_UUID_3 = "(idea.uuid IS NULL OR idea.uuid = '')";
    public static final FinderPath FINDER_PATH_FETCH_BY_UUID_G = new FinderPath(IdeaModelImpl.ENTITY_CACHE_ENABLED,
            IdeaModelImpl.FINDER_CACHE_ENABLED, IdeaImpl.class,
            FINDER_CLASS_NAME_ENTITY, "fetchByUUID_G",
            new String[] { String.class.getName(), Long.class.getName() },
            IdeaModelImpl.UUID_COLUMN_BITMASK |
            IdeaModelImpl.GROUPID_COLUMN_BITMASK);
    public static final FinderPath FINDER_PATH_COUNT_BY_UUID_G = new FinderPath(IdeaModelImpl.ENTITY_CACHE_ENABLED,
            IdeaModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUUID_G",
            new String[] { String.class.getName(), Long.class.getName() });
    private static final String _FINDER_COLUMN_UUID_G_UUID_1 = "idea.uuid IS NULL AND ";
    private static final String _FINDER_COLUMN_UUID_G_UUID_2 = "idea.uuid = ? AND ";
    private static final String _FINDER_COLUMN_UUID_G_UUID_3 = "(idea.uuid IS NULL OR idea.uuid = '') AND ";
    private static final String _FINDER_COLUMN_UUID_G_GROUPID_2 = "idea.groupId = ?";
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_UUID_C = new FinderPath(IdeaModelImpl.ENTITY_CACHE_ENABLED,
            IdeaModelImpl.FINDER_CACHE_ENABLED, IdeaImpl.class,
            FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByUuid_C",
            new String[] {
                String.class.getName(), Long.class.getName(),
                
            Integer.class.getName(), Integer.class.getName(),
                OrderByComparator.class.getName()
            });
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C =
        new FinderPath(IdeaModelImpl.ENTITY_CACHE_ENABLED,
            IdeaModelImpl.FINDER_CACHE_ENABLED, IdeaImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUuid_C",
            new String[] { String.class.getName(), Long.class.getName() },
            IdeaModelImpl.UUID_COLUMN_BITMASK |
            IdeaModelImpl.COMPANYID_COLUMN_BITMASK |
            IdeaModelImpl.CREATEDATE_COLUMN_BITMASK |
            IdeaModelImpl.TITLE_COLUMN_BITMASK);
    public static final FinderPath FINDER_PATH_COUNT_BY_UUID_C = new FinderPath(IdeaModelImpl.ENTITY_CACHE_ENABLED,
            IdeaModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUuid_C",
            new String[] { String.class.getName(), Long.class.getName() });
    private static final String _FINDER_COLUMN_UUID_C_UUID_1 = "idea.uuid IS NULL AND ";
    private static final String _FINDER_COLUMN_UUID_C_UUID_2 = "idea.uuid = ? AND ";
    private static final String _FINDER_COLUMN_UUID_C_UUID_3 = "(idea.uuid IS NULL OR idea.uuid = '') AND ";
    private static final String _FINDER_COLUMN_UUID_C_COMPANYID_2 = "idea.companyId = ?";
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_GROUPID = new FinderPath(IdeaModelImpl.ENTITY_CACHE_ENABLED,
            IdeaModelImpl.FINDER_CACHE_ENABLED, IdeaImpl.class,
            FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByGroupId",
            new String[] {
                Long.class.getName(),
                
            Integer.class.getName(), Integer.class.getName(),
                OrderByComparator.class.getName()
            });
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUPID =
        new FinderPath(IdeaModelImpl.ENTITY_CACHE_ENABLED,
            IdeaModelImpl.FINDER_CACHE_ENABLED, IdeaImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByGroupId",
            new String[] { Long.class.getName() },
            IdeaModelImpl.GROUPID_COLUMN_BITMASK |
            IdeaModelImpl.CREATEDATE_COLUMN_BITMASK |
            IdeaModelImpl.TITLE_COLUMN_BITMASK);
    public static final FinderPath FINDER_PATH_COUNT_BY_GROUPID = new FinderPath(IdeaModelImpl.ENTITY_CACHE_ENABLED,
            IdeaModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByGroupId",
            new String[] { Long.class.getName() });
    private static final String _FINDER_COLUMN_GROUPID_GROUPID_2 = "idea.groupId = ?";
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_CALLID = new FinderPath(IdeaModelImpl.ENTITY_CACHE_ENABLED,
            IdeaModelImpl.FINDER_CACHE_ENABLED, IdeaImpl.class,
            FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByCallId",
            new String[] {
                Long.class.getName(),
                
            Integer.class.getName(), Integer.class.getName(),
                OrderByComparator.class.getName()
            });
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_CALLID =
        new FinderPath(IdeaModelImpl.ENTITY_CACHE_ENABLED,
            IdeaModelImpl.FINDER_CACHE_ENABLED, IdeaImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByCallId",
            new String[] { Long.class.getName() },
            IdeaModelImpl.CALLID_COLUMN_BITMASK |
            IdeaModelImpl.CREATEDATE_COLUMN_BITMASK |
            IdeaModelImpl.TITLE_COLUMN_BITMASK);
    public static final FinderPath FINDER_PATH_COUNT_BY_CALLID = new FinderPath(IdeaModelImpl.ENTITY_CACHE_ENABLED,
            IdeaModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByCallId",
            new String[] { Long.class.getName() });
    private static final String _FINDER_COLUMN_CALLID_CALLID_2 = "idea.callId = ?";
    private static final String _SQL_SELECT_IDEA = "SELECT idea FROM Idea idea";
    private static final String _SQL_SELECT_IDEA_WHERE = "SELECT idea FROM Idea idea WHERE ";
    private static final String _SQL_COUNT_IDEA = "SELECT COUNT(idea) FROM Idea idea";
    private static final String _SQL_COUNT_IDEA_WHERE = "SELECT COUNT(idea) FROM Idea idea WHERE ";
    private static final String _FILTER_ENTITY_TABLE_FILTER_PK_COLUMN = "idea.ideaId";
    private static final String _FILTER_SQL_SELECT_IDEA_WHERE = "SELECT DISTINCT {idea.*} FROM IM_Idea idea WHERE ";
    private static final String _FILTER_SQL_SELECT_IDEA_NO_INLINE_DISTINCT_WHERE_1 =
        "SELECT {IM_Idea.*} FROM (SELECT DISTINCT idea.ideaId FROM IM_Idea idea WHERE ";
    private static final String _FILTER_SQL_SELECT_IDEA_NO_INLINE_DISTINCT_WHERE_2 =
        ") TEMP_TABLE INNER JOIN IM_Idea ON TEMP_TABLE.ideaId = IM_Idea.ideaId";
    private static final String _FILTER_SQL_COUNT_IDEA_WHERE = "SELECT COUNT(DISTINCT idea.ideaId) AS COUNT_VALUE FROM IM_Idea idea WHERE ";
    private static final String _FILTER_ENTITY_ALIAS = "idea";
    private static final String _FILTER_ENTITY_TABLE = "IM_Idea";
    private static final String _ORDER_BY_ENTITY_ALIAS = "idea.";
    private static final String _ORDER_BY_ENTITY_TABLE = "IM_Idea.";
    private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No Idea exists with the primary key ";
    private static final String _NO_SUCH_ENTITY_WITH_KEY = "No Idea exists with the key {";
    private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
                PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
    private static Log _log = LogFactoryUtil.getLog(IdeaPersistenceImpl.class);
    private static Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
                "uuid", "state"
            });
    private static Idea _nullIdea = new IdeaImpl() {
            @Override
            public Object clone() {
                return this;
            }

            @Override
            public CacheModel<Idea> toCacheModel() {
                return _nullIdeaCacheModel;
            }
        };

    private static CacheModel<Idea> _nullIdeaCacheModel = new CacheModel<Idea>() {
            @Override
            public Idea toEntityModel() {
                return _nullIdea;
            }
        };

    public IdeaPersistenceImpl() {
        setModelClass(Idea.class);
    }

    /**
     * Returns all the ideas where uuid = &#63;.
     *
     * @param uuid the uuid
     * @return the matching ideas
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<Idea> findByUuid(String uuid) throws SystemException {
        return findByUuid(uuid, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
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
    @Override
    public List<Idea> findByUuid(String uuid, int start, int end)
        throws SystemException {
        return findByUuid(uuid, start, end, null);
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
    @Override
    public List<Idea> findByUuid(String uuid, int start, int end,
        OrderByComparator orderByComparator) throws SystemException {
        boolean pagination = true;
        FinderPath finderPath = null;
        Object[] finderArgs = null;

        if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
                (orderByComparator == null)) {
            pagination = false;
            finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID;
            finderArgs = new Object[] { uuid };
        } else {
            finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_UUID;
            finderArgs = new Object[] { uuid, start, end, orderByComparator };
        }

        List<Idea> list = (List<Idea>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if ((list != null) && !list.isEmpty()) {
            for (Idea idea : list) {
                if (!Validator.equals(uuid, idea.getUuid())) {
                    list = null;

                    break;
                }
            }
        }

        if (list == null) {
            StringBundler query = null;

            if (orderByComparator != null) {
                query = new StringBundler(3 +
                        (orderByComparator.getOrderByFields().length * 3));
            } else {
                query = new StringBundler(3);
            }

            query.append(_SQL_SELECT_IDEA_WHERE);

            boolean bindUuid = false;

            if (uuid == null) {
                query.append(_FINDER_COLUMN_UUID_UUID_1);
            } else if (uuid.equals(StringPool.BLANK)) {
                query.append(_FINDER_COLUMN_UUID_UUID_3);
            } else {
                bindUuid = true;

                query.append(_FINDER_COLUMN_UUID_UUID_2);
            }

            if (orderByComparator != null) {
                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);
            } else
             if (pagination) {
                query.append(IdeaModelImpl.ORDER_BY_JPQL);
            }

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                if (bindUuid) {
                    qPos.add(uuid);
                }

                if (!pagination) {
                    list = (List<Idea>) QueryUtil.list(q, getDialect(), start,
                            end, false);

                    Collections.sort(list);

                    list = new UnmodifiableList<Idea>(list);
                } else {
                    list = (List<Idea>) QueryUtil.list(q, getDialect(), start,
                            end);
                }

                cacheResult(list);

                FinderCacheUtil.putResult(finderPath, finderArgs, list);
            } catch (Exception e) {
                FinderCacheUtil.removeResult(finderPath, finderArgs);

                throw processException(e);
            } finally {
                closeSession(session);
            }
        }

        return list;
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
    @Override
    public Idea findByUuid_First(String uuid,
        OrderByComparator orderByComparator)
        throws NoSuchIdeaException, SystemException {
        Idea idea = fetchByUuid_First(uuid, orderByComparator);

        if (idea != null) {
            return idea;
        }

        StringBundler msg = new StringBundler(4);

        msg.append(_NO_SUCH_ENTITY_WITH_KEY);

        msg.append("uuid=");
        msg.append(uuid);

        msg.append(StringPool.CLOSE_CURLY_BRACE);

        throw new NoSuchIdeaException(msg.toString());
    }

    /**
     * Returns the first idea in the ordered set where uuid = &#63;.
     *
     * @param uuid the uuid
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the first matching idea, or <code>null</code> if a matching idea could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public Idea fetchByUuid_First(String uuid,
        OrderByComparator orderByComparator) throws SystemException {
        List<Idea> list = findByUuid(uuid, 0, 1, orderByComparator);

        if (!list.isEmpty()) {
            return list.get(0);
        }

        return null;
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
    @Override
    public Idea findByUuid_Last(String uuid, OrderByComparator orderByComparator)
        throws NoSuchIdeaException, SystemException {
        Idea idea = fetchByUuid_Last(uuid, orderByComparator);

        if (idea != null) {
            return idea;
        }

        StringBundler msg = new StringBundler(4);

        msg.append(_NO_SUCH_ENTITY_WITH_KEY);

        msg.append("uuid=");
        msg.append(uuid);

        msg.append(StringPool.CLOSE_CURLY_BRACE);

        throw new NoSuchIdeaException(msg.toString());
    }

    /**
     * Returns the last idea in the ordered set where uuid = &#63;.
     *
     * @param uuid the uuid
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the last matching idea, or <code>null</code> if a matching idea could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public Idea fetchByUuid_Last(String uuid,
        OrderByComparator orderByComparator) throws SystemException {
        int count = countByUuid(uuid);

        if (count == 0) {
            return null;
        }

        List<Idea> list = findByUuid(uuid, count - 1, count, orderByComparator);

        if (!list.isEmpty()) {
            return list.get(0);
        }

        return null;
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
    @Override
    public Idea[] findByUuid_PrevAndNext(long ideaId, String uuid,
        OrderByComparator orderByComparator)
        throws NoSuchIdeaException, SystemException {
        Idea idea = findByPrimaryKey(ideaId);

        Session session = null;

        try {
            session = openSession();

            Idea[] array = new IdeaImpl[3];

            array[0] = getByUuid_PrevAndNext(session, idea, uuid,
                    orderByComparator, true);

            array[1] = idea;

            array[2] = getByUuid_PrevAndNext(session, idea, uuid,
                    orderByComparator, false);

            return array;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    protected Idea getByUuid_PrevAndNext(Session session, Idea idea,
        String uuid, OrderByComparator orderByComparator, boolean previous) {
        StringBundler query = null;

        if (orderByComparator != null) {
            query = new StringBundler(6 +
                    (orderByComparator.getOrderByFields().length * 6));
        } else {
            query = new StringBundler(3);
        }

        query.append(_SQL_SELECT_IDEA_WHERE);

        boolean bindUuid = false;

        if (uuid == null) {
            query.append(_FINDER_COLUMN_UUID_UUID_1);
        } else if (uuid.equals(StringPool.BLANK)) {
            query.append(_FINDER_COLUMN_UUID_UUID_3);
        } else {
            bindUuid = true;

            query.append(_FINDER_COLUMN_UUID_UUID_2);
        }

        if (orderByComparator != null) {
            String[] orderByConditionFields = orderByComparator.getOrderByConditionFields();

            if (orderByConditionFields.length > 0) {
                query.append(WHERE_AND);
            }

            for (int i = 0; i < orderByConditionFields.length; i++) {
                query.append(_ORDER_BY_ENTITY_ALIAS);
                query.append(orderByConditionFields[i]);

                if ((i + 1) < orderByConditionFields.length) {
                    if (orderByComparator.isAscending() ^ previous) {
                        query.append(WHERE_GREATER_THAN_HAS_NEXT);
                    } else {
                        query.append(WHERE_LESSER_THAN_HAS_NEXT);
                    }
                } else {
                    if (orderByComparator.isAscending() ^ previous) {
                        query.append(WHERE_GREATER_THAN);
                    } else {
                        query.append(WHERE_LESSER_THAN);
                    }
                }
            }

            query.append(ORDER_BY_CLAUSE);

            String[] orderByFields = orderByComparator.getOrderByFields();

            for (int i = 0; i < orderByFields.length; i++) {
                query.append(_ORDER_BY_ENTITY_ALIAS);
                query.append(orderByFields[i]);

                if ((i + 1) < orderByFields.length) {
                    if (orderByComparator.isAscending() ^ previous) {
                        query.append(ORDER_BY_ASC_HAS_NEXT);
                    } else {
                        query.append(ORDER_BY_DESC_HAS_NEXT);
                    }
                } else {
                    if (orderByComparator.isAscending() ^ previous) {
                        query.append(ORDER_BY_ASC);
                    } else {
                        query.append(ORDER_BY_DESC);
                    }
                }
            }
        } else {
            query.append(IdeaModelImpl.ORDER_BY_JPQL);
        }

        String sql = query.toString();

        Query q = session.createQuery(sql);

        q.setFirstResult(0);
        q.setMaxResults(2);

        QueryPos qPos = QueryPos.getInstance(q);

        if (bindUuid) {
            qPos.add(uuid);
        }

        if (orderByComparator != null) {
            Object[] values = orderByComparator.getOrderByConditionValues(idea);

            for (Object value : values) {
                qPos.add(value);
            }
        }

        List<Idea> list = q.list();

        if (list.size() == 2) {
            return list.get(1);
        } else {
            return null;
        }
    }

    /**
     * Removes all the ideas where uuid = &#63; from the database.
     *
     * @param uuid the uuid
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeByUuid(String uuid) throws SystemException {
        for (Idea idea : findByUuid(uuid, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
                null)) {
            remove(idea);
        }
    }

    /**
     * Returns the number of ideas where uuid = &#63;.
     *
     * @param uuid the uuid
     * @return the number of matching ideas
     * @throws SystemException if a system exception occurred
     */
    @Override
    public int countByUuid(String uuid) throws SystemException {
        FinderPath finderPath = FINDER_PATH_COUNT_BY_UUID;

        Object[] finderArgs = new Object[] { uuid };

        Long count = (Long) FinderCacheUtil.getResult(finderPath, finderArgs,
                this);

        if (count == null) {
            StringBundler query = new StringBundler(2);

            query.append(_SQL_COUNT_IDEA_WHERE);

            boolean bindUuid = false;

            if (uuid == null) {
                query.append(_FINDER_COLUMN_UUID_UUID_1);
            } else if (uuid.equals(StringPool.BLANK)) {
                query.append(_FINDER_COLUMN_UUID_UUID_3);
            } else {
                bindUuid = true;

                query.append(_FINDER_COLUMN_UUID_UUID_2);
            }

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                if (bindUuid) {
                    qPos.add(uuid);
                }

                count = (Long) q.uniqueResult();

                FinderCacheUtil.putResult(finderPath, finderArgs, count);
            } catch (Exception e) {
                FinderCacheUtil.removeResult(finderPath, finderArgs);

                throw processException(e);
            } finally {
                closeSession(session);
            }
        }

        return count.intValue();
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
    @Override
    public Idea findByUUID_G(String uuid, long groupId)
        throws NoSuchIdeaException, SystemException {
        Idea idea = fetchByUUID_G(uuid, groupId);

        if (idea == null) {
            StringBundler msg = new StringBundler(6);

            msg.append(_NO_SUCH_ENTITY_WITH_KEY);

            msg.append("uuid=");
            msg.append(uuid);

            msg.append(", groupId=");
            msg.append(groupId);

            msg.append(StringPool.CLOSE_CURLY_BRACE);

            if (_log.isWarnEnabled()) {
                _log.warn(msg.toString());
            }

            throw new NoSuchIdeaException(msg.toString());
        }

        return idea;
    }

    /**
     * Returns the idea where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
     *
     * @param uuid the uuid
     * @param groupId the group ID
     * @return the matching idea, or <code>null</code> if a matching idea could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public Idea fetchByUUID_G(String uuid, long groupId)
        throws SystemException {
        return fetchByUUID_G(uuid, groupId, true);
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
    @Override
    public Idea fetchByUUID_G(String uuid, long groupId,
        boolean retrieveFromCache) throws SystemException {
        Object[] finderArgs = new Object[] { uuid, groupId };

        Object result = null;

        if (retrieveFromCache) {
            result = FinderCacheUtil.getResult(FINDER_PATH_FETCH_BY_UUID_G,
                    finderArgs, this);
        }

        if (result instanceof Idea) {
            Idea idea = (Idea) result;

            if (!Validator.equals(uuid, idea.getUuid()) ||
                    (groupId != idea.getGroupId())) {
                result = null;
            }
        }

        if (result == null) {
            StringBundler query = new StringBundler(4);

            query.append(_SQL_SELECT_IDEA_WHERE);

            boolean bindUuid = false;

            if (uuid == null) {
                query.append(_FINDER_COLUMN_UUID_G_UUID_1);
            } else if (uuid.equals(StringPool.BLANK)) {
                query.append(_FINDER_COLUMN_UUID_G_UUID_3);
            } else {
                bindUuid = true;

                query.append(_FINDER_COLUMN_UUID_G_UUID_2);
            }

            query.append(_FINDER_COLUMN_UUID_G_GROUPID_2);

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                if (bindUuid) {
                    qPos.add(uuid);
                }

                qPos.add(groupId);

                List<Idea> list = q.list();

                if (list.isEmpty()) {
                    FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_UUID_G,
                        finderArgs, list);
                } else {
                    Idea idea = list.get(0);

                    result = idea;

                    cacheResult(idea);

                    if ((idea.getUuid() == null) ||
                            !idea.getUuid().equals(uuid) ||
                            (idea.getGroupId() != groupId)) {
                        FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_UUID_G,
                            finderArgs, idea);
                    }
                }
            } catch (Exception e) {
                FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_UUID_G,
                    finderArgs);

                throw processException(e);
            } finally {
                closeSession(session);
            }
        }

        if (result instanceof List<?>) {
            return null;
        } else {
            return (Idea) result;
        }
    }

    /**
     * Removes the idea where uuid = &#63; and groupId = &#63; from the database.
     *
     * @param uuid the uuid
     * @param groupId the group ID
     * @return the idea that was removed
     * @throws SystemException if a system exception occurred
     */
    @Override
    public Idea removeByUUID_G(String uuid, long groupId)
        throws NoSuchIdeaException, SystemException {
        Idea idea = findByUUID_G(uuid, groupId);

        return remove(idea);
    }

    /**
     * Returns the number of ideas where uuid = &#63; and groupId = &#63;.
     *
     * @param uuid the uuid
     * @param groupId the group ID
     * @return the number of matching ideas
     * @throws SystemException if a system exception occurred
     */
    @Override
    public int countByUUID_G(String uuid, long groupId)
        throws SystemException {
        FinderPath finderPath = FINDER_PATH_COUNT_BY_UUID_G;

        Object[] finderArgs = new Object[] { uuid, groupId };

        Long count = (Long) FinderCacheUtil.getResult(finderPath, finderArgs,
                this);

        if (count == null) {
            StringBundler query = new StringBundler(3);

            query.append(_SQL_COUNT_IDEA_WHERE);

            boolean bindUuid = false;

            if (uuid == null) {
                query.append(_FINDER_COLUMN_UUID_G_UUID_1);
            } else if (uuid.equals(StringPool.BLANK)) {
                query.append(_FINDER_COLUMN_UUID_G_UUID_3);
            } else {
                bindUuid = true;

                query.append(_FINDER_COLUMN_UUID_G_UUID_2);
            }

            query.append(_FINDER_COLUMN_UUID_G_GROUPID_2);

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                if (bindUuid) {
                    qPos.add(uuid);
                }

                qPos.add(groupId);

                count = (Long) q.uniqueResult();

                FinderCacheUtil.putResult(finderPath, finderArgs, count);
            } catch (Exception e) {
                FinderCacheUtil.removeResult(finderPath, finderArgs);

                throw processException(e);
            } finally {
                closeSession(session);
            }
        }

        return count.intValue();
    }

    /**
     * Returns all the ideas where uuid = &#63; and companyId = &#63;.
     *
     * @param uuid the uuid
     * @param companyId the company ID
     * @return the matching ideas
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<Idea> findByUuid_C(String uuid, long companyId)
        throws SystemException {
        return findByUuid_C(uuid, companyId, QueryUtil.ALL_POS,
            QueryUtil.ALL_POS, null);
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
    @Override
    public List<Idea> findByUuid_C(String uuid, long companyId, int start,
        int end) throws SystemException {
        return findByUuid_C(uuid, companyId, start, end, null);
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
    @Override
    public List<Idea> findByUuid_C(String uuid, long companyId, int start,
        int end, OrderByComparator orderByComparator) throws SystemException {
        boolean pagination = true;
        FinderPath finderPath = null;
        Object[] finderArgs = null;

        if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
                (orderByComparator == null)) {
            pagination = false;
            finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C;
            finderArgs = new Object[] { uuid, companyId };
        } else {
            finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_UUID_C;
            finderArgs = new Object[] {
                    uuid, companyId,
                    
                    start, end, orderByComparator
                };
        }

        List<Idea> list = (List<Idea>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if ((list != null) && !list.isEmpty()) {
            for (Idea idea : list) {
                if (!Validator.equals(uuid, idea.getUuid()) ||
                        (companyId != idea.getCompanyId())) {
                    list = null;

                    break;
                }
            }
        }

        if (list == null) {
            StringBundler query = null;

            if (orderByComparator != null) {
                query = new StringBundler(4 +
                        (orderByComparator.getOrderByFields().length * 3));
            } else {
                query = new StringBundler(4);
            }

            query.append(_SQL_SELECT_IDEA_WHERE);

            boolean bindUuid = false;

            if (uuid == null) {
                query.append(_FINDER_COLUMN_UUID_C_UUID_1);
            } else if (uuid.equals(StringPool.BLANK)) {
                query.append(_FINDER_COLUMN_UUID_C_UUID_3);
            } else {
                bindUuid = true;

                query.append(_FINDER_COLUMN_UUID_C_UUID_2);
            }

            query.append(_FINDER_COLUMN_UUID_C_COMPANYID_2);

            if (orderByComparator != null) {
                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);
            } else
             if (pagination) {
                query.append(IdeaModelImpl.ORDER_BY_JPQL);
            }

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                if (bindUuid) {
                    qPos.add(uuid);
                }

                qPos.add(companyId);

                if (!pagination) {
                    list = (List<Idea>) QueryUtil.list(q, getDialect(), start,
                            end, false);

                    Collections.sort(list);

                    list = new UnmodifiableList<Idea>(list);
                } else {
                    list = (List<Idea>) QueryUtil.list(q, getDialect(), start,
                            end);
                }

                cacheResult(list);

                FinderCacheUtil.putResult(finderPath, finderArgs, list);
            } catch (Exception e) {
                FinderCacheUtil.removeResult(finderPath, finderArgs);

                throw processException(e);
            } finally {
                closeSession(session);
            }
        }

        return list;
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
    @Override
    public Idea findByUuid_C_First(String uuid, long companyId,
        OrderByComparator orderByComparator)
        throws NoSuchIdeaException, SystemException {
        Idea idea = fetchByUuid_C_First(uuid, companyId, orderByComparator);

        if (idea != null) {
            return idea;
        }

        StringBundler msg = new StringBundler(6);

        msg.append(_NO_SUCH_ENTITY_WITH_KEY);

        msg.append("uuid=");
        msg.append(uuid);

        msg.append(", companyId=");
        msg.append(companyId);

        msg.append(StringPool.CLOSE_CURLY_BRACE);

        throw new NoSuchIdeaException(msg.toString());
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
    @Override
    public Idea fetchByUuid_C_First(String uuid, long companyId,
        OrderByComparator orderByComparator) throws SystemException {
        List<Idea> list = findByUuid_C(uuid, companyId, 0, 1, orderByComparator);

        if (!list.isEmpty()) {
            return list.get(0);
        }

        return null;
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
    @Override
    public Idea findByUuid_C_Last(String uuid, long companyId,
        OrderByComparator orderByComparator)
        throws NoSuchIdeaException, SystemException {
        Idea idea = fetchByUuid_C_Last(uuid, companyId, orderByComparator);

        if (idea != null) {
            return idea;
        }

        StringBundler msg = new StringBundler(6);

        msg.append(_NO_SUCH_ENTITY_WITH_KEY);

        msg.append("uuid=");
        msg.append(uuid);

        msg.append(", companyId=");
        msg.append(companyId);

        msg.append(StringPool.CLOSE_CURLY_BRACE);

        throw new NoSuchIdeaException(msg.toString());
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
    @Override
    public Idea fetchByUuid_C_Last(String uuid, long companyId,
        OrderByComparator orderByComparator) throws SystemException {
        int count = countByUuid_C(uuid, companyId);

        if (count == 0) {
            return null;
        }

        List<Idea> list = findByUuid_C(uuid, companyId, count - 1, count,
                orderByComparator);

        if (!list.isEmpty()) {
            return list.get(0);
        }

        return null;
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
    @Override
    public Idea[] findByUuid_C_PrevAndNext(long ideaId, String uuid,
        long companyId, OrderByComparator orderByComparator)
        throws NoSuchIdeaException, SystemException {
        Idea idea = findByPrimaryKey(ideaId);

        Session session = null;

        try {
            session = openSession();

            Idea[] array = new IdeaImpl[3];

            array[0] = getByUuid_C_PrevAndNext(session, idea, uuid, companyId,
                    orderByComparator, true);

            array[1] = idea;

            array[2] = getByUuid_C_PrevAndNext(session, idea, uuid, companyId,
                    orderByComparator, false);

            return array;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    protected Idea getByUuid_C_PrevAndNext(Session session, Idea idea,
        String uuid, long companyId, OrderByComparator orderByComparator,
        boolean previous) {
        StringBundler query = null;

        if (orderByComparator != null) {
            query = new StringBundler(6 +
                    (orderByComparator.getOrderByFields().length * 6));
        } else {
            query = new StringBundler(3);
        }

        query.append(_SQL_SELECT_IDEA_WHERE);

        boolean bindUuid = false;

        if (uuid == null) {
            query.append(_FINDER_COLUMN_UUID_C_UUID_1);
        } else if (uuid.equals(StringPool.BLANK)) {
            query.append(_FINDER_COLUMN_UUID_C_UUID_3);
        } else {
            bindUuid = true;

            query.append(_FINDER_COLUMN_UUID_C_UUID_2);
        }

        query.append(_FINDER_COLUMN_UUID_C_COMPANYID_2);

        if (orderByComparator != null) {
            String[] orderByConditionFields = orderByComparator.getOrderByConditionFields();

            if (orderByConditionFields.length > 0) {
                query.append(WHERE_AND);
            }

            for (int i = 0; i < orderByConditionFields.length; i++) {
                query.append(_ORDER_BY_ENTITY_ALIAS);
                query.append(orderByConditionFields[i]);

                if ((i + 1) < orderByConditionFields.length) {
                    if (orderByComparator.isAscending() ^ previous) {
                        query.append(WHERE_GREATER_THAN_HAS_NEXT);
                    } else {
                        query.append(WHERE_LESSER_THAN_HAS_NEXT);
                    }
                } else {
                    if (orderByComparator.isAscending() ^ previous) {
                        query.append(WHERE_GREATER_THAN);
                    } else {
                        query.append(WHERE_LESSER_THAN);
                    }
                }
            }

            query.append(ORDER_BY_CLAUSE);

            String[] orderByFields = orderByComparator.getOrderByFields();

            for (int i = 0; i < orderByFields.length; i++) {
                query.append(_ORDER_BY_ENTITY_ALIAS);
                query.append(orderByFields[i]);

                if ((i + 1) < orderByFields.length) {
                    if (orderByComparator.isAscending() ^ previous) {
                        query.append(ORDER_BY_ASC_HAS_NEXT);
                    } else {
                        query.append(ORDER_BY_DESC_HAS_NEXT);
                    }
                } else {
                    if (orderByComparator.isAscending() ^ previous) {
                        query.append(ORDER_BY_ASC);
                    } else {
                        query.append(ORDER_BY_DESC);
                    }
                }
            }
        } else {
            query.append(IdeaModelImpl.ORDER_BY_JPQL);
        }

        String sql = query.toString();

        Query q = session.createQuery(sql);

        q.setFirstResult(0);
        q.setMaxResults(2);

        QueryPos qPos = QueryPos.getInstance(q);

        if (bindUuid) {
            qPos.add(uuid);
        }

        qPos.add(companyId);

        if (orderByComparator != null) {
            Object[] values = orderByComparator.getOrderByConditionValues(idea);

            for (Object value : values) {
                qPos.add(value);
            }
        }

        List<Idea> list = q.list();

        if (list.size() == 2) {
            return list.get(1);
        } else {
            return null;
        }
    }

    /**
     * Removes all the ideas where uuid = &#63; and companyId = &#63; from the database.
     *
     * @param uuid the uuid
     * @param companyId the company ID
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeByUuid_C(String uuid, long companyId)
        throws SystemException {
        for (Idea idea : findByUuid_C(uuid, companyId, QueryUtil.ALL_POS,
                QueryUtil.ALL_POS, null)) {
            remove(idea);
        }
    }

    /**
     * Returns the number of ideas where uuid = &#63; and companyId = &#63;.
     *
     * @param uuid the uuid
     * @param companyId the company ID
     * @return the number of matching ideas
     * @throws SystemException if a system exception occurred
     */
    @Override
    public int countByUuid_C(String uuid, long companyId)
        throws SystemException {
        FinderPath finderPath = FINDER_PATH_COUNT_BY_UUID_C;

        Object[] finderArgs = new Object[] { uuid, companyId };

        Long count = (Long) FinderCacheUtil.getResult(finderPath, finderArgs,
                this);

        if (count == null) {
            StringBundler query = new StringBundler(3);

            query.append(_SQL_COUNT_IDEA_WHERE);

            boolean bindUuid = false;

            if (uuid == null) {
                query.append(_FINDER_COLUMN_UUID_C_UUID_1);
            } else if (uuid.equals(StringPool.BLANK)) {
                query.append(_FINDER_COLUMN_UUID_C_UUID_3);
            } else {
                bindUuid = true;

                query.append(_FINDER_COLUMN_UUID_C_UUID_2);
            }

            query.append(_FINDER_COLUMN_UUID_C_COMPANYID_2);

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                if (bindUuid) {
                    qPos.add(uuid);
                }

                qPos.add(companyId);

                count = (Long) q.uniqueResult();

                FinderCacheUtil.putResult(finderPath, finderArgs, count);
            } catch (Exception e) {
                FinderCacheUtil.removeResult(finderPath, finderArgs);

                throw processException(e);
            } finally {
                closeSession(session);
            }
        }

        return count.intValue();
    }

    /**
     * Returns all the ideas where groupId = &#63;.
     *
     * @param groupId the group ID
     * @return the matching ideas
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<Idea> findByGroupId(long groupId) throws SystemException {
        return findByGroupId(groupId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
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
    @Override
    public List<Idea> findByGroupId(long groupId, int start, int end)
        throws SystemException {
        return findByGroupId(groupId, start, end, null);
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
    @Override
    public List<Idea> findByGroupId(long groupId, int start, int end,
        OrderByComparator orderByComparator) throws SystemException {
        boolean pagination = true;
        FinderPath finderPath = null;
        Object[] finderArgs = null;

        if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
                (orderByComparator == null)) {
            pagination = false;
            finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUPID;
            finderArgs = new Object[] { groupId };
        } else {
            finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_GROUPID;
            finderArgs = new Object[] { groupId, start, end, orderByComparator };
        }

        List<Idea> list = (List<Idea>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if ((list != null) && !list.isEmpty()) {
            for (Idea idea : list) {
                if ((groupId != idea.getGroupId())) {
                    list = null;

                    break;
                }
            }
        }

        if (list == null) {
            StringBundler query = null;

            if (orderByComparator != null) {
                query = new StringBundler(3 +
                        (orderByComparator.getOrderByFields().length * 3));
            } else {
                query = new StringBundler(3);
            }

            query.append(_SQL_SELECT_IDEA_WHERE);

            query.append(_FINDER_COLUMN_GROUPID_GROUPID_2);

            if (orderByComparator != null) {
                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);
            } else
             if (pagination) {
                query.append(IdeaModelImpl.ORDER_BY_JPQL);
            }

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                qPos.add(groupId);

                if (!pagination) {
                    list = (List<Idea>) QueryUtil.list(q, getDialect(), start,
                            end, false);

                    Collections.sort(list);

                    list = new UnmodifiableList<Idea>(list);
                } else {
                    list = (List<Idea>) QueryUtil.list(q, getDialect(), start,
                            end);
                }

                cacheResult(list);

                FinderCacheUtil.putResult(finderPath, finderArgs, list);
            } catch (Exception e) {
                FinderCacheUtil.removeResult(finderPath, finderArgs);

                throw processException(e);
            } finally {
                closeSession(session);
            }
        }

        return list;
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
    @Override
    public Idea findByGroupId_First(long groupId,
        OrderByComparator orderByComparator)
        throws NoSuchIdeaException, SystemException {
        Idea idea = fetchByGroupId_First(groupId, orderByComparator);

        if (idea != null) {
            return idea;
        }

        StringBundler msg = new StringBundler(4);

        msg.append(_NO_SUCH_ENTITY_WITH_KEY);

        msg.append("groupId=");
        msg.append(groupId);

        msg.append(StringPool.CLOSE_CURLY_BRACE);

        throw new NoSuchIdeaException(msg.toString());
    }

    /**
     * Returns the first idea in the ordered set where groupId = &#63;.
     *
     * @param groupId the group ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the first matching idea, or <code>null</code> if a matching idea could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public Idea fetchByGroupId_First(long groupId,
        OrderByComparator orderByComparator) throws SystemException {
        List<Idea> list = findByGroupId(groupId, 0, 1, orderByComparator);

        if (!list.isEmpty()) {
            return list.get(0);
        }

        return null;
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
    @Override
    public Idea findByGroupId_Last(long groupId,
        OrderByComparator orderByComparator)
        throws NoSuchIdeaException, SystemException {
        Idea idea = fetchByGroupId_Last(groupId, orderByComparator);

        if (idea != null) {
            return idea;
        }

        StringBundler msg = new StringBundler(4);

        msg.append(_NO_SUCH_ENTITY_WITH_KEY);

        msg.append("groupId=");
        msg.append(groupId);

        msg.append(StringPool.CLOSE_CURLY_BRACE);

        throw new NoSuchIdeaException(msg.toString());
    }

    /**
     * Returns the last idea in the ordered set where groupId = &#63;.
     *
     * @param groupId the group ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the last matching idea, or <code>null</code> if a matching idea could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public Idea fetchByGroupId_Last(long groupId,
        OrderByComparator orderByComparator) throws SystemException {
        int count = countByGroupId(groupId);

        if (count == 0) {
            return null;
        }

        List<Idea> list = findByGroupId(groupId, count - 1, count,
                orderByComparator);

        if (!list.isEmpty()) {
            return list.get(0);
        }

        return null;
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
    @Override
    public Idea[] findByGroupId_PrevAndNext(long ideaId, long groupId,
        OrderByComparator orderByComparator)
        throws NoSuchIdeaException, SystemException {
        Idea idea = findByPrimaryKey(ideaId);

        Session session = null;

        try {
            session = openSession();

            Idea[] array = new IdeaImpl[3];

            array[0] = getByGroupId_PrevAndNext(session, idea, groupId,
                    orderByComparator, true);

            array[1] = idea;

            array[2] = getByGroupId_PrevAndNext(session, idea, groupId,
                    orderByComparator, false);

            return array;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    protected Idea getByGroupId_PrevAndNext(Session session, Idea idea,
        long groupId, OrderByComparator orderByComparator, boolean previous) {
        StringBundler query = null;

        if (orderByComparator != null) {
            query = new StringBundler(6 +
                    (orderByComparator.getOrderByFields().length * 6));
        } else {
            query = new StringBundler(3);
        }

        query.append(_SQL_SELECT_IDEA_WHERE);

        query.append(_FINDER_COLUMN_GROUPID_GROUPID_2);

        if (orderByComparator != null) {
            String[] orderByConditionFields = orderByComparator.getOrderByConditionFields();

            if (orderByConditionFields.length > 0) {
                query.append(WHERE_AND);
            }

            for (int i = 0; i < orderByConditionFields.length; i++) {
                query.append(_ORDER_BY_ENTITY_ALIAS);
                query.append(orderByConditionFields[i]);

                if ((i + 1) < orderByConditionFields.length) {
                    if (orderByComparator.isAscending() ^ previous) {
                        query.append(WHERE_GREATER_THAN_HAS_NEXT);
                    } else {
                        query.append(WHERE_LESSER_THAN_HAS_NEXT);
                    }
                } else {
                    if (orderByComparator.isAscending() ^ previous) {
                        query.append(WHERE_GREATER_THAN);
                    } else {
                        query.append(WHERE_LESSER_THAN);
                    }
                }
            }

            query.append(ORDER_BY_CLAUSE);

            String[] orderByFields = orderByComparator.getOrderByFields();

            for (int i = 0; i < orderByFields.length; i++) {
                query.append(_ORDER_BY_ENTITY_ALIAS);
                query.append(orderByFields[i]);

                if ((i + 1) < orderByFields.length) {
                    if (orderByComparator.isAscending() ^ previous) {
                        query.append(ORDER_BY_ASC_HAS_NEXT);
                    } else {
                        query.append(ORDER_BY_DESC_HAS_NEXT);
                    }
                } else {
                    if (orderByComparator.isAscending() ^ previous) {
                        query.append(ORDER_BY_ASC);
                    } else {
                        query.append(ORDER_BY_DESC);
                    }
                }
            }
        } else {
            query.append(IdeaModelImpl.ORDER_BY_JPQL);
        }

        String sql = query.toString();

        Query q = session.createQuery(sql);

        q.setFirstResult(0);
        q.setMaxResults(2);

        QueryPos qPos = QueryPos.getInstance(q);

        qPos.add(groupId);

        if (orderByComparator != null) {
            Object[] values = orderByComparator.getOrderByConditionValues(idea);

            for (Object value : values) {
                qPos.add(value);
            }
        }

        List<Idea> list = q.list();

        if (list.size() == 2) {
            return list.get(1);
        } else {
            return null;
        }
    }

    /**
     * Returns all the ideas that the user has permission to view where groupId = &#63;.
     *
     * @param groupId the group ID
     * @return the matching ideas that the user has permission to view
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<Idea> filterFindByGroupId(long groupId)
        throws SystemException {
        return filterFindByGroupId(groupId, QueryUtil.ALL_POS,
            QueryUtil.ALL_POS, null);
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
    @Override
    public List<Idea> filterFindByGroupId(long groupId, int start, int end)
        throws SystemException {
        return filterFindByGroupId(groupId, start, end, null);
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
    @Override
    public List<Idea> filterFindByGroupId(long groupId, int start, int end,
        OrderByComparator orderByComparator) throws SystemException {
        if (!InlineSQLHelperUtil.isEnabled(groupId)) {
            return findByGroupId(groupId, start, end, orderByComparator);
        }

        StringBundler query = null;

        if (orderByComparator != null) {
            query = new StringBundler(3 +
                    (orderByComparator.getOrderByFields().length * 3));
        } else {
            query = new StringBundler(3);
        }

        if (getDB().isSupportsInlineDistinct()) {
            query.append(_FILTER_SQL_SELECT_IDEA_WHERE);
        } else {
            query.append(_FILTER_SQL_SELECT_IDEA_NO_INLINE_DISTINCT_WHERE_1);
        }

        query.append(_FINDER_COLUMN_GROUPID_GROUPID_2);

        if (!getDB().isSupportsInlineDistinct()) {
            query.append(_FILTER_SQL_SELECT_IDEA_NO_INLINE_DISTINCT_WHERE_2);
        }

        if (orderByComparator != null) {
            if (getDB().isSupportsInlineDistinct()) {
                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator, true);
            } else {
                appendOrderByComparator(query, _ORDER_BY_ENTITY_TABLE,
                    orderByComparator, true);
            }
        } else {
            if (getDB().isSupportsInlineDistinct()) {
                query.append(IdeaModelImpl.ORDER_BY_JPQL);
            } else {
                query.append(IdeaModelImpl.ORDER_BY_SQL);
            }
        }

        String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
                Idea.class.getName(), _FILTER_ENTITY_TABLE_FILTER_PK_COLUMN,
                groupId);

        Session session = null;

        try {
            session = openSession();

            SQLQuery q = session.createSQLQuery(sql);

            if (getDB().isSupportsInlineDistinct()) {
                q.addEntity(_FILTER_ENTITY_ALIAS, IdeaImpl.class);
            } else {
                q.addEntity(_FILTER_ENTITY_TABLE, IdeaImpl.class);
            }

            QueryPos qPos = QueryPos.getInstance(q);

            qPos.add(groupId);

            return (List<Idea>) QueryUtil.list(q, getDialect(), start, end);
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
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
    @Override
    public Idea[] filterFindByGroupId_PrevAndNext(long ideaId, long groupId,
        OrderByComparator orderByComparator)
        throws NoSuchIdeaException, SystemException {
        if (!InlineSQLHelperUtil.isEnabled(groupId)) {
            return findByGroupId_PrevAndNext(ideaId, groupId, orderByComparator);
        }

        Idea idea = findByPrimaryKey(ideaId);

        Session session = null;

        try {
            session = openSession();

            Idea[] array = new IdeaImpl[3];

            array[0] = filterGetByGroupId_PrevAndNext(session, idea, groupId,
                    orderByComparator, true);

            array[1] = idea;

            array[2] = filterGetByGroupId_PrevAndNext(session, idea, groupId,
                    orderByComparator, false);

            return array;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    protected Idea filterGetByGroupId_PrevAndNext(Session session, Idea idea,
        long groupId, OrderByComparator orderByComparator, boolean previous) {
        StringBundler query = null;

        if (orderByComparator != null) {
            query = new StringBundler(6 +
                    (orderByComparator.getOrderByFields().length * 6));
        } else {
            query = new StringBundler(3);
        }

        if (getDB().isSupportsInlineDistinct()) {
            query.append(_FILTER_SQL_SELECT_IDEA_WHERE);
        } else {
            query.append(_FILTER_SQL_SELECT_IDEA_NO_INLINE_DISTINCT_WHERE_1);
        }

        query.append(_FINDER_COLUMN_GROUPID_GROUPID_2);

        if (!getDB().isSupportsInlineDistinct()) {
            query.append(_FILTER_SQL_SELECT_IDEA_NO_INLINE_DISTINCT_WHERE_2);
        }

        if (orderByComparator != null) {
            String[] orderByConditionFields = orderByComparator.getOrderByConditionFields();

            if (orderByConditionFields.length > 0) {
                query.append(WHERE_AND);
            }

            for (int i = 0; i < orderByConditionFields.length; i++) {
                if (getDB().isSupportsInlineDistinct()) {
                    query.append(_ORDER_BY_ENTITY_ALIAS);
                } else {
                    query.append(_ORDER_BY_ENTITY_TABLE);
                }

                query.append(orderByConditionFields[i]);

                if ((i + 1) < orderByConditionFields.length) {
                    if (orderByComparator.isAscending() ^ previous) {
                        query.append(WHERE_GREATER_THAN_HAS_NEXT);
                    } else {
                        query.append(WHERE_LESSER_THAN_HAS_NEXT);
                    }
                } else {
                    if (orderByComparator.isAscending() ^ previous) {
                        query.append(WHERE_GREATER_THAN);
                    } else {
                        query.append(WHERE_LESSER_THAN);
                    }
                }
            }

            query.append(ORDER_BY_CLAUSE);

            String[] orderByFields = orderByComparator.getOrderByFields();

            for (int i = 0; i < orderByFields.length; i++) {
                if (getDB().isSupportsInlineDistinct()) {
                    query.append(_ORDER_BY_ENTITY_ALIAS);
                } else {
                    query.append(_ORDER_BY_ENTITY_TABLE);
                }

                query.append(orderByFields[i]);

                if ((i + 1) < orderByFields.length) {
                    if (orderByComparator.isAscending() ^ previous) {
                        query.append(ORDER_BY_ASC_HAS_NEXT);
                    } else {
                        query.append(ORDER_BY_DESC_HAS_NEXT);
                    }
                } else {
                    if (orderByComparator.isAscending() ^ previous) {
                        query.append(ORDER_BY_ASC);
                    } else {
                        query.append(ORDER_BY_DESC);
                    }
                }
            }
        } else {
            if (getDB().isSupportsInlineDistinct()) {
                query.append(IdeaModelImpl.ORDER_BY_JPQL);
            } else {
                query.append(IdeaModelImpl.ORDER_BY_SQL);
            }
        }

        String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
                Idea.class.getName(), _FILTER_ENTITY_TABLE_FILTER_PK_COLUMN,
                groupId);

        SQLQuery q = session.createSQLQuery(sql);

        q.setFirstResult(0);
        q.setMaxResults(2);

        if (getDB().isSupportsInlineDistinct()) {
            q.addEntity(_FILTER_ENTITY_ALIAS, IdeaImpl.class);
        } else {
            q.addEntity(_FILTER_ENTITY_TABLE, IdeaImpl.class);
        }

        QueryPos qPos = QueryPos.getInstance(q);

        qPos.add(groupId);

        if (orderByComparator != null) {
            Object[] values = orderByComparator.getOrderByConditionValues(idea);

            for (Object value : values) {
                qPos.add(value);
            }
        }

        List<Idea> list = q.list();

        if (list.size() == 2) {
            return list.get(1);
        } else {
            return null;
        }
    }

    /**
     * Removes all the ideas where groupId = &#63; from the database.
     *
     * @param groupId the group ID
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeByGroupId(long groupId) throws SystemException {
        for (Idea idea : findByGroupId(groupId, QueryUtil.ALL_POS,
                QueryUtil.ALL_POS, null)) {
            remove(idea);
        }
    }

    /**
     * Returns the number of ideas where groupId = &#63;.
     *
     * @param groupId the group ID
     * @return the number of matching ideas
     * @throws SystemException if a system exception occurred
     */
    @Override
    public int countByGroupId(long groupId) throws SystemException {
        FinderPath finderPath = FINDER_PATH_COUNT_BY_GROUPID;

        Object[] finderArgs = new Object[] { groupId };

        Long count = (Long) FinderCacheUtil.getResult(finderPath, finderArgs,
                this);

        if (count == null) {
            StringBundler query = new StringBundler(2);

            query.append(_SQL_COUNT_IDEA_WHERE);

            query.append(_FINDER_COLUMN_GROUPID_GROUPID_2);

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                qPos.add(groupId);

                count = (Long) q.uniqueResult();

                FinderCacheUtil.putResult(finderPath, finderArgs, count);
            } catch (Exception e) {
                FinderCacheUtil.removeResult(finderPath, finderArgs);

                throw processException(e);
            } finally {
                closeSession(session);
            }
        }

        return count.intValue();
    }

    /**
     * Returns the number of ideas that the user has permission to view where groupId = &#63;.
     *
     * @param groupId the group ID
     * @return the number of matching ideas that the user has permission to view
     * @throws SystemException if a system exception occurred
     */
    @Override
    public int filterCountByGroupId(long groupId) throws SystemException {
        if (!InlineSQLHelperUtil.isEnabled(groupId)) {
            return countByGroupId(groupId);
        }

        StringBundler query = new StringBundler(2);

        query.append(_FILTER_SQL_COUNT_IDEA_WHERE);

        query.append(_FINDER_COLUMN_GROUPID_GROUPID_2);

        String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
                Idea.class.getName(), _FILTER_ENTITY_TABLE_FILTER_PK_COLUMN,
                groupId);

        Session session = null;

        try {
            session = openSession();

            SQLQuery q = session.createSQLQuery(sql);

            q.addScalar(COUNT_COLUMN_NAME,
                com.liferay.portal.kernel.dao.orm.Type.LONG);

            QueryPos qPos = QueryPos.getInstance(q);

            qPos.add(groupId);

            Long count = (Long) q.uniqueResult();

            return count.intValue();
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    /**
     * Returns all the ideas where callId = &#63;.
     *
     * @param callId the call ID
     * @return the matching ideas
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<Idea> findByCallId(long callId) throws SystemException {
        return findByCallId(callId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
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
    @Override
    public List<Idea> findByCallId(long callId, int start, int end)
        throws SystemException {
        return findByCallId(callId, start, end, null);
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
    @Override
    public List<Idea> findByCallId(long callId, int start, int end,
        OrderByComparator orderByComparator) throws SystemException {
        boolean pagination = true;
        FinderPath finderPath = null;
        Object[] finderArgs = null;

        if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
                (orderByComparator == null)) {
            pagination = false;
            finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_CALLID;
            finderArgs = new Object[] { callId };
        } else {
            finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_CALLID;
            finderArgs = new Object[] { callId, start, end, orderByComparator };
        }

        List<Idea> list = (List<Idea>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if ((list != null) && !list.isEmpty()) {
            for (Idea idea : list) {
                if ((callId != idea.getCallId())) {
                    list = null;

                    break;
                }
            }
        }

        if (list == null) {
            StringBundler query = null;

            if (orderByComparator != null) {
                query = new StringBundler(3 +
                        (orderByComparator.getOrderByFields().length * 3));
            } else {
                query = new StringBundler(3);
            }

            query.append(_SQL_SELECT_IDEA_WHERE);

            query.append(_FINDER_COLUMN_CALLID_CALLID_2);

            if (orderByComparator != null) {
                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);
            } else
             if (pagination) {
                query.append(IdeaModelImpl.ORDER_BY_JPQL);
            }

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                qPos.add(callId);

                if (!pagination) {
                    list = (List<Idea>) QueryUtil.list(q, getDialect(), start,
                            end, false);

                    Collections.sort(list);

                    list = new UnmodifiableList<Idea>(list);
                } else {
                    list = (List<Idea>) QueryUtil.list(q, getDialect(), start,
                            end);
                }

                cacheResult(list);

                FinderCacheUtil.putResult(finderPath, finderArgs, list);
            } catch (Exception e) {
                FinderCacheUtil.removeResult(finderPath, finderArgs);

                throw processException(e);
            } finally {
                closeSession(session);
            }
        }

        return list;
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
    @Override
    public Idea findByCallId_First(long callId,
        OrderByComparator orderByComparator)
        throws NoSuchIdeaException, SystemException {
        Idea idea = fetchByCallId_First(callId, orderByComparator);

        if (idea != null) {
            return idea;
        }

        StringBundler msg = new StringBundler(4);

        msg.append(_NO_SUCH_ENTITY_WITH_KEY);

        msg.append("callId=");
        msg.append(callId);

        msg.append(StringPool.CLOSE_CURLY_BRACE);

        throw new NoSuchIdeaException(msg.toString());
    }

    /**
     * Returns the first idea in the ordered set where callId = &#63;.
     *
     * @param callId the call ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the first matching idea, or <code>null</code> if a matching idea could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public Idea fetchByCallId_First(long callId,
        OrderByComparator orderByComparator) throws SystemException {
        List<Idea> list = findByCallId(callId, 0, 1, orderByComparator);

        if (!list.isEmpty()) {
            return list.get(0);
        }

        return null;
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
    @Override
    public Idea findByCallId_Last(long callId,
        OrderByComparator orderByComparator)
        throws NoSuchIdeaException, SystemException {
        Idea idea = fetchByCallId_Last(callId, orderByComparator);

        if (idea != null) {
            return idea;
        }

        StringBundler msg = new StringBundler(4);

        msg.append(_NO_SUCH_ENTITY_WITH_KEY);

        msg.append("callId=");
        msg.append(callId);

        msg.append(StringPool.CLOSE_CURLY_BRACE);

        throw new NoSuchIdeaException(msg.toString());
    }

    /**
     * Returns the last idea in the ordered set where callId = &#63;.
     *
     * @param callId the call ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the last matching idea, or <code>null</code> if a matching idea could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public Idea fetchByCallId_Last(long callId,
        OrderByComparator orderByComparator) throws SystemException {
        int count = countByCallId(callId);

        if (count == 0) {
            return null;
        }

        List<Idea> list = findByCallId(callId, count - 1, count,
                orderByComparator);

        if (!list.isEmpty()) {
            return list.get(0);
        }

        return null;
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
    @Override
    public Idea[] findByCallId_PrevAndNext(long ideaId, long callId,
        OrderByComparator orderByComparator)
        throws NoSuchIdeaException, SystemException {
        Idea idea = findByPrimaryKey(ideaId);

        Session session = null;

        try {
            session = openSession();

            Idea[] array = new IdeaImpl[3];

            array[0] = getByCallId_PrevAndNext(session, idea, callId,
                    orderByComparator, true);

            array[1] = idea;

            array[2] = getByCallId_PrevAndNext(session, idea, callId,
                    orderByComparator, false);

            return array;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    protected Idea getByCallId_PrevAndNext(Session session, Idea idea,
        long callId, OrderByComparator orderByComparator, boolean previous) {
        StringBundler query = null;

        if (orderByComparator != null) {
            query = new StringBundler(6 +
                    (orderByComparator.getOrderByFields().length * 6));
        } else {
            query = new StringBundler(3);
        }

        query.append(_SQL_SELECT_IDEA_WHERE);

        query.append(_FINDER_COLUMN_CALLID_CALLID_2);

        if (orderByComparator != null) {
            String[] orderByConditionFields = orderByComparator.getOrderByConditionFields();

            if (orderByConditionFields.length > 0) {
                query.append(WHERE_AND);
            }

            for (int i = 0; i < orderByConditionFields.length; i++) {
                query.append(_ORDER_BY_ENTITY_ALIAS);
                query.append(orderByConditionFields[i]);

                if ((i + 1) < orderByConditionFields.length) {
                    if (orderByComparator.isAscending() ^ previous) {
                        query.append(WHERE_GREATER_THAN_HAS_NEXT);
                    } else {
                        query.append(WHERE_LESSER_THAN_HAS_NEXT);
                    }
                } else {
                    if (orderByComparator.isAscending() ^ previous) {
                        query.append(WHERE_GREATER_THAN);
                    } else {
                        query.append(WHERE_LESSER_THAN);
                    }
                }
            }

            query.append(ORDER_BY_CLAUSE);

            String[] orderByFields = orderByComparator.getOrderByFields();

            for (int i = 0; i < orderByFields.length; i++) {
                query.append(_ORDER_BY_ENTITY_ALIAS);
                query.append(orderByFields[i]);

                if ((i + 1) < orderByFields.length) {
                    if (orderByComparator.isAscending() ^ previous) {
                        query.append(ORDER_BY_ASC_HAS_NEXT);
                    } else {
                        query.append(ORDER_BY_DESC_HAS_NEXT);
                    }
                } else {
                    if (orderByComparator.isAscending() ^ previous) {
                        query.append(ORDER_BY_ASC);
                    } else {
                        query.append(ORDER_BY_DESC);
                    }
                }
            }
        } else {
            query.append(IdeaModelImpl.ORDER_BY_JPQL);
        }

        String sql = query.toString();

        Query q = session.createQuery(sql);

        q.setFirstResult(0);
        q.setMaxResults(2);

        QueryPos qPos = QueryPos.getInstance(q);

        qPos.add(callId);

        if (orderByComparator != null) {
            Object[] values = orderByComparator.getOrderByConditionValues(idea);

            for (Object value : values) {
                qPos.add(value);
            }
        }

        List<Idea> list = q.list();

        if (list.size() == 2) {
            return list.get(1);
        } else {
            return null;
        }
    }

    /**
     * Removes all the ideas where callId = &#63; from the database.
     *
     * @param callId the call ID
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeByCallId(long callId) throws SystemException {
        for (Idea idea : findByCallId(callId, QueryUtil.ALL_POS,
                QueryUtil.ALL_POS, null)) {
            remove(idea);
        }
    }

    /**
     * Returns the number of ideas where callId = &#63;.
     *
     * @param callId the call ID
     * @return the number of matching ideas
     * @throws SystemException if a system exception occurred
     */
    @Override
    public int countByCallId(long callId) throws SystemException {
        FinderPath finderPath = FINDER_PATH_COUNT_BY_CALLID;

        Object[] finderArgs = new Object[] { callId };

        Long count = (Long) FinderCacheUtil.getResult(finderPath, finderArgs,
                this);

        if (count == null) {
            StringBundler query = new StringBundler(2);

            query.append(_SQL_COUNT_IDEA_WHERE);

            query.append(_FINDER_COLUMN_CALLID_CALLID_2);

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                qPos.add(callId);

                count = (Long) q.uniqueResult();

                FinderCacheUtil.putResult(finderPath, finderArgs, count);
            } catch (Exception e) {
                FinderCacheUtil.removeResult(finderPath, finderArgs);

                throw processException(e);
            } finally {
                closeSession(session);
            }
        }

        return count.intValue();
    }

    /**
     * Caches the idea in the entity cache if it is enabled.
     *
     * @param idea the idea
     */
    @Override
    public void cacheResult(Idea idea) {
        EntityCacheUtil.putResult(IdeaModelImpl.ENTITY_CACHE_ENABLED,
            IdeaImpl.class, idea.getPrimaryKey(), idea);

        FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_UUID_G,
            new Object[] { idea.getUuid(), idea.getGroupId() }, idea);

        idea.resetOriginalValues();
    }

    /**
     * Caches the ideas in the entity cache if it is enabled.
     *
     * @param ideas the ideas
     */
    @Override
    public void cacheResult(List<Idea> ideas) {
        for (Idea idea : ideas) {
            if (EntityCacheUtil.getResult(IdeaModelImpl.ENTITY_CACHE_ENABLED,
                        IdeaImpl.class, idea.getPrimaryKey()) == null) {
                cacheResult(idea);
            } else {
                idea.resetOriginalValues();
            }
        }
    }

    /**
     * Clears the cache for all ideas.
     *
     * <p>
     * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache() {
        if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
            CacheRegistryUtil.clear(IdeaImpl.class.getName());
        }

        EntityCacheUtil.clearCache(IdeaImpl.class.getName());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    /**
     * Clears the cache for the idea.
     *
     * <p>
     * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache(Idea idea) {
        EntityCacheUtil.removeResult(IdeaModelImpl.ENTITY_CACHE_ENABLED,
            IdeaImpl.class, idea.getPrimaryKey());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

        clearUniqueFindersCache(idea);
    }

    @Override
    public void clearCache(List<Idea> ideas) {
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

        for (Idea idea : ideas) {
            EntityCacheUtil.removeResult(IdeaModelImpl.ENTITY_CACHE_ENABLED,
                IdeaImpl.class, idea.getPrimaryKey());

            clearUniqueFindersCache(idea);
        }
    }

    protected void cacheUniqueFindersCache(Idea idea) {
        if (idea.isNew()) {
            Object[] args = new Object[] { idea.getUuid(), idea.getGroupId() };

            FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_UUID_G, args,
                Long.valueOf(1));
            FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_UUID_G, args, idea);
        } else {
            IdeaModelImpl ideaModelImpl = (IdeaModelImpl) idea;

            if ((ideaModelImpl.getColumnBitmask() &
                    FINDER_PATH_FETCH_BY_UUID_G.getColumnBitmask()) != 0) {
                Object[] args = new Object[] { idea.getUuid(), idea.getGroupId() };

                FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_UUID_G, args,
                    Long.valueOf(1));
                FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_UUID_G, args,
                    idea);
            }
        }
    }

    protected void clearUniqueFindersCache(Idea idea) {
        IdeaModelImpl ideaModelImpl = (IdeaModelImpl) idea;

        Object[] args = new Object[] { idea.getUuid(), idea.getGroupId() };

        FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_UUID_G, args);
        FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_UUID_G, args);

        if ((ideaModelImpl.getColumnBitmask() &
                FINDER_PATH_FETCH_BY_UUID_G.getColumnBitmask()) != 0) {
            args = new Object[] {
                    ideaModelImpl.getOriginalUuid(),
                    ideaModelImpl.getOriginalGroupId()
                };

            FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_UUID_G, args);
            FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_UUID_G, args);
        }
    }

    /**
     * Creates a new idea with the primary key. Does not add the idea to the database.
     *
     * @param ideaId the primary key for the new idea
     * @return the new idea
     */
    @Override
    public Idea create(long ideaId) {
        Idea idea = new IdeaImpl();

        idea.setNew(true);
        idea.setPrimaryKey(ideaId);

        String uuid = PortalUUIDUtil.generate();

        idea.setUuid(uuid);

        return idea;
    }

    /**
     * Removes the idea with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param ideaId the primary key of the idea
     * @return the idea that was removed
     * @throws it.smartcommunitylab.platform.idea.NoSuchIdeaException if a idea with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public Idea remove(long ideaId) throws NoSuchIdeaException, SystemException {
        return remove((Serializable) ideaId);
    }

    /**
     * Removes the idea with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param primaryKey the primary key of the idea
     * @return the idea that was removed
     * @throws it.smartcommunitylab.platform.idea.NoSuchIdeaException if a idea with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public Idea remove(Serializable primaryKey)
        throws NoSuchIdeaException, SystemException {
        Session session = null;

        try {
            session = openSession();

            Idea idea = (Idea) session.get(IdeaImpl.class, primaryKey);

            if (idea == null) {
                if (_log.isWarnEnabled()) {
                    _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
                }

                throw new NoSuchIdeaException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                    primaryKey);
            }

            return remove(idea);
        } catch (NoSuchIdeaException nsee) {
            throw nsee;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    @Override
    protected Idea removeImpl(Idea idea) throws SystemException {
        idea = toUnwrappedModel(idea);

        Session session = null;

        try {
            session = openSession();

            if (!session.contains(idea)) {
                idea = (Idea) session.get(IdeaImpl.class,
                        idea.getPrimaryKeyObj());
            }

            if (idea != null) {
                session.delete(idea);
            }
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        if (idea != null) {
            clearCache(idea);
        }

        return idea;
    }

    @Override
    public Idea updateImpl(it.smartcommunitylab.platform.idea.model.Idea idea)
        throws SystemException {
        idea = toUnwrappedModel(idea);

        boolean isNew = idea.isNew();

        IdeaModelImpl ideaModelImpl = (IdeaModelImpl) idea;

        if (Validator.isNull(idea.getUuid())) {
            String uuid = PortalUUIDUtil.generate();

            idea.setUuid(uuid);
        }

        Session session = null;

        try {
            session = openSession();

            if (idea.isNew()) {
                session.save(idea);

                idea.setNew(false);
            } else {
                session.merge(idea);
            }
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

        if (isNew || !IdeaModelImpl.COLUMN_BITMASK_ENABLED) {
            FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
        }
        else {
            if ((ideaModelImpl.getColumnBitmask() &
                    FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID.getColumnBitmask()) != 0) {
                Object[] args = new Object[] { ideaModelImpl.getOriginalUuid() };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
                    args);

                args = new Object[] { ideaModelImpl.getUuid() };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
                    args);
            }

            if ((ideaModelImpl.getColumnBitmask() &
                    FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C.getColumnBitmask()) != 0) {
                Object[] args = new Object[] {
                        ideaModelImpl.getOriginalUuid(),
                        ideaModelImpl.getOriginalCompanyId()
                    };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_UUID_C, args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C,
                    args);

                args = new Object[] {
                        ideaModelImpl.getUuid(), ideaModelImpl.getCompanyId()
                    };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_UUID_C, args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C,
                    args);
            }

            if ((ideaModelImpl.getColumnBitmask() &
                    FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUPID.getColumnBitmask()) != 0) {
                Object[] args = new Object[] { ideaModelImpl.getOriginalGroupId() };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_GROUPID, args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUPID,
                    args);

                args = new Object[] { ideaModelImpl.getGroupId() };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_GROUPID, args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUPID,
                    args);
            }

            if ((ideaModelImpl.getColumnBitmask() &
                    FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_CALLID.getColumnBitmask()) != 0) {
                Object[] args = new Object[] { ideaModelImpl.getOriginalCallId() };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_CALLID, args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_CALLID,
                    args);

                args = new Object[] { ideaModelImpl.getCallId() };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_CALLID, args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_CALLID,
                    args);
            }
        }

        EntityCacheUtil.putResult(IdeaModelImpl.ENTITY_CACHE_ENABLED,
            IdeaImpl.class, idea.getPrimaryKey(), idea);

        clearUniqueFindersCache(idea);
        cacheUniqueFindersCache(idea);

        return idea;
    }

    protected Idea toUnwrappedModel(Idea idea) {
        if (idea instanceof IdeaImpl) {
            return idea;
        }

        IdeaImpl ideaImpl = new IdeaImpl();

        ideaImpl.setNew(idea.isNew());
        ideaImpl.setPrimaryKey(idea.getPrimaryKey());

        ideaImpl.setUuid(idea.getUuid());
        ideaImpl.setIdeaId(idea.getIdeaId());
        ideaImpl.setGroupId(idea.getGroupId());
        ideaImpl.setCompanyId(idea.getCompanyId());
        ideaImpl.setUserId(idea.getUserId());
        ideaImpl.setUserName(idea.getUserName());
        ideaImpl.setCreateDate(idea.getCreateDate());
        ideaImpl.setModifiedDate(idea.getModifiedDate());
        ideaImpl.setTitle(idea.getTitle());
        ideaImpl.setLongDesc(idea.getLongDesc());
        ideaImpl.setShortDesc(idea.getShortDesc());
        ideaImpl.setUserGroupId(idea.getUserGroupId());
        ideaImpl.setCallId(idea.getCallId());
        ideaImpl.setState(idea.getState());
        ideaImpl.setStateJudgement(idea.getStateJudgement());
        ideaImpl.setDeadlineConstraints(idea.getDeadlineConstraints());
        ideaImpl.setDiscussionLimit(idea.getDiscussionLimit());

        return ideaImpl;
    }

    /**
     * Returns the idea with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
     *
     * @param primaryKey the primary key of the idea
     * @return the idea
     * @throws it.smartcommunitylab.platform.idea.NoSuchIdeaException if a idea with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public Idea findByPrimaryKey(Serializable primaryKey)
        throws NoSuchIdeaException, SystemException {
        Idea idea = fetchByPrimaryKey(primaryKey);

        if (idea == null) {
            if (_log.isWarnEnabled()) {
                _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
            }

            throw new NoSuchIdeaException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                primaryKey);
        }

        return idea;
    }

    /**
     * Returns the idea with the primary key or throws a {@link it.smartcommunitylab.platform.idea.NoSuchIdeaException} if it could not be found.
     *
     * @param ideaId the primary key of the idea
     * @return the idea
     * @throws it.smartcommunitylab.platform.idea.NoSuchIdeaException if a idea with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public Idea findByPrimaryKey(long ideaId)
        throws NoSuchIdeaException, SystemException {
        return findByPrimaryKey((Serializable) ideaId);
    }

    /**
     * Returns the idea with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param primaryKey the primary key of the idea
     * @return the idea, or <code>null</code> if a idea with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public Idea fetchByPrimaryKey(Serializable primaryKey)
        throws SystemException {
        Idea idea = (Idea) EntityCacheUtil.getResult(IdeaModelImpl.ENTITY_CACHE_ENABLED,
                IdeaImpl.class, primaryKey);

        if (idea == _nullIdea) {
            return null;
        }

        if (idea == null) {
            Session session = null;

            try {
                session = openSession();

                idea = (Idea) session.get(IdeaImpl.class, primaryKey);

                if (idea != null) {
                    cacheResult(idea);
                } else {
                    EntityCacheUtil.putResult(IdeaModelImpl.ENTITY_CACHE_ENABLED,
                        IdeaImpl.class, primaryKey, _nullIdea);
                }
            } catch (Exception e) {
                EntityCacheUtil.removeResult(IdeaModelImpl.ENTITY_CACHE_ENABLED,
                    IdeaImpl.class, primaryKey);

                throw processException(e);
            } finally {
                closeSession(session);
            }
        }

        return idea;
    }

    /**
     * Returns the idea with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param ideaId the primary key of the idea
     * @return the idea, or <code>null</code> if a idea with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public Idea fetchByPrimaryKey(long ideaId) throws SystemException {
        return fetchByPrimaryKey((Serializable) ideaId);
    }

    /**
     * Returns all the ideas.
     *
     * @return the ideas
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<Idea> findAll() throws SystemException {
        return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
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
    public List<Idea> findAll(int start, int end) throws SystemException {
        return findAll(start, end, null);
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
    @Override
    public List<Idea> findAll(int start, int end,
        OrderByComparator orderByComparator) throws SystemException {
        boolean pagination = true;
        FinderPath finderPath = null;
        Object[] finderArgs = null;

        if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
                (orderByComparator == null)) {
            pagination = false;
            finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL;
            finderArgs = FINDER_ARGS_EMPTY;
        } else {
            finderPath = FINDER_PATH_WITH_PAGINATION_FIND_ALL;
            finderArgs = new Object[] { start, end, orderByComparator };
        }

        List<Idea> list = (List<Idea>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if (list == null) {
            StringBundler query = null;
            String sql = null;

            if (orderByComparator != null) {
                query = new StringBundler(2 +
                        (orderByComparator.getOrderByFields().length * 3));

                query.append(_SQL_SELECT_IDEA);

                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);

                sql = query.toString();
            } else {
                sql = _SQL_SELECT_IDEA;

                if (pagination) {
                    sql = sql.concat(IdeaModelImpl.ORDER_BY_JPQL);
                }
            }

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                if (!pagination) {
                    list = (List<Idea>) QueryUtil.list(q, getDialect(), start,
                            end, false);

                    Collections.sort(list);

                    list = new UnmodifiableList<Idea>(list);
                } else {
                    list = (List<Idea>) QueryUtil.list(q, getDialect(), start,
                            end);
                }

                cacheResult(list);

                FinderCacheUtil.putResult(finderPath, finderArgs, list);
            } catch (Exception e) {
                FinderCacheUtil.removeResult(finderPath, finderArgs);

                throw processException(e);
            } finally {
                closeSession(session);
            }
        }

        return list;
    }

    /**
     * Removes all the ideas from the database.
     *
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeAll() throws SystemException {
        for (Idea idea : findAll()) {
            remove(idea);
        }
    }

    /**
     * Returns the number of ideas.
     *
     * @return the number of ideas
     * @throws SystemException if a system exception occurred
     */
    @Override
    public int countAll() throws SystemException {
        Long count = (Long) FinderCacheUtil.getResult(FINDER_PATH_COUNT_ALL,
                FINDER_ARGS_EMPTY, this);

        if (count == null) {
            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(_SQL_COUNT_IDEA);

                count = (Long) q.uniqueResult();

                FinderCacheUtil.putResult(FINDER_PATH_COUNT_ALL,
                    FINDER_ARGS_EMPTY, count);
            } catch (Exception e) {
                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_ALL,
                    FINDER_ARGS_EMPTY);

                throw processException(e);
            } finally {
                closeSession(session);
            }
        }

        return count.intValue();
    }

    @Override
    protected Set<String> getBadColumnNames() {
        return _badColumnNames;
    }

    /**
     * Initializes the idea persistence.
     */
    public void afterPropertiesSet() {
        String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
                    com.liferay.util.service.ServiceProps.get(
                        "value.object.listener.it.smartcommunitylab.platform.idea.model.Idea")));

        if (listenerClassNames.length > 0) {
            try {
                List<ModelListener<Idea>> listenersList = new ArrayList<ModelListener<Idea>>();

                for (String listenerClassName : listenerClassNames) {
                    listenersList.add((ModelListener<Idea>) InstanceFactory.newInstance(
                            getClassLoader(), listenerClassName));
                }

                listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
            } catch (Exception e) {
                _log.error(e);
            }
        }
    }

    public void destroy() {
        EntityCacheUtil.removeCache(IdeaImpl.class.getName());
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }
}
