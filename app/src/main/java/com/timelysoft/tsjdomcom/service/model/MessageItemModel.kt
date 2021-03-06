package com.timelysoft.tsjdomcom.service.model

import com.google.gson.annotations.SerializedName

data class  MessageItemModel(
    @SerializedName("Id") val id: Int,
    @SerializedName("IsRead") val isRead: Boolean,
    @SerializedName("SendDate") val sendDate: String,
    @SerializedName("PersonName") val personName: String,
    @SerializedName("Title") val title: String
)