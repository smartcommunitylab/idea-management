package it.smartcommunitylab.platform.idea.model.impl;

import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.lar.StagedModelType;
import com.liferay.portal.kernel.util.DateUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.CacheModel;
import com.liferay.portal.model.impl.BaseModelImpl;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.util.PortalUtil;

import com.liferay.portlet.expando.model.ExpandoBridge;
import com.liferay.portlet.expando.util.ExpandoBridgeFactoryUtil;

import it.smartcommunitylab.platform.idea.model.Call;
import it.smartcommunitylab.platform.idea.model.CallModel;

import java.io.Serializable;

import java.sql.Types;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * The base model implementation for the Call service. Represents a row in the &quot;IM_Call&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface {@link it.smartcommunitylab.platform.idea.model.CallModel} exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link CallImpl}.
 * </p>
 *
 * @author mirko perillo
 * @see CallImpl
 * @see it.smartcommunitylab.platform.idea.model.Call
 * @see it.smartcommunitylab.platform.idea.model.CallModel
 * @generated
 */
public class CallModelImpl extends BaseModelImpl<Call> implements CallModel {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this class directly. All methods that expect a call model instance should use the {@link it.smartcommunitylab.platform.idea.model.Call} interface instead.
     */
    public static final String TABLE_NAME = "IM_Call";
    public static final Object[][] TABLE_COLUMNS = {
            { "uuid_", Types.VARCHAR },
            { "title", Types.VARCHAR },
            { "callId", Types.BIGINT },
            { "groupId", Types.BIGINT },
            { "companyId", Types.BIGINT },
            { "userId", Types.BIGINT },
            { "userName", Types.VARCHAR },
            { "createDate", Types.TIMESTAMP },
            { "modifiedDate", Types.TIMESTAMP },
            { "description", Types.CLOB },
            { "deadline", Types.TIMESTAMP },
            { "publicationDeadline", Types.TIMESTAMP },
            { "realizationDeadline", Types.TIMESTAMP },
            { "userGroupId", Types.BIGINT },
            { "categoryIds", Types.VARCHAR }
        };
    public static final String TABLE_SQL_CREATE = "create table IM_Call (uuid_ VARCHAR(75) null,title VARCHAR(75) null,callId LONG not null primary key,groupId LONG,companyId LONG,userId LONG,userName VARCHAR(75) null,createDate DATE null,modifiedDate DATE null,description TEXT null,deadline DATE null,publicationDeadline DATE null,realizationDeadline DATE null,userGroupId LONG,categoryIds VARCHAR(75) null)";
    public static final String TABLE_SQL_DROP = "drop table IM_Call";
    public static final String ORDER_BY_JPQL = " ORDER BY call.createDate DESC";
    public static final String ORDER_BY_SQL = " ORDER BY IM_Call.createDate DESC";
    public static final String DATA_SOURCE = "liferayDataSource";
    public static final String SESSION_FACTORY = "liferaySessionFactory";
    public static final String TX_MANAGER = "liferayTransactionManager";
    public static final boolean ENTITY_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.util.service.ServiceProps.get(
                "value.object.entity.cache.enabled.it.smartcommunitylab.platform.idea.model.Call"),
            true);
    public static final boolean FINDER_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.util.service.ServiceProps.get(
                "value.object.finder.cache.enabled.it.smartcommunitylab.platform.idea.model.Call"),
            true);
    public static final boolean COLUMN_BITMASK_ENABLED = GetterUtil.getBoolean(com.liferay.util.service.ServiceProps.get(
                "value.object.column.bitmask.enabled.it.smartcommunitylab.platform.idea.model.Call"),
            true);
    public static long COMPANYID_COLUMN_BITMASK = 1L;
    public static long GROUPID_COLUMN_BITMASK = 2L;
    public static long USERID_COLUMN_BITMASK = 4L;
    public static long UUID_COLUMN_BITMASK = 8L;
    public static long CREATEDATE_COLUMN_BITMASK = 16L;
    public static final long LOCK_EXPIRATION_TIME = GetterUtil.getLong(com.liferay.util.service.ServiceProps.get(
                "lock.expiration.time.it.smartcommunitylab.platform.idea.model.Call"));
    private static ClassLoader _classLoader = Call.class.getClassLoader();
    private static Class<?>[] _escapedModelInterfaces = new Class[] { Call.class };
    private String _uuid;
    private String _originalUuid;
    private String _title;
    private long _callId;
    private long _groupId;
    private long _originalGroupId;
    private boolean _setOriginalGroupId;
    private long _companyId;
    private long _originalCompanyId;
    private boolean _setOriginalCompanyId;
    private long _userId;
    private String _userUuid;
    private long _originalUserId;
    private boolean _setOriginalUserId;
    private String _userName;
    private Date _createDate;
    private Date _modifiedDate;
    private String _description;
    private Date _deadline;
    private Date _publicationDeadline;
    private Date _realizationDeadline;
    private long _userGroupId;
    private String _categoryIds;
    private long _columnBitmask;
    private Call _escapedModel;

    public CallModelImpl() {
    }

    @Override
    public long getPrimaryKey() {
        return _callId;
    }

    @Override
    public void setPrimaryKey(long primaryKey) {
        setCallId(primaryKey);
    }

    @Override
    public Serializable getPrimaryKeyObj() {
        return _callId;
    }

    @Override
    public void setPrimaryKeyObj(Serializable primaryKeyObj) {
        setPrimaryKey(((Long) primaryKeyObj).longValue());
    }

    @Override
    public Class<?> getModelClass() {
        return Call.class;
    }

    @Override
    public String getModelClassName() {
        return Call.class.getName();
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("uuid", getUuid());
        attributes.put("title", getTitle());
        attributes.put("callId", getCallId());
        attributes.put("groupId", getGroupId());
        attributes.put("companyId", getCompanyId());
        attributes.put("userId", getUserId());
        attributes.put("userName", getUserName());
        attributes.put("createDate", getCreateDate());
        attributes.put("modifiedDate", getModifiedDate());
        attributes.put("description", getDescription());
        attributes.put("deadline", getDeadline());
        attributes.put("publicationDeadline", getPublicationDeadline());
        attributes.put("realizationDeadline", getRealizationDeadline());
        attributes.put("userGroupId", getUserGroupId());
        attributes.put("categoryIds", getCategoryIds());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        String uuid = (String) attributes.get("uuid");

        if (uuid != null) {
            setUuid(uuid);
        }

        String title = (String) attributes.get("title");

        if (title != null) {
            setTitle(title);
        }

        Long callId = (Long) attributes.get("callId");

        if (callId != null) {
            setCallId(callId);
        }

        Long groupId = (Long) attributes.get("groupId");

        if (groupId != null) {
            setGroupId(groupId);
        }

        Long companyId = (Long) attributes.get("companyId");

        if (companyId != null) {
            setCompanyId(companyId);
        }

        Long userId = (Long) attributes.get("userId");

        if (userId != null) {
            setUserId(userId);
        }

        String userName = (String) attributes.get("userName");

        if (userName != null) {
            setUserName(userName);
        }

        Date createDate = (Date) attributes.get("createDate");

        if (createDate != null) {
            setCreateDate(createDate);
        }

        Date modifiedDate = (Date) attributes.get("modifiedDate");

        if (modifiedDate != null) {
            setModifiedDate(modifiedDate);
        }

        String description = (String) attributes.get("description");

        if (description != null) {
            setDescription(description);
        }

        Date deadline = (Date) attributes.get("deadline");

        if (deadline != null) {
            setDeadline(deadline);
        }

        Date publicationDeadline = (Date) attributes.get("publicationDeadline");

        if (publicationDeadline != null) {
            setPublicationDeadline(publicationDeadline);
        }

        Date realizationDeadline = (Date) attributes.get("realizationDeadline");

        if (realizationDeadline != null) {
            setRealizationDeadline(realizationDeadline);
        }

        Long userGroupId = (Long) attributes.get("userGroupId");

        if (userGroupId != null) {
            setUserGroupId(userGroupId);
        }

        String categoryIds = (String) attributes.get("categoryIds");

        if (categoryIds != null) {
            setCategoryIds(categoryIds);
        }
    }

    @Override
    public String getUuid() {
        if (_uuid == null) {
            return StringPool.BLANK;
        } else {
            return _uuid;
        }
    }

    @Override
    public void setUuid(String uuid) {
        if (_originalUuid == null) {
            _originalUuid = _uuid;
        }

        _uuid = uuid;
    }

    public String getOriginalUuid() {
        return GetterUtil.getString(_originalUuid);
    }

    @Override
    public String getTitle() {
        if (_title == null) {
            return StringPool.BLANK;
        } else {
            return _title;
        }
    }

    @Override
    public void setTitle(String title) {
        _title = title;
    }

    @Override
    public long getCallId() {
        return _callId;
    }

    @Override
    public void setCallId(long callId) {
        _callId = callId;
    }

    @Override
    public long getGroupId() {
        return _groupId;
    }

    @Override
    public void setGroupId(long groupId) {
        _columnBitmask |= GROUPID_COLUMN_BITMASK;

        if (!_setOriginalGroupId) {
            _setOriginalGroupId = true;

            _originalGroupId = _groupId;
        }

        _groupId = groupId;
    }

    public long getOriginalGroupId() {
        return _originalGroupId;
    }

    @Override
    public long getCompanyId() {
        return _companyId;
    }

    @Override
    public void setCompanyId(long companyId) {
        _columnBitmask |= COMPANYID_COLUMN_BITMASK;

        if (!_setOriginalCompanyId) {
            _setOriginalCompanyId = true;

            _originalCompanyId = _companyId;
        }

        _companyId = companyId;
    }

    public long getOriginalCompanyId() {
        return _originalCompanyId;
    }

    @Override
    public long getUserId() {
        return _userId;
    }

    @Override
    public void setUserId(long userId) {
        _columnBitmask |= USERID_COLUMN_BITMASK;

        if (!_setOriginalUserId) {
            _setOriginalUserId = true;

            _originalUserId = _userId;
        }

        _userId = userId;
    }

    @Override
    public String getUserUuid() throws SystemException {
        return PortalUtil.getUserValue(getUserId(), "uuid", _userUuid);
    }

    @Override
    public void setUserUuid(String userUuid) {
        _userUuid = userUuid;
    }

    public long getOriginalUserId() {
        return _originalUserId;
    }

    @Override
    public String getUserName() {
        if (_userName == null) {
            return StringPool.BLANK;
        } else {
            return _userName;
        }
    }

    @Override
    public void setUserName(String userName) {
        _userName = userName;
    }

    @Override
    public Date getCreateDate() {
        return _createDate;
    }

    @Override
    public void setCreateDate(Date createDate) {
        _columnBitmask = -1L;

        _createDate = createDate;
    }

    @Override
    public Date getModifiedDate() {
        return _modifiedDate;
    }

    @Override
    public void setModifiedDate(Date modifiedDate) {
        _modifiedDate = modifiedDate;
    }

    @Override
    public String getDescription() {
        if (_description == null) {
            return StringPool.BLANK;
        } else {
            return _description;
        }
    }

    @Override
    public void setDescription(String description) {
        _description = description;
    }

    @Override
    public Date getDeadline() {
        return _deadline;
    }

    @Override
    public void setDeadline(Date deadline) {
        _deadline = deadline;
    }

    @Override
    public Date getPublicationDeadline() {
        return _publicationDeadline;
    }

    @Override
    public void setPublicationDeadline(Date publicationDeadline) {
        _publicationDeadline = publicationDeadline;
    }

    @Override
    public Date getRealizationDeadline() {
        return _realizationDeadline;
    }

    @Override
    public void setRealizationDeadline(Date realizationDeadline) {
        _realizationDeadline = realizationDeadline;
    }

    @Override
    public long getUserGroupId() {
        return _userGroupId;
    }

    @Override
    public void setUserGroupId(long userGroupId) {
        _userGroupId = userGroupId;
    }

    @Override
    public String getCategoryIds() {
        if (_categoryIds == null) {
            return StringPool.BLANK;
        } else {
            return _categoryIds;
        }
    }

    @Override
    public void setCategoryIds(String categoryIds) {
        _categoryIds = categoryIds;
    }

    @Override
    public StagedModelType getStagedModelType() {
        return new StagedModelType(PortalUtil.getClassNameId(
                Call.class.getName()));
    }

    public long getColumnBitmask() {
        return _columnBitmask;
    }

    @Override
    public ExpandoBridge getExpandoBridge() {
        return ExpandoBridgeFactoryUtil.getExpandoBridge(getCompanyId(),
            Call.class.getName(), getPrimaryKey());
    }

    @Override
    public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
        ExpandoBridge expandoBridge = getExpandoBridge();

        expandoBridge.setAttributes(serviceContext);
    }

    @Override
    public Call toEscapedModel() {
        if (_escapedModel == null) {
            _escapedModel = (Call) ProxyUtil.newProxyInstance(_classLoader,
                    _escapedModelInterfaces, new AutoEscapeBeanHandler(this));
        }

        return _escapedModel;
    }

    @Override
    public Object clone() {
        CallImpl callImpl = new CallImpl();

        callImpl.setUuid(getUuid());
        callImpl.setTitle(getTitle());
        callImpl.setCallId(getCallId());
        callImpl.setGroupId(getGroupId());
        callImpl.setCompanyId(getCompanyId());
        callImpl.setUserId(getUserId());
        callImpl.setUserName(getUserName());
        callImpl.setCreateDate(getCreateDate());
        callImpl.setModifiedDate(getModifiedDate());
        callImpl.setDescription(getDescription());
        callImpl.setDeadline(getDeadline());
        callImpl.setPublicationDeadline(getPublicationDeadline());
        callImpl.setRealizationDeadline(getRealizationDeadline());
        callImpl.setUserGroupId(getUserGroupId());
        callImpl.setCategoryIds(getCategoryIds());

        callImpl.resetOriginalValues();

        return callImpl;
    }

    @Override
    public int compareTo(Call call) {
        int value = 0;

        value = DateUtil.compareTo(getCreateDate(), call.getCreateDate());

        value = value * -1;

        if (value != 0) {
            return value;
        }

        return 0;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof Call)) {
            return false;
        }

        Call call = (Call) obj;

        long primaryKey = call.getPrimaryKey();

        if (getPrimaryKey() == primaryKey) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        return (int) getPrimaryKey();
    }

    @Override
    public void resetOriginalValues() {
        CallModelImpl callModelImpl = this;

        callModelImpl._originalUuid = callModelImpl._uuid;

        callModelImpl._originalGroupId = callModelImpl._groupId;

        callModelImpl._setOriginalGroupId = false;

        callModelImpl._originalCompanyId = callModelImpl._companyId;

        callModelImpl._setOriginalCompanyId = false;

        callModelImpl._originalUserId = callModelImpl._userId;

        callModelImpl._setOriginalUserId = false;

        callModelImpl._columnBitmask = 0;
    }

    @Override
    public CacheModel<Call> toCacheModel() {
        CallCacheModel callCacheModel = new CallCacheModel();

        callCacheModel.uuid = getUuid();

        String uuid = callCacheModel.uuid;

        if ((uuid != null) && (uuid.length() == 0)) {
            callCacheModel.uuid = null;
        }

        callCacheModel.title = getTitle();

        String title = callCacheModel.title;

        if ((title != null) && (title.length() == 0)) {
            callCacheModel.title = null;
        }

        callCacheModel.callId = getCallId();

        callCacheModel.groupId = getGroupId();

        callCacheModel.companyId = getCompanyId();

        callCacheModel.userId = getUserId();

        callCacheModel.userName = getUserName();

        String userName = callCacheModel.userName;

        if ((userName != null) && (userName.length() == 0)) {
            callCacheModel.userName = null;
        }

        Date createDate = getCreateDate();

        if (createDate != null) {
            callCacheModel.createDate = createDate.getTime();
        } else {
            callCacheModel.createDate = Long.MIN_VALUE;
        }

        Date modifiedDate = getModifiedDate();

        if (modifiedDate != null) {
            callCacheModel.modifiedDate = modifiedDate.getTime();
        } else {
            callCacheModel.modifiedDate = Long.MIN_VALUE;
        }

        callCacheModel.description = getDescription();

        String description = callCacheModel.description;

        if ((description != null) && (description.length() == 0)) {
            callCacheModel.description = null;
        }

        Date deadline = getDeadline();

        if (deadline != null) {
            callCacheModel.deadline = deadline.getTime();
        } else {
            callCacheModel.deadline = Long.MIN_VALUE;
        }

        Date publicationDeadline = getPublicationDeadline();

        if (publicationDeadline != null) {
            callCacheModel.publicationDeadline = publicationDeadline.getTime();
        } else {
            callCacheModel.publicationDeadline = Long.MIN_VALUE;
        }

        Date realizationDeadline = getRealizationDeadline();

        if (realizationDeadline != null) {
            callCacheModel.realizationDeadline = realizationDeadline.getTime();
        } else {
            callCacheModel.realizationDeadline = Long.MIN_VALUE;
        }

        callCacheModel.userGroupId = getUserGroupId();

        callCacheModel.categoryIds = getCategoryIds();

        String categoryIds = callCacheModel.categoryIds;

        if ((categoryIds != null) && (categoryIds.length() == 0)) {
            callCacheModel.categoryIds = null;
        }

        return callCacheModel;
    }

    @Override
    public String toString() {
        StringBundler sb = new StringBundler(31);

        sb.append("{uuid=");
        sb.append(getUuid());
        sb.append(", title=");
        sb.append(getTitle());
        sb.append(", callId=");
        sb.append(getCallId());
        sb.append(", groupId=");
        sb.append(getGroupId());
        sb.append(", companyId=");
        sb.append(getCompanyId());
        sb.append(", userId=");
        sb.append(getUserId());
        sb.append(", userName=");
        sb.append(getUserName());
        sb.append(", createDate=");
        sb.append(getCreateDate());
        sb.append(", modifiedDate=");
        sb.append(getModifiedDate());
        sb.append(", description=");
        sb.append(getDescription());
        sb.append(", deadline=");
        sb.append(getDeadline());
        sb.append(", publicationDeadline=");
        sb.append(getPublicationDeadline());
        sb.append(", realizationDeadline=");
        sb.append(getRealizationDeadline());
        sb.append(", userGroupId=");
        sb.append(getUserGroupId());
        sb.append(", categoryIds=");
        sb.append(getCategoryIds());
        sb.append("}");

        return sb.toString();
    }

    @Override
    public String toXmlString() {
        StringBundler sb = new StringBundler(49);

        sb.append("<model><model-name>");
        sb.append("it.smartcommunitylab.platform.idea.model.Call");
        sb.append("</model-name>");

        sb.append(
            "<column><column-name>uuid</column-name><column-value><![CDATA[");
        sb.append(getUuid());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>title</column-name><column-value><![CDATA[");
        sb.append(getTitle());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>callId</column-name><column-value><![CDATA[");
        sb.append(getCallId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>groupId</column-name><column-value><![CDATA[");
        sb.append(getGroupId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>companyId</column-name><column-value><![CDATA[");
        sb.append(getCompanyId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>userId</column-name><column-value><![CDATA[");
        sb.append(getUserId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>userName</column-name><column-value><![CDATA[");
        sb.append(getUserName());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>createDate</column-name><column-value><![CDATA[");
        sb.append(getCreateDate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>modifiedDate</column-name><column-value><![CDATA[");
        sb.append(getModifiedDate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>description</column-name><column-value><![CDATA[");
        sb.append(getDescription());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>deadline</column-name><column-value><![CDATA[");
        sb.append(getDeadline());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>publicationDeadline</column-name><column-value><![CDATA[");
        sb.append(getPublicationDeadline());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>realizationDeadline</column-name><column-value><![CDATA[");
        sb.append(getRealizationDeadline());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>userGroupId</column-name><column-value><![CDATA[");
        sb.append(getUserGroupId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>categoryIds</column-name><column-value><![CDATA[");
        sb.append(getCategoryIds());
        sb.append("]]></column-value></column>");

        sb.append("</model>");

        return sb.toString();
    }
}
