package com.example.tsj.service.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class NewsModel {
    @SerializedName("Id")
    @Expose
    var id: Int? = null
    @SerializedName("PersonName")
    @Expose
    var personName: String? = null
    @SerializedName("Title")
    @Expose
    var title: String? = null
    @SerializedName("Content")
    @Expose
    var content: String? = null
    @SerializedName("PostDate")
    @Expose
    var postDate: String? = null

}
