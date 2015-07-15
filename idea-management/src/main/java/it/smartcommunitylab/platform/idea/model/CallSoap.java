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
public class CallSoap implements Serializable {
    private String _uuid;
    private String _title;
    private long _callId;
    private long _groupId;
    private long _companyId;
    private long _userId;
    private String _userName;
    private Date _createDate;
    private Date _modifiedDate;
    private String _description;
    private Date _deadline;
    private Date _publicationDeadline;
    private Date _realizationDeadline;

    public CallSoap() {
    }

    public static CallSoap toSoapModel(Call model) {
        CallSoap soapModel = new CallSoap();

        soapModel.setUuid(model.getUuid());
        soapModel.setTitle(model.getTitle());
        soapModel.setCallId(model.getCallId());
        soapModel.setGroupId(model.getGroupId());
        soapModel.setCompanyId(model.getCompanyId());
        soapModel.setUserId(model.getUserId());
        soapModel.setUserName(model.getUserName());
        soapModel.setCreateDate(model.getCreateDate());
        soapModel.setModifiedDate(model.getModifiedDate());
        soapModel.setDescription(model.getDescription());
        soapModel.setDeadline(model.getDeadline());
        soapModel.setPublicationDeadline(model.getPublicationDeadline());
        soapModel.setRealizationDeadline(model.getRealizationDeadline());

        return soapModel;
    }

    public static CallSoap[] toSoapModels(Call[] models) {
        CallSoap[] soapModels = new CallSoap[models.length];

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModel(models[i]);
        }

        return soapModels;
    }

    public static CallSoap[][] toSoapModels(Call[][] models) {
        CallSoap[][] soapModels = null;

        if (models.length > 0) {
            soapModels = new CallSoap[models.length][models[0].length];
        } else {
            soapModels = new CallSoap[0][0];
        }

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModels(models[i]);
        }

        return soapModels;
    }

    public static CallSoap[] toSoapModels(List<Call> models) {
        List<CallSoap> soapModels = new ArrayList<CallSoap>(models.size());

        for (Call model : models) {
            soapModels.add(toSoapModel(model));
        }

        return soapModels.toArray(new CallSoap[soapModels.size()]);
    }

    public long getPrimaryKey() {
        return _callId;
    }

    public void setPrimaryKey(long pk) {
        setCallId(pk);
    }

    public String getUuid() {
        return _uuid;
    }

    public void setUuid(String uuid) {
        _uuid = uuid;
    }

    public String getTitle() {
        return _title;
    }

    public void setTitle(String title) {
        _title = title;
    }

    public long getCallId() {
        return _callId;
    }

    public void setCallId(long callId) {
        _callId = callId;
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

    public String getDescription() {
        return _description;
    }

    public void setDescription(String description) {
        _description = description;
    }

    public Date getDeadline() {
        return _deadline;
    }

    public void setDeadline(Date deadline) {
        _deadline = deadline;
    }

    public Date getPublicationDeadline() {
        return _publicationDeadline;
    }

    public void setPublicationDeadline(Date publicationDeadline) {
        _publicationDeadline = publicationDeadline;
    }

    public Date getRealizationDeadline() {
        return _realizationDeadline;
    }

    public void setRealizationDeadline(Date realizationDeadline) {
        _realizationDeadline = realizationDeadline;
    }
}
