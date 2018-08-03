package com.example.inspire12.retrofitproject.Model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Photo {

    @SerializedName("date_taken")
    @Expose
    var dateTaken: String? = null
    @SerializedName("title")
    @Expose
    var title: String? = null
    @SerializedName("width")
    @Expose
    var width: String? = null
    @SerializedName("url")
    @Expose
    var url: String? = null
    @SerializedName("height")
    @Expose
    var height: String? = null

}