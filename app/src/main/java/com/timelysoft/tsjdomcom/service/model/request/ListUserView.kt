package com.timelysoft.tsjdomcom.service.model.request

import com.google.gson.annotations.SerializedName

class ListUserView (
    @SerializedName("CreatedDate")
    var createdDate: String = "",

    @SerializedName("PersonName")
    var personName: String = "",

    @SerializedName("PlacementNumber")
    var placementNumber: Int = 0,

    @SerializedName("HouseAddress")
    var houseAddress: String = "",

    @SerializedName("Entrance")
    var entrance: Any = 0,

    @SerializedName("Floor")
    var floor: Int = 0,

    @SerializedName("RequestTypeName")
    var requestTypeName: String = "",

    @SerializedName("Description")
    var description: String = "",

    @SerializedName("Id")
    var id: Int = 0,

    @SerializedName("StatusId")
    var statusId: Int = 0,

    @SerializedName("ClosedDate")
    var closedDate: Any = 0,

    @SerializedName("RepairDesc")
    var repairDesc: Any = 0

)