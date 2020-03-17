package com.example.tsj.model

import com.google.gson.annotations.SerializedName
//dastan
class VoteModel (

    @SerializedName("Id") val id : Int,
    @SerializedName("Question") val question : String,
    @SerializedName("EndDate") val endDate : String,
    @SerializedName("IsCanVote") val isCanVote : Boolean

)