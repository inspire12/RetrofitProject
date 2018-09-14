package com.example.inspire12.retrofitproject.loadData

import com.example.inspire12.retrofitproject.dataModel.DemoData
import com.example.inspire12.retrofitproject.dataModel.Photo
import com.example.inspire12.retrofitproject.observer.Observer
import com.example.inspire12.retrofitproject.observer.Publisher
import com.example.inspire12.retrofitproject.utils.CustomLog

import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import kotlin.collections.ArrayList

/*
* 데이터의 변화를 전달해주는 객체
*
* */

class Network : Publisher{

    private var observers :ArrayList<Observer>
    var data: ArrayList<Photo>? = null;
    init {
        observers = ArrayList()
    }

    fun loadData(baseUrl:String) {
        val retrofit: Retrofit = Retrofit.Builder()
                .baseUrl(baseUrl).addConverterFactory(GsonConverterFactory.create())
                .build()
        val service: DemoService = retrofit.create(DemoService::class.java)
        val request: Call<DemoData> = service.getData()

        request.enqueue(object : Callback<DemoData> {
            override fun onFailure(call: Call<DemoData>?, t: Throwable?) {
                CustomLog.d(t.toString())
            }

            override fun onResponse(call: Call<DemoData>?, response: Response<DemoData>?) {
                CustomLog.d("success")
                // data 전달, observer noti에 에서 알림
                data = response?.body()?.photos!! as ArrayList<Photo>
                notifyObserver()
            }
        })
    }

    /* Publisher: observer를 등록하는 부분*/
    override fun add(observer: Observer) {
        observers.add(observer)
    }

    override fun delete(observer: Observer) {
        observers.remove(observer)
    }

    override fun notifyObserver( ) {
        for(observer in observers){
            observer.update(data!!)
        }
    }
}
