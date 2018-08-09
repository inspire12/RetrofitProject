package com.example.inspire12.retrofitproject.view.main.presenter

import android.content.Context
import com.example.inspire12.retrofitproject.view.main.MainActivity
import com.example.inspire12.retrofitproject.model.Photo
import com.example.inspire12.retrofitproject.R
import com.squareup.picasso.Picasso
import com.squareup.picasso.RequestCreator
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.launch

class PhotoAdapterPresenter : PhotoAdapterContract.Presenter{

    var view: PhotoAdapterContract.View? = null

    fun getViewRes(viewType:Int): Int{
        var res: Int = R.layout.item_layout

        if (viewType != MainActivity.VIEW_MAIN) {
            res = R.layout.item_layout_sub
        }
        return res
    }

    override fun loadImage(context: Context, url: String) : RequestCreator {
        return Picasso.with(context)
                .load(url).placeholder(R.drawable.empty).error(R.drawable.empty)
    }

}
