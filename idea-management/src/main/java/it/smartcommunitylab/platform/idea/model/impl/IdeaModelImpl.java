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

import it.smartcommunitylab.platform.idea.model.Idea;
import it.smartcommunitylab.platform.idea.model.IdeaModel;

import java.io.Serializable;

import java.sql.Types;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * The base model implementation for the Idea service. Represents a row in the &quot;IM_Idea&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface {@link it.smartcommunitylab.platform.idea.model.IdeaModel} exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link IdeaImpl}.
 * </p>
 *
 * @author mirko perillo
 * @see IdeaImpl
 * @see it.smartcommunitylab.platform.idea.model.Idea
 * @see it.smartcommunitylab.platform.idea.model.IdeaModel
 * @generated
 */
public class IdeaModelImpl extends BaseModelImpl<Idea> implements IdeaModel {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this class directly. All methods that expect a idea model instance should use the {@link it.smartcommunitylab.platform.idea.model.Idea} interface instead.
     */
    public static final String TABLE_NAME = "IM_Idea";
    public static final Object[][] TABLE_COLUMNS = {
            { "uuid_", Types.VARCHAR },
            { "ideaId", Types.BIGINT },
            { "groupId", Types.BIGINT },
            { "companyId", Types.BIGINT },
            { "userId", Types.BIGINT },
            { "userName", Types.VARCHAR },
            { "createDate", Types.TIMESTAMP },
            { "modifiedDate", Types.TIMESTAMP },
            { "title", Types.VARCHAR },
            { "longDesc", Types.CLOB },
            { "shortDesc", Types.VARCHAR },
            { "userGroupId", Types.BIGINT },
            { "callId", Types.BIGINT }
        };
    public static final String TABLE_SQL_CREATE = "create table IM_Idea (uuid_ VARCHAR(75) null,ideaId LONG not null primary key,groupId LONG,companyId LONG,userId LONG,userName VARCHAR(75) null,createDate DATE null,modifiedDate DATE null,title VARCHAR(75) null,longDesc TEXT null,shortDesc VARCHAR(75) null,userGroupId LONG,callId LONG)";
    public static final String TABLE_SQL_DROP = "drop table IM_Idea";
    public static final String ORDER_BY_JPQL = " ORDER BY idea.createDate DESC, idea.title ASC";
    public static final String ORDER_BY_SQL = " ORDER BY IM_Idea.createDate DESC, IM_Idea.title ASC";
    public static final String DATA_SOURCE = "liferayDataSource";
    public static final String SESSION_FACTORY = "liferaySessionFactory";
    public static final String TX_MANAGER = "liferayTransactionManager";
    public static final boolean ENTITY_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.util.service.ServiceProps.get(
                "value.object.entity.cache.enabled.it.smartcommunitylab.platform.idea.model.Idea"),
            true);
    public static final boolean FINDER_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.util.service.ServiceProps.get(
                "value.object.finder.cache.enabled.it.smartcommunitylab.platform.idea.model.Idea"),
            true);
    public static final boolean COLUMN_BITMASK_ENABLED = GetterUtil.getBoolean(com.liferay.util.service.ServiceProps.get(
                "value.object.column.bitmask.enabled.it.smartcommunitylab.platform.idea.model.Idea"),
            true);
    public static long COMPANYID_COLUMN_BITMASK = 1L;
    public static long GROUPID_COLUMN_BITMASK = 2L;
    public static long UUID_COLUMN_BITMASK = 4L;
    public static long CREATEDATE_COLUMN_BITMASK = 8L;
    public static long TITLE_COLUMN_BITMASK = 16L;
    public static final long LOCK_EXPIRATION_TIME = GetterUtil.getLong(com.liferay.util.service.ServiceProps.get(
                "lock.expiration.time.it.smartcommunitylab.platform.idea.model.Idea"));
    private static ClassLoader _classLoader = Idea.class.getClassLoader();
    private static Class<?>[] _escapedModelInterfaces = new Class[] { Idea.class };
    private String _uuid;
    private String _originalUuid;
    private long _ideaId;
    private long _groupId;
    private long _originalGroupId;
    private boolean _setOriginalGroupId;
    private long _companyId;
    private long _originalCompanyId;
    private boolean _setOriginalCompanyId;
    private long _userId;
    private String _userUuid;
    private String _userName;
    private Date _createDate;
    private Date _modifiedDate;
    private String _title;
    private String _longDesc;
    private String _shortDesc;
    private long _userGroupId;
    private long _callId;
    private long _columnBitmask;
    private Idea _escapedModel;

    public IdeaModelImpl() {
    }

    @Override
    public long getPrimaryKey() {
        return _ideaId;
    }

    @Override
    public void setPrimaryKey(long primaryKey) {
        setIdeaId(primaryKey);
    }

    @Override
    public Serializable getPrimaryKeyObj() {
        return _ideaId;
    }

    @Override
    public void setPrimaryKeyObj(Serializable primaryKeyObj) {
        setPrimaryKey(((Long) primaryKeyObj).longValue());
    }

    @Override
    public Class<?> getModelClass() {
        return Idea.class;
    }

    @Override
    public String getModelClassName() {
        return Idea.class.getName();
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("uuid", getUuid());
        attributes.put("ideaId", getIdeaId());
        attributes.put("groupId", getGroupId());
        attributes.put("companyId", getCompanyId());
        attributes.put("userId", getUserId());
        attributes.put("userName", getUserName());
        attributes.put("createDate", getCreateDate());
        attributes.put("modifiedDate", getModifiedDate());
        attributes.put("title", getTitle());
        attributes.put("longDesc", getLongDesc());
        attributes.put("shortDesc", getShortDesc());
        attributes.put("userGroupId", getUserGroupId());
        attributes.put("callId", getCallId());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        String uuid = (String) attributes.get("uuid");

        if (uuid != null) {
            setUuid(uuid);
        }

        Long ideaId = (Long) attributes.get("ideaId");

        if (ideaId != null) {
            setIdeaId(ideaId);
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

        String title = (String) attributes.get("title");

        if (title != null) {
            setTitle(title);
        }

        String longDesc = (String) attributes.get("longDesc");

        if (longDesc != null) {
            setLongDesc(longDesc);
        }

        String shortDesc = (String) attributes.get("shortDesc");

        if (shortDesc != null) {
            setShortDesc(shortDesc);
        }

        Long userGroupId = (Long) attributes.get("userGroupId");

        if (userGroupId != null) {
            setUserGroupId(userGroupId);
        }

        Long callId = (Long) attributes.get("callId");

        if (callId != null) {
            setCallId(callId);
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
    public long getIdeaId() {
        return _ideaId;
    }

    @Override
    public void setIdeaId(long ideaId) {
        _ideaId = ideaId;
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
    public String getTitle() {
        if (_title == null) {
            return StringPool.BLANK;
        } else {
            return _title;
        }
    }

    @Override
    public void setTitle(String title) {
        _columnBitmask = -1L;

        _title = title;
    }

    @Override
    public String getLongDesc() {
        if (_longDesc == null) {
            return StringPool.BLANK;
        } else {
            return _longDesc;
        }
    }

    @Override
    public void setLongDesc(String longDesc) {
        _longDesc = longDesc;
    }

    @Override
    public String getShortDesc() {
        if (_shortDesc == null) {
            return StringPool.BLANK;
        } else {
            return _shortDesc;
        }
    }

    @Override
    public void setShortDesc(String shortDesc) {
        _shortDesc = shortDesc;
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
    public long getCallId() {
        return _callId;
    }

    @Override
    public void setCallId(long callId) {
        _callId = callId;
    }

    @Override
    public StagedModelType getStagedModelType() {
        return new StagedModelType(PortalUtil.getClassNameId(
                Idea.class.getName()));
    }

    public long getColumnBitmask() {
        return _columnBitmask;
    }

    @Override
    public ExpandoBridge getExpandoBridge() {
        return ExpandoBridgeFactoryUtil.getExpandoBridge(getCompanyId(),
            Idea.class.getName(), getPrimaryKey());
    }

    @Override
    public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
        ExpandoBridge expandoBridge = getExpandoBridge();

        expandoBridge.setAttributes(serviceContext);
    }

    @Override
    public Idea toEscapedModel() {
        if (_escapedModel == null) {
            _escapedModel = (Idea) ProxyUtil.newProxyInstance(_classLoader,
                    _escapedModelInterfaces, new AutoEscapeBeanHandler(this));
        }

        return _escapedModel;
    }

    @Override
    public Object clone() {
        IdeaImpl ideaImpl = new IdeaImpl();

        ideaImpl.setUuid(getUuid());
        ideaImpl.setIdeaId(getIdeaId());
        ideaImpl.setGroupId(getGroupId());
        ideaImpl.setCompanyId(getCompanyId());
        ideaImpl.setUserId(getUserId());
        ideaImpl.setUserName(getUserName());
        ideaImpl.setCreateDate(getCreateDate());
        ideaImpl.setModifiedDate(getModifiedDate());
        ideaImpl.setTitle(getTitle());
        ideaImpl.setLongDesc(getLongDesc());
        ideaImpl.setShortDesc(getShortDesc());
        ideaImpl.setUserGroupId(getUserGroupId());
        ideaImpl.setCallId(getCallId());

        ideaImpl.resetOriginalValues();

        return ideaImpl;
    }

    @Override
    public int compareTo(Idea idea) {
        int value = 0;

        value = DateUtil.compareTo(getCreateDate(), idea.getCreateDate());

        value = value * -1;

        if (value != 0) {
            return value;
        }

        value = getTitle().compareTo(idea.getTitle());

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

        if (!(obj instanceof Idea)) {
            return false;
        }

        Idea idea = (Idea) obj;

        long primaryKey = idea.getPrimaryKey();

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
        IdeaModelImpl ideaModelImpl = this;

        ideaModelImpl._originalUuid = ideaModelImpl._uuid;

        ideaModelImpl._originalGroupId = ideaModelImpl._groupId;

        ideaModelImpl._setOriginalGroupId = false;

        ideaModelImpl._originalCompanyId = ideaModelImpl._companyId;

        ideaModelImpl._setOriginalCompanyId = false;

        ideaModelImpl._columnBitmask = 0;
    }

    @Override
    public CacheModel<Idea> toCacheModel() {
        IdeaCacheModel ideaCacheModel = new IdeaCacheModel();

        ideaCacheModel.uuid = getUuid();

        String uuid = ideaCacheModel.uuid;

        if ((uuid != null) && (uuid.length() == 0)) {
            ideaCacheModel.uuid = null;
        }

        ideaCacheModel.ideaId = getIdeaId();

        ideaCacheModel.groupId = getGroupId();

        ideaCacheModel.companyId = getCompanyId();

        ideaCacheModel.userId = getUserId();

        ideaCacheModel.userName = getUserName();

        String userName = ideaCacheModel.userName;

        if ((userName != null) && (userName.length() == 0)) {
            ideaCacheModel.userName = null;
        }

        Date createDate = getCreateDate();

        if (createDate != null) {
            ideaCacheModel.createDate = createDate.getTime();
        } else {
            ideaCacheModel.createDate = Long.MIN_VALUE;
        }

        Date modifiedDate = getModifiedDate();

        if (modifiedDate != null) {
            ideaCacheModel.modifiedDate = modifiedDate.getTime();
        } else {
            ideaCacheModel.modifiedDate = Long.MIN_VALUE;
        }

        ideaCacheModel.title = getTitle();

        String title = ideaCacheModel.title;

        if ((title != null) && (title.length() == 0)) {
            ideaCacheModel.title = null;
        }

        ideaCacheModel.longDesc = getLongDesc();

        String longDesc = ideaCacheModel.longDesc;

        if ((longDesc != null) && (longDesc.length() == 0)) {
            ideaCacheModel.longDesc = null;
        }

        ideaCacheModel.shortDesc = getShortDesc();

        String shortDesc = ideaCacheModel.shortDesc;

        if ((shortDesc != null) && (shortDesc.length() == 0)) {
            ideaCacheModel.shortDesc = null;
        }

        ideaCacheModel.userGroupId = getUserGroupId();

        ideaCacheModel.callId = getCallId();

        return ideaCacheModel;
    }

    @Override
    public String toString() {
        StringBundler sb = new StringBundler(27);

        sb.append("{uuid=");
        sb.append(getUuid());
        sb.append(", ideaId=");
        sb.append(getIdeaId());
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
        sb.append(", title=");
        sb.append(getTitle());
        sb.append(", longDesc=");
        sb.append(getLongDesc());
        sb.append(", shortDesc=");
        sb.append(getShortDesc());
        sb.append(", userGroupId=");
        sb.append(getUserGroupId());
        sb.append(", callId=");
        sb.append(getCallId());
        sb.append("}");

        return sb.toString();
    }

    @Override
    public String toXmlString() {
        StringBundler sb = new StringBundler(43);

        sb.append("<model><model-name>");
        sb.append("it.smartcommunitylab.platform.idea.model.Idea");
        sb.append("</model-name>");

        sb.append(
            "<column><column-name>uuid</column-name><column-value><![CDATA[");
        sb.append(getUuid());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>ideaId</column-name><column-value><![CDATA[");
        sb.append(getIdeaId());
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
            "<column><column-name>title</column-name><column-value><![CDATA[");
        sb.append(getTitle());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>longDesc</column-name><column-value><![CDATA[");
        sb.append(getLongDesc());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>shortDesc</column-name><column-value><![CDATA[");
        sb.append(getShortDesc());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>userGroupId</column-name><column-value><![CDATA[");
        sb.append(getUserGroupId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>callId</column-name><column-value><![CDATA[");
        sb.append(getCallId());
        sb.append("]]></column-value></column>");

        sb.append("</model>");

        return sb.toString();
    }
}
