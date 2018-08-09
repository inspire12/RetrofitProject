package com.example.inspire12.retrofitproject.view.main.presenter

import com.example.inspire12.retrofitproject.model.DemoData
import com.example.inspire12.retrofitproject.model.Photo
import com.example.inspire12.retrofitproject.network.NetworkUtils
import com.example.inspire12.retrofitproject.utils.CustomLog
import kotlinx.coroutines.experimental.Job
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.ArrayList

class MainPresenter(val mView: MainContract.View)
    : MainContract.Presenter{

    override fun loadSetReclyclerView(): Job = launch(UI) {
        val request: Call<DemoData> = NetworkUtils.loadService().loadData()
        request.enqueue(object : Callback<DemoData> {
            override fun onFailure(call: Call<DemoData>?, t: Throwable?) {
                CustomLog.d(t.toString())
                mView.onLoadSetReclerView(false, null)
            }

            override fun onResponse(call: Call<DemoData>?, response: Response<DemoData>?) {
                CustomLog.d("success")
                // 세팅
                val body = response?.body()
                val data = body!!.photos!! as ArrayList<Photo>
                mView.onLoadSetReclerView(true, data)
            }
        })
    }
}