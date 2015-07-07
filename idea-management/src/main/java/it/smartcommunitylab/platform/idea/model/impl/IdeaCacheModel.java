package it.smartcommunitylab.platform.idea.model.impl;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.CacheModel;

import it.smartcommunitylab.platform.idea.model.Idea;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing Idea in entity cache.
 *
 * @author mirko perillo
 * @see Idea
 * @generated
 */
public class IdeaCacheModel implements CacheModel<Idea>, Externalizable {
    public String uuid;
    public long ideaId;
    public long groupId;
    public long companyId;
    public long userId;
    public String userName;
    public long createDate;
    public long modifiedDate;
    public String title;
    public String longDesc;
    public String shortDesc;
    public long userGroupId;
    public long callId;
    public String state;

    @Override
    public String toString() {
        StringBundler sb = new StringBundler(29);

        sb.append("{uuid=");
        sb.append(uuid);
        sb.append(", ideaId=");
        sb.append(ideaId);
        sb.append(", groupId=");
        sb.append(groupId);
        sb.append(", companyId=");
        sb.append(companyId);
        sb.append(", userId=");
        sb.append(userId);
        sb.append(", userName=");
        sb.append(userName);
        sb.append(", createDate=");
        sb.append(createDate);
        sb.append(", modifiedDate=");
        sb.append(modifiedDate);
        sb.append(", title=");
        sb.append(title);
        sb.append(", longDesc=");
        sb.append(longDesc);
        sb.append(", shortDesc=");
        sb.append(shortDesc);
        sb.append(", userGroupId=");
        sb.append(userGroupId);
        sb.append(", callId=");
        sb.append(callId);
        sb.append(", state=");
        sb.append(state);
        sb.append("}");

        return sb.toString();
    }

    @Override
    public Idea toEntityModel() {
        IdeaImpl ideaImpl = new IdeaImpl();

        if (uuid == null) {
            ideaImpl.setUuid(StringPool.BLANK);
        } else {
            ideaImpl.setUuid(uuid);
        }

        ideaImpl.setIdeaId(ideaId);
        ideaImpl.setGroupId(groupId);
        ideaImpl.setCompanyId(companyId);
        ideaImpl.setUserId(userId);

        if (userName == null) {
            ideaImpl.setUserName(StringPool.BLANK);
        } else {
            ideaImpl.setUserName(userName);
        }

        if (createDate == Long.MIN_VALUE) {
            ideaImpl.setCreateDate(null);
        } else {
            ideaImpl.setCreateDate(new Date(createDate));
        }

        if (modifiedDate == Long.MIN_VALUE) {
            ideaImpl.setModifiedDate(null);
        } else {
            ideaImpl.setModifiedDate(new Date(modifiedDate));
        }

        if (title == null) {
            ideaImpl.setTitle(StringPool.BLANK);
        } else {
            ideaImpl.setTitle(title);
        }

        if (longDesc == null) {
            ideaImpl.setLongDesc(StringPool.BLANK);
        } else {
            ideaImpl.setLongDesc(longDesc);
        }

        if (shortDesc == null) {
            ideaImpl.setShortDesc(StringPool.BLANK);
        } else {
            ideaImpl.setShortDesc(shortDesc);
        }

        ideaImpl.setUserGroupId(userGroupId);
        ideaImpl.setCallId(callId);

        if (state == null) {
            ideaImpl.setState(StringPool.BLANK);
        } else {
            ideaImpl.setState(state);
        }

        ideaImpl.resetOriginalValues();

        return ideaImpl;
    }

    @Override
    public void readExternal(ObjectInput objectInput) throws IOException {
        uuid = objectInput.readUTF();
        ideaId = objectInput.readLong();
        groupId = objectInput.readLong();
        companyId = objectInput.readLong();
        userId = objectInput.readLong();
        userName = objectInput.readUTF();
        createDate = objectInput.readLong();
        modifiedDate = objectInput.readLong();
        title = objectInput.readUTF();
        longDesc = objectInput.readUTF();
        shortDesc = objectInput.readUTF();
        userGroupId = objectInput.readLong();
        callId = objectInput.readLong();
        state = objectInput.readUTF();
    }

    @Override
    public void writeExternal(ObjectOutput objectOutput)
        throws IOException {
        if (uuid == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(uuid);
        }

        objectOutput.writeLong(ideaId);
        objectOutput.writeLong(groupId);
        objectOutput.writeLong(companyId);
        objectOutput.writeLong(userId);

        if (userName == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(userName);
        }

        objectOutput.writeLong(createDate);
        objectOutput.writeLong(modifiedDate);

        if (title == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(title);
        }

        if (longDesc == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(longDesc);
        }

        if (shortDesc == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(shortDesc);
        }

        objectOutput.writeLong(userGroupId);
        objectOutput.writeLong(callId);

        if (state == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(state);
        }
    }
}
