package com.example.tsj.service.model.news

import com.google.gson.annotations.SerializedName

class NewsCommentsModel(
    @SerializedName("Id") val id: Int,
    @SerializedName("PersonName") val personName: String,
    @SerializedName("PostDate") val postDate: String,
    @SerializedName("IsDeletable") val isDeletable: Boolean,
    @SerializedName("Message") val message: String
)