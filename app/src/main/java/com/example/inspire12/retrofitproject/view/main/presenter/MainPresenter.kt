package com.example.inspire12.retrofitproject.view.main.presenter

import com.example.inspire12.retrofitproject.model.Photo
import kotlinx.coroutines.experimental.Job
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.launch
import java.util.ArrayList

class MainPresenter(val mView: MainContract.View)
    : MainContract.Presenter{

    override fun loadSetReclyclerView(data: ArrayList<Photo>): Job = launch(UI) {
        mView.onLoadSetReclerView(true, data)
    }
}