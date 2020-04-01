package com.timelysoft.tsjdomcom.service.model

import com.google.gson.annotations.SerializedName

class RequestForConnectModel(
    @SerializedName("Name") val name: String,
    @SerializedName("AssociationName") val associationName: String,
    @SerializedName("Address") val address: String,
    @SerializedName("PhoneNumber") val phoneNumber: String,
    @SerializedName("Email") val email: String,
    @SerializedName("PlacementNumber") val placementNumber: Int,
    @SerializedName("Message") val message: String
)