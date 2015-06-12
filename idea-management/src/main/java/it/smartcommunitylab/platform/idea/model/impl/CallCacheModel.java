package it.smartcommunitylab.platform.idea.model.impl;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.CacheModel;

import it.smartcommunitylab.platform.idea.model.Call;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing Call in entity cache.
 *
 * @author mirko perillo
 * @see Call
 * @generated
 */
public class CallCacheModel implements CacheModel<Call>, Externalizable {
    public String uuid;
    public String title;
    public long callId;
    public long groupId;
    public long companyId;
    public long userId;
    public String userName;
    public long createDate;
    public long modifiedDate;
    public String description;
    public long deadline;

    @Override
    public String toString() {
        StringBundler sb = new StringBundler(23);

        sb.append("{uuid=");
        sb.append(uuid);
        sb.append(", title=");
        sb.append(title);
        sb.append(", callId=");
        sb.append(callId);
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
        sb.append(", description=");
        sb.append(description);
        sb.append(", deadline=");
        sb.append(deadline);
        sb.append("}");

        return sb.toString();
    }

    @Override
    public Call toEntityModel() {
        CallImpl callImpl = new CallImpl();

        if (uuid == null) {
            callImpl.setUuid(StringPool.BLANK);
        } else {
            callImpl.setUuid(uuid);
        }

        if (title == null) {
            callImpl.setTitle(StringPool.BLANK);
        } else {
            callImpl.setTitle(title);
        }

        callImpl.setCallId(callId);
        callImpl.setGroupId(groupId);
        callImpl.setCompanyId(companyId);
        callImpl.setUserId(userId);

        if (userName == null) {
            callImpl.setUserName(StringPool.BLANK);
        } else {
            callImpl.setUserName(userName);
        }

        if (createDate == Long.MIN_VALUE) {
            callImpl.setCreateDate(null);
        } else {
            callImpl.setCreateDate(new Date(createDate));
        }

        if (modifiedDate == Long.MIN_VALUE) {
            callImpl.setModifiedDate(null);
        } else {
            callImpl.setModifiedDate(new Date(modifiedDate));
        }

        if (description == null) {
            callImpl.setDescription(StringPool.BLANK);
        } else {
            callImpl.setDescription(description);
        }

        if (deadline == Long.MIN_VALUE) {
            callImpl.setDeadline(null);
        } else {
            callImpl.setDeadline(new Date(deadline));
        }

        callImpl.resetOriginalValues();

        return callImpl;
    }

    @Override
    public void readExternal(ObjectInput objectInput) throws IOException {
        uuid = objectInput.readUTF();
        title = objectInput.readUTF();
        callId = objectInput.readLong();
        groupId = objectInput.readLong();
        companyId = objectInput.readLong();
        userId = objectInput.readLong();
        userName = objectInput.readUTF();
        createDate = objectInput.readLong();
        modifiedDate = objectInput.readLong();
        description = objectInput.readUTF();
        deadline = objectInput.readLong();
    }

    @Override
    public void writeExternal(ObjectOutput objectOutput)
        throws IOException {
        if (uuid == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(uuid);
        }

        if (title == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(title);
        }

        objectOutput.writeLong(callId);
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

        if (description == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(description);
        }

        objectOutput.writeLong(deadline);
    }
}
