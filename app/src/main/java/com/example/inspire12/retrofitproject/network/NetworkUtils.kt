package com.example.inspire12.retrofitproject.network

import android.content.Context
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object NetworkUtils {
    fun loadService(): DemoService{
        val baseUrl = "http://demo2587971.mockable.io/"
        return Retrofit.Builder()
                .baseUrl(baseUrl).addConverterFactory(GsonConverterFactory.create())
                .build().create(DemoService::class.java)
    }
}