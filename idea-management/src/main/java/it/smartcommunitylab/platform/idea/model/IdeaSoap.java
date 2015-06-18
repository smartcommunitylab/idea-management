package it.smartcommunitylab.platform.idea.model;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * This class is used by SOAP remote services.
 *
 * @author mirko perillo
 * @generated
 */
public class IdeaSoap implements Serializable {
    private String _uuid;
    private long _ideaId;
    private long _groupId;
    private long _companyId;
    private long _userId;
    private String _userName;
    private Date _createDate;
    private Date _modifiedDate;
    private String _title;
    private String _longDesc;
    private String _shortDesc;
    private long _userGroupId;
    private long _callId;

    public IdeaSoap() {
    }

    public static IdeaSoap toSoapModel(Idea model) {
        IdeaSoap soapModel = new IdeaSoap();

        soapModel.setUuid(model.getUuid());
        soapModel.setIdeaId(model.getIdeaId());
        soapModel.setGroupId(model.getGroupId());
        soapModel.setCompanyId(model.getCompanyId());
        soapModel.setUserId(model.getUserId());
        soapModel.setUserName(model.getUserName());
        soapModel.setCreateDate(model.getCreateDate());
        soapModel.setModifiedDate(model.getModifiedDate());
        soapModel.setTitle(model.getTitle());
        soapModel.setLongDesc(model.getLongDesc());
        soapModel.setShortDesc(model.getShortDesc());
        soapModel.setUserGroupId(model.getUserGroupId());
        soapModel.setCallId(model.getCallId());

        return soapModel;
    }

    public static IdeaSoap[] toSoapModels(Idea[] models) {
        IdeaSoap[] soapModels = new IdeaSoap[models.length];

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModel(models[i]);
        }

        return soapModels;
    }

    public static IdeaSoap[][] toSoapModels(Idea[][] models) {
        IdeaSoap[][] soapModels = null;

        if (models.length > 0) {
            soapModels = new IdeaSoap[models.length][models[0].length];
        } else {
            soapModels = new IdeaSoap[0][0];
        }

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModels(models[i]);
        }

        return soapModels;
    }

    public static IdeaSoap[] toSoapModels(List<Idea> models) {
        List<IdeaSoap> soapModels = new ArrayList<IdeaSoap>(models.size());

        for (Idea model : models) {
            soapModels.add(toSoapModel(model));
        }

        return soapModels.toArray(new IdeaSoap[soapModels.size()]);
    }

    public long getPrimaryKey() {
        return _ideaId;
    }

    public void setPrimaryKey(long pk) {
        setIdeaId(pk);
    }

    public String getUuid() {
        return _uuid;
    }

    public void setUuid(String uuid) {
        _uuid = uuid;
    }

    public long getIdeaId() {
        return _ideaId;
    }

    public void setIdeaId(long ideaId) {
        _ideaId = ideaId;
    }

    public long getGroupId() {
        return _groupId;
    }

    public void setGroupId(long groupId) {
        _groupId = groupId;
    }

    public long getCompanyId() {
        return _companyId;
    }

    public void setCompanyId(long companyId) {
        _companyId = companyId;
    }

    public long getUserId() {
        return _userId;
    }

    public void setUserId(long userId) {
        _userId = userId;
    }

    public String getUserName() {
        return _userName;
    }

    public void setUserName(String userName) {
        _userName = userName;
    }

    public Date getCreateDate() {
        return _createDate;
    }

    public void setCreateDate(Date createDate) {
        _createDate = createDate;
    }

    public Date getModifiedDate() {
        return _modifiedDate;
    }

    public void setModifiedDate(Date modifiedDate) {
        _modifiedDate = modifiedDate;
    }

    public String getTitle() {
        return _title;
    }

    public void setTitle(String title) {
        _title = title;
    }

    public String getLongDesc() {
        return _longDesc;
    }

    public void setLongDesc(String longDesc) {
        _longDesc = longDesc;
    }

    public String getShortDesc() {
        return _shortDesc;
    }

    public void setShortDesc(String shortDesc) {
        _shortDesc = shortDesc;
    }

    public long getUserGroupId() {
        return _userGroupId;
    }

    public void setUserGroupId(long userGroupId) {
        _userGroupId = userGroupId;
    }

    public long getCallId() {
        return _callId;
    }

    public void setCallId(long callId) {
        _callId = callId;
    }
}
