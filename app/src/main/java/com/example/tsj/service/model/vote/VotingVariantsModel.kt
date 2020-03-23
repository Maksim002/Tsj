package com.example.tsj.service.model.vote

import com.google.gson.annotations.SerializedName

class VotingVariantsModel (
    @SerializedName("Count") val count : Int,
    @SerializedName("Percent") val percent : Int,
    @SerializedName("Id") val id : Int,
    @SerializedName("Name") val name : String

)