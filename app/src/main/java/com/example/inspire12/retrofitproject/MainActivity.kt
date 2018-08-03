package com.example.inspire12.retrofitproject

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.inspire12.retrofitproject.Model.DemoData
import com.example.inspire12.retrofitproject.Utils.CustomLog
import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    val baseUrl = "http://demo2587971.mockable.io/"
    lateinit var responseData: JsonObject
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var retrofit: Retrofit = Retrofit.Builder()
                .baseUrl(baseUrl).addConverterFactory(GsonConverterFactory.create())
                .build()

        var service: DemoService = retrofit.create(DemoService::class.java)

        var request:Call<DemoData> = service.getData()

        request.enqueue(object:Callback<DemoData>{
            override fun onFailure(call: Call<DemoData>?, t: Throwable?) {
                CustomLog.d(t.toString())
            }

            override fun onResponse(call: Call<DemoData>?, response: Response<DemoData>?) {
                CustomLog.d("success")
                CustomLog.d(response!!.body()!!.page.toString())

            }
        })
    }
}
