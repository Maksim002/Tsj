package com.timelysoft.tsjdomcom.service.model.service

import com.google.gson.annotations.SerializedName

class CreateServiceModel (
    @SerializedName("PeriodId")
    var periodId: Int = 0,

    @SerializedName("ServicePaidId")
    var servicePaidId: Int = 0,

    @SerializedName("IsActive")
    var isActive: Boolean = false,

    @SerializedName("IsDependent")
    var isDependent: Boolean = false,

    @SerializedName("IsVolumeMultiplier")
    var isVolumeMultiplier: Boolean = false,

    @SerializedName("IsCounter")
    var isCounter: Boolean = false,

    @SerializedName("Name")
    var name: String = "",

    @SerializedName("Tariff")
    var tariff: Int = 0
)