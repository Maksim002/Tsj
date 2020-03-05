package com.example.tsj.service.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RequestTypeModel {
    @SerializedName("RequestTypeId")
    @Expose
    private Integer requestTypeId;
    @SerializedName("RequestTypeName")
    @Expose
    private String requestTypeName;

    public Integer getRequestTypeId() {
        return requestTypeId;
    }

    public void setRequestTypeId(Integer requestTypeId) {
        this.requestTypeId = requestTypeId;
    }

    public String getRequestTypeName() {
        return requestTypeName;
    }

    public void setRequestTypeName(String requestTypeName) {
        this.requestTypeName = requestTypeName;
    }

    @Override
    public String toString() {
        return requestTypeName;
    }
}
