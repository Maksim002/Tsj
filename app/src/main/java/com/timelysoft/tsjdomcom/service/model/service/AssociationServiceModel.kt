package com.timelysoft.tsjdomcom.service.model.service

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class AssociationServiceModel (
    @SerializedName("Association")
    @Expose
    var association: Association,

    @SerializedName("Services")
    @Expose
    var services: List<Service> = arrayListOf()

)