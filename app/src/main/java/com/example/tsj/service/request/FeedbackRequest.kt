package com.example.tsj.service.request

import com.google.gson.annotations.SerializedName

class FeedbackRequest(

    @SerializedName("Name") val name: String,
    @SerializedName("Email") val email: String,
    @SerializedName("Message") val message: String

)