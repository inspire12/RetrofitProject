package com.example.inspire12.retrofitproject

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    val baseUrl = "http://demo2587971.mockable.io/"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var retrofit : Retrofit = Retrofit.Builder()
                .baseUrl(baseUrl).addConverterFactory(GsonConverterFactory.create())
                .build()
    }
}
