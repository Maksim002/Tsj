package com.example.tsj.service.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AddRequest {
    @SerializedName("PlacementId")
    @Expose
    private Integer placementId;
    @SerializedName("RequestTypeId")
    @Expose
    private Integer requestTypeId;
    @SerializedName("Entrance")
    @Expose
    private Integer entrance;
    @SerializedName("Floor")
    @Expose
    private Integer floor;
    @SerializedName("Description")
    @Expose
    private String description;

    public Integer getPlacementId() {
        return placementId;
    }

    public void setPlacementId(Integer placementId) {
        this.placementId = placementId;
    }

    public Integer getRequestTypeId() {
        return requestTypeId;
    }

    public void setRequestTypeId(Integer requestTypeId) {
        this.requestTypeId = requestTypeId;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public AddRequest(Integer placementId, Integer requestTypeId, Integer entrance, Integer floor, String description) {
        this.placementId = placementId;
        this.requestTypeId = requestTypeId;
        this.entrance = entrance;
        this.floor = floor;
        this.description = description;
    }

    public AddRequest() {
    }
}
