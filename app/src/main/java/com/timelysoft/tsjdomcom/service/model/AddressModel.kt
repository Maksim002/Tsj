package com.timelysoft.tsjdomcom.service.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class AddressModel {

    @SerializedName("LicNumber")
    @Expose
    var licNumber: Int = 0
    @SerializedName("PlacementId")
    @Expose
    var placementId: Int = 0
    @SerializedName("Address")
    @Expose
    var address: String = " "

    override fun toString(): String {
        return address.toString()
    }


}
