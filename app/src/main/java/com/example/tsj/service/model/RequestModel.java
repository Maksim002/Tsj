package com.example.tsj.service.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RequestModel {
    @SerializedName("Address")
    @Expose
    private String address;
    @SerializedName("Entrance")
    @Expose
    private Integer entrance;
    @SerializedName("Floor")
    @Expose
    private Integer floor;
    @SerializedName("Id")
    @Expose
    private Integer id;
    @SerializedName("RequestTypeName")
    @Expose
    private String requestTypeName;
    @SerializedName("IsEditableAndCloseable")
    @Expose
    private Boolean isEditableAndCloseable;
    @SerializedName("Description")
    @Expose
    private String description;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getEntrance() {
        return entrance;
    }

    public void setEntrance(Integer entrance) {
        this.entrance = entrance;
    }

    public Integer getFloor() {
        return floor;
    }

    public void setFloor(Integer floor) {
        this.floor = floor;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRequestTypeName() {
        return requestTypeName;
    }

    public void setRequestTypeName(String requestTypeName) {
        this.requestTypeName = requestTypeName;
    }

    public Boolean getEditableAndCloseable() {
        return isEditableAndCloseable;
    }

    public void setEditableAndCloseable(Boolean editableAndCloseable) {
        isEditableAndCloseable = editableAndCloseable;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
