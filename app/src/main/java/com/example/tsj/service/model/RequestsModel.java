package com.example.tsj.service.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RequestsModel {
    @SerializedName("Id")
    @Expose
    private Integer id;
    @SerializedName("DateArrival")
    @Expose
    private String dateArrival;
    @SerializedName("RequestTypeName")
    @Expose
    private String requestTypeName;
    @SerializedName("RepairDesc")
    @Expose
    private String repairDesc;
    @SerializedName("StatusName")
    @Expose
    private String statusName;
    @SerializedName("StatusDate")
    @Expose
    private String statusDate;
    @SerializedName("ProviderName")
    @Expose
    private String providerName;
    @SerializedName("IsEditableAndCloseable")
    @Expose
    private Boolean isEditableAndCloseable;
    @SerializedName("Description")
    @Expose
    private String description;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDateArrival() {
        return dateArrival;
    }

    public void setDateArrival(String dateArrival) {
        this.dateArrival = dateArrival;
    }

    public String getRequestTypeName() {
        return requestTypeName;
    }

    public void setRequestTypeName(String requestTypeName) {
        this.requestTypeName = requestTypeName;
    }

    public String getRepairDesc() {
        return repairDesc;
    }

    public void setRepairDesc(String repairDesc) {
        this.repairDesc = repairDesc;
    }

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }

    public String getStatusDate() {
        return statusDate;
    }

    public void setStatusDate(String statusDate) {
        this.statusDate = statusDate;
    }

    public String getProviderName() {
        return providerName;
    }

    public void setProviderName(String providerName) {
        this.providerName = providerName;
    }

    public Boolean getIsEditableAndCloseable() {
        return isEditableAndCloseable;
    }

    public void setIsEditableAndCloseable(Boolean isEditableAndCloseable) {
        this.isEditableAndCloseable = isEditableAndCloseable;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
