package com.example.inspire12.retrofitproject.view.main.presenter

import android.view.View
import com.example.inspire12.retrofitproject.BaseContract
import kotlinx.coroutines.experimental.Job

interface PhotoViewHolderContract {
    interface View: BaseContract.View{
        fun setPresenter(presenter: Presenter)

        fun onSetSize(width:String?, height: String?)
        fun onSetImage(src: String)
    }
    interface Presenter: BaseContract.Presenter{
        fun setSize(width:String?, height: String?): Job
        fun setImage(src: String): Job
        fun bindItemClick(position:Int, listener: android.view.View.OnClickListener):Job
    }
}