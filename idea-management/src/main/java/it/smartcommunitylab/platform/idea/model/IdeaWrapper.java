package it.smartcommunitylab.platform.idea.model;

import com.liferay.portal.kernel.lar.StagedModelType;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.ModelWrapper;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link Idea}.
 * </p>
 *
 * @author mirko perillo
 * @see Idea
 * @generated
 */
public class IdeaWrapper implements Idea, ModelWrapper<Idea> {
    private Idea _idea;

    public IdeaWrapper(Idea idea) {
        _idea = idea;
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

    /**
    * Returns the primary key of this idea.
    *
    * @return the primary key of this idea
    */
    @Override
    public long getPrimaryKey() {
        return _idea.getPrimaryKey();
    }

    /**
    * Sets the primary key of this idea.
    *
    * @param primaryKey the primary key of this idea
    */
    @Override
    public void setPrimaryKey(long primaryKey) {
        _idea.setPrimaryKey(primaryKey);
    }

    /**
    * Returns the uuid of this idea.
    *
    * @return the uuid of this idea
    */
    @Override
    public java.lang.String getUuid() {
        return _idea.getUuid();
    }

    /**
    * Sets the uuid of this idea.
    *
    * @param uuid the uuid of this idea
    */
    @Override
    public void setUuid(java.lang.String uuid) {
        _idea.setUuid(uuid);
    }

    /**
    * Returns the idea ID of this idea.
    *
    * @return the idea ID of this idea
    */
    @Override
    public long getIdeaId() {
        return _idea.getIdeaId();
    }

    /**
    * Sets the idea ID of this idea.
    *
    * @param ideaId the idea ID of this idea
    */
    @Override
    public void setIdeaId(long ideaId) {
        _idea.setIdeaId(ideaId);
    }

    /**
    * Returns the group ID of this idea.
    *
    * @return the group ID of this idea
    */
    @Override
    public long getGroupId() {
        return _idea.getGroupId();
    }

    /**
    * Sets the group ID of this idea.
    *
    * @param groupId the group ID of this idea
    */
    @Override
    public void setGroupId(long groupId) {
        _idea.setGroupId(groupId);
    }

    /**
    * Returns the company ID of this idea.
    *
    * @return the company ID of this idea
    */
    @Override
    public long getCompanyId() {
        return _idea.getCompanyId();
    }

    /**
    * Sets the company ID of this idea.
    *
    * @param companyId the company ID of this idea
    */
    @Override
    public void setCompanyId(long companyId) {
        _idea.setCompanyId(companyId);
    }

    /**
    * Returns the user ID of this idea.
    *
    * @return the user ID of this idea
    */
    @Override
    public long getUserId() {
        return _idea.getUserId();
    }

    /**
    * Sets the user ID of this idea.
    *
    * @param userId the user ID of this idea
    */
    @Override
    public void setUserId(long userId) {
        _idea.setUserId(userId);
    }

    /**
    * Returns the user uuid of this idea.
    *
    * @return the user uuid of this idea
    * @throws SystemException if a system exception occurred
    */
    @Override
    public java.lang.String getUserUuid()
        throws com.liferay.portal.kernel.exception.SystemException {
        return _idea.getUserUuid();
    }

    /**
    * Sets the user uuid of this idea.
    *
    * @param userUuid the user uuid of this idea
    */
    @Override
    public void setUserUuid(java.lang.String userUuid) {
        _idea.setUserUuid(userUuid);
    }

    /**
    * Returns the user name of this idea.
    *
    * @return the user name of this idea
    */
    @Override
    public java.lang.String getUserName() {
        return _idea.getUserName();
    }

    /**
    * Sets the user name of this idea.
    *
    * @param userName the user name of this idea
    */
    @Override
    public void setUserName(java.lang.String userName) {
        _idea.setUserName(userName);
    }

    /**
    * Returns the create date of this idea.
    *
    * @return the create date of this idea
    */
    @Override
    public java.util.Date getCreateDate() {
        return _idea.getCreateDate();
    }

    /**
    * Sets the create date of this idea.
    *
    * @param createDate the create date of this idea
    */
    @Override
    public void setCreateDate(java.util.Date createDate) {
        _idea.setCreateDate(createDate);
    }

    /**
    * Returns the modified date of this idea.
    *
    * @return the modified date of this idea
    */
    @Override
    public java.util.Date getModifiedDate() {
        return _idea.getModifiedDate();
    }

    /**
    * Sets the modified date of this idea.
    *
    * @param modifiedDate the modified date of this idea
    */
    @Override
    public void setModifiedDate(java.util.Date modifiedDate) {
        _idea.setModifiedDate(modifiedDate);
    }

    /**
    * Returns the title of this idea.
    *
    * @return the title of this idea
    */
    @Override
    public java.lang.String getTitle() {
        return _idea.getTitle();
    }

    /**
    * Sets the title of this idea.
    *
    * @param title the title of this idea
    */
    @Override
    public void setTitle(java.lang.String title) {
        _idea.setTitle(title);
    }

    /**
    * Returns the long desc of this idea.
    *
    * @return the long desc of this idea
    */
    @Override
    public java.lang.String getLongDesc() {
        return _idea.getLongDesc();
    }

    /**
    * Sets the long desc of this idea.
    *
    * @param longDesc the long desc of this idea
    */
    @Override
    public void setLongDesc(java.lang.String longDesc) {
        _idea.setLongDesc(longDesc);
    }

    /**
    * Returns the short desc of this idea.
    *
    * @return the short desc of this idea
    */
    @Override
    public java.lang.String getShortDesc() {
        return _idea.getShortDesc();
    }

    /**
    * Sets the short desc of this idea.
    *
    * @param shortDesc the short desc of this idea
    */
    @Override
    public void setShortDesc(java.lang.String shortDesc) {
        _idea.setShortDesc(shortDesc);
    }

    /**
    * Returns the user group ID of this idea.
    *
    * @return the user group ID of this idea
    */
    @Override
    public long getUserGroupId() {
        return _idea.getUserGroupId();
    }

    /**
    * Sets the user group ID of this idea.
    *
    * @param userGroupId the user group ID of this idea
    */
    @Override
    public void setUserGroupId(long userGroupId) {
        _idea.setUserGroupId(userGroupId);
    }

    /**
    * Returns the call ID of this idea.
    *
    * @return the call ID of this idea
    */
    @Override
    public long getCallId() {
        return _idea.getCallId();
    }

    /**
    * Sets the call ID of this idea.
    *
    * @param callId the call ID of this idea
    */
    @Override
    public void setCallId(long callId) {
        _idea.setCallId(callId);
    }

    @Override
    public boolean isNew() {
        return _idea.isNew();
    }

    @Override
    public void setNew(boolean n) {
        _idea.setNew(n);
    }

    @Override
    public boolean isCachedModel() {
        return _idea.isCachedModel();
    }

    @Override
    public void setCachedModel(boolean cachedModel) {
        _idea.setCachedModel(cachedModel);
    }

    @Override
    public boolean isEscapedModel() {
        return _idea.isEscapedModel();
    }

    @Override
    public java.io.Serializable getPrimaryKeyObj() {
        return _idea.getPrimaryKeyObj();
    }

    @Override
    public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
        _idea.setPrimaryKeyObj(primaryKeyObj);
    }

    @Override
    public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
        return _idea.getExpandoBridge();
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.liferay.portal.model.BaseModel<?> baseModel) {
        _idea.setExpandoBridgeAttributes(baseModel);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.liferay.portlet.expando.model.ExpandoBridge expandoBridge) {
        _idea.setExpandoBridgeAttributes(expandoBridge);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.liferay.portal.service.ServiceContext serviceContext) {
        _idea.setExpandoBridgeAttributes(serviceContext);
    }

    @Override
    public java.lang.Object clone() {
        return new IdeaWrapper((Idea) _idea.clone());
    }

    @Override
    public int compareTo(it.smartcommunitylab.platform.idea.model.Idea idea) {
        return _idea.compareTo(idea);
    }

    @Override
    public int hashCode() {
        return _idea.hashCode();
    }

    @Override
    public com.liferay.portal.model.CacheModel<it.smartcommunitylab.platform.idea.model.Idea> toCacheModel() {
        return _idea.toCacheModel();
    }

    @Override
    public it.smartcommunitylab.platform.idea.model.Idea toEscapedModel() {
        return new IdeaWrapper(_idea.toEscapedModel());
    }

    @Override
    public it.smartcommunitylab.platform.idea.model.Idea toUnescapedModel() {
        return new IdeaWrapper(_idea.toUnescapedModel());
    }

    @Override
    public java.lang.String toString() {
        return _idea.toString();
    }

    @Override
    public java.lang.String toXmlString() {
        return _idea.toXmlString();
    }

    @Override
    public void persist()
        throws com.liferay.portal.kernel.exception.SystemException {
        _idea.persist();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof IdeaWrapper)) {
            return false;
        }

        IdeaWrapper ideaWrapper = (IdeaWrapper) obj;

        if (Validator.equals(_idea, ideaWrapper._idea)) {
            return true;
        }

        return false;
    }

    @Override
    public StagedModelType getStagedModelType() {
        return _idea.getStagedModelType();
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedModel}
     */
    public Idea getWrappedIdea() {
        return _idea;
    }

    @Override
    public Idea getWrappedModel() {
        return _idea;
    }

    @Override
    public void resetOriginalValues() {
        _idea.resetOriginalValues();
    }
}
