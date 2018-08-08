package com.example.inspire12.retrofitproject.view.main.presenter

import android.view.View
import kotlinx.coroutines.experimental.Job
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.launch

class PhotoViewHolderPresenter (private val mView: PhotoViewHolderContract.View) : PhotoViewHolderContract.Presenter {


    init{
        mView.setPresenter(this)
    }

    // 이벤트 전달에 대한 처리


    override fun setSize(width: String?, height: String?) = launch (UI){
        // 3. 이벤트 전달에 대한 처리
        if(width!= null && height != null)
        // 4. View 로 데이터를 전달
            mView.onSetSize(width,height)
    }

    override fun setImage(src: String) = launch(UI) {

    }

    override fun bindItemClick(position:Int, listener: View.OnClickListener) = launch(UI) {

    }
}