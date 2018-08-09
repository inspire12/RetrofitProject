package com.example.inspire12.retrofitproject.view.main.presenter

import com.example.inspire12.retrofitproject.BaseContract
import com.example.inspire12.retrofitproject.model.Photo
import kotlinx.coroutines.experimental.Job
import java.util.ArrayList


interface MainContract {
    /**
     * Presenter에서 받은 View의 이벤트 처리
     */
    interface View : BaseContract.View{
        fun setPresenter(presenter:MainContract.Presenter)
        fun onLoadSetReclerView(success: Boolean, data: ArrayList<Photo>)
    }

    /**
     * View에서 전달된 이벤트 대한 처리
     */
    interface Presenter : BaseContract.Presenter{
        fun loadSetReclyclerView(data:ArrayList<Photo>): Job
    }
}