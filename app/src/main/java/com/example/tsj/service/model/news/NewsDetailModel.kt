package com.example.tsj.service.model.news

import com.google.gson.annotations.SerializedName

class NewsDetailModel(
    @SerializedName("Attachments") val attachments: List<NewsAttachments>,
    @SerializedName("Id") val id: Int,
    @SerializedName("PersonName") val personName: String,
    @SerializedName("Title") val title: String,
    @SerializedName("Content") val content: String,
    @SerializedName("PostDate") val postDate: String
)