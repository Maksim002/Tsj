package com.timelysoft.tsjdomcom.service.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class ReferencesFullModel {
    @SerializedName("NumberFull")
    @Expose
    var numberFull: String = ""
    @SerializedName("ForDate")
    @Expose
    var forDate: String = ""
    @SerializedName("Id")
    @Expose
    var id: Int = 0
    @SerializedName("Person")
    @Expose
    lateinit var person: PersonModel
    @SerializedName("Relatives")
    @Expose
    var relatives: List<RelativeModel>? = null

}