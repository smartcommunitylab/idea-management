package it.smartcommunitylab.platform.idea.model;

import com.liferay.portal.kernel.bean.AutoEscape;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.model.BaseModel;
import com.liferay.portal.model.CacheModel;
import com.liferay.portal.model.StagedGroupedModel;
import com.liferay.portal.service.ServiceContext;

import com.liferay.portlet.expando.model.ExpandoBridge;

import java.io.Serializable;

import java.util.Date;

/**
 * The base model interface for the Idea service. Represents a row in the &quot;IM_Idea&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This interface and its corresponding implementation {@link it.smartcommunitylab.platform.idea.model.impl.IdeaModelImpl} exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link it.smartcommunitylab.platform.idea.model.impl.IdeaImpl}.
 * </p>
 *
 * @author mirko perillo
 * @see Idea
 * @see it.smartcommunitylab.platform.idea.model.impl.IdeaImpl
 * @see it.smartcommunitylab.platform.idea.model.impl.IdeaModelImpl
 * @generated
 */
public interface IdeaModel extends BaseModel<Idea>, StagedGroupedModel {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this interface directly. All methods that expect a idea model instance should use the {@link Idea} interface instead.
     */

    /**
     * Returns the primary key of this idea.
     *
     * @return the primary key of this idea
     */
    public long getPrimaryKey();

    /**
     * Sets the primary key of this idea.
     *
     * @param primaryKey the primary key of this idea
     */
    public void setPrimaryKey(long primaryKey);

    /**
     * Returns the uuid of this idea.
     *
     * @return the uuid of this idea
     */
    @AutoEscape
    @Override
    public String getUuid();

    /**
     * Sets the uuid of this idea.
     *
     * @param uuid the uuid of this idea
     */
    @Override
    public void setUuid(String uuid);

    /**
     * Returns the idea ID of this idea.
     *
     * @return the idea ID of this idea
     */
    public long getIdeaId();

    /**
     * Sets the idea ID of this idea.
     *
     * @param ideaId the idea ID of this idea
     */
    public void setIdeaId(long ideaId);

    /**
     * Returns the group ID of this idea.
     *
     * @return the group ID of this idea
     */
    @Override
    public long getGroupId();

    /**
     * Sets the group ID of this idea.
     *
     * @param groupId the group ID of this idea
     */
    @Override
    public void setGroupId(long groupId);

    /**
     * Returns the company ID of this idea.
     *
     * @return the company ID of this idea
     */
    @Override
    public long getCompanyId();

    /**
     * Sets the company ID of this idea.
     *
     * @param companyId the company ID of this idea
     */
    @Override
    public void setCompanyId(long companyId);

    /**
     * Returns the user ID of this idea.
     *
     * @return the user ID of this idea
     */
    @Override
    public long getUserId();

    /**
     * Sets the user ID of this idea.
     *
     * @param userId the user ID of this idea
     */
    @Override
    public void setUserId(long userId);

    /**
     * Returns the user uuid of this idea.
     *
     * @return the user uuid of this idea
     * @throws SystemException if a system exception occurred
     */
    @Override
    public String getUserUuid() throws SystemException;

    /**
     * Sets the user uuid of this idea.
     *
     * @param userUuid the user uuid of this idea
     */
    @Override
    public void setUserUuid(String userUuid);

    /**
     * Returns the user name of this idea.
     *
     * @return the user name of this idea
     */
    @AutoEscape
    @Override
    public String getUserName();

    /**
     * Sets the user name of this idea.
     *
     * @param userName the user name of this idea
     */
    @Override
    public void setUserName(String userName);

    /**
     * Returns the create date of this idea.
     *
     * @return the create date of this idea
     */
    @Override
    public Date getCreateDate();

    /**
     * Sets the create date of this idea.
     *
     * @param createDate the create date of this idea
     */
    @Override
    public void setCreateDate(Date createDate);

    /**
     * Returns the modified date of this idea.
     *
     * @return the modified date of this idea
     */
    @Override
    public Date getModifiedDate();

    /**
     * Sets the modified date of this idea.
     *
     * @param modifiedDate the modified date of this idea
     */
    @Override
    public void setModifiedDate(Date modifiedDate);

    /**
     * Returns the title of this idea.
     *
     * @return the title of this idea
     */
    @AutoEscape
    public String getTitle();

    /**
     * Sets the title of this idea.
     *
     * @param title the title of this idea
     */
    public void setTitle(String title);

    /**
     * Returns the long desc of this idea.
     *
     * @return the long desc of this idea
     */
    @AutoEscape
    public String getLongDesc();

    /**
     * Sets the long desc of this idea.
     *
     * @param longDesc the long desc of this idea
     */
    public void setLongDesc(String longDesc);

    /**
     * Returns the short desc of this idea.
     *
     * @return the short desc of this idea
     */
    @AutoEscape
    public String getShortDesc();

    /**
     * Sets the short desc of this idea.
     *
     * @param shortDesc the short desc of this idea
     */
    public void setShortDesc(String shortDesc);

    @Override
    public boolean isNew();

    @Override
    public void setNew(boolean n);

    @Override
    public boolean isCachedModel();

    @Override
    public void setCachedModel(boolean cachedModel);

    @Override
    public boolean isEscapedModel();

    @Override
    public Serializable getPrimaryKeyObj();

    @Override
    public void setPrimaryKeyObj(Serializable primaryKeyObj);

    @Override
    public ExpandoBridge getExpandoBridge();

    @Override
    public void setExpandoBridgeAttributes(BaseModel<?> baseModel);

    @Override
    public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge);

    @Override
    public void setExpandoBridgeAttributes(ServiceContext serviceContext);

    @Override
    public Object clone();

    @Override
    public int compareTo(it.smartcommunitylab.platform.idea.model.Idea idea);

    @Override
    public int hashCode();

    @Override
    public CacheModel<it.smartcommunitylab.platform.idea.model.Idea> toCacheModel();

    @Override
    public it.smartcommunitylab.platform.idea.model.Idea toEscapedModel();

    @Override
    public it.smartcommunitylab.platform.idea.model.Idea toUnescapedModel();

    @Override
    public String toString();

    @Override
    public String toXmlString();
}
