package com.timelysoft.tsjdomcom.service.request;

import com.timelysoft.tsjdomcom.service.model.RelativeModel;
import com.timelysoft.tsjdomcom.service.model.PersonModel;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CertificateRequest {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("PlacementId")
    @Expose
    private Integer placementId;
    @SerializedName("Person")
    @Expose
    private PersonModel person;
    @SerializedName("Relatives")
    @Expose
    private List<RelativeModel> relatives = null;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPlacementId() {
        return placementId;
    }

    public void setPlacementId(Integer placementId) {
        this.placementId = placementId;
    }

    public PersonModel getPerson() {
        return person;
    }

    public void setPerson(PersonModel person) {
        this.person = person;
    }

    public List<RelativeModel> getRelatives() {
        return relatives;
    }

    public void setRelatives(List<RelativeModel> relatives) {
        this.relatives = relatives;
    }
}
