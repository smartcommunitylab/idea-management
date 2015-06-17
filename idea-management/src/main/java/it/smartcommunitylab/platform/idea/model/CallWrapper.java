package it.smartcommunitylab.platform.idea.model;

import com.liferay.portal.kernel.lar.StagedModelType;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.ModelWrapper;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link Call}.
 * </p>
 *
 * @author mirko perillo
 * @see Call
 * @generated
 */
public class CallWrapper implements Call, ModelWrapper<Call> {
    private Call _call;

    public CallWrapper(Call call) {
        _call = call;
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
    }

    /**
    * Returns the primary key of this call.
    *
    * @return the primary key of this call
    */
    @Override
    public long getPrimaryKey() {
        return _call.getPrimaryKey();
    }

    /**
    * Sets the primary key of this call.
    *
    * @param primaryKey the primary key of this call
    */
    @Override
    public void setPrimaryKey(long primaryKey) {
        _call.setPrimaryKey(primaryKey);
    }

    /**
    * Returns the uuid of this call.
    *
    * @return the uuid of this call
    */
    @Override
    public java.lang.String getUuid() {
        return _call.getUuid();
    }

    /**
    * Sets the uuid of this call.
    *
    * @param uuid the uuid of this call
    */
    @Override
    public void setUuid(java.lang.String uuid) {
        _call.setUuid(uuid);
    }

    /**
    * Returns the title of this call.
    *
    * @return the title of this call
    */
    @Override
    public java.lang.String getTitle() {
        return _call.getTitle();
    }

    /**
    * Sets the title of this call.
    *
    * @param title the title of this call
    */
    @Override
    public void setTitle(java.lang.String title) {
        _call.setTitle(title);
    }

    /**
    * Returns the call ID of this call.
    *
    * @return the call ID of this call
    */
    @Override
    public long getCallId() {
        return _call.getCallId();
    }

    /**
    * Sets the call ID of this call.
    *
    * @param callId the call ID of this call
    */
    @Override
    public void setCallId(long callId) {
        _call.setCallId(callId);
    }

    /**
    * Returns the group ID of this call.
    *
    * @return the group ID of this call
    */
    @Override
    public long getGroupId() {
        return _call.getGroupId();
    }

    /**
    * Sets the group ID of this call.
    *
    * @param groupId the group ID of this call
    */
    @Override
    public void setGroupId(long groupId) {
        _call.setGroupId(groupId);
    }

    /**
    * Returns the company ID of this call.
    *
    * @return the company ID of this call
    */
    @Override
    public long getCompanyId() {
        return _call.getCompanyId();
    }

    /**
    * Sets the company ID of this call.
    *
    * @param companyId the company ID of this call
    */
    @Override
    public void setCompanyId(long companyId) {
        _call.setCompanyId(companyId);
    }

    /**
    * Returns the user ID of this call.
    *
    * @return the user ID of this call
    */
    @Override
    public long getUserId() {
        return _call.getUserId();
    }

    /**
    * Sets the user ID of this call.
    *
    * @param userId the user ID of this call
    */
    @Override
    public void setUserId(long userId) {
        _call.setUserId(userId);
    }

    /**
    * Returns the user uuid of this call.
    *
    * @return the user uuid of this call
    * @throws SystemException if a system exception occurred
    */
    @Override
    public java.lang.String getUserUuid()
        throws com.liferay.portal.kernel.exception.SystemException {
        return _call.getUserUuid();
    }

    /**
    * Sets the user uuid of this call.
    *
    * @param userUuid the user uuid of this call
    */
    @Override
    public void setUserUuid(java.lang.String userUuid) {
        _call.setUserUuid(userUuid);
    }

    /**
    * Returns the user name of this call.
    *
    * @return the user name of this call
    */
    @Override
    public java.lang.String getUserName() {
        return _call.getUserName();
    }

    /**
    * Sets the user name of this call.
    *
    * @param userName the user name of this call
    */
    @Override
    public void setUserName(java.lang.String userName) {
        _call.setUserName(userName);
    }

    /**
    * Returns the create date of this call.
    *
    * @return the create date of this call
    */
    @Override
    public java.util.Date getCreateDate() {
        return _call.getCreateDate();
    }

    /**
    * Sets the create date of this call.
    *
    * @param createDate the create date of this call
    */
    @Override
    public void setCreateDate(java.util.Date createDate) {
        _call.setCreateDate(createDate);
    }

    /**
    * Returns the modified date of this call.
    *
    * @return the modified date of this call
    */
    @Override
    public java.util.Date getModifiedDate() {
        return _call.getModifiedDate();
    }

    /**
    * Sets the modified date of this call.
    *
    * @param modifiedDate the modified date of this call
    */
    @Override
    public void setModifiedDate(java.util.Date modifiedDate) {
        _call.setModifiedDate(modifiedDate);
    }

    /**
    * Returns the description of this call.
    *
    * @return the description of this call
    */
    @Override
    public java.lang.String getDescription() {
        return _call.getDescription();
    }

    /**
    * Sets the description of this call.
    *
    * @param description the description of this call
    */
    @Override
    public void setDescription(java.lang.String description) {
        _call.setDescription(description);
    }

    /**
    * Returns the deadline of this call.
    *
    * @return the deadline of this call
    */
    @Override
    public java.util.Date getDeadline() {
        return _call.getDeadline();
    }

    /**
    * Sets the deadline of this call.
    *
    * @param deadline the deadline of this call
    */
    @Override
    public void setDeadline(java.util.Date deadline) {
        _call.setDeadline(deadline);
    }

    /**
    * Returns the publication deadline of this call.
    *
    * @return the publication deadline of this call
    */
    @Override
    public java.util.Date getPublicationDeadline() {
        return _call.getPublicationDeadline();
    }

    /**
    * Sets the publication deadline of this call.
    *
    * @param publicationDeadline the publication deadline of this call
    */
    @Override
    public void setPublicationDeadline(java.util.Date publicationDeadline) {
        _call.setPublicationDeadline(publicationDeadline);
    }

    @Override
    public boolean isNew() {
        return _call.isNew();
    }

    @Override
    public void setNew(boolean n) {
        _call.setNew(n);
    }

    @Override
    public boolean isCachedModel() {
        return _call.isCachedModel();
    }

    @Override
    public void setCachedModel(boolean cachedModel) {
        _call.setCachedModel(cachedModel);
    }

    @Override
    public boolean isEscapedModel() {
        return _call.isEscapedModel();
    }

    @Override
    public java.io.Serializable getPrimaryKeyObj() {
        return _call.getPrimaryKeyObj();
    }

    @Override
    public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
        _call.setPrimaryKeyObj(primaryKeyObj);
    }

    @Override
    public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
        return _call.getExpandoBridge();
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.liferay.portal.model.BaseModel<?> baseModel) {
        _call.setExpandoBridgeAttributes(baseModel);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.liferay.portlet.expando.model.ExpandoBridge expandoBridge) {
        _call.setExpandoBridgeAttributes(expandoBridge);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.liferay.portal.service.ServiceContext serviceContext) {
        _call.setExpandoBridgeAttributes(serviceContext);
    }

    @Override
    public java.lang.Object clone() {
        return new CallWrapper((Call) _call.clone());
    }

    @Override
    public int compareTo(it.smartcommunitylab.platform.idea.model.Call call) {
        return _call.compareTo(call);
    }

    @Override
    public int hashCode() {
        return _call.hashCode();
    }

    @Override
    public com.liferay.portal.model.CacheModel<it.smartcommunitylab.platform.idea.model.Call> toCacheModel() {
        return _call.toCacheModel();
    }

    @Override
    public it.smartcommunitylab.platform.idea.model.Call toEscapedModel() {
        return new CallWrapper(_call.toEscapedModel());
    }

    @Override
    public it.smartcommunitylab.platform.idea.model.Call toUnescapedModel() {
        return new CallWrapper(_call.toUnescapedModel());
    }

    @Override
    public java.lang.String toString() {
        return _call.toString();
    }

    @Override
    public java.lang.String toXmlString() {
        return _call.toXmlString();
    }

    @Override
    public void persist()
        throws com.liferay.portal.kernel.exception.SystemException {
        _call.persist();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof CallWrapper)) {
            return false;
        }

        CallWrapper callWrapper = (CallWrapper) obj;

        if (Validator.equals(_call, callWrapper._call)) {
            return true;
        }

        return false;
    }

    @Override
    public StagedModelType getStagedModelType() {
        return _call.getStagedModelType();
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedModel}
     */
    public Call getWrappedCall() {
        return _call;
    }

    @Override
    public Call getWrappedModel() {
        return _call;
    }

    @Override
    public void resetOriginalValues() {
        _call.resetOriginalValues();
    }
}
