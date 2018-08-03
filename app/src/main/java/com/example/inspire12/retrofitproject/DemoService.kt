package com.example.inspire12.retrofitproject

import com.example.inspire12.retrofitproject.Model.DemoData
import retrofit2.Call
import retrofit2.http.GET

interface DemoService {
    @GET("images")
    fun getData(): Call<DemoData>
}