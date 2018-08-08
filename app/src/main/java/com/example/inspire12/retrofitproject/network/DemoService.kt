package com.example.inspire12.retrofitproject.network

import com.example.inspire12.retrofitproject.model.DemoData
import retrofit2.Call
import retrofit2.http.GET

interface DemoService {
    /**
     * 전체 데이터를 가져옴
     */
    @GET("images")
    fun loadData(): Call<DemoData>
}