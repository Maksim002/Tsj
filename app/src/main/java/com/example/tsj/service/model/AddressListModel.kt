package com.example.tsj.service.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class AddressListModel {

    @SerializedName("LicNumber")
    @Expose
    var licNumber: Int? = null
    @SerializedName("PlacementId")
    @Expose
    var placementId: Int? = null
    @SerializedName("Address")
    @Expose
    var address: String? = null

}
