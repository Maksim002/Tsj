package com.example.tsj.service.model.news

import com.google.gson.annotations.SerializedName

class NewsAttachments (

    @SerializedName("Id") val id : Int,
    @SerializedName("Type") val type : Int,
    @SerializedName("FileName") val fileName : String,
    @SerializedName("FilePath") val filePath : String

)