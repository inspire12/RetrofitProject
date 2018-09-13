package com.example.inspire12.retrofitproject.loadData

import com.example.inspire12.retrofitproject.dataModel.DemoData
import retrofit2.Call
import retrofit2.http.GET

interface DemoService {
    @GET("images")
    fun getData(): Call<DemoData>
}