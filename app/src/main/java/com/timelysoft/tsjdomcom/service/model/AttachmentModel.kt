package com.timelysoft.tsjdomcom.service.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class AttachmentModel{
    @SerializedName("FileName")
    @Expose
    var fileName: String = ""
    @SerializedName("FilePath")
    @Expose
    var filePath: String = ""
}