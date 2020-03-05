package com.example.tsj.service.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RequestAddressesModel {

    @SerializedName("PlacementId")
    @Expose
    private Integer placementId;
    @SerializedName("Address")
    @Expose
    private String address;

    public Integer getPlacementId() {
        return placementId;
    }

    public void setPlacementId(Integer placementId) {
        this.placementId = placementId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }


    @Override
    public String toString() {
        return address;
    }
}
