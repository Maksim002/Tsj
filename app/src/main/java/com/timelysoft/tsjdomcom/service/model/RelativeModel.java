package com.timelysoft.tsjdomcom.service.model;

import com.timelysoft.tsjdomcom.utils.MyUtils;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RelativeModel {
    @SerializedName("Id")
    @Expose
    private Integer id;
    @SerializedName("RelativeId")
    @Expose
    private Integer relativeId;
    @SerializedName("DateOfBirth")
    @Expose
    private String dateOfBirth;
    @SerializedName("FullName")
    @Expose
    private String fullName;
    @SerializedName("RelativeName")
    @Expose
    private String relative;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDate() {
        return MyUtils.INSTANCE.toMyDate(dateOfBirth);
    }


    public String getRelative() {
        return relative;
    }

    public RelativeModel() {
    }


    public RelativeModel(Integer relativeId, String dateOfBirth, String fullName, String relative) {
        this.relativeId = relativeId;
        this.fullName = fullName;
        this.relative = relative;
        this.dateOfBirth = dateOfBirth;
    }

    public void setRelative(String relative) {
        this.relative = relative;
    }

    public Integer getRelativeId() {
        return relativeId;
    }

    public void setRelativeId(Integer relativeId) {
        this.relativeId = relativeId;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

}
