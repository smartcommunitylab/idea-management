package it.smartcommunitylab.platform.idea.service.base;

import com.liferay.portal.kernel.bean.BeanReference;
import com.liferay.portal.kernel.bean.IdentifiableBean;
import com.liferay.portal.kernel.dao.jdbc.SqlUpdate;
import com.liferay.portal.kernel.dao.jdbc.SqlUpdateFactoryUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.liferay.portal.kernel.dao.orm.Projection;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.search.Indexable;
import com.liferay.portal.kernel.search.IndexableType;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.model.PersistedModel;
import com.liferay.portal.service.BaseLocalServiceImpl;
import com.liferay.portal.service.PersistedModelLocalServiceRegistryUtil;
import com.liferay.portal.service.persistence.UserPersistence;

import com.liferay.portlet.asset.service.persistence.AssetEntryPersistence;
import com.liferay.portlet.asset.service.persistence.AssetLinkPersistence;

import it.smartcommunitylab.platform.idea.model.Idea;
import it.smartcommunitylab.platform.idea.service.IdeaLocalService;
import it.smartcommunitylab.platform.idea.service.persistence.CallPersistence;
import it.smartcommunitylab.platform.idea.service.persistence.IdeaFinder;
import it.smartcommunitylab.platform.idea.service.persistence.IdeaPersistence;

import java.io.Serializable;

import java.util.List;

import javax.sql.DataSource;

/**
 * Provides the base implementation for the idea local service.
 *
 * <p>
 * This implementation exists only as a container for the default service methods generated by ServiceBuilder. All custom service methods should be put in {@link it.smartcommunitylab.platform.idea.service.impl.IdeaLocalServiceImpl}.
 * </p>
 *
 * @author mirko perillo
 * @see it.smartcommunitylab.platform.idea.service.impl.IdeaLocalServiceImpl
 * @see it.smartcommunitylab.platform.idea.service.IdeaLocalServiceUtil
 * @generated
 */
public abstract class IdeaLocalServiceBaseImpl extends BaseLocalServiceImpl
    implements IdeaLocalService, IdentifiableBean {
    @BeanReference(type = it.smartcommunitylab.platform.idea.service.CallLocalService.class)
    protected it.smartcommunitylab.platform.idea.service.CallLocalService callLocalService;
    @BeanReference(type = CallPersistence.class)
    protected CallPersistence callPersistence;
    @BeanReference(type = it.smartcommunitylab.platform.idea.service.IdeaLocalService.class)
    protected it.smartcommunitylab.platform.idea.service.IdeaLocalService ideaLocalService;
    @BeanReference(type = IdeaPersistence.class)
    protected IdeaPersistence ideaPersistence;
    @BeanReference(type = IdeaFinder.class)
    protected IdeaFinder ideaFinder;
    @BeanReference(type = com.liferay.counter.service.CounterLocalService.class)
    protected com.liferay.counter.service.CounterLocalService counterLocalService;
    @BeanReference(type = com.liferay.portal.service.ResourceLocalService.class)
    protected com.liferay.portal.service.ResourceLocalService resourceLocalService;
    @BeanReference(type = com.liferay.portal.service.UserLocalService.class)
    protected com.liferay.portal.service.UserLocalService userLocalService;
    @BeanReference(type = com.liferay.portal.service.UserService.class)
    protected com.liferay.portal.service.UserService userService;
    @BeanReference(type = UserPersistence.class)
    protected UserPersistence userPersistence;
    @BeanReference(type = com.liferay.portlet.asset.service.AssetEntryLocalService.class)
    protected com.liferay.portlet.asset.service.AssetEntryLocalService assetEntryLocalService;
    @BeanReference(type = com.liferay.portlet.asset.service.AssetEntryService.class)
    protected com.liferay.portlet.asset.service.AssetEntryService assetEntryService;
    @BeanReference(type = AssetEntryPersistence.class)
    protected AssetEntryPersistence assetEntryPersistence;
    @BeanReference(type = com.liferay.portlet.asset.service.AssetLinkLocalService.class)
    protected com.liferay.portlet.asset.service.AssetLinkLocalService assetLinkLocalService;
    @BeanReference(type = AssetLinkPersistence.class)
    protected AssetLinkPersistence assetLinkPersistence;
    private String _beanIdentifier;
    private ClassLoader _classLoader;
    private IdeaLocalServiceClpInvoker _clpInvoker = new IdeaLocalServiceClpInvoker();

    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this class directly. Always use {@link it.smartcommunitylab.platform.idea.service.IdeaLocalServiceUtil} to access the idea local service.
     */

    /**
     * Adds the idea to the database. Also notifies the appropriate model listeners.
     *
     * @param idea the idea
     * @return the idea that was added
     * @throws SystemException if a system exception occurred
     */
    @Indexable(type = IndexableType.REINDEX)
    @Override
    public Idea addIdea(Idea idea) throws SystemException {
        idea.setNew(true);

        return ideaPersistence.update(idea);
    }

    /**
     * Creates a new idea with the primary key. Does not add the idea to the database.
     *
     * @param ideaId the primary key for the new idea
     * @return the new idea
     */
    @Override
    public Idea createIdea(long ideaId) {
        return ideaPersistence.create(ideaId);
    }

    /**
     * Deletes the idea with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param ideaId the primary key of the idea
     * @return the idea that was removed
     * @throws PortalException if a idea with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Indexable(type = IndexableType.DELETE)
    @Override
    public Idea deleteIdea(long ideaId) throws PortalException, SystemException {
        return ideaPersistence.remove(ideaId);
    }

    /**
     * Deletes the idea from the database. Also notifies the appropriate model listeners.
     *
     * @param idea the idea
     * @return the idea that was removed
     * @throws SystemException if a system exception occurred
     */
    @Indexable(type = IndexableType.DELETE)
    @Override
    public Idea deleteIdea(Idea idea) throws SystemException {
        return ideaPersistence.remove(idea);
    }

    @Override
    public DynamicQuery dynamicQuery() {
        Class<?> clazz = getClass();

        return DynamicQueryFactoryUtil.forClass(Idea.class,
            clazz.getClassLoader());
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
    public List dynamicQuery(DynamicQuery dynamicQuery)
        throws SystemException {
        return ideaPersistence.findWithDynamicQuery(dynamicQuery);
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
    public List dynamicQuery(DynamicQuery dynamicQuery, int start, int end)
        throws SystemException {
        return ideaPersistence.findWithDynamicQuery(dynamicQuery, start, end);
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
    public List dynamicQuery(DynamicQuery dynamicQuery, int start, int end,
        OrderByComparator orderByComparator) throws SystemException {
        return ideaPersistence.findWithDynamicQuery(dynamicQuery, start, end,
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
    public long dynamicQueryCount(DynamicQuery dynamicQuery)
        throws SystemException {
        return ideaPersistence.countWithDynamicQuery(dynamicQuery);
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
    public long dynamicQueryCount(DynamicQuery dynamicQuery,
        Projection projection) throws SystemException {
        return ideaPersistence.countWithDynamicQuery(dynamicQuery, projection);
    }

    @Override
    public Idea fetchIdea(long ideaId) throws SystemException {
        return ideaPersistence.fetchByPrimaryKey(ideaId);
    }

    /**
     * Returns the idea with the matching UUID and company.
     *
     * @param uuid the idea's UUID
     * @param  companyId the primary key of the company
     * @return the matching idea, or <code>null</code> if a matching idea could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public Idea fetchIdeaByUuidAndCompanyId(String uuid, long companyId)
        throws SystemException {
        return ideaPersistence.fetchByUuid_C_First(uuid, companyId, null);
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
    public Idea fetchIdeaByUuidAndGroupId(String uuid, long groupId)
        throws SystemException {
        return ideaPersistence.fetchByUUID_G(uuid, groupId);
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
    public Idea getIdea(long ideaId) throws PortalException, SystemException {
        return ideaPersistence.findByPrimaryKey(ideaId);
    }

    @Override
    public PersistedModel getPersistedModel(Serializable primaryKeyObj)
        throws PortalException, SystemException {
        return ideaPersistence.findByPrimaryKey(primaryKeyObj);
    }

    /**
     * Returns the idea with the matching UUID and company.
     *
     * @param uuid the idea's UUID
     * @param  companyId the primary key of the company
     * @return the matching idea
     * @throws PortalException if a matching idea could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public Idea getIdeaByUuidAndCompanyId(String uuid, long companyId)
        throws PortalException, SystemException {
        return ideaPersistence.findByUuid_C_First(uuid, companyId, null);
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
    public Idea getIdeaByUuidAndGroupId(String uuid, long groupId)
        throws PortalException, SystemException {
        return ideaPersistence.findByUUID_G(uuid, groupId);
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
    public List<Idea> getIdeas(int start, int end) throws SystemException {
        return ideaPersistence.findAll(start, end);
    }

    /**
     * Returns the number of ideas.
     *
     * @return the number of ideas
     * @throws SystemException if a system exception occurred
     */
    @Override
    public int getIdeasCount() throws SystemException {
        return ideaPersistence.countAll();
    }

    /**
     * Updates the idea in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
     *
     * @param idea the idea
     * @return the idea that was updated
     * @throws SystemException if a system exception occurred
     */
    @Indexable(type = IndexableType.REINDEX)
    @Override
    public Idea updateIdea(Idea idea) throws SystemException {
        return ideaPersistence.update(idea);
    }

    /**
     * Returns the call local service.
     *
     * @return the call local service
     */
    public it.smartcommunitylab.platform.idea.service.CallLocalService getCallLocalService() {
        return callLocalService;
    }

    /**
     * Sets the call local service.
     *
     * @param callLocalService the call local service
     */
    public void setCallLocalService(
        it.smartcommunitylab.platform.idea.service.CallLocalService callLocalService) {
        this.callLocalService = callLocalService;
    }

    /**
     * Returns the call persistence.
     *
     * @return the call persistence
     */
    public CallPersistence getCallPersistence() {
        return callPersistence;
    }

    /**
     * Sets the call persistence.
     *
     * @param callPersistence the call persistence
     */
    public void setCallPersistence(CallPersistence callPersistence) {
        this.callPersistence = callPersistence;
    }

    /**
     * Returns the idea local service.
     *
     * @return the idea local service
     */
    public it.smartcommunitylab.platform.idea.service.IdeaLocalService getIdeaLocalService() {
        return ideaLocalService;
    }

    /**
     * Sets the idea local service.
     *
     * @param ideaLocalService the idea local service
     */
    public void setIdeaLocalService(
        it.smartcommunitylab.platform.idea.service.IdeaLocalService ideaLocalService) {
        this.ideaLocalService = ideaLocalService;
    }

    /**
     * Returns the idea persistence.
     *
     * @return the idea persistence
     */
    public IdeaPersistence getIdeaPersistence() {
        return ideaPersistence;
    }

    /**
     * Sets the idea persistence.
     *
     * @param ideaPersistence the idea persistence
     */
    public void setIdeaPersistence(IdeaPersistence ideaPersistence) {
        this.ideaPersistence = ideaPersistence;
    }

    /**
     * Returns the idea finder.
     *
     * @return the idea finder
     */
    public IdeaFinder getIdeaFinder() {
        return ideaFinder;
    }

    /**
     * Sets the idea finder.
     *
     * @param ideaFinder the idea finder
     */
    public void setIdeaFinder(IdeaFinder ideaFinder) {
        this.ideaFinder = ideaFinder;
    }

    /**
     * Returns the counter local service.
     *
     * @return the counter local service
     */
    public com.liferay.counter.service.CounterLocalService getCounterLocalService() {
        return counterLocalService;
    }

    /**
     * Sets the counter local service.
     *
     * @param counterLocalService the counter local service
     */
    public void setCounterLocalService(
        com.liferay.counter.service.CounterLocalService counterLocalService) {
        this.counterLocalService = counterLocalService;
    }

    /**
     * Returns the resource local service.
     *
     * @return the resource local service
     */
    public com.liferay.portal.service.ResourceLocalService getResourceLocalService() {
        return resourceLocalService;
    }

    /**
     * Sets the resource local service.
     *
     * @param resourceLocalService the resource local service
     */
    public void setResourceLocalService(
        com.liferay.portal.service.ResourceLocalService resourceLocalService) {
        this.resourceLocalService = resourceLocalService;
    }

    /**
     * Returns the user local service.
     *
     * @return the user local service
     */
    public com.liferay.portal.service.UserLocalService getUserLocalService() {
        return userLocalService;
    }

    /**
     * Sets the user local service.
     *
     * @param userLocalService the user local service
     */
    public void setUserLocalService(
        com.liferay.portal.service.UserLocalService userLocalService) {
        this.userLocalService = userLocalService;
    }

    /**
     * Returns the user remote service.
     *
     * @return the user remote service
     */
    public com.liferay.portal.service.UserService getUserService() {
        return userService;
    }

    /**
     * Sets the user remote service.
     *
     * @param userService the user remote service
     */
    public void setUserService(
        com.liferay.portal.service.UserService userService) {
        this.userService = userService;
    }

    /**
     * Returns the user persistence.
     *
     * @return the user persistence
     */
    public UserPersistence getUserPersistence() {
        return userPersistence;
    }

    /**
     * Sets the user persistence.
     *
     * @param userPersistence the user persistence
     */
    public void setUserPersistence(UserPersistence userPersistence) {
        this.userPersistence = userPersistence;
    }

    /**
     * Returns the asset entry local service.
     *
     * @return the asset entry local service
     */
    public com.liferay.portlet.asset.service.AssetEntryLocalService getAssetEntryLocalService() {
        return assetEntryLocalService;
    }

    /**
     * Sets the asset entry local service.
     *
     * @param assetEntryLocalService the asset entry local service
     */
    public void setAssetEntryLocalService(
        com.liferay.portlet.asset.service.AssetEntryLocalService assetEntryLocalService) {
        this.assetEntryLocalService = assetEntryLocalService;
    }

    /**
     * Returns the asset entry remote service.
     *
     * @return the asset entry remote service
     */
    public com.liferay.portlet.asset.service.AssetEntryService getAssetEntryService() {
        return assetEntryService;
    }

    /**
     * Sets the asset entry remote service.
     *
     * @param assetEntryService the asset entry remote service
     */
    public void setAssetEntryService(
        com.liferay.portlet.asset.service.AssetEntryService assetEntryService) {
        this.assetEntryService = assetEntryService;
    }

    /**
     * Returns the asset entry persistence.
     *
     * @return the asset entry persistence
     */
    public AssetEntryPersistence getAssetEntryPersistence() {
        return assetEntryPersistence;
    }

    /**
     * Sets the asset entry persistence.
     *
     * @param assetEntryPersistence the asset entry persistence
     */
    public void setAssetEntryPersistence(
        AssetEntryPersistence assetEntryPersistence) {
        this.assetEntryPersistence = assetEntryPersistence;
    }

    /**
     * Returns the asset link local service.
     *
     * @return the asset link local service
     */
    public com.liferay.portlet.asset.service.AssetLinkLocalService getAssetLinkLocalService() {
        return assetLinkLocalService;
    }

    /**
     * Sets the asset link local service.
     *
     * @param assetLinkLocalService the asset link local service
     */
    public void setAssetLinkLocalService(
        com.liferay.portlet.asset.service.AssetLinkLocalService assetLinkLocalService) {
        this.assetLinkLocalService = assetLinkLocalService;
    }

    /**
     * Returns the asset link persistence.
     *
     * @return the asset link persistence
     */
    public AssetLinkPersistence getAssetLinkPersistence() {
        return assetLinkPersistence;
    }

    /**
     * Sets the asset link persistence.
     *
     * @param assetLinkPersistence the asset link persistence
     */
    public void setAssetLinkPersistence(
        AssetLinkPersistence assetLinkPersistence) {
        this.assetLinkPersistence = assetLinkPersistence;
    }

    public void afterPropertiesSet() {
        Class<?> clazz = getClass();

        _classLoader = clazz.getClassLoader();

        PersistedModelLocalServiceRegistryUtil.register("it.smartcommunitylab.platform.idea.model.Idea",
            ideaLocalService);
    }

    public void destroy() {
        PersistedModelLocalServiceRegistryUtil.unregister(
            "it.smartcommunitylab.platform.idea.model.Idea");
    }

    /**
     * Returns the Spring bean ID for this bean.
     *
     * @return the Spring bean ID for this bean
     */
    @Override
    public String getBeanIdentifier() {
        return _beanIdentifier;
    }

    /**
     * Sets the Spring bean ID for this bean.
     *
     * @param beanIdentifier the Spring bean ID for this bean
     */
    @Override
    public void setBeanIdentifier(String beanIdentifier) {
        _beanIdentifier = beanIdentifier;
    }

    @Override
    public Object invokeMethod(String name, String[] parameterTypes,
        Object[] arguments) throws Throwable {
        Thread currentThread = Thread.currentThread();

        ClassLoader contextClassLoader = currentThread.getContextClassLoader();

        if (contextClassLoader != _classLoader) {
            currentThread.setContextClassLoader(_classLoader);
        }

        try {
            return _clpInvoker.invokeMethod(name, parameterTypes, arguments);
        } finally {
            if (contextClassLoader != _classLoader) {
                currentThread.setContextClassLoader(contextClassLoader);
            }
        }
    }

    protected Class<?> getModelClass() {
        return Idea.class;
    }

    protected String getModelClassName() {
        return Idea.class.getName();
    }

    /**
     * Performs an SQL query.
     *
     * @param sql the sql query
     */
    protected void runSQL(String sql) throws SystemException {
        try {
            DataSource dataSource = ideaPersistence.getDataSource();

            SqlUpdate sqlUpdate = SqlUpdateFactoryUtil.getSqlUpdate(dataSource,
                    sql, new int[0]);

            sqlUpdate.update();
        } catch (Exception e) {
            throw new SystemException(e);
        }
    }
}
