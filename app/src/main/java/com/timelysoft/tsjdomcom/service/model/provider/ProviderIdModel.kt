package com.timelysoft.tsjdomcom.service.model.provider

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class ProviderIdModel (
    @SerializedName("Id")
    @Expose
    var id: Int,

    @SerializedName("Name")
    @Expose
    var name: String,

    @SerializedName("Tin")
    @Expose
    var tin: String,

    @SerializedName("Bic")
    @Expose
    var bic: String,

    @SerializedName("Okpo")
    @Expose
    var okpo: String,

    @SerializedName("CheckingAccount")
    @Expose
    var checkingAccount: String,

    @SerializedName("OrganizationType")
    @Expose
    var organizationType: String,

    @SerializedName("Address")
    @Expose
    var address: String,

    @SerializedName("Phone")
    @Expose
    var phone: String,

    @SerializedName("Email")
    @Expose
    var email: String

)