package com.example.inspire12.retrofitproject.view.main.presenter

import android.content.Context
import com.example.inspire12.retrofitproject.view.main.MainActivity
import com.example.inspire12.retrofitproject.model.Photo
import com.example.inspire12.retrofitproject.R
import com.squareup.picasso.Picasso
import com.squareup.picasso.RequestCreator
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.launch

//(private val mView: PhotoAdapterContract.View)
class PhotoAdapterPresenter : PhotoAdapterContract.Presenter{


    var view: PhotoAdapterContract.View? = null

    private var items = mutableListOf<Photo>()

    fun onDataChange(inputItems: List<Photo>){
        items.forEach{
            if(!items.contains(it)){
                items.add(it)
            }
        }
       // view?.onGetNotifyAdapter() // 리스너
    }

    fun getCount(): Int = items.size

    fun getItemAt(position: Int) = items[position]
    // infix??
    infix fun itemAtPosition(position: Int): Photo = items[position]


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


    override fun getNotifyAdapter() = launch(UI) {

    }

    override fun getViewType(height: String?)= launch(UI) {
//        if (height == null) {
//            return MainActivity.VIEW_SUB
//        } else {
//            return MainActivity.VIEW_MAIN
//        }
    }
}
