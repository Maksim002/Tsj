package com.timelysoft.tsjdomcom.service.model.vote

import com.google.gson.annotations.SerializedName

class VotingDetailModel (
    @SerializedName("Variants") val variants : List<VotingVariantsModel>,
    @SerializedName("Id") val id : Int,
    @SerializedName("Question") val question : String,
    @SerializedName("EndDate") val endDate : String,
    @SerializedName("IsCanVote") val isCanVote : Boolean
)