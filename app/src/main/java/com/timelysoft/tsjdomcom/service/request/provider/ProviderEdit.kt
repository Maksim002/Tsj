package com.timelysoft.tsjdomcom.service.request.provider

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class ProviderEdit (
    @SerializedName("Id")
    var id: Int = 0,

    @SerializedName("Name")
    var name: String = "",

    @SerializedName("Tin")
    var tin: String = "",

    @SerializedName("Bic")
    var bic: String = "",

    @SerializedName("Okpo")
    var okpo: String = "",

    @SerializedName("CheckingAccount")
    var checkingAccount: String = "",

    @SerializedName("OrganizationType")
    var organizationType: String = "",

    @SerializedName("Address")
    var address: String = "",

    @SerializedName("Phone")
    var phone: String = "",

    @SerializedName("Email")
    var email: String = ""
)