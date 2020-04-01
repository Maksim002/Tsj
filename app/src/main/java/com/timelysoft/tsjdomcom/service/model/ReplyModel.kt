package com.timelysoft.tsjdomcom.service.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class ReplyModel {
    @SerializedName("HouseId")
    @Expose
    var houseId: Int = 0
    @SerializedName("PlacementId")
    @Expose
    var placementId: Int = 0
    @SerializedName("PersonId")
    @Expose
    var personId: Int = 0
    @SerializedName("IsToManager")
    @Expose
    var isToManager: Boolean = false

}
