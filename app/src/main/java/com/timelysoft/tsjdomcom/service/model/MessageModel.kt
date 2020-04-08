package com.timelysoft.tsjdomcom.service.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class MessageModel {
    @SerializedName("Address")
    @Expose
    var address: String? = ""
    @SerializedName("Body")
    @Expose
    var body: String? = ""
    @SerializedName("Attachments")
    @Expose
    var attachments: List<AttachmentModel>? = null
    @SerializedName("PersonNameHeader")
    @Expose
    var personNameHeader: String = ""
    @SerializedName("IsToManager")
    @Expose
    var isToManager: Boolean = false
    @SerializedName("Id")
    @Expose
    var id: Int = 0
    @SerializedName("SendDate")
    @Expose
    var sendDate: String = ""
    @SerializedName("PersonName")
    @Expose
    var personName: String = ""
    @SerializedName("Title")
    @Expose
    var title: String = ""

}