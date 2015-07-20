package it.smartcommunitylab.platform.idea.model;

import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.lar.StagedModelType;
import com.liferay.portal.kernel.util.DateUtil;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.model.BaseModel;
import com.liferay.portal.model.impl.BaseModelImpl;
import com.liferay.portal.util.PortalUtil;

import it.smartcommunitylab.platform.idea.service.CallLocalServiceUtil;
import it.smartcommunitylab.platform.idea.service.ClpSerializer;

import java.io.Serializable;

import java.lang.reflect.Method;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;


public class CallClp extends BaseModelImpl<Call> implements Call {
    private String _uuid;
    private String _title;
    private long _callId;
    private long _groupId;
    private long _companyId;
    private long _userId;
    private String _userUuid;
    private String _userName;
    private Date _createDate;
    private Date _modifiedDate;
    private String _description;
    private Date _deadline;
    private Date _publicationDeadline;
    private Date _realizationDeadline;
    private long _userGroupId;
    private String _categoryIds;
    private BaseModel<?> _callRemoteModel;
    private Class<?> _clpSerializerClass = it.smartcommunitylab.platform.idea.service.ClpSerializer.class;

    public CallClp() {
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
        return _uuid;
    }

    @Override
    public void setUuid(String uuid) {
        _uuid = uuid;

        if (_callRemoteModel != null) {
            try {
                Class<?> clazz = _callRemoteModel.getClass();

                Method method = clazz.getMethod("setUuid", String.class);

                method.invoke(_callRemoteModel, uuid);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getTitle() {
        return _title;
    }

    @Override
    public void setTitle(String title) {
        _title = title;

        if (_callRemoteModel != null) {
            try {
                Class<?> clazz = _callRemoteModel.getClass();

                Method method = clazz.getMethod("setTitle", String.class);

                method.invoke(_callRemoteModel, title);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public long getCallId() {
        return _callId;
    }

    @Override
    public void setCallId(long callId) {
        _callId = callId;

        if (_callRemoteModel != null) {
            try {
                Class<?> clazz = _callRemoteModel.getClass();

                Method method = clazz.getMethod("setCallId", long.class);

                method.invoke(_callRemoteModel, callId);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public long getGroupId() {
        return _groupId;
    }

    @Override
    public void setGroupId(long groupId) {
        _groupId = groupId;

        if (_callRemoteModel != null) {
            try {
                Class<?> clazz = _callRemoteModel.getClass();

                Method method = clazz.getMethod("setGroupId", long.class);

                method.invoke(_callRemoteModel, groupId);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public long getCompanyId() {
        return _companyId;
    }

    @Override
    public void setCompanyId(long companyId) {
        _companyId = companyId;

        if (_callRemoteModel != null) {
            try {
                Class<?> clazz = _callRemoteModel.getClass();

                Method method = clazz.getMethod("setCompanyId", long.class);

                method.invoke(_callRemoteModel, companyId);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public long getUserId() {
        return _userId;
    }

    @Override
    public void setUserId(long userId) {
        _userId = userId;

        if (_callRemoteModel != null) {
            try {
                Class<?> clazz = _callRemoteModel.getClass();

                Method method = clazz.getMethod("setUserId", long.class);

                method.invoke(_callRemoteModel, userId);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
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
        return _userName;
    }

    @Override
    public void setUserName(String userName) {
        _userName = userName;

        if (_callRemoteModel != null) {
            try {
                Class<?> clazz = _callRemoteModel.getClass();

                Method method = clazz.getMethod("setUserName", String.class);

                method.invoke(_callRemoteModel, userName);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public Date getCreateDate() {
        return _createDate;
    }

    @Override
    public void setCreateDate(Date createDate) {
        _createDate = createDate;

        if (_callRemoteModel != null) {
            try {
                Class<?> clazz = _callRemoteModel.getClass();

                Method method = clazz.getMethod("setCreateDate", Date.class);

                method.invoke(_callRemoteModel, createDate);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public Date getModifiedDate() {
        return _modifiedDate;
    }

    @Override
    public void setModifiedDate(Date modifiedDate) {
        _modifiedDate = modifiedDate;

        if (_callRemoteModel != null) {
            try {
                Class<?> clazz = _callRemoteModel.getClass();

                Method method = clazz.getMethod("setModifiedDate", Date.class);

                method.invoke(_callRemoteModel, modifiedDate);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getDescription() {
        return _description;
    }

    @Override
    public void setDescription(String description) {
        _description = description;

        if (_callRemoteModel != null) {
            try {
                Class<?> clazz = _callRemoteModel.getClass();

                Method method = clazz.getMethod("setDescription", String.class);

                method.invoke(_callRemoteModel, description);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public Date getDeadline() {
        return _deadline;
    }

    @Override
    public void setDeadline(Date deadline) {
        _deadline = deadline;

        if (_callRemoteModel != null) {
            try {
                Class<?> clazz = _callRemoteModel.getClass();

                Method method = clazz.getMethod("setDeadline", Date.class);

                method.invoke(_callRemoteModel, deadline);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public Date getPublicationDeadline() {
        return _publicationDeadline;
    }

    @Override
    public void setPublicationDeadline(Date publicationDeadline) {
        _publicationDeadline = publicationDeadline;

        if (_callRemoteModel != null) {
            try {
                Class<?> clazz = _callRemoteModel.getClass();

                Method method = clazz.getMethod("setPublicationDeadline",
                        Date.class);

                method.invoke(_callRemoteModel, publicationDeadline);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public Date getRealizationDeadline() {
        return _realizationDeadline;
    }

    @Override
    public void setRealizationDeadline(Date realizationDeadline) {
        _realizationDeadline = realizationDeadline;

        if (_callRemoteModel != null) {
            try {
                Class<?> clazz = _callRemoteModel.getClass();

                Method method = clazz.getMethod("setRealizationDeadline",
                        Date.class);

                method.invoke(_callRemoteModel, realizationDeadline);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public long getUserGroupId() {
        return _userGroupId;
    }

    @Override
    public void setUserGroupId(long userGroupId) {
        _userGroupId = userGroupId;

        if (_callRemoteModel != null) {
            try {
                Class<?> clazz = _callRemoteModel.getClass();

                Method method = clazz.getMethod("setUserGroupId", long.class);

                method.invoke(_callRemoteModel, userGroupId);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getCategoryIds() {
        return _categoryIds;
    }

    @Override
    public void setCategoryIds(String categoryIds) {
        _categoryIds = categoryIds;

        if (_callRemoteModel != null) {
            try {
                Class<?> clazz = _callRemoteModel.getClass();

                Method method = clazz.getMethod("setCategoryIds", String.class);

                method.invoke(_callRemoteModel, categoryIds);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public StagedModelType getStagedModelType() {
        return new StagedModelType(PortalUtil.getClassNameId(
                Call.class.getName()));
    }

    public BaseModel<?> getCallRemoteModel() {
        return _callRemoteModel;
    }

    public void setCallRemoteModel(BaseModel<?> callRemoteModel) {
        _callRemoteModel = callRemoteModel;
    }

    public Object invokeOnRemoteModel(String methodName,
        Class<?>[] parameterTypes, Object[] parameterValues)
        throws Exception {
        Object[] remoteParameterValues = new Object[parameterValues.length];

        for (int i = 0; i < parameterValues.length; i++) {
            if (parameterValues[i] != null) {
                remoteParameterValues[i] = ClpSerializer.translateInput(parameterValues[i]);
            }
        }

        Class<?> remoteModelClass = _callRemoteModel.getClass();

        ClassLoader remoteModelClassLoader = remoteModelClass.getClassLoader();

        Class<?>[] remoteParameterTypes = new Class[parameterTypes.length];

        for (int i = 0; i < parameterTypes.length; i++) {
            if (parameterTypes[i].isPrimitive()) {
                remoteParameterTypes[i] = parameterTypes[i];
            } else {
                String parameterTypeName = parameterTypes[i].getName();

                remoteParameterTypes[i] = remoteModelClassLoader.loadClass(parameterTypeName);
            }
        }

        Method method = remoteModelClass.getMethod(methodName,
                remoteParameterTypes);

        Object returnValue = method.invoke(_callRemoteModel,
                remoteParameterValues);

        if (returnValue != null) {
            returnValue = ClpSerializer.translateOutput(returnValue);
        }

        return returnValue;
    }

    @Override
    public void persist() throws SystemException {
        if (this.isNew()) {
            CallLocalServiceUtil.addCall(this);
        } else {
            CallLocalServiceUtil.updateCall(this);
        }
    }

    @Override
    public Call toEscapedModel() {
        return (Call) ProxyUtil.newProxyInstance(Call.class.getClassLoader(),
            new Class[] { Call.class }, new AutoEscapeBeanHandler(this));
    }

    @Override
    public Object clone() {
        CallClp clone = new CallClp();

        clone.setUuid(getUuid());
        clone.setTitle(getTitle());
        clone.setCallId(getCallId());
        clone.setGroupId(getGroupId());
        clone.setCompanyId(getCompanyId());
        clone.setUserId(getUserId());
        clone.setUserName(getUserName());
        clone.setCreateDate(getCreateDate());
        clone.setModifiedDate(getModifiedDate());
        clone.setDescription(getDescription());
        clone.setDeadline(getDeadline());
        clone.setPublicationDeadline(getPublicationDeadline());
        clone.setRealizationDeadline(getRealizationDeadline());
        clone.setUserGroupId(getUserGroupId());
        clone.setCategoryIds(getCategoryIds());

        return clone;
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

        if (!(obj instanceof CallClp)) {
            return false;
        }

        CallClp call = (CallClp) obj;

        long primaryKey = call.getPrimaryKey();

        if (getPrimaryKey() == primaryKey) {
            return true;
        } else {
            return false;
        }
    }

    public Class<?> getClpSerializerClass() {
        return _clpSerializerClass;
    }

    @Override
    public int hashCode() {
        return (int) getPrimaryKey();
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
