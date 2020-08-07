package com.timelysoft.tsjdomcom.service.model.service

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class ServiceModel (
    @SerializedName("ServicePaidName")
    @Expose
    var servicePaidName: String = "",

    @SerializedName("PeriodName")
    @Expose
    var periodName: String = "",

    @SerializedName("IsEditableAndCloseable")
    @Expose
    var isEditableAndCloseable: Boolean?,

    @SerializedName("Id")
    @Expose
    var id: Int = 0,

    @SerializedName("PeriodId")
    @Expose
    var periodId: Int = 0,

    @SerializedName("ServicePaidId")
    @Expose
    var servicePaidId: Int = 0,

    @SerializedName("IsActive")
    @Expose
    var isActive: Boolean?,

    @SerializedName("IsDependent")
    @Expose
    var isDependent: Boolean?,

    @SerializedName("IsVolumeMultiplier")
    @Expose
    var isVolumeMultiplier: Boolean?,

    @SerializedName("IsCounter")
    @Expose
    var isCounter: Boolean?,

    @SerializedName("Name")
    @Expose
    var name: String? = "",

    @SerializedName("Tariff")
    @Expose
    var tariff: Double = 0.0

)