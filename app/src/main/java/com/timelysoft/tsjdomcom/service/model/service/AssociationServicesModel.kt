package com.timelysoft.tsjdomcom.service.model.service

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class AssociationServicesModel (
    @SerializedName("Association")
    @Expose
    var association: AssociationModel? = null,

    @SerializedName("Services")
    @Expose
    var services: List<ServiceModel> = arrayListOf()

)