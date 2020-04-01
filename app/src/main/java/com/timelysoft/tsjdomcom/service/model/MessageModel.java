package com.timelysoft.tsjdomcom.service.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MessageModel {
    @SerializedName("Address")
    @Expose
    private String address;
    @SerializedName("Body")
    @Expose
    private String body;
    @SerializedName("Attachments")
    @Expose
    private List<AttachmentModel> attachments = null;
    @SerializedName("PersonNameHeader")
    @Expose
    private String personNameHeader;
    @SerializedName("IsToManager")
    @Expose
    private Boolean isToManager;
    @SerializedName("Id")
    @Expose
    private Integer id;
    @SerializedName("SendDate")
    @Expose
    private String sendDate;
    @SerializedName("PersonName")
    @Expose
    private String personName;
    @SerializedName("Title")
    @Expose
    private String title;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public List<AttachmentModel> getAttachments() {
        return attachments;
    }

    public void setAttachments(List<AttachmentModel> attachments) {
        this.attachments = attachments;
    }

    public String getPersonNameHeader() {
        return personNameHeader;
    }

    public void setPersonNameHeader(String personNameHeader) {
        this.personNameHeader = personNameHeader;
    }

    public Boolean getIsToManager() {
        return isToManager;
    }

    public void setIsToManager(Boolean isToManager) {
        this.isToManager = isToManager;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSendDate() {
        return sendDate;
    }

    public void setSendDate(String sendDate) {
        this.sendDate = sendDate;
    }

    public String getPersonName() {
        return personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
