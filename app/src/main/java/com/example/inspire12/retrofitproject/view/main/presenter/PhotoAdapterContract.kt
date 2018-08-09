package com.example.inspire12.retrofitproject.view.main.presenter

import android.content.Context
import com.squareup.picasso.RequestCreator
import kotlinx.coroutines.experimental.Job

interface PhotoAdapterContract {

    interface View {
        fun setPresenter(presenter: Presenter)
        fun onGetViewType(position: Int)
    }

    interface Presenter{
        fun loadImage(context: Context, url: String): RequestCreator
        fun getNotifyAdapter():Job
        fun getViewType(height: String?): Job
    }
}