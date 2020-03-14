package com.example.tsj.service.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PersonRequest {
    @SerializedName("DateOfBirth")
    @Expose
    private String dateOfBirth;
    @SerializedName("FullName")
    @Expose
    private String fullName;

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
