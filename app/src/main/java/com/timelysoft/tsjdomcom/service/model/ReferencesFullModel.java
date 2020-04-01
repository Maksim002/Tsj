package com.timelysoft.tsjdomcom.service.model;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ReferencesFullModel {
    @SerializedName("NumberFull")
    @Expose
    private String numberFull;
    @SerializedName("ForDate")
    @Expose
    private String forDate;
    @SerializedName("Id")
    @Expose
    private Integer id;
    @SerializedName("Person")
    @Expose
    private PersonModel person;
    @SerializedName("Relatives")
    @Expose
    private List<RelativeModel> relatives = null;

    public String getNumberFull() {
        return numberFull;
    }

    public void setNumberFull(String numberFull) {
        this.numberFull = numberFull;
    }

    public String getForDate() {
        return forDate;
    }

    public void setForDate(String forDate) {
        this.forDate = forDate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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
