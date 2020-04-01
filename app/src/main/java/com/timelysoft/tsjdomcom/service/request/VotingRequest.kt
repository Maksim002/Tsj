package com.timelysoft.tsjdomcom.service.request

import com.google.gson.annotations.SerializedName

class VotingRequest(
    @SerializedName("QuestionId") val questionId: Int,
    @SerializedName("VariantId") val variantId: Int,
    @SerializedName("PlacementId") val placementId: Int
)