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

import it.smartcommunitylab.platform.idea.service.ClpSerializer;
import it.smartcommunitylab.platform.idea.service.IdeaLocalServiceUtil;

import java.io.Serializable;

import java.lang.reflect.Method;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;


public class IdeaClp extends BaseModelImpl<Idea> implements Idea {
    private String _uuid;
    private long _ideaId;
    private long _groupId;
    private long _companyId;
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
    private String _state;
    private String _stateJudgement;
    private String _deadlineConstraints;
    private int _discussionLimit;
    private int _status;
    private long _statusByUserId;
    private String _statusByUserUuid;
    private String _statusByUserName;
    private String _categoryIds;
    private BaseModel<?> _ideaRemoteModel;
    private Class<?> _clpSerializerClass = it.smartcommunitylab.platform.idea.service.ClpSerializer.class;

    public IdeaClp() {
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
        attributes.put("state", getState());
        attributes.put("stateJudgement", getStateJudgement());
        attributes.put("deadlineConstraints", getDeadlineConstraints());
        attributes.put("discussionLimit", getDiscussionLimit());
        attributes.put("status", getStatus());
        attributes.put("statusByUserId", getStatusByUserId());
        attributes.put("statusByUserName", getStatusByUserName());
        attributes.put("categoryIds", getCategoryIds());

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

        String state = (String) attributes.get("state");

        if (state != null) {
            setState(state);
        }

        String stateJudgement = (String) attributes.get("stateJudgement");

        if (stateJudgement != null) {
            setStateJudgement(stateJudgement);
        }

        String deadlineConstraints = (String) attributes.get(
                "deadlineConstraints");

        if (deadlineConstraints != null) {
            setDeadlineConstraints(deadlineConstraints);
        }

        Integer discussionLimit = (Integer) attributes.get("discussionLimit");

        if (discussionLimit != null) {
            setDiscussionLimit(discussionLimit);
        }

        Integer status = (Integer) attributes.get("status");

        if (status != null) {
            setStatus(status);
        }

        Long statusByUserId = (Long) attributes.get("statusByUserId");

        if (statusByUserId != null) {
            setStatusByUserId(statusByUserId);
        }

        String statusByUserName = (String) attributes.get("statusByUserName");

        if (statusByUserName != null) {
            setStatusByUserName(statusByUserName);
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

        if (_ideaRemoteModel != null) {
            try {
                Class<?> clazz = _ideaRemoteModel.getClass();

                Method method = clazz.getMethod("setUuid", String.class);

                method.invoke(_ideaRemoteModel, uuid);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public long getIdeaId() {
        return _ideaId;
    }

    @Override
    public void setIdeaId(long ideaId) {
        _ideaId = ideaId;

        if (_ideaRemoteModel != null) {
            try {
                Class<?> clazz = _ideaRemoteModel.getClass();

                Method method = clazz.getMethod("setIdeaId", long.class);

                method.invoke(_ideaRemoteModel, ideaId);
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

        if (_ideaRemoteModel != null) {
            try {
                Class<?> clazz = _ideaRemoteModel.getClass();

                Method method = clazz.getMethod("setGroupId", long.class);

                method.invoke(_ideaRemoteModel, groupId);
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

        if (_ideaRemoteModel != null) {
            try {
                Class<?> clazz = _ideaRemoteModel.getClass();

                Method method = clazz.getMethod("setCompanyId", long.class);

                method.invoke(_ideaRemoteModel, companyId);
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

        if (_ideaRemoteModel != null) {
            try {
                Class<?> clazz = _ideaRemoteModel.getClass();

                Method method = clazz.getMethod("setUserId", long.class);

                method.invoke(_ideaRemoteModel, userId);
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

        if (_ideaRemoteModel != null) {
            try {
                Class<?> clazz = _ideaRemoteModel.getClass();

                Method method = clazz.getMethod("setUserName", String.class);

                method.invoke(_ideaRemoteModel, userName);
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

        if (_ideaRemoteModel != null) {
            try {
                Class<?> clazz = _ideaRemoteModel.getClass();

                Method method = clazz.getMethod("setCreateDate", Date.class);

                method.invoke(_ideaRemoteModel, createDate);
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

        if (_ideaRemoteModel != null) {
            try {
                Class<?> clazz = _ideaRemoteModel.getClass();

                Method method = clazz.getMethod("setModifiedDate", Date.class);

                method.invoke(_ideaRemoteModel, modifiedDate);
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

        if (_ideaRemoteModel != null) {
            try {
                Class<?> clazz = _ideaRemoteModel.getClass();

                Method method = clazz.getMethod("setTitle", String.class);

                method.invoke(_ideaRemoteModel, title);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getLongDesc() {
        return _longDesc;
    }

    @Override
    public void setLongDesc(String longDesc) {
        _longDesc = longDesc;

        if (_ideaRemoteModel != null) {
            try {
                Class<?> clazz = _ideaRemoteModel.getClass();

                Method method = clazz.getMethod("setLongDesc", String.class);

                method.invoke(_ideaRemoteModel, longDesc);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getShortDesc() {
        return _shortDesc;
    }

    @Override
    public void setShortDesc(String shortDesc) {
        _shortDesc = shortDesc;

        if (_ideaRemoteModel != null) {
            try {
                Class<?> clazz = _ideaRemoteModel.getClass();

                Method method = clazz.getMethod("setShortDesc", String.class);

                method.invoke(_ideaRemoteModel, shortDesc);
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

        if (_ideaRemoteModel != null) {
            try {
                Class<?> clazz = _ideaRemoteModel.getClass();

                Method method = clazz.getMethod("setUserGroupId", long.class);

                method.invoke(_ideaRemoteModel, userGroupId);
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

        if (_ideaRemoteModel != null) {
            try {
                Class<?> clazz = _ideaRemoteModel.getClass();

                Method method = clazz.getMethod("setCallId", long.class);

                method.invoke(_ideaRemoteModel, callId);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getState() {
        return _state;
    }

    @Override
    public void setState(String state) {
        _state = state;

        if (_ideaRemoteModel != null) {
            try {
                Class<?> clazz = _ideaRemoteModel.getClass();

                Method method = clazz.getMethod("setState", String.class);

                method.invoke(_ideaRemoteModel, state);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getStateJudgement() {
        return _stateJudgement;
    }

    @Override
    public void setStateJudgement(String stateJudgement) {
        _stateJudgement = stateJudgement;

        if (_ideaRemoteModel != null) {
            try {
                Class<?> clazz = _ideaRemoteModel.getClass();

                Method method = clazz.getMethod("setStateJudgement",
                        String.class);

                method.invoke(_ideaRemoteModel, stateJudgement);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getDeadlineConstraints() {
        return _deadlineConstraints;
    }

    @Override
    public void setDeadlineConstraints(String deadlineConstraints) {
        _deadlineConstraints = deadlineConstraints;

        if (_ideaRemoteModel != null) {
            try {
                Class<?> clazz = _ideaRemoteModel.getClass();

                Method method = clazz.getMethod("setDeadlineConstraints",
                        String.class);

                method.invoke(_ideaRemoteModel, deadlineConstraints);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getDiscussionLimit() {
        return _discussionLimit;
    }

    @Override
    public void setDiscussionLimit(int discussionLimit) {
        _discussionLimit = discussionLimit;

        if (_ideaRemoteModel != null) {
            try {
                Class<?> clazz = _ideaRemoteModel.getClass();

                Method method = clazz.getMethod("setDiscussionLimit", int.class);

                method.invoke(_ideaRemoteModel, discussionLimit);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getStatus() {
        return _status;
    }

    @Override
    public void setStatus(int status) {
        _status = status;

        if (_ideaRemoteModel != null) {
            try {
                Class<?> clazz = _ideaRemoteModel.getClass();

                Method method = clazz.getMethod("setStatus", int.class);

                method.invoke(_ideaRemoteModel, status);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public long getStatusByUserId() {
        return _statusByUserId;
    }

    @Override
    public void setStatusByUserId(long statusByUserId) {
        _statusByUserId = statusByUserId;

        if (_ideaRemoteModel != null) {
            try {
                Class<?> clazz = _ideaRemoteModel.getClass();

                Method method = clazz.getMethod("setStatusByUserId", long.class);

                method.invoke(_ideaRemoteModel, statusByUserId);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getStatusByUserUuid() throws SystemException {
        return PortalUtil.getUserValue(getStatusByUserId(), "uuid",
            _statusByUserUuid);
    }

    @Override
    public void setStatusByUserUuid(String statusByUserUuid) {
        _statusByUserUuid = statusByUserUuid;
    }

    @Override
    public String getStatusByUserName() {
        return _statusByUserName;
    }

    @Override
    public void setStatusByUserName(String statusByUserName) {
        _statusByUserName = statusByUserName;

        if (_ideaRemoteModel != null) {
            try {
                Class<?> clazz = _ideaRemoteModel.getClass();

                Method method = clazz.getMethod("setStatusByUserName",
                        String.class);

                method.invoke(_ideaRemoteModel, statusByUserName);
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

        if (_ideaRemoteModel != null) {
            try {
                Class<?> clazz = _ideaRemoteModel.getClass();

                Method method = clazz.getMethod("setCategoryIds", String.class);

                method.invoke(_ideaRemoteModel, categoryIds);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public boolean discussionExpired() {
        try {
            String methodName = "discussionExpired";

            Class<?>[] parameterTypes = new Class<?>[] {  };

            Object[] parameterValues = new Object[] {  };

            Boolean returnObj = (Boolean) invokeOnRemoteModel(methodName,
                    parameterTypes, parameterValues);

            return returnObj;
        } catch (Exception e) {
            throw new UnsupportedOperationException(e);
        }
    }

    @Override
    public java.util.Date discussionDeadline() {
        try {
            String methodName = "discussionDeadline";

            Class<?>[] parameterTypes = new Class<?>[] {  };

            Object[] parameterValues = new Object[] {  };

            java.util.Date returnObj = (java.util.Date) invokeOnRemoteModel(methodName,
                    parameterTypes, parameterValues);

            return returnObj;
        } catch (Exception e) {
            throw new UnsupportedOperationException(e);
        }
    }

    @Override
    public java.lang.String realState() {
        try {
            String methodName = "realState";

            Class<?>[] parameterTypes = new Class<?>[] {  };

            Object[] parameterValues = new Object[] {  };

            java.lang.String returnObj = (java.lang.String) invokeOnRemoteModel(methodName,
                    parameterTypes, parameterValues);

            return returnObj;
        } catch (Exception e) {
            throw new UnsupportedOperationException(e);
        }
    }

    @Override
    public StagedModelType getStagedModelType() {
        return new StagedModelType(PortalUtil.getClassNameId(
                Idea.class.getName()));
    }

    public BaseModel<?> getIdeaRemoteModel() {
        return _ideaRemoteModel;
    }

    public void setIdeaRemoteModel(BaseModel<?> ideaRemoteModel) {
        _ideaRemoteModel = ideaRemoteModel;
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

        Class<?> remoteModelClass = _ideaRemoteModel.getClass();

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

        Object returnValue = method.invoke(_ideaRemoteModel,
                remoteParameterValues);

        if (returnValue != null) {
            returnValue = ClpSerializer.translateOutput(returnValue);
        }

        return returnValue;
    }

    @Override
    public void persist() throws SystemException {
        if (this.isNew()) {
            IdeaLocalServiceUtil.addIdea(this);
        } else {
            IdeaLocalServiceUtil.updateIdea(this);
        }
    }

    @Override
    public Idea toEscapedModel() {
        return (Idea) ProxyUtil.newProxyInstance(Idea.class.getClassLoader(),
            new Class[] { Idea.class }, new AutoEscapeBeanHandler(this));
    }

    @Override
    public Object clone() {
        IdeaClp clone = new IdeaClp();

        clone.setUuid(getUuid());
        clone.setIdeaId(getIdeaId());
        clone.setGroupId(getGroupId());
        clone.setCompanyId(getCompanyId());
        clone.setUserId(getUserId());
        clone.setUserName(getUserName());
        clone.setCreateDate(getCreateDate());
        clone.setModifiedDate(getModifiedDate());
        clone.setTitle(getTitle());
        clone.setLongDesc(getLongDesc());
        clone.setShortDesc(getShortDesc());
        clone.setUserGroupId(getUserGroupId());
        clone.setCallId(getCallId());
        clone.setState(getState());
        clone.setStateJudgement(getStateJudgement());
        clone.setDeadlineConstraints(getDeadlineConstraints());
        clone.setDiscussionLimit(getDiscussionLimit());
        clone.setStatus(getStatus());
        clone.setStatusByUserId(getStatusByUserId());
        clone.setStatusByUserName(getStatusByUserName());
        clone.setCategoryIds(getCategoryIds());

        return clone;
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

        if (!(obj instanceof IdeaClp)) {
            return false;
        }

        IdeaClp idea = (IdeaClp) obj;

        long primaryKey = idea.getPrimaryKey();

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
        StringBundler sb = new StringBundler(43);

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
        sb.append(", state=");
        sb.append(getState());
        sb.append(", stateJudgement=");
        sb.append(getStateJudgement());
        sb.append(", deadlineConstraints=");
        sb.append(getDeadlineConstraints());
        sb.append(", discussionLimit=");
        sb.append(getDiscussionLimit());
        sb.append(", status=");
        sb.append(getStatus());
        sb.append(", statusByUserId=");
        sb.append(getStatusByUserId());
        sb.append(", statusByUserName=");
        sb.append(getStatusByUserName());
        sb.append(", categoryIds=");
        sb.append(getCategoryIds());
        sb.append("}");

        return sb.toString();
    }

    @Override
    public String toXmlString() {
        StringBundler sb = new StringBundler(67);

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
        sb.append(
            "<column><column-name>state</column-name><column-value><![CDATA[");
        sb.append(getState());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>stateJudgement</column-name><column-value><![CDATA[");
        sb.append(getStateJudgement());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>deadlineConstraints</column-name><column-value><![CDATA[");
        sb.append(getDeadlineConstraints());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>discussionLimit</column-name><column-value><![CDATA[");
        sb.append(getDiscussionLimit());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>status</column-name><column-value><![CDATA[");
        sb.append(getStatus());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>statusByUserId</column-name><column-value><![CDATA[");
        sb.append(getStatusByUserId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>statusByUserName</column-name><column-value><![CDATA[");
        sb.append(getStatusByUserName());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>categoryIds</column-name><column-value><![CDATA[");
        sb.append(getCategoryIds());
        sb.append("]]></column-value></column>");

        sb.append("</model>");

        return sb.toString();
    }
}
