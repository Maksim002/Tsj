package com.example.tsj.service.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CertificateRequest {
    @SerializedName("PlacementId")
    @Expose
    private Integer placementId;
    @SerializedName("Person")
    @Expose
    private PersonRequest person;
    @SerializedName("Relatives")
    @Expose
    private List<RelativeRequest> relatives = null;

    public Integer getPlacementId() {
        return placementId;
    }

    public void setPlacementId(Integer placementId) {
        this.placementId = placementId;
    }

    public PersonRequest getPerson() {
        return person;
    }

    public void setPerson(PersonRequest person) {
        this.person = person;
    }

    public List<RelativeRequest> getRelatives() {
        return relatives;
    }

    public void setRelatives(List<RelativeRequest> relatives) {
        this.relatives = relatives;
    }
}
