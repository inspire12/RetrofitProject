package com.example.inspire12.retrofitproject.view.main.presenter

import android.content.Context
import kotlinx.coroutines.experimental.Job

interface PhotoAdapterContract {

    interface View {
        fun setPresenter(presenter: Presenter)
        fun onGetViewType(position: Int)
    }

    interface Presenter{
        fun loadImage(context: Context, url: String):Job
        fun getNotifyAdapter():Job
        fun getViewType(height: String?): Job
    }
}