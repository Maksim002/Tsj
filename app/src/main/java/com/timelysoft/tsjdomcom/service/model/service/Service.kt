package com.timelysoft.tsjdomcom.service.model.service

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Service (
    @SerializedName("ServicePaidName")
    @Expose
    var servicePaidName: String? = null,

    @SerializedName("PeriodName")
    @Expose
    var periodName: String? = null,

    @SerializedName("IsEditableAndCloseable")
    @Expose
    var isEditableAndCloseable: Boolean? = null,

    @SerializedName("Id")
    @Expose
    var id: Int? = null,

    @SerializedName("PeriodId")
    @Expose
    var periodId: Int? = null,

    @SerializedName("ServicePaidId")
    @Expose
    var servicePaidId: Int? = null,

    @SerializedName("IsActive")
    @Expose
    var isActive: Boolean? = null,

    @SerializedName("IsDependent")
    @Expose
    var isDependent: Boolean? = null,

    @SerializedName("IsVolumeMultiplier")
    @Expose
    var isVolumeMultiplier: Boolean? = null,

    @SerializedName("IsCounter")
    @Expose
    var isCounter: Boolean? = null,

    @SerializedName("Name")
    @Expose
    var name: String? = null,

    @SerializedName("Tariff")
    @Expose
    var tariff: Int? = null

)