package com.example.inspire12.retrofitproject.view.main

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.example.inspire12.retrofitproject.R
import com.example.inspire12.retrofitproject.model.Photo
import com.example.inspire12.retrofitproject.view.main.adapter.PhotoRecyclerAdapter
import com.example.inspire12.retrofitproject.view.main.presenter.MainContract
import com.example.inspire12.retrofitproject.view.main.presenter.MainPresenter
import kotlinx.android.synthetic.main.activity_main.*

import java.util.*

class MainActivity : AppCompatActivity(), MainContract.View {
    private var mPresenter: MainContract.Presenter? = null

    init {
        // 생성시 mPresenter 초기화
        mPresenter = MainPresenter(this)
    }

    companion object {
        val VIEW_MAIN = 0
        val VIEW_SUB = 1
        @JvmStatic
        val intent_list = "LIST"
        val intent_index = "INDEX"
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mPresenter?.loadSetReclyclerView()
    }

    override fun setPresenter(presenter: MainContract.Presenter) {
        mPresenter = presenter
    }

    override fun onLoadSetReclerView(success: Boolean, data: ArrayList<Photo>?) {
        if(success){
            rvDataList.setHasFixedSize(true)
            rvDataList.layoutManager = LinearLayoutManager(baseContext)
            rvDataList.adapter = PhotoRecyclerAdapter(data!!, baseContext)
        }else{
            /* nothing */
        }
    }

    override fun showProgress() {
    }

    override fun hideProgress() {
    }
}
