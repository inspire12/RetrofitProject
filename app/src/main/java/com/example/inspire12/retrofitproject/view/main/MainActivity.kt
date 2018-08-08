package com.example.inspire12.retrofitproject.view.main

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.example.inspire12.retrofitproject.R
import com.example.inspire12.retrofitproject.model.DemoData
import com.example.inspire12.retrofitproject.model.Photo
import com.example.inspire12.retrofitproject.view.main.adapter.PhotoRecyclerAdapter
import com.example.inspire12.retrofitproject.network.NetworkUtils
import com.example.inspire12.retrofitproject.utils.CustomLog
import com.example.inspire12.retrofitproject.view.main.presenter.MainContract
import com.example.inspire12.retrofitproject.view.main.presenter.MainPresenter
import kotlinx.android.synthetic.main.activity_main.*

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*

class MainActivity : AppCompatActivity(), MainContract.View {

    companion object {
        val VIEW_MAIN = 0
        val VIEW_SUB = 1
        @JvmStatic
        val intent_list = "LIST"
        val intent_index = "INDEX"
    }
    private var mPresenter: MainContract.Presenter? = null
    private lateinit var responsePhotoList: ArrayList<Photo>

    init {
        // 생성시 mPresenter 초기화
        mPresenter = MainPresenter(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rvDataList.setHasFixedSize(true)
        val request: Call<DemoData> = NetworkUtils.loadService().loadData()
        request.enqueue(object : Callback<DemoData> {
            override fun onFailure(call: Call<DemoData>?, t: Throwable?) {
                CustomLog.d(t.toString())
            }

            override fun onResponse(call: Call<DemoData>?, response: Response<DemoData>?) {
                CustomLog.d("success")
                // 세팅
                val body = response?.body()
                responsePhotoList = body!!.photos!! as ArrayList<Photo>
//                rvDataList.layoutManager = LinearLayoutManager(baseContext)
//                rvDataList.adapter = PhotoRecyclerAdapter(responsePhotoList, baseContext)

                mPresenter?.loadSetReclyclerView(responsePhotoList)
            }
        })
    }

    override fun setPresenter(presenter: MainContract.Presenter) {
        mPresenter = presenter
    }

    override fun onLoadSetReclerView(success: Boolean, data: ArrayList<Photo>) {
        if(success){
            rvDataList.layoutManager = LinearLayoutManager(baseContext)
            rvDataList.adapter = PhotoRecyclerAdapter(data, baseContext)
        }
    }

    override fun showProgress() {
    }

    override fun hideProgress() {
    }

    private fun initView(){
        rvDataList.layoutManager = LinearLayoutManager(baseContext)
        rvDataList.adapter = PhotoRecyclerAdapter(responsePhotoList, baseContext)

    }
}
