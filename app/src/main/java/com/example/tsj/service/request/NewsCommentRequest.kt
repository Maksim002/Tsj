package com.example.tsj.service.request

import com.google.gson.annotations.SerializedName

class NewsCommentRequest (
    @SerializedName("NewsId") val newsId : Int,
    @SerializedName("Message") val message : String
)