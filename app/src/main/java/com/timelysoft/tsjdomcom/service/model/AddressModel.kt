package com.timelysoft.tsjdomcom.service.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class AddressModel {

    @SerializedName("LicNumber")
    @Expose
    var licNumber: Int? = null
    @SerializedName("PlacementId")
    @Expose
    var placementId: Int=0
    @SerializedName("Address")
    @Expose
    var address: String? = null

    override fun toString(): String {
        return address.toString()
    }


}
