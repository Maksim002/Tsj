package com.example.tsj.service.request

import com.google.gson.annotations.SerializedName

data class ToManager(
    @SerializedName("Body") val body: String,
    @SerializedName("Title") val title: String
)