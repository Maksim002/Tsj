package com.example.tsj.model

import com.google.gson.annotations.SerializedName

class VoteModel (

    @SerializedName("Id") val id : Int,
    @SerializedName("Question") val question : String,
    @SerializedName("EndDate") val endDate : String
)