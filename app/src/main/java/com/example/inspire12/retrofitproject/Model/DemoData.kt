package com.example.inspire12.retrofitproject.Model

import com.example.inspire12.retrofitproject.Model.Photo
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class DemoData {

    @SerializedName("stat")
    @Expose
    var stat: String? = null
    @SerializedName("page")
    @Expose
    var page: Int? = null
    @SerializedName("total_page")
    @Expose
    var totalPage: Int? = null
    @SerializedName("photos")
    @Expose
    var photos: List<Photo>? = null

}