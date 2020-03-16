package com.example.tsj.service.request;

import com.example.tsj.utils.MyUtils;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RelativeRequest {
    @SerializedName("RelativeId")
    @Expose
    private Integer relativeId;
    @SerializedName("DateOfBirth")
    @Expose
    private String dateOfBirth;
    @SerializedName("FullName")
    @Expose
    private String fullName;

    private String relative;
    private String date;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getRelative() {
        return relative;
    }

    public RelativeRequest() {
    }

    public RelativeRequest(Integer relativeId, String dateOfBirth, String fullName, String relative) {
        this.relativeId = relativeId;
        this.date = dateOfBirth;
        this.fullName = fullName;
        this.relative = relative;
        this.dateOfBirth = MyUtils.INSTANCE.toServerDate(dateOfBirth);
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
