package com.timelysoft.tsjdomcom.service.model.news

import com.google.gson.annotations.SerializedName

class NewsModel(
    @SerializedName("Id") val id: Int,
    @SerializedName("PersonName") val personName: String,
    @SerializedName("Title") val title: String,
    @SerializedName("Content") val content: String,
    @SerializedName("PostDate") val postDate: String

)
